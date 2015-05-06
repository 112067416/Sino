<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>原材记录详细</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	</head>
	<body>
	<form action="" xu.ajax="" xu.r="" xu.s="success();" xu.color="1" name="DataForm" method="post" >
	<table class="form">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="10%" />
			<col width="20%" />
			<col width="20%" />
			<col width="10%" />
		</colgroup>
		<tr>
		<th style="text-align: left;">供应商合同No.</th>
		<td>${ycaiTp.ybno}-${ycaiTp.line}</td>
		<th style="text-align: right;">原材卷板No.</th>
		<td>${ycaiTp.jbno}</td>
		<td colspan="3">&nbsp;</td>
		</tr>
	</table>
	<table class="form">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="10%" />
			<col width="15%" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<tr>
			<th style="text-align: left;">制造商卷板NO.</th>
			<td>${ycaiTp.zzsj}</td>
			<th style="text-align: right;">重量(T)</th>
			<td>${ycaiTp.tun}</td>
			<th style="text-align: right;">原板长</th>
			<td colspan="2">${ycaiTp.jbcn}</td>
		</tr>
		<tr>
			<th style="text-align: center;">尺寸·厚</th>
			<th style="text-align: center;">尺寸·宽</th>
			<th style="text-align: center;">商社</th>
			<th style="text-align: center;">币种</th>
			<th style="text-align: center;">制造商规格略称</th>
			<th style="text-align: center;">中日达规格略称</th>
			<th style="text-align: center;">规格</th>
		</tr>
		<tr>
			<td style="text-align: center;">${ycaiTp.xpho}</td>
			<td style="text-align: center;">${ycaiTp.xpkn}</td>
			<td style="text-align: center;">${ycaiTp.ssno}</td>
			<td style="text-align: center;">${ycaiTp.thqf}</td>
			<td style="text-align: center;">${ycaiTp.yblc}</td>
			<td style="text-align: center;">${ycaiTp.ggnm}</td>
			<td style="text-align: center;">${ycaiTp.ggno}</td>
		</tr>
		<tr>
			<th style="text-align: center;">运用规格</th>
			<th style="text-align: center;">表面</th>
			<th style="text-align: center;">等级</th>
			<th style="text-align: center;">内径</th>
			<th style="text-align: center;">制造商</th>
			<th style="text-align: center;">品种</th>
			<th style="text-align: center;">制造年月日</th>
		</tr>
		<tr>
			<td style="text-align: center;">${ycaiTp.yuny}</td>
			<td style="text-align: center;">${ycaiTp.face}</td>
			<td style="text-align: center;">${ycaiTp.deng}</td>
			<td style="text-align: center;">${ycaiTp.njno}</td>
			<td style="text-align: center;">${ycaiTp.rsv1}</td>
			<td style="text-align: center;">${ycaiTp.pzno}</td>
			<td style="text-align: center;">${ycaiTp.zzny}</td>
		</tr>
		<tr>
			<th style="text-align: center;">装船日期</th>
			<th style="text-align: center;">船名</th>
			<th style="text-align: center;">采购单价</th>
			<th colspan="4">&nbsp;</th>
		</tr>
		<tr>
			<td style="text-align: center;">${ycaiTp.blny}</td>
			<td style="text-align: center;">${ycaiTp.ship}</td>
			<td style="text-align: center;">${ycaiTp.cgdj}</td>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="7"><div class="opt-btm"> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('View')" /></div></td>
		</tr>
	</table>
	</form>
	</body>
</html>