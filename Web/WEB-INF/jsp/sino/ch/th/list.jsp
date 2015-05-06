<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /></colgroup>
	<tr>
		<th><input type="checkbox" xu.target="ids" /></th>
		<th>制品号</th>
		<th>订货号</th>
		<th>用户略称</th>
		<th>尺寸</th>
		<th>规格</th>
		<th>镀锡量</th>
		<th>重量</th>
		<th>张数</th>
		<th>发货日期</th>
		<th>退货日期</th>
		<th>发票号</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.id}"  xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td><input type="checkbox" name="ids" value="${item.id }" /></td>
		<td>${item.tsTp.jbno}</td>
		<td>${item.tsTp.dhno}-${item.tsTp.line}</td>
		<td>${item.tsTp.abbr}</td>
		<td>${item.tsTp.xpho}*${item.tsTp.xpkn}*<c:choose><c:when test="${item.tsTp.xpcn != null && item.tsTp.xpcn > 0}">${item.tsTp.xpcn }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
		<td>${item.tsTp.ggno}</td>
		<td>${item.tsTp.sczm}/${item.tsTp.scfm}</td>
		<td>${item.tsTp.zpzl}</td>
		<td>${item.tsTp.zshu}</td>
		<td><f:v value="item.tsTp.chri" format="yyyy/MM/dd"/></td>
		<td><f:v value="item.tsTp.thri" format="yyyy/MM/dd"/></td>
		<td>${item.yfph }</td>
	</tr>
	</c:forEach>	
</table>
<f:paged />