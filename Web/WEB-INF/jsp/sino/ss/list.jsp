<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/f" prefix="f" %><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table width="100%" align="center" class="pagination">
	<colgroup><col width="12%" /><col width="12%" /><col width="6%" /><col width="8%" /><col width="12%" />
		<col width="5%" /><col width="8%" /><col width="8%" /><col width="5%" /><col width="5%" />
		<col width="5%" /><col width="7%" /><col width="7%" /></colgroup>
	<tr>
		<th>PILE No.</th>
		<th>制品尺寸</th>
		<th>镀锡量</th>
		<th>订货No.</th>
		<th>用户略称</th>
		<th>等级</th>
		<th>主缺陷</th>
		<th>包装张数</th>
		<th>重量</th>
		<th>检定员</th>
		<th>记数员</th>
		<th>紧急</th>
		<th>剩余张数</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
	<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td><c:if test="${item.plqf != null}">${item.plqf}</c:if><c:if test="${item.zzsd != null}">${item.zzsd}-</c:if>${item.jbno }</td>
		<td>${item.size }</td>
		<td>${item.fuzm }/${item.fufm }</td>
		<td>${item.dhno }</td>
		<td>${item.abbr }</td>
		<td>${item.deng }</td>
		<td>${item.qqdm }</td>
		<td>${item.bzzs }</td>
		<td>${item.zpzl }</td>
		<td>${item.jdyn }</td>
		<td>${item.jsyn }</td>
		<td>
		<f:kv value="item.jinj" list="'0':'否','1':'<font color='red'>是</font>'"  />
		</td>
		<td>${item.zshu }</td>
	</tr>
	</c:forEach>
</table>
<f:paged/>