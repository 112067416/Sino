<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" class="form">
<colgroup><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /><col width="10%" /><col width="15%" /></colgroup>
<tr>
<th>指示书No.</th>
<td>${zss.zsno}</td>
<th>操作方法</th>
<td><sys:codeLabel code="#SINO_CAOZ" key="zss.caoz"/></td>
<th>制品尺寸</th>
<td>${zss.zpcc}</td>
<th>用户略称</th>
<td >${zss.abbr}</td>
</tr>
<tr>
<th>装入宽</th>
<td>${zss.zrkn}</td>
<th>剪断长</th>
<td>${zss.jdcn}</td>
<th>运用规格</th>
<td>${zss.yuny}</td>
<th>M单重</th>
<td>${zss.mdan}</td>
</tr>
<tr>
<th>内径代码</th>
<td><sys:codeLabel key="zss.njno" code="#020" /></td>
<th>分配等级</th>
<td>${zss.fpdj}</td>
<th>锡付着量</th>
<td>${zss.fugm}</td>
<th>B单重</th>
<td>${zss.bdan}</td>
</tr>
<tr>
<th>剪断长(下限)</th>
<td>${zss.cxzi}</td>
<th>剪断长(上限)</th>
<td>${zss.cszi}</td>
<th>公差厚(下限)</th>
<td>${zss.mxzi}</td>
<th>公差厚(上限)</th>
<td>${zss.mszi}</td>
</tr>
<tr>
<th>硬度(下限)</th>
<td>${zss.ymin}</td>
<th>硬度(上限)</th>
<td>${zss.ymax}</td>
<th>公差宽(下限)</th>
<td>${zss.kxzi}</td>
<th>公差宽(上限)</th>
<td>${zss.kszi}</td>
</tr>
<tr>
<th>表面精加工</th>
<td>${zss.face}</td>
<th>交货区分</th>
<td>${zss.jhdd}</td>
<th>合同号-行号</th>
<td>${zss.dhno}</td>
<th>品种</th>
<td>${zss.pzno}</td>
</tr>
<tr>
<th>垫木方向</th>
<td>${zss.dmfx}</td>
<th>捆包形式</th>
<td>${zss.kbao}</td>				
<th>垫木个数</th>
<td>${zss.dmsz}</td>
<th>附着量</th>
<td>${zss.lotz}</td>
</tr>
<tr>
<th>包装张数</th>
<td>${zss.kbsz}</td>
<th>涂油量</th>
<td>${zss.ytyp }-${zss.yqty }</td>				
<th></th>
<td></td>
<th></th>
<td></td>
</tr>
<tr>
<th>附页</th>
<td colspan="7"><c:forEach varStatus="stat" var="kpno" items="${zss.kpns }"><span class="link1" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span></c:forEach></td>
</tr>
<tr>
<th>木工所业联</th>
<td colspan="7"><span class="link1" style="padding-left: 10px;" onclick="view('${zss.mgsn}')">${zss.mgsn}</span></td>
</tr>
</table>
<table id="TblA" width="96%" align="center" class="pagination" style="margin-top: 10px;">
<colgroup><col width="14%" /><col width="12%" /><col width="12%" /><col width="8%" /><col width="10%" /><col width="36%" /><col width="8%" /></colgroup>
<caption>剪切卷材列表</caption>
<tr>
<th>COIL No.</th>
<th>卷取长</th>
<th>订货No</th>
<th>品种</th>
<th>重量(Kg)</th>
<th>业务联络</th>
<th>生产状态</th>
</tr>
<c:forEach varStatus="stat" var="item" items="${items }">
<tr <c:if test="${stat.index%2 == 1 }">class="odd"</c:if>  >
<td>${item.zzsd}-${item.jbno}</td>
<td>${item.jbcn}</td>
<td>${item.dhno }</td>
<td>${item.pzno }</td>
<td>${item.zpzl }</td>
<td><c:forEach varStatus="stat" var="ylno" items="${item.ylnos }"><span class="link" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span></c:forEach></td>
<td>${item.stat}</td>
</tr>
</c:forEach>
</table>