<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		
		contextmenu.putMenus("menu", [["更新硬度", "edit.gif", "doGxyd()"]]);
		
		// 更新硬度
		function doGxyd(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var vKey1 = coco.getAttr(oTr, "key1");
			var ymin = coco.getAttr(oTr, "ymin");
			var ymax = coco.getAttr(oTr, "ymax");
			var postContent = coco.parseParams([{name : "jbno",value: vKey1}]);
			var oForm = document.forms["DataForm"];
			cocoform.clear(oForm);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code > 0) {
					cocoform.fillResult(oForm, this.result, null, true);
				}
				oForm.elements["jbno"].value = vKey1;
				oForm.elements["ymin"].value = ymin;
				oForm.elements["ymax"].value = ymax;
				coco.showPage("YdPanel",{center:true,top:80,width:"40%"});
			};
			ajax.post("load.do", postContent);
		}
		// 确定[保存硬度]
		function doQd(oForm, oInputBl) {
			// 卷材号
			var oJbno = oForm.elements["jbno"];
			if(oJbno == null || oJbno.value == null || oJbno.value == "") {
				alert("卷材号 不能为空");
				return;
			}
			// 硬度
			var oYing = oForm.elements["ying"];
			if(oYing == null || oYing.value == null || oYing.value == "") {
				alert("硬度值 不能为空");
				oYing.focus();
				return;
			}
			// 检定员
			var oJdyn = oForm.elements["jdyn"];
			if(oJdyn == null || oJdyn.value == null || oJdyn.value == "") {
				alert("检定员 不能为空");
				oJdyn.focus();
				return;
			}
			var ymin = oForm.elements["ymin"].value;
			var ymax = oForm.elements["ymax"].value;
			var ajax = new Cocoajax();
			var postContent = coco.parseParams([{name:"jbno",value:oJbno.value},{name:"ying",value:oYing.value},{name:"jdyn",value:oJdyn.value},{name : "ymin",value: ymin},{name : "ymax",value: ymax}]);
			ajax.callback = function() {
				if(this.code <= 0 ) {
					alert(this.msg);
					return
				}
				var $msg = (this.code == 2 ? (this.msg + "。") : "");
				$msg = $msg + "是否确定更新硬度?";
				if(!confirm($msg)) return
				cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("更新硬度成功");
					coco.hidePage('YdPanel');
					cocoform.clear(oForm);
					cocopage.refresh();
					}, null, null, oInputBl);
			};
			ajax.post("checkYd.do", postContent);
		}
		//-->
		</script>
	
	</head>
<body>
<ui:page title="硬度录入">
	<f:page file="list.jsp" action="query.do" size="15">
		<table width="96%" align="center" class="form">
			<colgroup>
				<col><col><col><col><col><col>
			</colgroup>
			<tr>
				<th style="text-align: left;">用户代码</th>
				<td><ui:input name="userBegin" maxlength="4" />至<ui:input name="userEnd" maxlength="4" /></td>
				<th style="text-align: left;">订货NO</th>
				<td><ui:input name="dhno" maxlength="7" />-<ui:input name="line" maxlength="2" /></td>
				<th style="text-align: left;">指示书NO</th>
				<td><ui:input name="zsno" maxlength="6" /></td>
				<th style="text-align: left;">COIL No</th>
				<td><ui:input name="jbno" maxlength="8" /></td>
				<th style="text-align: left;">硬度是否已录入</th>
				<td><ui:select name="ying" list="'1':'否','0':'是'" headerText="全部" headerValue="" value="#1" /></td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="查  询 " onclick="cocopage.query();" /></div>
	</f:page>
	<ui:panel id="YdPanel" title="硬度更新" popup="true" display="false" closable="true">
		<form name="DataForm" action="updateYd.do" method="post" xu.s="success();">
			<input type="hidden" name="jbno" /><input type="hidden" name="ymin" /><input type="hidden" name="ymax" />
			<table id="YdTbl" width="96%" align="center" class="form">
				<colgroup>
					<col width="15%"/><col width="35%" /><col width="20%"/><col>
				</colgroup>
				<tr>
					<th>硬度值</th>
					<td><ui:int name="ying" maxlength="3" required="true" /></td>
					<th>检定人员</th>
					<td><ui:input name="jdyn" maxlength="2" required="true" /></td>
				</tr>
			</table>
			<div class="submit" style="padding-top: 15px;">
				<input type="button" class="button" value="确   定" onclick="doQd(this.form, this);" />
				<input type="button" class="button" value="关  闭" onclick="coco.hidePage('YdPanel');" />
			</div>
		</form>
	</ui:panel>
</ui:page>
</body>
</html>