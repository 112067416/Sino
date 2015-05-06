<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintView" style="font-size:10px;">
<div style="font-size: 30px; font-weight: bold; color: #000000; padding-top: 20px; padding-bottom:10px; " align="center">出货联络单</div>
<table width="1060" border="0" cellpadding="0" cellspacing="0" align="center">
<tr><td><table width="100%" align="center" style="font-size: 18px; font-weight: bold" border="0">
<colgroup><col width="200"><col width="300"><col width="300"><col width="100"><col width="160"></colgroup>
<tr>
<td colspan="3">&nbsp;</td>
<td align="right">确认时间：</td>
<td style="text-align: right;">&nbsp;<span style="font-size: 12px;">PAGE：${current + 1 } / ${pageCount }</span></td>
</tr>
<tr>
<td>发出部门：营业部</td>
<td colspan="2">&nbsp;</td>
<td align="right">出货日期：</td>
<td><f:v value="chqi" format="yyyy-MM-dd"/></td>
</tr>
<tr>
<td>送至部门：生产管理部</td>
<td colspan="2">&nbsp;</td>
<td align="right">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
<td>&nbsp;</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="1" bordercolor="#000000" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
<colgroup>
<col width="160"><col width="100"><col width="150"><col width="45"><col width="100"><col width="85"><col width="100"><col width="115">
<col width="200"><col width="200"><col width="100"></colgroup>
<tr>
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
</tr>
<tr>
<td colspan="4"></td>
<td>合计</td>
<td>${page.chzls }</td>
<td colspan="5"></td>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr>
<td>${item.ysnm }</td>
<td>${item.ysfm } </td>
<td>${item.name}</td>
<td><c:choose><c:when test="${item.cang != null && item.cang > 0}">S</c:when><c:otherwise>C</c:otherwise></c:choose></td>
<td>${item.dhno }<c:if test="${item.line != null }">-${item.line }</c:if></td>
<td>${item.chzl }</td>
<td>${item.shnm }</td>
<td>${item.addr }</td>
<td>${item.bhzk }</td>
<td><sino:ddqk chlldId="${item.id }" /></td>
<td>${item.ywnm }</td>
</tr>
</c:forEach>
</table>
</td>
</tr>
</table>
</div>