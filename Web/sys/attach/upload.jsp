<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>附件上传</title>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link rel="stylesheet" type="text/css" href="upload.css">
<script language="JavaScript" type="text/javascript" src="upload.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
var swfId = "uploadSwf";

function deleteFile(id) {
	alert(id);
}
// -->
</script>
</head>
<body>
	<object id="uploadSwf" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" width="60" height="22" align="middle">
	<param name="allowScriptAccess" value="sameDomain" />
	<param name="allowFullScreen" value="false" />
	<param name="movie" value="upload.swf" />
	<param name="quality" value="high" />
	<param name="wmode" value="transparent" />
	<param name="bgcolor" value="#f4f4f4" />
	<embed src="upload.swf" quality="high" wmode="transparent" bgcolor="#f4f4f4" width="60" height="22" name="upload" align="middle" allowScriptAccess="sameDomain" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer_cn" />
	</object>
	<div id="UploadDiv"></div>
	<div id="MsgDiv"></div>
</body>
</html>
