<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="pagination" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
		<col width="4%"/>
		<col width="12%"/>
		<col width="5%"/>
		<col width="12%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="6%"/>
		<col width="6%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="8%"/>
		<col width="6%"/>
	</colgroup>
	<tr >
		<th>
			<input type="checkbox" xu.target="ids" />
		</th>
	   	<th height="26" >
			供应商合同NO
		</th>
		<th height="26" >
			制造商规格略称
		</th>
		<th>
			中日达规格略称
		</th>
		<th>
			尺寸
		</th>	
		<th >
			合同重量(吨)
		</th>
		<th >
			到货量(吨)
		</th>
		<th>
			等级
		</th>
		<th >
			表面
		</th>
		<th >
			币种
		</th>
		<th >
			单价
		</th>	
		<th>
			状态
		</th>
	</tr>
	<c:forEach varStatus="stat" var="item" items="${page.datas }">
		<tr xu.id="${item.htno }-${item.line }" xu.menu="menu" <c:if test="${stat.index%2 == 1 }">class="odd"</c:if> ondblclick="toModify(this);"  onmouseover="overTr(this)" onmouseout="outTr(this)" title="双击修改">
			<td align="center"><input type="checkbox" name="ids" value="${item.htno }-${item.line }" /></td>
			<td>${item.htno }-${item.line }</td>
			<td>${item.zzgg }</td>
			<td>${item.zrgg }</td>
			<td>${item.houu }*${item.kuan }</td>
			<td>${item.htzl }</td>
			<td>${item.dhsl }</td>
			<td>${item.fpdj }</td>
			<td>${item.face }</td>
			<td><sys:codeLabel code="#013" key="item.thqf" show="1" /></td>
			<td>${item.htdj }</td>
			<td><f:kv value="item.qywl" list="'0':'未完了','1':'<font color='red'>已完了</font>'"  /></td>
		</tr>
	</c:forEach>
</table>
<f:paged />