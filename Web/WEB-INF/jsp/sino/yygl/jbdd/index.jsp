<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>基板订购单登录</title>	
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
			contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["复制", "edit.gif", "toCopy()"],["删除", "del.gif", "toDelete()"]]);	
			//新增
			function toAdd() {
				var form = document.forms["DataForm"];
				cocoform.clear(form);
			//	form.elements["abbr"].value = "";
			//	form.elements["face"].value = "";
			//	form.elements["yuny"].value = "";
			//	form.elements["houa"].value = "";
			//	form.elements["width"].value = "";
			//	form.elements["houb"].value = "";
			//	form.elements["ajb"].value = "";
				coco.showPage("Detail", {center : true, top : 100,width : "85%"});
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
						var form = document.forms["DataForm"];
						cocoform.clear(form);
						cocoform.fillResult(form, this.result);
						form.elements["$type"].value = "modify";
						form.elements["flag"].value = "Y";
						coco.showPage("Detail", {center : true, top : 130, width : "90%" });
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
			
			//删除操作
			function toDelete(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent,ids;
				var form = document.forms["PageForm_page"];
				var el = form.elements["ids"];
				if(oTr == null || oTr.length == 0){
					ids = el.value;
				} else {
					ids = coco.getAttr(oTr, "id0");
				}
				if(ids == null || ids.length == 0) {
					alert("请选择要做删除操作的记录");
					return;
				}
				if(!confirm("是否确定删除?")) return;
				var postContent = coco.parseParam("ids", ids);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					el.value = "";
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("delete.do", postContent);
			}
			
			//复制按钮的操作
			function toCopy(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent,ids;
				var form = document.forms["PageForm_page"];
				var el = form.elements["ids"];
				if(oTr == null || oTr.length == 0){
					ids = el.value;
				} else {
					ids = coco.getAttr(oTr, "id0");
				}
				if(ids == null || ids.length == 0) {
					alert("请选择要做复制操作的记录");
					return;
				}
				if(!confirm("是否确定复制?")) return false;
				var postContent = coco.parseParam("ids", ids);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					el.value = "";
					alert("复制成功");
					cocopage.refresh();
				};
				ajax.post("copy.do", postContent);
			}

			function calculate() {
				var oForm = document.forms["DataForm"];
				var houa = oForm.elements["houa"].value;
				if(houa == null || houa.length == 0) return;
				var houb = oForm.elements["houb"].value;
				if(houb == null || houb.length == 0) return;
				oForm.elements["ajb"].value = (houa - houb).toFixed(3);
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
				calculate();
				cocoform.submit(form, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						cocopage.refresh();
						return;
					}, '是否确定保存?', null);
			}
			
			function doPrint(LODOP, content) {
				//	LODOP.SET_PRINTER_INDEXA("HPrint");
					LODOP.PRINT_INIT("JBDGD-<%=System.currentTimeMillis()%>");
					LODOP.SET_PRINTER_INDEXA("HP LaserJet Professional P1606dn");
					LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
					LODOP.ADD_PRINT_HTM(2, -13, 1100, 760, content);
				//	LODOP.SET_PRINTER_INDEXA("HP LaserJet 5200 Series PCL 5");
				//	LODOP.SET_PRINTER_INDEXA("HP LaserJet Professional P1606dn");
				//	LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
				//	LODOP.ADD_PRINT_HTM(10, 0, 1100, 1100, content);
					//打印预览
					LODOP.PREVIEW();
					//打印
				//	LODOP.PRINT();
			}

			function toPrint(el) {
				var form = el.form;
				var ids = form.elements["ids"].value;
				if(ids == null || ids.length == 0) {
					alert("请选择要打印的基板订单记录");
					return;
				}
				var pages = [];
				var idStr = '';
				var arrs = ids.split(",");
				for(var i = 0; i < arrs.length; i++) {
					idStr = idStr + "," + arrs[i];
					if((i + 1) % 20 == 0) {
						pages[pages.length] = idStr;
						idStr = '';
					}
				}
				if(idStr.length > 0) {
					pages[pages.length] = idStr;
				}
				var index = 0;
				var postContent = coco.parseParams([{name:"idStr",value:pages[index++]},{name:"current",value:index},{name:"pages",value:pages.length}]);
				var LODOP = document.getElementById("lodop");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					doPrint(LODOP,this.result);
					if(index < pages.length) {
						postContent = coco.parseParams([{name:"idStr",value:pages[index++]},{name:"current",value:index},{name:"pages",value:pages.length}]);
						ajax.post(path + "/sino/dy/jbdd!print.do", postContent);
					}
				};
				ajax.post(path + "/sino/dy/jbdd!print.do", postContent);
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

			//加载默认基板订购单信息
			function getDefault() {
				if(!confirm("是否确定加载默认基板订购单信息?")) return false;
				var form = document.forms["DataForm"];
				var abbr = form.elements["abbr"].value;
				if(abbr == null || abbr.length == 0) {
					alert("用户略称为空");
					form.elements["abbr"].focus();
					return;
				}
				var face = form.elements["face"].value;
				if(face == null || face.length == 0) {
					alert("表面为空");
					form.elements["face"].focus();
					return;
				}
				var yuny = form.elements["yuny"].value;
				if(yuny == null || yuny.length == 0) {
					alert("硬度为空");
					form.elements["yuny"].focus();
					return;
				}
				var houa = form.elements["houa"].value;
				if(houa == null || houa.length == 0) {
					alert("厚度A为空");
					form.elements["houa"].focus();
					return;
				}
				var width = form.elements["width"].value;
				if(width == null || width.length == 0) {
					alert("宽度为空");
					form.elements["width"].focus();
					return;
				}
				var houb = form.elements["houb"].value;
				if(houb == null || houb.length == 0) {
					alert("厚度B为空");
					form.elements["houb"].focus();
					return;
				}
				var postContent = coco.parseParams([{name:"abbr",value:abbr},{name:"face",value:face},{name:"yuny",value:yuny},{name:"houa",value:houa},{name:"width",value:width},{name:"houb",value:houb}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						return;
					}
					var obj;
					eval("obj="+this.result+";");
					if(obj.isgi != null) form.elements["isgi"].value = obj.isgi;
					if(obj.inpu != null) form.elements["inpu"].value = obj.inpu;
					if(obj.cond != null) form.elements["cond"].value = obj.cond;
					if(obj.code != null) form.elements["code"].value = obj.code;
					if(obj.hanc != null) form.elements["hanc"].value = obj.hanc;
					if(obj.fuzm != null) form.elements["fuzm"].value = obj.fuzm;
					if(obj.fufm != null) form.elements["fufm"].value = obj.fufm;
					if(obj.rema != null) form.elements["rema"].value = obj.rema;
				};
				ajax.post("loadDefault.do", postContent);

			}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<ui:page title="基板订购单登录">
	<f:page action="index.do" file="list.jsp">
	<input type="hidden" name="ids" />
		<table width="100%" class="form">
		<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="30%" /></colgroup>
		<tr>
			<th>订购单号</th>
			<td><ui:select name="pid" sql="#select a.ID_,a.JBNO_ from SINO_JBDD a order by a.JBNO_ desc" headerText="--请选择--" headerValue="" onchange="doQuery(this);"/> </td>
			<th>用户略称</th>
			<td><ui:input name="abbr" title="用户略称" /></td>
			<th>订单状态</th>
			<td><ui:select name="stat" list="'0':'初始','1':'已制作'" headerText="全部" headerValue="" value="#0"  /></td>
		</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="查 询" onclick="doQuery(this);" />
			<input type="button" class="button" value="新 增" onclick="toAdd();" />
			<input type="button" class="button" value="删 除" onclick="toDelete('');" />
			<input type="button" class="button" value="复 制 " onclick="toCopy('');" />
			<input type="button" class="button" value="打 印 " onclick="toPrint(this);" />
		</div>
	</f:page>
	</ui:page>
	<ui:panel id="Detail" title="基板订购单维护" popup="true" display="false" closable="true">
		<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
		<input type="hidden" name="$type" /><input type="hidden" name="id" /><input type="hidden" name="calc" /><input type="hidden" name="conf" /><input type="hidden" name="zjzt" /><input type="hidden" name="flag" />
		<table align="center" width="100%" class="form" >
		<colgroup><col width="10%" /><col width="12%" /><col width="9%" /><col width="10%" /><col width="10%" /><col width="11%" /><col width="10%" /><col width="10%" /><col width="9%" /><col width="9%" /></colgroup>
		<tr>
			<th style="text-align: center; vertical-align: middle;" colspan="2">用户略称</th>
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
			<td style="text-align: center; vertical-align: middle;" colspan="2"><ui:input name="abbr" title="用户略称" maxlength="24" onkeydown="if(window.event.keyCode==13) getDefault(); " /></td>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="face" title="表面" maxlength="2" onkeydown="if(window.event.keyCode==13) getDefault(); " /></td><td style="text-align: center; vertical-align: middle;"><ui:input name="yuny" title="运用规格" maxlength="6" onkeydown="if(window.event.keyCode==13) getDefault(); " /></td>
			<td style="text-align: center; vertical-align: middle;"><ui:number name="houa" title="厚度A" maxlength="5" scale="4" onkeydown="if(window.event.keyCode==13) getDefault(); " precision="3" onchange="calculate();" /></td><td style="text-align: center; vertical-align: middle;"><ui:number name="width" title="宽度" scale="6" precision="2" onkeydown="if(window.event.keyCode==13) getDefault(); " /></td>
			<td style="text-align: center; vertical-align: middle;"><ui:number name="houb" title="厚度B" maxlength="5" scale="4" precision="3" onchange="calculate();" onkeydown="if(window.event.keyCode==13) getDefault(); "  /></td><td style="text-align: center; vertical-align: middle;"><ui:number name="ajb" title="A-B" maxlength="6" scale="4" precision="3" onkeydown="if(window.event.keyCode==13) getDefault(); "/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:int name="dhsl" title="国内数量(吨)" scale="8" maxlength="4"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:int name="cksl" title="出口数量(吨)" scale="8" maxlength="4"/></td>
		</tr>
		<tr>
			<th style="text-align: center; vertical-align: middle;">紧急程度</th>
			<th style="text-align: center; vertical-align: middle;">输入编号</th>
			<th style="text-align: center; vertical-align: middle;" colspan="4">用途</th>
			<th style="text-align: center; vertical-align: middle;">代码</th>
			<th style="text-align: center; vertical-align: middle;">C/*</th>
			<th style="text-align: center; vertical-align: middle;" colspan="2">镀锡量</th>
		</tr>
		<tr>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="isgi" title="紧急程度" maxlength="4"/></td>
			<td style="text-align: center; vertical-align: middle;"><ui:input name="inpu" title="输入编号" maxlength="4"/></td>
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
		</table>
		<div class="submit"><input type="button" class="button" value="保 存" onclick="doSave(this);" />  <input type="button" class="button"value="关 闭" onclick="coco.hidePage('Detail')" /> </div>
		</form>
	</ui:panel>
	</body>
</html>