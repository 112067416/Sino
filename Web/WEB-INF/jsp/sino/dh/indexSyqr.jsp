<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>仕样确认</title>
		<%@include file="../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["仕样确认", "edit.gif", "toModify()"], ["上锁", "edit.gif", "toLock()"], ["解锁", "edit.gif", "toDeLock()"], ["取消仕样确认", "edit.gif", "toCancelSyqr()"]]);
			//仕样确认                 			
	        function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "id0");
				parent.cocoframe.open("syqrDh", "订货合同仕样确认", "/sino/dh/syqrDh.do?id="+key1, null, null, true);
			}

			//上锁
			function toLock(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var qForm = document.forms["PageForm_page"];
				var postContent,ids;
				if(oTr == null || oTr.length == 0){
					ids = qForm.elements["ids"].value;
					//postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				} else {
					ids =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
				if(ids == null || ids.length == 0){
					alert("请选择要做上锁操作的记录");
					return;
				}
				var postContent = coco.parseParam("ids", ids);
			    if(!confirm("确定上锁吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("上锁成功");
					qForm.elements["ids"].value = "";
					cocopage.refresh();
				};
				ajax.post("lock.do", postContent);
			}
			//解锁
			function toDeLock(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var qForm = document.forms["PageForm_page"];
				var postContent,ids;
				if(oTr == null || oTr.length == 0){
					ids = qForm.elements["ids"].value;
					//postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				} else {
					ids =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
				if(ids == null || ids.length == 0){
					alert("请选择要做解锁操作的记录");
					return;
				}
				var postContent = coco.parseParam("ids", ids);
				if(!confirm("确定解锁吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("解锁成功");
					qForm.elements["ids"].value = "";
					cocopage.refresh();
				};
				ajax.post("deLock.do", postContent);
			}

			//取消仕样确认
			function toCancelSyqr(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var qForm = document.forms["PageForm_page"];
				var postContent,ids;
				if(oTr == null || oTr.length == 0){
					ids = qForm.elements["ids"].value;
					//postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				} else {
					ids =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
				if(ids == null || ids.length == 0){
					alert("请选择要做取消仕样确认操作的记录");
					return;
				}
				var postContent = coco.parseParam("ids", ids);
				if(!confirm("确定取消仕样确认吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("取消仕样确认成功");
					qForm.elements["ids"].value = "";
					cocopage.refresh();
				};
				ajax.post("cancelSyqr.do", postContent);
			}

			//仕样确认
			function toSyqr(el) {
				var qForm = el.form;
				var postContent,ids;
				ids = qForm.elements["ids"].value;
				if(ids == null || ids.length == 0){
					alert("请选择要做仕样确认操作的记录");
					return;
				}
				var postContent = coco.parseParam("ids", ids);
				if(!confirm("确定仕样确认吗?")) return;
				coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					coco.hideAlert();
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("仕样确认成功");
					qForm.elements["ids"].value = "";
					cocopage.refresh();
				};
				ajax.post("plSyqr.do", postContent);
			}

			//复选框选中
			function doCheck(obj) {
				if(obj == null) {
					return;
				}
				//选中装箱指示书号
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
					var $v = v.replace(/^\(/gi,'\\(').replace(/\)/gi,'\\)');
					rgExp = new RegExp($v+',|('+$v+'$)' , "g");
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
			//查询
			function doQuery(el) {
				var form = el.form;
				form.elements["ids"].value = "";
				cocopage.query();
			}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="仕样确认">
		<f:page action="indexSyqr.do" file="listSyqr.jsp">
		<input type="hidden" name="ids" />
			<table class="form" width="100%">
				<colgroup>
					<col width="10%" />
					<col width="35%" />
					<col width="10%" />
					<col width="25%" />
					<col width="10%" />
					<col width="10%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th style="text-align: left;">用户代码</th>
					<td><ui:input name="userBegin" maxlength="4"/>至<ui:input name="userEnd" maxlength="4"/></td>
					<th>用户中文名</th>
					<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">仕样时间</th>
					<td><ui:date name="sydtBegin" prop="calendar: true;" />至<ui:date name="sydtEnd" prop="calendar: true;" /></td>
					<th style="text-align: left;">订货合同状态</th>
					<td><ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_DH_STAT'" headerText="全部" headerValue="" value="page.stat" onchange="doQuery(this);" /></td>
					<th style="text-align: left;">仕样是否已修订</th>
					<td><ui:select name="syxded" list="'1':'否','0':'是'" headerText="全部" headerValue="" onchange="doQuery(this);" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="doQuery(this);" />
				<input type="button" class="button" value="上 锁" onclick="toLock('');" />
				<input type="button" class="button" value="解 锁" onclick="toDeLock('');" />
				<input type="button" class="button" value="取消仕样确认" onclick="toCancelSyqr('');" />
				<input type="button" class="button" value="批量仕样确认" onclick="toSyqr(this);" />
			</div>
		</f:page>
	</ui:page>
	</body>
</html>