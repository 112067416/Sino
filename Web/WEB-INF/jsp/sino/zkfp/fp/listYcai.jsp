<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${page.query }">
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="12%"/>
		<col width="15%"/>
		<col width="15%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="6%"/>
		<col width="6%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" onclick="checkAll(this);" /></th>
	   	<th>现品NO.</th>
		<th>原板客户</th>
		<th>尺寸</th>
		<th>规格</th>
		<th>表面</th>
		<th>重量</th>
		<th>等级</th>
		<th>堆场</th>
		<th>停止</th>
		<th>匹配?</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
	<tr xu.id="${item.jbno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
		<td align="center"><input type="checkbox" name="ids" value="${item.jbno }" onclick="doCheck(this);" zpzl="${item.zpzl }" /></td>
		<td><c:if test="${item.zzsd != null}">${item.zzsd}-</c:if>${item.jbno}</td>
		<td>${item.ycbr }</td>
		<td>${item.xpho }*${item.xpkn }*COIL</td>
		<td>${item.ggno }</td>
		<td>${item.face }</td>
		<td>${item.zpzl }</td>
		<td>${item.deng }</td>
		<td>${item.kw }</td>
		<td>${item.ztbj }</td>
		<td><sino:sfpp dhno="${dhno }" line="${line }" jbno="${item.jbno }" xpzk="${item.xpzk }" /></td>
	</tr>
	</c:forEach>
</table>
<f:paged callback="setChecked" />
</c:if>