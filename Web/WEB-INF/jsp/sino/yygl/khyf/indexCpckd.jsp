<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>成品出库单</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
		//打印操作
		function doPreview(postContent, num) {
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("CpckdArea");
			ajax.callback = function() {
				coco.hideAlert();
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(num);
			};
			ajax.post("previewCpckd.do", postContent);
		}

		function tjCpckd(oInput) {
			var oForm = oInput.form;
			var chriS = oForm.elements["chriS"].value;
			var chriE = oForm.elements["chriE"].value;
			if(chriS == null || chriS.length == 0) {
				alert("出货日期不能为空");
				return;
			}
			var postContent = coco.parseParams([{name:"chriS",value:chriS},{name:"chriE",value:chriE}]);
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				doPreview(postContent,parseInt(this.msg));
			};
			ajax.post("tjCpckd.do", postContent);
		}
		function doPrint(num) {
			var LODOP = document.getElementById("lodop");
			LODOP.PRINT_INIT("CPCKD-<%=System.currentTimeMillis()%>");
			var dataDiv;
			for(var i = 1; i <= num; i++) {
				dataDiv = document.getElementById("div"+i);
				if(dataDiv == null) continue;
				LODOP.ADD_PRINT_HTM(0,0,150,600,dataDiv.innerHTML);
				if(i < num) {
					LODOP.NewPage();
					continue;
				}
			}
			LODOP.PREVIEW();
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
		
		function resize() {
			var height = document.documentElement.clientHeight;
			var queryHeight = document.getElementById("QueryArea").offsetHeight;
			document.getElementById("ExcelArea").style.height = (height - queryHeight- 3) + "px";
		};
		
		coco.addEventListener(window, "load", resize);
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<div id="QueryArea">
		<form name="DataForm" method="post" >
		<table align="center" class="form" width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup><col width="10%" /><col width="30%" /><col width="60%" /></colgroup>
			<tr>
				<th style="text-align: left;">出货日期</th>
				<td><ui:date name="chriS" value="chriS"  required="true" />至<ui:date name="chriE" value="chriE" /></td>
				<td><input type="button" class="button" value="检索(Q)" accesskey="q" onclick="outExcel();" /></td>
			</tr>
		</table>
		</form>
		</div>
		<div id="ExcelArea"><OBJECT id="WebOffice" style="LEFT: 0px; TOP: 0px; WIDTH: 100%;" height="100%" classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5"  codebase="../../activex/weboffice_v6.0.5.0.cab#V6,0,5,0 ">
			<PARAM NAME="_ExtentX" VALUE="6350">
			<PARAM NAME="_ExtentY" VALUE="6350">
			</OBJECT></div>
	</body>
</html>