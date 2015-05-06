<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView">
<table align="left" width="730" border="0" cellpadding="0" cellspacing="0" border="0">
<colgroup><col width="720" /><col width="10" /></colgroup>
<tr>
<td>
<table align="left" width="720" border="0" cellpadding="0" cellspacing="0" border="0">
<tr><td height="30" style="font-size: 16px; font-weight: bold;  vertical-align: bottom; text-align: center;"><u>不良扣除联络单</u></td></tr>
<tr><td height="15" style="font-size: 14px; text-align: center;">（Contact List）</td></tr>
<tr><td height="15" style="font-size: 14px; text-align: center;">&nbsp;</td></tr>
<tr><td height="15" style="font-size: 13px; text-align: left; padding-left: 25px;">该卷中混有不良,产品标签显示的卷重已经扣除了不良部分的重量</td></tr>
<tr><td height="15" style="font-size: 13px; text-align: left; padding-left: 25px;">This Product Contains defect portion.The weight of defect portion is decreased.</td></tr>
<tr>
<td>
<table width="720" align="left" cellpadding="0" cellspacing="0" border="1" bordercolor="#0eb8e4" style="color: #000000; border-collapse: collapse; margin-top: 5px; text-align: left; font-size: 12px;">
<colgroup><col width="90" /><col width="90"/><col width="220"/><col width="330"/></colgroup>
<tr>
<td height="27px" style="padding-left: 10px; border-right: 0px;">日期(DATE)</td>
<td style="border-left: 0px; border-right: 0px;"><span style="font-size: 20px;"><f:v value="item.upda" format="yyyy-MM-dd" /></span></td>
<td style="border-left: 0px;">&nbsp;</td>
<td style="padding-left: 10px;">卷 号&nbsp;(Coil No)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${item.zzsd }-${item.jbno }</span></td>
</tr>
<tr>
<td height="27px" colspan="4" style="padding-left: 10px;">客户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${name } </span></td>
</tr>
<tr>
<td colspan="3" style="padding-left: 10px;"> 不良名称(defect)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${item.losq }</span></td>
<td style="padding-left: 10px;">扣除长度&nbsp;(decreased length)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${item.losc }</span>&nbsp;&nbsp;&nbsp;m</td>
</tr>
<tr>
<td colspan="4" height="27px" style="padding-left: 10px;">
不良分布(destribution)：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="lxx" <c:if test="${lxx }">checked="checked"</c:if> />  <span style="font-size: 18px;">连续性(continuty)；</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="jdx" <c:if test="${jdx }">checked="checked"</c:if> />  <span style="font-size: 18px;">间断性(intermittent)；</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 18px;"><input type="checkbox" name="lx" <c:if test="${lx }">checked="checked"</c:if> />  零星(spordic)</span>
</td>
</tr>
<tr>
<td colspan="4" height="80">
<table width="720" align="left" cellpadding="0" cellspacing="0" border="0" style="color: #000000; font-size: 12px;">
<colgroup><col width="60"><col width="570"><col><col width="100"></colgroup>
<tr><td height="15" colspan="3" align="center" style="font-size: 15px; font-weight: bold;"><u>不良位置(defect portion)</u></td></tr>
<tr><td height="15" style="padding-left: 10px;">内圈</td><td>&nbsp;</td><td>外圈 </td></tr>
<tr><td height="15" style="padding-left: 10px;">Inner</td><td rowspan="2"><img alt="不良位置" src="/images/sino/bl_defect.png" width="550" height="15"></td><td>Outer</td></tr>
<tr><td height="15" style="padding-left: 10px;"><span style="font-size: 20px;">0m</span></td><td style="text-align: left;"><span style="font-size: 20px;">${item.jbcn }</span>m</td></tr>
</table>
</td>
</tr>
<tr>
<td rowspan="4" style="text-align: center;">不良简图<br />(sketch)</td>
<td height="10" align="left" valign="top" style="padding: 8px; line-height: 14px; border-bottom: 0px; border-right: 0px;"><span>表面<br />(Top)</span></td>
<td colspan="2" rowspan="2" valign="top" style="text-align: left; border-left: 0px;">
	<span><img alt="表面简图" src="/images/sino/bl_top.png" width="500" height="70"></span>
</td>
</tr>
<tr>
<td height="60" style="border-top: 0px; border-right: 0px;">&nbsp;</td>
</tr>
<tr>
<td height="10" align="left" valign="top" style="padding: 8px; line-height: 14px; border-bottom: 0px; border-right: 0px;"><span>背面<br />(Bottom)</span></td>
<td colspan="2" rowspan="2" valign="top" style="text-align: left; border-left: 0px;">
	<span><img alt="背面简图" src="/images/sino/bl_bottom.png" width="500" height="70"></span>
</td>
</tr>
<tr>
<td height="60" style="border-top: 0px; border-right: 0px;">&nbsp;</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
<td style="text-align: right;">
第<br />一<br />联<br />存<br />档<br />联
</td>
</tr>
<tr><td height="30" colspan="2">&nbsp;</td></tr>
<tr>
<td>
<table align="left" width="720" border="0" cellpadding="0" cellspacing="0" border="0">
<tr><td height="30" style="font-size: 16px; font-weight: bold;  vertical-align: bottom; text-align: center;"><u>不良扣除联络单</u></td></tr>
<tr><td height="15" style="font-size: 14px; text-align: center;">（Contact List）</td></tr>
<tr><td height="15" style="font-size: 14px; text-align: center;">&nbsp;</td></tr>
<tr><td height="15" style="font-size: 13px; text-align: left; padding-left: 25px;">该卷中混有不良,产品标签显示的卷重已经扣除了不良部分的重量</td></tr>
<tr><td height="15" style="font-size: 13px; text-align: left; padding-left: 25px;">This Product Contains defect portion.The weight of defect portion is decreased.</td></tr>
<tr>
<td>
<table width="720" align="left" cellpadding="0" cellspacing="0" border="1" bordercolor="#0eb8e4" style="color: #000000; border-collapse: collapse; margin-top: 5px; text-align: left; font-size: 12px;">
<colgroup><col width="90" /><col width="90"/><col width="220"/><col width="330"/></colgroup>
<tr>
<td height="27px" style="padding-left: 10px; border-right: 0px;">日期(DATE)</td>
<td style="border-left: 0px; border-right: 0px;"><span style="font-size: 20px;"><f:v value="item.upda" format="yyyy-MM-dd" /></span></td>
<td style="border-left: 0px;">&nbsp;</td>
<td style="padding-left: 10px;">卷 号&nbsp;(Coil No)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${item.zzsd }-${item.jbno }</span></td>
</tr>
<tr>
<td height="27px" colspan="4" style="padding-left: 10px;">客户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${name } </span></td>
</tr>
<tr>
<td colspan="3" style="padding-left: 10px;"> 不良名称(defect)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${item.losq }</span></td>
<td style="padding-left: 10px;">扣除长度&nbsp;(decreased length)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${item.losc }</span>&nbsp;&nbsp;&nbsp;m</td>
</tr>
<tr>
<td colspan="4" height="27px" style="padding-left: 10px;">
不良分布(destribution)：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="lxx" <c:if test="${lxx }">checked="checked"</c:if> />  <span style="font-size: 18px;">连续性(continuty)；</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="jdx" <c:if test="${jdx }">checked="checked"</c:if> />  <span style="font-size: 18px;">间断性(intermittent)；</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 18px;"><input type="checkbox" name="lx" <c:if test="${lx }">checked="checked"</c:if> />  零星(spordic)</span>
</td>
</tr>
<tr>
<td colspan="4" height="80">
<table width="720" align="left" cellpadding="0" cellspacing="0" border="0" style="color: #000000; font-size: 12px;">
<colgroup><col width="60"><col width="570"><col><col width="100"></colgroup>
<tr><td height="15" colspan="3" align="center" style="font-size: 15px; font-weight: bold;"><u>不良位置(defect portion)</u></td></tr>
<tr><td height="15" style="padding-left: 10px;">内圈</td><td>&nbsp;</td><td>外圈 </td></tr>
<tr><td height="15" style="padding-left: 10px;">Inner</td><td rowspan="2"><img alt="不良位置" src="/images/sino/bl_defect.png" width="550" height="15"></td><td>Outer</td></tr>
<tr><td height="15" style="padding-left: 10px;"><span style="font-size: 20px;">0m</span></td><td style="text-align: left;"><span style="font-size: 20px;">${item.jbcn }</span>m</td></tr>
</table>
</td>
</tr>
<tr>
<td rowspan="4" style="text-align: center;">不良简图<br />(sketch)</td>
<td height="10" align="left" valign="top" style="padding: 8px; line-height: 14px; border-bottom: 0px; border-right: 0px;"><span>表面<br />(Top)</span></td>
<td colspan="2" rowspan="2" valign="top" style="text-align: left; border-left: 0px;">
	<span><img alt="表面简图" src="/images/sino/bl_top.png" width="500" height="70"></span>
</td>
</tr>
<tr>
<td height="60" style="border-top: 0px; border-right: 0px;">&nbsp;</td>
</tr>
<tr>
<td height="10" align="left" valign="top" style="padding: 8px; line-height: 14px; border-bottom: 0px; border-right: 0px;"><span>背面<br />(Bottom)</span></td>
<td colspan="2" rowspan="2" valign="top" style="text-align: left; border-left: 0px;">
	<span><img alt="背面简图" src="/images/sino/bl_bottom.png" width="500" height="70"></span>
</td>
</tr>
<tr>
<td height="60" style="border-top: 0px; border-right: 0px;">&nbsp;</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
<td style="text-align: right;">
第<br />二<br />联<br />客<br />户<br />联
</td>
</tr>
</table>
</div>