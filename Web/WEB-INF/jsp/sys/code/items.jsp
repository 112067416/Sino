<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table id="ItemTbl" align=center border=0 cellpadding="0" cellspacing="0" class="pagination">
	<colgroup>
		<col width="30" />
		<col width="120" />
		<col width="180" />
		<col width="200" />
		<col width="100" />
		<col width="60" />
		<col width="60" />
		<col width="60" />
		<col width="60" />
	</colgroup>
	<tr>
	<th><input type="checkbox" onclick="coco.selAll('itemKeys',this)" /></th>
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
	<td align="center">
	<f:bool bool="item.deletable" trueText="#<input type=\"checkbox\" name=\"itemKeys\" />" falseText=""/>
	</td>
	<td nowrap="nowrap"><ui:span name="key" value="item.key" /></td>
	<c:if test="${item.editable}">
	<td><ui:input name="value" value="item.value" cssStyle="width:98%" lowToUp="false" /></td>
	<td><ui:input name="remark" value="item.remark" cssStyle="width:98%" lowToUp="false" /></td>
	<td><ui:number name="number" value="item.number" cssStyle="width:98%" /></td>
	<td><ui:input name="order" value="item.order" cssStyle="width:95%" lowToUp="false" /></td>
	<td align="center"><ui:bool name="editable" value="item.editable" /></td>
	<td align="center"><ui:bool name="deletable" value="item.deletable" /></td>
	<td align="center"><ui:bool name="valid" value="item.valid" /></td>
	</c:if>
	<c:if test="${item.editable == false}">
	<td><ui:span name="value" value="item.value" /></td>
	<td><ui:span name="remark" value="item.remark" /></td>
	<td><ui:span name="number" value="item.number" /></td>
	<td><ui:span name="order" value="item.order" /></td>
	<td align="center"><f:bool bool="item.editable" trueText="#可编辑" falseText="#不可编辑" tableField="editable"/></td>
	<td align="center"><f:bool bool="item.deletable" trueText="#可删除" falseText="#不可删除" tableField="deletable"/></td>
	<td align="center"><f:bool bool="item.valid" trueText="#有效" falseText="#无效" tableField="valid"/></td>
	</c:if>
</tr>
	</c:forEach>
</table>
<div class="submit">
<table width="100%"><tr><td align="left">
<input type="button" class="button" value="添 加" onclick="tableForm.insertEmptyRow('ItemTblTmp');" />
<input type="button" class="button" value="移 除" onclick="tableForm.removeRow('itemKeys');" />
</td><td align="right">
<input type="button" class="button" value="保 存" onclick="saveItems('${id}');" />
</td>
</tr>
</table>
</div>