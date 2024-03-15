<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/users/account/email-auth" method="POST">
        Email: <input type="email" name="emailAddress">
        <input type="submit" value="Send"/>
    </form>
</body>
</html>
