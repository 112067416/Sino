<%@page contentType="text/html; charset=UTF-8"%><%@taglib prefix="ui"
	uri="/ui"%><%@taglib prefix="sys" uri="/sys"%><%@taglib prefix="sino"
	uri="/sino"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>表单元素</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="../js/calendar.js"></script>
<script type="text/javascript">

	var tableform = new TableForm("DataTbl");

	function view(form) {
		tableform.formatTblForm("items",1);
		var content = cocoform.postContent(form);
		alert(content);
	}
</script>
</head>
<body>
<form name="DataForm">
<table id="DataTbl" width="98%" align="center"
	style="margin: 20px auto;" class="form">
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<tr>
		<td><input type="checkbox" xu.target="ids" /></td>
		<th style="text-align: center;">供应商<br>
		合同NO</th>
		<th style="text-align: center;">行号</th>
		<th style="text-align: center;">制造商<br>
		规格略称</th>
		<th style="text-align: center;">中日达<br>
		规格略称</th>
		<th style="text-align: center;">品种</th>
		<th style="text-align: center;">尺寸</th>
		<th style="text-align: center;">重量<br>
		（吨）</th>
		<th style="text-align: center;">等级</th>
		<th style="text-align: center;">表面</th>
		<th style="text-align: center;">单价</th>
		<th style="text-align: center;">内径</th>
	</tr>
	<tr>
		<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="htno" maxlength="8" title="供应商合同NO(手工设定)" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:int
			name="line" maxlength="2" title="行号" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="zzgg" maxlength="15" title="制造商规格略称" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="zrgg" maxlength="16" title="中日达规格略称" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:int
			name="pzno" maxlength="2" title="品种代码" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:number
			name="kuan" maxlength="7" title="尺寸.宽" />*<ui:number name="houu"
			maxlength="5" title="尺寸.厚" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:number
			name="htzl" maxlength="9" title="实际重量" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="fpdj" maxlength="3" title="等级" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="face" maxlength="2" title="表面" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:number
			name="htdj" maxlength="8" title="采购单价" /></td>
		<td nowrap="nowrap" style="text-align: center;"><sys:codeBox name="neij" code="#020" val="2" /></td>
	</tr>
</table>
<div class="submit"><input type="button" class="button"
	value="查看填写数据" onclick="view(this.form)" /></div>
</form>
<table id="HidTbl" style="display: none;">
	<tr>
		<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>

		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="htno" maxlength="8" title="供应商合同NO(手工设定)" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:int
			name="line" maxlength="2" title="行号" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="zzgg" maxlength="15" title="制造商规格略称" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="zrgg" maxlength="16" title="中日达规格略称" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:int
			name="pzno" maxlength="2" title="品种代码" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:number
			name="kuan" maxlength="7" title="尺寸.宽" />*<ui:number name="houu"
			maxlength="5" title="尺寸.厚" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:number
			name="htzl" maxlength="9" title="实际重量" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="fpdj" maxlength="3" title="等级" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:input
			name="face" maxlength="2" title="表面" /></td>
		<td nowrap="nowrap" style="text-align: center;"><ui:number
			name="htdj" maxlength="8" title="采购单价" /></td>
		<td nowrap="nowrap" style="text-align: center;"><sys:codeBox name="neij" code="#020" val="2" /></td>
	</tr>
</table>
</body>
</html>