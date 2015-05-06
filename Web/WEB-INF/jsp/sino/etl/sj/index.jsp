<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		var type = '${type}';
		if(type == "1" ){
			contextmenu.putMenus("menu", [["查看", "edit.gif", "view()"],["订正", "edit.gif", "sjdz()"],["删除", "view.gif", "doDelete()"],["打印制品卡", "view.gif", "doPrint()"],["修改组", "edit.gif", "toEditZu()"]]);
		}
		if(type == "2" ){
			contextmenu.putMenus("menu", [["查看", "edit.gif", "view()"],["订正", "edit.gif", "sjdz()"],["删除", "view.gif", "doDelete()"],["打印制品卡", "view.gif", "doPrint()"]]);
		}	
		//检索
		function doQuery() {
			cocopage.query();
		}	
		//查看ETL生产实绩信息
		function view() {
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ViewArea");
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('View',{center:true,top:50,width:"80%"});
			};
			ajax.post("view!"+contextmenu.selectedId()+".do");
		}
		// 实绩订正
		function sjdz() {
			parent.cocoframe.open("update", "实绩订正", "/sino/etl/sj/sjdz.do?jbno="+contextmenu.selectedId(),null,null, true);
		}	
		//删除
		function doDelete() {
			if(!window.confirm("确定要删除该条记录吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete!"+contextmenu.selectedId()+".do", null, "POST");
		}
		//批量删除
		function toDel(){
			var form = document.forms["PageForm_page"];
			var els = form.elements["ids"];
			var content = "";
			if(els.length > 0) {
				for(var i = 0; i < els.length; i++) {
					if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
				}
			}
			else {
				if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
			}
			if(content == "") {
				alert("请选择删除行");
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code>0){
					alert("删除成功");
					cocopage.refresh();
				}
				else{
					coco.alert(this.msg);
				}
			};
			ajax.post("delAll.do", content.substring(1));
		}
		// 打印制品卡
		function doPrint() {
			if(!window.confirm("确定打印吗?")) return;
			document.getElementById("PrintFrame").src = "../../dy/zpk.do?jbnos="+contextmenu.selectedId();
		}

		function toEditZu(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var vKey1 = coco.getAttr(oTr, "xu.id");
			var vKey2 = coco.getAttr(oTr, "xu.zu");
			var oForm = document.forms["ZuForm"];
			oForm.elements["jbno"].value = vKey1;
			oForm.elements["zu"].value = vKey2;
			coco.showPage('ZuDetail',{center:true,top:50,width:"80%"});
		}

		function saveZu(el) {
			if(!confirm("是否确定保存?")) return;
			var oForm = el.form;
			cocoform.submit(oForm, new function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("保存成功");
				coco.hidePage('ZuDetail');
				cocopage.refresh();
			}, '', null, el);
		}
		//-->
		</script>
	</head>
	<body>
		<ui:page title="ETL生产线实绩维护">
		<f:page action="query.do" file="list.jsp">
		<table width="96%" align="center" class="form">
			<colgroup><col width="10%" /><col width="10%" /><col width="5%"/><col width="5%"/><col width="5%"/><col width="5%"/><col width="10%" /><col width="10%" /><col width="10%" /><col width="30%" /></colgroup>
			<tr>
				<th>生产线</th>
				<td><ui:select sql="#select CODE_,NAME_ from SINO_SCXBPZ WHERE TYPE_ = 1" name="elin" value="page.elin" headerText="" headerValue="" /></td>
				<th>班</th>
				<td><ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'" headerText="" headerValue=""/></td>
				<th>组</th>
				<td><ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'" headerText="" headerValue=""/></td>
			    <th>COIL.No.</th>
			    <td><ui:input id="jzjbno" name="jbno" maxlength="11" title="表示经过ETL后，生成产品的编号"/></td>
			    <th>实绩日期</th>
				<td><ui:date name="crea_begin" value="page.crea_begin"/> - <ui:date name="crea_end" value="page.crea_end" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			<input type="button" class="button" value="删 除" onclick="toDel()" />
		</div>
		</f:page>
		</ui:page>
		<ui:panel id="ZuDetail" title="修改组" popup="true" display="false" closable="true">
		<form action="updateZu.do" name="ZuForm" method="post" >
		<table width="98%" align="center" style="margin: 20px auto;"
		class="form">
		<colgroup><col width="10%" /><col width="40%" /><col width="10%" /><col width="40%" /></colgroup>
		<tr>
			<th>Coil No.</th>
			<td><ui:input name="jbno" maxlength="8" readonly="true" /></td>
			<th>组</th>
			<td><ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'" /></td>
		</tr>
		</table>
		<div class="submit"><input type="button" onclick="saveZu(this);"  class="button" value="保 存" /> <input type="button" class="button"
			value="关 闭" onclick="coco.hidePage('ZuDetail')" /></div>
		</form>
		</ui:panel>
		<ui:panel id="View" title="查看详细信息" popup="true" display="false" closable="true" >
		<DIV id="ViewArea" ></DIV>
		</ui:panel>
		<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>