<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" align="center" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="5%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="12%"/>
		<col width="12%"/>
		<col width="10%"/>
	</colgroup>
	<tr >
		<th>
			<input type="checkbox" xu.target="ids" />
		</th>
	   	<th  >
			制品号
		</th>
		<th >
			生产线别
		</th>
		<th>
			重量(kg)
		</th>
		<th>
			足
		</th>	
		<th >
			捆包形式
		</th>
		<th >
			堆场
		</th>
		<th >
			是否捆包
		</th>
		<th >
			捆包时间
		</th>
		<th >
			创建时间
		</th>
		<th >
			状态
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>   onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td align="center"><input type="checkbox" name="ids" value="${item.jbno }" /></td>
			<td>${item.jbno }</td>
			<td><f:kv value="item.slin" list="'T':'选别','V':'剪切二线','W':'剪切三线'" /></td>
			<td>${item.zpzl}</td>
			<td>${item.dmfx}</td>
			<td><f:kv value="item.kbao" list="'S21':'S21','S31':'S31'"  /></td>
			<td>${item.duic}</td>
			<td><f:kv value="item.duic" list="'F':'否','G':'是'"  /></td>
			<td><f:v value="item.kbsj" format="yyyy-MM-dd"/> </td>
			<td><f:v value="item.crea" format="yyyy-MM-dd"/> </td>
			<td><f:kv value="item.zt" list="'0':'初始','1':'<span style='color:red;'>已发货</span>'"  /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />