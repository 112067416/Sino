<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<title>员工管理</title>
<%@include file="../../global/header.jsp"%>
<script type="text/javascript" src="../../js/calendar.js"></script>
<script type="text/javascript">

contextmenu.putMenus("menu", [["查看", "view.gif", "toView()"],
                              ["修改", "edit.gif", "toModify()"],
                      		["删除", "del.gif", "toDel()"]]);
	function toAdd() {
		var form = document.forms["DataForm"];
		cocoform.clear(form);
		form.elements["valid"].checked = true;
		coco.showPage("Detail",{center:true,top:50,width:"90%"});
	}

	function toModify(oTr) {
		if(oTr == null) oTr = contextmenu.selectedTarget();
		var id = coco.getAttr(oTr, "xu.id");
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			cocoform.fillResult("DataForm", this.result);
			coco.showPage("Detail",{center:true,top:50,width:"80%"});
		};
		ajax.post("get!"+id+".do", null, "POST");
	}

	function toDel() {
		if(!window.confirm("确定要删除该条记录吗?")) return;
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			alert("删除成功");
			coco.hidePage('Detail');
			cocopage.refresh();
		};
		ajax.post("del!"+contextmenu.selectedId()+".do", null, "POST");
	}

	function success() {
		alert("保存成功");
		coco.hidePage('Detail');
		cocopage.refresh();
	}

	function toView() {
		var ajax = new Cocoajax();
		ajax.dataDiv = document.getElementById("ViewArea");
		ajax.callback = function() {
			if(this.code <= 0) {
				coco.alert(this.msg);
				return;
			}
			coco.showPage('View',{center:true,top:50,width:"80%"});
		};
		ajax.post("view!"+contextmenu.selectedId()+".do");
	}
	//-->
</script>
</head>
<body>
<ui:page title="员工管理">
	<f:page action="index.do" file="list.jsp">
		<table width="100%" class="form">
			<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<tr>
				<th>姓名</th>
				<td><ui:input name="name"/></td>
				<th>工号</th>
				<td><ui:input name="no"/></td>
			</tr>
			<tr>
				<th>机构</th>
				<td><ui:treebox name="dept" cssStyle="width:98%;" items="depts" fieldLabel="name" fieldOrder="order" root="0" headerText="全部" headerValue=""/></td>
				<th>性别</th>
				<td><ui:select name="sex" list="'':'全部','1':'男','2':'女'" cssStyle="width:96%" /></td>
			</tr>
		</table>
			<div class="submit">
			<input type="button" class="button" value="查 询(Q)" onclick="cocopage.query()" accesskey="q" />
			<input type="button" class="button" value="新 增(A)" onclick="toAdd()" accesskey="a"/>
			</div>
	</f:page>
</ui:page>
<ui:panel id="Detail" title="详细信息" popup="true" display="false" closable="true" >
<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="save.do" >
<input type="hidden" name="id" />
	<table width="100%" class="form" >
		<colgroup>
			<col width="10%" />
			<col width="40%" />
			<col width="10%" />
			<col width="40%" />
		</colgroup>
		<tr>
			<th>工号</th>
			<td><ui:input name="no" maxlength="3" prop="nn:1;" /></td>
			<th>姓名</th>
			<td><ui:input name="name" cssStyle="width:96%;" prop="nn:1;" /></td>
		</tr>
		<tr>
			<th>性别</th>
			<td><ui:radiolist name="sex" list="'1':'男','2':'女'" value="#1" /></td>
			<th>出生日期</th>
			<td><ui:date name="birthday" /></td>
		</tr>
			<th>机构</th>
			<td colspan="3"><ui:treebox name="dept.id" prop="nn:1;" cssStyle="width:96%;" items="depts" fieldLabel="name" fieldOrder="order" root="0"/></td>
		</tr>
		<tr>
			<th>地址</th>
			<td colspan="3"><ui:input name="address" cssStyle="width:96%" /></td>
		</tr>
		<tr>
			<th>电话</th>
			<td><ui:input name="tele" maxlength="20"/></td>
			<th>手机</th>
			<td><ui:input name="mobile" maxlength="20"/></td>
		</tr>
		<tr>
			<th>QQ</th>
			<td><ui:input name="qq" maxlength="20"/></td>
			<th>MSN</th>
			<td><ui:input name="msn" cssStyle="width:96%" /></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><ui:input name="email" cssStyle="width:96%" prop="type:email" /></td>
			<th>排序</th>
			<td><ui:input name="order" maxlength="20"/></td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><ui:textarea name="remark" rows="3" cssStyle="width:96%" /></td>
		</tr>
		<tr>
			<th>身份证</th>
			<td><ui:input name="idcard" prop="type:idcard" maxlength="18"/></td>
			<th>有效</th>
			<td><ui:bool name="valid" /></td>
		</tr>
	</table>
	<div class="submit">
		<input type="button" class="button" value="保 存(S)" onclick="cocoform.submit(this);" accesskey="s" />
	</div>
	</form>
</ui:panel>
<ui:panel id="View" title="查看详细信息" popup="true" display="false" closable="true" >
<DIV id="ViewArea" ></DIV>
</ui:panel>
</body>
</html>