<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="TzTbl"  width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="2%"/><col width="2%"/><col width="4%"/><col width="5%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="5%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="10%"/><col width="9%"/><col width="9%"/></colgroup>
<tr class="page-head" align="center">
<th nowrap="nowrap" rowspan="2" colspan="2">${year }</th>
<th nowrap="nowrap" rowspan="3">摘要</th>
<th nowrap="nowrap" colspan="4">购进</th>
<th nowrap="nowrap" colspan="4">领用</th>
<th nowrap="nowrap" colspan="3">结存</th>
</tr>
<tr>
<th nowrap="nowrap" rowspan="2">进仓单号</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
<th nowrap="nowrap" rowspan="2">领料单号</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
</tr>
<tr>
<th nowrap="nowrap">月</th>
<th nowrap="nowrap">日</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
<td><f:v value="item.riqi" format="MM" /></td>
<td><f:v value="item.riqi" format="dd" /></td>
<td><input type="hidden" name="id" value="${item.id }" /></td>
<td><f:v value="item.riqi" format="yyMMdd" /></td>
<td><ui:int name="gjzl" maxlength="8" value="item.gjzl" /></td>
<td><ui:int name="gjybmy" maxlength="8" value="item.gjybmy" /></td>
<td><ui:int name="gjbs" maxlength="8" value="item.gjbs" /></td>
<td><f:v value="item.riqi" format="yyMMdd" /></td>
<td><ui:int name="lyzl" maxlength="8" value="item.lyzl" /></td>
<td><ui:int name="lyybmy" maxlength="8" value="item.lyybmy" /></td>
<td><ui:int name="lybs" maxlength="8" value="item.lybs" /></td>
<td><ui:int name="jczl" maxlength="8" value="item.jczl" /></td>
<td><ui:int name="bspjc" maxlength="8" value="item.bspjc" /></td>
<td><ui:int name="ybmyjc" maxlength="8" value="item.ybmyjc" /></td>
</tr>
</c:forEach>
</table>
<f:paged />