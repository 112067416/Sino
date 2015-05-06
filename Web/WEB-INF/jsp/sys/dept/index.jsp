<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>机构管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">
	function toAdd() {
		document.getElementById("delbtn").style.visibility = "hidden";
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
		if(!window.confirm("确定要删除机构（包含所有下级机构）吗？")) return;
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
</script>
</head>
<body resizes="TreeArea:h-100;">
<ui:page title="机构管理">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="50%" valign="top"><ui:panel title="组织机构树">
				<ui:tree tree="tree" uri="/sys/dept/tree.do?all=true" id="TreeArea" cssClass="scroll">
				tree.type = 1;
				tree.icons["folder"] = "users.gif";
				tree.eicons["folder"] = "users.gif";
				tree.icons["file"] = "users.gif";
			tree.click = function(id) {
				var ajax = new Cocoajax();
				ajax.async = false;
				ajax.post("exclude!"+id+".do");
				document.forms["DataForm"].elements["parent"].parentNode.innerHTML = ajax.result;
				ajax.async = true;
				ajax.callback = function() {
					cocoform.fillResult("DataForm", this.result);
					document.getElementById("delbtn").style.visibility = "visible";
				};
				ajax.post("get!"+id+".do");
			};
			</ui:tree>
			</ui:panel></td>
			<td width="50%" valign="top"><ui:panel title="机构信息">
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
						<th>类型</th>
						<td colspan="3"><sys:codeSelect name="type" code="#COCO_DEPT_TYPE" />
						</td>
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
				<input type="button" class="button" value="新 增(A)" onclick="toAdd()" accesskey="a"/>
				<input type="button" class="button" value="保 存(S)" onclick="cocoform.submit(this);" accesskey="s" />
				<input type="button" class="button" value="删 除(D)" onclick="toDel()" accesskey="d" id="delbtn" style="visibility: hidden;" />
				</div>
				</form>
			</ui:panel></td>
		</tr>
	</table>
</ui:page>
</body>
</html>
