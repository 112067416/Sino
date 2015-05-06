<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="15%" /><col width="25%" /><col width="10%" /><col width="50%" /></colgroup>
	<tr>
		<th>目录</th>
		<th>名称</th>
		<th>类型</th>
		<th>备注</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" id1=${item.attachment } xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="view(this);" onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td>${item.directory.name }</td>
			<td>${item.name }</td>
			<td>${item.ext }</td>
			<td>${item.bz }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />