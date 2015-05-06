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
			function doSubmit() {
				var oForm = document.forms["DataForm"];
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
							alert(this.msg);
	                     	return;
						}
						alert("保存成功");
						parent.cocoframe.close(parent.cocoframe.pageCurr);
					});
				};
				ajax.post("loadZzsy.do", postContent);
			}
			//确定操作即取仕样信息
			function loadZzsy() {
				if(!confirm("是否确定加载仕样信息?")) return;
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
						//form.elements["kbzx"].value = 2;
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
	<body>
		<ui:page title="编辑订货合同信息">
		<form name="DataForm" xu.ajax="" xu.s="success();" xu.color="1" method="post" action="update.do" >
		<input type="hidden" name="syno" value="" />
		<table width="96%" class="form">
		<colgroup>
			<col width="10%" />
			<col width="25%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="25%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th>担当</th>
			<td style="font-size: 14px;"><input type="hidden" name="ddnm" /><ui:input name="ddno" title="订货DB的担当者名称" maxlength="3" value="dhuoTp.ddno"/></td>
			<th>订货NO.</th>
			<td><ui:input name="dhno" title="订货DB的订货NO" maxlength="7" value="dhuoTp.dhno" />-<ui:input name="line" title="行号" maxlength="2" value="dhuoTp.line"/></td>
			<td colspan="3"><input type="hidden" name="ydhno" value="${dhuoTp.dhno }${dhuoTp.line }" /></td>
		</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<table width="96%" class="form">
			<colgroup><col width="15%" /><col width="10%" /><col width="15%" /><col width="15%" /><col width="10%" /><col width="10%" /><col width="25%" /></colgroup>
			<tr>
				<th style="text-align: left;">规格&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="ggno" title="订货DB的规格代码" maxlength="4" value="dhuoTp.ggno"/></th>
				<th style="text-align: left;">品种&nbsp;&nbsp;&nbsp;&nbsp;<ui:int name="pzno" title="订货DB的品种代码" maxlength="2" onchange="doKbjs();" value="dhuoTp.pzno"/></th>
				<th style="text-align: left;">附着量<span style="font-size: 12px;">(单位.正面.反面)</span></th>
				<td>
					<ui:input name="fudw" title="订货DB的附着量单位（GM 或  WB）" maxlength="2" value="dhuoTp.fudw"/>
					&nbsp;<ui:number name="fuzm" title="订货DB的附着量正面" maxlength="3" value="dhuoTp.fuzm"/>
					&nbsp;<ui:number name="fufm" title="订货DB的附着量反面" maxlength="3" value="dhuoTp.fufm"/>
				</td>
				<th style="text-align: left;">差厚&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="chdx" title="订货DB的差厚镀锡（D1 或  D2  或 A1 或 A2）。当附着量的正面和反面的值不一致时，必须输入。" maxlength="2" value="dhuoTp.chdx"/></th>
				<th style="text-align: left;">内外&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="nwai" title="内外" maxlength="1" value="dhuoTp.nwai" /></th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th style="text-align: left;">尺寸<span style="font-size: 12px;">(厚*宽*剪切板长)</span></th>
				<td colspan="2">
					 <ui:number name="houu" title="订货DB的订货尺寸.厚" scale="4" precision="3" positive="true" value="dhuoTp.houu"/>
					*
					<ui:number name="kuan" title="订货DB的订货尺寸.宽" scale="6" precision="2" positive="true" maxlength="7" value="dhuoTp.kuan" onchange="doKbjs();getYyan();"/>
					*<ui:number name="cang" title="订货DB的订货尺寸.长" scale="6" precision="2" positive="true" value="dhuoTp.cang" onchange="doKbjs();getYyan();"/>
				</td>
				<th colspan="4" style="text-align: left;">用途&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="cond" title="订货DB的加工用途条件" maxlength="4" onblur="getCdnm(this);" value="dhuoTp.cond" />&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="cdnm" title="订货DB的加工用途名称" maxlength="8" required="true" value="dhuoTp.cdnm" />&nbsp;&nbsp;&nbsp;&nbsp;用户代码&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="user" title="用户代码" maxlength="4" required="true" onblur="getYong();" value="dhuoTp.user"  />&nbsp;&nbsp;<ui:input name="abbr" title="用户略称" readonly="true" maxlength="16" value="dhuoTp.abbr" /><input type="hidden" name="name" value="${dhuoTp.name }" ></th>
			</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<table width="100%" class="form">
			<colgroup><col width="9%" /><col width="17%" /><col width="9%" /><col width="9%" /><col width="9%" /><col width="8%" /><col width="8%" /><col width="7%" /><col width="6%" /><col width="6%" /><col width="12%" /></colgroup>
			<tr>
			<th colspan="11" style="text-align: left;">交货地点&nbsp;&nbsp;&nbsp;&nbsp;<ui:select name="addr" list="${shdz }" prop="nn:1;" headerText="请选择" headerValue="" value="dhuoTp.addr" />&nbsp;&nbsp;<input name="shnm" type="hidden" title="送货地址" /></th>
			</tr>
			<tr>
			<th colspan="5" style="text-align: left;">商社&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="ssno" title="商社" maxlength="4" readonly="true" value="dhuoTp.ssno"/> &nbsp;&nbsp;<ui:input name="ssnm" title="商社名称" readonly="true" maxlength="28" value="dhuoTp.ssnm"/></th>
			<th colspan="2" style="text-align: left;">交货日期&nbsp;&nbsp;&nbsp;&nbsp;<ui:date name="jhqi" showCalendar="false" value="dhuoTp.jhqi"/></th> 
			<th colspan="4" style="text-align: left;">&nbsp;</th>
			</tr>
			<tr>
			<th style="text-align: left;">重内&nbsp;&nbsp;&nbsp;&nbsp;<ui:int name="jhnr" title="订货DB的交货重量内容（1 或 2 或  3 或  6 或  7 或  8）" maxlength="1" value="dhuoTp.jhnr" /></th>
			<th style="text-align: left;">合同重量&nbsp;&nbsp;&nbsp;&nbsp;<ui:number name="htzl" title="订货DB的合同重量" scale="7" precision="3" positive="true" maxlength="8" value="dhuoTp.htzl"/></th>
			<th colspan="3" style="text-align: left;">交货允许&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="jhqf" title="订货DB的交货允许.区分（W或T）" maxlength="1" value="dhuoTp.jhqf"/>&nbsp;&nbsp;<ui:int name="jhfz" title="交货允许.-值（货允许.-值小于交货允许.+ 值）" maxlength="3" value="dhuoTp.jhfz" />-&nbsp;&nbsp;<ui:int name="jhzz" title="交货允许.+值" maxlength="3" value="dhuoTp.jhzz" /></th>
			<th colspan="2" style="text-align: left;">单包张数&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="kbsq" title="订货DB的捆包指定张数.区分" maxlength="1" value="dhuoTp.kbsq" />&nbsp;<ui:int name="kbsz" title="捆包指定张数.值" maxlength="4" value="dhuoTp.kbsz" /></th>
			<th colspan="3" style="text-align: left;">卷重&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="kbzq" title="订货DB的捆包指定重量.区分(5或6或8)" maxlength="1" value="dhuoTp.kbzq"/>&nbsp;<ui:input name="kbzd" title="捆包指定重量.单位（T）" maxlength="1" value="dhuoTp.kbzd" />&nbsp;<ui:number name="kbzx" title="捆包指定重量.下限" maxlength="6" scale="5" precision="3" value="dhuoTp.kbzx" />&nbsp;<ui:number name="kbzs" title="捆包指定重量.上限" maxlength="6" scale="5" precision="3" positive="true" value="dhuoTp.kbzs" onchange="setKbzx(this);" /></th>
			<th style="text-align: left;">捆包计算&nbsp;&nbsp;&nbsp;&nbsp;<ui:input name="kbjs" title="1：捆包形式由系统计算；其余：捆包形式非系统计算" maxlength="1" value="dhuoTp.kbjs" onblur="doKbjs();" /></th>
			</tr>
			<tr>
			<th style="text-align: left;">化成&nbsp;&nbsp;&nbsp;<ui:input name="huac" title="订货DB的化成处理(空或311或300)" maxlength="3" value="dhuoTp.huac"/></th>
			<th style="text-align: left;">捆包形式&nbsp;&nbsp;&nbsp;<ui:input name="kbao" title="系统根据制造仕样和订货信息进行计算的计算结果" maxlength="3" value="dhuoTp.kbao" /></th>
			<th style="text-align: left;">表面&nbsp;&nbsp;&nbsp;<ui:input name="face" title="订货DB的表面精加工" maxlength="2" value="dhuoTp.face" /></th>
			<th style="text-align: left;">压延&nbsp;&nbsp;&nbsp;<ui:input name="yyan" title="制造仕样主文件的压延方向指定标记（空或1或2）" maxlength="1" value="dhuoTp.yyan" /></th>
			<th style="text-align: left;">垫木&nbsp;&nbsp;&nbsp;<ui:input name="dmfx" title="造仕样主文件的垫木方向（空或L或C）" maxlength="1" value="dhuoTp.dmfx"/></th>
			<th style="text-align: left;">外W&nbsp;&nbsp;&nbsp;<ui:input name="rjie" title="订货DB的销售溶接部不可(0或1或2)" maxlength="1" value="dhuoTp.rjie" /></th>
			<th style="text-align: left;">内径&nbsp;&nbsp;&nbsp;<ui:input name="neij" title="订货DB的内径代码（419或508）" maxlength="3" value="dhuoTp.neij"/></th>
			<th style="text-align: left;">货标&nbsp;&nbsp;&nbsp;<ui:input name="hbbj" title="订货DB的货标标记（空或1）" maxlength="1" value="dhuoTp.nbbj" /></th>
			<th style="text-align: left;">B&nbsp;&nbsp;&nbsp;<ui:input name="tbno" title="订货DB的特殊BNO（空或1）" maxlength="1" value="dhuoTp.tbno" /></th>
			<th style="text-align: left;">C&nbsp;&nbsp;&nbsp;<ui:input name="tcno" title="订货DB的特殊CNO（空或1）" maxlength="1" value="dhuoTp.tcno" /></th>
			<th style="text-align: left;">需L&nbsp;&nbsp;&nbsp;<ui:input name="yhbq" title="订货DB的用户标签名表示（空或1）" maxlength="1" value="dhuoTp.yhbq" onkeydown="if(window.event.keyCode == 13) loadZzsy();" /></th>
			</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<table width="96%" class="form">
			<colgroup>
			<col width="6%" /><col width="3%" /><col width="6%" /><col width="11%" />
			<col width="8%" /><col width="5%" /><col width="8%" /><col width="5%" />
			<col width="6%" /><col width="5%" /><col width="6%" /><col width="5%" />
			<col width="8%" /><col width="5%" /><col width="8%" /><col width="5%" />
			</colgroup>
			<tr>
			<th style="text-align: left;">剪边</th>
			<td><ui:input name="jian" maxlength="1" readonly="true" value="dhuoTp.jian" /> </td>
			<th style="text-align: left;">零头下限</th>
			<td><ui:input name="ltdw" title="单位" maxlength="1" readonly="true" value="dhuoTp.ltdw" />&nbsp;&nbsp;<ui:int name="ltzi" title="值" maxlength="4" readonly="true" value="dhuoTp.ltzi" /> </td>
			<th style="text-align: left;">涂油种类</th>
			<td><ui:input name="ytyp" maxlength="1" readonly="true" title="涂油种类" value="dhuoTp.ytyp" /></td>
			<th style="text-align: left;">KPLATE</th>
			<td><ui:input name="plat" maxlength="1" readonly="true" title="KPLATE" value="dhuoTp.plat" /></td>
			<th style="text-align: left;">涂油量</th>
			<td><ui:input name="yqty" maxlength="2" title="涂油量" readonly="true" value="dhuoTp.yqty" /></td>
			<th style="text-align: left;">合金层</th>
			<td><ui:input name="hjin" maxlength="3" title="合金层" readonly="true" value="dhuoTp.hjin" /></td>
			<th style="text-align: left;">分配等级</th>
			<td><ui:input name="fpdj" maxlength="3" readonly="true" title="分配等级" value="dhuoTp.fpdj" /></td>
			<th style="text-align: left;">直角度</th>
			<td><ui:number name="jiao" scale="2" precision="1" readonly="true" title="直角度" maxlength="3" value="dhuoTp.jiao"/></td>
			</tr>
			<tr>
			<th style="text-align: left;">保护标记</th>
			<td><ui:input name="prot" maxlength="1" readonly="true" title="保护标记" value="dhuoTp.prot" /></td>
			<th colspan="14" style="text-align: left;">硬度上下限&nbsp;&nbsp;<ui:input name="ymin" maxlength="2" readonly="true" title="硬度下限" value="dhuoTp.ymin" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="ymax" maxlength="2" readonly="true" title="硬度上限" value="dhuoTp.ymax" />&nbsp;&nbsp;&nbsp;&nbsp;附着量.正上下限&nbsp;&nbsp;<ui:number name="fuzx" scale="4" precision="2" readonly="true" title="附着面.正面.下限值" value="dhuoTp.fuzx" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fuzs" scale="4" precision="2" readonly="true" title="附着面.正面.上限值" value="dhuoTp.fuzs" />&nbsp;&nbsp;&nbsp;&nbsp;附着量.反上下限&nbsp;&nbsp;<ui:number name="fufx" scale="4" precision="2" readonly="true" title="附着面.反面.下限值" value="dhuoTp.fufx" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:number name="fufs" scale="4" precision="2" readonly="true" title="附着面.反面.上限值" value="dhuoTp.fufs" /></th>
			</tr>
			<tr>
			<th style="text-align: left;">锯齿形式</th>
			<td><ui:input name="jcxs" maxlength="4" readonly="true" title="锯齿形式" value="dhuoTp.jcxs" /></td>
			<th colspan="14" style="text-align: left;">公差厚(%)&nbsp;&nbsp;<ui:number name="hxzi" scale="3" precision="1" readonly="true" value="dhuoTp.hxzi" title="尺寸允许范围.厚%下限.值" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="hszi" scale="3" precision="1" readonly="true" value="dhuoTp.hszi" title="尺寸允许范围.厚%上限.值" />&nbsp;&nbsp;&nbsp;&nbsp;公差宽(mm)&nbsp;&nbsp;<ui:number name="kxzi" scale="3" precision="2" readonly="true" value="dhuoTp.kxzi" title="尺寸允许范围.宽mm下限.值" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="kszi" scale="3" precision="2" readonly="true" value="dhuoTp.kszi" title="尺寸允许范围.宽mm上限.值" />&nbsp;&nbsp;&nbsp;&nbsp;公差长(mm)&nbsp;&nbsp;<ui:number name="cxzi" scale="3" precision="2" readonly="true" value="dhuoTp.cxzi" title="尺寸允许范围.长mm下限.值" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:number name="cszi" scale="3" precision="2" readonly="true" value="dhuoTp.cszi" title="尺寸允许范围.长mm上限.值" /></th>
			</tr>
			<tr>
			<th colspan="6" style="text-align: left;">装入宽&nbsp;&nbsp;<ui:number name="zrkn" scale="6" precision="2" readonly="true" title="装入宽" value="dhuoTp.zrkn" />&nbsp;&nbsp;&nbsp;&nbsp;内径保护筒&nbsp;&nbsp;<ui:input name="njbh" maxlength="1" readonly="true" title="内径保护筒" value="dhuoTp.njbh" />&nbsp;&nbsp;&nbsp;&nbsp;运用规格&nbsp;&nbsp;<ui:input name="yuny" maxlength="6" readonly="true" title="运用规格" value="dhuoTp.yuny" /></th>
			<th colspan="10" style="text-align: left;">附页KPNo&nbsp;&nbsp;<ui:input name="kpn1Flag" maxlength="1" title="标识位.1：ETL;2：SL;3;两者" readonly="true" value="dhuoTp.kpn1Flag" />-<ui:input name="kpn1" maxlength="9" title="附页KPNO.1" readonly="true" value="dhuoTp.kpn1" />&nbsp;&nbsp;<ui:input name="kpn2Flag" maxlength="1" title="标识位.2：ETL;2：SL;3;两者" readonly="true" value="dhuoTp.kpn2Flag" />-<ui:input name="kpn2" maxlength="9" title="附页KPNO.2" readonly="true" value="dhuoTp.kpn2" />&nbsp;&nbsp;<ui:input name="kpn3Flag" maxlength="1" title="标识位.3：ETL;2：SL;3;两者" readonly="true" value="dhuoTp.kpn3Flag"/>-<ui:input name="kpn3" maxlength="9" title="附页KPNO.3" readonly="true" value="dhuoTp.kpn3" />&nbsp;&nbsp;<ui:input name="kpn4Flag" maxlength="1" title="标识位.4：ETL;2：SL;3;两者" readonly="true" value="dhuoTp.kpn41Flag" />-<ui:input name="kpn4" maxlength="9" title="附页KPNO.4" readonly="true" value="dhuoTp.kpn4" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">木工所No</th>
				<td colspan="15"><input type="hidden" name="mgsn" value="${dhuoTp.mgsn }"/> <span id="mgsnSpan" title="木工所No（如果有多个木工所no时，要用半角英文输入法的“,”间开）" class="form-text">${dhuoTp.mgsn }</span></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注1</th>
				<td colspan="15"><input type="hidden" name="bz1" value="${dhuoTp.bz1 }"/> <span id="bz1Span" title="ETL和木工所指示书的备注内容" class="form-text">${dhuoTp.bz1 }</span></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注2</th>
				<td colspan="15"><input type="hidden" name="bz2" value="${dhuoTp.bz2 }"/> <span id="bz2Span" title="SL指示书的备注内容" class="form-text">${dhuoTp.bz2 }</span></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注3</th>
				<td colspan="15"><input type="hidden" name="bz3" value="${dhuoTp.bz3 }"/> <span id="bz3Span" title="ETL、木工所和SL指示书的备注内容" class="form-text">${dhuoTp.bz3 }</span><input type="hidden" name="jcts" /></td>
			</tr>
		</table>
		<hr style="border: 1px dashed #338899;">
		<div align="center">
		   <input type="button" value="加载仕样信息" onclick="loadZzsy()" class="button" />&nbsp;&nbsp;
			<input type="button" value="保 存" onclick="doSubmit();" class="button" />
		</div>
		</form>
		</ui:page>
	</body>
</html>
