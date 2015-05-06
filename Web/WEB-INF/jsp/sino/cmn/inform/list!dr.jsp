<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="50%" /><col width="25%" /><col width="25%" /></colgroup>
	<tr>
		<th>附件名</th>
		<th>扩展名</th>
		<th>创建时间</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="view(this);" onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td>${item.name }</td>
			<td>${item.ext }</td>
			<td><f:v value="item.createTime" format="yyyy-MM-dd" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />