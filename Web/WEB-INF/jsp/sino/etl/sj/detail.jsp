<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/ui" prefix="ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<SCRIPT type="text/javascript">
		<!--
			//保存操作
			function doSubmit() {
				if(!confirm("确定保存吗?")) return false;
				alert("保存成功");
			}
		//-->
		</SCRIPT>
	</head>
	<body>
		<ui:page title="ETL 生产线实绩信息 ">
			<table width="98%" align="center" style="margin: 20px auto;"
				class="form">
				<colgroup>
					<col width="45%" /><col />
				</colgroup>
				<tr>
					<th>
						出端COIL No.
					</th>
					<td>
						<input name="_ciolno" class="normal" style="width: 4em;" disabled="disabled" value="0005801" title="输入材料（制品）" onkeydown="doQuery();" />
					</td>
				</tr>
			</table>
			
			<table id="PileTbl" width="96%" align="center" class="form" style="margin: 5px 0;">
				<colgroup><col width="15%"/><col /><col width="10%" /></colgroup>
				<tr>
					<td><span style="font-size: 16px;">录入方式：卷长</span></td>
					<th>班别</th>
					<td><ui:select name="" list="'A':'A班','B':'B班','C':'C班','D':'D班'" /></td>
				</tr>
			</table>
			<table width="96%" align="center" class="form" style="border: 1px solid #999; margin-top: 10px;">
				<colgroup>
					<col />
					<col />
					<col />
					<col />
					<col />
					<col />
					<col />
					<col />
				</colgroup>
				<tr style="height: 40px; line-height: 40px;">
					<th>
						指示书No.
					</th>
					<td>
						<span title="指示书No">N000017</span>
					</td>
					<th>
						出端COIL No.
					</th>
					<td colspan="2">
						<span title="表示经过ETL后，生成产品的编号">1-00058-01</span>
					</td>
					<th>
						制品尺寸
					</th>
					<td colspan="2">
						<span title="表示母卷的尺寸">0.200*704</span>
					</td>
				</tr>
				<tr>
					<th>
						卷取长
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal"  title="输入生成每一卷的长度" />
					</td>
					<th>
						CUT长
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal"  title="输入切割损耗" />
					</td>
					<th>
						LOSS长
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入其它损耗" />
					</td>
					<th>
						LOSS缺
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入其它损耗原因" />
					</td>
				</tr>
				<tr>
					<th>
						产量代码
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入与等级共同说明产出的品质"  />
					</td>
					<th>
						等级
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入与产量代码共同说明产出的品质" />
					</td>
					<th>
						处置
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入对有欠缺的产品进行处理的代码" />
					</td>
					<th>
						失陷
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入欠缺代码" />
					</td>
				</tr>
				<tr>
					<th>
						检定员
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入检定员代码" />
					</td>
					<th>
						计数员
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入计数员代码"  />
					</td>
					<th>
						出货重量
					</th>
					<td colspan="3">
						<ui:input name="" maxlength="6" cssClass="normal" title="表示计算所得重量"  />
					</td>
				</tr>
				<tr>
					<th>
						P.H个数
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入P.H"  />
					</td>
					<th>
						溶接个数
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入溶接个数ETL"  />
					</td>
					<th>
						板厚不良
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入厚板情况"  />
					</td>
					<th>
						卷取TR No.
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入1或2"  />
					</td>
				</tr>
				<tr>
					<th>
						耳波Op
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入边波纹OP" />
					</td>
					<th>
						Dr
					</th>
					<td>
						<ui:input name="" maxlength="6" cssClass="normal" title="输入边波纹Dr"  />
					</td>
					<th>
						中伸
					</th>
					<td colspan="3">
						<ui:input name="" maxlength="6" cssClass="normal" title="输入可波纹等级" />
					</td>
				</tr>
			</table>

			<div style="width: 96%; text-align: right; margin-top: 15px;">
				<input type="button" class="button" value="实绩确认(S)" accesskey="s"
					onclick="doSubmit();" />
			</div>
		</ui:page>
		<br />
	</body>
</html>