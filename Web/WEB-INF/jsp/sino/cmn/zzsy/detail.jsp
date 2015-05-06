<%@ page language="java" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@  taglib uri="/sys" prefix="sys" %><%@ taglib prefix="sino" uri="/sino" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="$type" value="modify" />
<br/>
<div class="form-row">
<span class="form-label">制造仕样 No. = </span><ui:input name="syno" value="entity.syno" maxlength="6" required="true" readonly="true" />
</div>
<fieldset class="group"><legend>第1KEY</legend>
<div class="form-row">
<span class="form-label">规格 =</span><ui:input name="ggno" value="entity.ggno" maxlength="4" onblur="fillGgno(this);" required="true"  />
<span class="form-label">附着量 =</span><ui:input name="fudw" value="entity.fudw" title="单位" maxlength="2" required="true" onblur="fillFudw(this);" />&nbsp;<ui:input name="fuzm" value="entity.fuzm" title="正面值" maxlength="3" required="true" onblur="fillFuzm(this);" />&nbsp;<ui:input name="fufm" value="entity.fufm" title="反面值" maxlength="3" required="true" onblur="fillFufm(this);" />
<span class="form-label">品种 =</span><ui:input name="pzno" value="entity.pzno" required="true" maxlength="2" />
<span class="form-label">差厚 =</span><ui:input name="chdx" value="entity.chdx" maxlength="2" />
<span class="form-label">内外销 =</span><ui:input name="nwai" value="entity.nwai" required="true" maxlength="1" />
</div>
<div class="form-row">
<span class="form-label">订货尺寸 = </span>
<ui:number name="hmin" title="厚下限" value="entity.hmin" scale="7" precision="3" positive="true" />&nbsp;&nbsp;-&nbsp;&nbsp;<ui:number name="hmax" value="entity.hmax" title="厚上限" scale="7" precision="3" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="kmin" title="宽下限" value="entity.kmin" scale="9" precision="2" positive="true" />&nbsp;&nbsp;-&nbsp;&nbsp;<ui:number name="kmax" value="entity.kmax" title="宽上限" scale="9" precision="2" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="cmin" title="长下限" value="entity.cmin" scale="9" precision="2" positive="true" />&nbsp;&nbsp;-&nbsp;&nbsp;<ui:number name="cmax" value="entity.cmax" title="长上限" scale="9" precision="2" positive="true" />
</div>
<div class="form-row" style="padding-left: 125px">
<ui:number name="houu" title="厚" value="entity.houu" scale="7" precision="3" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="kuan" title="宽" value="entity.kuan" scale="9" precision="2" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="cang" title="长" value="entity.cang" scale="9" precision="2" positive="true" />
</div></fieldset>
<fieldset class="group"><legend>第2KEY</legend>
<div class="form-row">
<span class="form-label">加工用途  = </span>
<ui:input name="cond" value="entity.cond" maxlength="4" /> 
<span class="form-label">用户代码  = </span>
<ui:input name="user" value="entity.user" maxlength="4" />
</div>
</fieldset>
<fieldset class="group"><legend>指定项目</legend>
<table class="form">
<colgroup>
<col width="75" />
<col width="15" />
<col width="80" />
<col width="65" />
<col width="15" />
<col width="100" />
<col width="75" />
<col width="115" />
<col width="180" />
</colgroup>
<tr>
<th style="text-align: left;">剪边</th>
<th style="text-align: center;">=</th>
<td><ui:input name="jian" value="entity.jian" maxlength="1" /> </td>
<th style="text-align: left;">零头下限</th>
<th style="text-align: center;">=</th>
<td><ui:input name="ltdw" value="entity.ltdw" title="单位" maxlength="1" />&nbsp;&nbsp;<ui:int name="ltzi" value="entity.ltzi" title="值" maxlength="6" positive="true" /> </td>
<th style="text-align: left;">硬度上下限</th>
<th style="text-align: center;">=</th>
<td><ui:input name="ymin" value="entity.ymin" maxlength="2" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="ymax" value="entity.ymax" maxlength="2" /></td>
</tr>
<tr>
<th style="text-align: left;">涂油种类</th>
<th style="text-align: center;">=</th>
<td><ui:input name="ytyp" maxlength="1" required="true" value="entity.ytyp" /></td>
<th style="text-align: left;">KPLATE</th>
<th style="text-align: center;">=</th>
<td><ui:input name="plat" value="entity.plat" maxlength="1" /></td>
<th style="text-align: left;">附着量 正</th>
<th style="text-align: center;">=</th>
<td><ui:number name="fuzx" value="entity.fuzx" scale="5" precision="2" positive="true" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fuzs" value="entity.fuzs" scale="5" precision="2" positive="true" /></td>
</tr>
<tr>
<th style="text-align: left;">涂油量</th>
<th style="text-align: center;">=</th>
<td><ui:input name="yqty" value="entity.yqty" maxlength="2" required="true" /></td>
<th style="text-align: left;">合金层</th>
<th style="text-align: center;">=</th>
<td><ui:input name="hjin" value="entity.hjin" maxlength="3" /></td>
<th style="text-align: left;">上下限 反</th>
<th style="text-align: center;">=</th>
<td><ui:number name="fufx" value="entity.fufx" scale="5" precision="2" positive="true" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fufs" value="entity.fufs" scale="5" precision="2" positive="true" /></td>
</tr>
<tr>
<th style="text-align: left;">分配等级</th>
<th style="text-align: center;">=</th>
<td><ui:input name="fpdj" value="entity.fpdj" maxlength="3" required="true" /></td>
<td colspan="3">&nbsp;</td>
<th style="text-align: left;">公差厚(%)</th>
<th style="text-align: center;">=</th>
<td><ui:number name="hxzi" scale="5" precision="1" value="entity.hxzi" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="hszi" scale="5" precision="1" value="entity.hszi" /></td>
</tr>
<tr>
<th style="text-align: left;">直角度</th>
<th style="text-align: center;">=</th>
<td><ui:number name="jiao" value="entity.jiao" scale="5" precision="1" positive="true" /></td>
<th style="text-align: left;">&nbsp;</th>
<th style="text-align: center;">&nbsp;</th>
<td>&nbsp;</td>
<th style="text-align: left;">公差宽(mm)</th>
<th style="text-align: center;">=</th>
<td><ui:number name="kxzi" scale="5" precision="2" format="2" value="entity.kxzi" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="kszi" scale="5" precision="2" format="2" value="entity.kszi" /></td>
</tr>
<tr>
<th style="text-align: left;">内径保护筒</th>
<th style="text-align: center;">=</th>
<td><ui:input name="njbh" value="entity.njbh" maxlength="1" /></td>
<th style="text-align: left;">运用规格</th>
<th style="text-align: center;">=</th>
<td><ui:input name="yuny" value="entity.yuny" maxlength="6" readonly="true" /></td>
<th style="text-align: left;">公差长(mm)</th>
<th style="text-align: center;">=</th>
<td><ui:number name="cxzi" scale="5" precision="2" format="2" value="entity.cxzi" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="cszi" scale="5" precision="2" format="2" value="entity.cszi" /></td>
</tr>
<tr>
<th style="text-align: left;">保护板</th>
<th style="text-align: center;">=</th>
<td><ui:input name="prot" value="entity.prot" maxlength="1" /></td>
<th style="text-align: left;">装入宽</th>
<th style="text-align: center;">=</th>
<td><ui:number name="zrkn" value="entity.zrkn" scale="9" precision="2" positive="true" /></td>
<th style="text-align: left;">波剪直剪</th>
<th style="text-align: center;">=</th>
<td><ui:input name="jcxs" value="entity.jcxs" maxlength="4" /></td>
</tr>
<tr>
<th style="text-align: left;">附页KPNo</th>
<th style="text-align: center;">=</th>
<td colspan="6"><ui:input name="kpn1Flag" value="entity.kpn1Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn1" value="entity.kpn1" maxlength="9" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="kpn2Flag" value="entity.kpn2Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn2" value="entity.kpn2" maxlength="9" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="kpn3Flag" value="entity.kpn3Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn3" value="entity.kpn3" maxlength="9" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="kpn4Flag" value="entity.kpn4Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn4" value="entity.kpn4" maxlength="9" title="最多只能输入四个KPNO" /></td>
<th style="text-align: left;">证书提示信息&nbsp;&nbsp;&nbsp;<ui:input name="jcts" value="entity.jcts" maxlength="2" title="检查证明书提示信息" /></th>
</tr>
<tr>
<th style="text-align: left;">木工所No</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="mgsn" value="entity.mgsn" cols="100" rows="" title="木工所No（如果有多个木工所no时，要用半角英文输入法的“,”间开）" />
</td>
</tr>
<tr>
<th style="text-align: left;">备注1</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="bz1" value="entity.bz1" cssStyle="" cssClass="normal" cols="100" rows="" title="ETL和木工所指示书的备注内容"/></td>
</tr>
<tr>
<th style="text-align: left;">备注2</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="bz2" value="entity.bz2" cssStyle="" cssClass="normal" cols="100" rows="" title="SL指示书的备注内容"/></td>
</tr>
<tr>
<th style="text-align: left;">备注3</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="bz3" value="entity.bz3" cssStyle="" cssClass="normal" cols="100" rows="" title="ETL、木工所和SL指示书的备注内容"/></td>
</tr>
</table>
</fieldset>
<div class="submit"><input type="button" class="button" value="保 存" onclick="doUpdate();" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('ModifyDetail')" /></div>