/**
 * 页面框架
 */
var cocoframe = {
	webroot : typeof(path) == "string" ? path : "",
	//打开页面限制数
	maxs : 4,
	//不提示
	noneAlarm:true,
	// 当前页面
	pageCurr : null,
	// 桌面页面
	pageDesktop : null,
	// 桌面页面地址
	desktop : "about:blank",
	// 容器
	container : "container",
	// tab导航条
	navbars : "navbars",
	// 页面容器
	pageContainer : null,
	// 桌面页面容器
	desktopContainer : null,
	// 浏览器高度
	clientHeight : 0,
	// 浏览器宽度
	clientWidth : 0,
	// 页面高度
	pageHeight : 0,
	
	/**
	 * 生成HTML框架
	 * @param container 所在的容器
	 */
	build : function(event) {
		if(cocoframe.pageContainer != null) return;
		if(typeof(cocoframe.container) == "string") cocoframe.container = document.getElementById(cocoframe.container);
		if(typeof(cocoframe.navbars) == "string") cocoframe.navbars = document.getElementById(cocoframe.navbars);
		if(cocoframe.container == null) {
			alert("没有提供页面框架容器");
			return false;
		}
		if(cocoframe.navbars == null) {
			alert("没有提供页面标签导航条");
			return false;
		}
		//增加页面容器
		cocoframe.pageContainer = document.createElement("DIV");
		cocoframe.pageContainer.id = "coco!pages";
		with(cocoframe.pageContainer.style) {
			border = "0px";
			display = "none";
			width = "100%";
			height = "100%";
		}
		cocoframe.container.appendChild(cocoframe.pageContainer);
		
		//增加桌面容器
		cocoframe.desktopContainer = document.createElement("DIV");
		cocoframe.desktopContainer.id = "coco!desktop";
		with(cocoframe.desktopContainer.style) {
			border = "0px";
			display = "block";
			width = "100%";
			height = "100%";
		}
		cocoframe.container.appendChild(cocoframe.desktopContainer);
		//绑定桌面页面
		cocoframe.pageDesktop = document.createElement("IFRAME");
		cocoframe.pageDesktop.id = "coco!desktop!page";
		cocoframe.pageDesktop.src = cocoframe.desktop;
		cocoframe.pageDesktop.frameBorder = 0;
		with(cocoframe.pageDesktop.style) {
			border = "0px";
			width = "100%";
			height = "100%";
		}
		cocoframe.pageDesktop.src = cocoframe.desktop;
		cocoframe.desktopContainer.appendChild(cocoframe.pageDesktop);
	},
	
	/**
	 * 获取页面元素的编码
	 * @param obj 页面元素对象
	 */
	getCode : function(obj) {
		if(obj == null || obj.id == null) return null;
		var id = obj.id;
		var i = id.indexOf("_");
		return i > 0 ? id.substr(i + 1) : id;
	},
	
	/**
	 * 根据指定的代码获取页面对象
	 * @param code
	 */
	getPage : function(code) {
		return document.getElementById("coco!page_" + code);
	},
	
	/**
	 * 根据指定的代码获取Tab对象
	 * @param code
	 */
	getTab : function(code) {
		return document.getElementById("coco!tab_" + code);
	},
	
	showDesktop : function(url) {
		if(url == null) url = cocoframe.desktop;
		else if(url.indexOf("http") != 0) url =  cocoframe.webroot + url;
		cocoframe.pageDesktop.src = url;
		cocoframe.hideAll();
	},
	/**
	 * 打开页面
	 * @param code 页面资源代码
	 * @param name 页面资源名称
	 * @param url 页面资源地址
	 * @param prompt 若打开页面超过指定页面时提示
	 * @param loadfunc 页面加载函数
	 * @param reloadable 重新加载页面标志
	 */
	open : function(code, name, url, sprompt, loadfunc, reloadable) {
        if(url.indexOf("http") != 0) url =  cocoframe.webroot + url;
		if(sprompt == null) sprompt = true;
		var pages = cocoframe.pageContainer.childNodes;
		var tab = cocoframe.getTab(code);
		//若没有存在tab页，则新增一个tab页
		if(tab == null) {
			if(cocoframe.maxs <= pages.length) {
				if(cocoframe.noneAlarm || !sprompt || window.confirm("已经打开了"+cocoframe.maxs+"个页面.\n自动关闭第一个选[确定]\n手动关闭选[取消]")) { try {cocoframe.close(pages[0]); } catch(e) {} }
				else return; 
			}
			var page = document.createElement("IFRAME");
			page.id = "coco!page_" + code;
			page.src = url;
			page.frameBorder = 0;
			with(page.style) {
				width = "100%";
				height = "100%";
			}
			if(typeof(loadfunc) == "function") page.onload = loadfunc;
			cocoframe.createTab(code, name);
			cocoframe.pageContainer.appendChild(page);
			cocoframe.show(page);
		}
		else {
			var page = cocoframe.getPage(code);
			if(page != cocoframe.pageCurr) {
				cocoframe.show(page);
				if(reloadable) page.contentWindow.location.href = url;
			}
			else page.contentWindow.location.href = url;
		}
	},
	/**
	 * 创建tab页
	 */
	createTab : function(code, name) {
		if(code == null || name == null) return;
		var tab = document.createElement("SPAN");
		tab.id = "coco!tab_" + code;
		tab.align = "left";
		tab.className = "tab-bx";
		tab.setAttribute("xu.id", code);
		tab.setAttribute("xu.menu", "menu.cocomenu");
		tab.setAttribute("xu.menu.nobg", "1");
		tab.onclick = function() {
			var page = cocoframe.getPage(code);
			if(page == cocoframe.pageCurr) return true;
			cocoframe.hide(cocoframe.pageCurr);
			cocoframe.show(page);
			return true;
		};
		tab.ondblclick = cocoframe.maxmin;
		tab.onmouseover = function(event) {
			tab.className = tab.className.replace(/x/,"y");
		};
		tab.onmouseout = function(event) {
			tab.className = tab.className.replace(/y/,"x");
		};
		tab.onselectstart = function(){return false;};
		tab.innerHTML = '<table align="left" cellpadding="0" cellspacing="0"><tr><td class="tab-left"></td><td class="tab-bg">&nbsp;'+name+'&nbsp;</td><td class="tab-bg" style="text-align:center;width:20px;" onmouseover="cocoframe.cancelEvent(event);this.childNodes[0].src=this.childNodes[0].src.replace(/_0/,\'_1\');" onmouseout="cocoframe.cancelEvent(event);this.childNodes[0].src=this.childNodes[0].src.replace(/_1/,\'_0\');"><img src="'+cocoframe.webroot+'/images/main/icon_x_0.gif" width="12" heigh="12" onclick="cocoframe.close(\''+code+'\');cocoframe.cancelEvent(event);"/></td><td class="tab-right"></td></tr></table>';
		cocoframe.navbars.appendChild(tab);
	},
	
	/**
	 * 隐藏指定的页面
	 * @param page
	 * @param flag 是否显示桌面页面 
	 */
	hide : function(page, flag) {
		if(page == null) return;
		var code = null;
		if(typeof(page) == "string") page = cocoframe.getPage(code = page);
		else code = cocoframe.getCode(page);
		page.style.display = "none";
		var tab = cocoframe.getTab(code);
		if(tab != null) tab.className = "tab-ax";
		if(page == cocoframe.pageCurr) {
			cocoframe.pageCurr = null;
			if(flag) cocoframe.showDesktop();
		}
	},
	
	/**
	 * 显示指定的页面
	 * @param page
	 */
	show : function(page) {
		if(page == null) return;
		if(cocoframe.pageCurr != null) cocoframe.hide(cocoframe.pageCurr);
		var code = null;
		if(typeof(page) == "string") page = cocoframe.getPage(code = page);
		else code = cocoframe.getCode(page);
		page.style.display = "block";
		var tab = cocoframe.getTab(code);
		tab.className = "tab-bx";
		cocoframe.pageCurr = page;
		if(cocoframe.pageContainer.style.display != "block") cocoframe.pageContainer.style.display = "block";
		if(cocoframe.desktopContainer.style.display != "none") cocoframe.desktopContainer.style.display = "none";
	},
	
	refresh : function(page) {
		if(typeof(page) == "string") page = cocoframe.getPage(page);
		if(page == null) return;
		page.contentWindow.location.reload();
	},
	/**
	 * 关闭指定的页面
	 */
	close : function(page) {
		if(page == null) return;
		var code = null;
		if(typeof(page) == "string") page = cocoframe.getPage(code = page);
		else code = cocoframe.getCode(page);
		if(page == cocoframe.pageCurr) cocoframe.pageCurr = null;
		var tab = cocoframe.getTab(code);
		if(tab != null) {
			cocoframe.navbars.removeChild(tab);
			delete tab;
		}
		cocoframe.pageContainer.removeChild(page);
		delete page;
		if(cocoframe.pageContainer.childNodes.length == 0) cocoframe.maxmin(2);
		else if(cocoframe.pageCurr == null) cocoframe.show(cocoframe.pageContainer.childNodes[0]);
	},
	
	/**
	 * 隐藏所有tab页，显示桌面
	 */
	hideAll : function() {
		if(cocoframe.pageCurr != null) cocoframe.hide(cocoframe.pageCurr);
		if(cocoframe.pageContainer.style.display != "none") cocoframe.pageContainer.style.display = "none";
		if(cocoframe.desktopContainer.style.display != "block") cocoframe.desktopContainer.style.display = "block";
	},
	
	/**
	 * 关闭所有tab页，显示桌面
	 */
	closeAll : function(prompt) {
		var pages = cocoframe.pageContainer.childNodes;
		if(pages.length > 0 && prompt && !window.confirm("确定要关闭全部窗口吗？")) return false;
		var code, page;
		for(var i = pages.length - 1; i >= 0; i--) {
			page = pages[i];
			code = cocoframe.getCode(page);
			var tab = cocoframe.getTab(code);
			if(tab != null) {
				cocoframe.navbars.removeChild(tab);
				delete tab;
			}
			cocoframe.pageContainer.removeChild(page);
			delete page;
		}
		cocoframe.pageCurr = null;
		cocoframe.maxmin(2);
		if(cocoframe.pageContainer.style.display != "none") cocoframe.pageContainer.style.display = "none";
		if(cocoframe.desktopContainer.style.display != "block") cocoframe.desktopContainer.style.display = "block";
	},
	cancelEvent : function(event) {
		if(event == null) event = window.event;
		if(event != null) {
			if(event.stopPropagation) {
				event.preventDefault();
				event.stopPropagation();
			}
			else {
				event.returnValue = false;
				event.cancelBubble = true;
			}
		}
	},
	
	maxmin : function(event, none) {
		if(cocoframe.pageContainer == null) return;
		if(none == null || !none)	cocoframe.isMax = !cocoframe.isMax;
		if(cocoframe.isMax) {
			if(cocoframe.toper) cocoframe.toper.style.display = "none";
			if(cocoframe.lefter) cocoframe.lefter.style.display = "none";
			if(cocoframe.bottomer) cocoframe.bottomer.style.display = "none";
			cocoframe.container.style.height = document.documentElement.clientHeight - 2;
			cocoframe.pageContainer.style.height = parseInt(cocoframe.container.style.height) - cocoframe.navHeight;
			cocoframe.desktopContainer.style.height = parseInt(cocoframe.container.style.height) - cocoframe.navHeight;
		}
		else {
			if(cocoframe.toper) cocoframe.toper.style.display = "block";
			if(cocoframe.lefter) cocoframe.lefter.style.display = cocoframe.lefter.tagName == "DIV" ? "block" : "";
			if(cocoframe.bottomer) cocoframe.bottomer.style.display = "block";
			cocoframe.container.style.height = cocoframe.clientHeight;
			cocoframe.pageContainer.style.height = parseInt(cocoframe.container.style.height) - cocoframe.navHeight;
			cocoframe.desktopContainer.style.height = parseInt(cocoframe.container.style.height) - cocoframe.navHeight;
		}
	},
	
	doRefresh : function() {
		var page = cocoframe.pageCurr;
		if(page == null) return;
		cocoframe.close(page);
		var $page = cocoframe.pageCurr;
		if($page == null) return;
		if(typeof($page.contentWindow.cocopage.refresh) == "function") $page.contentWindow.cocopage.refresh();
	//	$page.contentWindow.location.reload();
		//$page.contentWindow.location.href = $page.src;
	},
	
	resize : function(event) {
		cocoframe.changeSize();
		cocoframe.maxmin(event, true);
	},
	
	changeSize : function() {
		cocoframe.clientHeight = document.documentElement.clientHeight - cocoframe.subHeight;
		cocoframe.pageHeight = cocoframe.clientHeight - cocoframe.navHeight;
		if(cocoframe.lefter != null) cocoframe.lefter.style.height = cocoframe.clientHeight;
		if(cocoframe.container != null) {
			cocoframe.container.style.height = cocoframe.clientHeight;
			cocoframe.clientWidth = cocoframe.container.clientWidth;
		}
		if(cocoframe.pageContainer != null) {
			cocoframe.pageContainer.style.height = parseInt(cocoframe.container.style.height) - cocoframe.navHeight;
			cocoframe.desktopContainer.style.height = parseInt(cocoframe.container.style.height) - cocoframe.navHeight;
		}
	}
};
