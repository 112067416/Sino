<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="5%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="18%" /><col width="14%" />
<col width="5%" /><col width="9%" /><col width="9%" /><col width="5%" /><col width="5%" />
</colgroup>
	<tr>
		<th><input type="checkbox" xu.target="ids" /></th>
		<th>现品NO.</th>
		<th>装箱指示书No.</th>
		<th>订货No.</th>
		<th>用户略称</th>
		<th>尺寸</th>
		<th>表面</th>
		<th>镀锡量</th>
		<th>现品重量</th>
		<th>堆场</th>
		<th>停止</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr ondblclick="" title="" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" value="${item.jbno}" /></td>
			<td><c:if test="${item.plqf != null}">${item.plqf}</c:if><c:if test="${item.zzsd != null}">${item.zzsd}-</c:if>${item.jbno }</td>
			<td>${item.chno }</td>
			<td>${item.dhno }</td>
			<td>${item.abbr }</td>
			<td>${item.xpho }*${item.xpkn }*<c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}">${item.xpcn }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.face }</td>
			<td><c:if test="${item.fuzm != null && item.fuzm != \"\"}">${item.fuzm }/</c:if>${item.fufm }</td>
			<td>${item.zpzl }</td>
			<td>${item.kw }</td>
			<td>${item.ztbj }</td>
		</tr>
	</c:forEach>
</table>		
<f:paged/>

