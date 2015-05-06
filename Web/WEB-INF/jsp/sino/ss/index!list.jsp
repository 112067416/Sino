<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="DataTbl" width="96%" align="center" style="margin-top: 10px;"
	class="pagination">
	<colgroup>
		<col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col />
		<col />
	</colgroup>
	<tr>
		<th>PILE No.</th>
		<th>订货号</th>
		<th>生产线</th>
		<th>班</th>
		<th>组</th>
		<th>制品尺寸</th>
		<th>产量</th>
		<th>规格</th>
		<th>等级</th>
		<th>枚数</th>
		<th>制品<br/>重量</th>
		<th>实际<br/>重量</th>
		<th>硬度</th>
		<th>作成时间</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr xu.menu="menu" xu.id="${item.jbno}" ondblclick="doView();" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.plqf }${item.zzno }-${item.jbno}</td>
			<td>${item.dhno }</td>
			<td><f:v value="item.slin" ql="select name from Scxbpz where code=?"/></td>
			<td><sys:codeLabel code="#SINO_BAN" key="item.ban" show="1" /></td>
			<td><sys:codeLabel code="#SINO_ZU" key="item.zu" /></td>
			<td>${item.houu }*${item.kuan }*${item.cang }</td>
			<td>${item.chan }</td>
			<td>${item.ggno }</td>
			<td>${item.deng }</td>
			<td>${item.zshu }</td>
			<td>${item.zpzl }</td>
			<td>${item.sjzl }</td>
			<td>${item.ying }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd HH:mm" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged/>