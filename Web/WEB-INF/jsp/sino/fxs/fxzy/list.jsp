<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
<col width="4%" /><col width="15%" /><col width="10%" /><col width="10%" />
<col width="12%" /><col width="18%" /><col width="13%" /><col width="18%" />
</colgroup>
<tr>
<th align="center"><input type="checkbox" xu.target="ids" /></th>
<th>记录日期</th>
<th>星期</th>
<th>班组</th>
<th>创建人</th>
<th>创建时间</th>
<th>修改人</th>
<th>修改时间</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr xu.id="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
<td align="center"><input type="checkbox" name="ids" value="${item.id}" /></td>
<td><fmt:formatDate value="${item.tbrq}" pattern="yyyy-MM-dd"/></td>
<td height="24">星期${item.tbxq }</td>
<td>${item.banzu }</td>
<td>${item.cjnm}</td>
<td><fmt:formatDate value="${item.cjsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<td>${item.xgnm}</td>
<td><fmt:formatDate value="${item.xgsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
</tr>
</c:forEach>
</table>
<f:paged/>
