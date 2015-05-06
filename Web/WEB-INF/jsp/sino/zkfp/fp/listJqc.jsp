<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${page.query }">
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="12%"/>
		<col width="15%"/>
		<col width="6%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="13%"/>
		<col width="8%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="6%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox"  xu.target="ids"  /></th>
		<th>分配No.</th>
		<th>制品No.</th>
		<th>状况</th>
		<th>用户略称</th>
		<th>重量</th>
		<th>等级</th>
		<th>规格</th>
		<th>镀锡量</th>
		<th>堆场</th>
		<th>状态</th>
		<th>匹配?</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
	<tr xu.id="${item.jbno }"  xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
		<td align="center"><input type="checkbox" name="ids" value="${item.jbno }" /></td>
		<td>${item.fpno}</td>
		<td><c:if test="${item.plqf != null}">${item.plqf}</c:if><c:if test="${item.zzno != null}">${item.zzno}-</c:if>${item.jbno }</td>
		<td><f:code code="#XPZK" key="${item.yczk }" /></td>
		<td>${item.abbr }</td>
		<td>${item.zpzl }</td>
		<td>${item.deng }</td>
		<td>${item.xpho }*${item.xpkn }*<c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}">${item.xpcn }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
		<td>${item.fuzm }/${item.fufm }</td>
		<td>${item.duic }</td>
		<td><f:kv value="item.stat" list="'0':'未指示','1':'<font color=red>已指示</font>','2':'<font color=red>取消分配</font>'" /></td>
		<td><f:kv value="item.sfpp" list="'0':'<font color=red>不配匹</font>','1':'配匹'" /></td>
	</tr>
	</c:forEach>
</table>
<f:paged />
</c:if>