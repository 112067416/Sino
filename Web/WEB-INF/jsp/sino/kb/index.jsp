<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
			function doQuery() {
				var qForm = document.forms["PageForm_page"];
				var dhno = qForm.elements["dhno"].value;
				var line = qForm.elements["line"].value;
				qForm.elements["dhnoLine"].value = dhno + line;
				cocopage.query();
			}
		//-->
		</script>
	</head>
	<body>
	<ui:page title="待捆包制品检索">
		<f:page action="queryDkb!${duic }.do" file="list.jsp">
		<input name="dhnoLine" type="hidden" />
			<table width="96%" align="center" style="margin: 10px 0;" class="form">
				<colgroup>
					<col width="6%" /><col width="18%" /><col width="6%" /><col width="15%" /><col width="6%"  /><col width="15%"  /><col width="7%" /><col width="10%" /><col width="7%" /><col width="10%" /></colgroup>
				<tr>
					<th>用户代码</th>
					<td><ui:input name="userBegin" maxlength="4" />  至 <ui:number name="userEnd" maxlength="4" /></td>
					<th>订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th>PILE No.</th>
					<td><ui:input name="jbno" maxlength="11" title="PILE No." /></td>
					<th>紧急</th>
					<td><ui:select name="sfjj" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='ZSJJ'" headerText="全部" headerValue=""/></td>
					<th colspan="2">&nbsp;</th>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button"  value="查 询" onclick="doQuery()" />
			</div>
		</f:page>
	</ui:page>
	</body>
</html>