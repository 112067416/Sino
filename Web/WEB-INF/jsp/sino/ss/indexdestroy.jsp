<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--

		//检索待消灭的包
		function fetchXm(jbno) {
			var form = document.forms["DataForm"];
			if(jbno == "") {
				form.elements["size"].value = "";
				form.elements["deng"].value = "";
				form.elements["zshu"].value = "";
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return false;
				}
				cocoform.fillResult(form, this.result);
			};
			ajax.post("fetchXm.do", coco.parseParam("jbno", jbno));
		}

		//PILE消除确认_dj
		function doDestroy() {
			var form = document.forms["DataForm"];
			cocoform.submit(form, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return false;
				}
				alert("Pile消灭完毕");
				cocoform.clear(form);
			});
		}
		//全选/反选操作
		function doCheck(checkbox, checks){	
			var e = document.getElementsByName(checks);
			for(var i = 0; i < e.length; i++){
				if(checkbox.checked==true){
					e[i].checked=true;	 
					continue;
				}
				e[i].checked=false;
			}	   
		}
		var tableform = new TableForm("DataTbl");
		//提交
		function doSubmit1() {
			tableform.formatTblForm("items",1);
			var ajax = new Cocoajax();
			cocoform.submit(document.forms["DataForm"], null, "确定消灭吗?");
		}
		// 提交实绩录入
		function doSubmit(obj) {
			tableform.formatTblForm("items",1);
			obj.disabled = true;
			var oForm = obj.form;
			 oForm.action = "destroyvalidate.do";
			 cocoform.submit(obj,function() {
				 if(this.code <= 0&&this.code!=-99) {
					 coco.alert(this.msg);
						return ;
			        }
				 validateSuccess(obj);});
		}
		//保存检查成功
		function validateSuccess(obj) {
			var oForm = obj.form;
			oForm.action = "destroy.do";   
			tableform.formatTblForm("items",1);
			cocoform.submit(oForm, null, "确定消灭吗?");
		}
		var tf = new TableForm("DataTbl");
		//新增一行
		function doAdd() {
			tf.insertRow('HidTbl');
		}
		
		//删除行
		function doDelete() {
			if(!confirm("确定删除行吗?")) return false;
			var oDataTbl = document.getElementById("DataTbl");
			if(!oDataTbl) return false;
			tf.removeTableRow(oDataTbl, "ids");
			
		}
		function success() {
			alert("Pile消灭完毕");
			window.location.reload();
		}
		
		//-->
		</script>
	</head>
<body>
<ui:page title="PILE消除">
    <form name="DataForm" xu.ajax="true" xu.s="success()" method="post" action="destroy.do" >
		<table id="DataTbl" width="98%" align="center" style="margin: 20px auto;"
				class="form">
				<colgroup>
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				</colgroup>
				<tr>
					<td><input type="checkbox" xu.target="ids" /></td>
					<th style="text-align: center;">PILE No.</th>
					<th style="text-align: center;">1.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					<th style="text-align: center;">2.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					<th style="text-align: center;">3.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					<th style="text-align: center;">4.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					
			        <th style="text-align: center;">5.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					<th style="text-align: center;">6.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					<th style="text-align: center;">7.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
					<th style="text-align: center;">8.缺陷</th>
					<th style="text-align: center;">G</th> 
					<th style="text-align: center;">枚数</th>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
				<tr >
					<td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
				</tr>
		</table>
			<div class="opt-btm">
			<input type="button" class="button" value="新 增 行" onclick="doAdd();" /> 
			<input type="button" class="button" value="删 除 行" onclick="doDelete();" />
			 <input type="button" class="button" value="确认消灭(S)" onclick="doSubmit(this)" /></div>
		</form>
	</ui:page>
	<table id="HidTbl" style="display: none;">
		<tr>
			    <td nowrap="nowrap"><input type="checkbox" name="ids" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:input name="jbno" maxlength="11"  title="PILE No" prop="type:abcn" /></td>
					<td nowrap="nowrap" style="text-align: center;">1.<ui:input name="qxdm1" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng1" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu1" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">2.<ui:input name="qxdm2" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng2" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu2" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">3.<ui:input name="qxdm3" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng3" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu3" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">4.<ui:input name="qxdm4" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng4" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu4" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">5.<ui:input name="qxdm5" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng5" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu5" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">6.<ui:input name="qxdm6" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng6" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu6" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">7.<ui:input name="qxdm7" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng7" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu7" maxlength="4"  title="枚数" /></td>
					<td nowrap="nowrap" style="text-align: center;">8.<ui:input name="qxdm8" maxlength="2"  title="缺陷" /></td>
					<td nowrap="nowrap" style="text-align: center;" ><ui:input name="deng8" maxlength="1" title="等级" /></td>
					<td nowrap="nowrap" style="text-align: center;"><ui:int name="zshu8" maxlength="4"  title="枚数" /></td>
		</tr>
	</table>
	</body>
</html>