<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
	prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>生产指示单</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<style type="text/css">
		TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
		TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
		TABLE.pagination1 th { font-size: 12px; color: #003449; height: 20px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
		TABLE.pagination1 td { font-size: 13px; color: #333333; height: 25px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
		TABLE.pagination1 tr {background-color: #F7F7F7;}
		TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
		TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
		</style>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDelete()"],["分配指示", "edit.gif", "toFpzs()"]]);
		//进入次日出货联络单登录页面
		function toFpzs(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var dhno = coco.getAttr(oTr, "dhno");
			var line = coco.getAttr(oTr, "line");
			var id = coco.getAttr(oTr, "id0");
			parent.cocoframe.open("fpzs", "分配指示", "/sino/zkfp/fp/indexYcai.do?dhno="+dhno+"&line="+line+"&sczsId="+id, null, null, true);
		}
		//打印操作
		function doPrint() {
			var form = document.forms["PageForm_page"];
			var crea = form.elements["creaBegin"].value;
			if(crea == null || crea.length == 0) {
				alert("制单日期不能为空");
				return;
			}
			var postContent = coco.parseParams([{name:"crea",value:crea}]);
			if(!confirm("是否确定打印?")) return;
			document.getElementById("PrintFrame").src = path + "/sino/dy/sczs.do?"+postContent;
		}
		//分配结束
		function doFinish() {
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(ids == null || ids.length == 0){
				alert("请选择要做分配结束操作的生产指示单");
				return;
			}
		    if(!confirm("确定分配结束吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("操作成功");
				cocopage.refresh();
			};
			ajax.post("finish.do", ids);
		}
		//修改操作
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			var ajax = new Cocoajax();
			ajax.dataDiv = DataDiv;
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("Detail", {center : true, top : 100, width : "80%" });
			};
			ajax.post("toUpdate.do", "id="+key1);
		}
		
		//删除操作
		function toDelete(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var postContent;
			if(oTr == null || oTr.length == 0){
				postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
			} else {
			    postContent =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
			}
			if(postContent == null || postContent.length == 0){
				alert("请选择要做删除操作的记录");
				return;
			}
			if(!confirm("是否确定删除?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				cocopage.refresh();
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				var form = document.forms["DataForm"];
				cocoform.clear(form);
			};
			ajax.post("delete.do", postContent);
		}
		//修改操作
		function doUpdate(oInput) {
			cocoform.submit(oInput, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				}, "是否确定保存?") ;

		}
			//十秒钟，自动刷新页面
			//window.setInterval(cocopage.refresh, 60000);
		//-->
		</SCRIPT>
	</head>
	<body >
<ui:page title="生产指示单">
<f:page action="indexSczs.do" file="listSczs.jsp">
<table width="100%" class="form">
<colgroup><col width="10%"/><col width="10%" /><col width="6%"/><col width="10%" /><col width="10%"/><col width="14%" /><col width="10%"/><col width="10%" /><col width="10%"/><col width="10%" /></colgroup>
<tr>
<th style="text-align: left;">制单日期 </th>
<td><ui:date name="creaBegin" cssClass="normal" onchange="cocopage.query();"/></td>
<th style="text-align: left;">状态 </th>
<td><ui:select name="stat" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_SCZS_STAT'" value="page.stat" headerText="全部" headerValue="" onchange="cocopage.query();"/></td>
<th style="text-align: left;">订货NO</th>
<td><ui:input name="dhno" cssClass="normal" maxlength="7" />-<ui:input name="line" cssClass="normal" maxlength="2" /></td>
<th style="text-align: left;">用户名称</th>
<td><ui:input name="name" cssClass="normal" maxlength="26" /></td>
<th style="text-align: left;">业务员</th>
<td><ui:input name="ddnm" cssClass="normal" maxlength="8" /></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
<input type="button" class="button" value="分配结束" onclick="doFinish();" />
</div>
</f:page>
</ui:page>
<ui:panel id="Detail" title="修改" popup="true" display="false" closable="true">
<form name="UpdateForm" method="post" action="save.do" >
<div id="DataDiv"></div>
<div class="opt-btm"><input type="button" class="button" value="保   存" onclick="doUpdate(this);" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail');" /></div>
</form>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>