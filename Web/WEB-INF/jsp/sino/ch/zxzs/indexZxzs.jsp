<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@
	 taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>制作装箱指示书</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<SCRIPT type="text/javascript">
		<!--
			//复选框选中
			function doCheck(obj) {
				if(obj == null) {
					return;
				}
				//选中制品的制品号
				var v = obj.getAttribute("no");
				//选中制品的重量
				var z = parseInt(obj.value);
				var oForm = document.forms["DataForm"];
				var chzl = oForm.elements["chzl"].value;
				var zpnos = oForm.elements["zpnos"].value;
				var chsu = oForm.elements["chsu"].value;
				var rgExp;
				if(obj.checked) {
					if(zpnos == null || zpnos.length == 0) {
						oForm.elements["zpnos"].value = v;
						oForm.elements["chzl"].value = parseFloat(z / 1000.0).toFixed(3);
						oForm.elements["chsu"].value=1;
						return;
					}
					oForm.elements["zpnos"].value = zpnos.replace(/(,)+$/g,'') + ',' + v;
					oForm.elements["chzl"].value = (parseFloat(chzl) + parseFloat(z / 1000.0)).toFixed(3);
					oForm.elements["chsu"].value= parseInt(chsu)+1;
				} else {
					rgExp = new RegExp(v+',|('+v+'$)' , "g");
					oForm.elements["zpnos"].value = zpnos.replace(rgExp, '');
					oForm.elements["chzl"].value = (parseFloat(chzl) - parseFloat(z / 1000.0)).toFixed(3);
					oForm.elements["chsu"].value-=1;
				}
			}
			//全选或全不选
			function checkAll(obj) {
				var oForm = document.forms["PageForm_page"];
				var chks = oForm.elements["ids"];
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
				var dForm = document.forms["DataForm"];
				var zpnos = dForm.elements["zpnos"].value;
				if(zpnos == null || zpnos.length == 0) {
					return;
				}
				var oForm = document.forms["PageForm_page"];
				var chks = oForm.elements["ids"];
				if(chks == null) return;
				if(chks.tagName != null) {
					if(zpnos.indexOf(chks.getAttribute("no")) >= 0) {
						chks.checked = true;
					}
				} else {
					var el, elIndex=0;
					while((el = chks[elIndex++]) != null) {
						if(zpnos.indexOf(el.getAttribute("no")) >= 0) {
							el.checked = true;
						}
					}
				}
			}
           
			//排序
			function setOrders(obj) {
				if(obj == null) return;
				var qForm = document.forms["PageForm_page"];
				qForm.elements["order"].value = obj.value;
				cocopage.query(null, function() {
					setChecked();
				});
			}
		    //
		    var flag1 = true;
			function chlldData(flag2) {
				var oForm = document.forms["DataForm"];
				if(!flag1 && flag2) {
					oForm.elements["dhno"].focus();
					return;
				}
				flag1 = false;
				var id = oForm.elements["id"].value;
				var dhno = oForm.elements["dhno"].value;
				var line = oForm.elements["line"].value;
				var zpnos = oForm.elements["zpnos"].value;
				var chzl = oForm.elements["chzl"].value;
				var chsu = oForm.elements["chsu"].value;
				var zxno = oForm.elements["zxno"].value;
				if((id == null || id.length == 0) && (dhno == null || dhno.length == 0) && (line == null || line.length == 0)) {
					return;
				}
				var ele = oForm.elements["addr"];
				var length = ele.options.length;
				for(var i = 0; i < length - 1; i++) {
					ele.options.remove(1);
				}
				var ajax = new Cocoajax();
				var postContent = coco.parseParams([{name:"id",value:id},{name:"dhno",value:dhno},{name:"line",value:line}]);
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						oForm.elements["chqi"].value="";
						oForm.elements["abbr"].value="";
						oForm.elements["user"].value="";
						oForm.elements["ysgs"].value="";
						oForm.elements["shno"].value="";
						PageData_page.style.display = 'none';
						return;
					}
					cocoform.fillResult(oForm, this.result);
					oForm.elements["id"].value = oForm.elements["pid"].value;
					oForm.elements["zxno"].value = zxno;
					eval("obj=" + this.result);
					if(zpnos != null && zpnos.length  > 0){
                       oForm.elements["zpnos"].value = zpnos;
                       oForm.elements["chzl"].value = chzl;
                       oForm.elements["chsu"].value = chsu;
					}
					var shdzs;
					var shdz;
					for(var id in obj) {
						if(id != null && id == 'addrs') {
							shdzs = obj[id];
							continue;
						}
						if(id != null && id == 'addr') {
							shdz = obj[id];
							continue;
						}
					}
					if(shdzs != null) {
						var vs = shdzs.split(",");
						var opt;
						for(var v in vs) {
							opt = document.createElement("OPTION");
							ele.options.add(opt);
							if(shdz != null && shdz == vs[v]) {
								opt.selected = true;
							}
							opt.innerText = vs[v];
							opt.value = vs[v];
						}
					}
					dhno = oForm.elements["dhno"].value;
					line = oForm.elements["line"].value;
					var qForm = document.forms["PageForm_page"];
					qForm.elements["dhnoLine"].value = dhno+line;
					cocopage.query(null, function() {
						setChecked();
					});
					PageData_page.style.display = 'block';
				};
				ajax.post("chlldData.do",postContent);
			}
			//返回
			function doClose() {
				parent.cocoframe.close(parent.cocoframe.pageCurr);
				//清空查询条件
			//	var oForm = document.forms["DataForm"];
			//	cocoform.clear(oForm);
			//	var qForm = document.forms["PageForm_page"];
			//	qForm.elements["dhnoLine"].value = "";
			//	qForm.elements["order"].value = "";
			//	PageData_page.style.display = 'none';
			}
            //提交
			function doSubmit(oForm, oInput) {
				var zpnos = oForm.elements["zpnos"].value.replace(/\s+|\s+$/g, '');
				if(zpnos == null || zpnos.length == 0) {
					alert("请选择制作装箱指示书的制品");
					return;
				}
				if(!cocoform.verify(oForm)) return;
				var postContent = coco.parseParam("jbnos", zpnos);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code == -1) {
						alert(this.msg);
						return;
					}
					var $msg = this.code == -2 ? this.msg : "是否确定制作装箱指示书?";
					if(!confirm($msg)) return;
					cocoform.submit(oForm, function() {
						if(this.code <= 0) {
							coco.alert(this.msg);
							return;
						} 
						alert("保存成功");
						var obj;
						eval("obj="+this.result+";");
						autoPrint(obj.zxno, obj.pageSize);
					},null,null,oInput);
				};
				ajax.post("check.do", postContent);
			}

			//装箱指示书打印
			function autoPrint(zxno, pageSize) {
				if(zxno == null || zxno.length == 0 || pageSize == null || pageSize.length == 0) return;
				var LODOP = document.getElementById("lodop");
				var index = 0;
				var postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"current",value:index++},{name:"pages",value:pageSize}]);
				coco.alert('装箱指示书打印正在处理中...<br/><div align="center" style="vertical-align: middle;"><img alt="等待..." src="<%=request.getContextPath() %>/images/sino/waiting.gif"></div>', "系统提示");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.hideAlert();
						coco.alert(this.msg);
						return;
					}
					doPrint(this.result, LODOP);
					if(index < pageSize) {
						postContent = coco.parseParams([{name:"zxno",value:zxno},{name:"current",value:index++},{name:"pages",value:pageSize}]);
						ajax.post(path + "/sino/dy/chdy/zxzs!print.do", postContent);
					} else {
						coco.hideAlert();
						parent.cocoframe.doRefresh();
					}
				};
				ajax.post(path + "/sino/dy/chdy/zxzs!print.do", postContent);
			}
			
			function doPrint(content, LODOP) {
				LODOP.PRINT_INIT("ZXZS-<%=System.currentTimeMillis()%>");
				LODOP.SET_PRINTER_INDEXA("ZXD");
				LODOP.SET_PRINT_PAGESIZE(1, 3300, 2795,"A3");
				LODOP.ADD_PRINT_HTM(0, -50, "1100px", "980px", content);
			//	LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", content);
				//打印预览
				//LODOP.PREVIEW();
				//打印
				LODOP.PRINT();	
			}
			
			//设置送货点名称或运输公司名称
			function setNm(obj, name) {
				if(obj == null || name == null) return;
				var oForm = document.forms["DataForm"];
				oForm.elements[name].value = obj.options[obj.selectedIndex].text;
			}

			//弹出设置查询现品的查询条件页面
			function doCondition() {
				var oForm = document.forms["ConditionForm"];
				//cocoform.clear(oForm);
				coco.showPage("ConditionDetail", { center : true, top : 150, width : "90%" });
			}
			//设置查询条件
			function setJbnos(el) {
				var v = el.value;
				if(v == null || v.length == 0) {
					el.focus("请输入制品No.");
					el.focus();
					return;
				}
				var oForm = document.forms["ConditionForm"];
				var $el = oForm.elements["jbnos"];
				var jbnos = $el.value;
				jbnos = jbnos != null && jbnos.length > 0 ? jbnos : "";
				if(v != null && (v.length == 7 || v.length == 10)) {
					v = "0" + v;
				}
				if(jbnos.length == 0) {
					$el.value = v;
				} else {
					$el.value = jbnos + "," + v;
				}
				countJbnos(oForm);
				el.value = "";
				el.focus();
			}
			
			function countJbnos(oForm) {
				var el = oForm.elements["jbnos"];
				var $el = oForm.elements["num"];
				var jbnos = el.value;
				if(jbnos == null || jbnos.length == 0) {
					$el.value = 0;
					return;
				}
				var arrs = jbnos.split(",");
				$el.value = arrs.length;
			}

			function queryXp() {
				var oForm = document.forms["ConditionForm"];
				var jbnos = oForm.elements["jbnos"].value;
				var $oForm = document.forms["PageForm_page"];
				var dhnoLine = $oForm.elements["dhnoLine"].value;
				if(dhnoLine == null || dhnoLine.length == 0) {
					alert("订货合同No.不能为空");
					return;
				}
				$oForm.elements["jbnoStr"].value = jbnos;
				cocopage.query('page', setChecked);
				coco.hidePage('ConditionDetail');
			}
		//-->
		</SCRIPT>
</head>
<body onload="chlldData(true)">
	<ui:print id="lodop"/>
	<ui:page title="制作装箱指示书">
		<form action="save.do" xu.ajax="" xu.r="" name="DataForm" method="post" >
			<input type="hidden" name="id" value="${id }" />
			<table width="100%" class="form">
				<colgroup>
					<col width="7%" /><col width="16%" /><col width="7%" /><col width="10%" /><col width="10%" /><col width="12%" /><col width="10%" /><col width="12%" /><col width="6%" /><col width="10%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" required="true" title="订货号No" onkeydown="if(event.keyCode == '13') chlldData(false); " />-<ui:number name="line" maxlength="2" title="行号" onkeydown="if(event.keyCode == '13') chlldData(false); " onblur="chlldData(false);" /></td>
					<th style="text-align: left;">装箱指示No.</th>
					<td><ui:input name="zxno" maxlength="10" readonly="true" title="装箱指示No。系统自动生成" /></td>
					<th style="text-align: left;">出货日期</th>
					<td><ui:date name="chqi" required="true" cssStyle="width: 120;" /></td>
					<th style="text-align: left;">用户略称</th>
					<td><ui:input name="abbr" required="true" readonly="true" maxlength="16" title="用户略称" /></td>
					<td colspan="2">
					<input type="hidden" name="pid" />
					<input type="hidden" name="user"/>
					<input type="hidden" name="name"/>
					</td>
				</tr>
				<tr>
					<th style="text-align: left;">运输公司</th>
					<td colspan="4"><ui:select name="ysgs" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='021'" prop="nn:1;" headerText="请选择" headerValue="" onchange="setNm(this,'ysnm')"/><input type="hidden" name="ysnm" /></td>
					<th style="text-align: left;">送货点</th>
					<td><ui:select name="shno" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SHD'" prop="nn:1;" headerText="请选择" headerValue="" onchange="setNm(this,'shnm')"/><input type="hidden" name="shnm" /></td>
					<th style="text-align: right;">未出货重量</th>
					<td colspan="2"><ui:input name="wczl" readonly="true" maxlength="10" title="未出货重量" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">合计重量</th>
					<td><ui:input name="chzl" readonly="true" maxlength="10" title="合计重量" /></td>
					<th style="text-align: left;">合计数量</th>
					<td><ui:input name="chsu" readonly="true" maxlength="10" title="合计数量" /></td>
					<th style="text-align: left;">到达地点</th>
					<td colspan="5"><ui:select name="addr" list="" prop="nn:1;" headerText="请选择" headerValue=""/></td>
				</tr>
				<tr>
				    <th style="text-align: left; vertical-align: middle;"> 已选制品</th>
					<td colspan="6"><ui:textarea name="zpnos" cssStyle="" cssClass="normal" readonly="true" cols="80" rows="3" title="当现品NO.有多个时，用,号隔开。"/></td>
					<td colspan="3" style="text-align: center; vertical-align: middle;"><input type="button" value="确 定" class="button" onclick="doSubmit(this.form, this);"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="设置查询条件" onclick="doCondition()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="关 闭" class="button" onclick="doClose();"  /></td>
				</tr>
			</table>
		</form>
		<f:page action="zzZxzs.do" file="listZxzs.jsp" >
			<input type="hidden" name="dhnoLine" />
			<input type="hidden" name="jbnoStr" />
			<input type="hidden" name="order" value="asc" />
		</f:page>
	</ui:page>
<ui:panel id="ConditionDetail" title="设置查询条件" popup="true" display="false" closable="true">
<form name="ConditionForm" method="post" >
<table width="100%" class="form">
<colgroup>
<col width="17%" /><col width="34%" /><col width="17%" /><col width="8%" /><col width="16%" /><col width="8%" />
</colgroup>
<tr>
<td colspan="3"><ui:textarea name="jbnos" cssStyle="" cssClass="normal" cols="75" rows="6" title="当制品NO.有多个时，用,号隔开。" />&nbsp;&nbsp;<span style="color: red; font-size: 12px;">(现品NO.用,号隔开。)</span></td>
<th>制品NO.</th>
<td><ui:input name="zpno" maxlength="12" title="制品No." onkeydown="if(window.event.keyCode == 13) setJbnos(this);" /></td>
<td style="text-align: left;"><ui:int name="num" maxlength="3" readonly="true" title="制品数量" /></td>
</tr>
</table>
<div class="submit"><input type="button" class="button" value="查  询" onclick="queryXp();" /> <input type="button" class="button" value="清  空" onclick="cocoform.clear('ConditionForm');" /> <input type="button" class="button" value="关  闭" onclick="coco.hidePage('ConditionDetail');" /></div>
</form>
</ui:panel>
</body>
</html>
