<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui" %>
<%@ taglib uri="/f" prefix="f"%>
<%@ taglib uri="/sys" prefix="sys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		
         contextmenu.putMenus("menu", [
		                              
		                              ["查看指示书", "view.gif", "doShowZs()"]
		                            
		                             ]);
	
		
		// 查看指示书详细
		function doShowZs() {
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ViewArea");
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('View',{center:true,top:50,width:"80%"});
			};
			ajax.post("view.do", "zsno="+ contextmenu.selectedId());
		}
		//查询
		function doquery() {
			cocopage.refresh();
			
		}
		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		 	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		//-->
		</script>
		
	</head>
<body>
		
<ui:page title="指示书已完成">
   <f:page action="indexYwc.do" file="listYwc.jsp">
		<table width="96%" align="center" style="margin: 10px 0;" class="form">
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
			</colgroup>
			<tr>
				<th>指示书NO</th>
			<td><ui:input name="zsno" maxlength="6" title="指示书No" /></td>
			<th>订货NO.</th>
			<td><ui:input name="dhno" maxlength="9" /></td>
			<th>卷板NO.</th>
			<td><ui:input name="jbno"  maxlength="11" /></td>
			<th>用户略称</th>
			<td><ui:input name="abbr" maxlength="8" title="用户略称" /></td>
			
			</tr>
			<tr>
			<th>作成时间</th>
			<td colspan="2"><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
			<th>完成时间</th>
			<td colspan="2"><ui:date name="zsny_begin" value="page.zsny_begin" /> 至 <ui:date name="zsny_end" value="page.zsny_end" /></td>
		</tr>
		</table>
		<div class="submit">
		<input type="button" class="button" value="查询" onclick="cocopage.query();" />	
	</div>
	</f:page>
</ui:page>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
	</body>
</html>