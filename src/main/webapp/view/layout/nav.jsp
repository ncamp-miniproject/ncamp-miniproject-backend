<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.user.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="role" value="" scope="page"/>
<c:if test="${!empty user}">
    <c:set var="role" value="${user.role.role}" scope="page"/>
</c:if>
<c:if test="${empty user}">
    <c:set var="role" value="none" scope="page"/>
</c:if>

<body>

    <nav>
        <ul class="nav-box user">
        <c:if test="${!empty user}">
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/users/${user.userId}">개인정보 조회</a>
            </li>
        </c:if>
        <c:if test="${role == 'admin'}">
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/users">회원정보조회</a>
            </li>
        </c:if>
        </ul>

        <ul class="nav-box products">
        <c:if test="${role == 'seller'}">
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/products/add-form">판매상품등록</a>
            </li>
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/products?menu=manage">판매상품관리</a>
            </li>
        </c:if>
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/products?menu=search">상품검색</a>
            </li>
            <li class="nav-item">
                <a class="depth03" href="javascript:history()">최근 본 상품</a>
            </li>
        </ul>
        <ul class="nav-box purchases">
        <c:if test="${role == 'currentUser'}">
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/purchases?menu=search">구매이력조회</a>
            </li>
        </c:if>
        <c:if test="${role == 'admin'}">
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/purchases?menu=manage">판매조회</a>
            </li>
        </c:if>
            <li class="nav-item">
                <a class="depth03" href="${pageContext.request.contextPath}/cart/items">장바구니</a>
            </li>
        </ul>

    </nav>
</body>