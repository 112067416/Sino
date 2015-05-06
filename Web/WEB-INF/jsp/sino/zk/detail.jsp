<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input name="id" type="hidden" value="${item.id }" />
<table width="96%" align="center" class="form"><colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="30%" /><col width="10%" /><col width="30%" /></colgroup>
<tr>
<th style="text-align: center; vertical-align: middle;">记录日期</th>
<td><ui:datetime name="rq" required="true" value="item.rq" showCalendar="false" /></td>
<td colspan="4">&nbsp;</tr>
</table>
<table width="100%" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup>
<col width="10%" /><col width="7%" /><col width="7%" /><col width="10%" />
<col width="10%" /><col width="7%" /><col width="7%" /><col width="9%" />
<col width="10%" /><col width="7%" /><col width="7%" /><col width="9%" /></colgroup>
<tr>
<th style="text-align: center; vertical-align: middle;" rowspan="2">碱浸</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">补给</th>
<th style="text-align: center; vertical-align: middle;">水</th>
<td><ui:input cssClass="normal" maxlength="4" name="jjbgs" value="item.jjbgs" /></td>
<th style="text-align: center; vertical-align: middle;" rowspan="2">碱电解</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">补给</th>
<th style="text-align: center; vertical-align: middle;">水</th>
<td><ui:input cssClass="normal" maxlength="4" name="jdjbgs" value="item.jdjbgs" /></td>
<th style="text-align: center; vertical-align: middle;" rowspan="2">酸电解</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">补给</th>
<th style="text-align: center; vertical-align: middle;">水</th>
<td><ui:input cssClass="normal" maxlength="4" name="sdjbgs" value="item.sdjbgs" /></td>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;">碱</th>
<td><ui:input cssClass="normal" maxlength="4" name="jjbgj" value="item.jjbgj" /></td>
<th style="text-align: center; vertical-align: middle;">碱</th>
<td><ui:input cssClass="normal" maxlength="4" name="jdjbgj" value="item.jdjbgj" /></td>
<th style="text-align: center; vertical-align: middle;">硫酸</th>
<td><ui:input cssClass="normal" maxlength="5" name="sdjbgLs" value="item.sdjbgLs" /></td>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;" rowspan="3">电镀</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">补给</th>
<th style="text-align: center; vertical-align: middle;">ENSA</th>
<td><ui:input cssClass="normal" maxlength="4" name="ddbgEnsa" value="item.ddbgEnsa" /></td>
<th style="text-align: center; vertical-align: middle;" rowspan="3">去药液</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">排入</th>
<th style="text-align: center; vertical-align: middle;">201</th>
<td><ui:input cssClass="normal" maxlength="4" name="qyypr201" value="item.qyypr201"/></td>
<th style="text-align: center; vertical-align: middle;" rowspan="3">化学</th>
<th style="text-align: center; vertical-align: middle;" rowspan="3">补给</th>
<th style="text-align: center; vertical-align: middle;">水</th>
<td><ui:input cssClass="normal"  maxlength="4" name="hxbgSh" value="item.hxbgSh"/></td>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;">PSA</th>
<td><ui:input cssClass="normal" maxlength="5" name="ddbgPsa" value="item.ddbgPsa" /></td>
<th style="text-align: center; vertical-align: middle;">403</th>
<td><ui:input cssClass="normal" maxlength="4" name="qyypr403" value="item.qyypr403" /></td>
<th style="text-align: center; vertical-align: middle;">铬酸酐</th>
<td><ui:input cssClass="normal" maxlength="4" name="hxbgGsg" value="item.hxbgGsg" /></td>
</tr>
<tr>
<td colspan="3">&nbsp;</td>
<td colspan="3">&nbsp;</td>
<th style="text-align: center; vertical-align: middle;">重铬酸钠</th>
<td colspan="5"><ui:input cssClass="normal" maxlength="5" name="hxbgZgsn" value="item.hxbgZgsn" /></td>
</tr>
</table>