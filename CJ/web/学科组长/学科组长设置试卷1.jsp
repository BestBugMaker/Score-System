<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/11/24
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>学科组长设置试卷1</title>
      <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
      <script>
          function getschoolyear() {
              $.ajax({
                  type:"POST",
                  url:"getschoolyear.action",
                  dataType:"json",
                  success:function (data) {
                      $("#schoolyear").html(data);
                  }
              })
          }
          function getgrade() {
              $.ajax({
                  type:"POST",
                  url:"getgrade.action",
                  dataType:"json",
                  success:function (data) {
                      $("#grade").html(data);
                  }
              })
          }
          function getsubject() {
              var schoolyear = $("#schoolyear").val();
              var grade = $("#grade").val();
              $.ajax({
                  type:"POST",
                  url:"getteachersubject.action?schoolyear=" + schoolyear + "&grade=" + grade,
                  dataType:"json",
                  success:function (data) {
                      $("#subject").html(data);
                  }
              })
          }
          $(function () {
              getschoolyear();
              getgrade();
          });
      </script>
  </head>
  <body>
      <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
      <div align="center">
          <s:form action="submitexam">
              <select name="SchoolYear" id="schoolyear" onchange="getsubject()" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:160px;" size="auto"></select>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <select name="Semester" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px" size="auto">
                  <option>选择学期</option>
                  <option value = "1">第一学期</option>
                  <option value = "2">第二学期</option>
              </select>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <select name="Grade" id="grade" onchange="getsubject()" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:100px;" size="auto"></select>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <select name="Tclass" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:140px" size="auto">
                  <option>选择考试类型</option>
                  <option value="00">第一次月考</option>
                  <option value="01">期中考试</option>
                  <option value="02">第二次月考</option>
                  <option value="03">期末考试</option>
              </select>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <select name="Tquality" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:150px;" size="auto">
                  <option>选择考试性质</option>
                  <option value="0">非统考</option>
                  <option value="1">统考</option>
              </select>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <select name="Subject" id="subject" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px;width:80px;" size="auto"></select>
              <br /><br /><br /><br />
              <div align="center">
                  <input type="submit" style="background:url(../img/提交按钮.png); width:150px; height:46px;" value="">
              </div>
          </s:form>
      </div>
  </body>
</html>
