<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		
		function outExcel() {
			var form = document.forms["DataForm"];
			if(!cocoform.verify(form))return;
			var content = cocoform.postContent(form);
			var wo = document.getElementById("WebOffice");
			//wo.HideMenuItem(0x33);
			if(window.self == window.top) wo.LoadOriginalFile("./outFpckListExcel.do?"+content, "xls");
			else wo.LoadOriginalFile("<%=request.getContextPath()%>/sino/zkfp/fp/outFpckListExcel.do?"+content, "xls");
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
		<table align="center" class="form" width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
				<col width="8%" /><col width="17%" />
				<col width="8%" /><col width="17%" />
				<col width="8%" /><col width="17%" />
				<col width="8%" /><col width="17%" />
			</colgroup>
			<tr>
				<th>产量代码</th>
				<th><ui:select name="chan" list="${chan }" />&nbsp;&nbsp;&nbsp;<ui:select name="pz" list="${pz }" headerText="" headerValue="" /></th>
				<th>运用规格</th>
				<td><ui:input name="yuny" maxlength="6" /></td>
				<th>现品No</th>
				<td colspan="3"><ui:input name="jbnoS" maxlength="11" title="现品No" />至<ui:input name="jbnoE" maxlength="11" title="现品No" /></td>
			</tr>
			<tr>
				<th>尺寸·厚</th>
				<td><ui:number name="xphoS" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true"/>至<ui:number name="xphoE" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true"/></td>
				<th>尺寸·宽</th>
				<td><ui:number name="xpknS" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true"/>至<ui:number name="xpknE" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true"/></td>
				<th>尺寸·长</th>
				<td><ui:number name="xpcnS" maxlength="7"  scale="6" precision="2" title="尺寸.长" positive="true"/>至<ui:number name="xpcnE" maxlength="7"  scale="6" precision="2" title="尺寸.长" positive="true"/></td>
				<th>表面加工</th>
				<td><ui:input name="face" maxlength="2" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<th>原板客户</th>
				<td><ui:input name="ycbr" maxlength="18" /></td>
				<td colspan="6" style="text-align: right;">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="检索(Q)" accesskey="q" onclick="outExcel();" /></td>
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