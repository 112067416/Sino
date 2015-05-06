<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="9%" /><col width="9%" /><col width="9%" /><col width="12%" /><col width="9%" /><col width="6%" /><col width="7%" />
	<col width="7%" /><col width="9%" /><col width="9%" /><col width="7%" /><col width="7%" /></colgroup>
	<tr>
		<th>Coil No.</th>
		<th>订货No.</th>
		<th>用户略称</th>
		<th>现品尺寸</th>
		<th>运用规格</th>
		<th>等级</th>
		<th>表面</th>
		<th>镀锡量</th>
		<th>生产日期</th>
		<th>硬度录入日期</th>
		<th>范围要求</th>
		<th>硬度值</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr xu.menu="menu" key1="${item.jbno}" ymin="${item.ymin }" ymax="${item.ymax }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> <sino:ifYing ying="${item.ying }" ymax="${item.ymax }" ymin="${item.ymin }">style="background-color: red;" title="不合格"</sino:ifYing> ondblclick="doGxyd(this);" onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td>${item.zzsd }-${item.jbno }</td>
			<td>${item.dhno }</td>
			<td>${item.abbr }</td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.yuny }</td>
			<td>${item.fpdj }</td>
			<td>${item.face }</td>
			<td>${item.fuzm }/${item.fufm }</td>
			<td><f:v value="item.sjsj" format="yyyy-MM-dd" /></td>
			<td><f:v value="item.ydsj" format="yyyy-MM-dd" /></td>
			<td><c:if test="${item.ymin != null && item.ymin !=0 }">${item.ymin }~</c:if>${item.ymax }</td>
			<td>${item.ying }</td>
		</tr>
	</c:forEach>
</table>
<f:paged/>