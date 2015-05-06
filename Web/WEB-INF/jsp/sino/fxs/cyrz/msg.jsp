<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@
    taglib uri="/ui" prefix="ui"%><%@
    taglib uri="/f" prefix="f"%><%@
    taglib uri="/sys" prefix="sys"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript">
<!--

var deptId= '<f:v value="$deptId"/>';
var cyrzMsgUrl = null;
var cyrzAjax = new Cocoajax();
var feature = {width:515};
cyrzAjax.callback = function() {
	if(this.code > 0) {
		document.getElementById("MsgArea").innerHTML = this.result;
		showPage();
	}
};

function fetchCyrzMsg() {
	if(!isShow()) {
		cyrzAjax.post(cyrzMsgUrl);
	}
}

if(deptId != null) {
	cyrzMsgUrl = null;
	if(deptId == "1047") {
		cyrzMsgUrl = "getForSl.do";
	}
	else if(deptId == "1043") {
		cyrzMsgUrl = "getForFxs.do";
	}
	if(cyrzMsgUrl != null) {
		coco.addEventListener(window, "load", function() {
			feature["left"] = Math.max(window.screen.availWidth - 538, 0);
			feature["top"] = Math.max(window.screen.availHeight - 588, 0);
			window.setInterval(fetchCyrzMsg, 5000);
		});
	}
}

function toUpdateState(type, id) {
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code > 0) {
			hidePage();
		}
		else coco.alert(this.msg);
	}
	ajax.post("updateState.do", "type=" + type + "&id=" + encodeURIComponent(id));
}

function toRecv(type, id) {
	var ajax = new Cocoajax();
	ajax.callback = function() {
		if(this.code > 0) {
			hidePage();
		}
		else coco.alert(this.msg);
	}
	ajax.post("update.do", "type=" + type + "&id=" + encodeURIComponent(id));
}

function isShow() {
	return parent.document.getElementById("CyrzMsgPanel").style.display != "none";
}

function showPage() {
	parent.coco.showPage("CyrzMsgPanel",feature);
}

function hidePage() {
	parent.coco.hidePage('CyrzMsgPanel');
}
//-->
</script>
</head>
<body>
<div id="MsgArea">
</div>
</body>
</html>