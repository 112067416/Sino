<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
	prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>生产指示单登录</title>
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
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDelete()"]]);	
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
			
			//验证用户代码与订货no是否对应
			function doDhno(obj){
				var dhno = obj.value;
				if(dhno == null || dhno.length == 0){
					return ;
				}
				var form = obj.form;
				var postContent = coco.parseParam("dhno", dhno);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						obj.focus();
						return;
					}
					cocoform.fillResult(form, this.result,null,true);
				};
				ajax.post("getDhInfo.do", postContent);
			}
			
			//根据订货no及行号获取订货db中的信息填充引入项
			function doLine(obj){
				var line = obj.value;
				if(line == null || line.length == 0){
					return ;
				}
				var form = obj.form;
				var dhno = form.elements["dhno"].value;
				if(dhno == null || dhno.length == 0){
					alert("请输入订货合同号");
					form.elements["dhno"].focus();
					return ;
				}
				var postContent = coco.parseParams([{name:"dhno",value:dhno},{name:"line",value:line}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						obj.focus();
						return;
					}
					cocoform.fillResult(form, this.result,null,true);
				};
				ajax.post("getDhInfo.do",postContent);
			}
			//保存操作
			function doSave(oInput) {
				var oForm = oInput.form;
				cocoform.submit(oInput, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("保存成功");
					var ignores = ["crea"];
					cocoform.clear(oForm, ignores);
					oForm.elements["dhno"].focus();
					cocopage.query();
					}, '是否确定保存?');
			}

			var flag = true;
			function doLoad() {
				if(!flag) return;
				flag = false;
				var oForm = document.forms["DataForm"];
				var el1 = oForm.elements["dhno"];
				var el2 = oForm.elements["line"];
				var dhno = el1.value;
				var line = el2.value;
				if(dhno == null || dhno.length == 0 || line == null || line.length == 0) {
					el1.focus();
					return;
				}
				var postContent = coco.parseParams([{name:"dhno",value:dhno},{name:"line",value:line}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					cocoform.fillResult(oForm, this.result,null,true);
				};
				ajax.post("getDhInfo.do",postContent);
			}
			
			//改变日期
			function changeDate(v) {
				if(v == null || v.length == 0) {
					alert("出货日期不能为空");
					return;
				}
				var qForm = document.forms["PageForm_page"];
				qForm.elements["creaBegin"].value = v;
				cocopage.query();
			}

			//打印操作
			function doPrint(oForm) {
				var crea = oForm.elements["crea"].value;
				var ddno = oForm.elements["ddno"].value;
				var postContent = coco.parseParams([{name:"crea",value:crea},{name:"ddno",value:ddno}]);
				if(!confirm("是否确定打印?")) return;
				document.getElementById("PrintFrame").src = path + "/sino/dy/sczs.do?"+postContent;
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

			function viewKc(form) {

			}
		//-->
		</SCRIPT>
</head>
<body onload="doLoad();">
<ui:page title="生产指示单登录 ">
<form name="DataForm" method="post" action="save.do" >
<input type="hidden" name="id" /><input type="hidden" name="ddno" value="${ddno }" />
<table width="100%" >
<colgroup>
<col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="11%" />
<col width="9%" /><col width="9%" /><col width="6%" /><col width="6%" />
<col width="9%" /><col width="9%" /><col width="9%" />
</colgroup>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">制单日期</th>
<td colspan="2"><ui:date name="crea" cssClass="normal" required="true" showCalendar="true" value="crea" onchange="changeDate(this.value);" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">预定出货日期</th>
<td colspan="2"><ui:date name="chqi" cssClass="normal" showCalendar="true" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;" style="text-align: left;">预安排生产量</th>
<td colspan="2"><ui:number name="yscl" cssClass="normal" title="预安排生产量" scale="7" precision="3" positive="true"/></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;" style="text-align: left;">是否紧急</th>
<td colspan="2" style="text-align: left;"><input type="checkbox" name="sfjj" value="Y" /></td>
</tr>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">订货NO</th>
<td colspan="2"><ui:input name="dhno" cssClass="normal" title="订货DB的订货NO" value="dhno" maxlength="7" required="true" />-<ui:input name="line" cssClass="normal" title="行号" value="line" maxlength="2" required="true" onblur="doLine(this);" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">合同量</th>
<td><ui:number name="htzl" title="合同量" readonly="true" scale="7" precision="3" positive="true"/></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">运用规格</th>
<td><ui:input name="yuny" title="运用规格" readonly="true" maxlength="6" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">表面</th>
<td><ui:input name="face" title="表面" readonly="true" maxlength="2" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">镀锡量</th>
<td colspan="2"><ui:input name="fudw" title="附着量.单位" readonly="true" maxlength="2" /> <ui:input name="fuzm" title="附着量.正面" maxlength="3" readonly="true" />/<ui:input name="fufm" title="附着量.反面" maxlength="3" readonly="true" /></td>
</tr>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">交货日期</th>
<td colspan="2"><ui:input name="jhqi" readonly="true" maxlength="10" /></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">尺寸</th>
<td colspan="3"><ui:number name="houu" title="订货DB的订货尺寸.厚" readonly="true" scale="4" precision="3" positive="true"/>
*<ui:number name="kuan" title="订货DB的订货尺寸.宽" readonly="true" scale="6" precision="2" positive="true"/>
*<ui:number name="cang" title="订货DB的订货尺寸.长" readonly="true" scale="6" precision="2" positive="true"/></td>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">用户名称</th>
<td colspan="4"><ui:input name="name" title="用户" readonly="true" maxlength="26" /><input type="hidden" name="abbr" /> <input type="hidden" name="user" /></td>
</tr>
<tr>
<th height="23" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">原板库存量</th>
<td colspan="2"><ui:number name="ybkc" title="原板库存量" maxlength="12" scale="11" precision="3" positive="true" readonly="true" /></td>
<td colspan="9">&nbsp;</td>
</tr>
</table>
<div class="submit" > <input type="button" class="button" value="保 存" onclick="doSave(this);" />  <input type="reset"" class="button" value="重 置" />  <input type="reset"" class="button" value="删 除" onclick="toDelete('');" />  <input type="button" class="button" value="打 印" onclick="doPrint(this.form)" /> </div>
</form>
<f:page action="index.do" file="list.jsp">
<input type="hidden" name="creaBegin" value="<f:v value="crea" format="yyyy-MM-dd" />" />
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
