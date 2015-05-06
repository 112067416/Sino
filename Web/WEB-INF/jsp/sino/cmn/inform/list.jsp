<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="5%" /><col width="30%" /><col width="35%" /><col width="30%" /></colgroup>
	<tr>
		<th align="center"><input type="checkbox" xu.target="ids" /></th>
		<th>目录</th>
		<th>名称</th>
		<th>类型</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" id1=${item.attachment } xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="view(this);" onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td align="center"><input type="checkbox" name="ids" value="${item.id }" /></td>
			<td>${item.directory.name }</td>
			<td>${item.name }</td>
			<td>${item.ext }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />