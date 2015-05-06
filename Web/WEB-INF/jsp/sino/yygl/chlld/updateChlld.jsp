<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" name="id" value="${entity.id }" />
<table width="100%" class="form" >
<colgroup><col width="10%" /><col width="17%" /><col width="15%" /><col width="14%" /><col width="8%" /><col width="36%" /></colgroup>
<tr>
<th>出货日期</th>
<td><ui:date name="chqi" cssClass="normal" required="true" showCalendar="true" value="entity.chqi" /></td>
<th style="text-align: left;">运输方式</th>
<td><ui:select name="ysfs" value="entity.ysfs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_YSFS'" prop="nn:1;" headerText="请选择" headerValue="" onchange="changeYsfs(this)" /><input type="hidden" name="ysfm" value="${entity.ysfm }" /></td>
<td colspan="2">&nbsp;</td>
</tr>
<tr>
<th style="text-align: left;">到达地点</th>
<td><ui:select name="addr" value="entity.addr" list="${shdz }" prop="nn:1;" headerText="请选择" headerValue="" /></td>
<th style="text-align: left;">吨数</th>
<td><ui:number name="chzl" value="entity.chzl" cssClass="normal" title="重量" scale="7" precision="3" required="true" positive="true"/></td>
<td colspan="2">&nbsp;</td>
</tr>
<tr>
<th style="text-align: right;vertical-align: middle;">备货状况</th>
<td colspan="5"><ui:textarea name="bhzk" value="entity.bhzk" cssStyle="" cssClass="normal" cols="100" rows="4" title="备货状况"/></td>
</tr>
</table>