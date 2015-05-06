<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@
    taglib uri="/ui" prefix="ui" %><%@
    taglib uri="/f" prefix="f"%><%@
    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="hidden" name="type" value="slsy" />
<table width="96%" align="center" class="form">
<colgroup><col width="15%" /><col width="85%" /></colgroup>
<tr>
<th style="text-align: left; vertical-align: middle;">单号</th>
<td><ui:input name="id" readonly="true" maxlength="10" value="entity.id" /></td>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">采样项目</th>
<td><c:forEach varStatus="stat" var="item" items="${xms }"><span style="display: -moz-inline-box; display: inline-block; width: 135px; white-space: nowrap;"><input type="checkbox" xu.type="checklist" name="fxxms" value="${item }" onclick="setSlBz(this.form, this);" />${item }&nbsp;&nbsp;</span><c:if test="${stat.count % 4 == 0 }"><br /></c:if></c:forEach></td>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">备注</th>
<td><ui:textarea name="slBz" rows="4" cssStyle="width: 98%;" value="entity.slBz" /></td>
</tr>
</table>