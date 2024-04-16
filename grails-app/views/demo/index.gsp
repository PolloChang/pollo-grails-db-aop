<%--
  Created by IntelliJ IDEA.
  User: jameschang
  Date: 4/15/24
  Time: 2:46 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div>
    <a href="${createLink(controller: "demo", action:"index" )}" >新增資料</a>
</div>
<div>
    <a href="${createLink(controller: "demo", action:"testSql" )}" >測試SQL</a>
</div>
</body>
</html>