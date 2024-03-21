<%@ page import="com.model2.mvc.purchase.domain.TranStatusCode" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>구매 목록조회</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/purchase/purchaseList.js"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">구매 목록 조회</h2>
        </div>
        <p>전체 ${data.count} 건수, 현재 ${data.pageInfo.currentPage} 페이지</p>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>구매자 ID</th>
                    <th>수령인 이름</th>
                    <th>연락처</th>
                    <th>배송현황</th>
                    <th>정보수정</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="purchase" items="${data.purchaseList}">
                    <tr class="data-row" data-tran-no="${purchase.tranNo}" data-buyer-id="${purchase.buyer.userId}">
                        <td><a class="tran-no">${purchase.tranNo}</a></td>
                        <td><a class="buyer-id">${purchase.buyer.userId}</a></td>
                        <td>${purchase.receiverName}</td>
                        <td>${purchase.receiverPhone}</td>
                        <td>${purchase.tranStatusCode.status}</td>
                        <td>
                            <c:if test="${data.loginUser.role.role == 'seller' && purchase.tranStatusCode.code == TranStatusCode.PURCHASE_DONE.code}">
                                <form action="${pageContext.request.contextPath}/purchases/tran-code/update" method="POST">
                                    <input type="hidden" name="tranNo" value="${purchase.tranNo}">
                                    <input type="hidden" name="tranCode" value="2">
                                    <input type="submit" value="배송하기">
                                </form>
                            </c:if>
                            <c:if test="${data.loginUser.role.role == 'seller' && purchase.tranStatusCode.code == TranStatusCode.IN_DELIVERY.code}">
                                <form action="${pageContext.request.contextPath}/purchases/tran-code/update" method="POST">
                                    <input type="hidden" name="tranNo" value="${purchase.tranNo}">
                                    <input type="hidden" name="tranCode" value="3">
                                    <input type="submit" value="물건도착">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:set var="url" value="${pageContext.request.contextPath}/purchases" scope="request" />
        <c:import var="pageNumbers" url="${pageContext.request.contextPath}/view/fragment/pageNumbers.jsp" scope="request" />
        ${ pageNumbers }
    </main>
</body>
</html>