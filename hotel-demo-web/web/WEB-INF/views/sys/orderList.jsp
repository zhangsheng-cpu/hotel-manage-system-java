<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2020-01-31
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="/static/images/sys/title_arrow.gif" /> 餐厅订单列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" style="text-align: center;" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>订单编号</td>
            <td>餐桌名</td>
            <td>下单日期</td>
            <td>付款日期</td>
            <td>总金额</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:choose>
            <c:when test="${not empty list.list }">
                <c:forEach  items="${list.list}"  var="order">
                    <tr height="60">
                        <td>${order.orderCode}</td>
                        <td>${order.tableId}</td>
                        <td><fmt:formatDate value="${order.orderDate}"  pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                        <td><fmt:formatDate value="${order.payDate}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatNumber value="${order.totalPrice}"  pattern="0.00"></fmt:formatNumber> </td>
                        <td>
                            <c:if test="${order.orderStatus ==1}">
                                已付款
                            </c:if>
                            <c:if test="${order.orderStatus ==0}">
                                未付款
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${order.disabled ==1}">
                                <a href="/sys/order/update?id=${order.id}&disabled=0" class="FunctionButton" target="right">激活</a>
                            </c:if>
                            <c:if test="${order.disabled ==0}">
                                <a href="/sys/order/update?id=${order.id}&disabled=1" class="FunctionButton" target="right">删除</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
        </tbody>
    </table>
    <%-- 分页--%>
    <div>
        <ul class="pagination">
            <li><a href="/sys/order/list?pageNum=1"> 首页</a></li>
            <li><a href="/sys/order/list?pageNum=${list.prePage}"> 上一页</a></li>
            <c:forEach items="${list.navigatepageNums}" var="p">
                <li><a href="/sys/order/list?pageNum=${p}"> ${p}</a></li>
            </c:forEach>
            <li><a href="/sys/order/list?pageNum=${list.nextPage}"> 下一页</a></li>
            <li><a href="/sys/order/list?pageNum=${list.pages}"> 尾页</a></li>
        </ul>
    </div>
    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
    </div>
</div>
</body>
</html>
