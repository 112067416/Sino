<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" name="id" value="${entity.id }" /><input type="hidden" name="bxfl" value="${entity.bxfl }" /><input type="hidden" name="spbh" value="${entity.spbh }" /><input type="hidden" name="nwai" value="${entity.nwai }" /><input type="hidden" name="pz" value="${entity.pz }" /><input type="hidden" name="deng" value="${entity.deng }" /><input type="hidden" name="cond" value="${entity.cond }" /><input type="hidden" name="cdnm" value="${entity.cdnm }" />
<table class="form" width="100%">
<colgroup><col width="10%" /><col width="15%" /><col width="10%" /><col width="27%" /><col width="10%" /><col width="28%" /></colgroup>
<tr>
<th style="text-align: left;">订货NO.</th>
<td><ui:input name="dhno" title="订货No." maxlength="7" value="entity.dhno" />-<ui:input name="line" title="行号" maxlength="2" value="entity.line" onblur="doDhno(this);" /></td>
<th style="text-align: left;">出货日期</th> 
<td><ui:date name="chri" showCalendar="true" value="entity.chri" /></td>
<th style="text-align: left;">合同客户</th> 
<td><ui:input name="name" title="合同客户名称" value="entity.name" /><input name="user" type="hidden" value="${entity.user }" /><input name="abbr" type="hidden" value="${entity.abbr }" /></td>
</tr>
<tr>
<th style="text-align: left;">规格</th> 
<td><ui:input name="ggno" title="规格代码" maxlength="4" value="entity.ggno" readonly="true"/></td>
<th style="text-align: left;">尺寸</th> 
<td><ui:number name="houu" scale="4" precision="3" title="厚" value="entity.houu" readonly="true"/>*<ui:number name="kuan" scale="6" precision="2" value="entity.kuan" title="宽" readonly="true"/>*<ui:number name="cang" scale="6" precision="2" title="长" value="entity.cang" readonly="true"/></td>
<th style="text-align: left;">合同单价</th> 
<td><ui:number name="htdj" title="合同单价" scale="7" precision="2" value="entity.htdj" readonly="true"/></td>
</tr>
<tr>
<th style="text-align: left;">镀锡量</th> 
<td><ui:input name="fudw" title="附着量.单位" maxlength="2" value="entity.fudw" readonly="true"/> <ui:input name="fuzm" title="附着量.正面" maxlength="3" value="entity.fuzm" readonly="true"/>/<ui:input name="fufm" title="附着量.反面" maxlength="3" value="entity.fufm" readonly="true"/></td>
<th style="text-align: left;">发票日期</th> 
<td><ui:date name="fpri" value="entity.fpri" required="true" /></td>
<th style="text-align: left;">币种</th> 
<td><ui:select name="thqf" list="${thqf }" value="entity.thqf" /> </td>
<td colspan="2">&nbsp;</td>
</tr>
</table>
<br>
<table id="DataTbl" class="form" border="1" bordercolor="#ffffff" width="100%" style="text-align: center; border-collapse: collapse;">
<colgroup><col width="20%"/><col width="12%"/><col width="12%"/><col width="14%"/><col width="14%"/><col width="14%"/><col width="14%"/></colgroup>
<tr>
<th nowrap="nowrap" style="text-align: center;">发票客户</th>
<th nowrap="nowrap" style="text-align: center;">吨数</th>
<th nowrap="nowrap" style="text-align: center;">发票号</th>
<th nowrap="nowrap" style="text-align: center;">发票单价</th>
<th nowrap="nowrap" style="text-align: center;">利息折扣</th>
<th nowrap="nowrap" style="text-align: center;">质量折扣</th>
<th nowrap="nowrap" style="text-align: center;">算出总价</th>
</tr>
<tr>
<td nowrap="nowrap"><ui:input name="fpymc" title="发票用户名称" value="entity.fpymc" maxlength="20" required="true"/></td>
<td nowrap="nowrap"><ui:number name="kfzl" value="entity.kfzl" scale="7" precision="3" title="开票吨数" onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:input name="fpno" value="entity.fpno" title="发票号" maxlength="10" required="true"/></td>
<td nowrap="nowrap"><ui:number name="kpdj" value="entity.kpdj" scale="9" precision="4" maxlength="10" title="发票单价" onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:number name="lxzr" value="entity.lxzr" scale="10" precision="2" maxlength="11" title="利息折扣" onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:number name="zlzr" value="entity.zlzr" scale="10" precision="2" maxlength="11" title="质量折扣"  onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:number name="fpje" value="entity.fpje" maxlength="12" scale="11" precision="2" title="发票金额" required="true" readonly="true"/></td>
</tr>
</table>
<div class="opt-btm">
<input type="button" class="button" value="保 存" onclick="doSubmit(this);"/>
<input type="button" class="button" value="关 闭" onclick="coco.hidePage('Detail');" />
</div>