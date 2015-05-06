<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="7%"/><col width="7%"/><col width="6%"/><col width="15%"/><col width="9%"/><col width="5%"/><col width="5%"/><col width="5%"/><col width="5%"/><col width="16%"/><col width="11%"/><col width="9%"/></colgroup>
	<tr class="page-head" align="center">
		<th nowrap="nowrap">出货日期</th>
		<th nowrap="nowrap">船公司</th>
		<th nowrap="nowrap">运输行</th>
	   	<th nowrap="nowrap">用户名称</th>
	   	<th nowrap="nowrap">订货号</th>
	   	<th nowrap="nowrap">件数</th>
		<th nowrap="nowrap">吨数</th>
		<th nowrap="nowrap">毛重</th>
		<th nowrap="nowrap">送货点</th>
		<th nowrap="nowrap">到达地点</th>
		<th nowrap="nowrap">尺寸</th>
		<th nowrap="nowrap">装箱指示No.</th>
	</tr>
	<tr>
		<td colspan="4"></td>
		<td>合计</td>
		<td>${chtjVO.chsu }</td>
		<td>${chtjVO.chzl }</td>
		<td colspan="5">&nbsp;</td>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
	<c:choose>
	<c:when test="${item.id != null }">
	<tr key1="${item.id }"  xu.menu="menu" <c:choose><c:when test="${item.stat == \"0\" }">style="background-color: #AFAEA4;" title="未设置运费"</c:when><c:when test="${stat.index%2 == 1 }"></c:when><c:otherwise></c:otherwise></c:choose> ondblclick="toSetting(this);" onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td><f:v value="item.chri" format="yyyy-MM-dd"/> </td>
		<td><c:choose><c:when test="${item.cyhhnm != null }">${item.cyhhnm }</c:when><c:when test="${item.cyhhnm1 != null }">${item.cyhhnm1 }</c:when><c:when test="${item.cycknm != null }">${item.cycknm }</c:when><c:when test="${item.cycknm1 != null }">${item.cycknm1 }</c:when><c:otherwise></c:otherwise></c:choose></td>
		<td><f:ot text="item.ysnm" len="6" /></td>
	    <td>${item.name}</td>
		<td>${item.dhno}-${item.line}</td>
		<td>${item.chsu}</td>
		<td>${item.chzl}</td>
		<td>${item.mazl}</td>
		<td>${item.shnm}</td>
		<td><f:ot text="item.addr" len="10" /></td>
		<td>${item.houu}*${item.kuan}*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
		<td><f:rt value="${item.zxno }" /></td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
		<td colspan="3" style="text-align: center;">小计</td>
	    <td>${item.name}</td>
		<td>&nbsp;</td>
		<td>${item.chsu}</td>
		<td>${item.chzl}</td>
		<td>${item.mazl}</td>
		<td>${item.shnm}</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	</c:otherwise>
	</c:choose>
	</c:forEach>
</table>
<f:paged />