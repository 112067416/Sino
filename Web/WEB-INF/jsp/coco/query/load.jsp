<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>公共查询- ${ entry.name}</title>
<%@include file="../../global/header.jsp" %>
<script type="text/javascript" src="../../js/calendar.js"></script>
<c:out value="${iniScript}" escapeXml="false"></c:out>
</head>
<body>
<table align="left" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td ><input type="image" id="queryButton" onclick="cocoquery.query(event)" src="<c:url value="/images/main/drop_button_query.gif" />" accept="q" /></td>
		<c:if test="${entry.excel }">
		<td ><input type="image" id="excelButton" onclick="cocoquery.excel(event)" src="<c:url value="/images/main/drop_button_excel.gif" />" accesskey="e" /></td>
		</c:if>
		<td id="queryMetaShower" style="display: none;"><input type="image" onclick="cocoquery.showOrHideBlock(event, 'queryMetaShower', 'queryMetaBlock')" src="<c:url value="/images/main/drop_button_meta.gif" />" /></td>
		<td id="queryParamShower" style="display: none;"><input type="image" onclick="cocoquery.showOrHideBlock(event, 'queryParamShower', 'queryParamBlock')" src="<c:url value="/images/main/drop_button_param.gif" />" /></td>
	</tr>
</table><br/>
<table width="100%"  border="0" cellpadding="0" cellspacing="0"align="center">
	<colgroup><col width=30 /><col width=25 /><col /></colgroup>
	<tr id="queryMetaBlock">
		<td valign="middle" nowrap="nowrap">
		<span style="font-size:14px;">显示<br/>设置</span>
		</td>
		<td valign="middle">
		<input type="image" src="<c:url value="/images/main/icon_hide.gif" />" alt="隐藏显示设置" onclick="cocoquery.showOrHideBlock(event, 'queryMetaShower', 'queryMetaBlock')" />
		<input type="image" id="PreMetaAreaOpt" show="0" src="<c:url value="/images/main/icon_toright.gif" />" alt="备选字段(显示/隐藏)" onclick="cocoquery.showOrHideArea(event, 'PreMetaAreaOpt', 'queryPreMetaArea');" />
		</td>
		<td valign="top" id="queryMetas" class="query-metas"></td>
	</tr>
	<tr id="queryParamBlock">
		<td valign="middle" nowrap="nowrap">
		<span style="font-size:14px;">条件<br/>设置</span>
		</td>
		<td valign="middle">
		<input type="image" src="<c:url value="/images/main/icon_hide.gif" />" alt="隐藏条件设置" onclick="cocoquery.showOrHideBlock(event, 'queryParamShower', 'queryParamBlock')" />
		<input type="image" id="PreParamAreaOpt" show="0" src="<c:url value="/images/main/icon_toright.gif" />" alt="备选字段(显示/隐藏)" onclick="cocoquery.showOrHideArea(event, 'PreParamAreaOpt', 'queryPreParamArea');" />
		</td>
		<td valign="top" id="queryParams" class="query-params"></td>
	</tr>
</table>
<div id="queryResult"></div>
<div id="queryPreMetaArea" class="query-pre-area">
<div class="query-title move" xu.move="block" xu.move.target="queryPreMetaArea">备选字段</div>
<div id="queryPreMetas" class="query-pre-metas">
<span style="color: #FF0000; cursor: pointer;" ondblclick="cocoquery.selectAllMeta(event)"><b>&nbsp;&nbsp;全部&nbsp;&nbsp;</b></span>
<c:forEach varStatus="stat" var="meta" items="${ entry.metas}">
<span xu.query.meta="true" xu.drag="block" xu.drag.area="queryMetas,queryPreMetas" class="query-meta" xu.query.id="${meta.id }" xu.index="${stat.index }">&nbsp;${meta.label }&nbsp;
</span>
</c:forEach>
</div>
</div>
<div id="queryPreParamArea" class="query-pre-area">
<div class="query-title move" xu.move="block" xu.move.target="queryPreParamArea">预选条件</div>
<div id="queryPreParams" class="query-pre-params">
<c:forEach var="entryParam" items="${entry.params}">
<span xu.query.param="true"
	xu.query.id="<c:out value="${entryParam.id }"/>"
	xu.query.opts="<c:forEach var="opt" items="${entryParam.allowOpts }"><c:out value="${opt.type }" />,</c:forEach>"
	xu.query.options="<c:forEach var="option" items="${entryParam.options }"><c:out value="${option.key }" />@#&#<c:out value="${option.value }" />@&#&</c:forEach>"
	xu.query.sqlOption="<c:out value="${entryParam.sqlOption }" />"
	xu.drag="block"
	xu.drag.clonable="" 
	xu.drag.area="queryParams" 
	class="query-param"
	xu.type="param">
	&nbsp;<c:out value="${entryParam.label}" />&nbsp;
</span>
</c:forEach>
</div>
</div>
</body>
</html>