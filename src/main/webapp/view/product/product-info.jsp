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

	<style>
		main {
			padding: 12px 120px;
		}

		h4 {
			font-size: 14px;
		}

		img {
			width: 160px;
		}
	</style>
</head>

<body data-context-path="${pageContext.request.contextPath}">
	<c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

	<main>
		<div class="page-header">
			<h2>상품상세조회</h2>
		</div>
		<h4>상품번호 ${productData.prodNo}</h4>
		<div class="panel panel-default">
			<div class="panel-heading">상품번호</div>
			<div class="panel-body">${productData.prodNo}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">상품명</div>
			<div class="panel-body">${productData.prodName}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">상품이미지</div>
			<img src="${pageContext.request.contextPath}/images/uploadFiles/${productData.fileName}" alt="No image"/>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">상품상세</div>
			<div class="panel-body">${productData.prodDetail}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">제조일자</div>
			<div class="panel-body">${productData.manuDate}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">가격</div>
			<div class="panel-body">${productData.price}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">등록일자</div>
			<div class="panel-body">${productData.regDate}</div>
		</div>
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