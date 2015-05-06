<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
 uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<table id="DataTbl" align="center" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="25" /><col width="200" /><col /></colgroup>
	<tr>
		<th><input type="checkbox" xu.target="sources" /></th>
		<th>名称</th>
		<th>类名(方法名)</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${items }">
		<tr style="background-color: #FFCCAA;font-size:16px; font-weight: bold;" onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td colspan="2">${item.name }</td>
			<td>${item.className }</td>
		</tr>
		<c:if test="${item.methods != null}">
			<c:forEach varStatus="stat1" var="method" items="${item.methods }">
				<tr onmouseover="overTr(this)" onmouseout="outTr(this)"> 
					<td><input type="checkbox" name="sources" value="${method.id }" <f:contains name="ids" object="method.id">checked="checked"</f:contains> /></td>
					<td style="padding-left:5px;">${method.name }</td>
					<td style="padding-left:10px;">${method.method }</td>
				</tr>
			</c:forEach>
		</c:if>
	</c:forEach>
</table>