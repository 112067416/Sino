<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagination">
	<colgroup><col width="30" /><col /></colgroup>
	<tr>
		<th><input type="checkbox" onclick="coco.selAll('jbnos', this)" /></th>
		<th>制品号</th>
	</tr>
	<c:forEach items="${items }" var="item">
	<tr>
		<td><input type="checkbox" name="jbnos" value="${item.jbno }" /></td>
		<td>${item.jbno }</td>
	</tr>
	</c:forEach>
</table>