<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--
		
	
		//-->
		</script>
	</head>
	<body>
		<ui:page title="SL待生产制品">
		<f:page action="querysl.do" file="listsl.jsp">
		<table width="96%" align="center" style="margin: 10px 0;" class="form">
			<colgroup>
				<col /><col /><col /><col /><col /><col /><col /><col /><col /><col />
			</colgroup>
			<tr>
				<th>订货NO.</th>
			<td><ui:input name="dhno" maxlength="9" /></td>
				 <th>垫木方向</th>
				    <td>
				      <ui:input name="dmfx"  maxlength="1" />
				    </td>
				     <th>捆包形式</th>
				    <td>
				      <ui:input name="kbao"   maxlength="3" />
				    </td>
				    <th>COIL.No.</th>
				    <td>
				      <ui:input id="jzjbno" name="jbno" maxlength="11"   title="表示经过ETL后，生成产品的编号"/>
				    </td>
				    <th>实绩日期</th>
					<td><ui:date name="crea_begin" value="page.crea_begin"/> - <ui:date name="crea_end" value="page.crea_end" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />

			</div>
		</f:page>
		
		<br/>
		</ui:page>

	</body>
</html>