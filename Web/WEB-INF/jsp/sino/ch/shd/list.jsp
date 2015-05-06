<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="12%"/>
		<col width="12%"/>
		<col width="14%"/>
		<col width="26%"/>
		<col width="16%"/>
		<col width="8%"/>
		<col width="8%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
		<th>装箱指示No.</th>
	   	<th>出货日期</th>
		<th>运输公司</th>
		<th>用户名称</th>
		<th>送货点</th>
		<th>合计重量</th>
		<th>合计数量</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.zxno}"  xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids"  value="${item.zxno }" /></td>
			<td>${item.zxno } </td>
			<td><f:v value="item.chri" format="yyyy-MM-dd"/></td>
			<td>${item.ysnm }</td>
			<td>${item.name }</td>
			<td>${item.shnm }</td>
			<td>${item.chzl }</td>
			<td>${item.chsu }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />