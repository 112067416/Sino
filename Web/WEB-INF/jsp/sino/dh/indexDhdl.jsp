<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui" %><%@ 
taglib uri="/sys" prefix="sys" %><%@
taglib prefix="sino" uri="/sino" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
            //提交操作
			function doSubmit(oForm, oInput) {
				var postContent = cocoform.postContent(oForm);
				postContent = postContent + "&flag=true";
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						var el = oForm.elements[Math.abs(this.code)];
						if(el != null) el.focus(); 
                     	return;
					}
					cocoform.fillResult(oForm, this.result, null, true);
					SyDiv.style.display = "block";
					var mgsn = oForm.elements["mgsn"].value;
					var bz1 = oForm.elements["bz1"].value;  
					var bz2 = oForm.elements["bz2"].value;  
					var bz3 = oForm.elements["bz3"].value;  
					document.getElementById("mgsnSpan").innerHTML = mgsn;
					document.getElementById("bz1Span").innerHTML = bz1;
					document.getElementById("bz2Span").innerHTML = bz2;
					document.getElementById("bz3Span").innerHTML = bz3;

					if(!confirm("是否确定保存?")) return;
					cocoform.submit(oForm, function() {
						if(this.code <= 0) {
							alert(this.msg.replace(/^\w+/g,''));
							var name = this.msg.substring(0, 4);
							var el = oForm.elements[name];
							if(el == null) return; 
							oForm.elements[name].focus();
	                     	return;
						}
						ContDiv.style.display = "block";
						DivContent.style.display = "none";
						SyDiv.style.display = "none";
						BtDiv.style.display = "none";
						var el1 = oForm.elements["dhno"];
						var el2 = oForm.elements["line"];
						var el3 = oForm.elements["ddno"];
						el1.className = "normal";
						el1.readOnly = false;
						el2.className = "normal";
						el2.readOnly = false;
						el3.className = "normal";
						el3.readOnly = false;
						alert("保存成功");
						el1.value = "";
						el2.value = "";
						el2.focus();
					},null,null,oInput);
				};
				ajax.post("loadZzsy.do", postContent);
			}
			//确定操作即取仕样信息
			function loadZzsy() {
				var oForm = document.forms["DataForm"];
				var postContent = cocoform.postContent(oForm);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if (this.code <= 0) {
						alert(this.msg);
						var el = oForm.elements[Math.abs(this.code)];
						if(el != null) el.focus(); 
                     	return;
					}
					cocoform.fillResult(oForm, this.result, null, true);
					SyDiv.style.display = "block";
					var mgsn = oForm.elements["mgsn"].value;
					var bz1 = oForm.elements["bz1"].value;  
					var bz2 = oForm.elements["bz2"].value;  
					var bz3 = oForm.elements["bz3"].value;  
					document.getElementById("mgsnSpan").innerHTML = mgsn;
					document.getElementById("bz1Span").innerHTML = bz1;
					document.getElementById("bz2Span").innerHTML = bz2;
					document.getElementById("bz3Span").innerHTML = bz3;
					alert("加载仕样信息完成");
				};
				ajax.post("loadZzsy.do", postContent);
			}
			//（验证订货no及行号）
			function checkDhno() {
				var form = document.forms["DataForm"];
				var el1 = form.elements["dhno"];
				var el2 = form.elements["line"];
				var el3 = form.elements["ddno"];
				var addr = form.elements["addr"];
				var postContent = coco.parseParams([{name:"dhno",value:el1.value},{name:"line",value:el2.value},{name:"ddno",value:el3.value}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						DivContent.style.display = "none";
						return;
					}
					var ignores = ["ddno", "dhno", "line"];
					cocoform.clear(form, ignores);
					if(this.code == 2 && !confirm("担当者与系统当前操作人不一致,是否确定登录?")) {
						return;
					}
					var obj = cocoform.fillResult(form, this.result, null, true);
					BtDiv.style.display = "block";
					DivContent.style.display = "block";
					ContDiv.style.display = "none";
					el1.className = "form-readonly";
					el1.readOnly = true;
					el2.className = "form-readonly";
					el2.readOnly = true;
					el3.className = "form-readonly";
					el3.readOnly = true;
					form.elements["ggno"].focus();
					var shdzs = obj.shdz;
					var $addr = obj.addr;
					var vs = shdzs.split(",");
					var opt;
					if(vs.length > 0) {
						for(var v in vs) {
							opt = document.createElement("OPTION");
							addr.options.add(opt);
							if($addr != null && $addr == vs[v]) {
								opt.selected = true;
							}
							opt.innerText = vs[v];
							opt.value = vs[v];
						}
					}
				};
				ajax.post("checkDhno.do", postContent);
				
			}
			//捆包计算
			function doKbjs(){
				var form = document.forms["DataForm"];
				var kbjs = form.elements["kbjs"].value;
				var pzno = form.elements["pzno"].value;
				if(kbjs == null || kbjs.length == 0 || pzno == null || pzno.length == 0) {
					return;
				}
				var kuan = form.elements["kuan"].value;
				var cang = form.elements["cang"].value;
				var postContent = coco.parseParams([{name:"kbjs",value:kbjs},{name:"kuan",value:kuan},{name:"cang",value:cang},{name:"pzno",value:pzno}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(pzno.substring(0,1) == 1) {
						form.elements["kbzq"].value = "";
						form.elements["kbzd"].value = "";
						form.elements["kbzx"].value = "";
						form.elements["kbzs"].value = "";
						form.elements["rjie"].value = "";
						form.elements["neij"].value = "";
					} else {
						//form.elements["kbzx"].value = "";
						form.elements["kbsq"].value = "";
						form.elements["kbsz"].value = "";
						form.elements["yyan"].value = "";
						form.elements["dmfx"].value = "";
						form.elements["cang"].value = "";
					}
					if(this.code <= 0) {
						obj.value = "";
						return ;
					}
					form.elements["kbao"].value = this.msg;
				};
				ajax.post("kbjs.do", postContent);
			}
			//根据尺寸.长和尺寸.宽，自动计算压延方向
			function getYyan() {
				var form = document.forms["DataForm"];
				var kuan = form.elements["kuan"].value;
				var cang = form.elements["cang"].value;
				if(kuan == null || kuan.length == 0 || cang == null || cang.length == 0) {
					return;
				}
				var postContent = coco.parseParams([{name:"kuan",value:kuan},{name:"cang",value:cang}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						form.elements["yyan"].value = "";
						return ;
					}
					form.elements["yyan"].value = this.msg;
				};
				ajax.post("getYyan.do", postContent);
			}

			//返回操作
			function doReturn() {
				if(SyDiv.style.display == 'block') {
					SyDiv.style.display = 'none';
				} else {
					DivContent.style.display = 'none';
					SyDiv.style.display = 'none';
					BtDiv.style.display = 'none';
					var form = document.forms["DataForm"];
					var el1 = form.elements["dhno"];
					var el2 = form.elements["line"];
					var el3 = form.elements["ddno"];
					el1.className = "normal";
					el1.readOnly = false;
					el2.className = "normal";
					el2.readOnly = false;
					el3.className = "normal";
					el3.readOnly = false;
					el1.focus();
				}
			}
			
			function doLoad() {
				var form = document.forms["DataForm"];
				form.elements["dhno"].focus();
			}
			//连续获得订货No.
			function doContinue() {
				var form = document.forms["DataForm"];
				var el1 = form.elements["dhno"];
				var el2 = form.elements["line"];
				ContDiv.style.display = "none";
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						return;
					}
					cocoform.fillResult(form, this.msg, null, true);
					el2.focus();
				};
				ajax.post("getDhno.do");
			}
			//获得用途代码名称
			function getCdnm(el) {
				var cond = el.value;
				if(cond == null || cond.length == 0) return;
				var form = el.form;
				var cdnm = form.elements["cdnm"];
				var code = coco.getCode("SINO_COND_CDNM", cond);
				if(code == null) return;
				cdnm.value = code.value;
			}

			//根据送货地址代码，获得对应的送货地址
			function getYong() {
				var form = document.forms["DataForm"];
				var user = form.elements["user"];
				var abbr = form.elements["abbr"];
				abbr.value = "";
				var name = form.elements["name"];
				name.value = "";
				var ssno = form.elements["ssno"];
				ssno.value = "";
				var ssnm = form.elements["ssnm"];
				ssnm.value = "";
				var addr = form.elements["addr"];
				var length = addr.options.length;
				for(var i = 0; i < length - 1; i++) {
					addr.options.remove(1);
				}
				if(user.value == null || user.value.length == 0) return;
				var postContent = coco.parseParams([{name:"user", value:user.value}]);
				var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						alert(this.msg);
						user.value = "";
						user.focus();
						return;
					}
					var obj;
					eval("obj = " + this.msg + ";");
					abbr.value = obj.abbr;
					name.value = obj.name;
					ssno.value = obj.ssno != null && obj.ssno.length > 0 ? obj.ssno : "";
					ssnm.value = obj.ssnm != null && obj.ssnm.length > 0 ? obj.ssnm : "";
					var shdzs = obj.shdz;
					var vs = shdzs.split(",");
					var opt;
					if(vs.length > 0) {
						for(var v in vs) {
							opt = document.createElement("OPTION");
							addr.options.add(opt);
							opt.innerText = vs[v];
							opt.value = vs[v];
						}
						addr.options[1].selected = true;
					}
				};
				ajax.post("getYong.do", postContent);
			}
			//根据捆包下限，计算捆包上限
			function setKbzx(obj) {
				var v = obj.value;
				if(v == null || v.length == 0) {
					return;
				}
				v = parseFloat(v);
				var oForm = obj.form;
				oForm.elements["kbzx"].value = (v / 4).toFixed(3);
			}
		//-->
		</SCRIPT>
	</head>
	<body onload="doLoad();">
		<ui:page title="订货登录">
		<form name="DataForm" xu.ajax="" xu.s="success();" method="post" action="save.do" >
		<input type="hidden" name="syno" />
		<table width="96%" class="form">
		<colgroup>
			<col width="10%" />
			<col width="25%" />
			<col width="10%" />
			<col width="25%" />
			<col width="10%" />
			<col width="25%" />
		</colgroup>
		<tr>
			<th>担当</th>
			<td style="font-size: 14px;"><input type="hidden" name="ddnm" /><ui:input name="ddno" title="订货DB的担当者代码" maxlength="3" onkeydown="if(window.event.keyCode==13) checkDhno(); " /></td>
			<th>订货NO.</th>
			<td><ui:input name="dhno" title="订货DB的订货NO" maxlength="7" onkeydown="if(window.event.keyCode==13) checkDhno(); "/>-<ui:input name="line" title="行号" maxlength="2" onkeydown="if(window.event.keyCode==13) checkDhno(); "/></td>
			<td colspan="3" style="text-align: center;"><div id="ContDiv" style="height: 20px; width: 10px; display: none;"><input type="button" value="继续登录" class="button" onclick="doContinue();" /></div></td>
		</tr>
		</table>
		<div id="DivContent" style="display: none;">
			<hr style="border: 1px dashed #338899;">
			<table width="96%" class="form">
				<colgroup><col width="15%" /><col width="10%" /><col width="15%" /><col width="15%" /><col width="10%" /><col width="10%" /><col width="25%" /></colgroup>
				<tr>
					<th style="text-align: left;">规格&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="ggno" title="订货DB的规格代码" maxlength="4"/></th>
					<th style="text-align: left;">品种&nbsp;&nbsp;&nbsp;&nbsp;<ui:int name="pzno" title="订货DB的品种代码" maxlength="2" onchange="doKbjs();"/></th>
					<th style="text-align: left;">附着量<span style="font-size: 12px;">(单位.正面.反面)</span></th>
					<td>
						<ui:input name="fudw" title="订货DB的附着量单位（GM 或  WB）" maxlength="2" />
						&nbsp;<ui:number name="fuzm" title="订货DB的附着量正面" maxlength="3" />
						&nbsp;<ui:number name="fufm" title="订货DB的附着量反面" maxlength="3" />
					</td>
					<th style="text-align: left;">差厚&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="chdx" title="订货DB的差厚镀锡（D1 或  D2  或 A1 或 A2）。当附着量的正面和反面的值不一致时，必须输入。" maxlength="2" /></th>
					<th style="text-align: left;">内外&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="nwai" title="内外" maxlength="1" /></th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th style="text-align: left;">尺寸<span style="font-size: 12px;">(厚*宽*剪切板长)</span></th>
					<td colspan="2">
						 <ui:number name="houu" title="订货DB的订货尺寸.厚" scale="4" precision="3" positive="true"/>
						*
						<ui:number name="kuan" title="订货DB的订货尺寸.宽" scale="6" precision="2" positive="true" maxlength="7" onchange="doKbjs();getYyan();"/>
						*<ui:number name="cang" title="订货DB的订货尺寸.长" scale="6" precision="2" positive="true" onchange="doKbjs();getYyan();"/>
					</td>
					<th colspan="4" style="text-align: left;">用途&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="cond" title="订货DB的加工用途条件" maxlength="4" onblur="getCdnm(this);" />&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="cdnm" title="订货DB的加工用途名称" maxlength="8" required="true" />&nbsp;&nbsp;&nbsp;&nbsp;用户代码&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="user" title="用户代码" maxlength="4" required="true" onblur="getYong();" />&nbsp;&nbsp;<ui:input name="abbr" title="用户略称" readonly="true" maxlength="16"/><input type="hidden" name="name" ></th>
				</tr>
			</table>
			<hr style="border: 1px dashed #338899;">
			<table width="100%" class="form">
				<colgroup><col width="9%" /><col width="17%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="8%" /><col width="8%" /><col width="7%" /><col width="6%" /><col width="6%" /><col width="12%" /></colgroup>
				<tr>
				<th colspan="11" style="text-align: left;">交货地点&nbsp;&nbsp;&nbsp;<ui:select name="addr" list="" prop="nn:1;" headerText="请选择" headerValue=""/>&nbsp;&nbsp;<input name="shnm" type="hidden" title="送货地址" /></th>
				</tr>
				<tr>
				<th colspan="5" style="text-align: left;">商社&nbsp;&nbsp;&nbsp;<ui:input name="ssno" title="商社" maxlength="4" readonly="true" />&nbsp;&nbsp;<ui:input name="ssnm" title="商社名称" readonly="true" maxlength="28" /></th>
				<th colspan="2" style="text-align: left;">交货日期&nbsp;&nbsp;&nbsp;<ui:date name="jhqi" showCalendar="false" /></th> 
				<th colspan="4" style="text-align: left;">&nbsp;</th>
				</tr>
				<tr>
				<th style="text-align: left;">重内&nbsp;&nbsp;&nbsp;<ui:int name="jhnr" title="订货DB的交货重量内容（1 、 2 、3 、6 、 7 或  8）" maxlength="1" /></th>
				<th style="text-align: left;">合同重量&nbsp;&nbsp;&nbsp;<ui:number name="htzl" title="订货DB的合同重量" scale="7" precision="3" positive="true" maxlength="8"/></th>
				<th colspan="3" style="text-align: left;">交货允许&nbsp;&nbsp;&nbsp;<ui:input name="jhqf" title="订货DB的交货允许.区分（W或T）" maxlength="1" />&nbsp;&nbsp;<ui:int name="jhfz" title="交货允许.-值（货允许.-值小于交货允许.+ 值）" maxlength="3" />-&nbsp;&nbsp;<ui:int name="jhzz" title="交货允许.+值" maxlength="3" /></th>
				<th colspan="2" style="text-align: left;">单包张数&nbsp;&nbsp;&nbsp;<ui:input name="kbsq" title="订货DB的捆包指定张数.区分" maxlength="1" />&nbsp;<ui:int name="kbsz" title="捆包指定张数.值" maxlength="4" /></th>
				<th colspan="3" style="text-align: left;">卷重&nbsp;&nbsp;&nbsp;<ui:input name="kbzq" title="订货DB的捆包指定重量.区分(5、6或8)" maxlength="1"/>&nbsp;<ui:input name="kbzd" title="捆包指定重量.单位（T）" maxlength="1" />&nbsp;<ui:number name="kbzx" title="捆包指定重量.下限" maxlength="6" scale="5" precision="3" />&nbsp;<ui:number name="kbzs" title="捆包指定重量.上限" maxlength="6" scale="5" precision="3" positive="true" onchange="setKbzx(this);" /></th>
				<th style="text-align: left;">捆包计算&nbsp;&nbsp;&nbsp;<ui:input name="kbjs" title="1：捆包形式由系统计算；其余：捆包形式非系统计算" maxlength="1" onblur="doKbjs();"/></th>
				</tr>
				<tr>
				<th style="text-align: left;">化成&nbsp;&nbsp;&nbsp;<ui:input name="huac" title="订货DB的化成处理(空或311或300)" maxlength="3"/></th>
				<th style="text-align: left;">捆包形式&nbsp;&nbsp;&nbsp;<ui:input name="kbao" title="系统根据制造仕样和订货信息进行计算的计算结果" maxlength="3" onchange="doKbjs();" /></th>
				<th style="text-align: left;">表面&nbsp;&nbsp;&nbsp;<ui:input name="face" title="订货DB的表面精加工" maxlength="2" /></th>
				<th style="text-align: left;">压延&nbsp;&nbsp;&nbsp;<ui:input name="yyan" title="制造仕样主文件的压延方向指定标记（空或1或2）" maxlength="1" /></th>
				<th style="text-align: left;">垫木&nbsp;&nbsp;&nbsp;<ui:input name="dmfx" title="造仕样主文件的垫木方向（空或L或C）" maxlength="1"/></th>
				<th style="text-align: left;">外W&nbsp;&nbsp;&nbsp;<ui:input name="rjie" title="订货DB的销售溶接部不可(0或1或2)" maxlength="1" /></th>
				<th style="text-align: left;">内径&nbsp;&nbsp;&nbsp;<ui:input name="neij" title="订货DB的内径代码（419或508）" maxlength="3" /></th>
				<th style="text-align: left;">货标&nbsp;&nbsp;&nbsp;<ui:input name="hbbj" title="订货DB的货标标记（空或1）" maxlength="1" /></th>
				<th style="text-align: left;">B&nbsp;&nbsp;&nbsp;<ui:input name="tbno" title="订货DB的特殊BNO（空或1）" maxlength="1" value="dhuoTp.tbno" /></th>
				<th style="text-align: left;">C&nbsp;&nbsp;&nbsp;<ui:input name="tcno" title="订货DB的特殊CNO（空或1）" maxlength="1" /></th>
				<th style="text-align: left;">需L&nbsp;&nbsp;&nbsp;<ui:input name="yhbq" title="订货DB的用户标签名表示（空或1）" maxlength="1" onkeydown="if(window.event.keyCode == 13) loadZzsy();" /></th>
				</tr>
			</table>
			<hr style="border: 1px dashed #338899;">
		</div>
		<div id="SyDiv" style="display: none;" >
		<table width="100%" class="form">
			<colgroup>
			<col width="6%" /><col width="3%" /><col width="6%" /><col width="11%" />
			<col width="8%" /><col width="5%" /><col width="8%" /><col width="5%" />
			<col width="6%" /><col width="5%" /><col width="6%" /><col width="5%" />
			<col width="8%" /><col width="5%" /><col width="8%" /><col width="5%" />
			</colgroup>
			<tr>
			<th style="text-align: left;">剪边</th>
			<td><ui:input name="jian" maxlength="1" readonly="true" /> </td>
			<th style="text-align: left;">零头下限</th>
			<td><ui:input name="ltdw" title="单位" maxlength="1" readonly="true" />&nbsp;&nbsp;<ui:int name="ltzi" title="值" maxlength="4" readonly="true" /> </td>
			<th style="text-align: left;">涂油种类</th>
			<td><ui:input name="ytyp" maxlength="1" readonly="true" title="涂油种类" /></td>
			<th style="text-align: left;">KPLATE</th>
			<td><ui:input name="plat" maxlength="1" readonly="true" title="KPLATE" /></td>
			<th style="text-align: left;">涂油量</th>
			<td><ui:input name="yqty" maxlength="2" title="涂油量" readonly="true" /></td>
			<th style="text-align: left;">合金层</th>
			<td><ui:input name="hjin" maxlength="3" title="合金层" readonly="true" /></td>
			<th style="text-align: left;">分配等级</th>
			<td><ui:input name="fpdj" maxlength="3" readonly="true" title="分配等级" /></td>
			<th style="text-align: left;">直角度</th>
			<td><ui:number name="jiao" scale="2" precision="1" readonly="true" title="直角度" maxlength="3"/></td>
			</tr>
			<tr>
			<th style="text-align: left;">保护标记</th>
			<td><ui:input name="prot" maxlength="1" readonly="true" title="保护标记" /></td>
			<th colspan="14" style="text-align: left;">硬度上下限&nbsp;&nbsp;<ui:input name="ymin" maxlength="2" readonly="true" title="硬度下限" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="ymax" maxlength="2" readonly="true" title="硬度上限" />&nbsp;&nbsp;&nbsp;&nbsp;附着量.正上下限&nbsp;&nbsp;<ui:number name="fuzx" scale="4" precision="2" readonly="true" title="附着面.正面.下限值" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fuzs" scale="4" precision="2" readonly="true" title="附着面.正面.上限值" />&nbsp;&nbsp;&nbsp;&nbsp;附着量.反上下限&nbsp;&nbsp;<ui:number name="fufx" scale="4" precision="2" readonly="true" title="附着面.反面.下限值" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fufs" scale="4" precision="2" readonly="true" title="附着面.反面.上限值" /></th>
			</tr>
			<tr>
			<th style="text-align: left;">锯齿形式</th>
			<td><ui:input name="jcxs" maxlength="4" readonly="true" title="锯齿形式" /></td>
			<th colspan="14" style="text-align: left;">公差厚(%)&nbsp;&nbsp;<ui:number name="hxzi" scale="3" precision="1" readonly="true" title="尺寸允许范围.厚%下限.值" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="hszi" scale="3" precision="1" readonly="true" title="尺寸允许范围.厚%上限.值" />&nbsp;&nbsp;&nbsp;&nbsp;公差宽(mm)&nbsp;&nbsp;<ui:number name="kxzi" scale="3" precision="2" readonly="true" title="尺寸允许范围.宽mm下限.值" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="kszi" scale="3" precision="2" readonly="true" title="尺寸允许范围.宽mm上限.值" />&nbsp;&nbsp;&nbsp;&nbsp;公差长(mm)&nbsp;&nbsp;<ui:number name="cxzi" scale="3" precision="2" readonly="true" title="尺寸允许范围.长mm下限.值" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="cszi" scale="3" precision="2" readonly="true" title="尺寸允许范围.长mm上限.值" /></th>
			</tr>
			<tr>
			<th colspan="6" style="text-align: left;">装入宽&nbsp;&nbsp;<ui:number name="zrkn" scale="6" precision="2" readonly="true" title="装入宽" />&nbsp;&nbsp;内径保护筒&nbsp;&nbsp;<ui:input name="njbh" maxlength="1" readonly="true" title="内径保护筒" />&nbsp;&nbsp;运用规格&nbsp;&nbsp;<ui:input name="yuny" maxlength="6" readonly="true" title="运用规格" /></th>
			<th colspan="10" style="text-align: left;">附页KPNo&nbsp;&nbsp;<ui:input name="kpn1Flag" maxlength="1" title="标识位.1：ETL;2：SL;3;两者" readonly="true" />-<ui:input name="kpn1" maxlength="9" title="附页KPNO.1" readonly="true" />&nbsp;&nbsp;<ui:input name="kpn2Flag" maxlength="1" title="标识位.2：ETL;2：SL;3;两者" readonly="true" />-<ui:input name="kpn2" maxlength="9" title="附页KPNO.2" readonly="true" />&nbsp;&nbsp;<ui:input name="kpn3Flag" maxlength="1" title="标识位.3：ETL;2：SL;3;两者" readonly="true" />-<ui:input name="kpn3" maxlength="9" title="附页KPNO.3" readonly="true" />&nbsp;&nbsp;<ui:input name="kpn4Flag" maxlength="1" title="标识位.4：ETL;2：SL;3;两者" readonly="true" />-<ui:input name="kpn4" maxlength="9" title="附页KPNO.4" readonly="true" /></th>
			</tr>
			<tr>
				<th style="text-align: left;">木工所No</th>
				<td colspan="15"><input type="hidden" name="mgsn" value=""/> <span id="mgsnSpan" title="木工所No（如果有多个木工所no时，要用半角英文输入法的“,”间开）" class="form-text"></span></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注1</th>
				<td colspan="15"><input type="hidden" name="bz1" value=""/> <span id="bz1Span" title="ETL和木工所指示书的备注内容" class="form-text"></span></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注2</th>
				<td colspan="15"><input type="hidden" name="bz2" value=""/> <span id="bz2Span" title="SL指示书的备注内容" class="form-text"></span></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注3</th>
				<td colspan="15"><input type="hidden" name="bz3" value=""/> <span id="bz3Span" title="ETL、木工所和SL指示书的备注内容" class="form-text"></span><input type="hidden" name="jcts" /></td>
			</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		</div>
		<div id="BtDiv" style="display: none;" align="center">
		    <input type="button" value="加载仕样信息" onclick="loadZzsy()" class="button" />&nbsp;&nbsp;
			<input type="button" value="保 存" onclick="doSubmit(this.form, this);" class="button" />&nbsp;&nbsp;
			<input type="button"" value="返 回" class="button" onclick="doReturn();" />
		</div>
		</form>
		</ui:page>
	</body>
</html>
