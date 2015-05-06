<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%  
	// 首先创建SOAOfficeX.SOAOfficeCtrl对象
	SOAOfficeX.SOAOfficeCtrl SOACtrl = new SOAOfficeX.SOAOfficeCtrl(response);
	// 设置连接SOAOFFICE中间件服务器端授权页面
	SOACtrl.ServerURL = request.getContextPath() + "/soaserv.kehan";
	SOACtrl.MainStyle = 2;
	SOACtrl.Caption = "Excel报表";
	SOACtrl.BorderStyle = 0;
	//SOACtrl.Titlebar = false; 
	//SOACtrl.TitlebarColor = Color.decode("#FF0000"); 
	//SOACtrl.TitlebarTextColor = Color.decode("#FFFF00"); 
	//SOACtrl.Menubar = false; 
	SOACtrl.Toolbars = false;
	// 设置保存文档的服务器页面
	SOACtrl.SaveDocURL = "/coco/report/openExcel!"+request.getAttribute("uuid")+".do";
	//SOACtrl.SaveHtmlURL = "SaveHtml.jsp"; 
	// 打开文档
	 SOACtrl.webOpen("/coco/report/openExcel!"+request.getAttribute("uuid")+".do", 0, "Simon", "Excel.Sheet"); 
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body style="margin:0px;background-color: transparent;">
	<OBJECT id="SOAOfficeCtrl" codeBase="../../activex/SOAOffice.ocx#version=8,8,0,0" height="100%" width="99%" classid="clsid:83171BA4-BDCA-42a8-BE44-745ABF36EB7E" data="" >
   <div align=center STYLE="color:red;">本机尚未安装SOAOffice 客户端控件，请安装浏览器上方黄色提示条或弹出提示框中的SOAOffice 客户端控件。</div>
   </OBJECT>
	</body>
</html>
</html>