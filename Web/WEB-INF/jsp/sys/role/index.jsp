<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="../../global/header.jsp"%>
		<script type="text/javascript">
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],
			                      		["分配菜单", "tablechk.gif", "toGrantMenu()"],
			                      		["分配资源", "tablechk.gif", "toGrantSource()"],
		                      		["删除", "del.gif", "toDel()"]]);
		 
	function toAdd() {
		var oForm = document.forms["DataForm"];
		oForm.elements["id"].value = "";
		oForm.elements["name"].value = "";
		oForm.elements["description"].value = "";
		oForm.elements["valid"].checked = true;
		coco.showPage("Detail",{center:true,top:50,width:"90%"});
	}

	function success() {
		alert("保存成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	}

	function toModify() {
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			cocoform.fillResult("DataForm", this.result);
			coco.showPage("Detail",{center:true,top:50,width:"80%"});
		};
		ajax.post("get!" + contextmenu.selectedId() + ".do");
	}
	
	function toDel() {
		if(!window.confirm("确定要删除该条记录吗?")) return;
		var oForm = document.forms["DataForm"];
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			oForm.elements["id"].value = "";
			oForm.elements["name"].value = "";
			oForm.elements["valid"].checked = true;
			coco.hidePage('Detail');
			cocopage.refresh();
		};
		ajax.post("del!"+contextmenu.selectedId()+".do", null, "POST");
	}

	var menuId = null;
	
	/**
	 * 分配菜单准备
	 */
	function toGrantMenu() {
		menuId = contextmenu.selectedId();
		if(menuId == null || menuId == "") return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				menuTree.refreshCheck(this.result);
				coco.showPage("MenuPanel",{center:true,top:30});
			}
			else coco.alert(this.msg);
		};
		ajax.post("getMenuIds!"+menuId+".do");
	}

	/**
	 * 分配菜单
	 */
	function grantMenu() {
		if(menuId == null || menuId == "") {
			alert("菜单选中ID已经丢失");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("分配成功");
				coco.hidePage("MenuPanel");
			}
			else coco.alert(this.msg);
		};
		var params = "";
		var ids = menuTree.checkeds();
		for(var i = 0; i < ids.length; i++) {
			if(params.length > 0) params += "&";
			params += "menus=" + encodeURIComponent(ids[i]);
		}
		ajax.post("grantMenu!"+menuId+".do", params);
	}
	
	/**
	 * 分配资源准备
	 */
	function toGrantSource() {
		menuId = contextmenu.selectedId();
		if(menuId == null || menuId == "") return;
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("SourceArea");
		ajax.callback = function() {
			if(this.code > 0) {
				coco.showPage("SourcePanel",{center:true,top:30});
			}
			else coco.alert(this.msg);
		};
		ajax.post("loadSource!"+menuId+".do");
	}

	/**
	 * 分配资源
	 */
	function grantSource() {
		if(menuId == null || menuId == "") {
			alert("菜单选中ID已经丢失");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("分配成功");
				coco.hidePage("SourcePanel");
			}
			else coco.alert(this.msg);
		};
		var content = cocoform.postCheckValues("SourceForm", "sources");
		
		ajax.post("grantSource!"+menuId+".do", content);
	}
	//-->
</script>
	</head>
	<body>
<ui:page title="角色管理">
	<f:page action="index.do" file="list.jsp">
	<div class="submit">
		<input type="button" class="button" value="查 询(Q)" onclick="cocopage.query();" accesskey="q" />
		<input type="button" class="button" value="新 增(A)" onclick="toAdd();" accesskey="a"/>
	</div>
	</f:page>
</ui:page>
<ui:panel id="Detail" title="详细信息" popup="true" display="false" closable="true">
<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
<input type="hidden" name="id" />
	<table width="100%" class="form" >
		<colgroup>
			<col width="80" />
			<col />
		</colgroup>
		<tr>
			<th>名称</th>
			<td><ui:input name="name" cssStyle="width:96%" prop="nn:1;" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td><ui:textarea name="description" rows="3" cssStyle="width:96%" /></td>
		</tr>
		<tr>
			<th>有效</th>
			<td><ui:bool name="valid" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="保 存(S)" onclick="cocoform.submit(this)" accesskey="s" />
	</div>
	</form>
</ui:panel>
<ui:panel id="MenuPanel" title="菜单分配" popup="true" display="false" width="480" closable="true">
	<ui:tree tree="menuTree" uri="/sys/menu/tree.do" id="MenuTree" cssClass="scroll" cssStyle="width: 450px; height: 350px;padding:5px;">
	menuTree.checkName = "menuIds";
	menuTree.type = 1;
	</ui:tree>
	<div class="submit">
		<input type=button value="确定选择" class="button" onclick="grantMenu()" />
	</div>
</ui:panel>
<ui:panel id="SourcePanel" title="资源分配" popup="true" display="false" width="550" closable="true">
	<form name="SourceForm">
	<div id="SourceArea" class="scroll" style="width:550px;height: 400px;"></div>
	</form>
	<div class="submit">
		<input type=button value="确定选择" class="button" onclick="grantSource()" />
	</div>
</ui:panel>
	</body>
</html>