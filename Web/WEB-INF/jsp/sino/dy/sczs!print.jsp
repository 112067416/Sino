<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintView" style="font-size:10px;">
<div style="font-size: 30px; font-weight: bold; color: #000000; padding-top: 20px; padding-bottom:10px; " align="center">生产指示单</div>
<table width="1060" border="0" cellpadding="0" cellspacing="0" align="center">
<tr><td><table width="100%" align="center" style="font-size: 18px; font-weight: bold" border="0">
<colgroup><col width="200"><col width="300"><col width="300"><col width="100"><col width="160"></colgroup>
<tr>
<td colspan="3">&nbsp;</td>
<td align="right">确认时间：</td>
<td style="text-align: right;">&nbsp;<span style="font-size: 12px;">PAGE：${current + 1 } / ${pageCount }</span></td>
</tr>
<tr>
<td>发出部门：营业管理部</td>
<td colspan="2">&nbsp;</td>
<td align="right">制单日期：</td>
<td><f:v value="crea" format="yyyy-MM-dd"/></td>
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
<table width="100%" cellpadding="0" cellspacing="0" border="1" bordercolor="#000000" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:13px;font-family: 'Courier';">
<colgroup>
<col width="14%"/><col width="8%"/><col width="12%"/>
<col width="8%"/><col width="8%"/><col width="8%"/><col width="8%"/>
<col width="8%"/><col width="8%"/><col width="10%"/><col width="8%"/></colgroup>
<tr>
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
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr>
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
</tr>
</c:forEach>
</table></td></tr></table>
</div>