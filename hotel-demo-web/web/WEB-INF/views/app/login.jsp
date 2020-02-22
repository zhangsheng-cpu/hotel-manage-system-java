<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2020-02-04
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入</title>
    <!-- Custom Theme files -->
    <script>
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
            //先判断用户是否输入用户名和密码，如其中一个未输入，则不需要提交表单中的内容到数据库
            var loginname = document.getElementById("loginname").value;
            var password = document.getElementById("password").value;

            if (loginname == null) {
                $("#message").val("请输入用户名");
                //用户名的输入框获取焦点
                document.getElementById("loginname").focus;
                //阻止默认行为
                return false;
            }

            if (password == null) {
                $("#message").val("请输入密码");
                //用户名的输入框获取焦点
                document.getElementById("password").focus;
                return false;
            }
            //提交表单
            document.getElementById("loginform").submit();
        }

    </script>
</head>
<body>

<!-- banner -->
<div class="banner about-w3bnr">
    <!-- header -->
    <div class="header">
        <!-- //header-one -->
        <!-- navigation -->
        <div class="navigation agiletop-nav">
            <div class="container">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header w3l_logo">
                        <h1><a href="/app/index">欢迎光临~</a></h1>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="/app/index" class="active">主页</a></li>
                            <!-- Mega Menu -->
                            <li class="dropdown">
                                <a href="/app/menuList" class="dropdown-toggle">菜单 </a>
                            </li>
                            <li class="w3pages">
                                <a href="/app/order/list">我的订单</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- //navigation -->
    </div>
    <!-- //header-end -->
</div>
<!-- //banner -->
<!-- breadcrumb -->
<div class="container">
    <ol class="breadcrumb w3l-crumbs">
        <li><a href="/app/index"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">登录</li>
    </ol>
</div>
<!-- //breadcrumb -->
<!-- login-page -->
<div class="login-page about">
    <div class="container">
        <h3 class="w3ls-title w3ls-title1">登录您的账户</h3>
        <div class="login-agileinfo">
            <form action="/app/login" method="post" id="loginform"  onsubmit=" return submitTable()">
                <center> <font color="red" id="message">${message}</font></center>
                <input class="agile-ltext" type="text" id="loginname" name="loginname" placeholder="请输入用户名" value="" required="">
                <input class="agile-ltext" type="password" id="password" name="password" placeholder="请输入密码"  required="">
                <div class="wthreelogin-text">
                    <ul>
                        <li>
                            <label class="checkbox">
                                <input type="checkbox" id="remenber" name="remenber" value="reme"><i></i>
                                记住我 ?
                            </label>
                        </li>
                    </ul>
                    <div class="clearfix"> </div>
                </div>
                <input type="submit" onclick="submitTable()"  value="登录">
            </form>
            <p>没有帐号? <a href="/app/register"> 现在注册!</a></p>
        </div>
    </div>
</div>
</body>
</html>
