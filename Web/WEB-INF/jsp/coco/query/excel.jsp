<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
			<title>Excel报表</title>
			<script type="text/javascript" >
			<!--
			window.onload = function() {
				var wo = document.getElementById("WebOffice");
				wo.HideMenuItem(0x33);
				wo.LoadOriginalFile("./openExcel!${id}.do", "xls");
				wo.hideMenuArea("hideall","","","");
			};
			//-->
			</script>
	</head>
	<body style="margin: 0px">
	<OBJECT id="WebOffice" style="LEFT: 0px; TOP: 0px; WIDTH: 100%; HEIGHT: 100%;" classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5"  codebase="../../activex/weboffice_v6.0.5.0.cab#V6,0,5,0 ">
	<PARAM NAME="_ExtentX" VALUE="6350">
	<PARAM NAME="_ExtentY" VALUE="6350">
	</OBJECT>
	</body>
</html>