<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/purchase/purchaseForm.js"
            type="text/javascript"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">상품등록</h2>
        </div>
        <form name="purchase">
            <ul>
                <li>
                    <label>구매자 아이디</label>
                    <input type="text" name="buyerId" value="${loginUser.userId}" readonly>
                </li>
                <li>
                    <label for="payment-option">구매 방법</label>
                    <select name="paymentOption" id="payment-option">
                        <c:forEach var="po" items="${paymentOptions}">
                            <option value="${po.code}">${po.paymentName}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label for="receiver-name">수령자 이름</label>
                    <input type="text" name="receiverName" value="${loginUser.userName}" id="receiver-name"/>
                </li>
                <li>
                    <label for="receiver-phone">수령인 연락처</label>
                    <input type="text" name="receiverPhone" value="${loginUser.phone}" id="receiver-phone"/>
                </li>
                <li>
                    <label for="address">주소</label>
                    <input type="text" name="divyAddr" value="${loginUser.addr}" id="address">
                </li>
                <li>
                    <label for="divy-request">배송 요청사항</label>
                    <input type="text" name="divyRequest" id="divy-request">
                </li>
                <li>
                    <label for="divy-date">배송희망일자</label>
                    <input type="text" name="divyDate" id="divy-date" readonly>
                </li>
            </ul>
            <p>전체 ${data.productCount} 종류 품목</p>
            <table>
                <thead>
                    <tr>
                        <th>상품번호</th>
                        <th>상품명</th>
                        <th>상품 상세 정보</th>
                        <th>제조일자</th>
                        <th>가격</th>
                        <th>등록일자</th>
                        <th>수량</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${data.productsToPurchase}">
                        <input type="hidden" name="tranProds" value="${product.key.prodNo}%DFS${product.value}">
                        <tr>
                            <td>${ product.key.prodNo }</td>
                            <td>${ product.key.prodName }</td>
                            <td>${ product.key.prodDetail }</td>
                            <td>${ product.key.manuDate }</td>
                            <td>${ product.key.price }</td>
                            <td>${ product.key.regDate }</td>
                            <td>${ product.value }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <label>총액</label>
                <p>${data.priceSum}</p>
            </div>
            <div>
                <label>총수량</label>
                <p>${data.quantitySum}</p>
            </div>
            <div class="btn-box">
				<button type="button" class="btn btn--submit">구매</button>
				<button type="button" class="btn btn--cancel">취소</button>
			</div>
        </form>
    </main>
</body>
</html>