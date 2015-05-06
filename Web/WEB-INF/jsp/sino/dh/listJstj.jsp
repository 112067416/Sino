<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="DataTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="5%"/><col width="4%"/><col width="10%"/><col width="6%"/><col width="6%"/><col width="6%"/><col width="5%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="13%"/></colgroup>
<tr>
<th><input type="checkbox" xu.target="ids" /></th>
<th></th>
<th>订货No.</th>
<th>预付款<br />比例</th>
<th>出货款<br />比例</th>
<th>后付款<br />比例</th>
<th>期限</th>
<th>币种</th>
<th>运输方式</th>
<th>运费单价</th>
<th>合同单价</th>
<th>合同重量</th>
<th>合同金额</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr>
<td align="center"><input type="checkbox" name="ids" /></td>
<td><ui:input name="jstj" maxlength="1" value="#1" /></td>
<td>${item.dhno }-${item.line }<input type="hidden" name="dhno" value="${item.dhno }" /><input type="hidden" name="line" value="${item.line }" /></td>
<td><ui:int name="yfkn" maxlength="3" positive="true" value="item.yfkn" /></td>
<td><ui:int name="chkn" maxlength="3" positive="true" value="item.chkn" /></td>
<td><ui:int name="hfkn" maxlength="3" positive="true" value="item.hfkn" /></td>
<td><ui:int name="qixn" maxlength="3" positive="true" value="item.qixn" /></td>
<td><ui:select name="thqf" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='013'" prop="nn:1;" headerText="请选择" headerValue="" value="item.thqf" /></td>
<td><ui:select name="ysfs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_YSFS'" prop="nn:1;" headerText="请选择" headerValue="" value="item.ysfs" /></td>
<td><ui:number name="yfei" scale="7" precision="2" positive="true" required="true" value="item.yfei" /></td>
<td><ui:number name="htdj" scale="7" precision="2" positive="true" required="true" value="item.htdj" onchange="getHtje(this)" /></td>
<td><ui:number name="htzl" title="订货DB的合同重量" value="item.htzl" scale="7" precision="3" positive="true" cssClass="normal" readonly="true" maxlength="8"/></td>
<td><ui:number name="htje" scale="11" precision="2" title="合同金额" cssClass="normal" readonly="true" value="item.htje" format="###.##" /></td>
</tr>
</c:forEach>
</table>