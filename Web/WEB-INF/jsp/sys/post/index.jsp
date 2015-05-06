<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="../../global/header.jsp"%>
		<script type="text/javascript">
		contextmenu.putMenus("menu", [["修改", "tablechk.gif", "toModify()"],
		                      		["授权", "tablechk.gif", "toGrantUser()"],
		                      		["分配角色", "tablechk.gif", "toGrantRole()"],
		                      		["删除", "del.gif", "toDel()"]]);
		 
	function toAdd(id) {
		var oForm = document.forms["DataForm"];
		cocoform.clear(oForm);
		oForm.elements["id"].value = "";
		oForm.elements["dept.id"].value = id;
		oForm.elements["privDept.id"].value = id;
		oForm.elements["valid"].checked = true;
		oForm.elements["share"].checked = false;
		oForm.elements["contain"].checked = false;
	}

	function toModify() {
		var ajax = new Cocoajax();
		ajax.callback = function() {
			cocoform.fillResult("DataForm", this.result);
		};
		ajax.post("get!" + contextmenu.selectedId() + ".do");
	}

	function success() {
		var oForm = document.forms["DataForm"];
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ListArea");
		ajax.post("list!" + tree.selectedValue() + ".do");
		if(oForm.elements["id"].value == "") {
			oForm.elements["name"].value = "";
			oForm.elements["valid"].checked = true;
			oForm.elements["share"].checked = false;
			oForm.elements["contain"].checked = false;
		}
	}

	function toDel() {
		if (!window.confirm("确定要删除吗?")) return;
		var oForm = document.forms["DataForm"];
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code >= 0) {
				alert("删除成功");
				document.getElementById("ListArea").innerHTML = this.result;
			}
		};
		ajax.post("del!" + contextmenu.selectedId() + ".do", "dept="+oForm.elements["dept.id"].value);
		oForm.elements["id"].value = "";
		oForm.elements["name"].value = "";
		oForm.elements["valid"].checked = true;
		oForm.elements["share"].checked = false;
		oForm.elements["contain"].checked = false;
	}

	var menuId = null;
	function toGrantUser() {
		menuId = contextmenu.selectedId();
		if(menuId == null || menuId == "") return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				userTree.refreshCheck(this.result);
				if(tree.selectedValue() != null) userTree.expandTo("d_"+tree.selectedValue());
				coco.showPage("UserArea",{center:true,top:30});
			}
			else coco.alert(this.msg);
		};
		ajax.post("getUserIds!"+menuId+".do");
	}

	function toGrantRole() {
		menuId = contextmenu.selectedId();
		if(menuId == null || menuId == "") return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				var form = document.forms["RoleForm"];
				var els = form.elements["roleIds"];
				if(els != null) {
					var values = ","+this.result+",";
					if(els.length == 0) els.checked = values.indexOf(","+els.value+",") != -1;
					else {
						for(var i = 0; i < els.length; i++) {
							els[i].checked = values.indexOf(","+els[i].value+",") != -1;
						}
					}
				}
				coco.showPage("RoleArea",{center:true,top:30});
			}
			else coco.alert(this.msg);
		};
		ajax.post("getRoleIds!"+menuId+".do");
	}
	
	function grantUser() {
		if(menuId == null || menuId == "") {
			alert("岗位ID已经丢失");
			return;
		}
		var ids = userTree.checkeds(1);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("授权成功");
				coco.hidePage("UserArea");
			}
			else coco.alert(this.msg);
		};
		var params = "";
		for(var i = 0; i < ids.length; i++) {
			if(i > 0) params += "&";
			params += "users=" + encodeURIComponent(ids[i]);
		}
		ajax.post("grantUser!"+menuId+".do", params);
	}
	
	function grantRole() {
		if(menuId == null || menuId == "") {
			alert("岗位ID已经丢失");
			return;
		}
		var params = "";
		var form = document.forms["RoleForm"];
		var els = form.elements["roleIds"];
		var values = ","+this.result+",";
		if(els != null) {
			if(els.length == 0) {
				 if(els.checked) params += "&roles=" + encodeURIComponent(els.value);
			}
			else {
				for(var i = 0; i < els.length; i++) {
					 if(els[i].checked) params += "&roles=" + encodeURIComponent(els[i].value);
				}
			}
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("分配角色成功");
				coco.hidePage("RoleArea");
			}
			else coco.alert(this.msg);
		};
		ajax.post("grantRole!"+menuId+".do", params.length > 0 ? params.substring(1) : null);
	}
	//-->
</script>
	</head>
	<body>
	<ui:page title="岗位管理">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td valign="top" width=360>
				<ui:tree tree="tree" uri="/sys/dept/tree.do" id="Treee" cssStyle="width: 350px; height: 450px; padding: 5px;" cssClass="scroll">
				tree.type = 1;
				tree.icons["folder"] = "users.gif";
				tree.eicons["folder"] = "users.gif";
				tree.icons["file"] = "users.gif";
				tree.click = function(id) {
					var ajax = new Cocoajax();
					ajax.dataDiv = document.getElementById("ListArea");
					ajax.post("list!" + id + ".do");
					toAdd(id);
				};
				</ui:tree>
				</td>
				<td valign="top" align="left" >
					<ui:panel title="岗位列表">
					<div align=center id="ListArea" class="scroll"
						style="border: 0px; width: 98%; height: 200px;">
						<%@include file="list.jsp"%>
					</div>
					</ui:panel>
					<br/>
					<ui:panel title="岗位信息">
					<form action="save.do" xu.ajax="" xu.s="success()" name="DataForm" method="post">
					<input type="hidden" name="id" />
					<table width=98% align=center border="0" cellpadding="0" cellspacing="0" class="form">
						<colgroup>
							<col width=80>
							<col width="80">
							<col width=80>
							<col width="80">
							<col width=80>
							<col>
						</colgroup>
						<tr>
							<th>
								岗位名称
							</th>
							<td colspan="5">
							<ui:input name="name" required="true" cssStyle="width:98%;"/>
							</td>
						</tr>
						<tr>
							<th>
								所属部门
							</th>
							<td colspan="5">
							<ui:treebox name="dept.id" items="depts" fieldLabel="name" fieldId="id" fieldParent="parent" fieldOrder="order" cssStyle="width:98%;"/>
							</td>
						</tr>
						<tr>
							<th>
								权限部门
							</th>
							<td colspan="5">
							<ui:treebox name="privDept.id" items="depts" fieldLabel="name" fieldId="id" fieldParent="parent" fieldOrder="order" cssStyle="width:98%;"/>
							</td>
						</tr>
						<tr>
							<th>
								下级兼管
							</th>
							<td>
							<ui:bool name="contain"/>
							</td>
							<th>
								跨部门
							</th>
							<td>
							<ui:bool name="share"/>
							</td>
							<th>
								有效标志
							</th>
							<td>
							<ui:bool name="valid"/>
							</td>
						</tr>
					</table>
					<div class="submit">
						<input type=button value="保 存" class="button" onclick="cocoform.submit(this);" />
						&nbsp;&nbsp;
						<input type=button value="新 增" class="button" onclick="toAdd(tree.selectedValue(tree.id_));" />
						&nbsp;&nbsp;
					</div>
					</form></ui:panel>
				</td>
			</tr>
		</table>
	</ui:page>
	<ui:panel id="UserArea" title="用户授权" popup="true" display="false" width="480" closable="true">
	<ui:tree tree="userTree" uri="/sys/user/tree.do" id="UserTree" cssClass="scroll" cssStyle="width: 450px; height: 350px;padding:5px;">
	userTree.checkName = "userId";
	userTree.type = 1;
	userTree.icons["folder"] = "users.gif";
	userTree.eicons["folder"] = "users.gif";
	userTree.icons["file"] = "user.gif";
	</ui:tree>
	<div class="submit">
		<input type=button value="确定选择" class="button" onclick="grantUser()" />
	</div>
	</ui:panel>
	<ui:panel id="RoleArea" title="角色分配" popup="true" display="false" width="480" closable="true">
	<form name="RoleForm">
	<div id="roleList" class="scroll" style="width: 450px; height: 350px;padding:5px;">
	<%@include file="../role/checklist.jsp" %>
	</div>
	<div class="submit">
		<input type=button value="确定选择" class="button" onclick="grantRole()" />
	</div></form>
	</ui:panel>
	</body>
</html>