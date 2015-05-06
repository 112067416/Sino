<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
		<col width="4%"/>
		<col width="10%"/>
		<col width="19%"/>
		<col width="8%"/>
		<col width="5%"/>
		<col width="10%"/>
		<col width="7%"/>
		<col width="15%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" onclick="checkAll(this);" /></th>
	   	<th>
			订货No.
		</th>
		<th>
			用户名称
		</th>
		<th>
			用途
		</th>
		<th>
			品种
		</th>	
		<th>
			交货期
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
		<th >
			合同重
		</th>
		<th >
			状态
		</th>	
	</tr>
		<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.dhno }-${item.line}"  <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)">
			<td align="center"><input type="checkbox" name="ids" value="${item.dhno }-${item.line}" onclick="doCheck(this);" /></td>
			<td>${item.dhno }-${item.line}</td>
			<td>${item.name }</td>
			<td>${item.cond }</td>
			<td>${item.pzno }</td>
			<td><f:v value="item.jhqi" format="yyyy-MM-dd"/></td>
			<td>${item.ggno }</td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.face }</td>
			<td>${item.htzl }</td>
			<td><f:code code="#SINO_DH_STAT" key="${item.stat }" color="red" fixed="${fixed }" /></td>
		</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />
