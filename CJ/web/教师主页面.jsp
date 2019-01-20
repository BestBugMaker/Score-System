<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2018-12-06
  Time: 00:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师主页面</title>
    <script>
        function play(){
            var myaudio = document.getElementById('music');
            myaudio.play();
        }
        function pause(){
            var myaudio = document.getElementById('music');
            myaudio.pause();
        }
    </script>
    <script type="text/javascript">
        function showTime() {
            var t = new Date();
            var year = t.getFullYear();
            var month = t.getMonth();
            var day = t.getDate();
            var week = t.getDay();
            var arr = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
            var hour = t.getHours();
            var minute = t.getMinutes();
            var second = t.getSeconds();
            var showTime = year+"/"+month+"/"+day+" "+arr[week]+" "+hour+((minute<10)?":0":":")+minute+((second<10)?":0":":")+second+((hour>12)?".pm":".am");
            document.getElementById("clock").value=showTime;
        }
        setInterval("showTime()",1000);
    </script>
</head>
    <body style="background:url(img/背景图片.jpg); background-attachment: fixed; background-repeat: no-repeat; background-size: cover">
        <h1 align="center" style="background-color: rgba(0,0,0,0.37);background-size: cover;color:  white" >教务成绩系统</h1>
        <b>Welcome to our system! Now is</b> <input type="text" id="clock" style="font-family: Consolas;font-size: 15px;font-weight:bold;border: none;width:300px;background:none">
        <table width="100%" height="500px" border="1">
            <tbody>
            <tr align="center">
                <td width="20%"><iframe style="background-color: rgba(247,245,246,0.70)" name="menu" width="100%" height="100%" src="教师权限选择页面.jsp" align="middle"></iframe></td>
                <td><iframe style="background-color: rgba(247,245,246,0.70)" name="ifa1" width="100%" height="100%" src="欢迎页面.jsp"></iframe></td>
            </tr>
            </tbody>
        </table>
        <br><br><br>
        <div align="center">
            <!--播放器-->
            <audio id="music" src="../music/恋爱循环.mp3" hidden="true" autoplay="true" loop="true" ></audio>
            <img name="button2" src="../img/播放按钮.jpg" onclick="play()" style="cursor:pointer;height:80px;width:80px;">
            <img style=" width:100px;height:100px;" src="../img/Constantine.jpg" >
            <img name="button1" src="../img/暂停按钮.jpg" onclick="pause()" style="cursor:pointer;height:80px;width:80px;">
        </div>
    </body>
</html>