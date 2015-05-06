<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>投诉登录</title>
      	<%@include file="../../../global/header.jsp"%>
      	<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<script type="text/javascript">
		<!--
		// 查询制品信息
		function queryZp(obj) {
			var v = obj.value.replace(/^\s+|\s+$/g, '');
			if(v == null || v.length == 0) return;
			var postContent = coco.parseParams([{name:"jbno",value:v}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				obj.value = '';
				var oForm = document.forms["DataForm"];
				cocoform.fillResult(oForm, this.result);
				HidDataDiv.style.display = "block";
				oForm.elements["bz"].focus();
			};
			ajax.post("zpMx.do", postContent);
		}
		// 返回
		function doReturn() {
			cocoform.clear('DataForm');
			HidDataDiv.style.display = "none";
		}
		// 退货确认
		function doTs() {
			var oForm = document.forms["DataForm"];
			cocoform.submit(oForm,function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("投诉成功");
				HidDataDiv.style.display = "none";
				return;
			});
		}
		//-->
		</script>
	</head>
	<body>
		<ui:page title="投诉登录">
			<table width="96%" align="center" class="form" style="margin-top: 10px;">
				<colgroup>
					<col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col />
				</colgroup>
				<tr>
					<th>制品No.</th>
					<td><ui:input name="jbno" maxlength="11" title="制品No." onkeydown="if(window.event.keyCode==13) queryZp(this);"/></td>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
			<div id="HidDataDiv" style="display: none;">
			<form action="doTs.do" xu.ajax="" xu.r="" xu.s="success();" name="DataForm" method="post" >
			<input type="hidden" name="zzny"/>
			<input type="hidden" name="cpno"/>
			<input type="hidden" name="zzno"/>
			<input type="hidden" name="plqf"/>
			<input type="hidden" name="fudw"/>
			<table width="96%" class="form" style="margin-top: 10px;">
				<colgroup>
					<col width="9%" /><col width="12%" /><col width="9%" /><col width="13%" /><col width="10%" /><col width="13%" /><col width="12%" /><col width="22%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">送货单号</th>
					<td><ui:input name="shno" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">制品No.</th>
					<td><ui:input name="jbno" maxlength="11" readonly="true"/></td>
					<th style="text-align: left;">重量</th>
					<td><ui:number name="zpzl" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">张数</th>
					<td><ui:number name="zshu" maxlength="10" readonly="true"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" required="true" readonly="true" maxlength="7" title="订货No." />-<ui:input name="line" required="true" title="行号" readonly="true" maxlength="2" /></td>
					<th style="text-align: left;">客户略称</th>
					<td><ui:input name="abbr" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">尺寸<span style="font-size: 12px;">(厚*宽*剪切板长)</span></th>
					<td colspan="3"><ui:number name="xpho" title="现品尺寸.厚" scale="4" precision="3" readonly="true"/>*<ui:number name="xpkn" title="现品尺寸.宽" scale="6" precision="2" readonly="true" />*<ui:number name="xpcn" title="现品尺寸.长" scale="6" precision="2" readonly="true" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">镀锡量</th>
					<td><ui:input name="sczm" maxlength="3" readonly="true"/>/<ui:input name="scfm" maxlength="3" readonly="true"/></td>
					<th style="text-align: left;">等级</th>
					<td><ui:input name="deng" maxlength="3" readonly="true"/></td>
					<th style="text-align: left;">运用规格</th>
					<td><ui:input name="yuny" maxlength="6" readonly="true"/></td>
					<th style="text-align: left;">运输公司</th>
					<td><ui:input name="ysnm" maxlength="10" readonly="true"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">表面</th>
					<td><ui:input name="face" maxlength="2" readonly="true"/></td>
					<th style="text-align: left;">规格代码</th>
					<td><ui:input name="ggno" maxlength="4" readonly="true"/></td>
					<th style="text-align: left;">原出库日期</th>
					<td><ui:input name="chri" format="yyyy-MM-dd" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">投诉日期</th>
					<td><ui:date name="tsri" required="true" prop="calendar:true;" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">备注</th>
					<td colspan="7"><ui:input name="bz" required="true" cssStyle="width: 35em;" /></td>
				</tr>
			</table>
			</form>
			<div class="submit"> <input type="button" class="button" value="确认投诉" onclick="doTs();" /> <input type="button" class="button" value="返 回" onclick="doReturn();" /></div>
			</div>
		</ui:page>
	</body>
</html>