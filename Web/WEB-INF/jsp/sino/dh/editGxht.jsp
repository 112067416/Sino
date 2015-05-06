<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="/sys" prefix="sys" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="user" value="${entity.user }" /><input type="hidden" name="name" value="${entity.name }" />
<table width="96%" class="form">
<colgroup><col width="15%" /><col width="85%" /></colgroup>
<tr>
<th style="text-align: left;">合同号</th>
<td><ui:input name="dhno" maxlength="7" title="合同号" readonly="true" value="entity.dhno" /></td>
</tr>
<tr>
<th style="text-align: left;">约定日期</th>
<td><ui:date name="qrsj" value="entity.qrsj" /></td>
</tr>
<tr>
<th style="text-align: left;">货物品名</th>
<td><ui:input name="hwpm" maxlength="60" value="entity.hwpm" /></td>
</tr>
<tr>	
<th style="text-align: left;">交货地点及运输方式</th>
<td><ui:input name="jhfs" maxlength="60" value="entity.jhfs" /></td>
</tr>
<tr>
<th style="text-align: left;">运输及到达港和费用负担</th>
<td><ui:input name="ysfs" maxlength="60" value="entity.ysfs" /></td>
</tr>
<tr>
<th style="text-align: left;">包装标准及包装物的供应与回收</th>
<td><ui:input name="bzfs" maxlength="60" value="entity.bzfs" /></td>
</tr>
<tr>
<th style="text-align: left;">结算方式及期限</th>
<td><ui:input name="jsfs" maxlength="60" value="entity.jsfs" /></td>
</tr>
<tr>
<th style="text-align: left;">其它约定事项</th>
<td><ui:input name="ydsx" maxlength="60" value="entity.ydsx" /></td>
</tr>
</table>
<div id="BtDiv" style="display: block;" align="center">
<input type="button" class="button" value="保   存" onclick="doSubmit(this);" />
<input type="button" class="button" value="关   闭" onclick="coco.hidePage('GxhtDetail');" /></div>