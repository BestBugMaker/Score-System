<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/5
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教务主任成绩审核1</title>
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
        function selectscore(){
            var schoolyear = $("#schoolyear").val();
            var semester = $("#semester").val();
            var grade = $("#grade").val();
            var tclass = $("#tclass").val();
            var tquality = $("#tquality").val();
            $.ajax({
                type:"POST",
                url:"BatchStateSet2SelectScore.action?schoolyear=" + schoolyear + "&semester=" + semester +
                    "&grade=" + grade + "&tclass=" + tclass + "&tquality=" + tquality,
                dataType:"json",
                success:function (data) {
                    if(data == "[]"){
                        alert("该批次成绩还未上传！");
                    }
                    else{
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="60%"  border="1" cellspacing="0" cellpadding="0">';
                        scoretable += '<tr><td>学号</td><td>姓名</td><td>总分</td><td>排名</td></tr>';
                        for(var i=0; i<result.length; i++){
                            scoretable += '<tr>';
                            for(var j=0; j<result[i].length ;j++){
                                scoretable += '<td>' + result[i][j] + '</td>';
                            }
                            scoretable += '<td>' + (i + 1) + '<tr></tr>';
                        }
                        scoretable += "</table>";
                        scoretable += '<br/><br/><input style=\"background:url(../img/提交按钮.png); width:150px; ' +
                            'height:46px;\" type=\"button\" value=\"\" onclick=\"BatchStateSet2()\">';
                        $("#scoretable").html(scoretable);
                    }
                }
            })
        }
        function BatchStateSet2(){
            $.ajax({
                type:"POST",
                url:"BatchStateSet2.action",
                dataType:"json",
                success:function () {
                    alert("成绩审核成功!");
                    window.location.href = "../欢迎页面.jsp";
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
            &nbsp;
            <input type="button" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px;" size="auto" value="查询成绩" onclick="selectscore()">
            <br /><br /><br /><br />
        </s:form>
        <div id="exam"></div>
    </div>
    <div id="scoretable" align="center">
    </div>
</body>
</html>
