<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib 
	uri="/ui" prefix="ui"%><%@ taglib 
	uri="/f" prefix="f"%><%@ taglib 
	uri="/sys" prefix="sys"%><%@ taglib 
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>端板维护</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		//删除
		function doDelete() {
			if(!window.confirm("确定要删除该条记录吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete!"+contextmenu.selectedId()+".do", null, "POST");
		}
		//修改
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var oForm = document.forms["DataForm"];
			var vKey1 = coco.getAttr(oTr, "xu.id");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				cocoform.fillResult(oForm, this.result);
				coco.showPage("Detail",{center:true,top:50,width:"80%"});
			};
			ajax.post("get!"+vKey1+".do", null, "POST");
		}
		//保存成功
		function success() {
			alert("保存成功");
			coco.hidePage('Detail');
			cocopage.refresh();
		}
		//清空
		function doQC(){
			var oForm = document.forms["DataForm"];
			var id=oForm.elements["id"].value;
			oForm.reset();
			oForm.elements["id"].value=id;
		   
		}
		//清空
		function doPageQC(){
			var form = document.forms["PageForm_page"];
			form.reset();
		}
			//-->
		</SCRIPT>
	</head>
	<body>
<ui:page title="垫木库存查询">
		<f:page action="index!${type}.do" file="list.jsp">
			<table width="100%" class="form">
				<colgroup>
				<col  />
				<col  />
				<col  />
				<col  />
				<col  />
				<col  />
				<col  />
				<col  />
				</colgroup>
				<tr>
					<th style="text-align: right;">尺寸</th>
					<td>
				<ui:number name="kuan" title="尺寸.宽" scale="6" precision="2"  positive="true" maxlength="7"  />*
				<ui:number name="cang" title="尺寸.长" scale="6" precision="2" positive="true" maxlength="7" /></td>
					<th style="text-align: right;">垫木方向</th>
				<td ><ui:input name="dmfx"  maxlength="1" /></td>
				<th style="text-align: right;">捆包形式</th>
				<td ><ui:input name="kbao"   maxlength="3" /></td>
				<th style="text-align: right;">库位</th>
				<td ><ui:input name="kw"  maxlength="3" /></td>
					<th>创建时间</th>
			<td ><ui:date name="crea_begin" value="page.crea_begin" /> 至 <ui:date name="crea_end" value="page.crea_end" /></td>
				   </tr>
				
			</table>
			<div class="submit">
			<input type="button" class="button" value="清空" onclick="doPageQC();" />
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			</div>
		</f:page>
	</ui:page>
	<ui:panel id="Detail" title="详细信息" popup="true" display="false" closable="true" >
	<form name="DataForm" xu.ajax="true" xu.s="success()" method="post" action="update.do" >
	<input type="hidden" name="id" />
		<table width="96%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="15%" />
				<col width="20%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
			
			<tr>
				<th style="text-align: right;">尺寸</th>
				<td> 
				<ui:number name="kuan" title="尺寸.宽" scale="6" precision="2"  positive="true" maxlength="7" required="true"  />*
				<ui:number name="cang" title="尺寸.长" scale="6" precision="2" positive="true" maxlength="7" required="true" /></td>
				<th style="text-align: right;">垫木方向</th>
				<td ><ui:input name="dmfx"  maxlength="1"  /></td>
				<th style="text-align: right;">捆包形式</th>
				<td ><ui:input name="kbao" required="true"  maxlength="3" /></td>
				<th style="text-align: right;">库位</th>
				<td colspan="2"><ui:input name="kw"  maxlength="3" required="true"  /></td>
			</tr>
			
		</table>
		
			<div class="opt-btm">
			<input type="button" class="button" value="清空" onclick="doQC();" />
			 <input type="button" class="button" value="保 存" onclick="cocoform.submit(this, null, '确定保存吗?');" /></div>
		</form>
		
</ui:panel>
<script type="text/javascript">
		var type = '${type}';
		if(type == "2" ){
			contextmenu.putMenus("menu", [
			                              ["删除", "view.gif", "doDelete()"],
			                              ["修改", "view.gif", "toModify()"]
			                            
			                             ]);
			 document.getElementById("pais").style.display = "none";
		}
		if(type == "1" ){
			contextmenu.putMenus("menu", [
                                           ["", "view.gif", ""]
			                             ]);
		}
		</script>
</body>
</html>