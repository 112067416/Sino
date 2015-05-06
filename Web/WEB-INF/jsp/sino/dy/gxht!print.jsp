<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%
	String path = request.getContextPath();
%>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
<table width="730" align="left" cellpadding="0" cellspacing="0"
	border="0" bordercolor="#000000" style="border-collapse: collapse; text-align: left; font-size: 14px">
	<colgroup><col width="60" /><col width="180" /><col width="290" /><col width="200" /></colgroup>
	<tr><td rowspan="4" align="right"><img alt="logo" src="<%=path%>/images/main/sino-japan.png" border="0" height="50px" width="60px" ></td><td height="10" style="text-align: left; font-size: 8px;">福建中日达金属有限公司</td><td rowspan="4" colspan="2" align="left" style="font-size: 23px; font-weight: bold;">工 矿 产 品 购 销 合 同</td></tr>
	<tr><td height="10" style="text-align: left; font-size: 8px;">福建省福州市马尾经济技术开发区罗星中路16号</td></tr>
	<tr><td height="10" style="text-align: left; font-size: 8px;">电话：0591-83688878 0591-83983068</td></tr>
	<tr><td height="10" style="text-align: left; font-size: 8px;">传真：0591-83985520</td></tr>
	<tr><td colspan="4" height="10">&nbsp;</td></tr>
	<tr><td colspan="2" height="25">供 方：福建中日达金属有限公司</td><td>&nbsp;</td><td>合同编号：${entity.dhno }</td></tr>
	<tr><td colspan="2" height="25">需 方：${entity.name }</td><td>&nbsp;</td><td>签订地点：福州市马尾区</td></tr>
	<tr><td colspan="4" height="25">&nbsp;</td></tr>
	<tr><td height="25" colspan="4">一.&nbsp;&nbsp;购销货物名称、规格、数（重）量、价格等</td></tr>
	<tr>
	<td height="25" colspan="4">
	<table width="100%" border="1" bordercolor="#000000" cellspacing="0" cellpadding="0" style="border-collapse: collapse; text-align: center; font-size: 14px;">
	<colgroup>
	<col width="3%" /><col width="15%" /><col width="7%" /><col width="11%" /><col width="3%" />
	<col width="9%" /><col width="14%" /><col width="7%" /><col width="7%" /><col width="10%" /><col width="14%" />
	</colgroup>
	<tr>
	<td height="25">NO.</td><td>出货期</td><td>品种</td><td>规格(钢种.<br />退火条件)</td><td>表面</td><td>镀锡量</td><td>尺寸</td><td>单件<br />(张数)</td><td>数量<br />(MT)</td><td>单价<br />(RMB)</td><td>金额<br />(RMB)</td>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${list }">
	<c:set var="count" value="${stat.count }" scope="page" />
	<tr>
	<td height="25">${item.line }</td><td><f:v value="item.jhqi" format="yyyy年MM月"/></td>
	<td>马口铁</td><td>${item.ggnm }</td><td>${item.face }</td><td>${item.fuzm }/${item.fufm }</td>
	<td style="text-align: left; padding-right: 15px;">${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
	<td><c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.kbsz }</c:when><c:otherwise>ø${item.neij }</c:otherwise></c:choose></td>
	<td style="text-align: right; padding-right: 10px;">${item.htzl }</td>
	<td style="text-align: right; padding-right: 15px;"><f:v value="item.htdj" format="#,##0.00"/></td>
	<td style="text-align: right; padding-right: 10px;"><f:v value="item.htje" format="#,##0.00"/></td>
	</tr>
	</c:forEach>
	<c:if test="${count < pageSize + 1 }">
	<c:forEach begin="${count + 1 }" var="index" end="${pageSize }">
	<tr>
	<td height="25">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
	</tr>
	</c:forEach>
	</c:if>
	<tr>
	<td height="25" colspan="2" align="left">总      计：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
	<td style="text-align: right; padding-right: 10px;">${hjzl }</td>
	<td>&nbsp;</td>
	<td style="text-align: right; padding-right: 10px;"><f:v value="hjje" format="#,##0.00"/></td>
	</tr>
	<tr><td height="25" colspan="11" align="left">总计人民币金额：(大 写)&nbsp;&nbsp;&nbsp;&nbsp;${cHjje }</td></tr>
	</table>
	</td>
	</tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >二.&nbsp;&nbsp;货物品名：&nbsp;&nbsp;${gxhtTp.hwpm }。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >三.&nbsp;&nbsp;货物质量技术标准：销售方提供的货物质量应符合JIS G3303标准所规定的数据和性能要求；于签<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;署本合同时，双方已知晓并确认该JIS G3303标准对马口铁货物所规定的数据和性能；</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >四.&nbsp;&nbsp;交货地点及运输方式：${gxhtTp.jhfs }。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >五.&nbsp;&nbsp;运输方式及运输费用负担：${gxhtTp.ysfs }。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >六.&nbsp;&nbsp;包装标准及包装物的供应和回收：${gxhtTp.bzfs }。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >七.&nbsp;&nbsp;货物验收及质量异议：按JIS G3303标准验收，对货物质量提出异议的期限为收到货物后30天内；<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如对货物质量是否符合技术标准有异议的，最终以JIS G3303标准制定方出具的书面意见为判断依据。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >八.&nbsp;&nbsp;货款支付期限及方式：${gxhtTp.jsfs }。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >九.&nbsp;&nbsp;违约责任：&nbsp;&nbsp;合同签订后，任何一方有违约行为都应按《中华人民共和国合同法》的有关规定处理。</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >十.&nbsp;&nbsp;争议处理：如有争议，双方应友好协商解决；若协商不成，可在原告所在地人民法院提起诉讼</td></tr>
	<tr><td height="25" colspan="4" style="font-size: 14px;" >十一其它约定事项：${gxhtTp.ydsx }</td></tr>
	<tr>
	<td colspan="4">
	<table width="100%" cellpadding="0" cellspacing="0"
	border="1" bordercolor="#000000" style="border-collapse: collapse; text-align: left; padding-left: 10; font-size: 14px;">
	<colgroup><col width="53%" /><col width="47%" /></colgroup>
	<tr>
	<td height="27" align="center" style="border-bottom: 0px;">供&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方</td>
	<td align="center" style="border-bottom: 0px;">需&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">单位名称（章）</td>
	<td style="border-bottom: 0px; border-top: 0px;">单位名称（章）</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">单位地址：福建省福州市马尾区罗星中路16号</td>
	<td style="border-bottom: 0px; border-top: 0px;">单位地址：<span style="font-size: 14px;">${entity.addr }</span></td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">法定代表人：林祺爕</td>
	<td style="border-bottom: 0px; border-top: 0px;">法定代表人：</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">委托代理人：</td>
	<td style="border-bottom: 0px; border-top: 0px;">委托代理人：</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">&nbsp;</td>
	<td style="border-bottom: 0px; border-top: 0px;">&nbsp;</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">签约时间：<f:v value="gxhtTp.qrsj" format="yyyy"/> 年 <f:v value="gxhtTp.qrsj" format="MM"/> 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</td>
	<td style="border-bottom: 0px; border-top: 0px;">签约时间：<f:v value="gxhtTp.qrsj" format="yyyy"/> 年 &nbsp;&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;&nbsp;日</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">开户行：${gxhtTp.khh }</td>
	<td style="border-bottom: 0px; border-top: 0px;">开户银行：</td>
	</tr>
	<tr>
	<td height="23" style="border-bottom: 0px; border-top: 0px;">帐 号：${gxhtTp.zh }</td>
	<td style="border-bottom: 0px; border-top: 0px;">帐 号：</td>
	</tr>
	<tr>
	<td height="23" style="border-top: 0px;">邮政编码：350015</td>
	<td style="border-top: 0px;">邮政编码：</td>
	</tr>
	</table>
	</td>
	</tr>
</table>
</div>
