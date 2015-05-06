<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="3%" /><col width="19%" /><col width="5%" /><col width="25%" /><col width="6%" /><col width="7%" /><col width="11%" /><col width="7%" /><col width="7%" /><col width="10%" /></colgroup>
	<tr align="center">
		<th nowrap="nowrap"><input type="checkbox" xu.target="ids" /></th>
	   	<th nowrap="nowrap">用户名称</th>
	   	<th nowrap="nowrap">送货点</th>
	   	<th nowrap="nowrap">到达地点</th>
	   	<th nowrap="nowrap">运输行</th>
	   	<th nowrap="nowrap">运输方式</th>
	   	<th nowrap="nowrap">路段</th>
	   	<th nowrap="nowrap">运费单位</th>
	   	<th nowrap="nowrap">运费单价</th>
   		<th nowrap="nowrap">创建日期</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.id}"  xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this);" onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td><input type="checkbox" name="ids" value="${item.id }" /></td>
		<td>${item.name}</td>
	    <td>${item.shnm}</td>
		<td>${item.addr}</td>
		<td>${item.ysnm }</td>
		<td>${item.ysfm}</td>
		<td>${item.ld}</td>
		<td>${item.djdw }</td>
		<td>${item.yfdj }</td>
		<td><f:v value="item.crea" format="yyyy-MM-dd HH:mm" /></td>
	</tr>
	</c:forEach>
</table>
<f:paged />