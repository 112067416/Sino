<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ 
	taglib uri="/f" prefix="f"%><%@ 
	taglib uri="/sys" prefix="sys"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>台帐备注管理</title>	
		<%@include file="/WEB-INF/jsp/global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		//修改台帐信息
		function saveTz(el) {
			var oForm = el.form;
			var bbm = oForm.elements["bbm"];
			var $bbm = bbm.value;
			if($bbm == null || $bbm.length == 0) {
				alert("报表名不能为空");
				bbm.focus();
				return;
			}
			var xpzk = oForm.elements["xpzk"];
			var $xpzk = xpzk.value;
			if($xpzk == null || $xpzk.length == 0) {
				alert("现品状况不能为空");
				xpzk.focus();
				return;
			}
			var ny = oForm.elements["ny"];
			var $ny = ny.value;
			if($ny == null || $ny.length == 0) {
				alert("日期不能为空");
				ny.focus();
				return;
			}
			//var spbh = oForm.elements["spbh"];
			//var $spbh = spbh.value;
			//if($spbh == null || $spbh.length == 0) {
			//	alert("商品编号不能为空");
			//	spbh.focus();
			//	return;
			//}
			var bz = oForm.elements["bz"];
			var $bz = bz.value;
			if($bz == null || $bz.length == 0) {
				alert("备注不能为空");
				bz.focus();
				return;
			}
			cocoform.submit(oForm, function() {
					if(this.code <= 0) {
						alert(this.msg);
						return;
					}
					coco.hidePage('TzbzDetail');
					alert("保存成功");
					cocopage.refresh();
				}, "是否确定保存?", null, el);
		}

		//弹出新增页面
		function doAdd() {
			var oForm = document.forms["TzbzForm"];
			cocoform.clear(oForm);
			coco.showPage("TzbzDetail", { center : true, top : 80, width : "90%" });
		}

		//修改
		function toModify(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var oForm = document.forms["TzbzForm"];
			var vKey1 = coco.getAttr(oTr, "xu.id");
			var postContent = coco.parseParam("id", vKey1);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				cocoform.fillResult(oForm, this.result);
				coco.showPage("TzbzDetail", { center : true, top : 80, width : "90%" });
			};
			ajax.post("loadTzbz.do", postContent);
		}

		//删除按钮的操作（批量删除）
		function toDel() {
			var ids = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(ids == null || ids.length == 0){
				alert("请选择要做删除操作的记录");
				return;
			}
		    if(!confirm("确定删除吗?")) return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("删除成功");
				cocopage.refresh();
			};
			ajax.post("delsTzbz.do", ids);
		}
		//-->
		</SCRIPT>
	</head>
<body>
<ui:page title="台帐备注管理">
<f:page action="indexTzbz.do" file="listTzbz.jsp">
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="30%" /><col width="10%" /><col width="50%" /></colgroup>
<tr>
<th style="text-align: left;">年月(yyyyMM)</th>
<td><ui:input name="ny" cssClass="normal" value="page.ny" maxlength="6" /></td>
<td colspan="2">&nbsp;</td>
</tr>
</table>
<div class="submit">
<input type="button" class="button" value="查 询" onclick="cocopage.query();" />
<input type="button" class="button" value="新 增" onclick="doAdd();" />
<input type="button" class="button" value="删 除" onclick="toDel();" />
</div>	
</f:page>
</ui:page>
<ui:panel id="TzbzDetail" title="设置台帐备注" popup="true" display="false" closable="true">
<form action="saveTzbz.do" name="TzbzForm" method="post" >
<input type="hidden" name="id" />
<table width="100%" class="form">
<colgroup>
<col width="7%" /><col width="34%" /><col width="7%" /><col width="20%" /><col width="8%" /><col width="24%" />
</colgroup>
<tr>
<th style="text-align: left; vertical-align: middle;">报表名</th>
<td colspan="5"><ui:select name="bbm" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BBM'" prop="nn: 1;" /></td>
</tr>
<tr>
<th style="text-align: left;">现品状况</th>
<td><ui:select name="xpzk" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='XPZK'" prop="nn: 1;" /></td>
<th style="text-align: left;">年月(yyyyMM)</th>
<td><ui:input name="ny" maxlength="6" title="规格代码" required="true" /></td>
<th style="text-align: left;">商品编号</th>
<td><ui:select name="spbh" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SPBH'" prop="nn: 1;" /></td>
</tr>
<tr>
<th style="text-align: left; vertical-align: middle;">备注</th>
<td colspan="5"><ui:textarea name="bz" cols="60" rows="3" /></td>
</tr>
</table>
<div class="submit"><input type="button" class="button" value="保  存" onclick="saveTz(this);" /> <input type="button" class="button" value="关  闭" onclick="coco.hidePage('TzbzDetail');" /></div>
</form>
</ui:panel>
	</body>
</html>