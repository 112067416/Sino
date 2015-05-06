<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width=60 /><col width="80" /><col width="35" /><col /></colgroup>
	<tr>
		<th>工号</th>
		<th>姓名</th>
		<th>性别</th>
		<th>机构</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" ondblclick="toModify(this);" title="双击编辑" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.no }</td>
			<td>${item.name }</td>
			<td><f:kv value="item.sex" list="'1':'男','2':'女'" text="-"  /></td>
			<td>${item.dept.name }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />