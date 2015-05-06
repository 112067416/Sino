<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table id="DataTbl" width="100%" align="center" class="pagination" style="margin-top: 10px;">
<colgroup><col width="5%" /><col width="7%" /><col width="7%" /><col width="4%" />
<col width="10%" /><col width="5%" /><col width="5%" /><col width="3%" />
<col width="5%" /><col width="5%" /><col width="6%" /><col width="5%" /><col width="5%" /><col width="4%" /><col width="12%" />
<col width="12%" />
</colgroup>
<tr>
<th>状态</th>
<th>指示No.</th>
<th>Coil NO.</th>
<th>操作</th>
<th>现品尺寸</th>
<th>运用规格</th>
<th>锡附着量</th>
<th>表面</th>
<th>合金层</th>
<th>涂油量</th>
<th>分配等级</th>
<th>软溶?</th>
<th>化成</th>
<th>内径</th>
<th>别纸No.</th>
<th>业联No.</th>
</tr>
<c:forEach varStatus="stat" var="item"  items="${rcmxs}">
<tr key="${item.jbno }" >
<td ><input type="hidden" name="sczt" value="${item.stat }" /><font <c:if test="${stat.index%2 == 1 }">color="red"</c:if> color="green" size="2"> <f:kv value="item.stat" list="'1':'备用卷','2':'当前卷'"  /></font></td>
<td><input type="hidden" name="jbno" value="${item.jbno }" />${item.zsno }</td>
<td>${item.jbno }</td>
<td>${item.caoz }</td>		
<td>${item.size }</td>
<td>${item.yuny }</td>
<td>${item.xfuz }</td>
<td>${item.face }</td>
<td>${item.hjin }</td>
<td>${item.yqty }</td>
<td>${item.fpdj }</td>
<td>${item.flow }</td>
<td><c:if test="${item.hxcl != \"311\" }">${item.hxcl }</c:if></td>
<td>${item.njno }</td>
<td><c:forEach varStatus="stat" var="kpno" items="${item.kpns }"><span class="link" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span></c:forEach></td>
<td><c:forEach varStatus="stat" var="ylno" items="${item.ylns }"><span class="link" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span></c:forEach></td>
</tr>
</c:forEach>
</table>