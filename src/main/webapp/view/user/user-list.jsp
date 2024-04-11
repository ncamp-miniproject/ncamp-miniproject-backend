<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>회원 목록조회</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/userList.js"></script>

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

<body>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

<%--    <main data-context-path="${pageContext.request.contextPath}">--%>
<%--        <div class="inner-header">--%>
<%--            <h2 class="page-title">회원 목록 조회</h2>--%>
<%--            <form class="search">--%>
<%--                <select name="searchCondition" class="search-condition">--%>
<%--                    <option value="0" selected>회원 ID</option>--%>
<%--                    <option value="1">회원명</option>--%>
<%--                </select>--%>
<%--                <input type="text"--%>
<%--                       name="searchKeyword"--%>
<%--                       value=""--%>
<%--                       placeholder="검색어 입력">--%>
<%--                <button type="submit">검색</button>--%>
<%--            </form>--%>
<%--            <p>전체 ${total}건수, 현재 ${currentPage} 페이지</p>--%>
<%--        </div>--%>
<%--        <table class="list">--%>
<%--            <thead>--%>
<%--                <tr>--%>
<%--                    <td>No</td>--%>
<%--                    <td>회원 ID</td>--%>
<%--                    <td>회원명</td>--%>
<%--                    <td>이메일</td>--%>
<%--                </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--                <c:set var="no" value="${list.size()}" scope="page"/>--%>
<%--                <c:forEach var="data" items="${list}">--%>
<%--                    <tr>--%>
<%--                        <td>${no}</td>--%>
<%--                        <c:set var="no" value="${no - 1}" scope="page"/>--%>
<%--                        <td><a class="user-id" data-user-id="${data.userId}">${data.userId}</a></td>--%>
<%--                        <td>${data.userName}</td>--%>
<%--                        <td>${data.email}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--        <div class="page-nav" data-current-page="${currentPage}">--%>
<%--            <c:forEach var="i" begin="1" end="${totalPage}" step="1">--%>
<%--                <a class="page-nav-item" data-page="${i}">${i}</a>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </main>--%>
</body>
</html>