<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="PrintArea"
	style="position: absolute; z-index: 2000;"></div>
<div id="PrintView" style="font-size:12px;">
<table width="1080" align="left" cellpadding="0" cellspacing="0"
	border="0" bordercolor="#000000" style="border-collapse: collapse; text-align: center;"  >
	<colgroup><col width="200" /><col width="600" /><col width="280" /></colgroup>
		<tr>
			<td>&nbsp;</td>
			<td rowspan="3"><span style="font-size: 35px;  font-weight: bold;">分 析 作 业 日 志</span></td>
			<td rowspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td align="left" style="padding-left: 10px;"><fmt:formatDate value="${entity.tbrq }" pattern="yyyy年MM月dd日"/></td>
		</tr>
		<tr>
			<td align="left" style="padding-left: 10px;">星期${entity.tbxq }</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0">
					<colgroup><col width="330" /><col width="50" /><col width="700" /></colgroup>
					<tr>
						<td>
							<table height="70" width="100%" border="1" bordercolor="#000000" class="form" style="border-collapse: collapse; text-align: center;">
								<colgroup><col width="25%" /><col width="25%" /><col width="25%" /><col width="25%" /></colgroup>
								<tr>
									<th rowspan="3">纯水</th>
									<th>时间</th>
									<td>${entity.cssj1 }</td>
									<td>${entity.cssj2 }</td>
							  	</tr>
								 <tr>
									<th>导电度</th>
									<td>${entity.ddd1 }</td>
									<td>${entity.ddd2 }</td>
								 </tr>
								 <tr>
									<th>PH</th>
									<td>${entity.ph1 }</td>
									<td>${entity.ph2 }</td>
								 </tr>
							</table>
						</td>
						<td>&nbsp;</td>
						<td>
							<table align="right" height="70" width="50%" border="1" bordercolor="#000000" cellpadding="0" cellspacing="0" style="border-collapse: collapse; text-align: center;">
								<tr>
									<td height="20">经理</td>
									<td>核对</td>
									<td>记录</td>
									<td>班别</td>
								</tr>
								<tr>
									<td height="50">&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>${entity.banzu }</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20" align="left" style="font: bold;font-size: larger;">作业实绩</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="3">
				<table height="100%" width="100%" border="0" style="text-align: justify;">
					<colgroup><col width="18%" /><col width="2%" /><col width="45%" /><col width="2%" /><col width="33%" /></colgroup>
					<tr>
						<td height="300" valign="top">
						   <table height="100%" width="100%" style="border-collapse: collapse; text-align: center;" border="1" bordercolor="#000000" class="form">
			           		<colgroup><col width="50%"/><col width="30%"/><col width="20%"/></colgroup>
							 <tr>
							   <th colspan="2">碱浸液</th>
							  
							   <td  >${entity.jjy }</td>
							 </tr>
							 <tr>
							   <th colspan="2">碱电解液</th>
							   <td>${entity.jdjy }</td>
							 </tr>
							  <tr>
							   <th width="25%" rowspan="2">酸电解液</th>
							   <th>H<sub>2</sub>SO<sub>4</sub></th>
							   <td>${entity.sdjyHso }</td>
							 </tr>
							  <tr>
							   <th>Fe</th>
							   <td>${entity.sdjyFe }</td>
							 </tr>
							  <tr>
							   <th rowspan="5">锡电镀液</th>
							   <th>Sn</th>
							   <td>${entity.xddySn}</td>
							 </tr>
							 <tr>
							   <th>Acid</th>
							   <td>${entity.xddyAcid }</td>
							 </tr>
							 <tr>
							   <th>ENSA</th>
							   <td>${entity.xddyEnsa }</td>
							 </tr>
							 <tr>
							   <th>Fe</th>
							   <td>${entity.xddyFe }</td>
							 </tr>
							 <tr>
							   <th>Sludge</th>
							   <td>${entity.xddySlud }</td>
							 </tr>
							  <tr>
							   <th rowspan="2">拖出液</th>
							   <th>Sn</th>
							   <td>${entity.tcySn }</td>
							 </tr>
							 <tr>
							   <th>Acid</th>
							   <td>${entity.tcyAcid }</td>
							 </tr>
							  <tr>
							   <th rowspan="3">化学处理液</th>
							   <th>Cr</th>
							   <td>${entity.hxclyCr }</td>
							 </tr>
							 <tr>
							   <th>PH</th>
							   <td>${entity.hxclyPh }</td>
							 </tr>
							 <tr>
							   <th>Sludge</th>
							   <td>${entity.hxclySlud }</td>
							 </tr>
					    </table>
						</td>
						<td>&nbsp;</td>
						<td valign="top">
							<table height="100%" width="100%" class="form" border="1" bordercolor="#000000" style="border-collapse: collapse; text-align: center;">
							 <colgroup><col width="20%"/><col width="20%"/><col width="20%"/><col width="20%"/><col width="20%"/></colgroup>
							 <tr>
							    <th>&nbsp;</th>
							    <th>昨日未完成</th>
							    <th>今日接收</th>
							    <th>今日完成</th>
							    <th>今日未完成</th>
							 </tr>
							  <tr>
							    <th>锡附着量</th>
							    <td>${entity.xfzlZr }</td>
							    <td>${entity.xfzlJrjs }</td>
							    <td>${entity.xfzlJrwc }</td>
							    <td>${entity.xfzlJrww }</td>
							 </tr>
							  <tr>
							    <th>铬附着量</th>
							    <td>${entity.gfzlZr }</td>
							    <td>${entity.gfzlJrjs }</td>
							    <td>${entity.gfzlJrwc }</td>
							    <td>${entity.gfzlJrww }</td>
							 </tr>
							  <tr>
							    <th>涂油量</th>
							    <td>${entity.tylZr }</td>
							    <td>${entity.tylJrjs }</td>
							    <td>${entity.tylJrwc }</td>
							    <td>${entity.tylJrww }</td>
							 </tr>
							  <tr>
							    <th>表面六价铬</th>
							    <td>${entity.bmljgZr }</td>
							    <td>${entity.bmljgJrjs }</td>
							    <td>${entity.bmljgJrwc }</td>
							    <td>${entity.bmljgJrww }</td>
							 </tr>
							  <tr>
							    <th>ISV</th>
							    <td>${entity.isvZr }</td>
							    <td>${entity.isvJrjs }</td>
							    <td>${entity.isvJrwc }</td>
							    <td>${entity.isvJrww }</td>
							 </tr>
							  <tr>
							    <th>TCS</th>
							    <td>${entity.tcsZr}</td>
							    <td>${entity.tcsJrjs }</td>
							    <td>${entity.tcsJrwc }</td>
							    <td>${entity.tcsJrww }</td>
							 </tr>
							  <tr>
							    <th>ATC</th>
							    <td>${entity.atcZr }</td>
							    <td>${entity.atcJrjs }</td>
							    <td>${entity.atcJrwc }</td>
							    <td>${entity.atcJrww }</td>
							 </tr>
							  <tr>
							    <th>TCV</th>
							    <td>${entity.tcvZr }</td>
							    <td>${entity.tcvJrjs }</td>
							    <td>${entity.tcvJrwc }</td>
							    <td>${entity.tcvJrww }</td>
							 </tr>
							  <tr>
							    <th>PLT</th>
							    <td>${entity.pltZr }</td>
							    <td>${entity.pltJrjs }</td>
							    <td>${entity.pltJrwc }</td>
							    <td>${entity.pltJrww }</td>
							 </tr>
							  <tr>
							    <th>涂料密着性</th>
							    <td>${entity.tlmzxZr }</td>
							    <td>${entity.tlmzxJrjs }</td>
							    <td>${entity.tlmzxJrwc }</td>
							    <td>${entity.tlmzxJrww }</td>
							 </tr>
							  <tr>
							    <th>Epon Flow</th>
							    <td>${entity.epflZr }</td>
							    <td>${entity.epflJrjs }</td>
							    <td>${entity.epflJrwc }</td>
							    <td>${entity.epflJrww }</td>
							 </tr>
							  <tr>
							    <th>Smudge</th>
							    <td>${entity.smudZr }</td>
							    <td>${entity.smudJrjs }</td>
							    <td>${entity.smudJrwc }</td>
							    <td>${entity.smudJrww }</td>
							 </tr>
							</table>
						</td>
						<td>&nbsp;</td>
						<td valign="top">
							<table height="100%" width="100%" class="form"  border="1" bordercolor="#000000" style="border-collapse: collapse; text-align: center; ">
								<colgroup><col width="100%"/></colgroup>
								<tr height="20"><td>其他</td></tr>
					            <tr><td height="280" valign="top" align="left"><div><pre>${qt }</pre></div></td></tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td height="15" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="1" bordercolor="#000000" style="border-collapse: collapse;">
			<colgroup><col width="3%" /><col width="97%" /></colgroup>
			<tr>
				<th height="180">特别事项</th>
				<td valign="top" align="left"><pre>${tbsx }</pre></td>
			</tr>
		    <tr>
		       <th height="20" colspan="2" align="left">异常事项&nbsp;&nbsp;&nbsp;&nbsp;${ycsx }</th>
		     </tr>
		    </table>
		    </td>
		</tr>
</table>
</div>
