<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2018/12/4
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生成绩查询1</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function querystudentpaper() {
            $.ajax({
                type:"POST",
                url:"getstudentpaper.action",
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
        function selectscore(){
            var batch = $("#batch").val();
            $.ajax({
                type:"POST",
                url:"StudentGetScore.action?batch=" + batch,
                dataType:"json",
                success:function (data) {
                    if(data == "{}"){
                        alert("该批次成绩还未发布！");
                    }
                    else{
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="60%"  border="1" cellspacing="0" cellpadding="0">';
                        scoretable += '<tr><td>科目</td><td>分数</td></tr>';
                        for(var i in  result)
                            if(i != "总分及排名"){
                                for(var j in result[i]){
                                    scoretable += '<tr><td>' + j + '</td><td onclick="getdetail(this.abbr)" abbr="' + i + '">' + result[i][j] + '</td><tr>';
                                }
                            }
                        scoretable += '<tr><td colspan="2">总分:' + result["总分及排名"][0] +  '&nbsp;&nbsp;&nbsp;&nbsp;班级排名:' + result["总分及排名"][1] +
                        '&nbsp;&nbsp;&nbsp;&nbsp;年级排名:' + result["总分及排名"][2] + '</td></tr>';
                        scoretable += "</table>";
                        scoretable += '<h2 align="center">点击各科分数查看各科得分情况!</h2>';
                        $("#scoretable").html(scoretable);
                    }
                }
            })
        }
        function getdetail(exampaperno){
            $.ajax({
                type:"POST",
                url:"StudentGetDetail.action?exampaperno=" + exampaperno,
                dataType:"json",
                success:function (data) {
                    var result = JSON.parse(data);  //获取返回的json字符串
                    var detailtable = '<table id="detailtable" style="text-align:center; table-layout:fixed" ' +
                        'align="center" width="100%"  border="1" cellspacing="0" cellpadding="0">';
                    detailtable += '<tr><td></td>';
                    for(var i in result){
                        if(i!="分数"){
                            var length = 0;
                            for(var j in result[i] ){
                                length++;
                            }
                            detailtable += '<td colspan="' + length + '">' + i +'</td>'; //获取题目名
                        }
                    }
                    detailtable += "</tr>";
                    detailtable += '<tr><td>试卷详情</td>';
                    for(var i in result){
                        if(i != "分数"){
                            for(j in result[i]){
                                detailtable += '<td>' + result[i][j] + '</td>';  //获取题目得分
                            }
                        }
                    }
                    detailtable += '</tr><tr><td>得分详情</td>';
                    for(var i in result["分数"]){
                            detailtable += '<td style="color: red" >' + result["分数"][i] + '</td>';
                    }
                    detailtable += "</tr>";
                    detailtable += "</table></br><input type='button' style=\"background-color:rgb(255,228,223);border-color:rgb(28,4,50);height: 30px;width:25% ;\" onclick='createphoto()' value='获取成绩变化图'>";
                    $("#insertdetailtable").html(detailtable);    //将table写入dib内
                }
            })
        }
        function createphoto() {
            $.ajax({
                type:"POST",
                url:"getphoto.action",
                dataType:"json",
                success:function (data) {
                    alert("图片生成中，请稍等！");
                    alert("恭喜你，下载成功！")
                }
            })
        }
        $(function () {
            querystudentpaper();
        });
    </script>
</head>
<body>
    <form>
        <div style="font-family: 华文行楷;font-size: 30px" align="center">
            <select style="height: 30px;width: 350px;" name="Batch" id="batch"></select>
            &nbsp;&nbsp;
            <input type="button" style="background-color:rgb(255,159,245);border-color:rgb(28,4,50);height: 30px;width:100px ;" value="查询成绩" onclick="selectscore()">
        </div>
        <div id="scoretable" align="center"></div>
        <div id="insertdetailtable" align="center"></div>
    </form>
</body>
</html>
