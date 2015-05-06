
$$pageZIndex = 100;

var coco = {
	setAttr : function(el, name, value) {
		if(el == null || name == null) return;
		if(value == null) value = "";
		if(el && el.setAttribute) {
			try { el.setAttribute(name, value);} catch (e) { }
		}		
	},
	getAttr : function(el, name) {
		try {return (el && el.getAttribute) ? el.getAttribute(name) : null;} catch (e) {}
		return null;
	},
	removeAttr : function(el, name) {
		if(el == null || name == null) return;
		if(el && el.removeAttribute) {
			try{ el.removeAttribute(name); } catch(e) { }
		}
	},
	setText : function(el, text, append) {
		if(el == null) return;
		if (ie) el.innerText = (append ? (el.innerText + text) : text);
		else el.textContent = (append ? (el.textContent + text) : text);
	},
	getText : function(el) {
		if(el == null) return null;
		if (ie) return el.innerText;
		else return el.textContent;
	},
	getNodeText : function(node, tagName) {
		if (node == null || !(node.nodeType == 1 || node.nodeType == 9) || tagName == null) return "";
		if (node.nodeType == 9) node = node.documentElement;
		return coco.nodeText(node.selectSingleNode(tagName));
	},
	nodeText : function(node) {
		if (node != null) {
			if (node.childNodes.length > 0) return node.firstChild.data;
			else return node.text;
		}
		return "";
	},
	getXY : function(el) {
		if(el == null || el.tagName == null) return [];
		return YAHOO.util.Dom.getXY(el);
	},
	findParentByTag : function(el, tagName) {
		if(el == null) return null;
		if(tagName == null || tagName == "") return el.parentNode;
		tagName = tagName.toUpperCase();
		var oParent = el;
		while((oParent = oParent.parentNode) != null) {
			if(oParent.tagName == tagName) return oParent;
		}
		return null;
	},
	findParentByAttr : function(el, attrName, attrValue) {
		if(el == null) return null;
		if(attrName == null || attrName == "") return null;
		var oParent = el;
		var value = null;
		while((oParent = oParent.parentNode) != null && oParent.getAttribute != null) {
			value = oParent.getAttribute(attrName);
			if(value == null) continue;
			if(attrValue == null || value == attrValue) return oParent;
		}
		return null;
	},	
	addEventListener : function(el, evt, func) {
		if(el.attachEvent) el.attachEvent("on"+evt, func);
		else el.addEventListener(evt, func, false);
	},
	onload : function(event) {
		if(typeof(onload) == "function") try{onload(event);}catch(e){}
		try{contextmenu.build(event);}catch(e){}
		try{coco.onresize(event);}catch(e){}
		try{cocoform.onload(event);}catch(e){}
		try{cococombobox.onload(event);}catch(e){}
	},
	/**
	 * 页面大小被改变
	 */
	onresize : function(event) {
		var resizes = coco.getAttr(document.body, "resizes");
		if(resizes == null || resizes == "") return;
		var arr = resizes.split(";");
		var el, width, height;
		for(var i = 0; i < arr.length; i++) {
			var strs = arr[i].split(":");
			if(strs.length == 2) {
				el = document.getElementById(strs[0]);
				if(el == null) continue;
				var wh = strs[1].split(",");
				width = null, height = null;
				for(var j = 0; j < wh.length; j++) {
					try {
						if(wh[j].indexOf("w") != -1) {
							var w = Math.max(document.documentElement.clientWidth  , document.body.clientWidth);
							width = eval(wh[j].replace(/w/, w));
							if(isNaN(width)) width = null;
						}
						else if(wh[j].indexOf("h") != -1) {
							var h = Math.max(document.documentElement.clientHeight , document.body.clientHeight);
							height = eval(wh[j].replace(/h/, h));
							if(isNaN(height)) height = null;
						}
					}
					catch(e){}
				}
				try {
					if(width != null && (el.style.width == null || el.style.width=="" || (width - parseInt(el.style.width)) > 3)) el.style.width = width + "px";
					if(height != null && (el.style.height == null || el.style.height=="" || (height - parseInt(el.style.height)) > 3)) el.style.height = height + "px";
				}
				catch(e){}
			}
		}
	},
	msgbox : function(msg) {
		var msgbox = document.getElementById("coco$msgbox");
		if(msgbox == null) {
			msgbox = document.createElement("DIV");
			msgbox.id = "coco$msgbox";
			msgbox.style.position = "absolute";
			msgbox.style.zIndex = "9000";
			msgbox.style.left = 0;
			msgbox.style.top = 0;
			msgbox.style.display = "none";
			//msgbox.style.backgroundColor = "#EECCAA";
			var innerHTML = '<DIV style="position:absolute;z-index:9001;width:300px;padding:2px;font-size:12px;border:1px dotted #FF0000;background-color:#EECCAA;"></DIV>';
			msgbox.innerHTML = innerHTML;
			document.body.appendChild(msgbox);
			coco.addEventListener(document, "click", function(event) { if(event == null) event = window.event; var target = event.target || event.srcElement; if(msgbox.contains(target)) return; msgbox.style.display = "none"; });
			coco.addEventListener(msgbox, "dblclick", function(event) { msgbox.style.display = "none"; });
		}
		msgbox.style.left = document.body.scrollLeft + document.body.clientWidth / 2;
		msgbox.style.top = document.body.scrollTop + document.body.clientHeight / 2;
		msgbox.childNodes[0].innerHTML = msg;
		msgbox.style.display = "block";
	},
	alert : function(msg, title) {
		var doc = (self == top && top.document.body) ? document : top.document;
		var msgbox = doc.getElementById("coco$alert");
		if(msgbox == null) {
			msgbox = doc.createElement("DIV");
			msgbox.id = "coco$alert";
			msgbox.style.display = "none";
			var innerHTML = '<DIV id="coco$alert$msg" style="position:absolute;z-index:9001;left:50px;top:50px;"><table style="width:500px;background-image:url('+path+'/images/main/alert_title.png);" cellpadding=0 cellspacing=0 border=0 xu.move="block" xu.move.target="coco$alert$msg"><tr><td width=470 height=22 style="padding-left:10px;padding-top:2px;color:#000000;font-size:14px;font-weight:bold;letter-spacing:3px;"><td><td><img src="'+path+'/images/main/icon_x_0.gif" onmouseover="this.src = this.src.replace(/_0/,\'_1\');" onmouseout="this.src = this.src.replace(/_1/,\'_0\');" onclick="document.getElementById(\'coco$alert\').style.display=\'none\';" /></td></tr></table><DIV style="width:495px;height:350px;overflow:auto;padding:2px;font-size:16px;color:#FF0000;border:1px solid #184962;background-color:#EEEEEE;"></DIV></DIV>';
			if(ie) innerHTML += '<DIV style="position:absolute;z-index:9000;left:0px;top:0px;"><iframe src="'+path+'/js/blank.htm" width="100%" height="100%" frameborder="0" border="0" allowtransparency="true" ></iframe></DIV>';
			else innerHTML += '<DIV style="position:absolute;z-index:9000;left:0px;top:0px;background-image:url('+path+'/images/main/hide_bg.png)">&nbsp;</DIV>';
			msgbox.innerHTML = innerHTML;
			doc.body.appendChild(msgbox);
		}
		msgbox.childNodes[0].childNodes[0].rows[0].cells[0].innerHTML = (title != null ? title : '系统出错');
		msgbox.childNodes[0].childNodes[1].innerHTML = msg;
		msgbox.childNodes[0].style.left = Math.max((doc.body.clientWidth - 500) / 2, 0);
		msgbox.childNodes[1].style.width = doc.body.clientWidth;
		msgbox.childNodes[1].style.height = doc.body.clientHeight;
		msgbox.style.display = "block";
	},
	hideAlert : function () {
		var doc = (self == top && top.document.body) ? document : top.document;
		var msgbox = doc.getElementById("coco$alert");
		if(msgbox != null) {
			msgbox.style.display = "none";
		}
	},
	getElName : function (el) {
		if(el == null) return null;
		var field = coco.getAttr(el, "field");
		if(field != null && (field = field.replace(/^\s+|\W+|\s+$/g, "")).length > 0) return field;
		var name = coco.getAttr(el, "name");
		if(name == null || (name == name.replace(/^\s+|\W+|\s+$/g, "")).length > 0) return name;
		return name;
	},
	/**
	 * 解析参数成提交的字段和值
	 * @param name 字段名
	 * @param value 字段值
	 */
	parseParam : function(name, value) {
		return encodeURIComponent(name) + "=" + encodeURIComponent(value); 
	},
	/**
	 * 解析参数列表（元素为对象:{name:n,value:v}）成提交的字段和值串
	 * @param params 参数对象列表
	 */
	parseParams : function(params) {
		var content = "";
		for(var i = 0; i < params.length; i++) {
			if(content.length > 0) content += "&";
			content += encodeURIComponent(params[i].name) + "=" + encodeURIComponent(params[i].value); 
		}
		return content; 
	},
	/**
	 * 解析参数列表（元素为数组:[name,v]）成提交的字段和值串
	 * @param params 参数对象列表
	 */
	parseParamArr : function(params) {
		var content = "";
		for(var i = 0; i < params.length; i++) {
			if(content.length > 0) content += "&";
			content += encodeURIComponent(params[i][0]) + "=" + encodeURIComponent(params[i][1]); 
		}
		return content; 
	},
	/**
	 * 解析HTML元素属性值成提交的字段和值串
	 * @param el HTML元素
	 * @param params 参数字段列表
	 * @param prefix 指定前缀
	 */
	parseParamByElAttr : function(el, params, prefix) {
		if(el == null) return "";
		if(prefix == null) prefix = "";
		var content = "";
		var v = null;
		if(params == null || params.length == 0) {
			var index;
			var attrs = el.attributes;
			var attr, name, value;
			for(var i = 0; i < attrs.length; i++) {
				attr = attrs[i];
				if(attr == null) continue;
				name = attr.nodeName;
				value = attr.nodeValue;
				index = name.indexOf("xu.v.");
				if(index != -1) {
					if(value == null) value = "";
					if(content.length > 0) content += "&";
					content += encodeURIComponent(prefix+name.substring(5)) + "=" + encodeURIComponent(value); 
				}
			}
		}
		else {
			for(var i = 0; i < params.length; i++) {
				v = coco.getAttr(el, "xu.v."+params[i]);
				if(v == null) v = "";
				if(content.length > 0) content += "&";
				content += encodeURIComponent(prefix+params[i]) + "=" + encodeURIComponent(v); 
			}
		}
		return content; 
	},
	/**
	 * 填充信息到HTML元素属性
	 * @param el HTML元素
	 * @param obj 信息对象
	 */
	setParamInElAttr : function(el, obj) {
		if(el == null || obj == null) return;
		var v;
		for(var id in obj) {
			if(id != null && id.length > 0) {
				v = obj[id];
				if(v == null) v ="";
				coco.setAttr(el, "xu.v."+id, v);
			}
		}
	},
	createDocument : function(async, root){
		var dom = null;
		if (window.ActiveXObject) {
			dom = new ActiveXObject("Microsoft.XMLDOM");
			if(root != null) dom.appendChild(dom.createElement(root));
		}
		else if (document.implementation && document.implementation.createDocument) dom = document.implementation.createDocument(root != null ? root : "","", null);
		if(dom != null) dom.async = async != null ? async : false;
		return dom;
	},
	/**
	 * 显示内部页面
	 */
	showPage : function(id, feature) {
		var o = document.getElementById(id);
		if(o == null) return;
		o.style.display = "block";
		if(feature != null) {
			if(feature.width != null) o.style.width = feature.width;
			if(feature.height != null) o.style.height = feature.height;
			if(feature.center) o.style.left = Math.max((document.documentElement.clientWidth - o.clientWidth) / 2, 0) + "px";
			else if(feature.left != null) o.style.left = feature.left + "px";
			else o.style.left = "0px";
			if(feature.middle) o.style.top = Math.max((document.documentElement.clientHeight - o.clientHeight) / 2, 0) + "px";
			else if(feature.top != null) o.style.top = feature.top + "px";
			else o.style.top = "0px";
		}
		var hidepage = null;
		if(o.className != null && o.className.indexOf("panel-popup") != -1) {
			o.style.zIndex = $$pageZIndex + 2;
			hidepage = document.getElementById("coco$hide$"+id);
			if(hidepage == null) {
				hidepage = document.createElement('DIV');
				hidepage.id = "coco$hide$"+id;
				with(hidepage.style) {
					border = "0px";
					left = "0px";
					top = "0px";
					width = "100%";
					position = "absolute";
					zIndex = $$pageZIndex + 1;
					backgroundImage = 'url('+path+'/images/main/hide_bg.png)';
				};
				document.body.appendChild(hidepage);
				if(ie && version < 8) {
					var frame = document.createElement("iframe");
					frame.border = 0;
					frame.frameBorder = 0;
					with(frame.style) {
						border = 0;
						frameBorder = 0;
						left = "0px";
						top = "0px";
						width = "100%";
					}
					hidepage.appendChild(frame);
					var frameHTML = '<body style="margin: 0px auto;border-collapse: collapse; border-spacing: 0px;background-image:url('+path+'/images/main/hide_bg.png);height:100%;"></body>';
					with (frame.contentWindow.document) {
						open();
						writeln(frameHTML);
						close();
					}
				}
			}
			hidepage.style.display = "block";
			hidepage.style.width = Math.max(document.documentElement.clientWidth, document.body.clientWidth) + "px";
			hidepage.style.height = Math.max(document.documentElement.clientHeight, document.body.clientHeight) + "px";
			if(ie && version < 8) hidepage.childNodes[0].style.height = hidepage.style.height;
		}
		$$pageZIndex += 2;
		coco.addEventListener(self, "resize", function() {
			if(o.style.display == "block") {
				if(feature != null) {
					if(feature.width != null) o.style.width = feature.width;
					if(feature.height != null) o.style.height = feature.height;
					if(feature.center) o.style.left = Math.max((document.documentElement.clientWidth - o.clientWidth) / 2, 0) + "px";
					else if(feature.left != null) o.style.left = feature.left + "px";
					else o.style.left = "0px";
					if(feature.middle) o.style.top = Math.max((document.documentElement.clientHeight - o.clientHeight) / 2, 0) + "px";
					else if(feature.top != null) o.style.top = feature.top + "px";
					else o.style.top = "0px";
				}
			}
			if(hidepage.style.display == "block") {
				hidepage.style.width = Math.max(document.documentElement.clientWidth, document.body.clientWidth) + "px";
				hidepage.style.height = Math.max(document.documentElement.clientHeight, document.body.clientHeight) + "px";
				if(ie && version < 8) hidepage.childNodes[0].style.height = hidepage.style.height;
			}
		});
	},
	/**
	 * 隐藏内部页面
	 */
	hidePage : function(id) {
		var o = document.getElementById(id);
		if(o == null) return;
		o.style.display = "none";
		var hidepage = document.getElementById("coco$hide$"+id);
		if(hidepage != null) {
			hidepage.style.display = "none";		
		}
		coco.removeMsg();
	},
	/**
	 * 删除行的时候。自动清除掉输入框校验提示层。张良加
	 * 
	 */
	removeMsg : function() {
		var fes = cocoform.fes;
		if(fes != null && fes.length > 0) {
			for(var i = 0; i < fes.length; i++) {
				var msgbox = document.getElementById("cocoform$msgbox$"+fes[i].id);
				if(msgbox != null) msgbox.style.display = "none";
			}
		}
 	},
	/**
	 * 选择全部选框
	 * @param name 选框名称
	 * @param obj “全部选框”对象
	 */
	selAll : function(name, obj) {
		if(obj == null || name == null) return;
		var checked = obj.checked;
		var objs = obj.form ? obj.form.elements[name] : document.getElementsByName(name);
		if(objs == null) return;
		if(objs.length == null || objs.length == 0) objs.checked = checked;
		else {
			for(var i = 0; i < objs.length; i++) {
				if(objs[i].disabled) continue;
				objs[i].checked = checked;
			}
		}
	},
	/**
	 * 选择表格中的全部选框
	 * @param name 选框名称
	 * @param container 容器对象
	 * @param obj “全部选框”对象
	 */
	selTableAll : function(name, container, obj) {
		if(obj == null || name == null) return;
		if(container == null) container = obj.form ? obj.form : document;
		var checked = obj.checked;
		var objs = container.getElementsByName(name);
		if(objs == null) return;
		if(objs.length == null || objs.length == 0) objs.checked = checked;
		else {
			for(var i = 0; i < objs.length; i++)  {
				if(objs[i].disabled) continue;
				objs[i].checked = checked;
			}
		}
	},
	contains : function(container, el) {
		if(container == null || el == null) return false;
		return ie ? container.contains(el) : ((container.compareDocumentPosition(el) & 16) == 16);
	},
	stopPropagation : function(event, prevent) {
		event = event != null ? event : window.event;
		prevent = prevent != null && prevent;
		if(ie) {
			if(prevent) event.returnValue = false;
			event.cancelBubble = true;
		}
		else {
			if(prevent) event.preventDefault();
			event.stopPropagation();
		}
	},
	//获取光标位置
	getCursorIndex : function(input){
		//IE
		if(ie) {
			var rng = document.selection.createRange();
			rng.moveStart("character", -1 * input.value.length);
			return rng.text.length;
		}
		//非IE
		return input.selectionStart;
	},
	//获取码表对象
	getCode : function(code, key) {
		if(code == null) return null;
		var ajax = new Cocoajax();
		ajax.async = false;
		ajax.post(path + "/sys/code/getItem.do", "code="+encodeURIComponent(code) + "&key="+encodeURIComponent(key));
		var obj = null;
		try {eval("obj = " + ajax.result + ";");}catch(e){}
		return obj;
	},
	
	getCodeByValue : function(code, value) {
		if(code == null) return null;
		var ajax = new Cocoajax();
		ajax.async = false;
		ajax.post(path + "/sys/code/getByValue.do", "code="+encodeURIComponent(code) + "&value="+encodeURIComponent(value));
		var obj = null;
		try {eval("obj = " + ajax.result + ";");}catch(e){}
		return obj;
	}
	
};

function Cocoajax() {
	this.ajaxUrl = path + "/ajax";	
	//xml结果方式
	this.xml = false;	
	//返回结果
	this.result = null;	
	//返回代码
	this.code = 0;	
	//返回消息信息
	this.msg = null;	
	//页面状态
	this.status = 0;	
	//请求对象
	this.xmlhttp = null;	
	//是否异步
	this.async = true; 	
	//数据对象
	this.dataDiv = null;	
	//是否显示loading
	this.showLoading = true;	
	this.loadingImg = null;	
	//操作按钮
	this.oInput = null;	
	this.init = function() {this.code = 0; this.msg = null; this.status = 0; this.result = null;};
	
	this.parseResult = function() {
		this.init();
		if(this.xmlhttp == null) {
			this.code = -1;
			this.msg = "无法创建XML请求对象";
			return;
		}
		this.status = this.xmlhttp.status;
		this.result = this.xmlhttp.responseText.replace(/^\s+/g,"");
		if(this.status == 404) {
			this.code = -404;
			this.msg = "没有找到请求的页面";
			return;
		}
		var index = -1;
		if(this.result.substring(0,4) == "<!--" && (index = this.result.indexOf("-->")) != -1) {
			var msgs = this.result.substr(4, index - 4);
			var msgIndex = msgs.indexOf(":");
			this.code = msgIndex != -1 ? msgs.substring(0,msgIndex) : msgs;
			this.msg = msgIndex != -1 ? msgs.substring(msgIndex + 1) : msgs;
			if(!isNaN(this.code)) {
				this.result = this.result.substr(index + 3);
			}
			else {
				this.code = 1;
				this.result = this.msg = this.result.substr(index + 3);
			}
		}
		else {
			this.code = 1;
			this.msg = this.result;
		}
		if(this.xml && this.code > 0) {
			var oXML = document.createElement("XML");
			oXML.loadXML(this.result);
			this.result = oXML;
		}
		if(this.code <= 0 && this.msg == null) {
			this.msg = "系统出错";
		}
	};
	
	this.post = function(url, postContent, method) {
		if(this.xmlhttp) this.xmlhttp.abort();
		else {
			if (window.XMLHttpRequest) this.xmlhttp = new XMLHttpRequest();
			else if (window.ActiveXObject) {
				  try {this.xmlhttp = new ActiveXObject("MSXML2.XMLHTTP");}
					catch(e) {this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");}
			}
		}
		if(!this.xmlhttp) {
			alert("无法实例XMLHttp对象，可能您的浏览器不支持");
			return false;
		}
		if(this.async) {
			if(this.showLoading && this.dataDiv != null && !this.xml) {
				if(this.loadingImg != null && this.loadingImg.length > 0) this.dataDiv.innerHTML = '<div align=center ><img src="'+path+'/images/main/'+this.loadingImg+'" /></div>';
				else this.dataDiv.innerHTML = '<div align=center style="padding-top:100px;"><img src="'+path+'/images/main/loader-bar.gif" /></div>';
			}
			if(this.oInput != null) this.oInput.disabled = true;
			var _this = this;
			this.xmlhttp.onreadystatechange = function() {
				// if xmlhttp shows "loaded"
				if (_this.xmlhttp.readyState == 4) {
					_this.parseResult();
					//登录超时
					if(_this.code == -40001) {
						coco.alert(_this.msg);
						window.location.href = path + "/login.do";
						return;
					}
					if(_this.dataDiv != null && !_this.xml) {
						_this.dataDiv.innerHTML = _this.result;
					}
					try {
					if(typeof(_this.callback) == "function") _this.callback();
					}catch(e){}
					if(_this.oInput != null) _this.oInput.disabled = false;
				}
			};
		}
		if(url.indexOf("?") != -1) url += "&__ajax__=1";
		else url += "?__ajax__=1";
		if(method != null && method.toLowerCase() == "get") {
			url += "&" + postContent;
			this.xmlhttp.open("GET", url, this.async);
			if(ie && version > 8 && typeof(postContent) == "object") this.xmlhttp.setRequestHeader("Content-Type","text/xml");
			else this.xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			this.xmlhttp.setRequestHeader("User-Agent", "MyCustomUser");
			this.xmlhttp.setRequestHeader("If-Modified-Since", "0");
			this.xmlhttp.setRequestHeader("pragma", "no-cache");
			this.xmlhttp.setRequestHeader("Cache-Control", "no-cache,   must-revalidate");
			this.xmlhttp.setRequestHeader("expires", "-1");
			this.xmlhttp.send(null);
		}
		else {
			this.xmlhttp.open("POST", url, this.async);
			if(ie && version > 8 && typeof(postContent) == "object") this.xmlhttp.setRequestHeader("Content-Type","text/xml");
			else this.xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			this.xmlhttp.setRequestHeader("User-Agent", "MyCustomUser");
			this.xmlhttp.setRequestHeader("If-Modified-Since", "0");
			this.xmlhttp.setRequestHeader("pragma", "no-cache");
			this.xmlhttp.setRequestHeader("Cache-Control", "no-cache,   must-revalidate");
			this.xmlhttp.setRequestHeader("expires", "-1");
			this.xmlhttp.send(postContent);
		}
		if(!this.async) {
			this.parseResult();
		}
	
	};
	
}

Cocoajax.prototype.callback = function() {
};

var cocopage = {
	query : function(name, callback) {
		cocopage.jump(name, "query", callback);
	},
	refresh : function(name, callback) {
		cocopage.jump(name, "refresh", callback);
	},
	jump : function(name, type, callback) {
		if(name == null || name == "") name = "page";
		var oForm = document.forms["PageForm_"+name];
		if(oForm == null) return;
		var pi;
		if(type == "query") {
			oForm.elements["index"].value = 0;
		}
		else if(type == "first") {
			pi = oForm.elements["index"].value;
			if(pi == 0) return;
			oForm.elements["index"].value = 0;
		}
		else if(type == "pre") {
			pi = oForm.elements["index"].value;
			if(pi == 0) return;
			oForm.elements["index"].value = pi - 1;
		}
		else if(type == "next") {
			pi = oForm.elements["index"].value;
			var pageCount = oForm.elements["pageCount"].value;
			if(pi >= pageCount - 1) return;
			else pi = parseInt(pi);
			oForm.elements["index"].value = pi + 1;
		}
		else if(type == "last") {
			pi = oForm.elements["index"].value;
			var pageCount = oForm.elements["pageCount"].value;
			if(pi >= pageCount - 1) return;
			oForm.elements["index"].value = pageCount - 1;
		}
		else if(type == "refresh") {
		}
		else {
			var oJi = oForm.elements[name + "_jumpIndex"];
			if(oJi == null) return false;
			var ji = oJi.value;
			if(ji == "") return;
			ji = parseInt(ji);
			if(isNaN(ji)) {
				alert("请输入正确的页码");
				oJi.focus();
				return;
			}
			var ci = oForm.elements["pageCount"].value;
			if(ci == "" || isNaN(ci)) ci = 1;
			else ci = parseInt(ci);
			if(ji <= 0 || ji > ci) {
				alert("页码超过页数范围，请输入正确的页码");
				oJi.focus();
				return;
			}
			oForm.elements["index"].value = ji - 1;
		}
		if(name == null || name == "") name = "page";
		var oDiv = document.getElementById("PageData_"+name);
		if(oDiv == null) return;
		var flag = cocoform.submit(oForm, function() {
			oDiv.innerHTML = this.result;
			var o = document.getElementById("PageIndex_"+name);
			var pageIndex = o != null && !isNaN(o.innerText) ? parseInt(o.innerText) - 1 : 0;
			var o = document.getElementById("PageCount_"+name);
			var pageCount = o != null && !isNaN(o.innerText) ? parseInt(o.innerText) : 1;
			var o = document.getElementById("RecordCount_"+name);
			var recordCount = o != null && !isNaN(o.innerText) ? parseInt(o.innerText) : 0;
			oForm.elements["index"].value = pageIndex;
			oForm.elements["count"].value = recordCount;
			oForm.elements["pageCount"].value = pageCount;
			if(callback != null && typeof(callback) == "function") setTimeout(callback,0);
		}, null, function() {
			oDiv.innerHTML = "<br/><div style='width:100%' align=center><table align=center><tr><td align=center><img src='"+path+"/images/main/loader-bar.gif' /></td></tr><tr><td style='line-height:150%;font-size:14px;color:FF0000'>数据加载中,请等待...</td></tr></table></div><br/>";
		});
		
		
	}
};

/**
 * 树
 * @param name 页面引用名称
 * @param hiddenRootChild 隐藏根下级节点
 */
function Cocotree(name, hiddenRootChild) {
	
	this.encoding = "UTF-8";

	this.name = name;
	
	this.hiddenRootChild = hiddenRootChild;
	
	this.type = 0; //0-element; 1-attribute
	
	this.initialized = false;
	
	//以下划线为结尾的表示节点名称
	/**
	 * 根节点名称
	 */
	this.root_ = "root";
	/**
	 * 树型节点名称
	 */
	this.node_ = "item";
	/**
	 * ID节点名称
	 */
	this.id_ = "id";
	/**
	 * 上级ID名称
	 */
	this.pid_ = "pid";
	/**
	 * 显示节点名称
	 */
	this.label_ = "label";
	/**
	 * 活动显示节点名称
	 */
	this.title_ = "title";
	/**
	 * 叶子标志节点名称
	 */
	this.leaf_ = "leaf";
	/**
	 * 执行方法节点名称
	 */
	this.method_ = "method";
	/**
	 * 超链接节点名称
	 */
	this.link_ = "link";
	
	/**
	 * 链接目标节点名称
	 */
	this.target_ = "target";
	/**
	 * 显示图标节点名称
	 */
	this.img_ = "img";
	/**
	 * 展开图片节点名称
	 */
	this.img1_ = "img1";
	/**
	 * 灰度标志节点名称
	 */
	this.gray_ = "gray";
	/**
	 * 选择状态节点名称，对应值0-未选，1-选中，2-半选中
	 */
	this.check_ = "check";
	/**
	 * 复选框值对应节点名称
	 */
	this.value_ = "id";
	/**
	 * 根节点值
	 */
	this.rootId = 0;
	/**
	 * 默认超链接
	 */
	this.defaultLink = "#";
	/**
	 * 默认链接目标
	 */
	this.defaultTarget = "_self";
	/**
	 * 节点实例集合
	 * id : Node{id,node,....}
	 */
	this.nodes = {};
	/**
	 * 当前选中节点实例
	 * Node{id,node,...}
	 */
	this.selectedNode;
	/**
	 * 图标路径
	 */
	this.iconPath = "images/tree/"; 
	/**
	 * 应用根路径
	 */
	this.rootPath = null;
	/**
	 * 是否激活灰度
	 */
	this.iconGray = false;
	/**
	 * 复选框名称
	 */
	this.checkName = null;
	/**
	* 冒泡复选框
	*/
	this.bubbleCheck = true;
	/**
	 * 仅仅可以复选叶子
	 */
	this.onlyCheckLeaf = false;
	/**
	 * 页面区域
	 */
	this.area = null;
	/**
	 * 树型XML引用值
	 */
	this.xml = null;
	/**
	 * 树型XML对象
	 */
	this.oXml = null;
	/**
	 * 图标
	 */
	this.icons = {
		L0        : "L0.gif",  //┏
		L1        : "L1.gif",  //┣
		L2        : "L2.gif",  //┗
		L3        : "L3.gif",  //━
		L4        : "L4.gif",  //┃
		PM0       : "P0.gif",  //＋┏
		PM1       : "P1.gif",  //＋┣
		PM2       : "P2.gif",  //＋┗
		PM3       : "P3.gif",  //＋━
		empty     : "L5.gif",  //空白图
		root      : "root.gif",//缺省的根节点图标
		folder    : "folder.gif", //缺省的文件夹图标
		file      : "file.gif"   //缺省的文件图标
	};
	/**
	 * 展开图标
	 */
	this.eicons = { 
		PM0       : "M0.gif",     //－┏
		PM1       : "M1.gif",     //－┣
		PM2       : "M2.gif",     //－┗
		PM3       : "M3.gif",     //－━
		folder    : "folderopen.gif"
	};
	/**
	 * 颜色
	 */
	this.colors = {
		"highLight" : "#0A246A",
		"highLightText" : "#FFFFFF",
		"mouseOverBgColor" : "#D4D0C8"
	};
	/**
	 * 树初始化
	 */
	this.init = function() {
		this.initialized = true;
		for(var id in this.icons) {
			var img = new Image();
			img.src = this.iconPath + this.icons[id];
			this.icons[id] = img;
		}
		for(var id in this.eicons) {
			var img = new Image();
			img.src = this.iconPath + this.eicons[id];
			this.eicons[id] = img;
		}
	};
	this.childNodes = function(id) {
		if(this.oXml == null || id == null) return [];
		var xpath = this.root_ + "/" + this.node_;
		if(this.type == 1) xpath += '[@' + this.pid_ + '="' + id + '"]';
		else xpath += '[' + this.pid_ + '="' + id + '"]';
		return this.oXml.selectNodes(xpath);
	};
	this.nodeId = function(node) {
		return this.nodeValue(node,this.id_);
	};
	this.nodePid = function(node) {
		return this.nodeValue(node,this.pid_);
	};
	this.nodeName = function(node) {
		return this.nodeValue(node,this.label_);
	};
	this.nodeValue = function(node, tag_) {
		if(node == null || node.nodeType != 1 || tag_ == null || tag_ == "") return null;
		if(this.type == 1) return node.getAttribute(tag_);
		else {
			var o = node.selectSingleNode(tag_);
			return o != null ? o.text : null;
		}
		return null;
	};
	this.setNodeValue = function(node, tag_, v) {
		if(node == null || node.nodeType != 1 || tag_ == null || tag_ == "") return null;
		if(v == null) v = "";
		if(this.type == 1) node.setAttribute(tag_, v);
		else {
			var o = node.selectSingleNode(tag_);
			if(o != null) o.text = v;
			else {
				var o = node.ownerDocument.createElement(tag_);
				o.text = v;
				node.appendChild(o);
			}
		}
	};
	/**
	 * 页面装载初始化树
	 * @param areaId 显示域ID
	 * @param xml xml对象
	 */
	this.onload = function(areaId, xml) {
		var $this = this;
		coco.addEventListener(window, "load", function(event) {$this.create(areaId, xml);});
	};
	/**
	 * 创建树
	 * @param area 显示域
	 * @param xml xml对象
	 */
	this.create = function(area, xml) {
		if(area == null || xml == null) return;
		if(typeof(area) == "string") area = document.getElementById(area);
		if(area == null) return;
		this.area = area;
		area.innerHTML = this.build(xml);
	};
	this.checkXml = function(xml, ids) {
		if(xml == null || ids == null || ids.length == 0) return;
		var root = xml.nodeType == 9 ? xml.documentElement : (xml.nodeType == 1 ? xml : null);
		if(root == null) return;
		var path, items = [], item, pid;
		for(var i = 0; i < ids.length; i++) {
			path = this.node_+'['+ (this.type == 1 ? '@' : '') + this.id_ + '="'+ids[i] + '"]';
			item = root.selectSingleNode(path);
			if(item != null) {
				this.setNodeValue(item, this.check_, "2");
				items[items.length] = item;
			}
		}
		var pitem, pitems = {}, children;
		for(var i = 0; i < items.length; i++) {
			item = items[i];
			pid = this.nodeValue(item, this.pid_);
			var deep = 100;
			while(deep-- > 0) {
				if(pitems[pid] != null) break;
				path = this.node_+'['+ (this.type == 1 ? '@' : '') + this.id_ + '="'+ pid + '"]';
				pitem = root.selectSingleNode(path);
				if(pitem == null) break;
				pitems[pid] = pitem;
				path = this.node_+'['+ (this.type == 1 ? '@' : '') + this.pid_ + '="'+ pid + '"]';
				children = root.selectNodes(path);
				var f = 0;
				for(var j = 0; j < children.length; j++) {
					item = children[j];
					var chk = this.nodeValue(item, this.check_);
					if(chk == "2") f |= 2;
					else if(chk == "3") f = 3;
					else f |= 1;
					if(f == 3) break;
				}
				this.setNodeValue(pitem, this.check_, f == 3 ? "3" : (f == 2 ? "2" : "1"));
				pid = this.nodeValue(pitem, this.pid_);
			}
		}
		items = null;
		pitems = null;
	};
	this.build = function(xml) {
		if(!this.initialized) {this.init();}
		if(xml != null) this.xml = xml;
		if(typeof(this.xml) == "string") {
			this.oXml = coco.createDocument();
			if(this.xml.length > 0) this.oXml.load(this.xml);
		}
		else if(typeof(this.xml) == "object") this.oXml = this.xml;
		this.nodes = {};
		this.selectedNode = null;
		var nodes = this.childNodes(this.rootId);
		var html = '<DIV id="' + this.name + "$root_" + this.rootId + '" onclick="'+ this.name + '.clickHandle(event)" ondblclick="' + this.name + '.dblclickHandle(event);" >';
		var node, ids = [], id;
		if(nodes != null) {
			var size = nodes.length - 1;
			for(var i = 0; i <= size; i++) {
				node = nodes[i];
				id = this.nodeId(node);
				if(id == null || id == "") continue;
				ids[ids.length] = id;
				html += this.nodeHTML(nodes[i], i == size);
			}
		}
		html += '</DIV>';
		for(var i = 0; i < ids.length; i++) {
			var id = ids[i];
			var oNode = this.nodes[id];
			if(oNode == null) continue;
			var childNodes = oNode["childNodes"];
			if(childNodes != null) {
				var size = childNodes.length - 1;
				var childHTML = "";
				for(var j = 0; j <= size; j++) {
					childHTML += this.nodeHTML(childNodes[j], j == size);
				}
				if(childHTML != "") {
					var hiddenRootChild = this.hiddenRootChild ? "none" : "block";
					var reg = new RegExp('<SPAN style="DISPLAY: none" id="' + this.name + '\\$child_' + id + '" ></SPAN>',"gi");
					html = html.replace(reg, '<SPAN style="DISPLAY: '+hiddenRootChild+'" id="' + this.name + '$child_' + id + '" >'+childHTML+'</SPAN>');
				}
			}
			oNode["expand"] = this.hiddenRootChild ? false : true;
			oNode["loaded"] = true;
		}
		return html;
	};
	this.nodeHTML = function(node, atEnd) {
		var id = this.nodeId(node);
		if(id == null) return "";
		var pid = this.nodePid(node);
		if(pid == null) pid = this.rootId;
		var title = this.nodeValue(node, this.title_);
		var link, target;
		link = this.nodeValue(node, this.link_);
		if(link == null || link == "") link = this.defaultLink;
		target = this.nodeValue(node, this.target_);
		if(target == null || target == "") target = this.defaultTarget;
		var leaf = this.nodeValue(node, this.leaf_);
		var oParentNode = this.nodes[pid];
		var deep = 0, head = "";
		if(oParentNode != null) {
			deep = oParentNode["deep"] + 1;
			head = oParentNode["head"];
			if(head == null) head = "";
			head += atEnd ? " " : "4";
		}
		var oNode = null;
		this.nodes[id] = oNode = {
			id : id,
			node : node, 
			childNodes : this.childNodes(id), 
			parentNode : oParentNode, 
			deep : deep, 
			head : head, 
			expand : false,
			loaded : false,
			leaf : false
		};
		oNode["leaf"]  = leaf = (leaf == null && oNode["childNodes"].length == 0  || leaf == "1");
		var iconSrc, iconFilter = "";
		var isRoot = pid == this.rootId;
		var hasChild = oNode.childNodes != null && oNode.childNodes.length > 0;
		iconSrc = this.nodeValue(node, this.img_);
		if(iconSrc == null || iconSrc.length == 0) iconSrc = isRoot ? this.icons["root"].src : (hasChild || !leaf ? this.icons["folder"].src : this.icons["file"].src);
		if(this.iconGray && this.nodeValue(node, this.gray_) == "Y") {
			iconfFilter = 'style="filter:progid:DXImageTransform.Microsoft.BasicImage(grayScale=1);"';
		}
		var html = '<SPAN id="' + this.name + '$node_' + id + '" ><DIV NOWRAP class="Cocotree"><TABLE border=0 cellspacing=0 cellpadding=0 ><TR>';
		if(!isRoot) {
			for(var i = 0; i < head.length - 1; i++) {
				var img = null;
				switch (head.charAt(i)) {
					case ' ' : img = 'empty'; break;
					case '0' : img = 'L0'; break;
					case '1' : img = 'L1'; break;
					case '2' : img = 'L2'; break;
					case '3' : img = 'L3'; break;
					case '4' : img = 'L4'; break;
				}
				if(img != null) html += '<TD><IMG align="absMiddle" src="'+ this.icons[img].src +'" /></TD>';
			}
			oNode["eximg"] = (hasChild ? 'PM' : 'L') + (atEnd ? '2' : '1');
			var cursor = hasChild ? '' : 'cursor:hand;';
			html += '<TD><IMG align="absMiddle" id="' + this.name + '$expand_' + id + '" src="'+ this.icons[oNode["eximg"]].src + '" style="'+cursor+'" /></TD>';
		}
		if(this.checkName != null && this.checkName) {
			if(!this.onlyCheckLeaf || leaf) {
				var check = this.nodeValue(node, this.check_);
				var checked = check == 2 || check == 3;
				html += '<TD><input type="checkbox" onclick="' + this.name + '.checkHandle(event);coco.stopPropagation(event);" name="' + this.checkName + '" id="' + this.name + '$chk'+(leaf?'1' : '0')+'_' + id + '"'+(checked ? ' checked' : '') + ' value="' + this.nodeValue(node,this.value_) + '" /></TD>';
				if(!leaf && check == 3) {
					var _name = this.name ;
					setTimeout(function() {
						var oChk = document.getElementById(_name + '$chk0_' + id);
						if(oChk != null) oChk.indeterminate = true;
					}, 100);
				}
			}
		}
		var menu = "";
		if(this.leafMenu != null && leaf) menu = ' xu.menu="' + this.leafMenu + '" xu.id="'+id+'" ';
		else if(this.nodeMenu != null && !left) menu = ' xu.menu="' + this.nodeMenu + '" xu.id="'+id+'" ';
		html +=  '<TD><IMG align="absMiddle" id="' + this.name + '$icon_' + id + '" src="' + iconSrc + '" ' + iconFilter + '/></TD>';
		html += '<TD'+menu+'><A id="' + this.name + '$link_' + id + '" href="' + link + '" target="' + target + '" '+(title != null ? 'title="' + title + '" ' : '')+'>'+this.nodeName(node)+'</A></TD></TR></TABLE>';
		html += '</DIV><SPAN style="DISPLAY: none" id="' + this.name + "$child_" + id + '" ></SPAN></SPAN>';
		return html;
	};
	this.parseId = function(o) {
		if( o == null || o.id == null) return null;
		var i = o.id.indexOf("_");
		if(i == -1) return null;
		return o.id.substring(i + 1);
	};
	this.objIcon = function(id) {
		if(id == null) return null;
		return document.getElementById(this.name + "$icon_" + id);
	};
	this.objExpand = function(id) {
		if(id == null) return null;
		return document.getElementById(this.name + "$expand_" + id);
	};
	this.objLink = function(id) {
		if(id == null) return null;
		return document.getElementById(this.name + "$link_" + id);
	};
	this.objChild = function(id) {
		if(id == null) return null;
		return document.getElementById(this.name + "$child_" + id);
	};
	/**
	 * 单击操作
	 * @param e window.event 事件
	 */
	this.clickHandle = function(e) {
		e = e || window.event;
		if(e == null) return false;
		var o = e.srcElement || e.target;
		if(o == null) return false;
		var id = this.parseId(o);;
		if(id == null) return false;
		var oNode = this.nodes[id];
		if(oNode == null) return;
		switch(o.tagName) {
			case "IMG" :
				if (o.id.indexOf(this.name + "$expand_") == 0 || this.nodeValue(oNode["node"],this.pid_) == this.rootId) this.expand(id);
				else if(o.id.indexOf(this.name + "$icon_") == 0) this.nodeClick(id);
				break;
			case "A" :
				this.nodeClick(id);
				var method = this.selectedValue(this.method_);
				if(method != null && method.length > 0) try { eval(method);}catch(e){}
				break;
			default :
		}
	};
	/**
	 * 双击操作
	 * @param e window.event 事件
	 */
	this.dblclickHandle = function(e) {
		e = e || window.event;
		if(e == null) return false;
		var o = e.srcElement || e.target;
		if(o == null) return false;
		var id = this.parseId(o);
		if(id != null && (o.tagName == "A" || o.tagName == "IMG")) {
		  var oNode = this.nodes[id];
		  if(oNode == null) return false;
		  if(oNode.childNodes.length > 0) this.expand(id);
		  if(typeof(this.dblclick) == "function") try {this.dblclick(id);} catch(e){}
		}
	};
	/**
	 * 选择操作
	 * @param e window.event 事件
	 */
	this.checkHandle = function(e) {
		//若不需要递归级联选框则退出
		if(this.bubbleCheck != null && !this.bubbleCheck) return;
		e = e != null ? e : window.event;
		if(e == null) return false;
		var o = e.srcElement || e.target;
		if(o == null || o.tagName != "INPUT" || o.type != "checkbox" ) return false;
		if(this.oXml == null) return false;
		var id = this.parseId(o);
		var oNode = this.nodes[id];
		if(oNode == null) return false;
		//展开这个节点
		this.expandAll(id);
		var check = o.checked ? "2" : "1";
		this.setNodeValue(oNode["node"], this.check_, check);
		this.recurseCheck(oNode["childNodes"], check);
		var objChild = this.objChild(id);
		if(objChild != null) {
			var oChks = objChild.getElementsByTagName("INPUT");
			if(oChks != null) {
				var oChk;
				for(var i = 0; i < oChks.length; i++) {
					oChk = oChks[i];
					if(oChk.type == "checkbox" && oChk.name == this.checkName) {
						oChk.indeterminate = false;
						oChk.checked = o.checked;
					}
				}
			}
		}
		this.resurseCheckParent(oNode["parentNode"], check);
	};
	
	/**
	 * 指定节点向下选择
	 * @param nodes [] 指定节点集合
	 * @param check string 选择状态，0-未选，1-选中，2-半选
	 */
	this.recurseCheck = function(nodes, check) {
		if(nodes == null || nodes.length == 0) return;
		if(check == null) check = "1";
		for(var i = 0 ; i < nodes.length; i++) {
			this.setNodeValue(nodes[i], this.check_, check);
			this.recurseCheck(this.childNodes(this.nodeId(nodes[i])), check);
		}
	};
	
	/**
	 * 指定节点向上选择
	 * @param oNode object 指定节点
	 * @param check string 选择状态，0-未选，1-选中，2-半选
	 */
	this.resurseCheckParent = function(oNode, check) {
		if(oNode == null) return;
		var chk_0 = this.nodeValue(oNode["node"], this.check_), chk_1;
		if(chk_0 == null) chk_0 = "0";
		var childNodes = oNode["childNodes"];
		var chk0 = false, chk1 = false, chk2 = false;
		for(var i = 0; i < childNodes.length; i++) {
			chk_1 = this.nodeValue(childNodes[i], this.check_);
			if(chk_1 == 3) {
				chk2 = true;
				break;
			}
			else if(chk_1 == 2) chk1 = true;
			else chk0 = true;
		} 
		if(chk2 || chk0 && chk1) chk_1 = "3";
		else if(chk1) chk_1 = "2";
		else chk_1 = "1";
		if(chk_0 == chk_1) return;
		this.setNodeValue(oNode["node"], this.check_, chk_1);
		var objChk = document.getElementById(this.name + "$chk" + (oNode["leaf"] ? "1" : "0") + "_" + oNode["id"]);
		if(objChk) {
			switch(chk_1) {
				case "3" : 
						objChk.checked = true;
						objChk.indeterminate = true;
						break;
				case "2" : 
						objChk.checked = true;
						objChk.indeterminate = false;
						break;
				default :
						objChk.checked = false;
						objChk.indeterminate = false;
			}
		}
		this.resurseCheckParent(oNode["parentNode"]);
	};
	/**
	 * 展开指定节点Id
	 * @param ids [] 指定节点Id
	 * @param ex boolean 强制展开
	 */
	this.expand = function(id, ex) {
		if(id == null) return;
		var oNode = this.nodes[id];
		if(oNode == null) return;
		if(oNode["loaded"]) {
			if(ex && oNode["expand"]) return;
			var childNodes = oNode["childNodes"];
			if(childNodes != null && childNodes.length == 0) return;
			var objChild = this.objChild(id);
			if(objChild != null) objChild.style.display = oNode["expand"] ? "none" : "block";
			oNode["expand"] = !oNode["expand"];
		}
		else {
			var childNodes = oNode["childNodes"];
			if(childNodes != null && childNodes.length > 0) {
				var size = childNodes.length - 1;
				var childHTML = "";
				for(var j = 0; j <= size; j++) {
					childHTML += this.nodeHTML(childNodes[j], j == size);
				}
				var objChild = this.objChild(id);
				if(objChild != null) {
					objChild.innerHTML = childHTML;
					objChild.style.display = "block";
				}
			}
			oNode["expand"] = true;
			oNode["loaded"] = true;
		}
		this.changeImg(oNode);
	};
	
	/**
	 * 展开指定节点Id链
	 * @param ids [] 指定节点Id链
	 * @param f boolean 是否顺序，true-顺序，false-逆序
	 */
	this.expands = function(ids, f) {
		if(f) {
			for(var i = 0; i < ids.length; i++) {
				this.expand(ids[i], true);
				if(i == ids.length - 1) this.nodeFocus(ids[i]);
			}
		}
		else {
			for(var i = ids.length - 1; i >= 0; i--) {
				this.expand(ids[i], true);
				if( i == 0) this.nodeFocus(ids[i]);
			}
		}
	};
	
	/**
	 * 展开指定节点ID下的所有节点
	 * @param id string 指定节点Id
	 */
	this.expandAll = function(id) {
		if(id == null) id = this.rootId;
		var nodes = this.childNodes(id);
		if(nodes.length == 0)  return;
		for(var i = 0; i < nodes.length; i++) {
			this.expand(id, true);
			id = this.nodeId(nodes[i]);
			this.expandAll(id);
		}			
	};
	
	this.expandTo = function(id) {
		this.expands(this.ascendNodeIds(id));		
	};
	
	/**
	 * 展开时转换图标
	 * @param oNode object 指定节点
	 */
	this.changeImg = function(oNode) {
		if(oNode == null || oNode["childNodes"] == null || oNode["childNodes"].length == 0) return;
		var node = oNode["node"];
		var id = oNode.id;
		var expand = oNode["expand"];
		var objExpand = this.objExpand(id);
		if(objExpand != null) objExpand.src =  expand ? this.eicons[oNode["eximg"]].src : this.icons[oNode["eximg"]].src;
		var objIcon = this.objIcon(id);
		if(objIcon != null) {
			var imgSrc = this.nodeValue(node, this.img_);
			var icon = null;
			if(imgSrc == null || imgSrc.length == 0) {
				if(oNode["parentNode"]) icon = "folder";
				else return;
			}
			if(icon != null) {
				objIcon.src = expand ? this.eicons[icon].src : this.icons[icon].src;
			}
			else {
				var exImgSrc = this.nodeValue(node, this.img1_);
				if(exImgSrc != null && exImgSrc.length > 0) {
					if(this.rootPath != null) objIcon.src = this.rootPath + (expand ? exImgSrc : imgSrc);
					else objIcon.src = expand ? exImgSrc : imgSrc;
				}
				else {
					objIcon.style.filter = expand || !this.iconGray ? '' : 'progid:DXImageTransform.Microsoft.BasicImage(grayScale=1)';
				}
			}
		}
	};
	
	/**
	 * 聚焦节点
	 * @param id string 指定节点ID
	 */
	this.nodeFocus = function(id) {
		if(id == null) return;
		if(this.selectedNode) {
			var objLink = this.objLink(this.selectedNode["id"]);
			if(objLink) with(objLink.style){color = ""; backgroundColor=	"transparent"; }
		}
		var objLink = this.objLink(id);
		if(objLink) with(objLink.style){color = this.colors.highLightText; backgroundColor = this.colors.highLight;}
		
		this.selectedNode = this.nodes[id];
		if(typeof(this.select) == "function") {
			try {this.select(id);} catch(e){}
		}
	};
	
	/**
	 * 点击节点
	 * @param id string 指定节点ID
	 */
	this.nodeClick = function(id) {
		this.nodeFocus(id);
		if(typeof(this.click) == "function") {
			try {this.click(id);} catch(e){}
		}
	};
	
	/**
	 * 获取节点向上递归链
	 * @param id string 默认为当前节点ID
	 */
	this.ascendIds = function(id) {
		if(id == null && this.selectedNode != null) id = this.selectedNode["id"];
		if(id == null) return [];
		var ids = [];
		var oNode = this.nodes[id];
		if(oNode != null) {
			ids[ids.length] = id;
			oParentNode = oNode.parentNode;
			while(oParentNode != null) {
				ids[ids.length] = oParentNode.id;
				oParentNode = oParentNode.parentNode;
			}
		}
		return ids;
	};

	this.ascendNodeIds = function(id) {
		if(id == null || this.oXml == null || this.oXml.documentElement == null) return [];
		var root = this.oXml.documentElement;
		var xpath = "item[" + (this.type == 1 ? "@" : "") + this.id_ + "=\"";
		var ids = [];
		while(true) {
			node = root.selectSingleNode(xpath + id  + "\"]");
			if(node == null) break;
			ids[ids.length] = id;
			id = this.nodeValue(node, this.pid_);
		}
		return ids;
	};
	
	/**
	 * 刷新该树
	 * @param bFocus boolean 是否聚焦到当前节点
	 */
	this.refresh = function(bFocus) {
		if(this.area == null) return;
		var ids = null;
		if(bFocus) ids = this.ascendIds();
		this.create(this.area, this.xml);
		if(bFocus && ids != null && ids.length > 0) this.expands(ids);
	};
	
	/**
	 * 刷新该树并重新给定选框
	 * @param ids Array 选中值列表
	 */
	this.refreshCheck = function(ids) {
		if(this.area == null || this.oXml == null || this.checkName == null || this.check_ == null) return;
		if(ids == null) ids = [];
		else if(typeof(ids) == "string") ids = ids.split(",");
		else if(ids.length == null) return;
		var root = this.oXml.documentElement;
		var items = root.selectNodes(this.node_);
		for(var i = 0; i < items.length; i++) {
			this.setNodeValue(items[i], this.check_, "1");
		}
		this.checkXml(this.oXml, ids);
		this.create(this.area, this.oXml);
	};
	
	/**
	 * 列举选中的节点
	 * @param type int 类型，1-叶子，0-节点，null-全部
	 */
	this.checkedNodes = function(type) {
		var xpath = this.root_ + "/" + this.node_;
		if(this.type == 1) {
			xpath += '[(@' + this.check_ + '=2 or @' + this.check_+'=3)';
			if(type == 1) xpath += ' and @' + this.leaf_ + '=1';
			else if(type == 0) xpath += ' and (not(@'+this.leaf_+') or @' + this.leaf_ + '!=1)';
			xpath += ']';
		}
		else {
			xpath += '[(' + this.check_ + '=2 or ' + this.check_+'=3)';
			if(type == 1) xpath += ' and ' + this.leaf_ + '=1';
			else if(type == 0) xpath += ' and (not('+this.leaf_+') or ' + this.leaf_ + '!=1)';
			xpath += ']';
		}
		return this.oXml.selectNodes(xpath);
	};
	
	/**
	 * 列举选中的节点
	 * @param type int 类型，1-叶子，0-节点，null-全部
	 * @param tag string 字段，默认为Id
	 */
	this.checkeds = function(type, tag) {
		var nodes = this.checkedNodes(type);
		var ids = [];
		if(tag == null || tag == "") tag = this.id_;
		for(var i = 0; i < nodes.length; i++) {
			ids[ids.length] = this.nodeValue(nodes[i], tag);
		}
		return ids;
	};
	
	
	/**
	 * 列举未选中的节点
	 * @param type int 类型，1-叶子，0-节点，null-全部
	 * @param tag string 字段，默认为Id
	 */
	this.uncheckeds = function(type, tag) {
		var xpath = this.root_ + "/" + this.node_;
		if(this.type == 1) {
			xpath += '[(not(@' + this.check_ + ') or @' + this.check_+'=1)';
			if(type == 1) xpath += ' and @' + this.leaf_ + '=1';
			else if(type == 0) xpath += ' and (not(@'+this.leaf_+') or @' + this.leaf_ + '!=1)';
			xpath += ']';
		}
		else {
			xpath += '[(not(' + this.check_ + ') or ' + this.check_+'=1)';
			if(type == 1) xpath += ' and ' + this.leaf_ + '=1';
			else if(type == 0) xpath += ' and (not('+this.leaf_+') or ' + this.leaf_ + '!=1)';
			xpath += ']';
		}
		var nodes = this.oXml.selectNodes(xpath);
		var ids = [];
		if(tag == null || tag == "") tag = this.id_;
		for(var i = 0; i < nodes.length; i++) {
			ids[ids.length] = this.nodeValue(nodes[i], tag);
		}
		return ids;
	};
	
	this.selectedValue = function(tag) {
		if(this.selectedNode == null) return null;
		if(tag == null) tag = this.id_;
		return this.nodeValue(this.selectedNode["node"], tag);
	};
};

var cocoform = {
	fes : [],
	documentBandClickEvent : false,
	value : function(el) {
		if(el.type == "text" || el.type == "textarea" || el.type == "password" || el.type == "hidden")  return el.value; 
	    else if(el.type=="select-one" || el.type=="select-multiple") {
	    	var options = el.options, item;
	    	var values = [];
			for(var j = 0; j < options.length; j++) {
	    		item=options[j];
	    		if(item.selected) values[values.length] = item.value;
			}
			if(values.length == 0) return "";
			return values.length > 1 ? values : values[0];
		} 
		else if(el.type == "checkbox") {
			if(el.checked) return coco.getAttr(el,"y") != null ? "Y" : (coco.getAttr(el,"b") != null ? "true" : el.value);
			if(coco.getAttr(el, "y") != null) return "N";
			if(coco.getAttr(el, "b") != null) return "false";
			return "";
		}
		else if(el.type == "radio") return el.checked ? el.value : "";
		else if(el.type == "file")  return "";
		return el.value != null ? el.value : "";
	},
	register : function(el) {
		var feIndex = coco.getAttr(el, "xu.index");
		var fe = null;
		if(feIndex != null && !isNaN(feIndex) && feIndex >= 0) fe = cocoform.fes[feIndex];
		if(fe == null || fe.el != el) {
			var sprop = coco.getAttr(el, "xu.prop");
			var prop = null;
			if(sprop != null) {
				sprop = sprop.replace(/(^\s+)|(\s+$)/g,"").replace(/\s*:\s*/g, ":\"").replace(/(\s*;\s*)/g, "\",").replace(/,\s*$/g,"");
				if(sprop.charAt(sprop.length - 1) != '"') sprop += '"';
				try { eval("prop={" + sprop + "};" );} catch(e){}
			}
			feIndex = cocoform.fes.length;
			coco.setAttr(el, "xu.index", feIndex);
			coco.setAttr(el, "xu.reload", "false");
			cocoform.fes[feIndex] = fe = {id : feIndex, el : el, prop : prop, value: null};
		}
		else if(coco.getAttr(el, "xu.reload") == "true") {
			var sprop = coco.getAttr(el, "xu.prop");
			var prop = null;
			if(sprop != null) {
				sprop = sprop.replace(/(^\s+)|(\s+$)/g,"").replace(/\s*:\s*/g, ":\"").replace(/(\s*;\s*)/g, "\",").replace(/,\s*$/g,"");
				if(sprop.charAt(sprop.length - 1) != '"') sprop += '"';
				try { eval("prop = {" + sprop + "};" );} catch(e){}
			}
			fe['prop'] = prop == null ? {} : prop;
			coco.setAttr(el, "xu.reload", "false");
		}
		fe.value = cocoform.value(fe.el);
		if(fe.prop == null || fe.prop.trim != 0) fe.value = fe.value.replace(/(^\s+)|(\s+$)/g, "");
		return fe;
	},
	verify : function(el) {
		if(el == null) return true;
		if(el.tagName == "FORM") {
			var els = el.elements;
			var el0, elIndex = 0;
			while((el0 = els[elIndex++]) != null) {
				if(!cocoform.verify(el0)) return false;
			}
			return true;
		}
		if(el.name == null || el.name == "") return true;
		var fe = cocoform.register(el);
		var msgbox = document.getElementById("cocoform$msgbox$"+fe.id);
		if(msgbox != null && msgbox.style.display != "none") msgbox.style.display = "none";
		if(fe.prop == null) return true;
		if(fe.prop.nn && (fe.value == null || fe.value == "")) {
			cocoform.alert("不能为空", fe);
			el.focus();
			return false;
		}
		try {
			switch(fe.prop.type) {
				case "digit" : return cocoform.verifyDigit(fe);
				case "int" : return cocoform.verifyInteger(fe);
				case "number" : return cocoform.verifyNumber(fe);
				case "date" : ;
				case "datetime" : return cocoform.verifyDate(fe);
				case "time" : return cocoform.verifyTime(fe);
				case "idcard" : return cocoform.verifyIdcard(fe);
				case "email" : return cocoform.verifyEmail(fe);
				case "zipcode" : return cocoform.verifyZipcode(fe);
				case "abcn" : return cocoform.verifyAbcn(fe);
				case "custom" : return cocoform.verifyCustom(fe);
				default: return true;
			}
		}
		catch(e) {
			cocoform.alert(e, fe);
			return false;
		}
	},
	verifyDigit : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var value = fe.value;
		if(/^\d+$/.test(value)) return true;
		throw "请全部输入数字";
	},
	verifyAbcn : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var value = fe.value;
		if(/^[a-zA-Z0-9]+$/.test(value)) return true;
		throw "请全部输入字母和数字";
	},
	verifyNumber : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var value = fe.value;
		if(/^(|-|\+)\d+(|\.\d+)$/.test(value)) {
			var scale = fe.prop.scale;
			//不限制大小
			if(scale == null) return true;
			var scales = scale.split(",");
			var s = parseInt(scales[0]);
			if(isNaN(s)) return true;
			var p = scales.length > 1 ? parseInt(scales[1]) : 0;
			if(isNaN(p) || p < 0) p = 0;
			var i = s - p;
			var v = value.replace(/-/,'');
			var dot = v.indexOf('.');
			if(dot == -1 && v.length > i || dot > i) throw "输入值整数超过限制位数";
			if(dot != -1 && v.length - dot - 1 > p) throw "输入值小数超过限制位数";
			return true;
		}
		throw "请输入有效数值";
	},
	verifyInteger : function (fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var value = fe.value;
		if(!isNaN(value) && parseInt(value) == value) return true;
		throw "请输入整数";
	},
	verifyEmail : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var value = fe.value;
		if(( /^\w+((-\w+)|(\.\w+))*\@\w+((\.|-)\w+)*\.\w+$/).test(value)) return true;
		throw "请有效的Email地址";
	},
	verifyZipcode : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var value = fe.value;
		if(/^[1-9]\d{5}$/.test(value)) return true;
		throw "请有效的邮政编码";
	},
	verifyCustom : function(fe) {
		if(fe == null || fe.value == null || fe.value == "" || fe.prop == null || fe.prop.rule == null) return true;
		var value = fe.value;
		var reg = new RegExp(fe.prop.rule, fe.prop.rule1);
		if((reg).test(value)) return true;
		throw fe.prop.msg ? fe.prop.msg : "请输入有效的数据";
	},
	verifyDate : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var obj = fe.el;
		//长时间标志
		var ld = fe.prop.type == "datetime";
		var v = fe.value.replace(/\s+/g, " ");
		var vs = v.split(" ");
		var dates = [], times = [];
		var date = vs[0].replace(/\.|\-|\//g,"");
		var y,m,d,h,n,s;
		if(date.length >= 1) d = new Number(date.substring(Math.max(0,date.length - 2),date.length));
		if(date.length >= 3) m = new Number(date.substring(Math.max(0,date.length - 4),date.length - 2));
		if(date.length >= 5) y = new Number(date.substring(Math.max(0,date.length - 8),date.length - 4));
		if(vs.length > 1) {
			var time = vs[1].replace(/\.|:|\//g,"");
			if(time.length >= 1) h = new Number(time.substring(0,Math.min(2,time.length)));
			if(time.length >= 3) n = new Number(time.substring(2,Math.min(4,time.length)));
			if(time.length >= 5) s = new Number(time.substring(4,Math.min(6,time.length)));
		}
		var curr = new Date();
		if(y == null || isNaN(y) || y == 0) y = curr.getFullYear();
		else if(y < 100) y = 2000 + y;
		if(m == null || isNaN(m) || m == 0) m = curr.getMonth() + 1;
		if(d == null || isNaN(d) || d == 0) d = curr.getDate();
		if(h == null || isNaN(h)) h = 0;
		if(n == null || isNaN(n)) n = 0;
		if(s == null || isNaN(s)) s = 0;
		//判断日期是否有效
		if(m <= 0 || m > 12) throw ("无效月份");
		if(m  == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
			if(d <= 0 || d > 31) throw ("无效日期");
		}
		else if(m == 2) {
			//闰年
			if(y % 4 == 0 && y % 400 != 0) {
				if(d <= 0 || d > 29) throw ("无效日期");
			}
			else {
				if(d <= 0 || d > 28) throw ("无效日期");
			}
		}
		else {
			if(d <= 0 || d > 30) throw ("无效日期");
		}
		//短时间
		if(!ld) {
			fe.value = obj.value = y + "-" + (m > 9 ? m : "0"+m) + "-" + (d > 9 ? d : "0"+d);
			return true;
		}
		if(h < 0 || h > 24) throw ("无效小时");
		if(n < 0 || n > 59) throw ("无效分钟");
		if(s < 0 || s > 59) throw ("无效秒");
		fe.value = obj.value = y + "-" + (m > 9 ? m : "0"+m) + "-" + (d > 9 ? d : "0"+d) + " " + (h > 9 ? h : "0"+h) + ":" + (n > 9 ? n : "0"+n)+ ":" + (s > 9 ? s : "0"+s);
		return true;
	},

	verifyTime : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var obj = fe.el;
		//长时间标志
		var ld = fe.prop.l != null;
		var v = fe.value.replace(/\s+/g, "");
		if(v == "") return;
		var times = [];
		var h,n,s;
		var time = v.replace(/\.|:|\//g,"");
		if(time.length >= 1) h = new Number(time.substring(0,Math.min(2,time.length)));
		if(time.length >= 3) n = new Number(time.substring(2,Math.min(4,time.length)));
		if(time.length >= 5) s = new Number(time.substring(4,Math.min(6,time.length)));
		if(h == null || isNaN(h)) h = 0;
		if(n == null || isNaN(n)) n = 0;
		if(s == null || isNaN(s)) s = 0;
		if(h < 0 || h > 24) throw ("无效小时");
		if(n < 0 || n > 59) throw ("无效分钟");
		if(s < 0 || s > 59) throw ("无效秒");
		fe.value = obj.value = (h > 9 ? h : "0"+h) + ":" + (n > 9 ? n : "0"+n) + (ld ? (":" + (s > 9 ? s : "0"+s)) : "");
		return true;
	},
	verifyIdcard : function(fe) {
		if(fe == null || fe.value == null || fe.value == "") return true;
		var obj = fe.el;
		var idcard = fe.value;
		var area = {11:"\u5317\u4eac", 12:"\u5929\u6d25", 13:"\u6cb3\u5317", 14:"\u5c71\u897f", 15:"\u5185\u8499\u53e4", 21:"\u8fbd\u5b81", 22:"\u5409\u6797", 23:"\u9ed1\u9f99\u6c5f", 31:"\u4e0a\u6d77", 32:"\u6c5f\u82cf", 33:"\u6d59\u6c5f", 34:"\u5b89\u5fbd", 35:"\u798f\u5efa", 36:"\u6c5f\u897f", 37:"\u5c71\u4e1c", 41:"\u6cb3\u5357", 42:"\u6e56\u5317", 43:"\u6e56\u5357", 44:"\u5e7f\u4e1c", 45:"\u5e7f\u897f", 46:"\u6d77\u5357", 50:"\u91cd\u5e86", 51:"\u56db\u5ddd", 52:"\u8d35\u5dde", 53:"\u4e91\u5357", 54:"\u897f\u85cf", 61:"\u9655\u897f", 62:"\u7518\u8083", 63:"\u9752\u6d77", 64:"\u5b81\u590f", 65:"\u65b0\u7586", 71:"\u53f0\u6e7e", 81:"\u9999\u6e2f", 82:"\u6fb3\u95e8", 91:"\u56fd\u5916"};
		var Y, JYM;
		var S, M;
		var idcard_array = new Array();
		idcard_array = idcard.split("");
		//地区检验
		if (area[parseInt(idcard.substr(0, 2))] == null) throw "\u5730\u533a\u975e\u6cd5!";
		//身份号码位数及格式检验
		switch (idcard.length) {
		  case 15:
			if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
			} else {
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
			}
			if (ereg.test(idcard)) return true;
			else throw "\u51fa\u751f\u65e5\u671f\u8d85\u51fa\u8303\u56f4\u6216\u542b\u6709\u975e\u6cd5\u5b57\u7b26!";
		  case 18:
				//18位身份号码检测
				//出生日期的合法性检查
				//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
				//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
				ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
			} else {
				ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
			}
			if (ereg.test(idcard)) {//测试出生日期的合法性
					//计算校验位
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
				Y = S % 11;
				M = "F";
				JYM = "10X98765432";
				M = JYM.substr(Y, 1);//判断校验位
				//检测ID的校验位
				if (M == idcard_array[17])  return true;
				else throw "\u6821\u9a8c\u4f4d\u9519\u8bef!";
			} 
			else throw "\u51fa\u751f\u65e5\u671f\u8d85\u51fa\u8303\u56f4\u6216\u542b\u6709\u975e\u6cd5\u5b57\u7b26!";
			break;
		  default:
			throw "\u4f4d\u6570\u4e0d\u5bf9!";
		}
		return true;
	},
	validateKey : function(el,event) {
		if(el == null || event == null) return true;
		var kc = ie ? event.keyCode : event.which;
		if(kc == null) return true;
		//忽略backspace, tab, enter, end, home, to left, to right, delete
		if(kc == 8 || kc == 9 || kc == 13 || kc == 35 || kc == 36 || kc == 37 || kc == 39 || kc == 46) return true;
		var fe = cocoform.register(el);
		if(fe.prop == null) return true;
		var pass = true;
		try {
			switch(fe.prop.type) {
				case "digit" :;
				case "zipcode" :
								pass = cocoform.validateKeyDigit(fe,event,kc); 
								break;
				case "idcard" : 
								pass = cocoform.validateKeyIdcard(fe,event,kc); 
								break;
				case "abcn" : 
								pass = cocoform.validateKeyAbcn(fe,event,kc); 
								break;
				case "int" :
								pass = cocoform.validateKeyInteger(fe,event,kc);
								break;
				case "number" : 
								pass = cocoform.validateKeyNumber(fe,event,kc);
								break;
				case "date" :
								pass = cocoform.validateKeyDate(fe,event,kc);
								break;
				case "datetime" : 
								pass = cocoform.validateKeyDateTime(fe,event,kc);
								break;
				case "time" : 
								pass = cocoform.validateKeyTime(fe,event,kc);
								break;
				default:
					return true;
			}
		}
		catch(e) {
			return true;
		}
		if(!pass) {
			if(ie) {
				event.returnValue = false;
				event.cancelBubble = true;
			}
			else {
				coco.stopPropagation(event, true);
			}
			return false;
		}
		return true;
	},
	validateKeyDigit : function(fe,event,kc) {
		return kc > 47 && kc < 58 || kc > 95 && kc < 106;
	},
	validateKeyIdcard : function(fe,event,kc) {
		return kc > 47 && kc < 58 || kc > 95 && kc < 106 || kc == 88;
	},
	validateKeyAbcn : function(fe,event,kc) {
		return kc > 47 && kc < 58 || kc > 95 && kc < 106 || kc >= 65 && kc <= 90;
	},
	validateKeyInteger : function(fe,event,kc) {
		//光标位置
		var cursorIndex = coco.getCursorIndex(fe.el);
		//值
		var v = fe.el.value;
		//是否已经有'-'
		var hasNeg = v.indexOf('-') != -1;
		//0~9
		if(kc > 47 && kc < 58 || kc > 95 && kc < 106) return cursorIndex != 0 || !hasNeg;		
		//-
		if(kc == 109 || kc == 189) return fe.prop.positive == null && !hasNeg && cursorIndex == 0;
		return false;
	},
	validateKeyNumber : function(fe,event,kc) {
		//光标位置
		var cursorIndex = coco.getCursorIndex(fe.el);
		//值
		var v = fe.el.value;
		//是否已经有'-'
		var hasNeg = v.indexOf('-') != -1;
		//是否已经有'.'
		var hasDot = v.indexOf('.') != -1;
		//0~9
		if(kc > 47 && kc < 58 || kc > 95 && kc < 106) return cursorIndex != 0 || !hasNeg;		
		//-
		if(kc == 109 || kc == 189) return fe.prop.positive == null && !hasNeg && cursorIndex == 0;
		//.
		if(kc == 110 || kc == 190) return !hasDot;
		return false;
	},
	validateKeyDate : function(fe,event) {
		return kc > 47 && kc < 58 || kc > 95 && kc < 106 || kc == 109 || kc == 189;
	},
	validateKeyDateTime : function(fe,event) {
		return kc > 47 && kc < 58 || kc > 95 && kc < 106 || kc == 32 || kc == 109 || kc == 189 || event.shiftLeft && event.shiftKey && kc == 186;
	},
	validateKeyTime : function(fe,event) {
		return kc > 47 && kc < 58 || kc > 95 && kc < 106 || event.shiftLeft && event.shiftKey && kc == 186;
	},
	postContent : function(oForm) {
		if(oForm == null) return "";
		if(typeof(oForm) == "string") oForm = document.forms[oForm];
		if(oForm.tagName != "FORM") return "";
		var els = oForm.elements;
		//IE中如果有元素名为length的话，elements.length将得到的是length元素而不是元素数量。
		//故就此问题不能用oForm.elements.length;
		var el, elIndex = 0;
		while((el = els[elIndex++]) != null) {
			cocoform.register(el);
		}
		var formElements = [];
		for(var i = 0; i < cocoform.fes.length; i++) {
			if(cocoform.fes[i].el.form == oForm) formElements[formElements.length] = cocoform.fes[i];
		}
		var fe, element;
		var postContent = "";
		for(var i = 0; i < formElements.length; i++) {
			fe = formElements[i];
			element = fe.el;
			if(element.name == null || element.name == "") continue;
			if(fe.el.type == "radio" && !fe.el.checked) continue;
			if(fe.el.type == "checkbox" && !fe.el.checked && (coco.getAttr(fe.el, "y") == null || coco.getAttr(fe.el, "b") == null)) continue;
			if(postContent.length > 0) postContent += "&";
			if(fe.value == null) postContent += encodeURIComponent(element.name) + "="; 
			else if(typeof(fe.value) == "string") postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(fe.value); 
			else if(fe.value.constructor == Array) {
				for(var i = 0; i < fe.value.length; i++) {
					if(i > 0) postContent += "&";
					postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(fe.value[i]); 
				}
			}
		}
		return postContent;
	},
	/**
	 * 解析选择框的值成提交串
	 * @param form 表单名称，表单对象
	 * @param name 表单中选框name名称
	 * @param field 提交表单数据的字段名，为null表示与name同名
	 * @return String 返回字符串，为null表示没有数据，为空表示没有选中数据，有选中则返回如"id=1001&id=1002"的串
	 */
	postCheckValues : function(form, name, field) {
		if(form == null || name == null) return null;
		if(typeof(form) == "string") form = document.forms[form];
		var ochks = form.elements[name];
		if(ochks == null) return null;
		if(field == null) field = name;
		if(ochks.tagName != null) return ochks.checked ? (field + "=" + encodeURIComponent(ochks.value)): "";
		var content = "";
		for(var i = 0; i < ochks.length; i++) {
			if(ochks[i].checked) content += "&" + field + "=" + encodeURIComponent(ochks[i].value);
		}
		return content.length > 0 ? content.substring(1) : "";
	},
	/**
	 * 解析选择框的值成提交串
	 * @param form 表单名称，表单对象
	 * @param name 表单中选框name名称
	 * @param field 提交表单数据的字段名，为null表示与name同名
	 * @return String 返回所有选择框的值字符串，为null表示没有数据，
	 * @author 张红国
	 */
	postCheckAllValues : function(form, name, field) {
		if(form == null || name == null) return null;
		if(typeof(form) == "string") form = document.forms[form];
		var ochks = form.elements[name];
		if(ochks == null) return null;
		if(field == null) field = name;
		if(ochks.tagName != null) return ochks.checked ? (field + "=" + encodeURIComponent(ochks.value)): "";
		var content = "";
		for(var i = 0; i < ochks.length; i++) {
			content += "&" + field + "=" + encodeURIComponent(ochks[i].value);
		}
		return content.length > 0 ? content.substring(1) : "";
	},
	alert : function(msg, fe) {
		if(fe == null) return;
		var el = fe.el;
		el.focus();
		if(coco.getAttr(el, "xu.type") != "combobox" && (el.type == "hidden" || el.style.display == "none" || el.style.visibility == "hidden")) {
			alert(el.title + " : " + msg);
			return;
		}
		var msgbox = document.getElementById("cocoform$msgbox$"+fe.id);
		if(msgbox == null) {
			msgbox = document.createElement("DIV");
			msgbox.id = "cocoform$msgbox$"+fe.id;
			msgbox.style.position = "absolute";
			msgbox.style.zIndex = "9000";
			
			msgbox.style.left = 0;
			msgbox.style.top = 0;
			msgbox.style.display = "none";
			var innerHTML = '<DIV class="msgbox"></DIV>';
			msgbox.innerHTML = innerHTML;
			document.body.appendChild(msgbox);
			//coco.addEventListener(msgbox, "dblclick", function(event) { msgbox.style.display = "none"; });
			coco.addEventListener(msgbox, "click", function(event) { msgbox.style.display = "none"; });
		}
		if(coco.getAttr(el, "xu.type") == "combobox") el = el.previousSibling;
		var xy = coco.getXY(el);
		if(xy[0] == 0 && xy[1] == 0) {
			xy = coco.getXY(el.parentNode);
			if(coco.getAttr(el.parentNode,"bandEvent") == null) {
				coco.addEventListener(el.parentNode, "mouseover", function(event) { msgbox.style.display = "none"; });
				coco.setAttr(el.parentNode, "bandEvent", "true");
			}
		}
		else if(coco.getAttr(el,"bandEvent") == null) {
			coco.addEventListener(el, "click", function(event) { msgbox.style.display = "none"; });
			coco.addEventListener(el, "keydown", function(event) { msgbox.style.display = "none"; });
			coco.setAttr(el, "bandEvent", "true");
		}
		msgbox.style.left =(xy[0] + 20) + "px";
		msgbox.style.top = xy[1] + "px";
		msgbox.childNodes[0].innerHTML = msg;
		msgbox.style.display = "block";
	},
	clear : function(form, ignores) {
		if(typeof(form) == "string") form = document.forms[form];
		if(form == null) return;
		var els = form.elements;
		var el, elIndex = 0;
		while((el = els[elIndex++]) != null) {
			if(el.name == "") continue;
			if(ignores != null) {
				var flag = false;
				for(var i = 0; i < ignores.length; i++) {
					if(ignores[i] == el.name) {
						flag = true;
						break;
					}
				}
				if(flag) continue;
			}
			if(el.type == "text" || el.type == "textarea" || el.type == "password" || el.type == "hidden")  {
				el.value = "";
				if(coco.getAttr(el, "xu.type") == "combobox") el.previousSibling.value = "";
			}
		    else if(el.type=="select-one" || el.type=="select-multiple") {
		    	el.value = "";
		    	el.selectedIndex = -1;
			}
			else if(el.type == "checkbox" ) {
				el.checked = false;
			}
			else if(el.type == "radio") {
				el.checked = false;
			}
		}
	},
	/**
	 * 填写表单信息
	 * @param form 表单对象
	 * @param obj 表单值对象
	 * @param prefix 表单名称前缀
	 */
	fillObject : function(form, obj, prefix) {
		if(form == null || obj == null) return;
		if(prefix == null) prefix = "";
		var el;
		for(var id in obj) {
			if(id == "") continue;
			var value = obj[id];
			if(value != null && typeof(value) == "object" && value.constructor != Array) {
				cocoform.fillObject(form, value, prefix + id + ".");
				continue;
			}
			el = form.elements[prefix + id];
			if(el == null) continue;
			//新增下面两行的目的是在做信息维护的时候，对改变的内容自动变色
			if(el.style != null) el.style.color = "";
			if(coco.getAttr(el, "init.value") != null) coco.removeAttr(el, "init.value");
			cocoform.setValue(el, value);
		}
	},
	setValue : function(el, value) {
		if(value == null) value = "";
		if(el.type == null && el.length > 0 && typeof(el) != "string") {
			if(el[0].type == "checkbox") {
				if(value !=null && typeof(value) == "object" && value.constructor == Array) {
					for(var i = 0; i < el.length; i++) {
						el[i].checked = false;
						for(var j = 0; j < value.length; j++) {
							if(el[i].value == value[j]) {
								el[i].checked = true;
								break;
							}
						}
					}
				}
				else {
					for(var i = 0; i < el.length; i++) {
						el[i].checked = el.value == value;
					}
				}
			}
			else {
				if(value != null && typeof(value) == "object" && value.constructor == Array) {
					for(var i = 0; i < el.length; i++) {
						cocoform.setValue(el[i], value.length > i ? value[i] : "");
					}
				}
				else {
					cocoform.setValue(el[0], value);
				}
			}
			return;
		}
		if(el.type == "text" || el.type == "textarea" || el.type == "password" || el.type == "hidden")  {
			el.value = value;
			if(coco.getAttr(el, "xu.type") == "combobox") {
				var boxTxt = el.previousSibling;
				var comp = cococombobox.init(boxTxt);
				var items = comp.items;
				var selectedIndex = -1;
				for(var i = 0; i < items.length; i++) {
					if(items[i][0] == value) {
						selectedIndex = i;
						break;
					}
				}
				if(selectedIndex == -1 && !comp.params.match) {
					boxTxt.value = value;
				}
				else {
					comp.selectedIndex = selectedIndex;
					cococombobox.changeValue(comp);
				}
			}
		} 
	    else if(el.type=="select-one"||el.type=="select-multiple") {
	    	var options = el.options, item;
	    	coco.setAttr(el, "xu.value", value);
			for(var j = 0; j < options.length; j++) {
				item = options[j]; 
				item.selected = (item.value == value);
			}
		}
		else if(el.type == "checkbox") {
			if(coco.getAttr(el,"y") != null) el.checked = value == "Y";
			else if(coco.getAttr(el,"b") != null) el.checked = value;
			else el.checked = el.value == value;
		}
		else if(el.type == "radio") {
			if(el.value == value) el.checked = true;
		}
		else if(typeof(el) != "string"){
			el.value = value;
		}
	},
	changeValue : function(target) {
		//级联
		var cascade = coco.getAttr(target, "xu.cascade");
		if(cascade != null) {
			try {eval("cascade="+cascade);}catch(e){}
			if(typeof(cascade) == "object") {
				var key, module;
				key = cascade.key;
				module = cascade.module;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.status == "550") {
						alert(key + "还没有实现级联查询器");
						return;
					}
					else if(this.status == "500") {
						alert("级联查询出错:" + this.result);
						return;
					}
					var result = null;
					try { eval("result="+this.result); } catch(e){}
					if(result == null) {
						alert("级联查询返回无效的数据");
						return;
					}
					var obj,fieldName, fieldValue;
					var fields = cascade.fields;
					if(fields == null) fields = result;
					for(var field in fields) {
						if(field == "$") continue;
						fieldName = null;
						if(cascade.fields != null && cascade.fields[field] != null) fieldName = cascade.fields[field][0];
						if(fieldName == null || fieldName == "") fieldName = field;
						obj = target.form.elements[fieldName];
						if(obj == null) continue;
						fieldValue = result[field];
						if(fieldValue == null) fieldValue = result["$"];
						if(fieldValue == null) fieldValue = [""];
						//级联下拉框
						if(fieldValue[1] == 1) {
							var theValue = obj.value;
							if(theValue == "") theValue = coco.getAttr(obj, "xu.value");
							obj.innerHTML = "";
							var options = fieldValue[0];
							//头信息
							if(cascade.fields != null && cascade.fields[field] != null && cascade.fields[field][1] != null) options = '<option value="">'+cascade.fields[field][1]+'</option>' + options;
							if(ie) obj.outerHTML = obj.outerHTML.replace(/<\/select>$/i, options + "</select>");
							else obj.innerHTML = options;
							if(theValue != null && theValue != "") cocoform.setValue(target.form.elements[fieldName],theValue);
						}
						else obj.value = fieldValue[0];
					}
					//清空其他控件
					var clears = cascade.clears;
					if(clears != null) {
						for(var i = 0; i < clears.length; i++) {
							obj  = target.form.elements[clears[i]];
							if(obj == null) continue;
							if(obj.tagName == "SELECT") {
								obj.innerHTML = "";
								if(ie) obj.outerHTML = obj.outerHTML;
							}
							else obj.value = "";
						}
					}
				};
				var url = path + "/coco/cascade.do?key=" + encodeURIComponent(key) + "&value="+encodeURIComponent(target.value);
				if(module != null) url += "&module=" + encodeURIComponent(module);
				ajax.post(url,null,"GET");
			}
		}
	},
	/**
	 * 填写表单信息
	 * @param form 表单对象
	 * @param result 表单值对象js字符串
	 * @param prefix 表单名称前缀
	 * @param noClear 不清空表单标志
	 */
	fillResult : function(form, result, prefix, noClear) {
		var obj = null;
		eval("obj = " + result + ";");
		if(typeof(form) == "string") form = document.forms[form];
		if(form == null) return;
		if(noClear == null || !noClear) cocoform.clear(form);	
		cocoform.fillObject(form, obj, prefix);
		return obj;
	},
	onload : function(event) {
		if(event == null) event = window.event;
		var forms = document.forms;
		for(var i = 0; i < forms.length; i++) {
			var oForm = forms[i];
			if(coco.getAttr(oForm, "xu.ajax") != null) {
				 oForm.onsubmit = function(event) {
					try { cocoform.submit(this); }catch(e){alert(e.description);}
					return false; 
				};
			}
			if(coco.getAttr(oForm, "xu.color") != null) {
				var eles = oForm.elements;
				var el,elIndex = 0;
				while((el = eles[elIndex++]) != null) {
					if(el.tagName == "INPUT" && el.type == "text") {
						coco.addEventListener(el, "focus", function() {
							var target = this.event.target || this.event.srcElement;
							if(target == null) return;
							var initV = coco.getAttr(target, "init.value");
							if(initV == null) coco.setAttr(target, "init.value", target.value);
						});
					}
				}
			}
		}
	},
	onmouseover : function(event) {
		if(event == null) event = window.event;
		var target = event.target || event.srcElement;
		if(target == null) return;
		var oForm = target.form;
		if(oForm == null) return;
		if(coco.getAttr(oForm, "xu.color") == null) return;
		if((target.tagName == "INPUT" && target.type == "text") || target.tagName == "SELECT") {
			var initV = coco.getAttr(target, "init.value");
			if(initV != null) return;
			coco.setAttr(target, "init.value", target.value);
			if(target.tagName == "INPUT") return;
			coco.addEventListener(target, "change", function() {
				var target = this.event.target || this.event.srcElement;
				if(target == null) return;
				var v = target.value;
				var initV = coco.getAttr(target, "init.value");
				if(initV == null) coco.setAttr(target, "init.value", v);
				if(initV != null) {
					v = (v != null && v.length > 0 ? v : "");
					initV = (initV != null && initV.length > 0 ? initV : "");
					if(v != initV) {
						target.style.color = "#ff0000";
					} else {
						target.style.color = "";
					}
				}
			});
		}
	},
	onclick : function(event) {
		if(event == null) event = window.event;
		var target = event.target || event.srcElement;
		if(target == null) return;
		if(target.tagName == "INPUT" && target.type == "text") {
			coco.setAttr(target, "xu.value", target.value);
		}
		var prop = coco.getAttr(target, "xu.prop");
		//监听控件属性
		if(prop != null) {
			var fe = cocoform.register(target);
			if(fe.prop != null && fe.prop.type != null) {
				if(coco.getAttr(target, "xu.onblur") == null) {
					coco.addEventListener(target, "blur", cocoform.onblur);
					coco.setAttr(target, "xu.onblur", "true");
				}
				if(fe.prop.calendar) {
					loadCalendar(target, fe.prop.type == "date");
				}
			}
		}
		//监听复选框(全选)
		if(target.type == "checkbox") {
			var form = target.form;
			var targetChks = coco.getAttr(target, "xu.target");
			if(form != null && targetChks != null && targetChks != "") {
				var chks = form.elements[targetChks];
				if(chks != null) {
					var chked = target.checked;
					if(chks.tagName == "INPUT") {
						chks.checked = chked;
					}
					else {
						for(var i = 0; i < chks.length; i++) {
							chks[i].checked = chked;
						}
					}
				}
			}
		}
	},
	onblur : function(event) {
		if(event == null) event = window.event;
		var target = event.target || event.srcElement;
		var v = target.value;
		var initV = coco.getAttr(target, "init.value");
		//if(initV == null) coco.setAttr(target, "init.value", v);
		if(initV != null) {
			v = (v != null && v.length > 0 ? v : "");
			initV = (initV != null && initV.length > 0 ? initV : "");
			if(v != initV) {
				target.style.color = "#ff0000";
			} else {
				target.style.color = "";
			}
		}
		if(v != null && v.length > 0) {
			cocoform.verify(target);
		}
		if(target.tagName == "INPUT") {
			var val = coco.getAttr(target, "xu.value");
			if(val == null || val != target.value) cocoform.changeValue(target);
		}
	},
	onkeydown : function(event) {
		if(ie) event = window.event;
		var keycode = ie ? event.keyCode : event.which;
		//F1~F12
		if(keycode >= 112 && keycode <= 123) {
			coco.stopPropagation(event, true);
			return;
		}
		var target = ie ? event.srcElement :  event.target;
		//backspace
		if(keycode == 8) {
			if(target.tagName == "INPUT" || target.tagName == "TEXTAREA") {
				if(target.readOnly || target.disabled) {
					coco.stopPropagation(event, true);
					return;
				}
			}
			else {
				coco.stopPropagation(event, true);
				return;
			}
		}
		if(target.tagName == "INPUT" && coco.getAttr(target, "xu.validate") == "1") cocoform.validateKey(target, event);
	},
	onkeyup : function(event) {
		if(ie) event = window.event;
		var keycode = ie ? event.keyCode : event.which;
		var target = ie ? event.srcElement :  event.target;
		if(target.tagName == "INPUT") {
			//忽略backspace, tab, enter, end, home, to left, to right, delete
			if(!(keycode == 8 || keycode == 9 || keycode == 13 || keycode == 35 || keycode == 36 || keycode == 37 || keycode == 39 || keycode == 46)) {
				//若键值为小写字母，则更新输入框为大写
				var ltu = coco.getAttr(target, "xu.ltu");
				if(ltu != null && keycode >= 65 && keycode <= 90) {
					//光标位置
					var cursorIndex = coco.getCursorIndex(target);
					target.value = target.value.toUpperCase();
					//还原光标位置
					//非IE
					if(target.setSelectionRange) target.setSelectionRange(cursorIndex,cursorIndex);
					//IE
					else {
						var rng = target.createTextRange();
						rng.collapse(true); 
						rng.moveStart('character', cursorIndex); 
						rng.select();
					}				
				}
				if(target.maxLength > 0 && target.value.length >= target.maxLength) {
					var oForm = target.form;
					if(oForm != null) {
						var els = oForm.elements;
						var isFind = false, el, elIndex=0;
						while((el = els[elIndex++]) != null) {
							if(el == target) {
								isFind = true;
								continue;
							}
							if(isFind && el.type != "hidden" && el.type != "button" && !el.readOnly && el.style.display != "none" && el.style.visibility != "hidden") {
								try {el.focus();}catch(e){}
								break;
							}
						}
					}
				}
			}
		}
	},
	/**
	 * 提交表单，表单自定义属性：
	 * xu.s : 提交成功返回执行的js脚本
	 * xu.e : 提交错误返回执行的js脚本
	 * xu.r : 提交返回的结果设置标志
	 */
	submit : function(form, callback, msg, callbefore, oInput) {
		if(typeof(form) == "string") form = document.forms[form];
		if(form == null) return false;
		if(form.tagName == "INPUT") {
			if(oInput == null) oInput = form;
			form = form.form;
		}
		if(form == null || form.tagName != "FORM") return false;
		if(!cocoform.verify(form)) return false;
		if(typeof(verify) == "function") {
			var verified = verify(form);
			if(verified != null && !verified) return false;
		}
		if(msg != null && msg.length > 0) {
			if(!confirm(msg)) return false;
		}
		var checkFunction = coco.getAttr(form, "xu.c");
		if(checkFunction != null && checkFunction != "") {
			var checkFlag = true;
			try{eval("checkFlag = " + checkFunction + ";");}catch(e){}
			if(checkFlag != null && !checkFlag) return false;
		}
		var postContent = cocoform.postContent(form);
		var successFunction = coco.getAttr(form, "xu.s");
		var errorFunction = coco.getAttr(form, "xu.e");
		var result = coco.getAttr(form, "xu.r");
		var ajax = new Cocoajax();
		if(oInput != null) ajax.oInput = oInput;
		if(callback == null || typeof(callback) != "function") {
			ajax.callback = function() {
				if(result != null) coco.setAttr(form, "xu.r", ajax.result);
				if(ajax.code > 0) {
					if(successFunction != null && successFunction.length > 0) {
						try { eval(successFunction);}catch(e){}
					}
				}
				else {
					coco.alert("系统出错:\n"+ajax.msg);
					if(errorFunction != null && errorFunction.length > 0) {
						try { eval(errorFunction);}catch(e){}
					}
				}
			};
		}
		else {
			ajax.callback = callback;
		}
		if(callbefore != null && typeof(callbefore) == "function") {
			try { callbefore();}catch(e){}
		}
		ajax.post(coco.getAttr(form, "action"), postContent, "post");
		return false;
	}
		
};

/**
 * 表格元素
 * @param 表格名称
 */
function TableElement(name) {
	this.name = name;
	this.els = [];
	this.type = null; //text, password, select, radio, checkbox
	/**
	 * 放入元素
	 * @param el 表单元素
	 */
	this.put = function(el) {
		if(el != null && el.tagName != null) {
			this.els[this.els.length] = el;
			if(this.type == null) {
				var tn = el.tagName;
				if(tn == "INPUT" || tn == "SELECT" || tn == "TEXTAREA") this.type = el.type;
				else if(tn == "SPAN") this.type = "span";
			}
		}
	};
	
	/**
	 * 清空表格元素
	 */
	this.clear = function() {
		this.els = [];
	};
	
	/**
	 * 获取元素值
	 */
	this.getValue = function() {
		if(this.els == null || this.els.length == 0) return "";
		var el = this.els[0];
		if(this.type == "text" || this.type == "textarea" || this.type == "password" || this.type == "hidden")  return el.value;
	    else if(this.type=="select-one" || this.type=="select-multiple") {
	    	var options = this.els[0].options, item;
	    	var values = [];
			for(var j = 0; j < options.length; j++) {
	    		item=options[j];
	    		if(item.selected) values[values.length] = item.value;
			}
			return values.length > 1 ? values : values[0];
		} 
		else if(this.type == "checkbox") {
			if(el.checked) return coco.getAttr(el,"y") != null ? "Y" : (coco.getAttr(el,"b") != null ? "true" : el.value);
			else if(coco.getAttr(el, "y") != null) return "N";
			else if(coco.getAttr(el, "b") != null) return "false";
			return "";
		}
		else if(this.type == "radio") {
			if(el.checked) return el.value;
			return "";
		}
		else if(this.type == "file")  return "";
		else if(this.type == "span") {
			var val = coco.getAttr(el, "xu.value");
			if(val != null) return val;
			return el.innerText || el.textContent ;
		}
		return el.value;
	};
	
	/**
	 * 设置元素值
	 */
	this.setValue = function(value) {
		if(this.els == null || this.els.length == 0) return false;
		if(value == null) value = "";
		var el = this.els[0];
		if(this.type == "text" || this.type == "textarea" || this.type == "password" || this.type == "hidden")  {
			el.value = value;
			if(coco.getAttr(el, "xu.type") == "combobox") {
				var boxTxt = el.previousSibling;
				var comp = cococombobox.init(boxTxt);
				var items = comp.items;
				boxTxt.value = "";
				for(var i = 0; i < items.length; i++) {
					if(items[i][0] == value) {
						boxTxt.value = items[i][1];
						break;
					}
				}
			}
		} 
	    else if(this.type=="select-one"||this.type=="select-multiple") {
	    	var options = el.options, item;
			for(var j = 0; j < options.length; j++) {
				item = options[j]; 
				item.selected = (item.value == value);
			}
		}
		else if(this.type == "checkbox") {
			if(coco.getAttr(el,"y") != null) el.checked = value == "Y";
			else if(coco.getAttr(el,"b") != null) el.checked = value;
			else el.checked = el.value == value;
		}
		else if(this.type == "radio") {
			if(el.value == value) el.checked = true;
		}
		else if(this.type == "span") {
			var val = coco.getAttr(el, "xu.value");
			if(val != null) coco.setAttr(el, "xu.value", value);
			if(ie) el.innerText = value;
			else el.textContent = value;
			
		} 
		else return false;
		return true;
	};
}

/**
 * 表格表单类封装
 * @param id 表格Id
 * @param tempId 临时表格Id，插入空行时使用
 * @param chkName 选框名称，删除时使用
 */
function TableForm(id, tempId, chkName) {
	
	this.id = id;
	
	this.tempId = tempId;
	
	this.chkName = chkName;
	
	this.tags = ["INPUT", "SELECT", "TEXTAREA", "SPAN"];
	
	/**
	 * 获取表格每一行的表单元素
	 * @param row 表格行对象
	 */
	this.getTableElements = function(row) {
		if(row == null) return null;
		var name, els, tels = [], el, tel;
		for(var i = 0; i < this.tags.length; i++) {
			els = row.getElementsByTagName(this.tags[i]);
			for(var j = 0; j < els.length; j++) {
				el = els[j];
				if(el.tagName == "SPAN" && coco.getAttr(el, "xu.f") == null) continue; 
				name = coco.getElName(el);
				if(name == null || name == "") continue;
				tel = tels[name];
				if(tel == null) {
					tel = new TableElement(name);
					tels[name] = tel;
				}
				tel.put(el);
			}
		}
		return tels;
	};
	
	/**
	 * 插入模板空行
	 */
	this.insertEmptyRow = function(tableId) {
		if(tableId == null) tableId = this.tempId;
		var table = document.getElementById(tableId);
		var row = null;
		if(table != null) {
			row = table.rows[0];
		}
		if(row == null) {
			alert("没有提供空行模板");
			return;
		}
		row = this.insertRow(row);
		if(row == null) {
			alert("插入行失败");
			return;
		}
		row.scrollIntoView();
		return row;
	};
	
	/**
	 * 插入行
	 * @param row 参照行对象
	 * @param item 插入行的xml值节点
	 */
	this.insertRow = function(row, item) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null || row == null) return null;
		if(typeof(row) == "string") {
			row = document.getElementById(row);
			if(row == null) return null;
			if(row.tagName == "TABLE") row = row.rows[0];
			else if(row.tagName != "TR") return null;
		}
		var oRow = oTbl.insertRow(-1);
		oRow.className = row.className;
		var cells = row.cells;
		for(var i = 0; i < cells.length; i++) {
			oRow.appendChild(cells[i].cloneNode(true));
		}
		if(item == null) return oRow;
		var tels = this.getTableElements(oRow);
		if(tels == null) return oRow;
		var fields = item.childNodes;
		for(var iField = 0; iField < fields.length; iField++) {
			field = fields[iField];
			if(field.tagName == null || field.nodeType != 1 || field.tagName == "") continue;
			tel = tels[field.tagName];
			if(tel == null) continue;
			tel.setValue(field.text || feild.textContent);
		}
		return oRow;
	};
	
	
	/**
	 * 插入行
	 * @param oTbl 表格对象，
	 * @param row 参照行对象
	 * @param rowIndex 插入表格的哪行后面
	 * @param item 插入行的xml值节点
	 */
	this.insertTableRow = function(row, rowIndex, item) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null || row == null) return null;
		rowIndex = rowIndex == null || rowIndex < 0 ? -1 : rowIndex;
		var oRow = oTbl.insertRow(rowIndex);
		oRow.className = row.className;
		var cells = row.cells;
		for(var i = 0; i < cells.length; i++) {
			oRow.appendChild(cells[i].cloneNode(true));
		}
		if(item == null) return oRow;
		var tels = this.getTableElements(oRow);
		if(tels == null) return oRow;
		var fields = item.childNodes;
		for(var iField = 0; iField < fields.length; iField++) {
			field = fields[iField];
			if(field.tagName == null || field.nodeType != 1 || field.tagName == "") continue;
			tel = tels[field.tagName];
			if(tel == null) continue;
			tel.setValue(isIE ? field.text : field.textContent);
		}
		return oRow;
	};
	
	/**
	 * 生成XML
	 * @param first 开始行号（从0开始）
	 * @param length 从开始行算起生成行数（默认9999行）
	 */
	this.buildXml = function (first, length) {
		var doc = coco.createDocument(false, "root");
		var root = doc.documentElement;
		oTbl = document.getElementById(this.id);
		if(oTbl == null) return doc;
		if(first == null || first == "" || isNaN(first)) first = 0;
		else {
			first = parseInt(first);
			if(first < 0) first = 0;
		}
		if(length == null || length == "" || isNaN(length)) length = 9999;
		else {
			length = parseInt(length);
			if(length <= 0) length = 9999;
		}
		var rows = oTbl.rows;
		if(rows.length < first) return doc;
		var len = Math.min(length, rows.length);
		var row, tels, tel, val;
		var item, field;
		for(var iRow = first; iRow < len; iRow++) {
			row = rows[iRow];
			tels = this.getTableElements(row);
			if(tels == null) continue;
			item = doc.createElement("item");
			var flag = false;
			for(var name in tels) {
				tel = tels[name];
				field = doc.createElement(name);
				val = tel.getValue();
				if(ie) field.text = val;
				else field.textContent = val;
				item.appendChild(field);
				flag = true;
			}
			if(flag) root.appendChild(item);
		}
		return doc;
	};
	
	/**
	 * 生成Http提交数据
	 * @param name 数值参数名称
	 * @param first 开始行号（从0开始）
	 * @param length 从开始行算起生成行数（默认9999行）
	 */
	this.buildData = function (name, first, length) {
		if(name == null || name.length == 0) name = "items";
		oTbl = document.getElementById(this.id);
		if(oTbl == null) return "";
		if(first == null || first == "" || isNaN(first)) first = 0;
		else {
			first = parseInt(first);
			if(first < 0) first = 0;
		}
		if(length == null || length == "" || isNaN(length)) length = 9999;
		else {
			length = parseInt(length);
			if(length <= 0) length = 9999;
		}
		var rows = oTbl.rows;
		if(rows.length < first) return "";
		var len = Math.min(length, rows.length);
		var row, tels, tel, val;
		var item, field;
		var content = "";
		var index = 0;
		for(var iRow = first; iRow < len; iRow++) {
			row = rows[iRow];
			tels = this.getTableElements(row);
			if(tels == null) continue;
			item = name + "[" + (index++) + "].";
			for(var fieldName in tels) {
				tel = tels[fieldName];
				content += "&" + item + fieldName + "=" + encodeURIComponent(tel.getValue());
			}
		}
		return content.length > 0 ? content.substring(1) : "";
	};
	
	
	/**
	 * 根据指定选框生成XML
	 * @param checkName 选框名称
	 * @param first 开始行号（从0开始）
	 */
	this.buildCheckedXml = function (checkName, first) {
		var doc = coco.createDocument(false, "root");
		var root = doc.documentElement;
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return doc;
		if(first == null || first == "" || isNaN(first)) first = 0;
		else {
			first = parseInt(first);
			if(first < 0) first = 0;
		}
		var rows = oTbl.rows;
		if(rows.length < first) return doc;
		var len = Math.min(length, rows.length);
		var row, tels, tel, val;
		var item, field;
		for(var iRow = first; iRow < rows.length; iRow++) {
			row = rows[iRow];
			tels = this.getTableElements(row);
			if(tels == null) continue;
			tel = tels[checkName];
			if(tel == null || !tel.checked) continue;
			item = doc.createElement("item");
			var flag = false;
			for(var name in tels) {
				tel = tels[name];
				field = doc.createElement(name);
				val = tel.getValue();
				if(ie) field.text = val;
				else field.textContent = val;
				item.appendChild(field);
				flag = true;
			}
			if(flag) root.appendChild(item);
		}
		return doc;
	};
	
	/**
	 * 填充表格,xml 没有对应的则表格清空
	 * @param doc 填充的XML文档对象
	 * @param first 开始行号（从0开始）
	 * @param length 行数（默认9999行）
	 */
	this.fill = function(doc, first, length) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		if(first == null || first == "" || isNaN(first)) first = 0;
		else {
			first = parseInt(first);
			if(first < 0) first = 0;
		}
		if(length == null || length == "" || isNaN(length)) length = 9999;
		else {
			length = parseInt(length);
			if(length <= 0) length = 9999;
		}
		var rows = oTbl.rows;
		if(rows.length < first) return true;
		var len = Math.min(length, rows.length);
		var row, tels, tel;
		var root = doc != null ? doc.documentElement : null;
		var items = root != null ? root.selectNodes("item") : [];
		var iItem = 0, item, field;
		for(var iRow = first; iRow < len; iRow++) {
			item = iItem < items.length ? items[iItem] : null;
			iItem++; 
			row = rows[iRow];
			tels = this.getTableElements(row);
			if(tels == null) continue;
			for(var name in tels) {
				tel = tels[name];
				if(item == null) tel.setValue("");
				else {
					field = item.selectSingleNode(name);
					tel.setValue(field != null ? (ie ? field.text : field.textContent) : "");
				}
			}
		}
	};
	
	/**
	 * 插入xml对应表格的值，
	 * @param doc 填充的XML文档对象
	 * @param length 行数（默认9999行）
	 */
	this.replace = function(doc, first, length) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		if(first == null || first == "" || isNaN(first)) first = 0;
		else {
			first = parseInt(first);
			if(first < 0) first = 0;
		}
		if(length == null || length == "" || isNaN(length)) length = 9999;
		else {
			length = parseInt(length);
			if(length <= 0) length = 9999;
		}
		var rows = oTbl.rows;
		if(rows.length < first) return true;
		var len = Math.min(length, rows.length);
		var row, tels, tel;
		var root = doc != null ? doc.documentElement : null;
		var items = root != null ? root.selectNodes("item") : [];
		var iItem = 0, item, fields, field;
		for(var iRow = first; iRow < len; iRow++) {
			item = iItem < items.length ? items[iItem] : null;
			if(item == null) return true;
			iItem++; 
			row = rows[iRow];
			tels = this.getTableElements(row);
			if(tels == null) continue;
			fields = item.childNodes;
			for(var iField = 0; iField < fields.length; iField++) {
				field = fields[iField];
				if(field.tagName == null || field.nodeType != 1 || field.tagName == "") continue;
				tel = tels[field.tagName];
				if(tel == null) continue;
				tel.setValue(isIE ? field.text : field.textContent);
			}
		}
	};
	
	/**
	 * 映射表格
	 * @param doc 填充的XML文档对象
	 * @param iheader 行头数（从0开始）
	 * @param row 映射的行
	 */
	this.map = function(doc, iheader, row) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		if(iheader == null || iheader == "" || isNaN(iheader)) iheader = 0;
		else {
			iheader = parseInt(iheader);
			if(iheader < 0) iheader = 0;
		}
		var rows = oTbl.rows;
		if(rows.length < iheader) return true;
		var size = rows.length - iheader;
		var root = doc != null ? doc.documentElement : null;
		var items = root != null ? root.selectNodes("item") : [];
		
		//同步doc的行和table的行
		if(items.length > size) {
			for(var i = 0; i < items.length - size; i++ ) {
				this.insertRow(tblId, row);
			}
		}
		else if(items.length < size) {
			for(var  i = rows.length - 1; i >= items.length + iheader ; i--) {
				oTbl.deleteRow(i);
			}
		}
		rows = oTbl.rows;
		
		var row, tels, tel;
		var iItem = 0, item, field;
		for(var iItem = 0; iItem < items.length; iItem++) {
			item = items[iItem];
			row = rows[iItem + iheader];
			if(row == null) break;
			tels = this.getTableElements(row);
			if(tels == null) continue;
			for(var name in tels) {
				tel = tels[name];
				if(item == null) tel.setValue("");
				else {
					field = item.selectSingleNode(name);
					tel.setValue(field != null ? (isIE ? field.text : field.textContent) : "");
				}
			}
		}
	};
	
	/**
	 * 映射表格
	 * @param doc 填充的XML文档对象
	 * @param iheader行头数（从0开始）
	 */
	this.insert = function(doc, row) {
		var root = doc != null ? doc.documentElement : null;
		var items = root != null ? root.selectNodes("item") : [];
		var rows = [];
		var item, field, tels, tel;
		for(var i = 0; i < items.length; i++ ) {
			item = items[i];
			var newRow = this.insertRow(row);
			if(newRow == null) continue;
			tels = this.getTableElements(newRow);
			if(tels == null) continue;
			for(var name in tels) {
				tel = tels[name];
				field = item.selectSingleNode(name);
				tel.setValue(field != null ? (isIE ? field.text : field.textContent) : "");
			}
		}
	};
	
	/**
	 * 提交表格，
	 * @param url 请求的url地址
	 * @param first 开始行号（从0开始）
	 * @param length 行数（默认9999行）
	 * @param oCallback 保存完后回调的对象（对象必须有callback方法）
	 */
	this.submit = function(url, first, length) {
		var ajax = new Cocoajax();
		if(typeof(this.callback) == "function") ajax.callback = this.callback;
		ajax.post(url,this.buildXml(first,length));
		return false;
	};
	
	/**
	 * 提交表格
	 * @param url 请求的url地址
	 * @param tblId 表格Id
	 * @param checkName 选框名称
	 * @param first 开始行号（从0开始）
	 * @param oCallback 保存完后回调的对象（对象必须有callback方法）
	 */
	this.submitChecked = function(url, checkName, first) {
		var ajax = new Cocoajax();
		if(typeof(this.callback) == "function") ajax.callback = this.callback;
		ajax.post(url, this.buildCheckedXml(checkName, first));
		return false;
	};
	
	/**
	 * 移除选中行
	 * @param chkName 选框名称
	 */
	this.removeRow = function(chkName) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		if(chkName == null) chkName = this.chkName;
		var objs = document.getElementsByName(chkName);
		if(objs == null) return;
		var oChk, oTr;
		for(var i = objs.length - 1; i >= 0; i--) {
			oChk = objs[i];
			if(!coco.contains(oTbl,oChk)) continue;
			if(oChk.checked)	{
				oTr = coco.findParentByTag(oChk, "TR");
				if(oTr != null) {
					coco.removeMsg();
					oTr.parentNode.removeChild(oTr);
				}
			}
		}
	};
	
	
	/**
	 * 移除选中行
	 * @param oTbl 表格对象
	 * @param chkName 选框名称
	 */
	this.removeTableRow = function(oTbl, chkName) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		var objs = document.getElementsByName(chkName);
		if(objs == null) return;
		var oChk, oTr;
		for(var i = objs.length - 1; i >= 0; i--) {
			oChk = objs[i];
			if(!coco.contains(oTbl,oChk)) continue;
			if(oChk.checked) {
				oTr = coco.findParentByTag(oChk, "TR");
				if(oTr != null) {
					coco.removeMsg();
					oTr.parentNode.removeChild(oTr);
				}
			}
		}
	};
	/**
	 * 移除所有数据行
	 */
	this.removeAll = function() {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		var rows = oTbl.rows;
		var row;
		for(var i = rows.length - 1; i >= 0; i--) {
			row = rows[i];
			if(row.cells.length == 0 || row.cells[0].tagName == "TH") {
				continue;
			}
			coco.removeMsg();
			row.parentNode.removeChild(row);
		}
	};
	/**
	 * 格式化表格行数据为表单数据
	 * @param field 表单名称
	 * @param rowIndex 行号,从指定行号开始计数
	 * @param rowClass 指定行样式类
	 */
	this.formatForm = function(field, rowIndex, rowClass) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		var rows = oTbl.rows;
		var ro, cells, cell, input, children, iItem = 0, name;
		for(var i = (rowIndex == null || rowIndex <= 0 ? 0 : rowIndex); i < rows.length; i++ ) {
			row = rows[i];
		 	if(rowClass != null && (row.className == null || row.className.indexOf(rowClass) == -1)) continue;
		 	for(var j = 0; j < this.tags.length; j++) {
		 		var els = row.getElementsByTagName(this.tags[j]);
		 		for(var k = 0; k < els.length; k++) {
		 			input = els[k];
		 			if(input.parentNode == row.cells[0] || coco.getAttr(input, "xu.ignore") != null) continue; //忽略选择列和指定忽略的元素
					name = coco.getAttr(input, "name");
					if(name == null || input.tagName == null || name.indexOf(field + "[") != -1) continue;
					name = field + "[" + iItem +"]." +name;
					coco.setAttr(input, "name", name);
		 		}
			}
			iItem++;
		}
	};
	
	


	/**
	 * 格式化多个表格的行数据为表单数据
	 * @param field 表单名称
	 * @param rowIndex 行号,从指定行号开始计数
	 * @param index 序号,从指定序号开始计数 field[index]...
	 * @param rowClass 指定行样式类
	 */
	this.formatTblForm = function(field, rowIndex, index, rowClass) {
		var oTbl = document.getElementById(this.id);
		if(oTbl == null) return false;
		var rows = oTbl.rows;
		var ro, cells, cell, input, children, name;
		var iItem = index == null || index <= 0 ? 0 : index;
		for(var i = (rowIndex == null || rowIndex <= 0 ? 0 : rowIndex) ;i < rows.length; i++ ) {
			row = rows[i];
		 	if(rowClass != null && (row.className == null || row.className.indexOf(rowClass) == -1)) {
		 		continue;
		 	}
			for(var j = 0; j < this.tags.length; j++) {
		 		var els = row.getElementsByTagName(this.tags[j]);
		 		for(var k = 0; k < els.length; k++) {
		 			input = els[k];
		 			if(input.parentNode == row.cells[0] || coco.getAttr(input, "xu.ignore") != null) continue; //忽略选择列和指定忽略的元素
					name = coco.getAttr(input, "name");
					if(name == null || input.tagName == null || name.indexOf(field + "[") != -1) continue;
					name = field + "[" + iItem +"]." +name;
					coco.setAttr(input, "name", name);
		 		}
			}
			iItem++;
		}
	};

}

/**
 * 可编辑的下拉组合框
 */
var cococombobox = {
	comps : [],
	write : function(obj) {
		if(obj == null || obj.name == null) return;
		var items = obj.items != null ? obj.items : [];
		var width = 0;
		if(obj.width != null) width = obj.width;
		else {
			for(var i = 0; i < items.length; i++) width = Math.max(width, items[i][1].length * 20 + 21);
		}
		var value = obj.value;
		if(value == null) value = "";
		var text = obj.text;
		if(text == null) text = "";
		var selectedIndex = -1;
		if(value != "") {
			for(var i = 0; i < items.length; i++) {
				if(items[i][0] == value) {
					selectedIndex = i;
					text = items[i][1];
					break;
				}
			}
			if(text == "") {
				//强制匹配时
				if(comp.params.match) value = "";
				else text= value;			
			}
		}
		var html = '<table id="cococombobox_'+cococombobox.comps.length+'" width="'+width+'" border="0" cellspacing="0" cellpadding="0" ><colgroup><col /><col width=17 /></colgroup><tr>';
		html += '<td style="height:24px;padding:0px;">';
		html += '<input type="text" xu.ignore="1" xu.combobox.index="'+cococombobox.comps.length+'" name="'+obj.name+'_text"  value="'+text+'"';
		html += ' onclick="cococombobox.click(this,event);"';
		html += ' onblur="cococombobox.change(this,event);"';
		html += ' class="combobox" onkeyup="cococombobox.keyup(this,event);" ondblclick="if(event.ctrlKey || event.leftCtrl) {this.value = \'\';cococombobox.change(this,event);}" />';
		html += '<input type="hidden" xu.type="combobox" name="'+obj.name+'" value="'+value+'"';
		if(obj.required) html += ' xu.prop="nn:1;"';
		if(obj.cascade != null) html += ' xu.cascade="' + obj.cascade.replace(/"/g, '&quot;') + '"';
		html += ' /></td>';
		html += '<td style="height:19px;padding:0px;"><img src="'+path+'/images/form/selbtn1.gif" onmouseover="cococombobox.btnOver(this);" onmouseout="cococombobox.btnOut(this);" onclick="cococombobox.dropdown(this,event)" width=17 height=24 /></td></tr><tr><td colspan=2 style="padding-top:0px;padding-left:0px;height:1px;">';
		html += '<div style="width:'+width+';background-color:#FAFAFA;border:1px solid #226688;z-index:1;display:none;position:absolute;line-height:150%;cursor:pointer;font-size:12px;height:200px;overflow-y:scroll;padding:5px;"></div>';
		html += '<div style="width:'+width+';background-color:#FAFAFA;border:1px solid #226688;z-index:2;display:none;position:absolute;line-height:150%;cursor:pointer;font-size:12px;height:200px;overflow-y:scroll;padding:5px;" ></div><div></div></td></tr></table>';
		document.write(html);
		cococombobox.comps[cococombobox.comps.length] = {table : null, box : null, vbox : null, list:null, view : null, items : items, lastv : "", selectedIndex : selectedIndex, value : value, params: obj};
	},
	onload : function(event) {
		var comp, il = cococombobox.comps.length;
		for(var i = 0; i < il; i++) {
			comp = cococombobox.comps[i];
			if(comp.selectedIndex != -1) {
				var table = document.getElementById("cococombobox_" + i);
				if(table != null) {
					cococombobox.init(table.rows[0].cells[0].childNodes[0]);
					cococombobox.changeValue(comp);
				}
			}
		}
	},
	btnOver : function(obj) {
		obj.src = obj.src.replace(/1/,'2');
	},
	btnOut : function(obj) {
		obj.src = obj.src.replace(/2/,'1');
	},
	init : function(obj) {
		var box = obj.tagName == "INPUT" ? obj : obj.parentNode.previousSibling.children[0];
		var index = coco.getAttr(box, "xu.combobox.index");
		if(index == null || isNaN(index)) return;
		var comp = cococombobox.comps[parseInt(index)];
		if(comp == null) return;
		if(comp.box == null) {
			comp.box = box;
			comp.vbox = box.nextSibling;
			comp.list = box.parentNode.parentNode.nextSibling.children[0].children[0];
			comp.view = box.parentNode.parentNode.nextSibling.children[0].children[1];
			comp.table = coco.findParentByTag(box, "TABLE");
			var option;
			var items = comp.items != null ? comp.items : [];
			for(var i = 0; i < items.length; i++) {
				option = document.createElement("DIV");
				option.noWrap = true;
				option.style.paddingLeft = "5px";
				option.style.paddingRight = "5px";
				
				option.setAttribute("xu.combobox.i", i);
				option.setAttribute("xu.combobox.index", index);
				
				option.title = items[i][0];
				option.innerHTML = items[i][1];
				option.style.color = "#000000";
				option.style.fontSize = "20px";
				//不是组的情况
				if(items[i][2] != 1) {
					option.onmouseover = cococombobox.optionOver;
					option.onmouseout = cococombobox.optionOut;
					option.onclick = cococombobox.optionClick;
				}
				else {
					option.style.fontWeight = "bold";
				}
				comp.list.appendChild(option);
			}
		}
		return comp;
	},
	dropdown : function(obj,event) {
		var comp = cococombobox.init(obj);
		if(comp.vbox.value != "") {
			var items = comp.items;
			var selectedIndex = -1;
			for(var i = 0; i < items.length; i++) {
				if(comp.vbox.value == items[i][0]) {
					selectedIndex = i;
					break;
				}
			}
			if(comp.selectedIndex != selectedIndex) {
				if(comp.selectedIndex != -1) {
					var option = comp.list.childNodes[comp.selectedIndex];
					option.setAttribute("xu.combobox.s", "0");
					option.style.backgroundColor = "transparent";
					option.style.color = "#000000";
				}
				if(selectedIndex != -1) {
					var option = comp.list.childNodes[selectedIndex];
					option.setAttribute("xu.combobox.s", "1");
					option.style.backgroundColor = "#D73F2F";
					option.style.color = "#FFFFFF";
				}
			}
			comp.selectedIndex = selectedIndex;
		}
		comp.list.style.display = 'block';
		//comp.view.nextSibling.scrollIntoView();
		if(comp.view.style.display != 'none') comp.view.style.display = 'none';
		coco.stopPropagation(event);
	},
	view : function(obj) {
		var comp = cococombobox.init(obj);
		if(comp == null) return;
		var value = obj.value;
		var index = comp.box.getAttribute("xu.combobox.index");
		if(value != "" && comp.lastv != value) {
			comp.view.innerHTML = "";
			var items = comp.items;
			var v,t,f = false;
			for(var i = 0; i < items.length; i++) {
				v = items[i][0];
				t = items[i][1];
				if(t.indexOf(value) != 0 || items[i][2] == 1) continue;
				f = true;
				option = document.createElement("DIV");
				option.noWrap = true;
				option.style.paddingLeft = 3;
				option.style.paddingRight = 3;
				option.style.color = "#000000";
				option.style.fontSize = "20px";
				
				option.setAttribute("xu.combobox.i", i);
				option.setAttribute("xu.combobox.index", index);
				
				option.title = items[i][0];
				option.innerHTML = items[i][1];
				option.onmouseover = cococombobox.optionOver;
				option.onmouseout = cococombobox.optionOut;
				option.onclick = cococombobox.optionClick;
				comp.view.appendChild(option);
			}
			if(f) {
				comp.list.style.display = 'none';
				comp.view.style.display = 'block';
				comp.view.nextSibling.scrollIntoView();
			}
		}
		comp.lastv = value;
	},
	changeValue : function(comp) {
		var item = null;
		if(comp.selectedIndex == -1) {
			comp.box.value = "";
			comp.vbox.value = "";
		}
		else {
			item = comp.items[comp.selectedIndex];
			comp.box.value = item[1];
			comp.vbox.value = item[0];
		}
		if(comp.params.tofield != null && comp.params.tofield.length > 0) {
			//设置其他值
			var il = comp.params.tofield.length;
			var fields, jl, index, field, obj, isRow, v;
			for(var i = 0; i < il; i++) {
				fields = comp.params.tofield[i];
				jl = fields.length;
				if(jl < 2) continue;
				index = fields[0];
				field = fields[1];
				isRow = fields[2] == 1;
				obj = null;
				//控件在表格同行的情况
				if(isRow) {
					var oTr = coco.findParentByTag(comp.table, "TR");
					if(oTr != null) {
						var els = oTr.getElementsByTagName("INPUT");
						for(var k = 0; k < els.length; k++) {
							if(els[k].name == "field") {
								obj = els[k];
								break;
							}
						}
					}
				}
				else {
					var form = comp.box.form;
					if(form != null) obj = form.elements[field];
				}
				if(obj == null) continue;
				v = null;
				if(item != null) {
					v = item[index + 3];
				}
				obj.value = v != null ? v : "";
			}
		}
		if(comp.params.onchange != null) {
			try {eval(comp.params.onchange);}catch(e){}
		}
		if(comp.params.cascade != null) cocoform.changeValue(comp.vbox);
	},
	keyup : function(obj, event) {
		setTimeout(function(){cococombobox.view(obj);},100);
	},
	optionOver : function(event) {
		this.style.borderTop = "1px dotted #FFF8C8";
		this.style.borderBottom = "1px dotted #FFF8C8";
		this.style.backgroundColor = "#336699";
		this.style.color = "#FFFFFF";
	},
	optionOut : function(event) {
		if(event == null) event = window.event;
			this.style.borderTop = "0px";
			this.style.borderBottom = "0px";
		if(this.getAttribute("xu.combobox.s") == "1") {
			this.style.backgroundColor = "#D73F2F";
			this.style.color = "#FFFFFF";
		}
		else {
			this.style.backgroundColor = "transparent";
			this.style.color = "#000000";
		}
	},
	optionClick : function(event) {
		var index = this.getAttribute("xu.combobox.index");
		if(index == null || isNaN(index)) return;
		var comp = cococombobox.comps[parseInt(index)];
		comp.selectedIndex = parseInt(this.getAttribute("xu.combobox.i"));
		cococombobox.changeValue(comp);
	},
	hide : function(event) {
		var target = event != null? event.target : window.event.srcElement;
		var comp;
		for(var name in cococombobox.comps) {
			comp = cococombobox.comps[name];
			if(comp.box == null) continue;
			if(comp.list.style.display != "none") comp.list.style.display = "none";
			if(comp.view.style.display != "none") comp.view.style.display = "none";
		}
	},
	click : function(obj, event) {
		obj.setAttribute("v", obj.value);
		if(obj.clickdown) cococombobox.dropdown(this,event);
	},
	change : function(obj, event) {
		var oldVal = obj.getAttribute("v");
		obj.removeAttribute("v");
		if(obj.value == oldVal) return;
		var comp = cococombobox.init(obj);
		if(comp == null) return;
		if(!comp.params.match) {
			comp.vbox.value = comp.box.value;
			if(comp.params.onchange != null) {
				try {eval(comp.params.onchange);}catch(e){}
			}
			return;
		}
		var value = obj.value;
		if(value == "") {
			comp.selectedIndex = -1;
			cococombobox.changeValue(comp);
			return; 
		}
		if(comp.selectedIndex >= 0 && comp.list.childNodes[comp.selectedIndex].innerHTML == value) return;
		var items = comp.items;
		var v,t,f = false;
		for(var i = 0; i < items.length; i++) {
			if(items[i][1].indexOf(value) == 0) {
				comp.selectedIndex = i;
				cococombobox.changeValue(comp);
				return; 
			}
		}
		comp.selectedIndex = -1;
		cococombobox.changeValue(comp);
	}
};
/**
 * 属性说明
 * 1. xu.drag : 是否可以拖拽标志，值为block，可以拖拽，否则不可
 * 2. xu.drag.clonable : 是否要克隆对象来拖拽，有值表示克隆，值为drag表示克隆对象继续可以拖拽
 * 3. xu.drag.clone : 克隆后设置的克隆标志
 * 4. xu.drag.area : 标志可以拖入的区域，值为区域元素的ID，多个以逗号隔开
 * 5. xu.drag.container : true标志可以拖入的容器,所属元素必须在xu.drag.area区域内
 * 6. xu.move : 是否可以拖动标志，值为block，可以拖动，否则不可
 * 7. xu.move.target : 拖动目标对象
 * =====================================
 * ============  例子 ==================
<div id="drag_area1" style="width:600px;height:300px;border:1px solid #000000;float:left;">区域1
<div id="a1" xu.drag="block" xu.drag.clonable="" xu.drag.area="drag_area2" style="background-color:#FF0000">拖入区域2，克隆方式，克隆后不能再拖动</div>
<div id="a2" xu.drag="block" xu.drag.area="drag_area2" style="background-color:#00FF00;border:1px solid #FF00FF;" >拖入区域2，移动方式</div>
<div id="a3" xu.drag="block" xu.drag.clonable="" xu.drag.area="drag_area1" style="background-color:#0000FF;color:FFFFFF;" >拖入区域1，克隆方式，克隆后不能再拖动</div>
<div id="a4" xu.drag="block" xu.drag.area="drag_area1" style="background-color:#FF00FF" >拖入区域1，移动方式</div>
<span id="a4" xu.drag="block" xu.drag.clonable="drag" xu.drag.area="drag_area1,drag_area2" style="width:300px;height:50px;background-color:#FF8833;display:block" >拖入区域1、2，克隆方式，克隆后可以再拖动</span>
<span id="a4" xu.drag="block" xu.drag.area="drag_area1,drag_area2" style="width:300px;height:50px;background-color:#3388FF;display:block" >拖入区域1、2，移动方式，克隆后不能再拖动</span>
</div>
<div id="drag_area2" style="width:600px;height:300px;border:1px solid #FF0000;float:left;">区域2
<span id="a5" xu.drag.container="true" style="width:300px;height:100px;background-color:#00FFFF;display:block">容器1&nbsp;</span>
<span id="e6" xu.drag.container="true" style="width:300px;height:100px;background-color:#FFFF00;display:block">容器2&nbsp;</span>
</div>
 * ================================== 
 */
var cocodrag = {
	dragged : false,
	moved : false,
	target : null,
	containers : [],
	tempDiv : null,
	lastLeft : 0,
	lastTop : 0,
	offsetX : 0,
	offsetY : 0,
	interval : null,
	hist : {parent : null, next : null, position:{}},
	//元素属性
	attr : function(el, name) {
		try { return (el && el.getAttribute) ? el.getAttribute(name) : null; } catch (e) { return null; }
	},
	//设置元素属性
	setAttr : function(el, name, value) {
		try { if(el && el.setAttribute)  el.setAttribute(name, value);} catch (e) { }
	},
	//目标位置信息
	position : function(el) {
		if(el == null) return {left : 0, right : 0, top : 0, bottom : 0, width : 0, height : 0};
		var toPosition = {left : 0, right : 0, top : 0, bottom : 0, width : el.offsetWidth, height : el.offsetHeight};
		var xy = coco.getXY(el);
		toPosition.left = xy[0];
		toPosition.top = xy[1];
		toPosition.right = toPosition.left + toPosition.width;
		toPosition.bottom = toPosition.top + toPosition.height;
		return toPosition;
	},
	//查找拖拽或者移动对象
	findTarget : function(o) {
		while(o != null) {
			var drag = cocodrag.attr(o, "xu.drag");
			if(drag == "block") {
				var position = cocodrag.position(o);
				cocodrag.hist.parent = o.parentNode;
				cocodrag.hist.next = o.nextSibling;
				cocodrag.hist.position = position;
				var clonable = cocodrag.attr(o, "xu.drag.clonable");
				if(clonable == null || cocodrag.attr(o, "xu.drag.clone") != null) return o;
				o = o.cloneNode(true);
				o.style.left = position.left + "px";
				o.style.top = position.top + "px";
				o.style.width = position.width;
				o.style.height = position.height;
				cocodrag.setAttr(o, "xu.drag.clone", "");
				if(clonable != "drag") cocodrag.setAttr(o, "xu.drag", "");
				return o;
			}
			else {
				var move = cocodrag.attr(o, "xu.move");
				if(move == "block") {
					var moveId = cocodrag.attr(o, "xu.move.target");
					if(moveId != null) {
						var target = document.getElementById(moveId);
						if(target != null) return target;
					}
					return o;
				}
			}
			o = o.parentNode;
		};
		return null;
	},
	findContainer : function(container) {
		if(container == null) return;
		var childNodes = container.childNodes;
		if(childNodes != null && childNodes.length > 0) {
			for(var i = 0; i < childNodes.length; i++) {
				cocodrag.findContainer(childNodes[i]);
			}
		}
		if(cocodrag.attr(container, "xu.drag.container") == "true") cocodrag.containers[cocodrag.containers.length] = container;
	},
	dragStart : function(event) {
		//firefox, 左键为0
		if(ie) event = window.event;
		if(!(ie && event.button == 1 || !ie && event.button == 0)) return;
		cocodrag.dragged = false;
		cocodrag.moved = false;
		var target = event.target || event.srcElement;
		cocodrag.target = cocodrag.findTarget(target);
		if(cocodrag.target == null) return;
		var area = cocodrag.attr(cocodrag.target, "xu.drag.area");
		if(area == null) {
			//若不为拖拽,则移动
			cocodrag.moveStart(event);
			return;
		}
		cocodrag.containers = [];
		var containers = [];
		area = area.split(",");
		for(var i = 0; i < area.length; i++) {
			var o = document.getElementById(area[i]);
			if(o != null) containers[containers.length] = o;
		}
		if(containers.length == 0) return;
		for(var i = 0; i < containers.length; i++) {
			cocodrag.findContainer(containers[i]);
			cocodrag.containers[cocodrag.containers.length] = containers[i];
		}
		
		cocodrag.dragged = true;
		cocodrag.tempDiv = document.createElement("div");
		cocodrag.tempDiv.appendChild(cocodrag.target.cloneNode(true));
		cocodrag.tempDiv.style.border = "1px dashed blue";
		cocodrag.tempDiv.style.display = "block";
		cocodrag.tempDiv.style.position = "absolute";
		cocodrag.tempDiv.style.filter = "alpha(opacity=70)";
		cocodrag.tempDiv.style.cursor = "move";
		var position = cocodrag.position(target);
		if(cocodrag.target.style.zIndex == null || cocodrag.target.style.zIndex == "" || cocodrag.target.style.zIndex < 1) cocodrag.target.style.zIndex = 1000;
		document.body.appendChild(cocodrag.tempDiv);
		cocodrag.tempDiv.style.width = position.width;
		cocodrag.tempDiv.style.height = position.height;
		cocodrag.tempDiv.style.top = (position.top + 5) + "px";
		cocodrag.tempDiv.style.left = (position.left + 5) + "px";
		cocodrag.offsetX = position.left + 5 - event.clientX;
		cocodrag.offsetY = position.top + 5 - event.clientY;
	},
	moveStart : function(event) {
		cocodrag.moved = true;
		if(cocodrag.target.style.zIndex == null || cocodrag.target.style.zIndex == "" || cocodrag.target.style.zIndex < 1) cocodrag.target.style.zIndex = 1000;
		cocodrag.target.style.position = "absolute";
		var position = cocodrag.position(cocodrag.target);
		cocodrag.offsetX = position.left + 5 - event.clientX;
		cocodrag.offsetY = position.top + 5 - event.clientY;
	},
	draging : function(event){
		if(event == null) event = window.event;
		if(cocodrag.moved && cocodrag.target != null) {
			cocodrag.moving(event);
			return;	
		}
		if(!cocodrag.dragged || cocodrag.target == null) return;
		if(document.selection) document.selection.clear();
		else if(window.getSelection) window.getSelection().removeAllRanges();
		var tX = event.clientX;
		var tY = event.clientY;
		cocodrag.tempDiv.style.left = (tX + cocodrag.offsetX) + "px";
		cocodrag.tempDiv.style.top = (tY + cocodrag.offsetY) + "px";
		var area = null, position = null, overEl;
		for(var i = 0; i < cocodrag.containers.length; i++) {
			area = cocodrag.containers[i];
			position = cocodrag.position(area);
			if(tX >= position.left && tX <= position.right && tY >= position.top && tY <= position.bottom) {
				area = cocodrag.containers[i];
				var childNodes = area.childNodes;
				if(childNodes == null || childNodes.length == 0) {
					area.appendChild(cocodrag.target);
					break;
				}
				else {
					for(var j = 0; j < childNodes.length; j++) {
						overEl = childNodes[j];
						if(overEl == cocodrag.target) continue;
						position = cocodrag.position(overEl);
						if(tX >= position.left && tX <= position.right && tY >= position.top && tY <= position.bottom) {
							area.insertBefore(cocodrag.target, overEl);
							overEl = null;
							break;
						}
					}
					if(overEl != null) {
						area.appendChild(cocodrag.target);
						break;
					}
				}
				break;
			}
		}
	},
	moving : function(event) {
		if(document.selection) document.selection.clear();
		else if(window.getSelection) window.getSelection().removeAllRanges();
		cocodrag.target.style.left = (event.clientX + cocodrag.offsetX) + "px";
		cocodrag.target.style.top = (event.clientY + cocodrag.offsetY) + "px";
	},
	dragEnd : function(event){
		if(cocodrag.moved) {
			cocodrag.moved = false;
			cocodrag.target = null;
			return;
		}
		if(!cocodrag.dragged) return;
		cocodrag.dragged = false;
		if(cocodrag.tempDiv != null) cocodrag.tempDiv.style.borderWidth = "0px";
		if(cocodrag.target.style.zIndex == null || cocodrag.target.style.zIndex < 1) cocodrag.target.style.zIndex = 1000;
		cocodrag.interval = cocodrag.repos(150,15);
		try {cocoquery.addParam(cocodrag.target);}catch(e){}
		cocodrag.target = null;
	},
	repos : function(aa,ab){
		var tempPosition = cocodrag.position(cocodrag.tempDiv);
		var targetPosition = cocodrag.position(cocodrag.target);
		var tl = parseInt(tempPosition.left);
		var tt = parseInt(tempPosition.top);
		var kl = (tl-targetPosition.left) / ab;
		if(isNaN(kl)) kl = 0;
		var kt = (tt-targetPosition.top) / ab;
		if(isNaN(kt)) kt = 0;
		return setInterval(function(){ 
				if( ab < 1){
					cocodrag.tempDiv.parentNode.removeChild(cocodrag.tempDiv);
					cocodrag.tempDiv = null;
					cocodrag.target = null;
					clearInterval(cocodrag.interval);
					return;
				}
				ab--;
				tl -= kl;
				tt -= kt;
				cocodrag.tempDiv.style.left = tl+"px";
				cocodrag.tempDiv.style.top = tt+"px";
			} , aa / ab);
	},
	onkeydown : function(event) {
		if(!cocodrag.dragged) return;
		if(event == null) event = window.event;
		if(event.which == 27 || event.keyCode == 27) {
			cocodrag.tempDiv.parentNode.removeChild(cocodrag.tempDiv);
			if(cocodrag.attr(cocodrag.target, "xu.drag.clone") != null) {
				if(cocodrag.target.parentNode != null) cocodrag.target.parentNode.removeChild(cocodrag.target);
				cocodrag.dragged = false;
				cocodrag.target = null;
				return;
			}
			if(cocodrag.hist.next != null) {
				cocodrag.target.parentNode.removeChild(cocodrag.target);
				cocodrag.hist.parent.insertBefore(cocodrag.target, cocodrag.hist.next);
			}
			else cocodrag.hist.parent.appendChild(cocodrag.target);
		}
	}
};
var cocoquery = {
	config : {
		index : 0,
		size : 15,
		pageCount : 0,
		count : 0,
		tableUrl : "/coco/query/table!",
		excelUrl : "/coco/query/excel!",
		loadExcelUrl : "/coco/query/loadExcel!",
		id:null,
		query_opts : []
	},
	xmlhttp : null,
	lastMeta : null,
	loadingDiv : null,
	paramInputs : [],
	validate : function(event) {
		event = event != null ? event : window.event;
		var keyCode = ie ? event.keyCode : event.which;
		if(keyCode == 9 || keyCode == 8 || keyCode >= 37 && keyCode <= 40 || keyCode >= 16 && keyCode <= 18 || keyCode == 27 
			|| keyCode >= 48 && keyCode <= 57 || keyCode >= 96 && keyCode <= 105 || keyCode == 190 || keyCode == 110) {
			return true;
		}
		coco.stopPropagation(event);
		return false;
	},
	query : function(event, type) {
		if(event == null) event = window.event;
		if(type == "pre") {
			//上一页
			if(cocoquery.config.index <= 0) return;
			cocoquery.config.index = cocoquery.config.index - 1;
		}
		else if(type == "next") {
			//下一页
			if(cocoquery.config.index >= cocoquery.config.pageCount - 1 ) return;
			cocoquery.config.index = cocoquery.config.index + 1;
		}
		else if(type == "first") {
			//首页
			if(cocoquery.config.index <= 0) return;
			cocoquery.config.index = 0;
		}
		else if(type == "last") {
			//尾页
			if(cocoquery.config.index >= cocoquery.config.pageCount - 1 ) return;
			cocoquery.config.index = cocoquery.config.pageCount - 1;
		}
		else if(type == "jump") {
			//跳转
			var target = ie ? event.srcElement : event.target;
			var value = target.previousSibling.previousSibling.value;
			if(value == "" || isNaN(value)) {
				alert("请输入有效的跳转页码");
				return false;
			}
			value = parseInt(value);
			if(value < 1) {
				alert("页码不能小于1");
				return false;
			}
			if(value > cocoquery.config.pageCount) {
				alert("页码不能大于最大页数"+cocoquery.config.pageCount);
				return false;
			}
			cocoquery.config.index = value - 1;
		}
		else {
			cocoquery.config.index = 0;
		}
		var doc = coco.createDocument();
		var root = doc.createElement("root");
		doc.appendChild(root);
		root.setAttribute("index", cocoquery.config.index);
		root.setAttribute("size", cocoquery.config.size);
		var metas = doc.createElement("metas");
		root.appendChild(metas);
		var params = doc.createElement("params");
		root.appendChild(params);
		
		var queryMetas = document.getElementById("queryMetas");
		var nodes,node, meta, param;
		nodes = queryMetas.childNodes;
		for(var i = 0; i < nodes.length; i++) {
			node = nodes[i];
			if(coco.getAttr(node, "xu.query.meta") == "true") {
				var id = coco.getAttr(node, "xu.query.id");
				if(id == null || id == "") {
					continue;
				}
				meta = doc.createElement("meta");
				meta.setAttribute("id", id);
				metas.appendChild(meta);
			}
		}
		
		var queryParams = document.getElementById("queryParams");
		cocoquery.parseParam(doc, params, queryParams);
		
		var oQueryBtn = document.getElementById("queryButton");
		var oQueryOpt = ie? window.event.srcElement : event.target;
		
		oQueryBtn.disabled = true;
		if(oQueryOpt != null) oQueryOpt.disabled = true;
		cocoquery.showLoading();
		var ajax = new Cocoajax();
		ajax.xml = true;
		ajax.callback = function() {
			if(oQueryOpt != null) oQueryOpt.disabled = false;
			oQueryBtn.disabled = false;
			var doc = this.result;
			if(doc == null || doc.documentElement == null || doc.documentElement.tagName != "root") {
				var resultDiv = document.getElementById("queryResult");
				resultDiv.innerHTML = doc;
			}
			else cocoquery.build(doc);
			cocoquery.hideLoading();
		};
		var url = path + cocoquery.config.tableUrl + cocoquery.config.id + ".do";
		ajax.post(url, doc);
	},
	excel : function(event, type) {
		var doc = coco.createDocument();
		var root = doc.createElement("root");
		doc.appendChild(root);
		root.setAttribute("index", "0");
		root.setAttribute("size", "-1");
		var metas = doc.createElement("metas");
		root.appendChild(metas);
		var params = doc.createElement("params");
		root.appendChild(params);
		
		var queryMetas = document.getElementById("queryMetas");
		var nodes,node, meta, param;
		nodes = queryMetas.childNodes;
		for(var i = 0; i < nodes.length; i++) {
			node = nodes[i];
			if(coco.getAttr(node, "xu.query.meta") == "true") {
				var id = coco.getAttr(node, "xu.query.id");
				if(id == null || id == "") continue;
				meta = doc.createElement("meta");
				meta.setAttribute("id", id);
				metas.appendChild(meta);
			}
		}
		
		var queryParams = document.getElementById("queryParams");
		cocoquery.parseParam(doc, params, queryParams);
		
		var oQueryBtn = document.getElementById("excelButton");
		var oQueryOpt = ie? window.event.srcElement : event.target;
		
		oQueryBtn.disabled = true;
		if(oQueryOpt != null) oQueryOpt.disabled = true;
		cocoquery.showLoading();
		var ajax = new Cocoajax();
		ajax.xml = true;
		ajax.callback = function() {
			if(oQueryOpt != null) oQueryOpt.disabled = false;
			oQueryBtn.disabled = false;
			var doc = this.result;
			if(doc == null || doc.documentElement == null || doc.documentElement.tagName != "root") {
				var resultDiv = document.getElementById("queryResult");
				resultDiv.innerHTML = doc;
			}
			else {
				var id = doc.documentElement.getAttribute("id");
				if(id == null) alert("系统出错");
				else window.open(path + cocoquery.config.loadExcelUrl + id + ".do", id, "menubar=0,toolbar=0,left=0,top=0,location=0,width="+(window.screen.availWidth - 10) + ",height=" + (window.screen.availHeight - 30));
			}
			cocoquery.hideLoading();
		};
		var url = path + cocoquery.config.excelUrl  + cocoquery.config.id + ".do";
		ajax.post(url, doc);
	},
	showLoading : function() {
		if(cocoquery.loadingDiv == null) {
			cocoquery.loadingDiv = document.createElement("div");
			document.body.appendChild(cocoquery.loadingDiv);
			cocoquery.loadingDiv.innerHTML = '<img src="'+path+'/images/main/loader-bar.gif" />';
			cocoquery.loadingDiv.style.position = "absolute";
			cocoquery.loadingDiv.style.zIndex = 9999;
			cocoquery.loadingDiv.style.padding = "10px";
			cocoquery.loadingDiv.style.border = "1px solid #000000";
			cocoquery.loadingDiv.style.backgroundColor = "#FFFFFF";
		}
		cocoquery.loadingDiv.style.left = (document.body.clientWidth / 2) + "px";
		cocoquery.loadingDiv.style.top = (document.body.clientHeight / 2) + "px";
		cocoquery.loadingDiv.style.display = "block";
	},
	hideLoading : function() {
		cocoquery.loadingDiv.style.display = "none";
	},
	build : function(doc) {
		var resultDiv = document.getElementById("queryResult");
		var root = doc.documentElement;
		cocoquery.config.index = parseInt(root.getAttribute("index"));
		cocoquery.config.size = parseInt(root.getAttribute("size"));
		cocoquery.config.pageCount = parseInt(root.getAttribute("pageCount"));
		cocoquery.config.count = parseInt(root.getAttribute("count"));
		var metaIdStr = root.getAttribute("meta");
		cocoquery.lastMeta = metaIdStr;
		cocoquery.newResultPage(resultDiv, root);
	},
	newResultPage : function(resultDiv, root) {
		var columnNodes = root.selectNodes("columns/column");
		var columns = [];
		var columnNode, id, label, orderable;
		for(var i = 0; i < columnNodes.length; i++) {
			columnNode = columnNodes[i];
			id = columnNode.getAttribute("id");
			label = columnNode.getAttribute("label");
			orderable = columnNode.getAttribute("orderable");
			columns[columns.length] = {id : id, label : label, orderable : orderable};
		}
		var html = '<table id="queryResultTable" width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="query-result"><tr>';
		for(var i = 0; i < columns.length; i++) {
			html += '<th>' + columns[i].label + '</th>';
		}
		html += '</tr>';
		var items = root.selectNodes("item");
		for(var i = 0; i < items.length; i++) {
			html += '<tr>';
			var datas = items[i].childNodes;
			for(var j = 0; j < datas.length; j++) {
				var data = datas[j];
				html += '<td class="'+(i % 2 == 0 ? 'even' : 'odd')+'">' + coco.nodeText(data) + '</td>';
			}
			html += '</tr>';
		}
		html += '</table><br/>';
		html += '<div class="query-info" align="right" style="width:98%">第<span id="queryResultIndex" class="n">' + (cocoquery.config.index + 1);
		html += '</span>页/共<span id="queryResultPageCount" class="n">' + cocoquery.config.pageCount; 
		html += '</span>页&nbsp;&nbsp;共<span id="queryResultCount" class="n">' + cocoquery.config.count; 
		html += '</span>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
		var isFirst = cocoquery.config.index == 0;
		var isLast = cocoquery.config.index >= cocoquery.config.pageCount - 1;
		html += '<span id="queryResultFirst" class="'+(isFirst ? 'd' : 'e')+'" onclick="cocoquery.query(event, \'first\')">首页</span>&nbsp;&nbsp;';
		html += '|&nbsp;&nbsp;<span id="queryResultPre" class="'+(isFirst ? 'd' : 'e')+'" onclick="cocoquery.query(event,\'pre\')">上页</span>&nbsp;&nbsp;';
		html += '|&nbsp;&nbsp;<span id="queryResultNext" class="'+(isLast ? 'd' : 'e')+'" onclick="cocoquery.query(event, \'next\')">下页</span>&nbsp;&nbsp;';
		html += '|&nbsp;&nbsp;<span id="queryResultLast" class="'+(isLast ? 'd' : 'e')+'" onclick="cocoquery.query(event, \'last\')">尾页</span>&nbsp;';
		html += '&nbsp;&nbsp;&nbsp;跳转至<input type="text" class="j" onkeydown="cocoquery.validate(event);" />页<input type="button" class="query-opt-btn" onclick="cocoquery.query(event, \'jump\')" /></div>';
		resultDiv.innerHTML = html;;
	},
	fillResultPage : function(resultDiv, root) {
		var items = root.selectNodes("item");
		var oTbl = document.getElementById("queryResultTable");
		var rows = oTbl.rows;
		var row, item, cells, cell, datas;
		for(var i = 1; i < rows.length; i++) {
			item = items.length >= i ? items[i - 1] : null;
			row = rows[i];
			cells = row.cells;
			if(item == null) {
				for(var j = 0; j < cells.length; j++) {
					cell = cells[j];
					coco.setText(cell, "");
				}
				continue;
			}
			datas = item.childNodes;
			for(var j = 0; j < cells.length; j++) {
				cell = cells[j];
				if(datas.length <= j) {
					coco.setText(cell, "");
				}
				else {
					coco.setText(cell, getNodeText(datas[j]));
				}
			}
		}
		var isFirst = cocoquery.config.index == 0;
		var isLast = cocoquery.config.index >= cocoquery.config.pageCount - 1;
		coco.setText(document.getElementById("queryResultIndex"), cocoquery.config.index + 1);
		coco.setText(document.getElementById("queryResultPageCount"), cocoquery.config.pageCount);
		coco.setText(document.getElementById("queryResultCount"), cocoquery.config.count);
		document.getElementById("queryResultFirst").className = 'query-info '+(isFirst ? 'd' : 'e');
		document.getElementById("queryResultPre").className = 'query-info '+(isFirst ? 'd' : 'e');
		document.getElementById("queryResultNext").className = 'query-info '+(isLast ? 'd' : 'e');
		document.getElementById("queryResultLast").className = 'query-info '+(isLast ? 'd' : 'e');
	},
	parseParam : function(doc, param, paramArea) {
		var nodes = paramArea.childNodes;
		var row, child, optArea, opt, valueArea, oInput, value, valueEl;
		for(var i = 0; i < nodes.length; i++) {
			node = nodes[i];
			if(coco.getAttr(node, "xu.query.param") == "true") {
				var id = coco.getAttr(node, "xu.query.id");
				if(id == null || id == "") continue;
				oTbl = node.childNodes[0];
				row = oTbl.rows[0];
				optArea = row.cells[1];
				valueArea = row.cells[2];
				paramArea = row.lastChild;
				opt = optArea.childNodes[0].value;
				child = doc.createElement("param");
				child.setAttribute("id", id);
				child.setAttribute("opt", opt);
				oInput = valueArea.childNodes[0];
				if(oInput.tagName == "TEXTAREA") {
					value = oInput.value;
					var values = value.split("\n");
					for(var vi = 0; vi < values.length; vi++) {
						value = values[vi].replace(/^\s+|\s+$/g, "");
						if(value == "") continue;
						valueEl = doc.createElement("value");
						valueEl.appendChild(doc.createTextNode(value));
						child.appendChild(valueEl);
					}
				}
				else if(oInput.tagName == "SELECT") {
					if (oInput.multiple) {
						var options = oInput.options;
						var oOption;
						for (var opti = 0; opti < options.length; opti++) {
							oOption = options[opti];
							if(oOption.selected) {
								value = oOption.value;
								if(value == "") continue;
								valueEl = doc.createElement("value");
								valueEl.appendChild(doc.createTextNode(value));
								child.appendChild(valueEl);
							}
						}
					} 
					else {
						value = oInput.value;
						if(value != null) {
							valueEl = doc.createElement("value");
							valueEl.appendChild(doc.createTextNode(value));
							child.appendChild(valueEl);
						}
					}
				}
				else {
					value = oInput.value;
					valueEl = doc.createElement("value");
					valueEl.appendChild(doc.createTextNode(value));
					child.appendChild(valueEl);
				}
				param.appendChild(child);
				cocoquery.parseParam(doc, child, paramArea);
			}
		}
	},
	onmousedown : function(event) {
		event  = event != null ? event : window.event;
		var target = ie? event.srcElement : event.target;
		if(target.tagName != null && coco.getAttr(target, "xu.query.meta") == null && coco.getAttr(target, "xu.query.param") == null) {
			cocoquery.showOrHideArea(event, 'PreMetaAreaOpt', 'queryPreMetaArea', true);
			cocoquery.showOrHideArea(event, 'PreParamAreaOpt', 'queryPreParamArea', true);
		}
	},
	showOrHideBlock : function (event, optId, targetId, hidden) {
		if(ie) event = window.event;
		var obj = document.getElementById(optId);
		var target = document.getElementById(targetId);
		var flag = obj.style.display == "block";
		obj.style.display = flag ? "none" : "block";
		target.style.display = flag ? "block" : "none";
	},
	showOrHideArea : function(event, optId, targetId, hidden) {
		if(ie) event = window.event;
		var optObj = document.getElementById(optId);
		var target = document.getElementById(targetId);
		var isShow = optObj != null ? optObj.getAttribute("show") == "1" : false;
		
		if(hidden != null && hidden) {
			if(!isShow) return;
			var el = ie? event.srcElement : event.target;
			if(coco.contains(target, el)) {
				coco.stopPropagation(event);
				return;
			}
		}
		else {
			coco.stopPropagation(event);
		}
		if(target == null) return;
		target.style.display = isShow ? 'none' : 'block';
		if(optObj) {
			optObj.src = isShow ? optObj.src.replace(/toleft/, "toright") :  optObj.src.replace(/toright/, "toleft") ;
			optObj.setAttribute("show", isShow ? "0" : "1");
		}
		if(!isShow) {
			target.style.left = event.clientX + "px";
			target.style.top = event.clientY + "px";
		}
	},
	selectAllMeta : function(event) {
		var queryMetas = document.getElementById("queryMetas");
		var queryPreMetas = document.getElementById("queryPreMetas");
		var nodes = [];
		var node;
		for(var i = 0; i < queryMetas.childNodes.length; i++) {
			node = queryMetas.childNodes[i];
			nodes[nodes.length] = node;
		}
		for(var i = 0; i < nodes.length; i++) {
			queryPreMetas.appendChild(nodes[i]);
		}
	},
	addParam : function(el) {
		var type = coco.getAttr(el, "xu.type");
		if(type != "param") return;
		var style = "";
		if(el.parentNode.className != "query-params") {
			var oTdArea = el.parentNode;
			if(oTdArea.childNodes.length <= 1) {
				var td = document.createElement('TD');
				td.noWrap = true;
				td.style.width = '35px';
				td.style.color = '#FF0000';
				td.style.textAlign = "center";
				td.innerHTML = '或者';
				oTdArea.parentNode.insertBefore(td, oTdArea);
				oTdArea.className = 'query-container has-or';
			}
		}
		else style = 'style="border:1px solid #FF0000;table-layout:fixed"';
		var optHtml = cocoquery.parseOptHtml(el);
		var inputHtml = cocoquery.parseInputHtml(el);
		var tableHTML = '<table border=0 cellspacing=0 cellpadding=0 '+style+' ><tr><td noWrap=true ondblclick="cocoquery.removeParamHandler(event, this)">' + el.innerHTML + '</td><td >'+optHtml+'</td><td nowrap=true >'+inputHtml+'</td><td class="query-container not-or" xu.drag.container="true" valign="top" ></td></tr></table>';
		el.removeAttribute("class");
		el.removeAttribute("style");
		el.innerHTML = tableHTML;
	},
	removeParamHandler : function(event, obj) {
		if(event == null) event = window.event;
		var oSpan = obj;
		while(oSpan != null && oSpan.tagName != "SPAN") {
			oSpan = oSpan.parentNode;
		}
		if(oSpan == null) return;
		var oTdArea = oSpan.parentNode;
		oTdArea.removeChild(oSpan);
		if(oTdArea.tagName == "TD" && oTdArea.className != "query-params" && oTdArea.childNodes.length == 0) {
			oTdArea.parentNode.removeChild(oTdArea.previousSibling);
			oTdArea.className = 'query-container not-or';
		}
		coco.stopPropagation(event);
	},
	parseOptHtml : function(el) {
		var opts = coco.getAttr(el, "xu.query.opts");
		var optArr = opts.split(",");
		var html = '<select onchange="cocoquery.changeOpt(this)">';
		for(var i = 0; i < optArr.length; i++) {
			if(optArr[i] != "" && !isNaN(optArr[i])) {
				var index = parseInt(optArr[i]);
				html += '<option value="' + index + '">'+cocoquery.config.query_opts[index].name+'</option>';
			}
		}
		return html + '</select>';
	},
	changeOpt : function(oSelect) {
		var index = parseInt(oSelect.value);
		if(isNaN(index) || index < 0) return;
		var needValue = cocoquery.config.query_opts[index].needValue;
		var oInput = oSelect.parentNode.nextSibling.childNodes[0];
		oInput.style.visibility = needValue ? "visible" : "hidden";
		//in or not in
		if(index == 7 || index == 8) {
			if(oInput.tagName == "SELECT") {
				oInput.multiple = true;
				oInput.size = 4;
			}
		}
		else {
			if(oInput.tagName == "SELECT") {
				oInput.multiple = false;
				oInput.size = 1;
			}
		}
	},
	parseInputHtml : function(el) {
		var id = coco.getAttr(el, "xu.query.id");
		var html = cocoquery.paramInputs[id];
		if(html != null) return html;
		var options = coco.getAttr(el, "xu.query.options");
		var sqlOption = coco.getAttr(el, "xu.query.sqlOption");
		if((options == null || options == "") && (sqlOption == null || sqlOption == "")) {
			html = '<input type=text class="query-form" />';
		}
		else { 
			html = '<select style="border: 1px solid #6688DD;"><option value="">全  部</option>';
			if(options == null || options == "") {
				var xmlhttp = null;
				if (window.XMLHttpRequest) xmlhttp = new XMLHttpRequest();
				else if (window.ActiveXObject) xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				xmlhttp.open("post", $query_root_path + '/query/sqlOption.do', false);
				xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
				xmlhttp.setRequestHeader("User-Agent", "MyCustomUser");
				xmlhttp.setRequestHeader("If-Modified-Since", "0");
				xmlhttp.setRequestHeader("pragma", "no-cache");
				xmlhttp.setRequestHeader("Cache-Control", "no-cache,   must-revalidate");
				xmlhttp.setRequestHeader("expires", "-1");
				xmlhttp.send("sql="+encodeURIComponent(sqlOption));
				options = xmlhttp.responseText;
			}
			var optionArr = options.split("@&#&");
			for(var i = 0; i < optionArr.length; i++) {
				if(optionArr[i] == "") continue;
				var kv = optionArr[i].split("@#&#");
				if(kv.length != 2) continue;
				html += '<option value="' + kv[0].replace(/"/g, "\\\"") + '">'+kv[1]+'</option>';
			}
			html += '</select>';
		}
		return cocoquery.paramInputs[id] = html;
	}
};

/**
 * 菜单对象
 * 示例：
 * HTML部分：<div xu.menu="menu1">...</div>
 * JS部分：
 * 第一种方式：
 * var menuItem = new ContextmenuItem("添加", "", "selectMenu(1)");
 * contextmenu.addMenu("menu1",menuItem);
 * menuItem = new ContextmenuItem("修改", "", "selectMenu(2)");
 * contextmenu.addMenu("menu1",menuItem);
 * menuItem = new ContextmenuItem("删除", "", "selectMenu(3)");
 * contextmenu.addMenu("menu1",menuItem);
 * var menuSeperator = new ContextmenuSeperator();
 * contextmenu.addMenu("menu1", menuSeperator);
 * menuItem = new ContextmenuItem("复制", "", "selectMenu(4)");
 * contextmenu.addMenu("menu1", menuItem);
 * 第二种方式：
 * var menuItems = [["修改", "", "selectMenu(5)"],
 *              	["删除", "", "selectMenu(6)"],
 * 	             	[],
 * 	             	["查看", "", "selectMenu(7)"]];
 * contextmenu.putMenus("menu1", menuItems);
 */
var contextmenu = {
	/**
	 * 当前击中元素
	 */
	target : null,

	/**
	 * 操作元素,由页面元素指定contextmenu属性来确定
	 */
	targetElement : null,

	/**
	 * 当前击中菜单项
	 */
	menuItem : null,

	/**
	 * 菜单页面元素对象列表
	 */
	elements : null,

	/**
	 * 当前菜单页面
	 */
	element : null,

	/**
	 * 菜单列表
	 */
	menus : {},
	
	/**
	 * 添加菜单
	 * 
	 * @param key
	 *            菜单块
	 * @param item
	 *            菜单（菜单项或者分割线）
	 */
	addMenu : function(key, item) {
		if (key == null || (key = key.replace(/\s+|\s+/g, "")).length == 0 || item == null) return;
		if(item.constructor == Array) {
			if(item.length == 0) item = new ContextmenuSeperator();
			else item = new ContextmenuItem(item[0], item[1], item[2]);
		}
		if(!(item.constructor == ContextmenuItem || item.constructor == ContextmenuSeperator)) return;
		var items = contextmenu.menus[key];
		if (items == null) {
			items = [];
			contextmenu.menus[key] = items;
		}
		items[items.length] = item;
	},
	
	/**
	 * 设置菜单
	 * 
	 * @param key
	 *            菜单块
	 * @param items
	 *            菜单列表（菜单项或者分割线列表）
	 */
	putMenus : function(key, items) {
		if (key == null || (key = key.replace(/\s+|\s+/g, "")).length == 0 || items == null) return;
		if(items.constructor != Array) return;
		for(var i = 0; i < items.length; i++) {
			contextmenu.addMenu(key, items[i]);
		}
	},

	/**
	 * 生成菜单
	 */
	build : function() {
		var element;
		if (contextmenu.elements != null) {
			for ( var key in elements) {
				element = elements[key];
				if (element == null) continue;
				if (element.parentNode != null) element.parentNode.removeChild(element);
			}
			delete contextmenu.elements;
		}
		var menu;
		if (contextmenu.elements == null) contextmenu.elements = {};
		for ( var key in contextmenu.menus) {
			items = contextmenu.menus[key];
			if (items == null || items.length == 0) continue;
			element = document.createElement("DIV");
			contextmenu.elements[key] = element;
			element.className = "contextmenu";
			var html = '<div class="contextmenu-area" ><table width="100px" cellpadding=0 cellspacing=0 border=0 >';
			for ( var i = 0; i < items.length; i++) html += items[i].toHtml();
			html += '</table></div>';
			element.innerHTML = html;
			if (document.body != null) document.body.appendChild(element);
		}
	},
	selectedId : function() {
		if(contextmenu.targetElement == null) return null;
		return contextmenu.targetElement.getAttribute("xu.id");
	},
	selectedTarget : function() {
		return contextmenu.targetElement;
	},
	/**
	 * 弹出菜单
	 * 
	 * @param event
	 *            右键事件对象
	 * @return boolean
	 */
	popup : function(event) {
		if (event == null) event = window.event;
		var target = ie ? event.srcElement : event.target;
		if (target == null) return false;
		if(contextmenu.element != null) {
			if (contextmenu.targetElement != null) contextmenu.targetElement.style.backgroundColor = contextmenu.targetElement.getAttribute("xu.bgcolor.hist");
			contextmenu.element.style.display = "none";
			contextmenu.element.style.left = "0px";
			contextmenu.element.style.top = "0px";
		}
		
		var parentNode = target;
		var key = null;
		// 寻找菜单属性
		while (parentNode != null && parentNode.getAttribute) {
			if ((key = parentNode.getAttribute("xu.menu")) != null) {
				targetElement = parentNode;
				break;
			}
			parentNode = parentNode.parentNode;
		}
		if (key == null) return false;
		if (targetElement.getAttribute("xu.bgcolor.hist") == null) {
			if(targetElement.style.backgroundColor == null) targetElement.setAttribute("xu.bgcolor.hist", "none");
			else targetElement.setAttribute("xu.bgcolor.hist", targetElement.style.backgroundColor);
		}
		targetElement.style.backgroundColor = "#4FADE7";
		var x, y, w, h, ox, oy;
		x = event.clientX;
		y = event.clientY;
		var obj = contextmenu.elements[key];
		if (obj == null) return false;
		contextmenu.targetElement = targetElement;
		contextmenu.target = target;
		contextmenu.element = obj;
		ox = document.body.clientWidth;
		oy = document.body.clientHeight;
		if (x > ox || y > oy) return false;
		w = obj.offsetWidth;
		h = obj.offsetHeight;
		if((x + w) > ox) x = x - w;
		if((y + h) > oy) y = y - h;
		obj.style.left = (x + document.body.scrollLeft) + "px";
		obj.style.top = (y + document.body.scrollTop) + "px";
		obj.style.display = "block";
		return false;
	},
	/**
	 * 隐藏菜单
	 */
	hide : function(event) {
		if(event == null) event = window.event;
		if (event.button != 2) {
			if (contextmenu.targetElement != null) {
				contextmenu.targetElement.style.backgroundColor = contextmenu.targetElement.getAttribute("xu.bgcolor.hist");
			}
			var obj = contextmenu.element;
			if (obj == null) return true;
			obj.style.display = "none";
			obj.style.left = "0px";
			obj.style.top = "0px";
		}
	},
	onclick : function(event) {
		if(event == null) event = window.event;
		if(ie) contextmenu.hide(event);
		else {
			if(event.button == 2) contextmenu.popup(event);
			else contextmenu.hide(event); 
		}
	},
	menuItem_onmouseover : function(menuItem) {
		menuItem.style.backgroundColor = "#AAAAAA";
	},
	menuItem_onmouseout : function(menuItem) {
		menuItem.style.backgroundColor = "";
	}
};

/**
 * 菜单项
 * 
 * @param text
 *            菜单项名称
 * @param icon
 *            菜单项图标
 * @param cmd
 *            菜单项点击函数
 */
function ContextmenuItem(text, icon, cmd) {
	this.text = text != null && typeof(text) == "string"  && (text = text.replace(/^\s+|\s+$/g, "")).length > 0 ? text
			: "&nbsp;";
	this.icon = icon != null && typeof(icon) == "string" ? icon : "";
	this.cmd = cmd != null && typeof(cmd) == "string" ? cmd : "";
	this.toHtml = function() {
		var html = '<tr onclick="contextmenu.hide(event);' + this.cmd + ';coco.stopPropagation(event, true);" onmouseover="contextmenu.menuItem_onmouseover(this);" onmouseout="contextmenu.menuItem_onmouseout(this);" >';
		html += '<td width="20" height="20" style="padding:2px">';
		if(this.icon == "") html += '<span style="width:16px;">&nbsp;</span>';
		else {
			if(this.icon.indexOf("/") == 0)	html += '<img src="' + path + this.icon + '" width="16" height="16" align="absmiddle" />';
			else html += '<img src="' + path + '/images/icon/' + this.icon + '" width="16" height="16" align="absmiddle" />';
		}
		html += '</td><td nowrap="nowrap" class="contextmenu-item">';
		html += this.text;
		html += '</td></tr>';
		return html;
	};
}

/**
 * 菜单分割线
 */
function ContextmenuSeperator() {
	this.toHtml = function() {
		var html = '<tr><td colspan="2" class="contextmenu-seperator"></td></tr>';
		return html;
	};
}

function overTr(target) {
	if(target == null) return;
	var className = target.className;
	if(className == null) return;
	target.className = className + " overTr";
}

function outTr(target) {
	if(target == null) return;
	var className = target.className;
	if(className == null) return;
	target.className = className.replace(/\soverTr/g, "");
}

//===================全局设置==================
//===============事件注册===================
if(ie) {
//	document.attachEvent("onmousemove", cocodrag.draging);
//	document.attachEvent("onmouseup", cocodrag.dragEnd);
//	document.attachEvent("onmousedown", cocodrag.dragStart);
//	document.attachEvent("onmousedown", cocoquery.onmousedown);
//	document.attachEvent("onkeydown", cocodrag.onkeydown);
	
	document.attachEvent("onkeydown", cocoform.onkeydown);
	document.attachEvent("onkeyup", cocoform.onkeyup);
	window.attachEvent("onload", coco.onload);
	//window.attachEvent("onresize", coco.onresize);
	document.attachEvent("onclick", cocoform.onclick);
	document.attachEvent("onmouseover", cocoform.onmouseover);
	document.attachEvent("onclick", cococombobox.hide);
	document.attachEvent("onclick", contextmenu.onclick);
	document.attachEvent("oncontextmenu", contextmenu.popup);
}
else {
//	document.addEventListener("mousemove", cocodrag.draging, true);
//	document.addEventListener("mouseup", cocodrag.dragEnd, true);
//	document.addEventListener("mousedown", cocodrag.dragStart, true);
//	document.addEventListener("mousedown", cocoquery.onmousedown, true);
//	document.addEventListener("keydown", cocodrag.onkeydown, true);
	
	document.addEventListener("keydown", cocoform.onkeydown, true);
	document.addEventListener("keyup", cocoform.onkeyup, true);
	window.addEventListener("load", coco.onload, true);
	//window.addEventListener("resize", coco.onresize, true);
	document.addEventListener("click", cocoform.onclick, true);
	document.addEventListener("click", cococombobox.hide, true);
	document.addEventListener("click", contextmenu.onclick, true);
	document.oncontextmenu = function(e) { return false;};
	
	if(document.implementation && document.implementation.createDocument) {
		XMLDocument.prototype.loadXML = function(xmlString) {
			var childNodes = this.childNodes;
	        for (var i = childNodes.length - 1; i >= 0; i--) this.removeChild(childNodes[i]);
	        var dp = new DOMParser();
	        var newDOM = dp.parseFromString(xmlString, "text/xml");
		    var newElt = this.importNode(newDOM.documentElement, true);
		    this.appendChild(newElt);
		};
		if(document.implementation.hasFeature("XPath", "3.0") ) {
			XMLDocument.prototype.selectNodes = function(cXPathString, xNode)  {
			   	if(!xNode ) xNode = this;
			   	if(this.documentElement == null) return [];
			   	var oNSResolver = this.createNSResolver(this.documentElement);
			   	var aItems = this.evaluate(cXPathString, xNode, oNSResolver,  XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
			   	var aResult = [];
			   	for( var i = 0; i < aItems.snapshotLength; i++) aResult[i] =  aItems.snapshotItem(i);
			   	return aResult;
		   	};
		   	Element.prototype.selectNodes = function(cXPathString) {
		   		if(this.ownerDocument.selectNodes) return this.ownerDocument.selectNodes(cXPathString, this);
		   		else{throw "For XML Elements Only";}
		   	};
		   	XMLDocument.prototype.selectSingleNode = function(cXPathString, xNode) {
		   		if( !xNode ) { xNode = this; }
		   		var xItems = this.selectNodes(cXPathString, xNode);
		   		if( xItems.length > 0 ) return xItems[0];
		   		else return null;
		   	};
		   	Element.prototype.selectSingleNode = function(cXPathString) {
		   		if(this.ownerDocument.selectSingleNode) return this.ownerDocument.selectSingleNode(cXPathString, this);
		   		else{throw "For XML Elements Only";}
		   	};
		   	Element.prototype.__defineGetter__( "text",  function(){ return this.textContent;});
		   	XMLDocument.prototype.__defineGetter__( "xml",  function(){
		   		var oSerializer = new XMLSerializer();
		   		return oSerializer.serializeToString(this);
		   	});
		   	Element.prototype.__defineGetter__( "xml",  function(){
		   		var oSerializer = new XMLSerializer();
		   		return oSerializer.serializeToString(this);
		   	});
		}
	}
}