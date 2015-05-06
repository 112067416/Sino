<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="3%"/><col width="8%"/><col width="12%"/><col width="5%"/><col width="3%"/><col width="5%"/><col width="12%"/><col width="4%"/><col width="7%"/><col width="7%"/><col width="9%"/><col width="9%"/><col width="10%"/><col width="6%"/></colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" onclick="checkAll(this);" /></th>
	   	<th>
			订货No.
		</th>
		<th>
			用户略称
		</th>
		<th>
			用途
		</th>
		<th>
			品种
		</th>	
		<th >
			规格
		</th>
		<th >
			订货尺寸
		</th>
		<th>
			表面
		</th>
		<th>
			上锁人
		</th>
		<th>
			解锁人
		</th>
		<th>
			仕样修订人
		</th>
		<th>
			仕样确认人
		</th>
		<th>
			仕样时间
		</th>
		<th >
			状态
		</th>	
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.dhno }-${item.line}" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入确认">
			<td align="center"><input type="checkbox" name="id" value="${item.dhno }-${item.line}" onclick="doCheck(this);" /></td>
			<td>${item.dhno }-${item.line }</td>
			<td>${item.abbr}</td>
			<td>${item.cond }</td>
			<td>${item.pzno }</td>
			<td>${item.ggno }</td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.face }</td>
			<td>${item.lknm }</td>
			<td>${item.jsnm }</td>
			<td>${item.xdnm }</td>
			<td>${item.synm }</td>
			<td><f:v value="item.sydt" format="yyyy-MM-dd HH:mm" /></td>
			<td><f:code code="#SINO_DH_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />