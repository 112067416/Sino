<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib 
	uri="/ui" prefix="ui"%><%@ taglib 
	uri="/f" prefix="f"%><%@ taglib 
	uri="/sys" prefix="sys"%><%@ taglib 
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>采购单价维护</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDel()"]]);

			//修改
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var oForm = document.forms["AddForm"];
				var vKey1 = coco.getAttr(oTr, "xu.id");
				var postContent = coco.parseParam("id", vKey1);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					cocoform.fillResult(oForm, this.result);
					coco.showPage('AddDetail',{center:true,top:50,width:"80%"});
				};
				ajax.post("loadCgdj.do", postContent);
			}
			//设置基础参数
			function toSetting() {
				var oForm = document.forms["DataForm"];
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code > 0) {
						cocoform.fillResult(oForm, this.result);
					}
					coco.showPage('BaseDetail',{center:true,top:50,width:"80%"});
				};
				ajax.post("getBase.do");
			}
			//进入增加采购单价页面
			function toAdd() {
				var oForm = document.forms["AddForm"];
				oForm.elements["id"].value = "";
				coco.showPage('AddDetail',{center:true,top:50,width:"80%"});
			}
			//保存采购单价
			function doSave(oInput) {
				var oForm = oInput.form;
				cocoform.submit(oForm, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						//coco.hidePage('AddDetail');
						cocopage.refresh();
					}, "是否确定保存?", null, oInput);
			}

			//删除采购单价
			function toDel(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key = coco.getAttr(oTr, "xu.id");
				var content;
				if(key != null && key.length > 0) {
					content = coco.parseParam("ids", key);
				} else {
					content = cocoform.postCheckValues("PageForm_page","ids","ids");
				}
				if(content == null || content.length == 0){
					alert("请选择要删除的记录");
					return;
				}
				if(!confirm("是否确定删除?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("deleteCgdj.do", content);
			}
			//计算费用
			function calculat(oInput) {
				var oForm = oInput.form;
				cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage('BaseDetail');
					cocopage.refresh();
					}, "是否确定保存?", null, oInput);	
			}
			//-->
		</SCRIPT>
	</head>
	<body>
<ui:page title="采购单价管理">
<f:page action="indexCgdj.do" file="listCgdj.jsp">
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="10%" /><col width="20%" /></colgroup>
<tr>
<th style="text-align: left;">板厚</th>
<td><ui:number name="xphoS" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true"/>至<ui:number name="xphoE" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true"/></td>
<th style="text-align: left;">板宽</th>
<td><ui:select name="knfw" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_KNFW'" headerText="" headerValue="" />
<th style="text-align: left;">材质</th>
<td><ui:input name="yuny" maxlength="2" /></td>
<td>&nbsp;</td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
<input type="button" class="button" value="设置基础费用" onclick="toSetting();" />
<input type="button" class="button" value="新 增" onclick="toAdd();" />
<input type="button" class="button" value="删 除" onclick="toDel()" />
</div>
</f:page>
</ui:page>
<ui:panel id="BaseDetail" title="设置基础费用" popup="true" display="false" closable="true" >
<form name="DataForm" method="post" action="calculateCgdj.do" >
<table width="100%" class="form" >
<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="20%" /></colgroup>
<tr>
<th style="text-align: left;">Base</th>
<td><ui:number name="base" title="基价" maxlength="9" scale="8" precision="2" positive="true" required="true" /></td>
<th style="text-align: left;">运费</th>
<td><ui:number name="yf" title="基价" maxlength="9" scale="8" precision="2" positive="true" required="true" /></td>
<th style="text-align: left;">利息利率</th>
<td><ui:number name="lxll" title="利息利率" maxlength="8" scale="7" precision="6" positive="true" required="true" /></td>	
<th style="text-align: left;">天数</th>
<td><ui:int name="days" maxlength="3" /> </td>					
<td>&nbsp;</td>					
</tr>
</table>
<div class="submit"><input type="button" class="button" value="保 存" onclick="calculat(this);" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('BaseDetail')" /></div>
</form>
</ui:panel>
<ui:panel id="AddDetail" title="编辑采购单价" popup="true" display="false" closable="true" >
<form name="AddForm" method="post" action="saveCgdj.do" >
<input type="hidden" name="id" />
<table width="100%" class="form" >
<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="20%" /></colgroup>
<tr>
<th style="text-align: left;">板厚</th>
<td><ui:number name="xpho" maxlength="5" scale="4" precision="3" title="尺寸.厚" required="true" positive="true"/></td>
<th style="text-align: left;">板宽</th>
<td><ui:select name="knfw" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_KNFW'" headerText="" headerValue="" prop="nn: 1;" />
</td>	
<th style="text-align: left;">材质</th>
<td><ui:input name="yuny" maxlength="2" required="true" /> </td>					
<th style="text-align: left;">差价</th>
<td><ui:number name="extra" maxlength="7" scale="6" precision="2" title="差价" required="true" positive="true"/></td>
<td>&nbsp;</td>				
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="保 存" onclick="doSave(this);" />
<input type="button" class="button" value="清 空" onclick="cocoform.clear(this.form);" />
<input type="button" class="button" value="关 闭" onclick="coco.hidePage('AddDetail');" />
</div>
</form>
</ui:panel>
</body>
</html>