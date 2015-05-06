<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input name="id" type="hidden" value="${entity.id }" /><input name="cdwDd" type="hidden" value="${entity.cdwDd }" />
<input name="cdwHx" type="hidden" value="${entity.cdwHx }" />
<input name="ddtnd" type="hidden" value="${entity.ddtnd }" />
<input name="jlsj" type="hidden" value="<f:v value="entity.jlsj" format="yyyy-MM-dd" />" />
<table width="96%" align="center" class="form"><colgroup><col width="10%" /><col width="10%" /><col width="23%" /><col width="30%" /><col width="23%" /><col width="24%" /></colgroup>
<tr>
<th style="text-align: center; vertical-align: middle;">1班确认人</th>
<td><ui:input name="qrr1" maxlength="10" value="entity.qrr1" /></td>
<th style="text-align: center; vertical-align: middle;">2班确认人</th>
<td><ui:input name="qrr2" maxlength="10" value="entity.qrr2" /></td>
<th style="text-align: center; vertical-align: middle;">3班确认人</th>
<td><ui:input name="qrr3" maxlength="10" value="entity.qrr3" /></td>
</table>
<table width="100%" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup>
<col width="10%" /><col width="10%" /><col width="9%" /><col width="9%" /><col width="11%" /><col width="17%" /><col width="17%" /><col width="17%" />
</colgroup>
<tr>
<th style="text-align: center; vertical-align: middle;" rowspan="2">班次</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">T101液量</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">T102液量</th>
<th style="text-align: center; vertical-align: middle;" rowspan="2">T106液量</th>
<th style="text-align: center; vertical-align: middle;" colspan="4">电镀液</th>
<th style="text-align: center; vertical-align: middle;" colspan="2">去药液抽到动力(次/周)</th>
<th style="text-align: center; vertical-align: middle;">化学液量</th>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;" colspan="2">T201液量</th>
<th style="text-align: center; vertical-align: middle;">T202液量</th>
<th style="text-align: center; vertical-align: middle;">T203液量</th>
<th style="text-align: center; vertical-align: middle;">班次</th>
<th style="text-align: center; vertical-align: middle;">T403</th>
<th style="text-align: center; vertical-align: middle;">T304</th>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;">1班</th>
<td><ui:input name="ylT1011" maxlength="4" value="entity.ylT1011" /></td>
<td><ui:input name="ylT1012" maxlength="4" value="entity.ylT1012" /></td>
<td><ui:input name="ylT1013" maxlength="4" value="entity.ylT1013" /></td>
<td><ui:input name="ddyT2011" maxlength="4" value="entity.ddyT2011" /></td>
<td><ui:input name="ddyYl1" maxlength="4" value="entity.ddyYl1" /></td>
<td><ui:input name="ddyT2021" maxlength="4" value="entity.ddyT2021" /></td>
<td><ui:input name="ddyT2031" maxlength="4" value="entity.ddyT2031" /></td>
<th style="text-align: center; vertical-align: middle;">1班</th>
<td><ui:input name="qyycdT4031" maxlength="4" value="entity.qyycdT4031" /></td>
<td><ui:input name="hxylT3041" maxlength="4" value="entity.hxylT3041" /></td>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;">2班</th>
<td><ui:input name="ylT1021" maxlength="4" value="entity.ylT1021" /></td>
<td><ui:input name="ylT1022" maxlength="4" value="entity.ylT1022" /></td>
<td><ui:input name="ylT1023" maxlength="4" value="entity.ylT1023" /></td>
<td><ui:input name="ddyT2012" maxlength="4" value="entity.ddyT2012" /></td>
<td><ui:input name="ddyYl2" maxlength="4" value="entity.ddyYl2" /></td>
<td><ui:input name="ddyT2022" maxlength="4" value="entity.ddyT2022" /></td>
<td><ui:input name="ddyT2032" maxlength="4" value="entity.ddyT2032" /></td>
<th style="text-align: center; vertical-align: middle;">2班</th>
<td><ui:input name="qyycdT4032" maxlength="4" value="entity.qyycdT4032" /></td>
<td><ui:input name="hxylT3042" maxlength="4" value="entity.hxylT3042" /></td>
</tr>
<tr>
<th style="text-align: center; vertical-align: middle;">3班</th>
<td><ui:input name="ylT1031" maxlength="4" value="entity.ylT1031" /></td>
<td><ui:input name="ylT1032" maxlength="4" value="entity.ylT1032" /></td>
<td><ui:input name="ylT1033" maxlength="4" value="entity.ylT1033" /></td>
<td><ui:input name="ddyT2013" maxlength="4" value="entity.ddyT2013" /></td>
<td><ui:input name="ddyYl3" maxlength="4" value="entity.ddyYl3" /></td>
<td><ui:input name="ddyT2023" maxlength="4" value="entity.ddyT2023" /></td>
<td><ui:input name="ddyT2033" maxlength="4" value="entity.ddyT2033" /></td>
<th style="text-align: center; vertical-align: middle;">1班</th>
<td><ui:input name="qyycdT4033" maxlength="4" value="entity.qyycdT4033" /></td>
<td><ui:input name="hxylT3043" maxlength="4" value="entity.hxylT3043" /></td>
</tr>
</table>