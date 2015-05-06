<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${page.query }">
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
	<col width="3%"/>
	<col width="11%"/>
	<col width="7%"/>
	<col width="10%"/>
	<col width="11%"/>
	<col width="11%"/>
	<col width="6%"/>
	<col width="5%"/>
	<col width="6%"/>
	<col width="4%"/>
	<col width="4%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="8%"/>
	<col width="4%"/>
</colgroup>
<tr class="page-head" align="center">
	<th><input type="checkbox" onclick="checkAll(this);" /></th>
   	<th>现品NO.</th>
   	<th>订货No.</th>
   	<th>装箱指示书No.</th>
	<th>用户略称</th>
	<th>尺寸</th>
	<th>规格</th>
	<th>表面</th>
	<th>重量</th>
	<th>等级</th>
	<th>产量</th>
	<th>堆场</th>
	<th>硬度</th>
	<th>镀锡量</th>
	<th>停止</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
	<tr xu.id="${item.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
		<td align="center"><input type="checkbox" name="id" value="${item.jbno }" onclick="doCheck(this);" /></td>
		<td><c:if test="${item.plqf != null}">${item.plqf}</c:if><c:if test="${item.zzsd != null}">${item.zzsd}-</c:if>${item.jbno}</td>
		<td>${item.dhno }</td>
		<td>${item.chno }</td>
		<td>${item.abbr }</td>
		<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
		<td>${item.ggno }</td>
		<td>${item.face }</td>
		<td>${item.zpzl }</td>
		<td>${item.deng }</td>
		<td>${item.chan }</td>
		<td>${item.kw }</td>
		<td>${item.ying }</td>
		<td>${item.fudw } ${item.fuzm }/${item.fufm }</td>
		<td>${item.ztbj }</td>
	</tr>
</c:forEach>
</table>
<f:paged callback="setChecked" />
</c:if>