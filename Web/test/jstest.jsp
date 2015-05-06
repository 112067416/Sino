<%-- 
    version		: 1.0
    Created on 	: 2011-1-6
    Author     	: YuanLong.F
    Description	: 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
		<script type="text/javascript">
		<!--

		function doTest() {
			var s = " abc d ";
			alert("开始 :" + s);
			alert("转换 :" + comm.strim(s));
			alert("结束 :" + s);
		}
		
		//-->
		</script>
		
	</head>
	<body>
	
		<ui:page title="">
			<input type="button" class="button" onclick="doTest();" value="测试" />
			
		<br />
		</ui:page>
		
	</body>
</html>