<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-19
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Frame bottom</title>
    <link href="/static/css/sys/common_style_blue.css"          type="text/css" rel="stylesheet"/>
    <script src="/static/js/jquery-3.3.1.min.js"></script>

    <style type="text/css">
        body{
            margin: 0;
        }
        img{
            vertical-align: inherit;
            border:0;
        }
        a:link, a:hover, a:visited {
            color: #A9DCFF;
            text-decoration: none;
        }
        #StatusBar {
            background-color: #4386B7;
            border-top: 1px solid #FFFFFF;
            height: 19px;
            width: 100%;
        }
        #StatusBar #StatusBar_Links {
            color: #A9DCFF;
            float: left;
            font-family: "宋体";
            font-size: 12px;
            padding-left: 20px;
            padding-top: 3px;
        }
        #StatusBar #StatusBar_Right {
            color: #A9DCFF;
            float: right;
            font-family: "宋体";
            font-size: 12px;
            padding-right: 20px;
            padding-top: 4px;
        }
    </style>
</head>
<body>

<div id="StatusBar">
    <!-- 链接 -->
    <div id="StatusBar_Links">
        <a href="#" target="_blank">欢迎光临~</a>
    </div>
    <!-- 右侧功能按钮 -->
    <div id="StatusBar_Right">
        <!-- 版本 -->
        <a href="javascript:void(0)">
            <img border="0" width="11" height="11" src="/static/images/sys/info.gif" />
            <img border="0" width="40" height="11" src="/static/images/sys/version.gif" />
        </a>
    </div>
</div>


</body>
</html>
