<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%>
	<%@ taglib uri="/f" prefix="f"%><%@ taglib uri="/sys" prefix="sys"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>垫木登录</title>
	<%@include file="/WEB-INF/jsp/global/header.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
	<SCRIPT type="text/javascript">
	<!--
	//显示附着量,显示出端COIL No，制品尺寸
    function doCreate(){
		 form=document.forms["DataForm"];
		 var zsno=form.elements["zsno"].value;
    	var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			 cocoform.fillResult("DataForm", this.result);	  
		};
		var postContent="zsno="+zsno;	
		ajax.post("getZsdh.do", postContent, "POST");
    }
	 function showDmgs(){
		 form=document.forms["DataForm"];
		 var kuan=form.elements["kuan"].value;
		 var cang=form.elements["cang"].value;
		 var dmfx=form.elements["dmfx"].value;
		 var kbao=form.elements["kbao"].value;
		 var kw=form.elements["kw"].value;
    	var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			form.elements["dmgs"].value=this.result;	  
		};
		var postContent="kuan="+kuan+"&cang="+cang+"&dmfx="+dmfx+"&kbao="+kbao+"&kw="+kw;	
		ajax.post("getDmgs.do", postContent, "POST");
    }
	//提交
	function doSubmit(obj) {
		if(!window.confirm("确定保存 吗?")) return;
		cocoform.submit(obj,success);
	}
	
	function success() {
		if(this.code < 0) {
			coco.alert(this.msg);
			return;
		}
		alert("保存成功");
		doShowKZ();
		doQC();
		
	}
	function doShowKZ()  { 
		
		 form=document.forms["DataForm"];
		 var kuan=form.elements["kuan"].value;
		 var cang=form.elements["cang"].value;
		 var dmfx=form.elements["dmfx"].value;
		 var kbao=form.elements["kbao"].value;
		 var kw=form.elements["kw"].value;
    	var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			cocoform.fillResult("DataKZForm", this.result);	  
		};
		var postContent="kuan="+kuan+"&cang="+cang+"&dmfx="+dmfx+"&kbao="+kbao+"&kw="+kw;	
		ajax.post("getDmkz.do", postContent, "POST");
	} 
	//清空
	function doQC(){
		var oForm = document.forms["DataForm"];
		oForm.reset();
	    form.elements["zsno"].value="N";
		
	}
	
	//-->
	</SCRIPT>
</head>
<body>
	<ui:page title="垫木个数登录 ">
    <form name="DataForm" xu.ajax="true" xu.s="success()" method="post" action="save.do" >
		<table width="96%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="15%" />
				<col width="20%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
			<tr>
				<th style="text-align: right;">指示书号</th>
				<td colspan="9"><ui:input name="zsno"  maxlength="10" onkeydown="if(event.keyCode != 13) return;doCreate();" value="n"/></td>
				
			</tr>
			<tr>
				<th style="text-align: right;">尺寸</th>
				<td> 
				<ui:number name="kuan" title="尺寸.宽" scale="6" precision="2"  positive="true" maxlength="7" required="true" onblur="showDmgs();" />*
				<ui:number name="cang" title="尺寸.长" scale="6" precision="2" positive="true" maxlength="7" required="true" onblur="showDmgs();"/></td>
				<th style="text-align: right;">垫木方向</th>
				<td ><ui:input name="dmfx"  maxlength="1" onblur="showDmgs();" /></td>
				<th style="text-align: right;">捆包形式</th>
				<td ><ui:input name="kbao" required="true"  maxlength="3" onblur="showDmgs();"/></td>
				<th style="text-align: right;">库位</th>
				<td colspan="2"><ui:input name="kw"  maxlength="3" required="true"  onblur="showDmgs();"/></td>
			</tr>
			<tr>
				<th style="text-align: right;">库存个数</th>
				<td ><ui:input name="dmgs" readonly="true"  maxlength="10"/></td>
				<th style="text-align: right;">新增个数</th>
				<td ><ui:int name="adddmgs" required="true" maxlength="10"/></td>
				<th style="text-align: right;">操作员</th>
				<td colspan="2"><ui:input name="dann" required="true" maxlength="10" /></td>
				<th>班</th>
					<td>
					<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;" value="ban" headerText="" headerValue="" />
					</td>
					<th>组</th>
					<td>
					<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"  prop="nn:1;"  value="zu" headerText="" headerValue="" />
					</td>
			</tr>
		</table>
		
			<div class="opt-btm">
			<input type="button" class="button" value="清空" onclick="doQC();" />
			 <input type="button" class="button" value="保 存" onclick="doSubmit(this)" /></div>
		</form>
		
	 <form name="DataKZForm" >
		<table width="100%" class="pagination" border="0" align="center" cellpadding="0" cellspacing="0">
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<tr >
	   	<th>
			尺寸
		</th>
		<th>
			垫木方向
		</th>
		<th>
			捆包形式
		</th>
		<th>
			库位
		</th>
		<th >
			库存
		</th>
		<th>
			创建时间
		</th>
		<th>
			创建者
		</th>	
		
	</tr>
		<tr >
			<td><ui:number name="kuan"  maxlength="7"  />*
				<ui:number name="cang"  maxlength="7" /></td>
			<td><ui:input name="dmfx"  maxlength="1" /></td>
			<td><ui:input name="kbao"  maxlength="3"  /></td>
			<td><ui:input name="kw"  maxlength="3"  /></td>
			<td><ui:input name="dmgs"  maxlength="10"  /></td>
			<td><ui:input name="crea"  maxlength="20"  /></td>
			<td><ui:input name="dann"  maxlength="10"  /></td>
		</tr>

</table>
</form>
	</ui:page>
	
</body>
</html>
