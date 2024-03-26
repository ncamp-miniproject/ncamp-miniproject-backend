<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 목록조회</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/product/productList.js"></script><link rel="stylesheet"
                                                                                                      href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
                                                                                                      integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
                                                                                                      crossorigin="anonymous">

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css"
          integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
          crossorigin="anonymous">


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>

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
                <c:if test="${!empty data.currentCategoryNo}">
                    <input type="hidden" name="categoryNo" value="${data.currentCategoryNo}">
                </c:if>
                <input type="hidden" name="page" value="1">
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
                    <th>No</th>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>등록일</th>
                    <th>재고</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="no" value="${data.products.size()}" scope="page"/>
                <c:forEach var="product" items="${data.products}">
                    <tr class="item"
                        data-prod-no="${product.prodNo}"
                        data-stock="${product.stock}">
                        <td>
                            <a class="prod-no">
                                ${product.prodNo}
                            </a>
                        </td>
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
