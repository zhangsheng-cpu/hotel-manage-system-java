<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-16
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单列表</title>
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
    </div>
</div>
    <!-- //banner -->
    <!-- breadcrumb -->
    <div class="container">
        <ol class="breadcrumb w3l-crumbs">
            <li><a href="/app/index"><i class="fa fa-home"></i> 主页</a></li>
            <li class="active">我的订单</li>
        </ol>
    </div>
    <!-- //breadcrumb -->
    <!-- products -->
    <div class="products">
        <div class="container">
            <div class="col-md-12 ">
                <!-- 查询 所有订单遍历  未付款的排在上面     开始-->
                <div class="rsidebar-top col-md-12">
                    <!-- 遍历所有的订单 -->
                    <c:if test="${not empty orders }">
                        <c:forEach items="${orders}" var="order">
                            <div class="sidebar-row">
                                <h4>
									<span class="col-md-10">
										${order.dinnerTable.tableName}
										订单编号：${order.orderCode}
										下单时间：<fmt:formatDate value="${order.orderDate}"  pattern="yyyy-MM-dd HH:ss:mm"/>
									</span>
                                    <span class="col-md-2">
										<font color="#0096e6">
											<c:if test="${order.orderStatus ==1 }">
                                                已付
                                            </c:if>
											<c:if test="${order.orderStatus ==0 }">
                                                未付
                                            </c:if>
											￥<fmt:formatNumber value="${order.totalPrice }" pattern="0.00"></fmt:formatNumber>
										</font>
									</span>
                                </h4>
                                <ul class="faq">
                                    <c:forEach items="${order.orderDetails }" var="orderDetail">
                                        <li>
                                            <a href="#">
                                                <span class="col-md-8">${orderDetail.food.foodName }</span>
                                                <span class="col-md-2">${orderDetail.buyNum}份</span>
                                                <span class="col-md-2">
													￥<fmt:formatNumber value="${orderDetail.food.price*orderDetail.discount*orderDetail.buyNum}" pattern="0.00"></fmt:formatNumber>
												</span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <hr>
                            </div>
                        </c:forEach>
                    </c:if>

                    <!-- <div class="sidebar-row"><center><font color="red">暂时没有订单！</font></center></div> -->
                </div>
            </div>
            <div class="clearfix">&nbsp;</div>
            <!-- 查询 所有订单遍历  未付款的排在上面		结束-->
        </div>
    </div>
</body>
</html>
