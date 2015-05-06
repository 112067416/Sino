<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" align="center" cellpadding="0" cellspacing="0">
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<tr >
	   	<th>
			尺寸
		</th>
		<th>
			垫木方向
		</th>
		<th>
			捆包形式
		</th>
		<th>
			库位
		</th>
		<th >
			库存
		</th>
		<th>
			创建时间
		</th>
		<th>
			创建者
		</th>	
		
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>   onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td>${item.kuan }*${item.cang }</td>
			<td>${item.dmfx }</td>
			<td>${item.kbao }</td>
			<td>${item.kw }</td>
			<td>${item.dmgs }</td>
			<td>${item.crea}</td>
			<td>${item.dann}</td>
		</tr>
	</c:forEach>
</table>
<f:paged />