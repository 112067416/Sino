<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>检查证明书编辑</title>	
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<SCRIPT type="text/javascript">
<!--
var tableForm = new TableForm("ItemTbl");
tableForm.callback = function() {
	if(this.code > 0) {
		alert("保存成功");
		parent.cocoframe.close(parent.cocoframe.pageCurr);
	}
	else alert(this.msg);
};

function update() {
	if(!confirm("是否确定保存?")) return;
	tableForm.submit("updateAll.do");
}

function setChkValue(obj) {
	if(obj.checked) {
		obj.value = "1";
	} else {
		obj.value = "";
	}
}
//-->
</SCRIPT>
</head>
<body>
<ui:page  title="修改制品检查证明书" >
<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="update.do" >
<input type="hidden" name="filed" />
<input type="hidden" name="id" />
<table class="form" width="100%">
<colgroup><col width="7%" /><col width="10%" /><col width="9%" /><col width="15%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="20%" /><col width="9%" /></colgroup>
<tr>
<th>出货日期</th>
<td>&nbsp;&nbsp; <f:v value="jczms.zzri" format="yyyy-MM-dd"  /></td>
<th>用户略称</th>
<td>&nbsp;&nbsp; ${jczms.abbr }</td>
<th>送货单No.</th>
<td>&nbsp;&nbsp; ${jczms.zxno }</td>
<th>检查证明书No.</th>
<td>${jczms.jcno }</td>
<td><input type="button" class="button" value="保  存" onclick="update();" /></td>
</tr>
</table>
</form>
<table id="ItemTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="10%"/><col width="7%"/><col width="7%"/><col width="7%"/><col width="7%"/><col width="7%"/><col width="7%"/><col width="7%"/>
<col width="7%" /><col width="7%"/><col width="7%"/><col width="10%"/><col width="10%"/></colgroup>
<tr class="page-head" align="center">
<th rowspan="2">制品号</th>
<th rowspan="2">钢号</th>
<th rowspan="2">重量</th>
<th rowspan="2">件数</th>
<th rowspan="2">C</th>
<th rowspan="2">Si</th>
<th rowspan="2">Mn</th>
<th rowspan="2">P</th>
<th rowspan="2">S</th>
<th colspan="2">附着量</th>
<th rowspan="2">硬度</th>	
<th rowspan="2">是否解除<br />硬度判定</th>	
</tr>
<tr>
<th>正面</th>
<th>反面</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>>
<td>${item.zpno }<input type="hidden" name="id" value="${item.id }" /></td>
<td>${item.chui }</td>
<td>${item.jszl }</td>
<td>${item.jssu }</td>
<td><ui:input name="cfcc" value="item.cfcc" maxlength="4" title="C" /></td>
<td><ui:input name="cfsi" value="item.cfsi" maxlength="2" title="Si" /></td>
<td><ui:input name="cfmn" value="item.cfmn" maxlength="2" title="Mn" /></td>
<td><ui:input name="cfpp" value="item.cfpp" maxlength="2" title="P" /></td>
<td><ui:input name="cfss" value="item.cfss" maxlength="2" title="S" /></td>
<td><ui:number name="sczm" value="item.sczm" maxlength="5" scale="4" precision="2" title="附着量(正面)" /></td>
<td><ui:number name="scfm" value="item.scfm" maxlength="5" scale="4" precision="2" title="附着量(反面)" /></td>
<td style="text-align: center;"><ui:int name="ying" value="item.ying" maxlength="2" title="硬度" /></td>
<td style="text-align: center;"><input type="checkbox" name="cfy1" value="${item.cfy1 }" <c:if test="${item.cfy1 != null && item.cfy1  == \"1\"}">checked</c:if> onclick="setChkValue(this);" /></td>
</tr>
</c:forEach>
<tr>
	<td colspan="2" align="center">合计</td>
	<td>${chzl }  T</td>
	<td>${chsu }</td>
	<td colspan="9" style="text-align: right;" >&nbsp;</td>
</tr>
</table>
</ui:page>
</body>
</html>