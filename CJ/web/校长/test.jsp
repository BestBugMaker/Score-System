<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/12
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.BufferedReader" %>
<%@ page import = "java.io.InputStreamReader" %>
<html>
<head>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function getphoto() {
            $.ajax({
                type:"POST",
                url:"getphoto.action",
                dataType:"json",
                success:function (data) {
                    alert("hello");
                    document.getElementById("a").innerHTML='<img src="../img/aa.png" />';
                }
            })
        }
    </script>
</head>
<body>
    <input type="button" onclick="getphoto()" value="生成图片">
    <div id="a">

    </div>
</body>
</html>
