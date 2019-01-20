<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/11/29
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任课教师成绩录入1</title>
    <style>
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
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function getschoolyear() {
            $.ajax({
                type:"POST",
                url:"getschoolyear.action",
                dataType:"json",
                success:function (data) {
                    $("#schoolyear").html(data);
                }
            })
        }
        function getgrade() {
            $.ajax({
                type:"POST",
                url:"getgrade.action",
                dataType:"json",
                success:function (data) {
                    $("#grade").html(data);
                }
            })
        }
        function selectexam() {
            var schoolyear = $("#schoolyear").val();
            var semester = $("#semester").val();
            var grade = $("#grade").val();
            var tclass = $("#tclass").val();
            var tquality = $("#tquality").val();
            $.ajax({
                type:"POST",
                url:"insertselectexam.action?schoolyear=" + schoolyear + "&semester=" + semester +
                    "&grade=" + grade + "&tclass=" + tclass + "&tquality=" + tquality,
                dataType:"json",
                success:function (data) {
                    if(data == "0"){
                        alert("试卷不存在！");
                    }
                    else{
                        alert("试卷存在，请选择班级！");
                        $.ajax({
                            type:"POST",
                            url:"insertselectclass.action",
                            dataType:"json",
                            success:function (data) {
                                $("#class").html(data);
                                ShowDiv('MyDiv','fade');
                            }
                        })
                    }
                }
            })
        }
        function submitclass(){
            var classno =  $("#class").val();
            $.ajax({
                type:"POST",
                url:"setclass.action?classno=" + classno,
                dataType:"json",
                success:function () {
                    window.location.href = "任课教师成绩录入2.jsp";
                }
            })
        }
        $(function () {
            getschoolyear();
            getgrade();
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
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <div align="center">
        <select name="SchoolYear" id="schoolyear" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:160px;" size="auto"></select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Semester" id="semester" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px" size="auto">
            <option>选择学期</option>
            <option value = "1">第一学期</option>
            <option value = "2">第二学期</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Grade" id="grade" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px" size="auto"></select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Tclass" id="tclass" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:140px" size="auto">
            <option>选择考试类型</option>
            <option value="00">第一次月考</option>
            <option value="01">期中考试</option>
            <option value="02">第二次月考</option>
            <option value="03">期末考试</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Tquality" id="tquality" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:150px;" size="auto">
            <option>选择考试性质</option>
            <option value="0">非统考</option>
            <option value="1">统考</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:150px;" size="auto" value="查询考试" onclick="selectexam()">
        </br>
    </div>
    <div align="center" id="MyDiv" style="width: 50%;height: 65%;position: absolute;left: 20%;top: 18%" class="white_content" >
        <div style="text-align: right; cursor: default; height: 40px;">
            <span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')">关闭</span>
        </div>
        <form>
            </br></br></br>
            <select name="Class" id="class" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:140px" size="auto">
            </select>
            </br></br></br>
            <input type="button" onclick="submitclass()" style="background:url(../img/提交按钮.png);width:150px;height:46px;" value="">
        </form>
    </div>
</body>
</html>
