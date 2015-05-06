<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib uri="/sino" prefix="sino"%>
<div id="PrintView" style="font-size:12px;">
<table cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" style="border-collapse: collapse;">
	<colgroup><col width="15" /><col width="70" /><col width="420" /><col width="255" /><col width="120" />
	<col width="130" /><col width="240" /><col width="10" /></colgroup>
	<tr>
		<td height="40" rowspan="10">&nbsp;</td>
		<td colspan="2" rowspan="2">&nbsp;</td>
		<td style="font-size: 37px; font-weight: bold; font-family: 黑体;" align="left">&nbsp;</td>
		<td rowspan="2">&nbsp;</td>
		<td colspan="2">&nbsp;</td>
		<td rowspan="10">&nbsp;</td>
	</tr>
	<tr>
		<td style="font-size: 15px; font-weight: bold;">&nbsp;</td>
		<td style="font-size: 12px; font-weight: bold;" colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td height="12" align="center" style="font-size: 16px;"></td>
		<td rowspan="2" >&nbsp;${jczms.user } &nbsp; ${jczms.name }</td>
		<td colspan="2" rowspan="8" <c:if test="${jczms.zwbz != null || jczms.ywbz != null }"> style="border: 1px solid #000000;text-align: center; font-size: 14px; font-family: 'Courier';"</c:if>>${jczms.zwbz }<br />${jczms.ywbz }</td>
		<td style="font-size: 11px; font-weight: bold;" colspan="2"></td>
	</tr>
	<tr>
		<td height="11" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td colspan="2" style="padding-left: 73px; font-size: 12px; font-weight: bold;" valign="bottom"></td>
	</tr>
	<tr>
		<td height="15" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td rowspan="2" >&nbsp;${jczms.ssno }&nbsp;${jczms.ssmc }</td>
		<td style="font-size: 16px;" valign="bottom"></td>
		<td rowspan="2" >&nbsp;${jczms.jcno }</td>
	</tr>
	<tr>
		<td height="17" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td style="font-size: 16px;" valign="bottom"></td>
	</tr>
	<tr>
		<td height="18" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td rowspan="2" >&nbsp;${jczms.pm }</td>
		<td style="font-size: 16px;" valign="bottom" ></td>
		<td rowspan="2" >&nbsp;<f:v value="jczms.zzri" format="yyyy/MM/dd"/></td>
	</tr>
	<tr>
		<td height="15" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td style="font-size: 16px;" valign="bottom"></td>
	</tr>
	<tr>
		<td height="15" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td rowspan="2" >&nbsp;${jczms.ggnm } &nbsp;COATING WEIGHT WB &nbsp; ${jczms.fuzm }-${jczms.fufm }</td>
		<td style="font-size: 16px;" valign="bottom"></td>
		<td rowspan="2" >&nbsp;${jczms.dhno }-${jczms.line }</td>
	</tr>
	<tr>
		<td height="17" align="center" style="font-size: 16px;" valign="bottom"></td>
		<td style="font-size: 16px;" valign="bottom"></td>
	</tr>
	<tr>
		<td height="14" colspan="8"></td>
	</tr>
	<tr>
		<td colspan="8">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" style="border-collapse: collapse; text-align: center; font-size: 13px; font-weight: bold; font-family: monospace;">
				<colgroup>
					<col width="105" /><col width="290" /><col width="24" /><col width="34" /><col width="25" /><col width="30" />
					<col width="30" /><col width="30" /><col width="27" /><col width="27" /><col width="27" /><col width="38" />
					<col width="32" /><col width="39" /><col width="38" /><col width="48" /><col width="50" /><col width="52" />
					<col width="37" /><col width="70" /><col width="67" /><col width="67" /><col width="72" />
				</colgroup>
				<tr>
					<td height="30" align="right" style="border-bottom: 0px; padding-right: 5px; padding-top: 5px;">&nbsp;</td>
					<td style="border-bottom: 0px;">&nbsp;</td>
					<td colspan="2" style="border-bottom: 0px;">&nbsp;</td>
					<td colspan="2" style="border-bottom: 0px;">&nbsp;</td>
					<td colspan="10" >&nbsp;</td>
					<td colspan="3" >&nbsp;</td>
					<td style="border-bottom: 0px;">&nbsp;</td>
					<td style="border-bottom: 0px;">&nbsp;</td>
					<td rowspan="3">&nbsp;</td>
					<td rowspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td rowspan="2" align="left" style="border-top: 0px; padding-left: 5px;">&nbsp;</td>
					<td rowspan="2" style="border-top: 0px;">&nbsp;</td>
					<td rowspan="2" colspan="2" style="border-top: 0px;">&nbsp;</td>
					<td rowspan="2" colspan="2" style="border-top: 0px;">&nbsp;</td>
					<td height="18" >&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td rowspan="2" >&nbsp;</td>
					<td rowspan="2" >&nbsp;</td>
					<td rowspan="2" >&nbsp;</td>
					<td rowspan="2" style="border-top: 0px;">&nbsp;</td>
					<td rowspan="2" style="border-top: 0px;">&nbsp;</td>
				</tr>
				<tr>
					<td height="18" colspan="3" >&nbsp;</td>
					<td colspan="2" >&nbsp;</td>
					<td colspan="4" >&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach varStatus="stat" var="item" items="${page.datas }">
				<c:set var="count" value="${stat.count }" scope="page" />
				<tr>
					<td style="text-align: center; vertical-align: bottom;">${item.zpno}</td>
					<td rowspan="2"><f:v value="item.cpho" format="3" />  ×  <f:v value="item.cpkn" format="2" />w  ×  <c:choose><c:when test="${item.cpcn != null && item.cpcn > 0}"><f:v value="item.cpcn" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
					<td></td>
					<td>${item.jssu }</td>
					<td colspan="2" rowspan="2" align="right" style="padding-right: 6px;">${item.jszl }</td>
					<td rowspan="2" style="text-align: right;"><c:choose><c:when test="${item.cfcc != null && item.cfcc > 1}"><f:v value="item.cfcc" format="-1" /></c:when><c:otherwise>${item.cfcc }</c:otherwise></c:choose></td>
					<td rowspan="2">${item.cfsi }</td>
					<td rowspan="2">${item.cfmn }</td>
					<td rowspan="2">${item.cfpp }</td>
					<td rowspan="2">${item.cfss }</td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td height="16">${item.sczm }</td>
					<td rowspan="2">${item.ying }<c:if test="${item.cfy1 == null || item.cfy1 != \"1\" }" ><sino:ying ying="${item.ying * 10 }" ymax="${jczms.ymax }" ymin="${jczms.ymin }" label1="*" label2="" /></c:if></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
				</tr>
				<tr>
					<td style="text-align: left; vertical-align: bottom; padding-left: 5px;">${item.chui}</td>
					<td colspan="2" align="right" style="padding-right: 8px;"><c:choose><c:when test="${item.bzzs != null && item.bzzs > 0}">${item.bzzs }</c:when><c:otherwise></c:otherwise></c:choose></td>
					<td height="16" style="border-top: 0px;">${item.scfm }</td>
				</tr>
				</c:forEach>
				<c:if test="${count < pageSize + 1 }">
				<c:forEach begin="${count + 1 }" var="index" end="${pageSize + 1}">
				<c:choose><c:when test="${index == count + 1 }">
			    	<tr>
			    	<td rowspan="2"></td>
				 	<c:choose><c:when test="${current + 1 == pages }">
						<td rowspan="2" align="center">合计</td>
						<td></td>
						<td align="center">${chsu }</td>
						<td colspan="2" rowspan="2" align="right" style="padding-right: 6px;">${chzl }</td>
				    </c:when><c:otherwise>
						<td rowspan="2" align="center"></td>
						<td></td>
						<td align="center"></td>
						<td rowspan="2" align="center"></td>
						<td rowspan="2" align="center"></td>
				    </c:otherwise></c:choose>
				    <td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td height="16"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
					<td rowspan="2"></td>
				    </tr>
				    <tr>
				    	<td colspan="2" align="right" style="padding-right: 8px;"><c:choose><c:when test="${jczms.bzzs != null && jczms.bzzs > 0}">${jczms.bzzs }</c:when><c:otherwise></c:otherwise></c:choose></td>
						<td height="16" style="border-top: 0px;"></td>
					</tr>
			    	</c:when>
			    	<c:otherwise>
			    	<tr>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td height="15"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
						<td rowspan="2"></td>
					</tr>
					<tr>
						<td height="16" style="border-top: 0px;"></td>
					</tr>
			    	</c:otherwise></c:choose>
				</c:forEach> 
				</c:if>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10" colspan="8" style="text-align: right; vertical-align: bottom; padding-right: 50px;">${jczms.jcts }</td>
	</tr>
</table>
</div>