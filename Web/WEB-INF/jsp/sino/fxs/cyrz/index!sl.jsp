<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript">
<!--
	contextmenu.putMenus("menu", [["接收采样单", "download.gif", "toRecv()"],["发送样品", "upload.gif", "toSend()"],["删除", "edit.gif", "toDelete()"],["查看", "view.gif", "toView()"]]);
	contextmenu.putMenus("menuEnd", [["查看", "view.gif", "toView()"]]);
	//收单更新
	function toRecv() {
		var id = contextmenu.selectedId();
		if(!confirm("是否确定接收采样单?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("收单成功");
				cocopage.refresh();
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("update.do", "type=slsd&id="+id);
	}

	//发送样品
	function toSend(oTr) {
		var id = null;
		if(oTr == null) id = contextmenu.selectedId();
		else id = coco.getAttr(oTr, "xu.id");
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("FsypArea");
		ajax.callback = function() {
			if(this.code > 0) {
				coco.showPage("Detail", { center : true, top : 20, width : "80%"});
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("toFsyp.do", "id="+id);
	}

	//SL剪切线删除自已发送的采样单记录
	function toDelete(oTr) {
		if(!confirm("是否确定删除采样单?")) return;
		var id = null;
		if(oTr == null) id = contextmenu.selectedId();
		else id = coco.getAttr(oTr, "xu.id");
		var postContent = coco.parseParams([{name:"id",value:id},{name:"fsxb",value:"SL"}]);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			cocopage.refresh();
		};
		ajax.post("del.do", postContent);

	}
	// 查看采样单
	function toView(oTr) {
		var id = null;
		if(oTr == null) id = contextmenu.selectedId();
		else id = coco.getAttr(oTr, "xu.id");
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ViewArea");
		ajax.callback = function() {
			if(this.code > 0) {
				coco.showPage("View", { center : true, top : 0, width : "80%"});
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("view.do", "id="+id);
	}
	//新增采样单
	function toAdd() {
		var oForm = document.forms["CydForm"];
		var ignores = ["ban", "zu", "bz"];
		cocoform.clear(oForm,ignores);
		coco.showPage("CydDetail", {width:"69%", center:1});
		oForm.elements["jbno"].focus();
	}
	//根据采样钢卷号，获得其对应的镀锡量、涂油量和指示书号
	function loadFxmb(el) {
		var oForm = document.forms["CydForm"];
		var jbno = el.value.replace(/^\s+|\s+$/gi,'');
		if(jbno == null || jbno.length == 0) {
			alert("Coil No. 不能为空");
			return;
		}
		var postContent = coco.parseParams([{name:"jbno",value:jbno}]);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				oForm.elements["dxl"].value = '';
				oForm.elements["tyl"].value = '';
				oForm.elements["zsno"].value = '';
				oForm.elements["jbno"].focus();
				return;
			}
			var obj;
			eval("obj=" + this.result + ";");
			oForm.elements["dxl"].value = obj.dxl;
			oForm.elements["tyl"].value = obj.tyl;
			oForm.elements["zsno"].value = obj.zsno;
		};
		ajax.post("loadFxmb.do",postContent);
	}
	//保存操作
	function doSave(oForm, oInput) {
		var eles = oForm.elements["fxxms"];
		var el, fxxms = null, cywzs = null;
		for(var i = 0; i < eles.length; i++) {
			el = eles[i];
			if(!el.checked) continue;
			if(fxxms == null) {
				fxxms = el.value;
			} else {
				fxxms = fxxms + "," + el.value;
			}
		}
		if(fxxms == null || fxxms.length == 0) {
			alert("请选择分析项目");
			return;
		}
		cocoform.submit(oForm, function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert('保存成功');
			cocopage.refresh();
			coco.hidePage('CydDetail');
			}, "是否确定保存?", null, oInput);

	}

	function setSlBz(oForm, el) {
		var $el = oForm.elements["slBz"];
		var bz = $el.value.replace(/^\s+|\s+$/gi, '');
		bz = bz != null && bz.length > 0 ? bz : "";
		var xm = el.value;
		if(el.checked) {
			if(bz.indexOf(xm) >= 0) return;
			if(bz.length == 0) {
				$el.value = xm;
			} else {
				$el.value = bz + "," + xm;
			}
		} else {
			if(bz.indexOf(xm) < 0) return;
			var rgExp = new RegExp(xm+',|('+xm+'$)' , "g");
			$el.value = bz.replace(rgExp, '');
		}
	}
//-->
</script>
</head>
<body>
<ui:page title="采样记录维护">
<f:page action="index.do" file="list.jsp">
<input name="type" type="hidden" value="${type }" />
<table width="100%" class="form">
	<colgroup><col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="30%"></colgroup>
	<tr>
		<th style="text-align: left;">指示书NO</th>
		<td><ui:input name="zsno" maxlength="6" /></td>
		<th style="text-align: left;">Coil No</th>
		<td><ui:input name="jbno" maxlength="8" /></td>
		<th style="text-align: left;">单号</th>
		<td><ui:input name="id" maxlength="9" /></td>
		<th style="text-align: left;">发行日期</th>
		<td><ui:date name="dateBegin" /> 至 <ui:date name="dateEnd" /></td>
	</tr>
</table>
<div class="submit"><input type="button" class="button" value="查  询" onclick="cocopage.query();" />  <input type="button" class="button" value="新增分析单" onclick="toAdd()" /></div>
</f:page>
</ui:page>
<ui:panel id="CydDetail" title="新增采样分析单" popup="true" display="false" closable="true">
<form name="CydForm" action="saveSl.do" method="post" >
<table width="100%" align="center" class="form">
	<colgroup><col width="19%" /><col width="31%" /><col width="15%" /><col width="35%" /></colgroup>
	<tr>
		<th>班</th>
		<td><sys:codeSelect name="ban" code="#SINO_BAN" prop="nn:1;" value="ban" /></td>
		<th>组</th>
		<td><sys:codeSelect name="zu" code="#SINO_ZU" prop="nn:1;" value="zu" /></td>
	</tr>
	<tr>
		<th>指示连号</th>
		<td><ui:input name="zsno" maxlength="6" required="true" readonly="true" /></td>
		<th>Coil No.</th>
		<td><ui:input name="jbno" maxlength="7" required="true" onkeydown="if(window.event.keyCode==13) loadFxmb(this);" onblur="loadFxmb(this);" /></td>
	</tr>
	<tr>
		<th>镀锡量</th>
		<td><ui:input name="dxl" maxlength="12" required="true" readonly="true" /></td>
		<th>涂油量</th>
		<td><ui:input name="tyl" maxlength="7" required="true" readonly="true" /></td>
	</tr>
	<tr>
		<th height="150" style="text-align: right; vertical-align: middle;">分析项目</th>
		<td colspan="3"><c:forEach varStatus="stat" var="item" items="${xms }"><span style="display: -moz-inline-box; display: inline-block; width: 135px; white-space: nowrap;"><input type="checkbox" xu.type="checklist" name="fxxms" value="${item }" />${item }&nbsp;&nbsp;</span><c:if test="${stat.count % 4 == 0 }"><br /></c:if></c:forEach></td>
	</tr>
	<tr>
		<th>采样位置</th>
		<td colspan="3"><ui:checklist name="cywzs" ql="select key, value from CodeItem where code.id='SINO_CYFX_WZ' order by order" /></td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3"><ui:textarea name="bz" rows="4" cssStyle="width: 98%" value="bz" /></td>
	</tr>
</table>
<div class="submit"><input type="button" class="button" value="保   存" onclick="doSave(this.form, this);" /> <input type="button" class="button" value="关  闭" onclick="coco.hidePage('CydDetail');" /></div>
</form>
</ui:panel>
<ui:panel id="Detail" title="发送样品" popup="true" display="false" closable="true">
<form name="DataForm" action="update.do" xu.s="alert('发送成功');cocopage.refresh();coco.hidePage('Detail');" method="post" >
<div id="FsypArea"></div>
<div class="submit"><input type="button" class="button" value="保    存" onclick="cocoform.submit(this, null, '是否确定保存?')" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail');" /></div>
</form>
</ui:panel>
<ui:panel id="View" title="采样分析单" popup="true" display="false" closable="true">
<div id="ViewArea"></div>
</ui:panel>
</body>
</html>