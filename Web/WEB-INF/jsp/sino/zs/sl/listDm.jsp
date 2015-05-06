<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" align="center" class="pagination">
<colgroup>
	<col width="5%" /><col width="6%" /><col width="8%" /><col width="4%" /><col width="9%" /><col width="5%" />
	<col width="5%" /><col width="4%" /><col width="11%" /><col width="4%" /><col width="9%" /><col width="4%" />
	<col width="5%" /><col width="5%" /><col width="5%" /><col width="11%"/></colgroup>
	<tr>
     	<th><input type="checkbox" xu.target="ids" /></th>
		<th>指示No.</th>
		<th>订货No.</th>
		<th>S/C</th>
		<th>用户略称</th>
		<th>运用规格</th>
		<th>等级</th>
		<th>表面</th>
		<th>尺寸</th>
		<th>附着量</th>
		<th>总重量</th>
		<th>作成时间</th>
		<th>紧急</th>
		<th>垫木状态</th>
		<th >状态</th>
		<th>备注</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas}">
		<tr xu.menu="menu" xu.id="${item.zsno }" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" <c:if test="${item.wsjz>0}">style="background-color:#FFD39B;"</c:if> <c:if test="${item.vsjz>0}">style="background-color:#7FFFD4;"</c:if> >
			<td align="center"><input type="checkbox" name="ids" value="${item.zsno }" /></td>
			<td>${item.zsno}</td>
			<td>${item.dhno }</td>
			<td><sys:codeLabel code="#SINO_CAOZ" key="item.caoz"/></td>
			<td>${item.abbr }</td>
			<td>${item.yuny }</td>
			<td>${item.fpdj }</td>
			<td>${item.face }</td>
			<td>${item.zpcc }</td>
			<td>${item.fugm }</td>
			<td>${item.zzl }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd" /></td>
			<td><c:if test="${item.jinjzjs==item.zjs}"><font color='red'>是</font></c:if></td>
			<td><c:if test="${item.sfdmzjs==item.zjs}"><font color='red'>已完成</font></c:if></td>
			<td align="center"><font size="4" color="#00AA00">${item.ywcjs }</font> / ${item.zjs }</td>
			<td>${item.remk }</td>
		</tr>
	</c:forEach>
</table>
<f:paged/>