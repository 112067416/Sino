<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib 
	uri="/ui" prefix="ui"%><%@ taglib 
	uri="/f" prefix="f"%><%@ taglib 
	uri="/sys" prefix="sys"%><%@ taglib 
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>订单合同维护</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["查看", "view.gif", "toView()"],["修改", "edit.gif", "toModify()"],["删除", "del.gif", "toDelelte()"]]);
			//修改
			function toModify(oTr) {
				if(oTr == null) oTr = contextmenu.selectedTarget();
				var oForm = document.forms["DataForm"];
				var vKey1 = coco.getAttr(oTr, "xu.id");
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					cocoform.fillResult(oForm, this.result);
					oForm.elements["$htno"].value = oForm.elements["htno"].value;
					oForm.elements["$line"].value = oForm.elements["line"].value;
					coco.showPage("Detail",{center:true,top:50,width:"80%"});
				};
				ajax.post("get!"+vKey1+".do", null, "POST");
			}
			//批量删除合同
			function toDel() {
				var postContent = cocoform.postCheckValues("PageForm_page","ids","ids");
				if(postContent == null || postContent.length == 0) {
					alert("请选择删除行");
					return;
				}
				if(!confirm("确定删除吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0){
						coco.alert(this.msg);
						return;
					}
					alert("删除成功");
					cocopage.refresh();
				};
				ajax.post("del.do", postContent);
			}
			//查看合同详细
			function toView() {
				var ajax = new Cocoajax();
				ajax.dataDiv = document.getElementById("ViewArea");
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					coco.showPage('View',{center:true,top:50,width:"80%"});
				};
				ajax.post("view!"+contextmenu.selectedId()+".do");
			}
			//删除单个合同
			function toDelelte() {
				if(!window.confirm("确定要删除该条记录吗?")) return;
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						coco.alert(this.msg);
						return;
					}
					alert("删除成功");
					coco.hidePage('Detail');
					cocopage.refresh();
				};
				ajax.post("delete!"+contextmenu.selectedId()+".do", null, "POST");
			}
			//显示中日达规格略称
		    function getZrgg(el){
			    if(el == null) return;
			    var v = el.value;
			    if(v == null || v.length == 0) return;
		    	var form = document.forms["DataForm"];
		    	var obj = coco.getCode("017", v);
		    	if(obj == null) {
					alert("制造商规格略称"+v+"不存在");
			    	el.focus();
			    	return;
		    	}
		    	form.elements["zrgg"].value = obj.value;
		    }
			//保存成功
			function success() {
				alert("保存成功");
				coco.hidePage('Detail');
				cocopage.refresh();
			}
			//
			function setNm(obj, name) {
				var oForm = document.forms["DataForm"];
				oForm.elements[name].value = obj.options[obj.selectedIndex].text;
			}
			//-->
		</SCRIPT>
	</head>
	<body>
<ui:page title="采购维护">
		<f:page action="indexCgwh.do" file="list.jsp">
			<table width="100%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">供应商合同NO</th>
					<td><ui:input name="htno" maxlength="8" title="供应商合同NO" />-<ui:digit name="line" maxlength="3" title="行号" /></td>
					<th style="text-align: left;">状态</th>
					<td><ui:select name="qywl" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='QYWL'" headerText="全部" headerValue="" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">签约日期</th>
					<td ><ui:date name="qyri_begin" value="page.qyri_begin" />至<ui:date name="qyri_end" /></td>
					<th style="text-align: left;">制造商</th>
					<td><ui:select name="zzsd" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='012'" headerText="全部" headerValue="" /></td>
				</tr>
				<tr>
					<th style="text-align: left;">合同日期</th>
					<td><ui:date name="htdt_begin" />至<ui:date name="htdt_end" /></td>
					<th style="text-align: left;">商社</th>
					<td><ui:select name="ssno" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='010'" headerText="全部" headerValue="" /></td>
				</tr>
			</table>
			<div class="submit">
			<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
			<input type="button" class="button" value="删 除" onclick="toDel()" />
			</div>
		</f:page>
	</ui:page>
<ui:panel id="Detail" title="详细信息" popup="true" display="false" closable="true" >
	<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" xu.color="1" method="post" action="update.do" >
	<input type="hidden" name="htno" /><input type="hidden" name="line" />
		<table width="100%" class="form" >
		<colgroup>
			<col width="20%" />
			<col width="30%" />
			<col width="20%" />
			<col width="30%" />
		</colgroup>
		<tr>
			<th>供应商合同NO</th>
			<td><ui:input name="$htno" maxlength="8" title="供应商合同NO(手工设定)" required="true" />-<ui:digit name="$line" maxlength="3" title="行号" required="true" /></td>
			<th>签约日期</th>
			<td><ui:date name="qyri" required="true"/></td>						
		</tr>
		<tr>
			<th>制造商</th>
			<td><ui:select name="zzsd" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='012'" onchange="setNm(this,'zzsm');" /><input type="hidden" name="zzsm" /></td>
			<th>币种</th>
			<td><ui:select name="thqf" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='013'" /></td>
		</tr>
		<tr>
			<th>商社</th>
			<td colspan="3" ><ui:select name="ssno" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='010'" onchange="setNm(this,'ssnm');" /><input type="hidden" name="ssnm"/></td>	
		</tr>
		<tr>
		 	<th>合同日期</th>
			<td><ui:date name="htdt" required="true"/></td>
			<th>制造商规格略称</th>
			<td><ui:input name="zzgg" maxlength="15" title="制造商规格略称" required="true" onchange="getZrgg(this)"/></td>
		</tr>
		<tr>
		  	<th>中日达规格略称</th>
			<td><ui:input name="zrgg" maxlength="16" title="中日达规格略称" readonly="true" required="true"/></td>
			<th>用户略称</th>
			<td><ui:input name="abbr" maxlength="16" title="用户略称" /> </td>
		</tr>
		<tr>
		  	<th>品种</th>
			<td><ui:int name="pzno" maxlength="2" title="品种代码" required="true"/></td>
			<th>尺寸</th>
			<td><ui:number name="houu" maxlength="5" scale="4" precision="3" title="尺寸.厚" required="true" positive="true"/>*<ui:number name="kuan" maxlength="7" scale="6" precision="2" title="尺寸.宽" required="true" positive="true"/></td>
		</tr>
		<tr>
		  	<th>重量（吨）</th>
			<td><ui:number name="htzl" maxlength="9" scale="8" precision="3" title="实际重量" required="true" positive="true"/></td>
			<th>等级</th>
			<td><ui:input name="fpdj" maxlength="3" title="等级" required="true"/> </td>
		</tr>
		<tr>
			<th>表面</th>
			<td><ui:input name="face" maxlength="2" title="表面" required="true"/></td>
			<th>单价</th>
			<td><ui:number name="htdj" maxlength="8" scale="7" precision="2" title="采购单价" required="true" positive="true"/></td>						
		</tr>
		<tr>
			<th>内径</th>
			<td><ui:int name="neij" scale="3" title="内径" maxlength="3" positive="true" required="true" /></td>
			<th>合同完成状态</th>
			<td><ui:select name="qywl" sql="#select KEY_, VALUE_ from COCO_CODE_ITEM where CODE_='QYWL'" /></td>						
		</tr>
	</table>
	<div class="submit"><input type="button" class="button" value="保 存" onclick="cocoform.submit(this, null, '确定保存吗?');" /> <input type="button" class="button" value="关 闭" onclick="coco.hidePage('Detail')" /></div>
 </form>
</ui:panel>
<ui:panel id="View" title="查看详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
</body>
</html>