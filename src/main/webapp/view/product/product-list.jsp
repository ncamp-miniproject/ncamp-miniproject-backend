<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>상품 목록조회</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/product/productList.js"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}" data-menu="${data.menuMode}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">상품 목록 조회</h2>
            <form class="search-form"
                  name="search"
                  data-search-condition="${data.searchInfo.searchCondition}"
                  data-search-keyword="${data.searchInfo.searchKeyword}">
                <select name="searchCondition" class="search-condition">
                    <option value="1">상품명</option>
                    <option value="2">상품가격</option>
                </select>
                <button type="submit">검색</button>
            </form>
            <p>전체 ${data.count}건수, 현재 ${data.pageInfo.currentPage} 페이지</p>
        </div>
        <div class="category-box" data-current-category-no="${data.currentCategoryNo}">
            <div class="category-display">
                <a data-category="all" class="category-item">모든 항목</a>
                <c:forEach var="category" items="${data.categories}">
                    <a data-page="${category}"
                       class="category-item"
                       data-category-no="${category.categoryNo}">
                        ${category.categoryName}
                    </a>
                </c:forEach>
            </div>
            <c:if test="${!empty user && user.role.role == 'admin' && data.menuMode == 'manage'}">
                <a href="${pageContext.request.contextPath}/categories/new-form">
                    카테고리 생성
                </a>
            </c:if>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <td>No</td>
                    <td>상품명</td>
                    <td>가격</td>
                    <td>등록일</td>
                    <td>재고</td>
                </tr>
            </thead>
            <tbody>
                <c:set var="no" value="${data.products.size()}" scope="page"/>
                <c:forEach var="product" items="${data.products}">
                    <tr>
                        <td>${product.prodNo}</td>
                        <td>${product.prodName}</td>
                        <td>${product.price}</td>
                        <td>${product.regDate}</td>
                        <td>${product.stock}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:set var="url" value="${pageContext.request.contextPath}/products" scope="request"/>
        <c:set var="additionalQueryString"
               value="&menu=${data.menuMode}&searchCondition=${data.searchInfo.searchCondition}&searchKeyword=${data.searchInfo.searchKeyword}"
               scope="request"/>
        <c:import var="pageNumbers"
                  url="${pageContext.request.contextPath}/view/fragment/pageNumbers.jsp"
                  scope="request"/>
        ${pageNumbers}
    </main>
</body>
</html>
