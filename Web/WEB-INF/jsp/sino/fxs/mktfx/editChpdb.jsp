<%@ page language="java" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ taglib uri="/sys" prefix="sys" %><%@ taglib prefix="sino" uri="/sino" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" >
<colgroup>
<col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="11%" />
<col width="9%" /><col width="9%" /><col width="6%" /><col width="6%" />
<col width="9%" /><col width="9%" /><col width="9%" />
</colgroup>
<tr>
<th height="30" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">创建时间</th>
<td colspan="11"><ui:date name="cjsj" cssClass="normal" required="true" value="cjsj" /></td>
</tr>
<tr>
<th height="30" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">Coil No.</th>
<td colspan="2"><ui:input name="jbno" cssClass="normal" required="true" maxlength="7" onkeydown="if(window.event.keyCode==13) loadChpdb(this);" onchange="loadChpdb(this);" value="entity.jbno" /><input type="hidden" name="zzsd" value="${entity.zzsd }" /></td>
<th style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">尺寸</th>
<td colspan="3"><ui:number name="houu" cssClass="normal" title="订货DB的订货尺寸.厚" value="entity.houu" scale="4" precision="3" positive="true"/>
*<ui:number name="kuan" cssClass="normal" title="订货DB的订货尺寸.宽" value="entity.kuan" scale="6" precision="2" positive="true"/>
*<ui:number name="cang" cssClass="normal" title="订货DB的订货尺寸.长" value="entity.cang" scale="6" precision="2" positive="true"/></td>
<th style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">用户略称</th>
<td colspan="4"><ui:input name="abbr" cssClass="normal" title="用户略称" value="entity.abbr" maxlength="10" /></td>
</tr>
<tr>
<th height="30" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">订货NO</th>
<td colspan="2"><ui:input name="dhno" cssClass="normal" title="订货DB的订货NO" maxlength="9" required="true" value="entity.dhno" /></td>
<th style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">用途</th>
<td><ui:input name="cond" cssClass="normal" title="用途" maxlength="4" value="entity.cond" /></td>
<th style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">运用规格</th>
<td><ui:input name="yuny" cssClass="normal" title="运用规格" maxlength="6" value="entity.yuny" /></td>
<th style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">表面</th>
<td><ui:input name="face" cssClass="normal" title="表面" maxlength="2" value="entity.face" /></td>
<th style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">镀锡量</th>
<td colspan="2"><ui:input name="fudw" title="附着量.单位" maxlength="2" value="entity.fudw" /> <ui:input name="fuzm" title="附着量.正面" value="entity.fuzm" maxlength="3" />/<ui:input name="fufm" title="附着量.反面" value="entity.fufm" maxlength="3" /></td>
</tr>
</table>
<br />
<table border="1" bordercolor="#ffffff" width="100%" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal; text-align: center; border-collapse: collapse;">
<colgroup>
<col width="3%" />
<col width="8%" /><col width="8%" /><col width="8%" /><col width="9%" /><col width="9%" />
<col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" />
</colgroup>
<tr>
<th height="25" rowspan="2">&nbsp;</th>
<th colspan="3">锡附着量</th>
<th rowspan="2">Cr</th>
<th>ISV</th>
<th>TCS</th>
<th>ATC</th>
<th>Pore</th>
<th>TCV</th>
<th>PLT</th>
</tr>
<tr>
<th height="25">M-Sn</th>
<th>Alloy</th>
<th>T-Sn</th>
<th>&lt;=5</th>
<th>&lt;=8.5</th>
<th>&lt;=0.120</th>
<th>&lt;=13</th>
<th>&lt;=2</th>
<th>&lt;=10</th>
</tr>
<tr>
<td height="25">F</td>
<td><ui:number maxlength="5" scale="4" precision="2" name="xfzlMt" value="entity.xfzlMt" /></td>
<td><ui:number maxlength="4" scale="3" precision="2" name="xfzlAt" value="entity.xfzlAt" /></td>
<td><ui:number maxlength="5" name="xfzlSt" value="entity.xfzlSt" /></td>
<td><ui:number maxlength="5" scale="4" precision="2" name="crT" value="entity.crT" /></td>
<td rowspan="2"><ui:number maxlength="5" scale="4" precision="2" name="isv" value="entity.isv" /></td>
<td rowspan="2"><ui:number maxlength="3" scale="2" precision="1" name="tcs" value="entity.tcs" /></td>
<td rowspan="2"><ui:number maxlength="6" scale="5" precision="4" name="atc" value="entity.atc" /></td>
<td rowspan="2"><ui:int maxlength="2" name="pore" value="entity.pore" /></td>
<td rowspan="2"><ui:number maxlength="4" scale="3" precision="2" name="tcv" value="entity.tcv" /></td>
<td rowspan="2"><ui:number maxlength="5" scale="4" precision="2" name="pltPd" value="entity.pltPd" /></td>
</tr>
<tr>
<td height="25">B</td>
<td><ui:number maxlength="5" scale="4" precision="2" name="xfzlMb" value="entity.xfzlMb" /></td>
<td><ui:number maxlength="4" scale="3" precision="2" name="xfzlAb" value="entity.xfzlAb" /></td>
<td><ui:number maxlength="5" name="xfzlSb" value="entity.xfzlSb" /></td>
<td><ui:number maxlength="5" scale="4" precision="2" name="crB" value="entity.crB" /></td>
</tr>
</table>

<div class="opt-btm"><input type="button" class="button" value="保   存" onclick="doSave(this);" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('EditMktArea');" /></div>