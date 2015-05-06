<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div style="font-size: 25px; font-weight: bold; color: #FF9B59; height: 20; visibility: hidden;" align="center">PACKING/WEIGHT LIST</div>
	<table width="755" cellspacing="0" cellpadding="0" border="0">
		<colgroup><col width="3%" /><col width="97%" /></colgroup>
		<tr>
			<td colspan="2">
				<table width="100%" cellspacing="0" style="padding-left: 10px;" cellpadding="0" border="0">
					<colgroup><col width="30%" /><col width="35%" /><col width="35%" /></colgroup>
					<tr>
						<td height="90">&nbsp;</td>
						<td align="right" style="visibility: hidden;"><img alt="logo" src="<%=request.getContextPath()%>/images/main/sino.jpg" border="0" width="500px" ></td>
						<td style="font-size: 12px; text-align: right; vertical-align: top;">PAGE：${current+1 } / ${pages }</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" bordercolor="#000000" style="padding-left: 10px; border-collapse: collapse;">
					<colgroup>
						<col width="45%" /><col width="22%" /><col width="33%" />
					</colgroup>
					<tr>
						<td height="35" style="font-size: 20px; color: #FF9B59; visibility: hidden;">Purchaser&nbsp;&nbsp;Messrs</td>
						<td>&nbsp;</td>
						<td style="font-size: 20px; color: #FF9B59; visibility: hidden;">Date</td>
					</tr>
					<tr>
						<td height="35" style="font-size: 14px;">${dhuoTp.user }&nbsp;&nbsp;${dhuoTp.name }</td>
						<td>&nbsp;</td>
						<td style="font-size: 14px;"><f:v value="date" format="yyyy/MM/dd"/></td>
					</tr>
					<tr>
						<td height="30" style="font-size: 20px; color: #FF9B59; visibility: hidden;">Purchaser&nbsp;Order&nbsp;No.</td>
						<td>&nbsp;</td>
						<td style="font-size: 20px; color: #FF9B59; visibility: hidden;">Suppliers&nbsp;Order&nbsp;No.</td>
					</tr>
					<tr>
						<td height="35">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="30" style="font-size: 20px; color: #FF9B59; visibility: hidden;">Supppliers：&nbsp;Messrs</td>
						<td>&nbsp;</td>
						<td style="font-size: 20px; color: #FF9B59; visibility: hidden;">Order&nbsp;No.</td>
					</tr>
					<tr>
						<td height="35" style="font-size: 14px;">${dhuoTp.ssno }&nbsp;&nbsp;${dhuoTp.ssnm }</td>
						<td>&nbsp;</td>
						<td style="font-size: 16px;">${dhuoTp.dhno }-${dhuoTp.line }</td>
					</tr>
					<tr>
						<td height="30" style="font-size: 20px; color: #FF9B59; visibility: hidden;">Commodity&nbsp;&nbsp;Specification：</td>
						<td>&nbsp;</td>
						<td style="font-size: 20px; color: #FF9B59; visibility: hidden;">Dimension：</td>
					</tr>
					<tr>
						<td height="45" style="font-size: 14px;">${pm }<br>${dhuoTp.ggnm }&nbsp;COATING WEIGHT ${dhuoTp.fudw }&nbsp;${dhuoTp.fuzm }-${dhuoTp.fufm}</td>
						<td>&nbsp;</td>
						<td style="font-size: 16px;"><f:v value="dhuoTp.houu" format="3" />×&nbsp;<f:v value="dhuoTp.kuan" format="2" />×&nbsp;<c:choose><c:when test="${dhuoTp.cang != null && dhuoTp.cang > 0}"><f:v value="dhuoTp.cang" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="14" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" bordercolor="#FF9B59" style="border-collapse: collapse; font-size: 15px;">
					<colgroup>
						<col width="20%" />
						<col width="20%" />
						<col width="15%" />
						<col width="17%" />
						<col width="14%" />
						<col width="14%" />
					</colgroup>
					<tr>
						<th rowspan="2" style="color: #FF9B59;">&nbsp;</th>
						<th rowspan="2" style="color: #FF9B59;">&nbsp;</th>
						<th rowspan="2" style="color: #FF9B59;">&nbsp;</th>
						<th height="30" colspan="3" style="color: #FF9B59;">&nbsp;</th>
					</tr>
					<tr>
						<th height="30" style="color: #FF9B59;">&nbsp;</th>
						<th style="color: #FF9B59;">&nbsp;</th>
						<th style="color: #FF9B59;">&nbsp;</th>
					</tr>
					<c:forEach varStatus="stat" var="item" items="${vos }">
				    <c:set var="count" value="${stat.count }" scope="page" />
					<tr>
						<td height="16" align="center" valign="bottom"><f:v value="item.ckno" format="%06d" /></td>
						<td align="center" style="vertical-align: bottom;">${item.jbno }</td>
						<td align="center" valign="bottom"><c:choose><c:when test="${item.zshu != null && item.zshu > 0 }">${item.zshu }</c:when><c:otherwise>${item.jscn }</c:otherwise></c:choose></td>
						<td align="center" valign="bottom">&nbsp;</td>
						<td align="center" valign="bottom">${item.zpzl}</td>
						<td align="center" valign="bottom">${item.mazl }</td>
					</tr>
					</c:forEach>
					<c:if test="${count < pageSize + 1 }">
				    <c:forEach begin="${count + 1 }" var="index" end="${pageSize + 1 }">
			    	<c:choose><c:when test="${index == count + 1 }">
			    	<tr>
					<td height="15" colspan="2">&nbsp;</td>
				 	<c:choose><c:when test="${current + 1 == pages }">
						<td align="center" valign="bottom" style="font-size: 15px;">合计</td>
						<td align="center" valign="bottom" style="font-size: 15px;">${chsu } 件</td>
						<td align="center" valign="bottom" style="font-size: 15px;">${hjzl }</td>
						<td align="center" valign="bottom" style="font-size: 15px;">${hjmz }</td>
				    </c:when><c:otherwise>
						<td align="center" valign="bottom" style="font-size: 15px;"></td>
						<td align="center" valign="bottom" style="font-size: 15px;"></td>
						<td></td>
						<td align="center" valign="bottom" style="font-size: 15px;"></td>
				    </c:otherwise></c:choose>
				    </tr>
			    	</c:when>
			    	<c:otherwise>
			    	<tr>
						<td height="16"></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
			    	</c:otherwise></c:choose>
				    </c:forEach>
				    </c:if>
				</table>
			</td>
		</tr>
	</table>
</div>