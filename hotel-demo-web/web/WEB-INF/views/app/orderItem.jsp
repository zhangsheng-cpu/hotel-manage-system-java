<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-11
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情页</title>

    <script type="text/javascript">

        //付款
        function pay(orderId) {
            window.location.href = "/app/order/pay?orderId=" + orderId;
        }

        //取消订单
        function deleteOrder(orderId) {
            window.location.href = "/app/order/delete?orderId=" + orderId;
        }

    </script>
</head>
<body>
    <%--banner--%>
    <div class="banner about-w3bnr">
        <%--header--%>
        <div class="header">
            <%-- header-one --%>
            <%-- navigation --%>
            <div class="navigation agiletop-nav">
            <div class="container" style="margin-right: 5px;margin-left: 5px;">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header w3l_logo">
                        <h1><a href="/app/index">欢迎光临~</a></h1>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="/app/index" class="active">主页</a>
                            </li>
                            <li>
                                <a href="" class="dropdown-toggle">菜单</a>
                            </li>
                            <li class="w3pages">
                                <a href="/app/order/list" class="dropdown-toggle">我的订单</a>
                            </li>
                            <c:if test="${empty session_user}">
                                <li class="head-dpdn">
                                    <a href="">登录</a>
                                </li>
                                <li class="head-dpdn">
                                    <a href="">免费注册</a>
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
        </div>
    </div>
    <!-- //banner -->
    <!-- breadcrumb -->
    <div class="container">
        <ol class="breadcrumb w31-crumbs">
            <li><a href="/app/index"><i class="fa fa-home">主页</i> </a></li>
            <li class="active">${dinnerTable.tableName}订单详情</li>
        </ol>
    </div>
    <!-- //breadcrumb -->
    <!-- products -->

    <div class="products">
        <div class="container">
            <div class="col-md-12">
                <%--查询 所有订单遍历 未付款的排在上面--%>
                <div class="rsidebar-top col-md-12">
                    <div class="sidebar-row">
                        <%--遍历 付款未删除的订单开始--%>
                        <c:if test="${not empty orders}">
                            <c:forEach items="${orders}" var="order">
                                <h4>
                                <span class="col-md-12">
                                    订单编号：${order.orderCode} &nbsp; &nbsp;
                                    下单时间：<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </span>
                                </h4>
                                <ul class="faq">
                                    <%--订单明细遍历 开始--%>
                                    <c:if test="${not empty order.orderDetails}">
                                        <c:forEach items="${order.orderDetails}" var="orderDetail">
                                            <li>
                                                <a href="#">
                                                    <span class="col-md-8">${orderDetail.food.foodName}</span>
                                                    <span class="col-md-2">${orderDetail.buyNum}份</span>
                                                    <span class="col-md-2">
                                                       ￥<fmt:formatNumber value="${orderDetail.food.price*orderDetail.discount*orderDetail.buyNum}" pattern="0.00"></fmt:formatNumber>
                                                    </span>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                    <%--订单明细 遍历 结束--%>
                                </ul>
                                <br/>
                                <span class="col-md-8"></span>
                                <span class="col-md-4">
                                    <font color="#0096e6">
                                        总计:¥ <fmt:formatNumber value="${order.totalPrice}" pattern="0.00"></fmt:formatNumber>
                                    </font>
                                            <%-- 订单 id--%>
                                            <c:if test="${order.orderStatus == 0}">
                                                <input type="button" onclick="pay(${order.id})" value="付款">
                                                <input type="button" onclick="deleteOrder(${order.id})" value="取消订单">
                                            </c:if>
                                </span>
                            </c:forEach>
                        </c:if>
                        <%--遍历 订单 结束--%>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</body>
</html>
