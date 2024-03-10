<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br>
<form action="hello-servlet" method="GET">
    <input name="text" placeholder="sex" type="text"/>
    <input type="submit" value="Отправить" onclick="return validateInput();">
</form>
<script>
    function validateInput() {
        var userInput = document.getElementsByName("text")[0].value;

        if (userInput.indexOf("<script") !== -1 || userInput.indexOf("</script") !== -1) {
            alert("Неприпустимі символи. Будь ласка, введіть безпечний текст.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>