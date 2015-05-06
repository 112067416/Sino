<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="150" /><col /><col width="50" /><col width="50" /></colgroup>
	<tr>
		<th>模块</th>
		<th>说明</th>
		<th>可编辑?</th>
		<th>有效?</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" ondblclick="toView(this);" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.id }</td>
			<td>${item.description }</td>
			<td><f:bool bool="item.editable" trueText="#是" falseText="#否" /></td>
			<td><f:bool bool="item.valid" trueText="#有效" falseText="#无效" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />