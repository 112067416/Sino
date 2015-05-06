<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="/sys" prefix="sys"%>
<table id="DataTb" width="1300" cellpadding="0" cellspacing="0" align="center" class="pagination1" style="border-collapse: collapse;">
<colgroup>
<col width="3%" /><col width="3%" /><col width="3%" /><col width="4%" /><col width="3%" /><col width="3%" /><col width="3%" />
<col width="4%" /><col width="3%" /><col width="3%" /><col width="3%" /><col width="5%" /><col width="3%" /><col width="4%" />
<col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" />
<col width="3%" /><col width="2%" /><col width="3%" /><col width="2%" /><col width="3%" /><col width="4%" /><col width="4%" />
<col width="4%" /></colgroup>
<tr>
<th colspan="5" rowspan="2" style="text-align: center; vertical-align: middle;"><f:v value="page.rqBegin" format="yyyy年MM月dd日" /></th>
<th colspan="17" rowspan="2" align="center" style="font-size: 28px; vertical-align: top;">ETL  药  液  管  理  记  表</th>
<th height="25" colspan=2">厂长</th>
<th colspan=2">主任</th>
<th>1班</th>
<th>2班</th>
<th>3班</th>
</tr>
<tr>
<th colspan=2" height="40">&nbsp;</th>
<th colspan=2">&nbsp;</th>
<th>${entity.qrr1 }</th>
<th>${entity.qrr2 }</th>
<th>${entity.qrr3 }</th>
</tr>
<tr>
<th nowrap="nowrap" rowspan="3">时分</th>
<th nowrap="nowrap" colspan="4">碱浸</th>
<th nowrap="nowrap" colspan="4">碱电解</th>
<th nowrap="nowrap" colspan="4">酸电解</th>
<th nowrap="nowrap" colspan="5">电镀</th>
<th nowrap="nowrap" colspan="4">去药液</th>
<th nowrap="nowrap" colspan="7">化学</th>
</tr>
<tr>
<th nowrap="nowrap">自由碱</th>
<th nowrap="nowrap">总碱</th>
<th nowrap="nowrap" colspan="2">补给</th>
<th nowrap="nowrap">自由碱</th>
<th nowrap="nowrap">总碱</th>
<th nowrap="nowrap" colspan="2">补给</th>
<th nowrap="nowrap">硫酸</th>
<th nowrap="nowrap">全铁</th>
<th nowrap="nowrap" colspan="2">补给</th>
<th nowrap="nowrap">锡</th>
<th nowrap="nowrap">ENSA</th>
<th nowrap="nowrap">酸</th>
<th nowrap="nowrap" colspan="2">补给</th>
<th nowrap="nowrap">锡</th>
<th nowrap="nowrap">酸</th>
<th nowrap="nowrap" colspan="2">排入</th>
<th nowrap="nowrap" colspan="2">铬</th>
<th nowrap="nowrap" colspan=2">PH值</th>
<th nowrap="nowrap" colspan="3">补给</th>
</tr>
<tr>
<th nowrap="nowrap">40±5</th>
<th nowrap="nowrap">50±5</th>
<th nowrap="nowrap">水</th>
<th nowrap="nowrap">碱</th>
<th nowrap="nowrap">40±5</th>
<th nowrap="nowrap">50±5</th>
<th nowrap="nowrap">水</th>
<th nowrap="nowrap">碱</th>
<th nowrap="nowrap">32±5</th>
<th nowrap="nowrap">20以下</th>
<th nowrap="nowrap">水</th>
<th nowrap="nowrap">硫酸</th>
<th nowrap="nowrap">30±4</th>
<th nowrap="nowrap">10±2</th>
<th nowrap="nowrap">13±3</th>
<th nowrap="nowrap">ENSA</th>
<th nowrap="nowrap">PSA</th>
<th nowrap="nowrap">8以下</th>
<th nowrap="nowrap">6以下</th>
<th nowrap="nowrap">201</th>
<th nowrap="nowrap">403</th>
<th nowrap="nowrap" colspan="2">20±3</th>
<th nowrap="nowrap" colspan=2">5.5±0.5</th>
<th nowrap="nowrap">水</th>
<th nowrap="nowrap">铬酸酐</th>
<th nowrap="nowrap">重铬酸钠</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<c:choose>
<c:when test="${item.yyfxid != null }">
<tr xu.id="${item.id }" xu.menu="menu1" <c:choose><c:when test="${item.newed }">style="background-color: #AFAEA4;"</c:when><c:when test="${stat.index%2 == 1 }">class="odd"</c:when><c:otherwise></c:otherwise></c:choose> onmouseover="overTr(this)" onmouseout="outTr(this)">
<td><f:v value="item.rq" format="HH:mm" /> </td>
<td><c:if test="${item.yyfxid.jjyFreeCon >= 0 }"><f:jt min="${cnsts.jyZyjMin }" max="${cnsts.jyZyjMax }" value="${item.yyfxid.jjyFreeCon }" precision="1" /></c:if></td>
<td><c:if test="${item.yyfxid.jjyTotalCon >= 0 }"><f:jt min="${cnsts.jyZjMin }" max="${cnsts.jyZjMax }" value="${item.yyfxid.jjyTotalCon }" precision="1" /></c:if></td>
<td>&nbsp;</td>
<td>&nbsp;</td>

<td><c:if test="${item.yyfxid.jdjyFreeCon >= 0 }"><f:jt min="${cnsts.jdjZyjMin }" max="${cnsts.jdjZyjMax }" value="${item.yyfxid.jdjyFreeCon }" precision="1" /></c:if></td>
<td><c:if test="${item.yyfxid.jdjyTotalCon >= 0 }"><f:jt min="${cnsts.jdjZjMin }" max="${cnsts.jdjZjMax }" value="${item.yyfxid.jdjyTotalCon }" precision="1" /></c:if></td>
<td>&nbsp;</td>
<td>&nbsp;</td>

<td><c:if test="${item.yyfxid.sdjyHsoCon >= 0 }"><f:jt min="${cnsts.sdjLsMin }" max="${cnsts.sdjLsMax }" value="${item.yyfxid.sdjyHsoCon }" precision="1" /></c:if></td>
<td><c:if test="${item.yyfxid.sdjyQt >= 0 }"><f:jt min="${cnsts.sdjQtMin }" max="${cnsts.sdjQtMax }" value="${item.yyfxid.sdjyQt }" precision="1" /></c:if></td>
<td>&nbsp;</td>
<td>&nbsp;</td>

<td><c:if test="${item.yyfxid.xddySntCon >= 0 }"><f:jt min="${cnsts.ddXMin }" max="${cnsts.ddXMax }" value="${item.yyfxid.xddySntCon }" precision="1" /></c:if></td>
<td><c:if test="${item.yyfxid.xddyEnsa >= 0 }"><f:jt min="${cnsts.ddEnsaMin }" max="${cnsts.ddEnsaMax }" value="${item.yyfxid.xddyEnsa }" precision="1" /></c:if></td>
<td><c:if test="${item.yyfxid.xddyAcidtCon >= 0 }"><f:jt min="${cnsts.ddSMin }" max="${cnsts.ddSMax }" value="${item.yyfxid.xddyAcidtCon }" precision="1" /></c:if></td>
<td>&nbsp;</td>
<td>&nbsp;</td>

<td><c:if test="${item.yyfxid.tcySnCon >= 0 }"><f:jt min="${cnsts.qyyXMin }" max="${cnsts.qyyXMax }" value="${item.yyfxid.tcySnCon }" precision="1" /></c:if></td>
<td><c:if test="${item.yyfxid.tcyAcidCon >= 0 }"><f:jt min="${cnsts.qyySMin }" max="${cnsts.qyySMax }" value="${item.yyfxid.tcyAcidCon }" precision="1" /></c:if></td>
<td>&nbsp;</td>
<td>&nbsp;</td>

<td colspan="2"><c:if test="${item.yyfxid.hxclyCrCon >= 0 }"><f:jt min="${cnsts.hxLMin }" max="${cnsts.hxLMax }" value="${item.yyfxid.hxclyCrCon }" precision="1" /></c:if></td>
<td colspan="2"><c:if test="${item.yyfxid.hxclyPh >= 0 }"><f:jt min="${cnsts.hxPhMin }" max="${cnsts.hxPhMax }" value="${item.yyfxid.hxclyPh }" precision="1" /></c:if></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
</c:when>
<c:otherwise>
<tr xu.id="${item.id }" xu.menu="menu2" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)">
<td><f:v value="item.rq" format="HH:mm" /> </td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>${item.jjbgs }</td>
<td>${item.jjbgj }</td>

<td>&nbsp;</td>
<td>&nbsp;</td>
<td>${item.jdjbgs }</td>
<td>${item.jdjbgj }</td>

<td>&nbsp;</td>
<td>&nbsp;</td>
<td>${item.sdjbgs }</td>
<td>${item.sdjbgLs }</td>

<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>${item.ddbgEnsa }</td>
<td>${item.ddbgPsa }</td>

<td>&nbsp;</td>
<td>&nbsp;</td>
<td>${item.qyypr201 }</td>
<td>${item.qyypr403 }</td>

<td colspan="2">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>${item.hxbgSh }</td>
<td>${item.hxbgGsg }</td>
<td>${item.hxbgZgsn }</td>
</tr>
</c:otherwise>
</c:choose>
</c:forEach>
<c:if test="${count < pageSize }">
<c:forEach var="index" begin="${count }" end="${pageSize - 1 }" >
<tr xu.id="" xu.menu="menu2" <c:if test="${index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)">
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
</c:forEach>
</c:if>
<tr>
<th rowspan="5">其它</th>
<th colspan="4">沉淀物(次/周)</th>
<th colspan="2" rowspan="2">电镀铁浓度<br />(次/周)</th>
<th rowspan="2">班次</th>
<th colspan="3" rowspan="2">T101液量</th>
<th colspan="3" rowspan="2">T102量</th>
<th colspan="3" rowspan="2">T106量</th>
<th colspan="6">电镀液</th>
<th colspan="4">去药液抽到动力(次数)</th>
<th colspan="2">化学液量</th>
</tr>
<tr>
<th colspan="2">电镀</th>
<th colspan="2">化学</th>
<th colspan="2">T201液量</th>
<th colspan="2">T202液量</th>
<th colspan="2">T203液量</th>
<th colspan="2">班次</th>
<th colspan="2">T403</th>
<th colspan="2">T304</th>
</tr>
<tr>
<td rowspan="3" colspan="2"><f:v value="entity.cdwDd" format="1" /></td>
<td rowspan="3" colspan="2"><f:v value="entity.cdwHx" format="1" /></td>
<td rowspan="3" colspan="2"><f:v value="entity.ddtnd" format="1" /></td>
<th>1班</th>
<td colspan="3">${entity.ylT1011 }</td>
<td colspan="3">${entity.ylT1012 }</td>
<td colspan="3">${entity.ylT1013 }</td>
<td>${entity.ddyT2011 }</td>
<td>${entity.ddyYl1 }</td>
<td colspan="2">${entity.ddyT2021 }</td>
<td colspan="2">${entity.ddyT2031 }</td>
<th colspan="2">1班</th>
<td colspan="2">${entity.qyycdT4031 }</td>
<td colspan="2">${entity.hxylT3041 }</td>
</tr>
<tr>
<th>2班</th>
<td colspan="3">${entity.ylT1021 }</td>
<td colspan="3">${entity.ylT1022 }</td>
<td colspan="3">${entity.ylT1023 }</td>
<td>${entity.ddyT2012 }</td>
<td>${entity.ddyYl2 }</td>
<td colspan="2">${entity.ddyT2022 }</td>
<td colspan="2">${entity.ddyT2032 }</td>
<th colspan="2">2班</th>
<td colspan="2">${entity.qyycdT4032 }</td>
<td colspan="2">${entity.hxylT3042 }</td>
</tr>
<tr>
<th>3班</th>
<td colspan="3">${entity.ylT1031 }</td>
<td colspan="3">${entity.ylT1032 }</td>
<td colspan="3">${entity.ylT1033 }</td>
<td>${entity.ddyT2013 }</td>
<td>${entity.ddyYl3 }</td>
<td colspan="2">${entity.ddyT2023 }</td>
<td colspan="2">${entity.ddyT2033 }</td>
<th colspan="2">3班</th>
<td colspan="2">${entity.qyycdT4033 }</td>
<td colspan="2">${entity.hxylT3043 }</td>
</tr>
</table>
