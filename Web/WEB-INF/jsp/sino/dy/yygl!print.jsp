<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
<table width="1500" align="center" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse: collapse; border-top: 0px; border-left: 0px; font-size: 13px; padding-left: 5px;">
	<colgroup>
		<col width="63"/>
		<col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/>
		<col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/><col width="47"/>
		<col width="47"/><col width="47"/>
		<col width="67"/><col width="67"/><col width="67"/><col width="67"/><col width="60"/><col width="61"/><col width="61"/>
	</colgroup>
	<tr style="border-top: 0px; border-left: 0px;">
		<th colspan="5" rowspan="2" style="border-left: 0px; border-top: 0px; border-right: 0px; text-align: center; vertical-align: middle;"><f:v value="page.rqBegin" format="yyyy年MM月dd日" /></th>
		<th colspan="17" rowspan="2" align="center" style="border-left: 0px; border-top: 0px; font-size: 28px; vertical-align: top;">ETL  药  液  管  理  记  表</th>
		<th height="25" colspan=2">厂长</th>
		<th colspan=2">主任</th>
		<th>1班</th>
		<th>2班</th>
		<th>3班</th>
	</tr>
	<tr>
		<th colspan=2" height="40">&nbsp;</th>
		<th colspan=2">&nbsp;</th>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
	</tr>
	<tr>
		<th rowspan="3">时分</th>
		<th colspan="4">碱浸</th>
		<th colspan="4">碱电解</th>
		<th colspan="4">酸电解</th>
		<th colspan="5">电镀</th>
		<th colspan="4">去药液</th>
		<th colspan="7">化学</th>
	</tr>
	<tr>
		<th>自由碱</th>
		<th>总碱</th>
		<th colspan="2">补给</th>
		<th>自由碱</th>
		<th>总碱</th>
		<th colspan="2">补给</th>
		<th>硫酸</th>
		<th>全铁</th>
		<th colspan="2">补给</th>
		<th>锡</th>
		<th>ENSA</th>
		<th>酸</th>
		<th colspan="2">补给</th>
		<th>锡</th>
		<th>酸</th>
		<th colspan="2">排入</th>
		<th colspan="2">铬</th>
		<th colspan=2">PH值</th>
		<th colspan="3">补给</th>
	</tr>
	<tr>
		<th>40±5</th>
		<th>50±5</th>
		<th>水</th>
		<th>碱</th>
		<th>40±5</th>
		<th>50±5</th>
		<th>水</th>
		<th>碱</th>
		<th>32±5</th>
		<th>20以下</th>
		<th>水</th>
		<th>硫酸</th>
		<th>30±4</th>
		<th>10±2</th>
		<th>13±3</th>
		<th>ENSA</th>
		<th>PSA</th>
		<th>8以下</th>
		<th>1~6</th>
		<th>201</th>
		<th>403</th>
		<th colspan="2">20±3</th>
		<th colspan=2">5.5±0.5</th>
		<th>水</th>
		<th>铬酸酐</th>
		<th>重铬酸钠</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
	<c:choose>
	<c:when test="${item.yyfxid != null }">
	<tr>
	<td><f:v value="item.rq" format="HH:mm" /> </td>
	<td><f:v value="item.yyfxid.jjyFreeCon" format="1" /></td>
	<td><f:v value="item.yyfxid.jjyTotalCon" format="1" /></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	
	<td><f:v value="item.yyfxid.jdjyFreeCon" format="1" /></td>
	<td><f:v value="item.yyfxid.jdjyTotalCon" format="1" /></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	
	<td><f:v value="item.yyfxid.sdjyHsoCon" format="1" /></td>
	<td><f:v value="item.yyfxid.sdjyQt" format="1" /></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	
	<td><f:v value="item.yyfxid.xddySntCon" format="1" /></td>
	<td><f:v value="item.yyfxid.xddyEnsa" format="1" /></td>
	<td><f:v value="item.yyfxid.xddyAcidtCon" format="1" /></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	
	<td><f:v value="item.yyfxid.tcySnCon" format="1" /></td>
	<td><f:v value="item.yyfxid.tcyAcidCon" format="1" /></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	
	<td colspan="2"><f:v value="item.yyfxid.hxclyCrCon" format="1" /></td>
	<td colspan="2"><f:v value="item.yyfxid.hxclyPh" format="1" /></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
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
	<tr>
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
		<td rowspan="3" colspan="2">${entity.cdwDd }</td>
		<td rowspan="3" colspan="2">${entity.cdwHx }</td>
		<td rowspan="3" colspan="2">${entity.ddtnd }</td>
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
</div>
