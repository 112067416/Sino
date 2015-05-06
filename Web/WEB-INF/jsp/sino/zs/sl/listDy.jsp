<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table  width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /></colgroup>
	<tr>
	 <th><input type="checkbox" xu.target="ids" /></th>
		<th>指示No.</th>
		<th>订货No.</th>
		<th>操作方法</th>
		<th>用户略称</th>
		<th>运用规格</th>
		<th>分配等级</th>
		<th>表面</th>
		<th>尺寸</th>
		<th>附着量</th>
		<th>总重量(Kg)</th>
		<th>作成时间</th>
		<th>打印标志</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr xu.menu="menu" xu.id="${item.zsno }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)"  <c:if test="${item.wsjz>0}">style="background-color:#FFD39B;"</c:if> <c:if test="${item.vsjz>0}">style="background-color:#7FFFD4;"</c:if> >
			<td align="center"><input type="checkbox" name="ids" value="${item.zsno }" /></td>	
			<td>${item.zsno}</td>
			<td>${item.dhno }</td>
			<td><sys:codeLabel code="#SINO_CAOZ" key="item.caoz"/></td>
			<td>${item.abbr }</td>
			<td>${item.yuny }</td>
			<td>${item.fpdj }</td>
			<td>${item.face }</td>
			<td>${item.zpcc }</td>
			<td>${item.fugm }</td>
			<td>${item.zzl }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd" /></td>
			<td><f:kv value="item.zsfx" list="'0':'未打印','1':'<font color='red'>已打印</font>'"  /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />