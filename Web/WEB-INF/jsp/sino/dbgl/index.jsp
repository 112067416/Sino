<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib 
	uri="/ui" prefix="ui"%><%@ taglib 
	uri="/f" prefix="f"%><%@ taglib 
	uri="/sys" prefix="sys"%><%@ taglib 
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>端板维护</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [
		                              ["查看", "view.gif", "toView()"],
		                              ["订正", "edit.gif", "doDz()"],
		                              ["删除", "del.gif", "toDelelte()"],
		                              ["撤消发货", "edit.gif", "doCxfh()"],
		                              ["打印标签", "view.gif", "doPrint1()"],
		                              ["打印服务卡", "view.gif", "doPrint2()"]
		                              ]);
		// 打印标签
		function doPrint1() {
			if(!window.confirm("确定打印吗?")) return;
			document.getElementById("PrintFrame").src = "../dy/dbbq.do?jbnos="+contextmenu.selectedId();
		}
		// 打印服务卡
		function doPrint2() {
			if(!window.confirm("确定打印吗?")) return;
			document.getElementById("PrintFrame").src = "../dy/zpdbk.do?jbnos="+contextmenu.selectedId();
		}
		// 订正
		function doDz() {
			parent.cocoframe.open("dz", "端板订正", "/sino/dbgl/toDz.do?jbno="+contextmenu.selectedId(),null,null, true);
		}	
		//批量删除
		function toDel() {
			var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(postContent == null || postContent.length == 0) {
				alert("请选择删除行");
				return;
			}
			if(!confirm("确定删除吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("del.do", postContent);
		}
		//查看详细
		function toView() {
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ViewArea");
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('View',{center:true,top:50,width:"80%"});
			};
			ajax.post("view!"+contextmenu.selectedId()+".do");
		}
		//删除单个
		function toDelelte() {
			if(!window.confirm("确定要删除该条记录吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete!"+contextmenu.selectedId()+".do", null, "POST");
		}
		//发货操作
		function doFh() {
			var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(postContent == null || postContent.length == 0) {
				alert("请选择要发货的端板");
				return;
			}
			if(!confirm("确定发货吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				alert("发货成功");
				cocopage.refresh();
			};
			ajax.post("doFh.do", postContent);
		}

		//撤消发货
		function doCxfh() {
			if(!window.confirm("确定要撤消该条记录吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("撤消成功");
				cocopage.refresh();
			};
			ajax.post("doCxfh!"+contextmenu.selectedId()+".do", null, "POST");
		}
		//-->
		</SCRIPT>
	</head>
	<body>
<ui:page title="端板维护">
		<f:page action="index.do" file="list.jsp">
			<table width="100%" class="form">
				<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /></colgroup>
				<tr>
				<th style="text-align: right;">制品号</th>
				<td><ui:input name="jbno" maxlength="11" title="制品号" /></td>
				<th style="text-align: right;">生产线别</th>
				<td><ui:select list="'T':'选别','V':'剪切二线','W':'剪切三线'" name="slin"  headerText="" headerValue=""  /></td>
				<th style="text-align: right;">捆包形式</th>
				<td><ui:select name="kbao"  headerText="" headerValue="" list="'S21':'S21','S31':'S31'"  /></td>
				<th style="text-align: right;">是否捆包</th>
				<td><ui:select name="duic"  headerText="" headerValue="" list="'F':'否','G':'是'"  /></td>
				<th style="text-align: right;">状态 </th>
				<td><ui:select name="zt"  headerText="" headerValue="" list="'0':'初始','1':'已发货'"  /></td>
		   		</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			<input type="button" class="button" value="删 除" onclick="toDel()" />
			<input type="button" class="button" value="已发货" onclick="doFh()" />
			</div>
		</f:page>
	</ui:page>
<ui:panel id="View" title="查看详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>