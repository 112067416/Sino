<%-- 
    version		: 1.0
    Created on 	: 2010-10-25
    Author     	: YuanLong.F
    Description	: 采样记录维护-用于与SL和分析室之间的消息互通
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui" %>
<%@ taglib uri="/f" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
		<script type="text/javascript">
		<!--

		// 检索
		function doQuery() {
			//
		}
		
		// 新增分析单
		function doAdd() {
			coco.showPage("FxdXzPanel",{center:true,top:20,width:"60%"});
		}
		
		// 删除分析单--当SL发送样品后，该单不能删除
		function doDel() {
			
		}
		
		// 提交分析单-确认新增
		function doTj() {
			if(!confirm("确认提交吗？")) return ;
			doGb(); // 关闭
		}
		
		// 关闭弹出层
		function doGbTj() {
			coco.hidePage("FxdXzPanel");
		}
		
		// 查看分析单
		function doView() {
			coco.showPage("FxdCkPanel",{center:true,top:20,width:"80%"});
		}
		
		// 关闭采样单
		function doGbView() {
			coco.hidePage("FxdCkPanel");
		}
		
		//-->
		</script>
		
	</head>
	<body>
		<ui:page title="采样记录维护">
			<table width="96%" align="center" style="margin: 10px 0;" class="form">
				<colgroup>
					<col><col><col><col><col><col>
				</colgroup>
				<tr>
					<th>指示书NO</th>
					<td><ui:input name="" maxlength="6" /></td>
					<th>Coil No</th>
					<td><ui:input name="" maxlength="8" /></td>
					<th>单号</th>
					<td><ui:input name="" maxlength="8" /></td>
					<th>发行日期</th>
					<td><ui:date name="" showCalendar="true" /> 至 <ui:date name="rq" prop="calendar: true;" /></td>
				</tr>
			</table>
			<div class="submit">
				<input type="button" class="button" value="检索(Q)" accesskey="q" onclick="doQuery();" />
				<input type="button" class="button" value="新增分析单(A)" accesskey="d" onclick="doAdd();" />
				<input type="button" class="button" value="删除分析单(D)" accesskey="d" onclick="doDel();" />
			</div>
			
			<table id="DataTbl" width="96%" align="center" class="pagination" style="margin-top: 10px;">
				<colgroup>
					<col align="center"/><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col /><col align="center"/>
				</colgroup>
				<tr>
					<th><input type="checkbox" name="_allchk" onclick="coco.selAll('_chk', this)" /></th>
					<th>单号</th>
					<th>指示书号</th>
					<th>CoilNo</th>
					<th>分析项目</th>
					<th>采样位置</th>
					<th>发行日期</th>
					<th>SL收单日期</th>
					<th>SL送样日期</th>
					<th>分析室收单日期</th>
					<th>分析室收样日期</th>
					<th>分析室分析日期</th>
				</tr>
				<c:forEach items="1,2,3,4,5">
					<tr ondblclick="doView();" title="双击时查看分析单" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> onmouseover="overTr(this)" onmouseout="outTr(this)">
						<td><input type="checkbox" name="_chk" /></td>
						<td>单号</td>
						<td>指示书号</td>
						<td>CoilNo</td>
						<td>分析项目</td>
						<td>采样位置</td>
						<td>发行日期</td>
						<td>SL收单日期</td>
						<td>SL送样日期</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
			<table width="96%" align="center" style="color: #EEE; margin-top: 5px;">
				<tr>
					<th align="left"><span>第<font color="red">1</font>/<font color="red">1</font>页</span> <span style="margin: 0 15px;">共<font color="red">7</font>条   每页<font color="red">15</font>条</span></th>
					<th align="right"><span>【上页】【下页】【首页】【尾页】 转到<input name="" style="width: 2em; border: 0; border-bottom: 1px solid #999; text-align: center; background-color: transparent;" />页</span></th>
				</tr>
			</table>

			<ui:panel id="FxdXzPanel" title="新增分析行-TP" popup="true" display="false" closable="true">
				<table width="96%" align="center" class="form">
					<colgroup>
						<col width="15%" /><col width="35%" /><col width="15%" /><col>
					</colgroup>
					<tr>
						<th>班</th>
						<td><ui:select name="" list="'':'请选择','1':'1班','2':'2班','3':'3班'" /></td>
						<th>组</th>
						<td><ui:select name="" list="'':'请选择','A':'A组','B':'B组','C':'C组','D':'D组'" /></td>
					</tr>
					<tr>
						<th>指示连号</th>
						<td>N</td>
						<th>Coil No.</th>
						<td></td>
					</tr>
					<tr>
						<th>镀锡量</th>
						<td><ui:input name="" maxlength="7" /></td>
						<th>涂油量</th>
						<td><ui:input name="" maxlength="7" /></td>
					</tr>
					<tr>
						<th>分析项目</th>
						<td colspan="3">
							<ui:checklist name="" list="'1':'油','2':'Sn','3':'Cr'"/>
						</td>
					</tr>
					<tr>
						<th>采样位置</th>
						<td><ui:checklist name="" list="'T':'T','M':'M','B':'B'"/></td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3"><ui:textarea name="1" rows="4" cssStyle="width: 98%" /></td>
					</tr>
				</table>
				<div class="submit">
					<input type="button" class="button" value="提交(S)" accesskey="s" onclick="doTj();" />
					<input type="button" class="button" value="关闭(C)" accesskey="c" onclick="doGbTj();" />
				</div>
			</ui:panel>
			
			<ui:panel id="FxdCkPanel" title="查看分析行-TP" popup="true" display="false" closable="true">
				<table width="96%" align="center" class="form">
					<colgroup>
						<col width="20%" /><col width="30%" /><col width="15%" /><col>
					</colgroup>
					<tr>
						<th>单号</th>
						<td>FXD20101212001</td>
						<th>发行时间</th>
						<td>2010年12月12日</td>
					</tr>
					<tr>
						<th>指示连号</th>
						<td>N</td>
						<th>Coil No.</th>
						<td>N00001</td>
					</tr>
					<tr>
						<th>镀锡量</th>
						<td>5.6/5.6</td>
						<th>涂油量</th>
						<td>5.6/5.6</td>
					</tr>
					<tr>
						<th>分析项目</th>
						<td>油、Cr</td>
						<th>采样位置</th>
						<td>T</td>
					</tr>
					<tr>
						<th>发行班</th>
						<td>1班</td>
						<th>发行组</th>
						<td>A组</td>
					</tr>
					<tr>
						<th>SL接收日期</th>
						<td>2010年12月12日</td>
						<th>SL送样日期</th>
						<td>2010年12月12日</td>
					</tr>
					<tr>
						<th>分析室收单日期</th>
						<td>2010年12月12日</td>
						<th>分析室收样日期</th>
						<td>2010年12月12日</td>
					</tr>
					<tr>
						<th>分析室分析日期</th>
						<td>2010年12月12日</td>
					</tr>
					<tr>
						<th>ETL备注</th>
						<td colspan="3">ETL发单时所作的备注</td>
					</tr>
					<tr>
						<th>SL备注</th>
						<td colspan="3">SL发样品时所作的备注</td>
					</tr>
					<tr>
						<th>分析室备注</th>
						<td colspan="3">分析室分析结束时所作的备注</td>
					</tr>
				</table>
				<div class="submit">
					<input type="button" class="button" value="关闭(X)" accesskey="x" onclick="doGbView();" />
				</div>
				
			</ui:panel>
			

		<br />
		</ui:page>
		
	</body>
</html>