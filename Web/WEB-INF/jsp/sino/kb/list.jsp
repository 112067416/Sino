<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
		<col width="12%"/>
		<col width="8%"/>
		<col width="9%"/>
		<col width="14%"/>
		<col width="6%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="6%"/>
	</colgroup>
	<tr class="page-head" align="center">
	   	<th>PILE No</th>
	   	<th>订货No.</th>
	   	<th>用户略称</th>
		<th>现品尺寸</th>
		<th>等级</th>
		<th>镀锡量</th>
		<th>制品重量</th>
		<th>捆包形式</th>
		<th>堆场</th>
		<th>ST</th>
		<th>紧急</th>
		<th>现品保留</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td>${item.plqf}${item.zzsd}-${item.jbno }</td>
			<td>${item.dhno }</td>
			<td>${item.abbr }</td>
			<td>${item.xpho}*${item.xpkn}*<c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}">${item.xpcn }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.deng }</td>
			<td>${item.sczm}/${item.scfm }</td>
			<td>${item.zpzl }</td>
			<td>${item.kbao }</td>
			<td>${item.kw}</td>
			<td>${item.ztbj }</td>
			<td><f:kv value="item.sfjj" list="'0':'否','1':'<font color=red>是</font>'"/></td>
			<td>${item.blbj }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />
