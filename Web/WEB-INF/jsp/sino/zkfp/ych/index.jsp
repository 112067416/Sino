<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
  taglib uri="/ui" prefix="ui" %><%@ 
  taglib uri="/f" prefix="f"%><%@ 
  taglib uri="/WEB-INF/classes/META-INF/sys.tld" prefix="sys"%><%@ 
  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看制品详情", "view.gif", "viewZp()"]]);
		// 查询
		function doQuery() {
			var oForm = document.forms["PageForm_page"];
			var dhno = oForm.elements["dhno"].value;
			var line = oForm.elements["line"].value;
			var jbno = oForm.elements["jbno"].value;
			if((dhno == null || dhno == "") && (line == null || line == "") && (jbno == null || jbno == "")) {
				alert("请输入订货No.或现品No.");
				return;
			}
			oForm.elements["ids"].value = "";
			oForm.elements["dhnoLine"].value = dhno + line;
			PageData_page.style.display = "block";
			cocopage.query();
		}
		//余材化处理
		function toYch() {
			var oForm = document.forms["PageForm_page"];
			var ids = oForm.elements["ids"].value;
			if(ids == null || ids.length == 0) {
				alert("请选择要做余材化操作的制品");
				return;
			}
			if(!confirm("确定要余材化吗?")) return ;
			var postContent = coco.parseParam("ids", ids);
			coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				coco.hideAlert();
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("余材化成功");
				oForm.elements["ids"].value = "";
				cocopage.refresh();
			};
			ajax.post("ych.do", postContent);
		}
		//查看现品详情
		function viewZp(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "xu.id");
			var ajax = new Cocoajax();
			var Shdz = document.getElementById("ZpDiv");
			ajax.dataDiv = Shdz; 
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("ZpArea", {center : true, top : 30, width : "80%" });
			};
			ajax.post("viewZp.do", "jbno="+key1);
		}
		//返回
		function toReturn() {
			var oForm = document.forms["PageForm_page"];
			oForm.elements["dhno"].value = "";
			oForm.elements["line"].value = "";
			oForm.elements["jbno"].value = "";
			oForm.elements["dhnoLine"].value = "";
			oForm.elements["ids"].value = "";
			PageData_page.style.display = "none";
		}

		//复选框选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
			//选中装箱指示书号
			var v = obj.value;
			
			var oForm = document.forms["PageForm_page"];
			var ids = oForm.elements["ids"].value;
			var rgExp;
			if(obj.checked) {
				if(ids == null || ids.length == 0) {
					oForm.elements["ids"].value = v;
					return;
				}
				oForm.elements["ids"].value = ids.replace(/(,)+$/g,'') + ',' + v;
			} else {
				var $v = v.replace(/^\(/gi,'\\(').replace(/\)/gi,'\\)');
				rgExp = new RegExp($v+',|('+$v+'$)' , "g");
				oForm.elements["ids"].value = ids.replace(rgExp, '');
			}
		}
		//全选或全不选
		function checkAll(obj) {
			var oForm = document.forms["PageForm_page"];
			var chks = oForm.elements["id"];
			if(chks == null )  return;
			if(chks.tagName != null) {
				if(obj.checked ^ chks.checked) {
					chks.checked = obj.checked;
					doCheck(chks);
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(obj.checked ^ el.checked) {
						el.checked = obj.checked;
						doCheck(el);
					}
					continue;
				}
			}
		}
		//自动设置复选框的checked值
		function setChecked() {
			var oForm = document.forms["PageForm_page"];
			var ids = oForm.elements["ids"].value;
			if(ids == null || ids.length == 0) {
				return;
			}
			var chks = oForm.elements["id"];
			if(chks == null) return;
			if(chks.tagName != null) {
				if(ids.indexOf(chks.value) >= 0) {
					chks.checked = true;
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(ids.indexOf(el.value) >= 0) {
						el.checked = true;
					}
				}
			}
		}
		//-->
		</script>
	</head>
	<body>
	<ui:page title="余材化处理">
		<f:page action="index.do" file="list.jsp"  >
		<input type="hidden" name="dhnoLine" /><input type="hidden" name="ids" />
		<table width="96%" align="center" class="form">
			<colgroup><col width="15%" /><col width="35%" /><col width="15%" /><col width="35%" /></colgroup>
			<tr>
				<th>订货No.</th>
				<td><ui:input name="dhno" title="订货DB的订货NO" maxlength="7" />-<ui:input name="line" title="行号" maxlength="2" onkeydown="if(window.event.keyCode == 13) doQuery();" /></td>
				<th>现品No.</th>
				<td><ui:input name="jbno" maxlength="11" onkeydown="if(window.event.keyCode == 13) doQuery();" /></td>
			</tr>
		</table>
		<div class="submit"> <input type="button" class="button" value="查 询" onclick="doQuery();" /> <input type="button" class="button" value="余材化" onclick="toYch();" /> <input type="button" class="button" value="返  回" onclick="toReturn();" /></div>
		</f:page>
	</ui:page>
	<ui:panel id="ZpArea" title="制品详情" display="false" closable="true" popup="true">
	<div id="ZpDiv" class="scroll" style="width:100%;height:400px;"></div>
	</ui:panel>
	</body>
</html>