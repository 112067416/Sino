<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%>
	<%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>端板登录</title>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<SCRIPT type="text/javascript">
	<!--
	//提交
	function doSubmit(obj) {
		if(!window.confirm("确定保存 吗?")) return;
		var ajax = new Cocoajax();
		cocoform.submit(obj,success);
	}
	
	function success() {
		if(this.code < 0) {
			coco.alert(this.msg);
			return;
		}
		alert("保存成功");
		var oForm = document.forms["DataForm"];
		var jbno=oForm.elements["jbno"].value;
		//打印制品卡
		doPrint2(jbno);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			oForm.elements["jbno"].value=this.result;
		};	
		ajax.post("getjbno.do", "", "POST");
		
		//清空
		doQC();
		//window.location.reload();
	}
	// 打印服务卡
	function doPrint2(jbno) {
		document.getElementById("PrintFrame").src = "../dy/zpdbk.do?jbnos="+jbno;
	}
	//清空
	function doQC(){
		var oForm = document.forms["DataForm"];
		var jbno=oForm.elements["jbno"].value;
		oForm.reset();
		oForm.elements["jbno"].value=jbno;
	}
	
	//-->
	</SCRIPT>
</head>
<body>
	<ui:page title="端板登录 ">
    <form name="DataForm" xu.ajax="true" xu.s="success()" method="post" action="save.do" >
		<table width="96%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="25%" />
			</colgroup>
			<tr>
				<th style="text-align: right;">制品号</th>
				<td><ui:input name="jbno" required="true" readonly="true"  value="jbno" maxlength="11"/></td>
				<th style="text-align: right;">生产线别</th>
				<td nowrap="nowrap"><ui:select  name="slin" list="'T':'选别','V':'剪切二线','W':'剪切三线'" headerText="" headerValue="" prop="nn:1;" /></td>
				<th style="text-align: right;">重量(kg)</th>
				<td colspan="2"><ui:int name="zpzl" maxlength="5" required="true" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">足</th>
				<td><ui:input name="dmfx" required="true" maxlength="1"/></td>
				<th style="text-align: right;">捆包形式</th>
				<td colspan="3"><ui:select name="kbao"  prop="nn:1;" headerText="" headerValue="" list="'S21':'S21','S31':'S31'"  /></td>
				
			</tr>
		</table>
		
			<div class="opt-btm">
			<input type="button" class="button" value="清空" onclick="doQC();" />
			 <input type="button" class="button" value="保 存" onclick="doSubmit(this)" /></div>
		</form>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>
