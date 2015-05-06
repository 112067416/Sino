<%@page import="com.quanta.sino.orm.Scxbpz"%><%@
page import="com.quanta.sino.cmn.constants.ProductType"%><%@
page import="com.quanta.sino.cmn.bo.api.IScxbBO"%><%@
page import="com.coco.sys.orm.CodeItem"%><%@
page import="com.coco.core.env.Helper"%><%@
page import="com.coco.sys.bo.api.ICodeBO"%><%@
page language="java" pageEncoding="UTF-8"%><%@
page import="java.util.*"%><%@
page import="com.coco.report.bean.Entry"%><%@
page import="com.coco.report.bean.Param"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%
Entry entry = (Entry)request.getAttribute("entry");
if(entry == null) return;
String root = request.getContextPath();
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>${entry.title }</title>    
    <%@include file="../../global/header.jsp" %>
    <script type="text/javascript" src="../../js/calendar.js"></script>
	<script type="text/javascript">
	<!--
	function doSubmit(oForm, obj) {
		cocoform.submit(oForm, function() {
			if(this.code < 0) coco.alert(this.msg);
			else if(this.msg == null) alert("系统出错");
			else {
				var wo = document.getElementById("WebOffice");
				wo.HideMenuItem(0x33);
				if(window.self == window.top) wo.LoadOriginalFile("./openExcel!"+this.msg+".do", "xls");
				else wo.LoadOriginalFile("<%=root%>/coco/report/openExcel!"+this.msg+".do", "xls");
				wo.hideMenuArea("hideall","","","");
				${entry.script};
			}
		}, null, null, obj);
		return false;
	}

	function resize() {
		var height = document.documentElement.clientHeight;
		var queryHeight = document.getElementById("QueryArea").offsetHeight;
		document.getElementById("ExcelArea").style.height = (height - queryHeight- 3) + "px";
	};
	
	//coco.addEventListener(window, "resize", resize);
	coco.addEventListener(window, "load", resize);
	//-->
	</script>
  </head>
  <body>
	  		<div id="QueryArea">
			<form action="excel!${entry.id}.do" name="DataForm" method="post" target="ExcelFrame">
			  <input type="text" name="hidden" style="display: none;" />
			  <table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="form">
			  <colgroup>
			  <col width="8%" /><col width="25%" />
			  <col width="8%" /><col width="25%" />
			  <col width="8%" /><col width="26%" />
			  </colgroup>
<%
boolean hasBtn = false;
if(entry.getParams() != null && entry.getParams().size() > 0) {
	int size = entry.getParams().size();
	int cell = 3;
	int row =  size / cell + 1;
	if(size % cell == 0) row--;
	Param param;
	int i, j, k;
	boolean isBetween;
	String prop;
	String value;
	int length;
	String maxLength;
	String style;
	String jointColumn,jointChar,jointMaxLength,jointStyle;
	int jointLength;
	for(i = 0; i < row; i++) {
		out.println("<TR>");
		for(j = 0; j < cell; j++) {
			k = i * cell + j;
			param = k < size ? entry.getParams().get(k) : null;
			if(param == null) {
				out.println("<TD colspan='"+((cell - j) * 2)+"' align='center' >&nbsp;<INPUT type='button' class='button' value='查 询' border=0 onclick='doSubmit(this.form, this)' /></TD>");
				hasBtn = true;
				break;
			}
 			isBetween = "between".equals(param.getOpt());
 			prop = param.isRequired() ? "nn:1;" : "";
 			prop = prop + ("int".equalsIgnoreCase(param.getType()) ? "type:number;" : "");
 			value = param.getDefaultValue();
 			if(value != null && !value.isEmpty()) {
 				value = " value=\"" + value.replaceAll("\"", "\\\\\"") + "\"";
 			}
 			length = param.getLength();
 			if(length > 0) {
 				maxLength = " maxlength="+length;
 				style = "width:" + (length * 10 + 2) + "px;";
 			} else {
 				maxLength = "";
 				style = "width:"+(isBetween?"45%":"98%");
 			}
 			jointColumn = param.getJointColumn();
 			jointChar = param.getJointChar();
 			jointLength = param.getJointLength();
 			if(jointLength > 0) {
 				jointMaxLength = " maxlength="+jointLength;
 				jointStyle = "width:" + (jointLength * 10 + 2) + "px;";
 			} else {
 				jointMaxLength = "";
 				jointStyle = "width:"+(isBetween?"45%":"98%");
 			}
	 		out.print("<TH NOWRAP=\"true\" ALIGN=\"right\" >");
	 		out.print(param.getName());
	 		out.print("</TH><TD NOWRAP=\"true\" >");
	 		if(param.getColumn() == null || param.getColumn().isEmpty()) {
	 			out.print("</TD>");
	 			continue;
	 		}
	 		if(param.getSelect() != null && !param.getSelect().isEmpty()) {
	 			out.print("<SELECT name='" + param.getColumn() + "' class='normal' style='"+style+"' xu.prop='" + prop + "' "+value+" >");
	 			out.print(param.getSelect());
	 			out.print("</SELECT>");
	 		}
	 		//码表
	 		else if(param.getKey() != null && !param.getKey().isEmpty()) {
	 			out.print("<SELECT name='" + param.getColumn() + "' class='normal' style='"+style+"' xu.prop='" + prop + "' "+value+" >");
	 			out.print("<option value=\"\">全部</option>");
	 			ICodeBO codeBo = Helper.getBean(ICodeBO.class);
	 			List<CodeItem> codeItems = codeBo.findItems(param.getKey());
	 			for(CodeItem codeItem : codeItems) {
	 				out.print("<option value=\"");
	 				if(codeItem.getKey() != null) {
	 					out.print(codeItem.getKey().replaceAll("\"","\\\\\""));
	 				}
	 				out.print("\">");
	 				if(codeItem.getValue() != null) {
	 					out.print(codeItem.getValue());
	 				}
	 				out.print("</option>");
	 			}
	 			out.print("</SELECT>");
	 		}
	 		//生产线配置
	 		else if(param.getScx() != null && !param.getScx().isEmpty()) {
	 			ProductType scxType = ProductType.get(param.getScx());
	 			if(scxType != null) {
		 			out.print("<SELECT name='" + param.getColumn() + "' class='normal' style='"+style+"' xu.prop='" + prop + "' "+value+" >");
		 			out.print("<option value=\"\">全部</option>");
		 			IScxbBO scxbBo = Helper.getBean(IScxbBO.class);
		 			List<Scxbpz> scxbs = scxbBo.findByType(scxType);
		 			for(Scxbpz scxb : scxbs) {
		 				out.print("<option value=\"");
		 				if(scxb.getCode() != null) {
		 					out.print(scxb.getCode().replaceAll("\"","\\\\\""));
		 				}
		 				out.print("\">");
		 				if(scxb.getName() != null) {
		 					out.print(scxb.getName());
		 				}
		 				out.print("</option>");
		 			}
	 			}
	 		}
	 		
	 		else if("date".equals(param.getType())) {
	 			out.print("<INPUT type='text' name='" + param.getColumn() + "' class='normal date' xu.validate='1' size='10' xu.prop='type:date;calendar:true;"+prop+ "' "+value+" onkeydown=\"if(window.event.keyCode == 13) doSubmit(this.form, this)\" />");
		  		if(isBetween) {
					out.print("至<INPUT type='text' name='"+ param.getColumn()+"_end' class='normal date' xu.validate='1' size='10' xu.prop='type:date;calendar:true;' "+value+" onkeydown=\"if(window.event.keyCode == 13) doSubmit(this.form, this)\" />");
		  		}
	 		}
	 		else {
	 			out.print("<INPUT type='text' name='" + param.getColumn() + "' class='normal' style='"+style+"' "+maxLength+" xu.prop='"+prop + "' "+value+" onkeydown=\"if(window.event.keyCode == 13) doSubmit(this.form, this)\" />");
		  		if(jointColumn != null && !jointColumn.isEmpty() && jointChar != null && !jointChar.isEmpty()) {
		  			out.print(jointChar+"<INPUT type='text' name='" + jointColumn + "' class='normal' style='"+jointStyle+"' "+jointMaxLength+" onkeydown=\"if(window.event.keyCode == 13) doSubmit(this.form, this)\" />");
		  		}
	 			if(isBetween) {
		  			out.print("至<INPUT type='text' name='" + param.getColumn()+"_end' class='normal' style='"+style+"' "+maxLength+" xu.prop='"+prop + "' "+value+" onkeydown=\"if(window.event.keyCode == 13) doSubmit(this.form, this)\" />");
		  			if(jointColumn != null && !jointColumn.isEmpty() && jointChar != null && !jointChar.isEmpty()) {
			  			out.print(jointChar+"<INPUT type='text' name='" + jointColumn+"_end' class='normal' style='"+jointStyle+"' "+jointMaxLength+" onkeydown=\"if(window.event.keyCode == 13) doSubmit(this.form, this)\" />");
			  		}
		  		}
	 		}
	 		out.print("</TD>");
	 	}
	 	out.println("</TR>");
	}
}
if(!hasBtn) {
	out.println("<tr><td colspan=\"6\" height=\"25\" align=\"center\"><INPUT type='button' class='button' value='查 询' border=0 onclick='doSubmit(this.form, this)' /></td></tr>");
}
%>
				</table>
			</form>
  			</div>
  			<div id="ExcelArea"><OBJECT id="WebOffice" style="LEFT: 0px; TOP: 0px; WIDTH: 100%;" height="100%" classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5"  codebase="../../activex/weboffice_v6.0.5.0.cab#V6,0,5,0 ">
			<PARAM NAME="_ExtentX" VALUE="6350">
			<PARAM NAME="_ExtentY" VALUE="6350">
			</OBJECT></div>
  </body>
</html>
