<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
    taglib uri="/f" prefix="f" %><%@ 
    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%
    String root = request.getContextPath();
%><table no="${item.zpno };${item.dhno }${item.dhline }" align="center"  cellspacing="0" cellpadding="0" border="0" >
	<colgroup><col width="650" /><col width="40" /><col width="310" /></colgroup>
	<tr>
		<td height="350px" style="border: 0px solid #000000; vertical-align: top;">
		<table width="100%" cellspacing="0" cellpadding="2" border="0" bordercolor="#000000" style="border-collapse: collapse; vertical-align: top;">
			<colgroup>
				<col width="25%" />
				<col width="22%" />
				<col width="25%" />
				<col width="28%" />
			</colgroup>
			<tr>
				<td height="42px" colspan="4" style="font-size: 14px;">
					 <span style="visibility: hidden;">用户</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.user }</span>
				</td>
			</tr>
			<tr>
				<td height="40px" style="border-right: 0px; font-size: 14px;" colspan="2">
					<span style="visibility: hidden;">商品名</span> <span style="font-size: 24px; font-weight: bold;" >${item.spm }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px; ">
					<span style="visibility: hidden;">等级</span>&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.pzdj }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px; ">
					<span style="visibility: hidden;">Package No.</span>&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.packageNo }</span>
				</td>
			</tr>
			<tr>
				<td height="40px" colspan="2" style="border-right: 0px; font-size: 14px; ">
					<span style="visibility: hidden;">规格</span> &nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.ggnm }</span>
				</td>
				<td colspan="2" style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">镀锡量 </span>&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.dxl }</span>
				</td>
			</tr>
			<tr height="30px">
				<td height="40px" colspan="2" style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">尺寸</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 22px; font-weight: bold;" ><f:v value="item.xpho" format="3" />×&nbsp;<f:v value="item.xpkn" format="2" />w×&nbsp;<c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}"><f:v value="item.xpcn" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></span>
				</td>
				<td colspan="2" style="font-size: 14px;">
					<span style="visibility: hidden;">表面精加工</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" >${item.faceK }   <c:if test="${item.face != null }">(${item.face })</c:if></span>
				</td>
			</tr>
			<tr>
				<td height="40px" style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">质量KGS(N/W)</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" >${item.zl }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">G/W</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" ></span>
				</td>
				<td style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">长(批量)</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" >${item.cang }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">制造年月日</span> &nbsp;<span style="border-left: 0px; font-size: 19px; font-weight: bold;" >${item.date }</span>
				</td>
			</tr>
			<tr>
				<td height="75px" colspan="3" valign="top" style="border-right: 0px;">
					<div style="height: 15px; font-size: 14px; visibility: hidden;">制品号</div>
					<div style="font-size: 40px; font-weight: bold;">${item.zpno }</div>
				</td>
				<td valign="top" style="font-size: 14px;" >
					<div style="height: 15px; font-size: 14px; visibility: hidden;">订货号</div>
					<div style="font-size: 30px; font-weight: bold;" ><c:if test="${item.dhno != null }">${item.dhno } - </c:if>${item.dhline }</div>
				</td>
			</tr>
			<tr>
				<td height="70px" colspan="4" style="visibility: hidden;">
					<img alt="logo" src="<%=root%>/images/main/sino.jpg" border="0" height="50px" >
				</td>
			</tr>
		</table>
		</td>
		<td>&nbsp;</td>
		<td style="border: 0px solid #000000; vertical-align: top;">
			<table height="100%" width="100%" cellspacing="0" cellpadding="1" border="0" bordercolor="#000000" style="border-collapse: collapse; vertical-align: top;">
				<colgroup>
					<col width="30%" />
					<col width="17%" />
					<col width="30%" />
					<col width="23%" />
				</colgroup>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">用户</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.abbr }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">规格</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.ggnm }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">镀锡量</td>
					<td style="border-left: 0px; font-size: 19px; font-weight: bold;" >${item.dxl }</td>
					<td style="border-right: 0px; font-size: 14px; visibility: hidden;">表面精加工</td>
					<td style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.faceK }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">尺寸</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.size }</td>
				</tr>
				<tr>
					<td height="30px" colspan="2" style="font-size: 14px;">
					<span style="visibility: hidden;">质量KGS(N/W)</span>&nbsp;<span style="font-size: 19px; font-weight: bold;" >${item.zl }</span>
					</td>
					<td style="border-right: 0px; font-size: 14px; visibility: hidden;">长(批量)</td>
					<td style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.cang }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">捆包型式</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.kbao }</td>
				</tr>
				<tr>
					<td height="70" colspan="4" valign="top" >
						<div style="height: 15px; font-size: 14px;"><span style="visibility: hidden;">制品号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 19px; font-weight: bold;">${item.zpno }</span></div>
					</td>
				</tr>
				<tr>
					<td height="70" colspan="4" valign="top" >
						<div style="height: 15px; font-size: 14px;"><span style="visibility: hidden;">订货号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 19px; font-weight: bold;"><c:if test="${item.dhno != null }">${item.dhno } - </c:if>${item.dhline }</span></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="3" height="40px" >&nbsp;</td></tr>
	<tr>
		<td height="350px" style="border: 0px solid #000000; vertical-align: top;">
		<table width="100%" cellspacing="0" cellpadding="2" border="0" bordercolor="#000000" style="border-collapse: collapse; vertical-align: top;">
			<colgroup>
				<col width="25%" />
				<col width="22%" />
				<col width="25%" />
				<col width="28%" />
			</colgroup>
			<tr>
				<td height="42px" colspan="4" style="font-size: 14px;">
					<span style="visibility: hidden;">用户</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.user }</span>
				</td>
			</tr>
			<tr>
				<td height="40px" style="border-right: 0px; font-size: 14px;" colspan="2">
					<span style="visibility: hidden;">商品名</span> <span style="font-size: 24px; font-weight: bold;" >${item.spm }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px; ">
					<span style="visibility: hidden;">等级</span>&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.pzdj }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px; ">
					<span style="visibility: hidden;">Package No.</span>&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.packageNo }</span>
				</td>
			</tr>
			<tr>
				<td height="40px" colspan="2" style="border-right: 0px; font-size: 14px; ">
					<span style="visibility: hidden;">规格</span> &nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.ggnm }</span>
				</td>
				<td colspan="2" style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">镀锡量 </span>&nbsp;&nbsp;<span style="font-size: 24px; font-weight: bold;" >${item.dxl }</span>
				</td>
			</tr>
			<tr height="30px">
				<td height="40px" colspan="2" style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">尺寸</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 22px; font-weight: bold;" ><f:v value="item.xpho" format="3" />×&nbsp;<f:v value="item.xpkn" format="2" />w×&nbsp;<c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}"><f:v value="item.xpcn" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></span>
				</td>
				<td colspan="2" style="font-size: 14px;">
					<span style="visibility: hidden;">表面精加工</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" >${item.faceK }   <c:if test="${item.face != null }">(${item.face })</c:if></span>
				</td>
			</tr>
			<tr>
				<td height="40px" style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">质量KGS(N/W)</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" >${item.zl }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">G/W</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" ></span>
				</td>
				<td style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">长(批量)</span>&nbsp;&nbsp;<span style="border-left: 0px; font-size: 24px; font-weight: bold;" >${item.cang }</span>
				</td>
				<td style="border-right: 0px; font-size: 14px;">
					<span style="visibility: hidden;">制造年月日</span> &nbsp;<span style="border-left: 0px; font-size: 19px; font-weight: bold;" >${item.date }</span>
				</td>
			</tr>
			<tr>
				<td height="75px" colspan="3" valign="top" style="border-right: 0px;">
					<div style="height: 15px; font-size: 14px; visibility: hidden;">制品号</div>
					<div style="font-size: 40px; font-weight: bold;">${item.zpno }</div>
				</td>
				<td valign="top" style="font-size: 14px;" >
					<div style="height: 15px; font-size: 14px; visibility: hidden;">订货号</div>
					<div style="font-size: 30px; font-weight: bold;" ><c:if test="${item.dhno != null }">${item.dhno } - </c:if>${item.dhline }</div>
				</td>
			</tr>
			<tr>
				<td height="70px" colspan="4" style="visibility: hidden;">
					<img alt="logo" src="<%=root%>/images/main/sino.jpg" border="0" height="50px" >
				</td>
			</tr>
		</table>
		</td>
		<td>&nbsp;</td>
		<td style="border: 0px solid #000000; vertical-align: top;">
			<table height="100%" width="100%" cellspacing="0" cellpadding="1" border="0" bordercolor="#000000" style="border-collapse: collapse; vertical-align: top;">
				<colgroup>
					<col width="30%" />
					<col width="17%" />
					<col width="30%" />
					<col width="23%" />
				</colgroup>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">用户</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.abbr }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">规格</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.ggnm }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">镀锡量</td>
					<td style="border-left: 0px; font-size: 19px; font-weight: bold;" >${item.dxl }</td>
					<td style="border-right: 0px; font-size: 14px; visibility: hidden;">表面精加工</td>
					<td style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.faceK }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">尺寸</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.size }</td>
				</tr>
				<tr>
					<td height="30px" colspan="2" style="font-size: 14px;">
					<span style="visibility: hidden;">质量KGS(N/W)</span>&nbsp;<span style="font-size: 19px; font-weight: bold;" >${item.zl }</span>
					</td>
					<td style="border-right: 0px; font-size: 14px; visibility: hidden;">长(批量)</td>
					<td style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.cang }</td>
				</tr>
				<tr>
					<td height="30px" style="border-right: 0px; font-size: 14px; visibility: hidden;">捆包型式</td>
					<td colspan="3" style="border-left: 0px;font-size: 19px; font-weight: bold;" >${item.kbao }</td>
				</tr>
				<tr>
					<td height="70" colspan="4" valign="top" >
						<div style="height: 15px; font-size: 14px;"><span style="visibility: hidden;">制品号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 19px; font-weight: bold;">${item.zpno }</span></div>
					</td>
				</tr>
				<tr>
					<td height="70" colspan="4" valign="top" >
						<div style="height: 15px; font-size: 14px;"><span style="visibility: hidden;">订货号</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 19px; font-weight: bold;"><c:if test="${item.dhno != null }">${item.dhno } - </c:if>${item.dhline }</span></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>