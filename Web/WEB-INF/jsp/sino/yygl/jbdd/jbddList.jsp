<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		
		function outExcel() {
			var form = document.forms["DataForm"];
			var jbno = form.elements["jbno"].value;
			if(jbno == null || jbno.length == 0) {
				alert("请输入基板订单号");
				return;
			}
			var content = coco.parseParam("jbno", jbno);
			var wo = document.getElementById("WebOffice");
			//wo.HideMenuItem(0x33);
			if(window.self == window.top) wo.LoadOriginalFile("./outJbddExcel.do?"+content, "xls");
			else wo.LoadOriginalFile("<%=request.getContextPath()%>/sino/yygl/jbdd/outJbddExcel.do?"+content, "xls");
			wo.hideMenuArea("hideall","","","");
		}
		
		function resize() {
			var height = document.documentElement.clientHeight;
			var queryHeight = document.getElementById("QueryArea").offsetHeight;
			document.getElementById("ExcelArea").style.height = (height - queryHeight- 3) + "px";
		};
		
		coco.addEventListener(window, "load", resize);
		//-->
		</script>
	</head>
	<body>
		<div id="QueryArea">
			<form name="DataForm" method="post" action="#" >
			<input type="text" name="hidden" style="display: none;" />
			<table align="center" class="form" width="100%" border="0" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="8%" /><col width="17%" />
					<col width="17%" /><col width="8%" />
					<col width="8%" /><col width="17%" />
					<col width="8%" /><col width="17%" />
				</colgroup>
				<tr>
					<th>订购单号</th>
					<td><ui:input name="jbno" maxlength="10" title="订购单号" /></td>
					<td style="text-align: right;">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="检索(Q)" accesskey="q" onclick="outExcel();" /></td>
					<td colspan="5" style="text-align: right;">&nbsp;</td>
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