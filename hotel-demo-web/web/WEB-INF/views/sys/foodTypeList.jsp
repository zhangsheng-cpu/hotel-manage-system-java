<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-20
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无线点餐平台</title>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="/static/images/sys/title_arrow.gif" /> 菜系列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>
<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="/sys/foodType/list" method="get" target="right">
        <input type="text" id="keyword" name="keyword" value="${keyword}" placeholder="请输入菜系名称">
        <select name="disabled" id="disabled" onchange="disabledChange(this)">
            <option value="">全部</option>
            <option value="0">未删</option>
            <option value="1">已删</option>
        </select>
        <input type="submit" value="搜索">
        <a href="/sys/foodType/addPage" target="right"><input type="button" value="添加"></a>
    </form>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" style="text-align: center;" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>编号</td>
            <td>菜系名称</td>
            <td>创建时间</td>
            <td>是否已删除</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:choose>
            <c:when test="${not empty foodTypes}">
                <c:forEach items="${list.list}"  var="foodType"  varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${foodType.typeName }</td>
                        <td><fmt:formatDate value="${foodType.createDate }"  pattern="yyyy-MM-dd  HH:ss:mm"/> </td>
                        <td>
                            <c:if test="${foodType.disabled == 0}">
                                未删
                            </c:if>
                            <c:if test="${foodType.disabled == 1}">
                                已删
                            </c:if>
                        </td>
                        <td>
                            <a href="/sys/foodType/viewUpdate?id=${foodType.id}" class="FunctionButton">更新</a>
                            <c:if test="${foodType.disabled == 0}">
                                <a href="/sys/foodType/update?id=${foodType.id}&disabled=1" class="FunctionButton">删除</a>
                            </c:if>
                            <c:if test="${foodType.disabled == 1}">
                                <a href="/sys/foodType/update?id=${foodType.id}&disabled=0" class="FunctionButton">激活</a>
                            </c:if>
                        </td>
                    </tr>

                </c:forEach>
            </c:when>
            <c:when test="${empty foodTypes}">
                <tr>
                    <td colspan="3" style="text-align: center;">没有你要找的数据！</td>
                </tr>
            </c:when>
        </c:choose>

        </tbody>
    </table>

    <%-- 分页--%>
    <div>
        <ul class="pagination">
            <li><a href="/sys/foodType/list?pageNum=1"> 首页</a></li>
            <li><a href="/sys/foodType/list?pageNum=${list.prePage}"> 上一页</a></li>
            <c:forEach items="${list.navigatepageNums}" var="p">
                <li><a href="/sys/foodType/list?pageNum=${p}"> ${p}</a></li>
            </c:forEach>
            <li><a href="/sys/foodType/list?pageNum=${list.nextPage}"> 下一页</a></li>
            <li><a href="/sys/foodType/list?pageNum=${list.pages}"> 尾页</a></li>
        </ul>
    </div>
</div>

</body>
</html>
