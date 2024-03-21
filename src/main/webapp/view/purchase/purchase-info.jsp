<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>구매상세조회</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/purchase/purchaseInfo.js"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">구매 상세 조회</h2>
        </div>
        <ul>
            <li>
                <label>구매번호</label>
                <p>${purchaseData.tranNo}</p>
            </li>
            <li>
                <label>구매자 아이디</label>
                <p>${purchaseData.buyer.userId}</p>
            </li>
            <li>
                <label>구매방법</label>
                <p>${purchaseData.paymentOption.paymentName}</p>
            </li>
            <li>
                <label>수령인 이름</label>
                <p>${purchaseData.receiverName}</p>
            </li>
            <li>
                <label>수령인 연락처</label>
                <p>${purchaseData.receiverPhone}</p>
            </li>
            <li>
                <label>배송지</label>
                <p>${purchaseData.divyAddr}</p>
            </li>
            <li>
                <label>구매 요청 사항</label>
                <p>${purchaseData.divyRequest}</p>
            </li>
            <li>
                <label>배송희망일</label>
                <p>${purchaseData.divyDate}</p>
            </li>
            <li>
                <label>주문일</label>
                <p>${purchaseData.orderDate}</p>
            </li>
        </ul>
        <table>
            <thead>
                <tr>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>개수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tranProd" items="${purchaseData.transactionProductions}">
                    <tr class="prod-item" data-prod-no="${tranProd.product.prodNo}">
                        <td><a class="prod-no">${tranProd.product.prodNo}</a></td>
                        <td>${tranProd.product.price}</td>
                        <td>${tranProd.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="btn-box">
            <button type="button" class="btn btn--update" data-tran-no="${purchaseData.tranNo}">수정</button>
            <button type="button" class="btn btn--cancel">뒤로</button>
        </div>
    </main>
</body>
</html>