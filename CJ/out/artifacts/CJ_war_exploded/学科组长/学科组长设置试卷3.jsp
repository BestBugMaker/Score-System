<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/11/25
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<html>
<head>
    <title>学科组长设置试卷3</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <style>
        .problem input:focus{
            border-style:solid;
            border-color: #03a9f4;
            box-shadow: 0 0 15px #03a9f4;
        }

        .black_overlay {
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: #717171;
            z-index: 1001;
            -moz-opacity: 0.8;
            opacity: .80;
            filter: alpha(opacity=80);
        }

        .white_content {
            display: none;
            position: absolute;
            top: 10%;
            left: 10%;
            width: 80%;
            height: 80%;
            border: 16px solid lightblue;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }

        .white_content_small {
            display: none;
            position: absolute;
            top: 20%;
            left: 30%;
            width: 40%;
            height: 50%;
            border: 16px solid lightblue;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
    </style>

    <script>
        function getlarge() {
            $.ajax({
                type:"POST",
                url:"getlarge.action",
                dataType:"json",
                success:function (data) {
                    $("#large").html(data);
                }
            })
        }

        function insertsmall(obj) {
            var name = "large" + obj.id + "name";
            var countid = "large" + obj.id + "smallcount";
            var scoreid = "large" + obj.id + "totalscore";
            var largename = document.getElementById(name).value;
            var smallcount = document.getElementById(countid).value;
            var totalscore = document.getElementById(scoreid).value;
            $.ajax({
                type:"POST",
                url:"getsmall.action?largeno=" + obj.id + "&largename=" + largename +
                    "&smallcount=" + smallcount +"&totalscore=" + totalscore,
                dataType:"json",
                success:function (data) {
                    $("#small").html(data);
                }
            })
        }

        function submitlargeandsmall(){
            $.ajax({
                type:"POST",
                url:"InsertLargeAndSmall.action",
                dataType:"json",
                data:$("#largeandsmallform").serialize(),
                success:function () {
                    $("#small").html("");
                }
            })
        }
        function finished(){
            alert("试卷设置成功!");
            window.location.href = "欢迎页面.jsp";
        }
        $(function () {
            getlarge();
        });
    </script>

    <script type="text/javascript">
        //弹出隐藏层
        function ShowDiv(show_div, bg_div) {
            document.getElementById(show_div).style.display = 'block';
            document.getElementById(bg_div).style.display = 'block';
            var bgdiv = document.getElementById(bg_div);
            bgdiv.style.width = document.body.scrollWidth;  // bgdiv.style.height = $(document).height();

            $("#" + bg_div).height($(document).height());
        };
        //关闭弹出层
        function CloseDiv(show_div, bg_div) {
            document.getElementById(show_div).style.display = 'none';
            document.getElementById(bg_div).style.display = 'none';
        };
    </script>
</head>
<body>
<div id="fade" class="black_overlay"></div>
<div align="center">
    <p align="center" style="color:#FF6600;font-style:italic;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px">试卷编号</p>
    <input name="ExamPaperNo" id="exampaperno" type="text" value="<%=session.getAttribute("ExamPaperNo")%>">
</div>
<br/>
<br/>
<div id="large" align="center">
</div>
<br/>
<br/>

<div id="MyDiv" style="width: 50%;height: 65%;position: absolute;left: 20%;top: 18%" class="white_content" >
    <div style="text-align: right; cursor: default; height: 40px;">
        <span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')">关闭</span>
    </div>
    <form id="largeandsmallform" action="##" method="post">
        <div id="small" align="center"></div>
    </form>
</div>

<div style="position:absolute;bottom: 0;left: 42%">
    <button  onclick="finished()" style="background:url(../img/提交按钮.png);width:150px;height:46px;"></button></a>
</div>
</body>
</html>

