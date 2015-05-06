<%@ page language="java" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@  taglib uri="/sys" prefix="sys" %><%@ taglib prefix="sino" uri="/sino" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="id" value="${entity.id }" />
<table width="96%" align="center" class="form">
<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /></colgroup>
<tr>
<th style="text-align: left; vertical-align: middle;">Coil No.</th>
<td><ui:input name="jbno" readonly="true" maxlength="7" value="entity.jbno" /></td>
<th style="text-align: left; vertical-align: middle;" colspan="2">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'" prop="nn:1;" value="entity.ban" /></th>
<th style="text-align: left; vertical-align: middle;" colspan="2">组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'" prop="nn:1;" value="entity.zu"  /></th>
<th style="text-align: left; vertical-align: middle;" colspan="2">2Pass&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="pass" list="'入':'入','切':'切'" value="entity.pass" /></th>
<th style="text-align: left; vertical-align: middle;">涂油机目视</th>
<td><ui:select name="tyjms" list="'OK':'OK','NG':'NG'" value="entity.tyjms" /></td>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">电流密度T</th>
<td><ui:input name="cheDlmdt" maxlength="5" value="entity.cheDlmdt" /></td>
<th style="text-align: left; vertical-align: middle;">电流密度B</th>
<td><ui:input name="cheDlmdb" maxlength="5" value="entity.cheDlmdb" /></td>
<th style="text-align: left; vertical-align: middle;">锡附着量Chart正面</th>
<td><ui:input name="sczm" maxlength="5" value="entity.sczm" /></td>
<th style="text-align: left; vertical-align: middle;">锡附着量Chart反面</th>
<td><ui:input name="scfm" maxlength="5" value="entity.scfm" /></td>
<th style="text-align: left; vertical-align: middle;">Oiler比率</th>
<td><ui:input name="oilerBl" maxlength="5" value="entity.oilerBl" /></td>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">特记</th>
<td colspan="9"><ui:textarea rows="10" cols="80" name="tj" cssClass="normal" value="entity.tj" cssStyle="font-size: 20px;" /></td>
</tr>
</table>
<div class="opt-btm"><input type="button" class="button" value="保  存" onclick="saveQt(this.form, this);" />  <input type="button" class="button" value="关  闭" onclick="coco.hidePage('DetailPanel');" /></div>