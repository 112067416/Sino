<%@ page language="java" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<SCRIPT type="text/javascript">
		//修改
		function View(ctrol) {
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				cocoform.fillResult("DataForm", this.result);
				coco.showPage("Detail",{center:true,top:50,width:"80%"});
			};
			if(ctrol.value==null){
			   alert("请输入装入卷板No");
		        return;
			}
			ajax.post("getPzlp!"+ctrol.value+".do", null, "POST");
		}
		</SCRIPT>
	</head>
	<body>
<ui:page title="ETL参数管理记录">
		<table width="98%" align="center" style="margin: 20px auto;" class="form">
		<tr>
					<th colspan="2">
						装入卷板No.
					</th>
					<td>
						<ui:input id="zrjb" name="zrjb" maxlength="11" title="输入母材号" onkeydown="if(event.keyCode != 13) return;View(this);" />
					</td>
					<td colspan="5">
						&nbsp;
					</td>
				</tr>
				</table>		
</ui:page>
		<ui:panel id="Detail" title="ETL品质管理记录日志信息" popup="true" display="false" closable="true" >
			<form name="DataForm" xu.ajax="" xu.r="" xu.s="success();" method="post" action="savePL.do">
			<table width="98%" align="center" style="margin: 20px auto;" class="form">
				<colgroup>
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="20%" />
				</colgroup>
				<tr>
					<th colspan="2">
						装入卷板No.
					</th>
					<td>
						<ui:input name="zrjb" maxlength="11" title="输入母材号" readonly="true"/>
					</td>
					<td colspan="5">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						整流器极性
					</th>
					<th>
						碱性
					</th>
					<td>
						<ui:input name="zljx" maxlength="2" />
					</td>
					<th>
						酸性
					</th>
					<td>
						<ui:input name="zlsx" maxlength="2" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						前处理电流
					</th>
					<th>
						碱性&nbsp;No.1
					</th>
					<td>
						<ui:int name="qcj1" maxlength="2" />
					</td>
					<th>
						No.2
					</th>
					<td>
						<ui:int name="qcj2" maxlength="2" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
					<th>
						酸性&nbsp;No.1
					</th>
					<td>
						<ui:int name="qcs1" maxlength="2" />
					</td>
					<th>
						No.2
					</th>
					<td>
						<ui:input name="qcs2" maxlength="2" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						镀金电流密度
					</th>
					<th>
						正面
					</th>
					<td>
						<ui:int name="djzm" maxlength="2" />
					</td>
					<th>
						反面
					</th>
					<td>
						<ui:int name="djfm" maxlength="3" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						REFLOW
					</th>
					<th>
						电压
					</th>
					<td>
						<ui:int name="rfdy" maxlength="3" />
					</td>
					<th>
						电流
					</th>
					<td>
						<ui:int name="rfdl" maxlength="3" />
					</td>
					<th>
						频率
					</th>
					<td>
						<ui:int name="rfpl" maxlength="3" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						CHEMICAL电量密度
					</th>
					<th>
						正面
					</th>
					<td>
						<ui:int name="chzm" maxlength="2" />
					</td>
					<th>
						反面
					</th>
					<td>
						<ui:int name="chfm" maxlength="2" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						涂油器条件
					</th>
					<th>
						比率
					</th>
					<td>
						<ui:int name="tybl" maxlength="3" />
					</td>
					<th>
						流量
					</th>
					<td>
						<ui:int name="tyll" maxlength="3" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						GAMA线板厚
					</th>
					<th>
						平均
					</th>
					<td>
						<ui:number name="gmpj" maxlength="5" scale="4" precision="3"  />
					</td>
					<th>
						最大
					</th>
					<td>
						<ui:number name="gmzd" maxlength="5" scale="4" precision="3" />
					</td>
					<th>
						最小
					</th>
					<td>
						<ui:number name="gmzx" maxlength="5" scale="4" precision="3" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						流水线速度
					</th>
					<td>
						&nbsp;
					</td>
					<td>
						<ui:int name="lnsd" maxlength="3" />
					</td>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>				
			</table>
		</form>
		</ui:panel>
	</body>
</html>