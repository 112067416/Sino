<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
<div style="font-size: 30px; font-weight: bold; color: #000000; padding-top: 20px; padding-bottom:10px; " align="center">${entity.name }--<f:v value="entity.crea" format="yyyy-MM-dd"/>号付款对应冲帐明细</div>
<table align="left" cellpadding="0" cellspacing="0" border="1" bordercolor="#000000" style="border-collapse: collapse;">
<colgroup>
<col width="70"><col width="100"><col width="70"><col width="50"><col width="80"><col width="115"><col width="60"><col width="60">
<col width="50"><col width="50"><col width="50"><col width="80">
<col width="80"><col width="70">
<col width="70"><col width="80"><col width="70">
</colgroup>
<tr height="40">
<td style="font-size: 12px; font-weight: bold;" align="center">出货日期</td>
<td style="font-size: 12px; font-weight: bold;" align="center">开票客户</td>
<td style="font-size: 12px; font-weight: bold;" align="center">订货No.</td>
<td style="font-size: 12px; font-weight: bold;" align="center">规格</td>
<td style="font-size: 12px; font-weight: bold;" align="center">镀锡量</td>
<td style="font-size: 12px; font-weight: bold;" align="center">尺寸</td>
<td style="font-size: 12px; font-weight: bold;" align="center">开票吨数</td>
<td style="font-size: 12px; font-weight: bold;" align="center">发票单价</td>

<td style="font-size: 12px; font-weight: bold;" align="center">质量折让</td>
<td style="font-size: 12px; font-weight: bold;" align="center">利息折让</td>
<td style="font-size: 12px; font-weight: bold;" align="center">调整金额</td>
<td style="font-size: 12px; font-weight: bold;" align="center">发票金额</td>

<td style="font-size: 12px; font-weight: bold;" align="center">发票号码</td>
<td style="font-size: 12px; font-weight: bold;" align="center">发票日期</td>

<td style="font-size: 12px; font-weight: bold;" align="center">收款日期</td>
<td style="font-size: 12px; font-weight: bold;" align="center">收款金额</td>
<td style="font-size: 12px; font-weight: bold;" align="center">未收余款</td>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr>
<td style="font-size: 12px;" height="30"><f:v value="item.fkfp.chri" format="yyyy-MM-dd"/></td>
<td style="font-size: 12px;">${item.fkfp.fpymc}</td>
<td style="font-size: 12px;">${item.fkfp.dhno }-${item.fkfp.line }</td>
<td style="font-size: 12px;">${item.fkfp.ggno }</td>
<td style="font-size: 12px;">${item.fkfp.fudw }${item.fkfp.fufm }/${item.fkfp.fuzm }</td>
<td style="font-size: 12px;"><f:v value="item.fkfp.houu" format="3" />*<f:v value="item.fkfp.kuan" format="2" />*<c:choose><c:when test="${item.fkfp.cang != null && item.fkfp.cang > 0}"><f:v value="item.fkfp.cang" format="2" /></c:when><c:otherwise>COIL</c:otherwise></c:choose></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkfp.kfzl" format="3" /></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkfp.kpdj" format="#,##0.00"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkfp.zlzr" format="#,##0.00"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkfp.lxzr" format="#,##0.00"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkfp.tzje" format="#,##0.00"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkfp.fpje" format="#,##0.00"/></td>
<td style="font-size: 12px;">${item.fkfp.fpno }</td>
<td style="font-size: 12px;"><f:v value="item.fkfp.fpri" format="yyyy-MM-dd"/></td>
<td style="font-size: 12px;"><f:v value="item.skri" format="yyyy-MM-dd"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.fkje" format="#,##0.00"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="item.wsyk" format="#,##0.00"/></td>
</tr>
</c:forEach>
<tr>
<td colspan="5">&nbsp;</td>
<td>合计：</td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="kfzl" format="3" /></td>
<td colspan="4">&nbsp;</td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="fpje" format="#,##0.00"/></td>
<td colspan="3">&nbsp;</td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="fkje" format="#,##0.00"/></td>
<td style="font-size: 12px; text-align: right; padding-right: 5;"><f:v value="wsyk" format="#,##0.00"/></td>
</tr>
</table>
</div>