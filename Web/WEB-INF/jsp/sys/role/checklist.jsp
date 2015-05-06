<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="30" /><col /></colgroup>
	<c:forEach varStatus="stat" var="item" items="${roles }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> >
			<td><input type="checkbox" name="roleIds" value="${item.id }" /></td>
			<td>${item.name }</td>
		</tr>
	</c:forEach>
</table>