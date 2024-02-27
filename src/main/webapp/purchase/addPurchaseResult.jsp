<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>

<body>

    <form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

        다음과 같이 구매가 되었습니다.

        <table border=1>
            <tr>
                <td>구매자아이디</td>
                <td>${ purchaseData.buyerId }</td>
                <td></td>
            </tr>
            <tr>
                <td>구매방법</td>
                <td>${ purchaseData.paymentOption }</td>
                <td></td>
            </tr>
            <tr>
                <td>구매자이름</td>
                <td>${ purchaseData.receiverName }</td>
                <td></td>
            </tr>
            <tr>
                <td>구매자연락처</td>
                <td>${ purchaseData.receiverPhone }</td>
                <td></td>
            </tr>
            <tr>
                <td>구매자주소</td>
                <td>${ purchaseData.divyAddr }</td>
                <td></td>
            </tr>
            <tr>
                <td>구매요청사항</td>
                <td>${ purchaseData.divyRequest }</td>
                <td></td>
            </tr>
            <tr>
                <td>배송희망일자</td>
                <td>${ purchaseData.divyDate }</td>
                <td></td>
            </tr>
        </table>

        <table width="600" border="0" cellspacing="0" cellpadding="0" align="center" style="margin-top: 13px;">
            <tr>
                <th class="ct_write">
                    상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
                </th>
                <th class="ct_write">
                    수량
                </th>
            </tr>

            <c:forEach var="tranProd" items="${purchaseData.transactionProductions}">
            <tr>
                <td class="ct_write01">${ tranProd.product.prodNo }</td>
                <td class="ct_write01">${ tranProd.quantity }</td>
            </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>