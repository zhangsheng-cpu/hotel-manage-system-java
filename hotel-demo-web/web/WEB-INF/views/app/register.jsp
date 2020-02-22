<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2020-02-04
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="/static/js/regex.js"></script>

    <script type="text/javascript">
        $(function () {
            //不同意协议  注册提交按钮为灰无法使用
            var regBtn = jQuery("#register");
            jQuery("#readme").change(function () {
                //选中已同意，返回true
                var checkedValue = jQuery("#readme").prop("checked");
                if (checkedValue) {
                    regBtn.prop("disabled", false);
                    regBtn.css("background", "#08cae2");
                } else {
                    regBtn.prop("disabled", true);
                    regBtn.css("background", "#909090");
                }
            });
        });

        function formOnblur(id) {
            //获取当前需要校验的输入框的值
            var fieldValue = document.getElementById(id).value;
            document.getElementById(id + "_error").innerHTML = "";
            var flag = true;
            if (id == "loginName") {
                if (fieldValue == null || fieldValue == "") {
                    document.getElementById(id + "_error").innerHTML = "请输入登录名！";
                    flag = false;
                } else {
                    //校验登录名是否已经在数据库存在
                    $.ajax({
                        type: "POST",
                        url: "/app/register/ajaxLoginName",
                        data: "loginName=" + fieldValue,
                        //同步请求
                        async: false,
                        success: function (msg) {
                            if (msg) {
                                document.getElementById(id + "_error").innerHTML = msg;
                                flag = false;
                            }
                        }
                    });
                }
            } else if (id == "phone") {
                if (fieldValue == null || fieldValue == "") {
                    document.getElementById(id + "_error").innerHTML = "请输入电话号码！";
                    flag = false;
                } else {
                    if (!checkPhone(fieldValue)) {
                        document.getElementById(id + "_error").innerHTML = "您输入的电话号码不合法！";
                        flag = false;
                    }
                }
            } else if (id == "email") {
                if (fieldValue == null || fieldValue == "") {
                    document.getElementById(id + "_error").innerHTML = "请输入邮箱地址！！";
                    flag = false;
                } else {
                    if (!checkEmail(fieldValue)) {
                        document.getElementById(id + "_error").innerHTML = "您输入的邮箱不合法！";
                        flag = false;
                    }
                }
            } else if (id == "passWord") {
                if (fieldValue == null || fieldValue == "") {
                    flag = false;
                    document.getElementById(id + "_error").innerHTML = "请输入6-16位英文或数字组成的密码！";
                } else {
                    if (!checkPassword(fieldValue)) {
                        flag = false;
                        document.getElementById(id + "_error").innerHTML = "您输入的密码不合法！";
                    }
                }
            } else if (id == "okPassWord") {
                if (fieldValue == null || fieldValue == "" ||
                    $("#passWord").val() == null || $("#passWord").val() == "" ||
                    $("#passWord").val() != fieldValue) {
                    flag = false;
                    document.getElementById(id + "_error").innerHTML = "两次输入的密码不能为空，且必须一致！";
                }
            }
            return flag;
        }

        function onRegister() {
            //获取所有输入框的ID
            var ids = ["loginName", "phone", "email", "passWord", "okPassWord"];

            //遍历所有输入框的ID
            for (var i = 0; i < ids.length; i++) {
                //只有其中一个输入框不符合要求，就不提交表单
                if (!formOnblur(ids[i])) {
                    //不符合要求
                    return false;
                }
            }

            //提交表单
            document.getElementById("registerForm").submit();
        }

    </script>
</head>

<body>

<!-- banner -->
<div class="banner about-w3bnr">
    <!-- header -->
    <div class="header">
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
                                <a href="/app/menuList" class="dropdown-toggle" >菜单 </a>
                            </li>
                            <li class="w3pages">
                                <a href="/app/order/list" >我的订单</a>
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
        <li class="active">注册</li>
    </ol>
</div>
<!-- //breadcrumb -->
<!-- sign up-page -->
<div class="login-page about">
    <div class="container">
        <h3 class="w3ls-title w3ls-title1">注册您的帐号</h3>
        <div class="login-agileinfo">
            <form action="/app/register/submitTable" method="post" id="registerForm"  onsubmit="return  onRegister()">
                <center> <font color="red">${message}</font></center>
                <input class="agile-ltext" type="text" id="loginName" name="loginName" placeholder="登录名"  autocomplete="off"
                       onblur="formOnblur(this.id);" required="" >
                <label id="loginName_error" class="null"></label>

                <input class="agile-ltext" type="text" id="phone" name="phone" placeholder="手机号码"
                       onblur="formOnblur(this.id);" required="">
                <label id="phone_error" class="null"></label>

                <input class="agile-ltext" type="email" id="email" name="email" placeholder="E-mail"
                       onblur="formOnblur(this.id);" required="">
                <label id="email_error" class="null"></label>

                <input class="agile-ltext" type="password"  id="passWord" name="passWord" placeholder="6-16位英文或数字组成的密码"
                       onblur="formOnblur(this.id);" required="">
                <label id="passWord_error" class="null"></label>

                <input class="agile-ltext" type="password"  id="okPassWord" name="okPassWord" placeholder="确认密码"
                       onblur="formOnblur(this.id);" required="">
                <label id="okPassWord_error" class="null"></label>
                <div>
                <input type="submit" id="register" onclick="onRegister()" value="注册"> </div>
            </form>
            <p>已有帐号?  <a href="/app/login"> 现在登录!</a></p>
        </div>
    </div>
</div>
</body>
</html>
