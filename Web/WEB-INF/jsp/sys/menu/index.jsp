<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单管理</title>
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
		if(!window.confirm("确定要删除菜单（包含所有下级菜单）吗？")) return;
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
<body resizes="TreeArea:h-80;">
<ui:page title="菜单管理">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="50%" valign="top"><ui:panel title="菜单树">
				<ui:tree tree="tree" uri="tree.do?all=true" id="TreeArea" cssClass="scroll">
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
				};
				ajax.post("get!"+id+".do");
			};
			</ui:tree>
			</ui:panel></td>
			<td width="50%" valign="top"><ui:panel title="菜单信息">
				<form name="DataForm" action="save.do" method="post" xu.s="success()" xu.ajax="true">
				<input type="hidden" name="id" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="form">
					<colgroup>
						<col width="80" />
						<col width="60" />
						<col width="80" />
						<col width="60" />
						<col width="80" />
						<col />
					</colgroup>
					<tr>
						<th>名称</th>
						<td colspan="5"><ui:input name="name" cssStyle="width:96%" prop="nn:1;" />
						</td>
					</tr>
					<tr>
						<th>上级</th>
						<td colspan="5"><ui:treebox name="parent" prop="nn:1;" cssStyle="width:96%;" items="items" fieldLabel="name" fieldOrder="order" root="0" headerValue="0" headerText="最高级"/></td>
					</tr>
					<tr>
						<th>URL</th>
						<td colspan="5"><ui:input name="url" cssStyle="width:96%" lowToUp="false" />
						</td>
					</tr>
					<tr>
						<th>弹出</th> 
						<td><ui:bool name="popup" checked="true" /></td>
						<th>有效</th> 
						<td><ui:bool name="valid" checked="true" /></td>
						<th>顺序</th>
						<td><ui:int name="order" scale="6" maxlength="7" /></td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="5"><ui:textarea name="description" rows="3"
							cssStyle="width:96%" /></td>
					</tr>
				</table>
				<div class="submit">
				<input type="button" class="button" value="新 增(A)" onclick="toAdd()" accesskey="a"/>
				<input type="submit" class="button" value="保 存(S)" accesskey="s" />
				<input type="button" class="button" value="删 除(D)" onclick="toDel()" accesskey="d" id="delbtn" style="visibility: hidden;" />
				</div>
				</form>
			</ui:panel></td>
		</tr>
	</table>
</ui:page>
</body>
</html>
