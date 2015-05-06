<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%>
	<%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>采购登录</title>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<SCRIPT type="text/javascript">
	<!--
	
	//全选/反选操作
	function doCheck(checkbox, checks){	
		var e = document.getElementsByName(checks);
		for(var i = 0; i < e.length; i++){
			if(checkbox.checked==true){
				e[i].checked=true;	 
				continue;
			}
			e[i].checked=false;
		}	   
	}
	var tableform = new TableForm("DataTbl");
	//提交
	function doSubmit() {
		tableform.formatTblForm("items",1);
		var ajax = new Cocoajax();
		cocoform.submit(document.forms["DataForm"], null, "确定保存吗?");
	}
	var tf = new TableForm("DataTbl");
	//新增一行
	function doAdd() {
		tf.insertRow('HidTbl');
		var oDataTbl = document.getElementById("DataTbl");
		var oldRow = oDataTbl.rows[oDataTbl.rows.length - 2];
		var newRow = oDataTbl.rows[oDataTbl.rows.length - 1];
		if(oldRow.cells[1].childNodes[0].value!=undefined){
		  newRow.cells[1].childNodes[0].value = oldRow.cells[1].childNodes[0].value;
		}	
		var line;
		if(oldRow.cells[2].childNodes[0].value!=undefined){
		   line=Number(oldRow.cells[2].childNodes[0].value)+1;
		   //合同号和行号赋值 
		   if(line<100)
		   {
			   if(line<10){
		           newRow.cells[2].childNodes[0].value = "00"+line;
			   }
			   else{
				   newRow.cells[2].childNodes[0].value = "0"+line;
			   }
		   }
		   else
		   {
			newRow.cells[2].childNodes[0].value =line;
		    }
		}
		else{
			newRow.cells[2].childNodes[0].value ="001";
		}
	}
	
	//显示中日达略称
	function show(td) {
		var oDataTbl = document.getElementById("DataTbl");
		var newRow = oDataTbl.rows[td.parentElement.rowIndex];
		newRow.cells[4].childNodes[0].value =td.childNodes[0].value.toUpperCase();
	}
	//删除行
	function doDelete() {
		if(!confirm("确定删除吗?")) return false;
		var oDataTbl = document.getElementById("DataTbl");
		if(!oDataTbl) return false;
		tf.removeTableRow(oDataTbl, "ids");
		alert("删除成功");
	}
	function success() {
		alert("保存成功");
		var form = document.forms["DataForm"];
		window.location.reload();
	}
	//
	function setNm(obj, name) {
		var oForm = document.forms["DataForm"];
		oForm.elements[name].value = obj.options[obj.selectedIndex].text;
	}
	//-->
	</SCRIPT>
</head>
<body>
	<ui:page title="采购登录 ">
    <form name="DataForm" xu.ajax="true" xu.s="success()" method="post" action="save.do" >
		<table width="96%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="25%" />
			</colgroup>
			<tr>
				<th style="text-align: right;">签约日期</th>
				<td><ui:date name="qyri" required="true" /></td>
				<th style="text-align: right;">制造商</th>
				<td nowrap="nowrap"><ui:select name="zzsd" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='012'" prop="nn:1;" headerText="请选择" headerValue="" onchange="setNm(this,'zzsm');" /><input type="hidden" name="zzsm" /></td>
				<th style="text-align: right;">币种</th>
				<td colspan="2"><ui:select name="thqf" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='013'" prop="nn:1;" headerText="请选择" headerValue="" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">合同日期</th>
				<td><ui:date name="htdt" required="true"/></td>
				<th style="text-align: right;">商社</th>
				<td colspan="3"><ui:select name="ssno" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='010'" prop="nn:1;" headerText="请选择" headerValue="" onchange="setNm(this,'ssnm');" /><input type="hidden" name="ssnm"/></td>
				
			</tr>
		</table>
		<table id="DataTbl" width="98%" align="center" style="margin: 20px auto;"
				class="form">
				<colgroup><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /></colgroup>
				<tr>
					<td><input type="checkbox" xu.target="ids" /></td>
					<th style="text-align: center;">供应商<br>合同NO</th>
					<th style="text-align: center;">行号</th>
					<th style="text-align: center;">制造商<br>规格略称</th> 
					<th style="text-align: center;">用户略称</th>
					<th style="text-align: center;">品种</th>
					<th style="text-align: center;">尺寸</th>
					<th style="text-align: center;">重量<br>（吨）</th>
					<th style="text-align: center;">等级</th>
					<th style="text-align: center;">表面</th>
					<th style="text-align: center;">单价</th>
					<th style="text-align: center;">内径</th>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="htno" maxlength="7"  title="供应商合同NO(手工设定)" prop="type:abcn" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:digit name="line" maxlength="3"  title="行号" value="#001" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="zzgg" maxlength="15" title="制造商规格略称" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="abbr" maxlength="16"  title="用户略称" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="pzno" maxlength="2" title="品种代码" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:number name="houu" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true" required="true"/>*<ui:number name="kuan" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:number name="htzl" maxlength="9"  scale="8" precision="3" title="实际重量" positive="true" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="fpdj" maxlength="3" title="等级" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="face" maxlength="2"  title="表面" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:number name="htdj" maxlength="8"  scale="7" precision="2" title="采购单价" positive="true" required="true"/></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="neij" maxlength="3" title="内径" positive="true" required="true" value="#508"/></td>
				</tr>
		</table>
			<div class="opt-btm">
			<input type="button" class="button" value="新 增" onclick="doAdd();" /> 
			<input type="button" class="button" value="删 除" onclick="doDelete();" />
			 <input type="button" class="button" value="保 存" onclick="doSubmit()" /></div>
		</form>
	</ui:page>
	<table id="HidTbl" style="display: none;">
		<tr>
			<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:input name="htno" maxlength="7"  title="供应商合同NO(手工设定)" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:digit name="line" maxlength="3"  title="行号"  required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;" ><ui:input name="zzgg" maxlength="15" title="制造商规格略称" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:input name="abbr" maxlength="16"  title="用户略称" /></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:int name="pzno" maxlength="2" title="品种代码" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:number name="houu" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true" required="true"/>*<ui:number name="kuan" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:number name="htzl" maxlength="9"  scale="8" precision="3" title="实际重量" positive="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:input name="fpdj" maxlength="3" title="等级" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:input name="face" maxlength="2"  title="表面" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:number name="htdj" maxlength="8"  scale="7" precision="2" title="采购单价" positive="true" required="true"/></td>
			<td nowrap="nowrap" style="text-align: center;"><ui:int name="neij" maxlength="3"  title="内径" positive="true" required="true" value="#508"/></td>
		</tr>
	</table>
</body>
</html>
