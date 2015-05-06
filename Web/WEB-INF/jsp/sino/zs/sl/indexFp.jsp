<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<script type="text/javascript">
	<!--
	contextmenu.putMenus("menu", [["查看指示书", "view.gif", "doView()"],["撤消指示书", "del.gif", "doZssCx()"]]);
	// 分派指示书-弹出分派层
	function doFp(){
		var form = document.forms["PageForm_page"];
		var els = form.elements["ids"];
		var content = "";
		if(els.length > 0) {
			for(var i = 0; i < els.length; i++) {
				if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
			}
		}
		else {
			if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
		}
		if(content == "") {
			alert("请选择数据行");
			return;
		}
		coco.showPage("Detail",{center:true,top:80,width:"40%"});
	}
	//保存分派
	function saveFp(){
		var form = document.forms["PageForm_page"];
		var els = form.elements["ids"];
		var content = "";
		if(els.length > 0) {
			for(var i = 0; i < els.length; i++) {
				if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
			}
		}
		else {
			if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
		}
		if(content == "") {
			alert("请选择数据行");
			return;
		}
		var dataForm=document.forms["DataForm"];
		var jinj=dataForm.elements["jinj"].value;
		var remk=dataForm.elements["remk"].value;
		if(jinj==""){
			alert("紧急程度不能为空！");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code>0){
				alert("紧急设置成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			}
			else{
				coco.alert(this.msg);
			}
		};
		var key=content.substring(1)+"&jinj="+jinj+"&remk="+remk;;
		ajax.post("updateSLFP.do",key);
	}
	// 取消分派
	function doQxFp() {
		var form = document.forms["PageForm_page"];
		var els = form.elements["ids"];
		var content = "";
		if(els.length > 0) {
			for(var i = 0; i < els.length; i++) {
				if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
			}
		}
		else {
			if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
		}
		if(content == "") {
			alert("请选择数据行");
			return;
		}
		if(!window.confirm("确定要取消设置吗?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("取消设置成功");
			coco.hidePage('Detail');
			cocopage.refresh();
		};
		ajax.post("deleteSLFP.do", content.substring(1));
	
	}

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
	}

	// 撤消指示书
	function doZssCx() {
		var menuId = contextmenu.selectedId();
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
		ajax.post("zssCx.do", "zsno=" + menuId);
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
	
	//分派保存成功
	function success() {
		alert("设置成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	}
	//-->
	</script>
	</head>
<body>
<ui:page title="未完成指示书列表">
<f:page file="listFp.jsp" action="indexSLFp.do">
	<table width="96%" align="center" style="margin: 10px 0;" class="form">
		<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="30%" /></colgroup>
		<tr>
			<th>指示书NO</th>
			<td><ui:input name="zsno" maxlength="6" title="指示书No" /></td>
			<th>订货NO.</th>
			<td><ui:input name="dhno" maxlength="9" /></td>
			<th>卷板NO.</th>
			<td><ui:input name="jbno"  maxlength="11" /></td>
			<th>作成时间</th>
			<td><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="查 询" onclick="cocopage.query();" />	
		<input type="button" class="button" onclick="doFp()" value="设置紧急" accesskey="c">
		<input type="button" class="button" onclick="doQxFp()" value="取消紧急" accesskey="c">
	</div>
</f:page>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</ui:page>
<ui:panel id="Detail" title="指示书紧急设置" popup="true" display="false" closable="true" >
	<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="updateSLFP.do" >
	 	<table id="FpTbl" width="96%" align="center" class="form" style="margin-top: 10px;">
			<colgroup><col width="15%" /><col width="35%" /><col width="10%" /><col width="40%" /></colgroup>
			<tr>
				<th>是否紧急</th>
				<td><ui:select  name="jinj" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='ZSJJ'"    headerText="" headerValue=""/></td>
				<th>备注</th>
				<td><ui:textarea name="remk" rows="4" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="确  定" onclick="saveFp();" />
			<input type="button" class="button" value="关  闭" onclick="coco.hidePage('Detail');" />
		</div>
	 </form>
 </ui:panel>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
</body>
</html>