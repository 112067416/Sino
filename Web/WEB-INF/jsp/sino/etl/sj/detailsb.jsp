<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%">
<colgroup><col width="48%"/><col width="4%"/><col width="48%"/></colgroup>
<tr>
<td valign="top">
<table width="100%" align="center" style="text-align: center;">
<colgroup><col width="40%"/><col width="30%"/><col width="30%"/></colgroup>
<tr>
<th>日期</th>
<th>计划生产量</th>
<th>操业时间</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${items}" begin="0" step="2">
<tr >
<td ><ui:input name="items[${stat.index }].dasr" maxlength="10" value="item.dasr"  /></td>
<td><ui:number name="items[${stat.index }].jszl" maxlength="10" scale="10" precision="3" value="item.jszl"/></td>
<td><ui:number name="items[${stat.index }].gzda" maxlength="10" scale="10" precision="3" value="item.gzda"/></td>
</tr>
</c:forEach>
</table>
</td>
<td></td>
<td valign="top">
<table width="100%" align="center" style="text-align: center;">
<colgroup><col width="40%"/><col width="30%"/><col width="30%"/></colgroup>
<tr>
<th>日期</th>
<th>计划生产量</th>
<th>操业时间</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${items}" begin="1" step="2">
<tr >
<td ><ui:input name="items[${stat.index }].dasr" maxlength="10" value="item.dasr"  /></td>
<td><ui:number name="items[${stat.index }].jszl" maxlength="10" scale="10" precision="3" value="item.jszl"/></td>
<td><ui:number name="items[${stat.index }].gzda" maxlength="10" scale="10" precision="3" value="item.gzda"/></td>
</tr>
</c:forEach>
</table>
</td>
</tr>
</table>