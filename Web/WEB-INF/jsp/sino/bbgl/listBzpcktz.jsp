<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="TzTbl"  width="100%" class="pagination1" border="0" cellpadding="0" cellspacing="0">
<colgroup>
<col width="2%"/><col width="2%"/><col width="6%"/><col width="3%"/>
<col width="5%"/><col width="5%"/><col width="5%"/><col width="5%"/><col width="5%"/>
<col width="5%"/><col width="3%"/><col width="5%"/><col width="5%"/><col width="5%"/>
<col width="5%"/><col width="5%"/><col width="5%"/><col width="5%"/><col width="4%"/>
<col width="5%"/><col width="5%"/><col width="5%"/>
</colgroup>
<tr class="page-head" align="center">
<th nowrap="nowrap" rowspan="2" colspan="2">${year }</th>
<th nowrap="nowrap" rowspan="3">摘要</th>
<th nowrap="nowrap" colspan="4">入库(下次工程产出)</th>
<th nowrap="nowrap" colspan="3">其中转选别</th>
<th nowrap="nowrap" colspan="6">出库</th>
<th nowrap="nowrap" colspan="3">E堆场消灭</th>
<th nowrap="nowrap" colspan="3">结存</th>
</tr>
<tr>
<th nowrap="nowrap" rowspan="2">入库<br />单号</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
<th nowrap="nowrap" rowspan="2">出库<br />单号</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
<th nowrap="nowrap" colspan="2">串货</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
<th nowrap="nowrap" rowspan="2">数量</th>
<th nowrap="nowrap" colspan="2">其中</th>
</tr>
<tr>
<th nowrap="nowrap">月</th>
<th nowrap="nowrap">日</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
<th nowrap="nowrap">内销</th>
<th nowrap="nowrap">外销</th>
<th nowrap="nowrap">内转外</th>
<th nowrap="nowrap">外转内</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
<th nowrap="nowrap">一般贸易</th>
<th nowrap="nowrap">保税</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
<td><f:v value="item.riqi" format="MM" /></td>
<td><f:v value="item.riqi" format="dd" /></td>
<td><input type="hidden" name="id" value="${item.id }" /><span style="font-size: 11px;">入库/出库</span></td>
<td><f:v value="item.riqi" format="yyMMdd" /></td>
<td><ui:int name="qbch1" value="item.qbch1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbybch1" value="item.qbybch1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbbsch1" value="item.qbbsch1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="che" value="item.che" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbybche" value="item.qbybche" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbbsche" value="item.qbbsche" cssStyle="font-size:11px; width: 45px;" /></td>
<td><f:v value="item.riqi" format="yyMMdd" /></td>
<td><ui:int name="qbfh1" value="item.qbfh1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbybfh1" value="item.qbybfh1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbbsfh1" value="item.qbbsfh1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbnzw" value="item.qbnzw" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbwzn" value="item.qbwzn" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="fhe" value="item.fhe" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbybfhe" value="item.qbybfhe" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbbsfhe" value="item.qbbsfhe" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbjc1" value="item.qbjc1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbybjc1" value="item.qbybjc1" cssStyle="font-size:11px; width: 45px;" /></td>
<td><ui:int name="qbbsjc1" value="item.qbbsjc1" cssStyle="font-size:11px; width: 45px;" /></td>
</tr>
</c:forEach>
</table>
<f:paged />