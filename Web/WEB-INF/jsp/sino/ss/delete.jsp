<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/print.js"></script>
		<script type="text/javascript">
		<!--
		//表格表单
		var tblform = new TableForm("DataTbl",'TempTbl');
		// 订正检查
		function dzCheck() {
			var obj = document.getElementById("pileno");
			var pileno=obj.value;
			if(pileno == null || pileno.length == 0) return;
			if(pileno=="") return;
			var ajax = new Cocoajax();
			ajax.dataDiv = DataDiv; 
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				DataDiv.style.display = "block";
			};
			var postContent="jbno="+pileno;
			ajax.post("deleteCheck.do", postContent);
		}
		
		
		
		//修改操作
		function doSubmit(obj) {
			var form = document.forms["DataForm"];
			if(!cocoform.verify(form)) return false;
			var oTbl = document.getElementById(tblform.id);
			var ajax = new Cocoajax();
			ajax.callback = success;
			var rows = oTbl.rows;
			var rowData = null;
			var index = 0;
			var tablePostContent = "";
			for(var i = 1; i < rows.length; i++) {
				rowData = coco.parseParamByElAttr(rows[i], null, "items["+index+"].");
				if(rowData == "")  continue;
				index++;
				if(tablePostContent.length > 0) tablePostContent += "&";
				tablePostContent += rowData;
			}
			if(tablePostContent == "") {
				alert("没有输入包");
				return false;
			}
			var formPostContent = cocoform.postContent(form);
			if(!window.confirm("确定删除吗?")) return;
			ajax.post("update.do", tablePostContent + "&" + formPostContent);
			
		}
		//删除
		function doDel() {
			if(!confirm("确定删除吗?")) return false;
			var obj = document.getElementById("pileno");
			var jbno=obj.value;
			if(jbno=="") {
				alert("卷板号为空");
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <=0) {
					coco.alert(this.msg);	
				}
				alert("删除制品成功");
				var obj = document.getElementById("pileno");
				obj.value="";
				DataDiv.style.display = "none";
			};
			ajax.post("delete.do", "jbno=" + jbno);
		}
		
		
		// 更新成功
		function success() {
			if(this.code < 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			var obj = document.getElementById("pileno");
			obj.value="";
			DataDiv.style.display = "none";
		}
		
		
		
		function addRow() {
			tblform.insertEmptyRow();
		}
		
		function changeItemZshu(el) {
			if(el.value == "" || coco.getAttr(el, "xu.value") == el.value) return;
			var row = el.parentNode.parentNode;
			coco.setAttr(row, "xu.v.zshu", el.value);
			var oTbl = document.getElementById(tblform.id);
			var ajax1 = new Cocoajax();
			ajax1.callback = build;
			var rows = oTbl.rows;
			var rowData = null;
			var index = 0;
			var postContent = "";
			for(var i = 1; i < rows.length; i++) {
				rowData = coco.parseParamByElAttr(rows[i], null, "items["+index+"].");
				if(rowData == "")  continue;
				index++;
				if(postContent.length > 0) postContent += "&";
				postContent += rowData;
			}
			if(postContent != "") {
				ajax1.post("build.do", postContent);
			}
			
		}
		function changeData(el) {
			
			if(el.value == "" || coco.getAttr(el, "xu.value") == el.value) return;
			var row = el.parentNode.parentNode;
			var oTbl = document.getElementById(tblform.id);
			//若数据不是首行，则匹配首行信息
			if(oTbl.rows[1] != row) {
				
				header = coco.parseParamByElAttr(oTbl.rows[1]);
				if(header == "") {
					alert("请先输入首行信息");
					el.value = "";
					return;
				}
			}
			for(var i = 1; i < oTbl.rows.length; i++) {
				if(row != oTbl.rows[i] && el.value == oTbl.rows[i].cells[1].childNodes[0].value) {
					alert("该制品号已经输入，请更换别的制品号");
					el.value = "";
					return;
				}
			}
			//获取数据
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code < 0) {
					coco.alert(this.msg);
					el.value = "";
					return;
				}
				var obj = null;
				eval("obj = " + this.result);
				fillData(row, obj);
				showBuid(oTbl);
				
			};
			var content = "newJbno="+el.value;
			if(header.length > 0) {
				content += "&" + header;
			}
			ajax.post("fetchItem.do", content);
		}
		 function showBuid(oTbl){
	        	var ajax1 = new Cocoajax();
				ajax1.callback = build;
				var rows = oTbl.rows;
				var rowData = null;
				var index = 0;
				var postContent = "";
				for(var i = 1; i < rows.length; i++) {
					rowData = coco.parseParamByElAttr(rows[i], null, "items["+index+"].");
					if(rowData == "")  continue;
					index++;
					if(postContent.length > 0) postContent += "&";
					postContent += rowData;
				}
				if(postContent != "") {
					ajax1.post("build.do", postContent);
				}
				showDataArea();
	        }
		function showDataArea() {
			var oTbl = document.getElementById(tblform.id);
			var rows = oTbl.rows;
			var hasData = false;
			for(var i = 1; i < rows.length; i++) {
				if(rows[i].cells[1].childNodes[0].value != "") {
					hasData = true;
					break;
				}
			}
			document.getElementById("DataDiv").style.display = hasData ? "block" : "none";
		}
		function fillData(row, obj) {
			coco.setParamInElAttr(row, obj);
			var oJbno = row.cells[1].childNodes[0];
			oJbno.className = "form-readonly normal";
			oJbno.readOnly = true;
			row.cells[2].childNodes[0].value = obj.zshu != null ? obj.zshu : "";
			row.cells[3].innerHTML = obj.dhno != null ? obj.dhno : "";
			row.cells[4].innerHTML = obj.abbr != null ? obj.abbr : "";
			row.cells[5].innerHTML = obj.deng != null ? obj.deng : "";
			row.cells[6].innerHTML = obj.size != null ? obj.size : "";
			row.cells[7].innerHTML = obj.qqdm != null ? obj.qqdm : "";
			row.cells[8].innerHTML = obj.qqd2 != null ? obj.qqd2 : "";
			row.cells[9].innerHTML = obj.qqd3 != null ? obj.qqd3 : "";
			var kpnsHtml = "";
			if(obj.kpns != null) {
				for(var i = 0; i < obj.kpns.length; i++) {
					if(kpnsHtml.length >0) kpnsHtml += '<br/>';
					kpnsHtml += '<span class="link" style="padding-left: 10px;" onclick="view(\''+obj.kpns[i]+'\')">'+obj.kpns[i]+'</span>';
				}
			}
			row.cells[10].innerHTML = kpnsHtml;
			var ylnsHtml = "";
			if(obj.ylns != null) {
				for(var i = 0; i < obj.ylns.length; i++) {
					if(ylnsHtml.length >0) ylnsHtml += '<br/>';
					ylnsHtml += '<span class="link" style="padding-left: 10px;" onclick="view(\''+obj.ylns[i]+'\')">'+obj.ylns[i]+'</span>';
				}
			}
			row.cells[11].innerHTML = ylnsHtml;
		}

		function build() {
			if(this.code < 0) {
				coco.alert(this.msg);
				return;
			}
			var obj = null;
			eval("obj = "+this.result);
			var form = document.forms["DataForm"];
			form.elements["zshu"].value = obj.jszs != null ? obj.jszs : "";
			form.elements["dhno"].value = obj.dhno != null ? obj.dhno : "";
			form.elements["sjzl"].value = "";
			form.elements["size"].value = obj.size != null ? obj.size : "";
			form.elements["jszl"].value = obj.jszszl != null ? obj.jszszl : "";
		}
		
		function removeRow(el) {
			tblform.removeRow('ids');
			tblform.formatTblForm("items",1);
			var oForm = el.form;
			oForm.action = "build.do";  
			
		    cocoform.submit(oForm,build);
		}
		
		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		  	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		//--><c:if test="${pileno!=null}">onload="doCreate()"</c:if>
		</script>
		
	</head>
	<body <c:if test="${pileno!=null}">onload="dzCheck()"</c:if>>
	<ui:print id="lodop"/>		
	<ui:page title="PILE删除">
		<table width="96%" align="center" class="form">
			<colgroup>
				<col />
				<col />
				
			</colgroup>
			<tr>
				
				<th>PILE No.</th>
				<td><ui:input id="pileno" name="pileno" value="pileno" maxlength="11" title="输入实绩后的制品号" onkeydown="if(event.keyCode == 13) dzCheck();" /></td>
			</tr>
		</table>
		<div id="DataDiv" style="width: 100%; display: none;"></div>
	</ui:page>
	<table id="TempTbl" style="display: none;">
			<tr>
				<td><input type="checkbox" name="ids" /></td>
				<td><ui:input name="jbno" maxlength="12" onblur="changeData(this);"  imeMode="false" validate="true" prop="type:abcn;" cssStyle="color:#00CC00;" /></td>
				<td><ui:int name="zshu" maxlength="5" onclick="coco.setAttr(this,'xu.value', this.vlaue);" onblur="changeItemZshu(this);" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</body>
</html>