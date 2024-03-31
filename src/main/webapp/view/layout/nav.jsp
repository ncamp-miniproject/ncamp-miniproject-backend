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

<nav class="navbar navbar-default">

    <div class="container">
        <div class="navbar-header">
            <button type="button"
                    class="navbar-toggle collapsed"
                    data-toggle="collapse"
                    data-target="#navbar-target"
                    aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Model 2 MVC Shop</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-target">
            <ul class="nav navbar-nav">
                <c:if test="${!empty user}">
                    <li>
                        <a href="${pageContext.request.contextPath}/users/${user.userId}">����������ȸ</a>
                    </li>
                </c:if>
                <c:if test="${role == 'admin'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/users">ȸ��������ȸ</a>
                    </li>
                </c:if>
                <c:if test="${role == 'seller'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/products/add-form">�ǸŻ�ǰ���</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/products?menu=manage">�ǸŻ�ǰ����</a>
                    </li>
                </c:if>
                <li>
                    <a href="${pageContext.request.contextPath}/products?menu=search">��ǰ�˻�</a>
                </li>
                <li>
                    <a href="javascript:history()">�ֱ� �� ��ǰ</a>
                </li>
                <c:if test="${role == 'user'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/purchases?menu=search">�����̷���ȸ</a>
                    </li>
                </c:if>
                <c:if test="${role == 'admin'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/purchases?menu=manage">�Ǹ���ȸ</a>
                    </li>
                </c:if>
            </ul>
            <c:if test="${empty user}">
                <form class="navbar-form navbar-right"
                      action="${pageContext.request.contextPath}/users/account/sign-in"
                      method="GET">
                    <button type="submit" class="btn btn-default" id="sign-in-btn">�α���</button>
                </form>
                <form class="navbar-form navbar-right"
                      action="${pageContext.request.contextPath}/users/account/signup-form"
                      method="GET">
                    <button type="submit" class="btn btn-default" id="sign-up-btn">ȸ������</button>
                </form>
            </c:if>
            <c:if test="${!empty user}">
                <form class="navbar-form navbar-right"
                      action="${pageContext.request.contextPath}/users/account/sign-out"
                      method="POST">
                    <button type="submit" class="btn btn-danger">�α׾ƿ�</button>
                </form>
            </c:if>
            <ul class="nav navbar-nav navbar-right" style="padding-right: 20px">
                <li>
                    <a href="${pageContext.request.contextPath}/cart/items">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>