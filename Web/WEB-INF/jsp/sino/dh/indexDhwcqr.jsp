<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订货合同完成确认</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["合同完成", "edit.gif", "finish()"],["撤消合同完成", "del.gif", "cancel()"]]);

			//撤消合同完成
			function cancel(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent;
				if(oTr == null || oTr.length == 0){
					postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
					if(postContent == null || postContent.length == 0){
						alert("请选择要做撤消订货合同完成操作的记录");
						return;
					}
				} else {
				    postContent =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
				if(!window.confirm("确定撤消订货合同完成吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("撤消订货合同完成操作成功");
					cocopage.refresh();
				};
				ajax.post("cancelFinish.do", postContent);
			}
			//合同完成
			function finish(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent;
				if(oTr == null || oTr.length == 0){
					postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
					if(postContent == null || postContent.length == 0){
						alert("请选择要做订货合同完成操作的记录");
						return;
					}
				} else {
				    postContent =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
			    if(!confirm("确定订货合同完成吗?")) return false;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("订货合同完成操作成功");
					cocopage.refresh();
				};
				ajax.post("finish.do", postContent);
				
			}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="订货合同完成确认">
		<f:page action="indexDhwcqr.do" file="listDhwcqr.jsp">
			<table class="form" width="100%">
				<colgroup>
					<col width="10%" />
					<col width="40%" />
					<col width="10%" />
					<col width="40%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th style="text-align: left;">用户代码</th>
					<td><ui:input name="userBegin" maxlength="4"/>至<ui:input name="userEnd" maxlength="4"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">交货期</th>
					<td><ui:date name="jhqiBegin" prop="calendar: true;" />至<ui:date name="jhqiEnd" prop="calendar: true;" /></td>
					<th style="text-align: left;">订货合同状态</th>
					<td>
					<ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_DH_STAT'" headerText="全部" headerValue="" value="page.stat" onchange="cocopage.query();" />
					</td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="cocopage.query()" />
				<input type="button" class="button" value="合同完成" onclick="finish('')" />
				<input type="button" class="button" value="撤消合同完成" onclick="cancel('')" />
			</div>
		</f:page>
	</ui:page>
	</body>
</html>