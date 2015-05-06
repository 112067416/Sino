<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/print.js"></script>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看制品", "view.gif", "view()"],["打印不良扣除联络单", "edit.gif", "doDyLossc()"]]);
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
		
		// 打印不良扣除联络单
		function doDyLossc() {
			if(!window.confirm("确定打印吗?")) return;
			var jbno = contextmenu.selectedId();
			var postContent = coco.parseParams([{name:"jbno",value:jbno}]);
			var ajax = new Cocoajax();
			ajax.dataDiv = LosscArea;
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('LosscView',{center:true,top:50,width:"80%"});
			};
			ajax.post(path + "/sino/dy/lossc.do", postContent);
		}
		//-->
		</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="不良扣除联络单打印 ">
 <f:page action="indexLosscDy.do" file="listLosscDy.jsp">
<table width="96%" align="center" style="margin: 10px 0;" class="form">
<colgroup><col /><col /><col /><col /><col /><col /><col /><col /></colgroup>
<tr>
<th>生产线</th>
<td><ui:select sql="#select CODE_,NAME_ from SINO_SCXBPZ where  TYPE_='1'" name="elin" headerValue="" /></td>
<th>班</th>
<td><ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'" headerText="" headerValue=""/></td>
<th>组</th>
<td><ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'" headerText="" headerValue=""/></td>
<th>COIL.No.</th>
<td><ui:input id="jzjbno" name="jbno" maxlength="11" title="表示经过ETL后，生成产品的编号"/></td>
<th>状态</th>
<td><ui:select name="lody"  list="'0':'未打印','1':'已打印'" /></td>
</tr>
</table>
<div class="submit"><input type="button" class="button" value="查 询" onclick="cocopage.query();" /></div>
</f:page>
</ui:page>
<ui:panel id="View" title="制品详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<ui:panel id="LosscView" title="不良扣除联络单详细信息" popup="true" display="false" closable="true" >
<DIV id="LosscArea" ></DIV>
</ui:panel>
</body>
</html>