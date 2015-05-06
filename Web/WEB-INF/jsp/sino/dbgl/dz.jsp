<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/sllrcompute.js"></script>
		<script type="text/javascript">
		<!--

		// 订正检查
		function dzCheck() {
			//var oForm = document.forms["HeadForm"];
			var obj = document.getElementById("jbno");
			var jbno=obj.value;
			if(jbno == null || jbno == 0) return;
			if(jbno=="") return;
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
			var postContent="jbno="+jbno;	
			ajax.post("dzCheck.do", postContent, "POST");
		}
		// 重录
		function doCl() {
			DataArea.style.display = "none";
			DataArea.style.display = "none";
			document.getElementById("jbno").disabled = false;
			document.getElementById("jbno").focus();
		}
	
		//修改操作
		function doSubmit(obj) {	
			if(!window.confirm("确定修改吗?")) return;
			var oForm = obj.form;
		    cocoform.submit(obj,success);
			
		}
		// 更新成功
		function success() {
			if(this.code < 0) {
				coco.alert(this.msg);
				return;
			}
			alert("修改成功");
			var oForm = document.forms["DataForm"];
			var jbno=oForm.elements["jbno"].value;
			doPrint2(jbno);
		}
		// 打印服务卡
		function doPrint2(jbno) {
			document.getElementById("PrintFrame").src = "../dy/zpdbk.do?jbnos="+jbno;
		}
		
		//--><c:if test="${pileno!=null}">onload="doCreate()"</c:if>
		</script>
		
	</head>
	<body <c:if test="${jbno!=null}">onload="dzCheck()"</c:if>>
			
	<ui:page title="端板实绩订正">
		<table width="96%" align="center" class="form">
			<colgroup>
				<col width="15%" /><col width="20%" />
			</colgroup>
			<tr>
				
				<th>制品号</th>
				<td><ui:input id="jbno" name="jbno" value="jbno" maxlength="11" title="输入的制品号" onkeydown="if(event.keyCode == 13) dzCheck();" /></td>
			</tr>
		</table>
		<div id=DataArea style="display: none;">
		<form name="DataForm" action="update.do" method="post" xu.s="success();" xu.color="1">
			<table width="96%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="25%" />
			</colgroup>
			<tr>
				<th style="text-align: right;">制品号</th>
				<td><ui:input name="jbno" required="true" readonly="true"  value="jbno" maxlength="11"/></td>
				<th style="text-align: right;">生产线别</th>
				<td nowrap="nowrap"><ui:select list="'T':'选别','V':'剪切二线','W':'剪切三线'" name="slin"  headerText="" headerValue="" prop="nn:1;" /></td>
				<th style="text-align: right;">重量(kg)</th>
				<td colspan="2"><ui:int name="zpzl" maxlength="5" required="true" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">足</th>
				<td><ui:input name="dmfx" required="true" maxlength="1"/></td>
				<th style="text-align: right;">捆包形式</th>
				<td colspan="3"><ui:select name="kbao"  prop="nn:1;" headerText="" headerValue="" list="'S21':'S21','S31':'S31'"  /></td>
				
			</tr>
		</table>
		
			<div class="opt-btm">
			 <input type="button" class="button" value="保 存" onclick="doSubmit(this)" />
			 	<input type="button" class="button" value="返回(A)" accesskey="a"
					onclick="doCl();" />
			 </div>
			
		</form>
		</div>
		<br/>
	</ui:page>
	<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
	</body>
</html>