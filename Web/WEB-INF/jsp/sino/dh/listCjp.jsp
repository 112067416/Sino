<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="4%"/><col width="12%"/><col width="12%"/><col width="12%"/><col width="12%"/><col width="12%"/><col width="12%"/><col width="12%"/><col width="12%"/></colgroup>
	<tr class="page-head" align="center">
		<th height="15" rowspan="2"><input type="checkbox" onclick="checkAll(this);" /></th>
	   	<th rowspan="2">运用规格	</th>
		<th colspan="3">现品尺寸	</th>
		<th rowspan="2">表面加工	</th>
		<th rowspan="2">现品状况</th>
		<th rowspan="2">保税品重量</th>
		<th rowspan="2">制品重量</th>	
	</tr>
	<tr>
		<th height="15" >尺寸.厚</th>
		<th>尺寸.宽</th>
		<th>尺寸.长</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="id" value="${item.id }" onclick="doCheck(this);" bszl="${item.bszl }" zpzl="${item.zpzl }"  /></td>
			<td>${item.yuny }</td>
			<td><f:v value="item.xpho" format="3" /></td>
			<td><f:v value="item.xpkn" format="2" /></td>
			<td><c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}"><f:v value="item.xpcn" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.face }</td>
			<td><f:code code="#XPZK" key="${item.xpzk }" /></td>
			<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.bszl" format="###,###" /></td>
			<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.zpzl" format="###,###" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />