<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
		<!--
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
			var drag = 0, move = 0,isdrag = false, y, x, oDragObj,nTY, nTX;
			
			function moveMouse(event) {
				if(event == null) envent = window.event;
				if (!isdrag) return;
				oDragObj.style.top  = nTY + event.clientY - y;
				oDragObj.style.left  = nTX + event.clientX - x;
				return;
			}

			function initDrag(event) {
				if(event == null) event = window.event;
				var oDragHandle = event.target || event.srcElement;
				var topElement = "HTML";
				while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") {
					oDragHandle = oDragHandle.parentNode;
				}
				if (oDragHandle.className == "dragAble") {
					isdrag = true;
					oDragObj = oDragHandle;
					nTY = parseInt(oDragObj.style.top+0);
					y = event.clientY;
					nTX = parseInt(oDragObj.style.left+0);
					x = event.clientX;
					return;
				}
			}
			
			if(ie) {
				document.attachEvent("onmousedown", initDrag);
				document.attachEvent("onmouseup", function() {isdrag = false; });
				document.attachEvent("onmousemove", moveMouse);
				document.attachEvent("onmousewheel", onmouseWheel);
			} else {
				document.addEventListener("onmousedown", initDrag);
				document.addEventListener("onmouseup", function() {isdrag = false; });
				document.attachEvent("onmousemove", moveMouse);
				document.addEventListener("onmousewheel", picture);
			}
			
			var imgHeight;
			var imgWidth;
			function doLoad() {
				var height = screen.availHeight;
			    var width = screen.availWidth;
			    imgHeight = oImg.height;
			    imgWidth = oImg.width;
			    if(imgHeight < height && imgWidth < width) return;
			    oImg.height = height;
			    oImg.width = (height * imgWidth / imgHeight);
			}

			function clickMove(s){
				if(s == "up") {
					dragObj.style.top = parseInt(dragObj.style.top) + 100;
				} else if (s == "down") {
					dragObj.style.top = parseInt(dragObj.style.top) - 100;
				} else if (s == "left") {
					dragObj.style.left = parseInt(dragObj.style.left) + 100;
				} else if (s == "right") {
					dragObj.style.left = parseInt(dragObj.style.left) - 100;
				}
			}

			function smallit() {            
				var height1 = oImg.height;            
				var width1 = oImg.width;            
				oImg.height = height1 / 1.2;            
				oImg.width = width1 / 1.2;           
			}             
			    
			function bigit() {            
				var height1 = oImg.height;            
				var width1 = oImg.width;            
				oImg.height = height1 * 1.2;          
				oImg.width = width1 * 1.2;           
			}     
			        
			function onmouseWheel(event) {
				if(event == null) event = window.event;
				var ele = event.srcElement || event.target;
				if (event.wheelDelta >= 120) {
					bigit();
				} else if (event.wheelDelta <= -120) {
					smallit();
				}
				return;
			}
			
			function realsize() {
				oImg.height = imgHeight;     
				oImg.width = imgWidth;
				ImgDiv.style.left = 0;
				ImgDiv.style.top = 0;
			}
		//-->
		</script>
	</head>
<body onload="doLoad();" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false"onmouseup="document.selection.empty()" style="overflow-y:hidden;overflow-x:hidden;">
	<div id="Layer1" style="position: absolute; z-index:100; top: 10px;">
	<table border="0" cellspacing="2" cellpadding="0">
		<tr>
			<td>&nbsp;</td>
			<td><img src="/images/icon/up.png" width="20" height="20" style="cursor:hand" onClick="clickMove('up')" title="向上"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><img src="/images/icon/left.png" width="20" height="20" style="cursor:hand" onClick="clickMove('left')" title="向左"></td>
			<td><img src="/images/icon/zoom.png" width="20" height="20" style="cursor:hand" onClick="realsize();" title="还原"></td>
			<td><img src="/images/icon/right.png" width="20" height="20" style="cursor:hand" onClick="clickMove('right')" title="向右"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="/images/icon/down.png" width="20" height="20" style="cursor:hand" onClick="clickMove('down')" title="向下"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="/images/icon/zoom_in.png" width="20" height="20" style="cursor:hand" onClick="bigit();" title="放大"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="/images/icon/zoom_out.png" width="20" height="20" style="cursor:hand" onClick="smallit();" title="缩小"></td>
			<td>&nbsp;</td>
		</tr>
	</table>
</div>
<div id="ImgDIV" onmouseout="drag=0;" onmouseover="dragObj=ImgDIV; drag=1;" style="z-index:10; height: 0; left: 0px; position: absolute; top: 0px; width: 0;" class="dragAble"> <img name="oImg" src=<%=request.getContextPath() %>"/sys/attachment/download.do?id=${attachId }" style="cursor: hand;" border="0"></div>
</body>
</html>
