<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
	</colgroup>
	<tr class="page-head" align="center">
		<th>订货号</th>
		<th>制品号</th>
		<th>规格代码</th>
		<th>规格略称</th>
	   	<th>附着量</th>
		<th>尺寸(宽*厚*长)</th>
		<th>重量</th>
		<th>堆场</th>	
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
		<td >${item.zng1Tp.dhno }-${item.zng1Tp.line }</td>
		<td >${item.jbno}</td>
		<td >${item.zng1Tp.ggno }</td>
		<td >${item.zng1Tp.ggnm}</td>
		<td >${item.zng1Tp.fudw}•${item.zng1Tp.fuzm}•${item.zng1Tp.fufm}</td>
		<td >${item.zng1Tp.houu}×&nbsp;${item.zng1Tp.kuan}×&nbsp;<c:if test="${item.zng1Tp.cang == null || item.zng1Tp.cang==0.0 }">COIL</c:if><c:if test="${item.zng1Tp.cang!=0.0}">${item.zng1Tp.cang}</c:if></td>
		<td >${item.zpzl}</td>
		<td >${item.dcno}</td>
		</tr>
    </c:forEach>
</table>
<f:paged />