<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>次级品订货合同管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<SCRIPT type="text/javascript">
<!--
//修改操作
function toZzCjp() {
	var form = document.forms["FspForm"];
	//cocoform.clear(form);
	var oForm = document.forms["PageForm_page"];
	var ids = oForm.elements["ids"].value;
	if(ids == null || ids.length == 0) {
		alert("请选择要制作订货合同的次级品");
		return;
	}
	form.elements["ids"].value = ids;
	form.elements["xpzk"].value = oForm.elements["xpzk"].value;
	form.elements["chan"].value = oForm.elements["chan"].value;
	coco.showPage("FspHt", {center : true, top : 100, width : "90%" });
}

//制作发生品订货合同
function doCjpHt(oInput) {
	var form = oInput.form;
	var ele1 = form.elements["dhno"];
	var dhno = ele1.value.replace(/^\s+|\s+$/gi, "");
	if(dhno == null || dhno.length == 0) {
		alert("订货号不能为空");
		ele1.focus();
		return;
	}
	var ele2 = form.elements["jhqi"];
	var jhqi = ele2.value;
	if(jhqi == null || jhqi.length == 0) {
		alert("交货日期不能为空");
		ele2.focus();
		return;
	}
	var ele3 = form.elements["user"];
	var user = ele3.value;
	if(user == null || user.length == 0) {
		alert("用户不能为空");
		ele3.focus();
		return;
	}
	var ele4 = form.elements["addr"];
	var addr = ele4.value;
	if(addr == null || addr.length == 0) {
		alert("送货地址不能为空");
		return;
	}
	var ele5 = form.elements["htdj"];
	var htdj = ele5.value;
	if(htdj == null || htdj.length == 0) {
		alert("合同单价不能为空");
		return;
	}
	if(!confirm("是否确定制作次级品订货合同?")) return;
	var qForm = document.forms["PageForm_page"];
	coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
	cocoform.submit(form, function() {
		coco.hideAlert();
		if(this.code <= 0) {
			coco.alert(this.msg);
			return;
		}
		cocopage.query();
		alert("生成数据成功");
		qForm.elements["ids"].value = "";
		qForm.elements["hjzp"].value = "";
		qForm.elements["hjbs"].value = "";
		qForm.elements["hjsl"].value = "";
		coco.hidePage("FspHt");
		}, null, null, oInput);
}

//复选框选中
function doCheck(obj) {
	if(obj == null) {
		return;
	}
	//选中装箱指示书号
	var v = obj.value;
	//选中制品的重量
	var zpzl = parseInt(obj.getAttribute("zpzl"));
	var bszl = parseInt(obj.getAttribute("bszl"));
	
	var oForm = document.forms["PageForm_page"];
	var hjzp = oForm.elements["hjzp"].value;
	var hjbs = oForm.elements["hjbs"].value;
	var hjsl = oForm.elements["hjsl"].value;
	var ids = oForm.elements["ids"].value;
	var rgExp;
	if(obj.checked) {
		if(ids == null || ids.length == 0) {
			oForm.elements["ids"].value = v;
			oForm.elements["hjzp"].value = parseFloat(zpzl / 1000.0).toFixed(3);
			oForm.elements["hjbs"].value = parseFloat(bszl / 1000.0).toFixed(3);
			oForm.elements["hjsl"].value=1;
			return;
		}
		oForm.elements["ids"].value = ids.replace(/(,)+$/g,'') + ',' + v;
		oForm.elements["hjzp"].value = (parseFloat(hjzp) + parseFloat(zpzl / 1000.0)).toFixed(3);
		oForm.elements["hjbs"].value = (parseFloat(hjbs) + parseFloat(bszl / 1000.0)).toFixed(3);
		oForm.elements["hjsl"].value= parseInt(hjsl)+1;
	} else {
		var $v = v.replace(/^\(/gi,'\\(').replace(/\)/gi,'\\)');
		rgExp = new RegExp($v+',|('+$v+'$)' , "g");
		oForm.elements["ids"].value = ids.replace(rgExp, '');
		oForm.elements["hjzp"].value = (parseFloat(hjzp) - parseFloat(zpzl / 1000.0)).toFixed(3);
		oForm.elements["hjbs"].value = (parseFloat(hjbs) - parseFloat(bszl / 1000.0)).toFixed(3);
		oForm.elements["hjsl"].value-=1;
	}
}
//全选或全不选
function checkAll(obj) {
	var oForm = document.forms["PageForm_page"];
	var chks = oForm.elements["id"];
	if(chks == null )  return;
	if(chks.tagName != null) {
		if(obj.checked ^ chks.checked) {
			chks.checked = obj.checked;
			doCheck(chks);
		}
	} else {
		var el, elIndex=0;
		while((el = chks[elIndex++]) != null) {
			if(obj.checked ^ el.checked) {
				el.checked = obj.checked;
				doCheck(el);
			}
			continue;
		}
	}
}
//自动设置复选框的checked值
function setChecked() {
	var oForm = document.forms["PageForm_page"];
	var ids = oForm.elements["ids"].value;
	if(ids == null || ids.length == 0) {
		return;
	}
	var chks = oForm.elements["id"];
	if(chks == null) return;
	if(chks.tagName != null) {
		if(ids.indexOf(chks.value) >= 0) {
			chks.checked = true;
		}
	} else {
		var el, elIndex=0;
		while((el = chks[elIndex++]) != null) {
			if(ids.indexOf(el.value) >= 0) {
				el.checked = true;
			}
		}
	}
}
//查询
function doQuery(el) {
	var form = el.form;
	form.elements["ids"].value = "";
	form.elements["hjzp"].value = "";
	form.elements["hjbs"].value = "";
	form.elements["hjsl"].value = "";
	cocopage.query();
}

//根据送货地址代码，获得对应的送货地址
function getYong(user) {
	var form = user.form;
	var abbr = form.elements["abbr"];
	abbr.value = "";
	var name = form.elements["name"];
	name.value = "";
	var addr = form.elements["addr"];
	var length = addr.options.length;
	for(var i = 0; i < length - 1; i++) {
		addr.options.remove(1);
	}
	if(user.value == null || user.value.length == 0) return;
	var postContent = coco.parseParams([{name:"user", value:user.value}]);
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code <= 0) {
			alert(this.msg);
			user.value = "";
			user.focus();
			return;
		}
		var obj;
		eval("obj = " + this.msg + ";");
		abbr.value = obj.abbr;
		name.value = obj.name;
		var shdzs = obj.shdz;
		var vs = shdzs.split(",");
		var opt;
		if(vs.length > 0) {
			for(var v in vs) {
				opt = document.createElement("OPTION");
				addr.options.add(opt);
				opt.innerText = vs[v];
				opt.value = vs[v];
			}
			addr.options[1].selected = true;
		}
	};
	ajax.post("getYong.do", postContent);
}
//-->
</SCRIPT>
</head>
<body>
<ui:page title="次级品订货合同管理">
<f:page action="indexCjp.do" file="listCjp.jsp">
<input type="hidden" name="ids" />
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="17%" /><col width="10%" /><col width="17%" /><col width="10%" /><col width="17%" /><col width="19%" /></colgroup>
<tr>
<th style="text-align: left;">运用规格</th>
<td><ui:input name="yuny" maxlength="2" /></td>
<th >表面</th>
<td><ui:input name="face" maxlength="2" /></td>
<th style="text-align: left;">现品状况</th>
<td><ui:select name="xpzk" list="${xpzks }" onchange="doQuery(this);" /></td>
<th style="text-align: left;">产量代码&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="chan" list="${chan }" onchange="doQuery(this);" /></th>
</tr>
<tr>
<th style="text-align: left;">尺寸.厚</th>
<td><ui:number name="xphoS" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true"/>至<ui:number name="xphoE" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true"/></td>
<th style="text-align: left;">尺寸.宽</th>
<td><ui:number name="xpknS" maxlength="7" scale="6" precision="2" title="尺寸.宽" positive="true"/>至<ui:number name="xpknE" maxlength="7" scale="6" precision="2" title="尺寸.宽" positive="true"/></td>
<th style="text-align: left;">尺寸.长</th>
<td><ui:number name="xpcnS" maxlength="7" scale="6" precision="2" title="尺寸.长" positive="true"/>至<ui:number name="xpcnE" maxlength="7" scale="6" precision="2" title="尺寸.长" positive="true"/></td>
<td>&nbsp;</td>
</tr>

<tr>
<th style="text-align: left;">合计保税品</th>
<td><ui:number name="hjbs" maxlength="8" scale="7" precision="3" title="合计保税品" readonly="true" /></td>
<th style="text-align: left;">合计制品</th>
<td><ui:number name="hjzp" maxlength="8" scale="7" precision="3" title="合计制品" readonly="true" /></td>
<th style="text-align: left;">合计数量&nbsp;&nbsp;&nbsp;&nbsp;<ui:int name="hjsl" maxlength="3" title="合计数量" readonly="true" /></th>
<td colspan="2" style="text-align: right;"><input type="button" class="button" value="查 询" onclick="doQuery(this);" /> <input type="button" class="button" value="制 作次级品订货合同" onclick="toZzCjp();" /></td>
</tr>
</table>
</f:page>
<ui:panel id="FspHt" title="制作次级品订货合同" popup="true" display="false" closable="true">
<form name="FspForm" method="post" action="doCjpHt.do" >
<input type="hidden" name="ids" /><input type="hidden" name="xpzk" /><input type="hidden" name="chan" />
<table class="form" width="100%">
<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="30%" /><col width="20%" /></colgroup>
<tr>
<th style="text-align: left;">订货号</th> 
<td><ui:input name="dhno" title="订货号" maxlength="7" required="true" /></td>
<th style="text-align: left;">交货日期</th> 
<td><ui:date name="jhqi" required="true" /></td>
<th style="text-align: left;">用户代码&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="user" title="用户代码" maxlength="4" required="true" onblur="getYong(this);" /></th>
</tr>
<tr>
<th style="text-align: left;">用户名称</th>
<td><ui:input name="name" title="用户名称" maxlength="17" required="true" readonly="true" /><input type="hidden" name="abbr" /></td>
<th style="text-align: left;">到达地点</th>
<td colspan="2"><ui:select name="addr" list="" prop="nn:1;" headerText="请选择" headerValue=""/></td>
</tr>
<tr>
<th style="text-align: left;">合同单价</th>
<td><ui:number name="htdj" maxlength="8" scale="7" precision="2" positive="true" /></td>
<td colspan="2">&nbsp;</td>
</table>
<div class="submit">
<input type="button" class="button" value="保    存" onclick="doCjpHt(this);"/>
<input type="button" class="button" value="关    闭" onclick="coco.hidePage('FspHt');" />
</div>
</form>
</ui:panel>
</ui:page>
</body>
</html>