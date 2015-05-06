<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>制品查看</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript">
<!--

function toView() {
	var o = document.getElementById("jbno");
	if(o.value != "") {
		tree.create(tree.area,"tree.do?jbno="+o.value);
		tree.expandAll();
	}
}
//-->
</script>
</head>
<body>
<ui:page title="制品查看">
	<table width="100%" class="form">
		<colgroup><col width="80" /><col width="120" /><col /></colgroup>
		<tr>
			<th>制品号</th>
			<td><ui:input id="jbno" name="jbno" maxlength="11" /></td>
			<td><input type="button" class="button" value="查看" onclick="toView();" /></td>
		</tr>
	</table>
	<ui:panel title="结构树" width="100%" display="true">
	<div style="height:400px;" class="scroll">
	<ui:tree tree="tree" uri="tree.do" id="tree" >
	tree.type = 1;
	tree.icons["folder"] = "coil.gif";
	tree.eicons["folder"] = "coil.gif";
	tree.click = function(id) {
	};
	</ui:tree>
	</div>
	</ui:panel>
</ui:page>
</body>
</html>