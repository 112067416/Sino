<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["设定库位", "edit.gif", "toKw()"],["紧急材设定", "edit.gif", "doJjcSd()"],["紧急材取消", "edit.gif", "doJjcQx()"]]);
		
		// 紧急材设定
		function doJjcSd(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			var content;
			if(key != null && key.length > 0) {
				content = coco.parseParam("ids", key);
			} else {
				content = cocoform.postCheckValues("PageForm_page","ids","ids");
			}
			if(content == null || content.length == 0){
				alert("请选择要设定紧急材的记录");
				return;
			}
		    if(!confirm("确定设定为紧急材吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("设定成功");
				cocopage.refresh();
			};
			ajax.post("saveJjcSd.do", content);
		}
		
		// 紧急材取消
		function doJjcQx(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			var content;
			if(key != null && key.length > 0) {
				content = coco.parseParam("ids", key);
			} else {
				content = cocoform.postCheckValues("PageForm_page","ids","ids");
			}
			if(content == null || content.length == 0){
				alert("请选择要设定紧急材的记录");
				return;
			}
			if(!confirm("确定取消紧急材的设定吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("撤消成功");
				cocopage.refresh();
			};
			ajax.post("saveJjcQx.do", content);
		}
		function toKw(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			var content;
			if(key != null && key.length > 0) {
				content = coco.parseParam("ids", key);
			} else {
				content = cocoform.postCheckValues("PageForm_page","ids","ids");
			}
			if(content == null || content.length == 0){
				alert("请选择要设定库位的记录");
				return;
			}
			coco.showPage("KwDetail", {center : true, top : 50, width : "50%" });
			var form = document.forms["DataForm"];
			form.elements["ids"].value = content;
			form.elements["kw"].value = "";
		}
		
		// 设定库位
		function saveKw(oInput) {
			var form = document.forms["DataForm"];
			var el = form.elements["kw"];
			var kw = el.value.replace(/^\s+|\s+$/gi, "");
			if(kw == null || kw.length == 0) {
				alert("库位不能为空");
				el.focus();
				return;
			}
			var code = coco.getCode("011", kw);
			if(code == null) {
				alert("库位在码表中不存在");
				el.value = "";
				el.focus();
				return;
			}
			var content = form.elements["ids"].value;
			content = content + "&kw=" + encodeURIComponent(kw);
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("保存成功");
				coco.hidePage("KwDetail");
				cocopage.refresh();
			};
			ajax.post("saveKw.do", content);
		}
		
		// 成功提示
		function success() {
			alert("保存成功");
			coco.hidePage('Detail');
			cocopage.refresh();
		}

		function doQuery() {
			var qForm = document.forms["PageForm_page"];
			var dhno = qForm.elements["dhno"].value;
			var line = qForm.elements["line"].value;
			qForm.elements["dhnoLine"].value = dhno + line;
			cocopage.query();
		}
		//-->
		</script>
	</head>
	<body>
	<ui:page title="设定紧急材">
		<f:page action="indexSdjjc!${duic }.do" file="listSdjjc.jsp">
			<input name="dhnoLine" type="hidden" />
			<table width="96%" align="center" style="margin: 10px 0;" class="form">
				<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="5%" /><col width="5%" /></colgroup>
				<tr>
					<th>用户代码</th>
					<td><ui:input name="userBegin" maxlength="4" />  至 <ui:number name="userEnd" maxlength="4" /></td>
					<th>订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th>PILE No.</th>
					<td><ui:input name="jbno" maxlength="11" title="PILE No." /></td>
					<th colspan="2">&nbsp;</th>
				</tr>
				<tr>
					<th>现品尺寸·厚</th>
					<td><ui:number name="xphoS" scale="4" precision="3"/>  至 <ui:number name="xphoE" scale="4" precision="3"/> </td>
					<th>现品尺寸·宽</th>
					<td><ui:number name="xpknS" scale="6" precision="2"/>  至 <ui:number name="xpknE" scale="6" precision="2"/></td>
					<th>现品尺寸·长</th>
					<td><ui:number name="xpcnS" scale="6" precision="2"/>  至 <ui:number name="xpcnE" scale="6" precision="2"/></td>
					<th>产量</th>
					<td><ui:input name="chan" maxlength="1" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button"  value="查 询" onclick="doQuery();" />
				<input type="button" class="button" value="紧急材设定" onclick="doJjcSd('');" />
				<input type="button" class="button" value="紧急材取消" onclick="doJjcQx('');" />
				<input type="button" class="button" value="设定库位" onclick="toKw('');" /></div>
		</f:page>
		<ui:panel id="KwDetail" title="设定库位" display="false" popup="true" closable="true">
			<form name="DataForm" method="post" action="#" >
			<input type="hidden" name="ids" />
				<table width="96%" align="center" style="margin: 10px 0;" class="form">
					<colgroup><col width="20%" /><col width="80%" /></colgroup>
					<tr>
						<th style="text-align: left;">库位</th>
						<td><ui:input name="kw" maxlength="4" required="true"/></td>
					</tr>
				</table>
				<div class="opt-btm">
				<input type="button" class="button" value="保   存" onclick="saveKw(this);" />
				<input type="button" class="button" value="关   闭" onclick="coco.hidePage('KwDetail')" /> 
			    </div>
			</form>
		</ui:panel>
	</ui:page>
	</body>
</html>