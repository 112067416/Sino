<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="PrintDiv" style="background-color: #FFFFFF; color: #000000; height: 510px;">
<table align="center"" width="730" border="0" cellpadding="0" cellspacing="0" border="0" style="color: #000000;" >
<tr><td height="30" style="font-size: 16px; font-weight: bold;  vertical-align: bottom; text-align: center;"><u>不良扣除联络单</u></td></tr>
<tr><td height="15" style="font-size: 14px; text-align: center;">（Contact List）</td></tr>
<tr><td height="15" style="font-size: 14px; text-align: center;">&nbsp;</td></tr>
<tr><td height="15" style="font-size: 12px; text-align: left; padding-left: 25px;">该卷中混有不良,产品标签显示的卷重已经扣除了不良部分的重量</td></tr>
<tr><td height="15" style="font-size: 12px; text-align: left; padding-left: 25px;">This Product Contains defect portion.The weight of defect portion is decreased.</td></tr>
<tr>
<td>
<table width="730" align="left" cellpadding="0" cellspacing="0" border="1" bordercolor="#0eb8e4" style="color: #000000; border-collapse: collapse; margin-top: 5px; text-align: left; font-size: 12px;">
<colgroup><col width="100" /><col width="50"/><col width="250"/><col width="330"/></colgroup>
<tr>
<td height="20px" colspan="3" style="padding-left: 10px;">日期(DATE)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:v value="item.upda" format="yyyy-MM-dd" /></td>
<td style="padding-left: 10px;">卷号(Coil No)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.zzsd }-${item.jbno }</td>
</tr>
<tr>
<td height="20px" colspan="4" style="padding-left: 10px;">客户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${name } </td>
</tr>
<tr>
<td colspan="3" style="padding-left: 10px;"> 不良名称1(defect)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.losq }</td>
<td style="padding-left: 10px;">扣除长度1(decreased length)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.losc }&nbsp;m</td>
</tr>
<tr>
<td colspan="4" height="20px" style="padding-left: 10px;">
不良分布1(destribution)：&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="lxx" id="lxx" />连续性(continuty)；
<input type="checkbox" name="jdx" id="jdx" />间断性(intermittent)；	
<input type="checkbox" name="lx" id="lx" />零星(spordic)
</td>
</tr>
<tr>
<td colspan="3" style="padding-left: 10px;"> 不良名称2(defect)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.losq2 }</td>
<td style="padding-left: 10px;">扣除长度2(decreased length)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.losc2 }&nbsp;m</td>
</tr>
<tr>
<td colspan="4" height="20px" style="padding-left: 10px;">
不良分布2(destribution)：&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="lxx2" id="lxx2" />连续性(continuty)；
<input type="checkbox" name="jdx2" id="jdx2" />间断性(intermittent)；	
<input type="checkbox" name="lx2" id="lx2" />零星(spordic)
</td>
</tr>
<tr>
<td colspan="4" height="80">
<table width="730" align="left" cellpadding="0" cellspacing="0" border="0" style="color: #000000; font-size: 12px;">
<colgroup><col width="60"><col width="610"><col><col width="60"></colgroup>
<tr><td height="15" colspan="3" align="center" style="font-size: 15px; font-weight: bold;"><u>不良位置(defect portion)</u></td></tr>
<tr><td height="15" style="padding-left: 10px;">内圈</td><td>&nbsp;</td><td>外圈 </td></tr>
<tr><td height="15" style="padding-left: 10px;">Inner</td><td rowspan="2"><img alt="不良位置" src="/images/sino/bl_defect.png" width="550" height="15"></td><td>Outer</td></tr>
<tr><td height="15" style="padding-left: 10px;">0m</td><td>${item.jbcn }m</td></tr>
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
</div>
<table width="730" align="center" cellpadding="0" cellspacing="0" border="0" style="text-align: right; margin-top: 10px;">
<tr>
<td>
<input type="button" class="button" value="打  印" onclick="printLoss('${item.jbno }',${total });" />
<input type="button" class="button" value="关  闭" onclick="coco.hidePage('LosscView');" />
</td>
</tr>
</table>
