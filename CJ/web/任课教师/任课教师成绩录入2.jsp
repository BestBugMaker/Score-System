<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/11/30
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任课教师成绩录入2</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        //获取成绩输入表
        function getscoretable(){
            var a;
            var b;
            $.ajax({
                type:"POST",
                url:"GetScoreTable.action",
                dataType:"json",
                success:function (data) {
                    var result = JSON.parse(data);  //获取返回的json字符串
                    var ExamPaperNo = result["试卷编号"];
                    var tdcount = result["列数"];
                    var scoretable = '<table id="scoretable" style="text-align:center; table-layout:fixed" ' +
                        'align="center" width="100%"  border="1" cellspacing="0" cellpadding="0">';
                    scoretable += '<tr><td colspan="' + tdcount + '">试卷编号:' + ExamPaperNo + '</td></tr>';
                    scoretable += "<tr><td><td>总人数</td>";
                    for(var i in result){
                        if(i!="试卷编号" && i!="列数" && i!="学生"){
                            var length = 0;
                            for(var j in result[i] ){
                                length++;
                            }
                            scoretable += '<td colspan="' + length + '">' + i +'</td>'; //获取题目名
                        }
                    }
                    scoretable += "</tr>";
                    scoretable += '<tr><td><td class="count" >';
                    var count1 = 0;  //为后续题目得分赋abbr
                    for(var i in result){
                        if(i!="试卷编号" && i!="列数" && i!="学生"){
                            for(j in result[i]){
                                scoretable += '<td abbr="' + ++count1 + '">' + result[i][j] + '</td>';  //获取题目得分
                            }
                        }
                    }
                    scoretable += '</tr><tbody id="scoretbody">';   //新建tbody，生成只包含成绩的行和列为后续成绩插入做准备
                    var studentcount = 0 ;
                    for(var i in result["学生"]){
                        studentcount = studentcount +1;
                        scoretable += '<tr id="' + studentcount + '"><td>' + i + '</td><td>' + result["学生"][i] + "</td>";
                        var count2 = 0;
                        for(var j=0; j<result["列数"]-2; j++ ){
                            scoretable += '<td><input style="width: 100%; height: 100%" type="text" id="' + studentcount + ',' + ++count2 + '" onchange="checkinput(this.id,this.value)"></td>';
                        }
                        scoretable += "</tr>";
                    }
                    scoretable += "</tbody></table>";
                    $("#insertscoretable").html(scoretable);    //将table写入dib内
                    $("#scoretable").find("td.count").text(studentcount + "," + tdcount);   //在总人数td中插入该班总人数
                    a = tdcount;
                    b = studentcount;
                }
            })
            var c = [];
            return c.push()
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
        //插入成绩
        function insertscore(){
            var scoretable = $("#scoretbody").children("tr");
            for (var i=0; i<scoretable.length; i++){
                var tdarr = scoretable.eq(i).find("td");
                var scorelist = []; //为当前考生生成成绩数组
                scorelist.push(tdarr.eq(0).text());
                for(var j=2; j<tdarr.length; j++ ){
                    var score = tdarr.eq(j).find('input').val();
                    scorelist.push(score);
                }
                //为每一个行成绩执行一次ajax，在后台插入成绩
                $.ajax({
                    type:"POST",
                    url:"InsertScore.action?scorelist=" + scorelist,
                    dataType:"json",
                    success:function () {}  //不需要返回值
                })
            }
            //成绩录入后先修改试卷状态为0
            $.ajax({
                type:"POST",
                url:"Set0.action",
                dataType:"json",
                success:function () {}  //不需要返回值
            })
            //成绩插入成功后跳转页面
            alert("成绩录入成功!");
            window.location.href = "../欢迎页面.jsp";
        }
        function RandomNumber() {
            var group = [];
            group = $("#scoretable").find("td.count").text().split(",");
            var a = group[0];
            var b = group[1];
            for(var i = 1; i <= a; i++) {
                for (var j = 1; j < b-1; j++) {
                    var stat = "#scoretable td[abbr=" + j + "]";
                    var max = $(stat).text();
                    var limit = parseFloat(max);
                    var id = i + "," + j;
                    document.getElementById(id).value=(Math.random()*limit).toFixed(1);
                }
            }
        }
        //加载即执行的函数
        $(function () {
            getscoretable();
        })
    </script>
</head>
<body>
    <form>
        <div id="insertscoretable"></div>
        </br></br>
        <div align="center">
            <input align="center" type="button" style="width:140px;height:40px;font-size:large;font-style: inherit;background-color:cornsilk;border-color: #2b669a;border-radius: 2px" onclick="RandomNumber()" value="自动生成成绩">
        </div>
        <div align="center" style="position: absolute;bottom: 0;left: 42%">
            <input style="background:url(../img/提交按钮.png); width:150px; height:46px;" type="button" value="" onclick="insertscore()">
        </div>
    </form>
</body>
</html>
