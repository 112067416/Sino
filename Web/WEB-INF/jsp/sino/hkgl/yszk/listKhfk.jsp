<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" name="idStr" /><input type="hidden" name="khfkIdStr" /><input type="hidden" name="fkye" />
<table class="form" width="96%">
<colgroup><col width="8%" /><col width="36%" /><col width="10%" /><col width="20%" /><col width="8%" /><col width="18%" /></colgroup>
<tr>
<th style="text-align: left;">客户名称</th>
<td><ui:input name="name" title="客户" readonly="true" required="true" value="entity.name" /></td>
<th style="text-align: left;">冲帐金额</th> 
<td><ui:input name="$fkye" required="true" maxlength="16" format="#,##0.00" readonly="true" /></td>
<td colspan="2">&nbsp;</td>
</tr>
</table>
<fieldset class="group"><legend>客户付款记录</legend>
<table width="96%" class="form">
<colgroup><col width="5%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="20%" /></colgroup>
<c:forEach varStatus="stat" var="item" items="${items }">
<tr style="text-align: left;">
<!-- 
<th><input type="radio" name="radio" <c:if test="${stat.count == 1 }">checked="checked"</c:if> onclick="changeKhfk(this.value);" value="${item.id }"  /></th> -->
<th><input type="checkbox" name="khfkId" onclick="changeKhfk(this);" value="${item.id }" fkye="${item.fkye }" /></th>
<th>付款日期</th>
<td><f:v value="item.crea" format="yyyy-MM-dd" /></td>
<th>付款金额</th>
<td style="text-align: right; padding-right: 10px;"><f:v value="item.fkje" format="#,##0.00" /></td>
<th>付款余额</th>
<td style="text-align: right; padding-right: 10px;"><f:v value="item.fkye" format="#,##0.00" /></td>
<td></td>
</tr>
</c:forEach>
</table>
</fieldset>
<div class="submit">
<input type="button" class="button" value="确   定" onclick="doSubmit(this);"/>
<input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail');" />	
</div>