<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="5%"/>
		<col width="20%"/>
		<col width="20%"/>
		<col width="15%"/>
		<col width="10%"/>
		<col width="9%"/>
		<col width="10%"/>
		<col width="8%"/>
	</colgroup>
	<tr align="center">
		<th nowrap="nowrap"><input type="checkbox" xu.target="ids" /></th>
		<th nowrap="nowrap">用户名称</th>
		<th nowrap="nowrap">付款日期</th>
		<th nowrap="nowrap">付款金额</th>
		<th nowrap="nowrap">货款金额</th>
		<th nowrap="nowrap">调整金额</th>
		<th nowrap="nowrap">付款余额</th>
		<th nowrap="nowrap">状态</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
			<td align="center"><input type="checkbox" name="ids" value="${item.id}"/></td>
			<td>${item.name }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd"/></td>
			<td style="text-align: right; padding-right: 10px;"><f:v value="item.fkje" format="#,##0.00"/></td>
			<td style="text-align: right; padding-right: 10px;"><f:v value="item.hkje" format="#,##0.00"/></td>
			<td style="text-align: right; padding-right: 10px;"><f:v value="item.tzye" format="#,##0.00"/></td>
			<td style="text-align: right; padding-right: 10px;"><f:v value="item.fkye" format="#,##0.00"/></td>
			<td><f:code code="#SINO_FKFP_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />