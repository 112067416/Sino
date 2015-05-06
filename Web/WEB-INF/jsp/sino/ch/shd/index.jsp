<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>送货单管理</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["打印", "view.gif", "print()"], ["删除", "del.gif", "toDel()"]]);
			//打印送货单
			function print(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				var postConent;
				if(vKey1 != null && vKey1.length > 0) {
					postContent = coco.parseParams([{name : "ids",value: vKey1}]);
				} else {
					postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				}
				document.getElementById("PrintFrame").src = "../../dy/chdy/shd.do?"+postContent;
			}
			//删除
			function toDel(oTr) {
				if(!confirm("确定删除吗?")) return false;
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				var postContent = coco.parseParams([{name : "ids",value: vKey1}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("dels.do", postContent);
			}
			
			//批量删除送货单
			function dels() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids == "") {
					alert("请选择要删除的行");
					return;
				}
				if(!confirm("确定删除吗?")) return false;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("dels.do", ids);
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="送货单管理">
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
						<td><ui:date name="chriS" />至<ui:date name="chriE" /></td>
						<th style="text-align: left;">装箱指示No.</th>
						<td><ui:input name="zxno" maxlength="10" title="装箱指示No." /></td>
					</tr>
					<tr>
						<th style="text-align: left;">用户名称</th>
						<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
						<th colspan="2">&nbsp;</th>
					</tr>
				</table>
				<div class="submit"> <input type="button" class="button" value="查 询" onclick="cocopage.query()" /> <input type="button" class="button" value="打 印" onclick="print('');" /> <input type="button" class="button" value="删 除" onclick="dels();"  /></div>	
			</f:page>
		</ui:page>
	 <iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>