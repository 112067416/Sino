<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table id="DataTbl" align="center" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="25" /><col width="200" /><col /></colgroup>
	<tr>
		<th><input type="checkbox" xu.target="ids" onclick="coco.selAll(this);expandAll(this);" /></th>
		<th>名称</th>
		<th>类名(方法名)</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.className }" xu.menu="menu" ondblclick="toModifyClass(this)" style="background-color: #FFCCAA;font-size:16px; font-weight: bold;" onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td><input type="checkbox" name="ids" onclick="expand(this)" /></td>
			<td>${item.name }</td>
			<td>${item.className }</td>
		</tr>
		<c:if test="${item.methods != null}">
			<c:forEach varStatus="stat1" var="method" items="${item.methods }">
				<tr xu.id="${method.id}" xu.className="${item.className}" xu.menu="menu1" ondblclick="toModifyMethod(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" style="display: none;">
					<td colspan="2" style="padding-left:35px;">${method.name }</td>
					<td style="padding-left:10px;">${method.method }</td>
				</tr>
			</c:forEach>
		</c:if>
	</c:forEach>
</table>
<f:paged />