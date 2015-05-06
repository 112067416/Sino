<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<style type="text/css">
		form1 { margin: 0px; font-size: 14px; color:#99FFFF;background-color:#000000;}
		TABLE.form1 { border: 0px;}
		TABLE.form1 th { COLOR:#99FFFF; FONT-SIZE: 15px; HEIGHT: 18px; BORDER-COLLAPSE: collapse; WHITE-SPACE: nowrap; PADDING: 2px 5px; VERTICAL-ALIGN: bottom; TEXT-ALIGN: right; FONT-WEIGHT: normal;}
		TABLE.form1 td { COLOR:#FFFF99; FONT-SIZE: 15px; HEIGHT: 18px; BORDER-COLLAPSE: collapse; PADDING: 2px 2px; VERTICAL-ALIGN: bottom;}
		TABLE.form1 INPUT.text { FONT-SIZE:15px; BACKGROUND-COLOR : #FFFFFF; BORDER:0px; BORDER-BOTTOM: 1px solid #555555;}
		</style>
		<script type="text/javascript">
		<!--
		//检索
		function doQuery(el) {
			var vKey = el.value.replace(/^\s+|\s+$/,'');;
			if(vKey == null || vKey.length == 0) {
				alert("引用COIL / PILE No为空");
				el.focus();
				return;
			}
			var postContent = coco.parseParams([{name : "jbno",value: vKey}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					el.focus();
					return;
				}
				DataDiv.style.display = 'block';
				el.className = "form-readonly";
				el.readOnly = true;
				var oForm = document.forms["DataForm"];
				var obj = cocoform.fillResult(oForm, this.result);
				DhccSpan.innerHTML = (obj.dhcc != null ? obj.dhcc : ""); 
				var scbj = oForm.elements["scbj"].value;
				if(scbj != null && scbj != "0") ScbjDiv.style.display = "block";
				else ScbjDiv.style.display = "none";
			};
			ajax.post("load.do", postContent, "POST");
		}
		//返回
		function doReturn() {
			var obj = document.getElementById("jbno");
			obj.value = "";
			obj.className = "normal";
			obj.readOnly = false;
			obj.focus();
			DataDiv.style.display = 'none';
		}
		//删除
		function toDelete(form, oInput) {
			var jbno = form.elements["jbno"].value;
			if(jbno == null || jbno.length == 0) {
				alert("引用COIL / PILE No.为空");
				return;
			}
			var postContent = coco.parseParams([{name:"jbno",value:jbno}]);
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				doDelete(postContent);
			};
			ajax.post("checkDelXp.do", postContent);
		}
		// 删除
		function doDelete(postContent) {
			if(!window.confirm("是否确认删除?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				doReturn();
			};
			ajax.post("delXp.do", postContent);
		}
		//页面加载
		function doLoad() {
			var el = document.getElementById("jbno");
			el.focus();
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
		<ui:page title="现品情报删除">
		<table width="100%" class="form">
			<colgroup><col width="15%" /><col width="85%" /></colgroup>
			<tr>
				<th style="text-align: left;">引用COIL / PILE No.</th>
				<td><ui:input id="jbno" name="jbno" maxlength="11" onkeydown="if(window.event.keyCode == 13) doQuery(this);"/></td>
			</tr>
		</table>
		<div id="DataDiv" style="display: none;">
		<fieldset class="group"><legend>现品信息</legend>
		<form name="DataForm" xu.ajax="" xu.r="" xu.color="1" method="post" action="delXp.do">
		<table class="form1" width="100%" align="center">
			<colgroup><col width="13%" /><col width="20%" /><col width="13%" /><col width="20%" /><col width="13%" /><col width="21%" /></colgroup>
			<tr>
				<td colspan="6">
					<table width="100%" align="center" class="form1" >
					<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="6%" /><col width="8%" /><col width="10%" /><col width="6%" /><col width="10%" /><col width="10%" /><col width="10%" /></colgroup>
					<tr>
						<th>订货NO.行号</th>
						<td ><ui:input name="dhno" readonly="true" maxlength="9" /><input type="hidden" name="jbno" /><input type="hidden" name="xpzk" /></td>
						<th>现品状态</th>
						<td><ui:input name="statName" readonly="true" maxlength="8"/></td>
						<th>现品状况</th>
						<td><ui:input name="xpzkName" readonly="true" maxlength="8"/></td>
						<th>是否已出货</th>
						<td><ui:input name="ched" readonly="true" maxlength="1"/></td>
						<th>装箱指示书</th>
						<td><ui:input name="chno" readonly="true" maxlength="10"/></td>
						<td style="text-align: left;"><input type="hidden" name="scbj" /><div id="ScbjDiv" style="color: red; display: none;">Delete</div></td>
					</tr>
					<tr>
						<th>
							现品尺寸
						</th>
						<td colspan="7">
							<div align="left" style="width: 100%">
								<span style="padding-right: 10px;">厚<ui:number name="xpho" required="true" scale="4" precision="3"/></span>
								<span style="padding-right: 10px;">宽<ui:number name="xpkn" required="true" scale="6" precision="2"/></span>
								<span style="padding-right: 10px;">长(Sheet)<ui:number name="xpcn" scale="6" precision="2"/></span>
								<span style="padding-right: 10px;">长(Coil)<ui:int name="jbcn" scale="6" /></span>
							</div>
						</td>
						<th>
							订货尺寸
						</th>
						<td colspan="2"><span id="DhccSpan"></span></td>
					</tr>
					<tr>
						<th>
							现品重量
						</th>
						<td colspan="10">
							<div align="left" style="width: 100%">
								<span style="padding-right: 15px;">计算重量<ui:int name="jszl" maxlength="8"/></span>
								<span style="padding-right: 15px;">实际重量<ui:int name="sjzl" maxlength="8"/></span>
								<span style="padding-right: 15px;">净重量<ui:int name="jnzl" maxlength="8" /></span>
								<span style="padding-right: 15px;">&nbsp;&nbsp;&nbsp;毛重量<ui:int name="mazl" maxlength="8"/></span>
								<span style="padding-right: 15px;">制品重量<ui:int name="zpzl" maxlength="8"/></span>
							</div>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<th>等级</th>
				<td>
					<ui:input required="true" name="deng" maxlength="3"/>
				</td>
				<th>产量</th>
				<td>
					<ui:input name="chan" maxlength="1"/>
				</td>
				<th>垫木重量</th>
				<td>
					<ui:int name="dmzl" maxlength="8"/>
				</td>
			</tr>
			<tr>
				<th>制造商代码</th>
				<td>
					<ui:input required="true" name="zzsd" maxlength="1"/>
				</td>
				<th>包装材料重量</th>
				<td>
					<ui:int name="bzcl" maxlength="8"/>
				</td>
				<th>PILE区分</th>
				<td>
					<ui:input name="plqf" maxlength="1"/>
				</td>
			</tr>
			<tr>
				<th>运用规格</th>
				<td>
					<ui:input required="true" name="yuny" maxlength="6" onblur="getGgno(this);" />
				</td>
				<th>出口包装NO</th>
				<td>
					<ui:int name="ckno" maxlength="8"/>
				</td>
				<th>钢种类型</th>
				<td>
					<ui:input required="true" name="gzlx" maxlength="4"/>
				</td>
			</tr>
			<tr>
				<th>包装张数</th>
				<td>
					<ui:int name="zshu" maxlength="8"/>
				</td>
				<th>再剪边标记</th>
				<td>
					<ui:input name="zjbb" maxlength="1"/>
				</td>
				<th>内径代码</th>
				<td>
					<ui:input name="njno" maxlength="1"/>
				</td>
			</tr>
			<tr>
				<th>表面</th>
				<td>
					<ui:input required="true" name="face" maxlength="2"/>
				</td>
				<th>品种</th>
				<td>
					<ui:input required="true" name="pzno" maxlength="2"/>
				</td>
				<th>涂油种类</th>
				<td>
					<ui:input name="ytyp" maxlength="1"/>
				</td>
			</tr>
			<tr>
				<th>规格代码</th>
				<td>
					<ui:input required="true" name="ggno" maxlength="4" onblur="getYuny(this);" />
				</td>
				<th>附着量</th>
				<td >
					正面<input type="hidden" name="fudw" /><ui:input name="fuzm" maxlength="3"/>
					反面<ui:input name="fufm" maxlength="3"/>
				</td>
				 <th>实绩品种等级</th>
				<td >
					<ui:input name="spdj" maxlength="1"/>
				</td>
			</tr>
			<tr>
				<th>作业停止标记</th>
				<td>
					<ui:input name="ztbj" maxlength="2"/>
				</td>
				<th>实绩附着量</th>
				<td colspan="3">
					正面<ui:number name="sczm" scale="4" precision="2"/>
					反面<ui:number name="scfm" scale="4" precision="2"/>
				</td>
			</tr>
			<tr>
				<th>差厚镀锡标记</th>
				<td>
					<ui:input name="chdx" maxlength="2"/>
				</td>
				<th>强制出货标记</th>
				<td>
					<ui:input name="qzch" maxlength="1"/>
				</td>
				<th>捆包形式</th>
				<td>
					<ui:input name="kbao" maxlength="3"/>
				</td>
			</tr>
			<tr>
				<th>硬度</th>
				<td>
					<ui:int name="ying" maxlength="3"/>
				</td>
				<th>锯齿形式</th>
				<td>
					<ui:input name="jcxs" maxlength="4"/>
				</td>
				<th>标签换贴标记</th>
				<td>
					<ui:input name="bqht" maxlength="1"/>
				</td>
			</tr>
			<tr>
				<th>业连NO</th>
				<td colspan="4">
				 <ui:input maxlength="50" id="ylno" readonly="true" name="ylno" />
				 <span class="link" style="color:#00CC00" onclick="doYlno();">(点击修改)</span>
				 </td>
				 <td><input type="button" class="button" value="确认删除" onclick="toDelete(this.form, this);" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="返   回" onclick="doReturn();" /></td>
			</tr>
			</table>
		</form>
		</fieldset>
		</div>
		</ui:page>
	</body>
</html>