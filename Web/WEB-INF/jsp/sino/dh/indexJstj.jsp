<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>结算条件登录</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			//结算条件
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key = coco.getAttr(oTr, "id0");
				var content;
				if(key != null && key.length > 0) {
					content = coco.parseParam("dhnos", key);
				} else {
					content = cocoform.postCheckValues("PageForm_page","dhnos","dhnos");
				}
				if(content == null || content.length == 0){
					alert("请选择要设置结算条件的记录");
					return;
				}
				var form = document.forms["DataForm"];
				cocoform.clear(form);
				coco.showPage("Detail", {center : true, top : 90, width : "80%" });
				if(key == null || key.length == 0) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code > 0) {
						cocoform.fillResult(form, this.result);
					}
					form.elements["dhnos"].value = content;
				};
				ajax.post("load.do", "id="+encodeURIComponent(key));
			}
			//获取合同金额
			function getHtje(obj) {
				var form = document.forms["DataForm"];
				var eles = form.elements["htdj"];
				var htdj,htzl;
				if(eles.tagName != null) {
					htdj = eles.value;
					htzl = form.elements["htzl"].value;
					if(htdj == null) htdj = 0;
					if(htzl == null) htzl = 0;
					form.elements["htje"].value = (htzl * htdj).toFixed(2);
				} else {
					var el, elIndex=0;
					while((el = eles[elIndex++]) != null && !(el == obj)) {
					}
					htdj = eles[elIndex-1].value;
					eles = form.elements["htzl"];
					htzl = eles[elIndex-1].value;
					if(htdj == null) htdj = 0;
					if(htzl == null) htzl = 0;
					eles = form.elements["htje"];
					eles[elIndex-1].value = (htzl * htdj).toFixed(2);
				}
			}
			//保存
			function doSave(oInput) {
				var oForm = oInput.form;
				var dhnos = oForm.elements["dhnos"].value;
				if(dhnos == null || dhnos.length == 0) {
					alert("请选择要设置结算条件的记录");
					return;
				}
				var yfkn = oForm.elements["yfkn"].value;
				var chkn = oForm.elements["chkn"].value;
				var hfkn = oForm.elements["hfkn"].value;
				var qixn = oForm.elements["qixn"].value;
				var thqf = oForm.elements["thqf"].value;
				var ysfs = oForm.elements["ysfs"].value;
				var yfei = oForm.elements["yfei"].value;
				var htdj = oForm.elements["htdj"].value;
				var postContent = coco.parseParams([{name:"yfkn", value:yfkn},{name:"chkn", value:chkn},{name:"hfkn", value:hfkn},{name:"qixn", value:qixn},{name:"thqf", value:thqf},{name:"ysfs", value:ysfs},{name:"yfei", value:yfei},{name:"htdj", value:htdj}]);
				postContent = postContent + "&" + dhnos;
				if(!cocoform.verify(oForm)) return;
				if(!confirm("是否确定保存?")) return;
				var ajax = new Cocoajax();
				ajax.oInput = oInput;
				ajax.callback = function() {
					if(this.code < 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				};
				ajax.post("saveJstj.do", postContent);
			}
			
			var tableform = new TableForm("DataTbl");
			//提交
			function doSubmit() {
				tableform.formatTblForm("items",1);
				var ajax = new Cocoajax();
				cocoform.submit(document.forms["DataForm"], function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						doReturn();
				}, "是否确定保存吗?");
			}
			//返回
			function doReturn() {
				DataArea.style.display = "none";
				var qForm = document.forms["QueryForm"];
				qForm.elements["dhno"].value = "";
				qForm.elements["lineStart"].value = "";
				qForm.elements["lineEnd"].value = "";
				qForm.elements["dhno"].focus();
			}
			
			//查询订货合同
			function doQuery() {
				var qForm = document.forms["QueryForm"];
				var dhno = qForm.elements["dhno"].value.replace(/^\s+|\s+$/gi, '');
				if(dhno == null || dhno.length == 0) {
					alert("订货合同不能为空");
					qForm.elements["dhno"].focus();
					return;
				}
				var lineStart = qForm.elements["lineStart"].value.replace(/^\s+|\s+$/gi, '');
				var lineEnd = qForm.elements["lineEnd"].value.replace(/^\s+|\s+$/gi, '');
				var postContent = coco.parseParams([{name:"dhno",value:dhno},{name:"lineStart",value:lineStart},{name:"lineEnd",value:lineEnd}]);
				var ajax = new Cocoajax();
				ajax.dataDiv = DataArea;
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					DataArea.style.display = "block";
				};
				ajax.post("indexJstj.do", postContent);
			}
		//-->
		</SCRIPT>
	</head>
<body>
<ui:page title="结算条件登录">
<form name="QueryForm" method="post" action="#" >
<table class="form" width="100%">
<colgroup><col width="10%" /><col width="40%" /><col width="10%" /><col width="40%" /></colgroup>
<tr>
<th style="text-align: left;">订货No.</th>
<td><ui:input name="dhno" maxlength="7" title="订货No." required="true" onkeydown="if(window.event.keyCode == 13) doQuery();" />&nbsp;-&nbsp;<ui:input name="lineStart" title="订货No.行号" maxlength="2" onkeydown="if(window.event.keyCode == 13) doQuery();" />&nbsp;至&nbsp;<ui:input name="lineEnd" title="订货No.行号" maxlength="2" onkeydown="if(window.event.keyCode == 13) doQuery();" /></td>
<td colspan="2">&nbsp;</td>
</tr>
</table>
<div class="submit"><input type="button" class="button" value="查   询" onclick="doQuery()" />  <input type="button" class="button" value="返    回" onclick=" doReturn()" />  <input type="button" class="button" value="移    除" onclick="tableform.removeRow('ids');" />  <input type="button" class="button" value="保   存" onclick="doSubmit();" /></div>
</form>
<br />
<form name="DataForm" method="post" action="saveJstj.do" >
<div id="DataArea"></div>
</form>
</ui:page>
</body>
</html>