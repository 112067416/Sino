<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" name="id" value="${entity.id }" />
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="30%" /></colgroup>
<tr>
<th style="text-align: left;">运输公司</th>
<td><ui:select name="ysgs" value="entity.ysgs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='021' and valid_=1" headerText="请选择" headerValue="" onchange="changeValue(this,'ysnm');" /><input type="hidden" name="ysnm" value="${entity.ysnm }" /></td>
<th style="text-align: left;">送货点</th>
<td><ui:select name="shno" value="entity.shno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SHD' and valid_=1" headerText="请选择" headerValue="" onchange="changeValue(this,'shnm');" /><input type="hidden" name="shnm" value="${entity.shnm }" /></td>
<th style="text-align: left;">吨数</th>
<td><ui:number name="chzl" value="entity.chzl" cssClass="normal" title="重量" scale="7" precision="3" maxlength="8" required="true" positive="true" /></td>
</tr>
</table>