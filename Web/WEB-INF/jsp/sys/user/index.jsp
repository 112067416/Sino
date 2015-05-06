<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript">

	function toDel() {
		if(!window.confirm("确定要删除这个用户吗？")) return;
		var form = document.forms["DataForm"];
		var ajax = new Cocoajax();
		ajax.async = false;
		var id = form.elements["id"].value;
		ajax.post("del!"+id+".do");
		if(ajax.code > 0) {
			alert("成功删除");
			cocoform.clear(form);
			form.elements["valid"].checked = true;
			document.getElementById("delbtn").style.visibility = "hidden";
			document.getElementById("savebtn").style.visibility = "hidden";
			form.style.visibility = "hidden";
		}
		else {
			alert(ajax.msg);
		}
	}

	function verify(form) {
		if(form.name == "DataForm") {
			var pswd = form.elements["pswd"].value.replace(/^\s+|\s+$/, "");
			if(form.elements["$type"].value != "modify") {
				if(pswd == "") {
					alert("密码不能为空");
					return false;
				}
			}
			if(pswd != "" && pswd != form.elements["typepswd"].value.replace(/^\s+|\s+$/, "")) {
				alert("两次输入的密码不一致");
				return false;
			}
		}
		return true;
	}
	
	function success() {
		alert("保存成功");
		var form = document.forms["DataForm"];
		cocoform.clear(form);
		form.elements["valid"].checked = true;
		document.getElementById("delbtn").style.visibility = "hidden";
		document.getElementById("savebtn").style.visibility = "hidden";
		form.style.visibility = "hidden";
	}
</script>
</head>
<body resizes="TreeArea:h-95;">
<ui:page title="用户管理">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="50%" valign="top"><ui:panel title="员工组织树">
				<ui:tree tree="tree" uri="/sys/person/tree.do?all=true" id="TreeArea" cssStyle="padding:5px;" cssClass="scroll">
				tree.type = 1;
				tree.icons["folder"] = "users.gif";
				tree.eicons["folder"] = "users.gif";
				tree.icons["file"] = "user.gif";
			tree.click = function(id) {
				var form = document.forms["DataForm"];
				if(this.selectedValue(this.leaf_) == "0") {
					form.style.visibility = "hidden";
					return;
				}
				else {
					form.style.visibility = "visible";
				}
				var ajax = new Cocoajax();
				ajax.async = true;
				ajax.callback = function() {
					var obj = null;
					eval("obj = " + this.result + ";");
					cocoform.clear(form);
					cocoform.fillObject(form, obj);
					if(obj.id != null) {
						document.getElementById("delbtn").style.visibility = "visible";
						form.elements["valid"].checked = true;
						form.elements["$type"].value = "modify";
						form.elements["id"].disabled = true;
					}
					else {
						form.elements["valid"].checked = true;
						document.getElementById("delbtn").style.visibility = "hidden";
						form.elements["id"].disabled = false;
					}
					document.getElementById("savebtn").style.visibility = "visible";
				};
				ajax.post("getByPerson!"+id+".do");
			};
			</ui:tree>
			</ui:panel></td>
			<td width="50%" valign="top"><ui:panel title="用户信息">
				<form name="DataForm" action="save.do" method="post" xu.s="success()" xu.ajax="true" style="visibility:hidden">
				<input type="hidden" name="$type" value="" />
				<input type="hidden" name="person.id" value="" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="form">
					<colgroup>
						<col width="10%" />
						<col width="40%" />
						<col width="18%" />
						<col width="32%" />
					</colgroup>
					<tr>
						<th>帐号</th>
						<td><ui:input name="id" cssStyle="width:96%" prop="nn:1;type:custom;rule:^(\\\\w|\\\\d)+$;rule1:g;msg:只能输入字母和数字" />
						</td>
						<th>级别</th>
						<td><ui:input name="grade" cssStyle="width:96%" />
						</td>
					</tr>
					<tr>
						<th>密码</th>
						<td><input type="password" name="pswd" class="normal" style="width:98%" /></td>
						<th>密码确认</th>
						<td><input type="password" name="typepswd" class="normal" style="width:98%" /></td>
					</tr>
					<tr>
						<th>顺序</th>
						<td><ui:int name="order" scale="6" maxlength="7" /></td>
						<th>有效标志</th> 
						<td><ui:bool name="valid" checked="true" /></td>
					</tr>
				</table>
				<div class="submit">
				<input type="button" class="button" value="保 存(S)" onclick="cocoform.submit(this);" accesskey="s" id="savebtn" style="visibility: hidden;" />
				<input type="button" class="button" value="删 除(D)" onclick="toDel()" accesskey="d" id="delbtn" style="visibility: hidden;" />
				</div>
				</form>
			</ui:panel></td>
		</tr>
	</table>
</ui:page>
</body>
</html>
