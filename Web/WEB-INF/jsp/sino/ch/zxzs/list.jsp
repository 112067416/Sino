<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="11%"/>
		<col width="11%"/>
		<col width="10%"/>
		
		<col width="12%"/>
		<col width="16%"/>
		<col width="12%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" onclick="checkAll(this);" /></th>
		<th>装箱指示No.</th>
	   	<th>出货日期</th>
	   	<th>装箱指示书日期</th>
		<th>运输公司</th>
		<th>用户名称</th>
		<th>送货点</th>
		<th>合计重量</th>
		<th>合计数量</th>
		<th>状态</th>	
	</tr>
    <c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.zxno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="print(this);" onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="id" value="${item.zxno }" onclick="doCheck(this);" /></td>
			<td>${item.zxno } </td>
			<td><f:v value="item.chri" format="yyyy-MM-dd"/></td>
			<td><f:v value="item.crea" format="yyyy-MM-dd"/></td>
			<td>${item.ysnm }</td>
			<td>${item.name }</td>
			<td>${item.shnm }</td>
			<td>${item.chzl }</td>
			<td>${item.chsu }</td>
			<td><f:code code="#SINO_CH_STAT" key="${item.stat }" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />