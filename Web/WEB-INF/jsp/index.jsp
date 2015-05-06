<%@page contentType="text/html; charset=UTF-8"%><%@
taglib uri="/ui" prefix="ui" %><%@
taglib uri="/f" prefix="f" %><%@
taglib uri="/sys" prefix="sys" %><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML><html>
<head>
<title>中日达生产管理系统</title>
<%@include file="global/header.jsp" %>
<script type="text/javascript" src="js/cocoframe.js"></script>
<script type="text/javascript">

	//容器桌面地址
	cocoframe.desktop = "desktop.do";
	var isMax = false;
	var h0 = 29, h1 = 108, h2 = 170;
	cocoframe.maxmin = function(type) {
		if(type == 1) isMax = true;
		else if(type == 2) isMax = false;
		else isMax = !isMax;
		var baseHeight = document.documentElement.clientHeight;
		if(isMax) {
			document.getElementById("topper").style.display = "none";
			document.getElementById("lefter").style.display = "none";
			document.getElementById("container").style.height = (baseHeight - h0) + "px";
			document.getElementById("bottomer").style.display = "none";
		}
		else {
			document.getElementById("topper").style.display = "block";
			document.getElementById("lefter").style.display = "";
			document.getElementById("menuer").style.height = (baseHeight - h2) + "px";
			document.getElementById("container").style.height = (baseHeight - h1) + "px";
			document.getElementById("bottomer").style.display = "block";
		}
	};
	coco.addEventListener(window, "load", function(event) {cocoframe.build(event);initLayout(event);});
	coco.addEventListener(window, "resize", initLayout);
	
	function initLayout(event) {
		var baseHeight = document.documentElement.clientHeight;
		document.getElementById("menuer").style.height = (baseHeight - h2) + "px";
		document.getElementById("container").style.height = (baseHeight - h1) + "px";
	};

	var timeAjax = new Cocoajax();
	var oCurrentTimeSpan = null;
	var timeFlag = true;
	timeAjax.callback = function() {
		timeFlag = true;
		if(oCurrentTimeSpan == null) oCurrentTimeSpan = document.getElementById("CurrentTimeSpan")
		if(oCurrentTimeSpan != null) oCurrentTimeSpan.innerHTML = this.result;
	};
	
	function timeInterval() {
		if(timeFlag) {
			timeFlag = false;
			timeAjax.post("currTime.do",null, "GET");
		}
	}

	coco.addEventListener(window, "load", function() {
		timeInterval();
		window.setInterval(timeInterval, 1000);
		
		if(document.getElementById("ConfigPanel") != null) {
			coco.showPage("ConfigPanel", {center:1,top:100,width:300});
		}
	});
</script>
<style type="text/css">
#topper {height: 50px;background-color: #0a68a9;padding: 0px;margin: 0px;}
#mainer {width: 100%;margin: 0px auto; border-bottom: 1px solid #4A7CBB;padding:0px;}
#lefter {width: 200px;text-align:left;vertical-align: top;border-right: 1px solid #4A7CBB;}
#infoer {width:100%;height:70px;background-image: url(images/main/main_left_t.gif);padding-top:14px;margin: 0px;}
#menuer {width:200px;overflow:auto;background-color:#000000;border:0px;padding:0px;padding-top:5px;padding-bottom:5px;}
#container {width:100%;border: 0px;padding:0px;margin:0px;}
#bottomer {height: 25px; width:100%; background-color: #333333;}
#navbars {width: 100%; height:29px; margin: 0px; padding-top: 2px; background-image: url(images/main/tab_bg.gif);}
#navbars .tab-ax {cursor: pointer;}
#navbars .tab-ax .tab-left { background-image: url(images/main/tab_a_l.gif); width: 8px;height: 27px; padding: 0px;}
#navbars .tab-ax .tab-right { background-image: url(images/main/tab_a_r.gif); width: 8px;padding: 0px;}
#navbars .tab-ax .tab-bg { background-image: url(images/main/tab_a_bg.gif); font-size: 14px;font-family:"微软雅黑"; font-weight:bold; color:#555555;white-space: nowrap;}
#navbars .tab-ay {cursor: pointer;}
#navbars .tab-ay .tab-left { background-image: url(images/main/tab_a_l.gif); width: 8px;height: 27px; padding: 0px;}
#navbars .tab-ay .tab-right { background-image: url(images/main/tab_a_r.gif); width: 8px;padding: 0px;}
#navbars .tab-ay .tab-bg { background-image: url(images/main/tab_a_bg.gif); font-size: 14px;font-family:"微软雅黑"; font-weight:bold; color:#555555;white-space: nowrap;}
#navbars .tab-bx {cursor: pointer;}
#navbars .tab-bx .tab-left { background-image: url(images/main/tab_b_l.gif); width: 8px;height: 27px; padding: 0px;}
#navbars .tab-bx .tab-right { background-image: url(images/main/tab_b_r.gif); width: 8px;padding: 0px;}
#navbars .tab-bx .tab-bg { background-image: url(images/main/tab_b_bg.gif); font-size: 14px;font-family:"微软雅黑"; font-weight:bold; color:#084075;white-space: nowrap;}
#navbars .tab-by {cursor: pointer;}
#navbars .tab-by .tab-left { background-image: url(images/main/tab_b_l.gif); width: 8px;height: 27px; padding: 0px;}
#navbars .tab-by .tab-right { background-image: url(images/main/tab_b_r.gif); width: 8px;padding: 0px;}
#navbars .tab-by .tab-bg { background-image: url(images/main/tab_b_bg.gif); font-size: 14px;font-family:"微软雅黑"; font-weight:bold; color:#084075;white-space: nowrap;}
</style>
</head>
<body>
<div id="topper"><table style="width:100%;margin: 0px auto;" cellpadding="0" cellspacing="0">
	<tr>
		<td><img src="images/main/top.gif" border="0" /></td>
		<td align="right" style="padding-top:3px;padding-right:20px;" valign="top"><span id="CurrentTimeSpan"></span></td>
	</tr>
</table></div>
<table id="mainer" cellpadding="0" cellspacing="0">
	<tr>
		<td id="lefter">
		<div id="infoer">
			<table border="0" cellpadding="2" cellspacing="1" width="180px" align="center" style="vertical-align: top; font-size: 12px; color: #555555;">
				<colgroup>
					<col align="right" style="width: 25%" /><col />
				</colgroup>
				<tr>
					<th>组织:</th>
					<td><f:v value="$deptName" /></td>
				</tr>
				<tr>
					<th>用户:</th>
					<td><f:v value="$name"/></td>
				</tr>
			</table>
		</div>
		<ui:tree tree="tree" uri="tree.do" id="menuer">
		tree.type = 1;
		tree.click = function(id) {
			var oNode = tree.selectedNode;
			var node = oNode["node"];
			var url = tree.nodeValue(node, "url");
			var popup = tree.nodeValue(node, "popup");
			if("Y" == popup) {
				if(url == null || (url = url.replace(/^\s+|\s+$/g, "")) == "") return;
	            if(url.indexOf("http") != 0) url =  path + url;
				window.open(url);
				return;
			}
			cocoframe.open("menu."+id, tree.nodeValue(node, "label"), url, null, null, true);
		};
		</ui:tree></td>
		<td valign="top"><div id="navbars"></div><div id="container"></div></td>
	</tr>
</table>
<div id="bottomer"><table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0px auto;">
<colgroup><col /><col width="200" /></colgroup>
	<tr>
		<td nowrap="nowrap" style="font-size:12px;">
		</td>
		<td align="right" style="padding-top:2px;padding-right:10px;">
		<c:if test="${!(isAdmin || singlePost)}">
		<input type="button" class="button" value="更换岗位"  onclick="if(window.confirm('确定要更换岗位吗?')) top.location.href = 'toChangePost.do';" />
		</c:if>
		<input type="button" class="button" value="注 销" onclick="if(window.confirm('确定要注销吗?')) top.location.href = 'logout.do';" /> 
		</td>
	</tr>
</table></div>
<ui:panel title="消息提醒" id="CyrzMsgPanel" display="false" popup="true" closable="true" movable="false" >
<iframe id="CyrzMsg" name="CyrzMsg" src="sino/fxs/cyrz/msg.do" width="500" height="400" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" ></iframe>
</ui:panel>
<f:equals value2="$deptType" value1="#20">
<f:null value="$group">
<ui:panel title="登录设置" id="ConfigPanel" display="false" popup="true" closable="false" movable="false">
<script type="text/javascript">
function configSuccess() {
	alert("设置成功");
	coco.hidePage("ConfigPanel");
}
</script>
<form action="config.do" method="post" xu.s="configSuccess();">
	<table class="form">
		<tr>
			<th>组别</th><td><sys:codeSelect name="group" code="#SINO_ZU" prop="nn: 1;" headerText="请选择" headerValue="" /></td>
		</tr>
		<tr>
			<th>班别</th><td><sys:codeSelect name="shift" value="shift" code="#SINO_BAN" prop="nn: 1;"  headerText="请选择" headerValue="" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="设置" onclick="cocoform.submit(this)" />
	</div>
</form>
</ui:panel>
</f:null>
</f:equals>
</body>
</html>