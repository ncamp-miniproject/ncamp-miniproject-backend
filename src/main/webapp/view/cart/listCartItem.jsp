<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>��ٱ���</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

    <div>

            <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15" height="37">
                        <img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
                    </td>
                    <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="93%" class="ct_ttl01">��ٱ��� ���</td>
                            </tr>
                        </table>
                    </td>
                    <td width="12" height="37">
                        <img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
                    </td>
                </tr>
            </table>

        <form name="cartForm" method="POST">

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td colspan="11" id="itemCount">��ü ${ data.itemCount }��</td>
                    <td colspan="11" id="priceSum">�Ѿ� ${ data.priceSum }��</td>
                </tr>
                <tr>
                    <td class="ct_list_b" width="100">No</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">��ǰ��</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">����</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">����</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">���</td>
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


            <button type="button" id="purchaseBtn">����</button>
            <button type="button" id="clear-cart-button">�ʱ�ȭ</button>

        </form>

    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>

    <script>
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
    </script>
</body>
</html>
