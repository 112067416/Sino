<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@
    taglib uri="/ui" prefix="ui" %><%@
    taglib uri="/f" prefix="f"%><%@
    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fieldset class="group"><legend>采样单信息</legend>
<table width="100%">
	<colgroup>
		<col width="10%" />
		<col width="60%" />
		<col width="10%" />
		<col width="20%" />
	</colgroup>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">单号</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;" id="CyrzId" xu.type="${type }">${item.id }</td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">发行时间</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.date" format="yyyy-MM-dd HH:mm" /></td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">指示连号</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;">${item.zsno }</td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">Coil No.</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;">${item.jbno }</td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">镀锡量</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;">${item.dxl }</td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">涂油量</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;">${item.tyl }</td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">分析项目</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;">${item.fxxm }</td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">采样位置</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;">${item.cywz }</td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">发行班</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.ban"
			ql="select value from CodeItem where code.id='SINO_BAN' and key=?" /></td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">发行组</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.zu"
			ql="select value from CodeItem where code.id='SINO_ZU' and key=?" /></td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">备注</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;" colspan="3">${item.bz }</td>
	</tr>
</table>
</fieldset>
<fieldset class="group"><legend>SL操作</legend>
<table width="100%">
	<colgroup>
		<col width="10%" />
		<col width="60%" />
		<col width="10%" />
		<col width="20%" />
	</colgroup>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">收单日期</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.slsd" format="yyyy-MM-dd HH:mm" /></td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">送样日期</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.slsy" format="yyyy-MM-dd HH:mm" /></td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">备注</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;" colspan="3">${item.slBz }</td>
	</tr>
</table>
</fieldset>
<fieldset class="group"><legend>分析室操作</legend>
<table width="100%">
	<colgroup>
		<col width="10%" />
		<col width="60%" />
		<col width="10%" />
		<col width="20%" />
	</colgroup>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">收单日期</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.fxsd" format="yyyy-MM-dd HH:mm" /></td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">收样日期</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.fxsy" format="yyyy-MM-dd HH:mm" /></td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">分析日期</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"><f:v value="item.fxsj" format="yyyy-MM-dd HH:mm" /></td>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;"></th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;"></td>
	</tr>
	<tr>
		<th height="20" style="color:#99ffff; font-size: 19px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">备注</th>
		<td style="color:#ffff99; font-size:  16px; padding: 2px 2px; vertical-align: bottom;" colspan="3">${item.fxBz }</td>
	</tr>
</table>
</fieldset>
<div class="submit"><input type="button" class="button" value="关   闭" onclick="coco.hidePage('View');" /></div>
