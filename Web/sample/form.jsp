<%@page contentType="text/html; charset=UTF-8"%><%@taglib 
prefix="ui" uri="/ui" %><%@taglib 
prefix="sys" uri="/sys" %><%@taglib 
prefix="sino" uri="/sino" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>表单元素</title>
		<%@include file="/WEB-INF/jsp/global/header.jsp" %>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<script type="text/javascript">
		function view(form) {
			var content = cocoform.postContent(form);
			alert(content);
		}
		</script>
	</head>
	<body>
	<form name="DataForm" >
	<ui:print id="aa"/>
	<table width="100%" border="1" cellpadding="2" cellspacing="" bordercolor="#000000" bordercolorDark="#FFFFFF">
		<colgroup><col width="100" /><col width="250" /><col width="300" /><col width="" /></colgroup>
		<tr bgcolor="#EEEEEE">
			<td align="center" height="30">名称</td>
			<td align="center">预览</td>
			<td align="center">源代码</td>
			<td align="center">说明</td>
		</tr>
		<tr>
			<td height="25" colspan="4" align="center">常用标签ui.tld</td>
		</tr>
		<tr>
			<td>布尔值选框</td>
			<td><ui:bool name="valid" value="#true" /></td>
			<td>&lt;ui:bool name="valid" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>文本框</td>
			<td><ui:input name="input" maxlength="6" /> * <ui:input name="input1" maxlength="3" readonly="true" value="#123" /></td>
			<td>&lt;ui:input name="input" maxlength="6" /&gt; * &lt;ui:input name="input1" maxlength="3" readonly="true" value="#123" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>文本框(自定义级联)</td>
			<td><ui:input name="input0" maxlength="6" cascade="{key:'sample',fields:{input01:[],input02:[]}}" />
			<br/>级联1:<ui:input name="input01" readonly="true" cssStyle="width:100px;" />
			<br/>级联2:<ui:input name="input02" readonly="true" cssStyle="width:100px;" />
			</td>
			<td>&lt;ui:input name="input0" maxlength="6" cascade="{key:'sample',fields:{input01:[],input02:[]}}" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>文本框(sql级联)</td>
			<td>
			部门ID：<ui:input name="input1" maxlength="4" cascade="{key:'sql',module:'sample'}" />
			部门ID：<ui:combobox name="combobox_dept" items="[['1001','董事长'],['1026','生产管理'],['1002','总经理']]" cascade="{key:\"sql\",module:'sample'}"/>
			<br/>部门名称:<ui:input name="input11" readonly="true" cssStyle="width:100px;" />
			<br/>员工:<ui:select name="select12" list="" />
			</td>
			<td>&lt;ui:input name="input1" maxlength="4" cascade="{key:'sql',module:'sample'}" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>文本框(bean级联)</td>
			<td><ui:input name="input2" maxlength="4" cascade="{key:'bean',module:'sample',fields:{beanInput1:[],beanInput2:[],beanSelect1:[]}}" />
			<br/>值1:<ui:input name="beanInput1" readonly="true" cssStyle="width:100px;" />
			<br/>值2:<ui:input name="beanInput2" readonly="true" cssStyle="width:100px;" />
			<br/>下拉:<ui:select name="beanSelect1" list="" />
			</td>
			<td>&lt;ui:input name="input2" maxlength="4" cascade="{key:'bean',module:'sample',fields:{beanInput1:[],beanInput2:[],beanSelect1:[]}}" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>下拉框(bean级联)</td>
			<td>省份<ui:select name="province" list="'':'省份','hi':'海南','gd':'广东'" cascade="{key:'bean',module:'sampleProv',fields:{city:[]},clears:['dist']}" />
			<br/>城市:<ui:select name="city" list="" cascade="{key:'bean',module:'sampleCity',fields:{dist:[]}}" />
			<br/>区镇:<ui:select name="dist" list="" />
			</td>
			<td>&lt;ui:select name="province" list="'':'省份','hi':'海南','gd':'广东'" cascade="{key:'bean',module:'sampleProv',fields:{city:[]},clears:['dist']}"  /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>数字</td>
			<td><ui:digit name="digit" maxlength="3" /></td>
			<td>&lt;ui:digit name="digit" maxlength="3" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>整数</td>
			<td><ui:int name="int" maxlength="5" /></td>
			<td>&lt;ui:int name="int" maxlength="5" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>正整数</td>
			<td><ui:int name="positive" scale="4" positive="true" /></td>
			<td>&lt;ui:int name="int" scale="4" positive="true" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>数值</td>
			<td><ui:number name="number" maxlength="20" /></td>
			<td>&lt;ui:number name="number" maxlength="20" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>数值(5,3)</td>
			<td><ui:number name="number53" scale="5" precision="3" /></td>
			<td>&lt;ui:number name="number53" scale="5" precision="3" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>数值(6,3)</td>
			<td><ui:number name="number63" scale="6" precision="3" /></td>
			<td>&lt;ui:number name="number63" scale="6" precision="3" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>日期</td>
			<td><ui:date name="date" /></td>
			<td>&lt;ui:date name="date" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>日期时间</td>
			<td><ui:datetime name="datetime" /></td>
			<td>&lt;ui:datetime name="datetime" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>组合框</td>
			<td><ui:combobox name="combobox" items="[['1','国内'],['2','国外']]" width="100"/></td>
			<td>&lt;ui:combobox name="combobox" items="[['1','国内'],['2','国外']]" width="100"/&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>多信息组合框</td>
			<td><ui:combobox name="combobox0" items="[['xdj','许德建', 0, '汉族', '男'],['zl','张良', 0, '汉族', '男'],['ly','凌云', 0, '黎族', '女']]" width="100" tofield="[[0,'mz'],[1,'xb']]" match="true" value="#ly" />
			<br/>民族:<ui:input name="mz" readonly="true" /><br/>性别:<ui:input name="xb" readonly="true" /></td>
			<td>&lt;ui:combobox name="combobox0" items="[['xdj','许德建', 0, '汉族', '男'],['zl','张良', 0, '汉族', '男'],['ly','凌云', 0, '黎族', '女']]" width="100" tofield="[[0,'mz'],[1,'xb']]" match="true" value="#ly" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>下拉框</td>
			<td><ui:select name="select" list="'1':'国内','2':'国外'" /></td>
			<td>&lt;ui:select name="select" list="'1':'国内','2':'国外'" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>复选框</td>
			<td><ui:checklist name="checklist" list="'Java':'Java','C#':'C#','C++':'C++'" /></td>
			<td>&lt;ui:checklist name="checklist" list="'Java':'Java','C#':'C#','C++':'C++'" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>复选框(SQL)</td>
			<td><ui:checklist name="checklistSql" sql="#select id_,name_ from coco_person"/></td>
			<td>&lt;ui:checklist name="checklistSql" sql="#select id_,name_ from coco_person" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>单选框</td>
			<td><ui:radiolist name="radiolist" list="'Java':'Java','C#':'C#','C++':'C++'" /></td>
			<td>&lt;ui:radiolist name="radiolist" list="'Java':'Java','C#':'C#','C++':'C++'" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>文本域</td>
			<td><ui:textarea name="textarea" rows="3" cols="30" /></td>
			<td>&lt;ui:textarea name="textarea" rows="3" cols="30" /&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td height="25" colspan="4" align="center">系统管理标签sys.tld</td>
		</tr>
		<tr>
			<td>码表</td>
			<td><sys:codeBox name="code" code="#012" txt="2" width="100" /></td>
			<td>&lt;sys:codeBox name="code" code="#012" txt="2" width="100"/&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>码表（关联）</td>
			<td><sys:codeBox name="code0" code="#012" value="#0" txt="2" width="100" match="true" dat="6" tofield="[[0,'codeValue'],[1,'codeRemark']]" />
			<br/>值:<ui:input name="codeValue" readonly="true" /><br/>备注:<ui:input name="codeRemark" readonly="true" />
			</td>
			<td>&lt;sys:codeBox name="code" code="#012" txt="2" width="100"/&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td>机构</td>
			<td><sys:deptBox name="dept" /></td>
			<td>&lt;sys:deptBox name="dept"/&gt;</td>
			<td></td>
		</tr>
		<tr>
			<td height="25" colspan="4" align="center">中日达生产管理标签sys.tld</td>
		</tr>
		<tr>
			<td>用户</td>
			<td><sino:yongBox name="yong" width="100" /></td>
			<td>&lt;sys:codeBox name="code" code="#012" txt="2" width="100"/&gt;</td>
			<td></td>
		</tr>
	</table>
	<div class="submit">
	<input type="button" class="button" value="查看填写数据" onclick="view(this.form)" />
	</div>
	</form>
	</body>
</html>