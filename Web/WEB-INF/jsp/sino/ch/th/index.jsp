<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>退货记录管理</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["撤消退货", "del.gif", "toDel()"]]);
			//修改退货信息
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "key1");
				var postContent = coco.parseParams([{name:"id",value:key1}]);
				var thDiv = document.getElementById("ThDiv");
				var ajax = new Cocoajax();
				ajax.dataDiv = thDiv; 
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					coco.showPage("ThDetail", {center : false, left: 20, top : 30, width : "80%" });
				};
				ajax.post("getForUpdate.do", postContent);
			}
			//撤销退货
			function toDel() {
				if(!window.confirm("是否确定撤消退货?")) return;
				var oTr = contextmenu.selectedTarget();
				var id = coco.getAttr(oTr, "key1");
				var postContent = coco.parseParams([{name:"ids",value:id}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("撤销退货成功");
					cocopage.refresh();
				};
				ajax.post("doCxth.do", postContent);
			}
			//批量撤销退货
			function doCxth() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0) {
					alert("请选择要做撤销退货操作的制品");
					return;
				}
				if(!confirm("确定撤销退货吗?")) return false;
				var ajax = new Cocoajax();
				ajax.oInput=document.getElementById("cxth");
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("撤销退货成功");
					cocopage.refresh();
				};
				ajax.post("doCxth.do", ids);
			}
			//设置发票号
			function toFp() {
				var form = document.forms["PageForm_page"];
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids == "") {
					alert("请选择要设置的记录");
					return;
				}
				coco.showPage("FpDetail",{center:true,top:150,width:"80%"});
				var oForm = document.forms["FpForm"];
				oForm.elements["fpno"].focus();
			}
			//保存发票号
			function saveFp(){
				var oForm = document.forms["FpForm"];
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0) {
					alert("请选择要设置发票号的记录");
					return;
				}
				var fpno = oForm.elements["fpno"].value.replace(/^\s+|\s+$/g, '');
				if(fpno == null || fpno.length == 0) {
					alert("请输入发票号");
					return;
				}
				var postContent = coco.parseParams([{name:"fpno",value:fpno}]);
				postContent = postContent + '&' + ids;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("设置成功");
					coco.hidePage('FpDetail');
					cocopage.refresh();
				};
				ajax.post("setFpno.do", postContent);
			}
			//
			function success() {
				alert("修改成功");
				coco.hidePage('ThDetail');
				cocopage.refresh();
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="退货记录管理">
			<f:page action="index.do" file="list.jsp">
				<table width="100%" class="form">
					<colgroup>
						<col width="18%" />
						<col width="32%" />
						<col width="18%" />
						<col width="32%" />
					</colgroup>
					<tr>
						<th style="text-align: left;">出货日期</th>
						<td><ui:date name="chri" prop="calendar:true;" /></td>
						<th style="text-align: left;">退货日期</th>
						<td><ui:date name="thri" prop="calendar:true;" /></td>
					</tr>
					<tr>
						<th style="text-align: left;">装箱指示No.</th>
						<td><ui:input name="zxno" maxlength="10" title="装箱指示No." /></td>
						<th style="text-align: left;">制品No.</th>
						<td><ui:input name="jbno" maxlength="11" title="制品No." /></td>
					</tr>
					<tr>
						<th style="text-align: left;">发票处置状态</th>
						<td>
						<ui:select name="fpcz" list="'':'全部','0':'未处置','1':'已处置'" cssStyle="width:55%" /></td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<div class="submit">
					<input type="button" class="button" value="查 询" onclick="cocopage.query()" />
					<input type="button" id="cxth" class="button" value="撤消退货" onclick="doCxth()" />
					<input type="button" class="button" value="设定退货发票号" onclick="toFp()" />
				</div>	
			</f:page>
		</ui:page>
		<ui:panel id="FpDetail" title="设定发票号" popup="true" display="false" closable="true">
			<form name="FpForm" xu.ajax="" xu.s="" method="post" action="">
				<table width="96%" class="form" style="margin-top: 10px;">
					<colgroup> <col width="15%" /><col width="55%" /><col width="30%" /> </colgroup>
					<tr>
						<th>发票号</th>
						<td><ui:input name="fpno" /></td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="submit"><input type="button" id="bcfp" class="button" value="保 存" onclick="saveFp();" /></div>
			</form>
		</ui:panel>
		<ui:panel id="ThDetail" title="修改退货信息" display="false" closable="true" popup="true">
		<div id="ThDiv" class="scroll" style="width:100%; height:260px;"></div>
		</ui:panel>
	</body>
</html>