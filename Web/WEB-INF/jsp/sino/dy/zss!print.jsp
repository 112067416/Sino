<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><div style="width:1298px;padding-top: 44px;">
<span style="padding-left: 420px; width: 100px; font-size: 40px; font-family: 'simsun';  border: 0px;">${type }</span>
<span style="padding-left: 500px;font-size: 22px; font-family: 'Courier'; border: 0px;"><f:v value="dysj" format="yy/MM/dd HH:mm:ss" /></span>
<div style="height:20px;">&nbsp;</div>
<div style="height:20px;"><span style="width:80px; font-size:12px;visibility: hidden;">指示连号</span><span style="font-size:26px;font-weight: bold;font-family: 'Courier';">${item.zsno }</span><span style="font-size:22px; font-weight: bold;font-family: 'Courier'; position: relative; left: 430px;">用途代码   ${item.cond }</span></div>
<div style="height:5px;font-size: 6px;">&nbsp;</div>
<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
<colgroup>
	<col width="50" /><col width="77" /><col width="86" /><col width="50" />
	<col width="80" /><col width="98" /><col width="160" /><col width="68" />
	<col width="68" /><col width="68" /><col width="68" /><col width="48" />
	<col width="70" /><col width="50" /><col width="69" /><col width="81" />
	<col width="58" /><col width="49" />
</colgroup>
<tr>
	<td height="32px" align="center" style="font-size:12px;visibility: hidden;">操 作<br/>方 法</td>
	<td align="center" style="font-size:12px;visibility: hidden;">装 入 宽</td>
	<td align="center" style="font-size:12px;visibility: hidden;">剪 断 长</td>
	<td align="center" style="font-size:12px;visibility: hidden;">修 边</td>
	<td align="center" style="font-size:12px;visibility: hidden;">修 边<br/>后  宽</td>
	<td align="center" style="font-size:12px;visibility: hidden;">运 用 规 格</td>
	<td align="center" style="font-size:12px;visibility: hidden;">锡 附 着 量  g/m<sup>2</sup></td>
	<td align="center" colspan="2" style="font-size:12px;visibility: hidden;">附 着 量 · 正<br/>下 限&nbsp;&nbsp;&nbsp;&nbsp;上 限</td>
	<td align="center" colspan="2" style="font-size:12px;visibility: hidden;">附 着 量 · 反<br/>下 限&nbsp;&nbsp;&nbsp;&nbsp;上 限</td>
	<td align="center" style="font-size:12px;visibility: hidden;">软熔<br/>是&nbsp;&nbsp;否</td>
	<td align="center" style="font-size:12px;visibility: hidden;">订&nbsp;&nbsp;&nbsp;货<br/>表面精加工</td>
	<td align="center" style="font-size:12px;visibility: hidden;">合金层</td>
	<td align="center" style="font-size:12px;visibility: hidden;">K-PLATE<br/>洗净强化</td>
	<td align="center" style="font-size:12px;visibility: hidden;">化学处理<br/>方&nbsp;&nbsp;&nbsp;法</td>
	<td align="center" colspan="2" style="font-size:12px;visibility: hidden;">目 标 涂 油 量<br/>油 种&nbsp;&nbsp;油 量</td>
</tr>
<tr>
	<td height="32px" align="center">&nbsp;${item.czff }</td>
	<td align="center" >${item.zrk }</td>
	<td align="center" >${item.jdc }</td>
	<td align="center" >${item.trim }</td>
	<td align="center" >${item.trimHk }</td>
	<td align="center" >${item.yygg }</td>
	<td align="center" >${item.xfzl }</td>
	<td align="center" >${item.fzxx }</td>
	<td align="center" >${item.fzsx }</td>
	<td align="center" >${item.ffxx }</td>
	<td align="center" >${item.ffsx }</td>
	<td align="center" >${item.reflow }</td>
	<td align="center" >${item.bmjg }</td>
	<td align="center" >${item.hjc }</td>
	<td align="center" >${item.kplate }</td>
	<td align="center" >${item.hxcl }</td>
	<td align="center" >${item.tyzl }</td>
	<td align="center" >${item.tyl }</td>
</tr>
</table></div>
<div style="height: 15px;font-size:6px;">&nbsp;</div>
<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#fffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
<colgroup>
	<col width="117" /><col width="145" /><col width="78" /><col width="79" />
	<col width="62" /><col width="68" /><col width="100" /><col width="99" />
	<col width="92" /><col width="84" /><col width="57" /><col width="78" />
	<col width="100" /><col width="50" /><col width="87" />
</colgroup>
<tr>
	<td height="32px" align="center" style="font-size:12px;visibility: hidden;">用 户 略 称</td>
	<td align="center" style="font-size:12px;visibility: hidden;">合 同 号 · 行 号</td>
	<td align="center" style="font-size:12px;visibility: hidden;">销 售 W 否</td>
	<td align="center" style="font-size:12px;visibility: hidden;">分 配 等 级</td>
	<td align="center" style="font-size:12px;visibility: hidden;">钢&nbsp;&nbsp;种<br/>TYPE</td>
	<td align="center" style="font-size:12px;visibility: hidden;">直 角 度</td>
	<td align="center" style="font-size:12px;visibility: hidden;">m 单 重</td>
	<td align="center" style="font-size:12px;visibility: hidden;">SHEET<br/>单 重</td>
	<td align="center" style="font-size:12px;visibility: hidden;">112张单重</td>
	<td align="center" style="font-size:12px;visibility: hidden;">交货区分</td>
	<td align="center" style="font-size:12px;visibility: hidden;">内&nbsp;&nbsp;径<br/>代&nbsp;&nbsp;码</td>
	<td align="center" style="font-size:12px;visibility: hidden;">内径保护筒</td>
	<td align="center" style="font-size:12px;visibility: hidden;">保护标记</td>
	<td align="center" colspan="2" style="font-size:12px;visibility: hidden;">SCROLL<br/>可否&nbsp;&nbsp;&nbsp;&nbsp;种类</td>
</tr>
<tr>
	<td height="32px" align="center">&nbsp;${item.yhlc }</td>
	<td align="center" >${item.dhno }-${item.dhline }</td>
	<td align="center" >${item.w }</td>
	<td align="center" >${item.fpdj }</td>
	<td align="center" >${item.gz }</td>
	<td align="center" >${item.zjd }</td>
	<td align="center" >${item.mdz }</td>
	<td align="center" >${item.sdz }</td>
	<td align="center" ></td>
	<td align="center" >${item.jhqf }</td>
	<td align="center" >${item.neij }</td>
	<td align="center" >${item.sleeve }</td>
	<td align="center" >${item.protect }</td>
	<td align="center" >${item.sckf }</td>
	<td align="center" >${item.sczl }</td>
</tr>
</table></div>
<div style="height: 15px;font-size:6px;">&nbsp;</div>
<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
<colgroup>
	<col width="255" /><col width="98" /><col width="160" /><col width="196" />
	<col width="196" /><col width="196" /><col width="196" />
</colgroup>
<tr>
	<td height="35px" align="center" rowspan="2" style="font-size:12px;visibility: hidden;">制&nbsp;&nbsp;&nbsp;&nbsp;品&nbsp;&nbsp;&nbsp;&nbsp;尺&nbsp;&nbsp;&nbsp;&nbsp;寸</td>
	<td align="center" rowspan="2" style="font-size:12px;visibility: hidden;">订 货 规 格</td>
	<td align="center" rowspan="2" style="font-size:12px;visibility: hidden;">订 货 附 着 量 略 称</td>
	<td align="center" colspan="4" style="font-size:12px;visibility: hidden;">尺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;寸&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;允&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;许&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;范&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;围</td>
</tr>
<tr>
	<td height="47px" align="center" style="font-size:12px;visibility: hidden;">板厚%<br/>下限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上限</td>
	<td align="center" style="font-size:12px;visibility: hidden;">板厚mm<br/>下限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上限</td>
	<td align="center" style="font-size:12px;visibility: hidden;">板宽mm<br/>下限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上限</td>
	<td align="center" style="font-size:12px;visibility: hidden;">剪断长mm<br/>下限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上限</td>
</tr>
<tr>
	<td height="32px" align="center" >${item.size }</td>
	<td align="center" >${item.dhgg }</td>
	<td align="center" >${item.dhdxl }</td>
	<td align="center" >${item.bhxx } ~ ${item.bhsx }</td>
	<td align="center" >${item.bhmxx } ~ ${item.bhmsx }</td>
	<td align="center" >${item.bkmxx } ~ ${item.bkmsx }</td>
	<td align="center" >${item.bcmxx } ~ ${item.bcmsx }</td>
</tr>
</table></div>
<div style="height: 15px;font-size:6px;">&nbsp;</div>
<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
<colgroup>
	<col width="96" /><col width="97" /><col width="98" /><col width="98" />
	<col width="97" /><col width="99" /><col width="98" /><col width="98" />
	<col width="99" /><col width="99" /><col width="218" /><col width="100" />
</colgroup>
<tr>
	<td height="32px" align="center" style="font-size:12px;visibility: hidden;">包 装 张 数</td>
	<td align="center" style="font-size:12px;visibility: hidden;">L O T 重 量</td>
	<td align="center" style="font-size:12px;visibility: hidden;">B O X 数</td>
	<td align="center" style="font-size:12px;visibility: hidden;">B / B 重 量</td>
	<td align="center" style="font-size:12px;visibility: hidden;">零 头 可 否</td>
	<td align="center" style="font-size:12px;visibility: hidden;">零 头 下 限</td>
	<td align="center" style="font-size:12px;visibility: hidden;">T i n<br/>补正系数</td>
	<td align="center" style="font-size:12px;visibility: hidden;">垫 木 方 向</td>
	<td align="center" style="font-size:12px;visibility: hidden;">垫 木 重 量</td>
	<td align="center" style="font-size:12px;visibility: hidden;">包 装 材 料<br/>重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</td>
	<td align="center" style="font-size:12px;visibility: hidden;">捆&nbsp;包&nbsp;指&nbsp;定<br/>上限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下限</td>
	<td align="center" style="font-size:12px;visibility: hidden;">捆 包 方 式</td>
</tr>
<tr>
	<td height="32px" align="center" >${item.bzzs }</td>
	<td align="center" >${item.lotZl }</td>
	<td align="center" >${item.boxs }</td>
	<td align="center" >${item.bbZl }</td>
	<td align="center" >${item.ling }</td>
	<td align="center" >${item.ltxx }</td>
	<td align="center" >${item.trim }</td>
	<td align="center" >${item.dmfx }</td>
	<td align="center" >${item.dmzl }</td>
	<td align="center" >${item.bzclZl }</td>
	<td align="center" >${item.kbxx } ~ ${item.kbsx }</td>
	<td align="center" >${item.kbxs }</td>
</tr>
</table></div>
<div style="height: 20px;font-size:6px;">&nbsp;</div>
<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
	<tr>
		<td valign="top">
		<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:14px;font-family: 'Courier';">
		<colgroup>
			<col width="35" /><col width="120" /><col width="70" /><col width="70" />
			<col width="60" /><col width="85" /><col width="75" /><col width="75" />
			<col width="75" /><col width="75" /><col width="225" />
		</colgroup>
		<tr>
			<td height="32px" align="center" style="font-size:12px;visibility: hidden;">顺序</td>
			<td align="center" style="font-size:12px;visibility: hidden;">卷板No.</td>
			<td align="center" style="font-size:12px;visibility: hidden;">重量</td>
			<td align="center" style="font-size:12px;visibility: hidden;">卷板长</td>
			<td align="center" style="font-size:12px;visibility: hidden;">w个 数</td>
			<td align="center" style="font-size:12px;visibility: hidden;">厚不良有无</td>
			<td colspan="4" align="center" style="font-size:12px;visibility: hidden;">别 纸 K P No.</td>
			<td colspan="4" align="center" style="font-size:12px;visibility: hidden;">业&nbsp;&nbsp;务&nbsp;&nbsp;联&nbsp;&nbsp;络</td>
		</tr>
		<c:forEach items="${item.jbs }" var="jb" varStatus="stat">
		<tr>
			<td height="31px" align="center"><span style="font-size:12px;visibility: hidden;">${stat.index + 1}</span></td>
			<c:if test="${jb.jbno != null }">
			<td align="center" >${jb.zzsd}-${jb.jbno }<br/>${jb.kw}</td>
			<td align="center" >${jb.zl }</td>
			<td align="center" >${jb.jbc }</td>
			<td align="center" >${jb.wgs }</td>
			<td align="center" >${jb.hbl }</td>
			<td align="center" >${jb.bzkpNo1 }</td>
			<td align="center" >${jb.bzkpNo2 }</td>
			<td align="center" >${jb.bzkpNo3 }</td>
			<td align="center" >${jb.bzkpNo4 }</td>
			<td align="center" >${jb.ywll }</td>
			</c:if>
			<c:if test="${jb == null || jb.jbno == null }">
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			</c:if>
		</tr></c:forEach>
		<tr>
			<td height="150" align="center" valign="top"></td>
			<td colspan="13" style="padding-left:10px;">
			<div>
			<span style="font-size:16px;font-family: 'Courier';">ETL和木工所备注:</span>${item.bz1 }
			</div><br/>
			<div>
			<span style="font-size:16px;font-family: 'Courier';">SL备注:</span>${item.bz2 }
			</div><br/>
			<div>
			<span style="font-size:16px;font-family: 'Courier';">ETL、木工所和SL备注:</span>${item.bz3 }
			</div>
			&nbsp;
			</td>
		</tr>
		</table></div>
		</td>
		<td width="10px" style="font-size:6px;">
		&nbsp;
		</td>
		<td valign="top">
		<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
			<colgroup>
				<col width="265" /><col width="50" />
			</colgroup>
			<tr>
				<td height="29px" align="center" style="font-size:12px;visibility: hidden;">S P E C I F I C A T I O N</td>
				<td align="center" style="font-size:12px;visibility: hidden;">货标</td>
			</tr>
			<tr>
				<td height="29px" align="center">${item.ggnm }</td>
				<td align="center">${item.huob }</td>
			</tr>
		</table></div>
		<div style="height: 8px; font-size: 6px;">&nbsp;</div>
		<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
			<colgroup>
				<col width="315" />
			</colgroup>
			<tr>
				<td height="29px" align="center" style="font-size:12px;visibility: hidden;">P a c k a g e&nbsp;&nbsp;&nbsp;No.</td>
			</tr>
			<tr>
				<td height="29px" align="center">${item.packageNo }</td>
			</tr>
		</table></div>
		<div style="height: 8px; font-size: 6px;">&nbsp;</div>
		<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
			<colgroup>
				<col width="315" />
			</colgroup>
			<tr>
				<td height="29px" align="center" style="font-size:12px;visibility: hidden;">用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;称</td>
			</tr>
			<tr>
				<td height="29px" align="center">${item.user }</td>
			</tr>
		</table></div>
		<div style="height: 8px; font-size: 6px;">&nbsp;</div>
		<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
			<colgroup>
				<col width="315" />
			</colgroup>
			<tr>
				<td height="29px" align="center" style="font-size:12px;visibility: hidden;">订&nbsp;&nbsp;&nbsp;货&nbsp;&nbsp;&nbsp;纳&nbsp;&nbsp;&nbsp;期</td>
			</tr>
			<tr>
				<td height="29px" align="center">${item.dhnq }</td>
			</tr>
		</table></div>
		<div style="height: 8px; font-size: 6px;">&nbsp;</div>
		<div style="border:0px;"><table cellpadding="0" cellspacing="0" border="1" bordercolor="#ffffff" style="border-collapse:collapse;border-spacing:0px;border-bottom:0px;border-right:0px;font-size:16px;font-family: 'Courier';">
			<colgroup>
				<col width="315" />
			</colgroup>
			<tr>
				<td height="20px" align="center" style="font-size:12px;visibility: hidden;">木&nbsp;&nbsp;&nbsp;工&nbsp;&nbsp;&nbsp;&nbsp;所&nbsp;&nbsp;&nbsp;NO.</td>
			</tr>
			<tr>
				<td height="29px" align="left" style="font-size:22px; font-weight: bold;font-family: 'Courier';">木工所业联：${item.mgsn }</td>
			</tr>
		</table></div>
		</td>
	</tr>
</table></div></div>