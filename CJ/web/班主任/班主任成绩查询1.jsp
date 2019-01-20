<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/6
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班主任成绩查询1</title>
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
        function selectscore(){
            var batch = $("#batch").val();
            $.ajax({
                type:"POST",
                url:"HeadTeacherGetScore.action?batch=" + batch,
                dataType:"json",
                success:function (data) {
                    if(data == "{}"){
                        alert("该批次成绩还未发布！");
                    }
                    else{
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="100%"  border="1" cellspacing="0" cellpadding="0">';
                        for (var i = 0;i < result.length;i++){
                            scoretable += "<tr>";
                            for (var j = 0;j < result[i].length;j++){
                                scoretable += "<td>"+result[i][j]+"</td>";
                            }
                            scoretable += "</tr>";
                        }
                        var result = headteachergetavg();
                        scoretable += "<tr><td colspan='2'>班级平均分</td>";
                        for(var i in result){
                            scoretable += '<td style="color:red" >' + i + ":" + parseFloat(result[i]).toFixed(2) + "</td>";
                        }
                        scoretable += "</table>";
                        $("#scoretable").html(scoretable);

                    }
                }
            })
        }
        function headteachergetavg(){
            var result;
            $.ajax({
                type:"POST",
                url:"HeadTeacherGetAvg.action",
                dataType:"json",
                async:false,
                success:function (data) {
                   result =  JSON.parse(data);
                }
            })
            return result;
        }
        $(function () {
            queryclasspaper();
        });
    </script>
</head>
<body>
    <form>
        <div align="center" style="font-family: 华文行楷;font-size: 30px" align="center">
            <select style="height:30px;width: 350px" name="Batch" id="batch"></select>
            &nbsp;&nbsp;
            <input type="button" style="background-color:rgb(255,159,245);border-color:rgb(28,4,50);height: 30px;width:100px ;" value="查询成绩" onclick="selectscore()">
        </div>
        <div id="scoretable" align="center"></div>
    </form>
</body>
</html>
