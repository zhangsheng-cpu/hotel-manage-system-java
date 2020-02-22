<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-19
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Frame top</title>

    <link href="/static/css/sys/common_style_blue.css"          type="text/css" rel="stylesheet"/>
    <script src="/static/js/jquery-3.3.1.min.js"></script>


    <style type="text/css">
        <!--
        body {
            margin: 0;
        }
        #Head_1 {
            background: url("/static/images/sys/img/top_head1_bg.gif") repeat-x scroll 0 0 transparent;
            height: 64px;
            margin: 0 auto;
            width: 100%;
        }
        #Head_1 #Head_1_Logo {
            float: left;
            left: 20px;
            position: absolute;
            top: 12px;
            color: #F1F9FE;
            font-family: Arial Black,Arial;
            font-size: 28px;
        }
        #Head_1 #Head_1_UserWelcome {
            float: right;
            color: #B3E1FF;
            font-family: "宋体";
            font-size: 12px;
            height: 25px;
            padding-top: 40px;
            margin-right: 20px;
        }
        #Head_1 #Head_1_FunctionButton {
            float: left;
            position: absolute;
            right: 5px;
            top: 35px;
            margin-right: 15px;
        }
        #Head_1 #Head_1_FunctionButton img {
            margin-left: 10px;
        }
        #Head_2 {
            background: url("/static/images/sys/img/top_head2_bg.gif") repeat-x scroll 0 0 transparent;
            border-bottom: 1px solid #FFFFFF;
            border-top: 1px solid #A0C6E1;
            height: 36px;
            margin: 0;
            width: 100%;
        }
        #Head_2 #Head2_Awoke {
            float: left;
            height: 100%;
            padding-left: 19px;
            padding-top: 2px;
        }
        #Head_2 #Head2_Awoke #AwokeNum {
            position: absolute;
            left: 20px;
            top: 68px;
            width: 406px;
            /*height: 21px;*/
            margin-top: 0;
            padding: 0;
            padding-top: 2px;
            list-style-type: none;
            margin-bottom: 0;
            margin-left: 0;
        }
        #Head_2 #Head2_Awoke #AwokeNum li {
            float: left;
            margin: 3px;
            display: inline;
        }
        #Head_2 #Head2_Awoke #AwokeNum a {
            color: #FFFFFF;
            font-family: "宋体";
            font-size: 12px;
            text-decoration: none;
        }
        #Head_2 #Head2_Awoke #AwokeNum .Line {
            border-left: 1px solid #005A98;
            border-right: 1px solid #A0C6E1;
            height: 17px;
            width: 0px;
        }
        #Head_2 #Head2_FunctionList, .Head2_FunctionList {
            color: #FFFFFF;
            float: right;
            font-family: "宋体";
            font-size: 13px;
            height: 100%;
            padding-right: 26px;
            padding-top: 10px;
        }
        -->
    </style>
</head>
<body>

<!-- 上部 -->
<div id="Head_1">
    <!-- 标题 -->
    <div id="Head_1_Logo">
        <b style="font-family: '黑体'">欢迎光临~</b>
    </div>

    <div id="Head_1_FunctionButton">
        <!-- 欢迎用户的文字 -->
        <div id="Head_1_UserWelcome">
            <c:if test="${ empty session_user.loginName}">
                <a target="_blank" href="/sys/login"  style="color: white;">
                    登录
                </a>
            </c:if>
            <c:if test="${not empty session_user.loginName}">
                <img border="0" width="13" height="14" src="/static/images/sys/user.gif" />
                欢迎
                <b>${session_user.loginName}</b>
                <a target="_parent" href="/sys/loginOut"  style="color: white;">
                    退出
                </a>
            </c:if>
        </div>

    </div>
</div>
<!-- 下部 -->
<div id="Head_2">
    <!-- 任务提醒 -->
    <div id="Head2_Awoke">
        <ul id="AwokeNum"></ul>
    </div>

    <div class="Head2_FunctionList" style="float:left">
        <a href="javascript: window.parent.right.history.back();">
            <img src="/static/images/sys/Header_back.gif" width="24" height="24" style="margin-top: -8px;"/>
            <b>后退</b>
        </a>
        <a href="javascript: window.parent.right.history.forward();">
            <img src="/static/images/sys/Header_forward.gif" width="24" height="24" style="margin-top: -8px;"/>
            <b>前进</b>
        </a>
    </div>
</div>
</body>
</html>
