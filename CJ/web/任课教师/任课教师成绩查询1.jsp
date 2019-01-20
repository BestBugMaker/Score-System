<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/8
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任课教师成绩查询1</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function queryclasspaper() {
            $.ajax({
                type:"POST",
                url:"getgradebatch.action",
                dataType:"json",
                success:function (data) {
                    var result  = JSON.parse(data); //获取返回的json字符串
                    var paperbatch = "<option>请选择考试批次</option>";
                    for(var key in result){
                        paperbatch += '<option value=\"' + key + '\">' + result[key] + "</option>";
                    }
                    $("#batch").html(paperbatch);
                }
            })
        }

        function queryclass(){
            $.ajax({
                type:"POST",
                url:"teachergetclass.action",
                dataType:"json",
                success:function (data) {
                    var result = JSON.parse(data);
                    var teacherclass = "<option>请选择班级</option>";
                    for (var key in result){
                        teacherclass += '<option value = "'+ key + '">'+ result[key] + "</option>";
                    }
                    $("#classno").html(teacherclass);
                }

            })

        }

        function selectscore(){
            var batch = $("#batch").val();
            var classno = $("#classno").val();
            $.ajax({
                type:"POST",
                url:"courseteachergetscore.action?batch="+ batch + "&classno=" + classno,
                dataType:"json",
                success:function (data) {
                    if (data == "{}"){
                        alert("该批次成绩还未发布！");
                    }
                    else {
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="100%"  border="1" cellspacing="0" cellpadding="0">';
                        scoretable += "<tr><td rowspan='2'>" + "学号" + "</td><td rowspan='2'>" + "姓名" + "</td>";
                        var tdcount = 3;
                        for(var i in result){
                            if(i != "成绩"){
                                //alert(i);
                                var length = 0;
                                for(var j in result[i] ){
                                    length++;
                                    tdcount++
                                }
                                scoretable += '<td colspan="' + length + '">' + i +'</td>'; //获取题目名
                            }
                        }
                        scoretable += "<td>" + "总分" + "</td>";
                        scoretable += "</tr>";

                        var score = 0;
                        for(var i in result){
                            if(i != "成绩"){
                                for(j in result[i]){
                                    scoretable += '<td style="color: red">' + result[i][j] + '</td>';  //获取小题总分
                                    score += parseInt(result[i][j]);                                    //求得试卷总分
                                }
                            }
                        }
                        scoretable += "<td style='color: red'>" + score + "</td>";
                        scoretable += "</tr><tr>";

                        for (var i in result["成绩"]){
                            for (var j = 0;j < result["成绩"][i].length;j++){
                                scoretable += '<td>' + result["成绩"][i][j] + '</td>';                //获取得分详情
                            }
                            scoretable += "</tr>";
                        }
                        scoretable += "</tr>";
                        var avgscore = courseteachergetavg();
                        scoretable += "<tr><td style='color: red' colspan='" + tdcount + "'>平均分：" + avgscore + "</td></tr>";
                        scoretable += "</table>";


                        $("#scoretable").html(scoretable);
                    }

                }
            })

        }
        function courseteachergetavg(){
            var result;
            $.ajax({
                type:"POST",
                url:"CourseTeacherGetAvg.action",
                dataType:"json",
                async:false,
                success:function (data) {
                    result = data;
                }
            })
            return result;
        }
        $(function () {
            queryclasspaper();
            queryclass();
        })
    </script>
</head>
<body>

<form>
    <div style="font-family: 华文行楷;font-size: 30px" align="center">
        <select style="height:30px;width: 350px" name="Batch" id="batch"></select>
        <select style="height:30px;width: 150px "name="Classno" id="classno"></select>
        &nbsp;
        <input type="button" style="background-color:rgb(255,159,245);border-color:rgb(28,4,50);height: 30px;width:100px ;" value="查询成绩" onclick="selectscore()">
    </div>
    <div id="scoretable" align="center"></div>
</form>
</body>
</html>
