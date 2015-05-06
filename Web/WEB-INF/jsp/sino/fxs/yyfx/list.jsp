<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<tr>
<th rowspan="3"><input type="checkbox" xu.target="ids" /></th>
<th rowspan="3">时间</th>
<th colspan="2">碱浸液</th>
<th colspan="2">碱电解液</th>
<th colspan="2">酸电解液</th>
<th colspan="5">锡电镀液</th>
<th colspan="2">拖出液</th>
<th colspan="3">化学处理液</th>
<th colspan="2">中央铬</th>
<th rowspan="3">备注</th>
</tr>
<tr>
<th>Free</th>
<th>Total</th>
<th>Free</th>
<th>Total</th>
<th>H<sub>2</sub>S0<sub>4</sub></th>
<th>Fe</th>
<th>Sn</th>
<th>Acid</th>
<th>ENSA</th>
<th>Fe</th>
<th>Sludge</th>
<th>Sn</th>
<th>Acid</th>
<th>Cr</th>
<th>PH</th>
<th>Sludge</th>
<th rowspan="2">浓度</th>
<th rowspan="2">PH</th>
</tr>
<tr>
<th>40±5</th>
<th>50±5</th>
<th>40±5</th>
<th>50±5</th>
<th>32±5</th>
<th>20以下</th>
<th>30±4</th>
<th>13±3</th>
<th>10±2</th>
<th>&lt;=15</th>
<th>&lt;=0.5%</th>
<th>8以下</th>
<th>6以下</th>
<th>20±3</th>
<th>5.5±0.5</th>
<th>&lt;=2%</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr xu.id="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
<td align="center"><input type="checkbox" name="ids" value="${item.id}" /></td>
<td><f:v value="item.rq" format="HH:mm"/></td>
<td><c:if test="${item.jjyFreeCon != null }"><f:jt max="${yypdVO.jyZyjMax }" min="${yypdVO.jyZyjMin }" value="${item.jjyFreeCon }" /></c:if></td>
<td><c:if test="${item.jjyTotalCon != null }"><f:jt max="${yypdVO.jyZjMax }" min="${yypdVO.jyZjMin }" value="${item.jjyTotalCon }" /></c:if></td>
<td><c:if test="${item.jdjyFreeCon != null }"><f:jt max="${yypdVO.jdjZyjMax }" min="${yypdVO.jdjZyjMin }" value="${item.jdjyFreeCon }" /></c:if></td>
<td><c:if test="${item.jdjyTotalCon != null }"><f:jt max="${yypdVO.jdjZjMax }" min="${yypdVO.jdjZjMin }" value="${item.jdjyTotalCon }" /></c:if></td>
<td><c:if test="${item.sdjyHsoCon != null }"><f:jt max="${yypdVO.sdjLsMax }" min="${yypdVO.sdjLsMin }" value="${item.sdjyHsoCon }" /></c:if></td>
<td><c:if test="${item.sdjyQt != null }"><f:jt max="${yypdVO.sdjQtMax }" min="${yypdVO.sdjQtMin }" value="${item.sdjyQt }" /></c:if></td>
<td><c:if test="${item.xddySntCon != null }"><f:jt max="${yypdVO.ddXMax }" min="${yypdVO.ddXMin }" value="${item.xddySntCon }" /></c:if></td>
<td><c:if test="${item.xddyAcidtCon != null }"><f:jt max="${yypdVO.ddSMax }" min="${yypdVO.ddSMin }" value="${item.xddyAcidtCon }" /></c:if></td>
<td><c:if test="${item.xddyEnsa != null }"><f:jt max="${yypdVO.ddEnsaMax }" min="${yypdVO.ddEnsaMin }" value="${item.xddyEnsa }" /></c:if></td>
<td>${item.xddyFe }</td>
<td>${item.xddySludge }</td>
<td><c:choose><c:when test="${item.tcySnCon != null && item.tcySnCon == 0 }">${item.tcySnCon }</c:when><c:when test="${item.tcySnCon != null }"><f:jt max="${yypdVO.qyyXMax }" min="${yypdVO.qyyXMin }" value="${item.tcySnCon }" /></c:when><c:otherwise></c:otherwise></c:choose></td>
<td><c:if test="${item.tcyAcidCon != null }"><f:jt max="${yypdVO.qyySMax }" min="${yypdVO.qyySMin }" value="${item.tcyAcidCon }" /></c:if></td>
<td><c:if test="${item.hxclyCrCon != null }"><f:jt max="${yypdVO.hxLMax }" min="${yypdVO.hxLMin }" value="${item.hxclyCrCon }" /></c:if></td>
<td><c:if test="${item.hxclyPh != null }"><f:jt max="${yypdVO.hxPhMax }" min="${yypdVO.hxPhMin }" value="${item.hxclyPh }" /></c:if></td>
<td>${item.hxclySludge }</td>
<td>${item.zygCon }</td>
<td>${item.zygPh }</td>
<td>${item.bz }</td>
</tr>
</c:forEach>
</table>
<f:paged/>
