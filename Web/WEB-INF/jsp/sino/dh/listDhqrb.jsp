<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
		<col width="4%"/>
		<col width="8%"/>
		<col width="11%"/>
		<col width="12%"/>
		<col width="8%"/>
		<col width="5%"/>
		<col width="9%"/>
		<col width="6%"/>
		<col width="11%"/>
		<col width="4%"/>
		<col width="5%"/>
		<col width="6%"/>
		<col width="11%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
	   	<th>
			订货No.
		</th>
		<th>
			创建时间
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
			修改人
		</th>
		<th >
			修改时间
		</th>	
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.dhno }-${item.line}" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="print(this)" onmouseover="overTr(this)" onmouseout="outTr(this)" title="右键进入打印">
			<td align="center"><input type="checkbox" name="ids" value="${item.dhno }${item.line}"/></td>
			<td>${item.dhno }-${item.line}</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd HH:mm" /></td>
			<td>${item.abbr}</td>
			<td>${item.cond }</td>
			<td>${item.pzno }</td>
			<td><f:v value="item.jhqi" format="yyyy-MM-dd"/></td>
			<td>${item.ggno }</td>
			<td>${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.face }</td>
			<td>${item.htzl }</td>
			<td>${item.xgnm }</td>
			<td><f:v value="item.upda" format="yyyy-MM-dd HH:mm"/></td>
		</tr>
	</c:forEach>
</table>
<f:paged />