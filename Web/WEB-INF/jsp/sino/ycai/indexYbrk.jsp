<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			cocoform.submit(oForm, function() {
				coco.hideAlert();
				if(this.code < 0) {
					coco.alert(this.msg);
				} else {
					alert("操作成功");
					}
				});
		}
	//-->
	</SCRIPT>
</head>
<body>
<ui:page title="原板入库 ">
	<form name="DataForm" xu.ajax="" method="post" action="ybrk.do" >
		<table width="98%" align="center" style="margin: 20px auto;" class="form">
			<colgroup>
				<col width="10%" />
				<col width="10%"  />
				<col width="50%"  />
				<col width="30%"  />
			</colgroup>
			<tr>
				<th style="text-align: left;">导入文件</th>
				<td><ui:attach id="ybrk" attachName="attachId" label="上传Text" width="100" single="true" typeFilter="#text#" /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="确 认" onclick="doSubmit();" /></div>
	</form>
</ui:page>
</body>
</html>