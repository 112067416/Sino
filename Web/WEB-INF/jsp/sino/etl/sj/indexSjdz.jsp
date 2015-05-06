<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/sjlrcompute.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/print.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		//生成钢卷
		function doCreate() {
			//出端COIL No.
			var dataForm=document.forms["DataForm"];
			var isfp=dataForm.elements["isfp"].value;
			var jbno=dataForm.elements["jbno"].value;
			if(jbno==""){
				alert("出端COIL No不能为空");
				return false;
			}
			
			//查询操作方法和重内，判断要显示哪个登录
			var ajax = new Cocoajax();
			ajax.callback = function() {			
				if(this.code <= 0) {
					coco.alert(this.msg);
					return false;
				}
			
				if(this.result == "S" ) {
					//不合格
					if(isfp=="0"){
						this.result="C";
						JcDataArea.style.display = "none";
						JzDataArea.style.display = "block";
						
					}else{
						JcDataArea.style.display = "block";
						JzDataArea.style.display = "none";
						
					}
					
				} else {
					JcDataArea.style.display = "none";
					JzDataArea.style.display = "block";
					
				}
				dataForm.elements["jbno"].disabled = true;
				//显示制品信息
				
				showZp(jbno,this.result);
				dataForm.elements["mode"].value =this.result;
				
				
			};
			var postContent="jbno="+jbno;	
			ajax.post("getModeForModify.do", postContent, "POST");
			
			
			
		}
		
		//显示制品信息
        function showZp(jbno,mode){
			
        	var ban;
        	var zu;
        	var form=null;
        	var formName="";
        	var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					JzDataArea.style.display = "none";	
					JcDataArea.style.display = "none";
					ciolno.disabled = false;
					return;
				}
				if(mode=='S'){
					 form=document.forms["JcForm"];
					 formName="JcForm";
				 }
				 if(mode=='C'){
					 form=document.forms["JzForm"];
					 formName="JzForm";
				 }
				        var dataForm=document.forms["DataForm"];
					   var isfp=dataForm.elements["isfp"].value;
					 
						cocoform.fillResult(formName, this.result);
						form.elements["mode"].value=mode;	
						form.elements["isfp"].value=isfp;
						
			};
			var postContent="jbno="+jbno+"&mode="+mode;	
			
			ajax.post("getZpInfo.do", postContent, "POST");
        }
		
		
		// 重录
		function doCl() {
			window.location.reload();
		}
		
		
		function save(obj){
			obj.disabled = true;
			var oForm = obj.form;
			 oForm.action = "validate.do";
			 cocoform.submit(obj,function() {
				 if(this.code <= 0) {			
					  var msg= new Array(); 
					  msg=this.msg.split('&');
					  alert(msg[0]);
					  exceptionPosition(this.code,oForm.elements["mode"].value);
					  obj.disabled = false;
					  return ;
			        }
				 validateSuccess(obj);});
		}
		//保存检查成功
		function validateSuccess(obj) {
			
			if(!window.confirm("确定要保存吗?")){
				 obj.disabled = false;
				return;
			}
			var oForm = obj.form;
			oForm.action = "updateSj.do";   
		    cocoform.submit(obj,success);
		}
		//保存成功
		function success() {
			//录入FORM类型
			var mode=document.getElementById("mode").value;
			if(this.code <= 0) {
				coco.alert(this.msg);
				 
				return ;
			} 
			alert("保存成功");
			  var form=null;
				 if(mode=='S'){
					 form=document.forms["JcForm"];	
				 }
				 if(mode=='C'){
					 form=document.forms["JzForm"];	
				 }
			
            	
            var chan=form.elements["chan"].value;
			 var jbno=form.elements["jbno"].value;
        	 var pzno=form.elements["pzno"].value;
			 if(jbno.value!=""&&chan!="9"){
	            	
	            	//打印制品卡
	            	if((pzno=="2")||(pzno=="1"&&chan!="1")){
	            		printZpk(jbno,'2');
	            	} 	
	         } 	
			//打印不良扣除联络单
			doDyLossc(mode);
			JzDataArea.style.display = "none";	
			JcDataArea.style.display = "none";
			document.getElementById("ciolno").disabled = false;
		}
		
		//-->
		</SCRIPT>
	</head>
	<body <c:if test="${jbno!=null}">onload="doCreate()"</c:if>>
		<ui:print id="lodop"/>
		<ui:page title="ETL生产实绩订正 ">
		<form name="DataForm">
			<table width="98%" align="center" style="margin: 20px auto;"
				class="form">
				<colgroup>
					<col /><col />
					<col  /><col />
					<col /><col />
					<col  /><col />
				    
				</colgroup>
				<tr>
					<th>
						出端COIL No.
					</th>
					<td>
						<ui:input id="ciolno" name="jbno" maxlength="8" title="输入材料（制品）" onkeydown="if(event.keyCode != 13) return;doCreate();" value="jbno" />
						<input type="hidden" name="mode"  id="mode"/>
						
					</td>
					<th>
						是否合格
					</th>
					<td>
						<ui:select list="'1':'是','0':'否'" name="isfp" onchange="doCreate();" value="isfp" />
					</td>
					
				</tr>
			</table>	
			</form>	
<div id="JcDataArea" style="display: none;">
		<form name="JcForm" xu.ajax="" xu.r="" xu.s="success();" xu.color="1" method="post" action="updateSj.do" >
			<input type="hidden" name="elin"   id="elinJC"/>
			<input type="hidden" name="zsno" />
			<input type="hidden" name="zrjb" />
			<input type="hidden" name="dhno" />
			<input type="hidden" name="pzno" />
			<input type="hidden" name="sjzl" />	
			<input type="hidden" name="mode" id="modeJC"/>
			<input type="hidden" name="isfp" />	
			<table id="BzTbl" width="96%" align="center" class="form" style="margin: 5px 0;">
				<colgroup><col width="15%"/><col /><col width="10%" /><col width="10%" /></colgroup>
				<tr>
					<td><span style="font-size: 16px;">录入方式：卷长</span></td>
					<th>班</th>
					<td>
					<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;" />
					</td>
					<th>组</th>
					<td>
					<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"  prop="nn:1;"  />
					</td>
					<td></td>
				</tr>
			</table>
			<table width="100%" align="center" class="form" style="border: 1px solid #999; margin-top: 10px;">
				<colgroup>
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="14%" />
					<col width="14%" />
					<col width="14%" />
					<col width="14%" />
					<col width="14%" />
				</colgroup>
				<tr style="height: 40px; line-height: 40px;">
					<th >
						出端COIL No.
					</th>
					<td  style="vertical-align: middle;">
						<ui:input  id="jcjbno" name="jbno" maxlength="11"  readonly="true" title="表示经过ETL后，生成产品的编号"/>
					</td>
					<th >
						制品尺寸
					</th>
					<td >
						<ui:input id="houuJC" name="houu" maxlength="5"  readonly="true" title="制品尺寸•厚"/>*
						<ui:input id="kuanJC" name="kuan" maxlength="7"  readonly="true" title="制品尺寸•宽"/>
					</td>
					<th>
						附着量
					</th>
					<td >
						<ui:input id="fuzmJZ" name="fuzm" maxlength="5"  readonly="true" title="表示电镀的正面厚度要求量"/>/<ui:input id="fufmJZ" name="fufm" maxlength="5"  readonly="true" title="表示电镀的反面厚度要求量"/>	
					</td>
					<th>
						镀锡附着量
					</th>
					<td>
						<ui:number id="sczmJC" name="sczm" maxlength="5" scale="4" precision="2"  onchange="checkFu();"  title="输入电镀的正面厚度实绩量" />/<ui:number id="scfmJC" name="scfm"  onchange="checkFu();"  maxlength="5" scale="4" precision="2" title="输入电镀的反面厚度实绩量" />
					</td>
				</tr>
				<tr>
					<th>
						卷取长
					</th>
					<td>
						<ui:int id="jbcnJC" name="jbcn" maxlength="5" title="输入生成每一卷的长度"  onblur="showCHZL();" positive="true"/>
					</td>
					<th>
						CUT长
					</th>
					<td>
						<ui:int id="cutcJC" name="cutc" maxlength="5" title="输入切割损耗"  onblur="showCHZL();" positive="true"/>
					</td>
					<th colspan="2">
						LOSS1长<ui:input id="LossCc" name="losc" maxlength="5" onblur="showCHZL();" title="输入其它损耗" />&nbsp;&nbsp;LOSS1缺陷&nbsp;&nbsp;<ui:input id="LossQxc" name="losq" maxlength="2" title="LOSS缺陷1"/>
					</th>
					<th colspan="2">
						LOSS2长<ui:input name="losc2" maxlength="5" onblur="showCHZL();" title="输入其它损耗" />&nbsp;&nbsp;LOSS2缺陷&nbsp;&nbsp;<ui:input name="losq2" maxlength="2" title="LOSS缺陷2"/>
					</th>
				</tr>
				<tr>
					<th>
						产量代码
					</th>
					<td>
						<ui:input name="chan" maxlength="1"  onblur="showCHZL();" title="输入与等级共同说明产出的品质"   />
					</td>
					<th>
						等级
					</th>
					<td>
						<ui:input name="deng" maxlength="3"  title="输入与产量代码共同说明产出的品质"  />
					</td>
					<th>
						缺陷
					</th>
					<td>
						<ui:input name="qqdm" maxlength="2"  title="输入欠缺代码" />
					</td>
					<th>
						出货重量
					</th>
					<td>
					<ui:input  id="zpzlJC" name="zpzl" maxlength="9"  readonly="true" title="制品的出货重量,即制品重量"/>
					</td>
				</tr>
				<tr>
					<th>
						检定员
					</th>
					<td>
						<ui:input name="jdyn" maxlength="2"  title="输入检定员代码" />
					</td>
					<th>
						计数员
					</th>
					<td>
						<ui:input name="jsyn" maxlength="2"  title="输入计数员代码"  />
					</td>
					<th>
						P.H个数
					</th>
					<td>
						<ui:int name="phgs" maxlength="2"  title="输入P.H"  positive="true"/>
					</td>
					<th>
						溶接个数
					</th>
					<td>
						<ui:int name="rjet" maxlength="1"  title="输入溶接个数ETL"  positive="true"/>
					</td>
				</tr>
				<tr>
					<th>
						耳波Op
					</th>
					<td>
						<ui:number name="bopg" maxlength="2"  title="输入边波纹OP高度" positive="true"/> - <ui:number name="bopj" maxlength="3"  title="输入边波纹OP间隔" positive="true"/>
					</td>
					<th>
						耳波Dr
					</th>
					<td>
						<ui:number name="bdrg" maxlength="2"  title="输入边波纹Dr高度"  positive="true"/> - <ui:number name="bdrj" maxlength="3"  title="输入边波纹Dr间隔"  positive="true"/>
					</td>
					<th>
						中伸
					</th>
					<td >
					<ui:number name="zbog" maxlength="2" title="输入中波纹高度" positive="true"/> - <ui:number name="zboj" maxlength="3" title="输入中波纹间隔" positive="true"/>
					</td>
					<th>
						板厚不良
					</th>
					<td>
						<ui:input name="bhbl" maxlength="1"  title="输入厚板情况"  />
					</td>
					
				</tr>
				<tr>
					<th>
						卷取TRNo.
					</th>
					<td>
						<ui:input name="jsno" maxlength="1"  title="输入1或2" required="true" />
					</td>
				</tr>
			</table>
			<div style="width: 96%; text-align: right; margin-top: 15px;">
				<input type="button" class="button" value="实绩录入确认" onclick="save(this);" />		
			</div>
		</form>
	</div>
<div id="JzDataArea" style="display: none;">
			<form name="JzForm" xu.ajax="" xu.r="" xu.s="success();" xu.color="1" method="post" action="updateSj.do">
	       <input type="hidden" name="elin"   id="elinJC"/>
			<input type="hidden" name="zsno" />
			<input type="hidden" name="zrjb" />
			<input type="hidden" name="pzno" />
			<input type="hidden" name="dhno" />
			<input type="hidden" name="mode"  id="modeJZ"/>
			<input type="hidden" name="isfp" />
				<table id="BzTbl" width="96%" align="center" class="form" style="margin: 5px 0;">
				<colgroup><col width="15%"/><col /><col width="10%" /><col width="10%" /></colgroup>
				<tr>
					<td><span style="font-size: 16px;">录入方式：卷重</span></td>
					<th>班</th>
					<td>
					<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;" />
					</td>
					<th>组</th>
					<td>
					<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"  prop="nn:1;"  />
					</td>
					<td></td>
				</tr>
			</table>
			<table width="100%" align="center" class="form" style="border: 1px solid #999; margin-top: 10px;">
				<colgroup>
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="14%" />
					<col width="14%" />
					<col width="14%" />
					<col width="14%" />
					<col width="14%" />
				</colgroup>
				<tr style="height: 40px; line-height: 40px;">
					<th >
						出端COIL No.
					</th>
					<td style="vertical-align: middle;">
						<ui:input id="jzjbno" name="jbno" maxlength="11"  readonly="true" title="表示经过ETL后，生成产品的编号"/>
					</td>
					<th>
						制品尺寸
					</th>
					<td>
						<ui:input id="houuJZ" name="houu" maxlength="5"  readonly="true" title="制品尺寸•厚"/>*
						<ui:input id="kuanJZ" name="kuan" maxlength="7"  readonly="true" title="制品尺寸•宽"/>
					</td>
					<th>
						附着量
					</th>
					<td >
						<ui:input id="fuzmJC" name="fuzm" maxlength="5"  readonly="true" title="表示电镀的正面厚度要求量"/>/<ui:input id="fufmJC" name="fufm" maxlength="5"  readonly="true" title="表示电镀的反面厚度要求量"/>
						
					</td>
					<th>
						镀锡附着量
					</th>
					<td>
						<ui:number id="sczmJC" name="sczm" maxlength="5" scale="4" precision="2"  onchange="checkFu();"  title="输入电镀的正面厚度实绩量" />/<ui:number id="scfmJC" name="scfm"  onchange="checkFu();"  maxlength="5" scale="4" precision="2" title="输入电镀的反面厚度实绩量" />
					</td>
				</tr>
				<tr>
					<th>
						实称重量
					</th>
					<td>
						<ui:int id="sjzlJZ" name="sjzl" maxlength="5"  title="表示实际所得重量"  onblur="showCHZL();" positive="true"/>
					</td>
					<th>
						CUT长
					</th>
					<td>
						<ui:int id="cutcJZ" name="cutc" maxlength="5" title="输入切割损耗" onblur="showCHZL();" positive="true"/>
					</td>
					<th colspan="2">
						LOSS1长<ui:input id="LossCc" name="losc" maxlength="5" onblur="showCHZL();" title="输入其它损耗" />&nbsp;&nbsp;LOSS1缺陷&nbsp;&nbsp;<ui:input id="LossQxc" name="losq" maxlength="2" title="LOSS缺陷1"/>
					</th>
					<th colspan="2">
						LOSS2长<ui:input name="losc2" maxlength="5" onblur="showCHZL();" title="输入其它损耗" />&nbsp;&nbsp;LOSS2缺陷&nbsp;&nbsp;<ui:input name="losq2" maxlength="2" title="LOSS缺陷2"/>
					</th>
				</tr>
				<tr>
					<th>
						卷取长
					</th>
					<td>
						<ui:input  id="jbcnJZ" name="jbcn" maxlength="9"  readonly="true" title="计算长"/>
					</td>
					<th>
						产量代码
					</th>
					<td>
						<ui:input name="chan" maxlength="1" onblur="showCHZL();" title="输入与等级共同说明产出的品质"   />
					</td>
					<th>
						等级
					</th>
					<td>
						<ui:input name="deng" maxlength="3"  title="输入与产量代码共同说明产出的品质" />
					</td>
					<th>
						缺陷
					</th>
					<td>
						<ui:input name="qqdm" maxlength="2"  title="输入欠缺代码" />
					</td>
				</tr>
				<tr>
					<th>
						出货重量
					</th>
					<td>
						<ui:input id="zpzlJZ" name="zpzl" maxlength="9"  readonly="true" title="制品的出货重量,即制品重量"/>
					</td>
					<th>
						检定员
					</th>
					<td>
						<ui:input name="jdyn" maxlength="2"  title="输入检定员代码"  />
					</td>
					<th>
						计数员
					</th>
					<td>
						<ui:input name="jsyn" maxlength="2"  title="输入计数员代码"    />
					</td>
					<th>
						板厚不良
					</th>
					<td>
						<ui:input name="bhbl" maxlength="1"  title="输入厚板情况"  />
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<th>
						卷取TR No.
					</th>
					<td>
						<ui:input name="jsno" maxlength="1"  title="输入1或2"  />
					</td>
					<th>
						P.H个数
					</th>
					<td>
						<ui:int name="phgs" maxlength="2"  title="输入P.H"  positive="true"/>
					</td>
					<th>
						溶接个数
					</th>
					<td>
						<ui:int name="rjet" maxlength="1"  title="输入溶接个数ETL"  positive="true"/>
					</td>
					
				</tr>
				<tr>
					
					<th>
						耳波Op
					</th>
					<td>
						<ui:number name="bopg" maxlength="2"  title="输入边波纹OP高度" positive="true"/> - <ui:number name="bopj" maxlength="3"  title="输入边波纹OP间隔" positive="true"/>
					</td>
					<th>
						耳波Dr
					</th>
					<td>
						<ui:number name="bdrg" maxlength="2"  title="输入边波纹Dr高度"  positive="true"/> - <ui:number name="bdrj" maxlength="3"  title="输入边波纹Dr间隔"  positive="true"/>
					</td>
					<th>
						中伸
					</th>
					<td>
					<ui:number name="zbog" maxlength="2" title="输入中波纹高度" positive="true"/> - <ui:number name="zboj" maxlength="3" title="输入中波纹间隔" positive="true"/>
					</td>
				</tr>
			</table>
			<div style="width: 96%; text-align: right; margin-top: 15px;">
				<input type="button" class="button" value="实绩录入确认"
					onclick="save(this);" />
			</div>
			</form>
  </div>		
		</ui:page>
		<ui:panel id="LosscView" title="不良扣除联络单详细信息" popup="true" display="false" closable="true" >
<DIV id="LosscArea" ></DIV>
</ui:panel>
	</body>
</html>