<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" name="id" value="${entity.id }" /><input type="hidden" name="ddno" value="${entity.ddno }" /><input type="hidden" name="ddnm" value="${entity.ddnm }" /><input type="hidden" name="fpln" value="${entity.fpln }" />
<table width="100%" >
<colgroup>
<col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="11%" />
<col width="9%" /><col width="9%" /><col width="6%" /><col width="6%" />
<col width="9%" /><col width="9%" /><col width="9%" />
</colgroup>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">创建日期</th>
<td colspan="2"><ui:date name="crea" cssClass="normal" required="true" showCalendar="true" value="entity.crea" onchange="changeDate(this.value);" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">预定出货日期</th>
<td colspan="2"><ui:date name="chqi" cssClass="normal" value="entity.chqi" showCalendar="true" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;" style="text-align: left;">预安排生产量</th>
<td colspan="2"><ui:number name="yscl" cssClass="normal" title="预安排生产量" value="entity.yscl" scale="7" precision="3" positive="true"/></td>
<th height="23" colspan="3" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;" style="text-align: left;">是否紧急&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="sfjj" value="Y" <c:if test="${entity.sfjj != null && entity.sfjj == \"Y\" }">checked</c:if>  /></th>
</tr>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">订货NO</th>
<td colspan="2"><ui:input name="dhno" cssClass="normal" title="订货DB的订货NO" maxlength="7" required="true" value="entity.dhno" />-<ui:input name="line" cssClass="normal" title="行号" maxlength="2" value="entity.line" required="true" onblur="doLine(this);" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">合同量</th>
<td><ui:number name="htzl" cssClass="normal" title="合同量" value="entity.htzl" scale="7" precision="3" positive="true"/></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">运用规格</th>
<td><ui:input name="yuny" cssClass="normal" title="运用规格" value="entity.yuny" maxlength="6" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">表面</th>
<td><ui:input name="face" cssClass="normal" value="entity.face" title="表面" maxlength="2" /></td>
<th height="23" colspan="3" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">镀锡量&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="fudw" title="附着量.单位" value="entity.fudw" maxlength="2" /> <ui:input name="fuzm" title="附着量.正面" value="entity.fuzm" maxlength="3" />/<ui:input name="fufm" title="附着量.反面" value="entity.fufm" maxlength="3" /></th>
</tr>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">交货日期</th>
<td colspan="2"><ui:input name="jhqi" cssClass="normal" value="entity.jhqi" maxlength="10" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">尺寸</th>
<td colspan="3"><ui:number name="houu" cssClass="normal" title="订货DB的订货尺寸.厚" value="entity.houu" scale="4" precision="3" positive="true"/>
*<ui:number name="kuan" cssClass="normal" value="entity.kuan" title="订货DB的订货尺寸.宽" scale="6" precision="2" positive="true"/>
*<ui:number name="cang" cssClass="normal" value="entity.cang" title="订货DB的订货尺寸.长" scale="6" precision="2" positive="true"/></td>
<th height="23" colspan="5" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">用户名称&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="name" cssClass="normal" title="用户" value="entity.name" maxlength="26" /><input type="hidden" name="abbr" value="${entity.abbr }" /> <input type="hidden" name="user" value="${entity.user }" /></th>
</tr>
</table>