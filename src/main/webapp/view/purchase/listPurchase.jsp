<%@ page import="com.model2.mvc.purchase.domain.TranStatusCode" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

    <div style="width: 98%; margin-left: 10px;">

        <form name="detailForm" action="${pageContext.request.contextPath}/users" method="post">

            <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15" height="37">
                        <img src="${pageContext.request.contextPath}/images/ct_ttl_img01.gif" width="15" height="37">
                    </td>
                    <td background="${pageContext.request.contextPath}/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="93%" class="ct_ttl01">구매 목록조회</td>
                            </tr>
                        </table>
                    </td>
                    <td width="12" height="37">
                        <img src="${pageContext.request.contextPath}/images/ct_ttl_img03.gif" width="12" height="37">
                    </td>
                </tr>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td colspan="11">전체 ${ data.count } 건수, 현재 ${ data.pageInfo.currentPage } 페이지</td>
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

                <c:forEach var="purchase" items="${ data.purchaseList }">
                    <tr class="ct_list_pop">
                        <td align="center">
                            <a href="/purchases/${ purchase.tranNo }">${ purchase.tranNo }</a>
                        </td>
                        <td></td>
                        <td align="left">
                            <a href="/users/${ purchase.buyer.userId }">${ purchase.buyer.userId }</a>
                        </td>
                        <td></td>
                        <td align="left">${ purchase.receiverName }</td>
                        <td></td>
                        <td align="left">${ purchase.receiverPhone }</td>
                        <td></td>
                        <td align="left">${ purchase.tranStatusCode.status }</td>
                        <td></td>
                        <td align="left">
                            <c:if test="${ data.loginUser.role == 'admin' && purchase.tranStatusCode.code == TranStatusCode.PURCHASE_DONE.code }">
                                <form action="${pageContext.request.contextPath}/purchases/tran-code/update" method="POST">
                                    <input type="hidden" name="tranNo" value="${purchase.tranNo}">
                                    <input type="hidden" name="tranCode" value="2">
                                    <input type="submit" value="배송하기">
                                </form>
                            </c:if>
                            <c:if test="${ data.loginUser.role == 'user' && purchase.tranStatusCode.code == TranStatusCode.IN_DELIVERY.code }">
                                <form action="${pageContext.request.contextPath}/purchases/tran-code/update" method="POST">
                                    <input type="hidden" name="tranNo" value="${purchase.tranNo}">
                                    <input type="hidden" name="tranCode" value="3">
                                    <input type="submit" value="물건도착">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
                    </tr>
                </c:forEach>

            </table>

            <c:set var="url" value="${pageContext.request.contextPath}/purchases" scope="request" />
            <c:import var="pageNumbers" url="${pageContext.request.contextPath}/view/fragment/pageNumbers.jsp" scope="request" />
            ${ pageNumbers }

            <!--  페이지 Navigator 끝 -->
        </form>

    </div>

</body>
</html>