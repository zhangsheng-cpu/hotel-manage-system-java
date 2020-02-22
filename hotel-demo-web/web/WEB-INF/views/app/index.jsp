<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common.jsp"%>
<html>
<head>
    <title>主页</title>

    <script type="application/x-javascript">
        /* 页面加载的时候添加一个定时器，0秒之后执行hideURLbar函数。hideURLbar函数将页面滚动至坐标（0,1）
 因为chrome等浏览器会有滚动缓存功能，比如你在A页面滚动后跳转到B页面，点击返回键回到A页面，会发现滚动条位置仍然保持 */
        addEventListener("load", function (ev) {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scroll(0, 1);
        }

        window.onload = function (ev) {
            //获取用户选中的物品类型
            var tableStatus = "${tableStatus}";
            var agileinfo_search = document.getElementById("agileinfo_search")

            //获取下拉框中所有的option
            var options = agileinfo_search.options;

            $.each(options, function (i, item) {
                //如果option的value值等于用户选择的tableStatus 就被选中
                $(item).attr("selected", item.value == tableStatus);
            });
        }

        function dataChange(obj) {
            //获取用户输入的餐桌名
            var tableName = $("#tableName").val();
            //餐桌的使用状态
            var tableStatus = obj.value;

            window.location = "/app/index1?tableName="+tableName+"&tableStatus="+tableStatus;
        }
    </script>
</head>
<body>
    <!--banner-->
    <div class="banner" style="height: 900px;">
        <%--header--%>
        <div class="header">
            <%--navigation--%>
            <div class="navigation agiletop-nav">
                <div class="container">
                    <nav class="navbar navbar-default">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header w31_logo">
                            <h1><a href="/app/index">欢迎光临~</a> </h1>
                        </div>
                        <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="/app/index" class="active">主页</a>
                                </li>
                                <li>
                                    <a href="/app/menuList" class="dropdown-toggle">菜单</a>
                                </li>
                                <li class="w3pages">
                                    <a href="/app/order/list" class="dropdown-toggle">我的订单</a>
                                </li>
                                <c:if test="${empty session_user}">
                                    <li class="head-dpdn">
                                        <a href="/app/login">登录</a>
                                    </li>
                                    <li class="head-dpdn">
                                        <a href="/app/register">免费注册</a>
                                    </li>
                                </c:if>
                                <c:if test="${not empty session_user}">
                                    <li class="head-dpdn">
                                        <a>${session_user.loginName}您好！</a>
                                    </li>
                                    <li class="head-dpdn">
                                        <a href="/app/loginOut">退出</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <%--navigation--%>
        </div>
        <%--header-end--%>
        <%--banner-text--%>
        <div class="banner-text">
            <div class="container" style="padding-left: 280px;margin-top: -100px;">
                <div class="agileits-search">
                    <form action="/app/index" method="post">
                        <input type="hidden" name="method" value="submitTable">
                        <div id="serchResult" cstyle="margin-top: 20px;">
                            <c:if test="${not empty dinnerTables}">
                                <c:forEach items="${dinnerTables}" var="dinnerTable">
                                    <a href="/app/menu?id=${dinnerTable.id}" style="color: white;font-size: 20px">
                                            ${dinnerTable.tableName}
                                    </a>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ empty dinnerTables}">
                                当前没有未使用的餐桌
                            </c:if>
                            <br><br>
                        </div>
                        <input name="tableName" id="tableName" type="text" placeholder="餐桌名">
                        <select id="agileinfo_search" name="tableStatus" onchange="dataChange(this)">
                            <option value="">全部</option>
                            <option value="1">正在使用</option>
                            <option value="0">未使用</option>
                        </select>
                        <input type=submit value="查看餐桌">
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
