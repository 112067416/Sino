<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看配匹情况", "view.gif", "viewMatch()"]]);
		//查看配匹结果
		function viewMatch(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key1 = coco.getAttr(oTr, "xu.id");
			var oForm = document.forms["DataForm"];
			var xpzk = oForm.elements["xpzk"].value;
			var dhno = oForm.elements["dhno"].value;
			var line = oForm.elements["line"].value;
			var postContent = coco.parseParams([{name:"jbno",value:key1},{name:"xpzk",value:xpzk},{name:"dhno",value:dhno},{name:"line",value:line}]);
			var Match = document.getElementById("MatchDiv");
			var ajax = new Cocoajax();
			ajax.dataDiv = Match; 
			ajax.callback = function() {
				if (this.code <= 0) {
					alert(this.msg);
					return;
				}
				coco.showPage("MatchArea", {center : true, top : 30, width : "80%" });
			};
			ajax.post("viewMatch.do", postContent);
		}
		//获得分配信息
		function doQuery() {
			var oForm = document.forms["DataForm"];
			var fpno = oForm.elements["dfpno"].value;
			if(fpno == null || fpno.length == 0) {
				alert("待取消分配No.不能为空");
			}
			var postContent = coco.parseParams([{name:"fpno", value:fpno}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				cocoform.fillResult(oForm, this.result);
				var xpzk = oForm.elements["xpzk"].value;
				if(xpzk == null || xpzk.length == 0) return;
				DataDiv.style.display = 'block';
				PageData_page.style.display = 'block';
				var qForm = document.forms["PageForm_page"];
				qForm.elements["fpno"].value = fpno;
				if(xpzk == 'A') {
					qForm.action = "indexSc.do";
				} else {
					qForm.action = "indexJqc.do";
				}
				cocopage.query();
			};
			ajax.post("getFpqx.do", postContent);
		}

		//取消分配
		function doFpqx() {
			if(!confirm("是否确定取消分配")) return;
			var oForm = document.forms["DataForm"];
			var fpno = oForm.elements["fpno"].value;
			var xpzk = oForm.elements["xpzk"].value;
			var dhno = oForm.elements["dhno"].value;
			var line = oForm.elements["line"].value;
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			var postContent = coco.parseParams([{name:"fpno", value:fpno},{name:"xpzk", value:xpzk},{name:"dhno", value:dhno},{name:"line", value:line}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				} 
				cocoform.fillResult(oForm, this.result);
				alert("分配取消成功");
				doReturn();
				return;
			};
			ajax.post("doFpqx.do",ids+'&'+postContent);
		}
		//返回
		function doReturn() {
			//清空查询条件
			var oForm = document.forms["PageForm_page"];
			oForm.elements["fpno"].value = "";
			DataDiv.style.display = 'none';
			PageData_page.style.display = 'none';
			var form = document.forms["DataForm"];
			form.elements["dfpno"].focus();
		}

		function doLoad() {
			var oForm = document.forms["DataForm"];
			oForm.elements["dfpno"].focus();
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
	<ui:page title="分配取消">
<form action="doFp.do" name="DataForm" method="post">
<input type="hidden" name="xpzk" />
<table width="100%" class="form">
	<tr>
		<td>
			<table width="100%" class="form">
				<colgroup><col width="15%" /><col width="85%" /></colgroup>
				<tr>
					<th>待取消分配No.</th>
					<td><ui:input name="dfpno" title="待取消分配No." maxlength="8" onkeydown="if(window.event.keyCode==13) doQuery()"  /></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<div id="DataDiv" style="display: none;" >
				<table width="100%">
					<tr>
						<td>
							<fieldset class="group"><legend>订货合同信息</legend>
								<table width="100%" class="form">
									<colgroup>
										<col width="8%" /><col width="15%" /><col width="6%" />
										<col width="23%" /><col width="8%" /><col width="8%" />
										<col width="10%" /><col width="7%" /><col width="10%" /><col width="4%" />
									</colgroup>
									<tr>
										<th>订货No.</th>
										<td><ui:input name="dhno" title="订货DB的订货NO" maxlength="7" readonly="true" />-<ui:input name="line" title="行号" maxlength="2" readonly="true" /></td>
										<th>分配No.</th>
										<td><ui:input name="fpno" title="分配NO." maxlength="8" readonly="true" /></td>
										<th>未分配量</th>
										<td><ui:input name="wfpl" title="未分配量" maxlength="8" readonly="true" /></td>
										<th>分配指示量</th>
										<td><ui:input name="cqzs" title="分配指示量" maxlength="8" readonly="true" /></td>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<th>用户略称</th>
										<td><ui:input name="abbr" title="用户略称" maxlength="16" readonly="true" /></td>
										<th>尺寸</th>
										<td><ui:number name="houu" title="订货DB的订货尺寸.厚" scale="4" precision="3" readonly="true"/>*<ui:number name="kuan" title="订货DB的订货尺寸.宽" scale="6" precision="2" readonly="true" />*<ui:number name="cang" title="订货DB的订货尺寸.长" scale="6" precision="2" readonly="true" /></td>
										<th>规格</th>
										<td><ui:input name="ggno" title="订货DB的规格代码" maxlength="4" readonly="true" /></td>
										<th>表面&nbsp;&nbsp;&nbsp;<ui:input name="face" title="订货DB的表面" maxlength="2" readonly="true" /></th>
										<th>镀锡量</th>
										<td colspan="2"><ui:input name="fudw" title="订货DB的附着量单位（GM 或  WB）" maxlength="2" readonly="true" />
										&nbsp;<ui:input name="fuzm" title="订货DB的附着量正面" maxlength="3" readonly="true" />
										&nbsp;<ui:input name="fufm" title="订货DB的附着量反面" maxlength="3" readonly="true" /></td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td>
							<fieldset class="group"><legend>分配指示信息</legend>
								<table width="100%" class="form">
									<colgroup>
										<col width="8%" /><col width="15%" /><col width="6%" />
										<col width="23%" /><col width="8%" /><col width="8%" />
										<col width="10%" /><col width="7%" /><col width="10%" /><col width="4%" />
									</colgroup>
									<tr>
										<th>再选</th>
										<td><ui:input name="zxbb" title="再选" maxlength="1" readonly="true" /></td>
										<th>剪边</th>
										<td><ui:input name="jbkb" title="剪边" maxlength="1" readonly="true" /></td>
										<th>允许超出</th>
										<td><ui:input name="clfp" title="允许超出" maxlength="1" readonly="true" /></td>
										<th>强制</th>
										<td><ui:input name="qzbj" title="强制分配" maxlength="1" readonly="true" /></td>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="10" align="right">
											<input type="button" class="button" value="取消分配" onclick="doFpqx();" />
											<input type="button" class="button" value="返 回" onclick="doReturn();" />
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>
</form>
<f:page action="indexSc.do" file="listSc.jsp"  >
<input type="hidden" name="fpno" />
</f:page>
</ui:page>
<ui:panel id="MatchArea" title="查看配匹情况" display="false" closable="true" popup="true">
<div id="MatchDiv" class="scroll" style="width:100%;height:400px;"></div>
</ui:panel>
</body>
</html>