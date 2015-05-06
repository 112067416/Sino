<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>客户余额查询</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="客户余额查询">
			<f:page action="khye.do" file="listKhye.jsp">
				<table class="form" width="100%">
					<colgroup><col width="10%" /><col width="35%" /><col width="10%" /><col width="45%" /></colgroup>
					<tr>
						<th style="text-align: left;">用户名称</th>
						<td> <sino:yongBox name="user" txt="8" /></td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<div class="submit">
					<input type="button" class="button" value="查 询" onclick="cocopage.query()"/>
					<input type="button" class="button" value="打 印" onclick="print()" />
				</div>
			</f:page>
		</ui:page>
	</body>
</html>