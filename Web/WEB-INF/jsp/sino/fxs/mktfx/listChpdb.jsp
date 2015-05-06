<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
<colgroup>
<col width="3%"><col width="7%"><col width="8%"><col width="4%"><col width="3%"><col width="6%"><col width="11%"><col width="4%"><col width="6%">
<col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%"><col width="4%">
<col width="8%">
</colgroup>
<tr>
<th rowspan="2"><input type="checkbox" onclick="checkAll(this);" /></th>
<th rowspan="2">订货No.</th>
<th rowspan="2">用户略称</th>
<th rowspan="2">运用<br />规格</th>
<th rowspan="2">表面</th>
<th rowspan="2">目标锡<br />附着量</th>
<th rowspan="2">尺寸</th>
<th rowspan="2">用途</th>
<th rowspan="2">Coil No.</th>
<th colspan="3">锡附着量</th>
<th rowspan="2">Cr</th>
<th>ISV</th>
<th>TCS</th>
<th>ATC</th>
<th>Pore</th>
<th>TCV</th>
<th>PLT</th>
<th rowspan="2">创建日期</th>
</tr>
<tr>
<th>M-Sn</th>
<th>Alloy</th>
<th>T-Sn</th>
<th>&lt;=5</th>
<th>&lt;=8.5</th>
<th>&lt;=0.120</th>
<th>&lt;=13</th>
<th>&lt;=2</th>
<th>&lt;=10</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr xu.id="${item.jbno }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
<td rowspan="2" align="center"><input type="checkbox" name="id" value="${item.jbno }" onclick="doCheck(this);" /></td>
<td rowspan="2">${item.dhno }</td>
<td rowspan="2">${item.abbr }</td>
<td rowspan="2">${item.yuny }</td>
<td rowspan="2">${item.face }</td>
<td rowspan="2">${item.fuzm }/${item.fufm }</td>
<td rowspan="2">${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
<td rowspan="2">${item.cond }</td>
<td rowspan="2">${item.zzsd }-${item.jbno }</td>
<td><f:v value="item.xfzlMt" format="2" /></td>
<td><f:v value="item.xfzlAt" format="2" /></td>
<td><f:v value="item.xfzlSt" format="2" /></td>
<td>${item.crT }</td>
<td rowspan="2"><c:choose><c:when test="${item.isv != null && item.isv > 0 }"><f:v value="item.isv" format="0" /></c:when><c:when test="${item.isv != null && item.isv == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.tcs != null && item.tcs > 0 }">${item.tcs }</c:when><c:when test="${item.tcs != null && item.tcs == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.atc != null && item.atc > 0 }">${item.atc }</c:when><c:when test="${item.atc != null && item.atc == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.pore != null && item.pore > 0 }">${item.pore }</c:when><c:when test="${item.pore != null && item.pore == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.tcv != null && item.tcv > 0 }">${item.tcv }</c:when><c:when test="${item.tcv != null && item.tcv == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><c:choose><c:when test="${item.pltPd != null && item.pltPd > 0 }">${item.pltPd }</c:when><c:when test="${item.pltPd != null && item.pltPd == 0 }">0</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
<td rowspan="2"><f:v value="item.cjsj" format="yyyy-MM-dd"/></td>
</tr>
<tr>
<td><f:v value="item.xfzlMb" format="2" /></td>
<td><f:v value="item.xfzlAb" format="2" /></td>
<td><f:v value="item.xfzlSb" format="2" /></td>
<td>${item.crB }</td>
</tr>
</c:forEach>
</table>
<f:paged callback="setChecked" />