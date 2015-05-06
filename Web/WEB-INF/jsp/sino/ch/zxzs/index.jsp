<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%>
	<%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>${title }</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			var type = '${type}';
			if(type == "1" ){
				contextmenu.putMenus("menu", [["针式打印", "view.gif", "print()"],["修改", "edit.gif", "toModify()"],["查看", "edit.gif", "toView()"],["作废", "del.gif", "toDel()"]]);
			}
			if(type == "2" ){
				contextmenu.putMenus("menu", [["针式打印", "view.gif", "print()"],["修改", "edit.gif", "toModify()"],["查看", "edit.gif", "toView()"]]);
			}
			//修改装箱指示书
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				if(vKey1== null ){
					alert("参数为空");
                  return ;
				}
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					cocoform.fillResult("DataForm", this.result);
					coco.showPage("Detail",{center:true,top:80,width:"90%"});
				};
				ajax.post("getForJs.do", coco.parseParams([{name : "zxno",value: vKey1}]));
			}
			//装箱指示书打印
			function print(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				if(vKey1 == null ) {
					alert("参数为空");
                  	return ;
				}
				var key = coco.parseParams([{name:"zxno",value:vKey1}]);
				document.getElementById("PrintFrame").src = path + "/sino/dy/chdy/zxzs.do?"+key;
			}
			//作废装箱指示书
			function toDel(oTr) {
				if(!confirm("确定作废吗?")) return false;
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("操作成功");
					cocopage.refresh();
				};
				ajax.post("del.do", coco.parseParams([{name : "zxno",value: vKey1}]));
			
			}
            //查看装箱指示书明细
			function toView(oTr){
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key1");
				if(vKey1 == null ){
					alert("参数为空");
					return ;
				}
				parent.cocoframe.open("view", "查看装箱指示书明细", "/sino/ch/zxzs/view.do?zxno="+vKey1, true,null,true);
			}
			//
			function success(){
				alert("修改成功");
				coco.hidePage('Detail');
				cocopage.refresh();
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
					rgExp = new RegExp(v+',|('+v+'$)' , "g");
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

			//打印制品商品检查证明书
			function printA4(oTr) {
				var oForm = document.forms["PageForm_page"];
				var ids = oForm.elements["ids"].value;
				if(ids == null || ids.length == 0) {
					alert("请选择要打印的装箱指示书");
					return;
				}
				if(!confirm("确定打印装箱指示书No." + ids + "吗?")) return;
				var postContent = coco.parseParam("ids", ids);
				var ajax = new Cocoajax();
				ajax.callback = function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						var obj;
						eval("obj="+this.result+";");
						autoPrintA4(obj.uuids, obj.pageSizes);
				};
				ajax.post(path + "/sino/dy/chdy/zxzsA4.do", postContent);
			}
			
			function autoPrintA4($uuids, $pageSizes) {
				var uuids = $uuids.split(",");
				var pageSizes = $pageSizes.split(",");
				var LODOP = document.getElementById("lodop");
				var index = 0, uuid = uuids[0], pageSize = pageSizes[0], pageIndex = 0, num = 0;
				index++;
				var postContent = coco.parseParams([{name:"zxno",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					num++;
					doPrint(this.result, LODOP);
					if(pageIndex < pageSize) {
						postContent = coco.parseParams([{name:"zxno",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
						ajax.post(path + "/sino/dy/chdy/zxzs!printA4.do", postContent);
					} else {
						if(index < uuids.length) {
							uuid = uuids[index];
							pageSize = pageSizes[index];
							index++;
							pageIndex = 0;
							postContent = coco.parseParams([{name:"zxno",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
							ajax.post(path + "/sino/dy/chdy/zxzs!printA4.do", postContent);
						}
					}
				};
				ajax.post(path + "/sino/dy/chdy/zxzs!printA4.do", postContent);
			}

			function doPrint(content, LODOP) {
				LODOP.PRINT_INIT("ZXZS-<%=System.currentTimeMillis()%>");
				LODOP.SET_PRINTER_INDEXA("HP LaserJet 5200 Series PCL 5");
				LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
				LODOP.ADD_PRINT_HTM(2, 0, 1100, 760, content);
				//打印预览
		//		LODOP.PREVIEW();
				//打印
				LODOP.PRINT();	
			}		

			function doQuery(el) {
				var form = el.form;
				form.elements["ids"].value = "";
				cocopage.query();
			}

		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<ui:page title="${title }">
		<f:page action="index!${type}.do" file="list.jsp">
			<input type="hidden" name="ids" />
			<table width="100%" class="form">
				<colgroup>
					<col width="18%" />
					<col width="32%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">出货日期</th>
					<td><ui:date name="chriS" />至<ui:date name="chriE" /></td>
					<th style="text-align: left;">装箱指示书日期</th>
					<td colspan="3"><ui:date name="creaS" />至<ui:date name="creaE" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">用户名称</th>
					<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
					<th style="text-align: left;">装箱指示No.</th>
					<td><ui:input name="zxno" maxlength="10" /></td>
					<th style="text-align: left;">装箱指示状态</th>
					<td><ui:select name="stat" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='SINO_CH_STAT'" headerText="全部" headerValue="" value="page.stat" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th style="text-align: left;">制品No.</th>
					<td><ui:input name="jbno" maxlength="11" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查 询" onclick="doQuery(this)" />
			<input type="button" class="button" value="打印A4版" onclick="printA4('');" />
			</div>	
		</f:page>
	</ui:page>
	<ui:panel id="Detail" title="修改装箱指示书" popup="true" display="false" closable="true">
		<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="update.do" >
		<table class="form" width="100%">
			<colgroup><col width="10%" /><col width="15%" /><col width="20%" /><col width="55%" /></colgroup>
			<tr>
				<th>出货日期</th>
				<td><ui:date name="chri" prop="calendar:true;" />
				<input type="hidden" name="zxno" />
				</td>
				<th>运输公司</th>
				<td><sys:codeBox  code="#021" name="ysgs" dat="2"  txt="2" match="true" tofield="[[0,'ysnm']]" required="true"/><input type="hidden" name="ysnm"  value="${ysnm }"/></td>
			</tr>
			<tr>
				<th>送货点</th>
				<td> <sys:codeBox  code="#SHD"  name="shno" dat="2"  txt="2"  match="true" tofield="[[0,'shnm']]"  required="true"/><input type="hidden" name="shnm" value="${shnm }"/></td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<div class="opt-btm"><input type="button" class="button" onclick="cocoform.submit(this);" value="保 存" /></div>
		</form>
	</ui:panel>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>