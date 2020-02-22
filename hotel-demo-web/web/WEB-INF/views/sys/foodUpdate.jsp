<%--
  Created by IntelliJ IDEA.
  User: 过往云烟~
  Date: 2019-12-22
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无线点餐平台</title>

    <script type="text/javascript">
        function ajaxFoodName() {
            var flag = true;

            $("#message").html("");
            //获取用户输入的 菜品名
            var foodName = $("#foodName").val();
            if (foodName == null || foodName == "") {
                $("#message").html("菜品名字不能为空");
                flag = false;
            } else {
                //查询 用户输入的菜品名 是否已经存在
                jQuery.ajax({
                    type: "POST",
                    url: "/sys/food/ajaxFoodName",
                    data: {
                        "foodName": foodName
                    },
                    dataType: "text",
                    async: false,
                }).done(function (msg) {
                    if (msg.trim() != null && msg.trim() == "fail") {
                        $("#message").html("菜品名称已存在，请重新取名！");
                        $("#foodName").val("");
                        flag = false;
                    }
                }).fail(function () {
                    alert("数据加载异常");
                })
            }
            return flag;
        }

        function ajaxPrice() {
            var flag = true;
            $("#message").html("");
            var price = $("#price").val();

            if (price == null || price == "") {
                $("#message").html("菜系价格不能为空");
                flag = false;
            } else if (!/^(([1-9]\d*[.]\d\d?)|([0][.]\d\d?)|([1-9]\d*)|0)$/.test(price)) {
                /*可以·小数 ，整数· ，0 */

                $("#message").html("请输入正确的菜品价格！");
                $("#price").val("");
                flag = false;
            }
            return flag;
        }

        function ajaxNum() {
            var flag = true;
            $("#message").html("");
            var discount = $("#discount").val();

            if (discount != null && discount != "") {
                if (!/^(0([.]\d{1,2})?|1([.]0{1,2})?)$/.test(discount)) {
                    $("#message").html("菜品折扣只能是0-1之间的两位小数！");
                    $("#discount").val("");
                    flag = false;
                }
            }
            return flag;
        }

        function addSubmitTest() {
            //再次 进行校验
            if (!ajaxFoodName() || !ajaxPrice() || !ajaxNum()) {
                return false;
            }

            //获取的是 上传文件的本地路径
            var imgName = $("#img").val();

            //找到最后 . 的索引值
            var extStar = imgName.lastIndexOf(".");

            //获取 .jpg  改为小写
            var ext = imgName.substring(extStar, imgName.length).toLowerCase();

            if (imgName == null || imgName == "") {
                $("#message").html("请上传png,jpg,gif,jpeg,bmp格式的菜品图片");
                return false;
            } else if (ext.match(/.png|.jpg|.gif|.jpeg|.bmp/i) == null) {
                //表明用户上传的文件不符合要求
                $("#message").html("只能上传png,jpg,gif,jpeg,bmp格式的菜品图片");
                return false;
            }
            //提交表单
            $.ajax({
                type: "post",
                url: "/sys/food/submitUpdate",
                data: new FormData($("form")[0]),
                contentType: false, //不设置内容类型
                processData: false,
                cache: false,
                async: false//不处理数据
            }).done(function () {
                window.location.href = "/sys/food/list";
            }).fail(function () {
                alert("2")
            })
        }
    </script>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="/static/images/sys/title_arrow.gif"/> 更新新菜品
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form  id="form" enctype="multipart/form-data">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="/static/images/sys/item_point.gif"> 菜品信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系</td>
                            <td>
                                <select id="foodTypeId" name="foodTypeId" style="width:150px">
                                    <c:forEach items="${foodTypes}"  var="foodType">
                                        <option value="${foodType.id}">${foodType.typeName}</option>
                                    </c:forEach>
                                </select>
                                *
                                <input type="hidden" name="id" value="${food.id}" />
                                <label color="red" id="message"></label>
                            </td>
                        </tr>
                        <tr>
                            <td width="80px">菜名</td>
                            <td><input type="text"  onblur="ajaxFoodName()"  id="foodName" name="foodName" class="InputStyle" value="${food.foodName}"/> *</td>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" placeholder="最多两位小数"  onblur="ajaxPrice()" id="price" name="price" class="InputStyle"
                                       value="${food.price}"/> *</td>
                        </tr>
                        <tr>
                            <td>折扣</td>
                            <td><input type="text" id="discount" name="discount" class="InputStyle" placeholder="0-1之间如0.8" onblur="ajaxNum()"
                                       value="${food.discount}"/></td>
                        </tr>

                        <tr>
                            <td>简介</td>
                            <td><textarea name="remark" class="TextareaStyle">${food.remark}</textarea></td>
                        </tr>
                        <tr>
                            <td width="80px">菜品图片</td>
                            <td>
                                <input type="file" name="img" id="img" /> *
                            </td>
                        </tr>
                        <tr>
                            <td  colspan="2">
                                <img id="preview" src="/static/images/app/food/${food.img}"
                                     width=200px height=100px />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>


        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="button" onclick="addSubmitTest()" value="修改" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>
</div>
</body>
</html>
