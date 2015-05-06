<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/global/header.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<style type="text/css">
TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; background-color: #0F88BB; color: #FFCC00;}
TABLE.pagination1 th { font-size: 11px; color: #003449; height: 21px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
TABLE.pagination1 td { font-size: 13px; color: #333333; height: 20px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
TABLE.pagination1 tr {background-color: #F7F7F7;}
TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
</style>
<script type="text/javascript">
<!--
	contextmenu.putMenus("menu1", [["已阅", "edit.gif", "toRead()"]]);
	contextmenu.putMenus("menu2", [["新增", "edit.gif", "toModify()"]]);
	//已阅药液管理记录
	function toRead(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var id = coco.getAttr(oTr, "xu.id");
		var postContent = coco.parseParam("id", id);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0 ) {
				coco.alert(this.msg);
				return;
			}
			cocopage.refresh();
		};
		ajax.post("readYygl.do", postContent);
	}
	//
	function toModify(oTr) {
		if(oTr == null)  oTr = contextmenu.selectedTarget();
		var id = coco.getAttr(oTr, "xu.id");
		var postContent = coco.parseParam("id", id);
		var ajax = new Cocoajax();
		ajax.dataDiv = YyglItemDIV;
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			coco.showPage("YyglItemDetail", {center : true, top : 50, width : "80%" });
		};
		ajax.post("getEtlyygljlItem.do", postContent);
	}
	//
	function toAdd() {
		var ajax = new Cocoajax();
		ajax.dataDiv = YyglItemDIV;
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			coco.showPage("YyglItemDetail", {center : true, top : 50, width : "80%" });
		};
		ajax.post("getEtlyygljlItem.do");
	}
	//
	function updateYyglItem(oInput) {
		cocoform.submit(oInput, function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("保存成功");
				coco.hidePage('YyglItemDetail');
				cocopage.refresh();
			},"是否确定保存?");
	}
	//
	function saveYygl(oInput) {
		cocoform.submit(oInput, function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			alert("保存成功");
			coco.hidePage('YyglDetail');
			cocopage.refresh();
		},"是否确定保存?");
	}
	//
	function getYygl() {
		var pForm = document.forms["PageForm_page"];
		var jlsj = pForm.elements["rqBegin"].value;
		if(jlsj == null || jlsj.length == 0) {
			alert("药液记录日期不能为空");
			pForm.elements["rqBegin"].focus();
			return;
		}
		var postContent = coco.parseParam("jlsj", jlsj);
		var ajax = new Cocoajax();
		ajax.dataDiv = YyglDIV;
		ajax.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			coco.showPage("YyglDetail", {center : true, top : 50, width : "80%" });
		};
		ajax.post("getYygl.do", postContent);
	}
	//
	function toPrint() {
		var pForm = document.forms["PageForm_page"];
		var jlsj = pForm.elements["rqBegin"].value;
		if(jlsj == null || jlsj.length == 0) {
			alert("药液记录日期不能为空");
			pForm.elements["rqBegin"].focus();
			return;
		}
		var postContent = coco.parseParam("jlsj", jlsj);
		var ajax = new Cocoajax();
		var LODOP = document.getElementById("lodop");
		ajax.callback = function() {
			if(this.code < 0) {
				coco.alert(this.msg); 
				return;
			}
			doPrint(LODOP, this.result);
		};
		ajax.post(path + "/sino/dy/yygl!print.do", postContent);
	}
	//
	function doPrint(LODOP, content) {
	//	LODOP.SET_PRINTER_INDEXA("HPrint");
		LODOP.SET_PRINT_PAGESIZE(2, "0", "0","A3");
		LODOP.SET_PRINT_STYLE("FontSize",12);
		LODOP.ADD_PRINT_HTM(0, 0, 1500, 1200, content);
		//打印预览
		LODOP.PREVIEW();
		//打印
	//	LODOP.PRINT();
	}
	
	window.setInterval(toRefresh, 3000);
	
	function toRefresh() {
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				cocopage.refresh();
			}
		};
		ajax.post("doRefreshYygl.do");
	}

//-->
</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="ETL药液管理记录">
<f:page action="indexYygl.do" file="listYygl.jsp">
<table width="100%" align="center" class="form">
<colgroup><col width="15%" /><col width="15%" /><col width="70%" /></colgroup>
<tr>
<th style="text-align: left;">药液记录日期</th>
<td><ui:date name="rqBegin" showCalendar="true" required="true" value="rq" onchange="cocopage.refresh();" /> </td>
<td><input type="button" class="button" value="查  询" onclick="cocopage.query();"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="设置液量" onclick="getYygl();"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="新  增" onclick="toAdd();"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="打  印" onclick="toPrint();"/></td>
</tr>
</table>
</f:page>
</ui:page>
<ui:panel id="YyglItemDetail" title="增加ETL药液管理记录" popup="true" display="false" closable="true">
<form name="YyglItemForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="updateYyglItem.do" >
<div id="YyglItemDIV"></div>
<div class="submit"><input type="button" class="button" value="保   存" onclick="updateYyglItem(this);"/>  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('YyglItemDetail');" /></div>
</form>
</ui:panel>
<ui:panel id="YyglDetail" title="设置液量" popup="true" display="false" closable="true">
<form name="YyglForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="saveYygl.do" >
<div id="YyglDIV"></div>
<div class="submit"><input type="button" class="button" value="保   存" onclick="saveYygl(this);"/>  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('YyglDetail');" /></div>
</form>
</ui:panel>
</body>
</html>