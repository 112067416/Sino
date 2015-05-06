<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
	uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>目录管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/upload.js"></script>
<script type="text/javascript">
<!--
contextmenu.putMenus("menu", [["阅读", "view.gif", "view()"]]);
//阅读附件
function view(oTr) {
	if(oTr == null) oTr = contextmenu.selectedTarget();
	var key1 = coco.getAttr(oTr, "xu.id");
	var height = screen.availHeight;
    var width = screen.availWidth;
	window.open(path + "/sino/cmn/inform/viewAttach.do?id="+key1, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
}

function doQuery(el) {
	var oForm = el.form;
	oForm.elements["directory"].value = "";
	cocopage.query();
}
//-->
</script>
</head>
<body resizes="TreeArea:h-100;">
<ui:page title="资料阅读管理">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="35%" valign="top"><ui:panel title="目录树">
				<ui:tree tree="tree" uri="/sys/directory/tree.do?all=true" id="TreeArea" cssClass="scroll">
				tree.type = 1;
				tree.click = function(id) {
					var oForm = document.forms["PageForm_page"];
					oForm.elements["directory"].value = id;
					cocopage.query();
				};
			</ui:tree>
			</ui:panel></td>
			<td width="65%" valign="top"><ui:panel title="资料信息">
				<f:page action="index.do" file="list.jsp" size="10">
					<input type="hidden" name="directory" />
					<table width="100%" class="form">
						<colgroup>
							<col width="10%" />
							<col width="20%" />
							<col width="70%" />
						</colgroup>
						<tr>
							<th>名称</th>
							<td><ui:input name="name" title="名称" maxlength="20" /></td>
							<td><div class="submit"><input type="button" class="button" value="查 询" onclick="doQuery(this);" /></div></td>
						</tr>
					</table>
					<br />
				</f:page>
			</ui:panel></td>
		</tr>
	</table>
</ui:page>
</body>
</html>