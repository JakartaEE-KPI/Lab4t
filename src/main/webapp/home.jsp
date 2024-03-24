<%--
  Created by IntelliJ IDEA.
  User: ASUS ROG STRIX
  Date: 23.03.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>Welcome to the Home Page</h1>
<form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
