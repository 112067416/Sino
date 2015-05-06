
var __op__ = null;

function __showMsg(msg,eo) {
	if(msg == null || msg == "") return;
	if(eo == null) eo = event ? event.srcElement : null;
	if(__op__ == null) __op__ = window.createPopup();
	var opBody = __op__.document.body;
	opBody.style.backgroundColor = "lightyellow";
	opBody.style.border = "solid black 1px";
	opBody.style.fontSize = "14px";
	opBody.style.textAlign = "center";
	opBody.style.vAlign = "bottom";
	opBody.innerHTML = "<div style='padding-top:2px;'>"+msg+"</div>";
	var leftPos = 0,topPos = 20;
	if(eo == null && document.body != null) {
		leftPos = document.body.offsetLeft + document.body.offsetWidth/2;
		topPos = document.body.offsetTop + document.body.offsetHeight/2;
		loadEventObj = document.body;
	}
	var l = msg.length;
	var w = 0;
	for(var i = 0; i < l; i++) {
		w += msg.charCodeAt(i) > 1000 ? 2 : 1;
	}
	__op__.show(leftPos, topPos, w * 8,  22, eo);
}

function __showAlt(eo, alt) {
	if(eo == null) eo = event ? event.srcElement : null;
	if(eo == null) return;
	__showMsg(alt, eo);
}

function __showTitle(eo) {
	if(eo == null) eo = event ? event.srcElement : null;
	if(eo == null) return;
	__showMsg(eo.title, eo);
}