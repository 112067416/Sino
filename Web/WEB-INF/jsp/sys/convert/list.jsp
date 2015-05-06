<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="150" /><col width="200" /><col width="80" /><col /></colgroup>
	<tr>
		<th>标识</th>
		<th>名称</th>
		<th>类型</th>
		<th>类名</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" title="双击修改" ondblclick="toModify(this);" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.id }</td>
			<td>${item.name}</td>
			<td><f:kv value="item.type" list="'0':'电子表格','1':'文本'" /></td>
			<td>${item.defaultClass}</td>
		</tr>
	</c:forEach>
</table>
<f:paged />