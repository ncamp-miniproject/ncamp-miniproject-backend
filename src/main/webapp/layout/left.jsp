<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.user.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="role" value="" scope="page"/>
<c:if test="${!empty user}">
    <c:set var="role" value="${user.role}" scope="page"/>
</c:if>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
    function history() {
        popWin = window
            .open(
                "/history.jsp",
                "popWin",
                "left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
    }
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0"
      topmargin="0" marginwidth="0" marginheight="0">

    <table width="159" border="0" cellspacing="0" cellpadding="0">

        <!--menu 01 line-->
        <tr>
            <td valign="top">
                <table border="0" cellspacing="0" cellpadding="0" width="159">
                    <c:if test="${!empty user}">


                    <tr>
                        <td class="Depth03"><a
                                href="/getUser.do?userId=${user.userId}" target="rightFrame">개인정보조회</a></td>
                    </tr>
                    </c:if>
                    <c:if test="${role == 'admin'}">
                    <tr>
                        <td class="Depth03">
                            <a href="/listUser.do"
                               target="rightFrame">회원정보조회</a>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td class="DepthEnd">&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>


        <c:if test="${role == 'admin'}">


        <!--menu 02 line-->
        <tr>
            <td valign="top">
                <table border="0" cellspacing="0" cellpadding="0" width="159">
                    <tr>
                        <td class="Depth03"><a href="../product/addProductView.jsp;"
                                               target="rightFrame">판매상품등록</a></td>
                    </tr>
                    <tr>
                    <td class="Depth03"><a href="/listProduct.do?menu=manage"
                                           target="rightFrame">판매상품관리</a></td>
                    </tr>
                    <tr>
                        <td class="DepthEnd">&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
        </c:if>

        <!--menu 03 line-->
        <tr>
            <td valign="top">
                <table border="0" cellspacing="0" cellpadding="0" width="159">
                    <tr>
                        <td class="Depth03"><a href="/listProduct.do?menu=search"
                                               target="rightFrame">상 품 검 색</a></td>
                    </tr>
                    <tr>
                        <td class="Depth03"><a href="/listCart.do"
                                               target="rightFrame">장 바 구 니</a></td>
                    </tr>
                    <c:if test="${!empty user && role == 'user'}">
                    <tr>
                        <td class="Depth03"><a href="/listPurchase.do?menu=search"
                                               target="rightFrame">구매이력조회</a></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty user && role == 'admin'}">
                        <tr>
                            <td class="Depth03"><a href="/listPurchase.do?menu=manage"
                                                   target="rightFrame">판매조회</a></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="DepthEnd">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="Depth03"><a href="javascript:history()">최근 본
                                상품</a></td>
                    </tr>
                </table>
            </td>
        </tr>

    </table>
</body>
</html>