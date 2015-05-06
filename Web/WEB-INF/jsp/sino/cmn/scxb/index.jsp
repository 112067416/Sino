<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--

		var etl = new TableForm("ETLTbl", "ETLTemp", "etlIds");
		var sl = new TableForm("SLTbl", "SLTemp", "slIds");

		function etlSuccess() {
			alert("保存成功");
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ETL");
			ajax.post("listEtl.do");
		}
		
		function slSuccess() {
			alert("保存成功");
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("SL");
			ajax.post("listSl.do");
		}
		//-->
		</script>
		
	</head>
	<body>
		<ui:page title="ETL和SL生产线别配置">
			<form action="save.do" method="post" xu.s="etlSuccess()" xu.e="alert('保存失败');" >
			<input type="hidden" name="type" value="${etlType }" />
			<div id="ETL">
			<%@include file="listEtl.jsp" %>
			</div>
			<table width="96%" align="center">
				<tr>
					<td align="left">
						<input type="button" class="button" value="添 加" onclick="etl.insertEmptyRow();" /> 
						<input type="button" class="button" value="移 除" onclick="etl.removeRow();" />
					</td>
					<td align="right">
						<input type="button" class="button" value="保 存" onclick="etl.formatForm('scxbs');cocoform.submit(this);" />
					</td>
				</tr>
			</table>
			</form>
			<table id="ETLTemp" style="display: none">
				<tr>
					<td align=center><input type="checkbox" name="etlIds" /></td>
					<td><ui:input name="name" required="true" maxlength="16" title="镀锡线别的名称" /></td>
					<td><ui:input name="code" required="true" maxlength="1" title="镀锡线别的代码" /></td>
					<td><sys:deptBox name="dept" prop="nn:1;" /> </td>
				</tr>
			</table>
			<br />
			<form action="save.do" method="post" xu.s="slSuccess()" xu.e="alert('保存失败');" >
			<input type="hidden" name="type" value="${slType }" />
			<div id="SL">
			<%@include file="listSl.jsp" %>
			</div>
			<table width="96%" align="center">
				<tr>
					<td align="left">
						<input type="button" class="button" value="添 加" onclick="sl.insertEmptyRow();" /> 
						<input type="button" class="button" value="移 除" onclick="sl.removeRow();" />
					</td>
					<td align="right">
						<input type="button" class="button" value="保 存" onclick="sl.formatForm('scxbs');cocoform.submit(this);"/>
					</td>
				</tr>
			</table>
			</form>
			<table id="SLTemp" style="display: none">
				<tr>
					<td align=center><input type="checkbox" name="slIds" /></td>
					<td><ui:input name="name" required="true" maxlength="16" title="剪切线别的名称" /></td>
					<td><ui:input name="code" required="true" maxlength="1" title="剪切线别的代码" /></td>
					<td><ui:input name="qualified" required="true" maxlength="1" title="合格品Pile区分" /></td>
					<td><ui:input name="unqualified" required="true" maxlength="1" title="选别品Pile区分" /></td>
					<td><sys:deptBox name="dept" prop="nn:1;" /></td>
				</tr>
			</table>
		<br />
		</ui:page>
	</body>
</html>