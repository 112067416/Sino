<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table width="96%" align="center" class="form" >
	<colgroup>
		<col width="10%" />
		<col width="40%" />
		<col width="10%" />
		<col width="40%" />
	</colgroup>
	<tr>
		<th>标识</th>
		<td><DIV class="form-text" >${seq.id }</DIV></td>
		<th>说明</th>
		<td><DIV class="form-text" >${seq.description }</DIV></td>
	</tr>
	<tr>
		<th>初始值</th>
		<td><DIV class="form-text" >${seq.value }</DIV></td>
		<th>流水号</th>
		<td><DIV class="form-text" >${seq.number }</DIV></td>
	</tr>
	<tr>
		<th>位宽</th>
		<td><DIV class="form-text" >${seq.scale }</DIV></td>
		<th>步长</th>
		<td><DIV class="form-text" >${seq.step }</DIV></td>
	</tr>
	<tr>
		<th>启用?</th>
		<td><DIV class="form-text" ><f:bool bool="valid" trueText="#启用" falseText="#禁用"/></DIV></td>
		<th></th>
		<td></td>
	</tr>
</table>
<table id="ItemTbl" width=96% align=center border=0 cellpadding="0" cellspacing="0" class="pagination">
	<colgroup>
		<col width="30" />
		<col width="70" />
		<col />
		<col width="60" />
		<col width="60" />
		<col width="150" />
	</colgroup>
	<tr>
	<th>类型</th>
	<th>表达式</th>
	<th>可复位</th>
	<th>顺序</th>
	<th>当前值</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${rules }">
	<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td><f:kv value="item.type" list="'1':'固定串','2':'时间','0':'流水项'"/></td>
		<td>${item.expr}</td>
		<td align="center"><f:bool bool="item.resetable" falseText="#-" trueText="#可复位"/></td>
		<td>${item.order}</td>
		<td>${item.value}</td>
	</tr>
	</c:forEach>
</table>