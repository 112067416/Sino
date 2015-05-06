<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src='<%=request.getContextPath() %>/js/calendar.js'></script>
	<script type="text/javascript">
	<!--
	
	var menuId = null;
	contextmenu.putMenus("menu", [["查看指示书", "view.gif", "doView()"]]);

	// 查看指示书详细
	function doView(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var menuId = coco.getAttr(oTr, "key1");
		//parent.cocoframe.open("SLZZS", "SL指示书详情", "/sino/zs/view.do?zsno="+menuId, true,null,true);
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ViewArea");
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			coco.showPage('View',{center:true,top:50,width:"80%"});
		};
		ajax.post("view.do", "zsno="+ menuId);
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
<ui:page title="已完成指示书查询">
	<f:page file="listYwc.jsp" action="indexYwc.do">
		<table width="96%" align="center" class="form">
			<colgroup>
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
				<th>指示书No.</th>
				<td><ui:input name="zsno" maxlength="6" /></td>
				<th>订货NO.</th>
				<td><ui:input name="dhno" maxlength="9" /></td>
				<th>卷板NO.</th>
			    <td><ui:input name="jbno"  maxlength="11" /></td>
				<th>作成时间</th>
				<td><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
			</tr>
			<tr>
				<th>完成时间</th>
				<td colspan="7"><ui:date name="zsny_begin" value="page.zsny_begin" /> 至 <ui:date name="zsny_end" value="page.zsny_end" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" onclick="cocopage.query();" value="查询(Q)" accesskey="q">
		</div>
	</f:page>
	<br/>
</ui:page>
<ui:panel id="View" title="指示书详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
</body>
</html>