<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sino" prefix="sino"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
<div align="center" style="font-size: 25px; font-weight: bold;">分析作业日志——异常事项</div><br />
<table id="ItemTbl" width="100%" align="left" border="1" bordercolor="#000000" style="border-collapse: collapse;">
	<colgroup><col width="6%" /><col width="4%" /><col width="8%" /><col width="5%" /><col width="3%" /><col width="3%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" /><col width="4%" />
	<col width="9%" /><col width="7%" /><col width="7%" /><col width="13%" /><col width="6%" /><col width="5%" /></colgroup>
	<tr>
		<th>Coil No.</th>
		<th>部位</th>
		<th>异常项目</th>
		<th>目标油</th>
		<th colspan="2">&nbsp;</th>
		<th colspan="3">异常数值</th>
		<th colspan="3">再分析情况</th>
		<th>用户略称</th>
		<th>调质度</th>
		<th>订货No.</th>
		<th>订货尺寸</th>
		<th>用途</th>
		<th>表面</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${entities }">
	<tr>
		<td rowspan="2">${item.jbno }</td>
		<td rowspan="2"><f:code code="#SINO_FXS_BW" key="${item.bw }"/></td>
		<td rowspan="2"><f:code code="#SINO_YCXM" key="${item.ycxm }"/></td>
		<td rowspan="2">${item.yqty }</td>
		<td>F</td>
		<td>${item.fuzm }</td>
		<td>${item.ycfs }</td>
		<td>${item.ycfc }</td>
		<td>${item.ycfm }</td>
		<td>${item.fxfs }</td>
		<td>${item.fxfc }</td>
		<td>${item.fxfm }</td>
		<td rowspan="2">${item.abbr }</td>
		<td rowspan="2">${item.yuny }</td>
		<td rowspan="2">${item.dhno }</td>
		<td rowspan="2">${item.houu }*${item.kuan }*<c:choose><c:when test="${item.cang != null && item.cang > 0}">${item.cang }</c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
		<td rowspan="2">${item.cond }</td>
		<td rowspan="2">${item.face }</td>
	</tr>
	<tr>
		<td>B</td>
		<td>${item.fufm }</td>
		<td>${item.ycbs }</td>
		<td>${item.ycbc }</td>
		<td>${item.ycbm }</td>
		<td>${item.fxbs }</td>
		<td>${item.fxbc }</td>
		<td>${item.fxbm }</td>
	</tr>
	</c:forEach>
	<tr>
		<td valign="middle" align="center" height="80">备注</td>
		<td colspan="17" valign="top"><pre>${ycsx }</pre></td>
	</tr>
</table>
</div>
