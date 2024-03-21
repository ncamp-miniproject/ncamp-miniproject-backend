<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/javascript/product/productInfo.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

	<c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
	<c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

	<main>
		<div class="inner-header">
			<h2 class="page-title">상품상세조회</h2>
		</div>
		<ul class="product-info">
			<li>
				<label>상품번호</label>
				<p>${productData.prodNo}</p>
			</li>
			<li>
				<label>상품명</label>
				<p>${productData.prodName}</p>
			</li>
			<li>
				<label>상품이미지</label>
				<img src="${pageContext.request.contextPath}/images/uploadFiles/${productData.fileName}" alt="No image"/>
			</li>
			<li>
				<label>상품상세</label>
				<p>${productData.prodDetail}</p>
			</li>
			<li>
				<label>제조일자</label>
				<p>${productData.manuDate}</p>
			</li>
			<li>
				<label>가격</label>
				<p>${productData.price}</p>
			</li>
			<li>
				<label>등록일자</label>
				<p>${productData.regDate}</p>
			</li>
		</ul>
		<div class="button-container">
			<c:if test="${productData.purchasable}">
				<form name="cart">
					<input type="hidden" name="prodNo" value="${ productData.prodNo }">
					<input type="number" name="quantity" id="quantityInput" value="0">
					<button type="button" id="loadOnCart">장바구니</button>
				</form>
			</c:if>
			<button class="btn btn--back">뒤로</button>
		</div>
	</main>
</body>
</html>