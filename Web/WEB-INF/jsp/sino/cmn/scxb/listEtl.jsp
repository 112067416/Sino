<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table id="ETLTbl" width="96%" align="center" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<caption>
		ETL生产线别配置
	</caption>
	<colgroup>
		<col width="60" /><col /><col width="100" /><col width="220" />
	</colgroup>
	<tr>
		<th>选定</th>
		<th>名称</th>
		<th>线别代码</th>
		<th>机构绑定</th>
	</tr>
	<c:forEach items="${etls }" var="item">
	<tr>
		<td align=center><input type="checkbox" name="etlIds" /></td>
		<td><input type="hidden" name="id" value="${item.id }" />${item.name }</td>
		<td>${item.code }</td>
		<td><f:v ql="select name from Dept where id=?" value="item.dept" /></td>
	</tr></c:forEach>
</table>