<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><c:choose><c:when test="${type == 2}">原板查看</c:when><c:otherwise>原板维护</c:otherwise></c:choose></title>	
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<SCRIPT type="text/javascript">
<!--
var type = '${type}';
if(type == "1" ){
	contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["修改海关加价", "edit.gif", "toHgjj()"],["原板商品检查书", "view.gif", "toSpjcs()"],["删除", "del.gif", "toDel()"]]);
}
if(type == "2" ){
	contextmenu.putMenus("menu", [["查看", "edit.gif", "toView()"]]);
}	
function toView(oTr) {
	if(oTr == null) oTr = contextmenu.selectedTarget();
	var vKey1 = coco.getAttr(oTr, "key1");
	var postContent = coco.parseParams([{name : "jbno",value: vKey1}]);
	var ajax = new Cocoajax();
	ajax.dataDiv = document.getElementById("ViewArea");
	ajax.callback = function() {
		if (this.code <= 0) {
			coco.alert(this.msg);
			return;
		}
		coco.showPage('View',{center:true,top:50,width:"80%"});
	};
	ajax.post("getYc.do", postContent, "POST");
}
	function toModify(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var vKey1 = coco.getAttr(oTr, "key1");
		var postContent = coco.parseParams([{name : "jbno",value: vKey1}]);
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ViewArea");
		ajax.callback = function() {
			if (this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			coco.showPage('View',{center:true,top:50,width:"80%"});
		};
		ajax.post("getForUpdate.do", postContent, "POST");
	}
	//单条删除
	function toDel(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key = coco.getAttr(oTr, "key1");
		var postContent = coco.parseParams([{name:"ids",value:key}]);
		if(!confirm("确定删除吗?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			cocopage.refresh();
		};
		ajax.post("dels.do", postContent);
	}
	//批量删除
	function toDels() {
		var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
		if(ids == null || ids.length == 0) {
			alert("请选择要做删除操作的记录");
			return;
		}
		if(!confirm("确定删除吗?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			cocopage.refresh();
		};
		ajax.post("dels.do", ids);
	}
	//计算卷板长
    function calculatJbcn(){
        var form = document.forms["DataForm"];
        var zl = form.elements["tun"].value;
        var kuan = form.elements["xpkn"].value;
        var hou = form.elements["xpho"].value;
        if(zl == null || zl == "") {
				return;
		}
        var chang = form.elements["jbcn"];
        var ajax=new Cocoajax();
        var postContent = coco.parseParams([{name : "kuan",value: kuan},{name:"hou",value: hou},{name:"zl",value: zl}]);
        ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
			    chang.value=this.msg;
			};
		ajax.post("getJbcn.do",postContent,"POST");
    }
     //获取合同数据
	function doLand(){
		var oForm = document.forms["DataForm"];
	    var htno = oForm.elements["ybno"].value;
	    var line = oForm.elements["line"].value;
	    var jbno = oForm.elements["jbno"].value;
	    var zzsj = oForm.elements["zzsj"].value;
	    var tun = oForm.elements["tun"].value;
	    var jbcn = oForm.elements["jbcn"].value;
	    var blny = oForm.elements["blny"].value;
	    var ship = oForm.elements["ship"].value;
	    if(htno == null || htno.length == 0 || line == null || line.length == 0){
            alert("请输入供应商合同No.");
            return ;
	    }
	    var postContent = coco.parseParams([{name : "htno",value: htno},{name:"line",value: line}]);
	    var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			cocoform.fillResult(oForm, this.result, null, true);
			oForm.elements["jbno"].value = jbno;
			oForm.elements["zzsj"].value = zzsj;
			oForm.elements["tun"].value  = tun; 
			oForm.elements["jbcn"].value = jbcn;
			oForm.elements["blny"].value = blny;
			oForm.elements["ship"].value = ship;
						
		};
		ajax.post("getWwht.do", postContent, "POST");
	}
	//原板检查证明书修改
	function toSpjcs() {
		var form = document.forms["SpjcsForm"];
		var jbno = contextmenu.selectedId();
		var ajax = new Cocoajax();
		var postContent = coco.parseParams([{name : "jbno",value: jbno}]);
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			try {
				var obj = null;
				eval("obj = " + this.result + ";");
				cocoform.fillObject(form, obj);
				form.elements["$type"].value = "modify";
				coco.showPage("SpjcsDetail", { center : true, top : 50, width : "80%" });
			} catch (e) {
				alert("系统出错:\n" + e.description);
			}
		};
		ajax.post("spjcs/load.do", postContent, "POST");
	}
	function success() {
		alert("保存成功");
		coco.hidePage('View');
		cocopage.refresh();
	}
	function successj() {
		alert("保存成功");
		coco.hidePage('SpjcsDetail');
	}
	//进入修改海关加价页面
	function toHgjj(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var jbno = coco.getAttr(oTr, "key1");
		var form = document.forms["HgjjForm"];
		var ajax = new Cocoajax();
		var postContent = coco.parseParams([{name : "jbno",value: jbno}]);
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			try {
				var obj = null;
				eval("obj = " + this.result + ";");
				form.elements["jbno"].value = jbno;
				form.elements["hgjj"].value = obj.hgjj;
				coco.showPage("HgjjDetail",{center:true,top:150,width:"80%"});
			} catch (e) {
				alert("系统出错:\n" + e.description);
			}
		};
		ajax.post("loadHgjj.do", postContent, "POST");
	}

	//保存海关加价
	function saveHgjj(oInput) {
		var form = oInput.form;
		cocoform.submit(form, function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			alert("保存成功");
			coco.hidePage('HgjjDetail');
			cocopage.refresh();
			return;
			}, "是否确定保存?", null, oInput);
	}
//-->
</SCRIPT>
</head>
<body>
	<ui:page title="${title }">
	<f:page action="index!${type}.do" file="list.jsp">
	<table width="100%" class="form">
		<colgroup>
		<col width="15%" />
		<col width="15%" />
		<col width="20%" />
		<col width="20%" />
		<col width="15%" />
		<col width="15%" />
		</colgroup>
	<tr>
	<th style="text-align: left;">原板用户</th>
	<td><ui:input name="ycbr" maxlength="20" /></td>
	<th style="text-align: left;">船名</th>
	<td><ui:input name="ship" maxlength="20" /></td>
	<th style="text-align: left;">状态</th>
	<td><ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_YB_STAT'" headerText="全部" headerValue="" value="page.stat" /></td>
	</tr>
	<tr>
	<th style="text-align: left;">原材卷板NO</th>
	<td><ui:input name="coilNo" maxlength="7" title="原材卷板NO" /></td>
	<th style="text-align: left;">制造商卷板NO</th>
	<td><ui:input name="zzsj" maxlength="10" title="制造商卷板NO" /></td>
	<th style="text-align: left;">供应商合同NO</th>
	<td><ui:input name="ybno" maxlength="8" title="合同NO" />-<ui:number name="line" maxlength="3" /></td>
	</tr>
	</table>
	
	<div class="submit">
	<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
	<c:if test="${type == 1}"><input type="button" class="button" value="删 除" onclick="toDels()" /></c:if>
	</div>
	</f:page>
	</ui:page>
	<ui:panel id="View" title="修改原板信息" popup="true" display="false" closable="true" >
		<DIV id="ViewArea" ></DIV>
	</ui:panel>
	<ui:panel id="SpjcsDetail" title="原板商品检查书" popup="true" display="false" closable="true">
	<form action="spjcs/save.do" xu.ajax="" xu.r="" xu.s="successj();" xu.color="1" name="SpjcsForm" method="post" >
	 <input type="hidden" name="$type" />
	<table width="98%" align="center" style="margin: 20px auto;"
	class="form">
	<colgroup>
	<col width="10%" />
	<col width="25%" />
	<col width="20%" />
	<col width="20%" />
	<col width="10%" />
	<col width="15%" />
	</colgroup>
	<tr>
		<th style="text-align: left;">船名</th>
		<td><ui:input name="ship" maxlength="20"  readonly="true" title="原材卷板DB的船名"  /></td>
		<th style="text-align: left;">原材卷板NO</th>
		<td><ui:input name="jbno" maxlength="6" readonly="true"  title="原材卷板DB的COIL.No." /></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<th style="text-align: left;">C</th>
		<td><ui:input name="cfcc" maxlength="2" title="检查证明DB的成分值（C）" /></td>
		<th style="text-align: left;">Si</th>
		<td><ui:input name="cfsi" maxlength="2" title="检查证明DB的成分值（Si）"  /></td>
		<th style="text-align: left;">Mn</th>
		<td><ui:input name="cfmn" maxlength="2" title="检查证明DB的成分值（Mn）"/></td>
	</tr>
	<tr>
		<th style="text-align: left;">P</th>
		<td><ui:input name="cfpp" maxlength="2" title="检查证明DB的成分值（P）" /></td>
		<th style="text-align: left;">S</th>
		<td><ui:input name="cfss" maxlength="2" title="检查证明DB的成分值（S）" /></td>
		<th style="text-align: left;">Sol.Al</th>
		<td><ui:input name="cfso" maxlength="2" title="检查证明DB的成分值（Sol.Al）"  /></td>
	</tr>
	<tr>
		<th style="text-align: left;">硬度</th>
		<td><ui:input name="ying" maxlength="2" title="检查证明DB的成分值硬度" /></td>
		<th style="text-align: left;">YP</th>
		<td><ui:number name="yp" scale="3" precision="1" title="YP" required="true" /></td>
		<th style="text-align: left;">吹练NO</th>
		<td><ui:input name="chui" maxlength="10" title="检查证明DB的成分值吹练NO" /></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	</table>
	<div class="opt-btm"><input type="button" onclick="cocoform.submit(this, null, '是否确定保存?');"  class="button" value="保 存" /> <input type="button" class="button"
		value="关 闭" onclick="coco.hidePage('SpjcsDetail')" /></div>
	</form>
	</ui:panel>
	<ui:panel id="HgjjDetail" title="修改海关加价" popup="true" display="false" closable="true">
		<form name="HgjjForm" method="post" action="updateHgjj.do" >
		<input type="hidden" name="jbno"/>
		<table class="form" width="100%">
		<colgroup><col width="10%" /><col width="90%" /></colgroup>
		<tr>
		<th style="text-align: left;">海关加价</th> 
		<td><ui:number name="hgjj" scale="7" precision="2" /></td>
		</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="保    存" onclick="saveHgjj(this);"/>
		<input type="button" class="button" value="关    闭" onclick="coco.hidePage('HgjjDetail');" />
		</div>
		</form>
	</ui:panel>
	</body>
</html>