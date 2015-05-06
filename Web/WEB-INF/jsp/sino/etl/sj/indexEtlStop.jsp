<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
	   <script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		//加载当前日期的停线信息
		function loadStop() {
			var form = document.forms["DataForm"];
			var dasr = form.elements["dasr"].value;
			if(dasr == null || dasr.length == 0) {
				alert("日期不能为空");
				return;
			}
			var ban = form.elements["ban"].value;
			var postContent = coco.parseParams([{name:"dasr",value:dasr},{name:"ban",value:ban}]);
			var ajax = new Cocoajax();
			ajax.dataDiv = DataDiv; 
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
			};
			ajax.post("getEtlStopData.do", postContent);
		}
		
		//提交
		function doSubmit(el) {
			if(!confirm("是否确定保存?")) return;
			var oForm = el.form;
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				} 
				alert("保存成功");
			}, null, null, el);
		}
		</script>
	</head>
	<body onload="loadStop();">		
	<ui:page title="ETL停线登录">
	<form name="DataForm" action="savestop.do" method="post" >
		<table width="96%" align="center" class="form">
			<colgroup><col width="20%" /><col width="20%" /><col width="20%" /><col width="20%" /><col width="20%" /></colgroup>
			<tr>
				<th>日期：</th>
				<td><ui:date name="dasr" value="{date}" onchange="loadStop();" /></td>
				<th>班</th>
				<td><ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;" value="ban" onchange="loadStop();"/></td>
				<td><input type="button" class="button" value="保  存" onclick="doSubmit(this);" /></td>
			</tr>
		</table>
		<br /><br />
		<div id="DataDiv" style="width: 100%; display: block;"></div>
	</form>
	</ui:page>
</body>
</html>