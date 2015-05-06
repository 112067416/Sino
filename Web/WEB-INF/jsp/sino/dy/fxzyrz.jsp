<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分析作业日志打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		function doPrint(content, LODOP) {
			LODOP.PRINT_INIT("FXZYRZ-<%=System.currentTimeMillis()%>");
			//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(-15, -10, 1100, 800, content);
			LODOP.SET_PRINTER_INDEXA("HP LaserJet 5200 Series PCL 5");
			//打印预览
			LODOP.PREVIEW();
			//打印
			//LODOP.PRINT();
		}
		
		function toPrintFxzyrz(id, ajax, LODOP) {
			ajax.post("fxzyrz!print.do", coco.parseParam("id", id));
			doPrint(ajax.result, LODOP);
		}

		function toPrintFxzyrzYcsx(id, ajax, LODOP) {
			ajax.post("fxzyrzYcsx!print.do", coco.parseParam("pid", id));
			if(ajax.code <= 0) return;
			doPrint(ajax.result, LODOP);
		}

		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var ids = oForm.elements["ids"].value;
			var LODOP = document.getElementById("lodop");
			var ajax = new Cocoajax();
			ajax.async = false;
			var arrs = ids.split(",");
			for(var i = 0; i < arrs.length; i++) {
				toPrintFxzyrz(arrs[i], ajax, LODOP);
				toPrintFxzyrzYcsx(arrs[i], ajax, LODOP);
			}
		}

		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<form name="DataForm" action="#"><input type="hidden" name="ids" value="${ids }" /></form>
	</body>
</html>