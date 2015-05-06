<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="../../global/header.jsp" %>
<script type="text/javascript">

function print(event, flag) {
	var oForm = document.forms["DataForm"];
	if(!flag) {
		alert("请指定卷号");
		return;
	}
	//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
	var LODOP = document.getElementById("lodop");
	LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
	LODOP.SET_PRINT_STYLE("FontSize",12);
	LODOP.ADD_PRINT_HTM(-15, -10, 1100, 800, PrintDiv.innerHTML);
	//打印预览
	LODOP.PREVIEW();
	//打印
	//LODOP.PRINT();
}

</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="不良扣除联络单打印">
<form name="DataForm" action="">
	<div id="PrintDiv" style="background-color: #FFFFFF;color: #000000; height: 600px;">
		<div align="center" style="width: 800; font-size: 16px; font-weight: bold; height: 30px; line-height: 30px; vertical-align: bottom; letter-spacing: 4px;">
			<u>不良扣除联络单</u>
		</div>
		<div align="center" style="width: 800; font-size: 14px; letter-spacing: 1px;">
			（Contact List）
		</div>
		<table width="800" align="center" cellpadding="0" cellspacing="0" border="0" style="text-align: left; padding-left: 20px; margin-top: 20px;">
			<tr>
				<td style="letter-spacing: 3px;">
					该卷中混有不良,产品标签显示的卷重已经扣除了不良部分的重量
				</td>
			</tr>
			<tr>
				<td style="letter-spacing: 2px;">
					This Product Contains defect portion.The weight of defect portion is decreased.
				</td>
			</tr>
		</table>
		<table width="800" align="center" cellpadding="0" cellspacing="0" border="1" bordercolor="#0eb8e4" style="border-collapse: collapse;margin-top: 5px; text-align: left; padding-left: 5px;">
			<colgroup>
				<col width="10%" /><col width="40%"/><col width="50%"/>
			</colgroup>
			<tr>
				<td height="30px" style="border-right: 0;">
					日期(DATE)
				</td>
				<td>
					&nbsp;<ui:input maxlength="10"/>
				</td>
				<td>
					卷号(Coil No)&nbsp;<ui:input name="cno" maxlength="11" />
				</td>
			</tr>
			<tr>
				<td colspan="2" height="30px">
					不良名称(defect)&nbsp;<ui:input name="_blmc" maxlength="20"/>
				</td>
				<td>
					扣除长度(decreased length)&nbsp;<ui:input name="_blcd" maxlength="4"/>&nbsp;m
				</td>
			</tr>
			<tr>
				<td colspan="3" height="30px">
					不良分布(destribution)：&nbsp;
					<input type="checkbox">连续性(continuty)；
					<input type="checkbox">间断性(intermittent)；
					<input type="checkbox">零星(spordic)
				</td>
			</tr>
			<tr>
				<td colspan="3" height="80">
					<table height="80" width="100%" align="center" cellpadding="0" cellspacing="0" border="0" >
						<colgroup>
							<col width="60px" style="padding-left: 5px;"><col><col width="60px" align="left">
						</colgroup>
						<tr>
							<td colspan="3" align="center">
								<u>不良位置(defect portion)</u>
							</td>
						</tr>
						<tr>
							<td>
								内圈
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								外圈 
							</td>
						</tr>
						<tr>
							<td>
								Inner
							</td>
							<td rowspan="2">
								<img alt="不良位置" src="/images/sino/bl_defect.png">
							</td>
							<td>
								Outer
							</td>
						</tr>
						<tr>
							<td>
								0m
							</td>
							<td>
								<ui:input name="" maxlength="5"/>m
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td rowspan="2" style="text-align: center;">
					不良简图&nbsp;
					(sketch)
				</td>
				<td colspan="2" height="100" align="left" valign="top" style="padding: 8px; line-height: 14px;">
					<span>表面<br />(Top)</span>
					<span><img alt="表面简图" src="/images/sino/bl_top.png"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" height="100" align="left" valign="top" style="padding: 8px; line-height: 14px;">
					<span>背面<br />(Bottom)</span>
					<span><img alt="背面简图" src="/images/sino/bl_bottom.png"></span>
				</td>
			</tr>
		</table>
	</div>
	<table width="800" align="center" cellpadding="0" cellspacing="0" border="0" style="text-align: right; margin-top: 10px;">
		<tr>
			<td>
				<input type="button" class="button" value="打 印(P)" onclick="print(event, true);" accesskey="p" />
			</td>
		</tr>
	</table>
</form>

<br />
</ui:page>
</body>
</html>