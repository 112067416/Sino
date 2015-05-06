<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<table width="765" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td style="font-size: 35px; font-weight: bold; font-family: 黑体; display: none;" align="center">送&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单</td>
	</tr>
	<tr><td height="40" style="text-align: right; font-size: 12px;"> PAGE：${current+1 } / ${pages }</td></tr>
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<colgroup>
					<col width="60%" />
					<col width="40%" />
				</colgroup>
				<tr>
					<td>
						<table width="100%" border="0" bordercolor="#ffffff" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
						<colgroup><col width="25%" /><col width="25%" /><col width="34%" /><col width="16%" /></colgroup>
							<tr align="center">
								<th height="32"style="visibility: hidden;">送货单No.</th>
								<th style="visibility: hidden;">发行日</th>
								<th style="visibility: hidden;" colspan="2">运输公司</th>
							</tr>
							<tr align="center">
								<td height="32">${zxzsTp.zxno }</td>
								<td><f:v value="zng1Tp.zxzsTp.chri" format="yyyy/MM/dd"/></td>
								<td colspan="2">${zxzsTp.ysnm }</td>
							</tr>
							<tr align="center">
								<th height="32" style="visibility: hidden;" colspan="2">送货地点代码</th>
								<th style="visibility: hidden;" colspan="2">送货地点名</th>
							</tr>
							<tr align="center">
								<td height="32" colspan="2">${zxzsTp.user }&nbsp;&nbsp;${zxzsTp.abbr }</td>
								<td colspan="2">${zxzsTp.shnm }</td>
							</tr>
							<tr align="left" style="padding-left: 15px;">
								<th height="32" style="visibility: hidden;" colspan="4">送货地址</th>
							</tr>
							<tr align="left" style="padding-left: 15px;">
								<td height="32" colspan="4">${zxzsTp.addr }</td>
							</tr>
							<tr align="left" style="padding-left: 15px;">
								<th height="32" style="visibility: hidden;" colspan="3">商社/TRADER</th>
								<th style="visibility: hidden;">附着量</th>
							</tr>
							<tr align="right" style="padding-right: 15px;">
								<td height="32" colspan="3">${dhuoTp.ssno }&nbsp; &nbsp; ${dhuoTp.ssnm }</td>
								<td>${dhuoTp.fuzm }/${dhuoTp.fufm }</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" bordercolor="#ffffff" style="border-collapse: collapse; padding-left: 10px;">
				<colgroup>
					<col width="10%" />
					<col width="15%" />
					<col width="48%" />
					<col width="15%" />
					<col width="7%" />
					<col width="5%" />
				</colgroup>
				<tr>
					<th height="25" style="visibility: hidden;" rowspan="2">客户订货<br>No.</th>
					<th style="visibility: hidden;" rowspan="2">中日达订货<br>No.</th>
					<th style="visibility: hidden;"><span style="position: relative; left: -50px;">规格代码</span><span style="position: relative; left: 50px;">规&nbsp;&nbsp;&nbsp;&nbsp;格&nbsp;&nbsp;&nbsp;&nbsp;略&nbsp;&nbsp;&nbsp;&nbsp;称</span></th>
					<th style="visibility: hidden;" rowspan="2">制&nbsp;&nbsp;&nbsp;品&nbsp;No.</th>
					<th style="visibility: hidden;" rowspan="2">重&nbsp;&nbsp;&nbsp;量<br>(kg)</th>
					<th style="visibility: hidden;" rowspan="2">张&nbsp;数</th>
				</tr>
				<tr>
					<th height="25" style="visibility: hidden;"><span style="position: relative; left: -80px;">厚</span><span style="position: relative; left: -30px;">×</span>宽<span style="position: relative; left: 40px;">×</span><span style="position: relative; left: 80px;">长</span></th>
				</tr>
				<c:forEach varStatus="stat" var="item" items="${page.datas }">
			 	<c:set var="count" value="${stat.count }" scope="page" />
				<tr>
					<td height="21">&nbsp;</td>
					<td valign="bottom">${item.zng1Tp.dhno }-${item.zng1Tp.line }</td>
					<td valign="bottom">${item.zng1Tp.ggno }&nbsp;&nbsp;&nbsp;&nbsp;${item.zng1Tp.ggnm }</td>
					<td valign="bottom">${item.jbno }</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="21">&nbsp;</td>
					<td>&nbsp;</td>
					<td valign="top">${item.zng1Tp.houu }×&nbsp;${item.zng1Tp.kuan }×&nbsp;<c:choose><c:when test="${item.zng1Tp.cang != null && item.zng1Tp.cang > 0}">${item.zng1Tp.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
					<td>&nbsp;</td>
					<td valign="top">${item.zpzl }</td>
					<td valign="top"><c:choose><c:when test="${item.zshu != null && item.zshu > 0}">${item.zshu }</c:when><c:otherwise>${item.jscn }</c:otherwise></c:choose></td>
				</tr>
				</c:forEach>
				<c:if test="${count < pageSize }">
			    <c:forEach begin="${count }" end="${pageSize - 1}">
				<tr>
					<td height="21">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="21">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				</c:forEach>
			    </c:if>
				<tr>
					<td height="15" colspan="2">&nbsp;</td>
					<td colspan="2" style="font-size: 10px;"><c:choose><c:when test="${current < pages - 1 }">小计重量：${page.hjzls}  小计包数：${page.hjbs }</c:when><c:otherwise>合计重量：${zxzsTp.chzl }  合计包数：${zxzsTp.chsu } <c:if test="${hjzs != null && hjzs > 0 }">合计张数：${hjzs }</c:if></c:otherwise></c:choose></td>
					<td colspan="2">&nbsp;</td>			
				</tr>
			</table>
		</td>
	</tr>
	<tr><td height="5">&nbsp;</td></tr>
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" bordercolor="#000000" style="border-collapse: collapse; padding-left: 5px;">
				<colgroup>
					<col width="15%" />
					<col width="40%" />
					<col width="45%" />
				</colgroup>
				<tr>
					<th height="65" valign="top" align="left"><span style="visibility: hidden;">装箱日</span><br />
					<p style="font-weight:normal; "><f:v value="zng1Tp.zxzsTp.chri" format="yyyy/MM/dd"/> </p>
					</th>
					<th valign="top" align="left" style="visibility: hidden;">驾驶员签字</th>
					<th rowspan="2" valign="middle" align="left"><span style="visibility: hidden;">中日达出货部门搬出批准</span><br />
					<p style="font-weight:normal; font-size: 30px; padding-left: 170px;">${zxzsTp.ddr }</p></th>
				</tr>
				<tr>
					<th height="65" valign="top" align="left" style="visibility: hidden;">到达日</th>
					<th valign="top" align="left" style="visibility: hidden;">买方签字</th>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>