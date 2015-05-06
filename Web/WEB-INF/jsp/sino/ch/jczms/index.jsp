<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sino" prefix="sino"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>检查证明书管理</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"]]);
			//修改制品商品检查证明书
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var vKey1 = coco.getAttr(oTr, "key");
				if(vKey1 == null ){
					alert("参数为空");
                  	return ;
				}
				var postContent = coco.parseParam("ids", vKey1);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					coco.hideAlert();
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					parent.cocoframe.open("jczms", "检查证明书编辑", path + "/sino/ch/jczms/indexUpdate.do?zxno="+vKey1, null, null, true);
				};
				ajax.post("checkJczms.do", postContent);
			}
			//打印制品商品检查证明书
			function print(oTr) {
				var oForm = document.forms["PageForm_page"];
				var ids = oForm.elements["ids"].value;
				if(ids == null || ids.length == 0) {
					alert("请选择要打印检查证明书的装箱指示书");
					return;
				}
				if(!confirm("确定打印装箱指示书No." + ids + "对应检查证明书吗?")) return;
				var postContent = coco.parseParam("ids", ids);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					coco.hideAlert();
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					toPrint(postContent);
				};
				ajax.post("checkJczms.do", postContent);
				
			}
			function doPrint(content, LODOP) {
				LODOP.PRINT_INIT("JCZMS-<%=System.currentTimeMillis()%>");
				LODOP.SET_PRINTER_INDEXA("SHD");
				LODOP.SET_PRINT_PAGESIZE(1, 3500, 2794,"A3");
				//LODOP.SET_PRINT_STYLE("FontSize",12);
				LODOP.ADD_PRINT_HTM(0, 20, "1300px", "3980px", content);
			//	LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", content);
				//打印预览
			//	LODOP.PREVIEW();
				//打印
				LODOP.PRINT();	
			}		
			function toPrint(postContent) {
				var ajax = new Cocoajax();
				ajax.callback = function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						var obj;
						eval("obj="+this.result+";");
						autoPrint(obj.uuids, obj.pageSizes);
				};
				ajax.post(path + "/sino/dy/chdy/toJczms.do", postContent);
			}

			function autoPrint($uuids,$pageSizes) {
				var uuids = $uuids.split(",");
				var pageSizes = $pageSizes.split(",");
				var LODOP = document.getElementById("lodop");
				var index = 0, uuid = uuids[0], pageSize = pageSizes[0], pageIndex = 0, num = 0, $height;
				index++;
				var postContent = coco.parseParams([{name:"jczmsId",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					num++;
					doPrint(this.result, LODOP);
					if(pageIndex < pageSize) {
						postContent = coco.parseParams([{name:"jczmsId",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
						ajax.post(path + "/sino/dy/chdy/jczms!print.do", postContent);
					} else {
						if(index < uuids.length) {
							uuid = uuids[index];
							pageSize = pageSizes[index];
							index++;
							pageIndex = 0;
							postContent = coco.parseParams([{name:"jczmsId",value:uuid},{name:"current",value:pageIndex++},{name:"pages",value:pageSize}]);
							ajax.post(path + "/sino/dy/chdy/jczms!print.do", postContent);
						}
					}
				};
				ajax.post(path + "/sino/dy/chdy/jczms!print.do", postContent);
				
			}
			//重新生成检查证明书
			function zzJczms(oInput) {
				var oForm = document.forms["PageForm_page"];
				var ids = oForm.elements["ids"].value;
				if(ids == null || ids.length == 0) {
					alert("请选择要制作检查证明书的行");
					return;
				}
				if(!confirm("确定制作装箱指示书No." + ids + "对应检查证明书吗?")) return;
				var postContent = coco.parseParam("ids", ids);
				coco.alert('正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
				var ajax = new Cocoajax();
				ajax.oInput = oInput;
				ajax.callback = function() {
					coco.hideAlert();
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					ajax.callback = function() {
						if(this.code <= 0) {
							coco.alert(this.msg);
						} else {
							alert("操作成功");
							cocopage.refresh('page', setChecked);
						}
						return;
					};
					ajax.post("checkZzJczms.do", postContent);
				};
				ajax.post("zzJczms.do", postContent);
			}

			//复选框选中
			function doCheck(obj) {
				if(obj == null) {
					return;
				}
				//选中订货同号
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

			function doQuery(oForm) {
				var el = oForm.elements["ids"];
				el.value = "";
				cocopage.query('page', setChecked);
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:print id="lodop"/>
		<ui:page title="检查证明书管理">
			<f:page action="index.do"  file="list.jsp">
				<input type="hidden" name="ids" />
				<table width="100%" class="form">
					<colgroup>
						<col width="18%" />
						<col width="32%" />
						<col width="18%" />
						<col width="32%" />
					</colgroup>
					<tr>
						<th style="text-align: left;">出货日期</th>
						<td><ui:date name="chriS" />至<ui:date name="chriE" /></td>
						<th style="text-align: left;">装箱指示No.</th>
						<td><ui:input name="zxno" maxlength="10" title="合同NO" /></td>
					</tr>
					<tr>
						<th style="text-align: left;">用户名称</th>
						<td><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" /></td>
						<th colspan="2">&nbsp;</th>
					</tr>
				</table>
				<div class="submit"><input type="button" class="button" value="查 询 " onclick="doQuery(this.form);" />  <input type="button" class="button" value="制作检查证明书 " onclick="zzJczms(this)" />  <input type="button" class="button" value="打 印" onclick="print('');" /></div>	
			</f:page>
		</ui:page>
	</body>
</html>