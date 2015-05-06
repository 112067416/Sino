<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
	prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>昨日出货实绩单打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		function doPrint(content, LODOP) {
			//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
			LODOP.PRINT_INIT("CHSJD-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(-15, -10, 1100, 800, content);
			//打印预览
			LODOP.PREVIEW();
			//打印
			//LODOP.PRINT();
		}
		
		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var pageCount = oForm.elements["pageCount"].value;
			var chriS = oForm.elements["chriS"].value;
			var chriE = oForm.elements["chriE"].value;
			var ysgs = oForm.elements["ysgs"].value;
			
			var LODOP = document.getElementById("lodop");
			var pageIndex = 0;
			var postContent = coco.parseParams([{name:"chriS",value:chriS},{name:"chriE",value:chriE},{name:"pageCount",value:pageCount},{name:"current",value:pageIndex++},{name:"ysgs",value:ysgs}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(pageIndex < pageCount) {
					postContent = postContent = coco.parseParams([{name:"chriS",value:chriS},{name:"chriE",value:chriE},{name:"pageCount",value:pageCount},{name:"current",value:pageIndex++},{name:"ysgs",value:ysgs}]);
					ajax.post("chsjd!print.do", postContent);
				}
			};
			ajax.post("chsjd!print.do", postContent);
		}
		
		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<form name="DataForm"><input type="hidden" name="pageCount" value="${pageCount }" /><input type="hidden" name="chriS" value="<f:v value="chriS" format="yyyy-MM-dd" />" /><input type="hidden" name="chriE" value="<f:v value="chriE" format="yyyy-MM-dd" />" /><input type="hidden" name="ysgs" value="${ysgs }" /></form>
	</body>
</html>