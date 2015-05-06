<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分配岗位</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
<form action="changePost.do" method="post">
<table align="center" class="pagination" border="0" cellpadding="3"
	cellspacing="0">
	<colgroup>
		<col />
		<col />
	</colgroup>
	<tr>
		<th></th>
		<th>岗位名称</th>
	</tr>
	<c:forEach var="item" items="${posts }" varStatus="stat">
		<tr>
			<td><input type="radio" name="postId" value="${item.id }"
				<c:if test="${item.id == postId}">checked="checked"</c:if> /></td>
			<td>${item.name}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="2" align="center"><input type="submit" class="button" value="确定岗位" /></td>
	</tr>
</table>
</form>
</body>
</html>