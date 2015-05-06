<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table id="JqcQTbl" width="100%" align="center" class="pagination">
<colgroup><col width="4%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="9%" />
<col width="13%" /><col width="9%" /><col width="4%" /><col width="9%" /><col width="9%" /><col width="12%" /><col width="4%" /></colgroup>
<tr>
<th><input type="checkbox" onclick="coco.selAll('chkitem',this);" checked="checked" /></th>
<th>COIL No.</th>
<th>分配No.</th>
<th>订货No</th>
<th>用户略称</th>
<th>现品尺寸</th>
<th>规格</th>
<th>表面</th>
<th>镀锡量</th>
<th>制品重量</th>
<th>ETL实绩日期</th>
<th>停止</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas}">
<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
<td><input type="checkbox" checked="checked" id="chkitem" name="chkitem" value="${item.jbno }#${item.fpno }#${item.dhno }#${item.jbkb }#${item.xpkn }#${item.xpho }#${item.sczm }#${item.scfm }#${item.face }#${item.yuny }#${item.ztbj }" /></td>
<td>${item.zzno}${item.plqf}-${item.jbno}</td>
<td>${item.fpno }</td>
<td>${item.dhno }</td>
<td>${item.abbr }</td>
<td>${item.xpho } * ${item.xpkn } *${item.cang }</td>
<td>${item.ggno }</td>
<td>${item.face }</td>
<td>${item.sczm } / ${item.scfm }</td>
<td>${item.zpzl }</td>
<td><f:v value="item.etsd" format="yyyy-MM-dd" /></td>
<td>${item.ztbj }</td>
</tr>
</c:forEach>
</table>
