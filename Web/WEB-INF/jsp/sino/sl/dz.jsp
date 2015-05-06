<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/sllrcompute.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/print.js"></script>
		<script type="text/javascript">
		<!--
		// 订正检查
		function dzCheck() {
			//var oForm = document.forms["HeadForm"];
			var obj = document.getElementById("pileno");
			var pileno = obj.value;
			if(pileno == null || pileno.length == 0) return;
			var postContent = coco.parseParam("jbno", pileno);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(ajax.code > 0) {
					// 显示内容
					DataArea.style.display = "block";
					// 使输入不可用
					obj.disabled = true;
					// 填充数据
					cocoform.fillResult("DataForm", this.result);
				} else {
					coco.alert(this.msg);
				}
			};
			//ajax.post("dzCheck.do", "jbno=" + pileno);
			ajax.post("dzCheck.do", postContent, "POST");
		}
		
		//修改操作
		function doSubmit(obj) {
			obj.disabled = true;
			var oForm =  obj.form;
			if(oForm.elements["jmjc"].value==""){
				alert("镜面检查必须为\"合格\"");
				obj.disabled = false;
				return;
			}
			if(oForm.elements["dmqr"].value==""){
				alert("垫足确认必须\"确认\"");
				obj.disabled = false;
				return;
			}
			if(oForm.elements["zkqr"].value==""){
				alert("针孔确认必须为\"合格\"");
				obj.disabled = false;
				return;
			}
			 oForm.action = "dzvalidate.do";
			 cocoform.submit(obj,function() {
				 if(this.code <= 0) {			
					 var msg= new Array(); 
						msg=this.msg.split('&');
						alert(msg[0]);
						exceptionPosition(this.code);				
						return ;
			        }
				 validateSuccess(obj);});
		}
		//保存检查成功
		function validateSuccess(obj) {
			if(!window.confirm("确定修改吗?")){
				obj.disabled = false;
				return;
			}
			var oForm = obj.form;
			oForm.action = "update.do";   
		    cocoform.submit(obj,success);
		}
		// 更新成功
		function success() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return ;
			}
			alert("修改成功");
			var oForm = document.forms["DataForm"];
			if(oForm.elements["jbno"].value!=""&&oForm.elements["chan"].value!="9"){
	         	var jbno=oForm.elements["jbno"].value;
	         	//打印制品卡
	 			printZpk(jbno,'1');	
	          } 	
			
		}
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
		
		//--><c:if test="${pileno!=null}">onload="doCreate()"</c:if>
		</script>
	</head>
	<body <c:if test="${pileno!=null}">onload="dzCheck()"</c:if>>
	<ui:print id="lodop"/>		
	<ui:page title="SL生产线实绩订正">
		<table width="96%" align="center" class="form">
			<colgroup><col width="15%" /><col width="20%" /><col width="15%" /><col width="50%" /></colgroup>
			<tr>
				<th>生产线</th>
				<td>${scxName }</td>
				<th>PILE No.</th>
				<td><ui:input id="pileno" name="pileno" value="pileno" maxlength="11" title="输入实绩后的制品号" onkeydown="if(event.keyCode == 13) dzCheck();" /></td>
			</tr>
		</table>
		<div id=DataArea style="display: none;">
		<form name="DataForm" action="update.do" method="post" xu.s="success();" xu.color="1">
			<input type="hidden" name="bdan" />
			<input type="hidden" name="rczpno" />
			<input type="hidden" name="scbj" />
			<input type="hidden" name="qdbj" />
			<input type="hidden" name="zsno" />
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
					<td><input type="button" class="button" value="查看指示(V)" accesskey="v" onclick="doViewSjzs();" /></td>
				</tr>
			</table>
			<fieldset class="group">
				<legend>制品信息</legend>
				<table width="96%" align="center" class="form" height="40px">
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
						<td><ui:int name="sjzl" maxlength="5"  /></td>
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
						<td><ui:number name="sckn" maxlength="6"  onchange="checkKuanCang();"/></td>
						<th>实测剪断长</th>
						<td><ui:number name="jdcn" maxlength="6"  onchange="checkKuanCang();"/></td>
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
						<td><ui:number name="zopz"
							maxlength="4" /></td>
						<th>直角Dr</th>
						<td><ui:number name="zdrz"
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
						<td><ui:select name="jmjc" list="'':'不合格','1':'合格'"  /></td>
						<th>垫足确认</th>
						<td><ui:select name="dmqr" list="'':'未确认','1':'已确认'"  /></td>
						<th>针孔确认</th>
						<td><ui:select name="zkqr" list="'':'不合格','1':'合格'"  /></td>
					</tr>
				</table>
			</fieldset>
			<div class="submit"><input type="button" class="button" value="实绩修改(S)" accesskey="s" onclick="doSubmit(this);" /></div>
			<div id="ViewZsDiv"></div>
		</form>
		</div>
	</ui:page>
	</body>
</html>