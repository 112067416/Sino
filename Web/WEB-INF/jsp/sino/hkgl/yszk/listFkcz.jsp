<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="3%" /><col width="8%" />
<col width="8%" /><col width="8%" /><col width="6%" /><col width="14%" /><col width="9%" /><col width="9%" /><col width="8%" /><col width="8%" /><col width="9%" /><col width="10%" /></colgroup>
<tr align="center">
<th nowrap="nowrap"><input type="checkbox" onclick="checkAll(this);" /></th>
<th nowrap="nowrap" onclick="setOrders('${chriOrder }');" title="设置按出货日期升序或降序排" style="cursor: pointer;">出货日期</th>
<th nowrap="nowrap">订货No.</th>
<th nowrap="nowrap">镀锡量</th>
<th nowrap="nowrap">规格</th>
<th nowrap="nowrap">尺寸</th>
<th nowrap="nowrap">开票吨数</th>
<th nowrap="nowrap">发票金额</th>
<th nowrap="nowrap">发票号码</th>
<th nowrap="nowrap">发票日期</th>
<th nowrap="nowrap">收款金额</th>
<th nowrap="nowrap">未收余款</th>
</tr>
<tr>
<td colspan="5">&nbsp;</td>
<td style="text-align: right; padding-right: 10px;">合计</td>
<td style="text-align: right; padding-right: 10px;"><f:v value="hjVO.chzl" format="3" /></td>
<td style="text-align: right; padding-right: 10px;"><f:v value="hjVO.fpje" format="#,##0.00" /></td>
<td colspan="2">&nbsp;</td>
<td style="text-align: right; padding-right: 10px;"><f:v value="hjVO.fkje" format="#,##0.00" /></td>
<td style="text-align: right; padding-right: 10px;"><f:v value="hjVO.wsyk" format="#,##0.00" /></td>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr id0="${item.id }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
<td align="center">
<c:if test="${item.wsyk != 0 }"><input type="checkbox" name="ids" value="${item.id }" onclick="doCheck(this);" wsyk="${item.wsyk }" /></c:if>
</td>
<td><f:v value="item.chri" format="yyyy-MM-dd"/></td>
<td><c:choose><c:when test="${item.dhno != null }">${item.dhno }- </c:when><c:otherwise></c:otherwise></c:choose>${item.line }</td>
<td>${item.fudw } &nbsp;<c:choose><c:when test="${item.fufm != null }">${item.fufm }/</c:when><c:otherwise></c:otherwise></c:choose>${item.fuzm }</td>
<td>${item.ggno }</td>
<td><c:choose><c:when test="${item.houu != null && item.houu > 0}"><f:v value="item.houu" format="3" />*</c:when><c:otherwise></c:otherwise></c:choose><c:choose><c:when test="${item.kuan != null && item.kuan > 0}"><f:v value="item.kuan" format="2" />*</c:when><c:otherwise></c:otherwise></c:choose><c:choose><c:when test="${item.cang != null && item.cang > 0}"><f:v value="item.cang" format="2" /></c:when><c:when test="${item.houu != null && item.houu > 0}">COIL</c:when><c:otherwise></c:otherwise></c:choose></td>
<td style="text-align: right; padding-right: 10px;"><f:v value="item.kfzl" format="3" /></td>
<td style="text-align: right; padding-right: 10px;"><c:choose><c:when test="${item.thqf != null && item.thqf == \"1\" }"></c:when><c:otherwise>$</c:otherwise></c:choose><f:v value="item.fpje" format="#,##0.00" /></td>
<td>${item.fpno }</td>
<td><f:v value="item.fpri" format="yyyy-MM-dd" /></td>
<td style="text-align: right; padding-right: 10px;"><c:choose><c:when test="${item.thqf != null && item.thqf == \"1\" }"></c:when><c:otherwise>$</c:otherwise></c:choose><f:v value="item.fkje" format="#,##0.00" /></td>
<td <c:choose><c:when test="${item.fppz == \"1\" }">style="background-color: red; text-align: right; padding-right: 10px;" title="红字发票"</c:when><c:otherwise>style="text-align: right; padding-right: 10px;"</c:otherwise></c:choose>><c:choose><c:when test="${item.thqf != null && item.thqf == \"1\" }"></c:when><c:otherwise>$</c:otherwise></c:choose><f:v value="item.wsyk" format="#,##0.00" />
</tr>
</c:forEach>
</table>
<f:paged callback="setChecked" />