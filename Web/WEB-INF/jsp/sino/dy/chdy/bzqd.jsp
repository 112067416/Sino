<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>制作包装清单</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		//复选框选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
			//选中制品的制品号
			var v = obj.getAttribute("no");
			var m =  parseInt(obj.getAttribute("mazl"));
			//选中制品的重量
			var z = parseInt(obj.value);
			var oForm = document.forms["DataForm"];
			var chzl = oForm.elements["chzl"].value;
			var mazl = oForm.elements["mazl"].value;
			var zhip = oForm.elements["zhip"].value;
			var chsu = oForm.elements["chsu"].value;
			var rgExp;
			if(obj.checked) {
				if(zhip == null || zhip.length == 0) {
					oForm.elements["zhip"].value = v;
					oForm.elements["chzl"].value = parseFloat(z / 1000.0).toFixed(3);
					oForm.elements["mazl"].value = parseFloat(m / 1000.0).toFixed(3);
					oForm.elements["chsu"].value = 1;
					return;
				}
				oForm.elements["zhip"].value = zhip.replace(/(,)+$/g,'') + ',' + v;
				oForm.elements["chzl"].value = (parseFloat(chzl) + parseFloat(z / 1000.0)).toFixed(3);
				oForm.elements["mazl"].value = (parseFloat(mazl) + parseFloat(m / 1000.0)).toFixed(3);
				oForm.elements["chsu"].value=parseInt(chsu)+1;
			} else {
				rgExp = new RegExp(v+',|('+v+'$)' , "g");
				oForm.elements["zhip"].value = zhip.replace(rgExp, '');
				oForm.elements["chzl"].value = (parseFloat(chzl) - parseFloat(z / 1000.0)).toFixed(3);
				oForm.elements["mazl"].value = (parseFloat(mazl) - parseFloat(m / 1000.0)).toFixed(3);
				oForm.elements["chsu"].value -= 1;
			}
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
		//自动设置复选框的checked值
		function setChecked() {
			var oForm = document.forms["DataForm"];
			var zhip = oForm.elements["zhip"].value;
			if(zhip == null || zhip.length == 0) {
				return;
			}
			var qForm = document.forms["PageForm_page"];
			var chks = qForm.elements["ids"];
			if(chks == null) return;
			if(chks.tagName != null) {
				if(zhip.indexOf(chks.getAttribute("no")) >=0) {
					chks.checked = true;
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(zhip.indexOf(el.getAttribute("no")) >=0) {
						el.checked = true;
					}
				}
			}
		}
		//打印
	    function toPrint() {
			var oForm = document.forms["DataForm"];
			var size = oForm.elements["size"].value;
			var zhip = oForm.elements["zhip"].value.replace(/^\s+|\s+$/gi,'');
			if(zhip == "" || zhip.length == 0){
				 alert("请选择制品");
				 return;
			}
			var zxno = oForm.elements["zxno"].value;
			var dhno = oForm.elements["dhno"].value;
			var line = oForm.elements["line"].value;
			var pm = oForm.elements["pm"].value;
			var hjzl = oForm.elements["chzl"].value;
			var hjmz = oForm.elements["mazl"].value;
			var chsu = oForm.elements["chsu"].value;
			var jbnos = zhip.split(",");
			var $pJbnos = null,postContent;
			var pageSize = (jbnos.length % size == 0 ? parseInt(jbnos.length / size) : parseInt(jbnos.length / size) + 1);
			var pJbnos = [],index = 0;
 			for(var i = 0; i < pageSize; i++) {
 				$pJbnos = null;
				for(var j = i*size; j < (i + 1)*size; j++) {
					if(j >= jbnos.length) break;
					if($pJbnos == null) {
						$pJbnos = jbnos[j];
						continue;
					}
					$pJbnos += (',' + jbnos[j]);
				}
				pJbnos[i] = $pJbnos;
			}
 			var LODOP = document.getElementById("lodop");
 			postContent = coco.parseParams([{name:"current",value:index},{name:"jbnos",value:pJbnos[index++]},{name:"pages",value:pageSize},{name:"zxno",value:zxno},{name:"dhno",value:dhno},{name:"line",value:line},{name:"pm",value:pm},{name:"hjzl",value:hjzl},{name:"hjmz",value:hjmz},{name:"chsu",value:chsu}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(index < pageSize) {
					postContent = coco.parseParams([{name:"current",value:index},{name:"jbnos",value:pJbnos[index++]},{name:"pages",value:pageSize},{name:"zxno",value:zxno},{name:"dhno",value:dhno},{name:"line",value:line},{name:"pm",value:pm},{name:"hjzl",value:hjzl},{name:"hjmz",value:hjmz},{name:"chsu",value:chsu}]);
					ajax.post("bzqd!print.do", postContent);
				}
			};
			ajax.post("bzqd!print.do", postContent);
	    }
	    
	    function doPrint(content, LODOP) {
			//调用打印控件进行套打
			LODOP.PRINT_INIT("BZQD-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("ZXD");
			LODOP.SET_PRINT_PAGESIZE(1, 2500, 2794,"A4");
		//	LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 0, "600px", "1470px", content);
			//打印预览
		//	LODOP.PREVIEW();
			LODOP.PRINT();	
	    }
         // 加载数据
		function queryZp(){
			var qForm = document.forms["PageForm_page"];
			var oForm = document.forms["DataForm"];
			var dhno = oForm.elements["dhno"].value.replace(/^\s+|\s+$/gi, '');
			var line = oForm.elements["line"].value.replace(/^\s+|\s+$/gi, '');
			var zxno = oForm.elements["zxno"].value.replace(/^\s+|\s+$/gi, '');
			var size = oForm.elements["size"].value;
			var postContent = coco.parseParams([{name:"dhno",value:dhno},{name:"line",value:line},{name:"zxno",value:zxno}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				cocoform.fillResult(oForm, this.result);
				oForm.elements["size"].value = size;
				PageData_page.style.display = 'block';
				DataDiv.style.display = 'block';
				if(zxno != null && zxno.length > 0) {
					qForm.action = "pageCh.do";
					qForm.elements["zxno"].value = oForm.elements["zxno"].value;
					cocopage.query();
					return;
				}
				qForm.action = "pageZp.do";
				qForm.elements["dhnoLine"].value = dhno + line;
				qForm.elements["zxno"].value = oForm.elements["zxno"].value;
				cocopage.query();
				return;
			};
			ajax.post("load.do", postContent);
		}
		//返回
		function doReturn() {
			//清空查询条件
			var oForm = document.forms["DataForm"];
			var ignores = ["size"];
			cocoform.clear(oForm, ignores);
			var qForm = document.forms["PageForm_page"];
			qForm.elements["dhnoLine"].value = '';
			qForm.elements["zxno"].value = '';
			PageData_page.style.display = 'none';
			DataDiv.style.display = 'none';
		}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:print id="lodop"/>
		<ui:page title="制作包装清单">
		<form name="DataForm" >
		<input type="hidden" name="size" value="${size }" /><input type="hidden" name="pm" />
		<table width="100%" class="form">
			<tr>
				<td>
					<table width="100%">
						<colgroup><col width="18%" /><col width="16%" /><col width="18%" /><col width="14%" /><col width="16%" /><col width="18%" /></colgroup>
						<tr>
							<th style="text-align: left;">订货No.</th>
							<td><ui:input name="dhno" maxlength="7" title="订货No" />-<ui:input name="line" maxlength="2" title="行号" onkeydown="if(window.event.keyCode==13) queryZp();" /></td>
							<th style="text-align: left;">装箱指示No.</th>
							<td><ui:input name="zxno" maxlength="10" title="装箱指示No." onkeydown="if(window.event.keyCode==13) queryZp();"/></td>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="DataDiv" style="display: none;" >
						<table width="100%">
							<colgroup><col width="18%" /><col width="16%" /><col width="18%" /><col width="14%" /><col width="16%" /><col width="18%" /></colgroup>
							<tr>
								<th style="text-align: left;">出货日期</th>
								<td><ui:date name="chri" prop="calendar:true;" /></td>
								<th style="text-align: left;">内外销</th>
								<td><ui:input name="nwai" maxlength="8" readonly="true" title="内外销" /></td>
								<th style="text-align: left;">用户略称</th>
								<td><ui:input name="abbr" readonly="true" title="用户略称" /></td>
							</tr>
							<tr>
								<th style="text-align: left;">合计重量</th>
								<td><ui:input name="chzl" readonly="true" maxlength="10" title="合计重量" required="false" /></td>
								<th style="text-align: left;">合计毛重</th>
								<td><ui:input name="mazl" readonly="true" maxlength="10" title="合计毛重" /></td>
								<th style="text-align: left;">合计数量</th>
								<td><ui:input name="chsu" readonly="true" maxlength="10" title="合计数量" /></td>
							</tr>
							<tr>
								<th style="text-align: left;">已选制品</th>
								<td colspan="5"><ui:textarea name="zhip" cols="100" rows="3" title="当现品NO.有多个时，用,号隔开。"/></td>
							</tr>
						</table>
						<div class="submit"><input type="button" class="button" value="打 印" onclick="toPrint()" /> <input type="button" class="button" value="返 回" onclick="doReturn()" /></div>
					</div>
				</td>
			</tr>
		</table>
		</form>	
		</ui:page>
		<f:page action="pageZp.do" file="bzqd!list.jsp">
		<input type="hidden" name="dhnoLine" /><input type="hidden" name="zxno" />
		</f:page>
	</body>
</html>