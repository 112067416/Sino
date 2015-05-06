<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table id="SLTbl" width="96%" align="center" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="60px" /><col /><col width="60px" /><col width="60px" /><col width="120px" /><col width="120px" /><col width="220px" />
	</colgroup>
	<caption>
		SL生产线别配置
	</caption>
	<tr>
		<th>选定</th>
		<th>名称</th>
		<th>代码</th>
		<th>合格品PILE区分</th>
		<th>选别品PILE区分</th>
		<th>机构绑定</th>
	</tr>
	<c:forEach items="${sls }" var="item">
	<tr>
		<td align=center><input type="checkbox" name="slIds" /></td>
		<td><input type="hidden" name="id" value="${item.id }" />${item.name }</td>
		<td>${item.code }</td>
		<td>${item.qualified }</td>
		<td>${item.unqualified }</td>
		<td><f:v ql="select name from Dept where id=?" value="item.dept" /></td>
	</tr>
	</c:forEach>
</table>