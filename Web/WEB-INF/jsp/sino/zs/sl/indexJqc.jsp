<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript">
<!--
	//指示书生成
	function doZc(isPrint) {
		if (!confirm("确定作成指示书吗？")) {
			return false;
		}
		var vChkNos = cocoform.postCheckValues("PageForm_page", "chkitem", "nos");
		if (vChkNos == null || vChkNos.length <= 0) {
			alert("必须选择数据行");
			return;
		}
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if (this.code > 0) {
				var zsnos= new Array(); 
			    zsnos=this.result.split('|');
				if(zsnos.length>1){
					if(zsnos[1]!=""){
						alert("作成指示书成功！作业停止的卷板号是："+zsnos[1]);
					}
					else{
						alert("作成指示书成功！");
					}
					
				}else{
					alert("作成指示书成功！");
				}
				if(isPrint){
					document.getElementById("PrintFrame").src = "/sino/dy/zss!2.do?zsno=" + zsnos[0];
				}
				
				cocopage.refresh();
			} else {
				coco.alert(this.msg, "错误提示");
			}
		};
		ajax.post("zc.do", vChkNos+ "&isPrint=" + isPrint);
	}
//-->
</script>
</head>
<body>
<ui:page title="制作指示书">
<f:page file="listJqc.jsp" action="indexJqc.do">
<table width="100%">
<tr><td>
<div class="submit">
<input type="button" class="button" onclick="cocopage.query()" value="刷新列表">
<input type="button" class="button" onclick="doZc(false)" value="指示书作成">
<input type="button" class="button"onclick="doZc(true)" value="指示书作成并打印"> 
</div>
</td></tr>
</table>
</f:page>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0"></iframe>
</ui:page>
</body>
</html>