<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="../../global/header.jsp" %>
<script type="text/javascript">

function print() {
	var oForm = document.forms["DataForm"];
	var zsnoBegin = oForm.elements["zsnoBegin"].value;
	var zsnoEnd = oForm.elements["zsnoEnd"].value;
	if(zsnoBegin == "") {
		alert("请指定指示连号No.");
		return false;
	}
	var type = null;
	var oType = oForm.elements["type"];
	if(oType.type == "radio") {
		if(oType[0].checked) type = oType[0].value;
		else if(oType[1].checked) type = oType[1].value;
	}
	else {
		type = oType.value;
	}
	if(type == null) {
		if(flag) alert("请指定指示书类型");
		return false;
	}
	var zsno = [];
	zsno[0] = zsnoBegin;
	if(zsnoEnd != null) {
		var ch = zsnoBegin.substring(0,1);
		var index = new Number(zsnoBegin.substring(1));
		var end = new Number(zsnoEnd.substring(1));
		var seq;
		if(!isNaN(index) && !isNaN(end)) {
			while((++index) <= end) {
				seq = "0000"+index;
				seq = seq.substring(seq.length - 5);
				zsno[zsno.length] = ch + seq;
			}
		}
	}
	doPrint(type, zsno);
}

function doPrint(type, zsno) {
	if(typeof(zsno) == "string") {
		zsno = [zsno];
	}
	if(zsno.length > 20) {
		if(!window.confirm("打印份数超过20份，你确定要打印吗？")) return;
	}
	var ajax = new Cocoajax();
	var index = 0;
	//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
	var LODOP = document.getElementById("lodop");
	var printBtn = document.getElementById("printBtn");
	printBtn.disabled = true;
	var url = "zss!"+type+"!print.do";
	var printer = type == '1' ? "SHD" : "DPK510";
	ajax.callback = function() {
		if(this.code < 0) {
			coco.alert(this.msg);
		}
		else {
			LODOP.PRINT_INIT("ZSS-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA(printer);
			//像素 ：1380px ，958px
			//0.1毫米：3500, 2540
			//LODOP.SET_PRINT_PAGESIZE(1, "350mm", "254.0mm", "A3");
			LODOP.SET_PRINT_PAGESIZE(1, 3500, 2540, "A3");
			LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", this.result);
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();
		}
		if(zsno.length > index) {
			ajax.post(url, coco.parseParam("zsno", zsno[index++]));
		}
		else {
			printBtn.disabled = false;
		}
	};
	ajax.post(url, coco.parseParam("zsno", zsno[index++]));
}

function autoPrint() {
	var type = '${type}';
	if(type == "" || type == "null") return;
	var ids = '${zsno}';
	if(ids == "" || ids == "null") return;
	var idArr = ids.split(",");
	doPrint(type, idArr);
}

coco.addEventListener(window, "load", autoPrint);
</script>
</head>
<body>
<ui:print id="lodop"/>
<c:if test="${type == '1' }">
<ui:page title="ETL指示书打印">
<form name="DataForm">
<input type="hidden" name="type" value="1" />
	<table width="96%" align="center" class="form"
		style="margin-top: 30px;">
		<colgroup>
			<col width="100" />
			<col />
			<col width="80" />
		</colgroup>
		<tr>
			<th>指示连号.</th>
			<td><ui:input name="zsnoBegin" maxlength="6"/> ~ <ui:input name="zsnoEnd" maxlength="6"/></td>
			<td><input id="printBtn" type="button" class="button" value="打 印(P)" onclick="print(event, true);" accesskey="p" /></td>
		</tr>
	</table>
	</form>
</ui:page>
</c:if>
<c:if test="${type == '2' }">
<ui:page title="SL指示书打印">
<form name="DataForm">
<input type="hidden" name="type" value="2" />
	<table width="96%" align="center" class="form"
		style="margin-top: 30px;">
		<colgroup>
			<col width="100" />
			<col />
			<col width="80" />
		</colgroup>
		<tr>
			<th>指示连号.</th>
			<td><ui:input name="zsnoBegin" maxlength="6"/> ~ <ui:input name="zsnoEnd" maxlength="6"/></td>
			<td><input id="printBtn" type="button" class="button" value="打 印(P)" onclick="print(event, true);" accesskey="p" /></td>
		</tr>
	</table>
	</form>
</ui:page>
</c:if>
<c:if test="${type == null }">
<ui:page title="指示书打印">
<form name="DataForm">
	<table width="96%" align="center" class="form"
		style="margin-top: 30px;">
		<colgroup>
			<col width="100" />
			<col />
			<col width="120" />
			<col width="80" />
		</colgroup>
		<tr>
			<th>指示连号.</th>
			<td><ui:input name="zsnoBegin" maxlength="6"/> ~ <ui:input name="zsnoEnd" maxlength="6"/></td>
			<td><ui:radiolist name="type" list="'1':'ETL','2':'SL'" value="type" /></td>
			<td><input id="printBtn" type="button" class="button" value="打 印(P)" onclick="print(event, true);" accesskey="p" /></td>
		</tr>
	</table>
	</form>
</ui:page>
</c:if>
<br/>
</body>
</html>