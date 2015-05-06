<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="4%"/><col width="22%"/><col width="20%"/><col width="22%"/><col width="8%"/></colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
	   	<th>订购单号</th>
		<th>担当者</th>
		<th>作成时间</th>
		<th>状态</th>
	</tr>
		<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td align="center"><input type="checkbox" name="ids" value="${item.id }"/></td>
			<td>${item.jbno}</td>
			<td>${item.ddnm }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd HH:mm:ss"/> </td>
			<td><f:kv list="'0':'未发送','1':'<font color=red>已发送</font>'" value="item.stat" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />