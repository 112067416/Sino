<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<SCRIPT type="text/javascript">
<!--
	function outKwbq() {
		var oForm = document.forms["DataForm"];
		var ele = oForm.elements["content"];
		ele.value = "";
		cocoform.submit(oForm, function() {
				if(this.code < 0) {
					alert(this.msg);
				} else {
					ele.value = this.result;
				}
		});
	}

	function doPrint(content) {
		//alert("打印内容="+content);
		//return;
		var LODOP = document.getElementById("lodop");
		LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
		LODOP.ADD_PRINT_HTM(-15, -10, 1100, 800, content);
		//打印预览
		LODOP.PREVIEW();
		//打印
		//LODOP.PRINT();	
	}
	function toPrint() {
		var oForm = document.forms["DataForm"];
		var size = oForm.elements["size"].value;
		var ele = oForm.elements["content"];
		var v = ele.value;
		if(v == null || v.length == 0) {
			alert("请输入库位标签");
			return;
		}
		//控制每张A4最多打印库位标签个数
		var s = v.split(",");
		if(s.length > size) {
			alert("一张纸最多只能打印"+size+"个库位标签");
		}		
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			doPrint(this.result);
		};
		ajax.post("kwbq!print.do", coco.parseParam("kws", v));
	}
//-->
</SCRIPT>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="库位标签制作">
	<form action="outKwbq.do" name="DataForm" method="post">
		<input type="hidden" name="size" value="${size }" >
		<table class="form" width="100%">
			<colgroup><col width="10%" /><col width="90%" /></colgroup>
			<tr>
				<th style="text-align: left;">库位</th>
				<td><ui:input name="prefix" cssClass="normal" maxlength="2" title="库位标签-前缀" />&nbsp;&nbsp;&nbsp;&nbsp;<ui:int name="start" cssClass="normal" maxlength="2" title="库位标签-开始号" />-<ui:int name="end" cssClass="normal" maxlength="2" onblur="outKwbq();" title="库位标签-结束号" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<ui:textarea name="content" cssStyle="" cssClass="normal" cols="155" rows="5" title="当库位标签号有多个时，用,号隔开。"/>
				</td>
			</tr>
		</table>
		<div align="right"><input type="button"" name="btn" value="打 印" class="button" onclick="toPrint();" /></div>
	</form>
</ui:page>
</body>
</html>
