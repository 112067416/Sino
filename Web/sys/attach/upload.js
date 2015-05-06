var swfId = null;
function upload_createXmlDom() {
	var dom = null;
	if (window.ActiveXObject) dom = new ActiveXObject("Microsoft.XMLDOM");
	else if (document.implementation && document.implementation.createDocument) dom = document.implementation.createDocument("","doc",null);
	if(dom != null) {
		var cpi = dom.createProcessingInstruction("xml", 'version="1.0" encoding="UTF-8"');
		dom.appendChild(cpi);
		dom.async = false;
		dom.resolveExternals = false;
		dom.validateOnParse = false;
		dom.preserveWhiteSpace = false;
	}
	return dom;
};


function upload_addFile(str) {
	var si = str.indexOf(",");
	var id = str.substring(0,si);
	var infos = str.substring(si+1).split("\n");
	var fileName = infos[0];
	if(fileName.length > 15) {
		var dot = fileName.lastIndexOf(".");
		if(dot < 5) {
			fileName = fileName.substring(0,12) + "...";
		}
		else {
			fileName = fileName.substring(0,10) + "..." + fileName.substring(dot);
		}
	}
	var fileSize = infos[1];
	var html = '<div id="upload_'+id+'" class="upload"><span style="font-size:12px;">'+fileName+'</span>&nbsp;&nbsp;<span style="font-size:10px;">';
	html += fileSize + "</span>&nbsp;&nbsp;";
	html += '<span id="uploaded_' + id + '">';
	html += '<span class="progressBar"><span><em id="progress_' + id + '" style="left:10px">&nbsp;</em></span></span><span id="percent_'+id+'" style="padding-left:105px;">0%</span>';
	html += '<span style="padding-top:3px;"><img src="../../images/page/cleancode.gif" width=13 height=13 align=absmiddle onclick="upload_cancelUpload('+id+')" /></span>';
	html += '</span></div>';
	UploadDiv.innerHTML += html;
}

function upload_loading(str) {
	var si = str.indexOf(",");
	var id = str.substring(0, si);
	var percent = str.substring(si+1);
	var num = parseInt(percent.substring(0,percent.length - 1));
	var oProgressEm = document.getElementById("progress_"+id);
	var oPercentSpan = document.getElementById("percent_"+id);
	if(oProgressEm != null) {
		oProgressEm.style.left = num + "px";
	}
	if(oPercentSpan != null) {
		oPercentSpan.innerText = percent;
	}
}

function upload_uploadedCallback(str) {
	var dom = upload_createXmlDom();
	dom.loadXML(str);
	var root = dom.documentElement;
	if(root != null) {
		var o = root.selectSingleNode("id");
		var id = o != null ? o.text : null; 
		o = root.selectSingleNode("code");
		var code = o != null ? o.text : null;
		o = root.selectSingleNode("msg");
		var msg = o != null ? o.text : null;
		if(id == null || code == null || isNaN(code) || code <= 0 || msg == null || isNaN(msg)) {
			if(msg != null) {
				alert(msg);
				return false;
			}
			alert("上传错误");
			return false;
		}
		if(typeof(uploadCallback) == "function") {
			try{uploadCallback(msg);}catch(e){}
		}
		var oUploaded = document.getElementById("uploaded_"+id);
		if(oUploaded != null) {
			oUploaded.title = msg;
			oUploaded.innerHTML = '<span style="padding-top:3px;"><img src="../../images/page/delete.gif" width=13 height=13 align=absmiddle onclick="parentNode.parentNode.parentNode.removeNode(true);upload_deleteFile('+msg+')" /></span>';
		}
	}
}

function upload_cancelUpload(id) {
	if(swfId != null && window.confirm("确定删除吗？")) {
		var oSwf = document.getElementById(swfId);
		if(oSwf != null) {
			try {
				alert(oSwf.cancelUpload(id));
				event.srcElement.parentNode.parentNode.parentNode.removeNode(true);
			}
			catch(e) {
				alert("系统错误");
			}
		}
	}
}

function upload_deleteFile(id) {
	if(typeof(deleteFile) == "function") {
		deleteFile(id);
	}
}