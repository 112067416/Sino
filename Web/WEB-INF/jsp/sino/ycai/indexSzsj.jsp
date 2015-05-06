<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置入库时间</title>	
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<SCRIPT type="text/javascript">
	<!--
		function success() {
			alert("设置成功");
			var form = document.forms["DataForm"];
			form.elements["ship"].value="";
			form.elements["date"].value="";
		}
	//-->
	</SCRIPT>
</head>
<body>
<ui:page title="设置入库时间 ">
	<form name="DataForm" xu.s="success()" action="setRksj.do" method="post">
		<table width="98%" align="center" style="margin: 20px auto;"
			class="form">
			<colgroup>
				<col />
				<col />
				<col />
				<col />
			</colgroup>
			<tr>
				<th style="text-align: left;">船名</th>
				<td><ui:input name="ship" required="true"  /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<th style="text-align: left;">入库时间</th>
				<td><ui:datetime name="date" required="true" prop="calendar:true;"  /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="确认(S)" accesskey="s" onclick="cocoform.submit(this);" />
		</div>
	</form>
	</ui:page>
</body>
</html>
