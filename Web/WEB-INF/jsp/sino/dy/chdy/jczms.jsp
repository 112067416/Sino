<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>检查证明书打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--		
		function doPrint(content, LODOP, $height) {
			//LODOP.PRINT_INIT("aaa");
			//LODOP.SET_PRINTER_INDEXA("DPK510");
			LODOP.PRINT_INIT("JCZMS-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("DPK510");
			LODOP.SET_PRINT_PAGESIZE(1, 3500, $height,"A3");
			//LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 20, "1300px", "3980px", content);
		//	LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", content);
			//打印预览
		//	LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}		
		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var $uuids = oForm.elements["uuids"].value;
			if($uuids == null || $uuids.length == 0) return;
			var uuids = $uuids.split(",");
			var $pageSizes = oForm.elements["pageSizes"].value;
			var pageSizes = $pageSizes.split(",");
			var LODOP = document.getElementById("lodop");
			var index = 0, uuid = uuids[0], pageSize = pageSizes[0], pageIndex = 0, num = 0, $height;
			index++;
			var postContent = coco.parseParams([{name:"jczmsId",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				num++;
				$height = num % 3 == 0 ? 2793 : 2794;
				doPrint(this.result, LODOP, $height);
				if(pageIndex < pageSize) {
					postContent = coco.parseParams([{name:"jczmsId",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
					ajax.post("jczms!print.do", postContent);
				} else {
					if(index < uuids.length) {
						uuid = uuids[index];
						pageSize = pageSizes[index];
						index++;
						pageIndex = 0;
						postContent = coco.parseParams([{name:"jczmsId",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
						ajax.post("jczms!print.do", postContent);
					}
				}
			};
			ajax.post("jczms!print.do", postContent);
		}
		
		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<form name="DataForm"> 
	<input type="hidden" name="uuids" value="${uuids }" />
	<input type="hidden" name="pageSizes" value="${pageSizes }" />
	</form>
	</body>
</html>