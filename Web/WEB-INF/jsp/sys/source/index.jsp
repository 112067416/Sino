<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">
contextmenu.putMenus("menu", [["新增方法", "add.gif", "toAddMethod()"],
                              ["修改", "edit.gif", "toModifyClass()"],
                      		["删除", "del.gif", "toDelClass()"]]);
contextmenu.putMenus("menu1", [["修改", "edit.gif", "toModifyMethod()"],
                      		["删除", "del.gif", "toDelMethod()"]]);
                      		
function toAddClass() {
	var form = document.forms["ClassForm"];
	cocoform.clear(form);
	var o = form.elements["className"];
	o.readOnly = false;
	o.className = "normal";
	coco.showPage("ClassPanel", {center:1,top:100});
}

function toModifyClass(oTr) {
	if(oTr == null) oTr = contextmenu.targetElement;
	var form = document.forms["ClassForm"];
	form.elements["$type"].value = "modify";
	form.elements["name"].value = oTr.cells[1].innerHTML;

	var o = form.elements["className"];
	o.readOnly = true;
	o.className = "form-readonly";
	o.value = oTr.cells[2].innerHTML;
	coco.showPage("ClassPanel", {center:1,top:100});
}

function toDelClass() {
	if(!window.confirm("确定要删除该类(包括所有方法)吗？")) return false;
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code > 0) {
			alert("删除成功");
			cocopage.refresh();
		}
		else {
			coco.alert(this.msg);
		}
	};
	ajax.post("delClass.do", "id="+contextmenu.selectedId())
}

function savedClass() {
	alert("保存成功");
	cocopage.refresh();
	coco.hidePage("ClassPanel");
}

function toAddMethod() {
	var form = document.forms["MethodForm"];
	cocoform.clear(form);
	form.elements["sourceClass.className"].value = contextmenu.selectedId();
	var o = form.elements["method"];
	o.readOnly = false;
	o.className = "normal";
	
	coco.showPage("MethodPanel", {center:1,top:100});
}

function toModifyMethod(oTr) {
	if(oTr == null) oTr = contextmenu.targetElement;
	var form = document.forms["MethodForm"];
	form.elements["$type"].value = "modify";
	form.elements["id"].value = coco.getAttr(oTr, "xu.id");
	form.elements["sourceClass.className"].value = coco.getAttr(oTr, "xu.className");
	form.elements["name"].value = oTr.cells[0].innerHTML;
	var o = form.elements["method"];
	o.readOnly = true;
	o.className = "form-readonly";
	o.value = oTr.cells[1].innerHTML;
	coco.showPage("MethodPanel", {center:1,top:100});
}

function toDelMethod() {
	if(!window.confirm("确定要删除该方法吗？")) return false;
	var ajax = new Cocoajax();
	var className = coco.getAttr(contextmenu.selectedTarget(), "xu.className");
	ajax.callback = function() {
		if(this.code > 0) {
			alert("删除成功");
			cocopage.refresh(null, function() {
				var oTbl = document.getElementById("DataTbl");
				var rows = oTbl.rows;
				var oRow;
				for(var i = 1; i < rows.length; i++) {
					oRow = rows[i];
					if(coco.getAttr(oRow, "xu.id") == className) {
						var oChk = oRow.cells[0].childNodes[0];
						oChk.checked = true;
						expand(oChk);
						break;
					}
				}
			});
		}
		else {
			coco.alert(this.msg);
		}
	};
	ajax.post("delMethod.do", "id="+ contextmenu.selectedId());
}

function savedMethod() {
	alert("保存成功");
	cocopage.refresh(null, function() {
		var className = document.forms["MethodForm"].elements["sourceClass.className"].value;
		var oTbl = document.getElementById("DataTbl");
		var rows = oTbl.rows;
		var oRow;
		for(var i = 1; i < rows.length; i++) {
			oRow = rows[i];
			if(coco.getAttr(oRow, "xu.id") == className) {
				var oChk = oRow.cells[0].childNodes[0];
				oChk.checked = true;
				expand(oChk);
				break;
			}
		}
	});
	coco.hidePage("MethodPanel");
}

function expandAll(obj) {
	var oTr = obj.parentNode.parentNode;
	var checked = obj.checked;
	while((oTr = oTr.nextSibling) != null && oTr.tagName == "TR") {
		if(coco.getAttr(oTr, "xu.className") != null) oTr.style.display = checked ? "" : "none";
	}
}

function expand(obj) {
	var oTr = obj.parentNode.parentNode;
	var checked = obj.checked;
	var className = coco.getAttr(oTr, "xu.id");
	while((oTr = oTr.nextSibling) != null && coco.getAttr(oTr, "xu.className") == className) {
		oTr.style.display = checked ? "" : "none";
	}
}
</script>
</head>
<body>
<ui:page title="资源管理">
	<f:page action="index.do" file="list.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<tr>
				<th>名称</th>
				<td><ui:input name="name" lowToUp="false" /></td>
				<th>类名</th>
				<td><ui:input name="className" lowToUp="false" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="查 询(Q)" onclick="cocopage.query()" accesskey="q" />
			<input type="button" class="button" value="新 增(A)" onclick="toAddClass()" accesskey="a"/>
		</div>
	</f:page>
</ui:page>
<ui:panel id="ClassPanel" title="类信息" closable="true" display="none" popup="true" >
<form name="ClassForm" action="saveClass.do" method="post" xu.s="savedClass()">
<input type="hidden" name="$type" />
<table class="form" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="50" /><col width="450"/></colgroup>
	<tr>
		<th>类名</th>
		<td><ui:input name="className" required="true" cssStyle="width:98%;" lowToUp="false" /></td>
	</tr>
	<tr>
		<th>名称</th>
		<td><ui:input name="name" required="true" cssStyle="width:98%;" lowToUp="false" /></td>
	</tr>
</table>
<div class="submit">
<input type="button" class="button" onclick="cocoform.submit(this);" value="保 存" />
</div>
</form>
</ui:panel>
<ui:panel id="MethodPanel" title="方法信息" closable="true" display="none" popup="true" >
<form name="MethodForm" action="saveMethod.do" method="post" xu.s="savedMethod()">
<input type="hidden" name="$type" />
<input type="hidden" name="id" />
<table class="form" border="0" cellpadding="0" cellspacing="0">
	<colgroup><col width="70" /><col width="450"/></colgroup>
	<tr>
		<th>类&nbsp;&nbsp;名</th>
		<td><ui:input name="sourceClass.className" required="true" lowToUp="false" readonly="true" cssStyle="width:98%;" /></td>
	</tr>
	<tr>
		<th>方法名</th>
		<td><ui:input name="method" lowToUp="false" required="true" cssStyle="width:98%;" /></td>
	</tr>
	<tr>
		<th>名&nbsp;&nbsp;称</th>
		<td><ui:input name="name" lowToUp="false" required="true" cssStyle="width:98%;" /></td>
	</tr>
</table>
<div class="submit">
<input type="button" class="button" onclick="cocoform.submit(this);" value="保 存" />
</div>
</form>
</ui:panel>
</body>
</html>