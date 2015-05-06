<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>昨天出货实绩单</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["运费设置", "settings.gif", "toSetting()"]]);
		// 设置客户运费
		function toSetting(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "key1");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				try {
					var form = document.forms["DataForm"];
					cocoform.clear(form);
					cocoform.fillResult(form, this.result);
					var e = form.elements["ysfs"];
					displayYsfs(e.value);
					coco.showPage("YsfzArea", {center : true, top : 50, width : "70%" });
				} catch (e) {
					alert("系统出错:\n" + e.description);
				}
			};
			ajax.post("load.do", "id="+key1);
		}
		//
		function changeYsfs(obj,e) {
			var ysnm = obj.options[obj.selectedIndex].text;
			var oForm = document.forms["DataForm"];
			oForm.elements[e].value = ysnm;
			displayYsfs(obj.value);
		}
		//
		function displayYsfs(v) {
			var hghyDiv = document.getElementById("HghyDiv");
			var shhyDiv = document.getElementById("ShhyDiv");
			var glysDiv = document.getElementById("GlysDiv");
			var tlysDiv = document.getElementById("TlysDiv");
			var ckDiv = document.getElementById("CkDiv");
			var ztDiv = document.getElementById("ZtDiv");
			switch (v) {
			case '' :  hghyDiv.style.display = "none"; glysDiv.style.display = "none"; tlysDiv.style.display = "none"; ckDiv.style.display = "none";shhyDiv.style.display = "none";ztDiv.style.display = "none"; break;
			case '1' : hghyDiv.style.display = "block"; glysDiv.style.display = "none"; tlysDiv.style.display = "none"; ckDiv.style.display = "none";shhyDiv.style.display = "none";ztDiv.style.display = "none"; break;
			case '2' :  hghyDiv.style.display = "none"; glysDiv.style.display = "none"; tlysDiv.style.display = "none"; ckDiv.style.display = "none";shhyDiv.style.display = "block";ztDiv.style.display = "none"; break;
			case '3' : hghyDiv.style.display = "none"; glysDiv.style.display = "block"; tlysDiv.style.display = "none"; ckDiv.style.display = "none";shhyDiv.style.display = "none";ztDiv.style.display = "none"; break;
			case '4' : hghyDiv.style.display = "none"; glysDiv.style.display = "none"; tlysDiv.style.display = "block"; ckDiv.style.display = "none";shhyDiv.style.display = "none";ztDiv.style.display = "none"; break;
			case '5' : hghyDiv.style.display = "none"; glysDiv.style.display = "none"; tlysDiv.style.display = "none"; ckDiv.style.display = "block";shhyDiv.style.display = "none";ztDiv.style.display = "none"; break;
			case '6' :  hghyDiv.style.display = "none"; glysDiv.style.display = "none"; tlysDiv.style.display = "none"; ckDiv.style.display = "none";shhyDiv.style.display = "none";ztDiv.style.display = "block"; break;
			}
		}
		//
		function doQuery() {
			var qForm = document.forms["PageForm_page"];
			var chriS = qForm.elements["chriS"].value;
			if(chriS == null || chriS == "") {
				alert("出货日期不能为空");
				return;
			}
			cocopage.query();
		}
		//提交
		function doSubmit(oInput) {
			if(!confirm("是否确定保存?")) return;
			cocoform.submit(oInput,function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				} 
				alert("保存成功");
				coco.hidePage('YsfzArea');
				cocopage.refresh();
			});
		}

		//
		function getYfdj(obj, e1, e2, djdw,ld) {
			var oForm = document.forms["DataForm"];
			var v = obj.value;
			var ysnm = "";
			if(v != null && v.length > 0) {
				ysnm = obj.options[obj.selectedIndex].text;
			}
			oForm.elements[e1].value = ysnm;
			var ysfs = oForm.elements["ysfs"].value;
			var id = oForm.elements["id"].value;
			var ajax = new Cocoajax();
			var postContent = coco.parseParams([{name:"ysfs", value:ysfs},{name:"id", value:id},{name:"ysnm",value:ysnm},{name:"djdw",value:djdw},{name:"ld",value:ld}]);
			ajax.callback = function() {
				if(this.code <= 0) {
					oForm.elements[e2].value = "";
					return;
				}
				oForm.elements[e2].value = this.msg;
			};
			ajax.post("getYfdj.do", postContent);
		}

		//打印操作
		function doPrint() {
			var form = document.forms["PageForm_page"];
			var chriS = form.elements["chriS"].value;
			var chriE = form.elements["chriE"].value;
			if(chriS == null || chriS.length == 0) {
				alert("出货日期不能为空");
				return;
			}
			var postContent = coco.parseParams([{name:"chriS",value:chriS},{name:"chriE",value:chriE}]);
			if(!confirm("是否确定打印?")) return;
			document.getElementById("PrintFrame").src = path + "/sino/dy/chsjd.do?"+postContent;
		}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="昨天出货实绩单">
		<f:page action="index.do" file="list.jsp">
			<table width="100%" class="form">
				<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="50%" /></colgroup>
				<tr>
					<th style="text-align: left;">出货日期</th>
					<td><ui:date name="chriS" cssClass="normal" value="page.chriS" required="true" onchange="cocopage.query();" />至<ui:date name="chriE" value="page.chriE" onchange="cocopage.query();"  /></td>
					<th style="text-align: left;">运输行</th>
					<td><ui:select name="ysgs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='021' and VALID_=1" headerText="请选择" headerValue="" onchange="cocopage.query();" /></td>
				</tr>
			</table>
			<div class="submit"> <input type="button" class="button" value="查 询" onclick="cocopage.query();" />  <input type="button" class="button" value="打 印" onclick="doPrint();" /></div>	
		</f:page>
	</ui:page>
	<ui:panel id="YsfzArea" title="运费设置" popup="true" display="false" closable="true">
	<form name="DataForm" method="post" action="save.do" >
		<input type="hidden" name="id" />
		<table width="100%" class="form">
			<colgroup><col width="10%" /><col width="90%" /></colgroup>
			<tr>
				<td>
					<table>
						<tr>
							<th style="text-align: left;">运输方式</th>
							<td><ui:select name="ysfs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_YSFS'" headerText="请选择" headerValue="" onchange="changeYsfs(this,'ysfm')" /><input type="hidden" name="ysfm" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="HghyDiv" style="display: none;" >
						<fieldset class="group"><legend>货柜海运</legend>
							<table width="100%" height="100%">
								<colgroup><col width="30%" /><col width="40%" /><col width="40%" /></colgroup>
								<tr style="text-align: left;">
									<th style="text-align: right;">中日达到起始港 (RMB/吨)</th>
									<td><ui:select name="qghhno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_DQSG'" headerText="请选择" headerValue="" onchange="getYfdj(this,'qghhnm','qghhdj','RMB/吨','中日达到起始港')" /><input type="hidden" name="qghhnm" /></td>
									<td><ui:number name="qghhdj" cssClass="normal" title="中日达到起始港每吨单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">堆场 (RMB/柜)</th>
									<td><ui:select name="dchhno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_DC'" headerText="请选择" headerValue="" onchange="getYfdj(this,'dchhnm','dchhdj','RMB/柜','堆场')" /><input type="hidden" name="dchhnm" /></td>
									<td><ui:number name="dchy" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="dchhdj" cssClass="normal" title="堆场单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">船运到客户 (RMB/柜)</th>
									<td><ui:select name="cyhhno1" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_HGHY_CY'" headerText="请选择" headerValue="" onchange="getYfdj(this,'cyhhnm1','cyhhdj1','RMB/柜','船运到客户')" /><input type="hidden" name="cyhhnm1" /></td>
									<td><ui:number name="cyhy1" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="cyhhdj1" cssClass="normal" title="船运单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">船运 (RMB/柜)</th>
									<td><ui:select name="cyhhno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_HGHY_CY'" headerText="请选择" headerValue="" onchange="getYfdj(this,'cyhhnm','cyhhdj','RMB/柜','船运')" /><input type="hidden" name="cyhhnm" /></td>
									<td><ui:number name="cyhy" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="cyhhdj" cssClass="normal" title="船运单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">目的港到客户 (RMB/吨)</th>
									<td><ui:select name="khhhno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_HGHY_MDG'" headerText="请选择" headerValue="" onchange="getYfdj(this,'khhhnm','khhhdj','RMB/吨','目的港到客户')" /><input type="hidden" name="khhhnm" /></td>
									<td><ui:number name="khhhdj" cssClass="normal" title="目的港到客户单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">目的港到客户 (RMB/柜)</th>
									<td><ui:select name="khhhno1" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_HGHY_MDG'" headerText="请选择" headerValue="" onchange="getYfdj(this,'khhhnm1','khhhdj1','RMB/柜','目的港到客户')" /><input type="hidden" name="khhhnm1" /></td>
									<td><ui:number name="khhh1" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="khhhdj1" cssClass="normal" title="船运单价" maxlength="8" /></td>
								</tr>
							</table>
						</fieldset>
					</div>
					<div id="ShhyDiv" style="display: none;" >
						<fieldset class="group"><legend>散货海运</legend>
							<table width="100%" height="100%">
								<colgroup><col width="30%" /><col width="50%" /><col width="30%" /></colgroup>
								<tr style="text-align: left;">
									<th style="text-align: right;">中日达到目的港 (RMB/吨)</th>
									<td><ui:select name="qgshno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_SHHY'" headerText="请选择" headerValue="" onchange="getYfdj(this,'qgshnm','qgshdj','RMB/吨','中日达到目的港')" /><input type="hidden" name="qgshnm" /></td>
									<td><ui:number name="qgshdj" cssClass="normal" title="中日达到目的港单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">目的港到客户 (RMB/吨)</th>
									<td><ui:select name="khshno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_SHHY'" headerText="请选择" headerValue="" onchange="getYfdj(this,'khshnm','khshdj','RMB/吨','目的港到客户')" /><input type="hidden" name="khshnm" /></td>
									<td><ui:number name="khshdj" cssClass="normal" title="目的港到客户单价" maxlength="8" /></td>
								</tr>
							</table>
						</fieldset>
					</div>
					<div id="GlysDiv" style="display: none;" >
						<fieldset class="group"><legend>公路运输</legend>
							<table width="100%" height="100%">
								<colgroup><col width="30%" /><col width="50%" /><col width="30%" /></colgroup>
								<tr style="text-align: left;">
									<th style="text-align: right;">中日达到客户(RMB/吨)</th>
									<td><ui:select name="khglno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_DQSG'" headerText="请选择" headerValue="" onchange="getYfdj(this,'khglnm','khgldj','RMB/吨','中日达到客户')" /><input type="hidden" name="khglnm" /></td>
									<td><ui:number name="khgldj" cssClass="normal" title="中日达到客户单价" maxlength="8" /></td>
								</tr>
							</table>
						</fieldset>
					</div>
					<div id="TlysDiv" style="display: none;" >
						<fieldset class="group"><legend>铁路运输</legend>
							<table width="100%" height="100%">
								<colgroup><col width="30%" /><col width="50%" /><col width="30%" /></colgroup>
								<tr style="text-align: left;">
									<th style="text-align: right;">中日达到终点站 (RMB/吨)</th>
									<td><ui:select name="zdtlno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_TLYS'" headerText="请选择" headerValue="" onchange="getYfdj(this,'zdtlnm','zdtldj','RMB/吨','中日达到终点站')" /><input type="hidden" name="zdtlnm" /></td>
									<td><ui:number name="zdtldj" cssClass="normal" title="中日达到终点站单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">终点站到客户 (RMB/吨)</th>
									<td><ui:select name="khtlno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_TLYS'" headerText="请选择" headerValue="" onchange="getYfdj(this,'khtlnm','khtldj','RMB/吨','终点站到客户')" /><input type="hidden" name="khtlnm" /></td>
									<td><ui:number name="khtldj" cssClass="normal" title="终点站到客户单价" maxlength="8" /></td>
								</tr>
							</table>
						</fieldset>
					</div>
					<div id="CkDiv" style="display: none;" >
						<fieldset class="group"><legend>出口</legend>
							<table width="100%" height="100%">
								<colgroup><col width="30%" /><col width="40%" /><col width="40%" /></colgroup>
								<tr style="text-align: left;">
									<th style="text-align: right;">中日达到起始港 (RMB/吨)</th>
									<td><ui:select name="qgckno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_DQSG'" headerText="请选择" headerValue="" onchange="getYfdj(this,'qgcknm','qgckdj','RMB/吨','中日达到起始港')" /><input type="hidden" name="qgcknm" /></td>
									<td><ui:number name="qgckdj" cssClass="normal" title="中日达到起始港单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">堆场 (RMB/柜)</th>
									<td><ui:select name="dcckno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_DC'" headerText="请选择" headerValue="" onchange="getYfdj(this,'dccknm','dcckdj','RMB/柜','堆场')" /><input type="hidden" name="dccknm" /></td>
									<td><ui:number name="dcck" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="dcckdj" cssClass="normal" title="堆场单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">船运 (USD/柜)</th>
									<td><ui:select name="cyckno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_CK_CY'" headerText="请选择" headerValue="" onchange="getYfdj(this,'cycknm','cyckdj','USD/柜','船运')" /><input type="hidden" name="cycknm" /></td>
									<td><ui:number name="cyck" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="cyckdj" cssClass="normal" title="船运单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">船运 (RMB/柜)</th>
									<td><ui:select name="cyckno1" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_CK_CY'" headerText="请选择" headerValue="" onchange="getYfdj(this,'cycknm1','cyckdj1','RMB/柜','船运')" /><input type="hidden" name="cycknm1" /></td>
									<td><ui:number name="cyck1" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="cyckdj1" cssClass="normal" title="船运单价" maxlength="8" /></td>
								</tr>
								<tr style="text-align: left;">
									<th style="text-align: right;">目的港到客户 (USD/柜)</th>
									<td><ui:select name="khckno" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_CK_MDG'" headerText="请选择" headerValue="" onchange="getYfdj(this,'khcknm','khckdj','USD/柜','目的港到客户')" /><input type="hidden" name="khcknm" /></td>
									<td><ui:number name="mdck" cssClass="normal" title="柜数" scale="3" precision="1" maxlength="4" positive="1" />×<ui:number name="khckdj" cssClass="normal" title="目的港到客户单价" maxlength="8" /></td>
								</tr>
							</table>
						</fieldset>
					</div>
					<div id="ZtDiv" style="display: none;" >
						<fieldset class="group"><legend>自提</legend>
							<table width="100%" height="100%">
								<colgroup><col width="30%" /><col width="50%" /><col width="30%" /></colgroup>
								<tr style="text-align: left;">
									<th style="text-align: right;">中日达到客户(RMB/吨)</th>
									<td><ui:number name="ztdj" cssClass="normal" title="中日达到客户单价" maxlength="8" /></td>
								</tr>
							</table>
						</fieldset>
					</div>
				</td>
			</tr>
		</table>
		<div class="opt-btm"><input type="button" class="button" value="保 存" onclick="doSubmit(this)" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('YsfzArea');" /></div>
	</form>
	</ui:panel>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>