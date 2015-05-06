<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代码管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">

contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],
                        		["删除", "del.gif", "toDel()"],
                          		["设置", "tablechk.gif", "toSettings()"],
                          		["查看", "preview.gif", "toView()"]]);
	function toAdd() {
		var form = document.forms["DataForm"];
		cocoform.clear(form);
		form.elements["id"].disabled = false;
		form.elements["valid"].checked = true;
		coco.showPage("Detail",{center:true,top:50,width:"90%"});
	}

	function toModify() {
		var id = contextmenu.selectedId();
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			var form = document.forms["DataForm"];
			cocoform.fillResult(form, this.result);
			form.elements["id"].disabled = true;
			form.elements["$type"].value = "modify";
			coco.showPage("Detail",{center:true,top:20,width:"80%"});
		};
		ajax.post("get.do","id="+encodeURIComponent(id));
	}

	function toDel() {
		if(!window.confirm("确定要删除该条记录吗?")) return;
		var id = contextmenu.selectedId();
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			cocopage.refresh();
		};
		ajax.post("del.do","id="+encodeURIComponent(id));
	}

	function success() {
		alert("保存成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	}

	function toSettings() {
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ItemDiv");
		ajax.post("listItem.do","id="+encodeURIComponent(contextmenu.selectedId()));
		coco.showPage("ItemArea",{center:true,top:20,width:"80%"});
	}


	var tableForm = new TableForm("ItemTbl");
	tableForm.callback = function() {
		if(this.code > 0) {
			alert("保存成功");
			coco.hidePage("ItemArea");
		}
		else coco.alert(this.msg);
	};
	
	function saveItems(id) {
		tableForm.submit("saveItem.do?id="+encodeURIComponent(id));
	}

	function toView(oTr) {
		var id = oTr != null ? oTr.getAttribute("xu.id") : contextmenu.selectedId();
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ItemDiv");
		ajax.post("view.do","id="+encodeURIComponent(id));
		coco.showPage("ItemArea",{center:true,top:20,width:"80%"});
	}
	//-->
</script>
</head>
<body>
<ui:page title="代码管理">
	<f:page action="index.do" file="list.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<tr>
				<th>模块</th>
				<td><ui:input name="id" cssStyle="width:98%"/></td>
				<th>说明</th>
				<td><ui:input name="description" cssStyle="width:98%"/></td>
			</tr>
		</table>
			<div class="submit">
			<input type="button" class="button" value="查 询(Q)" onclick="cocopage.query()" accesskey="q" />
			<input type="button" class="button" value="新 增(A)" onclick="toAdd()" accesskey="a"/>
			</div>
	</f:page>
</ui:page>
<ui:panel id="Detail" title="详细信息" popup="true" display="false" closable="true">
<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
<input type="hidden" name="$type" />
	<table width="100%" class="form" >
		<colgroup>
			<col width="10%" />
			<col width="40%" />
			<col width="10%" />
			<col width="40%" />
		</colgroup>
		<tr>
			<th>模&nbsp;&nbsp;块</th>
			<td colspan="3"><ui:input name="id" cssStyle="width:96%" /></td>
		</tr>
		<tr>
			<th>备&nbsp;&nbsp;注</th>
			<td colspan="3"><ui:textarea name="description" rows="3" cssStyle="width:96%" /></td>
		</tr>
		<tr>
			<th>可编辑</th>
			<td><ui:bool name="editable" /></td>
			<th>有&nbsp;&nbsp;效</th>
			<td><ui:bool name="valid" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="保 存(S)" accesskey="s" onclick="cocoform.submit(this)" />
	</div>
	</form>
</ui:panel>
<ui:panel id="ItemArea" title="代码信息" popup="true" display="false" closable="true">
<form name="ItemForm" method="post" action="saveItems.do" >
<div id="ItemDiv" class="scroll" style="width:100%;height:450px;"></div>
</form>
<table id="ItemTblTmp" style="display: none">
	<tr>
		<td align=center><input type="checkbox" name="itemKeys" /></td>
		<td><ui:input name="key" cssStyle="width:98%" lowToUp="false" /></td>
		<td><ui:input name="value" cssStyle="width:98%" lowToUp="false" /></td>
		<td><ui:input name="remark" cssStyle="width:98%" lowToUp="false" /></td>
		<td><ui:number name="number" cssStyle="width:95%" /></td>
		<td><ui:input name="order" cssStyle="width:90%" lowToUp="false" /></td>
		<td align="center"><ui:bool name="editable" checked="true"/></td>
		<td align="center"><ui:bool name="deletable" checked="true"/></td>
		<td align="center"><ui:bool name="valid" checked="true"/></td>
	</tr>
</table>
</ui:panel>
</body>
</html>