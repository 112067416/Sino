<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>信息提示</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		
		<script type="text/javascript">
		<!--

		// 确认
		function doQr() {
			this.close();
		}
		
		//-->
		</script>
		
	</head>
	<body>
	
		<ui:page title="备用卷不足信息提示">
			<table width="96%" align="center" class="form" style="margin-top: 10px;">
				<colgroup>
					<col />
				</colgroup>
				<tr>
					<td>
						<span style="color: red;">备用卷不足,请扫描备用卷!</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<input type="button" class="button" value="知道了(S)" accesskey="s" onclick="doQr();" />
					</td>
				</tr>
			</table>
			
		<br />
		</ui:page>
		
	</body>
</html>