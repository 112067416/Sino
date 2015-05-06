<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		var tf = new TableForm("DataTbl");
		//新增一行
		function doAdd(o) {
			var row = tf.insertRow(document.getElementById("HidDataTbl").rows[0]);
			var oDataTbl = document.getElementById("DataTbl");
			var newRow = oDataTbl.rows[oDataTbl.rows.length - 1];
			var jbno = o.jbno != null ? o.jbno : "";
			var zzsd = o.zzsd != null ? o.zzsd : "";
			var plqf = o.plqf != null ? o.plqf : "";
			var kw = o.kw != null ? o.kw : "";
			var dhno = o.dhno != null ? o.dhno : "";
			var abbr = o.abbr != null ? o.abbr : "";
			var houu = o.houu != null ? o.houu : "";
			var kuan = o.kuan != null ? o.kuan : "";
			var cang = o.cang != null && o.cang > 0 ? o.cang : "COIL";
			var deng = o.deng != null ? o.deng : "";
			var face = o.face != null ? o.face : "";
			var zpzl = o.zpzl != null ? o.zpzl : "";
			var kbao = o.kbao != null ? o.kbao : "";
			var ztbj = o.ztbj != null ? o.ztbj : "";
			newRow.cells[0].childNodes[0].value = jbno;
			newRow.cells[1].innerHTML= plqf + zzsd + "-" + jbno;
			newRow.cells[2].innerHTML = kw;
			newRow.cells[3].childNodes[0].value = jbno;
			newRow.cells[4].innerHTML = dhno;
			newRow.cells[5].innerHTML = abbr;
			newRow.cells[6].innerHTML = houu + "*" + kuan + "*" + cang;
			newRow.cells[7].innerHTML = deng;
			newRow.cells[8].innerHTML = face;
			newRow.cells[9].innerHTML = zpzl;
			newRow.cells[10].innerHTML = kbao; 
			newRow.cells[11].innerHTML = ztbj;
			document.getElementById("jbno").value = "";
		}
		
		//根据卷板号查询制品信息
		function doQuery(oInput) {
			var vKey = oInput.value.replace(/^\s+|\s+$/,'');
			if(vKey == null || vKey.length == 0) {
				alert("COIL / PILE No.为空");
				return;
			}
			var oForm = document.forms["DcsdFrom"];
			var jbnos = oForm.elements["jbnos"].value;
			if(jbnos != null && jbnos.indexOf(vKey) >= 0) {
				alert("COIL / PILE No." + vKey + "已登录");
				return;
			}
			var postContent = coco.parseParams([{name : "jbno",value: vKey},{name : "flag",value: false}]);
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					oInput.focus();
					return;
				}
				if(jbnos == null || jbnos.length == 0) {
					jbnos = vKey;
				} else {
					jbnos = jbnos + "," + vKey;
				}
				oForm.elements["jbnos"].value = jbnos;
				var obj = null;
				eval("obj = " + this.result + ";");
				doAdd(obj);
				oInput.disabled = false;
				oInput.focus();
			};
			ajax.post("loadZp.do", postContent, "POST");
		}

		// 移除选中记录
		function doRemove() {
			var oForm = document.forms["DcsdFrom"];
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
		}
		//提交
		function doSubmit(oInput) {
			var form = document.forms["DcsdFrom"];
			var eles1 = form.elements["kw"];
			var eles2 = form.elements["jbno"];
			var el1, el2, elIndex=0,kws="",jbnos="";
			if(eles2 == null) {
				alert("请输入COIL / PILE No.");
				document.getElementById("jbno").focus();
				return
			}
			if(eles1.tagName != null) {
				if(kws.length > 0) kws += "&";
				el1 = eles1.value.replace(/^\s+|\s+$/,'');
				if(el1 == null || el1.length == 0){
					alert("搬送堆场不能为空");
					eles1.focus();
					return;
				}
				kws += encodeURIComponent(eles1.name) + "=" + encodeURIComponent(el1); 
				if(jbnos.length > 0) jbnos += "&";
				jbnos += encodeURIComponent(eles2.name) + "=" + encodeURIComponent(eles2.value); 
			}
			 else {
				for(var i =0; i < eles1.length; i++) {
					el1 = eles1[i].value.replace(/^\s+|\s+$/,'');
					if(el1 == null || el1.length == 0){
						alert("搬送堆场不能为空");
						eles1[i].focus();
						return;
					}
					el2 = eles2[i];
					if(kws.length > 0) kws += "&";
					kws += encodeURIComponent(eles1[i].name) + "=" + encodeURIComponent(el1); 
					if(jbnos.length > 0) jbnos += "&";
					jbnos += encodeURIComponent(el2.name) + "=" + encodeURIComponent(el2.value); 
				}
			}
			var postContent = jbnos + "&" + kws;
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				var $msg = this.msg != null && this.msg.length > 0  ? this.msg + "\n是否强制转?" : "是否确定保存?";
				if(!confirm($msg)) return;
				ajax.callback = function() {
					if (this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("堆场转换操作成功");
					window.location.reload();
					return;
				};
				ajax.post("updateDc.do", postContent);
			};
			ajax.post("checkDcgz.do", postContent);
		}

		//判断库位.在码表中是否存在
		function checkKw(el) {
			var v = el.value;
			if(v == null || v.length == 0) return;
			var obj = coco.getCode("011", v);
			if(obj == null) {
				alert("库位" + v + "在码表中不存在; ");
				el.focus();
				return
			}
		}
		//页面加载
		function doLoad() {
			var obj = document.getElementById("jbno");
			obj.focus();
		}

		//返回
		function doReturn() {
			var oForm = document.forms["DcsdFrom"];
			oForm.elements["jbnos"].value = '';
			tf.removeAll();
			var obj = document.getElementById("jbno");
			obj.value = '';
			obj.focus();
		}
		</script>
	</head>
	<body onload="doLoad();">
		<ui:page title="堆场实绩维护">
		<table width="100%" class="form">
			<colgroup><col width="15%" /><col width="85%" /></colgroup>
			<tr>
				<th style="text-align: left;">COIL / PILE No.</th>
				<td><ui:input id="jbno" name="jbno" maxlength="11" onkeydown="if(window.event.keyCode == 13) doQuery(this);"/></td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="移 除" onclick="doRemove();" />  <input type="button" class="button" value="确认保存" onclick="doSubmit(this);" />  <input type="button" class="button" value="返 回" onclick="doReturn();" /></div>
		<form name="DcsdFrom" method="post" action="#" >
			<input type="hidden" name="jbnos" />
			<table id="DataTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
				<colgroup><col /><col /><col /><col /><col /></colgroup>
				<tr>
					<th><input type="checkbox" xu.target="ids" /></th>
					<th>PILE No.</th>
					<th>现堆场</th>
					<th>搬送堆场</th>
					<th>订货No.</th>
					<th>用户略称</th>
					<th>制品尺寸 </th>
					<th>等级 </th>
					<th>表面 </th>
					<th>制品重量 </th>
					<th>捆包形式 </th>
					<th>停止标志</th>
				</tr>
			</table>
		</form>
		<table id="HidDataTbl" style="display: none;">
			<tr>
				<td><input type="checkbox" name="ids" /></td>
				<td></td>
				<td></td>
				<td><input type="hidden" name="jbno" maxlength="11" /><ui:input name="kw" maxlength="4" required="true" onblur="checkKw(this);" /></td>
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
	</body>
</html>