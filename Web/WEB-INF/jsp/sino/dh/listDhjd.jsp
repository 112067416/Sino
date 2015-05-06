<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
<colgroup>
		<col width="3%"/>
		<col width="12%"/><col width="7%"/><col width="7%"/><col width="5%"/><col width="7%"/>
		<col width="10%"/><col width="6%"/><col width="6%"/><col width="4%"/><col width="4%"/>
		<col width="4%"/><col width="4%"/><col width="5%"/><col width="5%"/><col width="5%"/>
		<col width="6%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th rowspan="2"><input type="checkbox" xu.target="ids" /></th>
		
	   	<th rowspan="2">用户名称</th>
	   	<th rowspan="2">交货期</th>
		<th rowspan="2">订货No.</th>
		<th rowspan="2">运用<br />规格</th>
		<th rowspan="2">附着量</th>
		
		<th rowspan="2">订货尺寸</th>	
		<th rowspan="2">订货量</th>
		<th rowspan="2">分配量</th>
		<th colspan="2">ETL</th>
		
		<th colspan="2">SL</th>
		<th rowspan="2">出货<br />实绩</th>
		<th rowspan="2">捆包<br />实绩</th>
		<th rowspan="2">业务员</th>
		<th rowspan="2">状态</th>
	</tr>
	<tr>
		<th>未镀</th>
		<th>实绩</th>
		<th>未剪</th>
		<th>实绩</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> key1="${item.dhno }-${item.line }" xu.menu="menu" ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
			<td align="center"><input type="checkbox" name="ids" value="${item.dhno }-${item.line}" /></td>
			<td>${item.abbr }</td>
			<td><f:v value="item.jhqi" format="yyyy-MM-dd" /></td>
			<td>${item.dhno }-${item.line }</td>
			<td>${item.yuny }</td>
			<td>${item.fudw } ${item.fuzm }/${item.fufm }</td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}"><f:v value="item.cang" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.htzl" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.fpln" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.etlw" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.etlh" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.slw" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.slhg" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.chus" format="#,##0.000" /></td>
			<td style="text-align: right; padding-right: 5px; font-size: 13px;"><f:v value="item.kbus" format="#,##0.000" /></td>
			<td>${item.ddnm }</td>
			<td><f:code code="#SINO_DH_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />