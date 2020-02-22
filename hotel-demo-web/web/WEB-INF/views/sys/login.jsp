<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2020-02-03
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入</title>
    <link href="/static/css/sys/login.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        if (window.location != parent.window.location) {
            //将当前页面作为最顶级页面
            parent.window.location = window.location;

        }

        //在页面完成加载时调用
        window.onload = function () {
            document.getElementById("loginname").focus();
            document.onkeydown = function () {
                // firefox没有window.event对象
                var event = arguments[0] ? arguments[0] : window.event;
                if (event.keyCode === 13) {
                    submitTable();
                }
            };
        };

        function submitTable() {
            //验证用户名和密码是否输入
            var loginname = document.getElementById("loginname").value;
            if (loginname == null || loginname.length == 0) {
                $("#message").html("请输入用户名！");
                document.getElementById("loginname").focus;
                return false;
            }
            var password = document.getElementById("password").value;
            if (password == null || password.length == 0) {
                $("#message").html("请输入密码！");
                document.getElementById("password").focus;
                return false;
            }
            document.getElementById("loginform").submit();
        }
    </script>
</head>
<body>

<!-- 上部 -->
<div id="Head_1">
    <!-- 标题 -->
    <div id="Head_1_Logo">
        <b style="font-family: '黑体'">点餐系统后台登录</b>
    </div>
</div>
<div class="logo_box">
    <form action="/sys/login" method="post" id="loginform">
        <input type="hidden" name="method" value="submitTable">
        <center><font color="red" id="message">${message}</font></center>
        <div class="input_outer">
            <span class="u_user"></span>
            <input name="loginname" id="loginname" class="text" autocomplete="off" placeholder="请输入用户名"
                   style="color: black !important" class="text">
        </div>
        <div class="input_outer">
            <span class="us_uer"></span>
            <input name="password" id="password" placeholder="请输入密码" type="password" autocomplete="off" class="text"
                   style="color: black !important; position:absolute; z-index:100;">
        </div>
        <input id="remember" name="remember" value="reme" class="checkbox" type="checkbox"><span>记住一周</span>
        <a href="/sys/register" style="float:right;color: #0096e6;">注册！</a>
        <div class="mb2">
            <a class="act-but submit" href="javascript:;" onclick="submitTable()" style="color: black">登录</a>
        </div>
    </form>
</div>

</body>
</html>
