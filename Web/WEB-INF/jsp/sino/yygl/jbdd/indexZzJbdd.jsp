<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>制作基板订购单</title>	
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
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"]]);	
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
					var form = document.forms["DataForm"];
					cocoform.clear(form);
					cocoform.fillResult(form, this.result);
					form.elements["$type"].value = "modify";
					coco.showPage("Detail", {center : true, top : 100, width : "85%" });
				} catch (e) {
					alert("系统出错:\n" + e.description);
				}
			};
			ajax.post("load.do", "id="+key1);
		}

		// 成功提示
		function success() {
			alert("保存成功");
			coco.hidePage('Detail');
			cocopage.refresh();
		}

		function calculate() {
			var oForm = document.forms["DataForm"];
			var houa = oForm.elements["houa"].value;
			if(houa == null || houa.length == 0) return;
			var houb = oForm.elements["houb"].value;
			if(houb == null || houb.length == 0) return;
			oForm.elements["ajb"].value = (houa - houb).toFixed(3);
		}

		//复选框选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
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

		//新增
		function toAdd() {
			var form = document.forms["DataForm"];
			cocoform.clear(form);
			coco.showPage("Detail", {center : true, top : 100,width : "85%"});
		}

		//删除操作
		function toDelete(el) {
			var form = el.form;
			var ids = form.elements["ids"].value;
			if(ids == null || ids.length == 0){
				alert("请选择要做删除操作的记录");
				return;
			}
			var postContent = coco.parseParam("ids", ids);
			if(!confirm("是否确定删除?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				form.elements["ids"].value = "";
				cocopage.refresh();
			};
			ajax.post("dels.do", postContent);
		}

		//上锁操作
		function lock(el) {
			var form = el.form;
			var ids = form.elements["ids"].value;
			if(ids == null || ids.length == 0){
				alert("请选择要做上锁操作的记录");
				return;
			}
			var postContent = coco.parseParam("ids", ids);
			if(!confirm("是否确定上锁?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("上锁成功");
				form.elements["ids"].value = "";
				cocopage.refresh();
			};
			ajax.post("lock.do", postContent);
		}

		//解锁操作
		function unLock(el) {
			var form = el.form;
			var ids = form.elements["ids"].value;
			if(ids == null || ids.length == 0){
				alert("请选择要做解锁操作的记录");
				return;
			}
			var postContent = coco.parseParam("ids", ids);
			if(!confirm("是否确定解锁?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("解锁成功");
				form.elements["ids"].value = "";
				cocopage.refresh();
			};
			ajax.post("unLock.do", postContent);
		}


		//追加基板订单
		function toJbdd(el) {
			var qForm = el.form;
			var ids = qForm.elements["ids"].value;
			if(ids == null || ids.length == 0){
				alert("请选择要做追加操作的基板订购单记录");
				return;
			}
			var form = document.forms["JbddForm"];
			form.elements["ids"].value = ids;
			form.elements["pid"].value = "";
			coco.showPage("JbddDetail", {center : true, top : 100,width : "85%"});
		}

		//追加订单
		function saveJbdd(el) {
			var form = el.form;
			var el1 = form.elements["pid"];
			var pid = el1.value;
			if(pid == null || pid.length == 0) {
				alert("请选择订购单号");
				el1.focus();
				return;
			}
			var qForm = document.forms["PageForm_page"];
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("追加成功");
					coco.hidePage('JbddDetail');
					qForm.elements["pid"].value = pid;
					qForm.elements["ids"].value = "";
					cocopage.query();
					return;
				}, "是否确定保存?", null, el) ;
		}
		//补加基板订单
		function toBj(el) {
			var qForm = el.form;
			var ids = qForm.elements["ids"].value;
			if(ids == null || ids.length == 0){
				alert("请选择要做补加操作的基板订购单记录");
				return;
			}
			var form = document.forms["BjJbddForm"];
			form.elements["ids"].value = ids;
			form.elements["pid"].value = "";
			coco.showPage("BjJbddDetail", {center : true, top : 100,width : "85%"});
		}
		//补加订单
		function bjJbdd(el) {
			var form = el.form;
			var el1 = form.elements["pid"];
			var pid = el1.value;
			if(pid == null || pid.length == 0) {
				alert("请选择订购单号");
				el1.focus();
				return;
			}
			var qForm = document.forms["PageForm_page"];
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("补加成功");
					coco.hidePage('BjJbddDetail');
					qForm.elements["pid"].value = pid;
					qForm.elements["ids"].value = "";
					cocopage.query();
					return;
				}, "是否确定保存?", null, el) ;
		}
		//创建基板订单
		function toBuild(el) {
			var qForm = el.form;
			var ids = qForm.elements["ids"].value;
			if(ids == null || ids.length == 0){
				alert("请选择要创建的基板订购单记录");
				return;
			}
			var form = document.forms["CjJbddForm"];
			form.elements["ids"].value = ids;
			form.elements["jbno"].value = "";
			coco.showPage("CjJbddDetail", {center : true, top : 100,width : "85%"});
		}

		//创建订单
		function cjJbdd(el) {
			var form = el.form;
			var el1 = form.elements["jbno"];
			var jbno = el1.value;
			if(jbno == null || jbno.length == 0) {
				alert("请输入订购单号");
				el1.focus();
				return;
			}
			var qForm = document.forms["PageForm_page"];
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("创建成功");
					coco.hidePage('CjJbddDetail');
					qForm.elements["ids"].value = "";
					window.location.reload();
					return;
				}, "是否确定创建?", null, el) ;
		}

		function doSave(el) {
			var form = el.form;
			var dhsl = form.elements["dhsl"].value;
			var cksl = form.elements["cksl"].value;
			if(dhsl != null && dhsl.length > 0 && cksl != null && cksl.length > 0) {
				alert("国内数量和出口数量不能同时存在");
				form.elements["cksl"].focus();
				return;
			}
			if((dhsl == null || dhsl.length == 0) && (cksl == null || cksl.length == 0)) {
				alert("订货数量不能为空");
				form.elements["dhsl"].focus();
				return;
			}
			cocoform.submit(form, null, '是否确定保存?', null);
		}
		//将订单信息，移出订购单号
		function doRemove(el) {
			var form = el.form;
			var ids = form.elements["ids"].value;
			if(ids == null || ids.length == 0) {
				alert("请选择要移出的订单记录");
				return;
			}
			var el1 = form.elements["pid"];
			var pid = el1.value;
			if(pid == null || pid.length == 0) {
				alert("选择的订单记录不属于任何订购单号,因此不能做移出操作");
				return;
			}
			var jbno = el1.options[el1.selectedIndex].text;
			if(!confirm("是否确定将选中的订单记录从"+jbno+"订购单号中移出?")) return;
			var postContent = coco.parseParam("ids", ids);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("移出成功");
				form.elements["ids"].value = "";
				cocopage.refresh();
			};
			ajax.post("ycJbdd.do", postContent);
		}

		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="制作基板订购单">
		<f:page action="queryJbdd.do" file="listZzJbdd.jsp">
			<input type="hidden" name="ids" />
			<table width="100%" class="form">
			<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="50%" /></colgroup>
			<tr>
				<th>订购单号</th>
				<td><ui:select name="pid" sql="#select a.ID_,a.JBNO_ from SINO_JBDD a order by a.JBNO_ desc" headerText="--请选择--" headerValue="" onchange="doQuery(this)"/> </td>
				<td colspan="2">&nbsp;</td>
			</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查   询" onclick="doQuery(this);" />
				<input type="button" class="button" value="创   建" onclick="toBuild(this);" />
				<input type="button" class="button" value="新   增" onclick="toAdd();" />
				<input type="button" class="button" value="补   加" onclick="toBj(this);" />
				<input type="button" class="button" value="追   加" onclick="toJbdd(this);" />
				<input type="button" class="button" value="移   出" onclick="doRemove(this);" />
				<input type="button" class="button" value="删   除" onclick="toDelete(this);" />
				<input type="button" class="button" value="上   锁" onclick="lock(this);" />
				<input type="button" class="button" value="解   锁" onclick="unLock(this);" />
			</div>
		</f:page>
	</ui:page>
	<ui:panel id="Detail" title="基板订购单维护" popup="true" display="false" closable="true">
	<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
		<input type="hidden" name="$type" /><input type="hidden" name="id" /><input type="hidden" name="zjzt" />
		<table align="center" width="96%" class="form" >
		<colgroup><col width="10%" /><col width="12%" /><col width="9%" /><col width="10%" /><col width="10%" /><col width="11%" /><col width="10%" /><col width="10%" /><col width="9%" /><col width="9%" /></colgroup>
		<tr>
			<th style="text-align: center; vertical-align: middle;">紧急程度</th>
			<th style="text-align: center; vertical-align: middle;">输入编号</th>
			<th style="text-align: center; vertical-align: middle;">表面</th>
			<th style="text-align: center; vertical-align: middle;">硬度</th>
			<th style="text-align: center; vertical-align: middle;">厚度A</th>
			<th style="text-align: center; vertical-align: middle;">宽度</th>
			<th style="text-align: center; vertical-align: middle;">厚度B</th>
			<th style="text-align: center; vertical-align: middle;">A-B</th>
			<th style="text-align: center; vertical-align: middle;">国内数量</th>
			<th style="text-align: center; vertical-align: middle;">出口数量</th>
		</tr>
		<tr>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="isgi" title="紧急程度" maxlength="4"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="inpu" title="输入编号" maxlength="4"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="face" title="表面" maxlength="2"/></td><td style="text-align: center; vertical-align: middle;"><ui:input name="yuny" title="运用规格" maxlength="6"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:number name="houa" title="厚度A" maxlength="5" scale="4" precision="3" onchange="calculate();" /></td><td style="text-align: center; vertical-align: middle;"><ui:number name="width" title="宽度" scale="6" precision="2"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:number name="houb" title="厚度B" maxlength="5" scale="4" precision="3" onchange="calculate();" /></td><td style="text-align: center; vertical-align: middle;"><ui:number name="ajb" title="A-B" maxlength="6" scale="4" precision="3"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:int name="dhsl" title="国内数量(吨)" scale="8" maxlength="4"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:int name="cksl" title="出口数量(吨)" scale="8" maxlength="4"/></td>
		</tr>
		<tr>
			<th style="text-align: center; vertical-align: middle;" colspan="2">用户略称</th>
			<th style="text-align: center; vertical-align: middle;" colspan="4">用途</th>
			<th style="text-align: center; vertical-align: middle;">代码</th>
			<th style="text-align: center; vertical-align: middle;">C/*</th>
			<th style="text-align: center; vertical-align: middle;" colspan="2">镀锡量</th>
		</tr>
		<tr>
			<td style="text-align: center; vertical-align: middle;" colspan="2"><ui:input name="abbr" title="用户略称" maxlength="24"/></td>
			<td style="text-align: center; vertical-align: middle;" colspan="4"><ui:input name="cond" title="用途" maxlength="30"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="code" title="代码" maxlength="2"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="hanc" title="含碳C" maxlength="2"/></td>
			<td style="text-align: center; vertical-align: middle;" colspan="2"><ui:input name="fuzm" title="附着量.正面" maxlength="3"/>-<ui:input name="fufm" title="附着量.反面" maxlength="3"/></td>
		</tr>
		<tr>
			<th style="text-align: center; vertical-align: middle;" colspan="2">备注</th>
			<td style="text-align: center; vertical-align: middle;" colspan="8">&nbsp;</td>
		</tr>
		<tr>
			<td style="text-align: left; vertical-align: middle;" colspan="10"><ui:input name="rema" title="备注" maxlength="80" lowToUp="false" /></td>
		</tr>
		<tr>
			<th style="text-align: center; vertical-align: middle;" colspan="2">公式</th>
			<th style="text-align: center; vertical-align: middle;" colspan="2">&nbsp;</th>
			<th style="text-align: center; vertical-align: middle;" colspan="2">确认数</th>
			<th style="text-align: center; vertical-align: middle;" colspan="4">&nbsp;</th>
		</tr>
		<tr>
			<td style="text-align: center; vertical-align: middle;" colspan="4"><ui:input name="calc" title="公式" maxlength="30"/></td>
			<td style="text-align: center; vertical-align: middle;" colspan="2"><ui:int name="conf" title="确认数(吨)" required="true" maxlength="4"/></td>
			<td style="text-align: center; vertical-align: middle;" colspan="4">&nbsp;</td>
		</tr>
		</table>
		<div class="submit"><input type="button" class="button" value="保 存" onclick="doSave(this);" />  <input type="button" class="button"value="关 闭" onclick="coco.hidePage('Detail')" /> </div>
		</form>
	</ui:panel>
	<ui:panel id="JbddDetail" title="追加基板订购单" popup="true" display="false" closable="true">
	<form name="JbddForm" method="post" action="zjJbdd.do" >
		<input type="hidden" name="ids" />
		<table align="center" width="96%" class="form" >
		<colgroup><col width="20%" /><col width="30%" /><col width="20%" /><col width="30%" /></colgroup>
		<tr>
		<th style="text-align: left;">订购单号</th>
		<td><ui:select name="pid" sql="#select a.ID_,a.JBNO_ from SINO_JBDD a order by a.JBNO_ desc" headerText="" headerValue="" /> </td>
		<td colspan="2">&nbsp; </td>
		</tr>	
		</table>
		<div class="submit"><input type="button" class="button" value="保 存" onclick="saveJbdd(this);" />  <input type="button" class="button"value="关 闭" onclick="coco.hidePage('JbddDetail');" /> </div>
		</form>
	</ui:panel>
	<ui:panel id="BjJbddDetail" title="补加基板订购单" popup="true" display="false" closable="true">
	<form name="BjJbddForm" method="post" action="bjJbdd.do" >
		<input type="hidden" name="ids" />
		<table align="center" width="96%" class="form" >
		<colgroup><col width="20%" /><col width="30%" /><col width="20%" /><col width="30%" /></colgroup>
		<tr>
		<th style="text-align: left;">订购单号</th>
		<td><ui:select name="pid" sql="#select a.ID_,a.JBNO_ from SINO_JBDD a order by a.JBNO_ desc" headerText="" headerValue="" /> </td>
		<td colspan="2">&nbsp; </td>
		</tr>	
		</table>
		<div class="submit"><input type="button" class="button" value="保 存" onclick="bjJbdd(this);" />  <input type="button" class="button"value="关 闭" onclick="coco.hidePage('JbddDetail');" /> </div>
		</form>
	</ui:panel>
	<ui:panel id="CjJbddDetail" title="创建基板订购单" popup="true" display="false" closable="true">
	<form name="CjJbddForm" method="post" action="cjJbdd.do" >
		<input type="hidden" name="ids" />
		<table align="center" width="96%" class="form" >
		<colgroup><col width="20%" /><col width="30%" /><col width="20%" /><col width="30%" /></colgroup>
		<tr>
		<th style="text-align: left;">订购单号</th>
		<td><ui:input name="jbno" title="订购单号" maxlength="8"/></td>
		<td colspan="2">&nbsp;</td>
		</tr>	
		</table>
		<div class="submit"><input type="button" class="button" value="保 存" onclick="cjJbdd(this);" />  <input type="button" class="button"value="关 闭" onclick="coco.hidePage('CjJbddDetail');" /> </div>
		</form>
	</ui:panel>
	</body>
</html>