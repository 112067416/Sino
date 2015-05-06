<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui" %><%@ taglib uri="/f" prefix="f" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<table id="DataTbl" width="96%" align="center" class="pagination">
<colgroup>
<col width="2%"/>
<col width="10%"/>
<col width="10%"/>
<col width="10%"/>
<col width="10%"/>
<col width="5%"/>
<col width="10%"/>
<col width="5%"/>
<col width="5%"/>
<col width="5%"/>
<col width="10%"/>
<col width="10%" />
</colgroup>
<tr>
<th><input type="checkbox" onclick="coco.selAll('ids', this);" /></th>
<th>中间品包名</th>
<th>张数</th>
<th>订货No.</th>
<th>用户略称</th>
<th>等级</th>
<th>要求尺寸</th>
<th>主缺陷</th>
<th>缺陷2</th>
<th>缺陷3</th>
<th>KP别纸</th>
<th>业联No.</th>
</tr>
<c:forEach varStatus="stat" var="itemv" items="${item.items}">
<tr  xu.v.zpcc="" xu.v.jbno="${itemv.jbno}" xu.v.ggno="${itemv.ggno}" xu.v.yuny="${itemv.yuny}"  xu.v.zshu="${itemv.zshu}" xu.v.dhno="${itemv.dhno}" xu.v.user="${itemv.user}" xu.v.abbr="${itemv.abbr}"  xu.v.fuzm="${itemv.fuzm}" xu.v.fufm="${itemv.fuzm}" xu.v.ggno="${itemv.fuzm}" xu.v.yuny="${itemv.fufm}" xu.v.deng="${itemv.deng}" xu.v.houu="${itemv.houu}" xu.v.kuan="${itemv.kuan}" xu.v.cang="${itemv.cang}" xu.v.sjzl="${itemv.sjzl}" xu.v.plqf="${itemv.plqf}" xu.v.qqdm="${itemv.qqdm}" xu.v.qqd2="${itemv.qqd2}" xu.v.qqd3="${itemv.qqd3}" xu.v.size="${itemv.zpcc}" >
<td><input type="checkbox" name="ids" /></td>
<td><ui:input name="jbno" maxlength="11" value="itemv.jbno " onclick="coco.setAttr(this,'xu.value', this.vlaue);"  onblur="changeData(this);"  imeMode="false" validate="true" prop="type:abcn;" cssStyle="color:#00CC00;" /></td>
<td><ui:int name="zshu" maxlength="5" value="itemv.zshu " onclick="coco.setAttr(this,'xu.value', this.vlaue);" onblur="changeItemZshu(this);"/></td>
<td>${itemv.dhno }</td>
<td>${itemv.abbr }</td>
<td>${itemv.deng }</td>
<td>${itemv.zpcc }</td>
<td>${itemv.qqdm }</td>
<td>${itemv.qqd2 }</td>
<td>${itemv.qqd3 }
</td>
<td><c:forEach varStatus="stat" var="kpno" items="${itemv.kpns }">
<span class="link" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span>
</c:forEach></td>
<td><c:forEach varStatus="stat" var="ylno" items="${itemv.ylns }">
<span class="link" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span>
</c:forEach></td>
</tr>
</c:forEach>
</table>
<table width="96%" align="center" >
<tr>
<td align="left">
<input type="button" class="button" value="新增包(A)" accesskey="a" onclick="addRow();" />
<input type="button" class="button" value="删除选中包(D)" accesskey="d" onclick="removeRow(this);" />
</td>
</tr>
</table>
<form name="DataForm" action="update.do" method="post" >
<input type="hidden" name="houu" value="${item.houu }"/>
<input type="hidden" name="kuan" value="${item.kuan } "/>
<input type="hidden" name="cang" value="${item.cang } "/>
<input type="hidden" name="dhno" value="${item.dhno } "/>
<fieldset class="group">
<legend>生成的新制品信息</legend>
<table width="98%" align="center" class="form">
<colgroup>
<col /><col /><col /><col /><col /><col />
</colgroup>
<tr>
<th>制品号</th>
<td><ui:input name="jbno" readonly="true" maxlength="11" value="item.jbno "/></td>
<th>张数</th>
<td><ui:int name="zshu" maxlength="4" readonly="true" value="item.zshu "/></td>
<th>实际重量</th>
<td><ui:input name="sjzl" maxlength="5" value="item.sjzl " /></td>
</tr>
<tr>
<th>制品尺寸</th>
<td><ui:input name="size" maxlength="20" readonly="true" value="item.size "/></td>
<th>计算重量</th>
<td colspan="3"><ui:input name="jszl" maxlength="5" readonly="true" value="item.jszl "/></td>
</tr>
<tr>
<th>产量代码</th>
<td><ui:input name="chan" maxlength="1" required="true" value="item.chan "/></td>
<th>等级</th>
<td><ui:input name="deng" maxlength="3" required="true" value="item.deng "/></td>
<th>缺陷</th>
<td><ui:input name="qqdm" maxlength="2" value="item.qqdm "/></td>
</tr>
<tr>
<th>检定员</th>
<td><ui:input name="jdyn" maxlength="2" required="true" value="item.jdyn "/></td>
<th>计数员</th>
<td><ui:input name="jsyn" maxlength="2" required="true" value="item.jsyn "/></td>
<th>是否翻转</th>
<td><ui:input name="sffz" maxlength="1" required="true" value="item.sffz "/></td>
</tr>
<tr>
<th>组别</th>
<td><ui:select name="zu" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_ZU'"  prop="nn:1;"  value="item.zu"/></td>
<th>班别</th>
<td><ui:select name="ban" sql="#select KEY_,VALUE_ from COCO_CODE_ITEM where CODE_='SINO_BAN'"  prop="nn:1;"  value="item.ban"/></td>
<th>&nbsp;</th>
<td>&nbsp;</td>
</tr>
</table>
</fieldset>
<div class="submit" style="margin-top: 10px;">
<input type="button" class="button" value="实绩确认(S)" accesskey="s" onclick="doSubmit(this);" />
</div>
</form>