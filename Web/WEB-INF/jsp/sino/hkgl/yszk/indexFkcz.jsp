<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib prefix="sino" uri="/sino" %><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>付款冲帐管理</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["冲账", "edit.gif", "toCz()"]]);
			//冲帐操作
			function toCz(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key = coco.getAttr(oTr, "id0");
				var qForm = document.forms["PageForm_page"];
				var content;
				if(key != null && key.length > 0) {
					content = coco.parseParam("ids", key);
				} else {
					content = qForm.elements["zhip"].value;
				}
				if(content == null || content.length == 0){
					alert("请选择要冲账的记录");
					return;
				}
				var fpymc = qForm.elements["fpymc"].value;
				var postContent = coco.parseParam("fpymc", fpymc);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					ajax.dataDiv = document.getElementById("KhfkArea");
					ajax.callback = function() {
						if (this.code <= 0) {
							coco.alert(this.msg);
							return;
						}
						try {
							coco.showPage("Detail", {
								center : true,
								top : 120,
								width : "80%"
							});
							
						} catch (e) {
							alert("系统出错:\n" + e.description);
						}
					};
					ajax.post("toCz.do", postContent);
				};
				ajax.post("checkKhfk.do", postContent);
			}
			//复选框选中
			function doCheck(obj) {
				if(obj == null) {
					return;
				}
				//选中制品的制品号
				var v = obj.value;
				var wsyk =  parseFloat(obj.getAttribute("wsyk"));
				var oForm = document.forms["PageForm_page"];
				var hjyk = oForm.elements["hjyk"].value;
				var zhip = oForm.elements["zhip"].value;
				var rgExp;
				if(obj.checked) {
					if(zhip == null || zhip.length == 0) {
						oForm.elements["zhip"].value = v;
						oForm.elements["hjyk"].value = wsyk;
						oForm.elements["$hjyk"].value = formatMoney(oForm.elements["hjyk"].value);;
						return;
					}
					oForm.elements["zhip"].value = zhip.replace(/(,)+$/g,'') + ',' + v;
					oForm.elements["hjyk"].value = (parseFloat(hjyk) + wsyk).toFixed(2);
					oForm.elements["$hjyk"].value = formatMoney(oForm.elements["hjyk"].value);
				} else {
					rgExp = new RegExp(v+',|('+v+'$)' , "g");
					oForm.elements["zhip"].value = zhip.replace(rgExp, '');
					oForm.elements["hjyk"].value = (parseFloat(hjyk) - wsyk).toFixed(2);
					oForm.elements["$hjyk"].value = formatMoney(oForm.elements["hjyk"].value);
				}
			}
			//设置人民币格式
			function formatMoney(money) {
				if(money == null || money.length == 0) return;
				var len = money.indexOf("\.");
				var pn = money >= 0 ? true : false;
				var money1,money2;
				if(len < 0) {
					money1 = money.replace(/^-/gi,'');
					money2 = ".00";
				} else {
					money1 = pn ? money.substring(0, len) : money.substring(1, len);
					money2 = money.substring(len);
				}
				var symbol = pn ? '' : '-';
				var $money = '';
				var c;
				var num = 1;
				for(var i = money1.length - 1; i >= 0; i--) {
					c = money1.charAt(i);
					if(i > 0 && num % 3 == 0) {
						c = "," + c;
					}
					$money = c + $money;
					num++;
				}
				return symbol + $money + money2;
			}
			//全选或全不选
			function checkAll(obj) {
				var qForm = document.forms["PageForm_page"];
				var chks = qForm.elements["ids"];
				if(chks == null) return;
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
			//根据付款日期，获得客户的付款信息
			function changeKhfk(obj){
				if(obj == null) {
					return;
				}
				//选中制品的制品号
				var v = obj.value;
				var fkye1 =  parseFloat(obj.getAttribute("fkye"));
				var oForm = obj.form;
				var fkye2 = oForm.elements["fkye"].value;
				var khfkIdStr = oForm.elements["khfkIdStr"].value;
				var rgExp;
				if(obj.checked) {
					if(khfkIdStr == null || khfkIdStr.length == 0) {
						oForm.elements["khfkIdStr"].value = v;
						oForm.elements["fkye"].value = fkye1;
						oForm.elements["$fkye"].value = formatMoney(oForm.elements["fkye"].value);
						return;
					}
					oForm.elements["khfkIdStr"].value = khfkIdStr.replace(/(,)+$/g,'') + ',' + v;
					oForm.elements["fkye"].value = (parseFloat(fkye2) + fkye1).toFixed(2);
					oForm.elements["$fkye"].value = formatMoney(oForm.elements["fkye"].value);
				} else {
					rgExp = new RegExp(v+',|('+v+'$)' , "g");
					oForm.elements["khfkIdStr"].value = khfkIdStr.replace(rgExp, '');
					oForm.elements["fkye"].value = (parseFloat(fkye2) - fkye1).toFixed(2);
					oForm.elements["$fkye"].value = formatMoney(oForm.elements["fkye"].value);
				}
			//	if(crea == null || crea.length == 0) {
			//		alert("付款日期不能为空");
			//		ele1.focus();
			//		return;
			//	}
			//	var form = document.forms["DataForm"];
			//	var ele2 = form.elements["user"];
			//	var user = ele2.value;
			//	var ele3 = form.elements["fkye"];
			//	var ele4 = form.elements["id"];
			//	var ele5 = form.elements["$fkye"];
			//	var postContent = coco.parseParams([{name:"id",value:id}]);
			//	var ajax = new Cocoajax();
			//	ajax.callback = function() {
			//		if (this.code <= 0) {
			//			alert(this.msg);
			//			ele3.value = "";
			//			ele4.value = "";
			//			ele5.value = "";
			//			return;
			//		}
			//		try {
			//			var ignores = ["radio"];
			//			cocoform.clear(form, ignores);
			//			cocoform.fillResult(form, this.result,null,true);
			//			ele5.value = formatMoney(ele3.value);
			//			coco.showPage("Detail", {
			//				center : true,
			//				top : 120,
			//				width : "80%"
			//			});
			//			
			//	} catch (e) {
			//			alert("系统出错:\n" + e.description);
			//		}
			//	};
			//	ajax.post("getKhfk.do", postContent);
			}
			//查询
			function doQuery(oInput) {
				var oForm = oInput.form;
				oForm.elements["zhip"].value = "";
				oForm.elements["hjyk"].value = "";
				oForm.elements["$hjyk"].value = "";
				var ele1 = oForm.elements["chriBegin"];
				var chriBegin = ele1.value;
			///	if(chriBegin == null || chriBegin.length == 0) {
			//		alert("出货日期起始时间为空");
			//		ele1.focus();
			//		return;
			//	}
			//	var ele2 = oForm.elements["chriEnd"];
			//	var chriEnd = ele2.value;
			//	if(chriEnd == null || chriEnd.length == 0) {
			//		alert("出货日期结束时间为空");
			//		ele2.focus();
			//		return;
			//	}
				var ele3 = oForm.elements["fpymc"];
				var fpymc = ele3.value;
				if(fpymc == null || fpymc.length == 0) {
					alert("请选择要冲帐的用户");
					return;
				}
				cocopage.query();
			}
		
			//自动设置复选框的checked值
			function setChecked() {
				var qForm = document.forms["PageForm_page"];
				var zhip = qForm.elements["zhip"].value;
				if(zhip == null || zhip.length == 0) {
					return;
				}
				var chks = qForm.elements["ids"];
				if(chks == null) return;
				if(chks.tagName != null) {
					if(zhip.indexOf(chks.value) >=0) {
						chks.checked = true;
					}
				} else {
					var el, elIndex=0;
					while((el = chks[elIndex++]) != null) {
						if(zhip.indexOf(el.value) >=0) {
							el.checked = true;
						}
					}
				}
			}
			//提交
			function doSubmit(oInput) {
				var qForm = document.forms["PageForm_page"];
				var form = oInput.form;
			//	var ele1 = form.elements["crea"];
			//	var crea = ele1.value;
			//	if(crea == null || crea.length == 0) {
			//		alert("付款日期不能为空");
			//		ele1.focus();
			//		return;
			//	}
			//	var id = form.elements["id"].value;
			//	if(id == null || id.length == 0) {
			//		alert("该客户在" + crea + "号未付款,因此不能做冲帐操作");
			//		ele1.focus();
			//		return;
			//	}
				var ele2 = form.elements["fkye"];
				var fkye = ele2.value;
				if(fkye == null || fkye.length == 0 || fkye == 0) {
					alert("未选择客户付款记录,因此不能做冲帐操作");
					return;
				}
				var ele3 = form.elements["idStr"];
				ele3.value = qForm.elements["zhip"].value;
				cocoform.submit(form, function() {
						if(this.code <= 0) {
							coco.alert(this.msg);
							return;
						}
						alert("冲帐成功");
						coco.hidePage('Detail');
						
						qForm.elements["zhip"].value = "";
						qForm.elements["hjyk"].value = "";
						qForm.elements["$hjyk"].value = "";
						cocopage.query();
					}, "是否确定冲帐?", null, oInput);
			} 
			//排序
			function setOrders(v) {
				if(v == null) return;
				var qForm = document.forms["PageForm_page"];
				qForm.elements["chriOrder"].value = v;
				cocopage.query("page", setChecked);
			}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="付款冲帐管理">
		<f:page action="indexFkcz.do" file="listFkcz.jsp">
			<input type="hidden" name="zhip" /><input type="hidden" name="chriOrder" value="asc" /><input type="hidden" name="hjyk" />
			<table class="form" width="96%">
				<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="30%" /><col width="20%" /></colgroup>
				<tr>
					<th style="text-align: left;">出货日期</th>
					<td><ui:date name="chriBegin" showCalendar="true" />至<ui:date name="chriEnd" showCalendar="true" /></td>
					<th style="text-align: left;">发票客户</th>
					<td><ui:combobox name="fpymc" items="${fpkhs }" width="300" /></td>
					<th>发票品种&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="fppz" list="${fppz }" /></th>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
					<th style="text-align: left;">合计余款</th>
					<td><ui:input name="$hjyk" maxlength="18" title="合计余款" readonly="true" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<div class="submit" >
				<input type="button" onclick="doQuery(this);" value="查  询" class="button" /> 
				<input type="button" onclick="toCz('');" value="冲  账" class="button" />
			</div>
		</f:page>
	</ui:page>
	<ui:panel id="Detail" title="客户付款信息" popup="true" display="false" closable="true">
		<form name="DataForm" method="post" action="doCz.do" >
			<div id="KhfkArea" ></div>
		</form>
	</ui:panel>
	</body>
</html>