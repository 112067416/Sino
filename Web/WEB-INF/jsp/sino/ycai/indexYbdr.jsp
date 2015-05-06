<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/upload.js"></script>
	<SCRIPT type="text/javascript">
	<!--
		//提交
		function doSubmit() {
			if(!confirm("是否确定导入")) return false;
			var oForm = document.forms["DataForm"];
			var ship = oForm.elements["ship"].value;
			if(ship == null || ship.length == 0) {
				alert("船名不能为空");
				oForm.elements["ship"].focus();
				return;
			}
			var prefix = oForm.elements["prefix"].value;
			if(prefix == null || prefix.length == 0) {
				alert("钢卷号.前缀不能为空");
				oForm.elements["prefix"].focus();
				return;
			}
			var postContent = coco.parseParams([{name:"ship", value:ship}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0 && !confirm("该船名在原板库中已存在,是否确定再次导入?")) {
					return;
				}
				coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
				cocoform.submit(oForm, function() {
					coco.hideAlert();
					if(this.code < 0) {
						coco.alert(this.msg);
					} else {
						alert("操作成功");
					}
				});
			};
			ajax.post("checkShip.do", postContent);
		}
	//-->
	</SCRIPT>
</head>
<body>
<ui:page title="原板导入 ">
	<form name="DataForm" xu.ajax="" method="post" action="ybdr.do" >
		<table width="98%" align="center" style="margin: 20px auto;" class="form">
		<colgroup><col width="10%" /><col width="40%" /><col width="10%" /><col width="40%" /></colgroup>
		<tr>
			<th style="text-align: left;">船名</th>
			<td><ui:input name="ship" cssClass="normal" title="船名" maxlength="20" /></td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<th style="text-align: left;">装船日</th>
			<td><ui:date name="blny" cssClass="normal" /></td>
			<th style="text-align: left;">钢卷号.前缀</th>
			<td><ui:int name="prefix" value="prefix" cssClass="normal" title="钢卷号.前缀" maxlength="4" required="true" /></td>
		</tr>
		<tr>
			<th style="text-align: left;">导入文件</th>
			<td colspan="3"><ui:attach id="ycai" attachName="attachId" label="上传Excel" width="100" single="true" typeFilter="#excel#" /></td>
		</tr>
	</table>
	<div class="submit"><input type="button" class="button" value="确 认" onclick="doSubmit();" /></div>
	</form>
</ui:page>
</body>
</html>
