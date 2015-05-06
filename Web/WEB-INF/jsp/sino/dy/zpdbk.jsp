<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%-- 
	制品卡针式套打A4
	使用LODOP.ADD_PRINT_TEXT设置打印位置
	 --%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/print.js"></script>
<script type="text/javascript">

function find() {
	var form = document.forms["DataForm"];
	var dhno = form.elements["dhno"];
	if(dhno.value == "") {
		alert("请指定一个订货No.");
		dhno.focus();
		return false;
	}
	var ajax = new Cocoajax();
	ajax.dataDiv = document.getElementById("DataArea");
	ajax.callback = function() {
		if(this.code < 0) {
			coco.alert(this.msg);
			return;
		}
	};
	ajax.post("zpdbk!list.do", coco.parseParam("dhno", dhno.value));
}

function doPrint(jbnos) {
	if(jbnos == null || jbnos.length == 0) {
		alert("请指定一个制品No.");
		return false;
	}
	//调用打印控件进行套打
	var LODOP = document.getElementById("lodop");
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code < 0) {
			coco.alert(this.msg);
			return;
		}
		//返回js对象
		var obj = null;
		eval("obj = "+this.result);
		if(obj == null || obj.datas == null) {
			alert("返回错误信息");
			return;
		}
		if(obj.datas.length == 0) {
			alert("没有数据");
			return;
		}
		for(var i = 0; i < obj.datas.length; i++) {
			toPrintZpk(LODOP, obj.datas[i]);
		}
	};
	ajax.post("zpdbk!print.do", jbnos);
}

function formatSpace(str) {
	if(str == null) return "";
	return str.replace(/(.?)/g,"$1 ");
}

function printNo(obj) {
	var form = obj.form;
	var jbno = form.elements["jbno"];
	if(jbno.value == "") {
		alert("请指定一个制品No.");
		jbno.focus();
		return false;
	}
	doPrint(coco.parseParam("jbnos", jbno.value));
}

function printSel() {
	var form = document.forms["DataForm"];
	var content = cocoform.postCheckValues(form, "jbnos");
	if(content == null || content == "") {
		alert("至少选中一个制品No.");
		return false;
	}
	doPrint(content);
}

coco.addEventListener(window, "load", function() {
	var jbnos = "${jbnos}";
	if(jbnos != "") {
		doPrint(jbnos);
	}
});
</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="制品卡打印">
<form name="DataForm" >
	<table width="96%" align="center" class="form"
		style="margin-top: 30px;">
		<colgroup>
			<col width="80" />
			<col width="250" />
			<col width="50" />
		
		</colgroup>
		<tr>
			<th>制品No.</th>
			<td><ui:input name="jbno" maxlength="15" />&nbsp;&nbsp;<input type="button" class="button" value="打印(P)" onclick="printNo(this);" accesskey="p" /></td>
			<td></td>
			
		</tr>
	</table>
	<div id="DataArea"></div>
</form>
</ui:page>
</body>
</html>