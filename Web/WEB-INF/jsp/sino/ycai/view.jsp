<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="update.do" xu.ajax="" xu.r="" xu.s="success();" xu.color="1" name="DataForm" method="post" >
<table class="form">
<colgroup>
<col width="10%" />
<col width="20%" />
<col width="10%" />
<col width="10%" />
<col width="20%" />
<col width="20%" />
<col width="10%" />
</colgroup>
<tr>
<th style="text-align: left;">供应商合同No.</th>
<td><ui:input name="ybno" value="ycaiTp.ybno" title="供应商合同No" maxlength="8" onkeydown="if(window.event.keyCode == 13) doLand();" />-<ui:number name="line" value="ycaiTp.line" maxlength="3" onblur="doLand();" title="行号" /></td>
<th style="text-align: right;">原材卷板No.</th>
<td><ui:input name="jbno" value="ycaiTp.jbno" readonly="true" title="原材卷板DB的原材卷板No." maxlength="7" /></td>
<td colspan="3">&nbsp;</td>
</tr>
</table>
<table class="form">
<colgroup>
<col width="10%" />
<col width="20%" />
<col width="10%" />
<col width="10%" />
<col width="15%" />
<col width="15%" />
<col width="20%" />
</colgroup>
<tr>
<th style="text-align: left;">制造商卷板NO.</th>
<td><ui:input name="zzsj" value="ycaiTp.zzsj" maxlength="15" required="true" title="原材卷板DB的原板采购No." /></td>
<th style="text-align: right;">重量(T)</th>
<td><ui:number scale="6" precision="3" name="tun" maxlength="7" required="true" positive="true" onblur="calculatJbcn();" value="ycaiTp.tun" title="实际重量" /></td>
<th style="text-align: left;">原板长&nbsp;&nbsp;<ui:input name="jbcn" value="ycaiTp.jbcn" maxlength="8" required="true" readonly="true" title="原板长" /></th>
<td colspan="2">&nbsp;</td>
</tr>
<tr>
<th style="text-align: center;">尺寸·厚</th>
<th style="text-align: center;">尺寸·宽</th>
<th style="text-align: center;">商社</th>
<th style="text-align: center;">币种</th>
<th style="text-align: center;">制造商规格略称</th>
<th style="text-align: center;">中日达规格略称</th>
<th style="text-align: center;">规格</th>
</tr>
<tr>
<td style="text-align: center;"><ui:input name="xpho" value="ycaiTp.xpho" readonly="true" maxlength="6"  title="原材卷板DB的订货尺寸.厚" /></td>
<td style="text-align: center;"><ui:input name="xpkn" value="ycaiTp.xpkn" readonly="true" maxlength="6"  title="原材卷板DB的订货尺寸.宽" /></td>
<td style="text-align: center;"><ui:input name="ssno" value="ycaiTp.ssno" readonly="true" maxlength="6" title="商社" /></td>
<td style="text-align: center;"><ui:input name="thqf" value="ycaiTp.thqf" readonly="true" maxlength="6" title="币种" /></td>
<td style="text-align: center;"><ui:input name="yblc" value="ycaiTp.yblc" readonly="true" maxlength="12" title="制造商规格略称" /></td>
<td style="text-align: center;"><ui:input name="ggnm" value="ycaiTp.ggnm" readonly="true" maxlength="15" title="中日达规格略称" /></td>
<td style="text-align: center;"><ui:input name="ggno" value="ycaiTp.ggno" readonly="true" maxlength="6" title="规格" /></td>
</tr>
<tr>
<th style="text-align: center;">运用规格</th>
<th style="text-align: center;">表面</th>
<th style="text-align: center;">等级</th>
<th style="text-align: center;">内径</th>
<th style="text-align: center;">制造商</th>
<th style="text-align: center;">品种</th>
<th style="text-align: center;">制造年月日</th>
</tr>
<tr>
<td style="text-align: center;"><ui:input name="yuny" value="ycaiTp.yuny" readonly="true" maxlength="6" title="运用规格" /></td>
<td style="text-align: center;"><ui:input name="face" value="ycaiTp.face" readonly="true" maxlength="2" title="表面" /></td>
<td style="text-align: center;"><ui:input name="deng" value="ycaiTp.deng" readonly="true" maxlength="6" title="级" /></td>
<td style="text-align: center;"><ui:input name="njno" value="ycaiTp.njno" readonly="true" maxlength="3" title="内径" /></td>
<td style="text-align: center;"><ui:input name="rsv1" value="ycaiTp.rsv1" readonly="true" maxlength="6" title="制造商" /></td>
<td style="text-align: center;"><ui:input name="pzno" value="ycaiTp.pzno" readonly="true" maxlength="2" title="品种代码" /></td>
<td style="text-align: center;"><ui:input name="zzny" value="ycaiTp.zzny"  readonly="true" maxlength="10"  title="制造年月日"/></td>
</tr>
<tr>
<th style="text-align: center;">装船日期</th>
<th style="text-align: center;">船名</th>
<th style="text-align: center;">采购单价</th>
<th colspan="4">&nbsp;</th>
</tr>
<tr>
<td style="text-align: center;"><ui:date name="blny" value="ycaiTp.blny" required="true" prop="calendar:true;" /></td>
<td style="text-align: center;"><ui:input name="ship" value="ycaiTp.ship" required="true"  maxlength="20" title="船名" /></td>
<td style="text-align: center;"><ui:input name="cgdj" value="ycaiTp.cgdj" readonly="true" maxlength="6" title="采购单价" /></td>
<td colspan="4">&nbsp;</td>
</tr>
<tr>
<td colspan="7"><div class="opt-btm"><input type="button" class="button" onclick="cocoform.submit(this,null,'是否确定保存?');" value="保 存" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('View')" /></div></td>
</tr>
</table>
</form>
