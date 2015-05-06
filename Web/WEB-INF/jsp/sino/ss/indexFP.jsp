<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %>
<%@ 
taglib uri="/sys" prefix="sys"%>
<%@ 
taglib uri="/f" prefix="f" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
	</head>
	<script type="text/javascript">
	<!--
		// 查询
		function doQuery() {
			var oForm = document.forms["PageForm_page"];
			var dhno = oForm.elements["dhno"].value.replace(/^\s+|\s+$/gi, "");
			var line = oForm.elements["line"].value.replace(/^\s+|\s+$/gi, "");;
			oForm.elements["dhnoLine"].value = dhno + line;
			cocopage.query();
		}
	// 分派指示书-弹出分派层
	function doFp(){
		var form = document.forms["PageForm_page"];
		var els = form.elements["ids"];
		var content = "";
		if(els.length > 0) {
			for(var i = 0; i < els.length; i++) {
				if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
			}
		}
		else {
			if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
		}
		if(content == "") {
			alert("请选择数据行");
			return;
		}
		coco.showPage("Detail",{center:true,top:80,width:"40%"});
	}
	//保存分派
	function saveFp(){
		var form = document.forms["PageForm_page"];
		var els = form.elements["ids"];
		var content = "";
		if(els.length > 0) {
			for(var i = 0; i < els.length; i++) {
				if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
			}
		}
		else {
			if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
		}
		if(content == "") {
			alert("请选择数据行");
			return;
		}
		var dataForm=document.forms["DataForm"];
		var jinj=dataForm.elements["jinj"].value;
		if(jinj==""){
			alert("紧急程度不能为空！");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code>0){
				alert("紧急设置成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			}
			else{
				coco.alert(this.msg);
			}
		};
		var key=content.substring(1)+"&jinj="+jinj;
		ajax.post("updateFP.do",key);
	}
	// 取消分派
	function doQxFp() {
		var form = document.forms["PageForm_page"];
		var els = form.elements["ids"];
		var content = "";
		if(els.length > 0) {
			for(var i = 0; i < els.length; i++) {
				if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
			}
		}
		else {
			if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
		}
		if(content == "") {
			alert("请选择数据行");
			return;
		}
		if(!window.confirm("确定要取消设置吗?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("取消设置成功");
			coco.hidePage('Detail');
			cocopage.refresh();
		};
		ajax.post("deleteFP.do", content.substring(1));
	
	}
	//-->
	</script>
	<body>
		<ui:page title="选别PILE检索">
		<f:page action="indexFP.do" file="listFP.jsp">
		<input type="hidden" name="dhnoLine" />
		<table  class="form" width="100%">
			<colgroup><col width="8%" /><col width="15%" /><col width="8%" /><col width="15%" />
				<col width="8%" /><col width="22%" /><col width="8%" /><col width="15%" /></colgroup>
			<tr>
				<th>PILE No.</th>
				<td><ui:input name="jbno" maxlength="11" /></td>
				<th>订货No.</th>
				<td><ui:input name="dhno" title="订货DB的订货NO" maxlength="7" />-<ui:input name="line" title="行号" maxlength="2" onkeydown="if(window.event.keyCode == 13) doQuery();" /></td>
				<th>制品尺寸</th>
				<td nowrap="nowrap"><ui:number name="xpho" positive="true" scale="4" precision="3"/>*
				<ui:number name="xpkn" positive="true" scale="6" precision="2" />*
				<ui:number name="xpcn" positive="true" scale="6" precision="2" /></td>
				<th style="text-align: left;">用户代码</th>
				<td colspan="2"><ui:input name="userBegin" maxlength="4" />至<ui:input name="userEnd" maxlength="4"/></td>
			</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="查 询" onclick="doQuery();" />
		<input type="button" class="button" onclick="doFp()" value="设置紧急" accesskey="c">
		<input type="button" class="button" onclick="doQxFp()" value="取消紧急" accesskey="c">
		</div>
		</f:page>
		</ui:page>
		<ui:panel id="Detail" title="指示书紧急设置" popup="true" display="false" closable="true" >
	<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="updateSLFP.do" >
	 	<table id="FpTbl" width="96%" align="center" class="form" style="margin-top: 10px;">
			<colgroup>
				<col width="20%"/><col />
			</colgroup>
			<tr>
				<th>是否紧急</th>
				<td><sys:codeBox code="#ZSJJ" name="jinj" txt="2"/></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="确定(F)" onclick="saveFp();" accesskey="s" />
			<input type="button" class="button" value="关闭(C)" accesskey="c" onclick="coco.hidePage('Detail');" />
		</div>
	 </form>
	 </ui:panel>
	</body>
</html>