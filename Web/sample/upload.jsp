<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="/WEB-INF/jsp/global/header.jsp" %>
<script type="text/javascript" src="../js/upload.js"></script>
<script type="text/javascript">


</script>
</head>
<ui:attach id="test0" />
<ui:attach id="test1" label="附件1" attachName="attachId" type="#Email" uri="E:\\proj\\sino\\Source\\Sino\\test\\file" />
<ui:attach id="test2" label="附件2(单个)" single="true" width="100" />
<ui:attach id="test3" label="附件3(多个)" width="100" />
<ui:attach id="test4" label="上传相片" width="100" typeFilter="#image#" />
<ui:attach id="test5" label="上传Excel" width="100" typeFilter="#excel#" />
<ui:attach id="test6" label="上传其他类型" width="100" typeFilter="页面(*.jsp,*.asp,*.php):*.jsp;*.asp;*.php" />
<ui:attach id="test7" label="上传Excel或word" width="150" typeFilter="#excel#|#word#" />
<ui:attach id="test10" label="隐藏加号图标" width="150" hidePlus="true" />

<br />
<br />
<br />
<input type="button" value="查看附件1" onclick="alert(document.getElementById('test1_attachId').value)"/>
</body>
</html>