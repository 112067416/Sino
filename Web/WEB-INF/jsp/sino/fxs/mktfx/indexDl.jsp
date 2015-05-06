<%@ page language="java" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@  taglib uri="/sys" prefix="sys" %><%@ taglib prefix="sino" uri="/sino" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="zzsd" value="${mktfxVO.zzsd }" /><input type="hidden" name="fxxms" value="${fxxms }" /><input type="hidden" name="cyrzId" value="${mktfxVO.cyrzId }" /><input type="hidden" name="stat" />
<table width="100%" align="center" class="form" >
<colgroup><col width="8%" /><col width="10%" /><col width="8%" /><col width="10%" /><col width="8%" /><col width="10%" /><col width="46%" /></colgroup>
<tr>
<th style="text-align: left;">Coil No.</th>
<td><ui:input name="jbno" maxlength="8" required="true" title="Coil No." value="mktfxVO.jbno" readonly="true" /></td>
<th>分析日</th>
<td><ui:datetime name="fxr" showCalendar="false" value="mktfxVO.fxr" /></td>
<th>部位</th>
<td><sys:codeSelect name="bw" code="#SINO_FXS_BW" prop="nn:1;" headerText="" headerValue="" value="#M" /></td>
<th>&nbsp;</th>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">备注</th>
<td colspan="6"><ui:textarea name="bz" cssStyle="" cssClass="normal" cols="90" rows="5" /></td>
</tr>
</table>
<br />
<c:if test="${fxxms != null }">
<table width="96%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
<c:forEach varStatus="stat" var="item"  items="${xms }">
<c:choose><c:when test="${stat.index == 0 }">
<td width="1" align="right"><img src="<%=request.getContextPath() %>/images/tabpage/line_left.gif" height="25"></td>
<td width="70" id="activeTab" nowrap class="tab02-bg02" onclick="chgPage(this, ${stat.index })"><span style="font-size: 12px;">${item.description }</span></td>
</c:when>
<c:otherwise>
<td width="1" align="left"><img src="<%=request.getContextPath() %>/images/tabpage/line_right.gif" height="25"></td>
<td width="70" id="TabMenu${stat.index }" nowrap class="tab02-bg01" onclick="chgPage(this, ${stat.index })"><span style="font-size: 12px;">${item.description }</span></td>
</c:otherwise></c:choose>
</c:forEach>
<td width="1" align="left"><img src="<%=request.getContextPath() %>/images/tabpage/line_right.gif" height="20"></td>
<td width="100%">&nbsp;</td>
</tr>
</table>
</c:if>
<div id="TYL" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="32%" /></colgroup>
<tr><th colspan="7" style="text-align: center; vertical-align: middle;">涂油量 (mg/m<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr>
<th>目标量</th>
<th style="text-align: center; vertical-align: middle;" colspan="2">S</th>
<th style="text-align: center; vertical-align: middle;" colspan="2">C</th>
<th style="text-align: center; vertical-align: middle;" colspan="2">N</th>
</tr>
<tr>
<td><ui:input readonly="true" maxlength="3" name="yqty" value="mktfxVO.yqty" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="tylSt" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="tylSb" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="tylCt" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="tylCb" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="tylNt" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="tylNb" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td>
</tr>
</table>
</div>
<div id="XFZL" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<tr>	
<th colspan="2" >目标附着量</th>
<td>F<ui:input readonly="true" maxlength="3" name="fuzm" value="mktfxVO.fuzm" />&nbsp;&nbsp;&nbsp;&nbsp;B<ui:input readonly="true" maxlength="3" name="fufm" value="mktfxVO.fufm" /></td>
<th colspan="6" style="text-align: center; vertical-align: middle;">锡附着量 (g/m<sup>2</sup>)</th>
</tr>
<tr>
<th rowspan="3" style="text-align: center; vertical-align: middle;" >S</th>
<th>M-Sn</th>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="xfzlSmt" onchange="calculate('xfzlSmt','xfzlSat','xfzlSst');" /> B<ui:number maxlength="5" scale="4" precision="2" name="xfzlSmb" onchange="calculate('xfzlSmb','xfzlSab','xfzlSsb');" /></td>
<th rowspan="3" style="text-align: center; vertical-align: middle;" >C</th>
<th>M-Sn</th>
<td>F<ui:number name="xfzlCmt" maxlength="5" scale="4" precision="2" onchange="calculate('xfzlCmt','xfzlCat','xfzlCst');" /> B<ui:number maxlength="5" scale="4" precision="2" name="xfzlCmb" onchange="calculate('xfzlCmb','xfzlCab','xfzlCsb');" /></td>
<th rowspan="3" style="text-align: center; vertical-align: middle;" >N</th>
<th>M-Sn</th>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="xfzlNmt" onchange="calculate('xfzlNmt','xfzlNat','xfzlNst');" /> B<ui:number maxlength="5" scale="4" precision="2" name="xfzlNmb" onchange="calculate('xfzlNmb','xfzlNab','xfzlNsb');" /></td>
</tr>
<tr>
<th>Alloy</th>
<td>F<ui:number maxlength="4" scale="3" precision="2" name="xfzlSat" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlSmt','xfzlSat','xfzlSst');" /> B<ui:number maxlength="4" scale="3" precision="2" name="xfzlSab" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlSmb','xfzlSab','xfzlSsb');" /></td>
<th>Alloy</th>
<td>F<ui:number maxlength="4" scale="3" precision="2" name="xfzlCat" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlCmt','xfzlCat','xfzlCst');" /> B<ui:number maxlength="4" scale="3" precision="2" name="xfzlCab" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlCmb','xfzlCab','xfzlCsb');" /></td>
<th>Alloy</th>
<td>F<ui:number maxlength="4" scale="3" precision="2" name="xfzlNat" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlNmt','xfzlNat','xfzlNst');" /> B<ui:number maxlength="4" scale="3" precision="2" name="xfzlNab" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlNmb','xfzlNab','xfzlNsb');" /></td>
</tr>
<tr>
<th>T-Sn</th>
<td>F<ui:number maxlength="5" name="xfzlSst" max="${mktfxVO.fuzs }" min="${mktfxVO.fuzx }" onclick="__showAlt(this,'${mktfxVO.zxbz }')" readonly="true" /> B<ui:number maxlength="5" name="xfzlSsb" max="${mktfxVO.fufs }" min="${mktfxVO.fufx }" onclick="__showAlt(this,'${mktfxVO.fxbz }')" readonly="true" /></td>
<th>T-Sn</th>
<td>F<ui:number maxlength="5" name="xfzlCst" max="${mktfxVO.fuzs }" min="${mktfxVO.fuzx }" onclick="__showAlt(this,'${mktfxVO.zxbz }')" readonly="true" /> B<ui:number maxlength="5" name="xfzlCsb" max="${mktfxVO.fufs }" min="${mktfxVO.fufx }" onclick="__showAlt(this,'${mktfxVO.fxbz }')" readonly="true" /></td>
<th>T-Sn</th>
<td>F<ui:number maxlength="5" name="xfzlNst" max="${mktfxVO.fuzs }" min="${mktfxVO.fuzx }" onclick="__showAlt(this,'${mktfxVO.zxbz }')"  readonly="true"/> B<ui:number maxlength="5" name="xfzlNsb" max="${mktfxVO.fufs }" min="${mktfxVO.fufx }" onclick="__showAlt(this,'${mktfxVO.fxbz }')" readonly="true" /></td>
</tr>
</table>
</div>
<div id="CR" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="40%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >Cr (mg/m<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr>
<td>F<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.crbz }')" name="crSt" /></td>
<td>B<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.crbz }')" name="crSb" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.crbz }')" name="crCt" /></td>
<td>B<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.crbz }')" name="crCb" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.crbz }')" name="crNt" /></td>
<td>B<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.crbz }')" name="crNb" /></td></tr>	
</table>
</div>
<div id="BMLJG" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="52%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >表面六价铬</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr><td>F <sys:codeSelect name="bmljgSt" code="#SINO_BMLJG" headerText="" headerValue="" /></td><td>B <sys:codeSelect name="bmljgSb" code="#SINO_BMLJG" headerText="" headerValue="" /></td><td>F <sys:codeSelect name="bmljgCt" code="#SINO_BMLJG" headerText="" headerValue="" /></td><td>B <sys:codeSelect name="bmljgCb" code="#SINO_BMLJG" headerText="" headerValue="" /></td><td>F <sys:codeSelect name="bmljgNt" code="#SINO_BMLJG" headerText="" headerValue="" /></td><td>B <sys:codeSelect name="bmljgNb" code="#SINO_BMLJG" headerText="" headerValue="" /></td></tr>	
</table>
</div>
<div id="ATC" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="12%" /><col width="12%" /><col width="60%" /></colgroup>
<tr><th colspan="4" style="text-align: center; vertical-align: middle;">ATC (μA/cm<sup>2</sup>)</th><th rowspan="4" >&nbsp;</th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">标准版</th><th style="text-align: center; vertical-align: middle;" rowspan="2" colspan="2">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><th style="text-align: center; vertical-align: middle;">无空烧</th><th style="text-align: center; vertical-align: middle;">有空烧</th></tr>
<tr><td><ui:input maxlength="6" name="atcFsywks" /></td><td><ui:input maxlength="6" name="atcFsyyks" /></td><td>F<ui:number maxlength="6" scale="5" precision="4" max="${mktfxVO.atcs }" min="${mktfxVO.atcx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.atcz }')" name="atcZt" /></td><td>B<ui:number maxlength="6" scale="5" precision="4" max="${mktfxVO.atcs }" min="${mktfxVO.atcx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.atcz }')" name="atcZb" /></td></tr>				
</table>	
</div>
<div id="TCV" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="76%" /></colgroup>
<tr><th colspan="3" style="text-align: center; vertical-align: middle;">TCV (mg/dm<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;">铁标液</th><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td><ui:number maxlength="4" scale="3" precision="2" name="tcvTby" /></td><td>F<ui:number maxlength="4" scale="3" precision="2" name="tcvZt" max="${mktfxVO.tcvzs }" min="${mktfxVO.tcvzx }" onclick="__showAlt(this,'${mktfxVO.tcvzbz }')" onchange="toJudegValue(this);" /></td><td>B<ui:number maxlength="4" scale="3" precision="2" name="tcvZb"  max="${mktfxVO.tcvfs }" min="${mktfxVO.tcvfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tcvfbz }')" /></td></tr>    	
</table>
</div>
<div id="ISV" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">ISV (μg/3in<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td>F<ui:number maxlength="5" scale="4" precision="2" name="isvZt" max="${mktfxVO.isvs }" min="${mktfxVO.isvx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.isvz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="isvZb" max="${mktfxVO.isvs }" min="${mktfxVO.isvx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.isvz }')" /></td></tr>    	
</table>
</div>
<div id="TCS" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">TCS评点</th><th rowspan="3"></th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td>F<ui:number maxlength="3" scale="2" precision="1" name="tcsPdt" max="${mktfxVO.tcss }" min="${mktfxVO.tcsx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tcsbz }')" /></td><td>B<ui:number maxlength="3" scale="2" precision="1" name="tcsPdb" max="${mktfxVO.tcss }" min="${mktfxVO.tcsx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tcsbz }')" /></td></tr>    	
</table>
</div>
<div id="WDSY" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">污点试验</th><th rowspan="3">&nbsp;</th></tr>
<tr><td>F<ui:int maxlength="1" name="wdsyt" max="${mktfxVO.wds }" min="${mktfxVO.wdx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.wdbz }')" /></td><td>B<ui:int maxlength="1" name="wdsyb" max="${mktfxVO.wds }" min="${mktfxVO.wdx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.wdbz }')" /></td></tr>    	
</table>
</div>	
<div id="EPON" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="52%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >Epon Flow</th><th rowspan="3"></th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr><td>F<ui:int maxlength="1" name="eponSt" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>B<ui:int maxlength="1" name="eponSb" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>F<ui:int maxlength="1" name="eponCt" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>B<ui:int maxlength="1" name="eponCb" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>F<ui:int maxlength="1" name="eponNt" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>B<ui:int maxlength="1" name="eponNb" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td></tr>	
</table>
</div>
<div id="PLT" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="90%" /></colgroup>
<tr><th style="text-align: center; vertical-align: middle;">PLT sec</th><th rowspan="3">&nbsp;</th></tr>
<tr><td><ui:number maxlength="5" scale="4" precision="2" name="pltPd" max="${mktfxVO.plts }" min="${mktfxVO.pltx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.ptbz }')" /></td></tr>    	
</table>
</div>
<div id="PORE" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">PORE</th><th rowspan="3">&nbsp;</th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td>F<ui:int maxlength="2" name="porePdt" max="${mktfxVO.pes }" min="${mktfxVO.pex }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.pebz }')" /></td><td>B<ui:int maxlength="2" name="porePdb" max="${mktfxVO.pes }" min="${mktfxVO.pex }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.pebz }')" /></td></tr>    	
</table>
</div>
<div id="TLMZX" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="52%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >涂料密着性</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr><td>F<ui:int maxlength="1" name="tlmzxSt" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td><td>B<ui:int maxlength="1" name="tlmzxSb" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td><td>F<ui:int maxlength="1" name="tlmzxCt" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td><td>B<ui:int maxlength="1" name="tlmzxCb" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td><td>F<ui:int maxlength="1" name="tlmzxNt" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td><td>B<ui:int maxlength="1" name="tlmzxNb" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td></tr>	
</table>
</div>
<div id="CDW" style="display: none;">
</div>
<div class="opt-btm"><input type="button" class="button" value="保   存" onclick="doSave(this.form, this, false);" />  <input type="button" class="button" value="分析完成" onclick="doSave(this.form, this, true);" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('MktfxPanel');" /></div>