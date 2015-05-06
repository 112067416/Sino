<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@
    taglib uri="/ui" prefix="ui" %><%@
    taglib uri="/f" prefix="f"%><%@
    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><table id="DataTbl" width="100%" align="center" class="pagination" style="margin-top: 10px;">
	<colgroup><col width="6%" /><col width="5%" /><col width="5%" /><col width="15%" /><col width="4%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="5%" /></colgroup>
	<tr>
		<th rowspan="2">单号</th>
		<th rowspan="2">指示<br />书号</th>
		<th rowspan="2">CoilNo</th>
		<th rowspan="2">分析项目</th>
		<th rowspan="2">采样<br />位置</th>
		<th rowspan="2">发行日期</th>
		<th colspan="2">SL</th>
		<th colspan="3">分析室</th>
		<th rowspan="2">分析<br />结果</th>
	</tr>
	<tr>
		<th>收单日期</th>
		<th>送样日期</th>
		<th>收单日期</th>
		<th>收样日期</th>
		<th>分析日期</th>
	</tr>
	<c:if test="${page != null }">
	<c:forEach var="item" items="${page.datas}" varStatus="stat">
		<tr xu.id="${item.id }" key1="${item.jbno }" <f:bool bool="item.end" trueText="#xu.menu=\"menuEnd\"" falseText="#xu.menu=\"menu\""/> ondblclick="toView(this);" title="双击时查看分析单" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.id }</td>
			<td>${item.zsno }</td>
			<td>${item.jbno }</td>
			<td>${item.fxxm }</td>
			<td>${item.cywz }</td>
			<td><f:v value="item.date" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:v value="item.slsd" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:v value="item.slsy" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:v value="item.fxsd" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:v value="item.fxsy" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:v value="item.fxsj" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:code code="#SINO_MKTFX_STAT" key="${item.stat }" color="#ff0000" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
	</c:if>
</table>
<f:paged/>