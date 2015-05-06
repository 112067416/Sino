<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订货合同进度管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<style type="text/css">
TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
TABLE.pagination1 th { font-size: 12px; color: #003449; height: 20px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
TABLE.pagination1 td { font-size: 13px; color: #333333; height: 25px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
TABLE.pagination1 tr {background-color: #F7F7F7;}
TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
</style>
<SCRIPT type="text/javascript">
<!--
contextmenu.putMenus("menu", [["新增生产指示", "edit.gif", "toSczs()"],["新增出货指示", "edit.gif", "toChzs()"]]);

//进入次日出货联络单登录页面
function toChzs(oTr) {
	if(oTr == null) oTr = contextmenu.selectedTarget();
	var vKey1 = coco.getAttr(oTr, "key1");
	parent.cocoframe.open("crchllddl", "次日出货联络单登录", "/sino/yygl/chlld/index.do?dhnoAndLine="+vKey1, null, null, true);
}
//查看已登录的出货联络单
function viewChzs() {
	parent.cocoframe.open("crchllddl", "次日出货联络单登录", "/sino/yygl/chlld/index.do", null, null, true);
}


//进入生产指示单登录页面
function toSczs(oTr) {
	if(oTr == null) oTr = contextmenu.selectedTarget();
	var vKey1 = coco.getAttr(oTr, "key1");
	parent.cocoframe.open("sczsddl", "生产指示单登录", "/sino/yygl/sczs/index.do?dhnoAndLine="+vKey1, null, null, true);
}

//查看已登录生产指示单
function viewChzs() {
	parent.cocoframe.open("sczsddl", "生产指示单登录", "/sino/yygl/sczs/index.do", null, null, true);
}
//撤消合同完成
function cancel() {
	var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
	if(postContent == null || postContent.length == 0){
		alert("请选择要做撤消订货合同完成操作的记录");
		return;
	}
	if(!window.confirm("确定撤消订货合同完成吗?")) return;
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if (this.code <= 0) {
			alert(this.msg);
			return;
		}
		alert("撤消订货合同完成操作成功");
		cocopage.refresh();
	};
	ajax.post("cancelFinish.do", postContent);
}
//合同完成
function doFinish() {
	var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
	if(postContent == null || postContent.length == 0){
		alert("请选择要做订货合同完成操作的记录");
		return;
	}
    if(!confirm("确定订货合同完成吗?")) return false;
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if (this.code <= 0) {
			alert(this.msg);
			return;
		}
		alert("订货合同完成操作成功");
		cocopage.refresh();
	};
	ajax.post("finish.do", postContent);
}

function setChecked(el) {
	if(el.checked) {
		el.value = "Y";
	} else {
		el.value = "";
	}
	cocopage.query();
	return;
}
//-->
</SCRIPT>
</head>
<body>
<ui:page title="订货合同进度管理">
<f:page action="indexDhjd.do" file="listDhjd.jsp">
<table width="100%" class="form">
<colgroup><col width="8%" /><col width="25%" /><col width="8%" /><col width="25%" /><col width="12%" /><col width="22%" /></colgroup>
<tr>
<th style="text-align: left;">订货No.</th>
<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
<th style="text-align: left;">用户代码</th>
<td><ui:input name="userBegin" maxlength="4" />至<ui:input name="userEnd" maxlength="4"/></td>
<th>用户中文名</th>
<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
</tr>
<tr>
<th style="text-align: left;">交货期</th>
<td><ui:date name="jhqiBegin" prop="calendar: true;" />至<ui:date name="jhqiEnd" prop="calendar: true;" /></td>
<th style="text-align: left;">创建日期</th>
<td><ui:date name="creaBegin" prop="calendar: true;" />至<ui:date name="creaEnd" prop="calendar: true;" /></td>
<th style="text-align: left;">订货合同状态</th>
<td>
<ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_DH_STAT'" headerText="全部" headerValue="" value="page.stat" onchange="cocopage.query();" />
&nbsp;&nbsp;&nbsp;<input type="checkbox" name="finish" onclick="setChecked(this);" /><span style="font-size: 15px; color: red;">显示未完成</span>
</td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
<input type="button" class="button" value="查看出货指示" onclick="viewChzs();" />
<input type="button" class="button" value="查看生产指示" onclick="viewChzs();" />
<input type="button" class="button" value="合同完成" onclick="doFinish();" />
<input type="button" class="button" value="撤消合同完成" onclick="cancel();" />
</div>
</f:page>
</ui:page>
</body>
</html>