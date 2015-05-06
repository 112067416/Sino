<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="4%"/><col width="14%"/><col width="8%"/><col width="6%"/><col width="8%"/><col width="60%"/></colgroup>
<tr>
<th><input type="checkbox" xu.target="ids" /></th>
<th nowrap="nowrap">报表名</th>
<th nowrap="nowrap">现品状况</th>
<th nowrap="nowrap">年月</th>
<th nowrap="nowrap">尺寸.厚</th>
<th nowrap="nowrap">备注</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr xu.id="${item.id }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
<td align="center"><input type="checkbox" name="ids" value="${item.id }" /></td>
<td><f:code code="#SINO_BBM" key="${item.bbm }" /></td>
<td><f:code code="#XPZK" key="${item.xpzk }" /></td>
<td>${item.ny }</td>
<td><f:code code="#SPBH" key="${item.spbh }" /></td>
<td>${item.bz }</td>
</tr>
</c:forEach>
</table>
<f:paged />