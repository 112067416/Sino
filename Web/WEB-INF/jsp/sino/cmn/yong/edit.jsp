<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><%@
taglib prefix="sino" uri="/sino" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<style type="text/css">
		TABLE.form1 { border: 0px;}
		TABLE.form1 th { COLOR:#99FFFF; FONT-SIZE: 15px; HEIGHT: 20px; BORDER-COLLAPSE: collapse; WHITE-SPACE: nowrap; PADDING: 2px 5px; VERTICAL-ALIGN: middle; TEXT-ALIGN: left; FONT-WEIGHT: normal;}
		TABLE.form1 td { COLOR:#FFFF99; FONT-SIZE:  14px; HEIGHT: 20px; BORDER-COLLAPSE: collapse; PADDING: 2px 2px; text-align: left; VERTICAL-ALIGN: middle;}
		TABLE.form1 INPUT.text { FONT-SIZE:22px; BACKGROUND-COLOR : #FFFFFF; BORDER:0px; BORDER-BOTTOM: 1px solid #555555;}
		</style>
		<SCRIPT type="text/javascript">
		<!--
            //提交操作
			function doSubmit(oInput) {
				var form = oInput.form;
				cocoform.submit(form, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						parent.cocoframe.doRefresh();
						return;
					}, "是否确定保存?", null, oInput);
			}
			//获得商社名称
			function getSsnm(oInput) {
				var form = oInput.form;
				var ssnm = form.elements["ssnm"];
				ssnm.value = "";
				var ssno = form.elements["ssno"];
				if(ssno.value == null || ssno.value.length == 0) return;
				var code = coco.getCode("010", ssno.value);
				if(code == null) {
					alert("商社不存在");
					ssno.value = "";
					ssno.focus();
					return;
				}
				ssnm.value = code.value;
			}

			//验证用户代码
			function checkUser(el) {
				var user = el.value.replace(/^\s+|\s+$/gi,'');
				if(user == null || user.length == 0) return;
				var postContent = coco.parseParam("user", user);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						el.value = "";
						el.focus();
						return;
					}
				};
				ajax.post("checkUser.do", postContent);
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="编辑用户信息">
		<form name="DataForm" method="post" action="save.do" >
		<table width="100%" class="form1">
			<colgroup><col width="9%" /><col width="19%" /><col width="9%" /><col width="19%" /><col width="9%" /><col width="35%" /></colgroup>
			<tr>
				<th>所属区域</th>
				<td><ui:select name="region" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_REGION'" headerText="" headerValue="" value="entity.region" /></td>
				<th style="color: red;"><ui:radiolist name="valid" list="'Y':'有效','N':'无效'" value="valid" ></ui:radiolist></th>
				<th colspan="2">SJM业务负责人&nbsp;&nbsp;&nbsp;<ui:input name="ddnm" maxlength="8" value="entity.ddnm" /></th>
				<td style="text-align: center; "><input type="button" value="保 存" onclick="doSubmit(this);" class="button" /></td>
			</tr>
			<tr>
				<th>用户代码</th>
				<th><ui:input name="user" required="true" maxlength="4" value="entity.user" onchange="checkUser(this);" /></th>
				<th>用户名称</th>
				<th colspan="3"><ui:input name="name" maxlength="40" value="entity.name" /></th>
			</tr>
			<tr>
				<th>用户中文名</th>
				<td colspan="3"><ui:input name="rsv1" maxlength="40" value="entity.rsv1" /></td>
				<th>发票用户名称</th><td><ui:input name="fpkh" maxlength="30" value="entity.fpkh" /></td>
			</tr>
			<tr>
				<th>用户略称</th>
				<td><ui:input name="abbr" maxlength="16" value="entity.abbr" /></td>
				<th>所在地</th>
				<td colspan="3"><ui:input name="addr" maxlength="65" value="entity.addr" /></td>
			</tr>
			<tr>
				<th>国名</th>
				<td><ui:input name="guom" maxlength="15" value="entity.guom" /></td>
				<th>商社代码</th>
				<td><ui:input name="ssno" maxlength="3" onblur="getSsnm(this);" value="entity.ssno" /></td>
				<th>商社名称</th>
				<td><ui:input name="ssnm" maxlength="35" readonly="true" value="entity.ssnm" /></td>
			</tr>
			<tr>
				<th>内外销</th>
				<td><ui:select name="nwai" list="'1':'国内','2':'国外'" value="entity.nwai" /></td>
				<th>信用额度</th>
				<td><ui:number name="xyed" scale="12" precision="2" value="entity.xyed" /></td>
				<th>检查证明书份数</th>
				<td><ui:number name="jcha" maxlength="2" precision="0" value="entity.jcha" /> </td>
			</tr>
			<tr>
				<th>资金组成</th>
				<td colspan="4"><ui:input name="fond" maxlength="40" value="entity.fond" /></td>
				<th>成立日期&nbsp;&nbsp;&nbsp;<ui:input name="fdt" maxlength="14" value="entity.fdt" />&nbsp;&nbsp;&nbsp;从业人员&nbsp;&nbsp;&nbsp;<ui:int name="staffs" maxlength="5" value="entity.staffs" /></th>
			</tr>
			<tr>
				<th>商议形式</th><th colspan="5"><ui:input name="chat" maxlength="10" value="entity.chat" />&nbsp;&nbsp;&nbsp;经销商&nbsp;&nbsp;&nbsp;<ui:input name="dealer" maxlength="10" value="entity.dealer" />&nbsp;&nbsp;&nbsp;付款方式&nbsp;&nbsp;&nbsp;<ui:input name="payment" maxlength="25" value="entity.payment" />&nbsp;&nbsp;&nbsp;</th>
			</tr>
			<tr>
				<th>联系人</th><th colspan="5"><ui:input name="link1" maxlength="10" value="entity.link1" />&nbsp;&nbsp;&nbsp;职务&nbsp;&nbsp;&nbsp;<ui:input name="job1" maxlength="10" value="entity.job1" />&nbsp;&nbsp;&nbsp;手机号&nbsp;&nbsp;&nbsp;<ui:input name="mobile1" maxlength="11" value="entity.mobile1" />&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp;<ui:input name="tel1" maxlength="18" value="entity.tel1" />&nbsp;&nbsp;&nbsp;传真&nbsp;&nbsp;&nbsp;<ui:input name="fax1" maxlength="18" value="entity.fax1" /></th>
			</tr>
			<tr>
				<th>采购员1</th><th colspan="5"><ui:input name="link2" maxlength="10" value="entity.link2" />&nbsp;&nbsp;&nbsp;职务&nbsp;&nbsp;&nbsp;<ui:input name="job2" maxlength="10" value="entity.job2" />&nbsp;&nbsp;&nbsp;手机号&nbsp;&nbsp;&nbsp;<ui:input name="mobile2" maxlength="11" value="entity.mobile2" />&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp;<ui:input name="tel2" maxlength="18" value="entity.tel2" />&nbsp;&nbsp;&nbsp;传真&nbsp;&nbsp;&nbsp;<ui:input name="fax2" maxlength="18" value="entity.fax2" /></th>
			</tr>
			<tr>
				<th>采购员2</th><th colspan="5"><ui:input name="link3" maxlength="10" value="entity.link3" />&nbsp;&nbsp;&nbsp;职务&nbsp;&nbsp;&nbsp;<ui:input name="job3" maxlength="10" value="entity.job3" />&nbsp;&nbsp;&nbsp;手机号&nbsp;&nbsp;&nbsp;<ui:input name="mobile3" maxlength="11" value="entity.mobile3" />&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp;<ui:input name="tel3" maxlength="18" value="entity.tel3" />&nbsp;&nbsp;&nbsp;传真&nbsp;&nbsp;&nbsp;<ui:input name="fax3" maxlength="18" value="entity.fax3" /></th>
			</tr>
			<tr>
				<th>总销售量</th><th colspan="5"><ui:int name="axsl" maxlength="10" value="entity.axsl" />&nbsp;&nbsp;&nbsp;一季度&nbsp;&nbsp;&nbsp;<ui:int name="oxsl" maxlength="10" value="entity.oxsl" />&nbsp;&nbsp;&nbsp;二季度&nbsp;&nbsp;&nbsp;<ui:int name="sxsl" maxlength="10" value="entity.sxsl" />&nbsp;&nbsp;&nbsp;三季度&nbsp;&nbsp;&nbsp;<ui:int name="txsl" maxlength="10" value="entity.txsl" />&nbsp;&nbsp;&nbsp;四季度&nbsp;&nbsp;&nbsp;<ui:int name="fxsl" maxlength="10" value="entity.fxsl" /></th>
			</tr>
			<tr><th>设备概要</th><td colspan="5"><ui:textarea name="sbjy" cols="100" rows="3" value="entity.sbjy" /></td></tr>
			<tr><th>备注</th><td colspan="5"><ui:textarea name="supplier" cols="100" rows="3" value="entity.supplier" /></td></tr>
			<tr><th>历史及最近消息</th><td colspan="5"><ui:textarea name="news" cols="100" rows="10" value="entity.news" /></td></tr>
		</table>
		</form>
		</ui:page>
	</body>
</html>
