<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>仕样未确认打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		function doPrint(content, LODOP) {
			//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
			LODOP.PRINT_INIT("SYWQR-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("HP LaserJet 5200 Series PCL 5");
			LODOP.SET_PRINT_PAGESIZE(2, "0", "0","A3");
			LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 0, 1500, 1200, content);
			//像素 ：1380px ，958px
			//0.1毫米：3500, 2540
			//LODOP.SET_PRINT_PAGESIZE(1, "350mm", "254.0mm", "A3");
		//	LODOP.SET_PRINT_PAGESIZE(1, 3500, 2795,"A3");
			//LODOP.SET_PRINT_STYLE("FontSize",12);
		//	LODOP.ADD_PRINT_HTM(0, 20, "1300px", "1010px", content);
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
			var size = arrs.length;
			var pages = size / 3;
			if(size % 3 != 0) {
				pages++;
			}
			var dhnos = [], dhno, num, index = 0;
			for(var page = 0; page < pages; page++) {
				dhno = null;
				for(var i = 0; i < 3; i++) {
					num = i + page * 3;
					if(num >= size) break;
					if(dhno == null) {
						dhno = arrs[num];
						continue;
					}
					dhno = dhno + "," +  arrs[num];
				}
				if(dhno != null) {
					dhnos[index++] = dhno;
				}
			}
			pages = dhnos.length;
			index = 0;
			var LODOP = document.getElementById("lodop");
			var postContent =  coco.parseParams([{name:"ids",value:dhnos[index++]},{name:"pages",value:pages},{name:"current",value:index}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(index < pages) {
					postContent =  coco.parseParams([{name:"ids",value:dhnos[index++]},{name:"pages",value:pages},{name:"current",value:index}]);
					ajax.post("sywqr!print.do", postContent);
				}
			};
			ajax.post("sywqr!print.do", postContent);
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