<%@ page language="java" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@  taglib uri="/sys" prefix="sys" %><%@ taglib prefix="sino" uri="/sino" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="id" value="${entity.id }" /><input type="hidden" name="zzsd" value="${entity.zzsd }" /><input type="hidden" name="fxxms" value="${fxxms }" /><input type="hidden" name="cyrzId" value="${entity.cyrzId }" /><input type="hidden" name="stat" value="${entity.stat }" />
<table width="100%" align="center" class="form" >
<colgroup><col width="8%" /><col width="10%" /><col width="8%" /><col width="10%" /><col width="8%" /><col width="10%" /><col width="46%" /></colgroup>
<tr>
<th style="text-align: left;">Coil No.</th>
<td><ui:input name="jbno" maxlength="8" required="true" title="Coil No." value="entity.jbno" readonly="true" /></td>
<th>分析日</th>
<td><ui:datetime name="fxr" showCalendar="false" value="entity.fxr" /></td>
<th>部位</th>
<td><sys:codeSelect name="bw" code="#SINO_FXS_BW" prop="nn:1;" headerText="" headerValue="" value="entity.bw" /></td>
<th>&nbsp;</th>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">备注</th>
<td colspan="6"><ui:textarea name="bz" cssStyle="" cssClass="normal" cols="90" rows="5" value="entity.bz" /></td>
</tr>
</table>
<br />
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
<td><ui:input readonly="true" maxlength="3" name="yqty" value="entity.yqty" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="tylSt" value="entity.tylSt" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="tylSb" value="entity.tylSb" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')"  /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="tylCt" value="entity.tylCt" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="tylCb" value="entity.tylCb" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')"  /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="tylNt" value="entity.tylNt" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="tylNb" value="entity.tylNb" max="${mktfxVO.tys }" min="${mktfxVO.tyx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tybz }')"  /></td>
</tr>
</table>
</div>
<div id="XFZL" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<tr>	
<th colspan="2" >目标附着量</th>
<td>F<ui:input readonly="true" maxlength="3" name="fuzm" value="entity.fuzm" />&nbsp;&nbsp;&nbsp;&nbsp;B<ui:input readonly="true" maxlength="3" name="fufm" value="entity.fufm" /></td>
<th colspan="6" style="text-align: center; vertical-align: middle;">锡附着量 (g/m<sup>2</sup>)</th>
</tr>
<tr>
<th rowspan="3" style="text-align: center; vertical-align: middle;" >S</th>
<th>M-Sn</th>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="xfzlSmt" value="entity.xfzlSmt" onchange="calculate('xfzlSmt','xfzlSat','xfzlSst');" /> B<ui:number maxlength="5" scale="4" precision="2" name="xfzlSmb" value="entity.xfzlSmb" onchange="calculate('xfzlSmb','xfzlSab','xfzlSsb');" /></td>
<th rowspan="3" style="text-align: center; vertical-align: middle;" >C</th>
<th>M-Sn</th>
<td>F<ui:number name="xfzlCmt" maxlength="5" scale="4" precision="2" value="entity.xfzlCmt" onchange="calculate('xfzlCmt','xfzlCat','xfzlCst');" /> B<ui:number maxlength="5" scale="4" precision="2" name="xfzlCmb" value="entity.xfzlCmb" onchange="calculate('xfzlCmb','xfzlCab','xfzlCsb');" /></td>
<th rowspan="3" style="text-align: center; vertical-align: middle;" >N</th>
<th>M-Sn</th>
<td>F<ui:number maxlength="5" scale="4" precision="2" name="xfzlNmt" value="entity.xfzlNmt" onchange="calculate('xfzlNmt','xfzlNat','xfzlNst');" /> B<ui:number maxlength="5" scale="4" precision="2" name="xfzlNmb" value="entity.xfzlNmb" onchange="calculate('xfzlNmb','xfzlNab','xfzlNsb');" /></td>
</tr>
<tr>
<th>Alloy</th>
<td>F<ui:number maxlength="4" scale="3" precision="2" name="xfzlSat" value="entity.xfzlSat" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlSmt','xfzlSat','xfzlSst');" /> B<ui:number maxlength="4" scale="3" precision="2" name="xfzlSab" value="entity.xfzlSab" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlSmb','xfzlSab','xfzlSsb');" /></td>
<th>Alloy</th>
<td>F<ui:number maxlength="4" scale="3" precision="2" name="xfzlCat" value="entity.xfzlCat" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlCmt','xfzlCat','xfzlCst');" /> B<ui:number maxlength="4" scale="3" precision="2" name="xfzlCab" value="entity.xfzlCab" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlCmb','xfzlCab','xfzlCsb');" /></td>
<th>Alloy</th>
<td>F<ui:number maxlength="4" scale="3" precision="2" name="xfzlNat" value="entity.xfzlNat" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlNmt','xfzlNat','xfzlNst');" /> B<ui:number maxlength="4" scale="3" precision="2" name="xfzlNab" value="entity.xfzlNab" min="${mktfxVO.hjin }" onclick="__showAlt(this,'${mktfxVO.hjbz }')" onchange="calculate('xfzlNmb','xfzlNab','xfzlNsb');" /></td>
</tr>
<tr>
<th>T-Sn</th>
<td>F<ui:number maxlength="5" name="xfzlSst" value="entity.xfzlSst" max="${mktfxVO.fuzs }" min="${mktfxVO.fuzx }" onclick="__showAlt(this,'${mktfxVO.zxbz }')" readonly="true" /> B<ui:number maxlength="5" name="xfzlSsb" value="entity.xfzlSsb" max="${mktfxVO.fufs }" min="${mktfxVO.fufx }" onclick="__showAlt(this,'${mktfxVO.fxbz }')" readonly="true" /></td>
<th>T-Sn</th>
<td>F<ui:number maxlength="5" name="xfzlCst" value="entity.xfzlCst" max="${mktfxVO.fuzs }" min="${mktfxVO.fuzx }" onclick="__showAlt(this,'${mktfxVO.zxbz }')" readonly="true" /> B<ui:number maxlength="5" name="xfzlCsb" value="entity.xfzlCsb" max="${mktfxVO.fufs }" min="${mktfxVO.fufx }" onclick="__showAlt(this,'${mktfxVO.fxbz }')" readonly="true" /></td>
<th>T-Sn</th>
<td>F<ui:number maxlength="5" name="xfzlNst" value="entity.xfzlNst" max="${mktfxVO.fuzs }" min="${mktfxVO.fuzx }" onclick="__showAlt(this,'${mktfxVO.zxbz }')"  readonly="true"/> B<ui:number maxlength="5" name="xfzlNsb" value="entity.xfzlNsb" max="${mktfxVO.fufs }" min="${mktfxVO.fufx }" onclick="__showAlt(this,'${mktfxVO.fxbz }')" readonly="true" /></td>
</tr>
</table>
</div>
<div id="CR" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="40%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >Cr (mg/m<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr>
<td>F<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onclick="__showAlt(this,'${mktfxVO.crbz }')" onchange="toJudegValue(this);" name="crSt" value="entity.crSt" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onclick="__showAlt(this,'${mktfxVO.crbz }')" onchange="toJudegValue(this);" name="crSb" value="entity.crSb" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onclick="__showAlt(this,'${mktfxVO.crbz }')" onchange="toJudegValue(this);" name="crCt" value="entity.crCt" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onclick="__showAlt(this,'${mktfxVO.crbz }')" onchange="toJudegValue(this);" name="crCb" value="entity.crCb" /></td>
<td>F<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onclick="__showAlt(this,'${mktfxVO.crbz }')" onchange="toJudegValue(this);" name="crNt" value="entity.crNt" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" max="${mktfxVO.crs }" min="${mktfxVO.crx }" onclick="__showAlt(this,'${mktfxVO.crbz }')" onchange="toJudegValue(this);" name="crNb" value="entity.crNb" /></td></tr>	
</table>
</div>
<div id="BMLJG" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="52%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >表面六价铬</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr>
<td>F <sys:codeSelect name="bmljgSt" code="#SINO_BMLJG" headerText="" headerValue="" value="entity.bmljgSt" /></td><td>B <sys:codeSelect name="bmljgSb" code="#SINO_BMLJG" headerText="" headerValue="" value="entity.bmljgSb" /></td>
<td>F <sys:codeSelect name="bmljgCt" code="#SINO_BMLJG" headerText="" headerValue="" value="entity.bmljgCt" /></td><td>B <sys:codeSelect name="bmljgCb" code="#SINO_BMLJG" headerText="" headerValue="" value="entity.bmljgCb" /></td>
<td>F <sys:codeSelect name="bmljgNt" code="#SINO_BMLJG" headerText="" headerValue="" value="entity.bmljgNt" /></td><td>B <sys:codeSelect name="bmljgNb" code="#SINO_BMLJG" headerText="" headerValue="" value="entity.bmljgNb" /></td>
</tr>	
</table>
</div>
<div id="ATC" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="12%" /><col width="12%" /><col width="60%" /></colgroup>
<tr><th colspan="4" style="text-align: center; vertical-align: middle;">ATC (μA/cm<sup>2</sup>)</th><th rowspan="4" >&nbsp;</th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">标准版</th><th style="text-align: center; vertical-align: middle;" rowspan="2" colspan="2">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><th style="text-align: center; vertical-align: middle;">无空烧</th><th style="text-align: center; vertical-align: middle;">有空烧</th></tr>
<tr><td><ui:input maxlength="6" name="atcFsywks" value="entity.atcFsywks" /></td><td><ui:input maxlength="6" name="atcFsyyks" value="entity.atcFsyyks" /></td><td>F<ui:number maxlength="6" scale="5" precision="4" name="atcZt" max="${mktfxVO.atcs }" min="${mktfxVO.atcx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.atcz }')" value="entity.atcZt" /></td><td>B<ui:number maxlength="6" scale="5" precision="4" name="atcZb" max="${mktfxVO.atcs }" min="${mktfxVO.atcx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.atcz }')" value="entity.atcZb" /></td></tr>				
</table>	
</div>
<div id="TCV" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="76%" /></colgroup>
<tr><th colspan="3" style="text-align: center; vertical-align: middle;">TCV (mg/dm<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;">铁标液</th><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td><ui:number maxlength="4" scale="3" precision="2" name="tcvTby" value="entity.tcvTby" /></td><td>F<ui:number maxlength="4" scale="3" precision="2" name="tcvZt" max="${mktfxVO.tcvzs }" min="${mktfxVO.tcvzx }" onclick="__showAlt(this,'${mktfxVO.tcvzbz }')" onchange="toJudegValue(this);"  value="entity.tcvZt"/></td><td>B<ui:number maxlength="4" scale="3" precision="2" name="tcvZb" max="${mktfxVO.tcvfs }" min="${mktfxVO.tcvfx }" onclick="__showAlt(this,'${mktfxVO.tcvfbz }')" onchange="toJudegValue(this);"  value="entity.tcvZb"/></td></tr>    	
</table>
</div>
<div id="ISV" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">ISV (μg/3in<sup>2</sup>)</th><th rowspan="3">&nbsp;</th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td>F<ui:number maxlength="5" scale="4" precision="2" name="isvZt" max="${mktfxVO.isvs }" min="${mktfxVO.isvx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.isvz }')" value="entity.isvZt" /></td><td>B<ui:number maxlength="5" scale="4" precision="2" name="isvZb" max="${mktfxVO.isvs }" min="${mktfxVO.isvx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.isvz }')" value="entity.isvZb" /></td></tr>    	
</table>
</div>
<div id="TCS" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">TCS评点</th><th rowspan="3"></th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td>F<ui:number maxlength="3" scale="2" precision="1" name="tcsPdt" value="entity.tcsPdt" max="${mktfxVO.tcss }" min="${mktfxVO.tcsx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tcsbz }')" /></td><td>B<ui:number maxlength="3" scale="2" precision="1" name="tcsPdb" value="entity.tcsPdb" max="${mktfxVO.tcss }" min="${mktfxVO.tcsx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.tcsbz }')" /></td></tr>    	
</table>
</div>
<div id="WDSY" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">污点试验</th><th rowspan="3">&nbsp;</th></tr>
<tr><td>F<ui:int maxlength="1" name="wdsyt" value="entity.wdsyt" max="${mktfxVO.wds }" min="${mktfxVO.wdx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.wdbz }')" /></td><td>B<ui:int maxlength="1" name="wdsyb" value="entity.wdsyb" max="${mktfxVO.wds }" min="${mktfxVO.wdx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.wdbz }')" /></td></tr>    	
</table>
</div>	
<div id="EPON" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="52%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >Epon Flow</th><th rowspan="3"></th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr><td>F<ui:int maxlength="1" name="eponSt" value="entity.eponSt" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>B<ui:int maxlength="1" name="eponSb" value="entity.eponSb" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>F<ui:int maxlength="1" name="eponCt" value="entity.eponCt" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td><td>B<ui:int maxlength="1" name="eponCb" value="entity.eponCb" /></td><td>F<ui:int maxlength="1" name="eponNt" value="entity.eponNt" /></td><td>B<ui:int maxlength="1" name="eponNb" value="entity.eponNb" max="${mktfxVO.epfs }" min="${mktfxVO.epfx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.epbz }')" /></td></tr>	
</table>
</div>
<div id="PLT" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="90%" /></colgroup>
<tr><th style="text-align: center; vertical-align: middle;">PLT sec</th><th rowspan="3">&nbsp;</th></tr>
<tr><td><ui:number maxlength="5" scale="4" precision="2" name="pltPd" value="entity.pltPd" max="${mktfxVO.plts }" min="${mktfxVO.pltx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.ptbz }')" /></td></tr>    	
</table>
</div>
<div id="PORE" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="10%" /><col width="10%" /><col width="80%" /></colgroup>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">PORE</th><th rowspan="3"></th></tr>
<tr><th colspan="2" style="text-align: center; vertical-align: middle;">${mktfxVO.fuzm }/${mktfxVO.fufm }</th></tr>
<tr><td>F<ui:int maxlength="2" name="porePdt" value="entity.porePdt" max="${mktfxVO.pes }" min="${mktfxVO.pex }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.pebz }')" /></td><td>B<ui:int maxlength="2" name="porePdb" value="entity.porePdb" max="${mktfxVO.pes }" min="${mktfxVO.pex }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.pebz }')"  /></td></tr>    	
</table>
</div>
<div id="TLMZX" style="display: none;">
<table width="96%" align="center" class="form" border="1" bordercolor="#ffffff" style="border-collapse: collapse;">
<colgroup><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="52%" /></colgroup>
<tr><th colspan="6" style="text-align: center; vertical-align: middle;" >涂料密着性</th><th rowspan="3">&nbsp;</th></tr>
<tr><th style="text-align: center; vertical-align: middle;" colspan="2">S</th><th style="text-align: center; vertical-align: middle;" colspan="2">C</th><th style="text-align: center; vertical-align: middle;" colspan="2">N</th></tr>
<tr><td>F<ui:int maxlength="1" name="tlmzxSt" value="entity.tlmzxSt" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" /></td><td>B<ui:int maxlength="1" name="tlmzxSb" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" value="entity.tlmzxSb" /></td><td>F<ui:int maxlength="1" name="tlmzxCt" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" value="entity.tlmzxCt" /></td><td>B<ui:int maxlength="1" name="tlmzxCb" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" value="entity.tlmzxCb" /></td><td>F<ui:int maxlength="1" name="tlmzxNt" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" value="entity.tlmzxNt" /></td><td>B<ui:int maxlength="1" name="tlmzxNb" max="${mktfxVO.mzxs }" min="${mktfxVO.mzxx }" onchange="toJudegValue(this);" onclick="__showAlt(this,'${mktfxVO.mzbz }')" value="entity.tlmzxNb" /></td></tr>	
</table>
</div>
<div id="CDW" style="display: none;">
</div>
<div class="opt-btm"><c:if test="${entity.stat == \"0\" }" ><input type="button" class="button" value="保   存" onclick="doSave(this.form, this, false);" />  </c:if><input type="button" class="button" value="分析完成" onclick="doSave(this.form, this, true);" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('MktfxPanel');" /></div>