<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" align="center" class="pagination">
<colgroup><col width="20%" /><col width="15%" /><col width="13%" /><col width="13%" /><col width="14%" /><col width="25%" /></colgroup>
<tr>
<th rowspan="2">停线时间</th>
<th rowspan="2">休止I</th>
<th colspan="3">休止II</th>
<th rowspan="2">理由</th>
</tr>
<tr>
<th>操业</th>
<th>机械</th>
<th>电气</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${items}">
<tr>
<td><input type="hidden" name="items[${stat.index }].id" value="${item.id }"/><ui:input  name="items[${stat.index }].stop" maxlength="20" value="item.stop"/></td>
<td><ui:number name="items[${stat.index }].xzda" maxlength="10" scale="10" precision="3" value="item.xzda"/></td>
<td><ui:number name="items[${stat.index }].xzzy" maxlength="10" scale="10" precision="3" value="item.xzzy"/></td>
<td><ui:number name="items[${stat.index }].xzjx" maxlength="10" scale="10" precision="3" value="item.xzjx"/></td>
<td><ui:number name="items[${stat.index }].xzdj" maxlength="10" scale="10" precision="3" value="item.xzdj"/></td>
<td><ui:input  name="items[${stat.index }].remk" maxlength="25" value="item.remk"/></td>
</tr>
</c:forEach>
</table>
<br/>
<br/>
<br/>
<table width="100%" align="center" class="pagination">
<colgroup><col width="100%" /></colgroup>
<tr><th >特记</th></tr>
<tr><td><ui:input name="vari1" maxlength="100" value="itemvari.vari1"/></td></tr>
<tr><td><ui:input name="vari2" maxlength="100" value="itemvari.vari2"/></td></tr>
<tr><td><ui:input name="vari3" maxlength="100" value="itemvari.vari3"/></td></tr>
<tr><td><ui:input name="vari4" maxlength="100" value="itemvari.vari4"/></td></tr>
<tr><td><ui:input name="vari5" maxlength="100" value="itemvari.vari5"/></td></tr>
</table>