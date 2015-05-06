<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
  taglib uri="/ui" prefix="ui"%><%@ 
  taglib uri="/f" prefix="f"%><%@ 
  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>原板检查证明书查询</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDel()"]]);
			//单条删除
			function toDel(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "xu.id");
				var postContent = coco.parseParams([{name : "id",value: vKey1}]);
				if(!window.confirm("确定要删除该条记录吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("删除成功");
					coco.hidePage('SpjcsDetail');
					cocopage.refresh();
				};
				ajax.post("del.do", postContent, "POST");
			
			}
			// 获取原版信息
			function getYcai() {
				var form = document.forms["DataForm"];
				var zzsj = form.elements["zzsj"].value;
				if(zzsj == null || zzsj.length == 0) {
					alert("请输入制造商卷板NO");
					return;
				}
				var postContent = coco.parseParams([{name : "zzsj",value: zzsj}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						form.elements["zzsj"].value = "";
						return;
					}
					cocoform.fillResult(form, this.msg, null, true);
					form.elements["ycaiTp.jbno"].value = form.elements["jbno"].value;
					form.elements["cfcc"].focus();
				};
				ajax.post("getYcai.do", postContent, "POST");
			}

			//批量删除
			function deletes() {
				var postContent = cocoform.postCheckValues("PageForm_page", "ids", null);
				if(postContent == null || postContent.length == 0) {
					alert("请选择要做删除操作的记录");
					return;
				}
				if(!confirm("确定删除吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alet(this.msg);
						return;
					}
					alert(this.msg);
					cocopage.refresh();
				};
				ajax.post("dels.do", postContent);
			}
			
			//新增
			function toAdd() {
				var form = document.forms["DataForm"];
				cocoform.clear(form);
				coco.showPage("SpjcsDetail", { center : true, top : 50, width : "80%" });
				var el = form.elements["zzsj"];
				el.className = "normal";
				el.readOnly = false;
				el.focus();
			}
			//修改
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "xu.id");
				var postContent = coco.parseParams([{name : "jbno",value: vKey1}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					var form = document.forms["DataForm"];
					var el = form.elements["zzsj"];
					el.className = "form-readonly";
					el.readOnly = true;
					var obj = cocoform.fillResult(form, this.result);
					var jbno = obj["ycaiTp"]["jbno"];
					form.elements["jbno"].value = jbno;
					form.elements["ycaiTp.jbno"].value = jbno;
					form.elements["$type"].value = "modify";
					coco.showPage("SpjcsDetail", {center : true, top : 50, width : "80%" });
				};
				ajax.post("load.do", postContent, "POST");
			}
			
			function success() {
				alert("保存成功");
				coco.hidePage('SpjcsDetail');
				cocopage.refresh();
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="原板检查证明书查询">
		<f:page action="index.do" file="list.jsp">
			<table class="form" width="100%">
				<colgroup>
				<col width="15%" />
				<col width="25%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">原材卷板NO</th>
					<td><ui:input name="jbnoBegin" title="开始原材卷板NO" maxlength="6"/>至<ui:input name="jbnoEnd" title="结束原材卷板NO" maxlength="6"/></td>
					<th style="text-align: left;">原板用户</th>
					<td colspan="3"><ui:input name="ycbr" maxlength="20"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">尺寸.厚</th>
					<td><ui:number name="xpho" scale="4" precision="3" /></td>
					<th style="text-align: left;">尺寸.宽</th>
					<td><ui:number name="xpkn" scale="6" precision="2"/></td>
					<th style="text-align: right;">表面</th>
					<td><ui:input name="face" maxlength="2"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">供应商合同NO</th>
					<td><ui:input name="ybno" title="合同NO" maxlength="8"/>-<ui:input name="line" maxlength="3" /></td>
					<th style="text-align: left;">船名</th>
					<td colspan="3"><ui:input name="ship" maxlength="20"/></td>
				</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查 询" onclick="cocopage.query();"/>
			<input type="button" class="button" value="新 增" onclick="toAdd()" />
			<input type="button" class="button" value="删 除" onclick="deletes()" />
			</div>
		</f:page>
	</ui:page>
	<ui:panel id="SpjcsDetail" title="商品检查书详细信息" popup="true" display="false" closable="true">
	<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
    	<input type="hidden" name="$type" />
    	<input type="hidden" name="ycaiTp.jbno" />
		<table width="98%" align="center" style="margin: 20px auto;"
			class="form">
			<colgroup>
				<col width="10%" />
				<col width="25%" />
				<col width="20%" />
				<col width="20%" />
				<col width="10%" />
				<col width="15%" />
			</colgroup>
			<tr>
				<th style="text-align: left;">船名</th>
				<td><ui:input name="ship" readonly="true" maxlength="20" title="原材卷板DB的船名" /></td>
				<th style="text-align: left;">制造商卷板NO</th>
				<td><ui:input name="zzsj" maxlength="10" onchange="getYcai();" onkeydown="if(window.event.keyCode == 13) getYcai();" required="true" title="制造商卷板NO"/></td>
				<th style="text-align: left;">原材板卷NO </th>
				<td><ui:input name="jbno" readonly="true" maxlength="6" title="原材板卷NO" required="true" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">C</th>
				<td><ui:number name="cfcc" scale="4" precision="1" title="检查证明DB的成分值（C）" required="true" /></td>
				<th style="text-align: left;">Si</th>
				<td><ui:int name="cfsi"  maxlength="2" title="检查证明DB的成分值（Si）" required="true" /></td>
				<th style="text-align: left;">Mn</th>
				<td><ui:int name="cfmn" maxlength="2" title="检查证明DB的成分值（Mn）" required="true" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">P</th>
				<td><ui:int name="cfpp" maxlength="2" title="检查证明DB的成分值（P）" required="true" /></td>
				<th style="text-align: left;">S</th>
				<td><ui:int name="cfss" maxlength="2" title="检查证明DB的成分值（S）" required="true" /></td>
				<th style="text-align: left;">Sol.Al</th>
				<td><ui:int name="cfso" maxlength="2" title="检查证明DB的成分值（Sol.Al）" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">硬度</th>
				<td><ui:int name="ying" maxlength="2" title="检查证明DB的成分值硬度" required="true" /></td>
				<th style="text-align: left;">YP</th>
				<td><ui:number name="yp" scale="3" precision="1" title="YP" /></td>
				<th style="text-align: left;">吹练NO</th>
				<td><ui:input name="chui" maxlength="10" title="检查证明DB的成分值吹练NO" required="true" /></td>
			</tr>
		</table>
		<div class="opt-btm"><input type="button" class="button" value="保  存" onclick="cocoform.submit(this, null, '是否确定保存?');" /> <input type="button" class="button"value="关  闭" onclick="coco.hidePage('SpjcsDetail')" /></div>
		</form>
	</ui:panel>
	</body>
</html>