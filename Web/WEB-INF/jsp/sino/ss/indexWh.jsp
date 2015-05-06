<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		
		contextmenu.putMenus("menu", [
		                            
		                              ["订正", "edit.gif", "doDz()"],
		                              ["删除", "view.gif", "doDel()"]
		                             
		                             ]);

	
		
		
		// 实绩订正
		function doDz() {
			parent.cocoframe.open("dz", "实绩订正", "/sino/ss/dz.do?jbno="+contextmenu.selectedId(),null,null, true);
		}	
		// 实绩订正
		function doDel() {
			parent.cocoframe.open("dz", "实绩删除", "/sino/ss/deleteindex.do?jbno="+contextmenu.selectedId(),null,null, true);
		}	
		//删除
		function doDel1() {
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
		
		
		
		//-->
		</script>
		
	</head>
	<body>

	<ui:page title="SL生产实绩维护">
		<f:page file="index!list.jsp" action="query.do" size="15">
			<table width="96%" align="center" class="form">
				<colgroup>
					<col /><col /><col /><col /><col /><col />
				</colgroup>
				<tr>
					<th></th>
					<td></td>
					<th>班</th>
					<td>
					<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"    headerText="" headerValue=""/>
					</td>
					<th>组</th>
					<td>
					<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"    headerText="" headerValue=""/>
					</td>
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
			<div class="submit">
				<input type="button" class="button" value="查询(Q)" accesskey="q" onclick="cocopage.refresh();" />
			</div>
		</f:page>
		<div id="ViewArea"></div>
		<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</ui:page>

	</body>
</html>