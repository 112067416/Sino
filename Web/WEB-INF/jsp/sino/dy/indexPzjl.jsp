<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<style type="text/css">
		TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
		TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
		TABLE.pagination1 th { font-size: 12px; color: #003449; height: 21px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
		TABLE.pagination1 td { font-size: 14px; color: #333333; height: 18px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
		TABLE.pagination1 tr {background-color: #F7F7F7;}
		TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
		TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
		</style>
		<script type="text/javascript">
		<!--
		function doPrint(LODOP, content) {
		//	LODOP.SET_PRINTER_INDEXA("HPrint");
			LODOP.PRINT_INIT("PZJL-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINT_PAGESIZE(2, "0", "0","A3");
			LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 0, 1500, 1200, content);
			//打印预览
			LODOP.PREVIEW();
			//打印
		//	LODOP.PRINT();
		}

		function toPrint() {
			var oForm = document.forms["DataForm"];
			var $scsjs = oForm.elements["scsjs"].value;
			if($scsjs == null || $scsjs.length == 0) {
				alert("生产记录为空");
				return;
			}
			var scsjs = $scsjs.split(",");
			var $pageSizes = oForm.elements["pageSizes"].value;
			var pageSizes = $pageSizes.split(",");
			var LODOP = document.getElementById("lodop");
			var index = 0, scsj = scsjs[0], pageSize = pageSizes[0], pageIndex = 0, num = 0;
			index++;
			var postContent = coco.parseParams([{name:"scsjBegin",value:scsj},{name:"scsjEnd",value:scsj},{name:"index",value:pageIndex++},{name:"pages",value:pageSize}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(LODOP,this.result);
				if(pageIndex < pageSize) {
					postContent = coco.parseParams([{name:"scsjBegin",value:scsj},{name:"scsjEnd",value:scsj},{name:"index",value:pageIndex++},{name:"pages",value:pageSize}]);
					ajax.post("pzjl!print.do", postContent);
				} else {
					if(index < scsjs.length) {
						scsj = scsjs[index];
						pageSize = pageSizes[index];
						index++;
						pageIndex = 0;
						postContent = coco.parseParams([{name:"scsjBegin",value:scsj},{name:"scsjEnd",value:scsj},{name:"index",value:pageIndex++},{name:"pages",value:pageSize}]);
						ajax.post("pzjl!print.do", postContent);
					}
				}
			};
			ajax.post("pzjl!print.do", postContent);
		}
		
		//设置每个生产日期的打印页数
		function setPageSizes() {
			var pForm = document.forms["PageForm_page"];
			var begin = pForm.elements["scsjBegin"].value;
			var end = pForm.elements["scsjEnd"].value;
			var postContent = coco.parseParams([{name:"scsjBegin",value:begin},{name:"scsjEnd",value:end}]);
			var oForm = document.forms["DataForm"];
			var ajax = new Cocoajax();
			ajax.callback = function() {
				var obj;
				eval("obj="+this.result+";");
				oForm.elements["scsjs"].value = obj.scsjs;
				oForm.elements["pageSizes"].value = obj.pageSizes;
				
			};
			ajax.post("pzjls.do", postContent);
		}
		//-->
		</script>
		
	</head>
	<body onload="cocopage.query('page', setPageSizes);">
	<ui:print id="lodop"/>
	<ui:page title="ETL实时品质记录表">
		<f:page action="indexPzjl.do" file="listPzjl.jsp">
			<table width="96%" align="center" class="form" style="margin-top: 10px;">
				<colgroup><col width="10%" /><col width="45%" /><col width="10%" /><col width="35%" /></colgroup>
				<tr>
					<th style="text-align: left;">生产日</th>
					<td><ui:datetime name="scsjBegin" value="scsj" onchange="cocopage.refresh('page', setPageSizes);" /> 至 <ui:datetime name="scsjEnd" onchange="cocopage.refresh('page', setPageSizes);" /></td>
					<th style="text-align: left;">Coil No.</th>
					<td><ui:input name="jbno" maxlength="7" /></td>
				</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查    询" onclick="cocopage.query('page', setPageSizes);" />
			<input type="button" class="button" value="打   印" onclick="toPrint();"/>
			</div>
		</f:page>
		<form name="DataForm" method="post" action="#">
			<input type="hidden" name="scsjs" /><input type="hidden" name="pageSizes" />
		</form>
	</ui:page>
	</body>
</html>