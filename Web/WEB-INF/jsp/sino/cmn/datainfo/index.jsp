<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/f" prefix="f" %><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/jsp/global/header.jsp" %>
	<script type="text/javascript">
	<!--

	contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],
	                        		["删除", "del.gif", "toDel()"],
	                          		["查看", "preview.gif", "toView()"]]);

	// 添加
	function toAdd() {
		var form = document.forms["DataForm"];
		cocoform.clear(form);
		coco.showPage("Detail", {
			center : true,
			top : 50,
			width : "80%"
		});
	}

	// 删除
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
		ajax.post("del!"+id+".do", null, "POST");
	}

	// 修改
	function toModify(oTr) {
		var id = oTr != null ? oTr.getAttribute("xu.id") : contextmenu.selectedId();
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
			coco.showPage("Detail",{center:true,top:50,width:"80%"});
		};
		ajax.post("get!"+id+".do", null, "POST");
	}

	//查看
	function toView() {
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ItemDiv");
		ajax.post("view!"+contextmenu.selectedId()+".do", null, "POST");
		coco.showPage("ItemArea",{center:true,top:50,width:"80%"});
	}

	// 成功提示
	function success() {
		alert("保存成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	}

	//-->
	</script>
	</head>
<body>
<ui:page title="短词库">
	<f:page action="index.do" file="list.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="15%" />
				<col width="25%" />
				<col width="15%" />
				<col />
			</colgroup>
			<tr>
				<th>短语</th>
				<td><ui:input name="name" cssClass="normal" /></td>
				<th>类型</th>
				<td><ui:input name="type" cssClass="normal" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="查 询(Q)" onclick="cocopage.query()" accesskey="q" />
			<input type="button" class="button" value="新 增(A)" onclick="toAdd()" accesskey="a"/>
		</div>
	</f:page>
</ui:page>
<ui:panel id="Detail" title="详细信息" popup="true" display="false" closable="true">
	<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do">
	<input type="hidden" name="id" />
	<input type="hidden" name="$type" />
	<table width="100%" class="form" style="margin-top: 15px;">
		<colgroup>
			<col width="13%" />
			<col width="20%" />
			<col width="13%" />
			<col width="20%" />
			<col width="13%" />
			<col width="21%" />
		</colgroup>
		<tr>
			<th>短语</th>
			<td><ui:input name="name" cssClass="normal" prop="nn:1;" /></td>
			<th>类型</th>
			<td><ui:input name="type" cssClass="normal" /></td>
			<th>代码</th>
			<td><ui:input name="code" cssClass="normal" /></td>
		</tr>
		<tr>
			<th>关键字</th>
			<td colspan="5"><ui:input name="keyw" cssClass="normal" cssStyle="width: 95%" maxlength="200"/></td>
		</tr>
		<tr>
			<th>描述</th>
			<td colspan="5"><textarea rows="12" name="memo" class="normal" style="width: 95%; overflow-y: auto; overflow-x: hidden;"></textarea></td>
		</tr>
	</table>
	<div class="submit">
		<input type="submit" class="button" value="保 存(S)" accesskey="s" />
		<input type="button" class="button" value="关 闭(C)" onclick="coco.hidePage('Detail')" accesskey="c" /> 
	</div>
	</form>
</ui:panel>
</body>
</html>