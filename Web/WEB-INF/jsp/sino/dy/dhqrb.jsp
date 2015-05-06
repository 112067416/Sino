<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订货确认表打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<SCRIPT type="text/javascript">
		function doPrint(content, LODOP) {
			//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
			LODOP.PRINT_INIT("DHQRB-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("DPK510");
			//像素 ：1380px ，958px
			//0.1毫米：3500, 2540
			//LODOP.SET_PRINT_PAGESIZE(1, "350mm", "254.0mm", "A3");
			LODOP.SET_PRINT_PAGESIZE(1, 3500, 2795,"A3");
			//LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 20, "1300px", "1010px", content);
		//	LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", content);
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}
		
		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var ids = oForm.elements["ids"].value;
			var arrs = ids.split(",");
			var id, index = 0, arr, $arr, pDhno, pDhnos = [];
			while(index < arrs.length) {
				id = arrs[index];
				index++;
				$arr = id.split("-");
				pDhno = $arr[0] + "-" + $arr[1];
				$arr[2] = ($arr[2] == null || $arr[2].length == 0 ? "" : $arr[2]);
				for(; index < arrs.length; index++) {
					arr = arrs[index].split("-");
					arr[2] = (arr[2] == null || arr[2].length == 0 ? "" : arr[2]);
					if(pDhno.indexOf(arr[0]) < 0 || pDhno.split(",").length >= 4 || $arr[2] != arr[2]) break;
					pDhno = pDhno + "," + arr[0] + "-" + arr[1];
				}
				pDhnos[pDhnos.length] = pDhno;
			}
			index = 0;
			var LODOP = document.getElementById("lodop");
			var postContent =  coco.parseParam("ids", pDhnos[index++]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(index < pDhnos.length) {
					postContent =  coco.parseParam("ids", pDhnos[index++]);
					ajax.post("dhqrb!print.do", postContent);
				}
			};
			ajax.post("dhqrb!print.do", postContent);
		}

		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<form name="DataForm"><input type="hidden" name="ids" value="${ids }" /></form>
	</body> 
</html>