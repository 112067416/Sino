<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>目录管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">
	function toAdd() {
		document.getElementById("delbtn").style.visibility = "hidden";
		document.getElementById("deptbtn").style.visibility = "hidden";
		var form = document.forms["DataForm"];
		var ajax = new Cocoajax();
		ajax.async = false;
		ajax.post("exclude!0.do");
		form.elements["parent"].parentNode.innerHTML = ajax.result;
		cocoform.clear(form);
		form.elements["valid"].checked = true;
		if(tree.selectedNode != null) {
			cocoform.setValue(form.elements["parent"], tree.selectedValue());
		}
	}

	function toDel() {
		if(!window.confirm("确定要删除目录（包含所有下级目录）吗？")) return;
		var form = document.forms["DataForm"];
		var ajax = new Cocoajax();
		ajax.async = false;
		var id = form.elements["id"].value;
		ajax.post("del!"+id+".do");
		if(ajax.code > 0) {
			alert("成功删除");
			tree.refresh(true);
			cocoform.clear(form);
			form.elements["valid"].checked = true;
			document.getElementById("delbtn").style.visibility = "hidden";
		}
		else {
			coco.alert(ajax.msg);
		}
	}
	
	function success() {
		alert("保存成功");
		tree.refresh(true);
		var form = document.forms["DataForm"];
		if(form.elements["id"].value == "")	toAdd();		
	}

	//弹出用户授权页面
	function toSetting(oForm) {
		var id = oForm.elements["id"].value;
		if(id == null || id.length == 0) {
			alert("请在左边指定资料室目录");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				deptTree.refreshCheck(this.msg);
				coco.showPage("DeptArea",{center:true,top:20,width:"90%"});
				return;
			}
			deptTree.refreshCheck(null);
			coco.showPage("DeptArea",{center:true,top:20,width:"90%"});
			return;
		};
		ajax.post(path+"/sino/cmn/inform/getDepts!"+id+".do");
	}
	//对资料进行授权
	function grantDept(oInput) {
		var ids = deptTree.checkeds(0);
		if(ids == null || ids.length == 0) {
			alert("请选择部门");
			return;
		}
		var form = document.forms["DataForm"];
		var id = form.elements["id"].value;
		if(id == null || id.length == 0) {
			alert("请在左边指定资料室目录");
			return;
		}
		var ajax = new Cocoajax();
		ajax.oInput = oInput;
		ajax.callback = function() {
			if(this.code > 0) {
				alert("授权成功");
				coco.hidePage("DeptArea");
				return;
			}
			coco.alert(this.msg);
		};
		var depts = "";
		for(var i = 0; i < ids.length; i++) {
			if(i > 0) depts += ",";
			depts += encodeURIComponent(ids[i]);
		}
		ajax.post(path+"/sino/cmn/inform/grantDept!"+id+".do", "depts="+depts);
	}
</script>
</head>
<body resizes="TreeArea:h-100;">
<ui:page title="目录管理">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="50%" valign="top"><ui:panel title="目录树">
				<ui:tree tree="tree" uri="/sys/directory/tree.do?all=true" id="TreeArea" cssClass="scroll">
				tree.type = 1;
				tree.click = function(id) {
					var ajax = new Cocoajax();
					ajax.async = false;
					ajax.post("exclude!"+id+".do");
					document.forms["DataForm"].elements["parent"].parentNode.innerHTML = ajax.result;
					ajax.async = true;
					ajax.callback = function() {
						cocoform.fillResult("DataForm", this.result);
						document.getElementById("delbtn").style.visibility = "visible";
						document.getElementById("deptbtn").style.visibility = "visible";
					};
					ajax.post("get!"+id+".do");
				};
			</ui:tree>
			</ui:panel></td>
			<td width="50%" valign="top"><ui:panel title="目录信息">
				<form name="DataForm" action="save.do" method="post" xu.s="success()" xu.ajax="true">
				<input type="hidden" name="id" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="form">
					<colgroup>
						<col width="10%" />
						<col width="40%" />
						<col width="18%" />
						<col width="32%" />
					</colgroup>
					<tr>
						<th>名称</th>
						<td colspan="3"><ui:input name="name" cssStyle="width:96%" prop="nn:1;" />
						</td>
					</tr>
					<tr>
						<th>上级</th>
						<td colspan="3"><ui:treebox name="parent" prop="nn:1;" cssStyle="width:96%;" items="items" fieldLabel="name" fieldOrder="order" root="0" headerValue="0" headerText="最高级"/></td>
					</tr>
					<tr>
						<th>顺序</th>
						<td><ui:int name="order" scale="6" maxlength="7" /></td>
						<th>有效标志</th> 
						<td><ui:bool name="valid" checked="true" /></td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3"><ui:textarea name="remark" rows="3"
							cssStyle="width:96%" /></td>
					</tr>
				</table>
				<div class="submit">
				<input type="button" class="button" value="新   增" onclick="toAdd()" />
				<input type="button" class="button" value="保   存" onclick="cocoform.submit(this);" />
				<input type="button" class="button" value="设置部门" onclick="toSetting(this.form)" id="deptbtn" style="visibility: hidden;" />
				<input type="button" class="button" value="删   除" onclick="toDel()" id="delbtn" style="visibility: hidden;" />
				</div>
				</form>
			</ui:panel></td>
		</tr>
	</table>
</ui:page>
<ui:panel id="DeptArea" title="设置部门" popup="true" display="false" width="480" closable="true">
	<ui:tree tree="deptTree" uri="/sys/dept/tree.do?all=true" id="DeptTree" cssClass="scroll" cssStyle="width: 450px; height: 350px;padding:5px;">
	deptTree.checkName = "deptId";
	deptTree.type = 1;
	</ui:tree>
	<div class="submit"><input type=button value="确定选择" class="button" onclick="grantDept(this)" />  <input type=button value="关   闭" class="button" onclick="coco.hidePage('DeptArea')" /></div>
</ui:panel>
</body>
</html>
