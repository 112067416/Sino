<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<tr>
	   	<th>COIL No.</th>
		<th>生产线</th>	
		<th>指示值</th>
		<th>实绩值</th>
		<th>等级</th>
		<th>表面</th>
		<th>制品尺寸</th>
		<th>实际重量</th>
		<th>打印状态</th>
		<th>生产日期</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr  xu.id="${item.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
			
			<td>${item.jbno }</td>
			<td><f:v value="item.elin" ql="select name from Scxbpz where code=?"/></td>
			<td>${item.fuzm }/${item.fufm }</td>
			<td>${item.sczm }/${item.scfm }</td>
			<td>${item.deng }</td>
			<td>${item.face }</td>
			<td>${item.houu }*${item.kuan }*COIL</td>
			<td>${item.zpzl }</td>
			<td><f:kv value="item.lody" list="'0':'未打印','1':'<font color='red'>已打印</font>'"  /></td>
			<td>${item.crea }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />
