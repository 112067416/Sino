<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /></colgroup>
	<tr xu.menu="menu"class="page-head" align="center">
		<th>
			<input type="checkbox" xu.target="ids" />
		</th>
		<th>订货No.</th>
		<th>交货期</th>
		<th>品种</th>
		<th>规格</th>
		<th>用途</th>
		<th>订货量</th>
		<th>分配量</th>
		<th>ETL实绩(T)</th>
		<th>SL实绩(T)</th>
		<th>捆包实绩(K)</th>
		<th>出货实绩(K)</th>
		<th>状态</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.dhno }-${item.line}" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" value="${item.dhno }-${item.line}"/></td>
			<td>${item.dhno }-${item.line}</td>
			<td><f:v value="item.jhqi" format="yyyy-MM-dd"/></td>
			<td>${item.pzno }</td>
			<td>${item.ggno }</td>
			<td>${item.cond }</td>
			<td>${item.htzl }</td>
			<td>${item.fpln }</td>
			<td>${item.etlh }</td>
			<td>${item.slhg }</td>
			<td>${item.kbus }</td>
			<td>${item.chus }</td>
			<td><f:code code="#SINO_DH_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
	</table>
<f:paged />
