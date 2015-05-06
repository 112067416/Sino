<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	 <div style="overflow:auto;width:1051px;height:500px;" id="div1">
<table class="pagination" border="0" cellpadding="0" cellspacing="0" id="tab2">
	      <colgroup>
				<col width="35"/>
				<col width="100"/>
				<col width="80"/>
				<col width="70"/>
				<col width="60"/>
				<col width="80"/>
				<col width="110"/>
				<col width="110"/>
				<col width="110"/>
				<col width="60"/>
				<col width="60"/>
				<col width="110"/>
				<col width="50"/>
			</colgroup>
			   <tbody>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  >
			<td align="center"><input type="checkbox" name="ids" value="${item.jbno }#${item.fpno }#${item.dhno }#${item.jbkb }#${item.xpkn }#${item.xpho }#${item.face }#${item.yuny }#${item.ztbj }" checked="checked"/></td>
			<td>${item.zzsd}-${item.jbno }</td>
			<td>${item.dhno }</td>
			<td>${item.fpno }</td>
			<td>${item.deng }</td>
			<td>${item.yuny }</td>
			<td>${item.xpho }*${item.xpkn }*COIL</td>
			<td>${item.abbr }</td>
			<td>${item.zpzl }</td>
			<td>${item.face }</td>
			<td>${item.kw }</td>
			<td><f:v value="item.crea" format="yyyy-MM-dd"/></td>
			<td>${item.ztbj }</td>
		</tr>
    </c:forEach>
    </tbody>
</table>
</div>
