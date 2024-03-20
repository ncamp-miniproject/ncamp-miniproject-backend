<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>

    <header>
        <div class="header-menu">
            <c:if test="${empty user}">
                <form action="${pageContext.request.contextPath}/users/account/sign-in" method="GET">
                    <input type="submit" value="login">
                </form>
            </c:if>
            <c:if test="${!empty user}">
                <form action="${pageContext.request.contextPath}/users/account/sign-out" method="POST">
                    <input type="submit" value="logout"/>
                </form>
            </c:if>
        </div>
        <h1 class="page-title">Model 2 MVC Shop</h1>
    </header>

</body>
