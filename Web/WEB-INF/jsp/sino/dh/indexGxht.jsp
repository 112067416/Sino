<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购销合同管理</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["制作购销合同", "edit.gif", "toZzGxht()"],["打印", "view.gif", "toPrint()"]]);

		
		//提交
		function doSubmit(el) {
			var form = el.form;
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("保存成功");
					cocopage.refresh();
					coco.hidePage('GxhtDetail');
			}, "是否确定保存吗?");
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

		function toPrint(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var dhno = coco.getAttr(oTr, "id0");
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

		function toZzGxht(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var dhno = coco.getAttr(oTr, "id0");
			var postContent = coco.parseParam("dhno", dhno);
			var ajax = new Cocoajax();
			ajax.dataDiv = GxhtDiv;
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("GxhtDetail",{center:true,top:50,width:"80%"});
			};
			ajax.post("loadGxht.do", postContent);
		}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<ui:page title="购销合同管理">
		<f:page action="indexGxht.do" file="listGxht.jsp">
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
					<td><ui:input name="dhno" maxlength="7" title="订货No." /></td>
					<th style="text-align: left;">用户代码</th>
					<td><ui:input name="userBegin" maxlength="4" />至<ui:input name="userEnd" maxlength="4"/></td>
					<th>用户中文名</th>
					<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">交货期</th>
					<td><ui:int name="yearBegin" maxlength="4" />年<ui:int name="monthBegin" maxlength="2" />月    至   <ui:int name="yearEnd" maxlength="4" />年<ui:int name="monthEnd" maxlength="2" />月</td>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			</div>
		</f:page>
		<ui:panel id="GxhtDetail" title="制作购销合同" popup="true" display="false" closable="true">
		<form name="GxhtForm" method="post" action="saveGxht.do" >
		<div id="GxhtDiv" class="scroll" style="width:100%;height:350px;"></div>
		</form>
		</ui:panel>
	</ui:page>
	</body>
</html>