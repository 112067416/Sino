<%@page contentType="text/html; charset=UTF-8" %><!---40001:timeout--><html>
<head>
<title>${systemName }</title>
<%@include file="global/header.jsp" %>
<script type="text/javascript">
<!--
if(window.self != window.top) top.location.href = window.location.href;

function closeWin() {
	if(ie && version > 6) window.open("","_self");  
	window.opener=null; 
	window.close(); 
}

function login() {
	var oForm = document.forms["DataForm"];
	var oUser = oForm.elements["userId"];
	var oPswd = oForm.elements["password"];
	if((oUser.value = oUser.value.replace(/^\s+|\s+$/g, "")) == "") {
		alert("请输入帐号");
		oUser.focus();
		return false;
	}
	if((oPswd.value = oPswd.value.replace(/^\s+|\s+$/g, "")) == "") {
		alert("请输入密码");
		oPswd.focus();
		return false;
	}

	var loginBtn = document.getElementById("loginBtn");
	loginBtn.disabled = true;
	if(window.name != "MAIN_WINDOW" && false) {
		oForm.action = "login!ajax.do";
		cocoform.submit(oForm, function() {
			if(this.code > 0) {
				var w = screen.availWidth - 10;
				var h = screen.availHeight - 30;
				var oWin = open("index.do", "MAIN_WINDOW", "width="+w+",height="+h+",top=0,left=0,resizable=0,menubar=0,scrollbars=0,status=0,titlebar=0,toolbar=0");
				oWin.focus();
				closeWin();
			}
			else alert(this.msg);
			loginBtn.disabled = false;
		});
	}
	else {
		oForm.action = "login!form.do";
		oForm.submit();
	}
}

function reset() {
	document.forms["DataForm"].reset();
}

coco.addEventListener(window, "load", function(event) {
	var oForm = document.forms["DataForm"];
	var oUid = oForm.elements["userId"];
	var oPwd = oForm.elements["password"];
	if(oUid.value.length > 0) oPwd.focus();
	else oUid.focus();
});

//-->
</script>
</head>
<body style="margin: 0px;overflow:hidden">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#CCDFF8">&nbsp;</td><!-- e5f6cf -->
  </tr>
  <tr>
    <td height="608" background="images/main/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="images/main/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="images/main/login_06.gif">&nbsp;</td>
            <td width="183" background="images/main/login_07.gif">
            <form name="DataForm" action="login.do" method="post" style="margin:0px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" background="images/main/login_07.gif">
              <tr>
                <td width="21%" height="30"><div align="center"><span style="color: #17355B; font-size: 12px;">帐号:</span></div></td>
                <td width="79%" height="30"><input type="text" name="userId"  onkeydown="if(event.keyCode == 13) event.keyCode = 9;" style="height:18px; width:130px; border:solid 1px #99C2F5; font-size:12px; color:#548DD4;"></td>
              </tr>
              <tr>
                <td height="30"><div align="center"><span style="color: #17355B; font-size: 12px;">密码:</span></div></td>
                <td height="30"><input type="password" name="password"  onkeydown="if(event.keyCode == 13) login();" style="height:18px; width:130px; border:solid 1px #99C2F5; font-size:12px; color:#548DD4;"></td>
              </tr>
              <tr>
                <td height="30">&nbsp;</td>
                <td height="30">
                <img id="loginBtn" src="images/main/login.gif" width="42" height="22" border="0" onclick="login();" style="cursor:hand">
                &nbsp;&nbsp;&nbsp;
                <img src="images/main/login_reset.gif" width="42" height="22" border="0" onclick="reset();" style="cursor:hand">
                </td>
              </tr>
            </table>
            </form>
            </td>
            <td width="255" background="images/main/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="245" valign="top" background="images/main/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%" style="padding-left:230px"><div style="width:200px;overflow:hidden;color:#FF0000;font-size:12px;">&nbsp;${message }</div></td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" style="color: #548DD4;font-size: 12px;vertical-align: middle;">版本 2010V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#6FB3F9">&nbsp;</td>
  </tr>
</table>
</body>
</html>
