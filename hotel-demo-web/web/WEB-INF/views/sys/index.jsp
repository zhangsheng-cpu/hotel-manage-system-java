<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-18
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>无线点餐后台管理</title>
</head>

<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
    <frame src="/public/top.jsp" scrolling="no" noresize >
    <frameset cols="178px,*">
        <frame noresize src="/public/left.jsp" scrolling="yes" />
        <frame noresize name="right" src="/public/right.jsp" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="/public/bottom.jsp" />
</frameset>

<body>
</body>
</html>
