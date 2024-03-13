<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>구매상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

    <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="15" height="37">
                <img src="${pageContext.request.contextPath}/images/ct_ttl_img01.gif" width="15" height="37"/>
            </td>
            <td background="${pageContext.request.contextPath}/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">구매상세조회</td>
                        <td width="20%" align="right">&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37">
                <img src="${pageContext.request.contextPath}/images/ct_ttl_img03.gif" width="12" height="37"/>
            </td>
        </tr>
    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 13px;">
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                구매번호 <img src="${pageContext.request.contextPath}/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="105">${ purchaseData.tranNo }</td>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                구매자아이디 <img src="${pageContext.request.contextPath}/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.buyer.userId }</td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">구매방법</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.paymentOption }</td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">구매자이름</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.receiverName }</td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">구매자연락처</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.receiverPhone }</td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">구매자주소</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.divyAddr }</td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">구매요청사항</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.divyRequest }</td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">배송희망일</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.divyDate }</td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">주문일</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">${ purchaseData.orderDate }</td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
        <tr>
            <td class="ct_list_b" width="150">상품명</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b" width="150">가격</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">개수</td>
        </tr>
        <tr>
            <td colspan="11" bgcolor="808285" height="1"></td>
        </tr>

        <c:forEach var="tranProd" items="${ purchaseData.transactionProductions }">
        <tr class="ct_list_pop">
            <td align="left">
                <a href="/products/${ tranProd.product.prodNo }&menu=search">${ tranProd.product.prodName }</a>
            </td>
            <td></td>
            <td align="left">${ tranProd.product.price }</td>
            <td></td>
            <td align="left">${ tranProd.quantity }</td>
        </tr>
        <tr>
            <td colspan="11" bgcolor="D6D7D6" height="1"></td>
        </tr>
        </c:forEach>
    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
        <tr>
            <td width="53%"></td>
            <td align="right">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="17" height="23">
                            <img src="${pageContext.request.contextPath}/images/ct_btnbg01.gif" width="17" height="23"/>
                        </td>
                        <td background="${pageContext.request.contextPath}/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                            <a href="/purchases/${ purchaseData.tranNo }/update-form">수정</a>
                        </td>
                        <td width="14" height="23">
                            <img src="${pageContext.request.contextPath}/images/ct_btnbg03.gif" width="14" height="23"/>
                        </td>
                        <td width="30"></td>
                        <td width="17" height="23">
                            <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
                        </td>
                        <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                            <a href="javascript:history.go(-1);">확인</a>
                        </td>
                        <td width="14" height="23">
                            <img src="/images/ct_btnbg03.gif" width="14" height="23"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

</body>
</html>