<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>基板订购单查询</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["删除", "del.gif", "toDelete()"],["修改", "edit.gif", "toModify()"],["取消发送", "edit.gif", "updateStat('0')"]]);	
			//修改状态
			function updateStat(stat,oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent;
				if(oTr == null || oTr.length == 0){
					postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				} else {
				    postContent =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
				if(postContent == null || postContent.length == 0){
					alert("请选择要做修改状态的记录");
					return;
				}
				if(!confirm("是否确定修改状态?")) return;
				postContent = postContent + "&stat=" + stat;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("操作成功");
					cocopage.refresh();
				};
				ajax.post("updateJbddStat.do", postContent);
			}

			function toDelete(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent =  coco.parseParam("pid", encodeURIComponent(coco.getAttr(oTr, "id0")));
				if(postContent == null || postContent.length == 0){
					alert("请选择要做删除操作的记录");
					return;
				}
				if(!confirm("是否确定删除?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("deleteJbdd.do", postContent);
			}
			//修改操作
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "id0");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					try {
						var form = document.forms["JbddForm"];
						cocoform.clear(form);
						cocoform.fillResult(form, this.result);
						coco.showPage("JbddDetail", {center : true, top : 100, width : "85%" });
					} catch (e) {
						alert("系统出错:\n" + e.description);
					}
				};
				ajax.post("loadJbdd.do", "id="+key1);
			}

			//修改基板订单
			function saveJbdd(el) {
				var form = el.form;
				var el1 = form.elements["jbno"];
				var jbno = el1.value;
				if(jbno == null || jbno.length == 0) {
					alert("请输入订购单号");
					el1.focus();
					return;
				}
				cocoform.submit(form, function() {
						if(this.code <= 0) {
							alert(this.msg);
							cocopage.query();
							return;
						}
						alert("修改成功");
						coco.hidePage('JbddDetail');
						cocopage.query();
						return;
					}, "是否确定修改?", null, el) ;
			}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="基板订购单查询">
		<f:page action="indexJbdd.do" file="listJbdd.jsp">
		<table width="100%" class="form">
			<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="50%" /></colgroup>
			<tr>
				<th>订购单号</th>
				<td><ui:select name="pid" sql="#select a.ID_,a.JBNO_ from SINO_JBDD a order by a.JBNO_ desc" headerText="--请选择--" headerValue="" onchange="doQuery(this)"/> </td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="查 询" onclick="cocopage.query()" />
		<input type="button" class="button" value="已发送" onclick="updateStat('1','');" />
		</div>
		</f:page>
	</ui:page>
	<ui:panel id="JbddDetail" title="修改基板订购单号" popup="true" display="false" closable="true">
	<form name="JbddForm" method="post" action="updateJbno.do" >
		<input type="hidden" name="id" />
		<table align="center" width="96%" class="form" >
		<colgroup><col width="20%" /><col width="30%" /><col width="20%" /><col width="30%" /></colgroup>
		<tr>
		<th style="text-align: left;">订购单号</th>
		<td><ui:input name="jbno" title="订购单号" maxlength="8"/></td>
		<td colspan="2">&nbsp;</td>
		</tr>	
		</table>
		<div class="submit"><input type="button" class="button" value="保 存" onclick="saveJbdd(this);" />  <input type="button" class="button"value="关 闭" onclick="coco.hidePage('JbddDetail');" /> </div>
		</form>
	</ui:panel>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>