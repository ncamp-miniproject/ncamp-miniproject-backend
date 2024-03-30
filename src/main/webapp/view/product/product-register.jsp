<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>상품등록</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/product/productRegister.js"
            type="text/javascript"></script>

    <link rel="stylesheet"
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

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>
    
    <main>
        <div class="inner-header">
            <h2 class="page-title">상품등록</h2>
        </div>
        <form:form name="product-register-form" modelAttribute="productDTO" enctype="multipart/form-data" id="product-register-form">
            <div class="form-item">
				<label class="form-label" for="prod-name">상품명</label>
                <form:input path="prodName" type="text" cssClass="form-input" id="prod-name"/>
			</div>
            <div class="form-item">
				<label class="form-label" for="prod-detail">상품상세정보</label>
                <form:input path="prodDetail" type="textarea" cssClass="form-input" id="prod-detail"/>
			</div>
            <div class="form-item">
				<label class="form-label" for="manu-date">제조일자</label>
                <form:input path="manuDate" type="text" cssClass="form-input" id="manu-date"/>
			</div>
            <div class="form-item">
				<label class="form-label" for="price">가격</label>
                <div>
                    <form:input path="price" type="number" cssClass="form-input" id="price"/>
                    <p>원</p>
                </div>
			</div>
            <div class="form-item">
				<label class="form-label">상품이미지</label>
                <button type="button" id="image-upload-window-button">이미지 선택</button>
                <div class="row" id="image-container">
                </div>
			</div>
            <div class="form-item">
				<label class="form-label" for="stock">재고</label>
                <div>
                    <form:input path="stock" type="number" cssClass="form-input" id="stock"/>
                    <p>개</p>
                </div>
			</div>
            <div class="form-item">
				<label class="form-label" for="categoryList">카테고리</label>
                <select name="categoryNo" id="categoryList">
                    <option value="-1">없음</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryNo}">${category.categoryName}</option>
                    </c:forEach>
                </select>
			</div>
            <div class="btn-box">
				<button type="button" class="btn btn--submit">등록</button>
				<button type="button" class="btn btn--cancel">취소</button>
			</div>
        </form:form>
    </main>
</body>
</html>