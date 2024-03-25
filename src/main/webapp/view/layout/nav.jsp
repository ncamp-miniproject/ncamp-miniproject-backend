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

<nav>
    <ul class="nav-box user">
    <c:if test="${!empty user}">
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/users/${user.userId}">�������� ��ȸ</a>
        </li>
    </c:if>
    <c:if test="${role == 'admin'}">
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/users">ȸ��������ȸ</a>
        </li>
    </c:if>
    </ul>

    <ul class="nav-box products">
    <c:if test="${role == 'seller'}">
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/products/add-form">�ǸŻ�ǰ���</a>
        </li>
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/products?menu=manage">�ǸŻ�ǰ����</a>
        </li>
    </c:if>
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/products?menu=search">��ǰ�˻�</a>
        </li>
        <li class="nav-item">
            <a class="depth03" href="javascript:history()">�ֱ� �� ��ǰ</a>
        </li>
    </ul>
    <ul class="nav-box purchases">
    <c:if test="${role == 'user'}">
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/purchases?menu=search">�����̷���ȸ</a>
        </li>
    </c:if>
    <c:if test="${role == 'admin'}">
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/purchases?menu=manage">�Ǹ���ȸ</a>
        </li>
    </c:if>
        <li class="nav-item">
            <a class="depth03" href="${pageContext.request.contextPath}/cart/items">��ٱ���</a>
        </li>
    </ul>

</nav>