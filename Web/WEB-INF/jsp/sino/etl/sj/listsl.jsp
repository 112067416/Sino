<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui"%>
<%@ taglib uri="/f" prefix="f"%>
<%@ taglib uri="/sys" prefix="sys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<tr >
	   	<th>COIL No.</th>
		<th>指示书</th>	
		<th>订货NO</th>	
		<th>垫木方向</th>	
		<th>捆包形式</th>	
		<th>制品尺寸</th>
		<th>制品重量</th>
		<th>lot重量</th>
		<th>垫木个数</th>
		<th>生产日期</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr  xu.id="${item.jbno }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td>${item.jbno }</td>
			<td>${item.zsno}</td>
			<td>${item.dhno}</td>
			<td>${item.dmfx}</td>
			<td>${item.kbao}</td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose>
			</td>
			<td>${item.zpzl }</td>
			<td>${item.lotzl}</td>
			<td>${item.dmgs}</td>
			<td>${item.crea }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />

