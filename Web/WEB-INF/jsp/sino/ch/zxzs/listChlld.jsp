<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib 
	uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="3%"/>
		<col width="7%"/>
		<col width="5%"/>
		<col width="11%"/>
		<col width="3%"/>
		<col width="8%"/>
		<col width="5%"/>
		<col width="5%"/>
		<col width="15%"/>
		<col width="15%"/>
		<col width="13%"/>
		<col width="5%"/>
		<col width="5%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
	   	<th>运输公司</th>
	   	<th>运输方式</th>
		<th>用户名称</th>
		<th>S/C</th>
		<th>订货No.</th>
		<th>吨数</th>
		<th>送货点</th>
		<th>到达地点</th>
		<th>备货状况</th>
		<th>打单情况</th>
		<th>业务员</th>
		<th>状态</th>
	</tr>
	<tr>
		<td colspan="5"></td>
		<td>合计</td>
		<td>${page.chzls }</td>
		<td colspan="3"></td>
		<td>${ddzl }</td>
		<td colspan="2"></td>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" value="${item.id }"/></td>
			<td><f:ot text="item.ysnm" len="6" /></td>
			<td>${item.ysfm } </td>
			<td>${item.name}</td>
			<td><c:choose><c:when test="${item.cang != null && item.cang > 0}">S</c:when><c:otherwise>C</c:otherwise></c:choose></td>
			<td>${item.dhno }<c:if test="${item.line != null }">-${item.line }</c:if></td>
			<td>${item.chzl }</td>
			<td>${item.shnm }</td>
			<td><f:ot text="item.addr" len="10" /></td>
			<td><f:ot text="item.bhzk" len="10" /></td>
			<td><sino:ddqk chlldId="${item.id }" callback="openZxzs"/></td>
			<td>${item.ywnm }</td>
			<td><f:code code="#SINO_CHLLD_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
</table>