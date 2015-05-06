<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>查看装箱指示书</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	</head>
	<body>
		<ui:page  title="查看装箱指示书" >
			<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="update.do" >
			<input type="hidden" name="filed" />
			<input type="hidden" name="id" />
			<table class="form" width="100%">
				<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" />
				<col width="10%" /><col width="10%" /><col width="10%" /><col width="30%" /></colgroup>
				<tr>
					<th>装箱指示书号</th>
					<td>&nbsp; ${zxzsTp.zxno }</td>
					<th>内外销</th>
					<td>&nbsp;${zng1Tp.nwai }</td>
					<th>用户代码</th>
					<td>&nbsp;${zxzsTp.user}</td>
					<th>用户名称</th>
					<td>&nbsp;${zxzsTp.name}</td>
				</tr>
				<tr>
					<th>送货地代码</th>
					<td>&nbsp;&nbsp; ${zxzsTp.shno }</td>
					<th>送货地名称</th>
					<td>&nbsp;${zxzsTp.shnm }</td>
					<th>出货日</th>
					<td><f:v format="yyyy-MM-dd" value="zxzsTp.chri"/></td>
					<th>运输公司</th>
					<td>&nbsp;${zxzsTp.ysnm}</td>
				</tr>
			</table>
			</form>
			<f:page action="view.do" file="zsmxList.jsp">
			<input type="hidden" name="zxno" value="${zxzsTp.zxno }" />
			</f:page>
		</ui:page>
	
	</body>
</html>