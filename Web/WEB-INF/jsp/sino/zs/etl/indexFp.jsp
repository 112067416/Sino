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
		
		
		// 未完成指示书分页查询
		function doQuery() {
			//
		}
		
		
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
			var oTr = contextmenu.selectedTarget();
			var zsno = coco.getAttr(oTr, "xu.id");
			var dhno = coco.getAttr(oTr, "xu.id1");
			var content = coco.parseParamArr([["zsno", zsno],["dhno", dhno]]);
			ajax.post("view.do", content);
		}
		// 撤消指示书
		function doCx() {
			if(!window.confirm("确定撤消吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("撤消成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			};
			var oTr = contextmenu.selectedTarget();
			var zsno = coco.getAttr(oTr, "xu.id");
			var dhno = coco.getAttr(oTr, "xu.id1");
			var content = coco.parseParamArr([["zsno", zsno]]);
			ajax.post("deleteZsdh.do", content);
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
				document.getElementById("PrintFrame").src = "../../dy/zss!1.do?zsno="+zsno;
				
			};
			ajax.post("updateZsPrint.do", "ids="+zsno);
		}
		
		// 打印钢卷缺陷单
		function doDyCxd() {
			if(!window.confirm("确定打印吗?")) return;
			var zsno=contextmenu.selectedId();
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				document.getElementById("PrintFrame").src = "../../dy/jbqxb.do?type=0&nos="+zsno;
			};
			ajax.post("updateZsQxPrint.do", "ids="+zsno);
			
		}
		
		
		function doFp(oTr) {
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				cocoform.fillResult("DataForm", this.result);
				coco.showPage("Detail",{center:true,top:80,width:"40%"});
			};
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var zsno = coco.getAttr(oTr, "xu.id");
			var content = coco.parseParam("zsno", zsno);
			ajax.post("get.do", content);
		}
		//保存分派
		function saveFp(){
			var form = document.forms["PageForm_page"];
			var els = form.elements["ids"];
			var content = "";
			if(els.length > 0) {
				for(var i = 0; i < els.length; i++) {
					if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
				}
			}
			else {
				if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
			}
			if(content == "") {
				alert("请选择数据行");
				return;
			}
			var dataForm=document.forms["DataForm"];
			var jinj=dataForm.elements["jinj"].value;
			var shch=dataForm.elements["shch"].value;
			var remk=dataForm.elements["remk"].value;
			if(jinj==""){
				alert("紧急程度不能为空！");
				return;
			}
			if(shch==""){
				alert("生产线不能为空！");
				return;
			}	
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code>0){
					alert("分派成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				}
				else{
					coco.alert(this.msg);
				}
			};
			var key=content.substring(1)+"&jinj="+jinj+"&shch="+shch+"&remk="+remk;
			ajax.post("updateFP.do",key);
		}
		// 分派指示书-弹出分派层
		function showFp(){
			var form = document.forms["PageForm_page"];
			var els = form.elements["ids"];
			var content = "";
			if(els.length > 0) {
				for(var i = 0; i < els.length; i++) {
					if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
				}
			}
			else {
				if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
			}
			if(content == "") {
				alert("请选择数据行");
				return;
			}
			coco.showPage("Detail",{center:true,top:80,width:"40%"});
		}
		// 取消分派
		function doQxFp() {
			var form = document.forms["PageForm_page"];
			var els = form.elements["ids"];
			var content = "";
			if(els.length > 0) {
				for(var i = 0; i < els.length; i++) {
					if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
				}
			}
			else {
				if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
			}
			if(content == "") {
				alert("请选择数据行");
				return;
			}
			if(!window.confirm("确定要取消分派吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("取消分派成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			};
			ajax.post("deleteFP.do", content.substring(1));
		}

		// 指示书分页查询
		function doRefresh() {
			// 刷新列表
		}
		
		//分派保存成功
		function success() {
			alert("分派成功");
			coco.hidePage('Detail');
			cocopage.refresh();
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

<ui:page title="指示书分派">
  <f:page action="indexFp.do" file="listFp.jsp">
	<table width="96%" align="center" style="margin: 10px 0;" class="form">
		<colgroup>
			<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
		</colgroup>
		<tr>
			<th>指示书NO</th>
			<td><ui:input name="zsno" maxlength="6" title="指示书No" />
			<input type="hidden" id="elin" name="elin" value="${elin}" />
			</td>
			<th>订货NO.</th>
			<td><ui:input name="dhno" maxlength="9" /></td>
			<th>卷板NO.</th>
			<td><ui:input name="jbno"  maxlength="11" /></td>
			<th>用户略称</th>
			<td><ui:input name="abbr" maxlength="8" title="用户略称" /></td>		
			<th>状态</th>
			<td><sys:codeBox code="#ZSFP" name="stat" txt="2" /></td>
		</tr>
		<tr>
			<th>作成时间</th>
			<td colspan="9"><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
			
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="查询" onclick="cocopage.query();" />	
		<input type="button" class="button" onclick="showFp()" value="分派" accesskey="c">
		<input type="button" class="button" onclick="doQxFp()" value="取消分派" accesskey="c">
	</div>
 </f:page>
</ui:page>

<ui:panel id="Detail" title="指示书分派" popup="true" display="false" closable="true" >
	<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="" >
	 	<table id="FpTbl" width="96%" align="center" class="form" style="margin-top: 10px;">
			<colgroup>
				<col width="20%"/><col />
			</colgroup>
			<tr>
				<th>是否紧急</th>
				<td><sys:codeBox code="#ZSJJ" name="jinj" txt="2"/></td>
			</tr>
			<tr>
				<th>镀锡线别</th>
				<td><ui:select sql="#select CODE_,NAME_ from SINO_SCXBPZ WHERE TYPE_='1'" name="shch"/></td>
			</tr>
			<tr>
				<th>备注</th>
				<td>
				<ui:textarea name="remk" rows="4"  />
				</td>
			</tr>
			
			
		</table>
		<div class="submit">
			<input type="button" class="button" value="确定分派(F)" onclick="saveFp();" accesskey="s" />
			<input type="button" class="button" value="关闭(C)" accesskey="c" onclick="coco.hidePage('Detail');" />
		</div>
	 </form>
</ui:panel>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
<script type="text/javascript">
		<!--
		if(document.getElementById("elin").value=="N"){
			contextmenu.putMenus("menu", [
				       		              
				       		               ["查看指示书", "view.gif", "doShowZs()"],				       		              
				       		               ["打印指示书", "edit.gif", "doDyZss()"],
				       		               ["打印钢卷缺陷", "edit.gif", "doDyCxd()"]
				       		               ]);
				       		
		}else{
			contextmenu.putMenus("menu", [			       		              
				       		               ["查看指示书", "view.gif", "doShowZs()"],
				       		               ["撤消指示书", "edit.gif", "doCx()"],
				       		               ["打印指示书", "edit.gif", "doDyZss()"],
				       		               ["打印钢卷缺陷", "edit.gif", "doDyCxd()"]
				       		               ]);
				       		
		}
		
		       		
	</script>
</body>
</html>