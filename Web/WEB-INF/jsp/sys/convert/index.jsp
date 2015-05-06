<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代码管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">
	contextmenu.putMenus("menu", [ [ "修改", "edit.gif", "toModify()" ],
			[ "删除", "del.gif", "toDel()" ] ]);

	function toAdd() {
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("DetailArea");
		ajax.post("get.do");
		coco.showPage('Detail', {
			width : "90%",
			center : 1
		});
	}

	function toModify(oTr) {
		if (oTr == null) {
			oTr = contextmenu.selectedTarget();
		}
		var id = coco.getAttr(oTr, "xu.id");
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("DetailArea");
		ajax.post("get.do", "id=" + encodeURIComponent(id));
		coco.showPage('Detail', {
			width : "90%",
			center : 1
		});
	}

	function toDel() {
		if (!window.confirm("确定要删除该条记录吗?"))
			return;
		var id = contextmenu.selectedId();
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if (this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			cocopage.refresh();
		};
		ajax.post("del.do", "id=" + encodeURIComponent(id));
	}

	function success() {
		alert("保存成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	}

	var tableForm = new TableForm("ItemTbl");

	function changeType(val) {
		var showSplit = val == "1";
		document.getElementById("SplitHeader").style.visibility = showSplit ? "visible" : "hidden";
		document.getElementById("SplitText").style.visibility = showSplit ? "visible" : "hidden";
	}
//-->
</script>
</head>
<body>
<ui:page title="转换配置">
	<f:page action="index.do" file="list.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<tr>
				<th>标识</th>
				<td><ui:input name="id" cssStyle="width:98%" /></td>
				<th>名称</th>
				<td><ui:input name="name" cssStyle="width:98%" /></td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button"
			value="查 询(Q)" onclick="cocopage.query()" accesskey="q" /> <input
			type="button" class="button" value="新 增(A)" onclick="toAdd()"
			accesskey="a" /></div>
	</f:page>
</ui:page>
<ui:panel id="Detail" title="详细信息" popup="true" display="false"
	closable="true">
	<form name="DataForm" xu.s="success();" method="post" action="save.do">
	<div id="DetailArea"></div>
	<div class="submit">
	<table width="100%">
		<tr>
			<td align="left"><input type="button" class="button" value="添 加"
				onclick="tableForm.insertEmptyRow('ItemTblTmp');" /> <input
				type="button" class="button" value="移 除"
				onclick="tableForm.removeRow('ids');" /></td>
			<td align="right"><input type="button" class="button"
				value="保 存" onclick="tableForm.formatForm('fields');cocoform.submit(this);" /></td>
		</tr>
	</table>
	</div>
	</form>
</ui:panel>
<form action="">
<table id="ItemTblTmp" style="display: none;">
	<tr>
		<td><input type="checkbox" name="ids" /></td>
		<td><ui:input name="title" lowToUp="false" /></td>
		<td><ui:input name="field" required="true" lowToUp="false" /></td>
		<td><ui:int name="index" maxlength="4" title="从0算起，若指定了题头，则列号无效。" /></td>
	</tr>
</table></form>
</body>
</html>