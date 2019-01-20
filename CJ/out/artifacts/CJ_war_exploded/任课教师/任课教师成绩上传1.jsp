<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/3
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任课教师成绩上传1</title>
</head>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function getclass() {
            $.ajax({
                type:"POST",
                url:"Set1GetClass.action",
                dataType:"json",
                success:function (data) {
                    $("#class").html(data);
                }
            })
        }
        function getexampaperno() {
            var classno = $("#class").val();
            $.ajax({
                type:"POST",
                url:"Set1GetPaper.action?classno=" + classno,
                dataType:"json",
                success:function (data) {
                    $("#exampaperno").html(data);
                }
            })
        }
        function submitscore(){
            var classno = $("#class").val();
            var exampaperno = $("#exampaperno").val();
            $.ajax({
                type:"POST",
                url:"submitscore.action?classno=" + classno +"&exampaperno=" + exampaperno,
                dataType:"json",
                success:function () {
                    alert("成绩上传成功!");
                    window.location.href = "../欢迎页面.jsp";
                }
            })
        }
        $(function () {
            getclass();
        });
    </script>
<body>
<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <div align="center">
            <select name="Class" id="class" onchange="getexampaperno()" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:160px;" size="auto"></select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <select name="ExamPaperNo" id="exampaperno" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:160px;" size="auto"></select>
            <br /><br /><br /><br />
            <div align="center">
                <input type="button" onclick="submitscore()" style="background:url(../img/提交按钮.png); width:150px; height:46px;" value="">
            </div>
    </div>
</body>
</html>
