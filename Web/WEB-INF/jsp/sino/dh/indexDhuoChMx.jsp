<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@taglib prefix="sino" uri="/sino" %><%@ 
taglib uri="/sys" prefix="sys" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		
		function outExcel() {
			var form = document.forms["DataForm"];
		//	if(!cocoform.verify(form))return;
		//	var content = cocoform.postContent(form);
			var jhqiB = form.elements["jhqiB"];
			var $jhqiB = jhqiB.value;
			if($jhqiB == null || $jhqiB.length == 0) {
				alert("交货日期不能为空");
				jhqiB.focus();
				return;
			}
			var jhqiE = form.elements["jhqiE"];
			var $jhqiE = jhqiE.value;
			if($jhqiE == null || $jhqiE.length == 0) {
				alert("交货日期不能为空");
				jhqiE.focus();
				return;
			}
			var content,el,name,value;
			var eles = form.elements;
			for(var i = 0; i < eles.length; i++) {
				el = eles[i];
				if(el.type == null || el.type == 'button') continue;
				name = encodeURI(el.name);
				name = encodeURI(name);
				value = encodeURI(el.value);
				value = encodeURI(value);
				if(i == 0) {
					content = name + "=" + value;
					continue;
				}
				content = content + "&" + name + "=" + value;
			}
			var wo = document.getElementById("WebOffice");
			//wo.HideMenuItem(0x33);
			if(window.self == window.top) wo.LoadOriginalFile("./outDhDhuoChmxExcel.do?"+content, "xls");
			else wo.LoadOriginalFile("<%=request.getContextPath()%>/sino/dh/outDhDhuoChmxExcel.do?"+content, "xls");
			wo.hideMenuArea("hideall","","","");
		}
		
		function resize() {
			var height = document.documentElement.clientHeight;
			var queryHeight = document.getElementById("QueryArea").offsetHeight;
			document.getElementById("ExcelArea").style.height = (height - queryHeight- 3) + "px";
		};
		
		coco.addEventListener(window, "load", resize);
		//-->
		</script>
	</head>
	<body>
	<div id="QueryArea">
		<form name="DataForm" method="post" >
		<table class="form" width="100%">
			<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="30%" /><col width="20%" /></colgroup>
			<tr>
				<th style="text-align: left;">交货日期</th>
				<td><ui:date name="jhqiB" required="true" />至<ui:date name="jhqiE" required="true" /></td>
				<th style="text-align: left;">出货日期</th>
				<td><ui:date name="chqiB" />至<ui:date name="chqiE" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="检索(Q)" accesskey="q" onclick="outExcel();" /></td>
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