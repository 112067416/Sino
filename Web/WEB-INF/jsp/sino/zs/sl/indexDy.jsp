<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui" %>
<%@ taglib uri="/f" prefix="f"%>
<%@ taglib uri="/sys" prefix="sys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看指示书", "view.gif", "doShowZs()"],["打印指示书", "edit.gif", "doDyZss()"]]);
		//查看指示书详细
		function doShowZs() {
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ViewArea");
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('View',{center:true,top:50,width:"80%"});
			};
			ajax.post("view.do", "zsno="+ contextmenu.selectedId());
		}	
		// 选择打印指示书
		function doZssPrint() {		
			var form = document.forms["PageForm_page"];
			var els = form.elements["ids"];
			var content = "";
			if(els.length > 0) {
				for(var i = 0; i < els.length; i++) {
					if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
				}
			}
			else {
				if(els.checked) content += "&ids=" + encodeURIComponent(els.value);
			}
			if(content == "") {
				alert("没有选择打印的数据行");
				return;
			}
			if(!confirm("确定打印吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				document.getElementById("PrintFrame").src = "../../dy/zss!2.do?zsno="+this.result;
				cocopage.refresh();
			};
			ajax.post("updateSLZsPrint.do", content.substring(1));
		}
		// 打印指示书
		function doDyZss() {
			if(!window.confirm("确定打印吗?")) return;
			var zsno=contextmenu.selectedId();
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				document.getElementById("PrintFrame").src = "../../dy/zss!2.do?zsno="+zsno;
				cocopage.refresh();
			};
			ajax.post("updateSLZsPrint.do", "ids="+zsno);
		}
		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		  	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		//-->
	</script>
</head>
<body>
<ui:page title="指示书打印">
<f:page action="indexDy.do" file="listDy.jsp">
<table width="100%" align="center" class="form">
<colgroup><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /></colgroup>
<tr>
<th>指示书NO</th>
<td><ui:input name="zsno" maxlength="6" title="指示书No" /></td>
<th>订货NO.</th>
<td><ui:input name="dhno" maxlength="9" /></td>
<th>卷板NO.</th>
<td><ui:input name="jbno"  maxlength="11" /></td>
<th>状态</th>
<td><ui:select  name="zsfx" list="'0':'未打印','1':'已打印'" /></td>
</tr>
<tr>
<th>作成时间</th>
<td colspan="7"><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="cocopage.query();" />	
<input type="button" class="button" onclick="doZssPrint()" value="打印指示书" >
</div>
</f:page>
</ui:page>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>