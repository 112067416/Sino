<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach varStatus="stat" var="item" items="${vos }">
<div id="div${stat.count }">
<table border="0" width="700" align="center">
<tr>
<td>
<table border="0" width="100%" align="center">
<colgroup><col width="70%" /><col width="30%" /></colgroup>
<tr><td colspan="2" height="30" style="text-align: center; vertical-align: middle; font-size: 25px; font-weight: bold;">成 品 出 库 单</td></tr>
<tr><td style="text-align: left; font-size: 15px;"><f:v value="item.chqi" format="yyyy-MM-dd" /></td><td style="text-align: center; font-size: 15px;">No <f:v value="item.chqi" format="MMdd" /></td></tr>
</table>
</td>
</tr>
<tr>
<td>
<table border="1" width="100%" height="106" cellspacing="0" bordercolor="#000000"  style="border-collapse:collapse; text-align: center;">
<colgroup><col width="12%" /><col width="13%" /><col width="13%" /><col width="13%" /><col width="13%" /><col width="13%" /><col width="13%" /></colgroup>
<tr><td height="35" colspan="7" style="text-align: left; padding-left: 5px;">送货单号码：<br /><f:rt value="${item.shnos }" num="7" /></td></tr>
<tr><td height="45" rowspan="2">成品名称</td><td style="border-bottom: 0px;">规        格</td><td style="border-bottom: 0px;">数       量</td><td colspan="4" rowspan="2">备                          注</td></tr>
<tr><td style="border-top: 0px; font-size: 12px;">(mm)</td><td style="border-top: 0px; font-size: 12px;">(吨)</td></tr>
<tr><td>马口铁切板</td><td>0.1~0.29</td><td>${item.bsl1 }</td><td rowspan="6">出口</td><td>马口铁切板</td><td>0.1~0.29</td><td>${item.cbsl1 }</td></tr>
<tr><td>马口铁切板</td><td>0.30~0.49</td><td>${item.bsl2 }</td><td>马口铁切板</td><td>0.30~0.49</td><td>${item.cbsl2 }</td></tr>
<tr><td>马口铁切板</td><td>0.50~1.00</td><td>${item.bsl3 }</td><td>马口铁切板</td><td>0.50~1.00</td><td>${item.cbsl3 }</td></tr>
<tr><td>马口铁卷材</td><td>0.1~0.29</td><td>${item.jsl1 }</td><td>马口铁卷材</td><td>0.1~0.29</td><td>${item.cjsl1 }</td></tr>
<tr><td>马口铁卷材</td><td>0.30~0.49</td><td>${item.jsl2 }</td><td>马口铁卷材</td><td>0.30~0.49</td><td>${item.cjsl2 }</td></tr>
<tr><td>马口铁卷材</td><td>0.50~1.00</td><td>${item.jsl3 }</td><td>马口铁卷材</td><td>0.50~1.00</td><td>${item.cjsl3 }</td></tr>
<tr><td>&nbsp;</td><td>合计：</td><td>${item.hj }</td><td>内销</td><td colspan="2">合计：</td><td>${item.nhj }</td></tr>
</table>
</td>
</tr>
<tr>
<td>
<table border="0" width="100%" align="center">
<colgroup><col width="55%" /><col width="45%" /></colgroup>
<tr><td style="text-align: right; font-size: 15px;">主管：</td><td style="text-align: center; font-size: 15px;">经办：</td></tr>
</table>
</td>
</tr>
</table>
</div>
<br><br>
</c:forEach>