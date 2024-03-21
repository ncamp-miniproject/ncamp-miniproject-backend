<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>상품등록</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/product/productUpdate.js"
            type="text/javascript"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">상품수정</h2>
        </div>
        <form:form name="product-update-form" modelAttribute="data" enctype="multipart/form-data">
            <form:input path="prodNo" type="hidden"/>
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
				<label class="form-label" for="image">상품이미지</label>
                <form:input path="fileName" type="file" cssClass="form-input" id="image"/>
			</div>
            <div class="form-item">
				<label class="form-label" for="stock">재고</label>
                <div>
                    <form:input path="stock" type="number" cssClass="form-input" id="stock"/>
                    <p>개</p>
                </div>
			</div>
<%--            <div class="form-item">--%>
<%--				<label class="form-label" for="categoryList">카테고리</label>--%>
<%--                <select name="categoryNo" id="categoryList">--%>
<%--                    <option value="-1">없음</option>--%>
<%--                    <c:forEach var="category" items="${categoryList}">--%>
<%--                        <option value="${category.categoryNo}">${category.categoryName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--			</div>--%>
            <div class="btn-box">
				<button type="button" class="btn btn--submit">등록</button>
				<button type="button" class="btn btn--cancel">취소</button>
			</div>
        </form:form>
    </main>
</body>
</html>