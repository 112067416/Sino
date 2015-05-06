<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>付款发票管理</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<style type="text/css">
		TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
		TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
		TABLE.pagination1 th { font-size: 12px; color: #003449; height: 30px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
		TABLE.pagination1 td { font-size: 13px; color: #333333; height: 25px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
		TABLE.pagination1 tr {background-color: #F7F7F7;}
		TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
		TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
		</style>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["分解", "edit.gif", "toDivied()"]]);
			var fpmxTbl = new TableForm("FpmxTbl");
			//设置发票信息
			function saveFp() {
				fpmxTbl.formatTblForm("items",2);
				var oForm = document.forms["PageForm_page"];
				if(!cocoform.verify(oForm)) return;
				if(!confirm("是否确定保存?")) return;
				oForm.action = "saveFp.do";
			    var qForm = document.forms["PageForm_page"];
				cocoform.submit(oForm, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						qForm.elements["ids"].value = "";
						oForm.action = "indexFpbj.do";
						cocopage.refresh();
					});
			}
			//调整及修改提交
			function doSubmit(oInput) {
				var oForm = oInput.form;
		 		var qForm = document.forms["PageForm_page"];
				cocoform.submit(oForm, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						qForm.elements["ids"].value = "";
						coco.hidePage('Detail');
						cocopage.refresh();
						return;
					}, "是否确定保存?", null, oInput);
			}
			//发票调整
			function toAdd() {
				var form = document.forms["DataForm"];
				coco.showPage("Detail", {
					center : true,
					top : 70,
					width : "90%"
				});
			}
			//获取修改信息
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key = coco.getAttr(oTr, "id0");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					doLoad(key);
				};
				ajax.post("doCheck.do", "id="+key);
			}
			//
			function doLoad(key) {
				var ajax = new Cocoajax();
				ajax.dataDiv = EditFpArea;
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					coco.showPage("Detail", {center : true, top : 90, width : "90%" });
				};
				ajax.post("load.do", "id="+key);
			}
			
			//计算总价
			function calculate(oInput) {
				var oForm = oInput.form;
				var kfzl = oForm.elements["kfzl"].value;
				kfzl = kfzl != null && kfzl.length > 0 ? kfzl : 0;
				if(kfzl <= 0) {
					alert("吨数输入有误");
					oForm.elements["kfzl"].focus();
					return;
				}
				var kpdj = oForm.elements["kpdj"].value;
				kpdj = kpdj != null && kpdj.length > 0 ? kpdj : 0;
				var lxzr = oForm.elements["lxzr"].value;
				lxzr = lxzr != null && lxzr.length > 0 ? lxzr : 0;
				var zlzr = oForm.elements["zlzr"].value;
				zlzr = zlzr != null && zlzr.length > 0 ? zlzr : 0;
				var postContent = coco.parseParams([{name:"kfzl",value:kfzl},{name:"kpdj",value:kpdj},{name:"lxzr",value:lxzr},{name:"zlzr",value:zlzr}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					var obj = null;
					eval("obj="+this.result+";");
					oForm.elements["fpje"].value = obj.fpje;
				};
				ajax.post("calculate.do", postContent);
			}
			
			//生成发票数据
			function outFpDatas(oInput) {
				var oForm = oInput.form;
				var chriBegin = oForm.elements["chriBegin"].value;
				if(chriBegin == null || chriBegin.length == 0) {
					alert("出货日期起始时间不能为空");
					return;
				}
				var chriEnd = oForm.elements["chriEnd"].value;
				if(chriEnd == null || chriEnd.length == 0) {
					alert("出货日期结束时间不能为空");
					return;
				}
				if(!confirm("是否确定生成发票数据?")) return;
				var postContent = coco.parseParams([{name:"chriBegin",value:chriBegin},{name:"chriEnd",value:chriEnd}]);
				coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
				var ajax = new Cocoajax();
				ajax.oInput = oInput;
				ajax.callback = function() {
					coco.hideAlert();
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("生成数据成功");
					cocopage.refresh();
				};
				ajax.post("outFpDatas.do", postContent);
			}
			//进入排序页面
			function toOrders() {
				coco.showPage("OrderDetail", { center : true, top : 150, width : "60%" });
			}
			//排序
			function setOrders(obj) {
				if(obj == null) return;
				var qForm = document.forms["PageForm_page"];
				qForm.elements[obj.name].value = obj.value;
				cocopage.query();
			}
			//修改
			function toDivied(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "id0");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					try {
						var form = document.forms["DiviedForm"];
						cocoform.clear(form);
						cocoform.fillResult(form, this.result);
						coco.showPage("DiviedDetail", {center : true, top : 90, width : "90%" });
					} catch (e) {
						alert("系统出错:\n" + e.description);
					}
				};
				ajax.post("toDivied.do", "id="+key1);
			}
			//保存分解信息
			function saveDivied(oInput) {
				var oForm = oInput.form;
			 	var qForm = document.forms["PageForm_page"];
				cocoform.submit(oForm, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						coco.hidePage('DiviedDetail');
						qForm.elements["ids"].value = "";
						cocopage.refresh();
						return;
					}, "是否确定保存?", null, oInput);
			}
			//进入设置发票参数的页面
			function toFpcs(el) {
				var qForm = el.form;
				var ids = qForm.elements["ids"].value;
				if(ids == null || ids.length == 0){
					alert("请选择要设置发票参数的记录");
					return;
				}
				var oForm = document.forms["FpcsForm"];
				oForm.elements["ids"].value = ids;
				coco.showPage("FpcsDetail", {center : true, top : 90, width : "90%" });
			}
			//保存设置发票参数
			function setFpcs(obj) {
				var form = obj.form;
				var fpno = form.elements["fpno"].value;
				var fpymc = form.elements["fpymc"].value;
				var kpdj = form.elements["kpdj"].value;
				if((fpno == null || fpno.length == 0) && (fpymc == null || fpymc.length == 0) && (kpdj == null || kpdj.length == 0)) {
					alert("发票公共参数不能为空");
					return;
				}
				var ids = form.elements["ids"].value;
				var postContent = coco.parseParams([{name:"fpno",value:fpno},{name:"ids",value:ids},{name:"fpymc",value:fpymc},{name:"kpdj",value:kpdj}]);
			    if(!confirm("确定设置发票公共参数吗?")) return;
			    var qForm = document.forms["PageForm_page"];
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("操作成功");
					form.elements["ids"].value = "";
					qForm.elements["ids"].value = "";
					cocopage.refresh();
					coco.hidePage("FpcsDetail");
				};
				ajax.post("setFpcs.do", postContent);
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
				var chks = oForm.elements["chk"];
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
				var chks = oForm.elements["chk"];
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
			//生成红字发票
			function toHzfp(el) {
				var form = el.form;
				var ids = form.elements["ids"].value;
				if(ids == null || ids.length == 0){
					alert("请选择要设置红字发票的记录");
					return;
				}
				if(!confirm("是否确定生成红字发票?")) return;
				var postContent = coco.parseParam("ids", ids);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("操作成功");
					form.elements["ids"].value = "";
					cocopage.refresh();
				};
				ajax.post("outHzfp.do", postContent);
			}

			//生成蓝字发票
			function toLzfp(el) {
				var form = el.form;
				var ids = form.elements["ids"].value;
				if(ids == null || ids.length == 0){
					alert("请选择要设置蓝字发票的记录");
					return;
				}
				if(!confirm("是否确定生成蓝字发票?")) return;
				var postContent = coco.parseParam("ids", ids);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("操作成功");
					form.elements["ids"].value = "";
					cocopage.refresh();
				};
				ajax.post("outLzfp.do", postContent);
			}
			//-->
		</SCRIPT>
	</head>
	<body>
<ui:page title="付款发票管理">
<f:page action="indexFpbj.do" file="listFpbj.jsp">
<input type="hidden" name="chriOrder" value="asc" /><input type="hidden" name="fpdmOrder" value="asc" /><input type="hidden" name="ids" />
<table class="form" width="100%">
<colgroup><col width="10%" /><col width="25%" /><col width="10%" /><col width="20%" /><col width="6%" /><col width="11%" /><col width="6%" /><col width="12%" /></colgroup>
<tr>
<th style="text-align: left;">出货日期</th>
<td><ui:date name="chriBegin" required="true" />至<ui:date name="chriEnd" required="true" /></td>
<th style="text-align: left;">发票客户</th>
<td colspan="3"><ui:combobox name="fpymc" items="${fpkhs }" width="300" /></td>
<th style="text-align: left;">发票号</th>
<td><ui:input name="eqFpno" title="发票号" maxlength="10" /></td>
</tr>
<tr>
<th style="text-align: left;">尺寸.厚</th>
<td><ui:number name="houuS" maxlength="5"  scale="4" precision="3" title="大于尺寸.宽" positive="true" />至<ui:number name="houuE" maxlength="5"  scale="4" precision="3" title="小于尺寸.宽" positive="true" /></td>
<th style="text-align: left;">内外销</th>
<td><ui:select name="nwai" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='NWX'" headerText="" headerValue="" /></td>
<th style="text-align: left;">品种</th>
<td><ui:select name="pz" list="${pz }" headerText="" headerValue="" /></td>
<th style="text-align: left;">等级</th>
<td><ui:select name="deng" list="${deng }" headerText="" headerValue="" /></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="doQuery(this)" />
<!-- 
<input type="button" class="button" value="发票调整" onclick="toAdd()" />
<input type="button" class="button" value="生成发票数据" onclick="outFpDatas(this)" />
 -->
<input type="button" class="button" value="保 存" onclick="saveFp();" />
<input type="button" class="button" value="设置排序" onclick="toOrders()" />
<input type="button" class="button" value="设置发票公共参数" onclick="toFpcs(this)" />
<input type="button" class="button" value="设置红字发票" onclick="toHzfp(this)" />
<input type="button" class="button" value="设置蓝字发票" onclick="toLzfp(this)" />
</div>
</f:page>
</ui:page>
<ui:panel id="OrderDetail" title="设置排序" popup="true" display="false" closable="true">
<form action="#" name="OrderForm" method="post" >
<table width="100%" class="form">
<colgroup><col width="20%" /><col width="25%" /><col width="20%" /><col width="25%" /><col width="10%" /></colgroup>
<tr>
<th>出货日期</th>
<td><ui:radiolist name="chriOrder" list="'asc':'升序','desc':'降序'" value="#asc" onclick="setOrders(this);" /></td>
<th>发票客户</th>
<td><ui:radiolist name="fpdmOrder" list="'asc':'升序','desc':'降序'" value="#asc" onclick="setOrders(this);" /></td>
<td>&nbsp;</td>
</tr>
</table>
<div class="submit"><input type="button" class="button" value="关  闭" onclick="coco.hidePage('OrderDetail');" /></div>
</form>
</ui:panel>
<ui:panel id="Detail" title="编辑发票" popup="true" display="false" closable="true">
<form name="DataForm" method="post" action="editFp.do" >
<div id="EditFpArea" ></div>
</form>
</ui:panel>
<ui:panel id="FpcsDetail" title="设置发票公共参数" popup="true" display="false" closable="true">
<form name="FpcsForm" method="post" action="#" >
<input type="hidden" name="ids" />
<table width="100%" class="form" >
<colgroup><col width="10%" /><col width="35%" /><col width="10%" /><col width="10%" /><col width="35%" /></colgroup>
<tr>
<th style="text-align: left;">用户名称</th>
<td><ui:combobox name="fpymc" items="${fpkhs }" width="300" /></td>
<td colspan="3">&nbsp;</td>
</tr>
<tr>
<th style="text-align: left;">发票号</th>
<td><ui:input name="fpno" title="发票号" maxlength="10" /></td>
<th style="text-align: left;">发票单价</th>
<td><ui:number name="kpdj" scale="9" precision="4" maxlength="10" title="发票单价" /></td>
<td>&nbsp;</td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="保   存" onclick="setFpcs(this);" />
<input type="button" class="button" value="关   闭" onclick="coco.hidePage('FpcsDetail')" />
</div>
</form>
</ui:panel>
<ui:panel id="DiviedDetail" title="分解付款发票" popup="true" display="false" closable="true">
<form name="DiviedForm"  method="post" action="saveDivied.do" >
<input type="hidden" name="id"/>
<table width="100%" class="form" >
<colgroup><col width="10%" /><col width="35%" /><col width="10%" /><col width="10%" /><col width="35%" /></colgroup>
<tr>
<th style="text-align: left;">用户名称</th>
<td><ui:combobox name="fpymc" items="${fpkhs }" width="300" /></td>
<td colspan="3">&nbsp;</td>
</tr>
</table><br /><br />
<table id="DataTbl" class="form" border="1" bordercolor="#ffffff" width="100%" style="text-align: center; border-collapse: collapse;">
<colgroup><col width="17%"/><col width="17%"/><col width="17%"/><col width="15%"/><col width="16%"/><col width="18%"/></colgroup>
<tr>
<th nowrap="nowrap" style="text-align: center;">吨数</th>
<th nowrap="nowrap" style="text-align: center;">发票号</th>
<th nowrap="nowrap" style="text-align: center;">发票单价</th>
<th nowrap="nowrap" style="text-align: center;">利息折扣</th>
<th nowrap="nowrap" style="text-align: center;">质量折扣</th>
<th nowrap="nowrap" style="text-align: center;">算出总价</th>
</tr>
<tr>
<td nowrap="nowrap"><ui:number name="kfzl" scale="7" precision="3" title="开票吨数" required="true" onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:input name="fpno" title="发票号" maxlength="10" /></td>
<td nowrap="nowrap"><ui:number name="kpdj" scale="9" precision="4" maxlength="10" title="发票单价" required="true" onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:number name="lxzr" scale="8" precision="2" maxlength="9" title="利息折扣" onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:number name="zlzr" scale="8" precision="2" maxlength="9" title="质量折扣"  onchange="calculate(this);"/></td>
<td nowrap="nowrap"><ui:number name="fpje" maxlength="12" scale="11" precision="2" title="发票金额" required="true" readonly="true"/></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="保   存" onclick="saveDivied(this);" />
<input type="button" class="button" value="关   闭" onclick="coco.hidePage('DiviedDetail')" />
</div>
</form>
</ui:panel>
</body>
</html>