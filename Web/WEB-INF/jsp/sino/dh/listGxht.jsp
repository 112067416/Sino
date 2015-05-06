<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
		<col width="5%"/>
		<col width="20%"/>
		<col width="35%"/>
		<col width="15%"/>
		<col width="15%"/>
		<col width="10%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
	   	<th>
			订货No.
		</th>
		<th>
			用户名称
		</th>
		<th>
			交货期
		</th>
		<th >
			合同重
		</th>
		<th >
			状态
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.dhno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td align="center"><input type="checkbox" name="ids" value="${item.dhno }"/></td>
			<td>${item.dhno }</td>
			<td>${item.name }</td>
			<td>${item.year }-${item.month }</td>
			<td>${item.htzl }</td>
			<td><c:choose><c:when test="${item.stat == \"1\" }">已制作</c:when><c:otherwise><span style="color: red;">未制作</span></c:otherwise></c:choose></td>
		</tr>
	</c:forEach>
</table>
<f:paged />