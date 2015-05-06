<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="12%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="9%"/>
		<col width="8%"/>
		<col width="7%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
		<th>制品No.</th>
		<th>送货单号</th>
		<th>订货号</th>
		<th>用户略称</th>
		<th>尺寸</th>
		<th>规格</th>
		<th>镀锡量</th>
		<th>重量</th>
		<th>张数</th>
		<th>发货日</th>
		<th>投诉日</th>
		<th>状态</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.id}"  xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td><input type="checkbox" name="ids" value="${item.id }" /></td>
		<td>${item.jbno}</td>
		<td>${item.shno}</td>
		<td>${item.dhno}-${item.line}</td>
		<td>${item.abbr}</td>
		<td>${item.xpho}*${item.xpkn}*<c:if test="${item.xpcn == null || item.xpcn==0.0 }">COIL</c:if><c:if test="${item.xpcn!=0.0}">${item.xpcn}</c:if></td>
		<td>${item.ggno}</td>
		<td>${item.sczm}/${item.scfm}</td>
		<td>${item.zpzl}</td>
		<td>${item.zshu}</td>
		<td><f:v value="item.chri" format="yyyy/MM/dd"/></td>
		<td><f:v value="item.tsri" format="yyyy/MM/dd"/></td>
		<td><f:code code="#SINO_TS_STAT" key="${item.stat }" /></td>
	</tr>
	</c:forEach>	
</table>
<f:paged />