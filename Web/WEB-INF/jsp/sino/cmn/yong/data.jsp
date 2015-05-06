<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="3%" />
<col width="10%" /><col width="6%" /><col width="10%" /><col width="15%" /><col width="12%" /><col width="6%" /><col width=7%"" /><col width="6%" /><col width="11%" /><col width="7%" /><col width="7%" /></colgroup>
<tr>
<th><input type="checkbox" onclick="checkAll(this);" /></th>
<th>所属区域</th>
<th>用户代码</th>
<th>用户略称</th>
<th>用户中文名</th>
<th>电话号码</th>
<th>国名</th>
<th>商社代码</th>
<th>内外销</th>
<th>信用额度</th>
<th>业务员</th>
<th>是否有效</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr id0="${item.user }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
<td align="center"><input type="checkbox" name="id" value="${item.user }" onclick="doCheck(this);" /></td>
<td><f:code code="#SINO_REGION" key="${item.region }" /></td>
<td>${item.user }</td>
<td>${item.abbr }</td>
<td>${item.rsv1 }</td>
<td>${item.tel1 }</td>
<td>${item.guom }</td>
<td>${item.ssno }</td>
<td><f:kv value="item.nwai" list="'1':'国内','2':'国外'"/></td>
<td style="text-align: right; padding-right: 10px;"><f:v value="item.xyed" format="#,##0.00" /></td>
<td>${item.ddnm }</td>
<td><c:choose><c:when test="${item.valid != null && item.valid == \"Y\" }">有效</c:when><c:otherwise><span style="color: red;">无效</span></c:otherwise>  </c:choose></td>
</tr>
</c:forEach>
</table>
<f:paged callback="setChecked" />