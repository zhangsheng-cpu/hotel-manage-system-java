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
    <title>Frame left</title>

    <link href="/static/css/sys/common_style_blue.css"          type="text/css" rel="stylesheet"/>
    <script src="/static/js/jquery-3.3.1.min.js"></script>

    <!-- 内容总宽度为 3px边框 * 2 + 155px内容 = 161px; -->
    <style type="text/css">

        html{
            height: 100%;
        }
        body {
            background: none repeat scroll 0 0 #D8EDFC;
            margin: 0;
            padding: 0;
        }
        #Menu {
            margin: 0;
            padding: 0;
            width: 155px;
            background: none repeat scroll 0 0 #D8EBF7;
            list-style: none outside none;

            margin-left: 3px;
            border-top: 3px solid #4891C6;
        }
        #Menu .level1 {
            color: #005790;
            font-weight: bold;
            padding-bottom: 1px;
            cursor: pointer;
        }
        #Menu .level1 .level1Style {
            background: url("/static/images/sys/img/menu_btn_bg.gif") no-repeat scroll 0 0 transparent;
            height: 23px;
            padding-left: 20px;
            padding-top: 5px;
            width: 135px;
            margin-bottom: -4px
        }
        #Menu .level1 .level1Style .Icon {
            margin-top: -2px;
        }
        #Menu .level1 .MenuLevel2 {
            background: none repeat scroll 0 0 #D8EBF7;
            list-style: none outside none;
            margin: 0;
            padding: 0;
        }
        #Menu .level1 .MenuLevel2 .level2Style{
            color: #005790;
            font-weight: normal;
            border-top: 1px solid #EFF6FB;
            height: 18px;
            padding-left: 43px;
            padding-top: 5px;
            width: 112px;
            background-image:url(/static/images/sys/img/menu_arrow_single.gif);
            background-color: #8EC4E9;
            background-repeat: no-repeat;
            background-position: 29px center;
        }
    </style>


</head>
<body>
<ul id="Menu">
    <li class="level1">
        <div class="level1Style">
            <img src="/static/images/sys/func20001.gif" class="Icon" />
            系统菜单
        </div>
        <ul class="MenuLevel2">
            <li class="level2 level2Style">
                <a target="right" href="/sys/dinnerTable/list">餐桌管理</a>
            </li>
            <li class="level2 level2Style">
                <a target="right" href="/sys/foodType/list">菜系管理</a>
            </li>
            <li class="level2 level2Style">
                <a target="right" href="/sys/food/list">菜品管理</a>
            </li>
            <li class="level2 level2Style">
                <a target="right" href="/sys/order/list">订单管理</a>
            </li>
        </ul>
    </li>
</ul>


</body>
</html>
