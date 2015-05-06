<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<div style="font-size: 25px; font-weight: bold; padding-left: 230px;" align="left">${title }用途销售量分析</div>
<table border="1" width="740" cellspacing="0" bordercolor="#000000"  style="border-collapse:collapse; text-align: center; font-size: 12px;">
<colgroup>
<col width="8%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" />
<col width="9%" /><col width="10%" /><col width="8%" /><col width="9%" /><col width="8%" /><col width="12%" />
</colgroup>
<tr>
<td height="45">月份</td><td>饮料</td><td>杂罐</td><td>食品</td><td>电池</td>
<td>喷雾</td><td>电子部件</td><td>皇冠</td><td>化工罐</td><td>奶粉</td>
<td>出货量<br />(吨)</td>
</tr>
<c:forEach varStatus="stat" var="item" items="${vos }">
<tr>
<td><c:choose><c:when test="${item.month > 0 }">${item.year }.${item.month }</c:when><c:otherwise>合计：</c:otherwise></c:choose></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.ylzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.zgzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.spzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.dczl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.pwzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.dzbjzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.hgzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.hggzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.nfzl" format="#,##0.000" /></td>
<td style="text-align: right; padding-right: 10px; font-size: 14px;"><f:v value="item.chzl" format="#,##0.000" /></td>
</tr>
</c:forEach>
</table><br />
<div id="ImgDIV" style="padding-left: 15px;" > <img name="oImg" src=<%=request.getContextPath() %>"/sys/chart/createCondChart.do?yearS=${yearS }&yearE=${yearE }&monthS=${monthS }&monthE=${monthE }&chart=${chart }" style="cursor: hand;" border="0"></div>
</div>