<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
	prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>次日出货联络单</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["分解", "edit.gif", "toDivide()"],["设置", "edit.gif", "toSetting()"]]);
			//解锁
			function deLock() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0){
					alert("请选择要解锁的记录");
					return false;
				}
				if(!confirm("是否确定解锁?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg)
						return;
					}
					alert("解锁成功");
					cocopage.refresh();
				};
				ajax.post("deLock.do", ids);
			}
			//上锁
			function toLock() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0){
					alert("请选择要上锁的记录");
					return false;
				}
			 	if(!confirm("是否确定上锁?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("上锁成功");
					cocopage.refresh();
				};
				ajax.post("lock.do", ids);
			}
			//打印操作
			function doPrint() {
				var form = document.forms["PageForm_page"];
				var chqi = form.elements["chqiBegin"].value;
				if(chqi == null || chqi.length == 0) {
					alert("出货日期不能为空");
					return;
				}
				var postContent = coco.parseParams([{name:"chqi",value:chqi}]);
				if(!confirm("是否确定打印?")) return;
				document.getElementById("PrintFrame").src = path + "/sino/dy/chlld.do?"+postContent;
			}
			
			// 设置
			function toSetting(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "id0");
				var ajax = new Cocoajax();
				ajax.dataDiv = DataDiv2;
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					coco.showPage("Detail", {center : true, top : 100, width : "80%" });
				};
				ajax.post("toSetting.do", "id="+key1);
			}
			//删除
			function toDelete() {
				var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(postContent == null || postContent.length == 0) {
					alert("请选择要删除的记录");
					return;
				}
				if(!confirm("是否确定删除?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						cocopage.refresh();
						return;
					}
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("delete.do", postContent);
			}
			// 分解
			function toDivide(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "id0");
				var ajax = new Cocoajax();
				ajax.dataDiv = DataDiv1;
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					coco.showPage("EditDetail", {center : true, top : 100, width : "80%" });
				};
				ajax.post("toDivide.do", "id="+key1);
			}
			//保存设置
			function doSetting(oInput) {
				cocoform.submit(oInput, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("设置成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				}, "是否确定保存?") ;
				
			}
			//分解操作
			function doDivide(oInput) {
				cocoform.submit(oInput, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("分解成功");
						coco.hidePage('EditDetail');
						cocopage.refresh();
					}, "是否确定分解?");
			}
			
			//装箱指示书查看
			function openZxzs(id) {
				if(id == null || id.length == 0) {
					alert("参数为空");
					return ;
				}
				parent.cocoframe.open("view", "查看装箱指示书明细", "/sino/ch/zxzs/view.do?zxno="+id, true);
			}

			//发送
			function toSend() {
				var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(postContent == null || postContent.length == 0) {
					alert("请选择要发送的记录");
					return;
				}
				if(!confirm("是否确定发送?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("发送成功");
					cocopage.refresh();
				};
				ajax.post("send.do", postContent);
			}

			function changeValue(obj,e) {
				var oForm = obj.form;
				var txt = obj.options[obj.selectedIndex].text;
				oForm.elements[e].value = txt;
			}
			//设置天气信息
			function setWeather(obj) {
				var v = obj.value;
				if(v == null || v.length == 0) {
					alert("天气不能为空");
					obj.focus();
					return;
				}
				var oForm = obj.form;
				var chqi = oForm.elements["chqiBegin"].value;
				if(chqi == null || chqi.length == 0) {
					alert("出货日期不能为空");
					return;
				}
				var postContent = coco.parseParams([{name:"chqi",value:chqi},{name:"weather",value:v}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("保存成功");
				};
				ajax.post("setWeather.do", postContent);
			}

			//获取天气信息
			function getWeather() {
				var oForm = document.forms["PageForm_page"];
				var el1 = oForm.elements["chqiBegin"];
				var chqi = el1.value;
				var el2 = oForm.elements["weather"];
				var postContent = coco.parseParams([{name:"chqi",value:chqi}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					el2.value = this.msg;
				};
				ajax.post("getWeather.do", postContent);
			}

			//十秒钟，自动刷新页面
			//window.setInterval(cocopage.refresh, 60000);
		//-->
		</SCRIPT>
	</head>
	<body >
		<ui:page title="次日出货联络单">
		<f:page action="indexChlld.do" file="listChlld.jsp">
			<table width="100%" class="form">
				<colgroup><col width="10%"/><col width="40%" /><col width="10%"/><col width="40%" /></colgroup>
				<tr>
					<th style="text-align: left; ">出货日期 </th>
					<td><ui:date name="chqiBegin" cssClass="normal" required="true" onchange="cocopage.query('page', getWeather);"/></td>
					<th style="text-align: right; ">天气 </th>
					<td style="text-align: left; "><ui:input name="weather" maxlength="10" value="weather" onkeydown="if(window.event.keyCode == 13) setWeather(this);"/></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="刷 新" onclick="cocopage.query('page', getWeather);" />
				<input type="button" class="button" value="发 送" onclick="toSend('')" />
				<input type="button" class="button" value="上 锁" onclick="toLock()" />
				<input type="button" class="button" value="解 锁" onclick="deLock()" />
				<input type="button" class="button" value="删 除" onclick="toDelete()" />
				<input type="button" class="button" value="打 印" onclick="doPrint()" />
			</div>
		</f:page>
	</ui:page>
	<ui:panel id="Detail" title="设置" popup="true" display="false" closable="true">
	<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="doSetting.do" >
		<input type="hidden" name="ids" />
		<div id="DataDiv2"></div>
		<div class="opt-btm"><input type="button" class="button" value="保   存" onclick="doSetting(this);" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail');" /></div>
	  </form>
	</ui:panel>
	<ui:panel id="EditDetail" title="分解" popup="true" display="false" closable="true">
		<form name="EditForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="doDivide.do" >
		<div id="DataDiv1"></div>
		<div class="opt-btm"><input type="button" class="button" value="保    存" onclick="doDivide(this);" />  <input type="button" class="button" value="关    闭" onclick="coco.hidePage('EditDetail');" /> </div>
	</form>
	</ui:panel>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>