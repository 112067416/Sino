<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table width="100%" class="form" >
	<colgroup>
		<col width="10%" />
		<col width="40%" />
		<col width="10%" />
		<col width="40%" />
	</colgroup>
	<tr>
		<th>标识</th>
		<td><c:if test="${entity != null }">
		<input type="hidden" name="$type" value="modify" />
		<ui:input name="id" cssStyle="width:96%" value="entity.id" readonly="true" lowToUp="false" />
		</c:if><c:if test="${entity == null }">
		<ui:input name="id" cssStyle="width:96%" lowToUp="false" />
		</c:if></td>
		<th>名称</th>
		<td><ui:input name="name" cssStyle="width:96%" lowToUp="false" value="entity.name" /></td>
	</tr>
	<tr>
		<th>转换类型</th>
		<td><ui:select name="type" list="'0':'电子表格','1':'文本'" value="entity.type" onchange="changeType(this.value);" /></td>
		<th>对象类名</th>
		<td><ui:input name="defaultClass" lowToUp="false" cssStyle="width:96%" value="entity.defaultClass" /></td>
	</tr>
	<tr>
		<th>题头行号</th>
		<td><ui:int name="titleRow" maxlength="4" value="entity.titleRow" /></td>
		<c:if test="${entity != null && entity.type == \"1\" }">
		<th ><span id="SplitHeader" style="visibility:visible;" >题头分割符</span></th>
		<td><ui:input id="SplitText" name="split" lowToUp="false" trim="false" maxlength="10" value="entity.split" cssStyle="visibility:visible;" /></td>
		</c:if>
		<c:if test="${entity == null || entity.type != \"1\"  }">
		<th ><span id="SplitHeader" style="visibility:hidden;" >题头分割符</span></th>
		<td><ui:input id="SplitText" name="split" lowToUp="false" trim="false" maxlength="10" value="entity.split" cssStyle="visibility:hidden;" /></td>
		</c:if>
	</tr>
	<tr>
		<th>起始行号</th>
		<td><ui:int name="first" title="数据转换开始行号，从0算起" maxlength="4" value="entity.first" /></td>
		<th>转换行数</th>
		<td><ui:int name="length" title="数据转换行数，从起始行号算起转换的行数" maxlength="4" value="entity.length" /></td>
	</tr>
</table>
<table width="100%" id="ItemTbl" align=center border=0 cellpadding="0" cellspacing="0" class="pagination">
	<colgroup>
		<col width="5%" />
		<col width="40%" />
		<col width="40%" />
		<col width="15%" />
	</colgroup>
	<tr>
	<th><input type="checkbox" onclick="coco.selAll('ids',this)" /></th>
	<th>字段</th>
	<th>题头</th>
	<th>列号</th>
	</tr>
	<c:if test="${entity != null && entity.fields != null }">
	<c:forEach varStatus="stat" var="item" items="${ entity.fields }">
	<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
	<td><input type="checkbox" name="ids" /></td>
	<td><input type="hidden" name="id" value="${ item.id}" /><ui:input name="field" value="item.field" lowToUp="false" required="true" /></td>
	<td><ui:input name="title" value="item.title" lowToUp="false" /></td>
	<td><ui:int name="index" maxlength="4" value="item.index" title="从0算起，若指定了题头，则列号无效。" /></td>
	</tr>
	</c:forEach></c:if>
</table>