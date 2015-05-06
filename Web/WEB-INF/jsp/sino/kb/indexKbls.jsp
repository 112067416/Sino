<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<script type="text/javascript">
		<!--
		// 回退操作
		function rollBack(xpzk) {
			var content =  cocoform.postCheckValues("PageForm_page","ids","ids");
			if(content == null || content.length == 0) {
				alert("请选择要回退的制品");
				return;
			}
			content = content + "&xpzk=" + xpzk;
		    if(!confirm("确定回退吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("回退操作成功");
				cocopage.refresh();
			};
			ajax.post("rollback.do", content);
		}
		//
		function doPrint() {
			var form = document.forms["PageForm_page"];
			var chks = form.elements["ids"];
			var jbnos = [];
			if(chks == null) {
				alert("没有数据，请查询.");
				return false;
			}
			if(chks.tagName != null) {
				if(chks.checked) jbnos[0] = chks.value;
			}
			else {
				for(var i = 0; i < chks.length; i++) {
					if(chks[i].checked) jbnos[jbnos.length] = chks[i].value;
				}
			}
			if(jbnos.length == 0) {
				alert("请选择要打印的制品");
				return;
			}
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
	<body>
	<ui:print id="lodop"/>
	<ui:page title="捆包历史">
		<f:page action="indexKbls!${xpzk}.do" file="listKbls.jsp">
			<table width="96%" class="form">
				<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="20%" /><col width="10%" /><col width="30%" /></colgroup>
				<tr>
					<th>PILE No.</th>
					<td><ui:input name="jbno" maxlength="11" title="PILE No." /></td>
					<th>捆包形式</th>
					<td><ui:input name="kbao" maxlength="4" title="捆包形式" /></td>
					<th>捆包日期</th>
					<td><ui:date name="kbsdBegin" showCalendar="true" value="page.kbsdBegin" /> 至 <ui:date name="kbsdEnd" showCalendar="true"/></td>
				</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查  询" onclick="cocopage.query();" />   
			<input type="button" class="button" value="回  退" onclick="rollBack('${xpzk }');" /> 
			<input type="button" class="button" value="打  印" onclick="doPrint();" /></div>
		</f:page>
	</ui:page>
	</body>
</html>