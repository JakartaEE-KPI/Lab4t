<%--
  Created by IntelliJ IDEA.
  User: liche
  Date: 3/24/2024
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <div>STUDENT PAGE</div>
</head>
<body>
<c:forEach var="mark" items="${marks}">
    <p>Mark ID: ${mark.id}</p>
    <p>Presence: ${mark.presence}</p>
    <p>Point: ${mark.point}</p>
    <p>Subject: ${mark.subject.title}</p>
    <p>Student: ${mark.student.name}</p>
    <hr>
</c:forEach>
</body>
</html>
