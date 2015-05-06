<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="65%"/>
		<col width="35%"/>
	</colgroup>
	<tr align="center">
		<th nowrap="nowrap">客户</th>
		<th nowrap="nowrap">余额</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td>${item.name }</td>
			<td>${item.fkye }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />