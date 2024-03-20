<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
	<title>회원가입</title>

	<link rel="stylesheet" href="/css/admin.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/signUpForm.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
	<c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>
    <main data-context-path="${pageContext.request.contextPath}">
        <div class="inner-header">
            <h2 class="page-title">회원가입</h2>
        </div>
		<form class="sign-up-form" name="sign-up">
			<div class="form-item">
				<label class="form-label" for="id-input">아이디</label>
				<input type="text" name="userId" class="form-input" id="id-input">
				<button type="button" class="btn btn--check-duplicate">ID 중복 확인</button>
			</div>
			<div class="form-item">
				<label class="form-label" for="pw-input">패스워드</label>
				<input type="password" name="password" class="form-input" id="pw-input">
			</div>
			<div class="form-item">
				<label class="form-label" for="pw-check-input">패스워드 재입력</label>
				<input type="password" class="form-input" id="pw-check-input">
			</div>
			<div class="form-item">
				<label class="form-label" for="username">이름</label>
				<input type="text" name="userName" class="form-input" id="username">
			</div>
			<div class="form-item">
				<label class="form-label" for="ssn">주민번호</label>
				<input type="text" name="ssn" class="form-input" id="ssn" placeholder="- 제외 13자리 입력">
			</div>
			<div class="form-item">
				<label class="form-label" for="address">주소</label>
				<input type="text" name="addr" class="form-input" id="address">
			</div>
			<div class="form-item">
				<label class="form-label">휴대전화번호</label>
				<select name="phone1" class="phone-number">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="018">018</option>
					<option value="019">019</option>
				</select>
				<input type="number" name="phone2" class="phone-number">
				-
				<input type="number" name="phone3" class="phone-number">
				<input type="hidden" name="phone">
			</div>
			<div class="form-item">
				<label class="form-label" for="email">이메일</label>
				<input class="form-input" type="text" name="email" id="email" value="${authenticatedEmail}" readonly>
			</div>
			<div class="form-item">
				<label class="form-label" for="role-select">역할</label>
				<select name="role" id="role-select">
					<option value="user" selected>유저</option>
					<option value="seller">판매자</option>
				</select>
			</div>

			<div class="btn-box">
				<button type="button" class="btn btn--submit">가입</button>
				<button type="button" class="btn btn--reset">초기화</button>
				<button type="button" class="btn btn--cancel">취소</button>
			</div>
		</form>
	</main>
</body>
</html>
