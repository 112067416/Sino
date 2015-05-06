<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看仕样未确认</title>
<SCRIPT type="text/javascript">
var path = "<%=request.getContextPath() %>";
var $ua = window.navigator.userAgent.toLowerCase();
var browser = null;
var version = 0;
if(/opera/i.test($ua)) {
	browser = "opera";
}
else if(/msie/i.test($ua)) {
	browser = "msie";
	v = $ua.match(/msie ([^;]+)/i);
	if(v != null && !isNaN(v[1])) version = parseFloat(v[1]);
}
var ie = browser == "msie";
//第一页
function doFirst() {
	cocopage.jump('page', 'first');
}
//上一页
function doPre() {
	var qForm = document.forms["PageForm_page"];
	var index = parseInt(qForm.elements["index"].value);
	if(index == 0) {
		alert("当前页为第一页,请下移");
		return;
	}
	cocopage.jump('page', 'pre');
}
//下一页
function doNext() {
	var qForm = document.forms["PageForm_page"];
	var index = parseInt(qForm.elements["index"].value);
	var pageCount = parseInt(qForm.elements["pageCount"].value);
	if(index >= pageCount - 1) {
		alert("当前页为最后一页,请上移");
		return;
	}
	cocopage.jump('page', 'next');
}
//最后一页
function doLast() {
	cocopage.jump('page', 'last');
}
//-->
</SCRIPT>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/cocowaves.js"></script>
	</head>
	<body>
		<f:page action="viewSywqr.do" file="listViewSywqr.jsp">
		</f:page>
	</body>
</html>