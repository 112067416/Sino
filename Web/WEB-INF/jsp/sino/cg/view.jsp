<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" class="form">
	<colgroup>
		<col width="20%" />
		<col width="30%" />
		<col width="20%" />
		<col width="30%" />
	</colgroup>
	<tr>
		<th>供应商合同NO</th>
		<td>${item.htno}-${item.line}</td>
		<th>签约日期</th>
		<td><f:v value="item.qyri"/></td>						
	</tr>
	<tr>
		<th>制造商</th>
		<td><sys:codeLabel code="#012" key="item.zzsd" show="1" /> </td>
		<th>商社</th>
		<td><sys:codeLabel code="#010" key="item.ssno" show="1" /> </td>						
	</tr>
	<tr>
		<th>币种</th>
		<td><sys:codeLabel code="#013" key="item.thqf" show="1" /> </td>
		<th>合同日期</th>
		<td><f:v value="item.htdt"/></td>						
	</tr>
	<tr>
		<th>制造商规格略称</th>
		<td>${item.zzgg} </td>
		<th>中日达规格略称</th>
		<td>${item.zrgg}</td>						
	</tr>
	<tr>
		<th>用户略称</th>
		<td>${item.abbr} </td>
		<th>品种</th>
		<td>${item.pzno}</td>						
	</tr>
	<tr>
		<th>尺寸</th>
		<td>${item.houu}*${item.kuan} </td>
		<th>重量（吨）</th>
		<td>${item.htzl}</td>						
	</tr>
	<tr>
		<th>等级</th>
		<td>${item.fpdj} </td>
		<th>表面</th>
		<td>${item.face}</td>						
	</tr>
	<tr>
		<th>单价</th>
		<td>${item.htdj} </td>
		<th>内径</th>
		<td>${item.neij}</td>						
	</tr>
</table>