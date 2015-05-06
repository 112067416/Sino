<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table align="center" style="padding-top: 10;" width="1080" cellspacing="0" cellpadding="0" border="0" >
	<caption><span style="font-size: 28px; font-weight: bold;">库位标签</span></caption>
	<c:forEach varStatus="stat" var="item" items="${entities }">
		<tr>
			<td height="50"><c:if test="${item.bq1 != null }"><img
				src="<%=request.getContextPath()%>/barcode?msg=${item.bq1 }&type=code39"
				height="50" width="200" /></c:if></td>
			<td><c:if test="${item.bq2 != null }"><img
				src="<%=request.getContextPath()%>/barcode?msg=${item.bq2 }&type=code39"
				height="50" width="200" /></c:if></td>
			<td><c:if test="${item.bq3 != null }"><img
				src="<%=request.getContextPath()%>/barcode?msg=${item.bq3 }&type=code39"
				height="50" width="200" /></c:if></td>
			<td><c:if test="${item.bq4 != null }"><img
				src="<%=request.getContextPath()%>/barcode?msg=${item.bq4 }&type=code39"
				height="50" width="200" /></c:if></td>
			<td><c:if test="${item.bq5 != null }"><img
				src="<%=request.getContextPath()%>/barcode?msg=${item.bq5 }&type=code39"
				height="50" width="200" /></c:if></td>
		</tr>
	</c:forEach>
</table>