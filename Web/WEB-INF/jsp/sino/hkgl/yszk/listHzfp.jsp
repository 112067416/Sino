<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="FpmxTbl" width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="3%" /><col width="7%" /><col width="7%" />
<col width="4%" /><col width="10%" /><col width="5%" /><col width="8%" /><col width="7%" />
<col width="8%" /><col width="8%" /><col width="8%" /><col width="10%" /><col width="7%" /><col width="5%" /><col width="3%" /></colgroup>
<tr align="center">
<th nowrap="nowrap"><input type="checkbox" onclick="checkAll(this);" /></th>
<th nowrap="nowrap">出货日期</th>
<th nowrap="nowrap">订货No.</th>
<th nowrap="nowrap">规格</th>
<th nowrap="nowrap">尺寸</th>
<th nowrap="nowrap">镀锡量</th>
<th nowrap="nowrap">发票客户</th>
<th nowrap="nowrap">吨数</th>
<th nowrap="nowrap">发票单价</th>
<th nowrap="nowrap">利息折扣</th>
<th nowrap="nowrap">质量折扣</th>
<th nowrap="nowrap">算出总价</th>
<th nowrap="nowrap">发票号</th>
<th nowrap="nowrap">状态</th>
<th nowrap="nowrap">类型</th>
</tr>
<tr>
<td colspan="6"></td>
<td>合计</td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="hjVO.chzl" format="3" /></td>
<td colspan="3">&nbsp;</td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><c:choose><c:when test="${thqf != null && thqf == \"1\" }"><f:v value="hjVO.fpje" format="#,##0.00"/></c:when><c:otherwise>$<f:v value="hjVO.fpje" format="#,##0.00"/></c:otherwise></c:choose></td>
<td colspan="3"></td>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr id0="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
<td nowrap="nowrap"><input type="checkbox" name="chk" value="${item.id }" onclick="doCheck(this);" /></td>
<td nowrap="nowrap"><f:v value="item.chri" format="yyyy-MM-dd"/><input type="hidden" name="stat"  value="${item.stat }"/></td>
<td nowrap="nowrap"><input type="hidden" name="id" value="${item.id }"/><c:choose><c:when test="${item.dhno != null }">${item.dhno }- </c:when><c:otherwise></c:otherwise></c:choose>${item.line }</td>
<td nowrap="nowrap">${item.ggno }</td>
<td nowrap="nowrap"><c:choose><c:when test="${item.houu != null && item.houu > 0}"><f:v value="item.houu" format="3" />*</c:when><c:otherwise></c:otherwise></c:choose><c:choose><c:when test="${item.kuan != null && item.kuan > 0}"><f:v value="item.kuan" format="2" />*</c:when><c:otherwise></c:otherwise></c:choose><c:choose><c:when test="${item.cang != null && item.cang > 0}"><f:v value="item.cang" format="2" /></c:when><c:when test="${item.houu != null && item.houu > 0}">COIL</c:when><c:otherwise></c:otherwise></c:choose></td>
<td nowrap="nowrap"><c:choose><c:when test="${item.fufm != null }">${item.fufm }/</c:when><c:otherwise></c:otherwise></c:choose>${item.fuzm }</td> 
<td nowrap="nowrap">${item.fpymc }</td>
<td nowrap="nowrap" style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.kfzl" format="3" /></td>
<td nowrap="nowrap" style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.kpdj" format="#,##0.00" /></td>
<td nowrap="nowrap" style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.lxzr" format="#,##0.00" /></td>
<td nowrap="nowrap" style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.zlzr" format="#,##0.00" /></td>
<td nowrap="nowrap" style="text-align: right; padding-right: 10px; font-size: 14px;"><c:choose><c:when test="${item.thqf != null && item.thqf == \"1\" }"><f:v value="item.fpje" format="#,##0.00" /></c:when><c:otherwise>$<f:v value="item.fpje" format="#,##0.00" /></c:otherwise></c:choose></td>
<td nowrap="nowrap" style="text-align: right; padding-right: 10px; font-size: 14px;">${item.fpno }</td>
<td nowrap="nowrap"><f:code code="#SINO_FKFP_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
<td nowrap="nowrap"><c:choose><c:when test="${item.kfzl == null || (item.kfzl != null && item.kfzl <= 0) }"><span style="color: red; font-size: 14px;">红字</span></c:when><c:otherwise><span style="color: blue; font-size: 14px;">蓝字</span></c:otherwise></c:choose> </td>
</tr>
</c:forEach>
</table>
<f:paged callback="setChecked" />