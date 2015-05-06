<% 
java.util.List<String> list = new java.util.ArrayList<String>();
list.add("a");
list.add("b");
request.setAttribute("items", list);
java.util.Date date = new java.util.Date();
request.setAttribute("date", date);
Double dbl = new Double(0.55);
request.setAttribute("dbl", dbl);
%><%@page contentType="text/html; charset=UTF-8"%><%@taglib 
prefix="f" uri="/f" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>功能标签</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="../js/calendar.js"></script>
	</head>
	<body>
	<f:bool bool="#n" trueText="#许德建" falseText="#张良"/>
	<f:contains object="#c" name="items">许德建</f:contains>
	<f:kv value="#c" list="'a':'啊','c':'从'"/>
	<f:v value="date" format="yyyy-MM-dd HH:mm"/>
	<f:v value="dbl" format="2" />
	<f:v value="dbl" format="%0" />
	</body>
</html>