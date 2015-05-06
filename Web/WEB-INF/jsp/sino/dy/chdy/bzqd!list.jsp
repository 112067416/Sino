<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<c:if test="${page.query }">
<fieldset class="group"><br>
<legend>备选制品 </legend>
<table width="100%" class="pagination" border="0" cellpadding="0"
	cellspacing="0">
	<colgroup>
		<col width="4%" />
		<col width="12%" />
		<col width="12%" />
		<col width="12%" />
		<col width="12%" />
		<col width="12%" />
		<col width="12%" />
		<col width="12%" />
		<col width="12%" />
	</colgroup>
	<tr class="page-head" align="center">
		<th rowspan="2"><input type="checkbox" onclick="checkAll(this);" /></th>
		<th rowspan="2">Package No.</th>
		<th rowspan="2">制品No.</th>
		<th rowspan="2">数量</th>
		<th colspan="3">Weight(kgs)</th>
	</tr>
	<tr class="page-head" align="center">
		<th>Cal</th>
		<th>Net</th>
		<th>Gross</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" onclick="doCheck(this);" no="${item.jbno }" value="${item.zpzl}" mazl="${item.mazl }" /></td>
			<td><f:v value="item.ckno" format="%06d" /></td>
			<td><c:if test="${item.plqf != null}">${item.plqf}</c:if><c:if test="${item.zzno != null}">${item.zzno }-</c:if>${item.jbno }</td>
			<td><c:if test="${item.zshu != null && item.zshu > 0 }">${item.zshu }</c:if></td>
			<td></td>
			<td>${item.zpzl}</td>
			<td>${item.mazl }</td>
		    </tr>
	</c:forEach>
</table>
<f:paged callback="setChecked"/>
</fieldset>
</c:if>