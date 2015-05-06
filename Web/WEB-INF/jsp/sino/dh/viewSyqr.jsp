<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib uri="/sys" prefix="sys"%><%@taglib 
prefix="sino" uri="/sino" %><%@ taglib uri="/f" prefix="f"%>
		<form action="#" name="DataForm" method="post" >
		<table width="96%" class="form">
		<colgroup>
			<col width="10%" />
			<col width="25%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="25%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th>担当</th>
			<td style="font-size: 14px;"><ui:input name="kpn2" cssClass="normal" title="订货DB的担当者代码" maxlength="6" /></td>
			<th>订货NO.</th>
			<td><ui:input name="kpn2" cssClass="normal" title="区分为\' 1\'时，自动取番；其它，订货DB的订货NO,行号" maxlength="7" />-<ui:input name="kpn2" cssClass="normal" title="行号" maxlength="2" onblur="doLand()" /></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		</table>
		<div id="DivContent">
			<hr style="border: 1px dashed #338899;">
			<table width="96%" class="form">
				<colgroup>
					<col width="5%" /><col width="6%" /><col width="5%" /><col width="5%" /><col width="5%" /><col width="15%" />
					<col width="15%" /><col width="8%" /><col width="5%" /><col width="8%" /><col width="23%" />
				</colgroup>
				<tr>
					<th style="text-align: left;">规格</th>
					<td><ui:input name="kpn2" cssClass="normal" title="订货DB的规格代码" maxlength="4" /></td>
					<th style="text-align: left;">品种</th>
					<td colspan="2"><ui:int name="kpn2" cssClass="normal" title="订货DB的品种代码" maxlength="2" /></td>
					<th style="text-align: left;">附着量<span style="font-size: 12px;">(单位.正面.反面)</span></th>
					<td>
						<ui:input name="kpn2" cssClass="normal" title="订货DB的附着量单位（GM 或  WB）" maxlength="2" />
						&nbsp;<ui:number name="kpn2" cssClass="normal" title="订货DB的附着量正面" maxlength="3" />
						&nbsp;<ui:number name="kpn2" cssClass="normal" title="订货DB的附着量反面" maxlength="3" />
					</td>
					<th style="text-align: left;">差厚</th>
					<td><ui:input name="kpn2" cssClass="normal" title="订货DB的差厚镀锡（D1 或  D2  或 A1 或 A2）。当附着量的正面和反面的值不一致时，必须输入。" maxlength="2" /></td>
					<th style="text-align: left;">内外</th>
					<td><sys:codeBox name="nwx" width="70" code="#NWX" txt="2"/></td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: left;">尺寸<span style="font-size: 12px;">(厚<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">宽<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">剪切板长)</span></th>
					<td colspan="9">
						<ui:number name="kpn2" cssClass="normal" title="订货DB的订货尺寸.厚" maxlength="5" />
					</td>
				</tr>
				<tr>
					<th style="text-align: left;">用途</th>
					<td><ui:input name="kpn2" cssClass="normal" title="订货DB的加工用途条件" maxlength="4" /></td>
					<th colspan="2" style="text-align: left;">用户代码</th>
					<td colspan="2"><sino:yongBox name="yong" width="60" txt="1" onchange="getKhjc();" /></td>
					<th colspan="5"><span id="kjkc"></span></th>
				</tr>
			</table>
			<hr style="border: 1px dashed #338899;">
			<table width="96%" class="form">
				<colgroup>
					<col width="6%" /><col width="5%" /><col width="6%" /><col width="5%" /><col width="5%" /><col width="8%" /><col width="10%" />
					<col width="6%" /><col width="5%" /><col width="10%" /><col width="5%" /><col width="6%" /><col width="23%">
				</colgroup>
				<tr>
				<th style="text-align: left;">地点</th>
				<td colspan="5"><ui:select name="nwai" list="'':'请选择'"  /></td>
				<th style="text-align: right;">商社</th>
				<td colspan="6"><ui:input name="ssno" cssClass="normal" title="商社" maxlength="4" /></td>
				</tr>
				<tr>
				<th style="text-align: left;">日期</th> 
				<td colspan="2"><ui:date name="date" /></td>
				<th style="text-align: right;">重内</th>
				<td><ui:int name="zn" cssClass="normal" title="订货DB的交货重量内容（1 或 2 或  3 或  6 或  7 或  8）" maxlength="1" /></td>
				<th style="text-align: left;">合同重量</th>
				<td><ui:number name="hj" cssClass="normal" title="订货DB的合同重量" maxlength="8" /></td>
				<th style="text-align: left;">交货允许</th>
				<td colspan="2"><ui:input name="cxzf" cssClass="normal" title="订货DB的交货允许.区分（W或T）" maxlength="1" />&nbsp;&nbsp;<ui:input name="cszf" cssClass="normal" title="交货允许.+值" maxlength="3" />&nbsp;&nbsp;<ui:input name="cszf" cssClass="normal" title="交货允许.-值（货允许.-值小于交货允许.+ 值）" maxlength="3" /></td>
				<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
				<th style="text-align: left;">单包张数</th>
				<td colspan="3"><ui:input name="zm" cssClass="normal" title="订货DB的捆包指定张数.区分" maxlength="1" />&nbsp;<ui:int name="njbh" cssClass="normal" title="捆包指定张数.值" maxlength="4" /></td>
				<th style="text-align: right;">卷重</th>
				<td colspan="2"><ui:input name="zz" cssClass="normal" title="订货DB的捆包指定重量.区分(5或6或8)" maxlength="1" />&nbsp;<ui:input name="njbh" cssClass="normal" title="捆包指定重量.单位（T）" maxlength="1" />&nbsp;<ui:input name="njbh" cssClass="normal" title="捆包指定重量.上限" maxlength="6" />&nbsp;<ui:input name="njbh" cssClass="normal" title="捆包指定重量.下限" maxlength="6" /></td>
				<th style="text-align: left;">捆包计算</th>
				<td colspan="2"><ui:input name="kbjs" cssClass="normal" title="1：捆包形式由系统计算；其余：捆包形式非系统计算" maxlength="1" /></td>
				<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr>
				<th style="text-align: left;">化成</th>
				<td><ui:int name="prot" cssClass="normal" title="订货DB的化成处理(311或300)" maxlength="3" /></td>
				<th style="text-align: left;">表面</th>
				<td colspan="3"><ui:input name="bm" cssClass="normal" title="订货DB的表面精加工" maxlength="2" /></td>
				<th style="text-align: right;">压延</th>
				<td><ui:int name="yy" cssClass="normal" title="制造仕样主文件的压延方向指定标记（空或1或2）" maxlength="1" /></td>
				<th style="text-align: left;">垫木</th>
				<td><ui:input name="dm" cssClass="normal" title="造仕样主文件的垫木方向（1或L或C）" maxlength="1" /></td>
				<th style="text-align: left;">捆包形式</th>
				<td colspan="2"><ui:input name="kb" cssClass="normal" title="系统根据制造仕样和订货信息进行计算的计算结果" maxlength="3" /></td>
				</tr>
				<tr>
				<th style="text-align: left;">货标</th>
				<td><ui:input name="hb" cssClass="normal" title="订货DB的货标标记（空或1）" maxlength="1" /></td>
				<th style="text-align: left;">外W</th>
				<td><ui:input name="ww" cssClass="normal" title="订货DB的销售溶接部不可" maxlength="1" /></td>
				<th style="text-align: right;">内径</th>
				<td><ui:int name="j1" cssClass="normal" title="订货DB的内径代码（1或3）" maxlength="3" /></td>
				<th style="text-align: right;">B</th>
				<td><ui:input name="b" cssClass="normal" title="订货DB的特殊BNO" maxlength="1" /></td>
				<th style="text-align: left;">C</th>
				<td><ui:input name="c" cssClass="normal" title="订货DB的特殊CNO" maxlength="1" /></td>
				<th style="text-align: left;">需L</th>
				<td><ui:input name="xl" cssClass="normal" title="订货DB的用户标签名表示（空或1）" maxlength="1" /></td>
				<td>&nbsp;</td>
				</tr>
			</table>
			<hr style="border: 1px dashed #338899;">
		</div>
		<table id="SyTbl" width="96%" class="form" style="display: none;" >
			<colgroup>
			<col width="10%" />
			<col width="5%" />
			<col width="10%" />
			<col width="8%" />
			<col width="12%" />
			<col width="55%" />
			</colgroup>
			<tr>
			<th style="text-align: left;">剪边</th>
			<td><ui:input name="jian" maxlength="1" /> </td>
			<th style="text-align: left;">零头下限</th>
			<td><ui:input name="ltdw" title="单位" maxlength="1" />&nbsp;&nbsp;<ui:input name="ltzi" title="值" maxlength="6" cssStyle="text-align:right;" /> </td>
			<th style="text-align: left;">硬度上下限</th>
			<td><ui:input name="ymin" maxlength="2" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="ymax" maxlength="2" /></td>
			</tr>
			<tr>
			<th style="text-align: left;">涂油种类</th>
			<td><ui:input name="ytyp" maxlength="1" /></td>
			<th style="text-align: left;">KPLATE</th>
			<td><ui:input name="plat" maxlength="1" /></td>
			<th style="text-align: left;">附着量 正</th>
			<td><ui:input name="fuzx" maxlength="6" cssStyle="width:40px;text-align:right;" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:input name="fuzs" maxlength="6" cssStyle="width:40px;text-align:right;" /></td>
			</tr>
			<tr>
			<th style="text-align: left;">涂油量</th>
			<td><ui:input name="yqty" maxlength="2" /></td>
			<th style="text-align: left;">合金层</th>
			<td><ui:input name="hjin" maxlength="2" /></td>
			<th style="text-align: left;">上下限 反</th>
			<td><ui:input name="fufx" maxlength="6" cssStyle="width:40px;text-align:right;" />&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<ui:input name="fufs" maxlength="6" cssStyle="width:40px;text-align:right;" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">附页KPNo</th>
				<td colspan="5"><ui:input name="bs" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" />-<ui:input name="kpn1" maxlength="6" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="bs" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" />-<ui:input name="kpn1" maxlength="6" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="bs" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" />-<ui:input name="kpn1" maxlength="6" title="最多只能输入四个KPNO" />&nbsp;&nbsp;<ui:input name="bs" maxlength="1" title="标识位。1：ETL;2：SL;3;两者" />-<ui:input name="kpn1" maxlength="6" title="最多只能输入四个KPNO" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">木工所No</th>
				<td colspan="5"><ui:input name="mgsn" maxlength="35" /></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注1</th>
				<td colspan="5"><ui:textarea name="bz1" cssStyle="" cssClass="normal" cols="100" rows="" title="ETL和木工所指示书的备注内容"/></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注2</th>
				<td colspan="5"><ui:textarea name="bz2" cssStyle="" cssClass="normal" cols="100" rows="" title="SL指示书的备注内容"/></td>
			</tr>
			<tr>
				<th style="text-align: left;">备注3</th>
				<td colspan="5"><ui:textarea name="bz3" cssStyle="" cssClass="normal" cols="100" rows="" title="ETL、木工所和SL指示书的备注内容"/></td>
			</tr>
			<tr>
			<th style="text-align: left;">分配等级</th>
			<td><ui:input name="fpdj" maxlength="3" /></td>
			<td colspan="2">&nbsp;</td>
			<th style="text-align: left;">公差厚(%)</th>
			<td><ui:input name="hxzf" maxlength="6" cssStyle="width:40px;text-align:right;" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="hszf" maxlength="6" cssStyle="width:40px;text-align:right;" /></td>
			</tr>
			<tr>
			<th style="text-align: left;">直角度</th>
			<td><ui:input name="jiao" maxlength="4" cssStyle="width:30px;text-align:right;" /></td>
			<th style="text-align: left;">&nbsp;</th>
			<th style="text-align: center;">&nbsp;</th>
			<th style="text-align: left;">公差宽(mm)</th>
			<td><ui:input name="kxzf" maxlength="6" cssStyle="width:40px;text-align:right;" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="kszf" maxlength="6" cssStyle="width:40px;text-align:right;" /></td>
			</tr>
			<tr>
			<th style="text-align: left;">内径保护筒</th>
			<td><ui:input name="njbh" maxlength="1" /></td>
			<th style="text-align: left;">运用规格</th>
			<td><ui:input name="yuny" maxlength="6" /></td>
			<th style="text-align: left;">公差长(mm)</th>
			<td><ui:input name="cxzf" maxlength="6" cssStyle="width:40px;text-align:right;" />&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<ui:input name="cszf" maxlength="6" cssStyle="width:40px;text-align:right;" /></td>
			</tr>
			<tr>
			<th style="text-align: left;">保护板</th>
			<td><ui:input name="prot" maxlength="1" /></td>
			<th style="text-align: left;">装入宽</th>
			<td><ui:input name="zrkn" maxlength="9" cssStyle="text-align:right;" /></td>
			<th style="text-align: left;">波剪直剪</th>
			<td><ui:input name="jcxs" maxlength="4" /></td>
			</tr>
		</table>
		<br />
		<div align="center"><input type="button" class="button" value="关 闭" onclick="coco.hidePage('Detail')" />&nbsp;&nbsp;<input type="button" value="保 存" onclick="" class="button" />&nbsp;&nbsp;<input type="reset" value="确 认" class="button" />&nbsp;&nbsp;<input type="reset" value="重 置" class="button" /></div>
	</form>