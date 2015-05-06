<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["修改", "view.gif", " toModify()"],["删除", "del.gif", "toDel()"]]);
		//删除
		function toDel(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var vKey1 = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParams([{name : "ids",value: vKey1}]);
			if(!window.confirm("是否确定删除?")) return;
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
		//批量删除
		function deletes() {
			var postContent = cocoform.postCheckValues("PageForm_page", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要删除的行");
				return;
			}
			if(!confirm("是否确定删除?")) return;
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
			parent.cocoframe.open("fxzyrzDl", "分析作业日志登录", "/sino/fxs/fxzy/indexDl.do", null, null, true);
		}
		//维护
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var id = coco.getAttr(oTr, "xu.id");
			parent.cocoframe.open("fxzyrzWh", "分析作业日志维护", "/sino/fxs/fxzy/load.do?id="+encodeURIComponent(id), null, null, true);
		}
		//打印
		function print() {
			var postContent = cocoform.postCheckValues("PageForm_page", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要打印的记录");
				return;
			}
			if(!confirm("是否确定打印?")) return;
			document.getElementById("PrintFrame").src = "/sino/dy/fxzyrz.do?"+postContent;
		}
		//-->
		</script>
	</head>
	<body>
	<ui:page title="分析作业日志管理">
	<f:page action="index.do" file="list.jsp">
		<table width="96%" align="center" class="form">
			<colgroup><col width="10%" /><col width="90%" /></colgroup>
			<tr>
				<th style="text-align: left;">记录日期</th>
				<td><ui:date name="tbsjBegin" showCalendar="true"/> 至 <ui:date name="tbsjEnd" showCalendar="true"/></td>
			</tr>
		</table>
		<div class="submit">
	 	<input type="button" class="button" value="查  询"  onclick="cocopage.query();"/>	
		<input type="button" class="button" value="新 增" onclick="toAdd()" />
		<input type="button" class="button" value="删 除" onclick="deletes()" />
		<input type="button" class="button" value="打 印" onclick="print()" />
		</div>
		</f:page>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>