<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@  taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@
    taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/upload.js"></script>
<script type="text/javascript">
<!--

contextmenu.putMenus("menu", [["查看", "view.gif", "toView()"],
                         //     ["修改", "edit.gif", "toModify()"],
                      		["删除", "del.gif", "toDel()"]]);
contextmenu.putMenus("menuEnd", [["查看", "view.gif", "toView()"]]);


	//新增采样单
	function toAdd() {
		var oForm = document.forms["DataForm"];
		var ignores = ["ban", "zu", "bz"];
		cocoform.clear(oForm,ignores);
		coco.showPage("Detail", {width:"69%", center:1});
		oForm.elements["jbno"].focus();
	}
	
	//更新采样单
	function toModify() {
		var id = contextmenu.selectedId();
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert(this.result);
				cocoform.fillResult("DataForm", this.result);
				coco.showPage("Detail", {width:"80%", center:1});
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("get.do", "id="+id);
	}
	
	function toDel() {
		if(!confirm("是否确定删除?")) return;
		var id = contextmenu.selectedId();
		var postContent = coco.parseParams([{name:"id",value:id},{name:"fsxb",value:"ETL"}]);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("删除成功");
				cocopage.refresh();
			}
			else {
				coco.alert(this.msg);
			}
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
				coco.showPage("View", { center : true, width : "80%"});
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("view.do", "id="+id);
	}
	//根据采样钢卷号，获得其对应的镀锡量、涂油量和指示书号
	function loadFxmb(el) {
		var oForm = document.forms["DataForm"];
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
		eles = oForm.elements["cywzs"];
		for(var i = 0; i < eles.length; i++) {
			el = eles[i];
			if(!el.checked) continue;
			if(cywzs == null) {
				cywzs = el.value;
			} else {
				cywzs = cywzs + "," + el.value;
			}
		}
		if(cywzs == null || cywzs.length == 0) {
			alert("请选择采样位置");
			return;
		}
		cocoform.submit(oForm, function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert('保存成功');
			cocopage.refresh();
			coco.hidePage('Detail');
			}, "是否确定保存?", null, oInput);
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
		<td><ui:input name="jbno" maxlength="7" /></td>
		<th style="text-align: left;">单号</th>
		<td><ui:input name="id" maxlength="9" /></td>
		<th style="text-align: left;">发行日期</th>
		<td><ui:date name="dateBegin" /> 至 <ui:date name="dateEnd" /></td>
	</tr>
</table>
<div class="submit"><input type="button" class="button" value="查  询" onclick="cocopage.query();" />  <input type="button" class="button" value="新增分析单" onclick="toAdd()" /></div>
</f:page>
</ui:page>
<ui:panel id="Detail" title="新增采样分析单" popup="true" display="false" closable="true">
<form name="DataForm" action="save.do" method="post" >
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
		<th style="vertical-align: middle;">备注</th>
		<td colspan="3" style="vertical-align: middle;"><ui:textarea name="bz" rows="4" cssStyle="width: 98%" value="bz" /></td>
	</tr>
</table>
<div class="submit">
	<input type="button" class="button" value="保   存" onclick="doSave(this.form, this);" />
	<input type="button" class="button" value="关  闭" onclick="coco.hidePage('Detail');" />
</div>
</form>
</ui:panel>
<ui:panel id="View" title="查看采样分析单" popup="true" display="false" closable="true">
<div id="ViewArea"></div>
</ui:panel>
</body>
</html>