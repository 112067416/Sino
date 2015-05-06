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
			var oForm = document.forms["DataForm"];
			var eles = oForm.elements["duics"];
			var duics = null;
			for(var i = 0; i < eles.length; i++) {
				el = eles[i];
				if(!el.checked) continue;
				if(duics == null) {
					duics = el.value;
				} else {
					duics = duics + "," + el.value;
				}
			}
			if(duics == null || duics.length == 0) {
				alert("请选择盘点堆场");
				return;
			}
			if(!confirm("是否确定导入")) return;
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			cocoform.submit(oForm, function() {
				coco.hideAlert();
				if(this.code < 0) {
					coco.alert(this.msg);
					return;
				}
				alert("在数据库中的制品信息与实物一致");
				});
		}
	//-->
	</SCRIPT>
</head>
<body>
<ui:page title="现品盘点 ">
	<form name="DataForm" xu.ajax="" method="post" action="zppd.do" >
		<table width="98%" align="center" style="margin: 20px auto;" class="form">
			<colgroup><col width="10%" /><col width="30%"  /><col width="10%" /><col width="50%"  /></colgroup>
			<tr>
				<th style="text-align: left;">导入文件</th>
				<td><ui:attach id="ybrk" attachName="attachId" label="上传Text" width="100" single="true" typeFilter="#text#" /></td>
				<th style="text-align: left;">堆场</th>
				<td>
				<c:forEach varStatus="stat" var="item" items="${dcs }"><span style="display: -moz-inline-box; display: inline-block; width: 55px; white-space: nowrap;"><input type="checkbox" xu.type="checklist" name="duics" value="${item }" />${item }&nbsp;&nbsp;</span></c:forEach>
				</td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="确 认" onclick="doSubmit();" /></div>
	</form>
</ui:page>
</body>
</html>