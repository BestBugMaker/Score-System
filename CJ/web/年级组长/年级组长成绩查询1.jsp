<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/12/8
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>年级组长成绩查询1</title>
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

        function getstudentscore() {                    //查询整个年级的成绩
            var batch = $("#batch").val();
            $.ajax({
                type:"POST",
                url:"gradeleadergetscore.action?batch=" + batch,
                dataType:"json",
                success:function (data) {
                    if(data == "{}"){
                        alert("该批次成绩还未发布！");
                    }
                    else {
                        document.getElementById("scoretable").value = "";
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="60%"  border="1" cellspacing="0" cellpadding="0">';
                        var avglist = new Array();
                            for(var i=0; i<result[0].length-3; i++){
                                avglist[i] = 0;
                            }
                        for (var i = 0;i < result.length;i++){
                            scoretable += "<tr>";
                            for (var j = 0; j < result[i].length; j++){
                                scoretable += "<td>"+result[i][j]+"</td>";
                                if(j>=2 && j< result[0].length - 1 && i>=1){
                                    avglist[j-2] += parseFloat(result[i][j]);
                                }
                            }
                            scoretable += "</tr>";
                        }
                        scoretable += "<tr><td colspan='2'>平均分</td>";
                        for(var i=0 ; i<avglist.length; i++){
                            var avgscore = avglist[i]/(result.length-1);
                            scoretable += "<td style='color: red'>" + avgscore.toFixed(2) + "</td>";
                        }
                        scoretable += "</table>";
                        $("#scoretable").html(scoretable);
                    }
                }
            })

        }

        function selectclass(){
            $.ajax({
                type:"POST",
                url:"gradeleadergetclass.action",
                dataType:"json",
                success:function (data) {
                    var result = JSON.parse(data);
                    var gradeclass = "<option>请选择班级</option>";
                    for (var key in result){
                        gradeclass += '<option value="' + key + '">' + result[key] + "</option>";
                    }
                    $("#classno").html(gradeclass);
                }
            })

        }

        function getclassgrade(){                           //按班级查询成绩
            var batch = $("#batch").val();
            var classno = $("#classno").val();
            $.ajax({
                type:"POST",
                url:"gradeleadergetclassscore.action?batch=" + batch + "&classno=" + classno,
                dataType:"json",
                success:function (data) {
                    if(data == "{}"){
                        alert("该批次成绩还未发布！");
                    }
                    else {
                        document.getElementById("scoretable").value = "";
                        var result = JSON.parse(data);
                        var scoretable = '</br></br></br><table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                            'align="center" width="60%"  border="1" cellspacing="0" cellpadding="0">';

                        for (var i = 0;i < result.length;i++){
                            scoretable += "<tr>";
                            for (var j = 0;j < result[i].length;j++){
                                scoretable += "<td>"+result[i][j]+"</td>";
                            }
                            scoretable += "</tr>";
                        }

                        scoretable += "</table>";
                        $("#scoretable").html(scoretable);

                    }
                }
            })
        }

        $(function () {
            queryclasspaper();
            selectclass();
        })
    </script>
</head>
<body>
<form>
    <div style="font-family: 华文行楷;font-size: 30px" align="center">
        <select style="height:30px;width: 350px" name="Batch" id="batch"></select>&nbsp;
        <input type="button" style="background-color:rgb(255,159,245);border-color:rgb(28,4,50);height: 30px;width:100px ;" value="查询成绩" onclick="getstudentscore()"><br />
        <select style="height: 30px;width: 150px" name="classno" id="classno"></select>&nbsp;
        <input type="button" style="background-color:rgb(255,159,245);border-color:rgb(28,4,50);height: 30px;width:100px ;" value="按班级查询" onclick="getclassgrade()">
    </div>
    <div id="scoretable" align="center"></div>
</form>
</body>
</html>
