<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>今日出货联络单</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
			var type = '${type}';
			if(type == "1" ){
				contextmenu.putMenus("menu", [["制作装箱指示书", "edit.gif", "doZxzs()"],["已结束", "edit.gif", "doOver()"],["取消已打单", "edit.gif", "doQxdd()"]]);	
			}
		
			//制作装箱指示
			function doZxzs(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				parent.cocoframe.open("zzZxzs", "制作装箱指示书", "/sino/ch/zxzs/zzZxzs.do?id="+vKey1, null, null, true);
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
			//设置次日出货单结束
			function doOver(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				if(vKey1 == null || vKey1.length == 0){
					alert("参数为空");
					return ;
				}
				if(!window.confirm("是否确定结束?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					cocopage.refresh();
				};
				ajax.post("updateChlld.do", coco.parseParams([{name : "id",value: vKey1},{name : "stat",value: "3"}]));
			}
			//设置次日出货单结束
			function doQxdd(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				if(vKey1 == null || vKey1.length == 0){
					alert("参数为空");
					return ;
				}
				if(!window.confirm("是否确定结束?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					cocopage.refresh();
				};
				ajax.post("updateChlld.do", coco.parseParams([{name : "id",value: vKey1},{name : "stat",value: "1"}]));
			}
			//查看装箱指示书
			function openZxzs(zxno) {
				if(zxno == null || zxno.length == 0){
				  alert("参数为空");
                  return ;
				}
				parent.cocoframe.open("viewZxzs", "查看装箱指示书明细", "/sino/ch/zxzs/view.do?zxno="+zxno, null, null, true);;
			}
			//十秒钟，自动刷新页面
			//window.setInterval(cocopage.refresh, 60000);
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="今日出货联络单">
		<f:page action="chlld!${type}.do" file="listChlld.jsp">
			<table width="100%" class="form">
				<colgroup><col width="10%"/><col width="90%" /></colgroup>
				<tr>
					<th style="text-align: left; ">出货日期 </th>
					<td><ui:date name="chqiBegin" cssClass="normal" required="true" onchange="cocopage.query();" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="cocopage.query()" />
				<input type="button" class="button" value="打 印" onclick="doPrint()" />
			</div>
		</f:page>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>