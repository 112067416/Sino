<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初始化数据</title>
</head>
<body>
<form action="" method="post" onsubmit="document.getElementById('submitbtn').disabled = true; return true;">
<input type="hidden" name="loaded" value="true" />
<table width="300" border="1" cellpadding="2" cellspacing="0" bordercolor="#000000" align="center">
	<tr>
		<th width="50">选择</th>
		<th>数据表</th>
	</tr>
	<c:if test="${modules != null}">
	<c:forEach var="module" items="${modules }">
	<tr>
		<td><input type="checkbox" name="tables" value="${module.value }" /></td>
		<td>${module.key }</td>
	</tr></c:forEach>
	</c:if>
</table>
<div align="center"><input type="submit" id="submitbtn" value="初始化数据"/>
<a href="../../coco/query/deploy.do" target="_blank">初始化查询配置</a></div>
</form>
<div align="center" style="text-align:center;font-size:18px;color:#FF0000;padding:20px;">${msg }</div>
</body>
</html>