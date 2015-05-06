<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><%@
taglib prefix="sino" uri="/sino" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		//保存
		function doSave(oForm, oInput) {
			cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					parent.cocoframe.close(parent.cocoframe.pageCurr);

				}, '是否确定保存?', null, oInput);
		}

		var tableForm = new TableForm("ItemTbl");
		//增加异常事项
		function toYcsx() {
			var oForm = document.forms["YcsxForm"];
			var id = oForm.elements["pid"].value;
			if(id == null || id.length == 0) {
				alert("还未增加分析作业日志");
				return;
			}
			var postContent = coco.parseParams([{name:"id",value:id}]);
			var ajax = new Cocoajax();
			ajax.dataDiv = YcsxDiv; 
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("YcsxPanel", {width : "100%" });
			};
			ajax.post("ycsx.do", postContent);
		}
		//获得卷材的生产信息
		function getDh(el) {
			var v = el.value.replace(/^s+\s+$/gi,'');
			if(v == null || v.length == 0) return;
			var postContent = coco.parseParams([{name:"jbno",value:v}]);
			var tr = el.parentElement.parentElement;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					setDhxx(tr, null);
					el.value = "";
					el.focus();
					return;
				}
				var obj = null;
				eval("obj=" + this.result + ";");	
				setDhxx(tr, obj);
			};
			ajax.post("getDhForJs.do", postContent);
		}
		//设置订货信息
		function setDhxx(tr, o) {
			if(o == null ) {
				tr.cells[4].innerHTML = "";
				tr.cells[6].innerHTML = "";
				tr.cells[7].innerHTML = "";
				tr.cells[8].innerHTML = "";
				tr.cells[9].innerHTML = "";
				tr.cells[10].innerHTML = "";
				tr.cells[11].innerHTML = "";
				return;
			}
			var dhno = o.dhno != null ? o.dhno : "";
			var abbr = o.abbr != null ? o.abbr : "";
			var houu = o.houu != null ? o.houu : "";
			var kuan = o.kuan != null ? o.kuan : "";
			var cang = o.cang != null && o.cang > 0 ? o.cang : "COIL";
			var abbr = o.abbr != null ? o.abbr : "";
			var face = o.face != null ? o.face : "";
			var yuny = o.yuny != null ? o.yuny : "";
			var cond = o.cond != null ? o.cond : "";
			var dhfz = o.dhfz != null ? o.dhfz : "";
			var yqty = o.yqty != null ? o.yqty : "";
			
			tr.cells[4].innerHTML = dhfz + "<br />" + yqty;
			tr.cells[7].innerHTML = abbr;
			tr.cells[8].innerHTML = yuny;
			tr.cells[9].innerHTML = dhno;
			tr.cells[10].innerHTML = houu + "*" + kuan + "*" + cang;
			tr.cells[11].innerHTML = cond;
			tr.cells[12].innerHTML = face;
		}
		//保存异常事项
		function saveYcsx(oForm, oInput) {
			var pid = oForm.elements["pid"].value;
			if(pid == null || pid.length == 0) {
				alert("请先保存分析作业日志,再增加异常事项");
				return;
			}
			tableForm.formatTblForm("items",1);
			var ajax = new Cocoajax();
			cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage('YcsxPanel');
					return;
				}, "是否确定保存吗?", null, oInput);
		}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="分析作业日志维护">
		<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" xu.color="1" method="post" action="update.do" >
			<input type="hidden" name="id" value="${entity.id }" />
			<table width="96%" class="form" align="center">
				<colgroup>
					<col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="40%" />
				</colgroup>
				<tr>
					<th style="text-align: center; vertical-align: middle;">班组</th>
					<td><sys:codeSelect name="banzu" code="#SINO_ZU" prop="nn:1;" headerText="" headerValue="" value="entity.banzu" /></td>
					<th style="text-align: center; vertical-align: middle;">星期</th>
					<td><ui:select list="${xqs }" prop="nn:1;" headerText="" headerValue="" name="tbxq" value="entity.tbxq" /></td>
					<th style="text-align: center; vertical-align: middle;">记录日期</th>
					<td><ui:date name="tbrq" required="true" prop="calendar:true;" value="entity.tbrq" /></td>
					<td><div class="submit"><input type="button" class="button" value="保  存" onclick="doSave(this.form, this);"/> <input type="button" class="button" value="异常事项" onclick="toYcsx();"/></div></td>
				</tr>
			</table>
			<table width="100%" class="form" border="1" style="border-collapse: collapse;border-color: #ffffff;">
				<colgroup>
				<col width="8%" /><col width="6%" /><col width="6%" /><col width="2%" /><col width="10%" />
				<col width="8%" /><col width="6%" /><col width="6%" /><col width="8%" /><col width="2%" />
				<col width="38%" />
				</colgroup>
				<tr>
					<th style="text-align: center; vertical-align: middle;" style="text-align: center; vertical-align: middle;" rowspan="3">纯水</th>
					<th style="text-align: center; vertical-align: middle;">时间</th>
					<td colspan="2"><ui:input name="cssj1" maxlength="5" imeMode="false" value="entity.cssj1" /></td>
					<td><ui:input name="cssj2" maxlength="5" imeMode="false" value="entity.cssj2" /></td>
					<th style="text-align: center; vertical-align: middle;" style="text-align: center; vertical-align: middle;" rowspan="3">特别事项</th>
					<td colspan="5" rowspan="3"><ui:textarea name="tbsx" cols="90" rows="7" value="entity.tbsx" /></td>
				</tr>
			 	<tr>
					<th style="text-align: center; vertical-align: middle;">导电度</th>
					<td colspan="2"><ui:number scale="4" precision="3" maxlength="5" name="ddd1" value="entity.ddd1" /></td>
					<td><ui:number scale="4" precision="3" maxlength="5" name="ddd2" value="entity.ddd2" /></td>
			 	</tr>
			 	<tr>
					<th style="text-align: center; vertical-align: middle;">PH</th>
					<td colspan="2"><ui:number scale="3" precision="2" maxlength="4" name="ph1" value="entity.ph1" /></td>
					<td><ui:number scale="3" precision="2" maxlength="4" name="ph2" value="entity.ph2" /></td>
			 	</tr>
			 	<tr>
				 	<th style="text-align: left; vertical-align: middle;" colspan="11">作业实绩</th>
			 	</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;" colspan="2">碱浸液</th>
					<td><ui:int maxlength="2" name="jjy" value="entity.jjy" /></td>
					<th style="text-align: center; vertical-align: middle;" rowspan="14">&nbsp;</th>
					<th style="text-align: center; vertical-align: middle;">&nbsp;</th>
					<th style="text-align: center; vertical-align: middle;">昨日未完成</th>
					<th style="text-align: center; vertical-align: middle;">今日接收</th>
					<th style="text-align: center; vertical-align: middle;">今日完成</th>
					<th style="text-align: center; vertical-align: middle;">今日未完成</th>
					<th style="text-align: center; vertical-align: middle;" rowspan="14">&nbsp;</th>
					<th style="text-align: center; vertical-align: middle;">其他</th>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;" colspan="2">碱电解液</th>
					<td><ui:int maxlength="2" name="jdjy" value="entity.jdjy" /></td>
					<th style="text-align: center; vertical-align: middle;">锡附着量</th>
					<td><ui:int maxlength="2" name="xfzlZr" value="entity.xfzlZr" /></td>
					<td><ui:int maxlength="2" name="xfzlJrjs" value="entity.xfzlJrjs" /></td>
					<td><ui:int maxlength="2" name="xfzlJrwc" value="entity.xfzlJrwc" /></td>
					<td><ui:int maxlength="2" name="xfzlJrww" value="entity.xfzlJrww" /></td>
					<td rowspan="13" valign="top"><ui:textarea name="qt" cols="45" rows="33" value="entity.qt" /></td>
				</tr>
			 	<tr>
					<th style="text-align: center; vertical-align: middle;" rowspan="2">酸电解液</th>
					<th style="text-align: center; vertical-align: middle;">H<sub>2</sub>SO<sub>4</sub></th>
					<td><ui:int maxlength="2" name="sdjyHso" value="entity.sdjyHso" /></td>
					<th style="text-align: center; vertical-align: middle;">铬附着量</th>
					<td><ui:int maxlength="2" name="gfzlZr" value="entity.gfzlZr" /></td>
					<td><ui:int maxlength="2" name="gfzlJrjs" value="entity.gfzlJrjs" /></td>
					<td><ui:int maxlength="2" name="gfzlJrwc" value="entity.gfzlJrwc" /></td>
					<td><ui:int maxlength="2" name="gfzlJrww" value="entity.gfzlJrww" /></td>
				</tr>
			 	<tr>
					<th style="text-align: center; vertical-align: middle;">Fe</th>
					<td><ui:int maxlength="2" name="sdjyFe" value="entity.sdjyFe" /></td>
					<th style="text-align: center; vertical-align: middle;">涂油量</th>
					<td><ui:int maxlength="2" name="tylZr" value="entity.tylZr" /></td>
					<td><ui:int maxlength="2" name="tylJrjs" value="entity.tylJrjs" /></td>
					<td><ui:int maxlength="2" name="tylJrwc" value="entity.tylJrwc" /></td>
					<td><ui:int maxlength="2" name="tylJrww" value="entity.tylJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;" rowspan="5">锡电镀液</th>
					<th style="text-align: center; vertical-align: middle;">Sn</th>
					<td><ui:int maxlength="2" name="xddySn" value="entity.xddySn" /></td>
					<th style="text-align: center; vertical-align: middle;">表面六价铬</th>
					<td><ui:int maxlength="2" name="bmljgZr" value="entity.bmljgZr" /></td>
					<td><ui:int maxlength="2" name="bmljgJrjs" value="entity.bmljgJrjs" /></td>
					<td><ui:int maxlength="2" name="bmljgJrwc" value="entity.bmljgJrwc" /></td>
					<td><ui:int maxlength="2" name="bmljgJrww" value="entity.bmljgJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">Acid</th>
					<td><ui:int maxlength="2" name="xddyAcid" value="entity.xddyAcid" /></td>
					<th style="text-align: center; vertical-align: middle;">ISV</th>
					<td><ui:int maxlength="2" name="isvZr" value="entity.isvZr" /></td>
					<td><ui:int maxlength="2" name="isvJrjs" value="entity.isvJrjs" /></td>
					<td><ui:int maxlength="2" name="isvJrwc" value="entity.isvJrwc" /></td>
					<td><ui:int maxlength="2" name="isvJrww" value="entity.isvJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">ENSA</th>
					<td><ui:int maxlength="2" name="xddyEnsa" value="entity.xddyEnsa" /></td>
					<th style="text-align: center; vertical-align: middle;">TCS</th>
					<td><ui:int maxlength="2" name="tcsZr" value="entity.tcsZr" /></td>
					<td><ui:int maxlength="2" name="tcsJrjs" value="entity.tcsJrjs" /></td>
					<td><ui:int maxlength="2" name="tcsJrwc" value="entity.tcsJrwc" /></td>
					<td><ui:int maxlength="2" name="tcsJrww" value="entity.tcsJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">Fe</th>
					<td><ui:int maxlength="2" name="xddyFe" value="entity.xddyFe" /></td>
					<th style="text-align: center; vertical-align: middle;">ATC</th>
					<td><ui:int maxlength="2" name="atcZr" value="entity.atcZr" /></td>
					<td><ui:int maxlength="2" name="atcJrjs" value="entity.atcJrjs" /></td>
					<td><ui:int maxlength="2" name="atcJrwc" value="entity.atcJrwc" /></td>
					<td><ui:int maxlength="2" name="atcJrww" value="entity.atcJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">Sludeg</th>
					<td><ui:int maxlength="2" name="xddySlud" value="entity.xddySlud" /></td>
					<th style="text-align: center; vertical-align: middle;">TCV</th>
					<td><ui:int maxlength="2" name="tcvZr" value="entity.tcvZr" /></td>
					<td><ui:int maxlength="2" name="tcvJrjs" value="entity.tcvJrjs" /></td>
					<td><ui:int maxlength="2" name="tcvJrwc" value="entity.tcvJrwc" /></td>
					<td><ui:int maxlength="2" name="tcvJrww" value="entity.tcvJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;" rowspan="2">拖出液</th>
					<th style="text-align: center; vertical-align: middle;">Sn</th>
					<td><ui:int maxlength="2" name="tcySn" value="entity.tcySn" /></td>
					<th style="text-align: center; vertical-align: middle;">PLT</th>
					<td><ui:int maxlength="2" name="pltZr" value="entity.pltZr" /></td>
					<td><ui:int maxlength="2" name="pltJrjs" value="entity.pltJrjs" /></td>
					<td><ui:int maxlength="2" name="pltJrwc" value="entity.pltJrwc"/></td>
					<td><ui:int maxlength="2" name="pltJrww" value="entity.pltJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">Acid</th>
					<td><ui:int maxlength="2" name="tcyAcid" value="entity.tcyAcid" /></td>
					<th style="text-align: center; vertical-align: middle;">涂料密着性</th>
					<td><ui:int maxlength="2" name="tlmzxZr" value="entity.tlmzxZr" /></td>
					<td><ui:int maxlength="2" name="tlmzxJrjs" value="entity.tlmzxJrjs" /></td>
					<td><ui:int maxlength="2" name="tlmzxJrwc" value="entity.tlmzxJrwc" /></td>
					<td><ui:int maxlength="2" name="tlmzxJrww" value="entity.tlmzxJrww" /></td>
				</tr>
				 <tr>
					<th style="text-align: center; vertical-align: middle;" rowspan="3">化学<br />处理液</th>
					<th style="text-align: center; vertical-align: middle;">Cr</th>
					<td><ui:int maxlength="2" name="hxclyCr" value="entity.hxclyCr" /></td>
					<th style="text-align: center; vertical-align: middle;">Epon Flow</th>
					<td><ui:int maxlength="2" name="epflZr" value="entity.epflZr" /></td>
					<td><ui:int maxlength="2" name="epflJrjs" value="entity.epflJrjs" /></td>
					<td><ui:int maxlength="2" name="epflJrwc" value="entity.epflJrwc" /></td>
					<td><ui:int maxlength="2" name="epflJrww" value="entity.epflJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">PH</th>
					<td><ui:int maxlength="2" name="hxclyPh" value="entity.hxclyPh" /></td>
					<th style="text-align: center; vertical-align: middle;" >Smudge</th>
					<td><ui:int maxlength="2" name="smudZr" value="entity.smudZr" /></td>
					<td><ui:int maxlength="2" name="smudJrjs" value="entity.smudJrjs" /></td>
					<td><ui:int maxlength="2" name="smudJrwc" value="entity.smudJrwc" /></td>
					<td><ui:int maxlength="2" name="smudJrww" value="entity.smudJrww" /></td>
				</tr>
				<tr>
					<th style="text-align: center; vertical-align: middle;">Sludge</th>
					<td><ui:int maxlength="2" name="hxclySlud" value="entity.hxclySlud" /></td>
					<th style="text-align: center; vertical-align: middle;" colspan="5">&nbsp;</th>
				</tr>
			</table>
			</form>
	</ui:page>
	<ui:panel id="YcsxPanel" title="增加异常事项" popup="true" display="false" closable="true">
		<form name="YcsxForm" action="saveYcsx.do" method="post">
		<input type="hidden" name="pid" value="${entity.id }" />
		<div id="YcsxDiv" class="scroll" style="width:100%;height:350px;"></div>
		</form>
		<table id="ItemTblTmp" style="display: none">
			<tr>
				<td nowrap="nowrap" align="center" style="vertical-align: bottom;"><input type="checkbox" name="ids" /><input type="hidden" name="id" /></td>
				<td nowrap="nowrap" style="vertical-align: bottom;"><ui:input name="jbno" maxlength="7" onblur="getDh(this);" onkeydown="if(window.event.keyCode == 13) getDh(this);" /></td>
				<td nowrap="nowrap" style="vertical-align: bottom;"><sys:codeSelect name="bw" code="#SINO_FXS_BW" prop="nn:1;" headerText="" headerValue="" /></td>
				<td nowrap="nowrap" style="vertical-align: bottom;"><sys:codeSelect name="ycxm" code="#SINO_YCXM" prop="nn:1;" headerText="" headerValue="" /></td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">
				F <ui:number scale="4" precision="2" maxlength="5" name="ycfs" /> <ui:number scale="4" precision="2" maxlength="5" name="ycfc" /> <ui:number scale="4" precision="2" maxlength="5" name="ycfm" /><br />
				B <ui:number scale="4" precision="2" maxlength="5" name="ycbs" /> <ui:number scale="4" precision="2" maxlength="5" name="ycbc" /> <ui:number scale="4" precision="2" maxlength="5" name="ycbm" />
				</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">
				F <ui:number scale="4" precision="2" maxlength="5" name="fxfs" /> <ui:number scale="4" precision="2" maxlength="5" name="fxfc" /> <ui:number scale="4" precision="2" maxlength="5" name="fxfm" /><br />
				B <ui:number scale="4" precision="2" maxlength="5" name="fxbs" /> <ui:number scale="4" precision="2" maxlength="5" name="fxbc" /> <ui:number scale="4" precision="2" maxlength="5" name="fxbm" />
				</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
				<td nowrap="nowrap" style="vertical-align: bottom;">&nbsp;</td>
			</tr>
		</table>
	</ui:panel>
	</body>
</html>
