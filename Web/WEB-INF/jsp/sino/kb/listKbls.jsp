<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup>
		<col width="4%"/>
		<col width="13%"/>
		<col width="10%"/>
		<col width="7%"/>
		<col width="8%"/>
		<col width="12%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="12%"/>
		<col width="7%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th><input type="checkbox" xu.target="ids" /></th>
	   	<th>PILE No</th>
	   	<th>订货No.</th>
	   	<th>出货指示书No.</th>
	    <th>用户略称</th>
		<th>现品尺寸</th>
		<th>制品重量</th>
		<th>捆包形式</th>
		<th>捆包日期</th>
		<th>停止标志</th>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
		<td colspan="2" style="text-align: right; font-size: 15px; font-weight: bold; padding-right: 10px;">合计重量：</td>
		<td style="font-size: 15px; font-weight: bold;">${zptjVO.zl }</td>
		<td colspan="2" style="text-align: right; font-size: 15px; font-weight: bold; padding-right: 10px;">合计包数：</td>
		<td style="font-size: 15px; font-weight: bold;">${zptjVO.su }</td>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr id0="${item.jbno}" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td align="center"><input type="checkbox" name="ids" value="${item.jbno}"/></td>
			<td>${item.plqf}${item.zzsd}-${item.jbno }</td>
			<td>${item.dhno }</td>
			<td>${item.chno }</td>
			<td>${item.abbr}</td>
			<td>${item.xpho}*${item.xpkn}*<c:choose><c:when test="${item.xpcn != null && item.xpcn > 0}">${item.xpcn }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
			<td>${item.zpzl }</td>
			<td>${item.kbao }</td>
			<td><f:v value="item.kbsd" format="yyyy-MM-dd"/></td>
			<td>${item.ztbj }</td>
		</tr>
	</c:forEach>
</table>
<f:paged />
