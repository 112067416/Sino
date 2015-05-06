<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintView" style="font-size:10px;">
<div style="font-size: 20px; font-weight: bold; color: #000000; vertical-align: top;" align="center">客户管理表</div>
<table width="1060" border="0" bordercolor="#000000" style="border-collapse: collapse;" cellpadding="0" cellspacing="0" align="left">
<colgroup><col width="400"><col width="660"></colgroup>
<tr>
<td valign="top">
<table width="100%" border="0" bordercolor="#000000" style="border-collapse: collapse; font-size: 12px; text-align: left;">
<colgroup><col width="30%" /><col width="70%" /></colgroup>
<tr><td>制表日期：</td><td><f:v value="crea" format="yyyy-MM-dd" /></td></tr>
<tr><td>客户名称：</td><td>${entity.rsv1 }</td></tr>
<tr><td>SJM业务负责人：</td><td>${entity.ddnm }</td></tr>
<tr><td>资金组成：</td><td>${entity.fond }</td></tr>
<tr><td>公司地址：</td><td>${entity.addr }</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td>名字/职务：</td><td>${entity.link1 }&nbsp;&nbsp;${entity.job1 }&nbsp;&nbsp;&nbsp;&nbsp;(手机号：${entity.mobile1 })</td></tr>
<tr><td>TEL1：</td><td>${entity.tel1 }</td></tr>
<tr><td>FAX1：</td><td>${entity.fax1 }</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td>采购员/职务1：</td><td>${entity.link2 }&nbsp;&nbsp;${entity.job2 }&nbsp;&nbsp;&nbsp;&nbsp;(手机号：${entity.mobile2 })</td></tr>
<tr><td>采购员/职务2：</td><td>${entity.link3 }&nbsp;&nbsp;${entity.job3 }&nbsp;&nbsp;&nbsp;&nbsp;(手机号：${entity.mobile3 })</td></tr>
<tr><td>TEL1：</td><td>${entity.tel2 }</td></tr>
<tr><td>TEL2：</td><td>${entity.tel3 }</td></tr>
<tr><td>FAX1：</td><td>${entity.fax2 }</td></tr>
<tr><td>FAX2：</td><td>${entity.fax3 }</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td>成立日期：</td><td>${entity.fdt }</td></tr>
<tr><td>从业人员：</td><td>${entity.staffs }</td></tr>
<tr><td colspan="2">设备概要：</td></tr>
<tr><td colspan="2"><table width="80%" border="1" bordercolor="#000000" style="border-collapse: collapse; text-align: left;"><tr><td height="90" valign="top"><pre>${entity.sbjy }</pre></td></tr></table></td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td>商谈形式：</td><td>${entity.chat }</td></tr>
<tr><td>经销商：</td><td>${entity.dealer }</td></tr>
<tr><td>付款方式：</td><td>${entity.payment }</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td>成交记录：</td><td rowspan="2" valign="top"><c:choose><c:when test="${khchtjVO.chjl != null }">${khchtjVO.chjl }</c:when><c:otherwise>&nbsp;</c:otherwise> </c:choose></td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</td>
<td valign="top"> 
<table width="100%" border="0" bordercolor="#000000" style="border-collapse: collapse; font-size: 12px;">
<tr>
<td>客户尺寸/用途/使用量</td>
</tr>
<tr>
<td>
<table width="100%" border="1" bordercolor="#000000" style="border-collapse: collapse; font-size: 12px; text-align: center;">
<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="20%" /></colgroup>
<tr>
<th rowspan="2">年度</th><th rowspan="2">主要尺寸</th><th rowspan="2">用途</th><th colspan="4">${title }</th><th rowspan="2">备注</th>
</tr>
<tr><th>1Q</th><th>2Q</th><th>3Q</th><th>4Q</th></tr>
<c:choose>
<c:when test="${khchtjVO.size > 0 }">
<c:forEach varStatus="stat" var="item" items="${khchtjVO.sizeVOs }">
<tr><td height="13">${year }年</td><td>${item.yuny }&nbsp;&nbsp;&nbsp;${item.face }&nbsp;&nbsp;${item.houu }*${item.kuan }</td><td>${item.cdnm }</td><c:if test="${stat.count == 1 }"><td rowspan="${khchtjVO.size }" valign="top">${entity.oxsl }</td><td rowspan="${khchtjVO.size }" valign="top">${entity.sxsl }</td><td rowspan="${khchtjVO.size }" valign="top">${entity.txsl }</td><td rowspan="${khchtjVO.size }" valign="top">${entity.fxsl }</td><td rowspan="${khchtjVO.size }" valign="top">${entity.supplier }</td></c:if></tr>
</c:forEach>
</c:when>
<c:otherwise>
<tr><td height="30">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td rowspan="4" valign="top">${entity.oxsl }</td><td rowspan="4" valign="top">${entity.sxsl }</td><td rowspan="4" valign="top">${entity.txsl }</td><td rowspan="4" valign="top">${entity.fxsl }</td><td rowspan="4" valign="top">${entity.supplier }</td></tr>
<tr><td height="30">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
<tr><td height="30">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
<tr><td height="30">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
</c:otherwise>
</c:choose>
</table>
</td>
</tr>
<tr>
<td height="20">&nbsp;</td>
</tr>
<tr>
<td>历史及最近消息</td>
</tr>
<tr>
<td height="200" valign="top"><pre>${entity.news }</pre></td>
</tr>
</table>
</td>
</tr>
</table>
</div>