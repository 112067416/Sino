<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="150" /><col /><col width="150" /><col width="50" /><col width="50" /><col width="50" /><col width="50" /></colgroup>
	<tr>
		<th>标识</th>
		<th>说明</th>
		<th>当前值</th>
		<th>流水号</th>
		<th>位宽</th>
		<th>步长</th>
		<th>启用?</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.id }</td>
			<td>${item.description }</td>
			<td>${item.value }</td>
			<td>${item.number }</td>
			<td>${item.scale }</td>
			<td>${item.step }</td>
			<td><f:bool bool="item.valid" trueText="#启用" falseText="#禁用" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />