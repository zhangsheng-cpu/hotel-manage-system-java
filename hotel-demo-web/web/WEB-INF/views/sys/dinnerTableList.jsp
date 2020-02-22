<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-19
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无线点餐平台</title>
    <script type="text/javascript">

        //文档加载完后
        window.onload=function(){
            //获取后台保存的tableStatus值
            var tableStatus = "${tableStatus}";

            //遍历餐桌使用状态select标签中所有的option标签
            var tableStatusSelect = document.getElementById("tableStatus");
            //获取下拉框中所有的option
            var  options = tableStatusSelect.options;

            //如option标签的value值域后台保存的tableStatus值一致
            //则该option的状态为被选中
            $.each( options, function(i, option){
                $(option).attr("selected",option.value == tableStatus);
            });


            //获取后台保存的disabled值
            var disabled = "${disabled}";
            //遍历餐桌是否删除状态select标签中所有的option标签
            var disabledSelect = document.getElementById("disabled");
            //获取下拉框中所有的option
            var  options = disabledSelect.options;

            $.each( options, function(i, option){
                $(option).attr("selected",option.value == disabled);
            });
        }

        function tableStatusChange(obj) {
            //获取用户输入的关键字
            var keyword = $("#keyword").val();

            //获取被选择的餐桌使用状态值
            var tableStatus = obj.value;

            //获取被选中的餐桌是否删除的状态
            var disabled = $("#disabled option:selected").val();
            //发送请求
            window.location = "/sys/dinnerTable/list?keyword="+keyword+"&tableStatus="+tableStatus+"&disabled="+disabled;
        }

        function disabledChange(obj) {
            //获取用户输入的关键字
            var keyword = $("#keyword").val();

            //获取被选择的餐桌使用状态值
            var tableStatus =  $("#tableStatus option:selected").val();

            //获取被选中的餐桌是否删除的状态
            var disabled = obj.value;
            //发送请求
            window.location = "/sys/dinnerTable/list?keyword="+keyword+"&tableStatus="+tableStatus+"&disabled="+disabled;
        }
    </script>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="/static/images/sys/title_arrow.gif"/> 餐桌列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="/sys/dinnerTable/list" method="get" target="right">
        <input type="text" id="keyword" name="keyword" value="${keyWord}" placeholder="请输入餐桌名称">
        <!-- 餐桌使用状态的select标签 -->
        <select name="tableStatus" id="tableStatus" onchange="tableStatusChange(this)">
            <option value="">全部</option>
            <option value="0">未使用</option>
            <option value="1">正在使用</option>
        </select>
        <!-- 餐桌是否删除的select标签 -->
        <select name="disabled" id="disabled" onchange="disabledChange(this)">
            <option value="">全部</option>
            <option value="0">未删</option>
            <option value="1">已删</option>
        </select>
        <input type="submit" value="搜索">
        <!-- 添加 -->
        <a href="/sys/dinnerTable/addPage" target="right"><input type="button" value="添加"></a>
    </form>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>编号</td>
            <td>桌名</td>
            <td>餐桌使用状态</td>
            <td>客人启用时间</td>
            <td>餐桌是否删除</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:forEach  items="${list.list}"  var="dinnerTable" varStatus="status">
            <tr class="TableDetail1">
                <td align="center">${status.index+1}&nbsp;</td>
                <td align="center">${dinnerTable.tableName}&nbsp;</td>
                <td align="center">
                    <c:if test="${dinnerTable.tableStatus == 1}">
                        正在使用
                    </c:if>
                    <c:if test="${dinnerTable.tableStatus == 0}">
                        未使用
                    </c:if>
                </td>
                <td align="center"><fmt:formatDate value="${dinnerTable.beginUseDate}"  pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                <td align="center">
                    <c:if test="${dinnerTable.disabled == 1}">
                        已删除
                    </c:if>
                    <c:if test="${dinnerTable.disabled == 0}">
                        未删除
                    </c:if>
                </td>
                <td>
                    <c:if test="${dinnerTable.disabled == 1}">
                        <a href="/sys/dinnerTable/update?id=${dinnerTable.id}&disabled=0"
                           class="FunctionButton"	target="right">激活</a>
                    </c:if>

                    <c:if test="${dinnerTable.disabled == 0}">
                        <a href="/sys/dinnerTable/update?id=${dinnerTable.id}&disabled=1"
                           class="FunctionButton"  target="right">删除</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <%-- 分页--%>
    <div>
        <ul class="pagination">
            <li><a href="/sys/dinnerTable/list?pageNum=1"> 首页</a></li>
            <li><a href="/sys/dinnerTable/list?pageNum=${list.prePage}"> 上一页</a></li>
            <c:forEach items="${list.navigatepageNums}" var="p">
                <li><a href="/sys/dinnerTable/list?pageNum=${p}"> ${p}</a></li>
            </c:forEach>
            <li><a href="/sys/dinnerTable/list?pageNum=${list.nextPage}"> 下一页</a></li>
            <li><a href="/sys/dinnerTable/list?pageNum=${list.pages}"> 尾页</a></li>
        </ul>
    </div>

</div>

</body>
</html>
