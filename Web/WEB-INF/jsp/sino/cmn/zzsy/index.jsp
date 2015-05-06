<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>制造仕样主文件</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript">
contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"]]);

	function doAdd() {
		var form = document.forms["DataForm"];
	//	var ignores = ["hxzi","hszi","kxzi","kszi","cxzi","cszi","ytyp"];
	//	cocoform.clear(form, ignores);
		form.reset();
		var ajax = new Cocoajax();
		ajax.async = false;
		ajax.post("getNewNo.do");
		form.elements["syno"].value = ajax.result;
		coco.showPage("Detail",{center:true,top:10,width:"860px"});
		form.elements["ggno"].focus();
	}

	function toModify11(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key1 = coco.getAttr(oTr, "id0");
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			try {
				var form = document.forms["DataForm"];
				cocoform.clear(form);
				var obj = null;
				eval("obj="+this.result+";");
				cocoform.fillObject(form, obj);
				if(obj.hmin == null || obj.hmin == 0) form.elements["hmin"].value = "";
				if(obj.hmax == null || obj.hmax == 0) form.elements["hmax"].value = "";
				if(obj.kmin == null || obj.kmin == 0) form.elements["kmin"].value = "";
				if(obj.kmax == null || obj.kmax == 0) form.elements["kmax"].value = "";
				if(obj.cmin == null || obj.cmin == 0) form.elements["cmin"].value = "";
				if(obj.cmax == null || obj.cmax == 0) form.elements["cmax"].value = "";
				if(obj.houu == null || obj.houu == 0) form.elements["houu"].value = "";
				if(obj.kuan == null || obj.kuan == 0) form.elements["kuan"].value = "";
				if(obj.cang == null || obj.cang == 0) form.elements["cang"].value = "";
				if(obj.hszi != null) form.elements["hszi"].value = obj.hszi.toFixed(1);
				if(obj.hxzi != null) form.elements["hxzi"].value = obj.hxzi.toFixed(1);
				if(obj.kszi != null) form.elements["kszi"].value = obj.kszi.toFixed(2);
				if(obj.kxzi != null) form.elements["kxzi"].value = obj.kxzi.toFixed(2);
				if(obj.cszi != null) form.elements["cszi"].value = obj.cszi.toFixed(2);
				if(obj.cxzi != null) form.elements["cxzi"].value = obj.cxzi.toFixed(2);
				form.elements["$type"].value = "modify";
				coco.showPage("Detail",{center:true,top:10,width:"860px"});
			}catch(e) {alert("系统出错:\n"+e.description);}
		};
		ajax.post("load.do", "syno="+key1);
	}


	//维护
	function toModify(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var id = coco.getAttr(oTr, "id0");
		var postContent = coco.parseParam("syno", id);
		var ajax = new Cocoajax();
		ajax.dataDiv = ModifyDiv;
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			coco.showPage("ModifyDetail",{center:true,top:10,width:"980px"});
			var form = document.forms["ModifyForm"];
			var hmin = form.elements["hmin"].value;
			var hmax = form.elements["hmax"].value;
			var kmin = form.elements["kmin"].value;
			var kmax = form.elements["kmax"].value;
			var cmin = form.elements["cmin"].value;
			var cmax = form.elements["cmax"].value;
			var houu = form.elements["houu"].value;
			var kuan = form.elements["kuan"].value;
			var cang = form.elements["cang"].value;
			if(hmin == null || hmin == 0) form.elements["hmin"].value = "";
			if(hmax == null || hmax == 0) form.elements["hmax"].value = "";
			if(kmin == null || kmin == 0) form.elements["kmin"].value = "";
			if(kmax == null || kmax == 0) form.elements["kmax"].value = "";
			if(cmin == null || cmin == 0) form.elements["cmin"].value = "";
			if(cmax == null || cmax == 0) form.elements["cmax"].value = "";
			if(houu == null || houu == 0) form.elements["houu"].value = "";
			if(kuan == null || kuan == 0) form.elements["kuan"].value = "";
			if(cang == null || cang == 0) form.elements["cang"].value = "";
			var hszi = form.elements["hszi"].value;
			var hxzi = form.elements["hxzi"].value;
			var kszi = form.elements["kszi"].value;
			var kxzi = form.elements["kxzi"].value;
			var cszi = form.elements["cszi"].value;
			var cxzi = form.elements["cxzi"].value;
			if(hszi != null) form.elements["hszi"].value = hszi.toFixed(1);
			if(hxzi != null) form.elements["hxzi"].value = hxzi.toFixed(1);
			if(kszi != null) form.elements["kszi"].value = kszi.toFixed(2);
			if(kxzi != null) form.elements["kxzi"].value = kxzi.toFixed(2);
			if(cszi != null) form.elements["cszi"].value = cszi.toFixed(2);
			if(cxzi != null) form.elements["cxzi"].value = cxzi.toFixed(2);
		};
		ajax.post("get.do", postContent);
	}

  	//保存操作
	function doSave() {
		var oForm = document.forms["DataForm"];
		var postContent = cocoform.postContent(oForm);
		var ajax = new Cocoajax;
		ajax.callback = function() {
			if(this.code <= 0 ) {
				alert(this.msg);
				var el = oForm.elements[Math.abs(this.code)];
				if(el != null) el.focus();
				return;
			}
			var $msg = "是否确定做保存操作?";
			if(this.code == 2) {
				$msg = "制造仕样No." + this.msg + "的仕样信息与该制造仕样信息一致,是否确定做保存操作?";
			} else if(this.code == 8) {
				$msg = this.msg + ",是否确定做强制保存操作?";
			}
			if(!confirm($msg)) return;
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					alert(this.msg);
	             	return;
				}
				alert("保存成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			});
		};
		ajax.post("validate.do", postContent);
	}


	//修改操作
	function doUpdate() {
		var oForm = document.forms["ModifyForm"];
		var postContent = cocoform.postContent(oForm);
		var ajax = new Cocoajax;
		ajax.callback = function() {
			if(this.code <= 0 ) {
				alert(this.msg);
				var el = oForm.elements[Math.abs(this.code)];
				if(el != null) el.focus();
				return;
			}
			var $msg = "是否确定做修改操作?";
			if(this.code == 2) {
				$msg = "制造仕样No." + this.msg + "的仕样信息与该制造仕样信息一致,是否确定做保存操作?";
			} else if(this.code == 8) {
				$msg = this.msg + ",是否确定做强制修改操作?";
			}
			if(!confirm($msg)) return;
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					alert(this.msg);
	             	return;
				}
				alert("保存成功");
				coco.hidePage('ModifyDetail');
				cocopage.refresh();
			});
		};
		ajax.post("validate.do", postContent);
	}
	
	//填充规格代码，码表
	function fillGgno(obj) {
		obj.form.elements["ymin"].value = "";
		obj.form.elements["ymax"].value = "";
		obj.form.elements["yuny"].value = "";
		if(obj.value == null || obj.value.length == 0) {
			return;
		}
		if(obj.value.length != 4) {
			alert("规格代码必须4位");
			obj.focus();
			return;
		}
		//后两位为调质度代号，通用主文件ID=111
		var code = coco.getCode("111", obj.value.substring(2));
		if(code == null) {
			alert("规格代码的调质度代号无效");
			obj.value = "";
			obj.focus();
			return;
		}
		//把值日填入硬度.上限值和硬度.下限值
		var ymin = code.value.substring(0,2);
		var ymax = code.value.substring(2);
		obj.form.elements["ymin"].value = ymin;
		obj.form.elements["ymax"].value = ymax;
		//查询运用规格，通用主文件ID=003
		var code = coco.getCode("003", obj.value);
		if(code == null) {
			alert("规格代码的运用规格不存在");
			obj.form.elements["yuny"].value = "";
			obj.focus();
			return;
		}
		obj.form.elements["yuny"].value = code.remark;
	}
	//填充附着量单位
	function fillFudw(obj) {
		if(obj.value == null || obj.value.length == 0) {
			return false;
		}
		if(!(obj.value == "GM" || obj.value == "WB")) {
			alert("附着量单位只能填GM、WB");
			obj.value = "";
			obj.focus();
			return false;
		}
	}
	//填充附着量正面，码表
	function fillFuzm(obj) {
		obj.form.elements["fuzx"].value = "";
		obj.form.elements["fuzs"].value = "";
		if(obj.value == null || obj.value.length == 0) {
			return false;
		}
		var dw = obj.form.elements["fudw"];
		if(dw.value == "") {
			alert("必须先填写附着量单位");
			obj.value = "";
			dw.focus();
			return false;
		}
		if(obj.value.length != 3) {
			alert("附着量值必须3位");
			obj.value = "";
			return false;
		}
		var code = coco.getCode("024", dw.value + obj.value);
		if(code == null) {
			alert("附着量值无效");
			obj.value = "";
			return false;
		}
		var min = (parseFloat(code.value.substring(0,4).replace(/^0+/g,"")) / 100).toFixed(2);
		var max = (parseFloat(code.value.substring(4).replace(/^0+/g,"")) / 100).toFixed(2);
		obj.form.elements["fuzx"].value = min;
		obj.form.elements["fuzs"].value = max;
	}


	//填充附着量正面，码表
	function fillFufm(obj) {
		obj.form.elements["fufx"].value = "";
		obj.form.elements["fufs"].value = "";
		if(obj.value == null || obj.value.length == 0) {
			return false;
		}
		var dw = obj.form.elements["fudw"];
		if(dw.value == "") {
			alert("必须先填写附着量单位");
			obj.value = "";
			dw.focus();
			return false;
		}
		if(obj.value.length != 3) {
			alert("附着量值必须3位");
			obj.value = "";
			return false;
		}
		var code = coco.getCode("024", dw.value + obj.value);
		if(code == null) {
			alert("附着量值无效");
			obj.value = "";
			return false;
		}
		var min = (parseFloat(code.value.substring(0,4).replace(/^0+/g,"")) / 100).toFixed(2);
		var max = (parseFloat(code.value.substring(4).replace(/^0+/g,"")) / 100).toFixed(2);
		obj.form.elements["fufx"].value = min;
		obj.form.elements["fufs"].value = max;
	}
	//别纸KpNo标示位
	function fillKpnFlag(obj) {
		if(obj.value == null || obj.value.length == 0) {
			return;
		}
		if(!(obj.value == "1" || obj.value == "2" || obj.value == "3")) {
			alert("别纸KpNo标示位只能是1、2或3");
			obj.value = "";
			obj.focus();
			return false;
		}
	}
	//更新仕样信息
	function doRefresh() {
		if(!confirm("是否确定更新仕样信息?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("更新成功");
			cocopage.refresh();
		};
		ajax.post("doRefresh.do");
	}
	//-->
</script>
</head>
<body>
<ui:page title="制造仕样主文件">
	<f:page action="index.do" file="data.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="100" />
				<col width="80" />
				<col width="50" />
				<col width="80" />
				<col />
			</colgroup>
			<tr>
				<th>制造仕样No.</th>
				<td><ui:input name="syno" maxlength="6" /></td>
				<th>规格</th>
				<td><ui:input name="ggno" maxlength="6" /></td>
				<td align="right">
				<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
				<input type="button" class="button" value="新 增" onclick="doAdd()" />				
				<input type="button" class="button" value="更 新" onclick="doRefresh();" /></td>
			</tr>
		</table>
	</f:page>
</ui:page>
<ui:panel id="Detail" title="新增制造 仕样主文件" popup="true" display="false" closable="true">
<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="save.do" >
<input type="hidden" name="$type" />
<br/>
<div class="form-row">
<span class="form-label">制造仕样 No. = </span><ui:input name="syno" maxlength="6" required="true" readonly="true" />
</div>
<fieldset class="group"><legend>第1KEY</legend>
<div class="form-row">
<span class="form-label">规格 =</span><ui:input name="ggno" maxlength="4" onblur="fillGgno(this);" required="true"  />
<span class="form-label">附着量 =</span><ui:input name="fudw" title="单位" maxlength="2" required="true" onblur="fillFudw(this);" />&nbsp;<ui:input name="fuzm" title="正面值" maxlength="3" required="true" onblur="fillFuzm(this);" />&nbsp;<ui:input name="fufm" title="反面值" maxlength="3" required="true" onblur="fillFufm(this);" />
<span class="form-label">品种 =</span><ui:input name="pzno" required="true" maxlength="2" />
<span class="form-label">差厚 =</span><ui:input name="chdx" maxlength="2" />
<span class="form-label">内外销 =</span><ui:input name="nwai" required="true" maxlength="1" />
</div>
<div class="form-row">
<span class="form-label">订货尺寸 = </span>
<ui:number name="hmin" title="厚下限" scale="7" precision="3" positive="true" />&nbsp;&nbsp;-&nbsp;&nbsp;<ui:number name="hmax" title="厚上限" scale="7" precision="3" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="kmin" title="宽下限" scale="9" precision="2" positive="true" />&nbsp;&nbsp;-&nbsp;&nbsp;<ui:number name="kmax" title="宽上限" scale="9" precision="2" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="cmin" title="长下限" scale="9" precision="2" positive="true" />&nbsp;&nbsp;-&nbsp;&nbsp;<ui:number name="cmax" title="长上限" scale="9" precision="2" positive="true" />
</div>
<div class="form-row" style="padding-left: 125px">
<ui:number name="houu" title="厚" scale="7" precision="3" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="kuan" title="宽" scale="9" precision="2" positive="true" />&nbsp;&nbsp;*&nbsp;&nbsp;
<ui:number name="cang" title="长" scale="9" precision="2" positive="true" />
</div></fieldset>
<fieldset class="group"><legend>第2KEY</legend>
<div class="form-row">
<span class="form-label">加工用途  = </span>
<ui:input name="cond" maxlength="4" /> 
<span class="form-label">用户代码  = </span>
<ui:input name="user" maxlength="4" />
</div>
</fieldset>
<fieldset class="group"><legend>指定项目</legend>
<table class="form">
<colgroup>
<col width="75" />
<col width="15" />
<col width="80" />
<col width="65" />
<col width="15" />
<col width="100" />
<col width="75" />
<col width="115" />
<col width="180" />
</colgroup>
<tr>
<th style="text-align: left;">剪边</th>
<th style="text-align: center;">=</th>
<td><ui:input name="jian" maxlength="1" /> </td>
<th style="text-align: left;">零头下限</th>
<th style="text-align: center;">=</th>
<td><ui:input name="ltdw" title="单位" maxlength="1" />&nbsp;&nbsp;<ui:int name="ltzi" title="值" maxlength="6" positive="true" /> </td>
<th style="text-align: left;">硬度上下限</th>
<th style="text-align: center;">=</th>
<td><ui:input name="ymin" maxlength="2" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="ymax" maxlength="2" /></td>
</tr>
<tr>
<th style="text-align: left;">涂油种类</th>
<th style="text-align: center;">=</th>
<td><ui:input name="ytyp" maxlength="1" required="true" value="entity.ytyp" /></td>
<th style="text-align: left;">KPLATE</th>
<th style="text-align: center;">=</th>
<td><ui:input name="plat" maxlength="1" /></td>
<th style="text-align: left;">附着量 正</th>
<th style="text-align: center;">=</th>
<td><ui:number name="fuzx" scale="5" precision="2" positive="true" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fuzs" scale="5" precision="2" positive="true" /></td>
</tr>
<tr>
<th style="text-align: left;">涂油量</th>
<th style="text-align: center;">=</th>
<td><ui:input name="yqty" maxlength="2" required="true" /></td>
<th style="text-align: left;">合金层</th>
<th style="text-align: center;">=</th>
<td><ui:input name="hjin" maxlength="3" /></td>
<th style="text-align: left;">上下限 反</th>
<th style="text-align: center;">=</th>
<td><ui:number name="fufx" scale="5" precision="2" positive="true" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fufs" scale="5" precision="2" positive="true" /></td>
</tr>
<tr>
<th style="text-align: left;">分配等级</th>
<th style="text-align: center;">=</th>
<td><ui:input name="fpdj" maxlength="3" required="true" /></td>
<td colspan="3">&nbsp;</td>
<th style="text-align: left;">公差厚(%)</th>
<th style="text-align: center;">=</th>
<td><ui:number name="hxzi" scale="5" precision="1" value="entity.hxzi" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="hszi" scale="5" precision="1" value="entity.hszi" /></td>
</tr>
<tr>
<th style="text-align: left;">直角度</th>
<th style="text-align: center;">=</th>
<td><ui:number name="jiao" scale="5" precision="1" positive="true" /></td>
<th style="text-align: left;">&nbsp;</th>
<th style="text-align: center;">&nbsp;</th>
<td>&nbsp;</td>
<th style="text-align: left;">公差宽(mm)</th>
<th style="text-align: center;">=</th>
<td><ui:number name="kxzi" scale="5" precision="2" format="2" value="entity.kxzi" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="kszi" scale="5" precision="2" format="2" value="entity.kszi" /></td>
</tr>
<tr>
<th style="text-align: left;">内径保护筒</th>
<th style="text-align: center;">=</th>
<td><ui:input name="njbh" maxlength="1" /></td>
<th style="text-align: left;">运用规格</th>
<th style="text-align: center;">=</th>
<td><ui:input name="yuny" maxlength="6" readonly="true" /></td>
<th style="text-align: left;">公差长(mm)</th>
<th style="text-align: center;">=</th>
<td><ui:number name="cxzi" scale="5" precision="2" format="2" value="entity.cxzi" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="cszi" scale="5" precision="2" format="2" value="entity.cszi" /></td>
</tr>
<tr>
<th style="text-align: left;">保护板</th>
<th style="text-align: center;">=</th>
<td><ui:input name="prot" maxlength="1" /></td>
<th style="text-align: left;">装入宽</th>
<th style="text-align: center;">=</th>
<td><ui:number name="zrkn" scale="9" precision="2" positive="true" /></td>
<th style="text-align: left;">波剪直剪</th>
<th style="text-align: center;">=</th>
<td><ui:input name="jcxs" maxlength="4" /></td>
</tr>
<tr>
<th style="text-align: left;">附页KPNo</th>
<th style="text-align: center;">=</th>
<td colspan="6"><ui:input name="kpn1Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn1" maxlength="9" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="kpn2Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn2" maxlength="9" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="kpn3Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn3" maxlength="9" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="kpn4Flag" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" onblur="fillKpnFlag(this);" />-<ui:input name="kpn4" maxlength="9" title="最多只能输入四个KPNO" /></td>
<th style="text-align: left;">证书提示信息&nbsp;&nbsp;&nbsp;<ui:input name="jcts" maxlength="2" title="检查证明书提示信息" /></th>
</tr>
<tr>
<th style="text-align: left;">木工所No</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="mgsn" cols="100" rows="" title="木工所No（如果有多个木工所no时，要用半角英文输入法的“,”间开）" />
</td>
</tr>
<tr>
<th style="text-align: left;">备注1</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="bz1" cssStyle="" cssClass="normal" cols="100" rows="" title="ETL和木工所指示书的备注内容"/></td>
</tr>
<tr>
<th style="text-align: left;">备注2</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="bz2" cssStyle="" cssClass="normal" cols="100" rows="" title="SL指示书的备注内容"/></td>
</tr>
<tr>
<th style="text-align: left;">备注3</th>
<th style="text-align: center;">=</th>
<td colspan="7"><ui:textarea name="bz3" cssStyle="" cssClass="normal" cols="100" rows="" title="ETL、木工所和SL指示书的备注内容"/></td>
</tr>
</table>
</fieldset>
<div class="submit"><input type="button" class="button" value="保 存" onclick="doSave();" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('Detail')" /></div>
</form>
</ui:panel>
<ui:panel id="ModifyDetail" title="修改制造 仕样主文件" display="false" closable="true" popup="true">
<form name="ModifyForm" method="post" action="save.do">
<div id="ModifyDiv" class="scroll" style="width:100%;height:700px;"></div>
</form>
</ui:panel>
</body>
</html>