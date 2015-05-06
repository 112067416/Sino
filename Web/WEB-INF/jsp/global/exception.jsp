<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%
	response.setStatus(200);
	String path = request.getContextPath();
	boolean isAjax = "1".equals(request.getParameter("__ajax__"));
	int code = 0;
	String message = null;
	if (exception instanceof com.coco.core.exception.CocoException) {
		com.coco.core.exception.CocoException ex = (com.coco.core.exception.CocoException) exception;
		code = ex.getCode();
		message = ex.getMessage();
	}
	else if(exception instanceof NullPointerException) {
		code = -1;
		message = "存在参数为空";
	}
		
	if (code >= 0) {
		code = -999999;
	}
	if (message == null || message.isEmpty()) {
		message = exception.getMessage();
	}
	//======特殊的异常=====
	//登录超时
	if (code == -40001) {
		if (isAjax) {
			out.print("<!---40001:-->");
		} else {
			out.print("<script type=\"text/javascript\">window.top.location.href = \"");
			out.print(path);
			out.print("/login.do\";</script>");
		}
		return;
	}
	//没有权限
	else if (code == -40002) {
		if (isAjax) {
			out.print("<!---40002:对不起！您没有访问该操作的特权，请与管理员联系!-->");
		}
		else {
			out.print("<script type=\"text/javascript\">alert(\"对不起！您没有访问该操作的特权，请与管理员联系!\");</script>");
		}
		return;
	}
	StringBuilder stackTrace = new StringBuilder();
	for (StackTraceElement el : exception.getStackTrace()) {
		stackTrace
				.append("<div style='white-space: nowrap;'><span style='color:#888888'>");
		stackTrace.append(el.getClassName());
		stackTrace.append("</span><span style='color:#99AAFF'>#");
		stackTrace.append(el.getMethodName());
		stackTrace.append("()");
		stackTrace.append(":</span><span style='color:#6699FF'>");
		stackTrace.append(el.getLineNumber());
		stackTrace.append("</span></div>");
	}
	if (isAjax) {
		out.print("<!--");
		out.print(code);
		out.print(":");
		out.print(message);
		out.print("&nbsp;&nbsp;&nbsp;&nbsp;<span align='right' style='font-size:12px;color:#6699FF;cursor:pointer;white-space: nowrap;' onclick='var show = this.innerHTML == \"查看详细....\"; this.nextSibling.style.display = show ? \"block\" : \"none\"; this.innerHTML = show ? \"隐藏详细\" : \"查看详细....\";'>查看详细....</span><div style='display:none;background-color:#F5F5F5;font-size:12px;'>");
		out.print(stackTrace);
		out.print("</div>");
		out.print("-->");
		return;
	}
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统异常</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path %>/css/style.css" />
</head>
<body>
<div align="center">
<table width="554" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><img src="<%=path%>/images/main/result_t.gif" width="554"
			height="13"></td>
	</tr>
	<tr>
		<td align="center" background="<%=path%>/images/main/result_m.gif">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="72" rowspan="3"><img
					src="<%=path%>/images/main/result_error.gif" width="72"
					height="72"></td>
				<td width="16" rowspan="3"><img
					src="<%=path%>/images/main/splite_line.gif" width="16" height="72"></td>
				<td height="20" align="left"><strong><font
					color="#EE0000">系统异常</font></strong></td>
			</tr>
			<tr>
				<td align="left">
				<div
					style="font-size: 12px; color: #0000FF; width: 450px; height: 50px; overflow: auto;">&nbsp;&nbsp;&nbsp;&nbsp;<%=message%></div>
				</td>
			</tr>
			<tr>
				<td height="25" align="right"><img
					src="<%=path%>/images/main/btn_detail.gif" width="64" height="21"
					border="0"
					onclick="ResultDetail.style.display = ('none' == ResultDetail.style.display ? 'block' : 'none');" /></td>
			</tr>
		</table>
		<div id="ResultDetail"
			style="width: 530px; height: 350px; line-height: 120%; color: #FF9966; overflow: auto; display: none; border: 1px solid #999999; text-align: left; padding-right: 5px;">
		<div style="font-size: 14px; color: #FF0000;"><%=message%></div>
		<%=stackTrace%></div>
		</td>
	</tr>
	<tr>
		<td><img src="<%=path%>/images/main/result_b.gif" width="554"
			height="13"></td>
	</tr>
</table>
</div>
</body>
</html>