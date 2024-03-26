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
            <h2 class="page-title">��ǰ���</h2>
        </div>
        <form name="purchase">
            <ul>
                <li>
                    <label>������ ���̵�</label>
                    <input type="text" name="buyerId" readonly>
                </li>
                <li>
                    <label for="payment-option">���� ���</label>
                    <select name="paymentOption" id="payment-option">
                        <c:forEach var="po" items="${paymentOptions}">
                            <option value="${po.code}">${po.paymentName}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label for="receiver-name">������ �̸�</label>
                    <input type="text" name="receiverName" id="receiver-name"/>
                </li>
                <li>
                    <label for="receiver-phone">������ ����ó</label>
                    <input type="text" name="receiverPhone" id="receiver-phone"/>
                </li>
                <li>
                    <label for="address">�ּ�</label>
                    <input type="text" name="divyAddr" id="address">
                </li>
                <li>
                    <label for="divy-request">��� ��û����</label>
                    <input type="text" name="divyRequest" id="divy-request">
                </li>
                <li>
                    <label for="divy-date">����������</label>
                    <input type="text" name="divyDate" id="divy-date" readonly>
                </li>
            </ul>
            <p>��ü ${data.productCount} ���� ǰ��</p>
            <table class="cart-info">
                <thead>
                    <tr>
                        <th>��ǰ��ȣ</th>
                        <th>��ǰ��</th>
                        <th>��ǰ �� ����</th>
                        <th>��������</th>
                        <th>����</th>
                        <th>�������</th>
                        <th>����</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div>
                <label>�Ѿ�</label>
                <p id="price-sum">0</p>
            </div>
            <div>
                <label>�Ѽ���</label>
                <p id="quantity-sum">0</p>
            </div>
            <div class="btn-box">
				<button type="button" class="btn btn--submit">����</button>
				<button type="button" class="btn btn--cancel">���</button>
			</div>
        </form>
    </main>
</body>
</html>