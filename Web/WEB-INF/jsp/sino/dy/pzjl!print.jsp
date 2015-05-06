<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
		<span style="padding-left: 1400px;">PAGE：${index + 1 } / ${pages }</span>
		<table width="1500" align="center" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse: collapse; border-top: 0px; border-left: 0px;">
			<colgroup>
				<col width="4%"/><col width="3%"/><col width="3%"/><col width="6%"/><col width="5%"/><col width="5%"/>
				<col width="4%"/><col width="4%"/><col width="4%"/><col width="4%"/><col width="4%"/>
				<col width="4%"/><col width="4%"/><col width="4%"/><col width="5%"/><col width="5%"/>
				<col width="7%"/><col width="5%"/><col width="5%"/><col width="5%"/><col width="5%"/>
				<col width="5%"/>
			</colgroup>
			<tr style="border-top: 0px; border-left: 0px;">
				<th colspan="4" rowspan="2" style="border-left: 0px; border-top: 0px; border-right: 0px; text-align: center; vertical-align: middle;"><f:v value="page.scsjBegin" format="yyyy年MM月dd日" /></th>
				<th colspan="13" rowspan="2" style="border-left: 0px; border-top: 0px; font-size: 28px; vertical-align: top;">ETL 品 质 管 理 表</th>
				<th height="25">厂长</th>
				<th>主任</th>
				<th>1班</th>
				<th>2班</th>
				<th>3班</th>
			</tr>
			<tr>
				<th height="40">&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<tr>
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
				<th rowspan="2" colspan="5">特记</th>
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
					<td rowspan="2">${item.pass }</td>
					<td rowspan="2"><sys:codeLabel key="item.ban" code="#SINO_BAN" show="1" /></td>
					<td rowspan="2"><sys:codeLabel key="item.zu" code="#SINO_ZU" show="1" /></td>
					<td rowspan="2">${item.zzsd }-${item.jbno }</td>
					<td rowspan="2">${item.fugm }</td>
					<td rowspan="2"><c:if test="${item.sczm != null }">${item.sczm }/</c:if>${item.scfm }</td>
					<td>F <c:if test="${item.xfzlSmt >= 0 }">${item.xfzlSmt }/</c:if><c:if test="${item.xfzlSat >= 0 }">${item.xfzlSat }</c:if></td>
					<td>  <c:if test="${item.xfzlCmt >= 0 }">${item.xfzlCmt }/</c:if><c:if test="${item.xfzlCat >= 0 }">${item.xfzlCat }/</c:if></td>
					<td>  <c:if test="${item.xfzlNmt >= 0 }">${item.xfzlNmt }/</c:if><c:if test="${item.xfzlNat >= 0 }">${item.xfzlNat }/</c:if></td>
					<td rowspan="2">${item.ytyp }${item.yqty }</td>
					<td rowspan="2">${item.oilerBl }</td>
					<td rowspan="2"><c:if test="${item.tylSt >= 0 }">${item.tylSt }/</c:if><c:if test="${item.tylSb >= 0 }">${item.tylSb }/</c:if></td>
					<td rowspan="2"><c:if test="${item.tylCt >= 0 }">${item.tylCt }/</c:if><c:if test="${item.tylCb >= 0 }">${item.tylCb }/</c:if></td>
					<td rowspan="2"><c:if test="${item.tylNt >= 0 }">${item.tylNt }/</c:if><c:if test="${item.tylNb >= 0 }">${item.tylNb }/</c:if></td>
					<td rowspan="2"><c:if test="${item.cheDlmdt !=null }">${item.cheDlmdt }/</c:if>${item.cheDlmdb }</td>
					<td rowspan="2"><c:if test="${item.crCt >= 0 }">${item.crCt }/</c:if><c:if test="${item.crCb >= 0 }">${item.crCb }/</c:if></td>
					<td rowspan="2" colspan="5">${item.tj}</td>
					<td rowspan="2">${item.tyjms }</td>
				</tr>
				<tr>
					<td>B <c:if test="${item.xfzlSmb >= 0 }">${item.xfzlSmb }/</c:if><c:if test="${item.xfzlSab >= 0 }">${item.xfzlSab }</c:if></td>
					<td>  <c:if test="${item.xfzlCmb >= 0 }">${item.xfzlCmb }/</c:if><c:if test="${item.xfzlCab >= 0 }">${item.xfzlCab }</c:if></td>
					<td>  <c:if test="${item.xfzlNmb >= 0 }">${item.xfzlNmb }/</c:if><c:if test="${item.xfzlNab >= 0 }">${item.xfzlNab }</c:if></td>
				</tr>
			</c:forEach>
		</table>
</div>
