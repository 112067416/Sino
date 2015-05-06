<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>合并包</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--//

		//表格表单
		var tblform = new TableForm("DataTbl",'TempTbl');
		
		function addRow() {
			tblform.insertEmptyRow();
			var oTbl = document.getElementById(tblform.id);
			var rows = oTbl.rows;
		}
		function changeData(el) {
		
			if(el.value == "" || coco.getAttr(el, "xu.value") == el.value) return;
			var row = el.parentNode.parentNode;
			var oTbl = document.getElementById(tblform.id);
			var header = "";
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
			};
			var content = "newJbno="+el.value;
			if(header.length > 0) {
				content += "&" + header;
			}
			ajax.post("fetchItem.do", content);
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
		
		function build() {
			if(this.code < 0) {
				coco.alert(this.msg);
				return;
			}
			var obj = null;
			eval("obj = "+this.result);
			var form = document.forms["DataForm"];
			form.elements["jbno"].value = obj.jbno != null ? obj.jbno : "";
			form.elements["zshu"].value = obj.jszs != null ? obj.jszs : "";
			form.elements["sjzl"].value = obj.sjzl != null ? obj.sjzl : "";
			form.elements["size"].value = obj.size != null ? obj.size : "";
			form.elements["jszl"].value = obj.jszszl != null ? obj.jszszl : "";
		}
		
		function removeRow() {
			tblform.removeRow('ids');
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

		//PILE生成确认
		function doCreate() {
			var form = document.forms["DataForm"];
			if(!cocoform.verify(form)) return false;
			var oTbl = document.getElementById(tblform.id);
			var ajax = new Cocoajax();
			ajax.callback = create;
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
			ajax.post("create.do", tablePostContent + "&" + formPostContent);
		}
		
		function create() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("PILE创建成功，新制品号："+this.result);
			tblform.removeAll();
			cocoform.clear("DataForm", ["ban", "zu"]);
			
		}
		
		//业连号明细
		function link(ylno) {
			
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
		<ui:page title="PILE生成">
			<div>
			<table id="DataTbl" width="96%" align="center" class="pagination">
				<colgroup>
					<col width="2%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="5%"/>
					<col width="10%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="10%"/>
					<col width="10%" />
				</colgroup>
				<tr>
					<th><input type="checkbox" onclick="coco.selAll('ids', this);" /></th>
					<th>中间品包名</th>
					<th>张数</th>
					<th>订货No.</th>
					<th>用户略称</th>
					<th>等级</th>
					<th>要求尺寸</th>
					<th>主缺陷</th>
					<th>缺陷2</th>
					<th>缺陷3</th>
					<th>KP别纸</th>
					<th>业联No.</th>
				</tr>
			</table>
			<table width="96%" align="center" >
				<tr>
					<td align="left">
						<input type="button" class="button" value="新增包(A)" accesskey="a" onclick="addRow();" />
						<input type="button" class="button" value="删除选中包(D)" accesskey="d" onclick="removeRow();" />
					</td>
				</tr>
			</table>
			</div>
		<div id="DataDiv" style="display: none;">
		<form name="DataForm" action="create.do" method="post" >
			<fieldset class="group">
			<legend>生成的新制品信息</legend>
			<table width="98%" align="center" class="form">
				<colgroup>
					<col /><col /><col /><col /><col /><col />
				</colgroup>
				<tr>
					<th>新制品号</th>
					<td><ui:input name="jbno" readonly="true" maxlength="11" /></td>
					<th>张数</th>
					<td><ui:int name="zshu" maxlength="4" /></td>
					<th>实际重量</th>
					<td><ui:input name="sjzl" maxlength="5" required="true" /></td>
				</tr>
				<tr>
					<th>制品尺寸</th>
					<td><ui:input name="size" maxlength="20" readonly="true" /></td>
					<th>计算重量</th>
					<td colspan="3"><ui:input name="jszl" maxlength="5" readonly="true" /></td>
				</tr>
				<tr>
					<th>产量代码</th>
					<td><ui:input name="chan" maxlength="1" required="true" /></td>
					<th>等级</th>
					<td><ui:input name="deng" maxlength="3" required="true" /></td>
					<th>缺陷</th>
					<td><ui:input name="qqdm" maxlength="2" required="true" /></td>
				</tr>
				<tr>
					<th>检定员</th>
					<td><ui:input name="jdyn" maxlength="2" required="true" /></td>
					<th>计数员</th>
					<td><ui:input name="jsyn" maxlength="2" required="true" /></td>
					<th>是否翻转</th>
					<td><ui:input name="sffz" maxlength="1" required="true" /></td>
				</tr>
				<tr>
					<th>组别</th>
					<td><sys:codeSelect code="#SINO_ZU" name="zu" value="$group" /></td>
					<th>班别</th>
					<td><sys:codeSelect code="#SINO_BAN" name="ban" value="$shift" /></td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
			</table>
			</fieldset>
			<div class="submit" style="margin-top: 10px;">
				<input type="button" class="button" value="实绩确认(S)" accesskey="s" onclick="doCreate();" />
			</div>
		</form>
			</div>
		<br/>
		</ui:page>
		<table id="TempTbl" style="display: none;">
			<tr>
				<td><input type="checkbox" name="ids" /></td>
				<td><ui:input name="jbno" maxlength="12" onclick="coco.setAttr(this,'xu.value', this.vlaue);"  onkeydown="if(this.readOnly)return;if (event == null || event.keyCode != 13)return; changeData(this);"  imeMode="false" validate="true" prop="type:abcn;" cssStyle="color:#00CC00;" /></td>
				<td><ui:int name="zshu" maxlength="5" onclick="coco.setAttr(this,'xu.value', this.vlaue);" onblur="changeItemZshu(this);"/></td>
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