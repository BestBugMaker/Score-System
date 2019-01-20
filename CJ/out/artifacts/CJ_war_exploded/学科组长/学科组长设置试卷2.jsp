<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 10012
  Date: 2018/11/25
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学科组长设置试卷2</title>
</head>
<body>
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <div align="center" style="color:#FF6600;font-style:italic;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:18px">
        <s:form action="InsertPaperDetail"  theme="simple">
            <b>试卷编号：</b>
            <input name="ExamPaperNo" id="exampaperno" type="text" value="<s:property value="ExamPaperNo"/>" readonly="readonly">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <b>请输入大题数目：</b>
            <input name="LargeCount" type="text">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <b>请输入试卷总分：</b><input name="ExamTotalScore" type="text">
            <br /><br /><br /><br />
            <div align="center">
                <input type="submit" style="background:url(../img/提交按钮.png); width:150px; height:46px;" value="">
            </div>
        </s:form>
    </div>
</body>
</html>
