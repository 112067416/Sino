<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<table  width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
			</colgroup>
			
	<tr >
		  <th><input type="checkbox" xu.target="ids" /></th>
			<th>指示No</th>
			<th>订货No</th>
			<th>操作</th>
			<th>用户略称</th>
			<th>尺寸</th>
			<th>附着量</th>
			<th>运用规格</th>
			<th>表面</th>
			<th>内径</th>
			<th>重量</th>
			<th>作成时间</th>
			<th>分派</th>
			<th>紧急</th>
			<th>生产线</th>
			<th>备注</th>
			</tr>
			
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
	
	<f:equals value2="item.stat" value1="#1"></f:equals>
		<tr  xu.id="${item.zsno }" xu.id1="${item.dhno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)" >
			<td align="center"><input type="checkbox" name="ids" value="${item.zsno }" /></td>	
			<td>${item.zsno }</td>
			<td>${item.dhno }</td>
			<td><f:kv value="item.caoz" list="'C':'卷材','S':'板材'"  /></td>
			<td>${item.abbr }</td>
			<td>${item.zpcc }</td>
			<td>${item.fugm}</td>
			<td>${item.yuny }</td>
			<td>${item.face }</td>
			<td><sys:codeLabel key="item.njno" code="#020"/></td>
			<td><f:v value="item.allzpzl" format="3"/> </td>
			<td><f:v value="item.crea" format="yyyy-MM-dd"/> </td>
			<td><sys:codeLabel key="item.stat" code="#ZSFP"/></td>
			<td><c:if test="${item.ycaijinjCount==item.ycaiAllCount}"><font color='red'>是</font></c:if></td>
			<td><f:v value="item.shch" ql="select name from Scxbpz where code=?"/></td>
			<td>${item.remk }</td>
		</tr>
		
    </c:forEach>
</table>
<f:paged />