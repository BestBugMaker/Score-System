<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/5
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教务主任成绩修改1</title>
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
                url:"DeanGetGrade.action",
                dataType:"json",
                success:function (data) {
                    $("#grade").html(data);
                }
            })
        }
        function getsubject() {
            var schoolyear = $("#schoolyear").val();
            var grade = $("#grade").val();
            $.ajax({
                type:"POST",
                url:"DeanGetSubject.action?schoolyear=" + schoolyear + "&grade=" + grade,
                dataType:"json",
                success:function (data) {
                    $("#subject").html(data);
                }
            })
        }
        function getclass(){
            var grade = $("#grade").val();
            $.ajax({
                type:"POST",
                url:"DeanGetClass.action?grade=" + grade,
                dataType:"json",
                success:function (data) {
                    data = JSON.parse(data);
                    var result;
                    for(var i in data){
                        result += "<option value='" + i + "'>" + data[i] + "</option>";
                    }
                    $("#class").html(result);
                }
            })
        }
        function selectexam() {
            var schoolyear = $("#schoolyear").val();
            var semester = $("#semester").val();
            var grade = $("#grade").val();
            var tclass = $("#tclass").val();
            var tquality = $("#tquality").val();
            var subno = $("#subject").val();
            var classno = $("#class").val();
            var exampaperno = schoolyear + semester + grade + tclass + tquality + subno;
            $.ajax({
                type:"POST",
                url:"DeanUpdateSelectExam.action?exampaperno=" + exampaperno + "&classno=" + classno,
                dataType:"json",
                success:function (data) {
                    if(data == "0"){
                        alert("试卷不存在或成绩已提交");
                    }
                    else{
                        window.location.href = "../任课教师/任课教师成绩修改2.jsp";
                    }
                }
            })
        }
        $(function () {
            getschoolyear();
            getgrade();
            getsubject();
        });
    </script>
</head>
<body>
<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <div align="center">
        <select name="SchoolYear" id="schoolyear" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:160px;" size="auto" onchange="getsubject()"></select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Semester" id="semester" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px" size="auto">
            <option>选择学期</option>
            <option value = "1">第一学期</option>
            <option value = "2">第二学期</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Grade" id="grade" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px" size="auto" onchange="getclass();getsubject()"></select>
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
        <select name="Subject" id="subject" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:80px;" size="auto"></select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="Class" id="class" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px;" size="auto"></select>
        </br></br></br></br>
        <input type="button" align="center" style="background:url(../img/提交按钮.png); width:150px; height:46px;" onclick="selectexam()">
    </div>
</body>
</html>
