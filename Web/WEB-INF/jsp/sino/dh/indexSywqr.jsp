<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>仕样未确认打印</title>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		//打印仕样未确认表
		function print() {
			var oForm = document.forms["PageForm_page"];
			var dhnos = oForm.elements["dhnos"].value;
			if(dhnos == null || dhnos.length == 0) {
				alert("请选择要打印的记录！");
				return false;
			}
			if(!confirm("是否确定打印订货No." + dhnos + "对应的订货合同吗?")) return;
			var postContent = coco.parseParam("ids", dhnos);
			document.getElementById("PrintFrame").src = path + "/sino/dy/sywqr.do?"+postContent;
		}
		//查看仕样未确认                 			
        function view() {
        	var qForm = document.forms["PageForm_page"];
			var pageCount = qForm.elements["pageCount"].value;
			if(pageCount == 0) {
				alert("没有仕样未确认的订货合同");
				return;
			}
			parent.cocoframe.open("viewDhSywqr", "查看仕样未确认", "/sino/dh/viewSywqr.do", null, null, true);
		}

      //复选框选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
			//选中订货同号
			var v = obj.value;
			var oForm = document.forms["PageForm_page"];
			var dhnos = oForm.elements["dhnos"].value;
			var rgExp;
			if(obj.checked) {
				if(dhnos == null || dhnos.length == 0) {
					oForm.elements["dhnos"].value = v;
					return;
				}
				oForm.elements["dhnos"].value = dhnos.replace(/(,)+$/g,'') + ',' + v;
			} else {
				rgExp = new RegExp(v+',|('+v+'$)' , "g");
				oForm.elements["dhnos"].value = dhnos.replace(rgExp, '');
			}
		}
		//全选或全不选
		function checkAll(obj) {
			var oForm = document.forms["PageForm_page"];
			var chks = oForm.elements["ids"];
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
			var dhnos = oForm.elements["dhnos"].value;
			if(dhnos == null || dhnos.length == 0) {
				return;
			}
			var chks = oForm.elements["ids"];
			if(chks == null) return;
			if(chks.tagName != null) {
				if(dhnos.indexOf(chks.value) >= 0) {
					chks.checked = true;
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(dhnos.indexOf(el.value) >= 0) {
						el.checked = true;
					}
				}
			}
		}
		
		function doQuery(oForm) {
			var el = oForm.elements["dhnos"];
			el.value = "";
			cocopage.query('page', setChecked);
		}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:page title="仕样未确认打印">
		<f:page action="indexSywqr.do" file="listSywqr.jsp">
		<input type="hidden" name="dhnos" />
			<table class="form" width="100%">
				<colgroup>
					<col width="10%" />
					<col width="40%" />
					<col width="10%" />
					<col width="40%" />
				</colgroup>
					<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th style="text-align: left;">用户代码</th>
					<td><ui:input name="userBegin" maxlength="4"/>至<ui:input name="userEnd" maxlength="4"/></td>
					<th>用户中文名</th>
					<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">交货期</th>
					<td colspan="2"><ui:date name="jhqiBegin" prop="calendar: true;" />至<ui:date name="jhqiEnd" prop="calendar: true;" /></td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="doQuery(this.form);" />
				<input type="button" class="button" value="打 印" onclick="print()" />
				<input type="button" class="button" value="查 看" onclick="view()" />
			</div>
		</f:page>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>