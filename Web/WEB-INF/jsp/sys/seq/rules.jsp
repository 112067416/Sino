<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<th><input type="checkbox" onclick="coco.selAll('itemKeys',this)" /></th>
	<th>类型</th>
	<th>表达式</th>
	<th>可复位</th>
	<th>顺序</th>
	<th>当前值</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${rules }">
	<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td align="center"><input type="checkbox" name="itemKeys" /></td>
		<td><ui:select name="type" list="'1':'固定串','2':'时间','0':'流水项'" value="item.type"/></td>
		<td><ui:input name="expr" cssStyle="width:90%" value="item.expr"/></td>
		<td align="center"><ui:bool name="resetable" value="item.resetable"/></td>
		<td><ui:input name="order" cssStyle="width:90%" value="item.order"/></td>
		<td><ui:input name="value" cssStyle="width:90%" value="item.value"/></td>
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