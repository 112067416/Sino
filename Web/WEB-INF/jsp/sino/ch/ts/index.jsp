<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>投诉记录管理</title>	
		<%@include file="../../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			contextmenu.putMenus("menu", [["修改", "view.gif", "toModify()"],["撤消投诉", "del.gif", "toDel()"]]);
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "key1");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					var form = document.forms["DataForm"];
					cocoform.clear(form);
					cocoform.fillResult(form, this.result);
					coco.showPage("Detail", {center : true, top : 50, width : "90%" });
				};
				ajax.post("getForUpdate.do", "id="+key1);
			}
			//删除
			function toDel() {
				var oTr = contextmenu.selectedTarget();
				var id = coco.getAttr(oTr, "key1");
				if(!confirm("是否确定撤销投诉?")) return;
				var postContent = coco.parseParams([{name:"ids",value:id}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}		
					alert("撤销投诉成功");
					cocopage.refresh();
				};
				ajax.post("doCxts.do", postContent);
			}
			//退货
			function doTh(){
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0) {
					alert("请选择要做退货操作的制品");
					return;
				}
				if(!confirm("是否确定退货?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}		
					alert("退货成功");
					cocopage.refresh();
				};
				ajax.post("doTh.do", ids);
			}
			//厂外转卖
			function doCwzm() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0) {
					alert("请选择要做厂外转卖操作的制品");
					return;
				}
				if(!confirm("是否确定厂外转卖?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}		
					alert("厂外转卖成功");
					cocopage.refresh();
				};
				ajax.post("doCwzm.do", ids);

			}
			//撤销制品投诉
			function doCxts() {
				var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(ids == null || ids.length == 0) {
					alert("请选择要做撤销投诉操作的制品");
					return;
				}
				if(!confirm("是否确定撤销投诉?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}		
					alert("撤销投诉成功");
					cocopage.refresh();
				};
				ajax.post("doCxts.do", ids);

			}
			function success() {
				alert("保存成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="投诉记录管理">
			<f:page action="index.do" file="list.jsp">
				<table width="100%" class="form">
					<colgroup><col width="18%" /><col width="16%" /><col width="18%" /><col width="16%" /><col width="16%" /><col width="16%" /></colgroup>
					<tr>
						<th style="text-align: left;">出货日期</th>
						<td><ui:date name="chri" prop="calendar:true;" /></td>
						<th style="text-align: left;">投诉日期</th>
						<td><ui:date name="tsri" prop="calendar:true;"/></td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<th style="text-align: left;">装箱指示No.</th>
						<td><ui:input name="zxno" maxlength="10" title="装箱指示No." /></td>
						<th style="text-align: left;">制品No.</th>
						<td><ui:input name="jbno" maxlength="11" title="制品No." /></td>
						<th style="text-align: left;">类型</th>
						<td><ui:select name="stat" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_TS_STAT'" value="page.stat" headerText="全部" headerValue="" /></td>
					</tr>
				</table>
				<div class="submit">
					<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
					<input type="button" class="button" value="确定退货" onclick="doTh();" />
					<input type="button" class="button" value="厂外转卖" onclick="doCwzm();" />
					<input type="button" class="button" value="撤消投诉" onclick="doCxts();" />
				</div>	
			</f:page>
		</ui:page>
		<ui:panel id="Detail" title="修改投诉信息" popup="true" display="false" closable="true">
			<form action="update.do" xu.ajax="" xu.r="" xu.s="success();" name="DataForm" method="post" >
			<input type="hidden" name="zzny"/>
			<input type="hidden" name="cpno"/>
			<input type="hidden" name="zzno"/>
			<input type="hidden" name="plqf"/>
			<input type="hidden" name="fudw"/>
			<input type="hidden" name="id"/>
			<input type="hidden" name="stat"/>
			<table width="96%" class="form" style="margin-top: 10px;">
				<colgroup>
					<col width="9%" /><col width="12%" /><col width="9%" /><col width="13%" /><col width="10%" /><col width="13%" /><col width="12%" /><col width="22%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">送货单号</th>
					<td><ui:input name="shno" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">制品No.</th>
					<td><ui:input name="jbno" maxlength="11" readonly="true"/></td>
					<th style="text-align: left;">重量</th>
					<td><ui:number name="zpzl" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">张数</th>
					<td><ui:number name="zshu" maxlength="10" readonly="true"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" required="true" readonly="true" maxlength="7" title="订货No." />-<ui:input name="line" required="true" title="行号" readonly="true" maxlength="2" /></td>
					<th style="text-align: left;">用户略称</th>
					<td><ui:input name="abbr" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">尺寸<span style="font-size: 12px;">(厚*宽*剪切板长)</span></th>
					<td colspan="3"><ui:number name="xpho" title="现品尺寸.厚" scale="4" precision="3" readonly="true"/>*<ui:number name="xpkn" title="现品尺寸.宽" scale="6" precision="2" readonly="true" />*<ui:number name="xpcn" title="现品尺寸.长" scale="6" precision="2" readonly="true" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">镀锡量</th>
					<td><ui:input name="sczm" maxlength="4" readonly="true"/>/<ui:input name="scfm" maxlength="4" readonly="true"/></td>
					<th style="text-align: left;">等级</th>
					<td><ui:input name="deng" maxlength="3" readonly="true"/></td>
					<th style="text-align: left;">运用规格</th>
					<td><ui:input name="yuny" maxlength="6" readonly="true"/></td>
					<th style="text-align: left;">运输公司</th>
					<td><ui:input name="ysnm" maxlength="28" readonly="true"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">表面</th>
					<td><ui:input name="face" maxlength="2" readonly="true"/></td>
					<th style="text-align: left;">规格代码</th>
					<td><ui:input name="ggno" maxlength="4" readonly="true"/></td>
					<th style="text-align: left;">原出库日期</th>
					<td><ui:input name="chri" format="yyyy-MM-dd" maxlength="10" readonly="true"/></td>
					<th style="text-align: left;">投诉日期</th>
					<td><ui:date name="tsri" required="true" prop="calendar:true;" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">备注</th>
					<td colspan="7"><ui:input name="bz" required="true" cssStyle="width: 35em;" /></td>
				</tr>
			</table>
			<div class="submit"> <input type="button" class="button" value="保 存" onclick="cocoform.submit(this);" /> </div>
			</form>
		</ui:panel>
	</body>
</html>