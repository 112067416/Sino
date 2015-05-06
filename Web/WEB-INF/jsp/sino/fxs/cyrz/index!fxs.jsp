<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@
    taglib uri="/ui" prefix="ui"%><%@
    taglib uri="/f" prefix="f"%><%@
    taglib uri="/sys" prefix="sys"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tabpage.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/message.js"></script>
<script type="text/javascript">
<!--
	contextmenu.putMenus("menu", [["分析数据登录", "edit.gif", "toDl()"],["接收采样单", "download.gif", "toRecv()"],["接收样品", "download.gif", "toRecv1()"],["查看", "view.gif", "toView()"]]);
	contextmenu.putMenus("menuEnd", [["分析数据登录", "edit.gif", "toDl()"],["查看", "view.gif", "toView()"]]);

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
		ajax.post("update.do", "type=fxsd&id="+id);
	}
	//接收样品
	function toRecv1() {
		var id = contextmenu.selectedId();
		if(!confirm("是否确定接收样品?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code > 0) {
				alert("收样成功");
				cocopage.refresh();
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("update.do", "type=fxsy&id="+id);
	}

	//收单更新
	function toEnd() {
		var id = contextmenu.selectedId();
		var form = document.forms["DataForm"];
		form.elements["id"].value = id;
		form.elements["fxBz"].value = "";
		coco.showPage("Detail", { center : true, top : 20, width : "80%"});
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
	function toJudegValue(el) {
		var min;
		if(!isNaN(el.getAttribute("min"))) {
			min = parseFloat(el.getAttribute("min"));
		}
		var max;
		if(!isNaN(el.getAttribute("max"))) {
			max = parseFloat(el.getAttribute("max"));
		}
		var v;
		if(!isNaN(el.value)) {
			v = parseFloat(el.value);
		}
		if((min != null && min > v) || (max != null && max < v)) {
			el.style.color = "#ff0000";
		}  else {
			el.style.color = "";
		}
	}
	//分析数据登录
	function toDl(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var cyrzId = coco.getAttr(oTr, "xu.id");
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("MktfxArea");
		ajax.callback = function() {
			coco.showPage("MktfxPanel", { center : true, top : 20, width : "80%"});
			var oForm = document.forms["MktfxForm"];
			var eles = oForm.elements;
			var el, index = 0;
			while((el = eles[index++]) != null) {
				if(el.type != "text") continue;
				toJudegValue(el);
			}
			var fxxms = oForm.elements["fxxms"].value;
			if(fxxms != null && fxxms.length > 0) {
				var xms = fxxms.split(",");
				pageTags = [];
				lastActivePage = null;
				for(var i = 0; i < xms.length; i++) {
					pageTags[i] = new PageTag(xms[i],"tab02-bg01","tab02-bg02");
				}
				lastActiveObj = pageTags[0];
				var disDiv = document.getElementById(pageTags[0].tid);
				disDiv.style.display = "block";
			}
		};
		ajax.post(path+"/sino/fxs/mktfx/indexDl.do", "cyrzId="+cyrzId);
	}
	//保存
	function doSave(oForm, oInput, flag) {
		var msg = flag ? '是否确定该马口铁分析完成?' : '是否确定保存?';
		oForm.action = path + "/sino/fxs/mktfx/save.do";
		var postContent = cocoform.postContent(oForm);
		postContent = postContent + "&flag="+flag;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			oForm.elements["stat"].value = this.msg;
			cocoform.submit(oInput, function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("保存成功");
				cocopage.refresh();
				coco.hidePage('MktfxPanel');
			}, msg);
		};
		ajax.post(path + "/sino/fxs/mktfx/checkStat.do", postContent);
	}
	//计算锡附着量
	function calculate(name1, name2, name3) {
		var oForm = document.forms["MktfxForm"];
		var v1 = oForm.elements[name1].value;
		v1 = (v1 == null || v1.length == 0 ? 0 : parseFloat(v1));
		var el2 = oForm.elements[name2];
		var v2 = el2.value;
		v2 = (v2 == null || v2.length == 0 ? 0 : parseFloat(v2));
		var el3 = oForm.elements[name3];
		if(v1 == 0 && v2 == 0) {
			el3.value = "";
			return;
		}
		el3.value = (v1 + v2).toFixed(2);
		toJudegValue(el2);
		toJudegValue(el3);
	}
//-->
</script>
</head>
<body>
<ui:page title="采样记录维护">
<f:page action="index.do" file="list.jsp">
<input name="type" type="hidden" value="${type }" />
<table width="100%" class="form">
	<colgroup><col width="10%"><col width="20%"><col width="10%"><col width="20%"><col width="10%"><col width="30%"></colgroup>
	<tr>
		<th style="text-align: left;">指示书NO</th>
		<td><ui:input name="zsno" maxlength="6" /></td>
		<th style="text-align: left;">Coil No</th>
		<td><ui:input name="jbno" maxlength="8" /></td>
		<th style="text-align: left;">单号</th>
		<td><ui:input name="id" maxlength="9" /></td>
	</tr>
	<tr>
		<th style="text-align: left;">发行日期</th>
		<td colspan="3"><ui:date name="dateBegin" /> 至 <ui:date name="dateEnd" /></td>
		<th style="text-align: left;">分析结果</th>
		<td><ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_MKTFX_STAT'" headerText="全部" headerValue="" value="stat" onchange="cocopage.query();" /></td>
	</tr>
</table>
<div class="submit"><input type="button" class="button" value="查  询" onclick="cocopage.query();" /> </div>
</f:page>
</ui:page>
<ui:panel id="Detail" title="分析样品" popup="true" display="false" closable="true">
<form name="DataForm" action="update.do" xu.s="alert('分析完毕');cocopage.refresh();coco.hidePage('Detail');" method="post" >
<input type="hidden" name="type" value="fx" />
<table width="96%" align="center" class="form">
	<colgroup>
		<col width="15%" /><col width="85%" />
	</colgroup>
	<tr>
		<th>单号</th>
		<td><ui:input name="id" readonly="true" maxlength="10" /></td>
	</tr>
	<tr>
		<th>备注</th>
		<td><ui:textarea name="fxBz" rows="4" cssStyle="width: 98%;" /></td>
	</tr>
</table>
<div class="submit">
	<input type="button" class="button" value="保    存" onclick="cocoform.submit(this, null, '是否确定保存?')" />
	<input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail');" />
</div>
</form>
</ui:panel>
<ui:panel id="View" title="采样分析单" popup="true" display="false" closable="true">
<div id="ViewArea"></div>
</ui:panel>
<ui:panel id="MktfxPanel" title="马口铁分析数据登录" popup="true" display="false" closable="true">
<form name="MktfxForm" method="post" action="#" >
<div id="MktfxArea"></div>
</form>
</ui:panel>
</body>
</html>