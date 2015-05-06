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
		<th>模&nbsp;&nbsp;块</th>
		<td><DIV class="form-text" >${code.id }</DIV></td>
		<th>备&nbsp;&nbsp;注</th>
		<td><DIV class="form-text" >${code.description}</DIV></td>
	</tr>
	<tr>
		<th>可编辑</th>
		<td><DIV class="form-text" ><f:bool bool="code.editable" trueText="#可编辑" falseText="#不可编辑" /></DIV></td>
		<th>有&nbsp;&nbsp;效</th>
		<td><DIV class="form-text" ><f:bool bool="code.valid" trueText="#有效" falseText="#无效" /></DIV></td>
	</tr>
</table>
<table id="ItemTbl" align=center border=0 cellpadding="0" cellspacing="0" class="pagination">
	<colgroup>
		<col width="120" />
		<col width="200" />
		<col width="210" />
		<col width="100" />
		<col width="60" />
		<col width="60" />
		<col width="60" />
		<col width="60" />
	</colgroup>
	<tr>
	<th>键</th>
	<th>值</th>
	<th>备注</th>
	<th>数值</th>
	<th>顺序</th>
	<th>可编辑</th>
	<th>可删除</th>
	<th>有效</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${items }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
	<td>${item.key}</td>
	<td>${item.value}</td>
	<td>${item.remark}</td>
	<td>${item.number}</td>
	<td>${item.order}</td>
	<td align="center"><f:bool bool="item.editable" trueText="#可编辑" falseText="#不可编辑" /></td>
	<td align="center"><f:bool bool="item.deletable" trueText="#可删除" falseText="#不可删除" /></td>
	<td align="center"><f:bool bool="item.valid" trueText="#有效" falseText="#无效" /></td>
</tr>
	</c:forEach>
</table>