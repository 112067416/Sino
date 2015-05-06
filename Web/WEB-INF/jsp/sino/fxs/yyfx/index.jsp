<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/sys" prefix="sys"%><%@ taglib 
uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/message.js"></script>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"], ["删除", "del.gif", "toDel()"]]);
		//删除
		function toDel(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var vKey1 = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParams([{name : "ids",value: vKey1}]);
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
			ajax.post("delete.do", postContent, "POST");
		}
		//批量删除
		function deletes() {
			var postContent = cocoform.postCheckValues("PageForm_page", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要删除的行");
				return;
			}
			if(!confirm("确定删除吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete.do", postContent);
		}
		//新增
		function toAdd() {
			var ajax = new Cocoajax();
			ajax.dataDiv = YyfxDiv;
			ajax.post("indexDl.do");
			coco.showPage("YyfxArea",{center:true,top:50,width:"80%"});
		}

		//保存
		function doSave(oInput) {
			cocoform.submit(oInput, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage("YyfxArea");
					cocopage.refresh();
				}, '是否确定保存?');
		}
		//保存
		function doUpdate(oInput) {
			cocoform.submit(oInput, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage("EditYyfxArea");
					cocopage.refresh();
				}, '是否确定保存?');
		}
		//维护
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var id = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParam("id", id);
			var ajax = new Cocoajax();
			ajax.dataDiv = EditYyfxDiv;
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("EditYyfxArea",{center:true,top:50,width:"80%"});
				var oForm = document.forms["EditYyfxForm"];
				var eles = oForm.elements;
				var el, index = 0;
				while((el = eles[index++]) != null) {
					if(el.type != "text") continue;
					toJudegValue(el);
				}
			};
			ajax.post("load.do", postContent);
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
		//-->
		</script>
	</head>
	<body>
<ui:page title="药液分析结果管理">
<f:page action="index.do" file="list.jsp">
	<table width="96%" align="center" class="form" style="margin-top: 10px;">
		<colgroup><col width="10%" /><col width="90%" /></colgroup>
		<tr>
			<th style="text-align: left;">记录日期</th>
			<td><ui:date name="rqBegin" showCalendar="true" required="true" value="page.rqBegin" /> 至 <ui:date name="rqEnd" showCalendar="true"/></td>
		</tr>
	</table>
	<div class="submit">
 	<input type="button" class="button" value="查   询" onclick="cocopage.query();"/>
	<input type="button" class="button" value="新   增" onclick="toAdd()" />
	<input type="button" class="button" value="删   除" onclick="deletes()" />
	</div>
</f:page>
</ui:page>
<ui:panel id="YyfxArea" title="新增药液分析结果" display="false" closable="true" popup="true">
<form name="YyfxForm" method="post" action="save.do">
<div id="YyfxDiv" class="scroll" style="width:100%;height:350px;"></div>
</form>
</ui:panel>
<ui:panel id="EditYyfxArea" title="修改药液分析结果" display="false" closable="true" popup="true">
<form name="EditYyfxForm" method="post" action="save.do">
<div id="EditYyfxDiv" class="scroll" style="width:100%;height:350px;"></div>
</form>
</ui:panel>
</body>
</html>