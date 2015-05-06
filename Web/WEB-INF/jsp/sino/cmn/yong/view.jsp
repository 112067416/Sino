<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="form">
	<colgroup>
		<col width="12%" />
		<col width="38%" />
		<col width="12%" />
		<col width="38%" />
	</colgroup>
	<tr>
		<th>用户代码</th>
		<td>${yong.user}</td>
		<th>用户名称</th>
		<td>${yong.name}</td>
	</tr>
	<tr>
		<th>用户略称</th>
		<td>${yong.abbr}</td>
		<th>用户中文名</th>
		<td>${yong.rsv1}</td>
	</tr>
	<tr>
		<th>所在地</th>
		<td colspan="3">${yong.addr}</td>
	</tr>
	<tr>
		<th>电话号码</th>
		<td>${yong.tele}</td>
		<th>国名</th>
		<td>${yong.guom}</td>
	</tr>
	<tr>
		<th>商社代码</th>
		<td>${yong.ssno}</td>
		<th>内外销</th>
		<td><f:kv value="yong.nwai" list="'1':'国内','2':'国外'"/></td>
	</tr>
	<tr>
		<th>信用额度</th>
		<td>${yong.xyed}</td>
		<th>检查证明书份数</th>
		<td>${yong.jcha }</td>
	</tr>
</table>
<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="pagination">
	<colgroup><col width="100%" /></colgroup>
	<tr>
		<th>送货地址</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${items }">
	<tr>
		<td>${item.addr }</td>
	</tr>
	</c:forEach>
</table>