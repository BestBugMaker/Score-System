<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/14
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教务主任邮件接收1</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function getupdate() {
            $.ajax({
                type:"POST",
                url:"GetUpdate.action",
                dataType:"json",
                success:function (data) {
                    alert("邮件加载中，请稍等！");
                    $("#updatetext").html(data);
                }
            })
        }
        $(function () {
            getupdate();
        })
    </script>
</head>
<body>
<div align="center">
    <h1>教师留言</h1>
    <textarea id="updatetext" style="font-size:40px;width: 100%;height: 100%" readonly="readonly"></textarea>
    <br>
    <input style="width:150px; height:46px;" type="button" value="已查阅" onclick="window.location.href='../欢迎页面.jsp'" >
</div>
</body>
</html>
