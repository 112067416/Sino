<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="/ui" prefix="ui"%><%@ taglib
	uri="/f" prefix="f"%><%@ taglib
	uri="/sys" prefix="sys"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="96%" align="center" class="form" style="margin-top: 10px;">
				<colgroup>
					<col width="15%"><col>
				</colgroup>
				<tr>
					<th>
						别纸
					</th>
					<td>
					<c:forEach varStatus="stat" var="kpno" items="${item.kpns }">
						<span class="link1" style="padding-left: 10px;" onclick="view('${kpno}')">${kpno}</span>
					</c:forEach>
					</td>
				</tr>
				<tr>
					<th>
						业联
					</th>
					<td>
					<c:forEach varStatus="stat" var="ylno" items="${item.ylns }">
						<span class="link1" style="padding-left: 10px;" onclick="view('${ylno}')">${ylno}</span>
					</c:forEach>
					</td>
				</tr>
				<tr>
					<th>
						生产备注
					</th>
					<td>
						${bz}
					</td>
				</tr>
			</table>