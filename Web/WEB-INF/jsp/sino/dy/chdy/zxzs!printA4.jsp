<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="/sino" prefix="sino"%>
<div>
<table width="1000" cellspacing="0" cellpadding="0" border="0" style="text-align: left;" >
	<tr>
		<td align="center" style="font-size: 35px; font-weight: bold; color: #1DAE53; font-family: 黑体;visibility: visible;">
			装&nbsp;&nbsp;箱&nbsp;&nbsp;指&nbsp;&nbsp;示&nbsp;&nbsp;书
		</td>
	</tr>
	<tr>
		<td height="25" align="right" style="font-size: 12px; vertical-align: top;"><f:v value="date" format="yyyy/MM/dd HH:mm:ss"/> PAGE：${current+1 } / ${pages }</td>
	</tr>
	<tr>
		<td>
			<table align="left" width="100%" cellspacing="0" cellpadding="0" border="1" bordercolor="#1DAE53" style="border-collapse: collapse; font-size: 13px;">
				<colgroup>
					<col width="240" />
					<col width="50" />
					<col width="60" />
					<col width="190" />
					<col width="60" />
					<col width="190" />
					<col width="100" />
					<col width="110" />
				</colgroup>
				<tr>
					<th height="20" align="left" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">装箱指示No.</th>
					<th align="center" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">内外销</th>
					<th align="center" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">用&nbsp;户<br />代&nbsp;码</th>
					<th align="left" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">用户名称</th>
					<th align="center" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">送货地<br />代&nbsp;&nbsp;&nbsp;码</th>
					<th align="left" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">送货地名称</th>
					<th align="left" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;">出货日</th>
					<th align="center" valign="top" style="border-bottom: 0; color: #1DAE53;visibility: visible;" colspan="2">运&nbsp;输<br />公&nbsp;司</th>
				</tr>
				<tr>
					<th height="40" style="border-top: 0;">${zng1Tp.zxzsTp.zxno }</th>
					<th style="border-top: 0;">${dhuoTp.nwai }</th>
					<th style="border-top: 0;">${zng1Tp.zxzsTp.user }</th>
					<th style="border-top: 0;">${zng1Tp.zxzsTp.abbr }</th>
					<th style="border-top: 0;">${zng1Tp.zxzsTp.shno }</th>
					<th style="border-top: 0;">${zng1Tp.zxzsTp.shnm }</th>
					<th style="border-top: 0;"><f:v value="zng1Tp.zxzsTp.crea" format="yyyy-MM-dd"/></th>
					<th style="border-top: 0;" colspan="2">${zng1Tp.zxzsTp.ysnm }</th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="15">&nbsp;</td>
	</tr>
	<tr>
		<td>
			<table align="left" width="100%" cellspacing="0" cellpadding="0" border="1" bordercolor="#1DAE53" style="border-left: 0; border-bottom: 0; border-collapse: collapse; text-align: center; font-size: 13px;">
				<colgroup>
					<col width="110" />
					<col width="110" />
					<col width="80" />
					<col width="200" />
					<col width="100" />
					<col width="150" />
					<col width="70" />
					<col width="45" />
					<col width="65" />
					<col width="70" />
				</colgroup>
				<tr height="50">
					<th style="color: #1DAE53;visibility: visible;">订货No.</th>
					<th style="color: #1DAE53;visibility: visible;">制品No.</th>
					<th style="color: #1DAE53;visibility: visible;">规格代码</th>
					<th style="color: #1DAE53;visibility: visible;">规 格 略 称</th>
					<th style="color: #1DAE53;visibility: visible;">付着量(单位 • 正 • 反)</th>
					<th colspan="2" style="color: #1DAE53;visibility: visible;">尺寸&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;厚 × 宽  × 长</th>
					<th colspan="2" style="color: #1DAE53;visibility: visible;">重&nbsp;&nbsp;&nbsp;&nbsp;量</th>
					<th style="color: #1DAE53;visibility: visible;">堆场代码</th>
				</tr>
				<c:set value="" var="dhnoLine" scope="page" /> 
				<c:forEach varStatus="stat" var="item" items="${page.datas }">
				 <c:set var="count" value="${stat.count }" scope="page" />
					<tr>
					<c:set value="${item.zng1Tp.dhno }${item.zng1Tp.line }" var="dhnoLineNow" scope="page" /> 
					<td height="20" style="text-align: right; padding-right: 10px;"><c:if test="${dhnoLine != dhnoLineNow }">${item.zng1Tp.dhno }-${item.zng1Tp.line }</c:if></td>
					<td>${item.jbno}</td>
					<td>${item.zng1Tp.ggno }</td>
					<td>${item.zng1Tp.ggnm}</td>
					<td>${item.zng1Tp.fudw}•${item.zng1Tp.fuzm}•${item.zng1Tp.fufm}</td>
					<td colspan="2">${item.zng1Tp.houu}×&nbsp;${item.zng1Tp.kuan}×&nbsp;<c:choose><c:when test="${item.zng1Tp.cang != null && item.zng1Tp.cang > 0}">${item.zng1Tp.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
					<td colspan="2">${item.zpzl}</td>
					<td>${item.dcno}<sino:ying ying="${item.llyd }" ymax="${item.zng1Tp.ymax }" ymin="${item.zng1Tp.ymin }" label1="***" label2="" /></td>
					<c:set value="${item.zng1Tp.dhno }${item.zng1Tp.line }" var="dhnoLine" scope="page" /> 
					</tr>
				</c:forEach>
               <c:if test="${count < pageSize }">
			    <c:forEach begin="${count }" var="index" end="${pageSize - 1}">
			    	<tr>
					<td height="20">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
					</tr>
			    </c:forEach>
			    </c:if>
				<tr>
					<th height="33" colspan="6" style="border-left: 0; border-bottom: 0">&nbsp;</th>
					<th colspan="2" style="color: #1DAE53;">装箱制品数</th>
					<th colspan="2" style="text-align: center;"><c:choose><c:when test="${current + 1 == pages }">${zng1Tp.zxzsTp.chsu }</c:when><c:otherwise></c:otherwise></c:choose></th>
				</tr>
				<tr>
					<th height="33" colspan="6" style="border-left: 0; border-top: 0; border-bottom: 0;">&nbsp;</th>
					<th colspan="2" style="color: #1DAE53;">装箱重量计</th>
					<th colspan="2" style="text-align: center;"><c:choose><c:when test="${current + 1 == pages }">${zng1Tp.zxzsTp.chzl }</c:when><c:otherwise></c:otherwise></c:choose></th>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>