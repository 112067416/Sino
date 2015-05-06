<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>用途统计单</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--

		function doPreview(oInput) {
			var oForm = oInput.form;
			var yearS = oForm.elements["yearS"].value;
			var yearE = oForm.elements["yearE"].value;
			var monthS = oForm.elements["monthS"].value;
			var monthE = oForm.elements["monthE"].value;
			var chart = oForm.elements["chart"].value;
			if(yearS == null || yearS.length == 0 || yearE == null || yearE.length == 0 || monthS == null || monthS.length == 0 || monthE == null || monthE.length == 0) {
				alert("出货日期不能为空");
				return;
			}
			var postContent = coco.parseParams([{name:"yearS",value:yearS},{name:"yearE",value:yearE},{name:"monthS",value:monthS},{name:"monthE",value:monthE},{name:"chart",value:chart}]);
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			var LODOP = document.getElementById("lodop");
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.dataDiv = document.getElementById("CondArea");
			ajax.callback = function() {
				coco.hideAlert();
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				this.oInput.disabled = false;
				doPrint(LODOP, this.result);
			};
			ajax.post("previewCond.do", postContent);
		}
		function doPrint(LODOP, result) {
			LODOP.PRINT_INIT("COND-<%=System.currentTimeMillis()%>");
			//LODOP.SET_PRINTER_INDEXA("ZPBQ");
			LODOP.SET_PRINT_PAGESIZE(1,"0","0","A4");
			LODOP.ADD_PRINT_HTM(0, 0, 1100, 960, result);
			//打印预览
			LODOP.PREVIEW();
			//打印
			//LODOP.PRINT();	
		}
		function outExcel() {
			var form = document.forms["DataForm"];
			if(!cocoform.verify(form))return;
			var content = cocoform.postContent(form);
			var wo = document.getElementById("WebOffice");
			//wo.HideMenuItem(0x33);
			if(window.self == window.top) wo.LoadOriginalFile("./outCpckdExcel.do?"+content, "xls");
			else wo.LoadOriginalFile("<%=request.getContextPath()%>/sino/yygl/khyf/outCpckdExcel.do?"+content, "xls");
			wo.hideMenuArea("hideall","","","");
		}
		
		//coco.addEventListener(window, "load", function() {
		//	var height = document.documentElement.clientHeight;
		//	var wo = document.getElementById("WebOffice");
		//	wo.style.height = height - 38;
		//});
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<ui:page title="加工作途统计">
		<form name="DataForm" method="post" >
		<table align="center" class="form" width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="50%" /></colgroup>
			<tr>
				<th style="text-align: left;">出货日期</th>
				<td><ui:int name="yearS" value="year" maxlength="4" required="true" />年<ui:int name="monthS" value="#1" maxlength="2" required="true" />月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<ui:int name="yearE" value="year" maxlength="4" required="true" />年<ui:int name="monthE" value="month" maxlength="2" required="true" />月</td>
				<th style="text-align: left;">图形类型</th>
				<td><ui:select name="chart" list="${chart }" headerText="请选择" headerValue=""  /></td>
			</tr>
		</table>
		<div class="submit"> <input type="button" class="button" value="预  览" onclick="doPreview(this);" /></div>	
		</form>
	</ui:page>
	<div id="CondArea" style="display: none;"></div>
	</body>
</html>