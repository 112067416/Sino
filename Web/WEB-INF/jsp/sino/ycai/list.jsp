<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="6%"/>
		<col width="8%"/>
		<col width="4%"/>
		<col width="4%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="15%"/>
		<col width="5%"/>
		<col width="6%"/>
	</colgroup>
	<tr align="center">
		<th height="26" >
			<input type="checkbox" xu.target="ids" />
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
			重量(Kg)
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
			制品NO
		</th>
		<th>
			船名
		</th>
		<th>
			堆场
		</th>
		<th>
			状态
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr key1="${item.jbno}" xu.id="${item.jbno }" xu.menu="menu"  ondblclick="toModify(this);" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" value="${item.jbno}" /></td>
			<td>${item.zzsd}-${item.jbno }</td>
			<td>${item.xpho }*${item.xpkn }</td>
			<td>${item.yuny }</td>
			<td>${item.zpzl }</td>
			<td>${item.face }</td>
			<td>${item.pzno }</td>
			<td>${item.ybno }-${item.line }</td>
			<td>${item.zzsj }</td>
			<td>${item.zpno }</td>
			<td>${item.ship }</td>
			<td>${item.kw }</td>
			<td><f:code code="#SINO_YB_STAT" key="${item.stat}"  /> </td>
		</tr>
	</c:forEach>
</table>
<f:paged />