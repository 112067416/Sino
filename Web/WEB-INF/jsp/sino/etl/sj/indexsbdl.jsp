<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
  		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<script type="text/javascript">
		<!--
		// 订正检查
		function addSbData() {
			var oForm = document.forms["QueryForm"];
			var date = oForm.elements["dasr"].value;
			if(date == null || date.length == 0) return;
			var postContent = coco.parseParam("date", date);
			var ajax = new Cocoajax();
			ajax.dataDiv = DataDiv; 
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				DataDiv.style.display = "block";
			};
			ajax.post("findSblrs.do", postContent);
		}
		//提交
		function doSubmit(el) {
			if(!confirm("是否确定保存?")) return;
			var oForm = document.forms["DataForm"];
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			cocoform.submit(oForm, function() {
				coco.hideAlert();
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				} 
				alert("保存成功");
			}, null, null, el);
		}
		//-->
	</script>
</head>
<body onload="addSbData()">		
<ui:page title="ETl速报登录">
<form name="QueryForm" action="#" method="post">
<table width="96%" align="center" class="form">
<colgroup><col width="40%" /><col width="30%" /><col width="30%" /></colgroup>
<tr><th>日期：</th><td><ui:date name="dasr" cssClass="normal" required="true" value="{date }" onchange="addSbData();"/></td><td><input type="button" class="button" value="保  存" onclick="doSubmit(this);" /></td></tr>
</table>
</form>
<form name="DataForm" action="savesb.do" method="post" >
<div id="DataDiv" style="width: 100%; display: none;"></div>
</form>
</ui:page>
</body>
</html>