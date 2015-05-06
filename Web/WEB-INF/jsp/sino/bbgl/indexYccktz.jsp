<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>原材料仓库台帐管理</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
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
		var tableForm = new TableForm("TzTbl");
		tableForm.callback = function() {
			if(this.code <= 0) {
				alert(this.msg);
				return;
			}
			alert("保存成功");
			cocopage.refresh();
		};
		
		function saveTz() {
			if(!confirm("是否确定保存?")) return;
			tableForm.submit("saveYctz.do");
		}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="原材料仓库台帐管理">
		<f:page action="indexYccktz.do" file="listYccktz.jsp">
			<table width="100%" class="form">
				<colgroup><col width="10%" /><col width="20%" /><col width="10%" /><col width="30%" /><col width="10%" /><col width="20%" /></colgroup>
				<tr>
					<th style="text-align: left;">年月(yyyyMM)</th>
					<td><ui:input name="ny" cssClass="normal" value="page.ny" maxlength="6" /></td>
					<th style="text-align: left;">日期</th>
					<td><ui:date name="riqi" prop="calendar: true;" /></td>
					<th style="text-align: left;">商品编号</th>
					<td><ui:select name="spbh" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SPBH'" prop="nn: 1;" /></td>
				</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			<input type="button" class="button" value="保 存" onclick="saveTz();" />
			</div>	
		</f:page>
	</ui:page>
	</body>
</html>