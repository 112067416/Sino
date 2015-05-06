<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ui:panel id="SjzsPanel" title="查看生产指示说明" popup="true" display="false" closable="true" movable="true">
	<table width="96%" align="center" class="form" style="margin-top: 10px;">
		<colgroup>
			<col width="15%"><col>
		</colgroup>
		<tr>
			<th>附页</th>
			<td>
					<c:forEach varStatus="stat" var="kpno" items="${vo.kpnos }">
						<span class="link1" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span>
					</c:forEach>
			</td>
		</tr>
		<tr>
			<th>业连</th>
			<td>
				<c:forEach varStatus="stat" var="ylno" items="${vo.ylnos }">
						<span class="link1" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span>
					</c:forEach>
			</td>
		</tr>
		<tr>
			<th>SL附加指示</th>
			<td>${vo.bz2 }</td>
		</tr>
		<tr>
			<th>SL和木工所附加指示</th>
			<td>${vo.bz3 }</td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="关闭(C)" onclick="coco.hidePage('SjzsPanel');;" accesskey="c" />
	</div>
	<br />
</ui:panel>