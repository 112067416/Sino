<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="4%"/><col width="5%"/><col width="10%"/><col width="6%"/>
	<col width="7%"/><col width="7%"/><col width="7%"/><col width="7%"/><col width="7%"/><col width="8%"/><col width="8%"/><col width="8%"/><col width="8%"/><col width="8%"/></colgroup>
	<tr >
		<th>
			<input type="checkbox" xu.target="ids" />
		</th>
	   	<th height="26">
			板厚
		</th>
		<th>
			板宽
		</th>
		<th>
			材质
		</th>
		<th>
			Base
		</th>
		<th>
			Extra
		</th>	
		<th >
			FOB
		</th>
		<th >
			运费
		</th>
		<th >
			Fukuyama<br />Freight
		</th>
		<th>
			CFR
		</th>
		<th>
			利息利率
		</th>
		<th>
			天数
		</th>
		<th>
			采购单价
		</th>
		<th>
			创建日期
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this);"  onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击修改">
			<td align="center"><input type="checkbox" name="ids" value="${item.id }" /></td>
			<td>${item.xpho }</td>
			<td><f:code code="#SINO_KNFW" key="${item.knfw }" /></td>
			<td>${item.yuny }</td>
			<td>${item.base }</td>
			<td>${item.extra }</td>
			<td>${item.fob }</td>
			<td>${item.yf }</td>
			<td>${item.freight }</td>
			<td>${item.cfr }</td>
			<td><f:v value="item.lxll" format="6" /></td>
			<td>${item.days }</td>
			<td>${item.cgdj }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />