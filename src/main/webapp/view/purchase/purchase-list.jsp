<%@ page import="com.model2.mvc.purchase.domain.TranStatusCode" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>구매 목록조회</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/purchase/purchaseList.js"></script>

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

    <style>
        main {
            padding: 12px 120px;
        }

        .page-container {
            display: flex;
            justify-content: center;
        }

        .page-navigator {
            cursor: pointer;
        }
    </style>
</head>

<body data-context-path="${pageContext.request.contextPath}" data-menu="${menu}" data-user-role="${loginUser.role.role}" data-user-id="${loginUser.userId}">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">구매 목록 조회</h2>
        </div>
        <p>전체 <span id="count-display">0</span> 건수, 현재 <span id="current-page-display">0</span> 페이지</p>

        <c:import url="${pageContext.request.contextPath}/view/fragment/pageNumbers.jsp"/>

        <table class="table table-striped table-bordered purchase-table">
            <thead>
                <tr>
                    <th>No</th>
                    <th>구매자 ID</th>
                    <th>수령인 이름</th>
                    <th>연락처</th>
                    <th>배송현황</th>
                    <c:if test="${loginUser.role.role == 'admin'}">
                        <th>정보수정</th>
                    </c:if>
                </tr>
            </thead>
            <tbody id="purchase-item-list">
            </tbody>
        </table>
    </main>
</body>
</html>