<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui" %>
<%@ taglib uri="/f" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 
<table id="SlZpList" width="96%" align="center" class="pagination">
	<colgroup>
		<col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col />
	</colgroup>
	<tr>
		<th>指示No</th>
		<th>订货No</th>
		<th>用户略称</th>
		<th>制品尺寸</th>
		<th>表面加工</th>
		<th>分配等级</th>
		<th>运用规格</th>
		<th>附着量</th>
		<th>总重量(Kg)</th>
		<th>作成时间</th>
		<th>完成时间 </th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr ondblclick="doView(this);" key1="${item.zsno }" xu.menu="menu" xu.id="${item.zsno }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.zsno }</td>
			<td>${item.dhno }</td>
			<td>${item.abbr }</td>
			<td>${item.zpcc }</td>
			<td>${item.face }</td>
			<td>${item.fpdj }</td>
			<td>${item.yuny }</td>
			<td>${item.fugm }</td>
			<td>${item.zzl }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd"/></td>
			<td><f:v value="item.zsny" format="yyyy-MM-dd"/></td>
		</tr>
	</c:forEach>
</table>
<f:paged/>