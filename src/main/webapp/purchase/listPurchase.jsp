<%@ page import="com.model2.mvc.purchase.domain.TranCode" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

    <div style="width: 98%; margin-left: 10px;">

        <form name="detailForm" action="/listUser.do" method="post">

            <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15" height="37">
                        <img src="/images/ct_ttl_img01.gif" width="15" height="37">
                    </td>
                    <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="93%" class="ct_ttl01">구매 목록조회</td>
                            </tr>
                        </table>
                    </td>
                    <td width="12" height="37">
                        <img src="/images/ct_ttl_img03.gif" width="12" height="37">
                    </td>
                </tr>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td colspan="11">전체 ${ count } 건수, 현재 ${ pageInfo.currentPage } 페이지</td>
                </tr>
                <tr>
                    <td class="ct_list_b" width="100">No</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">회원ID</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">회원명</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">전화번호</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">배송현황</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">정보수정</td>
                </tr>
                <tr>
                    <td colspan="11" bgcolor="808285" height="1"></td>
                </tr>

                <c:forEach var="purchase" items="${ purchaseList }">
                    <tr class="ct_list_pop">
                        <td align="center">
                            <a href="/getPurchase.do?tranNo=${ purchase.tranNo }">${ purchase.tranNo }</a>
                        </td>
                        <td></td>
                        <td align="left">
                            <a href="/getUser.do?userId=${ loginUser.userId }">${ loginUser.userId }</a>
                        </td>
                        <td></td>
                        <td align="left">${ purchase.receiverName }</td>
                        <td></td>
                        <td align="left">${ purchase.receiverPhone }</td>
                        <td></td>
                        <td align="left">${ purchase.tranStatus }</td>
                        <td></td>
                        <td align="left">
                            <c:if test="${ purchase.tranCode == TranCode.IN_DELIVERY.code }">
                                <a href="/updateTranCode.do?tranNo=${ purchase.tranNo }&tranCode=3">물건도착</a>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
                    </tr>
                </c:forEach>

            </table>

            <c:set var="url" value="/listPurchase.do" scope="request" />
            <c:import var="pageNumbers" url="/common/pageNumbers.jsp" scope="request" />
            ${ pageNumbers }

            <!--  페이지 Navigator 끝 -->
        </form>

    </div>

</body>
</html>