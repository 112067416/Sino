<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="DataTbl" width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
<colgroup><col width="5%" /><col width="6%" /><col width="8%" /><col width="8%" /><col width="4%" /><col width="9%" />
	<col width="11%" /><col width="6%" /><col width="5%" /><col width="4%" /><col width="4%" />
	<col width="4%" /><col width="6%" /><col width="8%" /><col width="4%" /><col width="4%" /><col width="4%" /></colgroup>
<tr>
<th>序号</th>
<th>指示No</th>
<th>订货No</th>
<th>生产线别</th>
<th>操作</th>
<th>用户略称</th>
<th>尺寸</th>
<th>附着量</th>
<th>运用规格</th>
<th>表面</th>
<th>内径</th>
<th>总重量</th>
<th>垫木状态</th>
<th>作成时间</th>
<th>紧急</th>
<th>状态</th>
<th>备注</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${page.datas }">
<tr xu.id="${item.zsno }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  <c:if test="${item.ycaiStat=='1'}">style="background-color:#FFD39B;"</c:if>  onmouseover="overTr(this)" onmouseout="outTr(this)" >	
<td align="center"><ui:number name="sort" maxlength="4" scale="4" precision="2" value="item.sort"/></td>	
<td>${item.zsno }<input type="hidden" name="$zsno" value="${item.zsno }" /></td>
<td>${item.dhno }</td>
<td><f:v ql="select name from Scxbpz where code=?" value="item.shch" /></td>
<td><f:kv value="item.caoz" list="'C':'卷材','S':'板材'"  /></td>
<td>${item.abbr }</td>
<td>${item.zpcc }</td>
<td>${item.fugm}</td>
<td>${item.yuny }</td>
<td>${item.face }</td>
<td><sys:codeLabel key="item.njno" code="#020"/></td>
<td><f:v value="item.allzpzl" format="3"/></td>
<td><c:if test="${item.ycaiSfdmCount==item.ycaiAllCount}"><font color='red'>已完成</font></c:if></td>
<td><f:v value="item.crea" format="yyyy-MM-dd" /></td>
<td><c:if test="${item.ycaijinjCount==item.ycaiAllCount}"><font color='red'>是</font></c:if></td>
<td><font color="red" size="4">${item.ywcYcaiCount }</font>/${item.ycaiAllCount }</td>
<td>${item.remk }</td>
</tr>
</c:forEach>
</table>
<f:paged />