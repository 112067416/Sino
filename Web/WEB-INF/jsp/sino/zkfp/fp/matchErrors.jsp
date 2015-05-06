<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="form" width="98%" align="center">
	<tr>
		<td>
			<table class="form" width="100%">
			<colgroup><col width="15%" /><col width="15%" /><col width="15%" /><col width="15%" /><col width="40%" /></colgroup>
			<caption style="font-size: 25px;">ERROR-LIST</caption>
			  <tr>
			    <th style="text-align: left;">现品No.</th>
			    <td>${jbno }</td>
			    <td colspan="3">&nbsp;</td>
			  </tr>
			  <tr>
			    <th colspan="2" style="text-align: left;">分配诸元 CHECK ERROR</th>
			    <td colspan="3">&nbsp;</td>
			  </tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table border="1" bordercolor="#ffffff" class="form" width="100%" >
			<c:forEach varStatus="stat" var="perror" items="${errors }">
				<tr>
					<th nowrap="nowrap" style="text-align: left;">项目名称</th>
					<c:forEach varStatus="stat" var="error" items="${perror.errors }">
					<c:set var="size" value="${stat.count }" scope="page" />
					<th nowrap="nowrap" style="text-align: center;">${error.name }</th>
					</c:forEach>
					<c:if test="${pageSize > size }">
					<c:forEach begin="${size }" end="${pageSize - 1 }">
					<td nowrap="nowrap">&nbsp;</td>
					</c:forEach>
					</c:if>
				</tr>
				<tr>
					<th nowrap="nowrap" style="text-align: left;">现品情报</th>
					<c:forEach varStatus="stat" var="error" items="${perror.errors }">
					<c:set var="size" value="${stat.count }" scope="page" />
					<td nowrap="nowrap" style="text-align: center;">${error.field1 }</td>
					</c:forEach>
					<c:if test="${pageSize > size }">
					<c:forEach begin="${size }" end="${pageSize - 1 }">
					<td nowrap="nowrap">&nbsp;</td>
					</c:forEach>
					</c:if>
				</tr>
				<tr>
					<th nowrap="nowrap" style="text-align: left;">订货情报</th>
					<c:forEach varStatus="stat" var="error" items="${perror.errors }">
					<c:set var="size" value="${stat.count }" scope="page" />
					<td nowrap="nowrap" style="text-align: center;">${error.field2 }</td>
					</c:forEach>
					<c:if test="${pageSize > size }">
					<c:forEach begin="${size }" end="${pageSize - 1 }">
					<td nowrap="nowrap">&nbsp;</td>
					</c:forEach>
					</c:if>
				</tr>
				<tr height="20"><td colspan="${pageSize + 1 }">&nbsp;</td></tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>