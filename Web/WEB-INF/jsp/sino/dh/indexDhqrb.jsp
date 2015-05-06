<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订货确认表打印</title>
		<%@include file="../../global/header.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		contextmenu.putMenus("menu", [["打印", "view.gif", "printSel()"]]);
		//选择打印订货确认表
		function printSel(oTr) {
			if(oTr == null) oTr = contextmenu.selectedTarget();
			var key = coco.getAttr(oTr, "id0");
			var content;
			if(key != null && key.length > 0) {
				content = coco.parseParam("ids", key);
			} else {
				content = cocoform.postCheckValues("PageForm_page","ids","ids");
			}
			if(content == null || content.length == 0){
				alert("请选择要打印的记录");
				return;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				var obj = null;
				eval("obj="+this.result+";");
				autoPrint(obj.ids);
			};
			ajax.post("checkDhqrbDy.do", content);
		}
		
		function doPrint(content, LODOP) {
			//调用打印控件进行套打,调试使用PrintView，套打使用PrintArea
			LODOP.PRINT_INIT("DHQRB-<%=System.currentTimeMillis()%>");
			LODOP.SET_PRINTER_INDEXA("DPK510");
			//像素 ：1380px ，958px
			//0.1毫米：3500, 2540
			//LODOP.SET_PRINT_PAGESIZE(1, "350mm", "254.0mm", "A3");
			LODOP.SET_PRINT_PAGESIZE(1, 3500, 2795,"A3");
			//LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.ADD_PRINT_HTM(0, 20, "1300px", "1010px", content);
		//	LODOP.ADD_PRINT_HTM(0, -15, "1300px", "880px", content);
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}
		
		function autoPrint(ids) {
			var arrs = ids.split(",");
			var id, index = 0, arr, $arr, pDhno, pDhnos = [];
			while(index < arrs.length) {
				id = arrs[index];
				index++;
				$arr = id.split("-");
				pDhno = $arr[0] + "-" + $arr[1];
				$arr[2] = ($arr[2] == null || $arr[2].length == 0 ? "" : $arr[2]);
				for(; index < arrs.length; index++) {
					arr = arrs[index].split("-");
					arr[2] = (arr[2] == null || arr[2].length == 0 ? "" : arr[2]);
					if(pDhno.indexOf(arr[0]) < 0 || pDhno.split(",").length >= 4 || $arr[2] != arr[2]) break;
					pDhno = pDhno + "," + arr[0] + "-" + arr[1];
				}
				pDhnos[pDhnos.length] = pDhno;
			}
			index = 0;
			var LODOP = document.getElementById("lodop");
			var postContent =  coco.parseParam("ids", pDhnos[index++]);
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				doPrint(this.result, LODOP);
				if(index < pDhnos.length) {
					postContent =  coco.parseParam("ids", pDhnos[index++]);
					ajax.post(path + "/sino/dy/dhqrb!print.do", postContent);
				}
			};
			ajax.post(path + "/sino/dy/dhqrb!print.do", postContent);
		}

		//全部打印
		function printAll() {
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				var obj = null;
				eval("obj="+this.result+";");
				autoPrint(obj.ids);
			};
			ajax.post("queryIds.do");
		}
		
		//取消打印
		function removePrint() {
			var content = cocoform.postCheckValues("PageForm_page","ids","ids");
			if(content == null || content.length == 0){
				alert("请选择要取消打印的记录");
				return;
			}
			if(!confirm("是否确定取消打印?")) return;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					alert(this.msg);
					return;
				}
				alert("取消打印成功");
				cocopage.refresh();
				return;
			};
			ajax.post("updateSfdy.do", content);
		}
		//-->
		</SCRIPT>
	</head>
	<body>
	<ui:print id="lodop"/>
	<ui:page title="订货确认表打印">
		<f:page action="indexDhqrb.do" file="listDhqrb.jsp">
			<table class="form" width="100%">
				<colgroup>
					<col width="10%" />
					<col width="30%" />
					<col width="10%" />
					<col width="30%" />
					<col width="10%" />
					<col width="10%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">订货No.</th>
					<td><ui:input name="dhno" maxlength="7" title="订货No." />&nbsp;-&nbsp;<ui:input name="line" title="订货No.行号" maxlength="2" /></td>
					<th style="text-align: left;">用户代码</th>
					<td><ui:input name="userBegin" maxlength="4"/>至<ui:input name="userEnd" maxlength="4"/></td>
				</tr>
				<tr>
					<th style="text-align: left;">交货期</th>
					<td><ui:date name="jhqiBegin" prop="calendar: true;" />至<ui:date name="jhqiEnd" prop="calendar: true;" /></td>
					<th style="text-align: left;">是否已打印</th>
					<td><ui:select name="sfdy" list="'N':'否','Y':'是'" onchange="cocopage.query();" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="查 询" onclick="cocopage.query()" />
				<input type="button" class="button" value="选择打印" onclick="printSel('');" />
				<input type="button" class="button" value="全部打印" onclick="printAll();" />
				<input type="button" class="button" value="取消打印" onclick="removePrint();" />
			</div>
		</f:page>
	</ui:page>
	</body>
</html>