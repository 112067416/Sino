<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib prefix="sino" uri="/sino" %><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>客户付款管理</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看付款发票", "preview.gif", "toView()"],
		                              ["设置调整金额", "settings.gif", "toTzje()"],
		                      		  ["修改", "edit.gif", "toModify()"],
	                    	  		  ["撤消冲帐", "edit.gif", "toCxcz()"],
	                      		      ["删除", "del.gif", "doDel()"]]);
		//设置发票及客户调整金额
		function toTzje(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			 var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					try {
						var form = document.forms["TzjeForm"];
						cocoform.clear(form);
						cocoform.fillResult(form, this.result,null,true);
						coco.showPage("TzjeDetail", {
							center : true,
							top : 120,
							width : "50%"
						});
						
					} catch (e) {
						alert("系统出错:\n" + e.description);
					}
				};
				ajax.post("tzje.do", "id="+key);
		}
		//撤消冲帐
		function toCxcz(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			if(!confirm("确定撤销冲账吗?")) return;
			var postContent = coco.parseParams([{name:"id",value:key}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("撤销冲账成功");
				cocopage.refresh();
			};
			ajax.post("doCxcz.do", postContent);
		}

		//删除
		function doDel(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			var content;
			if(key != null && key.length > 0) {
				content = coco.parseParam("ids", key);
			} else {
				content = cocoform.postCheckValues("PageForm_page","ids","ids");
			}
			if(content == null || content.length == 0){
				alert("请选择要删除的记录");
				return;
			}
		    if(!confirm("确定删除吗?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delete.do", content);
		}
		//修改
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				try {
					var form = document.forms["DataForm"];
					cocoform.clear(form);
					cocoform.fillResult(form, this.result);
					form.elements["$type"].value = "modify";
					coco.showPage("Detail", {center : true, top : 50, width : "90%" });
				} catch (e) {
					alert("系统出错:\n" + e.description);
				}
			};
			ajax.post("load.do", "id="+key1);
		}
		//新增
		function toAdd() {
			var form = document.forms["DataForm"];
			form.elements["id"].value = "";
			form.elements["$type"].value = "";
		//	cocoform.clear(form);
			coco.showPage("Detail",{center:true,top:50,width:"80%"});
		}
		
		//保存操作
		function doSave(oInput) {
			var oForm = oInput.form;
			cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						cocopage.refresh();
						alert(this.msg);
						return;
					}
					cocopage.refresh();
					alert("保存成功");
					//coco.hidePage('Detail');
				}, "是否确定保存?") ;
		}
		//保存调整金额
		function saveTzje(oInput) {
			var form = oInput.form;
			cocoform.submit(form, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("保存成功");
					cocopage.refresh();
					coco.hidePage('TzjeDetail');
				}, "是否确定保存?", null, oInput);
		}
		
		function doPrint(LODOP, content) {
				LODOP.SET_PRINTER_INDEXA("Microsoft office Document Image Writer");
				LODOP.PRINT_INIT("CZGLMX-<%=System.currentTimeMillis()%>");
				//LODOP.SET_PRINTER_INDEXA("DPK510");
				LODOP.SET_PRINT_PAGESIZE(1, 3500, 2794,"A3");
				LODOP.SET_PRINT_STYLE("FontSize",12);
				LODOP.ADD_PRINT_HTM(0, 0, 1500, 1200, content);
				//打印预览
				LODOP.PREVIEW();
				//打印
			//	LODOP.PRINT();
		}

		function toView(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "id0");
			var postContent = coco.parseParam("id", key1);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				var obj;
				eval("obj="+this.result+";");
				var pageSizes = obj.pageSizes;
				toPrint(pageSizes, key1);
			};
			ajax.post(path + "/sino/dy/czmxs.do", postContent);
		}

		function toPrint(pageSizes, id) {
			var pageIndex = 0;
			var LODOP = document.getElementById("lodop");
			postContent = coco.parseParams([{name:"khfkId",value:id},{name:"pages",value:pageSizes},{name:"index",value:pageIndex++}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(LODOP,this.result);
				if(pageIndex < pageSizes) {
					postContent = coco.parseParams([{name:"khfkId",value:id},{name:"pages",value:pageSizes},{name:"index",value:pageIndex++}]);
					ajax.post(path + "/sino/dy/czmx!print.do", postContent);
				} 
			};
			ajax.post(path + "/sino/dy/czmx!print.do", postContent);
		}
		//-->
		</SCRIPT>
	</head>
<body onload="cocopage.query();">
<ui:print id="lodop"/>
<ui:page title="客户付款管理">
<f:page action="indexKhfk.do" file="listKhfk.jsp">
<table class="form" width="100%">
<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="35%" /><col width="15%" /></colgroup>
<tr>
<th style="text-align: left;">用户名称</th>
<td><ui:combobox name="name" items="${fpkhs }" width="300" /></td>
<th style="text-align: left;">付款日期</th>
<td><ui:date name="creaBegin" cssClass="normal" prop="calendar:true;" cssStyle="width: 120;"/>至<ui:date name="creaEnd" cssClass="normal" prop="calendar:true;" cssStyle="width: 120;"/></td>
<th style="text-align: left;">状态&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="stat" list="${stat }" headerText="全部" headerValue="" /></th>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="cocopage.query();"/>
<input type="button" class="button" value="新 增" onclick="toAdd();" />
<input type="button" class="button" value="删 除" onclick="doDel('');" />
<input type="button" class="button" value="打 印" onclick="print('');" />
</div>
</f:page>
</ui:page>
<ui:panel id="Detail" title="编辑信息" popup="true" display="false" closable="true">
<form name="DataForm"  method="post" action="save.do" >
<input type="hidden" name="$type" />
<input type="hidden" name="id"/>
<table width="100%" class="form" >
<colgroup><col width="18%" /><col width="44%" /><col width="18%" /><col width="20%" /></colgroup>
<tr>
<th style="text-align: left;">用户名称</th>
<td><ui:combobox name="name" items="${fpkhs }" width="300" /></td>
<th style="text-align: left;">付款日期</th>
<td><ui:date name="crea"  prop="calendar:true;"  /></td>
</tr>
<tr>
<th style="text-align: left;">付款金额</th>
<td><ui:number name="fkje" required="true" scale="11" precision="2" maxlength="12" /></td>
<td colspan="2">&nbsp;</td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="保   存" onclick="doSave(this);" />
<input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail')" />
</div>
</form>
</ui:panel>
<ui:panel id="TzjeDetail" title="设置调整金额" popup="true" display="false" closable="true">
<form name="TzjeForm" method="post" action="saveTz.do" >
<input type="hidden" name="id"/>
<table class="form" width="100%">
<colgroup><col width="10%" /><col width="90%" /></colgroup>
<tr>
<th style="text-align: left;">调整金额</th> 
<td><ui:number name="tzye" title="调整金额" required="true" scale="11" precision="2" readonly="true"/></td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="保    存" onclick="saveTzje(this);"/>
<input type="button" class="button" value="关    闭" onclick="coco.hidePage('TzjeDetail');" />
</div>
</form>
</ui:panel>
</body>
</html>