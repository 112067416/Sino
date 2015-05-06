<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>捆包实绩</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		var tf = new TableForm("DataTbl");
		//新增一行
		function doAdd(o) {
			var row = tf.insertRow(document.getElementById("HidDataTbl").rows[0]);
			var oDataTbl = document.getElementById("DataTbl");
			var newRow = oDataTbl.rows[oDataTbl.rows.length - 1];
			var jbno = o.jbno != null ? o.jbno : "";
			var zzsd = o.zzsd != null ? o.zzsd : "";
			var plqf = o.plqf != null ? o.plqf : "";
			var dhno = o.dhno != null ? o.dhno : "";
			var abbr = o.abbr != null ? o.abbr : "";
			var houu = o.houu != null ? o.houu : "";
			var kuan = o.kuan != null ? o.kuan : "";
			var cang = o.cang != null && o.cang > 0 ? o.cang : "COIL";
			var kbao = o.kbao != null ? o.kbao : "";
			var deng = o.deng != null ? o.deng : "";
			var ztbj = o.ztbj != null ? o.ztbj : "";
			var sczm = o.sczm != null ? o.sczm : "";
			var scfm = o.scfm != null ? o.scfm : "";
			var chdx = o.chdx != null ? o.chdx : "";
			newRow.cells[0].childNodes[0].value = jbno;
			newRow.cells[1].innerHTML= plqf + zzsd + "-" + jbno;
			newRow.cells[2].innerHTML = dhno;
			newRow.cells[3].innerHTML = abbr;
			newRow.cells[4].innerHTML = houu + "*" + kuan + "*" + cang;
			newRow.cells[5].innerHTML = kbao;
			newRow.cells[6].innerHTML = deng;
			newRow.cells[7].innerHTML = ztbj;
			newRow.cells[8].innerHTML = sczm+'/'+scfm;
			var el;
			if(chdx != null && chdx.length > 0) {
				el = "<select name=\"mark\" class=\"normal\"><option value=\"0\">未确认</option><option value=\"1\">已确认</option></select>";
			} else {
				el = "<input type=\"hidden\" name=\"mark\" value=\"1\">";
			}
			newRow.cells[9].innerHTML = el; 
			document.getElementById("jbno").value = "";
		}
		//捆包录入
		function getKb(oInput) {
			var jbno = oInput.value;
			if(jbno == null || jbno.length == 0){
				alert("PILE No.不能为空");
				oInput.focus();
				return ;
			}
			var oForm = document.forms["DataForm"];
			var jbnos = oForm.elements["jbnos"].value;
			var duic = oForm.elements["duic"].value;
			if(jbnos != null && jbnos.indexOf(jbno) >= 0) {
				alert("PILE No." + jbno + "已登录");
				oInput.value = "";
				oInput.focus();
				return;
			}
			var postContent = coco.parseParams([{name:"jbno",value:jbno},{name:"duic",value:duic}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						oInput.value = "";
						oInput.focus();
						return;
					}
					if(jbnos == null || jbnos.length == 0) {
						jbnos = jbno;
					} else {
						jbnos = jbnos + "," + jbno;
					}
					oForm.elements["jbnos"].value = jbnos;
					var obj = null;
					eval("obj = " + this.result + ";");
					doAdd(obj);
					oInput.focus();
			};
			ajax.post("getKb.do", postContent);
		}
		//页面加载
		function doLoad() {
			document.getElementById("jbno").focus();
		}
		//返回
		function doReturn() {
			var oForm = document.forms["DataForm"];
			oForm.elements["jbnos"].value = '';
			tf.removeAll();
			var obj = document.getElementById("jbno");
			obj.value = '';
			obj.focus();
		}
		//选择清除的内容id
		function getCheckValues() {
			var form = document.forms["DataForm"];
			var oChks = form.elements["ids"];
			if(oChks == null) return null;
			var content = "";
			if(oChks.length == null) {
				if(oChks.checked) content = oChks.value;
			}
			else {
				for(var i = 0; i < oChks.length; i++) {
					if(oChks[i].checked) {
						if(content.length > 0) content += ",";
						content += oChks[i].value;
					}
				}
			}
			return content;	                   					
		}
		// 移除选中记录
		function doRemove() {
			var oForm = document.forms["DataForm"];
			var jbnos = oForm.elements["jbnos"].value;
			var chks = oForm.elements["ids"];
			if(chks == null) {
				alert("请选择移除的行");
				return;
			}
			var rgExp;
			if(chks.tagName != null) {
				if(chks.checked) {
					rgExp = new RegExp(chks.value+',|('+chks.value+'$)' , "g");
					oForm.elements["jbnos"].value = jbnos.replace(rgExp, '');
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(el.checked) {
						rgExp = new RegExp(el.value+',|('+el.value+'$)' , "g");
						jbnos = jbnos.replace(rgExp, '');
					}
				}
				oForm.elements["jbnos"].value = jbnos;
			}
			if(!confirm("确定移除该行吗?")) return;
			tf.removeRow("ids");
		}
		//提交确认捆包并打印标签
		function doSubmit(oInput){
			var oForm = document.forms["DataForm"];
			var jbnoStr = oForm.elements["jbnos"].value;
			if(jbnoStr == null || jbnoStr.length == 0) {
				alert("请输入要做捆包操作的制品");
				return;
			}
			var duic = oForm.elements["duic"].value;
			var marks = oForm.elements["mark"];
			if(marks.tagName != null) {
				if(marks.value != "1") {
					alert("存在MARK标记还未确认的记录,请确认");
					return;
				}
			} else {
				if(marks != null && marks.length > 0) {
					for(var i = 0; i < marks.length; i++) {
						if(marks[i].value != "1") {
							alert("存在MARK标记还未确认的记录,请确认");
							return;
						}
					}
				}
			}
			var bz = oForm.elements["bz"].value;
			postContent = coco.parseParams([{name:"jbnos",value:jbnoStr},{name:"bz",value:bz},{name:"duic",value:duic}]);
			var ajax = new Cocoajax();
			ajax.oInput = oInput;
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				if(duic == "F") {
					doPrint(jbnoStr);
				}
				doReturn();
			};
			ajax.post("saveKbsj.do", postContent);
		}
		//
		function doPrint(jbnoStr) {
			var jbnos = jbnoStr.split(",");;
			var size = jbnos.length;
			var index = 0;
			var ajax = new Cocoajax();
			var LODOP = document.getElementById("lodop");
			ajax.callback = function() {
				if(this.code < 0) coco.alert(this.msg);
				else print(LODOP, this.result);
				if(index < size) this.post(path + "/sino/dy/zpbq!print.do", coco.parseParam("jbno", jbnos[index++]));
			};
			ajax.post(path + "/sino/dy/zpbq!print.do", coco.parseParam("jbno", jbnos[index++]));
		}

		function print(LODOP, result) {
			LODOP.PRINT_INIT("ZPBQ");
			LODOP.SET_PRINTER_INDEXA("ZPBQ");
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(2, -13, 1100, 760, result);
			//ADD_PRINT_BARCODE(Top, Left,Width, Height, CodeType, CodeValue)
			var start = result.indexOf('<table no="');
			var end = result.indexOf('" ');
			var no1 = null, no2 = null;
			if(start != -1 && end != -1) {
				var nos = result.substring(start + 11, end).split(";");
				if(nos[0] != "") no1 = nos[0];
				if(nos[1] != "") no2 = nos[1];
			}
			if(no1 != null) {
				LODOP.ADD_PRINT_BARCODE("230px", "300px", "200px", "60px", "code39", no1);
				LODOP.ADD_PRINT_BARCODE("245px", "800px", "200px", "45px", "code39", no1);
				LODOP.ADD_PRINT_BARCODE("625px", "300px", "200px", "60px", "code39", no1);
				LODOP.ADD_PRINT_BARCODE("635px", "800px", "200px", "45px", "code39", no1);
			}
			if(no2 != null) {
				LODOP.ADD_PRINT_BARCODE("320px", "800px", "200px", "45px", "code39", no2);
				LODOP.ADD_PRINT_BARCODE("710px", "800px", "200px", "45px", "code39", no2);
			}
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}
		//-->
		</script>
	</head>
	<ui:print id="lodop"/>
	<body onload="doLoad();">
	 <ui:page title="捆包实绩录入">
	 	<table width="100%" class="form">
			<colgroup><col width="15%" /><col width="85%" /></colgroup>
			<tr>
				<th style="text-align: left;">PILE No.</th>
				<td><ui:input name="jbno" id="jbno" maxlength="11" onkeydown="if(window.event.keyCode == 13) getKb(this);" /></td>
			</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="移 除" onclick="doRemove()" />
		<input type="button" class="button" value="确认捆包并打印标签" onclick="doSubmit(this);" />
		<input type="button" class="button" value="返 回" onclick="doReturn();" />
		</div>
		<form name="DataForm" method="post" action="#" >
		<input type="hidden" name="jbnos" /><input type="hidden" name="duic" value="${duic }" />
		<table id="DataTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
		<colgroup><col width="4%"/><col width="10%"/><col width="10%"/><col width="16%"/><col width="16%"/><col width="10%"/><col width="7%"/><col width="7%"/><col width="10%"/><col width="10%"/></colgroup>
		<tr class="page-head" align="center">
			<th><input type="checkbox" xu.target="ids" /></th>
			<th>PILE No.</th>
			<th>订货No.</th>
			<th>用户略称</th>
			<th>制品尺寸 </th>
			<th>捆包形式 </th>
			<th>等级 </th>
			<th>停止</th>
			<th>镀锡量 </th>
			<th>MARK确认</th>
		</tr>
		</table>
		<table width="100%" class="form">
			<colgroup><col width="15%" /><col width="65%" /><col width="10%" /><col width="10%" /></colgroup>
			<tr>
				<th colspan="2">&nbsp;</th>
				<th width="10%">当前人班组</th>
				<td align="left"><ui:select name="bz" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'" value="group" headerText="请选择" headerValue="" prop="nn:1;"/></td>
			</tr>
		</table>
		</form>
		<table id="HidDataTbl" style="display: none;">
			<tr>
				<td><input type="checkbox" name="ids" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		</ui:page>
	</body>
</html>