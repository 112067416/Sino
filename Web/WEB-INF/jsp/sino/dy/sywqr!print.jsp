<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
	<table width="1300" align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td style="font-size: 20px; font-weight: bold; padding-left: 520px;">*** 制造仕样未确定列表  ***<span style="position: relative; left: 300px; font-size: 14px;">${date} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; PAGE：${current } / ${pages }</span></td>
		</tr>
		<c:forEach varStatus="stat" var="item" items="${list }">
		<tr height="15">
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<table width="1300" cellpadding="0" cellspacing="0" style="text-align: center; border-collapse: collapse;" border="1" bordercolor="#000000">
					<colgroup>
					<col width="80"><col width="60"><col width="60"><col width="60"><col width="60"><col width="60">
					<col width="60"><col width="60"><col width="60"><col width="60"><col width="60"><col width="60">
					<col width="60"><col width="60"><col width="60"><col width="60"><col width="60"><col width="60">
					</colgroup>
					<tr height="25">
						<th>交货期</th>
						<th colspan="2">内外销</th>
						<th colspan="3">用户</th>
						<th colspan="2">商社</th>
						<th colspan="2">订货NO</th>
						<th colspan="2">制造仕样NO</th>
						<th>区</th>
						<th colspan="2">作成年月日</th>
						<th colspan="2">担当者</th>
						<th>&nbsp;</th>
					</tr>
					<tr height="30">
						<td><f:v value="item.jhqi" format="yyyy-MM-dd"/></td>
						<td colspan="2"><c:choose><c:when test="${item.nwai != null && item.nwai == \"1\" }">国内</c:when><c:otherwise>国外</c:otherwise></c:choose></td>
						<td colspan="3">${item.user } ${item.abbr }</td>
						<td colspan="2">${item.ssno } ${item.ssnm }</td>
						<td colspan="2">${item.dhno }-${item.line }</td>
						<td colspan="2">${item.syno }</td>
						<td><c:if test="${item.dhqr == \"1\" }">订</c:if></td>
						<td colspan="2"><f:v value="item.crea" format="yyyy-MM-dd"/></td>
						<td colspan="2">${item.ddno } ${item.ddnm }</td>
						<td>&nbsp;</td>
					</tr>
					<tr height="25">
						<th>品种</th>
						<th colspan="4">规格</th>
						<th colspan="2">运用规格</th>
						<th colspan="2">附着量</th>
						<th>差厚</th>
						<th colspan="3">订货尺寸</th>
						<th>SCROLL</th>
						<th>用途</th>
						<th>表面</th>
						<th>化成</th>
						<th>装入宽</th>
					</tr>
					<tr height="30">
						<td>${item.pzno }</td>
						<td colspan="4">${item.ggno } ${item.ggnm }</td>
						<td colspan="2">${item.yuny }</td>
						<td colspan="2">${item.fudw } ${item.fuzm }-${item.fufm }</td>
						<td>${item.chdx }</td>
						<td colspan="3">${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
						<td>&nbsp;</td>
						<td>${item.cond }</td>
						<td>${item.face }</td>
						<td>${item.huac }</td>
						<td>${item.zrkn }</td>
					</tr>
					<tr height="25">
						<th bgcolor="red">特B</th>
						<th bgcolor="red">特C</th>
						<th bgcolor="red">需</th>
						<th>压延方向</th>
						<th>SKID</th>
						<th>捆包</th>
						<th>包材重</th>
						<th>SKID重</th>
						<th bgcolor="red">外W</th>
						<th>内径</th>
						<th>合重(T)</th>
						<th style="font-size: 13px;">理论/实称</th>
						<th>捆张</th>
						<th colspan="2">捆重</th>
						<th colspan="2">零头</th>
						<th>等级</th>
					</tr>
					<tr height="30">
						<td>${item.tbno }</td>
						<td>${item.tcno }</td>
						<td>${item.yhbq }</td>
						<td>${item.yyan }</td>
						<td>${item.dmfx }</td>
						<td>${item.kbao }</td>
						<td>${item.bzcl }</td>
						<td>${item.dmzl }</td>
						<td>${item.rjie }</td>
						<td>${item.neij }</td>
						<td>${item.htzl }</td>
						<td>${item.jhnr }</td>
						<td>${item.kbsz }</td>
						<td colspan="2"><c:if test="${item.kbzx != null && item.kbzx > 0 }">${item.kbzx } ~ </c:if>${item.kbzs }</td>
						<td colspan="2">${item.ltdw }  ${item.ltzi }</td>
						<td>${item.fpdj }</td>
					</tr>
					<tr height="25">
						<th>K-PLATE</th>
						<th colspan="2">合金</th>
						<th>涂油</th>
						<th>油量</th>
						<th>直角</th>
						<th>内筒</th>
						<th>保护板</th>
						<th colspan="7">别纸KP/业联</th>
						<th colspan="3">&nbsp;</th>
					</tr>
					<tr height="30">
						<td>${item.plat }</td>
						<td colspan="2">${item.hjin }</td>
						<td>${item.ytyp }</td>
						<td>${item.yqty }</td>
						<td>${item.jiao }</td>
						<td><c:choose><c:when test="${item.njbh != null && item.njbh == \"1\" }">纸圈</c:when><c:when test="${item.njbh != null && item.njbh == \"2\" }">钢圈</c:when><c:otherwise></c:otherwise> </c:choose></td>
						<td>${item.prot }</td>
						<td colspan="7" style="text-align: left;"><c:if test="${item.kpn1 != null }">${item.kpn1Flag }-${item.kpn1 }</c:if>&nbsp;&nbsp;&nbsp;<c:if test="${item.kpn2 != null }">${item.kpn2Flag }-${item.kpn2 }</c:if>&nbsp;&nbsp;&nbsp;<c:if test="${item.kpn3 != null }">${item.kpn3Flag }-${item.kpn3 }</c:if>&nbsp;&nbsp;&nbsp;<c:if test="${item.kpn4 != null }">${item.kpn4Flag }-${item.kpn4 }</c:if></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr height="25">
						<th>硬度</th>
						<th colspan="4">附着量正</th>
						<th colspan="2">附着量反</th>
						<th colspan="3">公差厚(%)</th>
						<th colspan="3">公差宽(MM)</th>
						<th colspan="3">公差长(MM)</th>
						<th colspan="2">&nbsp;</th>
					</tr>
					<tr height="30">
						<td>${item.ymin } / ${item.ymax }</td>
						<td colspan="4">${item.fuzx } / ${item.fuzs }</td>
						<td colspan="2">${item.fufx } / ${item.fufs }</td>
						<td colspan="3">${item.hxzi } / ${item.hszi } </td>
						<td colspan="3">${item.kxzi } / ${item.kszi }</td>
						<td colspan="3">${item.cxzi } / ${item.cszi }</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
