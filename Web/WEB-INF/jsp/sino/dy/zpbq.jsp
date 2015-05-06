<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="../../global/header.jsp" %>
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
	ajax.post("zpbq!list.do", coco.parseParam("dhno", dhno.value));
}

function doPrint(jbnos) {
	if(jbnos == null || jbnos.length == 0) {
		alert("请指定一个制品No.");
		return false;
	}
	var size = jbnos.length;
	var index = 0;
	var ajax = new Cocoajax();
	var LODOP = document.getElementById("lodop");
	ajax.callback = function() {
		if(this.code < 0) coco.alert(this.msg);
		else print(LODOP, this.result);
		if(index < size) this.post("zpbq!print.do", coco.parseParam("jbno", jbnos[index++]));
	};
	ajax.post("zpbq!print.do", coco.parseParam("jbno", jbnos[index++]));
}

function print(LODOP, result) {
//	LODOP.PRINT_INIT("ZPBQ");
//	LODOP.SET_PRINTER_INDEXA("ZPBQ");
	LODOP.PRINT_INIT("ZPBQ");
	LODOP.SET_PRINTER_INDEXA("ZPBQ");
	LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
	LODOP.ADD_PRINT_HTM(0, -13, 1100, 760, result);
	//ADD_PRINT_BARCODE(Top, Left,Width, Height, CodeType, CodeValue)
	var start = result.indexOf('<table no="');
	var end = result.indexOf('" ');
	var no1 = null, no2 = null;
	if(start != -1 && end != -1) {
		var nos = result.substring(start + 11, end).split(";");
		if(nos[0] != "") no1 = nos[0];
		if(nos[1] != "") no2 = nos[1];
	}
	if(no1 != null) {
		LODOP.ADD_PRINT_BARCODE("230px", "300px", "200px", "60px", "code39", no1);
		LODOP.ADD_PRINT_BARCODE("235px", "800px", "200px", "45px", "code39", no1);
		LODOP.ADD_PRINT_BARCODE("615px", "300px", "200px", "60px", "code39", no1);
		LODOP.ADD_PRINT_BARCODE("625px", "800px", "200px", "45px", "code39", no1);
	}
	if(no2 != null) {
		LODOP.ADD_PRINT_BARCODE("320px", "800px", "200px", "45px", "code39", no2);
		LODOP.ADD_PRINT_BARCODE("710px", "800px", "200px", "45px", "code39", no2);
	}
	//打印预览
	//LODOP.PREVIEW();
	//打印
	LODOP.PRINT();	
}

function printNo() {
	var form = document.forms["DataForm"];
	var jbno = form.elements["jbno"];
	if(jbno.value == "") {
		alert("请指定一个制品No.");
		jbno.focus();
		return false;
	}
	doPrint([jbno.value]);
}

function printSel() {
	var form = document.forms["DataForm"];
	var chks = form.elements["jbnos"];
	var jbnos = [];
	if(chks == null) {
		alert("没有数据，请查询.");
		return false;
	}
	if(chks.tagName != null) {
		if(chks.checked) jbnos[0] = chks.value;
	}
	else {
		for(var i = 0; i < chks.length; i++) {
			if(chks[i].checked) jbnos[jbnos.length] = chks[i].value;
		}
	}
	if(jbnos.length == 0) {
		alert("至少选中一个制品No.");
		return false;
	}
	doPrint(jbnos);
}

coco.addEventListener(window, "load", function() {
	var jbnos = "${jbnos}";
	if(jbnos != "") {
		doPrint(jbnos.split(","));
	}
});

</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="制品标签打印">
<form name="DataForm" >
	<table width="96%" align="center" class="form"
		style="margin-top: 30px;">
		<colgroup>
			<col width="80" />
			<col width="250" />
			<col width="50" />
			<col width="120" />
			<col width="200" />
			<col />
		</colgroup>
		<tr>
			<th>制品No.</th>
			<td><ui:input name="jbno" maxlength="11" />&nbsp;&nbsp;<input type="button" class="button" value="打印(P)" onclick="printNo();" accesskey="p" /></td>
			<td></td>
			<th>订货合同行号</th>
			<td><ui:input name="dhno" maxlength="9" />&nbsp;&nbsp;<input type="button" class="button" value="查询(F)" onclick="find();" accesskey="f" /></td>
			<td><input type="button" class="button" value="打印选中行" onclick="printSel();" /></td>
		</tr>
	</table>
	<div id="DataArea"></div>
</form>
</ui:page>
</body>
</html>