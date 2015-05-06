<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<table id="BzTbl" width="96%" align="center" class="form" style="margin: 5px 0;">
				<colgroup>
				<col width="40%"/>
				<col />
				<col width="10%" />
				<col width="10%" />
				</colgroup>
				<tr>
					<td><span style="font-size: 16px;">录入方式：卷长</span></td>
					<th>班</th>
					<td><sys:codeLabel code="#SINO_BAN" key="item.ban" show="1" /></td>
					<th>组</th>
					<td><sys:codeLabel code="#SINO_ZU" key="item.zu" show="1" /></td>
					<td></td>
				</tr>
			</table>
			<table width="96%" align="center" class="form" style="border: 1px solid #999; margin-top: 10px;">
				<colgroup>
					<col width="15%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="15%" />
				</colgroup>
				<tr style="height: 40px; line-height: 40px;">
					<th>
						出端COIL No.
					</th>
					<td>
						${item.jbno}
					</td>
					<th>
						指示书No.
					</th>
					<td >
						${item.zsno}
					</td>
					<th >
						制品尺寸
					</th>
					<td colspan="4">
						${item.houu}*
						${item.kuan}
					</td>
				</tr>
				<tr>
					<th>
						卷取长
					</th>
					<td>
						${item.jbcn}
					</td>
					<th>
						CUT长
					</th>
					<td>
						${item.cutc}
					</td>
					<th>
						LOSS长
					</th>
					<td>
						${item.losc}
					</td>
					<th>
						LOSS缺陷
					</th>
					<td>
						${item.losq}
					</td>
				</tr>
				<tr>
					<th>
						产量代码
					</th>
					<td>
						${item.chan}
					</td>
					<th>
						等级
					</th>
					<td>
						${item.deng}
					</td>
					<th>
						缺陷
					</th>
					<td>
						${item.qqdm}
					</td>
				</tr>
				<tr>
					<th>
						检定员
					</th>
					<td>
						${item.jdyn}
					</td>
					<th>
						计数员
					</th>
					<td>
						${item.jsyn}
					</td>
					<th>
						出货重量
					</th>
					<td>
					${item.zpzl}
					</td>
				</tr>
				<tr>
					<th>
						P.H个数
					</th>
					<td>
						${item.phgs}
					</td>
					<th>
						溶接个数
					</th>
					<td>
						${item.rjet}
					</td>
					<th>
						板厚不良
					</th>
					<td>
						${item.bhbl}
					</td>
					<th>
						卷取TRNo.
					</th>
					<td>
						${item.jsno}
					</td>
				</tr>
				<tr>
					<th>
						耳波Op
					</th>
					<td>
						${item.bopg}- ${item.bopj}
					</td>
					<th>
						耳波Dr
					</th>
					<td>
						${item.bdrg}- ${item.bdrj}
					</td>
					<th>
						中伸
					</th>
					<td colspan="3">
					${item.zbog}- ${item.zboj}
					</td>
				</tr>
			</table>
