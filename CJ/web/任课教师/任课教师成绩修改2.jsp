<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/11
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任课教师成绩修改2</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function selectscore(){
            $.ajax({
                type:"POST",
                url:"TeacherUpdateSelectScore.action",
                dataType:"json",
                success:function (data) {
                    var result = JSON.parse(data);
                    var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                        'align="center" width="100%"  border="1" cellspacing="0" cellpadding="0">';
                    scoretable += "<tr><td rowspan='2'>" + "学号" + "</td><td rowspan='2'>" + "姓名" + "</td>";
                    for(var i in result){
                        if(i != "成绩"){
                            var length = 0;
                            for(var j in result[i] ){
                                length++;
                            }
                            scoretable += '<td colspan="' + length + '">' + i +'</td>'; //获取题目名
                        }
                    }
                    scoretable += "</tr>";
                    var count1 = 0;
                    for(var i in result){
                        if(i != "成绩"){
                            for(j in result[i]){
                                scoretable += "<td style='color: red' abbr='" + ++count1 + "'>" + result[i][j] + "</td>";  //获取小题总分
                            }
                        }
                    }
                    scoretable += "</tr><tbody id='scoretbody'><tr>";
                    var studentcount = 0 ;
                    for (var i in result["成绩"]){
                        studentcount = studentcount +1;
                        scoretable += "<td>" + result["成绩"][i][0] + "</td><td>" + result["成绩"][i][1] + "</td>";
                        var count2 = 0;
                        for (var j = 2; j < result["成绩"][i].length; j++){
                            scoretable += "<td><input id='" + studentcount + "," + ++count2 + "' " +
                                "onchange='checkinput(this.id, this.value)' style='width: 100%;height: 100%' type='text' value='" + result["成绩"][i][j] + "'/></td>";    //获取得分详情
                        }
                        scoretable += "</tr>";
                    }
                    scoretable += "</tr>";
                    scoretable += "</tbody></table>";
                    $("#scoretable").html(scoretable);
                }
            })
        }
        function checkinput(dataid, datavalue){
            var titleno = dataid.split(",")[1];
            var stat = '#scoretable td[abbr="' + titleno + '"]';
            var limit = $(stat).text();
            if(parseFloat(datavalue) < 0 || parseFloat(datavalue) > parseFloat(limit)){
                alert("分数超出范围!");
                document.getElementById(dataid).value="";
            }
        }
        function updatescore() {
            var scoretable = $("#scoretbody").children("tr");
            for (var i = 0; i < scoretable.length; i++) {
                var tdarr = scoretable.eq(i).find("td");
                var scorelist = []; //为当前考生生成成绩数组
                scorelist.push(tdarr.eq(0).text());
                for (var j = 2; j < tdarr.length; j++) {
                    var score = tdarr.eq(j).find('input').val();
                    scorelist.push(score);
                }
                //为每一个行成绩执行一次ajax，在后台插入成绩
                $.ajax({
                    type: "POST",
                    url: "UpdateScore.action?scorelist=" + scorelist,
                    dataType: "json",
                    success: function () {

                    }
                })
            }
            alert("成绩修改成功!");
            location.reload();
        }
        $(function () {
            selectscore();
        })
    </script>
</head>
<body>
    <div id="scoretable" align="center"></div>
    </br>
    </br>
    </br>
    <div align="center">
        <input style="background:url(../img/提交按钮.png); width:150px; height:46px;" type="button" value="" onclick="updatescore()">
    </div>
</body>
</html>
