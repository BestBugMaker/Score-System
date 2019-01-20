<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/8
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学科组长成绩查询1</title>
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
        function selectscore() {
            var batch = $("#batch").val();
            $.ajax({
                type:"POST",
                url:"SubjectLeaderGetScore.action?batch=" + batch,
                dataType:"json",
                success:function (data) {
                    if (data == "{}"){
                        alert("该批次成绩还未发布！");
                    }
                    else {
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="100%"  border="1" cellspacing="0" cellpadding="0">';
                        scoretable += "<tr><td colspan='2'></td>";
                        for(var i in result){
                            if(i != "成绩"){
                                //alert(i);
                                var length = 0;
                                for(var j in result[i] ){
                                    length++;
                                }
                                scoretable += '<td colspan="' + length + '">' + i +'</td>'; //获取题目名
                            }
                        }
                        scoretable += "<td>" + "总分" + "</td>";
                        scoretable += "</tr>";
                        scoretable += "<tr><td>" + "学号" + "</td><td>" + "姓名" + "</td>";

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
                        var scoreavg = 0;
                        for (var i in result["成绩"]){
                            for (var j = 0;j < result["成绩"][i].length;j++){
                                scoretable += '<td>' + result["成绩"][i][j] + '</td>';                //获取得分详情
                                if(j == result["成绩"][i].length -1){
                                    scoreavg += parseFloat(result["成绩"][i][j]);
                                }
                            }
                            scoretable += "</tr>";
                        }
                        scoretable += "</tr>";
                        scoreavg = scoreavg/result["成绩"].length;
                        scoretable += "<tr><td style='color: red' colspan='" + result["成绩"][0].length + "'>平均分：" + scoreavg.toFixed(2) + "</td></tr>";
                        scoretable += "</table>";


                        $("#scoretable").html(scoretable);
                    }

                }
            })

        }
        $(function () {
            queryclasspaper();

        })
    </script>
</head>
<body>
<form>
    <div align="center">
        <select style="width: 350px;" name="Batch" id="batch"></select>
        <input type="button" value="查询成绩" onclick="selectscore()">
    </div>
    <div id="scoretable" align="center"></div>
</form>

</body>
</html>
