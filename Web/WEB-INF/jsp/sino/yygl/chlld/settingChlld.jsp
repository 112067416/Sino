<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" name="id" value="${entity.id }" />
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="10%" /><col width="20%" /><col width="30%" /></colgroup>
<tr>
<th style="text-align: left;">出货日期</th>
<td style="text-align: left;"><ui:date name="chqi" required="true" value="entity.chqi" /></td>
<th style="text-align: right;">吨数</th>
<td style="text-align: left;"><ui:number name="chzl" value="entity.chzl" cssClass="normal" title="重量" scale="7" precision="3" maxlength="8" required="true" positive="true" /></td>
<td colspan="4">&nbsp;</td>
</tr>
<tr>
<th style="text-align: left;">运输公司</th>
<td style="text-align: left;"><ui:select name="ysgs" value="entity.ysgs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='021' and valid_=1" headerText="请选择" headerValue="" onchange="changeValue(this,'ysnm');" /><input type="hidden" value="${entity.ysnm }" name="ysnm" /></td>
<th style="text-align: right;">送货点</th>
<td style="text-align: left;"><ui:select name="shno" value="entity.shno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SHD' and valid_=1" headerText="请选择" headerValue="" onchange="changeValue(this,'shnm');" /><input type="hidden" value="${entity.shnm }" name="shnm" /></td>
<td colspan="2">&nbsp;</td>
</tr>
<tr>
<th style="text-align: left;vertical-align: middle;">备货状况</th>
<td colspan="7"><ui:textarea name="bhzk" value="entity.bhzk" cssStyle="" cssClass="normal" cols="100" rows="4" title="备货状况"/></td>
</tr>
</table>