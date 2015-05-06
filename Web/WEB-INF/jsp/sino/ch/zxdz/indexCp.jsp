<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>设置发货制品车牌号</title>	
		<%@include file="../../../global/header.jsp"%>
		<SCRIPT type="text/javascript">
		<!--
			//提交
			function doSubmit() {
				var oForm= document.forms["DataForm"];
				var cpno = oForm.elements["cpno"].value;
				if(cpno == null || cpno.length == 0) {
					alert("车牌号不能为空");
					return;
				}
				var eles = oForm.elements["zpno"];
				var el, elIndex=0, v, postContent = null;
				while((el = eles[elIndex++]) != null) {
					v = el.value;
					if(v == null || v.length == 0) continue;
					if(postContent == null) {
						postContent = "jbnos=" + v;
						continue;
					}
					postContent = postContent + "&" + "jbnos=" + v;
				}
				if(postContent == null) {
					alert("请输入制品No.");
					return;
				}
				postContent = postContent + "&" + "cpno=" + cpno;
				if(!confirm("是否确定设置车牌号?")) return;
				var ajax = new Cocoajax();
				ajax.oInput=document.getElementById("btnPrint");
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("操作成功");
					window.location.reload();
				};
				ajax.post("setCpno.do", postContent);
			}

			var i = 1;
			//向下翻页 
			function doNext() {
				if(i >= 3) {
					alert("已到底,请上一页");
					return;
				}
				var oTbl1 = document.getElementById("DataTable"+i);
				oTbl1.style.display = "none";
				i += 1;
				var oTbl2 = document.getElementById("DataTable"+i);
				oTbl2.style.display = "block";
			}
			//向上翻页 
			function doPrevious() {
				if(i <= 1) {
					alert("已到顶,请下一页");
					return;
				}
				var oTbl1 = document.getElementById("DataTable"+i);
				oTbl1.style.display = "none";
				i -= 1;
				var oTbl2 = document.getElementById("DataTable"+i);
				oTbl2.style.display = "block";
			}
			//统计页面输入制品的个数
			function moveNext(el) {
				if(el == null) return;
				var oForm = el.form;
				var eles = oForm.elements["zpno"];
				var index;
				for(var i = 0; i < eles.length; i++) {
					if(eles[i] == el) {
						index = i;
						break;
					}	
				}
				var el = eles[index + 1];
				if(el != null) el.focus();
			}

			function doClear(oForm) {
				if(!confirm("是否确定清空?")) return;
				var el;
				var eles = oForm.elements["zpno"];
				for(var i = 0; i < eles.length; i++) {
					el = eles[i];
					if(el == null) continue;
					el.value = "";
				}
			}
		//-->
		</SCRIPT>
</head>
<body>
	<ui:page title="设置发货制品车牌号">
	<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="cp.do" >
		<table width="100%" class="form">
			<colgroup>
				<col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%"  />
			</colgroup>
			<tr>
				<th style="text-align: left;">车牌号</th>
				<td><ui:input name="cpno" maxlength="10" title="车牌号" /></td>
				<td colspan="4" style="text-align: center;"><input type="button" id="btnPrint" value="确 认" class="button" onclick="doSubmit();"/>    <input type="button" class="button" onclick="doClear(this.form);" value="清 空 " /><!--  <input type="button" class="button" onclick="doPrevious();" value="上一页" />  <input type="button" class="button" onclick="doNext();" value="下一页" /> --></td>
			</tr>
		</table>
		<table id ="DataTable1" style="display: block;" width="96%" align="center">
		<colgroup><col /><col /><col /><col /><col /><col /><col /><col /></colgroup>
			<tr>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
				<th style="text-align: left;">制品No.</th>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
				<td><ui:input name="zpno" cssClass="normal" maxlength="12" onkeydown="if(window.event.keyCode == 13) moveNext(this);" title="制品No." /></td>
			</tr>
		</table>
		</form>
	</ui:page>
</body>
</html>
