<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<style type="text/css">
		TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
		TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
		TABLE.pagination1 th { font-size: 12px; color: #003449; height: 21px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
		TABLE.pagination1 td { font-size: 14px; color: #333333; height: 18px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
		TABLE.pagination1 tr {background-color: #F7F7F7;}
		TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
		TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
		</style>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["设置", "edit.gif", "toModify()"],["查看分析记录", "edit.gif", "toFj()"],["已阅", "edit.gif", "toRead()"]]);
		//提交
		function doSubmit() {
			tableform.formatTblForm("items",1);
			var ajax = new Cocoajax();
			if(!confirm("确定保存吗?")) return false;
			cocoform.submit(document.forms["DataForm"]);
		}
		// 保存特记
		function saveQt(form, oInput) {
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage('DetailPanel');
					cocopage.refresh();
				}, null, null, oInput);
		}
		//
		function toFj(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "key");
			var postContent = coco.parseParams([{name:"jbno",value:key}]);
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("FjArea");
			ajax.callback = function() {
				if(this.code <= 0 ) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage("FjPanel", { center : true, top : 40, width : "80%"});
			};
			ajax.post(path + "/sino/fxs/mktfx/queryFj.do", postContent);
		}
		//分析数据登录
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var id = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParam("id", id);
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("DataArea");
			ajax.callback = function() {
				if(this.code <= 0 ) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage("DetailPanel", { center : true, top : 40, width : "80%"});
			};
			ajax.post("getEtlpzGl.do", postContent);
		}
		//已阅品质管理记录
		function toRead(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var id = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParam("id", id);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0 ) {
					coco.alert(this.msg);
					return;
				}
				cocopage.refresh();
			};
			ajax.post("readEtlpzGl.do", postContent);
		}
		//刷新入侧记录
		function doRefresh() {
			var ajax = new Cocoajax();
			ajax.dataDiv = DataDiv;
			ajax.post("querySsjl.do");
			cocopage.refresh();
		}

		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		  	var width = screen.availWidth;
		  	window.open(path + "/sino/cmn/inform/viewAttach.do?name="+encodeURIComponent(ylno), "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}

		function toFxd(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var jbno = coco.getAttr(oTr, "key");
			if(jbno == null || jbno.length == 0) return;
			var postContent = coco.parseParam("jbno", jbno);
			var oForm = document.forms["FxdForm"];
			var ignores = ["ban", "zu", "bz"];
			cocoform.clear(oForm,ignores);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					oForm.elements["jbno"].focus();
					return;
				}
				var obj;
				eval("obj=" + this.result + ";");
				oForm.elements["dxl"].value = obj.dxl;
				oForm.elements["tyl"].value = obj.tyl;
				oForm.elements["zsno"].value = obj.zsno;
				oForm.elements["jbno"].value = jbno;
				coco.showPage("Detail", { center : true, top : 60, width : "69%"});
			};
			ajax.post(path + "/sino/fxs/cyrz/loadFxmb.do",postContent);
		}

		function toAdd() {
			var form = document.forms["AddForm"];
			form.elements["jbno"].value = "";
			coco.showPage("AddPanel", { center : true, top : 60, width : "69%"});
		}
		//保存操作
		function doAdd(oForm, oInput) {
			var scsj = oForm.elements["scsj"];
			var $scsj = scsj.value;
			if($scsj == null || $scsj.length == 0) {
				alert("生产时间不能为空");
				scsj.focus();
				return;
			}
			var jbno = oForm.elements["jbno"];
			var $jbno = jbno.value;
			if($jbno == null || $jbno.length == 0) {
				alert("COIL NO. 不能为空");
				jbno.focus();
				return;
			}
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert('保存成功');
				cocopage.refresh();
				coco.hidePage('AddPanel');
				}, "是否确定保存?", null, oInput);
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
				coco.hidePage('Detail');
				}, "是否确定保存?", null, oInput);
		}


		function toRefresh() {
			var oForm = document.forms["PzglForm"];
			var eles1 = oForm.elements["jbno"];
			var eles2 = oForm.elements["sczt"];
			if(eles1 == null) {
				doRefresh();
				return;
			}
			var postContent, el, v;
			for(var i = 0; i < eles1.length; i++) {
				el = eles1[i];
				v = el.value;
				if(v == null || v.length == 0) continue;
				if(postContent == null || postContent.length == 0) {
					postContent = "jbnos=" + v;
				} else {
					postContent = postContent + "&" + "jbnos=" + v;
				}
				el = eles2[i];
				v = el.value;
				if(v == null || v.length == 0) continue;
				postContent = postContent + "&" + "sczts=" + v;
			}
			
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					doRefresh();
				}
			};
			ajax.post("doRefreshEtlpz.do", postContent);
		}
		
		window.setInterval(toRefresh, 3000);
		//-->
		</script>
	</head>
	<body onload="doRefresh();">
<ui:page title="ETL实时品质管理">
<form name="PzglForm" method="post">
<div id="DataDiv"></div>
</form>
<br />
<f:page action="indexPzgl.do" file="listPzgl.jsp">
<table width="96%" class="form">
	<colgroup><col width="10%"/><col width="10%" /><col width="80%" /></colgroup>
	<tr>
		<th style="text-align: left;">Coil No.</th>
		<td><ui:input name="jbno" maxlength="7" /></td>
		<td style="text-align: right;"><input type="button" class="button" value="查  询" onclick="cocopage.query();" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="新 增" onclick="toAdd();" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="刷  新" onclick="doRefresh();" /></td>
	</tr>
</table>
</f:page>
</ui:page>
<ui:panel id="DetailPanel" title="设置信息" popup="true" display="false" closable="true">
<form name="DataForm" action="updateEtlpzGl.do" method="post">
<div id="DataArea"></div>
</form>
</ui:panel>
<ui:panel id="FjPanel" title="查看分析记录" popup="true" display="false" closable="true">
<div id="FjArea"></div>
<div class="submit"><input type="button" class="button" value="关  闭" onclick="coco.hidePage('FjPanel');" /></div>
</ui:panel>
<ui:panel id="Detail" title="新增采样分析单" popup="true" display="false" closable="true">
<form name="FxdForm" action="/sino/fxs/cyrz/save.do" method="post" >
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
		<td><ui:input name="jbno" maxlength="7" required="true" onkeydown="if(window.event.keyCode==13) loadFxmb(this);" /></td>
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
		<td colspan="3" style="vertical-align: middle;"><ui:textarea name="bz" rows="4" value="bz" /></td>
	</tr>
</table>
<div class="submit">
	<input type="button" class="button" value="保   存" onclick="doSave(this.form, this);" />
	<input type="button" class="button" value="关  闭" onclick="coco.hidePage('Detail');" />
</div>
</form>
</ui:panel>
<ui:panel id="AddPanel" title="新增" popup="true" display="false" closable="true">
<form name="AddForm" action="addEtlpzGl.do" method="post" >
<table width="100%" align="center" class="form">
	<colgroup><col width="19%" /><col width="31%" /><col width="15%" /><col width="35%" /></colgroup>
	<tr>
		<th style="text-align: left;">生产日</th>
		<td><ui:datetime name="scsj" value="scsj" required="true"  /></td>
		<th>Coil No.</th>
		<td><ui:input name="jbno" maxlength="7" required="true" /></td>
	</tr>
</table>
<div class="submit">
	<input type="button" class="button" value="保   存" onclick="doAdd(this.form, this);" />
	<input type="button" class="button" value="关  闭" onclick="coco.hidePage('AddPanel');" />
</div>
</form>
</ui:panel>
</body>
</html>