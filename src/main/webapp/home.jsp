<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<c:set var="userType" value="${sessionScope.userType}"/> <!-- Assume user type is student for demonstration -->

<h1>Welcome to the Home Page</h1>
<form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form>

<c:if test="${not empty userType}">
    <c:choose>
        <c:when test="${userType eq 'teacher'}">
            <h1>You are logged in as a teacher</h1>
            <form action="${pageContext.request.contextPath}/add/mark" method="POST">
                <input name="mark" placeholder="Mark" type="number"/>
                <input name="studentId" placeholder="student id" type="number"/>
                <input name="subjectId" placeholder="subject id" type="number"/>
                <label for="isPresent">Is present</label>
                <input type="checkbox" name="isPresent" id="isPresent">
                <input type="submit" value="Add mark">
            </form>

            <form action="${pageContext.request.contextPath}/update/mark" method="POST">
                <input name="markId" placeholder="Mark id" type="number"/>
                <input name="mark" placeholder="Mark" type="number"/>
                <label for="isPresent">Is present</label>
                <input type="checkbox" name="isPresent" id="isPresent2">
                <input type="submit" value="Update mark">
            </form>

            <form action="${pageContext.request.contextPath}/delete/mark" method="POST">
                <input name="markId" placeholder="Mark id" type="number"/>
                <input type="submit" value="Delete mark">
            </form>

            <form action="${pageContext.request.contextPath}/mark" method="GET">
                <input name="studentId" placeholder="student id" type="number"/>
                <input name="subjectId" placeholder="subject id" type="number"/>
                <input type="submit" value="find">
            </form>
        </c:when>
        <c:when test="${userType eq 'student'}">
            <h1>You are logged in as a student</h1>
            <form action="${pageContext.request.contextPath}/mark" method="GET">
                <input name="studentId" placeholder="student id" type="number"/>
                <input name="subjectId" placeholder="subject id" type="number"/>
                <input type="submit" value="find">
            </form>
        </c:when>
    </c:choose>
</c:if>
<c:if test="${empty userType}">
    <h1>User type is not defined</h1>
</c:if>
</body>
</html>
