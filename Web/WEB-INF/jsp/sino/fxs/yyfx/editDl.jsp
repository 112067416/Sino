<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="/sys" prefix="sys" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="id" value="${entity.id }" />
<table width="96%" align="center" class="form"><colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="30%" /><col width="10%" /><col width="30%" /></colgroup>
<tr><th style="text-align: center; vertical-align: middle;">班组</th>
<td><sys:codeSelect name="banzu" code="#SINO_ZU" prop="nn:1;" headerText="" headerValue="" value="entity.banzu" /></td>
<th style="text-align: center; vertical-align: middle;">记录日期</th>
<td><ui:datetime name="rq" required="true" showCalendar="false" value="entity.rq" /></td>
<th style="text-align: center; vertical-align: middle;">确认人</th>
<td><ui:input name="qrr" maxlength="12" value="entity.qrr" /></td></tr>
</table>
<table width="100%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse; text-align: center;">
<tr><th style="text-align: center; vertical-align: middle;" style="text-align: center; vertical-align: middle;" colspan="2">碱浸液</th><th style="text-align: center; vertical-align: middle;" colspan="2">碱电解液</th><th style="text-align: center; vertical-align: middle;" colspan="2">酸电解液</th><th style="text-align: center; vertical-align: middle;" colspan="5">锡电镀液</th></tr>
<tr><th style="text-align: center; vertical-align: middle;">Free</th><th style="text-align: center; vertical-align: middle;">Total</th><th style="text-align: center; vertical-align: middle;">Free</th><th style="text-align: center; vertical-align: middle;">Total</th><th style="text-align: center; vertical-align: middle;">H<sub>2</sub>S0<sub>4</sub></th><th style="text-align: center; vertical-align: middle;">Fe</th><th style="text-align: center; vertical-align: middle;">Sn</th><th style="text-align: center; vertical-align: middle;">Acid</th><th style="text-align: center; vertical-align: middle;">ENSA</th><th style="text-align: center; vertical-align: middle;">Fe</th><th style="text-align: center; vertical-align: middle;">Sludge</th></tr>
<tr>
<td><ui:number scale="4" precision="2" maxlength="5" name="jjyFreeCon" value="entity.jjyFreeCon" max="${yypdVO.jyZyjMax }" min="${yypdVO.jyZyjMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.jyZyjBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="jjyTotalCon" value="entity.jjyTotalCon" max="${yypdVO.jyZjMax }" min="${yypdVO.jyZjMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.jyZjBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="jdjyFreeCon" value="entity.jdjyFreeCon" max="${yypdVO.jdjZyjMax }" min="${yypdVO.jdjZyjMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.jdjZyjBz }')"  /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="jdjyTotalCon" value="entity.jdjyTotalCon" max="${yypdVO.jdjZjMax }" min="${yypdVO.jdjZjMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.jdjZjBz }')" /></td>
<td><ui:number scale="5" precision="2" maxlength="6" name="sdjyHsoCon" value="entity.sdjyHsoCon" max="${yypdVO.sdjLsMax }" min="${yypdVO.sdjLsMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.sdjLsBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="sdjyQt" value="entity.sdjyQt" max="${yypdVO.sdjQtMax }" min="${yypdVO.sdjQtMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.sdjQtBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="xddySntCon" value="entity.xddySntCon" max="${yypdVO.ddXMax }" min="${yypdVO.ddXMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.ddXBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="xddyAcidtCon" value="entity.xddyAcidtCon" max="${yypdVO.ddSMax }" min="${yypdVO.ddSMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.ddSBz }')"  /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="xddyEnsa" value="entity.xddyEnsa" max="${yypdVO.ddEnsaMax }" min="${yypdVO.ddEnsaMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.ddEnsaBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="xddyFe" value="entity.xddyFe" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="xddySludge" value="entity.xddySludge" /></td>
</tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">拖出液</th><th style="text-align: center; vertical-align: middle;" colspan="3">化学处理液</th><th style="text-align: center; vertical-align: middle;" colspan="2">中央铬</th><th colspan="4" style="text-align: center; vertical-align: middle;">备注</th></tr>
<tr><th style="text-align: center; vertical-align: middle;">Sn</th><th style="text-align: center; vertical-align: middle;">Acid</th><th style="text-align: center; vertical-align: middle;">Cr</th><th style="text-align: center; vertical-align: middle;">PH</th><th style="text-align: center; vertical-align: middle;">Sludge</th><th style="text-align: center; vertical-align: middle;">浓度</th><th style="text-align: center; vertical-align: middle;">PH</th><th colspan="4" rowspan="2" style="text-align: center; vertical-align: middle;"><ui:textarea name="bz" value="entity.bz" cssStyle="width:100%;" cssClass="normal" rows="3" title="备注"/></th></tr>
<tr>
<td><ui:number scale="4" precision="2" maxlength="5" name="tcySnCon" value="entity.tcySnCon" max="${yypdVO.qyyXMax }" min="${yypdVO.qyyXMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.qyyXBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="tcyAcidCon" value="entity.tcyAcidCon" max="${yypdVO.qyySMax }" min="${yypdVO.qyySMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.qyySBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="hxclyCrCon" value="entity.hxclyCrCon" max="${yypdVO.hxLMax }" min="${yypdVO.hxLMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.hxLBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="hxclyPh" value="entity.hxclyPh" max="${yypdVO.hxPhMax }" min="${yypdVO.hxPhMin }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${yypdVO.hxPhBz }')" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="hxclySludge" value="entity.hxclySludge" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="zygCon" value="entity.zygCon" /></td>
<td><ui:number scale="4" precision="2" maxlength="5" name="zygPh" value="entity.zygPh" /></td>
</tr>
</table><div class="opt-btm"><input type="button" class="button" value="保   存" onclick="doUpdate(this);"/>	  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('EditYyfxArea');"/></div>