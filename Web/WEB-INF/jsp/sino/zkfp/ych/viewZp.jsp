<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table border="0" class="form" width="98%" align="center">
<colgroup>
<col width="13%" /><col width="20%" /><col width="13%" /><col width="20%" /><col width="13%" /><col width="21%" />
</colgroup>
<tr>
<th>订货NO.</th>
<td>${zpngTp.dhno }</td>
<th>制品状态</th>
<td colspan="3">${zpngTp.stat }</td>
</tr>
<tr>
<th>是否终了</th>
<td>&nbsp;</td>
<th>是否已出货</th>
<td>&nbsp;</td>
<th>装箱指示书</th>
<td>${zpngTp.chno }</td>
</tr>
<tr>
<th>制品尺寸</th>
<td>${zpngTp.houu }*${zpngTp.kuan }*${zpngTp.cang }</td>
<th>现品尺寸</th>
<td>${zpngTp.xpho }*${zpngTp.xpkn }*${zpngTp.xpcn }</td>
<th>制品重量</th>
<td>${zpngTp.zpzl }</td>
</tr>
<tr>
<th>等级</th>
<td>${zpngTp.deng }</td>
<th>产量代码</th>
<td>${zpngTp.chan }</td>
<th>垫木重量</th>
<td>${zpngTp.dmzl }</td>
</tr>
<tr>
<th>制造商代码</th>
<td>${zpngTp.zzsd }</td>
<th>包装材料重量</th>
<td>${zpngTp.bzcl }</td>
<th>Pile区分</th>
<td>${zpngTp.plqf }</td>
</tr>
<tr>
<th>运用规格</th>
<td>${zpngTp.yuny }</td>
<th>出口包装No.</th>
<td>${zpngTp.ckno }</td>
<th>钢种类型</th>
<td>${zpngTp.gzlx }</td>
</tr>
<tr>
<th>内径代码</th>
<td>${zpngTp.njno }</td>
<th>表面精加工</th>
<td>${zpngTp.face }</td>
<th>品种代码</th>
<td>${zpngTp.pzno }</td>
</tr>
<tr>
<th>涂油种类</th>
<td>${zpngTp.ytyp }</td>
<th>规格代码</th>
<td>${zpngTp.ggno }</td>
<th>附着量<span style="font-size: 12px;">(单位.正面.反面)</span></th>
<td>${zpngTp.fudw }&nbsp;${zpngTp.fuzm }/${zpngTp.fufm }</td>
</tr>
<tr>
<th>作业停止标记</th>
<td>${zpngTp.ztbj }</td>
<th>实绩附着量<span style="font-size: 12px;">(正面.反面)</span></th>
<td>${zpngTp.sczm }/${zpngTp.scfm }</td>
<th>业连NO</th>
<td>${zpngTp.ylno }</td>
</tr>
<tr>
<th>差厚镀锡标记</th>
<td>${zpngTp.chdx }</td>
<th>强制出货标记</th>
<td>${zpngTp.qzch }</td>
<th>捆包形式</th>
<td>${zpngTp.kbao }</td>
</tr>
<tr>
<th>硬度</th>
<td>${zpngTp.ying }</td>
<th>锯齿形式</th>
<td>${zpngTp.jcxs }</td>
<th>标签换帖标记</th>
<td>${zpngTp.bqht }</td>
</tr>
</table>