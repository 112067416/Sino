<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%>
	<%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>次级品装箱对照</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
			//提交
			function doSubmit(oForm, oInput) {
				cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					} 
					var zxno = oForm.elements["zxno"].value;
					print(zxno);
					cocoform.clear(oForm);
				}, null, null, oInput);
			}
			//判断装箱对照的数据是否合格
			function doCheck(oForm, oInput) {
				oForm.action = "getCjpCheck.do";
				if(!confirm("确定装箱对照?")) return;
				cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					} 
					oForm.action = "cjpZxdz.do";
					doSubmit(oForm, oInput);
				}, null, null, oInput);
			}
			//装箱指示书打印
			function print(zxno) {
				var key = coco.parseParams([{name:"ids",value:zxno}]);
				document.getElementById("PrintFrame").src = path + "/sino/dy/chdy/shd.do?"+key;
			}
			//统计页面输入制品的个数
			function count(el) {
				if(el == null) return;
				var oForm = el.form;
				var v = el.value;
				if(v != null && (v.length == 7 || v.length == 10)) {
					el.value = "0" + v;
				}
				var drsl = 0;
				var eles = oForm.elements["zpno"];
				var $el;
				var index = 0;
				var len = eles.length;
				for(var i = 0; i < len; i++) {
					$el = eles[i];
					if((i + 1 ) < len && $el == el) {
						index = i + 1;
					} 
					v = $el.value.replace(/^\s+|\s+$/g,'');
					if(v == null || v.length == 0) {
						continue;
					}
					drsl++;
				}
				eles[index].focus();
				oForm.elements["drbqs"].value = drsl;
			}
			//查询装箱指示书信息
			function queryZxzs(el){
				var zxno = el.value.replace(/^\s+|\s+$/,'');
				if(zxno == null || zxno.length == 0){
					alert("请输入装箱指示书No.");
					return;
				}
				var ignores = ["ddr"];
				var oForm = document.forms["DataForm"];
                var ajax = new Cocoajax();
                ajax.callback = function() {
					if(this.code < 0) {
						alert(this.msg);
						el.value = "";
						el.focus();
						return;
					}
					cocoform.clear(oForm, ignores);
					var obj;
					eval("obj=" + this.result + ";");
					oForm.elements["chri"].value = obj.chri;
					oForm.elements["chsu"].value = obj.chsu;
					el.value = zxno;
					oForm.elements["zpno"][0].focus();
				};
				ajax.post("getZxzs.do", coco.parseParams([{name : "zxno",value: zxno}]));
			}

			function doClear(oForm) {
				if(!confirm("是否确定清空?")) return;
				var el;
				oForm.elements["drbqs"].value = 0;
				var eles = oForm.elements["zpno"];
				for(var i = 0; i < eles.length; i++) {
					el = oForm.elements["zpno"];
					if(el == null || el == null) continue;
					el.value = "";
				}
			}
		//-->
		</SCRIPT>
</head>
<body>
	<ui:page title="次级品装箱对照">
	<form name="DataForm" method="post" action="cjpZxdz.do" >
		<table width="100%">
			<colgroup><col width="10%" /><col width="11%" /><col width="10%" /><col width="10%" /><col width="12%" /><col width="5%"  /><col width="12%" /><col width="5%"  /><col width="25%"  /></colgroup>
			<tr>
				<th height="18" style="color:#99ffff; font-size: 14px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">装箱指示No.</th>
				<td><ui:input name="zxno" maxlength="10" title="装箱指示No" required="true" onblur="queryZxzs(this);" onkeydown="if(window.event.keyCode == 13) queryZxzs(this);" /></td>
				<th style="color:#99ffff; font-size: 14px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">出货日期</th>
				<td><ui:date name="chri" prop="calendar:true;" required="true"  /></td>
				<th style="color:#99ffff; font-size: 14px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">应有标签数</th>
				<td><ui:input name="chsu" maxlength="5" readonly="true" title="应有标签数" /></td>
				<th style="color:#99ffff; font-size: 14px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">读入制品数</th>
				<td><ui:input name="drbqs" readonly="true" maxlength="5" title="读入制品数" /></td>
				<th style="color:#99ffff; font-size: 14px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">打单人&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="ddr" sql="#select VALUE_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_DY_SHD'" /></th>
			</tr>
			<tr>
				<td height="18" colspan="8">&nbsp;</td>
				<td style="text-align: center;">
				<input type="button" class="button" onclick="doCheck(this.form,this);" value="确 定 " />
				<input type="button" class="button" onclick="doClear(this.form);" value="清 空 " />
				</td>
			</tr>
		</table>
		<table id="DataTable1" style="display: block;" width="96%" align="center">
			<colgroup><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="12%" /><col width="10%" /></colgroup>
			<tr>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">制品No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">制品No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">制品No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">制品No.</th>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
			<tr>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td>&nbsp;</td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
			</tr>
		</table>
		</form>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>
