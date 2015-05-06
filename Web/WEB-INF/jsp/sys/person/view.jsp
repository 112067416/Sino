<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" class="form">
	<colgroup>
		<col width="10%" />
		<col width="40%" />
		<col width="10%" />
		<col width="40%" />
	</colgroup>
	<tr>
		<th>姓名</th>
		<td>${item.name }</td>
		<th>性别</th>
		<td><f:kv value="item.sex" list="'1':'男','2':'女'" /></td>
	</tr>
	<tr>
		<th>机构</th>
		<td>${item.dept.name }</td>
		<th>身份证</th>
		<td>${item.idcard }</td>
	</tr>
	<tr>
		<th>地址</th>
		<td colspan="3">${item.address }</td>
	</tr>
	<tr>
		<th>电话</th>
		<td>${item.tele }</td>
		<th>手机</th>
		<td>${item.mobile }</td>
	</tr>
	<tr>
		<th>QQ</th>
		<td>${item.qq }</td>
		<th>MSN</th>
		<td>${item.msn }</td>
	</tr>
	<tr>
		<th>Email</th>
		<td>${item.email }</td>
		<th>排序</th>
		<td>${item.order }</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3">${item.remark }</td>
	</tr>
	<tr>
		<th>有效</th>
		<td><f:bool bool="item.valid" trueText="有效" falseText="无效" /></td>
		<th>工号</th>
		<td>${item.no }</td>
	</tr>
</table>