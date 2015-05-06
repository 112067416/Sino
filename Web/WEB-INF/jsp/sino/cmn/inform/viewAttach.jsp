<%@ page language="java" pageEncoding="UTF-8"%><%
String host = request.getServerName();
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script LANGUAGE=javascript>
	<!--
	// 初始化控件
	function WebOffice1_NotifyCtrlReady() {
		var wo = document.getElementById("WebOffice1");
		//doc , xls, ppt
		wo.HideMenuItem(0x01); // 隐藏"新建"菜单
		wo.HideMenuItem(0x02); // 隐藏"打开"菜单
		wo.LoadOriginalFile('http://<%=host%>/sys/attachment/download.do?id=${attachId }', '${ext }');
		wo.HideMenuArea("","","",""); // 隐藏2003菜单
		wo.HideMenuArea("hideall","","",""); // 隐藏2007菜单
	} 
	//-->
	</script>
	<script LANGUAGE=javascript FOR=WebOffice1 EVENT=NotifyCtrlReady>
	<!--
		WebOffice1_NotifyCtrlReady();
	//-->
	</script>
	</head>
	<body bgcolor="#C1D8F7">
	<table align="left"  width="100%" height="100%">
		<tr>
			<td valign="top">
				<OBJECT id="WebOffice1" style="LEFT: 0px; TOP: 0px; WIDTH: 100%; HEIGHT: 100%" classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5"  codebase="../../activex/weboffice_v6.0.5.0.cab#V6,0,5,0 ">
				<PARAM NAME="_ExtentX" VALUE="6350">
				<PARAM NAME="_ExtentY" VALUE="6350">
				</OBJECT>
			</td>
		</tr>
	</table>
	</body>
</html>
