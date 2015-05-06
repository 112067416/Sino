
//==================== Upload 控件 ========================
var uploadIsIE = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
var uploadIsWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
var uploadIsOpera = (navigator.userAgent.indexOf("Opera") != -1) ? true : false;
var uploadParam = {};

function ControlVersion() {
	var version, axo, e;
	try {
		axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
		version = axo.GetVariable("$version");
	} catch (e) { }
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");
			version = "WIN 6,0,21,0";
			axo.AllowScriptAccess = "always";
			version = axo.GetVariable("$version");
		} catch (e) { }
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
			version = axo.GetVariable("$version");
		} catch (e) { }
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
			version = "WIN 3,0,18,0";
		} catch (e) { }
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
			version = "WIN 2,0,0,11";
		} catch (e) { version = -1; }
	}
	return version;
}

function GetSwfVer() {
	var flashVer = -1;
	if (navigator.plugins != null && navigator.plugins.length > 0) {
		if (navigator.plugins["Shockwave Flash 2.0"]
				|| navigator.plugins["Shockwave Flash"]) {
			var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
			var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
			var descArray = flashDescription.split(" ");
			var tempArrayMajor = descArray[2].split(".");
			var versionMajor = tempArrayMajor[0];
			var versionMinor = tempArrayMajor[1];
			var versionRevision = descArray[3];
			if (versionRevision == "") {
				versionRevision = descArray[4];
			}
			if (versionRevision[0] == "d") {
				versionRevision = versionRevision.substring(1);
			} else if (versionRevision[0] == "r") {
				versionRevision = versionRevision.substring(1);
				if (versionRevision.indexOf("d") > 0) {
					versionRevision = versionRevision.substring(0,
							versionRevision.indexOf("d"));
				}
			}
			var flashVer = versionMajor + "." + versionMinor + "."
					+ versionRevision;
		}
	} else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1)
		flashVer = 4;
	else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1)
		flashVer = 3;
	else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1)
		flashVer = 2;
	else if (uploadIsIE && uploadIsWin && !uploadIsOpera) {
		flashVer = ControlVersion();
	}
	return flashVer;
}

function DetectFlashVer(reqMajorVer, reqMinorVer, reqRevision) {
	versionStr = GetSwfVer();
	if (versionStr == -1) {
		return false;
	} else if (versionStr != 0) {
		if (uploadIsIE && uploadIsWin && !uploadIsOpera) {
			tempArray = versionStr.split(" ");
			tempString = tempArray[1];
			versionArray = tempString.split(",");
		} else {
			versionArray = versionStr.split(".");
		}
		var versionMajor = versionArray[0];
		var versionMinor = versionArray[1];
		var versionRevision = versionArray[2];
		if (versionMajor > parseFloat(reqMajorVer)) {
			return true;
		} else if (versionMajor == parseFloat(reqMajorVer)) {
			if (versionMinor > parseFloat(reqMinorVer))
				return true;
			else if (versionMinor == parseFloat(reqMinorVer)) {
				if (versionRevision >= parseFloat(reqRevision))
					return true;
			}
		}
		return false;
	}
}

function generateFlashObj(objAttrs, params, embedAttrs) {
	var str = '';
	if (uploadIsIE && uploadIsWin && !uploadIsOpera) {
		str += '<object ';
		for ( var i in objAttrs) {
			str += i + '="' + objAttrs[i] + '" ';
		}
		str += '>';
		for ( var i in params) {
			str += '<param name="' + i + '" value="' + params[i] + '" /> ';
		}
		str += '</object>';
	} else {
		str += '<embed ';
		for ( var i in embedAttrs) {
			str += i + '="' + embedAttrs[i] + '" ';
		}
		str += '> </embed>';
	}
	return str;
}
function runFlashContent() {
	var ret = getFlashArgs(arguments, "clsid:d27cdb6e-ae6d-11cf-96b8-444553540000", "application/x-shockwave-flash");
	return generateFlashObj(ret.objAttrs, ret.params, ret.embedAttrs);
}

function getFlashArgs(args, classid, mimeType) {
	var ret = new Object();
	ret.embedAttrs = new Object();
	ret.params = new Object();
	ret.objAttrs = new Object();
	for ( var i = 0; i < args.length; i = i + 2) {
		var currArg = args[i].toLowerCase();
		switch (currArg) {
		case "classid":
			break;
		case "pluginspage":
			ret.embedAttrs[args[i]] = args[i + 1];
			break;
		case "src":
		case "movie":
			ret.embedAttrs["src"] = args[i + 1];
			ret.params["movie"] = args[i + 1];
			break;
		case "onafterupdate":
		case "onbeforeupdate":
		case "onblur":
		case "oncellchange":
		case "onclick":
		case "ondblclick":
		case "ondrag":
		case "ondragend":
		case "ondragenter":
		case "ondragleave":
		case "ondragover":
		case "ondrop":
		case "onfinish":
		case "onfocus":
		case "onhelp":
		case "onmousedown":
		case "onmouseup":
		case "onmouseover":
		case "onmousemove":
		case "onmouseout":
		case "onkeypress":
		case "onkeydown":
		case "onkeyup":
		case "onload":
		case "onlosecapture":
		case "onpropertychange":
		case "onreadystatechange":
		case "onrowsdelete":
		case "onrowenter":
		case "onrowexit":
		case "onrowsinserted":
		case "onstart":
		case "onscroll":
		case "onbeforeeditfocus":
		case "onactivate":
		case "onbeforedeactivate":
		case "ondeactivate":
		case "type":
		case "codebase":
		case "id":
			ret.embedAttrs["id"] = args[i + 1];
			ret.objAttrs[args[i]] = args[i + 1];
			break;
		case "width":
		case "height":
		case "align":
		case "vspace":
		case "hspace":
		case "class":
		case "title":
		case "accesskey":
		case "name":
		case "tabindex":
			ret.embedAttrs[args[i]] = ret.objAttrs[args[i]] = args[i + 1];
			break;
		default:
			ret.embedAttrs[args[i]] = ret.params[args[i]] = args[i + 1];
		}
	}
	ret.objAttrs["classid"] = classid;
	if (mimeType)
		ret.embedAttrs["type"] = mimeType;
	return ret;
}

function createFlash(param) {
	if (param == null || typeof (param) != "object") param = {};
	if(param.id != null && param.id != "") uploadParam[param.id] = param;
	var flashVars = "";
	if (param != null) {
		var fields = ["type", "rel", "uri", "single", "rename", "label", "typeFilter", "hidePlus" ];
		var fieldName, fieldValue;
		for ( var i = 0; i < fields.length; i++) {
			fieldName = fields[i];
			fieldValue = param[fieldName];
			if (fieldValue != null) {
				flashVars += "&" + encodeURIComponent(fieldName) + "=" + encodeURIComponent(fieldValue);
			}
		}
		if (flashVars.length > 0) flashVars = flashVars.substring(1);
	}
	return runFlashContent(
			'codebase',
			'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0',
			'width', (param.width != null ? param.width : '60'), 'height',(param.height != null ? param.height : '22'),
			'src', param.src != null ? param.src : 'upload', 'quality', 'high',
			'pluginspage', 'http://www.adobe.com/go/getflashplayer_cn',
			'align', 'middle', 'play', 'true', 'loop', 'true', 'scale',
			'showall', 'wmode', 'opaque', 'devicefont', 'false', 'id',
			param.id != null ? param.id : 'upload', 'bgcolor', '#f4f4f4',
			'name', param.name != null ? param.name : 'upload', 'menu', 'true',
			'allowFullScreen', 'false', 'allowScriptAccess', 'always',
			'movie', param.movie != null ? param.movie : 'upload', 'salign',
			'left', 'flashVars', flashVars);
}

//===============删除处理==============

function cocoupload_createItem(param) {
	var itemId = 'cocoupload$' + param.id + '$' + param.index;
	var html = '<div id="' + itemId + '" _id="'+param.id+'" _index="'+param.index+'" style="align:left">';
	html += '<span style="width:20px;"></span>'; //图标
	html += '<a id="' + itemId + '$name" href="javascript:void(0);" >' + param.name + '</a>'; //文件名
	html += '<span id="' + itemId + '$perc" >' + (param.perc != null ? param.perc : '') + '</span>'; //上传百分比
	html += '<span id="' + itemId + '$opt" ></span>'; //取消或删除操作
	html += '</div>';
	return html;
}

/**
 * 文件开始上传结果
 * @param filepart {index:(int),length:(int),name:(str)}
 * @param id 控件ID
 */
function cocoupload_upload(filepart, id) {
	var win = cocoupload_win(id);
	win.style.display = "block";
	var doc = win.contentWindow.document;
	var area = doc.getElementById("Area");
	if(typeof(upload_callback) == "function")  upload_callback(filepart.name);
	if(uploadParam[id]["single"] == "1") {
		area.innerHTML = cocoupload_createItem({id:id, index:filepart.index, name:filepart.name});
		return;
	}
	var obj = doc.getElementById('cocoupload$' + id + '$' + filepart.index);
	if(obj != null) {
		obj.childNodes[2].innerHTML = '0%';
	}
	else {
		area.innerHTML += cocoupload_createItem({id:id, index:filepart.index, name:filepart.name});
	}
}

/**
 * 文件重命名
 * @param fileparts [{index:(int),name:(str)}]
 * @param id 控件ID
 */
function cocoupload_rename(fileparts, id) {
	var win = cocoupload_win(id);
	win.style.display = "block";
	var area = win.contentWindow.document.getElementById("Area");
	area.innerHTML = area.innerHTML + "<div>rename : " + fileparts.length + " [" +  fileparts[0].index + "," + fileparts[0].name+"]</div>";
}

/**
 * 文件进度
 * @param filepart {index:(int),loaded:(int),total:(int)}
 * @param id 控件ID
 */
function cocoupload_progress(filepart, id) {
	var win = cocoupload_win(id);
	//win.style.display = "block";
	var doc = win.contentWindow.document;
	var obj = doc.getElementById('cocoupload$' + id + '$' + filepart.index);
	if(obj != null && filepart.total > 0) {
		var perc = parseFloat(filepart.loaded) / parseFloat(filepart.total) * 100.0;
		var percStr = (""+perc.toFixed(1)).replace(/.0$/, "");
		if(percStr == "100") {
			obj.childNodes[2].style.display = "none";
		}
		else {
			obj.childNodes[2].innerHTML = percStr + '%';
		}
	}
}

/**
 * 文件上传完毕返回结果
 * @param filepart {index:(int),result:(str)}
 * @param id 控件ID
 */
function cocoupload_return(filepart, id) {
	var win = cocoupload_win(id);
	var doc = win.contentWindow.document;
	var obj = doc.getElementById('cocoupload$' + id + '$' + filepart.index);
	if(obj != null) {
		obj.childNodes[2].style.display = "none";
	}
	var result = null;
	try { eval("result = "+filepart.result); } catch(e) { }
	if(result == null || result.id == null) {
		if(filepart.result.indexof("<script") != null) {
			alert("登录超时，请重新登录");
			//window.top.location.href = uploadParam[id]["path"] + "/login.do";
			return;
		}
		alert("上传出错！");
	}
	var attachName = uploadParam[id]["attachName"];
	if(attachName != null) {
		var o = document.getElementById(id + "_" + attachName);
		if(o != null) {
			if(result.single) o.value = result.id;
			else o.value += (o.value.length > 0 ? "," : "") + result.id;
		}
	}
	//给定下载地址
	obj.childNodes[1].setAttribute("href", uploadParam[id]["path"] + '/sys/attachment/download.do?id='+encodeURIComponent(result.id) );
	obj.childNodes[1].setAttribute("target", "_blank");
}

/**
 * 右键查看上传历史
 * @param id 控件ID
 */
function cocoupload_history(id) {
	var win = cocoupload_win(id);
	win.style.display = (win.style.display == "block") ? "none" : "block";
}
function cocoupload_win(id) {
	var win = document.getElementById("Cocoupload$Frame$"+id);
	if(win == null) {
		win = document.createElement("IFRAME");
		win.id = "Cocoupload$Frame$" + id;
		win.marginWidth = 0;
		win.marginHeight = 0;
		win.frameBorder = 0;
		win.width = 400;
		win.height = 300;
		win.style.position = "absolute";
		win.style.zIndex = 8888;
		win.scrolling = "no";
		win.style.display = "none";
		var obj = document.getElementById(id);
		var xy = (obj != null ? coco.getXY(obj) : null);
		if(xy != null && xy.length > 1) {
			win.style.left = xy[0];
			win.style.top = xy[1] + 25;
		}
		else {
			win.style.left = Math.max(document.body.clientWidth / 2 - 200, 50);
			win.style.top = Math.max(document.body.clientHeight / 2 - 150, 50);
		}
		document.body.appendChild(win);

		var html = '<style>';
		html += 'td{font-size:12px;}';
		html += '.panel{border:1px solid #AAAAAA;text-align:center;}';
		html += '.title{background-color:#a2c4d3;text-align:center;font-size:12px;height:22px;padding-top:2px;border:1px solid #EEEEEE;border-bottom:0px;}';
		html += '.content{border:1px solid #EEEEEE;border-top:0px;background-color:#e2ecf1;text-align:left;width:100%;height:270px;overflow:auto;font-size:12px;}';
		html += '</style>';
		html += '<scr'+'ipt type="text/javascript">';
		html += '</scr' + 'ipt>';
		html += '<div class="panel">';
		html += '<div class="title"><table width="100%"><tr><td width="20">&nbsp;</td><td align="center" >附件列表 '+id+'</td><td width="20" align="right"><img src="'+uploadParam[id]["path"]+'/images/main/icon_x_0.gif" onclick="parent.cocoupload_win(\''+id+'\').style.display = \'none\';" onmouseover="this.src=this.src.replace(/_0/, \'_1\');" onmouseout="this.src=this.src.replace(/_1/, \'_0\');"/></td></tr></table></div>';
		html += '<div id="Area" class="content"></div>';
		html += '</div>';
		var doc = win.contentWindow.document;
		with (doc) {
			open();
			writeln(html);
			close();
		}
	}
	return win;
}
