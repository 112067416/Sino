<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="../../global/header.jsp" %>
<script type="text/javascript">

function success() {
	alert("部署成功");
}
</script>
</head>
<body>
<form name="DataForm" action="deploy.do" xu.ajax="" xu.s="success()">
<input type="text" class="normal" name="xmlFile" value="query.xml" />
<input type="submit" class="button" value="确定部署" />
</form>
</body>
</html>