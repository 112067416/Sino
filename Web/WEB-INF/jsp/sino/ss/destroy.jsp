<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--

		//检索待消灭的包
		function fetchXm(jbno) {
			var form = document.forms["DataForm"];
			if(jbno == "") {
				form.elements["size"].value = "";
				form.elements["deng"].value = "";
				form.elements["zshu"].value = "";
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return false;
				}
				cocoform.fillResult(form, this.result);
			};
			ajax.post("fetchXm.do", coco.parseParam("jbno", jbno));
		}

		//PILE消除确认_dj
		function doDestroy() {
			var form = document.forms["DataForm"];
			cocoform.submit(form, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return false;
				}
				alert("Pile消灭完毕");
				cocoform.clear(form);
			});
		}
		
		//-->
		</script>
	</head>
	<body>
		<ui:page title="PILE消除">
		<form name="DataForm" action="destroy.do" method="post">
		<table width="98%" align="center" class="form" style="margin-top: 20px;">
			<colgroup>
				<col width="15%" /><col width="20%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col />
			</colgroup>
			<tr>
				<th>PILE No. </th>
				<td><ui:input name="jbno" onblur="fetchXm(this.value)" maxlength="11" required="true"/></td>
				<th>制品尺寸</th>
				<td><ui:input name="size" maxlength="20" readonly="true" /></td>
				<th>等级</th>
				<td><ui:input name="deng" maxlength="3" readonly="true" /></td>
				<th>剩余枚数</th>
				<td><ui:input name="zshu" maxlength="4" readonly="true" /></td>
			</tr>
		</table>
		<table id="DataTbl" width="98%" align="center" class="form" style="margin-top: 20px;">
			<colgroup>
				<col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col />
			</colgroup>
			<tr>
				<th>1. 缺陷</th>
				<td><ui:input name="qxdm1" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng1" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu1" maxlength="4" /></td>
				<th>2. 缺陷</th>
				<td><ui:input name="qxdm2" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng2" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu2" maxlength="4" /></td>
			</tr>
			<tr>
				<th>3. 缺陷</th>
				<td><ui:input name="qxdm3" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng3" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu3" maxlength="4" /></td>
				<th>4. 缺陷</th>
				<td><ui:input name="qxdm4" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng4" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu4" maxlength="4" /></td>
			</tr>
			<tr>
				<th>5. 缺陷</th>
				<td><ui:input name="qxdm5" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng5" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu5" maxlength="4" /></td>
				<th>6. 缺陷</th>
				<td><ui:input name="qxdm6" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng6" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu6" maxlength="4" /></td>
			</tr>
			<tr>
				<th>7. 缺陷</th>
				<td><ui:input name="qxdm7" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng7" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu7" maxlength="4" /></td>
				<th>8. 缺陷</th>
				<td><ui:input name="qxdm8" maxlength="2" /></td>
				<th>G</th>
				<td><ui:input name="deng8" maxlength="1" /></td>
				<th>枚数</th>
				<td><ui:int name="zshu8" maxlength="4" /></td>
			</tr>
		</table>
		</form>
		<div class="submit" style="margin-top: 15px;">
			<input type="button" class="button" value="确认消灭(S)" accesskey="s" onclick="doDestroy();" />
		</div>
		<br />
		</ui:page>
	<br/>
	</body>
</html>