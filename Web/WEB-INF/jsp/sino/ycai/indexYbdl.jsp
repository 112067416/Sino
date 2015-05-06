<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>原板登录</title>	
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<SCRIPT type="text/javascript">
	<!--
       //计算卷板长
       function calculateJbcn(){
			var oForm = document.forms["DataForm"];
			var zl = oForm.elements["tun"].value;
			if(zl == null || zl.length == 0) {
				return;
			}
			var kuan = oForm.elements["xpkn"].value;
			var hou = oForm.elements["xpho"].value;
			var jbcn = oForm.elements["jbcn"];
			var ajax = new Cocoajax();
			var postContent = coco.parseParams([{name:"kuan",value:kuan},{name:"hou",value:hou},{name:"zl",value:zl}]);
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				jbcn.value = this.msg;
			};
			ajax.post("getJbcn.do",postContent);
		}
       
       //返回
		function doReturn() {
			HidDiv.style.display = "none";
			var oForm = document.forms["DataForm"];
			oForm.elements["ybno"].focus();
		}
		//获取合同数据
		function doLand(){
		    var oForm = document.forms["DataForm"];
		    var blny = oForm.elements["blny"].value;
		    var ship = oForm.elements["ship"].value;
		    var ybno = oForm.elements["ybno"].value;
		    var line = oForm.elements["line"].value;
		    var jbno = oForm.elements["jbno"].value;
		    if(ybno == null || ybno.length == 0 || line == null || line.length == 0){
				alert("请输入供应商合同号和行号");
				return ;
		    }
		    var postContent = coco.parseParams([{name : "htno",value: ybno},{name:"line",value: line}]);
		    var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					HidDiv.style.display = "none";
					return;
				}
				try {
					HidDiv.style.display = "block";
					cocoform.clear(oForm);
					var obj = null;
					eval("obj="+this.result+";");
					cocoform.fillObject(oForm, obj);
					oForm.elements["jbno"].value = "";
					oForm.elements["blny"].value = blny;
					oForm.elements["ship"].value = ship;
					oForm.elements["jbno"].value = jbno;
					oForm.elements["zzsj"].focus();
				} catch (e) {
					alert("系统出错:\n" + e.description);
				}
			};
			ajax.post("getWwht.do",postContent,"POST");
		}

		function doSubmit() {
			if(!confirm("是否确定保存?")) return;
			var oForm = document.forms["DataForm"];
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				oForm.elements["jbno"].value = this.msg; 
				oForm.elements["zzsj"].value = "";
				oForm.elements["tun"].value = "";
				alert("保存成功");
				oForm.elements["line"].focus();
				HidDiv.style.display = "none";
			});
		}
		function doLoad() {
			var oForm = document.forms["DataForm"];
			oForm.elements["ybno"].focus();
		}
	//-->
	</SCRIPT>
</head>
<body onload="doLoad();">
<ui:page title="原板登录 ">
		<form action="save.do" xu.ajax="" xu.r="" xu.s="success();" name="DataForm" method="post" >
		<input type="hidden" name="ycbr" />
		<input type="hidden" name="zzsd" />
		<table class="form">
			<colgroup>
				<col width="10%" />
				<col width="20%" />
				<col width="10%" />
				<col width="10%" />
				<col width="20%" />
				<col width="20%" />
				<col width="10%" />
			</colgroup>
			<tr>
				<th style="text-align: left;">供应商合同NO.</th>
				<td><ui:input name="ybno" title="供应商合同No" maxlength="8" onkeydown="if(window.event.keyCode==13) doLand();" />-<ui:number name="line" maxlength="3" onkeydown="if(window.event.keyCode==13) doLand();" onchange="doLand();" title="行号" /></td>
				<th style="text-align: right;">原材卷板NO.</th>
				<td><ui:input name="jbno" readonly="true" title="原材卷板DB的原材卷板No." maxlength="7" value="jbno" /></td>
				<td colspan="3">&nbsp;</td>
			</tr>
		</table>
		<div id="HidDiv" style="display: none;">
		<table class="form">
			<colgroup>
				<col width="10%" />
				<col width="20%" />
				<col width="10%" />
				<col width="10%" />
				<col width="15%" />
				<col width="15%" />
				<col width="20%" />
			</colgroup>
			<tr>
				<th style="text-align: left;">制造商卷板NO.</th>
				<td><ui:input name="zzsj" maxlength="15" required="true" title="原材卷板DB的原板采购No." /></td>
				<th style="text-align: right;">重量(T)</th>
				<td><ui:number scale="6" precision="3"  name="tun" maxlength="7" required="true" positive="true" onblur="calculateJbcn()"  title="实际重量" /></td>
				<th style="text-align: left;">原板长&nbsp;&nbsp;<ui:input name="jbcn" maxlength="8" required="true" readonly="true" title="原板长" /></th>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<th style="text-align: center;">尺寸·厚</th>
				<th style="text-align: center;">尺寸·宽</th>
				<th style="text-align: center;">商社</th>
				<th style="text-align: center;">币种</th>
				<th style="text-align: center;">制造商规格略称</th>
				<th style="text-align: center;">中日达规格略称</th>
				<th style="text-align: center;">规格</th>
			</tr>
			<tr>
				<td style="text-align: center;"><ui:input name="xpho" readonly="true" maxlength="6"  title="原材卷板DB的订货尺寸.厚" /></td>
				<td style="text-align: center;"><ui:input name="xpkn" readonly="true" maxlength="6"  title="原材卷板DB的订货尺寸.宽" /></td>
				<td style="text-align: center;"><ui:input name="ssno" readonly="true" maxlength="6" title="商社" /></td>
				<td style="text-align: center;"><ui:input name="thqf" readonly="true" maxlength="6" title="币种" /></td>
				<td style="text-align: center;"><ui:input name="yblc" readonly="true" maxlength="12" title="制造商规格略称" /></td>
				<td style="text-align: center;"><ui:input name="ggnm" readonly="true" maxlength="15" title="中日达规格略称" /></td>
				<td style="text-align: center;"><ui:input name="ggno" readonly="true" maxlength="6" title="规格" /></td>
			</tr>
			<tr>
				<th style="text-align: center;">运用规格</th>
				<th style="text-align: center;">表面</th>
				<th style="text-align: center;">等级</th>
				<th style="text-align: center;">内径</th>
				<th style="text-align: center;">制造商</th>
				<th style="text-align: center;">品种</th>
				<th style="text-align: center;">制造年月日</th>
			</tr>
			<tr>
				<td style="text-align: center;"><ui:input name="yuny" readonly="true" maxlength="6" title="运用规格" /></td>
				<td style="text-align: center;"><ui:input name="face" readonly="true" maxlength="2" title="表面" /></td>
				<td style="text-align: center;"><ui:input name="deng" readonly="true" maxlength="6" title="级" /></td>
				<td style="text-align: center;"><ui:input name="njno" readonly="true" maxlength="3" title="内径" /></td>
				<td style="text-align: center;"><ui:input name="rsv1" readonly="true" maxlength="6" title="制造商" /></td>
				<td style="text-align: center;"><ui:input name="pzno" readonly="true" maxlength="2" title="品种代码" /></td>
				<td style="text-align: center;"><ui:input name="zzny"  readonly="true" maxlength="10"  title="制造年月日"/></td>
			</tr>
			<tr>
				<th style="text-align: center;">装船日期</th>
				<th style="text-align: center;">船名</th>
				<th style="text-align: center;">采购单价</th>
				<th colspan="4">&nbsp;</th>
			</tr>
			<tr>
				<td style="text-align: center;"><ui:date name="blny" required="true" prop="calendar:true;" /></td>
				<td style="text-align: center;"><ui:input name="ship" required="true"  maxlength="20" title="船名" /></td>
				<td style="text-align: center;"><ui:input name="cgdj" readonly="true" maxlength="6" title="采购单价" /></td>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="7"><div class="opt-btm"><input
			type="button" class="button" onclick="doSubmit();" value="保  存" /> <input type="button" class="button"
			value="返 回" onclick="doReturn();" /></div></td>
			</tr>
		</table>
		</div>
		</form>
	</ui:page>
</body>
</html>
