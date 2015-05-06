<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintView" style="font-size:10px;">
<table width="1060" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0" align="left" style="border-collapse: collapse;">
<colgroup><col width="100"><col width="100"><col width="650"><col width="70"><col width="140"></colgroup>
<tr>
<td height="20" style="text-align: left; font-size: 12px;">商品管理部</td>
<td colspan="3">&nbsp;</td>
<td style="text-align: left; font-size: 12px;">QR-MX-501(0)</td>
</tr>
<tr>
<td height="20" style="text-align : center; font-size: 12px;"><f:v value="crea" format="yyyy-MM-dd"/></td>
<td colspan="4" rowspan="2" style="text-align: center; font-size: 22px; font-weight: bold;">高耐蚀性马口铁出货判定表 (#75以上)</td>
</tr>
<tr><td height="20"></td></tr>
<tr><td height="20">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
<tr>
<td colspan="5">
<table width="100%" border="1" bordercolor="#000000" cellspacing="0" cellpadding="0" style="border-collapse: collapse; text-align: center; font-size: 13px;">
<colgroup>
<col width="3%"><col width="7%"><col width="8%"><col width="4%"><col width="5%"><col width="1%"><col width="3%"><col width="2%"><col width="6%"><col width="3%"><col width="4%"><col width="6%">
<col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%">
<col width="8%">
</colgroup>
<tr>
<th rowspan="2">编号</th>
<th rowspan="2">订货No.</th>
<th rowspan="2">用户略称</th>
<th rowspan="2">调质度</th>
<th rowspan="2" colspan="2">号数</th>
<th rowspan="2">表面</th>
<th rowspan="2" colspan="3">尺寸</th>
<th rowspan="2">用途</th>
<th rowspan="2">Coil No.</th>
<th colspan="3">锡附着量</th>
<th rowspan="2">Cr</th>
<th height="20">ISV</th>
<th>TCS</th>
<th>ATC</th>
<th>Pore</th>
<th>TCV</th>
<th>PLT</th>
<th rowspan="2">出货判定</th>
</tr>
<tr>
<th height="20">M-Sn</th>
<th>Alloy</th>
<th>T-Sn</th>
<th>&lt;=5</th>
<th>&lt;=8.5</th>
<th>&lt;=0.120</th>
<th>&lt;=13</th>
<th>&lt;=2</th>
<th>&lt;=10</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${datas }">
<c:set var="count" value="${stat.count }" scope="page" />
<tr>
<td rowspan="2" align="center">${stat.count }</td>
<td rowspan="2">${item.dhno }</td>
<td rowspan="2">${item.abbr }</td>
<td rowspan="2">${item.yuny }</td>
<td rowspan="2" colspan="2">${item.fuzm }/${item.fufm }</td>
<td rowspan="2">${item.face }</td>
<td rowspan="2" colspan="3">${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
<td rowspan="2">${item.cond }</td>
<td rowspan="2">${item.zzsd }-${item.jbno }</td>
<td height="20" ><c:choose><c:when test="${item.xfzlMt != null && item.xfzlMt > 0 }"><f:v value="item.xfzlMt" format="2" /></c:when><c:when test="${item.xfzlMt != null && item.xfzlMt == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${item.xfzlAt != null && item.xfzlAt > 0 }"><f:v value="item.xfzlAt" format="2" /></c:when><c:when test="${item.xfzlAt != null && item.xfzlAt == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${item.xfzlSt != null && item.xfzlSt > 0 }"><f:v value="item.xfzlSt" format="2" /></c:when><c:when test="${item.xfzlSt != null && item.xfzlSt == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${item.crT != null && item.crT > 0 }">${item.crT }</c:when><c:when test="${item.crT != null && item.crT == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.isv != null && item.isv > 0 }"><f:v value="item.isv" format="0" /></c:when><c:when test="${item.isv != null && item.isv == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.tcs != null && item.tcs > 0 }">${item.tcs }</c:when><c:when test="${item.tcs != null && item.tcs == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.atc != null && item.atc > 0 }">${item.atc }</c:when><c:when test="${item.atc != null && item.atc == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.pore != null && item.pore > 0 }">${item.pore }</c:when><c:when test="${item.pore != null && item.pore == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.tcv != null && item.tcv > 0 }">${item.tcv }</c:when><c:when test="${item.tcv != null && item.tcv == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.pltPd != null && item.pltPd > 0 }">${item.pltPd }</c:when><c:when test="${item.pltPd != null && item.pltPd == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2">&nbsp;</td>
</tr>
<tr>
<td height="20"><c:choose><c:when test="${item.xfzlMb != null && item.xfzlMb > 0 }"><f:v value="item.xfzlMb" format="2" /></c:when><c:when test="${item.xfzlMb != null && item.xfzlMb == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${item.xfzlAb != null && item.xfzlAb > 0 }"><f:v value="item.xfzlAb" format="2" /></c:when><c:when test="${item.xfzlAb != null && item.xfzlAb == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${item.xfzlSb != null && item.xfzlSb > 0 }"><f:v value="item.xfzlSb" format="2" /></c:when><c:when test="${item.xfzlSb != null && item.xfzlSb == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${item.crB != null && item.crB > 0 }">${item.crB }</c:when><c:when test="${item.crB != null && item.crB == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
</tr>
</c:forEach>
<c:if test="${count < pageSize + 1 }">
<c:forEach begin="${count + 1 }" var="index" end="${pageSize }">
<tr>
<td rowspan="2" align="center">${index }</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2" colspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2" colspan="3">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td height="20">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
<td rowspan="2">&nbsp;</td>
</tr>
<tr>
<td height="20">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
</c:forEach>
</c:if>
<tr>
<td height="20">No.</td><td>01</td><td>02</td><td colspan="2">03</td><td colspan="3">04</td><td>05</td><td colspan="2">06</td>
<td>07</td><td colspan="2">08</td><td colspan="2">09</td><td colspan="2">10</td><td colspan="2">11</td><td colspan="2">12</td><td>13</td>
</tr>
<tr>
<td height="60">核<br />决</td><td>&nbsp;</td><td>&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="3">&nbsp;</td><td>&nbsp;</td><td colspan="2">&nbsp;</td>
<td>&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td>&nbsp;</td>
</tr>
</table>
</td>
</tr>
</table>
</div>