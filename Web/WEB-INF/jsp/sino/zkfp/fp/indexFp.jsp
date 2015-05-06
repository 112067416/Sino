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
					coco.alert(this.msg);
					return;
				}
				coco.showPage("MatchArea", {center : true, top : 30, width : "80%" });
			};
			ajax.post("viewMatch.do", postContent);
		}
		//查询订货合同信息
		function queryDh() {
			var oForm = document.forms["DataForm"];
			//订货号
			var dhno = oForm.elements["fpdhno"].value;
			var line = oForm.elements["fpline"].value;
			if(dhno == null || dhno == "" || line == null || line == "" ) {
				alert("待分配订货No.不能为空");
				return ;
			}
			var sczsId = oForm.elements["sczsId"].value;
			var postContent = coco.parseParams([{name:"dhno", value:dhno},{name:"line", value:line}]);
			//判断订货号是否存。同时要判断订货合同的状态，只能对状态为仕样确认和已分配的订货合同做分配操作。
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				//设置查询条件
				cocoform.fillResult(oForm, this.result);
				oForm.elements["sczsId"].value = sczsId;
				DataDiv.style.display = 'block';
				PageData_page.style.display = 'block';
				var qForm = document.forms["PageForm_page"];
				qForm.elements["dhno"].value = dhno;
				qForm.elements["line"].value = line;
				qForm.elements["xpzk"].value = 'A';
				qForm.action = "indexYcai.do";
				cocopage.query();
			};
			ajax.post("checkDhno.do", postContent);
		}

		//返回
		function doReturn() {
			//清空查询条件
			clearCondition();
			DataDiv.style.display = 'none';
			PageData_page.style.display = 'none';
			var oForm = document.forms["DataForm"];
			oForm.elements["fpdhno"].focus();
		}
		//清空分页查询条件
		function clearCondition() {
			//清空查询条件
			var oForm = document.forms["PageForm_page"];
			//现品NO.
			oForm.elements["jbnoStr"].value = "";
			//规格代码
			oForm.elements["ggno"].value = "";
			//大于现品尺寸宽
			oForm.elements["xpknS"].value = "";
			//小于现品尺寸宽
			oForm.elements["xpknE"].value = "";
			//大于现品尺寸长
			oForm.elements["xpcnS"].value = "";
			//小于现品尺寸长
			oForm.elements["xpcnE"].value = "";
			//大于现品尺寸厚
			oForm.elements["xphoS"].value = "";
			//小于现品尺寸厚
			oForm.elements["xphoE"].value = "";
			//订货合同号
			oForm.elements["dhno"].value = "";
			//订货合同行号
			oForm.elements["line"].value = "";
			oForm.elements["flag"].value = true;
		}

		//余材化处理
		function doFp() {
			var oForm = document.forms["DataForm"];
			var sczsId = oForm.elements["sczsId"].value;
			var ajax = new Cocoajax();
			var postContent = cocoform.postContent(oForm);
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				if((this.code == 100 && !confirm(this.msg)) || !confirm("确定要分配吗?")) return ;
				cocoform.submit(oForm, function() {
					if(this.code < 0) {
						coco.alert(this.msg);
						return;
					} 
					oForm.elements["fpno"].value = this.msg;
					alert("分配成功");
					if(sczsId != null && sczsId.length > 0) {
						parent.cocoframe.doRefresh();
					}
					doReturn();
				});
			};
			ajax.post("checkFp.do", postContent);
		}

		// 更换余材状况
		function changeXpzk(obj) {
			if(obj == null || obj.value == null) return;
			//改变余材状况时，要清空已选的制品
			var form = document.forms["DataForm"];
			form.elements["cqzs"].value = "";
			form.elements["xpnos"].value = "";
			//清空查询条件
			clearCondition();
			var oForm = document.forms["PageForm_page"];
			oForm.elements["dhno"].value = form.elements["dhno"].value;
			oForm.elements["line"].value = form.elements["line"].value;
			oForm.elements["flag"].value = true;
			if(obj.value == "A") {
				oForm.action = "indexYcai.do";
			} else {
				oForm.action = "indexZp!"+obj.value+".do";
			}
			cocopage.query();
		}
		//弹出设置查询现品的查询条件页面
		function doCondition() {
			var oForm = document.forms["ConditionForm"];
			cocoform.clear(oForm);
			coco.showPage("ConditionDetail", { center : true, top : 80, width : "90%" });
		}
		//设置查询条件,查询现品信息
		function queryXp() {
			var cForm = document.forms["ConditionForm"];
			var oForm = document.forms["PageForm_page"];
			//现品NO.
			var jbnos = cForm.elements["jbnos"].value;
			oForm.elements["jbnoStr"].value = jbnos;
			//规格代码
			var ggno = cForm.elements["ggno"].value;
			oForm.elements["ggno"].value = ggno;
			//大于现品尺寸宽
			var xpknS = cForm.elements["xpknS"].value;
			oForm.elements["xpknS"].value = xpknS;
			//小于现品尺寸宽
			var xpknE = cForm.elements["xpknE"].value;
			oForm.elements["xpknE"].value = xpknE;
			//大于现品尺寸长
			var xpcnS = cForm.elements["xpcnS"].value;
			oForm.elements["xpcnS"].value = xpcnS;
			//小于现品尺寸长
			var xpcnE = cForm.elements["xpcnE"].value;
			oForm.elements["xpcnE"].value = xpcnE;
			//大于现品尺寸厚
			var xphoS = cForm.elements["xphoS"].value;
			oForm.elements["xphoS"].value = xphoS;
			//小于现品尺寸厚
			var xphoE = cForm.elements["xphoE"].value;
			oForm.elements["xphoE"].value = xphoE;
			oForm.elements["flag"].value = false;
			cocopage.query();
		}
		//单个复选框选中或不选中
		function doCheck(obj) {
			if(obj == null) {
				return;
			}
			//选中制品的制品号
			var v = obj.value;
			//选中制品的重量
			var z = parseInt(obj.getAttribute("zpzl"));
			var oForm = document.forms["DataForm"];
			var cqzs = oForm.elements["cqzs"].value;
			var xpnos = oForm.elements["xpnos"].value;
			var rgExp;
			if(obj.checked) {
				if(xpnos == null || xpnos.length == 0) {
					oForm.elements["xpnos"].value = v;
					oForm.elements["cqzs"].value = parseFloat(z / 1000.0).toFixed(3);
					return;
				}
				if(xpnos.indexOf(v) >= 0) return;
				oForm.elements["xpnos"].value = xpnos.replace(/(,)+$/g,'') + ',' + v;
				oForm.elements["cqzs"].value = (parseFloat(cqzs) + parseFloat(z / 1000.0)).toFixed(3); 
			} else {
				rgExp = new RegExp(v+',|('+v+'$)' , "g");
				oForm.elements["xpnos"].value = xpnos.replace(rgExp, '');
				oForm.elements["cqzs"].value = (parseFloat(cqzs) - parseFloat(z / 1000.0)).toFixed(3); 
			}
		}
		//全选或全不选
		function checkAll(obj) {
			var oForm = document.forms["PageForm_page"];
			var chks = oForm.elements["ids"];
			if(chks == null) return;
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
			var xpnos = dForm.elements["xpnos"].value;
			if(xpnos == null || xpnos.length == 0) {
				return;
			}
			var oForm = document.forms["PageForm_page"];
			var chks = oForm.elements["ids"];
			if(chks == null) return;
			if(chks.tagName != null) {
				if(xpnos.indexOf(chks.value) >= 0) {
					chks.checked = true;
				}
			} else {
				var el, elIndex=0;
				while((el = chks[elIndex++]) != null) {
					if(xpnos.indexOf(el.value) >= 0) {
						el.checked = true;
					}
					continue;
				}
			}
		}

		var $flag = true;
		function doLoad() {
			if(!$flag) return;
			$flag = false;
			var oForm = document.forms["DataForm"];
			//订货号
			var dhno = oForm.elements["fpdhno"].value;
			var line = oForm.elements["fpline"].value;
			if(dhno == null || dhno == "" || line == null || line == "" ) {
				oForm.elements["fpdhno"].focus();
				return ;
			}
			var sczsId = oForm.elements["sczsId"].value;
			var postContent = coco.parseParams([{name:"dhno", value:dhno},{name:"line", value:line}]);
			//判断订货号是否存。同时要判断订货合同的状态，只能对状态为仕样确认和已分配的订货合同做分配操作。
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				//设置查询条件
				cocoform.fillResult(oForm, this.result);
				oForm.elements["sczsId"].value = sczsId;
				DataDiv.style.display = 'block';
				PageData_page.style.display = 'block';
				var qForm = document.forms["PageForm_page"];
				qForm.elements["dhno"].value = dhno;
				qForm.elements["line"].value = line;
				qForm.elements["xpzk"].value = 'A';
				qForm.action = "indexYcai.do";
				cocopage.query();
			};
			ajax.post("checkDhno.do", postContent);
		}
		//-->
		</script>
	</head>
	<body onload="doLoad();">
<ui:page title="分配指示">
<form action="doFp.do" name="DataForm" method="post">
<input type="hidden" name="sczsId" value="${sczsId }" />
<table width="100%" class="form">
	<tr>
		<td>
			<table width="100%" class="form">
				<colgroup><col width="15%" /><col width="85%" /></colgroup>
				<tr>
					<th>待分配订货No.</th>
					<td><ui:input name="fpdhno" title="订货DB的订货NO" maxlength="7" value="dhno" onkeydown="if(window.event.keyCode==13) queryDh()" />-<ui:input name="fpline" title="行号" maxlength="2" value="line" onkeydown="if(window.event.keyCode==13) queryDh()"  /></td>
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
									<th>分配NO.</th>
									<td><ui:input name="fpno" title="分配No." maxlength="8" readonly="true" /></td>
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
									<th>余材状况</th>
									<td><ui:select name="xpzk" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='XPZK'" onchange="changeXpzk(this)"/></td>
									<th>再选</th>
									<td><ui:input name="zxbb" title="再选" maxlength="1"/></td>
									<th>剪边</th>
									<td><ui:input name="jbkb" title="剪边" maxlength="1"/></td>
									<th>允许超出</th>
									<td><ui:input name="clfp" title="允许超出" maxlength="1"/></td>
									<th>强制</th>
									<td><ui:input name="qzbj" title="强制分配" maxlength="1"/></td>
								</tr>
								<tr>
									<th>现品NO.</th>
									<td colspan="5"><ui:textarea name="xpnos" cssStyle="" cssClass="normal" cols="70" rows="3" title="当现品NO.有多个时，用,号隔开。" readonly="readonly" /></td>
									<td colspan="4" align="right">
										<input type="button" class="button" value="设置查询条件" onclick="doCondition()" />
										<input type="button" class="button" value="确认分配" onclick="doFp();" />
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
<f:page action="indexYcai.do" file="listYcai.jsp"  >
<input type="hidden" name="xpzk" />
<input type="hidden" name="jbnoStr" />
<input type="hidden" name="ggno" />
<input type="hidden" name="xpknS" />
<input type="hidden" name="xpknE" />
<input type="hidden" name="xpcnS" />
<input type="hidden" name="xpcnE" />
<input type="hidden" name="xphoS" />
<input type="hidden" name="xphoE" />
<input type="hidden" name="dhno" />
<input type="hidden" name="line" />
<input type="hidden" name="flag" value="true" />
</f:page>
</ui:page>
<ui:panel id="ConditionDetail" title="设置查询条件" popup="true" display="false" closable="true">
<form action="#" xu.ajax="" xu.r="" xu.s="successj();" name="ConditionForm" method="post" >
<table width="100%" class="form">
<colgroup>
<col width="7%" /><col width="34%" /><col width="7%" /><col width="20%" /><col width="8%" /><col width="24%" />
</colgroup>
<tr>
<th>现品NO.</th>
<td colspan="3"><ui:textarea name="jbnos" cssStyle="" cssClass="normal" cols="65" rows="3" title="当现品NO.有多个时，用,号隔开。"/>&nbsp;&nbsp;<span style="color: red; font-size: 12px;">(现品NO.用,号隔开。)</span></td>
<th>规格</th>
<td><ui:input name="ggno" maxlength="4" title="规格代码" /></td>
</tr>
<tr>
<th>尺寸.厚</th>
<td><ui:number name="xphoS" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true" />至<ui:number name="xphoE" maxlength="5" scale="4" precision="3" title="尺寸.厚" positive="true" /></td>
<th>尺寸.宽</th>
<td><ui:number name="xpknS" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true" />至<ui:number name="xpknE" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true" /></td>
<th>尺寸.长</th>
<td><ui:number name="xpcnS" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true" />至<ui:number name="xpcnE" maxlength="7"  scale="6" precision="2" title="尺寸.宽" positive="true" /></td>
</tr>
</table>
<div class="submit"><input type="button" class="button" value="查  询" onclick="queryXp();" /> <input type="button" class="button" value="清  空" onclick="cocoform.clear('ConditionForm');" /> <input type="button" class="button" value="关  闭" onclick="coco.hidePage('ConditionDetail')" /></div>
</form>
</ui:panel>
<ui:panel id="MatchArea" title="查看配匹情况" display="false" closable="true" popup="true">
<div id="MatchDiv" class="scroll" style="width:100%;height:400px;"></div>
</ui:panel>
</body>
</html>