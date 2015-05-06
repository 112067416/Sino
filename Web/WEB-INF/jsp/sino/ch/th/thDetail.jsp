<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form action="update.do" xu.ajax="" xu.r="" xu.s="success();" name="DataForm" method="post" >
<input type="hidden" name="id" value="${thTp.id }"/>
<table width="96%" class="form">
<colgroup>
<col width="9%" /><col width="12%" /><col width="9%" /><col width="13%" /><col width="10%" /><col width="13%" /><col width="12%" /><col width="22%" />
</colgroup>
<tr>
<th style="text-align: left;">送货单号</th>
<td><ui:input name="shno" maxlength="10" readonly="true" value="thTp.tsTp.shno"/></td>
<th style="text-align: left;">制品No.</th>
<td><ui:input name="jbno" maxlength="11" readonly="true" value="thTp.tsTp.jbno"/></td>
<th style="text-align: left;">重量</th>
<td><ui:number name="zpzl" maxlength="10" readonly="true" value="thTp.tsTp.zpzl"/></td>
<th style="text-align: left;">张数</th>
<td><ui:number name="zshu" maxlength="10" readonly="true" value="thTp.tsTp.zshu"/></td>
</tr>
<tr>
<th style="text-align: left;">订货No.</th>
<td><ui:input name="dhno" required="true" readonly="true" maxlength="7" value="thTp.tsTp.dhno"/>-<ui:input name="line" required="true" readonly="true" maxlength="2" value="thTp.tsTp.line"/></td>
<th style="text-align: left;">用户略称</th>
<td><ui:input name="abbr" maxlength="10" readonly="true" value="thTp.tsTp.dhno"/></td>
<th style="text-align: left;">尺寸<span style="font-size: 12px;">(厚*宽*剪切板长)</span></th>
<td colspan="3"><ui:number name="xpho" scale="4" precision="3" readonly="true" value="thTp.tsTp.xpho"/>*<ui:number name="xpkn" scale="6" precision="2" readonly="true" value="thTp.tsTp.xpkn" />*<ui:number name="xpcn" scale="6" precision="2" readonly="true" value="thTp.tsTp.xpcn"/></td>
</tr>
<tr>
<th style="text-align: left;">镀锡量</th>
<td><ui:input name="sczm" maxlength="4" readonly="true" value="thTp.tsTp.sczm" />/<ui:input name="scfm" maxlength="4" readonly="true" value="thTp.tsTp.scfm" /></td>
<th style="text-align: left;">等级</th>
<td><ui:input name="deng" maxlength="3" readonly="true" value="thTp.tsTp.deng" /></td>
<th style="text-align: left;">运用规格</th>
<td><ui:input name="yuny" maxlength="6" readonly="true" value="thTp.tsTp.yuny" /></td>
<th style="text-align: left;">运输公司</th>
<td><ui:input name="ysnm" maxlength="28" readonly="true" value="thTp.tsTp.ysnm" /></td>
</tr>
<tr>
<th style="text-align: left;">表面</th>
<td><ui:input name="face" maxlength="2" readonly="true" value="thTp.tsTp.face"/></td>
<th style="text-align: left;">规格代码</th>
<td><ui:input name="ggno" maxlength="4" readonly="true" value="thTp.tsTp.ggno"/></td>
<th style="text-align: left;">原出库日期</th>
<td><ui:input name="chri" format="yyyy-MM-dd" maxlength="10" readonly="true" value="thTp.tsTp.chri"/></td>
<th style="text-align: left;">退货日期</th>
<td><ui:date name="thri" required="true" prop="calendar:true;"  value="thTp.thri"/></td>
</tr>
<tr>
<th style="text-align: left;">备注</th>
<td colspan="7"><ui:input name="bz" required="true" cssStyle="width: 35em;" value="thTp.bz" /></td>
</tr>
</table>
<div class="submit"> <input type="button" class="button" value="保 存" onclick="cocoform.submit(this);" /> </div>
</form>
