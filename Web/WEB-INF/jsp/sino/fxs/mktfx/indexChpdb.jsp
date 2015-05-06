<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/sys" prefix="sys"%><%@ taglib 
uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/message.js"></script>
		<style type="text/css">
		TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
		TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
		TABLE.pagination1 th { font-size: 12px; color: #003449; height: 20px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
		TABLE.pagination1 td { font-size: 13px; color: #333333; height: 17px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
		TABLE.pagination1 tr {background-color: #F7F7F7;}
		TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
		TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
		</style>
		<script type="text/javascript">
		<!--
		//批量删除
		function deletes(el) {
			var form = el.form;
			var ids = form.elements["ids"].value;
			if(ids == null || ids.length == 0) {
				alert("请选择要删除的行");
				return;
			}
			var postContent = coco.parseParam("jbnos", ids);
			if(!confirm("确定删除吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("删除成功");
				form.elements["ids"].value = "";
				cocopage.refresh();
			};
			ajax.post("deleteChpdb.do", postContent);
		}
		
		//新增
		function toAdd() {
			var ajax = new Cocoajax();
			ajax.dataDiv = EditMktDiv;
			ajax.post("editChpdb.do");
			coco.showPage("EditMktArea",{center:true,top:50,width:"85%"});
		}

		//保存
		function doSave(oInput) {
			cocoform.submit(oInput, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					coco.hidePage("EditMktArea");
					cocopage.refresh();
				}, '是否确定保存?');
		}

		function loadChpdb(el) {
			var jbno = el.value.replace(/^\s+|\s+$/gi,'');
			if(jbno == null || jbno.length == 0) {
				alert("Coil No.不能为空");
				return;
			}
			var form = el.form;
			var postContent = coco.parseParam("jbno", jbno);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				cocoform.fillResult(form, this.result);
			};
			ajax.post("loadChpdb.do", postContent);
		}
		
		//维护
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var id = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParam("jbno", id);
			var oForm = document.forms["EditMktForm"];
			var ajax = new Cocoajax();
			ajax.dataDiv = EditMktDiv;
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("EditMktArea",{center:true,top:50,width:"85%"});
				cocoform.fillResult(oForm, this.result);
			};
			ajax.post("editChpdb.do", postContent);
		}


		function doPrint(LODOP, content) {
			//	LODOP.SET_PRINTER_INDEXA("HPrint");
				LODOP.PRINT_INIT("CHPDB-<%=System.currentTimeMillis()%>");
				LODOP.SET_PRINTER_INDEXA("HP LaserJet 5200 Series PCL 5");
			//	LODOP.SET_PRINTER_INDEXA("HP LaserJet Professional P1606dn");
				LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
				LODOP.ADD_PRINT_HTM(10, 0, 1100, 1100, content);
				//打印预览
				LODOP.PREVIEW();
				//打印
				//LODOP.PRINT();
		}

		function toPrint(el) {
			var form = el.form;
			var ids = form.elements["ids"].value;
			if(ids == null || ids.length == 0) {
				alert("请选择要打印的马口铁");
				return;
			}
			var pages = [];
			var jbnos = '';
			var arrs = ids.split(",");
			for(var i = 0; i < arrs.length; i++) {
				jbnos = jbnos + "," + arrs[i];
				if((i + 1) % 13 == 0) {
					pages[pages.length] = jbnos;
					jbnos = '';
				}
			}
			if(jbnos.length > 0) {
				pages[pages.length] = jbnos;
			}
			var index = 0;
			var postContent = coco.parseParam("jbnos", pages[index++]);
			var LODOP = document.getElementById("lodop");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				doPrint(LODOP,this.result);
				if(index < pages.length) {
					postContent =  coco.parseParam("jbnos", pages[index++]);
					ajax.post(path + "/sino/dy/chpdb!print.do", postContent);
				}
			};
			ajax.post(path + "/sino/dy/chpdb!print.do", postContent);
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
		//查询
		function doQuery(el) {
			var form = el.form;
			form.elements["ids"].value = "";
			cocopage.query();
		}

		//-->
		</script>
	</head>
	<body>
<ui:print id="lodop"/>
<ui:page title="出货判定表管理">
<f:page action="indexChpdb.do" file="listChpdb.jsp">
<input type="hidden" name="ids" />
	<table width="96%" align="center" class="form" style="margin-top: 10px;">
		<colgroup><col width="10%" /><col width="40%" /><col width="10%" /><col width="40%" /></colgroup>
		<tr>
			<th style="text-align: left;">创建日期</th>
			<td><ui:date name="creaBegin" prop="calendar: true;" />至<ui:date name="creaEnd" prop="calendar: true;" /></td>
			<th style="text-align: left;">Coil No.</th>
			<td><ui:input name="jbno" maxlength="7" /> </td>
		</tr>
	</table>
	<div class="submit">
 	<input type="button" class="button" value="查   询" onclick="doQuery(this);"/>
	<input type="button" class="button" value="新   增" onclick="toAdd()" />
	<input type="button" class="button" value="删   除" onclick="deletes(this)" />
	<input type="button" class="button" value="打   印" onclick="toPrint(this)" />
	</div>
</f:page>
</ui:page>
<ui:panel id="EditMktArea" title="编辑高耐蚀性马口铁出货判定表(#75以上)" display="false" closable="true" popup="true">
<form name="EditMktForm" method="post" action="saveChpdb.do">
<div id="EditMktDiv" class="scroll" style="width:100%;height:290px;"></div>
</form>
</ui:panel>
</body>
</html>