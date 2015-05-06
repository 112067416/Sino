<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!---999998:${result.message }-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执行出现问题</title>
</head>
<body>
<div align="center">
<table width="554" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><img src="<c:url value="/images/main/result_t.gif" />"
			width="554" height="13"></td>
	</tr>
	<tr>
		<td align="center"
			background="<c:url value="/images/main/result_m.gif" />">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="72" rowspan="3"><img
					src="<c:url value="/images/main/result_error.gif" />" width="72"
					height="72"></td>
				<td width="16" rowspan="3"><img
					src="<c:url value="/images/main/splite_line.gif" />" width="16"
					height="72"></td>
				<td height="20" align="left"><strong><font
					color="#EE0000">执行出现问题啦！</font></strong></td>
			</tr>
			<tr>
				<td align="left">
				<div
					style="padding-top:6px;line-height: 120%; color: #0000FF; width: 450px; height: 55px; overflow: hidden">&nbsp;&nbsp;&nbsp;&nbsp;<c:out
					value="${result.title }" escapeXml="false" /></div>
				</td>
			</tr>
			<tr>
				<td height="25" align="right"><img
					src="<c:url value="/images/main/btn_detail.gif" />" width="64"
					height="21" border="0"
					onclick="ResultDetail.style.display = ('none' == ResultDetail.style.display ? 'block' : 'none');" /></td>
			</tr>
		</table>
		<div id="ResultDetail"
			style="width: 530px; height: 300px; overflow: auto; display: none; border: 1px solid #999999; text-align: left; padding-right: 5px;"><pre><c:out
			value="${result.message }" /></pre></div>
		</td>
	</tr>
	<tr>
		<td><img src="<c:url value="/images/main/result_b.gif" />"
			width="554" height="13"></td>
	</tr>
</table>
</div>
</body>
</html>