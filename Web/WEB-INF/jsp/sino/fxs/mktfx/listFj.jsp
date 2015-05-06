<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="10%" /></colgroup>
<tr>
<th rowspan="2">时间</th>
<th rowspan="2">Coil No.</th>
<th rowspan="2">目标锡</th>
<th colspan="3">锡附着量分析结果</th>
<th rowspan="2">目标油</th>
<th colspan="3">涂油量</th>
<th rowspan="2">Cr量</th>
</tr>
<tr>
<th>S</th>
<th>C</th>
<th>N</th>
<th>S</th>
<th>C</th>
<th>N</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${vos }">
<tr>
<td rowspan="2"><f:v value="item.fxr" format="HH:mm" /> </td>
<td rowspan="2">${item.jbno }</td>
<td rowspan="2">${item.fuzm }/${item.fufm }</td>
<td>F <c:choose><c:when test="${mktfxVO.fuzx != null && mktfxVO.fuzs != null && item.xfzlSmt != null && item.xfzlSat != null && (mktfxVO.fuzx > (item.xfzlSmt + item.xfzlSat) || mktfxVO.fuzs < (item.xfzlSmt + item.xfzlSat)) }"><span style="color:#F94545;">${item.xfzlSmt }/${item.xfzlSat }</span></c:when><c:otherwise><c:if test="${item.xfzlSmt >= 0 }">${item.xfzlSmt }/</c:if><c:if test="${item.xfzlSat >= 0 }"><f:jt min="${mktfxVO.hjin }" value="${item.xfzlSat }" /></c:if></c:otherwise></c:choose></td>
<td>  <c:choose><c:when test="${mktfxVO.fuzx != null && mktfxVO.fuzs != null && item.xfzlCmt != null && item.xfzlCat != null && (mktfxVO.fuzx > (item.xfzlCmt + item.xfzlCat) || mktfxVO.fuzs < (item.xfzlCmt + item.xfzlCat)) }"><span style="color:#F94545;">${item.xfzlCmt }/${item.xfzlCat }</span></c:when><c:otherwise><c:if test="${item.xfzlCmt >= 0 }">${item.xfzlCmt }/</c:if><c:if test="${item.xfzlCat >= 0 }"><f:jt min="${mktfxVO.hjin }" value="${item.xfzlCat }" /></c:if></c:otherwise></c:choose></td>
<td>  <c:choose><c:when test="${mktfxVO.fuzx != null && mktfxVO.fuzs != null && item.xfzlNmt != null && item.xfzlNat != null && (mktfxVO.fuzx > (item.xfzlNmt + item.xfzlNat) || mktfxVO.fuzs < (item.xfzlNmt + item.xfzlNat)) }"><span style="color:#F94545;">${item.xfzlNmt }/${item.xfzlNat }</span></c:when><c:otherwise><c:if test="${item.xfzlNmt >= 0 }">${item.xfzlNmt }/</c:if><c:if test="${item.xfzlNat >= 0 }"><f:jt min="${mktfxVO.hjin }" value="${item.xfzlNat }" /></c:if></c:otherwise></c:choose></td>
<td rowspan="2">${mktfxVO.ytyp }${mktfxVO.yqty }</td>
<td rowspan="2"><c:if test="${item.tylSt >= 0 }"><f:jt min="${mktfxVO.tyx }" max="${mktfxVO.tys }" value="${item.tylSt }" />/</c:if><c:if test="${item.tylSb >= 0 }"><f:jt min="${mktfxVO.tyx }" max="${mktfxVO.tys }" value="${item.tylSb }" /></c:if></td>
<td rowspan="2"><c:if test="${item.tylCt >= 0 }"><f:jt min="${mktfxVO.tyx }" max="${mktfxVO.tys }" value="${item.tylCt }" />/</c:if><c:if test="${item.tylCb >= 0 }"><f:jt min="${mktfxVO.tyx }" max="${mktfxVO.tys }" value="${item.tylCb }" /></c:if></td>
<td rowspan="2"><c:if test="${item.tylNt >= 0 }"><f:jt min="${mktfxVO.tyx }" max="${mktfxVO.tys }" value="${item.tylNt }" />/</c:if><c:if test="${item.tylNb >= 0 }"><f:jt min="${mktfxVO.tyx }" max="${mktfxVO.tys }" value="${item.tylNb }" /></c:if></td>
<td rowspan="2"><c:if test="${item.crCt >= 0 }"><f:jt min="${mktfxVO.crx }" max="${mktfxVO.crs }" value="${item.crCt }" />/</c:if><c:if test="${item.crCb >= 0 }"><f:jt min="${mktfxVO.crx }" max="${mktfxVO.crs }" value="${item.crCb }" /></c:if></td>
</tr>
<tr>
<td>B <c:choose><c:when test="${mktfxVO.fufx != null && mktfxVO.fufs != null && item.xfzlSmb != null && item.xfzlSab != null && (mktfxVO.fufx > (item.xfzlSmb + item.xfzlSab) || mktfxVO.fufs < (item.xfzlSmb + item.xfzlSab)) }"><span style="color:#F94545;">${item.xfzlSmb }/${item.xfzlSab }</span></c:when><c:otherwise><c:if test="${item.xfzlSmb >= 0 }">${item.xfzlSmb }/</c:if><c:if test="${item.xfzlSab >= 0 }"><f:jt min="${mktfxVO.hjin }" value="${item.xfzlSab }" /></c:if></c:otherwise></c:choose></td>
<td>  <c:choose><c:when test="${mktfxVO.fufx != null && mktfxVO.fufs != null && item.xfzlCmb != null && item.xfzlCab != null && (mktfxVO.fufx > (item.xfzlCmb + item.xfzlCab) || mktfxVO.fufs < (item.xfzlCmb + item.xfzlCab)) }"><span style="color:#F94545;">${item.xfzlCmb }/${item.xfzlCab }</span></c:when><c:otherwise><c:if test="${item.xfzlCmb >= 0 }">${item.xfzlCmb }/</c:if><c:if test="${item.xfzlCab >= 0 }"><f:jt min="${mktfxVO.hjin }" value="${item.xfzlCab }" /></c:if></c:otherwise></c:choose></td>
<td>  <c:choose><c:when test="${mktfxVO.fufx != null && mktfxVO.fufs != null && item.xfzlNmb != null && item.xfzlNab != null && (mktfxVO.fufx > (item.xfzlNmb + item.xfzlNab) || mktfxVO.fufs < (item.xfzlNmb + item.xfzlNab)) }"><span style="color:#F94545;">${item.xfzlNmb }/${item.xfzlNab }</span></c:when><c:otherwise><c:if test="${item.xfzlNmb >= 0 }">${item.xfzlNmb }/</c:if><c:if test="${item.xfzlNab >= 0 }"><f:jt min="${mktfxVO.hjin }" value="${item.xfzlNab }" /></c:if></c:otherwise></c:choose></td>
</tr>
</c:forEach>
</table>