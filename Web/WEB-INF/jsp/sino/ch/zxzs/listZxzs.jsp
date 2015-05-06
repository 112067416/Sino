<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="/sino" prefix="sino"%>
<c:if test="${page.query }">
 <table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="12%"/>
		<col width="11%"/>
		<col width="11%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="12%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" onclick="checkAll(this);" /></th>
	   	<th>制品NO</th>
		<th>重量</th>
		<th>堆场<ui:radiolist name="orders" list="'asc':'升序','desc':'降序'" value="order" onclick="setOrders(this);" /></th>
		<th>规格 </th>
		<th>硬度上下限 </th>
		<th>硬度</th>
		<th>产量代码</th>
		<th>是否保留</th>
		<th>STOP标识</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" onclick="doCheck(this);" no="${item.jbno }" value="${item.zpzl}"/></td>
			<td><c:if test="${item.plqf != null}">${item.plqf}</c:if><c:if test="${item.zzsd != null}">${item.zzsd}-</c:if>${item.jbno}</td>
			<td>${item.zpzl}</td>
			<td>${item.kw }</td>
			<td>${item.ggno }</td>
			<td>${item.ymin }~${item.ymax }</td>
			<td><sino:ying ying="${item.ying }" ymax="${item.ymax }" ymin="${item.ymin }" displayed="true" /></td>
			<td>${item.chan }</td>
			<td>${item.blbj }</td>
			<td>${item.ztbj }</td>
		</tr>
	</c:forEach>
 </table>
 <f:paged callback="setChecked"/>
 </c:if>