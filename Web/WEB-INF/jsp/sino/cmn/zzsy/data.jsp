<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col /><col /><col /></colgroup>
	<tr>
		<th>仕样No</th>
		<th>规格</th>
		<th>附着量</th>
		<th>品种</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.syno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
			<td>${item.syno }</td>
			<td>${item.ggno }</td>
			<td>${item.fudw } ${item.fuzm }/${item.fufm }</td>
			<td>${item.pzno }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />