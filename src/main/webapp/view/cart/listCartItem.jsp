<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>장바구니</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/admin.css" type="text/css">

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

    <script>
        $(() => {
            const cartForm = $("form[name=cartForm]");
            $("#purchaseBtn").on("click", () => {
                cartForm
                    .attr("method", "GET")
                    .attr("action", "/purchases/new-form")
                    .trigger("submit");
            });

            $("#clear-cart-button").on("click", () => {
                cartForm
                    .attr("method", "POST")
                    .attr("action", "/cart/items/clear")
                    .trigger("submit")
            });
        });
    </script>

    <style>
        main {
            width: 1024px;
        }

        .summary {
            font-size: 14px;
            float: right;
            margin-right: 24px;
        }
    </style>

</head>

<body bgcolor="#ffffff" text="#000000">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="page-header">
            <h2 class="page-title">장바구니</h2>
        </div>
        <p class="summary">총액 ${data.priceSum}원</p>
        <p class="summary">전체 ${data.itemCount}개</p>
    </main>
    <div class="cart-list container-fluid">

        <form name="cartForm" method="POST">

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">

                <tr>
                    <td class="ct_list_b" width="100">No</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">상품명</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">가격</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">개수</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">재고</td>
                </tr>
                <tr>
                    <td colspan="11" bgcolor="808285" height="1"></td>
                </tr>

                <c:set var="number" value="${ data.itemCount }" scope="page"/>
                <c:forEach var="product" items="${ data.productsInCart }">
                    <tr class="ct_list_pop">
                        <td align="center">${ number }</td>
                        <c:set var="number" value="${ number - 1 }" scope="page"/>
                        <td></td>
                        <td align="left">
                            <a href="/getProduct.do?prodNo=${ product.key.prodNo }&menu=search">${ product.key.prodName }</a>
                        </td>
                        <td></td>
                        <td align="left" id="price-${product.key.prodNo}">${ product.key.price }</td>
                        <td></td>
                        <td align="left">
                            <input type="number"
                                   id="quantity-${product.key.prodNo}"
                                   value="${ product.value }"
                            >
                        </td>
                        <td></td>
                        <td align="left">${product.key.stock}</td>
                    </tr>
                    <tr>
                        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
                    </tr>
                </c:forEach>
            </table>


            <button type="button" id="purchaseBtn">구매</button>
            <button type="button" id="clear-cart-button">초기화</button>

        </form>

    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>


</body>
</html>
