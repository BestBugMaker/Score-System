<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2018-12-06
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师权限选择页面</title>
</head>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
    function verifyjob() {
        $.ajax({
            type:"POST",
            url:"getteacheriob.action",
            dataType:"json",
            success:function (data) {
                for (var i = 0; i<data.length; i++){
                    if (data[i] == "00")
                        document.getElementById("authority2").style.display = "block";
                    else if (data[i] == "21")
                        document.getElementById("authority3").style.display = "block";
                    else if (data[i] == "22")
                        document.getElementById("authority4").style.display = "block";
                    else if (data[i] == "23")
                        document.getElementById("authority5").style.display = "block";
                    else if (data[i] == "24")
                        document.getElementById("authority6").style.display = "block";
                    else
                        document.getElementById("authority1").style.display = "block";
                }
            }
        })
    }
    $(function () {
        verifyjob();
    })
    </script>
<body>
    <br>
    <div style="text-align: center">
        <div align="center" id="authority1" style="text-align:center;width: 100%;height: 15%;display: none">
            <table align="center">
                <tr align="center">
                    <td align="center"><a href="任课教师/任课教师功能选择界面.jsp" target="menu">
                        <button style="height: 50px;width: 100px;background-color:rgb(255,159,245);border-color:rgb(28,4,50)">任课教师</button>
                    </a></td>
                </tr>
            </table>
        </div>
        <div align="center" id="authority2" style="text-align:center;width: 100%;height: 15%;display: none">
            <table align="center">
                <tr align="center">
                    <td align="center"><a href="班主任/班主任功能选择界面.jsp" target="menu">
                        <button style="height: 50px;width: 100px;background-color:rgb(85,182,255);border-color:rgb(28,4,50)">班主任</button>
                    </a></td>
                </tr>
            </table>
        </div>
        <div  align="center" id="authority3" style="text-align:center;width: 100%;height: 15%;display: none">
            <table align="center">
                <tr align="center">
                    <td align="center"><a href="学科组长/学科组长功能选择界面.jsp" target="menu">
                        <button style="height: 50px;width: 100px;background-color:rgb(159,177,255);border-color:rgb(28,4,50)">学科组长</button>
                    </a></td>
                </tr>
            </table>
        </div>
        <div align="center" id="authority4" style="text-align:center;width: 100%;height: 15%;display: none">
            <table align="center">
                <tr align="center">
                    <td align="center"><a href="年级组长/年级组长功能选择界面.jsp" target="menu">
                        <button style="height: 50px;width: 100px;background-color:rgb(45,242,255);border-color:rgb(28,4,50)">年级组长</button>
                    </a></td>
                </tr>
            </table>
        </div>
        <div align="center" id="authority5" style="text-align:center;width: 100%;height: 15%;display: none">
            <table align="center">
                <tr align="center">
                    <td align="center"><a href="教务员/教务员功能选择界面.jsp" target="menu">
                        <button style="height: 50px;width: 100px;background-color:rgb(255,207,63);border-color:rgb(28,4,50)">教务员</button>
                    </a></td>
                </tr>
            </table>
        </div>
        <div align="center" id="authority6" style="text-align:center;width: 100%;height: 15%;display: none">
            <table align="center">
                <tr align="center">
                    <td align="center"><a href="教务主任/教务主任功能选择界面.jsp" target="menu">
                        <button style="height: 50px;width: 100px;background-color:rgb(147,242,255);border-color:rgb(28,4,50)">教务主任</button>
                    </a></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
