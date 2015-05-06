<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [
		                              ["查看", "edit.gif", "doView()"],
		                              ["订正", "edit.gif", "doDz()"],
		                              ["删除", "view.gif", "doDel()"],
		                              ["打印制品卡", "view.gif", "doPrint()"]
		                             ]);
		//查看制品信息
		function doView() {
			var jbno = contextmenu.selectedId();
			jbno = jbno ? jbno.replace(/(^\s+)|(\s+$)/g, "") : null;
			if(!jbno)return;
			var ajax = new Cocoajax();
			ajax.dataDiv = ViewArea;
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
				} else {
					coco.showPage("ViewPanel",{center:true,top:50,width:"90%"});
				}
			};
			ajax.post("view.do", "jbno=" + jbno);
		}
		// 实绩订正
		function doDz() {
			parent.cocoframe.open("dz", "实绩订正", "/sino/sl/dz.do?jbno="+contextmenu.selectedId(),null,null, true);
		}	
		//删除
		function doDel() {
			if(!confirm("确定删除吗?")) return false;
			var jbno = contextmenu.selectedId();
			jbno = jbno ? jbno.replace(/(^\s+)|(\s+$)/g, "") : null;
			if(!jbno) {
				alert("无法获取卷板号,请联系管理员");
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code > 0) {
					alert("删除制品成功");
					cocopage.refresh();
				} else {
					coco.alert(this.msg);
				}
			};
			ajax.post("delete.do", "jbno=" + jbno);
		}
		// 打印制品卡
		function doPrint() {
			var jbno = contextmenu.selectedId();
			jbno = jbno ? jbno.replace(/(^\s+)|(\s+$)/g, "") : null;
			if(!jbno) {
				alert("无法获取卷板号,请联系管理员");
				return;
			}
			document.getElementById("PrintFrame").src = "/sino/dy/zpk.do?jbnos="+jbno;
		}
		//-->
		</script>
	</head>
	<body>
	<ui:page title="SL生产实绩维护">
		<f:page file="list.jsp" action="query.do" size="15">
			<table width="96%" align="center" class="form">
				<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="30%" /></colgroup>
				<tr>
					<th>生产线</th>
					<td><ui:select sql="#select CODE_,NAME_ from SINO_SCXBPZ WHERE TYPE_ = 2" name="slin" value="page.slin" headerText="" headerValue="" /></td>
					<th>班</th>
					<td><ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'" headerText="" headerValue=""/></td>
					<th>组</th>
					<td><ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'" headerText="" headerValue=""/></td>
				</tr>
				<tr>
					<th>指示书</th>
					<td><ui:input name="zsno" maxlength="6" /></td>
					<th>制品号</th>
					<td><ui:input name="jbno" maxlength="11"/></td>
					<th>实绩日期</th>
					<td><ui:date name="crea_begin" value="page.crea_begin"/> - <ui:date name="crea_end" value="page.crea_end" /></td>
				</tr>
			</table>
			<div class="submit"><input type="button" class="button" value="查   询" onclick="cocopage.refresh();" /></div>
		</f:page>
		<div id="ViewArea"></div>
		<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</ui:page>
	</body>
</html>