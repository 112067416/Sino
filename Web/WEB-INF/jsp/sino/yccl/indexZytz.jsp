<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		var tf = new TableForm("DataTbl");
		var tableForm = new TableForm("ItemTbl");
		//新增一行
		function doAdd(o) {
			var row = tf.insertRow(document.getElementById("HidDataTbl").rows[0]);
			var oDataTbl = document.getElementById("DataTbl");
			var newRow = oDataTbl.rows[oDataTbl.rows.length - 1];
			var jbno = o.jbno != null ? o.jbno : "";
			var zzsd = o.zzsd != null ? o.zzsd : "";
			var plqf = o.plqf != null ? o.plqf : "";
			var kw = o.kw != null ? o.kw : "";
			var chno = o.chno != null ? o.chno : "";
			var dhno = o.dhno != null ? o.dhno : "";
			var abbr = o.abbr != null ? o.abbr : "";
			var xpho = o.xpho != null ? o.xpho : "";
			var xpkn = o.xpkn != null ? o.xpkn : "";
			var xpcn = o.xpcn != null && o.xpcn > 0 ? o.xpcn : "COIL";
			var face = o.face != null ? o.face : "";
			var fuzm = o.fuzm != null ? o.fuzm : "";
			var fufm = o.fufm != null ? o.fufm : "";
			var zpzl = o.zpzl != null ? o.zpzl : "";
			var ggno = o.ggno != null ? o.ggno : "";
			var ztbj = o.ztbj != null ? o.ztbj : "";
			newRow.cells[0].childNodes[0].value = jbno;
			newRow.cells[1].innerHTML= plqf + zzsd + "-" + jbno;
			newRow.cells[2].innerHTML = chno;
			newRow.cells[3].innerHTML = dhno;
			newRow.cells[4].innerHTML = abbr;
			newRow.cells[5].innerHTML = xpho + "*" + xpkn + "*" + xpcn;
			newRow.cells[6].innerHTML = face;
			newRow.cells[7].innerHTML = fuzm+"/"+fufm;
			newRow.cells[8].innerHTML = zpzl;
			newRow.cells[9].innerHTML = kw; 
			newRow.cells[10].innerHTML = ztbj;
		}
		// 移除选中记录
		function doRemove() {
			var oForm = document.forms["ZytzFrom"];
			var jbnos = oForm.elements["jbnos"].value;
			var chks = oForm.elements["ids"];
			if(chks == null) {
				alert("请选择移除的行");
				return;
			}
			var rgExp;
			if(chks.tagName != null) {
				if(chks.checked) {
					rgExp = new RegExp(chks.value+',|('+chks.value+'$)' , "g");
					oForm.elements["jbnos"].value = jbnos.replace(rgExp, '');
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(el.checked) {
						rgExp = new RegExp(el.value+',|('+el.value+'$)' , "g");
						jbnos = jbnos.replace(rgExp, '');
					}
				}
				oForm.elements["jbnos"].value = jbnos;
			}
			if(!confirm("确定移除该行吗?")) return;
			tf.removeRow("ids");
			doLoad();
		}
		//返回
		function doReturn() {
			var oForm = document.forms["ZytzFrom"];
			oForm.elements["jbnos"].value = '';
			tf.removeAll();
			var obj = document.getElementById("jbno");
			obj.value = '';
			obj.focus();
		}
		
		//页面加载
		function doLoad() {
			var obj = document.getElementById("jbno");
			obj.focus();
		}
		//查询
		function doQuery(oInput) {
			var vKey = oInput.value.replace(/^\s+|\s+$/,'');
			if(vKey == null || vKey.length == 0) {
				alert("COIL / PILE No.为空");
				return;
			}
			var oForm = document.forms["ZytzFrom"];
			var jbnos = oForm.elements["jbnos"].value;
			if(jbnos != null && jbnos.indexOf(vKey) >= 0) {
				alert("COIL / PILE No." + vKey + "已登录");
				oInput.value = '';
				oInput.focus();
				return;
			}
			var postContent = coco.parseParams([{name : "jbno",value: vKey}]);
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					oInput.disabled = false;
					oInput.focus();
					return;
				}
				var obj = null;
				eval("obj = " + this.result + ";");
				var jbno;
				for(var i = 0; i < obj.datas.length; i++) {
					jbno = obj.datas[i].jbno;
					if(jbno == null || jbno.length == 0) continue;
					if(jbnos == null || jbnos.length == 0) {
						jbnos = jbno;
					} else {
						jbnos = jbnos + "," + jbno;
					}
					doAdd(obj.datas[i]);
				}
				oForm.elements["jbnos"].value = jbnos;
				oInput.value = '';
				oInput.disabled = false;
				oInput.focus();
			};
			ajax.post("loadZyzt.do", postContent, "POST");
		}
		//作业停止
		function doZytz() {
			var postContent = cocoform.postCheckValues("ZytzFrom", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要设置作业停止标记的现品");
				return;
			}
			var oForm = document.forms["TzForm"];
			cocoform.clear(oForm);
			coco.showPage("TzPanel",{center:true,top:80,width:"30%"});
		}

		//作业停止设定
		function doSave(oForm, obj) {
			var postContent = cocoform.postCheckValues("ZytzFrom", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要设置作业停止标记的现品");
				return;
			}
			if(!cocoform.verify(oForm)) return;
			var params = cocoform.postContent(oForm);
			if(params != null && params.length > 0) {
				postContent = postContent + "&" + params;
			}
			var ajax = new Cocoajax();
			ajax.oInput = obj;
			ajax.callback = function() {
				if(this.code == -2) {
					alert(this.msg);
					return;
				}
				var $msg = this.code <= 0  ? this.msg : "是否确定保存?";
				if(!confirm($msg)) return; 
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
					} else {
						alert("保存成功");
					}
					coco.hidePage('TzPanel');
					doReturn();
					return;
				};
				ajax.post("updateZytz.do", postContent);
			};
			ajax.post("checkZytz.do", postContent);
		}

		//作业停止
		function doCz() {
			var postContent = cocoform.postCheckValues("ZytzFrom", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要修改处置理由的现品");
				return;
			}
			var oForm = document.forms["CzForm"];
			cocoform.clear(oForm);
			coco.showPage("CzPanel",{center:true,top:80,width:"30%"});
		}

		//设置处置理由
		function saveCz(oForm, obj) {
			var postContent = cocoform.postCheckValues("ZytzFrom", "ids", null);
			if(postContent == null || postContent.length == 0) {
				alert("请选择要修改处置理由的现品");
				return;
			}
			if(!cocoform.verify(oForm)) return;
			var params = cocoform.postContent(oForm);
			if(params != null && params.length > 0) {
				postContent = postContent + "&" + params;
			}
			var ajax = new Cocoajax();
			ajax.oInput = obj;
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
				} else {
					alert("保存成功");
				}
				coco.hidePage('CzPanel');
				doReturn();
				return;
			};
			ajax.post("updateCz.do", postContent);
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
		<ui:page title="作业停止登录">
			<table width="96%" align="center" class="form">
				<colgroup><col width="10%" /><col width="18%" /><col width="6%" /><col width="18%" /><col width="6%" /><col width="18%" /><col width="6%" /><col width="18%" /></colgroup>
				<tr>
					<th style="text-align: left;">COIL / PILE No.</th>
					<td><ui:input maxlength="11" name="jbno" id="jbno" onkeydown="if(window.event.keyCode == 13) doQuery(this);" /></td>
					<td colspan="6">&nbsp;</td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="移 除" onclick="doRemove()" />
				<input type="button" class="button" value="作业停止设定" onclick="doZytz();" />
				<input type="button" class="button" value="修改处置理由" onclick="doCz();" />
				<input type="button" class="button" value="返 回" onclick="doReturn();" />
			</div>
			<form name="ZytzFrom" method="post" action="#" >
			<input type="hidden" name="jbnos" />
			<table id="DataTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
			<colgroup><col width="5%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="18%" /><col width="14%" />
			<col width="5%" /><col width="9%" /><col width="9%" /><col width="5%" /><col width="5%" />
			</colgroup>
				<tr>
					<th><input type="checkbox" xu.target="ids" /></th>
					<th>现品NO.</th>
					<th>装箱指示书No.</th>
					<th>订货No.</th>
					<th>用户略称</th>
					<th>尺寸</th>
					<th>表面</th>
					<th>镀锡量</th>
					<th>现品重量</th>
					<th>堆场</th>
					<th>停止</th>
				</tr>
			</table>
			</form>
			<table id="HidDataTbl" style="display: none;">
				<tr>
					<td><input type="checkbox" name="ids" checked="true" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</ui:page>
		<ui:panel id="TzPanel" title="设定停止标记" popup="true" display="false" closable="true">
			<form action="#" name="TzForm" method="post">
			<table width="96%" align="center" style="color: #EEE; margin-top: 5px;" class="form">
				<colgroup><col width="25%" /><col width="25%" /><col width="25%" /><col width="25%" /></colgroup>
				<tr>
					<th style="text-align: left;">操作人</th>
					<td><ui:input name="czr" maxlength="8" required="true" /></td>
					<th style="text-align: left;">停止标记</th>
					<td><ui:input name="ztbj" maxlength="2" /></td>
				</tr>
				<tr>
					<th style="text-align: left; vertical-align: middle;">作业理由</th>
					<td colspan="3"><ui:textarea name="zyly" rows="4" cols="26" /></td>
				</tr>
				<tr>
					<th style="text-align: left; vertical-align: middle;">处置</th>
					<td colspan="3"><ui:textarea name="cz" rows="4" cols="26" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="确  定" onclick="doSave(this.form, this);" />
				<input type="button" class="button" value="关  闭" onclick="coco.hidePage('TzPanel')" />
			</div>
			</form>
		</ui:panel>
		<ui:panel id="CzPanel" title="修改处置理由" popup="true" display="false" closable="true">
			<form action="#" name="CzForm" method="post">
			<table width="96%" align="center" style="color: #EEE; margin-top: 5px;" class="form">
				<colgroup><col width="25%" /><col width="25%" /><col width="25%" /><col width="25%" /></colgroup>
				<tr>
					<th style="text-align: left; vertical-align: middle;">作业理由</th>
					<td colspan="3"><ui:textarea name="zyly" rows="4" cols="26" /></td>
				</tr>
				<tr>
					<th style="text-align: left; vertical-align: middle;">处置</th>
					<td colspan="3"><ui:textarea name="cz" rows="4" cols="26" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="确  定" onclick="saveCz(this.form, this);" />
				<input type="button" class="button" value="关  闭" onclick="coco.hidePage('CzPanel')" />
			</div>
			</form>
		</ui:panel>
	</body>
</html>