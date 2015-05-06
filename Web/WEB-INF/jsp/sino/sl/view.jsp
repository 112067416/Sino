<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ 
taglib uri="/ui" prefix="ui"%><%@ 
taglib uri="/f" prefix="f"%><%@ 
taglib uri="/sys" prefix="sys"%><%@ 
taglib uri="/sino" prefix="sino"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ui:panel id="ViewPanel" title="SL实绩信息查看" display="false" popup="true" closable="true" >
	<table width="96%" align="center" class="form">
		<colgroup>
			<col width="15%" /><col width="20%" /><col width="15%" /><col />
		</colgroup>
		<tr>
			<th>生产线</th>
			<td><f:v value="zp.slin" format="select name from Scxbpz where code=?" /></td>
			<th>班</th>
			<td><ui:input name="ban" maxlength="2" value="zp.ban" readonly="true" /></td>
			<th>组</th>
			<td><ui:input name="zu" maxlength="2" value="zp.zu" readonly="true" /></td>
		</tr>
	</table>
	
	<fieldset class="group">
		<legend>制品信息</legend>
		<table width="96%" align="center" class="form" height="40px">
			<colgroup>
				<col width="15%" /><col width="15%" />
				<col width="15%" /><col width="30%" />
				<col width="10%" /><col />
			</colgroup>
			<tr>
				<th>PILE No.</th>
				<td><ui:input name="jbno" readonly="true" maxlength="11" value="zp.jbno" title="实绩后的板材制品号" /></td>
				<th>制品尺寸</th>
				<td>
					<ui:number name="houu" maxlength="5" readonly="true" value="zp.houu" title="制品尺寸•厚"/>*
					<ui:number name="kuan" maxlength="7" readonly="true" value="zp.kuan" title="制品尺寸•宽"/>*
					<ui:number name="cang" maxlength="7" readonly="true" value="zp.cang" title="制品尺寸•长"/>
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
		<table id="PileTbl" width="96%" align="center" class="form">
			<colgroup>
				<col /><col /><col /><col /><col /><col /><col /><col />
			</colgroup>
			<tr>
				<th>计算重量</th>
				<td><ui:int name="jszl" readonly="true" maxlength="5" value="zp.jszl" /></td>
				<th>实际重量</th>
				<td colspan="5"><ui:int name="sjzl" maxlength="5" readonly="true" value="zp.sjzl" /></td>
			</tr>
			<tr>
				<th>产量代码</th>
				<td><ui:input name="chan" maxlength="1" readonly="true" value="zp.chan" /></td>
				<th>等级</th>
				<td><ui:input name="deng" maxlength="3" readonly="true" value="zp.deng" /></td>
				<th>处置</th>
				<td><ui:input name="czdm" maxlength="1" readonly="true" value="zp.czdm" /></td>
				<th>主缺陷</th>
				<td><ui:input name="qqdm" maxlength="2" readonly="true" value="zp.qqdm" /></td>
			</tr>
			<tr>
				<th>缺陷2</th>
				<td><ui:input name="qqd2" maxlength="2" readonly="true" value="zp.qqd2" /></td>
				<th>缺陷3</th>
				<td><ui:input name="qqd3" maxlength="2" readonly="true" value="zp.qqd3" /></td>
				<th>检定员</th>
				<td><ui:input name="jdyn" maxlength="2" readonly="true" value="zp.jdyn" /></td>
				<th>计数员</th>
				<td><ui:input name="jsyn" maxlength="2" readonly="true" value="zp.jsyn" /></td>
			</tr>
			<tr>
				<th>枚数</th>
				<td><ui:int name="zshu" maxlength="4" readonly="true" value="zp.zshu" /></td>
				<th>D(A)-MARK</th>
				<td><ui:input name="dmrk" maxlength="1" readonly="true" value="zp.dmrk" /></td>
				<th>PILER</th>
				<td><ui:input name="cqpl" maxlength="1" readonly="true" value="zp.cqpl" /></td>
				<th>特记</th>
				<td><ui:input name="vari" maxlength="10" readonly="true" value="zp.vari" /></td>
			</tr>
			<tr>
				<th>实测宽</th>
				<td><ui:number name="sckn" maxlength="7" readonly="true" value="zp.sckn" /></td>
				<th>实测剪断长</th>
				<td><ui:number name="jdcn" maxlength="7" readonly="true" value="zp.jdcn" /></td>
				<th>LINE-SPEED</th>
				<td><ui:int name="lnsd" maxlength="3" readonly="true" value="zp.lnsd" /></td>
				<th>长分布</th>
				<td>
					<ui:int name="cm05" maxlength="1" title="-0.5" readonly="true" value="zp.cm05" /> &nbsp;
					<ui:int name="cp00" maxlength="1" title="0" readonly="true" value="zp.cp00" /> &nbsp;
					<ui:int name="cp05" maxlength="1" title="0.5" readonly="true" value="zp.cp05" /> &nbsp;
					<ui:int name="cp10" maxlength="1" title="1" readonly="true" value="zp.cp10" /> &nbsp;
					<ui:int name="cp15" maxlength="1" title="1.5" readonly="true" value="zp.cp15" />
				</td>
			</tr>
			<tr>
				<th>耳波Op</th>
				<td><ui:number name="bopg" maxlength="3" readonly="true" value="zp.bopg" /> - <ui:number name="bopj"
					maxlength="3" readonly="true" value="zp.bopj" /></td>
				<th>耳波Dr</th>
				<td><ui:number name="bdrg" maxlength="3" readonly="true" value="zp.bdrg" /> - <ui:number name="bdrj"
					maxlength="3" readonly="true" value="zp.bdrj" /></td>
				<th>耳波等级</th>
				<td colspan="3"><ui:input name="bdji" maxlength="1" readonly="true" value="zp.bdji" /></td>
			</tr>
			<tr>
				<th>直角Op</th>
				<td> <ui:number name="zopz"
					maxlength="4" readonly="true" value="zp.zopz" /></td>
				<th>直角Dr</th>
				<td> <ui:number name="zdrz"
					maxlength="4" readonly="true" value="zp.zdrz" /></td>
				<th>L反</th>
				<td><ui:number name="zndz" maxlength="3" readonly="true" value="zp.zndz" /></td>
				<th>C反</th>
				<td><ui:number name="hndz" maxlength="3" readonly="true" value="zp.hndz" /></td>
			</tr>
			<tr>
				<th>中伸</th>
				<td><ui:number name="zbog" maxlength="3" readonly="true" value="zp.zbog" /> - <ui:number name="zboj"
					maxlength="3" readonly="true" value="zp.zboj" /></td>
				<th>翘度</th>
				<td><ui:input name="qduz" maxlength="3" readonly="true" value="zp.qduz" /></td>
				<th>毛边上</th>
				<td><ui:input name="maos" maxlength="5" readonly="true" value="zp.maos" title="毛边上" /></td>
				<th>毛边下</th>
				<td><ui:input name="maox" maxlength="5" readonly="true" value="zp.maox" title="毛边下" /></td>
			</tr>
			<tr>
				<th>矢切量</th>
				<td><ui:input name="siql" maxlength="5" readonly="true" value="zp.siql" title="矢切量" /></td>
				<th>镜面检查</th>
				<td><ui:select name="jmjc" list="'':'不合格','1':'合格'" value="zp.jmjc" readonly="true"/></td>
				<th>垫足确认</th>
				<td><ui:select name="dmqr" list="'':'未确认','1':'已确认'" value="zp.dmqr" readonly="true" /></td>
				<th>针孔确认</th>
				<td><ui:select name="zkqr" list="'':'不合格','1':'合格'" value="zp.zkqr" readonly="true" /></td>
			</tr>
		</table>
	</fieldset>
	<div class="submit">
		<input type="button" class="button" value="关闭(C)" accesskey="c" onclick="coco.hidePage('ViewPanel');" />
	</div>
</ui:panel>