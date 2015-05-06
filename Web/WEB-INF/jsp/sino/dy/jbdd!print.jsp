<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:10px;">
<div style="font-size: 16px; color: #000000; font-weight: bold; padding-top: 20px; padding-bottom:10px; " align="center">基板订购单</div>
<table width="1060" border="0" cellpadding="0" cellspacing="0" align="center">
<tr><td><table width="100%" align="center" style="font-size: 12px; font-weight: bold" border="0">
<colgroup><col width="500"><col width="560"></colgroup>
<tr>
<td align="left"  style="padding-left: 20px;"><f:v value="date" format="yyyy-MM-dd HH:mm"/></td>
<td align="right" style="padding-right: 10px;">${current }/${pages }</td>
</tr>
</table>
</td>
</tr>
<tr>
<td colspan="2">
<table width="100%" align="center" cellpadding="0" cellspacing="0" border="1" bordercolor="#000000" style="border-collapse: collapse; text-align: left; padding-left: 6px; font-size: 8px;">
<colgroup>
<col width="25"><col width="25"><col width="28"><col width="32"><col width="35"><col width="45"><col width="20">
<col width="35"><col width="50"><col width="35"><col width="35">
<col width="105"><col width="40"><col width="40">
<col width="105"><col width="135"><col width="30"><col width="180"><col width="20"><col width="40">
</colgroup>
<tr>
<td height="30">ITEM</td>
<td>ISOGI</td>
<td>INPUT</td>
<td>SURF</td>
<td>TEMPER</td>
<td>THICK A</td>
<td>X</td>
<td>WIDTH</td>
<td>THICK B</td>
<td>A-B</td>
<td>CIVIL</td>
<td>CALCULATION</td>
<td>TOTAL</td>
<td>CONFIRM</td>
<td>CUSTOMER</td>
<td>APPLICATION</td>
<td>CODE</td>
<td>REMARK</td>
<td>C/*</td>
<td>COATING</td>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr>
<td height="25">${stat.count + (current - 1) * 10  }</td>
<td>${item.isgi}</td>
<td>${item.inpu }</td>
<td>${item.face }</td>
<td>${item.yuny }</td>
<td>${item.houa }</td>

<td>X</td>
<td>${item.width }</td>
<td>${item.houb }</td>
<td><f:v value="item.ajb" format="3" /></td>
<td>${item.dhsl }</td>

<td>${item.calc }</td>
<td>${item.dhsl }</td>
<td>${item.conf }</td>
<td>${item.abbr }</td>

<td>${item.cond}</td>
<td>${item.code }</td>
<td>${item.rema }</td>
<td>${item.hanc }</td>
<td>${item.fuzm }<c:if test="${item.fuzm != null }">/${item.fufm }</c:if></td>
</tr>
</c:forEach>
<c:if test="${current == pages }">
<tr>
<td colspan="12" height="25">&nbsp;</td>
<td>${page.dhsls }</td>
<td colspan="7">&nbsp;</td>
</tr>
</c:if>
</table>
</td>
</tr></table>
</div>