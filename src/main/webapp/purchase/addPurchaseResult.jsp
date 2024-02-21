<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

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
                <td>물품번호</td>
                <td>${ purchaseData.purchaseProd.prodNo }</td>
                <td></td>
            </tr>
            <tr>
                <td>구매자아이디</td>
                <td>${ purchaseData.buyer.userId }</td>
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
    </form>

</body>
</html>