<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户主文件</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript">
contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["维护送货地址", "settings.gif", "toSetting()"],["设备客户尺寸和用途", "settings.gif", "setKhSize()"],["查看", "preview.gif", "toView()"]]);
	function doAdd() {
		parent.cocoframe.open("editYong", "编辑用户信息", "/sino/cmn/yong/indexEdit.do", null, null, true);
	}

	function toModify(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key1 = coco.getAttr(oTr, "id0");
		parent.cocoframe.open("editYong", "编辑用户信息", "/sino/cmn/yong/indexEdit.do?user="+key1, null, null, true);
	}

	function toSetting(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key1 = coco.getAttr(oTr, "id0");
		var ajax = new Cocoajax();
		var Shdz = document.getElementById("ShdzDiv");
		ajax.dataDiv = Shdz; 
		ajax.callback = function() {
			if (this.code <= 0) {
				alert(this.msg);
				return;
			}
			coco.showPage("ShdzArea", {center : true, top : 50, width : "80%" });
		};
		ajax.post("shdz.do", "user="+key1);
	}

	function setKhSize(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key1 = coco.getAttr(oTr, "id0");
		var ajax = new Cocoajax();
		var sizeDIV = document.getElementById("SizeDiv");
		ajax.dataDiv = sizeDIV; 
		ajax.callback = function() {
			if (this.code <= 0) {
				alert(this.msg);
				return;
			}
			coco.showPage("SizeArea", {center : true, top : 50, width : "80%" });
		};
		ajax.post("khSize.do", "user="+key1);
	}

	var tableForm = new TableForm("ItemTbl");
	tableForm.callback = function() {
		if(this.code > 0) {
			alert("保存成功");
			coco.hidePage("ShdzArea");
			return;
		}
		else coco.alert(this.msg);
	};
	
	function saveShdz(user) {
		tableForm.submit("shdz!save.do?user="+encodeURIComponent(user));
	}
	var tblSizeForm = new TableForm("ISizeTbl");
	tblSizeForm.callback = function() {
		if(this.code > 0) {
			alert("保存成功");
			coco.hidePage("SizeArea");
			return;
		}
		else coco.alert(this.msg);
	};
	
	function saveSize(user) {
		tblSizeForm.submit("size!save.do?user="+encodeURIComponent(user));
	}
	
	function toView(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key1 = coco.getAttr(oTr, "id0");
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ShdzDiv");
		ajax.post("view.do","user="+encodeURIComponent(key1));
		coco.showPage("ShdzArea",{center:true,top:50,width:"80%"});
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

	function doPrint(LODOP, content) {
		//	LODOP.SET_PRINTER_INDEXA("HPrint");
			LODOP.PRINT_INIT("YONGCHTJ-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("HP LaserJet 5200 Series PCL 5");
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(10, 0, 1100, 1100, content);
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();
	}

	function toPrint(el) {
		var form = el.form;
		var ids = form.elements["ids"].value;
		if(ids == null || ids.length == 0) {
			alert("请选择要打印的用户");
			return;
		}
		var users = ids.split(",");
		var index = 0;
		var postContent = coco.parseParam("user", users[index++]);
		var LODOP = document.getElementById("lodop");
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			doPrint(LODOP,this.result);
			if(index < users.length) {
				postContent =  coco.parseParam("user", users[index++]);
				ajax.post(path + "/sino/dy/yong!print.do", postContent);
			}
		};
		ajax.post(path + "/sino/dy/yong!print.do", postContent);
	}

	//复选框选中
	function doCheck(obj) {
		if(obj == null) {
			return;
		}
		//选中装箱指示书号
		var v = obj.value;
		var oForm = document.forms["PageForm_page"];
		var ids = oForm.elements["ids"].value;
		var rgExp;
		if(obj.checked) {
			if(ids == null || ids.length == 0) {
				oForm.elements["ids"].value = v;
				return;
			}
			oForm.elements["ids"].value = ids.replace(/(,)+$/g,'') + ',' + v;
		} else {
			var $v = v.replace(/^\(/gi,'\\(').replace(/\)/gi,'\\)');
			rgExp = new RegExp($v+',|('+$v+'$)' , "g");
			oForm.elements["ids"].value = ids.replace(rgExp, '');
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
		cocopage.query();
	}

	function doDel(el) {
		var form = el.form;
		var ids = form.elements["ids"].value;
		if(ids == null || ids.length == 0) {
			alert("请选择要做删除操作的用户");
			return;
		}
		if(!confirm("是否确定删除?")) return;
		var postContent = coco.parseParam("users", ids);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			alert("删除成功");
			form.elements["ids"].value = "";
			cocopage.refresh();
		};
		ajax.post("dels.do", postContent);
	}

	//进入设置发票参数的页面
	function toParam(el) {
		var qForm = el.form;
		var ids = qForm.elements["ids"].value;
		if(ids == null || ids.length == 0){
			alert("请选择要设置参数的用户");
			return;
		}
		var oForm = document.forms["ParamForm"];
		oForm.elements["users"].value = ids;
		coco.showPage("ParamDetail", {center : true, top : 90, width : "90%" });
	}

	//批量保存参数
	function setParam(obj) {
		var form = obj.form;
		var qForm = document.forms["PageForm_page"];
		cocoform.submit(form, function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("保存成功");
				form.elements["users"].value = "";
				qForm.elements["ids"].value = "";
				cocopage.refresh();
				coco.hidePage("ParamDetail");
			}, "是否确定保存?", null, obj);
	}
	//-->
</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="用户主文件">
<f:page action="index.do" file="data.jsp">
<input type="hidden" name="ids" />
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="24%" /><col width="10%" /><col width="23%" /><col width="10%" /><col width="23%" /></colgroup>
<tr>
<th>用户代码</th>
<td><ui:input name="user" maxlength="4" /></td>
<th>业务员</th>
<td><ui:input name="ddnm" maxlength="10" /></td>
<th>所属区域</th>
<td><ui:select name="region" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_REGION'" headerText="请选择" headerValue="" /></td>
</tr>
<tr>
<th>用户中文名</th>
<td><ui:input name="rsv1" /></td>
<th>商社代码</th>
<td><ui:input name="ssno" maxlength="3" />&nbsp;&nbsp;&nbsp;<ui:radiolist name="valid" list="'Y':'有效','N':'无效'" value="#Y" ></ui:radiolist></td>
<th>内外销</th>
<td><select name="nwai" class="normal">
	<option value="">全部</option>
	<option value="1">国内</option>
	<option value="2">国外</option>
</select></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="doQuery(this);" />
<input type="button" class="button" value="新 增" onclick="doAdd();" />
<input type="button" class="button" value="删 除" onclick="doDel(this);" />
<input type="button" class="button" value="批量设置参数" onclick="toParam(this);" />
<input type="button" class="button" value="打 印" onclick="toPrint(this);" />
</div>
</f:page>
</ui:page>
<ui:panel id="ShdzArea" title="送货地址" display="false" closable="true" popup="true">
<form name="ShdzForm" method="post">
<div id="ShdzDiv" class="scroll" style="width:100%;height:350px;"></div>
</form>
<table id="ItemTblTmp" style="display: none">
<tr>
<td align="center" ><input type="checkbox" name="ids" /><input type="hidden" name="id" /></td>
<td><ui:input name="addr" cssStyle="width:99%" /></td>
</tr>
</table>
</ui:panel>
<ui:panel id="SizeArea" title="设置客户尺寸和用途" display="false" closable="true" popup="true">
<form name="SizeForm" method="post">
<div id="SizeDiv" class="scroll" style="width:100%;height:350px;"></div>
</form>
<table id="ISizeTblTmp" style="display: none">
<tr>
<td align="center" ><input type="checkbox" name="ids" /><input type="hidden" name="id" /></td>
<td><ui:input name="yuny" value="item.yuny" maxlength="4" required="true" /></td>
<td><ui:number name="houu" maxlength="5" scale="4" precision="3" /></td>
<td><ui:number name="kuan" scale="6" precision="2" maxlength="7" /></td>
<td><ui:input name="face" maxlength="2" required="true" /></td>
<td><ui:input name="cdnm" maxlength="8" required="true" /></td>
</tr>
</table>
</ui:panel>
<ui:panel id="ParamDetail" title="批量设置参数" popup="true" display="false" closable="true">
<form name="ParamForm" method="post" action="setParams.do" >
<input type="hidden" name="users" />
<table width="100%" class="form" >
<colgroup><col width="10%" /><col width="20%" /><col width="20%" /><col width="10%" /><col width="40%" /></colgroup>
<tr>
<th>所属区域</th>
<td><ui:select name="region" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_REGION'" headerText="请选择" headerValue="" /></td>
<th><ui:radiolist name="valid" list="'Y':'有效','N':'无效'" value="#Y" ></ui:radiolist></th>
<th>业务员</th>
<td><ui:input name="ddnm" maxlength="10" /></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="保   存" onclick="setParam(this);" />
<input type="button" class="button" value="关   闭" onclick="coco.hidePage('ParamDetail')" />
</div>
</form>
</ui:panel>
</body>
</html>