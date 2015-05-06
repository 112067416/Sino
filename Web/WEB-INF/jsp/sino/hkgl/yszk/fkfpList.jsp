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
			var chriBegin = form.elements["chriBegin"];
			var $chriBegin = chriBegin.value;
			if($chriBegin == null || $chriBegin.length == 0) {
				alert("出货日期不能为空");
				chriBegin.focus();
				return;
			}
			var chriEnd = form.elements["chriEnd"];
			var $chriEnd = chriEnd.value;
			if($chriEnd == null || $chriEnd.length == 0) {
				alert("出货日期不能为空");
				chriEnd.focus();
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
			if(window.self == window.top) wo.LoadOriginalFile("./outFkfpExcel.do?"+content, "xls");
			else wo.LoadOriginalFile("<%=request.getContextPath()%>/sino/hkgl/yszk/outFkfpExcel.do?"+content, "xls");
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
			<colgroup><col width="10%" /><col width="25%" /><col width="10%" /><col width="20%" /><col width="6%" /><col width="11%" /><col width="6%" /><col width="12%" /></colgroup>
			<tr>
				<th style="text-align: left;">出货日期</th>
				<td><ui:date name="chriBegin" required="true" />至<ui:date name="chriEnd" required="true" /></td>
				<th style="text-align: left;">发票客户</th>
				<td><ui:input name="fpymc" maxlength="14" /></td>
				<th style="text-align: left;">品种</th>
				<td><ui:select name="pz" list="${pz }" headerText="" headerValue="" /></td>
				<th style="text-align: left;">等级</th>
				<td><ui:select name="deng" list="${deng }" headerText="" headerValue="" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">尺寸.厚</th>
				<td><ui:number name="houuS" maxlength="5"  scale="4" precision="3" title="大于尺寸.宽" positive="true" />至<ui:number name="houuE" maxlength="5"  scale="4" precision="3" title="小于尺寸.宽" positive="true" /></td>
				<th style="text-align: left;">内外销</th>
				<td><ui:select name="nwai" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='NWX'" headerText="" headerValue="" /></td>
				<th style="text-align: left;">业务员</th>
				<td><ui:input name="yyno" maxlength="3" /></td>
				<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="检索(Q)" accesskey="q" onclick="outExcel();" /></td>
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