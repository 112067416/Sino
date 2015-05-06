<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
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
			
			var zsno =contextmenu.selectedId();
			ajax.post("deleteZsdh.do", "zsno="+contextmenu.selectedId());
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
			ajax.post("view.do", "zsno="+contextmenu.selectedId());
		}
		//双点击查看
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var vKey1 = coco.getAttr(oTr, "xu.id");
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ViewArea");
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('View',{center:true,top:50,width:"80%"});
			};
			ajax.post("view.do", "zsno="+vKey1);
		}
		//刷新指示书列表 
		function doRefresh() {
			window.location.reload();
		}

		//上移
		function doUp() {
			// 将顺序保持到数据库
			var tbl = document.getElementById("DataTbl");
			var rows = tbl.rows;
			var cell, chk;
			for(var i = 2; i < rows.length; i++) {
				chk = rows[i].cells[0].childNodes[0];
				if(!chk.checked) continue;
				tbl.moveRow(i, i - 1); 
			} 
		}

		//下移
		function doDown() {
			// 将顺序保持到数据库
			var tbl = document.getElementById("DataTbl");
			var rows = tbl.rows;
			var cell, chk;
			for(var i = rows.length - 2; i > 0; i--) {
				chk = rows[i].cells[0].childNodes[0];
				if(!chk.checked) continue;
				tbl.moveRow(i, i + 1); 
			} 
		}
		//保存排序
		function saveDyZzs(el) {
			if(!confirm("确定保存吗?")) return false;
			var content;
			var oForm = el.form;
			var sorts = oForm.elements["sort"];
			var zsnos = oForm.elements["$zsno"];
			var el1, el2;
			if(sorts == null) {
				alert("指示书数据为空");
				return;
			}
			if(sorts.tagName != null) {
				content = "sorts=" + zsnos.value + "-" + sorts.value;
			} else {
				for(var i = 0; i < sorts.length; i++) {
					el1 = sorts[i];
					el2 = zsnos[i];
					if(i == 0) {
						content = "sorts=" + el2.value + "-" + el1.value;
						continue;
					}
					content = content + "&sorts=" + el2.value + "-" + el1.value;
				}
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code > 0){
					alert("保存成功");
					cocopage.query();
					return;
				}
				coco.alert(this.msg);
			};
			ajax.post("saveDyZzs.do", content);
		}
		
		// 打印指示书
		function doDyZzs() {		
			if(!confirm("确定打印吗?")) return false;
			var vKey1 = contextmenu.selectedId();
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				document.getElementById("PrintFrame").src = "../../dy/zss!1.do?zsno="+vKey1;
				cocopage.refresh();
			};
			ajax.post("updateZsPrint.do", "ids="+vKey1);
			//document.getElementById("PrintFrame").src = "../../dy/zss!1.do?zsno="+content.substring(1);
		}
		
		// 打印钢卷缺陷单
		function doDyQxd() {			
			if(!confirm("确定打印吗?")) return false;
			var vKey1 = contextmenu.selectedId();
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				document.getElementById("PrintFrame").src = "../../dy/jbqxb.do?type=0&nos="+vKey1;
				cocopage.refresh();
			};
			ajax.post("updateZsQxPrint.do", "ids="+vKey1);
			//document.getElementById("PrintFrame").src = "../../dy/jbqxb.do?type=0&nos="+content.substring(1);
		}
		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		  	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+encodeURIComponent(ylno), "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		//
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
			var dataForm=document.forms["DataForm"];
			var zsno=dataForm.elements["zsno"].value;
			var remk=dataForm.elements["remk"].value;
			if(zsno==""){
				alert("指示书不能为空！");
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code>0){
					alert("保存成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				}
				else{
					coco.alert(this.msg);
				}
			};
			var key="zsno="+zsno+"&remk="+remk;
			ajax.post("updateREMK.do",key);
		}
		
		//-->
		</script>
	</head>
<body>
<ui:page title="指示书待生产">
   <f:page action="indexDsc!${type}.do" file="listDsc.jsp">
  <table width="96%" align="center" style="margin: 10px 0;" class="form">
		<colgroup><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /></colgroup>
		<tr>
			<th style="text-align: left;">指示书NO </th>
			<td><ui:input name="zsno" maxlength="6" title="指示书No" /><input type="hidden" id="elin" name="elin" value="${elin}" /></td>
			<th style="text-align: left;">订货NO.</th>
			<td><ui:input name="dhno" maxlength="9" /></td>
			<th style="text-align: left;">卷板NO.</th>
			<td><ui:input name="jbno"  maxlength="11" /></td>
			<th style="text-align: left;">用户略称</th>
			<td><ui:input name="abbr" maxlength="8" title="用户略称" /></td>
		</tr>
		<tr>
			<th style="text-align: left;">作成时间</th>
			<td colspan="7"><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="查询" onclick="cocopage.query();" />			
		<input type="button" id="pais" class="button" value="保存排序" onclick="saveDyZzs(this);" />
	</div>
  </f:page>
</ui:page>
<ui:panel id="Detail" title="指示书备注" popup="true" display="false" closable="true" >
	<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="" >
	 	<table id="FpTbl" width="96%" align="center" class="form" style="margin-top: 10px;">
			
			<tr>
				<th style="text-align: left;">指示书号</th>
				<td><ui:input name="zsno" maxlength="6" title="指示书No" required="true"
				readonly="true"/></td>
			</tr>
			<tr>
				<th style="text-align: left; vertical-align: middle;">备注</th>
				<td><ui:textarea name="remk" rows="4" cols="30" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="确定(F)" onclick="saveFp();" accesskey="s" />
			<input type="button" class="button" value="关闭(C)" accesskey="c" onclick="coco.hidePage('Detail');" />
		</div>
	 </form>
</ui:panel>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
<script type="text/javascript">
		var type = '${type}';
		if(type == "2" ){
			contextmenu.putMenus("menu", [
			                              ["查看指示书", "view.gif", "doShowZs()"],
			                              ["打印指示书", "edit.gif", "doDyZzs()"],
			                              ["打印缺陷单", "edit.gif", "doDyQxd()"]
			                             ]);
			 document.getElementById("pais").style.display = "none";
		}
		if(type == "1" ){
			contextmenu.putMenus("menu", [
			                              ["查看指示书", "view.gif", "doShowZs()"],
			                              ["撤消指示书", "edit.gif", "doCx()"],
			                              ["打印指示书", "edit.gif", "doDyZzs()"],
			                              ["打印缺陷单", "edit.gif", "doDyQxd()"],
			                              ["修改备注", "edit.gif", "doFp()"]
			                             ]);
		}
		</script>
	</body>
</html>