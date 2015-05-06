<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" class="form">
				<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="25%" />
			</colgroup>
			<tr>
				<th style="text-align: right;">制品号</th>
				<td>${item.jbno }</td>
				<th style="text-align: right;">生产线别</th>
				<td nowrap="nowrap"><f:v value="item.slin" ql="select name from Scxbpz where code=?"/></td>
				<th style="text-align: right;">重量(kg)</th>
				<td colspan="2">${item.zpzl }</td>
			</tr>
			<tr>
				<th style="text-align: right;">足</th>
				<td>${item.dmfx }</td>
				<th style="text-align: right;">捆包形式</th>
				<td colspan="3"><f:kv value="item.kbao" list="'S21':'S21','S31':'S31'"  /></td>
				
			</tr>
		</table>