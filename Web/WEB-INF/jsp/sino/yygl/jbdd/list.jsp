<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="3%"/><col width="6%"/><col width="4%"/><col width="4%"/><col width="3%"/>
	<col width="5%"/><col width="4%"/><col width="4%"/><col width="4%"/><col width="4%"/>
	<col width="4%"/><col width="9%"/><col width="13%"/><col width="3%"/><col width="5%"/>
	<col width="11%"/><col width="4%"/><col width="6%"/><col width="4%"/></colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" onclick="checkAll(this);" /></th>
		<th>订购<br />单号</th>
		<th>紧急<br />程度</th>
		<th>输入<br />编号</th>
		<th>表面</th>
		<th>硬度</th>
		<th>厚度A</th>
		<th>宽度</th>
		<th>厚度B</th> 
		<th>A-B</th> 
		<th>数量(吨)</th>
		<th>用户略称</th>
		<th>用途</th>
		<th>C/*</th>
		<th>附着量<br />(正/反)</th>
		<th>创建时间</th>
		<th>确认数</th>
		<th>状态</th>
		<th>是否锁</th>
	</tr>
	<tr>
		<td colspan="9">&nbsp;</td>
		<td>合计</td>
		<td>${tjVO.dhsl }</td>
		<td colspan="8">&nbsp;</td>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.id }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
			<td align="center"><input type="checkbox" name="id" value="${item.id }" onclick="doCheck(this);" /></td>
			<td>${item.jbdd.jbno }</td>
			<td>${item.isgi }</td>
			<td>${item.inpu }</td>
			<td>${item.face }</td>
			<td>${item.yuny }</td>
			<td><f:v value="item.houa" format="3" /></td>
			<td>${item.width }</td>
			<td><f:v value="item.houb" format="3" /></td>
			<td><f:v value="item.ajb" format="3" /></td>
			<td>${item.total }</td>
			<td>${item.abbr}</td>
			<td>${item.cond }</td>
			<td>${item.hanc }</td>
			<td><c:if test="${item.fuzm != null }">${item.fuzm }/</c:if>${item.fufm }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${item.conf }</td>
			<td><f:code code="#SINO_JBDD_STAT" key="${item.stat }" fixed="${fixed }"  /></td>
			<td><f:kv value="item.lock" list="'Y':'<span style='color: red'>已锁</span>','N':'未锁'" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />