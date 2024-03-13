<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/categories/new" method="POST">
        Category name: <input type="text" name="categoryName">
        <input type="submit" value="Submit"/>
    </form>

</body>
</html>
