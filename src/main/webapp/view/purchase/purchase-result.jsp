<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">다음과 같이 구매가 되었습니다.</h2>
        </div>

        <ul>
            <li>
                <p>구매자 아이디</p>
                <p>${purchaseData.buyerId}</p>
            </li>
            <li>
                <p>구매 방법</p>
                <p>${purchaseData.paymentOption.paymentName}</p>
            </li>
            <li>
                <p>수령인 이름</p>
                <p>${purchaseData.receiverName}</p>
            </li>
            <li>
                <p>수령인 연락처</p>
                <p>${purchaseData.receiverPhone}</p>
            </li>
            <li>
                <p>주소</p>
                <p>${purchaseData.divyAddr}</p>
            </li>
            <li>
                <p>구매 요청 사항</p>
                <p>${purchaseData.divyRequest}</p>
            </li>
            <li>
                <p>배송 희망 일자</p>
                <p>${purchaseData.divyDate}</p>
            </li>
        </ul>
        <table>
            <thead>
                <th>상품번호</th>
                <th>수량</th>
            </thead>
            <tbody>
                <c:forEach var="tranProd" items="${purchaseData.transactionProductions}">
                    <tr>
                        <td>${tranProd.product.prodNo}</td>
                        <td>${tranProd.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>