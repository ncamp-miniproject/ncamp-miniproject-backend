<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
    <title>로그인</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/signInForm.js"></script>

	<link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
          crossorigin="anonymous">


    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css"
          integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
          crossorigin="anonymous">


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="page-header">
			<h1>로그인</h1>
		</div>
        <form name="login-form">
            <div class="form-group">
                <label for="user-id">아이디</label>
                <input class="form-control" name="userId" type="text" placeholder="ID" id="user-id">
            </div>
            <div class="form-group">
                <label for="password">패스워드</label>
                <input class="form-control" name="password" type="password" placeholder="PASSWORD" id="password">
            </div>
            <div class="btn-box">
                <button class="btn btn-default btn--sign-in" type="button">로그인</button>
                <button class="btn btn-default btn--sign-up" type="button">회원가입</button>
            </div>
        </form>
    </main>
</body>
</html>
