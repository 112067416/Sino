<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
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
			var ylno = o.ylno != null ? o.ylno : "";
			var yuny = o.yuny != null ? o.yuny : "";
			var houu = o.houu != null ? o.houu : "";
			var kuan = o.kuan != null ? o.kuan : "";
			var cang = o.cang != null && o.cang > 0 ? o.cang : "COIL";
			var deng = o.deng != null ? o.deng : "";
			var face = o.face != null ? o.face : "";
			var zpzl = o.zpzl != null ? o.zpzl : "";
			var ggno = o.ggno != null ? o.ggno : "";
			var ztbj = o.ztbj != null ? o.ztbj : "";
			newRow.cells[0].childNodes[0].value = jbno;
			newRow.cells[1].innerHTML= plqf + zzsd + "-" + jbno;
			newRow.cells[2].innerHTML = ylno;
			newRow.cells[3].innerHTML = yuny;
			newRow.cells[4].innerHTML = zpzl;
			newRow.cells[5].innerHTML = houu + "*" + kuan + "*" + cang;
			newRow.cells[6].innerHTML = face;
			newRow.cells[7].innerHTML = deng;
			newRow.cells[8].innerHTML = ggno;
			newRow.cells[9].innerHTML = kw; 
			newRow.cells[10].innerHTML = ztbj;
			document.getElementById("jbno").value = "";
		}
		// 移除选中记录
		function doRemove() {
			var oForm = document.forms["YldlFrom"];
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
		//返回
		function doReturn() {
			var oForm = document.forms["YldlFrom"];
			oForm.elements["jbnos"].value = '';
			tf.removeAll();
			var obj = document.getElementById("jbno");
			obj.value = '';
			obj.focus();
		}
		//查询
		function doQuery(oInput) {
			var vKey = oInput.value.replace(/^\s+|\s+$/,'');
			if(vKey == null || vKey.length == 0) {
				alert("COIL / PILE No.为空");
				return;
			}
			var oForm = document.forms["YldlFrom"];
			var jbnos = oForm.elements["jbnos"].value;
			if(jbnos != null && jbnos.indexOf(vKey) >= 0) {
				alert("COIL / PILE No." + vKey + "已登录");
				oInput.focus();
				return;
			}
			var postContent = coco.parseParams([{name : "jbno",value: vKey},{name : "flag",value: true}]);
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
		//页面加载
		function doLoad() {
			document.getElementById("jbno").focus();
		}
		// 弹出业连NO设定界面
		function doYldl() {
			var ids = cocoform.postCheckValues("YldlFrom", "ids", null);
			if(ids == null || ids.length == 0) {
				alert("请选择要设置业连NO的现品");
				return;
			}
			tableForm.removeAll();
			coco.showPage("YlArea",{center:true,top:80,width:"30%"});
		}

		// 弹出批量设置业连NO设定界面
		function toPlyldl() {
			coco.showPage("PlszArea",{center:true,top:80,width:"50%"});
		}
		//保存业务NO
		function saveYlno(oForm, obj) {
			var ids = cocoform.postCheckValues("YldlFrom", "ids", null);
			if(ids == null || ids.length == 0) {
				alert("请选择要设置业连NO的现品");
				return;
			}
			var oTbl = document.getElementById(tableForm.id);
			if(oTbl == null) return;
			var rows = oTbl.rows;
			if(rows.length  == 1) {
				alert("请输入业连NO");
				return;
			}
			tableForm.formatTblForm("items",1);
			oForm.elements["jbnos"].value = ids;
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("保存成功");
				window.location.reload();
				return;
				}, "确定保存吗?", null, null, obj);
		}

		//批量设置业务NO
		function plszYlno(oForm, obj) {
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("保存成功");
				coco.hidePage('PlszArea');
				return;
				}, "确定保存吗?", null, null, obj);
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

		//判断业连NO在资料室中是否存在
		function checkYlno(obj) {
			var v = obj.value;
			if(v == null || v.length == 0) return;
			var ajax = new Cocoajax();
			var postContent = coco.parseParams([{name:"name",value:v}]);
			ajax.async = false;
			ajax.post(path + "/sino/cmn/inform/isExisted.do", postContent);
			if(ajax.code <= 0) {
				alert("业连NO" + v + "在资料室中不存在");
				obj.focus();
				return;
			}
		}
		//置空业连NO
		function doClear(oInput) {
			var ids = cocoform.postCheckValues("YldlFrom", "ids", null);
			if(ids == null || ids.length == 0) {
				alert("请选择要清空业连NO的现品");
				return;
			}
			var oForm = document.forms["YlForm"];
			oForm.elements["jbnos"].value = ids;
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("操作成功");
				window.location.reload();
				return;
				}, "是否确定清空业联NO吗?",null,oInput);
		}

		function changXpzk(el) {
			var v = el.value;
			var form = el.form;

			var el1 = form.elements["jbnoStart"];
			var el2 = form.elements["jbnoEnd"];
			el1.value = "";
			el2.value = "";
			if(v == "A") {
				el1.maxLength = 7;
				el1.style.width = "72px";

				el2.maxLength = 7;
				el2.style.width = "72px";
			} else {
				el1.maxLength = 9;
				el1.style.width = "92px";

				el2.maxLength = 9;
				el2.style.width = "92px";
			}
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
		<ui:page title="业连NO登录">
		<table width="96%" align="center" class="form">
			<colgroup><col width="15%"/><col width="85%"/></colgroup>
			<tr>
				<th style="text-align: left;">引用COIL / PILE No.</th>
				<td><ui:input maxlength="11" name="jbno" id="jbno" onkeydown="if(window.event.keyCode == 13) doQuery(this);" required="true" /></td>
			</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="移 除" onclick="doRemove()" />
		<input type="button" class="button" value="设置业连NO" onclick="doYldl();" />
		<input type="button" class="button" value="置空业连NO" onclick="doClear();" />
		<input type="button" class="button" value="批量业连NO" onclick="toPlyldl();" />
		<input type="button" class="button" value="返 回" onclick="doReturn();" />
		</div>
		<form name="YldlFrom" method="post" action="#" >
		<input type="hidden" name="jbnos" />
		<table id="DataTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
		<colgroup><col width="4%"/><col width="10%"/><col width="29%"/><col width="8%"/><col width="8%"/><col width="9%"/><col width="6%"/><col width="6%"/><col width="6%"/><col width="6%"/><col width="8%"/></colgroup>
		<tr class="page-head" align="center">
			<th><input type="checkbox" xu.target="ids" /></th>
			<th>现品NO.</th>
			<th>业连NO</th>
			<th>运用规格</th>
			<th>重量(Kg)</th>
			<th>现品尺寸</th>
			<th>表面</th>
			<th>等级</th>
			<th>规格</th>
			<th>堆场</th>
			<th>停止标志</th>
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
		<ui:panel id="YlArea" title="设置业连NO" display="false" closable="true" popup="true">
		<form name="YlForm" action="updateYlno.do" method="post">
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
		<input type="button" class="button" value="保 存" onclick="saveYlno(this.form, this);" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('YlArea')" />
		</td>
		</tr>
		</table>
		</div>
		</form>
		<table id="ItemTblTmp" style="display: none">
			<tr>
				<td align="center" ><input type="checkbox" name="ids" /><input type="hidden" name="id" /></td>
				<td><ui:input name="ylFlag" maxlength="1" required="true" onblur="fillFlag(this);" /></td>
				<td><ui:input name="ylno" maxlength="9" required="true" onblur="checkYlno(this)" /></td>
			</tr>
		</table>
		</ui:panel>
		<ui:panel id="PlszArea" title="批量设置业连NO" display="false" closable="true" popup="true">
		<form name="PlszForm" action="plszYlno.do" method="post">
		<table class="form" width="100%">
		<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="50%" /></colgroup>
		<tr>
		<th style="text-align: left;">现品状况</th>
		<td><ui:select name="xpzk" list="${xpzk }" onchange="changXpzk(this);" /></td>
		<th style="text-align: left;">现品No.</th> 
		<td><ui:input name="jbnoStart" maxlength="7" required="true" /> 至 <ui:input name="jbnoEnd" maxlength="7" required="true" /></td>
		</tr>
		<tr>
		<th style="text-align: left;">类型</th> 
		<td><ui:input name="ylFlag" maxlength="1" required="true" onblur="fillFlag(this);" /></td>
		<th style="text-align: left;">业连NO</th> 
		<td><ui:input name="ylno" maxlength="9" required="true" onblur="checkYlno(this)"  /></td>
		</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="保 存" onclick="plszYlno(this.form, this);" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('PlszArea');" />
		</div>
		</form>
		</ui:panel>
	</body>
</html>