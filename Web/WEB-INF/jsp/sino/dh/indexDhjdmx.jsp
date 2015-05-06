<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="<%=request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript">
		<!--
		
		function outExcel(btn) {
			var form = btn.form;
			var el1 = form.elements["dhno"];
			var dhno = el1.value;
			if(dhno == null || dhno.length == 0) {
				alert("订货合同号不能为空");
				el1.focus();
				return;
			}
			var el2 = form.elements["lineStart"];
			var lineStart = el2.value;
			if(lineStart == null || lineStart.length == 0) {
				alert("订货合同起始行号不能为空");
				el2.focus();
				return;
			}
			var el3 = form.elements["lineEnd"];
			var lineEnd = el3.value;
			btn.disabled = true;
			var content = encodeURIComponent("dhno") + "=" + encodeURIComponent(dhno) + "&" + encodeURIComponent("lineStart") + "=" + encodeURIComponent(lineStart) + "&" + encodeURIComponent("lineEnd") + "=" + encodeURIComponent(lineEnd);
			var wo = document.getElementById("WebOffice");
			//wo.HideMenuItem(0x33);
			if(window.self == window.top) wo.LoadOriginalFile("./outDhjdmxExcel.do?"+content, "xls");
			else wo.LoadOriginalFile("<%=request.getContextPath()%>/sino/dh/outDhjdmxExcel.do?"+content, "xls");
			wo.hideMenuArea("hideall","","","");
			btn.disabled = false;
		}
		
		function doLoad() {
			var height = document.documentElement.clientHeight;
			var queryHeight = document.getElementById("QueryArea").offsetHeight;
			document.getElementById("ExcelArea").style.height = (height - queryHeight- 3) + "px";
		}

		function toUpperCase(el) {
			var form = el.form;
			var v = el.value;
			if(el.name == 'dhno') {
				if(v != null && v.length > 0) el.value = el.value.toUpperCase();
				if(v.length == el.getAttribute("maxlength")) form.elements["lineStart"].focus();
			} else if(el.name == 'lineStart') {
				if(v == null || v.length == 0) return;
				if(v.length == el.getAttribute("maxlength")) form.elements["lineEnd"].focus();
			}
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
	<div id="QueryArea">
		<form name="DataForm" method="post" >
		<table align="center" class="form" width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
				<col width="100" />
				<col width="300" />
				<col align="center" />
			</colgroup>
			<tr>
				<th>订货No.</th>
				<td><input name="dhno" type="text" maxlength="7" class="normal" style="width: 72px;" onkeyup="toUpperCase(this);" />-<input type="text" name="lineStart" maxlength="2" class="normal" style="width: 22px;" onkeyup="toUpperCase(this);" />至<input type="text" name="lineEnd" maxlength="2" class="normal" style="width: 22px;" /></td>
				<td><input type="button" class="button" value="检索(Q)" accesskey="q" onclick="outExcel(this);" /></td>
			</tr>
		</table>
		</form>
		</div>
		<div id="ExcelArea"><OBJECT id="WebOffice" style="LEFT: 0px; TOP: 0px; WIDTH: 100%;" height="100%" classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5"  codebase="../../activex/weboffice_v6.0.5.0.cab#V6,0,5,0 ">
			<PARAM NAME="_ExtentX" VALUE="6350">
			<PARAM NAME="_ExtentY" VALUE="6350">
			</OBJECT></div>
	</body>
</html>