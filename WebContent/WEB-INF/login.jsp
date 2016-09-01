<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link href="css/validation.css" rel="stylesheet" type="text/css"/>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        body {
            background-color: white;
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }
    </style>
    
    <script>
function checkNumber() {
	var num1 = document.getElementById("username").value;
	var num2 = document.getElementById("password").value;
	
	if (num1 == "" || num2 == "") {
		window.alert("输入不能为空");
		return false;
	}
	
	var reg = /^[0-9]{1,11}$/;
	
	if (!reg.test(num1)) {
		window.alert("输入的账号非法");
		return false;
	}
}
</script>
</head>
<body>
<form id="loginForm" name="loginForm" method="post" action="LoginClServlet">
    <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" >
        <tr>
            <td><table width="100%" background="images/bg.jpg" border="0" align="left" cellpadding="0" cellspacing="0"  >
                <tr>
                    <td align="left"><table  height="667" width="1104" border="0" cellspacing="0" cellpadding="0" style="background:url(images/background.jpg) no-repeat" >
                        <tr>
                            <td height="306" colspan="2">&nbsp;</td>
                            <td width="309">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="240" colspan="2">&nbsp;</td>
                            <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="24%" height="37" align="right" valign="middle" style="font-size:12px;">用户账号：</td>
                                    <td width="76%" align="left" ><input type="text" name="user" size="16" eMsg=""  id="username" tabindex="1" style="height:22px; width:150px; border:1px solid #c0c0c0; line-height:22px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight: bold;line-height:24px;"><a href="getBackPassword.portal">找回密码</a></span></td>
                                </tr>
                                <tr>
                                    <td height="37" align="right" valign="middle" style="font-size:12px;">密&nbsp;&nbsp;&nbsp;码：</td>
                                    <td align="left"><input type="password" name="password" size="16" id="password" tabindex="2" style="height:22px; width:150px; border:1px solid #c0c0c0;line-height:22px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight: bold;line-height:24px;"><a href="getBackPassword.portal">注册用户</a></span></td>
                                </tr>
                          <%
                        String errinfo = (String)request.getAttribute("err");
                		if (errinfo != null) {
                			out.println("<font color = 'red'>" + errinfo + "</font>");
                		} %>
                            </table>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td height="34" align="right" valign="middle" colspan="4" ><input type="submit" onclick = "return checkNumber()" name="btn" value=""  tabindex="4" style="border-width:0;width:85px;height:40px;background-image:url(images/button_yhdl.gif);cursor:pointer;margin-right:30px;"  /></td>
                                        <td width="50%"  height="34" colspan="4" align="left" valign="middle" >
                                            <input type="reset" name="btn" value=""  tabindex="4" style="border-width:0;width:85px;height:40px;background-image:url(images/button_reset.gif);cursor:pointer;margin-right:30px;"  />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <td width="90" height="124" valign="top"></td>
                            <td width="705" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" style=" font-size:12px; font-family:Arial, Helvetica, sans-serif">
                                <tr>
                                    <td height="25" colspan="3" align="left" valign="middle">可定制的即时通讯系统</td>
                                </tr>

                                <tr>
                                    <td height="25" colspan="3" align="left" valign="middle">技术支持： smack && spark && openfire </td>
                                </tr>
                                <tr>
                                    <td height="25" colspan="3" align="left" valign="middle">技术支持电话：15852405635&nbsp;&nbsp;&nbsp;&nbsp;技术支持邮箱：zhikang_xie@163.com</td>
                                </tr>
                            </table></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr><td height="30" colspan="3" align="center" valign="middle" style="font-size:12px;color:#fff;font-family:Arial, Helvetica, sans-serif">Copyright &copy; 移动智能工作室&nbsp;&nbsp;All Rights Reserved</td></tr>
                    </table></td>
                </tr>
            </table></td>
        </tr>
    </table>
</form>
<script type="text/javascript">var Login = {forwardUrl:'http://my.scu.edu.cn/index.portal',loginURL:'userPasswordValidate.portal',nameField:'Login.Token1',pwdField:'Login.Token2',gotoUrl:'http://my.scu.edu.cn/loginSuccess.portal',gotoFailUrl:'http://my.scu.edu.cn/loginFailure.portal',hideCaptcha:true};</script>
<script type="text/javascript" src="js/portal-login.js"></script>

<!-- End ImageReady Slices -->
</body>
</html>