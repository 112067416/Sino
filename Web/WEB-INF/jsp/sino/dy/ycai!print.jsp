<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%
	String path = request.getContextPath();
%><div align="center" style="margin: 0 auto; text-align: center; vertical-align: top;">
<table align="center" cellspacing="0" cellpadding="0" border="0" >
	<colgroup><col width="650" /><col width="40" /><col width="310" /></colgroup>
	<%-- 标签1 --%>
	<c:if test="${vo1 != null }">
	<tr>
		<td style="border: 0px solid #000000; vertical-align: top;">
		<table width="100%" cellpadding="0" cellspacing="0"
			border="0" bordercolor="#000000" style="border-collapse: collapse; vertical-align: top;">
			<colgroup>
				<col width="20%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="30%" />
			</colgroup>
			<tr>
				<td height="75" colspan="3">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">中日达卷板No.</div>
				<div><span style="padding-left: 10px; font-size: 40px; font-weight: bold;">${vo1.jbno}</span></div>
				</td>
				<td colspan="2" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商卷板No.</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.zzsj}</div>
				</td>
			</tr>
			<tr>
				<td height="60" colspan="5" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商规格略称</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.yblc}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" colspan="3" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商生产年月日</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;"><f:v
					value="vo1.zzny " format="yyyy-MM-dd" /></div>
				</td>
				<td colspan="2" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">合同No.</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.ybno} - ${vo1.line }</div>
				</td>
			</tr>
			<tr>
				<td height="30px" colspan="4" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板尺寸</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;<f:v value="vo1.xpho" format="3"/>*<f:v value="vo1.xpkn" format="2"/></div>
				</td>
				<td valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板重量</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.zpzl}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板内径</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.njno}</div>
				</td>
				<td valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">表面精加工</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.face}</div>
				</td>
				<td colspan="4" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">分配等级</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo1.deng}</div>
				</td>
			</tr>
			<tr>
				<td height="58px" valign="middle" colspan="6" style="visibility: hidden;">
					<img alt="logo" src="<%=path%>/images/main/sino.jpg" border="0" height="50px" >
				</td>
			</tr>
		</table>
		</td>
		<td>&nbsp;</td>
		<td style="border: 0px solid #000000; vertical-align: top;">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" style="border-collapse: collapse; font-size: 18px; vertical-align: top;">
			<tr>
				<td height="50px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">中日达卷板No.</div>
				<div style="padding-left: 10px; font-size: 25px;; font-weight: bold;">&nbsp;${vo1.jbno}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商规格略称</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo1.yblc}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板尺寸</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;<f:v value="vo1.xpho" format="3"/>*<f:v value="vo1.xpkn" format="2"/></div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板重量</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo1.zpzl}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">合同No.</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo1.ybno} - ${vo1.line }</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商卷板No.</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo1.zzsj}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">船名</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo1.ship}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">CHECK</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr><td colspan="3" height="40">&nbsp;</td></tr>
	</c:if>
	<%-- 标签2 --%>
	<c:if test="${vo2 != null }">
	<tr>
		<td style="border: 0px solid #000000; vertical-align: top;">
		<table width="100%" cellpadding="0" cellspacing="0"
			border="0" bordercolor="#000000" style="border-collapse: collapse; vertical-align: top;">
			<colgroup>
				<col width="20%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="30%" />
			</colgroup>
			<tr>
				<td height="75" colspan="3" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">中日达卷板No.</div>
				<div><span style="padding-left: 10px; font-size: 40px; font-weight: bold;">${vo2.jbno}</span></div>
				</td>
				<td colspan="2" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商卷板No.</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.zzsj}</div>
				</td>
			</tr>
			<tr>
				<td height="60" colspan="5" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商规格略称</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.yblc}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" colspan="3" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商生产年月日</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;"><f:v
					value="vo2.zzny " format="yyyy-MM-dd" /></div>
				</td>
				<td colspan="2" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">合同No.</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.ybno} - ${vo2.line }</div>
				</td>
			</tr>
			<tr>
				<td height="30px" colspan="4" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板尺寸</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;<f:v value="vo2.xpho" format="3"/>*<f:v value="vo2.xpkn" format="2"/></div>
				</td>
				<td valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板重量</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.zpzl}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板内径</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.njno}</div>
				</td>
				<td valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">表面精加工</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.face}</div>
				</td>
				<td colspan="4" valign="top" style="line-height: 160%;">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">分配等级</div>
				<div style="padding-left: 20px; font-size: 30px;; font-weight: bold;">&nbsp;${vo2.deng}</div>
				</td>
			</tr>
			<tr>
				<td height="58px" valign="middle" colspan="6" style="visibility: hidden;" >
					<img alt="logo" src="<%=path%>/images/main/sino.jpg" border="0" height="50px" >
				</td>
			</tr>
		</table>
		</td>
		<td>&nbsp;</td>
		<td style="border: 0px solid #000000; vertical-align: top;">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" style="border-collapse: collapse; font-size: 18px; vertical-align: top;">
			<tr>
				<td height="50px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">中日达卷板No.</div>
				<div style="padding-left: 10px; font-size: 25px;; font-weight: bold;">&nbsp;${vo2.jbno}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商规格略称</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo2.yblc}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板尺寸</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;<f:v value="vo2.xpho" format="3"/>*<f:v value="vo2.xpkn" format="2"/></div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">卷板重量</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo2.zpzl}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">合同No.</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo2.ybno} - ${vo2.line }</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">制造商卷板No.</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo2.zzsj}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">船名</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;${vo2.ship}</div>
				</td>
			</tr>
			<tr>
				<td height="30px" valign="top">
				<div style="padding-left: 10px; font-size: 14px; visibility: hidden;">CHECK</div>
				<div style="padding-left: 20px; font-size: 22px;; font-weight: bold;">&nbsp;</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	</c:if>
</table>
</div>