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
		var tableForm = new TableForm("ItemTbl");
		//检索
		function doQuery(el) {
			if(DataDiv.style.display == 'block') return;
			var jbno = el.value.replace(/^\s+|\s+$/,'');
			if(jbno == null || jbno.length == 0) {
				alert("COIL / PILE No为空");
				el.focus();
				return;
			}
			var postContent = coco.parseParams([{name : "jbno",value: jbno}]);
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
		//保存
		function doSave(form, oInput) {
			var ajax = new Cocoajax();
			var postContent = cocoform.postContent(form);
			ajax.oInput = oInput;
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					var el = form.elements[Math.abs(this.code)];
					if(el != null) el.focus();
					return;
				}
				cocoform.submit(form, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("保存成功");
					doReturn();
				}, "是否确定保存?") ;
			};
			ajax.post("checkXpxx.do", postContent);
		}
		// 弹出业连NO设定-弹出设定界面
		function doYlno() {
			tableForm.removeAll();
			var oForm = document.forms["DataForm"];
			var content = oForm.elements["ylno"].value;
			//判断所选的行的作业停止标记 是否是一样的，如果是一样的就带
			if(content != null && content.length > 0) {
				var ylnos = content.split(",");
				for(var i = 0; i < ylnos.length; i++){
					var row = tableForm.insertEmptyRow('ItemTblTmp');
					row.cells[0].childNodes[0].value = "";
					row.cells[1].childNodes[0].value = ylnos[i].substring(0,1);
					row.cells[2].childNodes[0].value = ylnos[i].substring(2);
				}
			}
			coco.showPage("YlArea",{center:true,top:180,width:"30%"});
		}
		//设置业连NO
		function setYlno(){
			var form1 = document.forms["YlForm"];
			if(!cocoform.verify(form1)) return;
			var ele1 = form1.elements["ylFlag"];
			var ele2 = form1.elements["ylno"];
			var ylno;
			if(ele1 != null && ele1.length == null ) {
				if(!checkYlno(ele2.value) ) {
					alert("业连No."+ele2.value+"在资料室中不存在");
					ele2.focus();
					return;
				}
				ylno = ele1.value + "-" + ele2.value;
			} else if(ele1 != null) {
				for(var i = 0; i < ele1.length; i++) {
					if(!checkYlno(ele2[i].value) ) {
						alert("业连No."+ele2[i].value+"在资料室中不存在");
						ele2[i].focus();
						return;
					}
					if(ylno == null) {
						ylno = ele1[i].value + "-" + ele2[i].value;
						continue;
					}
					ylno = ylno + "," + ele1[i].value + "-" + ele2[i].value;
				}
			} else {
				ylno = "";
			}
			if(!confirm("是否确定修改？"))return;
			var form2 = document.forms["DataForm"];
			form2.elements["ylno"].value = ylno;
			coco.hidePage('YlArea');
		}
		//判断业连No.在资料室中是否存在
		function checkYlno(ylno) {
			var ajax = new Cocoajax();
			var postContent = coco.parseParams([{name:"name",value:ylno}]);
			ajax.async = false;
			ajax.post(path + "/sino/cmn/inform/isExisted.do", postContent);
			if(ajax.code <= 0) {
				return false;
			}
			return true;
		}
		//别纸KpNo标示位
		function fillFlag(obj) {
			if(obj.value == null || obj.value.length == 0) {
				return;
			}
			if(!(obj.value == "1" || obj.value == "2" || obj.value == "3")) {
				alert("业连NO标示位只能是1、2或3");
				obj.value = "";
				obj.focus();
				return;
			}
		}
		//页面加载
		function doLoad() {
			var el = document.getElementById("jbno");
			el.focus();
		}
		function getGgno(el) {
			var v = el.value;
			if(v == null || v.length == 0) return;
			var obj = coco.getCodeByValue("019", v);
			if(obj == null) {
				alert("运用规格" + v + "在码表中不存在");
				el.focus();
				return;
			}
			obj = coco.getCode("018", obj.key);
			if(obj == null) {
				alert("制造商规格略称" + obj.key + "在码表中不存在");
				el.focus();
				return;
			}
			var ggno = obj.value;
			var oForm = el.form;
			var xpzk = oForm.elements["xpzk"].value;
			var gzlx = ggno.substring(0,1);
			if(xpzk == "A") {
				oForm.elements["gzlx"].value = gzlx;
			} else {
				obj = coco.getCode("109", gzlx);
				if(obj == null) {
					alert("钢种类型" + gzlx + "在码表中不存在");
					el.focus();
					return;
				}
				oForm.elements["gzlx"].value = obj.value;
			}
			oForm.elements["ggno"].value = ggno;
		}

		function getYuny(el) {
			var v = el.value;
			if(v == null || v.length == 0) return;
			var obj = coco.getCodeByValue("018", v);
			if(obj == null) {
				alert("规格代码" + v + "在码表中不存在");
				el.focus();
				return;
			}
			obj = coco.getCode("019", obj.key);
			if(obj == null) {
				alert("制造商规格略称" + obj.key + "在码表中不存在");
				el.focus();
				return;
			}
			var yuny = obj.value;
			var oForm = el.form;
			var oForm = el.form;
			var xpzk = oForm.elements["xpzk"].value;
			var gzlx = v.substring(0,1);
			if(xpzk == "A") {
				oForm.elements["gzlx"].value = gzlx;
			} else {
				obj = coco.getCode("109", gzlx);
				if(obj == null) {
					alert("钢种类型" + gzlx + "在码表中不存在");
					el.focus();
					return;
				}
				oForm.elements["gzlx"].value = obj.value;
			}
			oForm.elements["yuny"].value = yuny;
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
		<ui:page title="现品情报修改">
		<table width="100%" class="form">
			<colgroup><col width="15%" /><col width="85%" /></colgroup>
			<tr>
				<th style="text-align: left;">COIL / PILE No.</th>
				<td><ui:input id="jbno" name="jbno" maxlength="11" onkeydown="if(window.event.keyCode == 13) doQuery(this);"/></td>
			</tr>
		</table>
		<div id="DataDiv" style="display: none;">
		<fieldset class="group"><legend>现品信息</legend>
		<form name="DataForm" xu.ajax="" xu.r="" xu.color="1" method="post" action="updateXp.do">
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
						<td><ui:input name="ched" readonly="true" maxlength="2"/></td>
						<th>装箱指示书</th>
						<td><ui:input name="chno" readonly="true" maxlength="10"/></td>
						<td style="text-align: left;"><input type="hidden" name="scbj" /><div id="ScbjDiv" style="color: red; display: none;">Delete</div></td>
					</tr>
					<tr>
						<th>
							现品尺寸
						</th>
						<td colspan="6">
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
						<td colspan="3"><span id="DhccSpan"></span></td>
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
								<span style="padding-right: 15px;">装入重量<ui:int name="zrzl" maxlength="8"/></span>
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
				 <td><input type="button" class="button" value="保存确认" onclick="doSave(this.form, this);" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="返   回" onclick="doReturn();" /></td>
			</tr>
			</table>
		</form>
		</fieldset>
		</div>
		</ui:page>
		<ui:panel id="YlArea" title="设置业连NO" display="false" closable="true" popup="true">
		<form name="YlForm" action="saveYlno.do" method="post">
		<input type="hidden" name="jbnos" />
		<table id="ItemTbl" width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="pagination">
			<colgroup><col width="10%" /><col width="20%" /><col width="70%" /><col /></colgroup>
			<tr>
				<th><input type="checkbox" onclick="coco.selAll('ids',this)" /></th>
				<th>类型</th>
				<th>业连NO</th>
			</tr>
		</table>
		<div class="submit">
		<table width="100%"><tr><td align="left">
		<input type="button" class="button" value="添 加" onclick="tableForm.insertEmptyRow('ItemTblTmp');" />
		<input type="button" class="button" value="移 除" onclick="tableForm.removeRow('ids');" />
		</td><td align="right">
		<input type="button" class="button" value="保 存" onclick="setYlno();" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('YlArea')" />
		</td>
		</tr>
		</table>
		</div>
		</form>
		<table id="ItemTblTmp" style="display: none">
			<tr>
				<td align="center" ><input type="checkbox" name="ids" /><input type="hidden" name="id" /></td>
				<td><ui:input name="ylFlag" maxlength="1" required="true" onblur="fillFlag(this);" /></td>
				<td><ui:input name="ylno" maxlength="9" required="true" /></td>
			</tr>
		</table>
		</ui:panel>
	</body>
</html>