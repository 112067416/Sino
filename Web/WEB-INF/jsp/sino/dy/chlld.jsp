<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
	prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>次日出货联络单打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		function doPrint(result, LODOP) {
			//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
			LODOP.PRINT_INIT("CHLLD-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("ZPBQ");
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(2, -13, 1100, 760, result);
			//打印预览
			LODOP.PREVIEW();
			//打印
			//LODOP.PRINT();
		}
		
		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var pageCount = oForm.elements["pageCount"].value;
			var chqi = oForm.elements["chqi"].value;
			var ddno = oForm.elements["ddno"].value;
			
			var LODOP = document.getElementById("lodop");
			var pageIndex = 0;
			var postContent = coco.parseParams([{name:"chqi",value:chqi},{name:"ddno",value:ddno},{name:"pageCount",value:pageCount},{name:"current",value:pageIndex++}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(pageIndex < pageCount) {
					postContent = postContent = coco.parseParams([{name:"chqi",value:chqi},{name:"ddno",value:ddno},{name:"pageCount",value:pageCount},{name:"current",value:pageIndex++}]);
					ajax.post("chlld!print.do", postContent);
				}
			};
			ajax.post("chlld!print.do", postContent);
		}
		
		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
<body>
<ui:print id="lodop"/>
<form name="DataForm"><input type="hidden" name="pageCount" value="${pageCount }" /><input type="hidden" name="chqi" value="<f:v value="chqi" format="yyyy-MM-dd" />" /><input type="hidden" name="title" value="${title }" /><input type="hidden" name="ddno" value="${ddno }" /></form>
</body>
</html>