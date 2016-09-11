<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.im.util.ChatHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.im.service.UsersService"%>
<%@page import="com.im.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/chat.css" />
    <meta charset="utf-8">
    <title>econnect</title>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="css/iconic.css" media="screen" rel="stylesheet" type="text/css" />
    
    <!--[if lt IE 7]>
    <script src="js/IE7.js" type="text/javascript"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('body, div, ul, img, li, input, a, span ,label');
    </script>
    <![endif]-->

    <script src="js/prefix-free.js"></script>
    <script src="js/themechange.js"></script>
    <script type="text/javascript" src="js/chat.js"></script>


</head>

<body id="BODY" onload="init()">
<div id = "cookie">
    <script src="js/Cookie.js"></script>
    <script>
        if(document.cookie == "") {
            var cookie = new Cookie("User_data");
            if (!cookie.name || !cookie.color) {
                cookie.name = prompt("输入姓名:", "张康");  //  prompt() 方法用于显示可提示用户进行输入的对话框。
                cookie.color = prompt("输入部门:", "开发部");
            }
            if (!cookie.visits) {
                cookie.visits = 1;
                cookie.store(10);
            }
            else cookie.visits++;

        }
        else {
            //  alert(document.cookie);
            // alert(red_cookie.get_name("User_data"));
            Cookie("User_data").visits++;
            Cookie("User_data").store(10);
        }
    </script>

</div>

<div class="wrap">

    <nav>
        <ul class="menu">
            <li><a href="/IM/JumpServlet?key=goHome"><span class="iconic home" ></span> 首页</a></li>
            <li><a href="#"><span class="iconic plus-alt"></span> 关于</a>
                <ul>
                    <li><a href="#">产品介绍</a></li>
                    <li><a href="#">我们的团队</a></li>
                </ul>
            </li>
            <li><a href="#"><span class="iconic magnifying-glass"></span> 选择主题</a>
                <ul>
                    <li onclick="themewhite()"><a href="#">白灰墙砖</a></li>
                    <li onclick="themegrey()"><a href="#">灰色泡泡</a></li>
                    <li onclick="themeblue()"><a href="#">木纹</a></li>
                    <li onclick="themered()"><a href="#">蓝色星空</a></li>
                    <li onclick="themered()"><a href="#">星星</a></li>
                    <li onclick="themered()"><a href="#">麦田</a></li>
                    <li ><a href="#" onclick="bounce.start()">获得更多主题</a></li>
                </ul>
            </li>
            <li><a href="#"><span class="iconic map-pin"></span> 为您提供的通讯模式</a>
                <ul>
                    <li><a href="#">a模式</a></li>
                    <li><a href="#">b模式</a></li>
                    <li><a href="#">c模式</a></li>
                    <li><a href="#">d模式</a></li>
                    <li><a href="#">e模式</a></li>
                    <li><a href="#">f模式</a></li>
                </ul>
            </li>
            <li><a href="#"><span class="iconic mail"></span> 用户</a>
                <ul>
                    <li><a href="#" onclick='look_mention("User_data");'>查看信息</a></li>
                    <script>
                        function look_mention(l_name) {
                            alert("用户姓名："+red_cookie.get_name(l_name)+"\n所属部门："+red_cookie.get_color(l_name));


                        }
                    </script>
                    <li><a href="/IM/JumpServlet?key=goLogin" onclick="window.cookie.remove()">退出登录</a></li>
                </ul>
            </li>
        </ul>
        <div class="clearfix"></div>
    </nav>
</div>

<div class="box" id="box">
    <script> </script>

    <div class="drawchat" id="drawchat">
        <div class="chatBox">
            <div class="chatLeft">
                <div class="chat01">
                    <div class="chat01_title">
                        <ul class="talkTo">
                            <li><a href="javascript:;">张康</a></li></ul>
                        <a class="close_btn" href="javascript:;"></a>
                    </div>
                    <div class="chat01_content">
                        <div id="message_box_mes1" >
                        </div>
                        <div class="message_box mes2">
                        </div>
                        <div class="message_box mes3" style="display: block;">
                        </div>
                        <div class="message_box mes4">
                        </div>
                        <div class="message_box mes5">
                        </div>
                        <div class="message_box mes6">
                        </div>
                        <div class="message_box mes7">
                        </div>
                        <div class="message_box mes8">
                        </div>
                        <div class="message_box mes9">
                        </div>
                        <div class="message_box mes10">
                        </div>
                        
                        <p id="receiveMsg">test</p>
                        
                    </div>
                </div>
                <div class="chat02">
                    <div class="chat02_title">
                        <a class="chat02_title_btn ctb01" href="javascript:;" onclick="pass_face()"></a><a class="chat02_title_btn ctb02"
                                                                                     href="javascript:;" title="选择文件">
                        <embed width="15" height="16" flashvars="swfid=2556975203&amp;maxSumSize=50&amp;maxFileSize=50&amp;maxFileNum=1&amp;multiSelect=0&amp;uploadAPI=http%3A%2F%2Fupload.api.weibo.com%2F2%2Fmss%2Fupload.json%3Fsource%3D209678993%26tuid%3D1887188824&amp;initFun=STK.webim.ui.chatWindow.msgToolBar.upload.initFun&amp;sucFun=STK.webim.ui.chatWindow.msgToolBar.upload.sucFun&amp;errFun=STK.webim.ui.chatWindow.msgToolBar.upload.errFun&amp;beginFun=STK.webim.ui.chatWindow.msgToolBar.upload.beginFun&amp;showTipFun=STK.webim.ui.chatWindow.msgToolBar.upload.showTipFun&amp;hiddenTipFun=STK.webim.ui.chatWindow.msgToolBar.upload.hiddenTipFun&amp;areaInfo=0-16|12-16&amp;fExt=*.jpg;*.gif;*.jpeg;*.png|*&amp;fExtDec=选择图片|选择文件"
                               data="upload.swf" wmode="transparent" bgcolor="" allowscriptaccess="always" allowfullscreen="true"
                               scale="noScale" menu="false" type="application/x-shockwave-flash" src="http://service.weibo.com/staticjs/tools/upload.swf?v=36c9997f1313d1c4"
                               id="swf_3140">
                    </a><a class="chat02_title_btn ctb03" href="javascript:;" title="选择附件">
                        <embed width="15" height="16" flashvars="swfid=2556975203&amp;maxSumSize=50&amp;maxFileSize=50&amp;maxFileNum=1&amp;multiSelect=0&amp;uploadAPI=http%3A%2F%2Fupload.api.weibo.com%2F2%2Fmss%2Fupload.json%3Fsource%3D209678993%26tuid%3D1887188824&amp;initFun=STK.webim.ui.chatWindow.msgToolBar.upload.initFun&amp;sucFun=STK.webim.ui.chatWindow.msgToolBar.upload.sucFun&amp;errFun=STK.webim.ui.chatWindow.msgToolBar.upload.errFun&amp;beginFun=STK.webim.ui.chatWindow.msgToolBar.upload.beginFun&amp;showTipFun=STK.webim.ui.chatWindow.msgToolBar.upload.showTipFun&amp;hiddenTipFun=STK.webim.ui.chatWindow.msgToolBar.upload.hiddenTipFun&amp;areaInfo=0-16|12-16&amp;fExt=*.jpg;*.gif;*.jpeg;*.png|*&amp;fExtDec=选择图片|选择文件"
                               data="upload.swf" wmode="transparent" bgcolor="" allowscriptaccess="always" allowfullscreen="true"
                               scale="noScale" menu="false" type="application/x-shockwave-flash" src="http://service.weibo.com/staticjs/tools/upload.swf?v=36c9997f1313d1c4"
                               id="swf_3140">
                    </a>
                        <label class="chat02_title_t">
                            <a href="chat.htm" target="_blank">聊天记录</a></label>
                        <div class="wl_faces_box" id="wl_faces_box" style=" display: none;">
                            <div class="wl_faces_content">
                                <div class="title">
                                    <ul>
                                        <li class="title_name">常用表情</li><li class="wl_faces_close"><span>&nbsp;</span></li></ul>
                                </div>
                                <div class="wl_faces_main">
                                    <ul>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_01.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_02.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_03.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_04.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_05.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_06.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_07.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_08.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_09.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_10.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_11.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_12.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_13.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_14.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_15.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_16.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_17.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_18.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_19.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_20.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_21.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_22.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_23.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_24.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_25.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_26.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_27.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_28.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_29.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_30.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_31.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_32.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_33.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_34.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_35.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_36.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_37.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_38.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_39.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_40.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_41.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_42.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_43.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_44.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_45.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_46.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_47.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_48.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_49.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_50.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_51.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_52.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_53.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_54.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_55.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_56.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_57.gif" /></a></li>
                                        <li><a href="javascript:;">
                                            <img src="img/emo_58.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_59.gif" /></a></li><li><a href="javascript:;">
                                        <img src="img/emo_60.gif" /></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="wlf_icon">
                            </div>
                        </div>
                    </div>
                    
                    <!-- 聊天信息框 -->
                    <div class="chat02_content">
							<textarea type="textarea" name="message" id="MsgID"></textarea>
						</div>
						<div class="chat02_bar">
							<ul>
								<li style="right: 5px; top: 5px;">消息接收者：<input type=text
									name="JID" id="JID"></li>
								<li style="right: 5px; top: 5px;">
									<!-- <a href="javascript:;">
                                <img src="img/send_btn.jpg">--> <input
									type="button" id="sendMsgID" onclick="msgSendCllick()"
									value="发送">
								</li>
							</ul>
						</div>
                </div>
            </div>
            <div class="chatRight">
                <div class="chat03">
                    <div class="chat03_title">
                        <label class="chat03_title_t">
                            成员列表</label>
                    </div>
                    <div class="chat03_content">
                        <ul><!-- 
                            <li>
                                <label class="online">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2013.jpg"></a><a href="javascript:;" class="chat03_name">员工1</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2014.jpg"></a><a href="javascript:;" class="chat03_name">员工2</a>
                            </li>
                            <li class="choosed">
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2015.jpg"></a><a href="javascript:;" class="chat03_name">张康</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2016.jpg"></a><a href="javascript:;" class="chat03_name">王二</a>
                            </li>
                            <li>
                                <label class="online">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2017.jpg"></a><a href="javascript:;" class="chat03_name">员工5</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2018.jpg"></a><a href="javascript:;" class="chat03_name">员工6</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2019.jpg"></a><a href="javascript:;" class="chat03_name">员工7</a>
                            <li>
                                <label class="online">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2020.jpg"></a><a href="javascript:;" class="chat03_name">员工8</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2021.jpg"></a><a href="javascript:;" class="chat03_name">员工9</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2022.jpg"></a><a href="javascript:;" class="chat03_name">员工10</a>
                            </li>
                             -->
                             
                             <%
									UsersService usersService = new UsersService();
									ArrayList<User> allUsers = usersService.getAllUsers();
									for (User user : allUsers) {
								%>
								<li><%=user.getUsername()%></li>
								<%
									}
								%>
                        </ul>
                    </div>
                </div>
            </div>
            <div style="clear: both;">
            </div>
        </div>

    </div>

    <div class="drawfunction" id="drawfunction">
        <div class="content">
            <section class="query">
                <table id="change_fonts">
                    <tr>
                        <td>
                            <a href="/IM/JumpServlet?key=goCalender">
                                <img src="img/img/timetable.png">
                                <p>查看日程</p>
                            </a>
                        </td>
                        <td>
                            <a href="/IM/JumpServlet?key=goProject">
                                <img src="img/img/classroom.png">
                                <p>项目计划</p>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="/IM/JumpServlet?key=goOffice">
                                <img src="img/img/keshi.png">
                                <p>科室查询</p>
                            </a>
                        </td>
                        <td>
                            <a href="/IM/JumpServlet?key=goText" id="a_memo">
                                <img src="img/img/chengji.png">
                                <p>会议记录</p>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="/IM/JumpServlet?key=goFinance">
                                <img src="img/img/electricity.png">
                                <p>财务申报</p>
                            </a>
                        </td>
                        <td>
                            <a href="/IM/JumpServlet?key=addFunction">
                                <img src="img/img/plus74.png">
                                <p>添加功能</p>
                            </a>
                        </td>
                    </tr>

                </table>
            </section>
        </div>
    </div>

    <div class="line" id="line">

    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	function msgSendCllick() {
		var jid = document.getElementById("JID").value;
		var msg = document.getElementById("MsgID").value;
		$.ajax({
			type : "post",
			url : "/IM/ChatServlet",
			data : {
				JID : jid,
				message : msg
			},
			dataType : "json",
			success : function(response) {

			}
		});
		
		document.getElementById("MsgID").value = "";
	}

	$(function () {
        
        window.setInterval(function () {
         
        	$.ajax({ 
    		    type: "GET", 	
    			url: "/IM/ReceiveMsg",
    			success: function(response) {
    				$("#receiveMsg").html(response);
    			},
    			error: function(response){     
    				$("#receiveMsg").html("暂未收到消息");  
    			},     
    		});
        }, 5000);
         
    });

	
	
	function get_id (id) {
        return document.getElementById(id);
    }
    function tap () {
        var oBox = get_id("box"), oTop = get_id("drawchat"), oBottom = get_id("drawfunction"), oLine = get_id("line");
        oLine.onmousedown = function(e) {
            var disX = (e || event).clientX;
            oLine.left = oLine.offsetLeft;
            document.onmousemove = function(e) {
                var iT = oLine.left + ((e || event).clientX - disX);
                var e=e||window.event,tarnameb=e.target||e.srcElement;
                var maxT = oBox.clientWight - oLine.offsetWidth;
                oLine.style.margin = 0;
                iT < 0 && (iT = 0);
                iT > maxT && (iT = maxT);
                oLine.style.left = oTop.style.width = iT - 20 + "px";
                oBottom.style.width = oBox.clientWidth - iT + "px";
                $("msg").innerText='top.width:'+oLine.style.width+'---bottom.width:'+oBottom.style.width+'---oLine.offsetLeft:'+oLine.offsetLeft+'---disX:'+disX+'---tarnameb:'+tarnameb.tagName;
                return false
            };
            document.onmouseup = function() {
                document.onmousemove = null;
                document.onmouseup = null;
                oLine.releaseCapture && oLine.releaseCapture()
            };
            oLine.setCapture && oLine.setCapture();
            return false
        };

    };
    tap();
</script>
</html>