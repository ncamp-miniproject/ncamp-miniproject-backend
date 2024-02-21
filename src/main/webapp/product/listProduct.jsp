<%@ page import="com.model2.mvc.service.purchase.TranCode"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetProductList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

    <div style="width: 98%; margin-left: 10px;">

        <form name="detailForm" action="/listProduct.do?menu=search" method="post">

            <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15" height="37">
                        <img src="/images/ct_ttl_img01.gif" width="15" height="37" />
                    </td>
                    <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="93%" class="ct_ttl01">상품 목록조회</td>
                            </tr>
                        </table>
                    </td>
                    <td width="12" height="37">
                        <img src="/images/ct_ttl_img03.gif" width="12" height="37" />
                    </td>
                </tr>
            </table>


            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>

                    <td align="right">
                        <select name="searchCondition" class="ct_input_g" style="width: 80px">
                            <option value="0" ${ searchInfo.searchCondition == "0" ? "selected" : "" }>상품번호</option>
                            <option value="1" ${ searchInfo.searchCondition == "1" ? "selected" : "" }>상품명</option>
                            <option value="2" ${ searchInfo.searchCondition == "2" ? "selected" : "" }>상품가격</option>
                        </select> <input type="text" name="searchKeyword" class="ct_input_g" value="${ searchInfo.searchKeyword }"
                            style="width: 200px; height: 19px" />
                    </td>


                    <td align="right" width="70">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="17" height="23">
                                    <img src="/images/ct_btnbg01.gif" width="17" height="23">
                                </td>
                                <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                                    <a href="javascript:fncGetProductList();">검색</a>
                                </td>
                                <td width="14" height="23">
                                    <img src="/images/ct_btnbg03.gif" width="14" height="23">
                                </td>
                            </tr>
                        </table>
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
                    <td class="ct_list_b" width="150">상품명</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">가격</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">등록일</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">현재상태</td>
                </tr>
                <tr>
                    <td colspan="11" bgcolor="808285" height="1"></td>
                </tr>

                <c:set var="number" value="${ pageInfo.pageSize }" scope="page" />
                <c:forEach var="data" items="${ productList }">
                    <tr class="ct_list_pop">
                        <td align="center">${ number }</td>
                        <c:set var="number" value="${ number - 1 }" scope="page" />
                        <td></td>
                        <td align="left">
                            <c:choose>
                                <c:when test="${ data.proTranCode == TranCode.PURCHASEABLE.getCode() }">
                                    <a href="/getProduct.do?prodNo=${ data.prodNo }&menu=${ menuMode }">${ data.prodName }</a>
                                </c:when>
                                <c:otherwise>
                                    ${ data.prodName }
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td></td>
                        <td align="left">${ data.price }</td>
                        <td></td>
                        <td align="left">${ data.regDate }</td>
                        <td></td>
                        <td align="left">${ data.proTranStatus }
                            <c:if test="${ data.proTranCode == TranCode.PURCHASE_DONE.getCode() && menuMode == 'manage' }">
                                <a href="/updateTranCodeByProd.do?prodNo=${ data.prodNo }&tranCode=2">배송하기</a>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
                    </tr>
                </c:forEach>
            </table>

            <c:set var="url" value="/listProduct.do" scope="request" />
            <c:set var="additionalQueryString" value="&menu=${ menuMode }&searchCondition=${ searchInfo.searchCondition }&searchKeyword=${ searchInfo.searchKeyword }" scope="request" />
            <c:import var="pageNumbers" url="/common/pageNumbers.jsp" scope="request" />
            ${ pageNumbers }
            <!--  페이지 Navigator 끝 -->

        </form>

    </div>
</body>
</html>
