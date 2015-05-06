<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="30"/>
		<col width="120"/>
		<col width="100"/>
		<col width="100"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="150"/>
		<col width="150"/>
		<col/>
	</colgroup>
	<tr align="center">
		<th height="26" >
			<input type="checkbox" onclick="checkAll(this);" />
		</th>
		<th>
			原材板卷NO
		</th>
		<th>
			尺寸
		</th>
		<th>
			运用规格
		</th>
		<th >
			重量
		</th>
		<th >
			表面
		</th>
		<th>
			品种
		</th>	
		<th>
			供应商合同NO
		</th>
		<th>
			制造商卷板NO
		</th>
		<th>
			船名
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="id" value="${item.jbno}" onclick="doCheck(this);" /></td>
			<td><c:if test="${item.zzsd != null}">${item.zzsd}-</c:if>${item.jbno }</td>
			<td>${item.xpho }*${item.xpkn }</td>
			<td>${item.yuny }</td>
			<td>${item.zpzl }</td>
			<td>${item.face }</td>
			<td>${item.pzno }</td>
			<td>${item.ybno }-${item.line }</td>
			<td>${item.zzsj }</td>
			<td>${item.ship }</td>
		</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />