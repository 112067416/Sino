<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="10%"/>
		<col width="9%"/>
		<col width="14%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
	</colgroup>
	<tr align="center">
		<th>
			<input type="checkbox" xu.target="ids" />
		</th>
		<th height="26" >
			原材板卷NO
		</th>
		<th height="26" >
			制造商卷板NO
		</th>
		<th>
			尺寸
		</th>
		<th>
			表面
		</th>
	   	<th >
			C
		</th>
		<th>
			Si
		</th>
		<th>
			Mn
		</th>	
		<th>
			P
		</th>
		<th>
			S
		</th>
		<th>
			YP
		</th>
		<th>
			硬度
		</th>
		<th >
			吹练No.
		</th>
		<th>
			状态
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.ycaiTp.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击进入修改">
			<td align="center"><input type="checkbox" name="ids" value="${item.ycaiTp.jbno}" /></td>
			<td><c:if test="${item.ycaiTp.zzsd != null}">${item.ycaiTp.zzsd}-</c:if>${item.ycaiTp.jbno }</td>
			<td>${item.zzsj }</td>
			<td>${item.ycaiTp.xpho }*${item.ycaiTp.xpkn }</td>
			<td>${item.ycaiTp.face }</td>
			<td>${item.cfcc }</td>
			<td>${item.cfsi }</td>
			<td>${item.cfmn }</td>
			<td>${item.cfpp }</td>
			<td>${item.cfss }</td>
			<td>${item.yp }</td>
			<td>${item.ying }</td>
			<td>${item.chui }</td>
			<td><f:code code="#SINO_YB_STAT" key="${item.ycaiTp.stat}"  /> </td>
		</tr>
	</c:forEach>
</table>
<f:paged />
