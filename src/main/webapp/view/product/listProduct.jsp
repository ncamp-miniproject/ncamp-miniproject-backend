<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>��ǰ �����ȸ</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
    <style>
        .category-box {
            display: flex;
            flex-direction: row;
            gap: 8px;
            width: auto;
            align-items: center;
        }

        .category-item {
            font-size: 12px;
            width: fit-content;
        }
    </style>

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
                        <img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
                    </td>
                    <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="93%" class="ct_ttl01">��ǰ �����ȸ</td>
                            </tr>
                        </table>
                    </td>
                    <td width="12" height="37">
                        <img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
                    </td>
                </tr>
            </table>


            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>

                    <td align="right">
                        <select name="searchCondition" class="ct_input_g" style="width: 80px">
                            <option value="0" ${ data.searchInfo.searchCondition == "0" ? "selected" : "" }>��ǰ��ȣ</option>
                            <option value="1" ${ data.searchInfo.searchCondition == "1" ? "selected" : "" }>��ǰ��</option>
                            <option value="2" ${ data.searchInfo.searchCondition == "2" ? "selected" : "" }>��ǰ����</option>
                        </select> <input type="text"
                                         name="searchKeyword"
                                         class="ct_input_g"
                                         value="${ data.searchInfo.searchKeyword }"
                                         style="width: 200px; height: 19px"/>
                    </td>


                    <td align="right" width="70">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="17" height="23">
                                    <img src="/images/ct_btnbg01.gif" width="17" height="23">
                                </td>
                                <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                                    <a href="javascript:fncGetProductList();">�˻�</a>
                                </td>
                                <td width="14" height="23">
                                    <img src="/images/ct_btnbg03.gif" width="14" height="23">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            <div class="category-box">
                <c:if test="${data.currentCategoryNo == null}">
                    <p class="category-item">��� �׸�</p>
                </c:if>
                <c:if test="${data.currentCategoryNo != null}">
                    <a href="${pageContext.request.contextPath}/listProduct.do?page=1&menu=${data.menuMode}" class="category-item">
                        ��� �׸�
                    </a>
                </c:if>
                <c:forEach var="category" items="${data.categories}">
                    <c:if test="${category.categoryNo == data.currentCategoryNo}">
                        <p class="category-item">${category.categoryName}</p>
                    </c:if>
                    <c:if test="${category.categoryNo != data.currentCategoryNo}">
                        <a href="${pageContext.request.contextPath}/listProduct.do?page=1&menu=${data.menuMode}&categoryNo=${category.categoryNo}" class="category-item">
                            ${category.categoryName}
                        </a>
                    </c:if>
                </c:forEach>
            </div>

            <c:if test="${!empty user && user.role == 'admin' && data.menuMode == 'manage'}">
                <a href="${pageContext.request.contextPath}/addCategoryView.do">
                    ī�װ��� ����
                </a>
            </c:if>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td colspan="11">��ü ${ data.count } �Ǽ�, ���� ${ data.pageInfo.currentPage } ������</td>
                </tr>
                <tr>
                    <td class="ct_list_b" width="100">No</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">��ǰ��</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">����</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">�����</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">���</td>
                </tr>
                <tr>
                    <td colspan="11" bgcolor="808285" height="1"></td>
                </tr>

                <c:set var="number" value="${ data.pageInfo.pageSize }" scope="page"/>
                <c:forEach var="product" items="${ data.products }">
                    <tr class="ct_list_pop">
                        <td align="center">${ number }</td>
                        <c:set var="number" value="${ number - 1 }" scope="page"/>
                        <td></td>
                        <td align="left">
                            <c:choose>
                                <c:when test="${ product.stock != 0 }">
                                    <a href="/getProduct.do?prodNo=${ product.prodNo }&menu=${ data.menuMode }">${ product.prodName }</a>
                                </c:when>
                                <c:otherwise>
                                    ${ product.prodName }
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td></td>
                        <td align="left">${ product.price }</td>
                        <td></td>
                        <td align="left">${ product.regDate }</td>
                        <td></td>
                        <td align="left">${ product.stock }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
                    </tr>
                </c:forEach>
            </table>

            <c:set var="url" value="/listProduct.do" scope="request"/>
            <c:set var="additionalQueryString"
                   value="&menu=${ data.menuMode }&searchCondition=${ data.searchInfo.searchCondition }&searchKeyword=${ data.searchInfo.searchKeyword }"
                   scope="request"/>
            <c:import var="pageNumbers" url="/fragment/pageNumbers.jsp" scope="request"/>
            ${ pageNumbers }
            <!--  ������ Navigator �� -->

        </form>

    </div>
</body>
</html>