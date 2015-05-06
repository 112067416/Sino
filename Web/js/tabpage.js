
var pageTags = [];
var lastActivePage = null;
function PageTag(tid,class1,class2) {
	this.tid = tid;
	this.obj = null;
	this.class1 = class1 != null ? class1 : "tab02-bg01";
	this.class2 = class2 != null ? class2 : "tab02-bg02";
	this.tab = null;
	this.getTab = function() {
		if(null == this.tab) {
			this.tab = document.getElementById(this.tid);
			if(null == this.tab) {
				this.tab = {"style" : {"display" : "none"}};
			}
		}
		return this.tab;
	};
}

function chgPage(obj,fid) {
	if(fid>=pageTags.length) {
		return;
	}
	if(null == lastActivePage) {
		lastActivePage = pageTags[0];
		lastActivePage.obj = document.getElementById("activeTab");
	}
	lastActivePage.obj.className = lastActivePage.class1;
	lastActivePage.getTab().style.display = "none";
	lastActivePage = pageTags[fid];
	if(null == lastActivePage.obj) {
		lastActivePage.obj = obj;
	}
	lastActivePage.obj.className = lastActivePage.class2;
	lastActivePage.getTab().style.display = "block";
}