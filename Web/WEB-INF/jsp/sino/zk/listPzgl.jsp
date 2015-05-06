<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/><col width="4%"/><col width="3%"/><col width="3%"/><col width="5%"/>
		<col width="5%"/><col width="5%"/><col width="10%"/><col width="5%"/><col width="5%"/>
		<col width="5%"/><col width="5%"/><col width="4%"/><col width="4%"/><col width="4%"/>
		<col width="6%"/><col width="5%"/><col width="14%"/><col width="4%"/>
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
	<c:if test="${etlpzGl != null }">
		<tr xu.id="${etlpzGl.id }" key="${etlpzGl.jbno }" <c:choose><c:when test="${etlpzGl.newed }">style="background-color: #AFAEA4;"</c:when><c:otherwise></c:otherwise></c:choose> ondblclick="toModify(this)" xu.menu="menu" title="">
			<td rowspan="2"><span title="<f:v value="etlpzGl.scsj" format="yyyy-MM-dd HH:mm" />"><f:v value="etlpzGl.scsj" format="HH:mm" /></span> </td>
			<td rowspan="2">${etlpzGl.pass }</td>
			<td rowspan="2"><sys:codeLabel key="etlpzGl.ban" code="#SINO_BAN" show="1" /></td>
			<td rowspan="2"><sys:codeLabel key="etlpzGl.zu" code="#SINO_ZU" show="1" /></td>
			<td rowspan="2">${etlpzGl.zzsd }-${etlpzGl.jbno }</td>
			<td rowspan="2">${etlpzGl.fugm }</td>
			<td rowspan="2"><c:if test="${etlpzGl.sczm != null }">${etlpzGl.sczm }/</c:if>${etlpzGl.scfm }</td>
			<td>F <c:choose><c:when test="${etlpzGl.fuzx != null && etlpzGl.fuzs != null && etlpzGl.xfzlSmt != null && etlpzGl.xfzlSat != null && (etlpzGl.fuzx > (etlpzGl.xfzlSmt + etlpzGl.xfzlSat) || etlpzGl.fuzs < (etlpzGl.xfzlSmt + etlpzGl.xfzlSat)) }"><span style="color:#F94545;">${etlpzGl.xfzlSmt }/${etlpzGl.xfzlSat }</span></c:when><c:otherwise><c:if test="${etlpzGl.xfzlSmt >= 0 }">${etlpzGl.xfzlSmt }/</c:if><c:if test="${etlpzGl.xfzlSat >= 0 }"><f:jt min="${etlpzGl.hjin }" value="${etlpzGl.xfzlSat }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${etlpzGl.fuzx != null && etlpzGl.fuzs != null && etlpzGl.xfzlCmt != null && etlpzGl.xfzlCat != null && (etlpzGl.fuzx > (etlpzGl.xfzlCmt + etlpzGl.xfzlCat) || etlpzGl.fuzs < (etlpzGl.xfzlCmt + etlpzGl.xfzlCat)) }"><span style="color:#F94545;">${etlpzGl.xfzlCmt }/${etlpzGl.xfzlCat }</span></c:when><c:otherwise><c:if test="${etlpzGl.xfzlCmt >= 0 }">${etlpzGl.xfzlCmt }/</c:if><c:if test="${etlpzGl.xfzlCat >= 0 }"><f:jt min="${etlpzGl.hjin }" value="${etlpzGl.xfzlCat }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${etlpzGl.fuzx != null && etlpzGl.fuzs != null && etlpzGl.xfzlNmt != null && etlpzGl.xfzlNat != null && (etlpzGl.fuzx > (etlpzGl.xfzlNmt + etlpzGl.xfzlNat) || etlpzGl.fuzs < (etlpzGl.xfzlNmt + etlpzGl.xfzlNat)) }"><span style="color:#F94545;">${etlpzGl.xfzlNmt }/${etlpzGl.xfzlNat }</span></c:when><c:otherwise><c:if test="${etlpzGl.xfzlNmt >= 0 }">${etlpzGl.xfzlNmt }/</c:if><c:if test="${etlpzGl.xfzlNat >= 0 }"><f:jt min="${etlpzGl.hjin }" value="${etlpzGl.xfzlNat }" /></c:if></c:otherwise></c:choose></td>
			<td rowspan="2">${etlpzGl.ytyp }${etlpzGl.yqty }</td>
			<td rowspan="2">${etlpzGl.oilerBl }</td>
			<td rowspan="2"><c:if test="${etlpzGl.tylSt >= 0 }"><f:jt min="${etlpzGl.tyx }" max="${etlpzGl.tys }" value="${etlpzGl.tylSt }" />/</c:if><c:if test="${etlpzGl.tylSb >= 0 }"><f:jt min="${etlpzGl.tyx }" max="${etlpzGl.tys }" value="${etlpzGl.tylSb }" /></c:if></td>
			<td rowspan="2"><c:if test="${etlpzGl.tylCt >= 0 }"><f:jt min="${etlpzGl.tyx }" max="${etlpzGl.tys }" value="${etlpzGl.tylCt }" />/</c:if><c:if test="${etlpzGl.tylCb >= 0 }"><f:jt min="${etlpzGl.tyx }" max="${etlpzGl.tys }" value="${etlpzGl.tylCb }" /></c:if></td>
			<td rowspan="2"><c:if test="${etlpzGl.tylNt >= 0 }"><f:jt min="${etlpzGl.tyx }" max="${etlpzGl.tys }" value="${etlpzGl.tylNt }" />/</c:if><c:if test="${etlpzGl.tylNb >= 0 }"><f:jt min="${etlpzGl.tyx }" max="${etlpzGl.tys }" value="${etlpzGl.tylNb }" /></c:if></td>
			<td rowspan="2"><c:if test="${etlpzGl.cheDlmdt !=null }">${etlpzGl.cheDlmdt }/</c:if>${etlpzGl.cheDlmdb }</td>
			<td rowspan="2"><c:if test="${etlpzGl.crCt >= 0 }"><f:jt min="${etlpzGl.crx }" max="${etlpzGl.crs }" value="${etlpzGl.crCt }" />/</c:if><c:if test="${etlpzGl.crCb >= 0 }"><f:jt min="${etlpzGl.crx }" max="${etlpzGl.crs }" value="${etlpzGl.crCb }" /></c:if></td>
			<td rowspan="2"><f:ot text="etlpzGl.tj" len="14" /></td>
			<td rowspan="2">${etlpzGl.tyjms }</td>
		</tr>
		<tr <c:choose><c:when test="${etlpzGl.newed }">style="background-color: #AFAEA4;"</c:when><c:otherwise></c:otherwise></c:choose>>
			<td>B <c:choose><c:when test="${etlpzGl.fufx != null && etlpzGl.fufs != null && etlpzGl.xfzlSmb != null && etlpzGl.xfzlSab != null && (etlpzGl.fufx > (etlpzGl.xfzlSmb + etlpzGl.xfzlSab) || etlpzGl.fufs < (etlpzGl.xfzlSmb + etlpzGl.xfzlSab)) }"><span style="color:#F94545;">${etlpzGl.xfzlSmb }/${etlpzGl.xfzlSab }</span></c:when><c:otherwise><c:if test="${etlpzGl.xfzlSmb >= 0 }">${etlpzGl.xfzlSmb }/</c:if><c:if test="${etlpzGl.xfzlSab >= 0 }"><f:jt min="${etlpzGl.hjin }" value="${etlpzGl.xfzlSab }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${etlpzGl.fufx != null && etlpzGl.fufs != null && etlpzGl.xfzlCmb != null && etlpzGl.xfzlCab != null && (etlpzGl.fufx > (etlpzGl.xfzlCmb + etlpzGl.xfzlCab) || etlpzGl.fufs < (etlpzGl.xfzlCmb + etlpzGl.xfzlCab)) }"><span style="color:#F94545;">${etlpzGl.xfzlCmb }/${etlpzGl.xfzlCab }</span></c:when><c:otherwise><c:if test="${etlpzGl.xfzlCmb >= 0 }">${etlpzGl.xfzlCmb }/</c:if><c:if test="${etlpzGl.xfzlCab >= 0 }"><f:jt min="${etlpzGl.hjin }" value="${etlpzGl.xfzlCab }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${etlpzGl.fufx != null && etlpzGl.fufs != null && etlpzGl.xfzlNmb != null && etlpzGl.xfzlNab != null && (etlpzGl.fufx > (etlpzGl.xfzlNmb + etlpzGl.xfzlNab) || etlpzGl.fufs < (etlpzGl.xfzlNmb + etlpzGl.xfzlNab)) }"><span style="color:#F94545;">${etlpzGl.xfzlNmb }/${etlpzGl.xfzlNab }</span></c:when><c:otherwise><c:if test="${etlpzGl.xfzlNmb >= 0 }">${etlpzGl.xfzlNmb }/</c:if><c:if test="${etlpzGl.xfzlNab >= 0 }"><f:jt min="${etlpzGl.hjin }" value="${etlpzGl.xfzlNab }" /></c:if></c:otherwise></c:choose></td>
		</tr>
	</c:if>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr xu.id="${item.id }" key="${item.jbno }" <c:choose><c:when test="${item.newed }">style="background-color: #AFAEA4;"</c:when><c:otherwise></c:otherwise></c:choose> ondblclick="toModify(this)" xu.menu="menu" title="">
			<td rowspan="2"><span title="<f:v value="item.scsj" format="yyyy-MM-dd HH:mm" />"><f:v value="item.scsj" format="HH:mm" /></span></td>
			<td rowspan="2">${item.pass }</td>
			<td rowspan="2"><sys:codeLabel key="item.ban" code="#SINO_BAN" show="1" /></td>
			<td rowspan="2"><sys:codeLabel key="item.zu" code="#SINO_ZU" show="1" /></td>
			<td rowspan="2"><span <c:if test="${item.jycs > 1 }">style="color:#00CC00;" title="复检"</c:if>>${item.zzsd }-${item.jbno }</span></td>
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
			<td rowspan="2"><f:ot text="item.tj" len="14" /></td>
			<td rowspan="2">${item.tyjms }</td>
		</tr>
		<tr <c:choose><c:when test="${item.newed }">style="background-color: #AFAEA4;"</c:when><c:otherwise></c:otherwise></c:choose>>
			<td>B <c:choose><c:when test="${item.fufx != null && item.fufs != null && item.xfzlSmb != null && item.xfzlSab != null && (item.fufx > (item.xfzlSmb + item.xfzlSab) || item.fufs < (item.xfzlSmb + item.xfzlSab)) }"><span style="color:#F94545;">${item.xfzlSmb }/${item.xfzlSab }</span></c:when><c:otherwise><c:if test="${item.xfzlSmb >= 0 }">${item.xfzlSmb }/</c:if><c:if test="${item.xfzlSab >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlSab }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${item.fufx != null && item.fufs != null && item.xfzlCmb != null && item.xfzlCab != null && (item.fufx > (item.xfzlCmb + item.xfzlCab) || item.fufs < (item.xfzlCmb + item.xfzlCab)) }"><span style="color:#F94545;">${item.xfzlCmb }/${item.xfzlCab }</span></c:when><c:otherwise><c:if test="${item.xfzlCmb >= 0 }">${item.xfzlCmb }/</c:if><c:if test="${item.xfzlCab >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlCab }" /></c:if></c:otherwise></c:choose></td>
			<td>  <c:choose><c:when test="${item.fufx != null && item.fufs != null && item.xfzlNmb != null && item.xfzlNab != null && (item.fufx > (item.xfzlNmb + item.xfzlNab) || item.fufs < (item.xfzlNmb + item.xfzlNab)) }"><span style="color:#F94545;">${item.xfzlNmb }/${item.xfzlNab }</span></c:when><c:otherwise><c:if test="${item.xfzlNmb >= 0 }">${item.xfzlNmb }/</c:if><c:if test="${item.xfzlNab >= 0 }"><f:jt min="${item.hjin }" value="${item.xfzlNab }" /></c:if></c:otherwise></c:choose></td>
		</tr>
	</c:forEach>
</table>
<f:paged />