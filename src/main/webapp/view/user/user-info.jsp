<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>회원정보조회</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">

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
				<p>${user.userId}</p>
			</li>
			<li class="user-info-item">
				<label>이름</label>
				<p>${user.userName}</p>
			</li>
			<li class="user-info-item">
				<label>주소</label>
				<p>${user.addr}</p>
			</li>
			<li class="user-info-item">
				<label>휴대전화번호</label>
				<p>${user.phone}</p>
			</li>
			<li class="user-info-item">
				<label>이메일</label>
				<p>${user.email}</p>
			</li>
			<li class="user-info-item">
				<label>가입일자</label>
				<p>${user.regDate}</p>
			</li>
		</ul>

		<div class="button-container">
			<button class="btn btn--update">수정</button>
			<button class="btn btn--back">뒤로</button>
		</div>
	</main>
</body>
</html>