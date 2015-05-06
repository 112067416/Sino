<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>装箱指示书打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		function doPrint(content, LODOP) {
			//LODOP.PRINT_INIT("aaa");
			//LODOP.SET_PRINTER_INDEXA("DPK510");
			LODOP.PRINT_INIT("ZXZS-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("ZXD");
			LODOP.SET_PRINT_PAGESIZE(1, 3300, 2795,"A3");
			//LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, -50, "1100px", "980px", content);
		//	LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", content);
			//打印预览
			LODOP.PREVIEW();
			//打印
		//	LODOP.PRINT();	
		}
		
		function autoPrint() {
			var oForm = document.forms["DataForm"];
			var zxno = oForm.elements["zxno"].value;
			var pageSize = oForm.elements["pageSize"].value;
			if(zxno == null) return;
			var LODOP = document.getElementById("lodop");
			var index = 0;
			var postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"current",value:index++},{name:"pages",value:pageSize}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(index < pageSize) {
					postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"current",value:index++},{name:"pages",value:pageSize}]);
					ajax.post("zxzs!print.do", postContent);
				} 
			};
			ajax.post("zxzs!print.do", postContent);
		}
		
		coco.addEventListener(window, "load", autoPrint);
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<form name="DataForm">
	<input type="hidden" name="zxno" value="${zxno }" />
	<input type="hidden" name="pageSize" value="${pageSize }" />
	</form>
	</body>
</html>