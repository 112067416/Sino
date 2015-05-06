<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>原板标签打印</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<SCRIPT type="text/javascript">
		<!--
		
		function printForShip() {
			var form = document.forms["PageForm_page"];
			var ship = form.elements["ship"];
			if(ship.value == "") {
				alert("请输入船名");
				ship.focus();
				return false;
			}
			if(!window.confirm("确定要整船打印吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code < 0) {
					coco.alert(this.msg);
					return false;
				}
				toPrint(eval(this.result));
			};
			ajax.post("ycai!ship.do", coco.parseParam("ship", ship.value));
		}
		
		function printForSelect() {
			var form = document.forms["PageForm_page"];
			var idStr = form.elements["ids"].value;
			if(idStr == null || idStr.length == 0) {
				alert("至少要选择一个原材卷板");
				return;
			}
			var ids = idStr.split(",");
			if(!window.confirm("确定要打印已经选择的项目吗?")) return false;
			toPrint(ids);
		}
		
		function toPrint(ids) {
			if(ids == null || ids.length == 0) return;
			var size = ids.length;
			var index = 0;
			var ajax = new Cocoajax();
			var LODOP = document.getElementById("lodop");
			var no1=null, no2=null;
			ajax.callback = function() {
				if(this.code < 0) coco.alert(this.msg); 
				else doPrint(LODOP, this.result, no1, no2);
				if(index < size) {
					no1 = ids[index++];
					no2 = null;
					var content = coco.parseParam("jbno", no1);
					if(index < size) {
						no2 = ids[index++];
						content += "&" + coco.parseParam("jbno", no2);
					}
					this.post("ycai!print.do", content);
				}
			};
			if(index < size) {
				no1 = ids[index++];
				no2 = null;
				var content = coco.parseParam("jbno", no1);
				if(index < size) {
					no2 = ids[index++];
					content += "&" + coco.parseParam("jbno", no2);
				}
				ajax.post("ycai!print.do", content);
			}
		}
		
		function doPrint(LODOP, content, no1, no2) {
			LODOP.PRINT_INIT("YCBQ");
			LODOP.SET_PRINTER_INDEXA("ZPBQ");
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(2, -13, 1100, 760, content);
			//ADD_PRINT_BARCODE(Top, Left,Width, Height, CodeType, CodeValue)
			if(no1 != null) {
				//卷板1的左边条形码
				LODOP.ADD_PRINT_BARCODE("28px", "250px", "200px", "60px", "code39", no1);
				//卷板1的右边条形码
				LODOP.ADD_PRINT_BARCODE("18px", "900px", "200px", "45px", "code39", no1);
			}
			if(no2 != null) {
				//卷板1的左边条形码
				LODOP.ADD_PRINT_BARCODE("422px", "250px", "200px", "60px", "code39", no2);
				//卷板1的右边条形码
				LODOP.ADD_PRINT_BARCODE("410px", "900px", "200px", "45px", "code39", no2);
			}
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();
		}

		//复选框选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
			//选中订货同号
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
				rgExp = new RegExp(v+',|('+v+'$)' , "g");
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

		function doQuery(oForm) {
			var el = oForm.elements["ids"];
			el.value = "";
			cocopage.query('page');
		}
		//-->
		</SCRIPT>
	</head>
	<body >
<ui:print id="lodop" />
	<ui:page title="原板标签打印">
	<f:page action="ycai.do" file="ycai!list.jsp">
			<input type="hidden" name="ids" />
			<table width="96%" class="form">
				<colgroup>
					<col width="10%" />
					<col width="24%" />
					<col width="18%" />
					<col width="22%" />
					<col width="16%" />
					<col width="10%" />
				</colgroup>
				<tr>
					<th style="text-align:left;">船名</th>
					<td><ui:input name="ship" maxlength="20" /></td>
					<th style="text-align:left;">原材卷板NO</th>
					<td><ui:input name="coilNoS" maxlength="7"/>至<ui:input name="coilNoE" maxlength="7"/></td>
					<th style="text-align:left;">是否已打印</th>
					<td><ui:select name="sfdy" list="'':'全部','N':'否','Y':'是'" /></td>
				</tr>
				<tr>
				</tr>
			</table>
			<div class="submit">
				<input type="button" value="查 询" class="button" onclick="doQuery(this.form);" />
				<input type="button" value="整船打印" class="button" onclick="printForShip();" />
				<input type="button" value="选择打印" class="button" onclick="printForSelect();" />
			</div>
		</f:page>
		<br/>
		<div>
			<input type="button" value="选择打印" class="button" onclick="printForSelect();" />
		</div>
	</ui:page>
	</body>
</html>