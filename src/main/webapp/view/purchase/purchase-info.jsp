<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>���Ż���ȸ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/purchase/purchaseInfo.js"></script>

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

        h4 {
            font-size: 14px;
        }
    </style>
</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="page-header">
            <h2>���� �� ��ȸ</h2>
        </div>
        <h4>���Ź�ȣ ${purchaseData.tranNo}</h4>
        <div class="panel panel-default">
            <div class="panel-heading">������ ���̵�</div>
            <div class="panel-body">${purchaseData.buyerId.userId}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">���Ź��</div>
            <div class="panel-body">${purchaseData.paymentOption.paymentName}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">������ �̸�</div>
            <div class="panel-body">${purchaseData.receiverName}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">������ ����ó</div>
            <div class="panel-body">${purchaseData.receiverPhone}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">�����</div>
            <div class="panel-body">${purchaseData.divyAddr}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">���� ��û ����</div>
            <div class="panel-body">${purchaseData.divyRequest}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">��������</div>
            <div class="panel-body">${purchaseData.divyDate}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">�ֹ���</div>
            <div class="panel-body">${purchaseData.orderDate}</div>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>��ǰ��</th>
                    <th>����</th>
                    <th>����</th>
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
            <button type="button" class="btn btn--update" data-tran-no="${purchaseData.tranNo}">����</button>
            <button type="button" class="btn btn--cancel">�ڷ�</button>
        </div>
    </main>
</body>
</html>