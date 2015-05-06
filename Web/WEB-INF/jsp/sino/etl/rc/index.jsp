<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>ETL入侧 </title>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		function doRefresh()  { 
			//查询第三方接口备用卷状态
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {	
					return;
				}
				window.location.reload(); 
			};
			ajax.post("updateYCBY.do", "", "POST");
			
		} 
		function doShowGif()  { 
			
			var ajax = new Cocoajax();
			//显示备用卷提示图片
			ajax.callback = function() {
				
				if(this.code <= 0) {	
					ViewGif1.style.display = "none";
					ViewGif2.style.display = "none";
				}else if(this.code=="1"){
					ViewGif1.style.display = "block";
					ViewGif2.style.display = "none";
				}else if(this.code=="2"){
					ViewGif1.style.display = "none";
					ViewGif2.style.display = "block";
				}
				
			};
			ajax.post("getbygif.do", "", "POST");
		} 
		window.setInterval(doRefresh, 3000); //指定5分钟刷新一次 (1000为1秒)
		window.setInterval(doShowGif, 3000); //指定5分钟刷新一次 (1000为1秒)
		// 弹出消息框
		function doPopMsg() {
			var msgWinWid = 300, msgWinHgt = 260;
			var vMsgWinTop = 0, vMsgWinLeft = 0;
			if (window.screen) {
				vMsgWinTop = screen.availHeight - msgWinHgt - 35;
				vMsgWinLeft = screen.availWidth - msgWinWid - 10;
			}
			msgWin = window.open('/sino/etl/rc/popMsg.do','MSGWINDOW','height='+ msgWinHgt +',width='+ msgWinWid +',top='+ vMsgWinTop +',left='+ vMsgWinLeft +',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
			msgWin.focus();
		}
        //添加备用卷
        function addByj() {
        	var oForm = document.forms["DataForm"];
        	var el1 = oForm.elements["jbno"];
        	var jbno = el1.value.replace(/^\s+|\s+$/gi,'');
        	if(jbno == null || jbno.length == 0 ) {
        		alert("COIL No.不能为空");
        		el1.focus();
        		return;
        	}
        	if(jbno.length == 5) {
       			el1.value = "0" + jbno;
        	}
        	var el2 = oForm.elements["zsno"];
        	var zsno = el2.value.replace(/^\s+|\s+$/gi,'');
        	if(zsno == null || zsno.length == 0 ) {
        		alert("指示书 No.不能为空");
        		el2.focus();
        		return;
        	}
        	cocoform.submit(oForm,success,'确定要添加备用卷吗?');
        }
        
        function success() {
			if (this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("备用卷添加成功");
			window.location.reload();
		}

		// 撤消
		function doCx(jbno) {
			if(jbno == null || jbno.length == 0) {
				alert("没有备用卷,不能做撤消操作");
				return;
			}
			if(!window.confirm("确定要取消该备用卷吗?")) return;
			var postContent = coco.parseParams([{name : "jbno",value: jbno}]);
			var ajax = new Cocoajax();
			ajax.dataDiv = ViewZsDiv;
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert("撤消成功!");
				window.location.reload();
				
			};
			ajax.post("deleteBY.do", postContent, "POST");
		}

		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		  	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		
		// 强制移行
		function doQzyh(jbno) {
			if(jbno == null || jbno.length == 0) {
				alert("没有当前卷,不能做强制移行操作");
				return;
			}
			if(!window.confirm("确定要强制移行该卷板吗?")) return;
			var postContent = coco.parseParams([{name : "dqjbno",value: jbno}]);
			//设定改卷生产完成
			var ajax = new Cocoajax();
			ajax.dataDiv = ViewZsDiv;
			ajax.callback = function() {
				if (this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				window.location.reload();
			};
			ajax.post("updateMove.do", postContent, "POST");
		}
		//进入采样单分析页面
		function toFxd(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var jbno = coco.getAttr(oTr, "key");
			if(jbno == null || jbno.length == 0) return;
			var postContent = coco.parseParam("jbno", jbno);
			var oForm = document.forms["FxdForm"];
			var ignores = ["ban", "zu", "bz", "plt"];
			cocoform.clear(oForm,ignores);
			var plt = oForm.elements["plt"].value;
			if(plt == null || plt.length == 0) {
				return;
			}
			var eles = oForm.elements["fxxms"];
			var el, fxxms = null, cywzs = null;
			for(var i = 0; i < eles.length; i++) {
				el = eles[i];
				if(el.value == plt) el.checked = true;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					oForm.elements["dxl"].value = '';
					oForm.elements["tyl"].value = '';
					oForm.elements["zsno"].value = '';
					oForm.elements["jbno"].focus();
					return;
				}
				var obj;
				eval("obj=" + this.result + ";");
				oForm.elements["dxl"].value = obj.dxl;
				oForm.elements["tyl"].value = obj.tyl;
				oForm.elements["zsno"].value = obj.zsno;
				oForm.elements["jbno"].value = jbno;
				coco.showPage("Detail", { center : true, top : 60, width : "69%"});
			};
			ajax.post(path + "/sino/fxs/cyrz/loadFxmb.do",postContent);
		}
		function loadFxmb(el) {
			var oForm = document.forms["FxdForm"];
			var jbno = el.value.replace(/^\s+|\s+$/gi,'');
			if(jbno == null || jbno.length == 0) {
				alert("Coil No. 不能为空");
				return;
			}
			var postContent = coco.parseParams([{name:"jbno",value:jbno}]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					oForm.elements["dxl"].value = '';
					oForm.elements["tyl"].value = '';
					oForm.elements["zsno"].value = '';
					oForm.elements["jbno"].focus();
					return;
				}
				var obj;
				eval("obj=" + this.result + ";");
				oForm.elements["dxl"].value = obj.dxl;
				oForm.elements["tyl"].value = obj.tyl;
				oForm.elements["zsno"].value = obj.zsno;
			};
			ajax.post(path + "/sino/fxs/cyrz/loadFxmb.do",postContent);
		}
		//保存操作
		function doSave(oForm, oInput) {
			var eles = oForm.elements["fxxms"];
			var el, fxxms = null, cywzs = null;
			for(var i = 0; i < eles.length; i++) {
				el = eles[i];
				if(!el.checked) continue;
				if(fxxms == null) {
					fxxms = el.value;
				} else {
					fxxms = fxxms + "," + el.value;
				}
			}
			if(fxxms == null || fxxms.length == 0) {
				alert("请选择分析项目");
				return;
			}
			eles = oForm.elements["cywzs"];
			for(var i = 0; i < eles.length; i++) {
				el = eles[i];
				if(!el.checked) continue;
				if(cywzs == null) {
					cywzs = el.value;
				} else {
					cywzs = cywzs + "," + el.value;
				}
			}
			if(cywzs == null || cywzs.length == 0) {
				alert("请选择采样位置");
				return;
			}
			oForm.action = path + "/sino/fxs/cyrz/save.do";
			cocoform.submit(oForm, function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				alert('保存成功');
				coco.hidePage('Detail');
				}, "是否确定保存?", null, oInput);
		}

		//-->
		</script>
	</head>
	<body onload="doRefresh();">
		<ui:page title="ETL入侧">
		<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
			<table width="98%" align="center" class="form">
				<colgroup>
				<col />
				<col />
				<col  />
				<col  />
				<col />
				<col />
				<col />
				<col  />
				<col  />
				<col  />
				<col  />
				</colgroup>
				<tr>
					<th>生产线</th>
					<td><input type="hidden" name="slin" value="${page.slin}" />
						<f:v value="page.slin" ql="select name from Scxbpz where code=?"/></td>
					<th>班</th>
					<td><ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;" value="page.ban" /></td>
					<th>组</th>
					<td><ui:select name="bz" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"  prop="nn:1;" value="page.zu" /></td>
					<th>指示书</th>
					<td><ui:input name="zsno" required="true" maxlength="6" onkeydown="if(window.event.keyCode == 13) addByj();" /></td>
					<th>COIL No.</th>
					<td><ui:input name="jbno" maxlength="7" required="true" title="扫描枪录入" onkeydown="if(window.event.keyCode == 13) addByj();" /></td>
					<td><input type="button" class="button" value="添加备用卷" accesskey="s" onclick="addByj();"/></td>
				</tr>
			</table>
			<table id="DataTbl" width="100%" align="center" class="pagination" style="margin-top: 10px;">
				<colgroup><col width="5%" /><col width="7%" /><col width="7%" /><col width="4%" />
				<col width="10%" /><col width="5%" /><col width="9%" /><col width="9%" /><col width="4%" /><col width="15%" />
				<col width="15%" /><col width="10%" align="center" />
				</colgroup>
				<tr>
					<th>状态</th>
					<th>指示No.</th>
					<th>Coil NO.</th>
					<th>操作</th>
					<th>现品尺寸</th>
					<th>重量</th>
					<th>运用规格</th>
					<th>锡附着量</th>
					<th>内径</th>
					<th>别纸No.</th>
					<th>业联No.</th>
					<th>操作</th>
				</tr>
				<c:forEach varStatus="stat" var="item"  items="${page.rcmxs}">
				 <tr key="${item.jbno }" ondblclick="toFxd(this);" title="双击新增采样分析单" >
					<td ><font <c:if test="${stat.index%2 == 1 }">color="red"</c:if> color="green" size="2"> <f:kv value="item.stat" list="'1':'备用卷','2':'当前卷'"  /></font></td>
					<td><input type="hidden" value=${item.sfqr } >${item.zsno }</td>
					<td>${item.jbno }</td>
					<td>${item.caoz }</td>		
					<td>${item.size }</td>
					<td>${item.zpzl }</td>
					<td>${item.yuny }</td>
					<td>${item.xfuz }</td>
					<td>${item.njno }</td>
					<td><c:forEach varStatus="stat" var="kpno" items="${item.kpns }"><span class="link" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span></c:forEach></td>
					<td><c:forEach varStatus="stat" var="ylno" items="${item.ylns }"><span class="link" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span></c:forEach></td>
					<td align="center">
					<c:if test="${ item.stat==1 }"><span class="link" onclick="doCx('${item.jbno }');">撤消</span></c:if>
					<c:if test="${ item.stat==2}"><span class="link" onclick="doQzyh('${item.jbno }');">强制移行</span></c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			</form>
			<div id="ViewZsDiv"></div>
		</ui:page>
		<div id="ViewGif1" style="display: none;">
		<table width="100%" align="center"  style="margin-top: 10px;">
			   <tr>
			       <td width="35%"></td> 
			       <td id="bygif"  width="35%" >
			       <img alt="" src="../../../images/main/by2.gif" id="bygif">
			        </td>
			        <td width="35%">
			        </td> 
				</tr>
			</table>
		</div>
		<div id="ViewGif2" style="display: none;">
		<table width="100%" align="center"  style="margin-top: 10px;">
			   <tr>
			       <td width="35%"></td> 
			       <td id="bygif"  width="35%" >
			       <img alt="" src="../../../images/main/by3.gif" id="bygif">
			        </td>
			        <td width="35%">
			        </td> 
				</tr>
			</table>
		</div>
		<ui:panel id="Detail" title="新增采样分析单" popup="true" display="false" closable="true">
		<form name="FxdForm" action="#" method="post" >
		<input type="hidden" name="plt" value="${plt }" />
		<table width="100%" align="center" class="form">
			<colgroup><col width="19%" /><col width="31%" /><col width="15%" /><col width="35%" /></colgroup>
			<tr>
				<th>班</th>
				<td><sys:codeSelect name="ban" code="#SINO_BAN" prop="nn:1;" value="ban" /></td>
				<th>组</th>
				<td><sys:codeSelect name="zu" code="#SINO_ZU" prop="nn:1;" value="zu" /></td>
			</tr>
			<tr>
				<th>指示连号</th>
				<td><ui:input name="zsno" maxlength="6" required="true" readonly="true" /></td>
				<th>Coil No.</th>
				<td><ui:input name="jbno" maxlength="7" required="true" onkeydown="if(window.event.keyCode==13) loadFxmb(this);" onblur="loadFxmb(this);" /></td>
			</tr>
			<tr>
				<th>镀锡量</th>
				<td><ui:input name="dxl" maxlength="12" required="true" readonly="true" /></td>
				<th>涂油量</th>
				<td><ui:input name="tyl" maxlength="7" required="true" readonly="true" /></td>
			</tr>
			<tr>
				<th height="150" style="text-align: right; vertical-align: middle;">分析项目</th>
				<td colspan="3"><c:forEach varStatus="stat" var="item" items="${xms }"><span style="display: -moz-inline-box; display: inline-block; width: 135px; white-space: nowrap;"><input type="checkbox" xu.type="checklist" name="fxxms" value="${item }" />${item }&nbsp;&nbsp;</span><c:if test="${stat.count % 4 == 0 }"><br /></c:if></c:forEach></td>
			</tr>
			<tr>
				<th>采样位置</th>
				<td colspan="3"><ui:checklist name="cywzs" ql="select key, value from CodeItem where code.id='SINO_CYFX_WZ' order by order" /></td>
			</tr>
			<tr>
				<th style="vertical-align: middle;">备注</th>
				<td colspan="3" style="vertical-align: middle;"><ui:textarea name="bz" rows="4" value="bz" /></td>
			</tr>
		</table>
		<div class="submit">
			<input type="button" class="button" value="保   存" onclick="doSave(this.form, this);" />
			<input type="button" class="button" value="关  闭" onclick="coco.hidePage('Detail');" />
		</div>
		</form>
		</ui:panel>
	</body>
</html>