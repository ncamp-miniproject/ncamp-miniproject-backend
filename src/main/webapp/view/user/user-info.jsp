<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>

<c:if test="${empty currentUser}">
	<c:set var="currentUser" value="${user}"/>
</c:if>

<html>
<head>
<title>회원정보조회</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
			integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
			crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/javascript/user/userInfo.js"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
	<c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
	<c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

	<main>
		<div class="inner-header">
			<h2 class="page-title">회원정보조회</h2>
		</div>
		<ul class="user-info">
			<li class="user-info-item">
				<label>아이디</label>
				<p>${currentUser.userId}</p>
			</li>
			<li class="user-info-item">
				<label>이름</label>
				<p>${currentUser.userName}</p>
			</li>
			<li class="user-info-item">
				<label>주소</label>
				<p>${currentUser.addr}</p>
			</li>
			<li class="user-info-item">
				<label>휴대전화번호</label>
				<p>${currentUser.phone}</p>
			</li>
			<li class="user-info-item">
				<label>이메일</label>
				<p>${currentUser.email}</p>
			</li>
			<li class="user-info-item">
				<label>가입일자</label>
				<p>${currentUser.regDate}</p>
			</li>
		</ul>

		<div class="button-container">
			<c:if test="${!empty user && currentUser.userId == user.userId}">
				<button class="btn btn--update" data-user-id="${currentUser.userId}">수정</button>
			</c:if>
			<button class="btn btn--back">뒤로</button>
		</div>
	</main>
</body>
</html>