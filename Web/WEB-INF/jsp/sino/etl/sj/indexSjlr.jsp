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
		<SCRIPT type="text/javascript">
		<!--
		function doRefresh()  { 
			//查询第三方接口备用卷状态
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {	
					return;
				}
				cocoform.fillResult("RcForm", this.result);
			};
			ajax.post("getRcInfo.do", "", "POST");
			
		} 
		window.setInterval(doRefresh, 60000); //指定1分钟刷新一次 (1000为1秒)
		//生成钢卷
		function doCreate() {
			
			var dataForm=document.forms["DataForm"];
			//指示书号
			var zsno=dataForm.elements["zsno"].value;
			//COIL No
			var jbno=dataForm.elements["jbno"].value;
			var isfp=dataForm.elements["isfp"].value;
			if(zsno==""){
				alert("指示书号不能为空");
				return false;
			}
			if(jbno==""){
				alert("COIL No不能为空");
				return false;
			}
			//查询操作方法和重内，判断要显示哪个登录
			var id=zsno+"-"+jbno;	
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					
					coco.alert(this.msg);
					return;
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
				dataForm.elements["zsno"].disabled = true;
				dataForm.elements["jbno"].disabled = true;
				//dataForm.elements["isfp"].disabled = true;
				//显示附着量,显示出端COIL No，制品尺寸
				showLr(zsno,jbno,this.result);
				//给from的Mode赋值
				dataForm.elements["mode"].value =this.result;
				//显示别纸与业联
				doViewKNYL();
				
				
			};
			var postContent="zsno="+zsno+"&jbno="+jbno+"";	
			ajax.post("getMode.do", postContent, "POST");
			
			
			
		}
		
		//显示附着量,显示出端COIL No，制品尺寸
        function showLr(zsno,jbno,mode){
        	var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					if(this.code==-2){
						coco.alert(this.msg);
						doCl();
						return;
					}
					coco.alert(this.msg);
					return;
				}
				var form=null;
				var formName;
				 if(mode=='S'){
					 form=document.forms["JcForm"];
					 formName="JcForm";
					 
				 }
				 if(mode=='C'){
					 form=document.forms["JzForm"];
					 formName="JzForm";
					
				 }
				 var chan=form.elements["chan"].value;
				 var deng=form.elements["deng"].value;
				 var jdyn=form.elements["jdyn"].value;
				 var jsyn=form.elements["jsyn"].value;
				 var qqdm=form.elements["qqdm"].value;
				 cocoform.fillResult(formName, this.result);
				 form.elements["chan"].value=chan;
				 form.elements["deng"].value=deng;
				 form.elements["jdyn"].value=jdyn;
				 form.elements["jsyn"].value=jsyn;
				 form.elements["qqdm"].value=qqdm;
				 
				 var dataForm=document.forms["DataForm"];
					var zsno=dataForm.elements["zsno"].value;
					var isfp=dataForm.elements["isfp"].value;
					var jbno=dataForm.elements["jbno"].value;
					var scxname=dataForm.elements["scxname"].value;
					var isfp=dataForm.elements["isfp"].value;
					
				 cocoform.fillResult("DataForm", this.result);
				   dataForm.elements["zsno"].value=zsno;
				  dataForm.elements["jbno"].value=jbno;
				  dataForm.elements["mode"].value=mode;
				  dataForm.elements["scxname"].value=scxname;
				  dataForm.elements["isfp"].value=isfp;
				  
				  form.elements["mode"].value=mode;
				  form.elements["isfp"].value=isfp;
				  
			};
			var postContent="zsno="+zsno+"&jbno="+jbno+"";	
			ajax.post("getLr.do", postContent, "POST");
        }
		
		
		//终了和中止激活
		function enableCtroll(ctrId){
			//清空实绩录入界面doQkJC()
			doQk();
			 var form=null;
			 var dataForm=document.forms["DataForm"];
			 var mode=dataForm.elements["mode"].value;
			 if(mode=='S'){
				 form=document.forms["JcForm"];
			 }
			 if(mode=='C'){
				 form=document.forms["JzForm"];
			 }
				//终了输入
				if(ctrId==1){
					if(form.elements["stat1"].value ==""){
						form.elements["stat2"].disabled = false;
						form.elements["jhou"].disabled = false;	
					}
					else{
						form.elements["stat2"].value="";
						form.elements["jhou"].value="";
						form.elements["stat2"].disabled = true;
						form.elements["jhou"].disabled = true;	
					}
						
				}
				//中止输入
				if(ctrId==2){
					if(form.elements["stat2"].value==""){
						form.elements["stat1"].disabled = false;
					    
					}
					else{
						form.elements["stat1"].value="";
						form.elements["stat1"].disabled = true;
						
					}	
				}
		}	
		
		// 重录
		function doCl() {
			var dataForm=document.forms["DataForm"];
			dataForm.elements["zsno"].disabled = false;
			dataForm.elements["jbno"].disabled = false;
			dataForm.elements["isfp"].disabled = false;
				JzDataArea.style.display = "none";
				JcDataArea.style.display = "none";
				dataForm.elements["isfp"].value="1";
				dataForm.elements["jbno"].focus();
		}
	
		
		function save(obj){
			obj.disabled = true;
			var oForm = obj.form;
			 //输入终了或中止
			 if(oForm.elements["stat1"].value !=""||oForm.elements["stat2"].value !=""){
				//清空实绩录入界面doQkJC()
				doQk();
			 }
			 oForm.action = "validate.do";
			 cocoform.submit(obj,function() {
				 if(this.code <= 0&&this.code!=-99) {	
					  var msg= new Array(); 
					  msg=this.msg.split('&');
					  alert(msg[0]);
					  exceptionPosition(this.code,oForm.elements["mode"].value);
					  obj.disabled = false;
					  return ;
			        }
				 validateSuccess(obj,this.code,this.msg);});
			
		}
		//保存检查成功
		function validateSuccess(obj,code,msg) {
			var dataForm=document.forms["DataForm"];
			var mode=dataForm.elements["mode"].value;
			var form=null;
			 if(mode=='S'){
				 form=document.forms["JcForm"];	
			 }
			 if(mode=='C'){
				 form=document.forms["JzForm"];	
			 }
			 if(code==-99){
				 var msgs= new Array(); 
				 msgs=msg.split('&');
				if(!window.confirm(msgs[0]+" ,确定要终了吗?")) {
					obj.disabled = false;
					return;
				}
			}else{
				if(form.elements["stat1"].value=="1"){
					 if(!window.confirm("确定要终了吗?")) {
						 obj.disabled = false;
						 return;
					 }
				 }else if(form.elements["stat2"].value=="1"){
					 if(!window.confirm("确定要中止吗?")){
						 obj.disabled = false;
						 return;
					 }
				 }else {
					 if(!window.confirm("确定要保存吗?")){
						 obj.disabled = false;
						 return;
					 }
				 }
			}
			 
			
			var oForm = obj.form;
			oForm.action = "saveSj.do";   
		    cocoform.submit(obj,success);
		}
		//保存成功
		function success() {
			var dataForm=document.forms["DataForm"];
			var mode=dataForm.elements["mode"].value;
			if(this.code <= 0) {
				coco.alert(this.msg);
				
				return ;
			}
			var form=null;
			 if(mode=='S'){
				 form=document.forms["JcForm"];	
			 }
			 if(mode=='C'){
				 form=document.forms["JzForm"];	
			 }
         
            if(this.code ==2){
            	//终了
            	 if(form.elements["stat1"].value=="1"){
            		 alert(this.msg);
    			 }else{
    				 alert("保存成功");
    			 }
				
			}
			else{
				alert("保存成功");
			}
            
			
			 if(form.elements["stat1"].value=="1"||form.elements["stat2"].value=="1"){
				 //重新登录
				   //doCl();
				 dataForm.elements["zsno"].disabled = false;
				dataForm.elements["jbno"].disabled = false;
				dataForm.elements["isfp"].disabled = false;
					JzDataArea.style.display = "none";
					JcDataArea.style.display = "none";
					dataForm.elements["jbno"].focus();
					 
				   return ;
			}
			 var chan=form.elements["chan"].value;
			 var jbno=form.elements["jbno"].value;
         	 var pzno=dataForm.elements["pzno"].value;
			 if(jbno!=""&&chan!="9"){
	            	//打印制品卡
	            	if((pzno=="2")||(pzno=="1"&&chan!="1")){
	            		printZpk(jbno,'2');
	            	} 	
	         } 	
				//打印不良扣除联络单
				doDyLossc(mode);
			//显示附着量,显示出端COIL No，制品尺寸
			//指示书号
			var zsno=dataForm.elements["zsno"].value;
			//COIL No
			var jbno =dataForm.elements["jbno"].value;	
			showLr(zsno,jbno,mode);
		}
		
		
		//-->
		</SCRIPT>
	</head>
	<body >
		<ui:print id="lodop"/>
		<ui:page title="ETL生产实绩登录 ">
			<fieldset class="group" style="">
				<legend>入则信息</legend>
			<form name="RcForm">
			<table width="98%" align="center" style="height: 10px;"
				class="form">
				<colgroup>
					<col width="20%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col />
				</colgroup>
				<tr>
					<th>
						当前卷
					</th>
					<td>
						<ui:input  name="dqjbno" maxlength="15"  readonly="true" title="当前卷" value="rcitem.dqjbno"/>
					</td>
					<th>
						
					</th>
					<td>
						
					</td>
					<th>
					    备用卷
					</th>
					<td>
						<ui:input  name="byjbno" maxlength="15"  readonly="true" title="备用卷" value="rcitem.byjbno"/>
					</td>
				</tr>
				</table>
				</form>	
		</fieldset>
		  <form name="DataForm">
			<table width="98%" align="center" style="margin: 20px auto;"
				class="form">
				<colgroup>
					<col width="20%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col />
				</colgroup>
				
				<tr>
					<th>
						生产线
					</th>
					<td>
						<ui:input  name="scxname" maxlength="15"  readonly="true" title="担当者所在的生产线" value="item.scxname"/>
						
					</td>
					<th>
						指示书
					</th>
					<td>
						<ui:input id="zsno" name="zsno" maxlength="6"  title="输入指示书号" required="true" value="item.zsno"/>
					</td>
					<th>
						COIL No.
					</th>
					<td>
						<ui:input id="oldjbno" name="jbno" maxlength="7"  title="输入材料（母材或制品）" onkeydown="if(event.keyCode != 13) return;doCreate();" required="true" value="item.jbno"/>
						<input type="hidden" name="mode"  id="mode"/>
						<input type="hidden" name="pzno"  id="pzno"  />
					</td>
				</tr>
				<tr>
					<th>
						是否合格
					</th>
					<td>
						<ui:select list="'1':'是','0':'否'" name="isfp" onchange="doCreate();"/>
					</td>
					<th>
						订货NO
					</th>
					<td>
						<ui:input  name="dhno" maxlength="15"  readonly="true" title="订货NO" value="item.dhno"/>
					</td>
					<th>
					    用户略称
					</th>
					<td>
						<ui:input  name="abbr" maxlength="15"  readonly="true" title="用户略称" value="item.abbr"/>
					</td>
				</tr>
			</table>	
		</form>
		<div id="JcDataArea" style="display: none;">
		<form name="JcForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="saveSj.do">
			<input type="hidden" name="elin" />
			<input type="hidden" name="zsno" />
			<input type="hidden" name="zrjb" />
			<input type="hidden" name="dhno" />
			<input type="hidden" name="sjzl" />	
			<input type="hidden" name="mode"/>	
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
					<td><input type="button" class="button" value="查看别纸与业联" accesskey="v" onclick="doViewZs();" /></td>
				</tr>
			</table>
			<table id="PileTbl" width="96%" align="center" class="form" style="border: 1px solid #999;">
				<colgroup>
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="25%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="15%" />
				</colgroup>
				<tr>
					<th>
						母卷板终了
					</th>
					<td>
						<ui:int id="stat1JC" name="stat1" maxlength="1"  title="进行母材终了时输入1" onblur="enableCtroll(1);" positive="true"/>
					</td>
					<th>
						附着量
					</th>
					<td>
						<ui:input id="fuzmJC" name="fuzm" maxlength="5"  readonly="true" title="表示电镀的正面厚度要求量"/>/<ui:input id="fufmJC" name="fufm" maxlength="5"  readonly="true" title="表示电镀的反面厚度要求量"/>
						&nbsp;
						<ui:number id="sczmJC" name="sczm" maxlength="5" scale="4" precision="2"  onchange="checkFu();"  title="输入电镀的正面厚度实绩量" />/<ui:number id="scfmJC" name="scfm"  onchange="checkFu();"  maxlength="5" scale="4" precision="2" title="输入电镀的反面厚度实绩量" />
					</td>
					<th>装入中止</th>
					<td><ui:int id="stat2JC" name="stat2" maxlength="1"  title="母材装入中止时，输入1" onblur="enableCtroll(2);" positive="true"/></td>
					<th>卷厚(mm)</th>
					<td><ui:int id="jhouJC" name="jhou" maxlength="3"   title="装入中止后，输入留下的厚度" positive="true"/></td>
				</tr>
			</table>
			<table width="96%" align="center" class="form" style="border: 1px solid #999; margin-top: 10px;">
				<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="15%" /><col width="15%" /><col width="15%" /><col width="15%" /></colgroup>
				<tr style="height: 40px; line-height: 40px;">
					<th colspan="2">
						出端COIL No.
					</th>
					<td colspan="2" style="vertical-align: middle;">
						<ui:input  id="jcjbno" name="jbno" maxlength="11" readonly="true" title="表示经过ETL后，生成产品的编号"/>
					</td>
					<th colspan="2">
						制品尺寸
					</th>
					<td colspan="2">
						<ui:input id="houuJC" name="houu" maxlength="5"  readonly="true" title="制品尺寸•厚"/>*
						<ui:input id="kuanJC" name="kuan" maxlength="7"  readonly="true" title="制品尺寸•宽"/>
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
						<ui:input name="chan" maxlength="1" onblur="showCHZL();" title="输入与等级共同说明产出的品质"   />
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
						板厚不良
					</th>
					<td>
						<ui:input name="bhbl" maxlength="1"  title="输入厚板情况"  />
					</td>
					
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
					<td colspan="3">
					<ui:number name="zbog" maxlength="2" title="输入中波纹高度" positive="true"/> - <ui:number name="zboj" maxlength="3" title="输入中波纹间隔" positive="true"/>
					</td>
					
				</tr>
				<tr>
				<th>
						卷取TRNo.
					</th>
					<td>
						<ui:input name="jsno" maxlength="1"  title="输入1或2" />
					</td>
				</tr>
				
			</table>
			<div style="width: 96%; text-align: right; margin-top: 15px;">
				<input type="button" class="button" value="实绩录入确认" onclick="save(this);" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空(C)" accesskey="c"
					onclick="doQk('all');" />
				<input type="button" class="button" value="返回(A)" accesskey="a"
					onclick="doCl();" />
			</div>
		</form>
	</div>
			
			
	<div id="JzDataArea" style="display: none;">
			<form name="JzForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="saveSj.do">
	        <input type="hidden" name="elin" />
	        <input type="hidden" name="zsno" />
			<input type="hidden" name="zrjb" />
			<input type="hidden" name="dhno" />
			<input type="hidden" name="mode" />
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
					<td><input type="button" class="button" value="查看别纸与业联" accesskey="v" onclick="doViewZs();" /></td>
				</tr>
			</table>
			<table id="PileTbl" width="96%" align="center" class="form" style="border: 1px solid #999;">
				<colgroup>
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="25%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="15%" />
				</colgroup>
				<tr>
					<th>
						母卷板终了
					</th>
					<td>
						<ui:int id="stat1JZ" name="stat1" maxlength="1"  title="进行母材终了时输入1" onblur="enableCtroll(1);" positive="true"/>
					</td>
					<th>
						附着量
					</th>
					<td>
						<ui:input id="fuzmJZ" name="fuzm" maxlength="5"  readonly="true" title="表示电镀的正面厚度要求量"/>/<ui:input id="fufmJZ" name="fufm" maxlength="5"  readonly="true" title="表示电镀的反面厚度要求量"/>
						&nbsp;
						<ui:number id="sczmJZ" name="sczm"  onchange="checkFu();"  maxlength="5" scale="4" precision="2"   title="输入电镀的正面厚度实绩量" />/<ui:number id="scfmJZ" name="scfm"  onchange="checkFu();"  maxlength="5" scale="4" precision="2" title="输入电镀的反面厚度实绩量" />
					</td>
					<th>装入中止</th>
					<td><ui:int id="stat2JZ" name="stat2" maxlength="1"  title="母材装入中止时，输入1" onblur="enableCtroll(2);" positive="true"/></td>
					<th>卷厚(mm)</th>
					<td><ui:int id="jhouJZ" name="jhou" maxlength="3"  title="装入中止后，输入留下的厚度"  positive="true"/></td>
				</tr>
			</table>
			<table width="96%" align="center" class="form" style="border: 1px solid #999; margin-top: 10px;">
				<colgroup><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="15%" /><col width="15%" /><col width="15%" /><col width="15%" /></colgroup>
				<tr style="height: 40px; line-height: 40px;">
					<th colspan="2">
						出端COIL No.
					</th>
					<td colspan="2" style="vertical-align: middle;">
						<ui:input id="jzjbno" name="jbno" maxlength="11"  readonly="true" title="表示经过ETL后，生成产品的编号"/>
					</td>
					<th>
						制品尺寸
					</th>
					<td>
						<ui:input id="houuJZ" name="houu" maxlength="5"  readonly="true" title="制品尺寸•厚"/>*
						<ui:input id="kuanJZ" name="kuan" maxlength="7"  readonly="true" title="制品尺寸•宽"/>
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
						<ui:input  id="jbcnJZ" name="jbcn" maxlength="9"  readonly="true" title="卷取长"/>
					</td>
					<th>
						产量代码
					</th>
					<td>
						<ui:input name="chan" maxlength="1" onblur="showCHZL();" title="输入与等级共同说明产出的品质"  />
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
						<ui:input name="jsyn" maxlength="2"  title="输入计数员代码"   />
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
						<ui:input name="jsno" maxlength="1"  title="输入1或2" />
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
				<input type="button" class="button" value="实绩录入确认" onclick="save(this);" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空(C)" accesskey="c"
					onclick="doQk('all');" />
				<input type="button" class="button" value="返回(A)" accesskey="a"
					onclick="doCl();" />
			</div>
			</form>
   </div>
			
		
		</ui:page>	
		
<ui:panel id="View" title="查看生产指示说明" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
<ui:panel id="LosscView" title="不良扣除联络单详细信息" popup="true" display="false" closable="true" >
<DIV id="LosscArea" ></DIV>
</ui:panel>
		<br />
	</body>
</html>