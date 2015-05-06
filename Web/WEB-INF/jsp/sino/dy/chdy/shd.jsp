<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>送货单打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		function doPrint(content, LODOP) {
			//LODOP.PRINT_INIT("aaa");
			//LODOP.SET_PRINTER_INDEXA("DPK510");
			LODOP.PRINT_INIT("SHD-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("SHD");
			//2793和2794之间
			LODOP.SET_PRINT_PAGESIZE(1, 2500, 2794,"A4");
			//LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 0, "600px", "1480px", content);
			//打印预览
		//	LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}
		
		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var $zxnos = oForm.elements["zxnos"].value;
			if($zxnos == null || $zxnos.length == 0) return;
			var zxnos = $zxnos.split(",");
			var $pageSizes = oForm.elements["pageSizes"].value;
			var pageSizes = $pageSizes.split(",");
			var LODOP = document.getElementById("lodop");
			var index = 0, zxno = zxnos[0], pageSize = pageSizes[0], pageIndex = 0;
			index++;
			var postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"pages",value:pageSize},{name:"current",value:pageIndex++}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(pageIndex < pageSize) {
					postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"pages",value:pageSize},{name:"current",value:pageIndex++}]);
					ajax.post("shd!print.do", postContent);
				} else {
					if(index < zxnos.length) {
						zxno = zxnos[index];
						pageSize = pageSizes[index];
						index++;
						pageIndex = 0;
						postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"pages",value:pageSize},{name:"current",value:pageIndex++}]);
						ajax.post("shd!print.do", postContent);
					}
				}
			};
			ajax.post("shd!print.do", postContent);
		}
		
		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
<body>
<ui:print id="lodop"/>
<form name="DataForm"> 
<input type="hidden" name="zxnos" value="${zxnos }" /><input type="hidden" name="pageSizes" value="${pageSizes }" /></form>
</body>
</html>