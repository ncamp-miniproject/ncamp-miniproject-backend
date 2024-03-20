<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
    <title>로그인</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/signInForm.js"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="login-form-container">
            <h2>로그인</h2>
            <form class="login-form" name="login-form">
                <div class="form-item">
                    <label class="form-label">아이디</label>
                    <input class="form-input" name="userId" type="text" placeholder="ID">
                </div>
                <div class="form-item">
                    <label class="form-label">패스워드</label>
                    <input class="form-input" name="password" type="password" placeholder="PASSWORD">
                </div>
                <div class="btn-box">
                    <button class="btn btn--sign-in" type="button">로그인</button>
                    <button class="btn btn--sign-up" type="button">회원가입</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
