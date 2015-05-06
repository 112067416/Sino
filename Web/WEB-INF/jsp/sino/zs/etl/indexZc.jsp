<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
	taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript">
		<!--
		//指示书生成并打印
		function doZssZc(isPrint) {
			var form = document.forms["PageForm_page"];
			var content = cocoform.postCheckValues(form, "ids");
			if(content == null) {
				alert("没有数据行");
				return;
			}
			if(content =="") {
				alert("请选择数据行");
				return;
			}
			if(!confirm("确定作成指示书吗？"))return false;
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0){
					coco.alert(this.msg);
					return;
				}
				
				var zsnos= new Array(); 
			    zsnos=this.result.split('|');
				if(zsnos.length>1){
					if(zsnos[1]!=""){
						alert("作成指示书成功！作业停止的卷板号是："+zsnos[1]);
					}
					else{
						alert("作成指示书成功！");
					}
					
				}else{
					alert("作成指示书成功！");
				}
				if(isPrint){
					document.getElementById("PrintFrame").src = "../../dy/zss!1.do?zsno="+zsnos[0];
				}
				cocopage.query();
			};
			ajax.post("zc.do", content + "&isPrint=" + isPrint);
		} 
		//-->
		</script>
	</head>
<body>
<ui:page title="制作指示书">	
<f:page action="indexZc.do" file="listZc.jsp">
<table width="100%">
<tr><td>
<div class="submit">
<input type="button" class="button" onclick="cocopage.query()" value="刷新列表" >
<input type="button" class="button" onclick="doZssZc(false)" value="指示书作成" >
<input type="button" class="button" onclick="doZssZc(true)" value="指示书作成并打印" >
</div>
</td></tr>
</table>
<table class="pagination" border="0" cellpadding="0" cellspacing="0" id="tab1">
<colgroup>
<col width="35"/>
<col width="100"/>
<col width="80"/>
<col width="70"/>
<col width="60"/>
<col width="80"/>
<col width="110"/>
<col width="110"/>
<col width="110"/>
<col width="60"/>
<col width="60"/>
<col width="110"/>
<col width="50"/>
</colgroup>
<thead>	
<tr>
<th><input type="checkbox" xu.target="ids" onclick="coco.selAll('ids',this)" checked="checked"/></th>
<th>COIL No.</th>
<th>订货No</th>
<th>分配NO</th>
<th>等级</th>
<th>运用规格</th>
<th>现品尺寸</th>
<th>用户略称</th>
<th>制品重量</th>
<th>表面</th>
<th>堆场</th>
<th>分配时间</th>
<th>停止</th>
</tr>
</thead>
</table>
</f:page>
</ui:page>	
<div id="PrintDiv" style="background-color: #FFFFFF;color: #000000; width: 1480px;height: 800px;display: none;"></div>
<iframe id="PrintFrame" name="PrintFrame" src="about:blank" width="0" height="0" ></iframe>
</body>
</html>