<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入文件管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/upload.js"></script>
<script type="text/javascript">

	contextmenu.putMenus("menu", [["阅读", "view.gif", "view()"]]);
	//阅读附件
	function view(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var key1 = coco.getAttr(oTr, "xu.id");
		var height = screen.availHeight;
	    var width = screen.availWidth;
		window.open(path + "/sino/cmn/inform/viewAttach.do?attachId="+key1, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
	}
	//-->
</script>
</head>
<body>
<ui:page title="导入文件管理">
	<f:page action="index!dr.do" file="list!dr.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="18%" />
				<col width="32%" />
				<col width="18%" />
				<col width="32%" />
			</colgroup>
			<tr>
				<th>类型</th>
				<td><ui:select name="type" sql="#select id_,name_ from coco_convert_entity" headerText="全部" headerValue="" /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="查 询" onclick="cocopage.query();" /></div>
	</f:page>
</ui:page>
</body>
</html>