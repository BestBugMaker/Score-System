<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="GB2312" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      <title>登录页面</title>
      <link rel='stylesheet' href='css/animations.css'>
      <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
      <style type='text/css'>
          .is-animate.style1 > div { animation-name: style1; }
          .is-animate > div {
              animation-duration: 1s;
              animation-fill-mode: both;
              animation-iteration-count: infinite;
          }
          .is-animate > div:nth-child(1) { animation-delay: 0.0s }
          .is-animate > div:nth-child(2) { animation-delay: 0.1s }
          .is-animate > div:nth-child(3) { animation-delay: 0.2s }
          .is-animate > div:nth-child(4) { animation-delay: 0.3s }
          .is-animate > div:nth-child(5) { animation-delay: 0.4s }
          .is-animate > div:nth-child(6) { animation-delay: 0.5s }
      </style>
      <style>
          .vintage4{
              color: gold;
              letter-spacing: 0;
              text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135 }
      </style>
      <style type="text/css">
          #form1 {
              clear: none;
              float: none;
              height: 180px;
              width: 250px;
              padding: 0px;
              margin-top: 100px;
              margin-right: auto;
              margin-bottom: auto;
              margin-left: 900px;
          }
      </style>
      <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
      <script>
          function login() {
              $.ajax({
                  type:"POST",
                  url:"login.action",
                  dataType:"json",
                  data:$("#form1").serialize(),
                  success:function (data) {
                      var result = JSON.parse(data);
                      var num = result["return"];
                      if (num == 100){
                          window.location.href = "学生主页面.jsp";
                      }
                      else if (num == 0){
                          document.getElementById("error").style.display = "block";
                          document.getElementById("account").value = "";
                          document.getElementById("password").value = "";
                      }
                      else{
                          var job = 1;
                          for (var i = 0; i<num.length; i++){
                              if ("-1" == num[i])   //判断用户名密码是否正确 -1存在则表示错误
                                  job = -1;
                          }
                          if (job < 0){
                              document.getElementById("error").style.display = "block";
                              document.getElementById("account").value = "";
                              document.getElementById("password").value = "";
                          }
                          else{
                              window.location.href = "教师主页面.jsp";
                          }
                      }
                  }
              })
          }
      </script>
  </head>
  <body style="background:url(img/背景图片.jpg); background-attachment: fixed; background-repeat: no-repeat; background-size: cover">
  </br></br></br>
  <div class='is-animate style1' align="center">
      <div class='vintage4' style="font-size:50px;color:#ff9900;float:left;margin-left:90px;margin-top:0px;">教</div>
      <div class='vintage4' style="font-size:50px;color:#ff9900;float:left;margin-left:90px;margin-top:0px;">务</div>
      <div class='vintage4' style="font-size:50px;color:#ff9900;float:left;margin-left:90px;margin-top:0px;">成</div>
      <div class='vintage4' style="font-size:50px;color:#ff9900;float:left;margin-left:90px;margin-top:0px;">绩</div>
      <div class='vintage4' style="font-size:50px;color:#ff9900;float:left;margin-left:90px;margin-top:0px;">系</div>
      <div class='vintage4' style="font-size:50px;color:#ff9900;float:left;margin-left:90px;margin-top:0px;">统</div>
  </div>
      <div align="center">
          <form id="form1" action="##" method="post" >
              <p>
                  <label>
                      账号:<input name="account" id="account" type="text" style="margin-left:15px;" />
                  </label>
              </p>
              <p>
                  <label>
                      密码:<input name="password" id="password" type="password" style="margin-left:15px;" />
                  </label>
              </p>
              <input type="button" onclick="login()"  value="登录" />
              </br>
              <div id="error" style="display: none;color: red"><b>账号或密码错误！</b></div>
          </form>
      </div>
  </body>
</html>
