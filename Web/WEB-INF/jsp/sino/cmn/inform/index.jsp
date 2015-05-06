<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>目录管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/upload.js"></script>
<script type="text/javascript">
<!--
contextmenu.putMenus("menu", [["阅读", "view.gif", "view()"],
                              ["设置用户", "settings.gif", "toSetting()"],["删除", "del.gif", "toDel()"]]);
function doAdd(btn) {
	var qForm = btn.form;
	var $directory = qForm.elements["directory"].value;
	var form = document.forms["DataForm"];
	var directory = form.elements["directory.id"].value;
	cocoform.clear(form);
	form.elements["directory.id"].value = ($directory != null && $directory.length > 0 ? $directory : directory);
	coco.showPage("Detail",{center:true,top:50,width:"80%"});
}
//批量删除
function doDel() {
	var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
	if(ids == null || ids == "") {
		alert("请选择要做删除操作的资料");
		return;
	}
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code <= 0) {
			coco.alert(this.msg);
			return;
		}
		alert("删除成功");
		cocopage.refresh();

	};
	ajax.post("dels.do", ids);
}
//单条删除
function toDel() {
	if(!window.confirm("确定要删除该条记录吗?")) return;
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code <= 0) {
			coco.alert(this.msg);
			return;
		}
		alert("删除成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	};
	ajax.post("del!"+contextmenu.selectedId()+".do", null, "POST");
}
//弹出用户授权页面
function toSetting() {
	var menuId = contextmenu.selectedId();
	if(menuId == null || menuId == "") return;
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code > 0) {
			userTree.refreshCheck(this.result);
			coco.showPage("UserArea",{center:true,top:50,width:"80%"});
		}
		else coco.alert(this.msg);
	};
	ajax.post("getUsers!"+menuId+".do");
}

//对资料进行授权
function grantUser() {
	var ids = userTree.checkeds(1);
	if(ids == null || ids == "") {
		alert("请选择用户");
		return;
	}
	var menuId = contextmenu.selectedId();
	if(menuId == null || menuId == "") return;
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code > 0) {
			alert("授权成功");
			coco.hidePage("UserArea");
		}
		else coco.alert(this.msg);
	};
	var users = "";
	for(var i = 0; i < ids.length; i++) {
		if(i > 0) users += ",";
		users += encodeURIComponent(ids[i]);
	}
	ajax.post("grantUser!"+menuId+".do", "users="+users);
}
//阅读附件
function view(oTr) {
	if(oTr == null) oTr = contextmenu.selectedTarget();
	var key1 = coco.getAttr(oTr, "xu.id");
	var height = screen.availHeight;
    var width = screen.availWidth;
	window.open(path + "/sino/cmn/inform/viewAttach.do?id="+key1, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
}

function doSave() {
	if(!confirm("是否确定保存?")) return;
	var oForm = document.forms["DataForm"];
	cocoform.submit(oForm, function() {
		if(this.code <= 0) {
			alert(this.msg);
         	return;
		}
		alert("保存成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	});
}

function doQuery(el) {
	var oForm = el.form;
	oForm.elements["directory"].value = "";
	cocopage.query();
}

function upload_callback(name) {
	if(name == null || name.length == 0) return;
	var $name = name.replace(/^\s+|\s+$/gi,'');
	$name = name.substring(0, name.lastIndexOf('.'));
	var oForm = document.forms["DataForm"];
	oForm.elements["name"].value = $name;
}
//-->
</script>
</head>
<body resizes="TreeArea:h-100;">
<ui:page title="资料管理">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="35%" valign="top"><ui:panel title="目录树">
				<ui:tree tree="tree" uri="/sys/directory/tree.do?all=true" id="TreeArea" cssClass="scroll">
				tree.type = 1;
				tree.click = function(id) {
					var oForm = document.forms["PageForm_page"];
					oForm.elements["directory"].value = id;
					cocopage.query();
				};
			</ui:tree>
			</ui:panel></td>
			<td width="65%" valign="top"><ui:panel title="资料信息">
				<f:page action="index.do" file="list.jsp" size="10">
					<input type="hidden" name="directory" />
					<table width="100%" class="form">
						<colgroup>
							<col width="10%" />
							<col width="20%" />
							<col width="70%" />
						</colgroup>
						<tr>
							<th>名称</th>
							<td><ui:input name="name" title="名称" maxlength="20" /></td>
							<td><div class="submit"><input type="button" class="button" value="查 询" onclick="doQuery(this);" />&nbsp;&nbsp;<input type="button" class="button" value="新 增" onclick="doAdd(this)" />&nbsp;&nbsp;<input type="button" class="button" value="删 除" onclick="doDel()" /></div></td>
						</tr>
					</table>
					<br />
				</f:page>
			</ui:panel></td>
		</tr>
	</table>
</ui:page>
<ui:panel id="Detail" title="新增资料" popup="true" display="false" closable="true">
	<form name="DataForm" xu.s="success();" method="post" action="save.do" >
		<table width="100%" class="form" >
			<colgroup><col width="18%" /><col width="32%" /><col width="18%" /><col width="32%" /></colgroup>
			<tr>
				<th>目录</th>
				<td>
					<ui:treebox name="directory.id" prop="nn:1;" cssStyle="width:96%;" items="directories" fieldLabel="name" fieldOrder="order" root="0" />
				</td>
				<th>名称</th>
				<td><ui:input name="name" title="名称" maxlength="16" required="true" imeMode="false" /></td>
			</tr>
			<tr>
				<th>文件上传</th>
				<td colspan="2"><ui:attach id="inform" attachName="attachment" label="上传Excel、Word或图片" width="150" single="true" typeFilter="#excel#|#word#|#image#" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>备&nbsp;&nbsp;&nbsp;&nbsp;注</th>
				<td colspan="3"><ui:textarea name="bz" cssStyle="width:95%"/></td>
			</tr>
		</table>
		<div class="opt-btm"><input type="button" class="button" value="保 存 " onclick="doSave();" />  <input type="button" class="button"
		value="关 闭" onclick="coco.hidePage('Detail')" /></div>
	</form>
</ui:panel>
<ui:panel id="UserArea" title="用户授权" popup="true" display="false" width="480" closable="true">
	<ui:tree tree="userTree" uri="/sys/user/tree.do" id="UserTree" cssClass="scroll" cssStyle="width: 450px; height: 350px;padding:5px;">
	userTree.checkName = "userId";
	userTree.type = 1;
	</ui:tree>
	<div class="submit"> <input type=button value="确定选择" class="button" onclick="grantUser()" />  <input type=button value="关   闭" class="button" onclick="coco.hidePage('UserArea')" /></div>
</ui:panel>
</body>
</html>