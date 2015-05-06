<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@
	taglib prefix="sys" uri="/sys" %><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>原板成本管理</title>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<SCRIPT type="text/javascript">
	<!--
		//提交
		function doSubmit(flag) {
			if(!confirm(flag == null ? "是否确定?" : "是否确定重新计算?")) return;
			var oForm = document.forms["DataForm"];
			oForm.elements["flag"].value = flag;
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			cocoform.submit(oForm, function(){
				coco.hideAlert();
				if(this.code <= 0) {
					alert(this.msg);
					return;
				} 
				alert("操作成功");
				return;
			});
		}
		//
		function doChange(el, name) {
			var form = el.form;
			form.elements[name].value = el.options[el.selectedIndex].text;
		}
		//
		function loadYbcb(el){
			var oForm = el.form;
		    var ship = oForm.elements["ship"].value;
		    if(ship == null || ship.length == 0) {
	            return ;
		    }
		    var postContent = coco.parseParams([{name : "ship",value: ship}]);
		    var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					return;
				}
				var obj;
				eval("obj="+this.result+";");
				oForm.elements["ship"].value = obj.ship;
				oForm.elements["ysgs"].value = obj.ysgs;
				oForm.elements["yssl"].value = obj.yssl;
				oForm.elements["xcmt"].value = obj.xcmt;
				oForm.elements["gzsl"].value = obj.gzsl;
				oForm.elements["bgbj"].value = obj.bgbj;
				oForm.elements["hxtd"].value = obj.hxtd;
				oForm.elements["jyjy"].value = obj.jyjy;
				oForm.elements["zljd"].value = obj.zljd;
				oForm.elements["gdf"].value = obj.gdf;
				oForm.elements["hgjj"].value = obj.hgjj;
				oForm.elements["bfhl"].value = obj.bfhl;
				oForm.elements["myhl"].value = obj.myhl;
			};
			ajax.post("loadYbcb.do", postContent);
		}
	//-->
	</SCRIPT>
</head>
<body>
<ui:page title="原板成本管理 ">
	<form name="DataForm" xu.ajax="" method="post" action="saveYbcb.do" >
		<input type="hidden" name="flag" />
		<table class="form">
			<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="30%" /></colgroup>
			<tr>
				<th style="text-align: left;">船名</th>
				<td><ui:input name="ship" maxlength="20" prop="nn:1;" onblur="loadYbcb(this);" /></td>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<th style="text-align: left;">运输费</th>
				<td><ui:select name="yssl" sql="#select VALUE_,KEY_ from COCO_CODE_ITEM where CODE_='YBYSGS'" headerValue="" headerText="请选择" onchange="doChange(this,'ysgs')"/><input type="hidden" name="ysgs" /></td>
				<th style="text-align: left;">港杂费</th>
				<td><ui:select name="gzsl" sql="#select VALUE_,KEY_ from COCO_CODE_ITEM where CODE_='XCMT'" headerValue="" headerText="请选择" onchange="doChange(this,'xcmt')"/><input type="hidden" name="xcmt" /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<th style="text-align: left;">进口报关报检费</th>
				<td><ui:number name="bgbj" scale="7" precision="2" /></td>
				<th style="text-align: left;">进口换小提单费</th>
				<td><ui:number name="hxtd" scale="7" precision="2" /></td>
				<th style="text-align: left;">进口检验检疫费</th>
				<td><ui:number name="jyjy" scale="7" precision="2" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">重量鉴定费</th>
				<td><ui:number name="zljd" scale="7" precision="2" /></td>
				<th style="text-align: left;">过磅费</th>
				<td><ui:number name="gdf" scale="7" precision="2" /></td>
				<th style="text-align: left;">海关加价</th>
				<td><ui:number name="hgjj" scale="7" precision="2" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">保费汇率</th>
				<td><ui:number name="bfhl" scale="9" precision="8" readonly="true" /></td>
				<th style="text-align: left;">美元汇率</th>
				<td><ui:number name="myhl" scale="8" precision="6" /></td>
				<th style="text-align: left;">海关基价</th>
				<td><ui:number name="hgbj" scale="7" precision="2" /></td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="确  认" onclick="doSubmit();" /> <input type="button" class="button" value="重新计算" onclick="doSubmit('Y');" /></div>
	</form>
</ui:page>
</body>
</html>
