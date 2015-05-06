<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%>
	<%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>装箱对照</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
			var sum = 180;
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
				oForm.action = "getCheck.do";
				var el1,el2,v1,v2;
				for(var i = 0; i < sum; i++) {
					el1 = oForm.elements["items[" + i + "].zpno"];
					el2 = oForm.elements["items[" + i + "].dhno"];
					v1 = el1.value.replace(/^\s+|\s+$/g,'');
					v2 = el2.value.replace(/^\s+|\s+$/g,'');
					if((v1 != null && v1.length > 0) && (v2 == null || v2.length == 0)) {
						el2.focus();
						alert("制品No."+v1+"对应的订货No.为空");
						return;
					}
					if((v2 != null && v2.length > 0) && (v1 == null || v1.length == 0)) {
						el1.focus();
						alert("订货No."+v2+"对应的制品No.为空");
						return;
					}
				}
				if(!confirm("确定装箱对照?")) return;
				cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					} 
					oForm.action = "zxdz.do";
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
				var name = el.name;
				var v = el.value;
				var index = parseInt(name.replace(/\D+/gi, ''));
				var oForm = el.form;
				var $el,el1,el2,v1,v2,index;
				if(((index + 1) % 60 == 0) && name.indexOf("dhno") >= 0) doNext();
				if(name.indexOf("zpno") >= 0) {
					$el = oForm.elements["items[" + index + "].dhno"];
					if(v != null && (v.length == 7 || v.length == 10)) {
						el.value = "0" + v;
					}
				} else {
					$el = oForm.elements["items[" + (index + 1) + "].zpno"];
				}
				if($el == null) return;
				$el.focus();
				var drsl = 0;
				for(var i = 0; i < sum; i++) {
					el1 = oForm.elements["items[" + i + "].zpno"];
					el2 = oForm.elements["items[" + i + "].dhno"];
					v1 = el1.value.replace(/^\s+|\s+$/g,'');
					v2 = el2.value.replace(/^\s+|\s+$/g,'');
					if(v1 == null || v1.length == 0 || v2 == null || v2.length == 0) {
						continue;
					}
					drsl++;
				}
		//		}
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
					oForm.elements["items[0].zpno"].focus();
				};
				ajax.post("getZxzs.do", coco.parseParams([{name : "zxno",value: zxno}]));
			}

			var i = 1;
			//向下翻页 
			function doNext(oForm) {
				if(i >= 3) {
					alert("已到底,请上一页");
					return;
				}
				var oTbl1 = document.getElementById("DataTable"+i);
				oTbl1.style.display = "none";
				i += 1;
				var oTbl2 = document.getElementById("DataTable"+i);
				oTbl2.style.display = "block";
				if(oForm == null) return;
				if(i == 2) {
					oForm.elements["items[60].zpno"].focus();
				} else if(i == 3) {
					oForm.elements["items[120].zpno"].focus();
				}
			}
			//向上翻页 
			function doPrevious(oForm) {
				if(i <= 1) {
					alert("已到顶,请下一页");
					return;
				}
				var oTbl1 = document.getElementById("DataTable"+i);
				oTbl1.style.display = "none";
				i -= 1;
				var oTbl2 = document.getElementById("DataTable"+i);
				oTbl2.style.display = "block";
				if(oForm == null) return;
				if(i == 1) {
					oForm.elements["items[0].zpno"].focus();
				} else if(i == 2) {
					oForm.elements["items[60].zpno"].focus();
				}
			}

			function doClear(oForm) {
				if(!confirm("是否确定清空?")) return;
				var el1,el2;
				oForm.elements["drbqs"].value = 0;
				for(var i = 0; i < sum; i++) {
					el1 = oForm.elements["items[" + i + "].zpno"];
					el2 = oForm.elements["items[" + i + "].dhno"];
					if(el1 == null || el2 == null) continue;
					el1.value = "";
					el2.value = "";
				}
			}
		//-->
		</SCRIPT>
</head>
<body>
	<ui:page title="装箱对照">
	<form name="DataForm" method="post" action="zxdz.do" >
		<table width="100%">
			<colgroup>
				<col width="10%" /><col width="11%" /><col width="10%" /><col width="10%" /><col width="12%" /><col width="5%"  /><col width="12%" /><col width="5%"  /><col width="25%"  />
			</colgroup>
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
				<td style="text-align: center;"><input type="button" class="button" onclick="doCheck(this.form,this);" value="确 定 " />   <input type="button" class="button" onclick="doClear(this.form);" value="清 空 " />    <input type="button" class="button" onclick="doPrevious(this.form);" value="上一页" />    <input type="button" class="button" onclick="doNext(this.form);" value="下一页" /></td>
			</tr>
		</table>
		<table id="DataTable1" style="display: block;" width="96%" align="center">
			<colgroup><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="12%" /><col width="10%" /></colgroup>
			<tr>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
			</tr>
			<tr>
				<td><ui:input name="items[0].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[0].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[1].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[1].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[2].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[2].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[3].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[3].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[4].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[4].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[5].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[5].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[6].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[6].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[7].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[7].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[8].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[8].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[9].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[9].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[10].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[10].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[11].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[11].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[12].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[12].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[13].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[13].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[14].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[14].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[15].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[15].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[16].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[16].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[17].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[17].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[18].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[18].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[19].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[19].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[20].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[20].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[21].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[21].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[22].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[22].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[23].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[23].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[24].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[24].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[25].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[25].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[26].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[26].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[27].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[27].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[28].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[28].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[29].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[29].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[30].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[30].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[31].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[31].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[32].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[32].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[33].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[33].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[34].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[34].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[35].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[35].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[36].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[36].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[37].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[37].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[38].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[38].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[39].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[39].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[40].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[40].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[41].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[41].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[42].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[42].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[43].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[43].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[44].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[44].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[45].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[45].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[46].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[46].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[47].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[47].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[48].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[48].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[49].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[49].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);"title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[50].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[50].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[51].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[51].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[52].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[52].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[53].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[53].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[54].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[54].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[55].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[55].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[56].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[56].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[57].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[57].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[58].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[58].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[59].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[59].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
		</table>
		<table id="DataTable2" style="display: none;" width="96%" align="center">
			<colgroup><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="12%" /><col width="10%" /></colgroup>
			<tr>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
			</tr>
			<tr>
				<td><ui:input name="items[60].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[60].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[61].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[61].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[62].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[62].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[63].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[63].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[64].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[64].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[65].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[65].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[66].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[66].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[67].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[67].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[68].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[68].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[69].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[69].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[70].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[70].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[71].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[71].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[72].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[72].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[73].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[73].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[74].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[74].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[75].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[75].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[76].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[76].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[77].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[77].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[78].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[78].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[79].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[79].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[80].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[80].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[81].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[81].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[82].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[82].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[83].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[83].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[84].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[84].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[85].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[85].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[86].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[86].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[87].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[87].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[88].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[88].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[89].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[89].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[90].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[90].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[91].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[91].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[92].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[92].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[93].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[93].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[94].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[94].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[95].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[95].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[96].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[96].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[97].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[97].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[98].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[98].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[99].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[99].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[100].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[100].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[101].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[101].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[102].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[102].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[103].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[103].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[104].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[104].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[105].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[105].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[106].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[106].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[107].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[107].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[108].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[108].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[109].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[109].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);"title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[110].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[110].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[111].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[111].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[112].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[112].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[113].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[113].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[114].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[114].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[115].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[115].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[116].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[116].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[117].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[117].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[118].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[118].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[119].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[119].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
		</table>
		<table id="DataTable3" style="display: none;" width="96%" align="center">
			<colgroup><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="13%" /><col width="10%" /><col width="3%" /><col width="12%" /><col width="10%" /></colgroup>
			<tr>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
				<td>&nbsp;</td>
				<th style="text-align: center;">制品No.</th>
				<th style="text-align: center;">订货No.</th>
			</tr>
			<tr>
				<td><ui:input name="items[120].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[120].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[121].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[121].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[122].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[122].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[123].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[123].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[124].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[124].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[125].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[125].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[126].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[126].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[127].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[127].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[128].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[128].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[129].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[129].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[130].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[130].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[131].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[131].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[132].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[132].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[133].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[133].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[134].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[134].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[135].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[135].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[136].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[136].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[137].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[137].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[138].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[138].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[139].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[139].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[140].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[140].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[141].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[141].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[142].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[142].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[143].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[143].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[144].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[144].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[145].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[145].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[146].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[146].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[147].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[147].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[148].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[148].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[149].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[149].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[150].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[150].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[151].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[151].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[152].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[152].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[153].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[153].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[154].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[154].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[155].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[155].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[156].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[156].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[157].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[157].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[158].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[158].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[159].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[159].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[160].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[160].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[161].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[161].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[162].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[162].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[163].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[163].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[164].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[164].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[165].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[165].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[166].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[166].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[167].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[167].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[168].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[168].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[169].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[169].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);"title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[170].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[170].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[171].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[171].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[172].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[172].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[173].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[173].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[174].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[174].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[175].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[175].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
			<tr>
				<td><ui:input name="items[176].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[176].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[177].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[177].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[178].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[178].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
				<td>&nbsp;</td>
				<td><ui:input name="items[179].zpno" maxlength="12" onkeydown="if(window.event.keyCode == 13) count(this);" title="制品No." /></td>
				<td><ui:input name="items[179].dhno" maxlength="10" onkeydown="if(window.event.keyCode == 13) count(this);" title="订货No" /></td>
			</tr>
		</table>
		</form>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>
