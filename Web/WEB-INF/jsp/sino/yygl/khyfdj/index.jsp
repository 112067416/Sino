<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>运费单价管理</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"]]);
			// 修改
			function toModify(oTr) {
				var form = document.forms["DataForm"];
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "key1");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					try {
						var form = document.forms["DataForm"];
						cocoform.clear(form);
						cocoform.fillResult(form, this.result);
						coco.showPage("Detail", {center : true, top : 50, width : "90%" });
					} catch (e) {
						alert("系统出错:\n" + e.description);
					}
				};
				ajax.post("load.do", "id="+key1);
			}
			//批量删除
			function toDels() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids == "") {
					alert("请选择要做删除操作的资料");
					return;
				}
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("删除成功");
					cocopage.refresh();

				};
				ajax.post("dels.do", ids);
			}

			//提交
			function doSubmit() {
				var oForm = document.forms["DataForm"];
				cocoform.submit(oForm, function() {
					if(this.code < 0) {
						coco.alert(this.msg);
						return;
					}
					alert("操作成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				});
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="运费单价管理">
			<f:page action="index.do" file="list.jsp">
				<table class="form" width="100%">
					<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="40%" /></colgroup>
					<tr>
						<th style="text-align: left;">用户名称</th>
						<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
						<th style="text-align: left;">运输方式</th>
						<td><ui:select name="ysfs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_YSFS'" headerText="请选择" headerValue="" onchange="cocopage.query();" /></td>
						<th style="text-align: left;">运输行</th>
						<td><ui:select name="ysnm" list="${ysgss }" headerText="请选择" headerValue="" onchange="cocopage.query();" /></td>
					</tr>
				</table>
				<div class="submit"> <input type="button" class="button" value="查 询" onclick="cocopage.query();" /> <input type="button" class="button" value="删 除" onclick="toDels()" /></div>
			</f:page>
		</ui:page>
		<ui:panel id="Detail" title="修改客户运输单价" popup="true" display="false" closable="true">
			<form name="DataForm" xu.ajax="" xu.result="" xu.success="success();" method="post" action="updateYfdj.do" >
			<input type="hidden" name="id" />
			<table class="form" width="100%">
				<colgroup><col width="10%" /><col width="20%" /><col width="20%" /><col width="20%" /><col width="10%" /><col width="20%" /></colgroup>
				<tr>
					<th>创建日期</th>
					<td><ui:input name="crea" readonly="true" maxlength="10" title="创建日期" /></td>
					<th>运输方式</th>
					<td><ui:input name="ysfm" readonly="true" maxlength="16" title="运输方式" /></td>
					<th>用户略称</th>
					<td><ui:input name="abbr" readonly="true" maxlength="16" title="用户略称" /></td>
				</tr>
				<tr>
					<th>运输行</th>
					<td colspan="5"><ui:input name="ysnm" readonly="true" maxlength="64" title="运输行" /></td>
				</tr>
				<tr>
					<th>到达地点</th>
					<td colspan="5"><ui:input name="addr" readonly="true" maxlength="64" title="到达地点" /></td>
				</tr>
				<tr>
					<th>送货点</th>
					<td><ui:input name="shnm" readonly="true" maxlength="8" title="送货点" /></td>
					<th>运费单位</th>
					<td><ui:input name="djdw" readonly="true" maxlength="8" title="运费单位" /></td>
					<th>运输单价</th>
					<td><ui:number name="yfdj" title="运输单价" maxlength="8" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<div class="opt-btm"><input type="submit" class="button" value="保 存" onclick="doSubmit()"/> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('Detail');" /></div>
			</form>
		</ui:panel>
	</body>
</html>