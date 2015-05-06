<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/coco.js"></script>
		<script type="text/javascript" src="/js/cocoajax.js"></script>
		<script type="text/javascript" src="/js/cocoform.js"></script>
		<script type="text/javascript" src="/js/calendar.js"></script>
		<script type="text/javascript" src="/js/yahoo-dom-event.js"></script>
		<SCRIPT type="text/javascript">
		<!--
			//提交
			function doSubmit() {
				if(!confirm("确定保存吗?")) return false;
				alert("保存成功");
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="合同订单信息 ">
			<form action="#" name="DataForm" method="post">
				<table width="98%" align="center" style="margin: 20px auto;"
					class="form">
					<colgroup>
						<col width="30%" />
						<col width="20%" />
						<col width="30%" />
						<col width="20%" />
					</colgroup>
					<tr>
						<td>
							制造商规格略称(中日达规格略称)
						</td>
						<td>

							<input name="" style="width: 10em;" />
						</td>
						<td>
							品种
						</td>
						<td style="font-size: 14px;">
							<select class="form full">
								<option value="">--请选择--</option>
								<option value="01">01</option>
								<option value="02">02</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							公差.厚
						</td>
						<td>
							<input name="" style="width: 10em;" />
						</td>
						<td>
							公差.宽
						</td>
						<td>
							<input name="" style="width: 10em;" />
						</td>
					</tr>
					<tr>
						<td>
							重量(吨)
						</td>
						<td>
							<input name="" style="width: 10em;" />
						</td>
						<td>
							级
						</td>
						<td>
							<select class="form full">
								<option value="">--请选择--</option>
								<option value="PBB">PBB</option>
								<option value="PAA">PAA</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							表面内径
						</td>
						<td>
							<select class="form full">
								<option value="">--请选择--</option>
								<option value="B">B</option>
								<option value="A">A</option>
							</select>
						</td>
						<td>
							区
						</td>
						<td>
							<select class="form full">
								<option value="">--请选择--</option>
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							币种
						</td>
						<td>
							<select class="form full">
								<option value="">--请选择--</option>
								<option value="B">人民币</option>
								<option value="A">美元</option>
							</select>
						</td>
						<td>
							单价
						</td>
						<td>
							<input name="" style="width: 10em;" />
						</td>
					</tr>
				</table>
				<br />
				<div align="center">
					<input type="button" value="保 存(S)" onclick="doSubmit()"
						class="button" accesskey="s" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重 置(R)" class="button" accesskey="r" />
				</div>
			</form>
		</ui:page>
	</body>
</html>
