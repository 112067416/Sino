<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@
taglib prefix="sino" uri="/sino" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	</head>
	<body>
		<ui:page title="查看订货合同信息">
		<form name="DataForm" >
		<input type="hidden" name="syno" value="${dhuoTp.syno }" />
		<table width="96%" class="form">
		<colgroup>
			<col width="10%" />
			<col width="25%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="25%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th>担当</th>
			<td>${dhuoTp.ddno }</td>
			<th>订货NO.</th>
			<td>${dhuoTp.dhno }-${dhuoTp.line }</td>
			<td colspan="3">&nbsp;</td>
		</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<table width="96%" class="form">
			<colgroup>
				<col width="5%" /><col width="6%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="15%" />
				<col width="15%" /><col width="8%" /><col width="5%" /><col width="8%" /><col width="23%" />
			</colgroup>
			<tr>
				<th style="text-align: left;">规格</th>
				<td>${dhuoTp.ggno }</td>
				<th style="text-align: left;">品种</th>
				<td colspan="2">${dhuoTp.pzno }</td>
				<th style="text-align: left;">附着量<span style="font-size: 12px;">(单位.正面.反面)</span></th>
				<td>
				${dhuoTp.fudw }&nbsp;${dhuoTp.fuzm }&nbsp;${dhuoTp.fufm }
				</td>
				<th style="text-align: left;">差厚</th>
				<td>${dhuoTp.chdx }</td>
				<th style="text-align: left;">内外</th>
				<td>${dhuoTp.nwai }</td>
			</tr>
			<tr>
				<th colspan="2" style="text-align: left;">尺寸<span style="font-size: 12px;">(厚*宽*剪切板长)</span></th>
				<td colspan="4"> ${dhuoTp.houu }* ${dhuoTp.kuan }* <c:choose><c:when test="${dhuoTp.cang != null && dhuoTp.cang > 0}">${dhuoTp.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
				<th style="text-align: left;">用途</th>
				<td> ${dhuoTp.cond }</td>
				<th style="text-align: left;">用户代码</th>
				<td>${dhuoTp.user }</td>
				<td>${dhuoTp.abbr }</td>
			</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<table width="100%" class="form" >
			<colgroup>
			<col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" />
			<col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="5%" />
			</colgroup>
			<tr>
			<th style="text-align: left;">交货地点</th>
			<td colspan="8">${dhuoTp.addr }</td>
			<th style="text-align: left;">商社</th>
			<td colspan="9">${dhuoTp.ssno } &nbsp;&nbsp; ${dhuoTp.ssnm }</td>
			<th style="text-align: left;">交货日期</th> 
			<td colspan="2"><f:v value="dhuoTp.jhqi" format="yyyy-MM-dd"/></td>
			</tr>
			<tr>
			<th style="text-align: left;">重内</th>
			<td>${dhuoTp.jhnr }</td>
			<th>合同重量</th>
			<td colspan="2">${dhuoTp.htzl }</td>
			<th style="text-align: left;">交货允许</th>
			<td colspan="4">${dhuoTp.jhqf }&nbsp;&nbsp;${dhuoTp.jhfz }&nbsp;&nbsp;${dhuoTp.jhzz }</td>
			<th style="text-align: left;">单包张数</th>
			<td colspan="3">${dhuoTp.kbsq }&nbsp;${dhuoTp.kbsz }</td>
			<th style="text-align: left;">卷重</th>
			<td colspan="5">${dhuoTp.kbzq }&nbsp;${dhuoTp.kbzd }&nbsp;${dhuoTp.kbzx }&nbsp;${dhuoTp.kbzs }</td>
			<th style="text-align: left;">捆包计算</th>
			<td>${dhuoTp.kbjs }</td>
			</tr>
			<tr>
			<th style="text-align: left;">化成</th>
			<td>${dhuoTp.huac }</td>
			<th style="text-align: left;">表面</th>
			<td>${dhuoTp.face }</td>
			<th style="text-align: left;">压延</th>
			<td>${dhuoTp.yyan }</td>
			<th style="text-align: left;">垫木</th>
			<td>${dhuoTp.dmfx }</td>
			<th style="text-align: left;">捆包形式</th>
			<td>${dhuoTp.kbao }</td>
			<th style="text-align: left;">货标</th>
			<td>${dhuoTp.hbbj }</td>
			<th style="text-align: left;">外W</th>
			<td>${dhuoTp.rjie }</td>
			<th style="text-align: left;">内径</th>
			<td>${dhuoTp.neij }</td>
			<th colspan="2" style="text-align: left;">B&nbsp;&nbsp;&nbsp;${dhuoTp.tbno }</th>
			<th style="text-align: left;">C</th>
			<td>${dhuoTp.tcno }</td>
			<th style="text-align: left;">需L</th>
			<td>${dhuoTp.yhbq }</td>
			</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<table width="96%" class="form">
			<colgroup>
			<col width="4%" /><col width="3%" /><col width="6%" /><col width="9%" />
			<col width="8%" /><col width="5%" /><col width="8%" /><col width="5%" />
			<col width="6%" /><col width="5%" /><col width="6%" /><col width="5%" />
			<col width="8%" /><col width="6%" /><col width="8%" /><col width="5%" />
			</colgroup>
			<tr>
			<th style="text-align: left;">剪边</th>
			<td>${dhuoTp.jian } </td>
			<th style="text-align: left;">零头下限</th>
			<td>${dhuoTp.ltdw }&nbsp;&nbsp;${dhuoTp.ltzi }</td>
			<th style="text-align: left;">涂油种类</th>
			<td>${dhuoTp.ytyp }</td>
			<th style="text-align: left;">KPLATE</th>
			<td>${dhuoTp.plat }</td>
			<th style="text-align: left;">涂油量</th>
			<td>${dhuoTp.yqty }</td>
			<th style="text-align: left;">合金层</th>
			<td>${dhuoTp.hjin }</td>
			<th style="text-align: left;">分配等级</th>
			<td>${dhuoTp.fpdj }</td>
			<th style="text-align: left;">直角度</th>
			<td>${dhuoTp.jiao }</td>
			</tr>
			<tr>
			<th style="text-align: left;">保护标记</th>
			<td>${dhuoTp.prot }</td>
			<th style="text-align: left;" >硬度上下限</th>
			<td colspan="2" >${dhuoTp.ymin }&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;${dhuoTp.ymax }</td>
			<th style="text-align: left;" colspan="2" >附着量.正上下限</th>
			<td colspan="3">${dhuoTp.fuzx }&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;${dhuoTp.fuzs }</td>
			<th style="text-align: left;" colspan="2" >附着量.反上下限</th>
			<td colspan="4">${dhuoTp.fufx }&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;${dhuoTp.fufs }</td>
			</tr>
			<tr>
			<th style="text-align: left;">锯齿形式</th>
			<td>${dhuoTp.jcxs }</td>
			<th style="text-align: left;">公差厚(%)</th>
			<td colspan="2" >${dhuoTp.hxzi }&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;${dhuoTp.hszi }</td>
			<th style="text-align: left;" colspan="2" >公差宽(mm)</th>
			<td colspan="3" >${dhuoTp.kxzi }&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;${dhuoTp.kszi }</td>
			<th style="text-align: left;" colspan="2" >公差长(mm)</th>
			<td colspan="4">${dhuoTp.cszi }&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;${dhuoTp.cxzi }</td>
			</tr>
			<tr>
			<th style="text-align: left;">装入宽</th>
			<td colspan="2" >${dhuoTp.zrkn }</td>
			<th style="text-align: left;">内径保护筒</th>
			<td>${dhuoTp.njbh }</td>
			<th style="text-align: left;">运用规格</th>
			<td colspan="2" >${dhuoTp.yuny }</td>
			<th style="text-align: left;">附页KPNo</th>
			<td colspan="7" ><f:null value="dhuoTp.kpn1Flag" not="true">${dhuoTp.kpn1Flag }-</f:null>${dhuoTp.kpn1 }&nbsp;&nbsp;<f:null value="dhuoTp.kpn2Flag" not="true">${dhuoTp.kpn2Flag }-</f:null>${dhuoTp.kpn2 }&nbsp;&nbsp;<f:null value="dhuoTp.kpn3Flag" not="true">${dhuoTp.kpn3Flag }-</f:null>${dhuoTp.kpn3 }&nbsp;&nbsp;<f:null value="dhuoTp.kpn4Flag" not="true">${dhuoTp.kpn4Flag }-</f:null>${dhuoTp.kpn4 }</td>
			</tr>
			<tr>
				<th style="text-align: left;">木工所No</th>
				<td colspan="15">${dhuoTp.mgsn }</td>
			</tr>
			<tr>
				<th style="text-align: left;">备注1</th>
				<td colspan="15">${dhuoTp.bz1 }</td>
			</tr>
			<tr>
				<th style="text-align: left;">备注2</th>
				<td colspan="15">${dhuoTp.bz2 }</td>
			</tr>
			<tr>
				<th style="text-align: left;">备注3</th>
				<td colspan="15">${dhuoTp.bz3 }</td>
			</tr>
		</table>
		</form>
		</ui:page>
	</body>
</html>
