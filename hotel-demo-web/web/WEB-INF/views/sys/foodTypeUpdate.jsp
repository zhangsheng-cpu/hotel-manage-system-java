<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-20
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">
        function updateSubmit() {
            //获取用户输入的菜系名称
            var foodTypeName = $("#foodTypeName").val();

            alert(foodTypeName);
            if (foodTypeName != null && foodTypeName != "" && foodTypeName != "${foodType.typeName}") {
                //没有重复返回true  保存菜系名称
                $.ajax({
                    type: "POST",
                    url: "/sys/foodType/updateSubmit",
                    data: {
                        "foodTypeName": foodTypeName,
                        "id": ${foodType.id},
                    },
                    dataType: "text",
                    async:false
                }).done(function (msg) {
                    if (msg != null && msg.trim() == "success") {
                        $("#message").html("保存成功！");
                        $("#foodTypeName").val("");
                    } else if (msg != null && msg.trim() == "fail") {
                        $("#message").html("菜系名称已存在，请重新取名！");
                        $("#foodTypeName").val("");
                    }
                }).fail(function () {
                    alert("数据加载异常");
                })
            } else {
                $("#message").html("菜系名称不能为空，且不能和修改前一致！");
            }
        }
    </script>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="static/images/sys/title_arrow.gif"/> 更新菜系
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="" method="post">

        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="/static/images/sys/item_point.gif"> 菜系信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系名称</td>
                            <td>
                                <input type="text" value="${foodType.typeName}" id="foodTypeName" name="foodTypeName"
                                       class="InputStyle"/> *
                                <label color="red" id="message"></label>
                                <input type="hidden" id="id" name="id" value="${foodType.id}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="button" onclick="updateSubmit()" value="修改" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>

</div>

</body>
</html>
