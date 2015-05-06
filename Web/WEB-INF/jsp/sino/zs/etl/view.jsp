<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" class="form">
	<colgroup>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col />
</colgroup>
<tr>
	<th>指示书No.</th>
	<td>${item.zsno}</td>
	<th>操作方法</th>
	<td><f:kv value="item.caoz" list="'C':'卷材','S':'板材'" /></td>
	<th>制品尺寸</th>
	<td>${item.zpcc}</td>
	<th>用户略称</th>
	<td >${item.abbr}</td>
	</tr>
</table>
<table width="100%" class="form">
	<colgroup>
	<col />
	<col />
	<col />
	<col />
	<col />
	<col />
	<col />
	<col />
</colgroup>
<tr>
	<th>装入宽</th>
	<td>${item.zrkn}</td>
	<th>剪断长</th>
	<td>${item.jdcn}</td>
	<th>运用规格</th>
	<td>${item.yuny}</td>
	<th>M单重</th>
	<td>${item.mdan}</td>
</tr>
<tr>
	<th>内径代码</th>
	<td><sys:codeLabel key="item.njno" code="#020"/></td>
	<th>分配等级</th>
	<td>${item.fpdj}</td>
	<th>锡付着量</th>
	<td>${item.fugm}</td>
	<th>B单重</th>
	<td>${item.bdan}</td>
</tr>
<tr>
	<th>附着量-正(下限)</th>
	<td>${item.fuzx}</td>
	<th>附着量-正(上限)</th>
	<td>${item.fuzs}</td>
	<th>附着量-反(下限)</th>
	<td>${item.fufx}</td>
	<th>附着量-反(上限)</th>
	<td>${item.fufs}</td>
</tr>
<tr>
	<th>表面精加工</th>
	<td>${item.face}</td>
	<th>交货区分</th>
	<td>${item.jhdd}</td>				
	<th>合同号-行号</th>
	<td>${item.dhno}</td>
	<th>品种</th>
	<td >${item.pzno}</td>
</tr>
<tr>
	<th>卷重下限</th>
	<td>${item.kbzx}</td>
	<th>卷重上限</th>
	<td>${item.kbzs}</td>				
	<th>涂油量</th>
	<td>${item.ytyp}&nbsp;${item.yqty} </td>
	<th>纸圈</th>
	<td >${item.njbh}</td>
</tr>
<tr>
	<th>垫木方向</th>
	<td>${item.dmfx}</td>
	<th>捆包形式</th>
	<td>${item.kbao}</td>				
	<th>垫木个数</th>
	<td>${item.dmsz}</td>
	<th>附着量</th>
	<td>${item.lotz}</td>
</tr>
<tr>
	<th>附页</th>
	<td colspan="6"><c:forEach varStatus="stat" var="kpno" items="${item.kpns }"><span class="link1" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span></c:forEach></td>
	</tr>
	<th>木工所业联</th>
	<td colspan="6"><span class="link1" style="padding-left: 10px;" onclick="view('${item.mgsn}')">${item.mgsn}</span></td>
	</tr>
	<tr>
	<th>备注1</th>
	<td colspan="6">${item.bz1}</td>
	</tr>
	<th>备注2</th>
	<td colspan="6">${item.bz3}</td>
	</tr>
</table>
<!-- 卷材列表 -->
<table id="TblA" width="96%" align="center" class="pagination" style="margin-top: 10px;">
<caption>原板卷材列表</caption>
<tr>
	<th>COIL No.</th>
	<th>订货No</th>
	<th>品种</th>			
	<th>重量(千克)</th>	
	<th>业务联络</th>
	<th>生产状态</th>
</tr>
<c:forEach varStatus="stat" var="ycaitp" items="${items }">
	<tr xu.id=""  xu.menu="menu"  <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> >
		<td>${ycaitp.zzsd}-${ycaitp.jbno}</td>
		<td>${ycaitp.dhno }</td>
		<td>${ycaitp.pzno }</td>
		<td>${ycaitp.zpzl }</td>
		<td><c:forEach varStatus="stat" var="ylno" items="${ycaitp.ylns }"><span class="link" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span></c:forEach></td>
		<td><f:kv value="ycaitp.stat" list="'0':'清单导入','1':'已入库','2':'已分配','3':'实绩录入','4':'实绩终了','5':'实绩中止'" /></td>
	</tr>
 </c:forEach>
</table>