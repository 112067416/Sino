<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/f" prefix="f" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
	</head>
	<script type="text/javascript">
	<!--
		// 查询
		function doQuery() {
			var oForm = document.forms["PageForm_page"];
			var dhno = oForm.elements["dhno"].value.replace(/^\s+|\s+$/gi, "");
			var line = oForm.elements["line"].value.replace(/^\s+|\s+$/gi, "");;
			oForm.elements["dhnoLine"].value = dhno + line;
			cocopage.query();
		}
	//-->
	</script>
	<body>
		<ui:page title="选别PILE检索">
		<f:page action="index.do" file="list.jsp">
		<input type="hidden" name="dhnoLine" />
		<table  class="form" width="100%">
			<colgroup><col width="8%" /><col width="15%" /><col width="8%" /><col width="15%" />
				<col width="8%" /><col width="22%" /><col width="8%" /><col width="15%" /></colgroup>
			<tr>
				<th>PILE No.</th>
				<td><ui:input name="jbno" maxlength="11" /></td>
				<th>订货No.</th>
				<td><ui:input name="dhno" title="订货DB的订货NO" maxlength="7" />-<ui:input name="line" title="行号" maxlength="2" onkeydown="if(window.event.keyCode == 13) doQuery();" /></td>
				<th>制品尺寸</th>
				<td nowrap="nowrap"><ui:number name="xpho" positive="true" scale="4" precision="3"/>*
				<ui:number name="xpkn" positive="true" scale="6" precision="2" />*
				<ui:number name="xpcn" positive="true" scale="6" precision="2" /></td>
				<th style="text-align: left;">用户代码</th>
				<td colspan="2"><ui:input name="userBegin" maxlength="4" />至<ui:input name="userEnd" maxlength="4"/></td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="查 询" onclick="doQuery();" /></div>
		</f:page>
		</ui:page>
	</body>
</html>