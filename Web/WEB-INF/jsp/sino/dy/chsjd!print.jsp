<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintView" style="font-size:10px;">
<div style="font-size: 30px; font-weight: bold; color: #000000; padding-top: 20px; padding-bottom:10px; " align="center"><span style="font-size: 15px;"><f:v value="chriS" format="yyyy-MM-dd" /><c:if test="${chriE  != null}"> 至 <f:v value="chriE" format="yyyy-MM-dd" /></c:if></span> 出货实绩单</div>
<div align="right" style="font-size: 12px; padding-right: 50px;">PAGE：${current + 1 } / ${pageCount }</div>
<table width="1060" cellpadding="0" cellspacing="0" align="center" border="1" bordercolor="#1DAE53" style="font-size: 14px; border-collapse: collapse; padding-left: 5px;">
<colgroup>
<col width="8%"/><col width="6%"/><col width="10%"/><col width="14%"/><col width="9%"/><col width="4%"/>
<col width="4%"/><col width="5%"/><col width="5%"/><col width="15%"/><col width="11%"/><col width="9%"/></colgroup>
<tr>
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
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<c:choose>
	<c:when test="${item.id != null }">
	<tr>
		<td><f:v value="item.chri" format="yyyy-MM-dd"/> </td>
		<td><c:choose><c:when test="${item.cyhhnm != null }">${item.cyhhnm }</c:when><c:when test="${item.cycknm != null }">${item.cycknm }</c:when><c:otherwise></c:otherwise></c:choose></td>
		<td>${item.ysnm }</td>
	    <td>${item.name}</td>
		<td>${item.dhno}-${item.line}</td>
		<td>${item.chsu}</td>
		<td>${item.chzl}</td>
		<td>${item.mazl}</td>
		<td>${item.shnm}</td>
		<td>${item.addr }</td>
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
<c:if test="${current + 1 == pageCount }">
<tr>
<td colspan="4"></td>
<td>合计</td>
<td>${chtjVO.chsu }</td>
<td>${chtjVO.chzl }</td>
<td colspan="5">&nbsp;</td>
</tr>
</c:if>
</table>
</div>