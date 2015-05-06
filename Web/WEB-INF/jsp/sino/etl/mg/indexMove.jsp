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
		
	
		// 订正
		function doDz() {
			parent.cocoframe.open("dz", "端板订正", "/sino/dbgl/toDz.do?jbno="+contextmenu.selectedId(),null,null, true);
		}	
		//保存排序
		function saveMove(){
			var form = document.forms["PageForm_page"];
			var els = form.elements["ids"];
			var content = "";
			if(els.length > 0) {
				for(var i = 0; i < els.length; i++) {
					if(els[i].checked) content += "&ids=" + encodeURIComponent(els[i].value);
				}
			}
			else {
				if(els.checked)  content += "&ids=" + encodeURIComponent(els.value);
			}
			if(content == "") {
				alert("请选择数据行");
				return;
			}
			 var ban=form.elements["ban"].value;
			 var zu=form.elements["zu"].value;
			 var dann=form.elements["dann"].value;
			 var ss;
			 if(ban==""){
				 alert("班别不能为空");
				 return;
			 }
             if(zu==""){
            	 alert("组不能为空");
            	 return;
			 }
             if(dann==""){
            	 alert("操作员不能为空");
            	 return;
             }
			if(!confirm("确定保存吗?")) return false;
			var oTbl = document.getElementById("DataTbl");
			var rows = oTbl.rows;
			var contentSort = "";
			for(var i = 1; i < oTbl.rows.length; i++) {
				if(oTbl.rows[i].cells[0].childNodes[0].checked){
					if(oTbl.rows[i].cells[6].childNodes[0].value==""||oTbl.rows[i].cells[7].childNodes[0].value==""){
						alert("第"+i+"行存在空值");
						return;
						
					}
					if(parseInt(oTbl.rows[i].cells[5].innerHTML)<parseInt(oTbl.rows[i].cells[6].childNodes[0].value)){
						alert("第"+i+"行移出量超过库存");
						return;
					}
					contentSort += "&sorts=" +coco.getAttr(oTbl.rows[i], "xu.id")+"#"+oTbl.rows[i].cells[6].childNodes[0].value+"#"+oTbl.rows[i].cells[7].childNodes[0].value;
				}
				
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code>0){
					alert("保存成功");
					cocopage.query();
				}
				else{
					coco.alert(this.msg);
				}
			};
			
			 ss="&ban="+ban+"&zu="+zu+"&dann="+dann;
			ajax.post("saveMove.do", contentSort.substring(1)+ss);
			//var tableform = new TableForm("DataTbl");
			
			
		}
		//清空
		function doQC(){
			var form = document.forms["PageForm_page"];
			form.reset();
		}
			//-->
		</SCRIPT>
	</head>
	<body>
<ui:page title="垫木库存整理">
		<f:page action="indexMove.do" file="listMove.jsp">
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
			<input type="button" class="button" value="清空" onclick="doQC();" />
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			班<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  value="ban" headerText="" headerValue="" />
			组<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"    value="zu" headerText="" headerValue="" />
			操作员<ui:input name="dann"  maxlength="10" />
			
			<input type="button" id="pais" class="button" value="保存移动" onclick="saveMove();" />
			</div>
		</f:page>
	</ui:page>

</body>
</html>