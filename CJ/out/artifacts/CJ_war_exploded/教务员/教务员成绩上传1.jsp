<%@ taglib prefix="f" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/2
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教务员成绩上传1</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function getschoolyear() {
            $.ajax({
                type:"POST",
                url:"DeanGetSchoolYear.action",
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
        function selectexam(){
            var schoolyear = $("#schoolyear").val();
            var semester = $("#semester").val();
            var grade = $("#grade").val();
            var tclass = $("#tclass").val();
            var tquality = $("#tquality").val();
            $.ajax({
                type:"POST",
                url:"Set2SelectExam.action?schoolyear=" + schoolyear + "&semester=" + semester +
                    "&grade=" + grade + "&tclass=" + tclass + "&tquality=" + tquality,
                dataType:"json",
                success:function (data) {
                    if(data =="undefined" || data==null || data==""){
                        alert("未查询到任何成绩提交信息！");
                    }
                    else{
                        var result = "<table style=\"text-align:center; table-layout:fixed\" align=\"center\" width=\"75%\"  border=\"1\" " +
                            "cellspacing=\"0\" cellpadding=\"0\"><tr><td colspan=2>已提交列表</td></tr><tr><td>试卷编号</td><td>班级编号</td></tr>";
                        result += data;
                        result += "</table>";
                        result += '<br/><br/><button onclick=\"checksubmit()\" ' +
                            'style=\"background:url(../img/提交按钮.png); width:150px; height:46px;\" ></button>';
                        $("#exam").html(result);
                    }
                }
            })
        }
        function checksubmit(){
            $.ajax({
                type:"POST",
                url:"checksubmit.action",
                dataType:"json",
                success:function (data) {
                    if(data=="undefined" || data==null || data==""){
                        $.ajax({
                            type:"POST",
                            url:"Set2.action",
                            dataType:"json",
                            success:function (data) {}
                        })
                        alert("成绩上传成功!");
                        window.location.href = "../欢迎页面.jsp";
                    }
                    else{
                        alert("以下班级还未上传成绩:\n" + data);
                        alert("成绩上传失败!");
                    }
                }
            })
        }
        $(function () {
            getschoolyear();
            getgrade();
        });
    </script>
</head>
<body>
    <br /><br /><br /><br />
    <div align="center">
        <s:form>
            <select name="SchoolYear" id="schoolyear" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:160px;" size="auto"></select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <select name="Semester" id="semester" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px" size="auto">
                <option>选择学期</option>
                <option value = "1">第一学期</option>
                <option value = "2">第二学期</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <select name="Grade" id="grade" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px;" size="auto"></select>
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
            <input type="submit" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px;" size="auto" value="查询考试" onclick="selectexam()">
            <br /><br /><br /><br />
        </s:form>
        <div id="exam"></div>
    </div>
</body>
</html>
