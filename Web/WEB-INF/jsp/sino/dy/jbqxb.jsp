<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%-- 
	卷板缺陷表针式套打A4
	使用LODOP.ADD_PRINT_TEXT设置打印位置
	 --%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">

function changeType(obj) {
	var isZs = obj.value == "0";
	var oForm = document.forms["DataForm"];
	var nb = oForm.elements["noBegin"];
	var ne = oForm.elements["noEnd"];
	if(isZs) {
		nb.maxLength = 6;
		ne.maxLength = 6;
		nb.style.width = 62;
		ne.style.width = 68;
		if(nb.value.length > 6) nb.value = "";
		if(ne.value.length > 6) ne.value = "";
	}
	else {
		nb.maxLength = 11;
		ne.maxLength = 11;
		nb.style.width = 112;
		ne.style.width = 112;
	}
}

function printCallback() {
	if(this.code < 0) {
		coco.alert(this.msg);
		return;
	}
	//返回js对象
	var obj = null;
	eval("obj = "+this.result + ";");
	if(obj == null || obj.datas == null) {
		alert("返回错误信息");
		return;
	}
	if(obj.datas.length == 0) {
		alert("没有数据");
		return;
	}
	//调用打印控件进行套打
	var LODOP = document.getElementById("lodop");
	for(var i = 0; i < obj.datas.length; i++) {
		doPrint(LODOP, obj.datas[i]);
	}
};

function doPrint(LODOP, data) {
	LODOP.PRINT_INIT("JBQXB-<%=System.currentTimeMillis()%>");
	LODOP.SET_PRINTER_INDEXA("DPK510");
	LODOP.SET_PRINT_PAGESIZE(1,2600,2050,"A4");
	LODOP.SET_PRINT_STYLE("FontSize",12);
	LODOP.SET_PRINT_STYLE("Bold",0);
	if(data.zsno != null) LODOP.ADD_PRINT_TEXT(100, 60, 100, 20, data.zsno);
	if(data.jbno != null) LODOP.ADD_PRINT_TEXT(100, 180, 100, 20, data.jbno);
	if(data.yh != null) LODOP.ADD_PRINT_TEXT(100,280, 100, 20, data.yh);
	if(data.bh != null) LODOP.ADD_PRINT_TEXT(100, 400, 100, 20, data.bh);
	if(data.bk != null) LODOP.ADD_PRINT_TEXT(100, 480, 100, 20, data.bk);
	if(data.xfzl != null) LODOP.ADD_PRINT_TEXT(100, 560, 100, 20, data.xfzl);
	if(data.jjg != null) LODOP.ADD_PRINT_TEXT(100, 710, 100, 20, data.jjg);
	if(data.jbzl != null) LODOP.ADD_PRINT_TEXT(100, 800, 100, 20, data.jbzl);
	if(data.jbc != null) LODOP.ADD_PRINT_TEXT(100, 920, 100, 20, data.jbc);
	//打印预览
	//LODOP.PREVIEW();
	//打印
	LODOP.PRINT();	
}

function check() {
	var oForm = document.forms["DataForm"];
	var nb = oForm.elements["noBegin"];
	var ne = oForm.elements["noEnd"];
	if(ne.value != "" && ne.value.length != nb.value.length) {
		alert("起始号码和结束号码位数不一致");
		return false;
	}
	return true;
}

function print() {
	cocoform.submit("DataForm", printCallback);
}
coco.addEventListener(window, "load", function() {
	var nos = '${vo.nos}';
	if(nos != "") {
		var ajax = new Cocoajax();
		ajax.callback = printCallback;
		ajax.post("jbqxb!print.do", 'type=${vo.type}&nos='+nos);
	}
});
</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="卷板缺陷表打印">
	<form name="DataForm" xu.c="check()" action="jbqxb!print.do" method="post">
	<table width="96%" align="center" class="form"
		style="margin-top: 30px;">
		<colgroup><col width="150" /><col /><col width="100" /></colgroup>
		<tr>
			<th><ui:select name="type" list="'0':'指示连号','1':'卷板No.'" prop="nn:1;" onchange="changeType(this)" value="vo.type" /></th>
			<td><ui:input name="noBegin" maxlength="6" required="true" value="vo.noBegin" /> ~ <ui:input name="noEnd" maxlength="6" value="vo.noEnd" /></td>
			<td><input type="button" class="button" value="打 印(P)" onclick="print();" accesskey="p" /></td>
		</tr>
	</table>
	</form>
	<div id="DataArea"></div>
</ui:page>
</body>
</html>