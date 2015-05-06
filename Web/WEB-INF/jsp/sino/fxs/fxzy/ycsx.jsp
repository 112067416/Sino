<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/sys" prefix="sys" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%">
	<colgroup><col width="10%" /><col width="90%" /></colgroup>
	<tr>
		<td colspan="2">
			<table id="ItemTbl" width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="pagination">
				<colgroup><col width="4%" /><col width="7%" /><col width="4%" /><col width="7%" /><col width="5%" /><col width="17%" /><col width="17%" />
				<col width="10%" /><col width="5%" /><col width="7%" /><col width="9%" /><col width="5%" /><col width="3%" /></colgroup>
				<tr>
					<th nowrap="nowrap"><input type="checkbox" onclick="coco.selAll('ids',this)" /></th>
					<th nowrap="nowrap">Coil No.</th>
					<th nowrap="nowrap">部位</th>
					<th nowrap="nowrap">异常项目</th>
					<th nowrap="nowrap">目标量</th>
					<th nowrap="nowrap">异常数值</th>
					<th nowrap="nowrap">再分析情况</th>
					<th nowrap="nowrap">用户略称</th>
					<th nowrap="nowrap">调质度</th>
					<th nowrap="nowrap">订货No.</th>
					<th nowrap="nowrap">订货尺寸</th>
					<th nowrap="nowrap">用途</th>
					<th nowrap="nowrap">表面</th>
				</tr>
				<c:forEach varStatus="stat" var="item" items="${ycsxs }">
				<tr>
					<td nowrap="nowrap" style="vertical-align: bottom;" align="center" ><input type="checkbox" name="ids" value="${item.id }" /><input type="hidden" name="id" value="${item.id }" /></td>
					<td nowrap="nowrap" style="vertical-align: bottom;"><ui:input name="jbno" value="item.jbno" maxlength="7" onblur="getDh(this);" onkeydown="if(window.event.keyCode == 13) getDh(this);" /></td>
					<td nowrap="nowrap" style="vertical-align: bottom;"><sys:codeSelect name="bw" code="#SINO_FXS_BW" prop="nn:1;" headerText="" headerValue="" value="item.bw" /></td>
					<td nowrap="nowrap" style="vertical-align: bottom;"><sys:codeSelect name="ycxm" code="#SINO_YCXM" prop="nn:1;" headerText="" headerValue="" value="item.ycxm" /></td>
					<td nowrap="nowrap" style="vertical-align: bottom;"><c:if test="${item.fuzm != null }">${item.fuzm }/</c:if>${item.fufm }<br/>${item.yqty }</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">
					F <ui:number scale="4" precision="2" maxlength="5" name="ycfs" value="item.ycfs" /> <ui:number scale="4" precision="2" maxlength="5" name="ycfc" value="item.ycfc" /> <ui:number scale="4" precision="2" maxlength="5" name="ycfm" value="item.ycfm" /><br />
					B <ui:number scale="4" precision="2" maxlength="5" name="ycbs" value="item.ycbs" /> <ui:number scale="4" precision="2" maxlength="5" name="ycbc" value="item.ycbc" /> <ui:number scale="4" precision="2" maxlength="5" name="ycbm" value="item.ycbm" />
					</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">
					F <ui:number scale="4" precision="2" maxlength="5" name="fxfs" value="item.fxfs" /> <ui:number scale="4" precision="2" maxlength="5" name="fxfc" value="item.fxfc" /> <ui:number scale="4" precision="2" maxlength="5" name="fxfm" value="item.fxfm" /><br />
					B <ui:number scale="4" precision="2" maxlength="5" name="fxbs" value="item.fxbs" /> <ui:number scale="4" precision="2" maxlength="5" name="fxbc" value="item.fxbc" /> <ui:number scale="4" precision="2" maxlength="5" name="fxbm" value="item.fxbm" />
					</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">${item.abbr }</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">${item.yuny }</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">${item.dhno }</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
					<td nowrap="nowrap" style="vertical-align: bottom;">${item.cond }</td>
					<td nowrap="nowrap" style="vertical-align: bottom;">${item.face }</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
<div class="submit">
<table width="100%">
<tr>
<td align="left" colspan="2">
<ui:textarea name="ycsx" cols="130" rows="14" value="fxzyRz.ycsx" />
</td>
</tr>
<tr><td align="left">
<input type="button" class="button" value="添 加" onclick="tableForm.insertEmptyRow('ItemTblTmp');" />
<input type="button" class="button" value="移 除" onclick="tableForm.removeRow('ids');" />
</td><td align="right">
<input type="button" class="button" value="保 存" onclick="saveYcsx(this.form);" /> <input type="button" class="button"
		value="关 闭" onclick="coco.hidePage('YcsxPanel')" />
</td>
</tr>
</table>
</div>