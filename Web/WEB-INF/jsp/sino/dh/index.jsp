<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订货合同管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看", "view.gif", "toView()"],["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDelete()"]]);
		//修改操作
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			parent.cocoframe.open("editDh", "编辑订货合同信息", "/sino/dh/editDh.do?id="+key1, null, null, true);
		}
		//右键删除操作（单删除）
		function toDelete(oTr) {
			if(!confirm("确定删除吗?")) return false;
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete.do", "ids="+key1);
		}
		
		//删除按钮的操作（批量删除）
		function toDel() {
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(ids == null || ids.length == 0){
				alert("请选择要做删除操作的订货合同");
				return;
			}
		    if(!confirm("确定删除吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete.do", ids);
		}

		//删除不合格订货合同的操作（批量删除）
		function toDelCjpHt() {
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(ids == null || ids.length == 0){
				alert("请选择要做删除操作的订货合同");
				return;
			}
		    if(!confirm("确定删除吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delCjpHt.do", ids);
		}
		//查看信息操作
		function toView(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			parent.cocoframe.open("view", "查看订货合同信息", "/sino/dh/view.do?id="+key1, null, null, true);
		}

		//进入修改合同重量页面
		function toUpdateHtzl(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			var htzl = coco.getAttr(oTr, "htzl");
			var form = document.forms["HtzlForm"];
			cocoform.clear(form);
			form.elements["dhnoAndLine"].value = key1;
			form.elements["htzl"].value = htzl;
			coco.showPage("HtzlDetail",{center:true,top:150,width:"80%"});
		}

		function toHtqi() {
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(ids == null || ids.length == 0){
				alert("请选择要设置合同日的订货合同");
				return;
			}
			var form = document.forms["HtqiForm"];
			form.elements["ids"].value = ids;
			form.elements["htqi"].value = "";
			coco.showPage("HtqiDetail",{center:true,top:150,width:"80%"});
		}

		//保存合同重量
		function saveHtqi(oInput) {
			var form = oInput.form;
			var ids = form.elements["ids"].value;
			var ele1 = form.elements["htqi"];
			var htqi = ele1.value;
		//	if(htqi == null || htqi.length == 0) {
		//		alert("合同日不能为空");
		//		ele1.focus();
		//		return;
		//	}
			var postContent = coco.parseParam("htqi", htqi);
			postContent = postContent + "&" + ids;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("保存成功");
				coco.hidePage('HtqiDetail');
				cocopage.refresh();
			};
			ajax.post("updateHtqi.do", postContent);
		}
		
		
		//保存合同重量
		function saveHtzl(oInput) {
			var form = oInput.form;
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("修改成功");
					coco.hidePage('HtzlDetail');
					cocopage.refresh();
				}, "是否确定保存?", null, oInput);
		}
		
		function toKhno() {
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(ids == null || ids.length == 0){
				alert("请选择要设置客户订货单号的订货合同");
				return;
			}
			var form = document.forms["KhnoForm"];
			form.elements["ids"].value = ids;
			form.elements["khno"].value = "";
			coco.showPage("KhnoDetail",{center:true,top:150,width:"80%"});
		}

		//修改客户订货单号
		function saveKhno(oInput) {
			var form = oInput.form;
			var ids = form.elements["ids"].value;
			var ele1 = form.elements["khno"];
			var khno = ele1.value;
			//if(khno == null || khno.length == 0) {
			//	alert("客户订货单号不能为空");
			//	ele1.focus();
			//	return;
			//}
			var postContent = coco.parseParam("khno", khno);
			postContent = postContent + "&" + ids;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("保存成功");
				coco.hidePage('KhnoDetail');
				cocopage.refresh();
			};
			ajax.post("updateKhno.do", postContent);
		}
		
		function doPrint(LODOP, content) {
			//	LODOP.SET_PRINTER_INDEXA("HPrint");
				LODOP.PRINT_INIT("GXHT-<%=System.currentTimeMillis()%>");
			//	LODOP.SET_PRINTER_INDEXA("HP LaserJet Professional P1606dn");
				LODOP.SET_PRINT_PAGESIZE(1,"0","0","A4");
				LODOP.ADD_PRINT_HTM(10, 0, 1100, 1100, content);
				//打印预览
				LODOP.PREVIEW();
				//打印
				//LODOP.PRINT();
		}

		function toPrint(oInput) {
			var form = oInput.form;
			var dhno = form.elements["dhno"].value;
			if(dhno == null || dhno.length == 0) {
				alert("请输入要打印的订货合同");
				return;
			}
			var postContent = coco.parseParam("dhno", dhno);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				var obj;
				eval("obj="+this.result+";");
				var pageSizes = obj.pageSizes;
				print(pageSizes, dhno);
			};
			ajax.post(path + "/sino/dy/gxht.do", postContent);
		}

		function print(pageSizes, id) {
			var pageIndex = 0;
			var LODOP = document.getElementById("lodop");
			postContent = coco.parseParams([{name:"dhno",value:id},{name:"pageSizes",value:pageSizes},{name:"index",value:pageIndex++}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(LODOP,this.result);
				if(pageIndex < pageSizes) {
					postContent = coco.parseParams([{name:"dhno",value:id},{name:"pageSizes",value:pageSizes},{name:"index",value:pageIndex++}]);
					ajax.post(path + "/sino/dy/gxht!print.do", postContent);
				} 
			};
			ajax.post(path + "/sino/dy/gxht!print.do", postContent);
		}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="订货合同管理">
		<f:page action="index.do" file="list.jsp">
			<table class="form" width="100%">
				<colgroup>
					<col width="8%" />
					<col width="30%" />
					<col width="8%" />
					<col width="30%" />
					<col width="12%" />
					<col width="12%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th style="text-align: left;">用户代码</th>
					<td><ui:input name="userBegin" maxlength="4" />至<ui:input name="userEnd" maxlength="4"/></td>
					<th>用户中文名</th>
					<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">交货期</th>
					<td><ui:date name="jhqiBegin" prop="calendar: true;" />至<ui:date name="jhqiEnd" prop="calendar: true;" /></td>
					<th style="text-align: left;">创建日期</th>
					<td><ui:date name="creaBegin" prop="calendar: true;" />至<ui:date name="creaEnd" prop="calendar: true;" /></td>
					<th style="text-align: left;">订货合同状态</th>
					<td><ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_DH_STAT'" headerText="全部" headerValue="" value="page.stat" onchange="cocopage.query();" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
				<input type="button" class="button" value="删 除" onclick="toDel();" />
				<input type="button" class="button" value="删除不合格品订货合同" onclick="toDelCjpHt();" />
				<input type="button" class="button" value="设置合同日" onclick="toHtqi();" />
				<input type="button" class="button" value="设置客户订货单号" onclick="toKhno();" />
			</div>
		</f:page>
		<ui:panel id="HtzlDetail" title="修改合同重量" popup="true" display="false" closable="true">
		<form name="HtzlForm" method="post" action="updateHtzl.do" >
		<input type="hidden" name="dhnoAndLine"/>
		<table class="form" width="100%">
		<colgroup><col width="10%" /><col width="90%" /></colgroup>
		<tr>
		<th style="text-align: left;">合同重量</th> 
		<td><ui:number name="htzl" title="订货DB的合同重量" scale="7" precision="3" positive="true" maxlength="8" required="true" /></td>
		</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="保    存" onclick="saveHtzl(this);"/>
		<input type="button" class="button" value="关    闭" onclick="coco.hidePage('HtzlDetail');" />
		</div>
		</form>
		</ui:panel>
		<ui:panel id="HtqiDetail" title="设置合同日" popup="true" display="false" closable="true">
		<form name="HtqiForm" method="post" action="updateHtqi.do" >
		<input type="hidden" name="ids"/>
		<table class="form" width="100%">
		<colgroup><col width="10%" /><col width="90%" /></colgroup>
		<tr>
		<th style="text-align: left;">合同日</th> 
		<td><ui:input name="htqi" title="合同日" maxlength="6" required="true" /></td>
		</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="保    存" onclick="saveHtqi(this);"/>
		<input type="button" class="button" value="关    闭" onclick="coco.hidePage('HtqiDetail');" />
		</div>
		</form>
		</ui:panel>
		<ui:panel id="KhnoDetail" title="设置客户订货单号" popup="true" display="false" closable="true">
		<form name="KhnoForm" method="post" action="updateKhno.do" >
		<input type="hidden" name="ids"/>
		<table class="form" width="100%">
		<colgroup><col width="10%" /><col width="90%" /></colgroup>
		<tr>
		<th style="text-align: left;">客户订货单号</th> 
		<td><ui:input name="khno" title="客户订货单号" maxlength="10" required="true" /></td>
		</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="保    存" onclick="saveKhno(this);"/>
		<input type="button" class="button" value="关    闭" onclick="coco.hidePage('KhnoDetail');" />
		</div>
		</form>
		</ui:panel>
	</ui:page>
	</body>
</html>