<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
	prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>次日出货联络单登录</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js" />"></script>
		<style type="text/css">
		TABLE.pagination1 { border-collapse: collapse;background-color: #FFFFFF;}
		TABLE.pagination1 caption { border: 1px solid #C8D8E8; height: 28px; line-height: 28px; text-align: left; padding-left: 15px; font-size: 14px; font-weight: bold; background-color: #0F88BB; color: #FFCC00;}
		TABLE.pagination1 th { font-size: 12px; color: #003449; height: 20px; background-color: #b5d4e9; border: 1px solid #8cb7d3; border-collapse: collapse; padding-top:4px; white-space: nowrap; letter-spacing: 3px; text-align: center;}
		TABLE.pagination1 td { font-size: 13px; color: #333333; height: 25px; border: 1px dashed #8cb7d3; border-collapse: collapse;padding-left:3px;}
		TABLE.pagination1 tr {background-color: #F7F7F7;}
		TABLE.pagination1 tr.odd {background-color: #EEF7FF;}
		TABLE.pagination1 tr.overTr { background-color: #CCDDFF;}
		</style>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDelete()"]]);	
			//修改操作
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var key1 = coco.getAttr(oTr, "id0");
				var ajax = new Cocoajax();
				ajax.dataDiv = DataDiv;
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					coco.showPage("Detail", {center : true, top : 200, width : "80%" });
				};
				ajax.post("toUpdate.do", "id="+key1);
			}
			
			//删除操作
			function toDelete(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var postContent;
				if(oTr == null || oTr.length == 0){
					postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				} else {
				    postContent =  coco.parseParam("ids", encodeURIComponent(coco.getAttr(oTr, "id0")));
				}
				if(postContent == null || postContent.length == 0){
					alert("请选择要做删除操作的记录");
					return;
				}
				if(!confirm("是否确定删除?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					cocopage.refresh();
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					alert("删除成功");
					var form = document.forms["DataForm"];
					cocoform.clear(form);
				};
				ajax.post("delete.do", postContent);
			}
			
			//验证用户代码与订货no是否对应
			function doDhno(obj){
				var dhno = obj.value;
				if(dhno == null || dhno.length == 0) {
					return ;
				}
				var form = obj.form;
				var postContent = coco.parseParam("dhno", dhno);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
					//	obj.focus();
						return;
					}
					cocoform.fillResult(form, this.result,null,true);
				};
				ajax.post("getDhInfo.do", postContent);
			}
			
			//根据订货no及行号获取订货db中的信息填充引入项
			function doLine(obj){
				var line = obj.value;
				var form = obj.form;
				var dhno = form.elements["dhno"].value;
				if(dhno == null || dhno.length == 0) {
					alert("请输入订货合同号");
					form.elements["dhno"].focus();
					return ;
				}
				var postContent = coco.parseParams([{name:"dhno",value:dhno},{name:"line",value:line}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
					//	obj.focus();
						return;
					}
					cocoform.fillResult(form, this.result,null,true);
				};
				ajax.post("getDhInfo.do",postContent);
			}

			//获得运输方式名称
			function changeYsfs(obj) {
				var ysnm = obj.options[obj.selectedIndex].text;
				var oForm = obj.form;
				oForm.elements["ysfm"].value = ysnm;
			}
			//保存操作
			function doSave(oInput) {
				var oForm = oInput.form;
				if(!cocoform.verify(oForm)) return;
				var postContent = cocoform.postContent(oForm);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					cocoform.submit(oInput, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						var ignores = ["chqi"];
						cocoform.clear(oForm, ignores);
						oForm.elements["dhno"].focus();
						cocopage.query();
						}, '是否确定保存?');
				};
				ajax.post("getCheck.do", postContent);
			}

			var flag = true;
			function doLoad() {
				if(!flag) return;
				flag = false;
				var oForm = document.forms["DataForm"];
				var el1 = oForm.elements["dhno"];
				var el2 = oForm.elements["line"];
				var dhno = el1.value;
				var line = el2.value;
				if(dhno == null || dhno.length == 0 || line == null || line.length == 0) {
					el1.focus();
					return;
				}
				var postContent = coco.parseParams([{name:"dhno",value:dhno},{name:"line",value:line}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						return;
					}
					cocoform.fillResult(oForm, this.result,null,true);
					oForm.elements["chzl"].focus();
				};
				ajax.post("getDhInfo.do",postContent);
			}
			
			//改变日期
			function changeDate(v) {
				if(v == null || v.length == 0) {
					alert("出货日期不能为空");
					return;
				}
				var qForm = document.forms["PageForm_page"];
				qForm.elements["chqiBegin"].value = v;
				cocopage.query();
			}

			//打印操作
			function doPrint(oForm) {
				var qForm = document.forms["PageForm_page"];
				var chqi = qForm.elements["chqiBegin"].value;
				if(chqi == null || chqi.length == 0) {
					alert("出货日期不能为空");
					return;
				}
				var ddno = oForm.elements["ddno"].value;
				var postContent = coco.parseParams([{name:"chqi",value:chqi},{name:"ddno",value:ddno}]);
				if(!confirm("是否确定打印?")) return;
				document.getElementById("PrintFrame").src = path + "/sino/dy/chlld.do?"+postContent;
			}

			//装箱指示书查看
			function openZxzs(id) {
				if(id == null || id.length == 0) {
					alert("参数为空");
					return ;
				}
				parent.cocoframe.open("view", "查看装箱指示书明细", "/sino/ch/zxzs/view.do?zxno="+id, true);
			}
			//保存操作
			function doUpdate(oInput) {
				cocoform.submit(oInput, function() {
						if(this.code <= 0) {
							alert(this.msg);
							return;
						}
						alert("保存成功");
						coco.hidePage('Detail');
						cocopage.refresh();
					}, "是否确定保存?") ;

			}
		//-->
		</SCRIPT>
</head>
<body onload="doLoad();">
<ui:page title="次日出货联络单登录 ">
<form name="DataForm" method="post" action="save.do" >
<input type="hidden" name="id" /><input type="hidden" name="ddno" value="${page.ddno }" />
<table width="100%" >
<colgroup>
<col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" /><col width="11%" />
<col width="9%" /><col width="9%" /><col width="6%" /><col width="6%" />
<col width="9%" /><col width="9%" /><col width="9%" />
</colgroup>
<tr>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">出货日期</th>
<td colspan="3"><ui:date name="chqi" cssClass="normal" required="true" showCalendar="true" onchange="changeDate(this.value);" /></td>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">用户名称</th>
<td colspan="7"><sino:yongBox name="user" width="220" txt="8" match="true" val="1" dat="13" tofield="[[1,'abbr'],[2,'name']]" cascade="{key:'sql',module:'chYongShdd',fields:{addr:['addr','请选择']}}"/><input type="hidden" name="abbr" /> <input type="hidden" name="name" /></td>
</tr>
<tr>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">订货NO</th>
<td colspan="2"><ui:input name="dhno" cssClass="normal" title="订货DB的订货NO" maxlength="7" required="true" value="dhno" onkeydown="if(window.event.keyCode==13) doDhno(this);"/>-<ui:input name="line" cssClass="normal" title="行号" maxlength="2" value="line" onblur="doLine(this);"/></td>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">吨数</th>
<td><ui:number name="chzl" cssClass="normal" title="重量" scale="7" precision="3" required="true" positive="true"/></td>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">运用规格</th>
<td><ui:input name="yuny" cssClass="normal" title="运用规格" maxlength="6" /></td>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">表面</th>
<td><ui:input name="face" cssClass="normal" title="表面" maxlength="2" /></td>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">镀锡量</th>
<td colspan="2"><ui:input name="fudw" title="附着量.单位" maxlength="2" /> <ui:input name="fuzm" title="附着量.正面" maxlength="3" />/<ui:input name="fufm" title="附着量.反面" maxlength="3" /></td>
</tr>
<tr>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">尺寸</th>
<td colspan="4"><ui:number name="houu" cssClass="normal" title="订货DB的订货尺寸.厚" scale="4" precision="3" positive="true"/>
*<ui:number name="kuan" cssClass="normal" title="订货DB的订货尺寸.宽" scale="6" precision="2" positive="true"/>
*<ui:number name="cang" cssClass="normal" title="订货DB的订货尺寸.长" scale="6" precision="2" positive="true"/></td>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;" style="text-align: left;">运输方式</th>
<td colspan="6"><ui:select name="ysfs" sql="#select key_,value_ from COCO_CODE_ITEM where CODE_='SINO_YSFS'" prop="nn:1;" headerText="请选择" headerValue="" onchange="changeYsfs(this)" /><input type="hidden" name="ysfm" /></td>
</tr>
<tr>
<th height="17" style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">到达地点</th>
<td colspan="11"><ui:select name="addr" list="" prop="nn:1;" headerText="请选择" headerValue=""/></td>
</tr>
<tr>
<th height="23"style="color:#99ffff; font-size: 18px; white-space: nowrap; padding: 2px 5px; vertical-align: bottom; text-align: left; font-weight: normal;">备货状况</th>
<td colspan="11"><ui:textarea name="bhzk" cssStyle="" cssClass="normal" cols="100" rows="3" title="备货状况"/></td>
</tr>
</table>
<div class="submit" > <input type="button" class="button" value="保 存" onclick="doSave(this);" />  <input type="reset"" class="button" value="重 置" />  <input type="reset"" class="button" value="删 除" onclick="toDelete('');" />  <input type="button" class="button" value="打 印" onclick="doPrint(this.form)" /></div>
</form>
<f:page action="index.do" file="list.jsp">
<input type="hidden" name="chqiBegin" value="<f:v value="chqi" format="yyyy-MM-dd" />" />
</f:page>
</ui:page>
<ui:panel id="Detail" title="修改" popup="true" display="false" closable="true">
<form name="UpdateForm" method="post" action="doUpdate.do" >
<div id="DataDiv"></div>
<div class="opt-btm"><input type="button" class="button" value="保   存" onclick="doUpdate(this);" />  <input type="button" class="button" value="关   闭" onclick="coco.hidePage('Detail');" /></div>
</form>
</ui:panel>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>
