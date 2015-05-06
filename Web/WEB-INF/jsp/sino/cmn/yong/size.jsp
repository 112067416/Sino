<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table id="ISizeTbl" width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="pagination">
	<colgroup><col width="5%" /><col width="20%" /><col width="20%" /><col width="20%" /><col width="17%" /><col width="18%" /></colgroup>
	<tr>
		<th><input type="checkbox" onclick="coco.selAll('ids',this)" /></th>
		<th>运用规格</th>
		<th>尺寸.厚</th>
		<th>尺寸.宽</th>
		<th>表面</th>
		<th>用途</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${items }">
	<tr>
		<td align="center" ><input type="checkbox" name="ids" value="${item.id }" /><input type="hidden" name="id" value="${item.id }" /></td>
		<td><ui:input name="yuny" value="item.yuny" maxlength="8" required="true" /></td>
		<td><ui:number name="houu" value="item.houu" maxlength="5" scale="4" precision="3" /></td>
		<td><ui:number name="kuan" value="item.kuan" scale="6" precision="2" maxlength="7" /></td>
		<td><ui:input name="face" value="item.face" maxlength="2" required="true" /></td>
		<td><ui:input name="cdnm" value="item.cdnm" maxlength="8" required="true" /></td>
	</tr>
	</c:forEach>
</table>
<div class="submit">
<table width="100%"><tr><td align="left">
<input type="button" class="button" value="添 加" onclick="tblSizeForm.insertEmptyRow('ISizeTblTmp');" />
<input type="button" class="button" value="移 除" onclick="tblSizeForm.removeRow('ids');" />
</td><td align="right">
<input type="button" class="button" value="保 存" onclick="saveSize('${user}');" /> <input type="button" class="button"
		value="关 闭" onclick="coco.hidePage('SizeArea')" />
</td>
</tr>
</table>
</div>