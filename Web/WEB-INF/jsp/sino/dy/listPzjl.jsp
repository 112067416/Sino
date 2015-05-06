<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/><col width="3%"/><col width="4%"/><col width="4%"/><col width="7%"/>
		<col width="5%"/><col width="5%"/><col width="8%"/><col width="5%"/><col width="5%"/>
		<col width="5%"/><col width="5%"/><col width="4%"/><col width="4%"/><col width="4%"/>
		<col width="6%"/><col width="5%"/><col width="13%"/><col width="4%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th rowspan="2">时间</th>
		<th rowspan="2">2Pass</th>
		<th rowspan="2">班别</th>
		<th rowspan="2">组别</th>
		<th rowspan="2">Coil No.</th>
		<th rowspan="2">锡附着量<br />(指示书)</th>
		<th rowspan="2">锡附着量<br />Chart</th>
		<th colspan="3">锡附着量分析结果</th>
		<th colspan="2">Oiler</th>
		<th colspan="3">涂油量</th>
		<th colspan="2">Chemical</th>
		<th rowspan="2">特记</th>
		<th rowspan="2">涂油机<br />目视</th>
	</tr>
	<tr>
		<th>S</th>
		<th>C</th>
		<th>N</th>
		<th>目标</th>
		<th>比率</th>
		<th>S</th>
		<th>C</th>
		<th>N</th>
		<th>电流密度</th>
		<th>Cr量</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr>
			<td rowspan="2"><span title="<f:v value="item.scsj" format="yyyy-MM-dd HH:mm" />"><f:v value="item.scsj" format="HH:mm" /></span></td>
			<td rowspan="2">${item.pass }</td>
			<td rowspan="2"><sys:codeLabel key="item.ban" code="#SINO_BAN" show="1" /></td>
			<td rowspan="2"><sys:codeLabel key="item.zu" code="#SINO_ZU" show="1" /></td>
			<td rowspan="2"><span <c:if test="${item.jycs > 1 }">style="color:#00CC00;"</c:if>>${item.zzsd }-${item.jbno }</span></td>
			<td rowspan="2">${item.fugm }</td>
			<td rowspan="2"><c:if test="${item.sczm != null }">${item.sczm }/</c:if>${item.scfm }</td>
			<td>F <c:choose><c:when test="${item.fuzx != null && item.fuzs != null && item.xfzlSmt != null && item.xfzlSat != null && (item.fuzx > (item.xfzlSmt + item.xfzlSat) || item.fuzs < (item.xfzlSmt + item.xfzlSat)) }"><span style="color:#F94545;">${item.xfzlSmt }/${item.xfzlSat }</span></c:when><c:otherwise><c:if test="${item.xfzlSmt >= 0 }">${item.xfzlSmt }/</c:if><c:if test="${item.xfzlSat >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlSat }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${item.fuzx != null && item.fuzs != null && item.xfzlCmt != null && item.xfzlCat != null && (item.fuzx > (item.xfzlCmt + item.xfzlCat) || item.fuzs < (item.xfzlCmt + item.xfzlCat)) }"><span style="color:#F94545;">${item.xfzlCmt }/${item.xfzlCat }</span></c:when><c:otherwise><c:if test="${item.xfzlCmt >= 0 }">${item.xfzlCmt }/</c:if><c:if test="${item.xfzlCat >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlCat }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${item.fuzx != null && item.fuzs != null && item.xfzlNmt != null && item.xfzlNat != null && (item.fuzx > (item.xfzlNmt + item.xfzlNat) || item.fuzs < (item.xfzlNmt + item.xfzlNat)) }"><span style="color:#F94545;">${item.xfzlNmt }/${item.xfzlNat }</span></c:when><c:otherwise><c:if test="${item.xfzlNmt >= 0 }">${item.xfzlNmt }/</c:if><c:if test="${item.xfzlNat >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlNat }" /></c:if></c:otherwise></c:choose></td>
			<td rowspan="2">${item.ytyp }${item.yqty }</td>
			<td rowspan="2">${item.oilerBl }</td>
			<td rowspan="2"><c:if test="${item.tylSt >= 0 }"><f:jt min="${item.tyx }" max="${item.tys }" value="${item.tylSt }" />/</c:if><c:if test="${item.tylSb >= 0 }"><f:jt min="${item.tyx }" max="${item.tys }" value="${item.tylSb }" /></c:if></td>
			<td rowspan="2"><c:if test="${item.tylCt >= 0 }"><f:jt min="${item.tyx }" max="${item.tys }" value="${item.tylCt }" />/</c:if><c:if test="${item.tylCb >= 0 }"><f:jt min="${item.tyx }" max="${item.tys }" value="${item.tylCb }" /></c:if></td>
			<td rowspan="2"><c:if test="${item.tylNt >= 0 }"><f:jt min="${item.tyx }" max="${item.tys }" value="${item.tylNt }" />/</c:if><c:if test="${item.tylNb >= 0 }"><f:jt min="${item.tyx }" max="${item.tys }" value="${item.tylNb }" /></c:if></td>
			<td rowspan="2"><c:if test="${item.cheDlmdt !=null }">${item.cheDlmdt }/</c:if>${item.cheDlmdb }</td>
			<td rowspan="2"><c:if test="${item.crCt >= 0 }"><f:jt min="${item.crx }" max="${item.crs }" value="${item.crCt }" />/</c:if><c:if test="${item.crCb >= 0 }"><f:jt min="${item.crx }" max="${item.crs }" value="${item.crCb }" /></c:if></td>
			<td rowspan="2"><f:ot text="item.tj" len="8" /></td>
			<td rowspan="2">${item.tyjms }</td>
		</tr>
		<tr>
			<td>B <c:choose><c:when test="${item.fufx != null && item.fufs != null && item.xfzlSmb != null && item.xfzlSab != null && (item.fufx > (item.xfzlSmb + item.xfzlSab) || item.fufs < (item.xfzlSmb + item.xfzlSab)) }"><span style="color:#F94545;">${item.xfzlSmb }/${item.xfzlSab }</span></c:when><c:otherwise><c:if test="${item.xfzlSmb >= 0 }">${item.xfzlSmb }/</c:if><c:if test="${item.xfzlSab >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlSab }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${item.fufx != null && item.fufs != null && item.xfzlCmb != null && item.xfzlCab != null && (item.fufx > (item.xfzlCmb + item.xfzlCab) || item.fufs < (item.xfzlCmb + item.xfzlCab)) }"><span style="color:#F94545;">${item.xfzlCmb }/${item.xfzlCab }</span></c:when><c:otherwise><c:if test="${item.xfzlCmb >= 0 }">${item.xfzlCmb }/</c:if><c:if test="${item.xfzlCab >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlCab }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${item.fufx != null && item.fufs != null && item.xfzlNmb != null && item.xfzlNab != null && (item.fufx > (item.xfzlNmb + item.xfzlNab) || item.fufs < (item.xfzlNmb + item.xfzlNab)) }"><span style="color:#F94545;">${item.xfzlNmb }/${item.xfzlNab }</span></c:when><c:otherwise><c:if test="${item.xfzlNmb >= 0 }">${item.xfzlNmb }/</c:if><c:if test="${item.xfzlNab >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlNab }" /></c:if></c:otherwise></c:choose></td>
		</tr>
	</c:forEach>
</table>
<f:paged />