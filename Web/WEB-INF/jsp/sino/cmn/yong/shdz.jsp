<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table id="ItemTbl" width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="pagination">
	<colgroup><col width="5%" /><col width="95%" /><col /></colgroup>
	<tr>
		<th><input type="checkbox" onclick="coco.selAll('ids',this)" /></th>
		<th>送货地址</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${items }">
	<tr>
		<td align="center" ><input type="checkbox" name="ids" value="${item.id }" /><input type="hidden" name="id" value="${item.id }" /></td>
		<td><ui:input name="addr" value="item.addr" cssStyle="width:99%" required="true" /></td>
	</tr>
	</c:forEach>
</table>
<div class="submit">
<table width="100%"><tr><td align="left">
<input type="button" class="button" value="添 加" onclick="tableForm.insertEmptyRow('ItemTblTmp');" />
<input type="button" class="button" value="移 除" onclick="tableForm.removeRow('ids');" />
</td><td align="right">
<input type="button" class="button" value="保 存" onclick="saveShdz('${user}');" /> <input type="button" class="button"
		value="关 闭" onclick="coco.hidePage('ShdzArea')" />
</td>
</tr>
</table>
</div>