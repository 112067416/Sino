<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<script type="text/javascript">
	<!--
	var menuId = null;
	// 查看指示书详细
	function doView() {
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ViewArea");
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			coco.showPage('View',{center:true,top:50,width:"80%"});
		};
		ajax.post("view.do", "zsno="+ contextmenu.selectedId());
		//menuId = contextmenu.selectedId();
		//parent.cocoframe.open("SLZZS", "SL指示书详情", "/sino/zs/view.do?zsno="+menuId, true,null,true);
	}

	// 撤消指示书
	function doUndo() {
		menuId = contextmenu.selectedId();
		menuId = menuId ? menuId.replace(/(^\s+)|(\s+$)/g, "") : null;
		if(menuId == null || menuId.length == 0) {
			alert("无法获取指示书号");
			return;
		}
		if(!confirm("确定撤消指示书吗?")) return false;
		var ajax = new Cocoajax();
		ajax.callback = function () {
			if(this.code > 0) {
				alert("撤消指示书成功");
				cocopage.refresh();
			}
			else {
				coco.alert(this.msg);
			}
		};
		ajax.post("cx.do", "zsno=" + menuId);
	}
	function showZsno(){
		var jbno=document.getElementById("jbno").value;
		if(jbno==null||jbno==""){
			document.getElementById("zsno").value="";
			document.getElementById("zsnoNO").value="";
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function () {
			if(this.code > 0) {
				if(this.result=="000000"){
					document.getElementById("zsno").value="";
					document.getElementById("zsnoNO").value="000000";
				}else{
					document.getElementById("zsno").value=this.result;
					document.getElementById("zsnoNO").value=this.result;
				}
				
			}
			
		};
		ajax.post("getSLZsno.do", "jbno=" + jbno);
	}
	// 打印指示书
	function doPrint() {
		if(!window.confirm("确定打印吗?")) return;
		var zsno=contextmenu.selectedId();
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0){
				coco.alert(this.msg);
				return;
			}
			document.getElementById("PrintFrame").src = "/sino/dy/zss!2.do?zsno="+zsno;
			
		};
		ajax.post("updateSLZsPrint.do", "ids="+zsno);
	}
	//阅读附件
	function view(ylno) {
		var height = screen.availHeight;
	  	var width = screen.availWidth;
		window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
	}
	//保存排序
	function saveDyZzs(el) {
		if(!confirm("确定保存吗?")) return false;
	//	var oTbl = document.getElementById("DataTbl");
	//	var rows = oTbl.rows;
	//	var contentSort = "";
	//	for(var i = 1; i < oTbl.rows.length; i++) {
	//		contentSort += "&sorts=" +coco.getAttr(oTbl.rows[i], "xu.id")+"-"+oTbl.rows[i].cells[0].childNodes[0].value;
	//	}
		var content;
		var oForm = el.form;
		var sorts = oForm.elements["sort"];
		var zsnos = oForm.elements["$zsno"];
		var el1, el2;
		if(sorts == null) {
			alert("指示书数据为空");
			return;
		}
		if(sorts.tagName != null) {
			content = "sorts=" + zsnos.value + "-" + sorts.value;
		} else {
			for(var i = 0; i < sorts.length; i++) {
				el1 = sorts[i];
				el2 = zsnos[i];
				if(i == 0) {
					content = "sorts=" + el2.value + "-" + el1.value;
					continue;
				}
				content = content + "&sorts=" + el2.value + "-" + el1.value;
			}
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code>0){
				alert("保存成功");
				cocopage.query();
			}
			else{
				coco.alert(this.msg);
			}
		};
		ajax.post("saveSLDyZzs.do", content);
	}
	function doFp(oTr) {
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			cocoform.fillResult("DataForm", this.result);
			coco.showPage("Detail",{center:true,top:80,width:"40%"});
		};
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var zsno = coco.getAttr(oTr, "xu.id");
		var content = coco.parseParam("zsno", zsno);
		ajax.post("get.do", content);
	}
	//保存分派
	function saveFp(){
		var dataForm=document.forms["DataForm"];
		var zsno=dataForm.elements["zsno"].value;
		var remk=dataForm.elements["remk"].value;
		if(zsno==""){
			alert("指示书不能为空！");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code>0){
				alert("保存成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			}
			else{
				coco.alert(this.msg);
			}
		};
		var key="zsno="+zsno+"&remk="+remk;
		ajax.post("updateREMK.do",key);
	}
	
	//-->
	</script>
	</head>
<body>
<ui:page title="未完成指示书列表">
	<f:page file="listWwc.jsp" action="indexWwc!${type}.do">
	<table width="96%" align="center" style="margin: 10px 0;" class="form">
		<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="30%" /></colgroup>
		<tr>
			<th style="text-align: left;">指示书NO</th>
			<td><ui:input name="zsno" maxlength="6" title="指示书No" /><input type="hidden" id="elin" name="elin" value="${elin}" /></td>
			<th style="text-align: left;">订货NO.</th>
			<td><ui:input name="dhno" maxlength="9" /></td>
			<th style="text-align: left;">卷板NO.</th>
			<td><ui:input name="jbno"  maxlength="11" /></td>
			<th style="text-align: left;">作成时间</th>
			<td><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="查 询" onclick="cocopage.query();" />	
		<input type="button" id="pais" class="button" value="保存排序" onclick="saveDyZzs(this);" />
	</div>
</f:page>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
<br/>
</ui:page>
<ui:panel id="Detail" title="指示书备注" popup="true" display="false" closable="true" >
<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="" >
 	<table id="FpTbl" width="96%" align="center" class="form" style="margin-top: 10px;">
		<tr>
			<th style="text-align: left;">指示书号</th>
			<td><ui:input name="zsno" maxlength="6" title="指示书No" required="true" readonly="true"/></td>
		</tr>
		<tr>
			<th style="text-align: left; vertical-align: middle;">备注</th>
			<td><ui:textarea name="remk" rows="4" cols="30"  /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="确定(F)" onclick="saveFp();" accesskey="s" />
		<input type="button" class="button" value="关闭(C)" accesskey="c" onclick="coco.hidePage('Detail');" />
	</div>
 </form>
</ui:panel>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<script type="text/javascript">
<!--
	var type = '${type}';
	if(type == "2" ){
		contextmenu.putMenus("menu", [["查看指示书", "view.gif", "doView()"],
			                          	 ["打印指示书", "exe.gif", "doPrint()"]]);
		document.getElementById("pais").style.display = "none";
	}
	if(type == "1" ){
		contextmenu.putMenus("menu", [["查看指示书", "view.gif", "doView()"],
			                          	 ["打印指示书", "exe.gif", "doPrint()"],
			                          	 ["修改备注", "edit.gif", "doFp()"]
		]);
	}
//-->
</script>
</body>
</html>