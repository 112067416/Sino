<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			var oTr = contextmenu.selectedTarget();
			var zsno = coco.getAttr(oTr, "xu.id");
			var dhno = coco.getAttr(oTr, "xu.id1");
			var content = coco.parseParamArr([["zsno", zsno],["dhno", dhno]]);
			ajax.post("view.do", content);
		}	
		// 选择打印指示书
		function doZssPrint() {		
			var form = document.forms["PageForm_page"];
			var idStr = form.elements["ids"].value;
			if(idStr == null || idStr.length == 0) {
				alert("没有选择打印的数据行");
				return;
			}
			var content = coco.parseParam("ids", idStr);
			if(!confirm("确定打印吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				form.elements["ids"].value = "";
				document.getElementById("PrintFrame").src = path + "/sino/dy/zss!1.do?zsno="+this.result;
				cocopage.refresh();
			};
			ajax.post("updateZsPrint.do", content);
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
				document.getElementById("PrintFrame").src = path + "/sino/dy/zss!1.do?zsno="+zsno;
				cocopage.refresh();
			};
			ajax.post("updateZsPrint.do", "ids="+zsno);
		}
		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		 	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		//复选框选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
			//选中订货同号
			var v = obj.value;
			var oForm = document.forms["PageForm_page"];
			var ids = oForm.elements["ids"].value;
			var rgExp;
			if(obj.checked) {
				if(ids == null || ids.length == 0) {
					oForm.elements["ids"].value = v;
					return;
				}
				oForm.elements["ids"].value = ids.replace(/(,)+$/g,'') + ',' + v;
			} else {
				rgExp = new RegExp(v+',|('+v+'$)' , "g");
				oForm.elements["ids"].value = ids.replace(rgExp, '');
			}
		}
		//全选或全不选
		function checkAll(obj) {
			var oForm = document.forms["PageForm_page"];
			var chks = oForm.elements["id"];
			if(chks == null )  return;
			if(chks.tagName != null) {
				if(obj.checked ^ chks.checked) {
					chks.checked = obj.checked;
					doCheck(chks);
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(obj.checked ^ el.checked) {
						el.checked = obj.checked;
						doCheck(el);
					}
					continue;
				}
			}
		}
		//自动设置复选框的checked值
		function setChecked() {
			var oForm = document.forms["PageForm_page"];
			var ids = oForm.elements["ids"].value;
			if(ids == null || ids.length == 0) {
				return;
			}
			var chks = oForm.elements["id"];
			if(chks == null) return;
			if(chks.tagName != null) {
				if(ids.indexOf(chks.value) >= 0) {
					chks.checked = true;
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(ids.indexOf(el.value) >= 0) {
						el.checked = true;
					}
				}
			}
		}

		function doQuery(oForm) {
			var el = oForm.elements["ids"];
			el.value = "";
			cocopage.query('page');
		}
		//-->
	</script>
</head>
<body>
<ui:page title="指示书打印">
  <f:page action="indexDy.do" file="listDy.jsp">
  <input type="hidden" name="ids" />
	<table width="96%" align="center" style="margin: 10px 0;" class="form">
		<colgroup><col /><col /><col /><col /><col /><col /><col />
		<col /><col /><col /></colgroup>
		<tr>
		<th style="text-align: left;">指示书NO</th>
		<td><ui:input name="zsno" maxlength="6" title="指示书No" /></td>
		<th style="text-align: left;">订货NO.</th>
		<td><ui:input name="dhno" maxlength="9" /></td>
		<th style="text-align: left;">卷板NO.</th>
		<td><ui:input name="jbno"  maxlength="11" /></td>
		<th style="text-align: left;">用户略称</th>
		<td><ui:input name="abbr" maxlength="8" title="用户略称" /></td>
		<th style="text-align: left;">状态</th>
		<td><ui:select  name="zsfx" list="'0':'未打印','1':'已打印'" /></td>
		</tr>
		<tr>
		<th style="text-align: left;">作成时间</th>
		<td colspan="9"><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="查 询" onclick="doQuery(this.form);" />	
		<input type="button" class="button" onclick="doZssPrint()" value="打印指示书" accesskey="c">
	</div>
 </f:page>
</ui:page>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>