<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintView">
	<table border="0" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse: collapse;">
		<tr>
			<td>
				<table width="1256" cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" style="border-collapse: collapse; text-align: center;">
				<colgroup><col width="98" /><col width="8" /><col width="105" /><col width="157" /><col width="83" />
					<col width="137" /><col width="120" /><col width="175" /><col width="55" />
					<col width="60" /><col width="50" /><col width="7" /><col width="68" /><col width="75" /><col width="58" /></colgroup>
					<tr>
						<td height="30">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td colspan="6">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					</tr>
					<tr>
						<td height="30" colspan="2" >&nbsp;</td>
						<td>&nbsp;</td>
						<td>内外销</td>
						<td rowspan="2" colspan="8">&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td height="35" colspan="2" >${fxr }</td>
						<td><c:if test="${tp.dhqr == \"1\" }">订</c:if></td>
						<td>${tp.nwai }</td>
						<td>${tp.ddno }</td>
						<td colspan="2">${tp.ddnm }</td>
					</tr>
					<tr>
						<td height="30" >&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="45" >${tp.dhno }</td>
						<td colspan="2">${tp.user }</td>
						<td>${tp.name }</td>
						<td>${tp.ssno }</td>
						<td colspan="2">${tp.ssnm }</td>
						<td>${tp.abbr }</td>
						<td>${tp.jhnr }</td>
						<td>${tp.ysfs }</td>
						<td>${tp.jcha }</td>
						<td colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<c:forEach varStatus="stat" var="item" items="${list }">
		<tr>
			<td>
				<table width="1256" cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" style="border-collapse: collapse; text-align: center;">
				<colgroup>
					<col width="30" /><col width="50" /><col width="8" /><col width="40" /><col width="45" /><col width="59" /><col width="8" />
					<col width="30" /><col width="8" /><col width="20" /><col width="34" /><col width="40" /><col width="40" /><col width="9" />
					<col width="48" /><col width="10" /><col width="26" /><col width="30" /><col width="30" /><col width="5" /><col width="40" />
					<col width="38" /><col width="8" /><col width="52" /><col width="37" /><col width="50" /><col width="64" /><col width="16" />
					<col width="8" /><col width="20" /><col width="70" /><col width="50" /><col width="15" /><col width="29" /><col width="48" /><col width="30" />
					<col width="34" /><col width="20" /><col width="55" />
				</colgroup>
					<tr>
						<td height="39">&nbsp;</td><td>重内</td><td>${item.jhnr }</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					</tr>
					<tr>
						<td rowspan="2">&nbsp;</td>
						<td height="17" colspan="5">&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="8">&nbsp;</td>
						<td rowspan="2" colspan="3">&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td colspan="6">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td height="17">&nbsp;</td>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td rowspan="6">${item.line }</td>
						<td height="20">&nbsp;</td>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td colspan="3">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td style="font-size: 11px; vertical-align: bottom;"><c:if test="${item.kbzx != null && item.kbzx > 0 }" >MIN</c:if></td>
						<td style="font-size: 11px; vertical-align: bottom;" colspan="2"><c:if test="${item.kbzs != null && item.kbzs > 0 }" >MAX</c:if></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td style="font-size: 11px; vertical-align: bottom;" colspan="2"><c:if test="${item.jhfz != null }">下</c:if></td>
						<td style="font-size: 11px; vertical-align: bottom;"><c:if test="${item.jhzz != null }">上</c:if></td>
					</tr>
					<tr>
						<td height="26">${item.ggno }</td>
						<td colspan="4">${item.ggnm }</td>
						<td colspan="2">${item.pzno }</td>
						<td colspan="2">${item.fudw }</td>
						<td>${item.fuzm }</td>
						<td>${item.fufm }</td>
						<td colspan="2">${item.chdx }</td>
						<td colspan="2">${item.houu }</td>
						<td colspan="3">${item.kuan }</td>
						<td colspan="3">${item.cang }</td>
						<td colspan="3">${item.htzl }</td>
						<td colspan="2"><f:v value="item.jhqi" format="yyyy-MM-dd"/></td>
						<td colspan="2">${item.kbzd }</td>
						<td>${item.kbzq }</td>
						<td>${item.kbzx }</td>
						<td colspan="2">${item.kbzs }</td>
						<td>${item.kbsq }</td>
						<td>${item.kbsz }</td>
						<td>${item.jhqf }</td>
						<td colspan="2">${item.jhfz }</td>
						<td>${item.jhzz }</td>
					</tr>
					<tr>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td height="14">&nbsp;</td>
						<td>&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td rowspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
						<td rowspan="2" colspan="4">&nbsp;</td>
						<td rowspan="2" colspan="5">&nbsp;</td>
						<td rowspan="2" colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td height="14">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="20" colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td style="font-size: 11px; vertical-align: bottom;" colspan="2">${item.cdnm }</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
						<td style="font-size: 11px; vertical-align: bottom;" colspan="2"><c:choose><c:when test="${item.thqf != null && item.thqf == \"1\" }">人民币</c:when><c:when test="${item.thqf != null && item.thqf == \"3\" }">港币</c:when><c:otherwise></c:otherwise></c:choose></td>
						<td colspan="2"><c:if test="${item.thqf != null && item.thqf == \"2\" }">美元</c:if></td>
						<td colspan="4">&nbsp;</td>
						<td style="font-size: 11px; vertical-align: bottom;" colspan="2">预</td>
						<td style="font-size: 11px; vertical-align: bottom;">出</td>
						<td style="font-size: 11px; vertical-align: bottom;">后</td>
						<td style="font-size: 11px; vertical-align: bottom;">期</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td height="24" colspan="2">${item.jcxs }</td>
						<td>${item.yyan }</td>
						<td>${item.face }</td>
						<td colspan="2">${item.cond }</td>
						<td colspan="2">${item.neij }</td>
						<td colspan="2">${item.huac }</td>
						<td>${item.rjie }</td>
						<td>${item.dmfx }</td>
						<td colspan="2">${item.kbao }</td>
						<td colspan="2">${item.plat }</td>
						<td>${item.ytyp }</td>
						<td colspan="2">${item.yqty }</td>
						<td>&nbsp;</td>
						<td colspan="2">${item.tbno }</td>
						<td>${item.tcno }</td>
						<td colspan="2"><c:choose><c:when test="${item.thqf != null && item.thqf == \"1\" }">${item.htdj }</c:when><c:when test="${item.thqf != null && item.thqf == \"3\" }">${item.htdj }</c:when><c:otherwise></c:otherwise></c:choose></td>
						<td colspan="2"><c:if test="${item.thqf != null && item.thqf == \"2\" }">${item.htdj }</c:if></td>
						<td colspan="4">${item.htje }</td>
						<td colspan="2">${item.yfkn }</td>
						<td>${item.chkn }</td>
						<td>${item.hfkn }</td>
						<td>${item.qixn }</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
