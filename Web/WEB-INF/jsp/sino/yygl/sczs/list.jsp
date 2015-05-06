<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="3%"/><col width="13%"/><col width="8%"/><col width="12%"/>
		<col width="8%"/><col width="8%"/><col width="7%"/><col width="6%"/>
		<col width="7%"/><col width="8%"/><col width="8%"/><col width="7%"/><col width="5%"/></colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
		<th>用户名称</th>
		<th>订货No.</th>
		<th>尺寸</th>
		<th>运用规格</th>
		<th>表面</th>
		<th>镀锡量</th>
		<th>是否<br />紧急</th>
		<th>合同量</th>
		<th>预安排<br />生产量</th>
		<th>预定<br />出货日期</th>
		<th>分配量</th>
		<th>状态</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this);" title="双击修改" onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" value="${item.id }"/></td>
			<td><f:ot text="item.name" len="12" /></td>
			<td>${item.dhno }-${item.line } </td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.yuny }</td>
			<td>${item.face }</td>
			<td>${item.fudw }&nbsp;&nbsp;<c:if test="${item.fuzm != null }">${item.fuzm }/</c:if>${item.fufm }</td>
			<td><c:choose><c:when test="${item.sfjj != null && item.sfjj == \"Y\"}"><span style="color: red;">紧急</span></c:when><c:otherwise>不紧急</c:otherwise></c:choose></td>
			<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.htzl" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.yscl" format="#,##0.000" /></td>
			<td><f:v value="item.chqi" format="yyyy-MM-dd" /> </td>
			<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.fpln" format="#,##0.000" /></td>
			<td><f:code code="#SINO_SCZS_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
</table>