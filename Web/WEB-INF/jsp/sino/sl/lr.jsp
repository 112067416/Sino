<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/global/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/sllrcompute.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/print.js"></script>
<script type="text/javascript">
<!--

	// 显示制品表单
	function doCheck() {
		var oForm = document.forms["LeadForm"];
		var oSlin = oForm.elements["slin"];// 生产线别
		var oZsno = oForm.elements["zsno"];// 指示书号
		var oRczpno = oForm.elements["rczpno"];// 入端卷号
		var vSlin = oSlin.value.replace(/(^\s*)|(\s*$)/g, "");// 生产线别
		var vZsno = oZsno.value.replace(/(^\s*)|(\s*$)/g, "");// 指示书号
		var vRczpno = oRczpno.value.replace(/(^\s*)|(\s*$)/g, "");// 入端卷号
		// 录入检查
		if (!vSlin || vSlin.length == 0) {
			alert("非生产线人员不能录入实绩");
			return;
		}
		if (!vZsno || vZsno.length == 0) {
			alert("必须输入：指示书号");
			return;
		}
		if (!vRczpno || vZsno.length == 0) {
			alert("必须输入：入端卷号");
			return;
		}
		var ajax = new Cocoajax();
		var postContent = "zsno=" + vZsno + "&rczpno=" + vRczpno + "&slin=" + vSlin;
		ajax.callback = function() {
			if (this.code < 0) {
				coco.alert(this.msg);
			} else {
				// 使输入框
				
				oZsno.disabled = true;
				oRczpno.disabled = true;
				// 显示录入内容
				DataDiv.style.display = "block";
				// 填充数据
				cocoform.fillResult("DataForm", this.result);
				////显示别纸与业联
				doViewKNYL();
			}
		};
		ajax.post("check.do", postContent);
	}
	
	

//终了和中止激活
function enableCtroll(ctrId){
	//清空实绩录入界面doQkJC()
	//doFormReset();
	 var form=null;
	 var form=document.forms["DataForm"];
		//终了输入
		if(ctrId==1){
			if(form.elements["zl"].value ==""){
				form.elements["zz"].disabled = false;
				form.elements["jh"].disabled = false;	
			}
			else{
				form.elements["zz"].disabled = true;
				form.elements["jh"].disabled = true;	
			}
				
		}
		//中止输入
		if(ctrId==2){
			if(form.elements["zz"].value==""){
				form.elements["zl"].disabled = false;
			    
			}
			else{
				form.elements["zl"].disabled = true;
				
			}	
		}
}	

	//清空输入界面
	function doFormReset(){
		var oForm = document.forms["DataForm"];
		var zl = oForm.elements["zl"].value;
		var zz = oForm.elements["zz"].value;
		var jh = oForm.elements["jh"].value;
		var ying = oForm.elements["ying"].value;
		var rczpno = oForm.elements["rczpno"].value;
		var ban = oForm.elements["ban"].value;
		var zu = oForm.elements["zu"].value;
		var bdan = oForm.elements["bdan"].value;
		var zsno = oForm.elements["zsno"].value;
		var slin = oForm.elements["slin"].value;
		var zzsd = oForm.elements["zzsd"].value;
		var jbno = oForm.elements["jbno"].value;
		var houu = oForm.elements["houu"].value;
		var kuan = oForm.elements["kuan"].value;
		var cang = oForm.elements["cang"].value;
		var sczm = oForm.elements["sczm"].value;
		var scfm = oForm.elements["scfm"].value;
		//清空DataForm
		oForm.reset();
		oForm.elements["zl"].value=zl;
		oForm.elements["zz"].value=zz;
		oForm.elements["jh"].value=jh;
		oForm.elements["rczpno"].value=rczpno;
		oForm.elements["ban"].value=ban;
		oForm.elements["zu"].value=zu;
		oForm.elements["bdan"].value=bdan;
		oForm.elements["zsno"].value=zsno;
	    oForm.elements["slin"].value=slin;
	    oForm.elements["zzsd"].value=zzsd;
		oForm.elements["jbno"].value=jbno;
		oForm.elements["houu"].value=houu;
		oForm.elements["kuan"].value=kuan;
		oForm.elements["cang"].value=cang;
		oForm.elements["sczm"].value=sczm;
		oForm.elements["scfm"].value=scfm;
		oForm.elements["ying"].value=ying;
	}
	
	
	// 查看指示：附页、业务、生产备注
	function doViewSjzs() {
		var oForm = document.forms["DataForm"];
		var oRczpno = oForm.elements["rczpno"];// 入端卷号
		if(oRczpno == null || oRczpno.value == null || oRczpno.value.length == 0) {
			alert("无法获取入端卷号");
			return;
		}
		var ajax = new Cocoajax();
		ajax.dataDiv = ViewZsDiv;
		ajax.callback = function() {
			if(this.code < 0) {
				coco.alert(this.msg, "错误提示");
			} else {
				coco.showPage("SjzsPanel", {
					center : true,
					top : 80,
					width : "60%"
				});
			}
		};
		ajax.post("viewSjzs.do", "rczpno="+oRczpno.value);
	}
	//阅读附件
	function view(ylno) {
		var height = screen.availHeight;
	  	var width = screen.availWidth;
		window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
	}

	// 清空
	function doQk() {
		doCheck();
	}
	
	// 重录
	function doCl() {
		var oForm = document.forms["LeadForm"];
		var oZsno = oForm.elements["zsno"];// 指示书号
		var oRczpno = oForm.elements["rczpno"];// 入端卷号
		// 重录
		// 使输入框
		
		oZsno.disabled = false;
		oRczpno.disabled = false;
		oRczpno.focus();
		// 显示录入内容
		DataDiv.style.display = "none";
	}

	// 提交实绩录入
	function doSubmit(obj) {
		obj.disabled = true;
		var oForm = obj.form;
		 oForm.action = "savevalidate.do";
		 cocoform.submit(obj,function() {
			 if(this.code <= 0 && this.code != -8888 && this.code != -99) {
				 //coco.alert(this.msg);
				 var msg= new Array(); 
					msg=this.msg.split('&');
					alert(msg[0]);
					if(this.code==-98){
						//刷新页面
						doCheck();
					}else{
						exceptionPosition(this.code);	
						obj.disabled = false;
					}
					return ;
		        }
		        if(this.code == -8888 && !confirm("PILE为1,产量代码为1。是否继续登录?")) return;
				validateSuccess(obj,this.code,this.msg);});
	}
	//保存检查成功
	function validateSuccess(obj,code,msg) {
		if(code==-99){
			 var msgs= new Array(); 
			 msgs=msg.split('&');
			if(!window.confirm("超过"+msgs[0]+" ,确定要保存吗?")){
				obj.disabled = false;
				return;
			}
		}else{
			if(!window.confirm("确定要保存吗?")){
				obj.disabled = false;
				return;
			}
		}
		
		var oForm = obj.form;
		oForm.action = "save.do";   
	    cocoform.submit(obj,success);
	}
	// 实绩保存成功
	function success() {
		if(this.code <= 0) {
			coco.alert(this.msg);
			obj.disabled = false;
			return ;
		}
		alert("实绩保存成功");	
		var oForm = document.forms["DataForm"];
		//母卷终了
		var vZl = oForm.elements["zl"].value;
		//装入中止
		var vZz = oForm.elements["zz"].value;
		if(vZl == "1" || vZz == "1") {
			var leadForm = document.forms["LeadForm"];
			var oZsno = leadForm.elements["zsno"];// 指示书号
			var oRczpno = leadForm.elements["rczpno"];// 入端卷号
			oZsno.disabled = false;
			oRczpno.disabled = false;
			oRczpno.focus();
			// 显示录入内容
			DataDiv.style.display = "none";
			return;
		}
		 if(oForm.elements["jbno"].value!=""&&oForm.elements["chan"].value!="9"){
         	var jbno=oForm.elements["jbno"].value;
         	//打印制品卡
 			printZpk(jbno,'1');
         	
          } 	
		// 继续
		showLr();
	
	}
	//显示附着量,显示出端COIL No，制品尺寸
    function showLr(vZsno,vRczpno,vSlin){
    	var oForm = document.forms["LeadForm"];
		var oSlin = oForm.elements["slin"];// 生产线别
		var oZsno = oForm.elements["zsno"];// 指示书号
		var oRczpno = oForm.elements["rczpno"];// 入端卷号
		var vSlin = oSlin.value.replace(/(^\s*)|(\s*$)/g, "");// 生产线别
		var vZsno = oZsno.value.replace(/(^\s*)|(\s*$)/g, "");// 指示书号
		var vRczpno = oRczpno.value.replace(/(^\s*)|(\s*$)/g, "");// 入端卷号
    	var ajax = new Cocoajax();
		var postContent = "zsno=" + vZsno + "&rczpno=" + vRczpno + "&slin=" + vSlin;
		ajax.callback = function() {
			if (this.code < 0) {
				coco.alert(this.msg);
				doCl();
			} else {
				oZsno.disabled = true;
				oRczpno.disabled = true;
				// 显示录入内容
				DataDiv.style.display = "block";
				// 填充数据
				var dataoForm = document.forms["DataForm"];
				var chan=dataoForm.elements["chan"].value;
				var deng=dataoForm.elements["deng"].value;
				var zshu=dataoForm.elements["zshu"].value;
				var jdyn=dataoForm.elements["jdyn"].value;
				var jsyn=dataoForm.elements["jsyn"].value;
				var cqpl=dataoForm.elements["cqpl"].value;
				var lnsd=dataoForm.elements["lnsd"].value;
				var zkqr=dataoForm.elements["zkqr"].value;
				var jszl=dataoForm.elements["jszl"].value;
				var dmrk=dataoForm.elements["dmrk"].value;
				var ying=dataoForm.elements["ying"].value;
				cocoform.fillResult("DataForm", this.result);
				
				dataoForm.elements["chan"].value=chan;
				dataoForm.elements["deng"].value=deng;
				dataoForm.elements["zshu"].value=zshu;
				dataoForm.elements["jdyn"].value=jdyn;
				dataoForm.elements["jsyn"].value=jsyn;
				dataoForm.elements["cqpl"].value=cqpl;
				dataoForm.elements["lnsd"].value=lnsd;
				dataoForm.elements["zkqr"].value=zkqr;
				dataoForm.elements["jszl"].value=jszl;
				dataoForm.elements["dmrk"].value=dmrk;
				dataoForm.elements["ying"].value=ying;
			}
		};
		ajax.post("getcheck.do", postContent);
    }
	
	// 实绩保存失败
	function error() {
		alert("保存失败，请联系管理员");
	}
//-->
</script>
</head>
<body>
<ui:print id="lodop"/>
<ui:page title="SL生产实绩录入">
	<form name="LeadForm">
		<table width="96%" align="center" class="form">
			<colgroup>
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
				<col width="15%" />
				<col width="10%" />
				<col />
			</colgroup>
			<tr>
				<th>生产线</th>
				<td>
					<input type="hidden" name="slin" value="${vo.slin}" />
					<f:v ql="select name from Scxbpz where code=?" value="vo.slin"/>
				</td>
				<th>指示书 No.</th>
				<td><ui:input name="zsno" maxlength="6" value="vo.zsno" title="输入指示书号" /></td>
				<th>COIL No.</th>
				<td>
					<ui:input name="rczpno" maxlength="9" value="vo.rczpno" title="入端卷号（中间品）" onkeydown="if(this.readOnly)return;if (event == null || event.keyCode != 13)return; doCheck();" />
				</td>
			</tr>
		</table>
	</form>
	<div id="DataDiv" style="display: none;">
		<form name="DataForm" action="save.do" method="post" xu.ajax="" xu.r="error();" xu.s="success();">
			<input type="hidden" name="bdan" />
			<input type="hidden" name="zsno" />
			<input type="hidden" name="slin" />
			<input type="hidden" name="rczpno" />
			<table id="BzTbl" width="96%" align="center" class="form">
				<colgroup><col /><col width="5%" /><col width="5%" /><col width="5%" /><col width="10%" /></colgroup>
				<tr>
					<th>班</th>
					<td>
					<ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;" />
					</td>
					<th>组</th>
					<td>
					<ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"  prop="nn:1;"  />
					</td>
					<td><input type="button" class="button" value="查看别纸和业联(V)" accesskey="v" onclick="doViewSjzs();" /></td>
				</tr>
			</table>
			<fieldset class="group">
				<legend>母卷完成</legend>
				<table id="PileTbl" width="96%" align="center" class="form">
					<colgroup><col width="20%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="15%" /><col width="15%" /></colgroup>
					<tr>
						<th>母卷终了</th>
						<td><ui:int name="zl" maxlength="1" onfocus="false" prop="" onblur="enableCtroll(1)" /></td>
						<th>硬度</th>
						<td><ui:int name="ying" maxlength="3"  prop=""  /></td>
						<th>装入中止</th>
						<td><ui:int name="zz" maxlength="1" onblur="enableCtroll(2)" /></td>
						<th>卷厚</th>
						<td><ui:int name="jh" maxlength="6" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="group"><legend>制品信息</legend>
				<table width="96%" align="center" class="form">
					<colgroup><col width="10%" /><col width="15%" /><col width="15%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="20%" /></colgroup>
					<tr>
						<th>PILE No.</th>
						<td><ui:input name="zzsd" maxlength="1" readonly="true" title="制造商代码"/>-<ui:input name="jbno" maxlength="11" readonly="true" title="出端制品号"/></td>
						<th>制品尺寸</th>
						<td colspan="3">
							<ui:number name="houu" maxlength="5" readonly="true" title="制品尺寸•厚"/>*
							<ui:number name="kuan" maxlength="7" readonly="true" title="制品尺寸•宽"/>*
							<ui:number name="cang" maxlength="7" readonly="true" title="制品尺寸•长"/>
						</td>
						<th>锡附着量</th>
						<td><ui:input name="sczm" maxlength="4" readonly="true" />-<ui:input name="scfm" maxlength="4" readonly="true" /></td>
					</tr>
					<tr>
						<th>计算重量</th>
						<td><ui:int name="jszl" readonly="true" maxlength="5" /></td>
						<th>实际重量</th>
						<td><ui:int name="sjzl" maxlength="5" /></td>
						<th>修边毛边OP</th>
						<td><ui:input name="maop" maxlength="5" title="修边毛边OP" /></td>
						<th>修边毛边DR</th>
						<td><ui:input name="madr" maxlength="5" title="修边毛边DR" /></td>
					</tr>
					<tr>
						<th>产量代码</th>
						<td><ui:input name="chan" maxlength="1"  /></td>
						<th>等级</th>
						<td><ui:input name="deng" maxlength="3"  /></td>
						<th>处置</th>
						<td><ui:input name="czdm" maxlength="1" /></td>
						<th>主缺陷</th>
						<td><ui:input name="qqdm" maxlength="2" /></td>
					</tr>
					<tr>
						<th>缺陷2</th>
						<td><ui:input name="qqd2" maxlength="2" /></td>
						<th>缺陷3</th>
						<td><ui:input name="qqd3" maxlength="2" /></td>
						<th>检定员</th>
						<td><ui:input name="jdyn" maxlength="2"  /></td>
						<th>计数员</th>
						<td><ui:input name="jsyn" maxlength="2"  /></td>
					</tr>
					<tr>
						<th>枚数</th>
						<td><ui:int name="zshu" maxlength="4"  onblur="showJSZL();" /></td>
						<th>D(A)-MARK</th>
						<td><ui:input name="dmrk" maxlength="1" /></td>
						<th>PILER</th>
						<td><ui:input name="cqpl" maxlength="1" /></td>
						<th>特记</th>
						<td><ui:input name="vari" maxlength="10" /></td>
					</tr>
					<tr>
						<th>实测宽</th>
						<td><ui:number name="sckn" maxlength="6" onchange="checkKuanCang();"/></td>
						<th>实测剪断长</th>
						<td><ui:number name="jdcn" maxlength="6" onchange="checkKuanCang();" /></td>
						<th>LINE-SPEED</th>
						<td><ui:int name="lnsd" maxlength="3"  /></td>
						<th>长分布</th>
						<td>
							<ui:int name="cp15" maxlength="1" title="1.5" />&nbsp;
							<ui:int name="cp10" maxlength="1" title="1" /> &nbsp;
							<ui:int name="cp05" maxlength="1" title="0.5" /> &nbsp;
							<ui:int name="cp00" maxlength="1" title="0" /> &nbsp;
							<ui:int name="cm05" maxlength="1" title="-0.5" /> 
						</td>
					</tr>
					<tr>
						<th>耳波Op</th>
						<td><ui:number name="bopg" maxlength="2" /> - <ui:number name="bopj"
							maxlength="3" /></td>
						<th>耳波Dr</th>
						<td><ui:number name="bdrg" maxlength="2" /> - <ui:number name="bdrj"
							maxlength="3" /></td>
						<th>耳波等级</th>
						<td colspan="3"><ui:input name="bdji" maxlength="1" /></td>
					</tr>
					<tr>
						<th>直角Op</th>
						<td> <ui:number name="zopz"
							maxlength="4" /></td>
						<th>直角Dr</th>
						<td> <ui:number name="zdrz"
							maxlength="4" /></td>
						<th>L反</th>
						<td><ui:number name="zndz" maxlength="3" /></td>
						<th>C反</th>
						<td><ui:number name="hndz" maxlength="3" /></td>
					</tr>
					<tr>
						<th>中伸</th>
						<td><ui:number name="zbog" maxlength="2" /> - <ui:number name="zboj"
							maxlength="3" /></td>
						<th>翘度</th>
						<td><ui:number name="qduz" maxlength="3" /></td>
						<th>毛边上</th>
						<td><ui:input name="maos" maxlength="5" title="毛边上" /></td>
						<th>毛边下</th>
						<td><ui:input name="maox" maxlength="5" title="毛边下" /></td>
					</tr>
					<tr>
						<th>矢切量</th>
						<td><ui:input name="siql" maxlength="5" title="矢切量" /></td>
						<th>镜面检查</th>
						<td><ui:input name="jmjc" maxlength="1" title="镜面检查" /></td>
						<th>垫足确认</th>
						<td><ui:input name="dmqr" maxlength="1" title="垫足确认" /></td>
						<th>针孔确认</th>
						<td><ui:input name="zkqr" maxlength="1" title="针孔确认"  /></td>
					</tr>
				</table>
			</fieldset>
			<div id="SubmitTbl" style="width: 96%; text-align: right; margin-top: 15px;">
				<input type="button" class="button" value="实绩录入确认(S)" accesskey="s" onclick="doSubmit(this);" />
					&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空(C)" accesskey="c" onclick="doFormReset();" />
				<input type="button" class="button" value="返回(B)" accesskey="b" onclick="doCl();" />
			</div>
		</form>
	</div>
	<div id="ViewZsDiv"></div>
	<br />
</ui:page>
<ui:panel id="View" title="查看生产指示说明" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
</body>
</html>