<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui"%>
<%@ taglib uri="/f" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="98%" align="center" class="pagination" border="0" cellpadding="0" cellspacing="0" style="margin-top: 15px;">
<colgroup><col width="150px" /><col width="120px" /><col /></colgroup>
	<tr>
		<th>短语名称</th>
		<th>类型</th>
		<th>内容</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id}" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
			<td><div style="width: 150px;" class="overflow">${item.name }</div></td>
			<td><div style="width: 150px;" class="overflow">${item.type }</div></td>
			<td><div style="width: 500px;" class="overflow">${item.memo }</div></td>
		</tr>
	</c:forEach>
</table>
<f:paged />