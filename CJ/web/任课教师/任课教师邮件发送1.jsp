<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/14
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任课教师邮件发送1</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function submitupdate() {
            var info = $("#updatetext").val();
            $.ajax({
                type:"POST",
                url:"SubmitUpdate.action?info=" + info,
                dataType:"json",
                success:function (data) {
                    alert("邮件发送成功！");
                    window.location.href = "../欢迎页面.jsp";
                }
            })
        }
    </script>
</head>
<body>
    <div align="center">
        <h1>成绩修改留言</h1>
        <textarea id="updatetext" style="font-size:35px;width: 80%;height: 50%" ></textarea>
        <br>
        <input style="background:url(../img/提交按钮.png); width:150px; height:46px;" type="button" value="" onclick="submitupdate()">
    </div>
</body>
</html>
