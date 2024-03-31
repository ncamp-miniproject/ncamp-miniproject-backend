<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>회원가입</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
			integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
			crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/signUpForm.js"></script>

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
			padding: 12px 36px;
		}

		.phone-input-group {
			display: flex;
			flex-direction: row;
			gap: 12px;
		}

		#phone1 {
			flex: 1;
		}

		#phone2 {
			flex: 3;
		}
	</style>
</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>
    <main data-context-path="${pageContext.request.contextPath}">
        <div class="page-header">
            <h2 class="page-title">회원가입</h2>
        </div>
		<form class="form-horizontal" name="sign-up">
			<div class="form-group" id="email-form">
				<label class="control-label col-sm-2" for="email">이메일</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="email" id="email">
				</div>
				<button type="button" class="btn col-sm-2" id="email-authentication">이메일 인증</button>
			</div>
			<div class="form-group form-item">
				<label class="form-label col-sm-2 control-label" for="id-input">아이디</label>
				<div class="col-sm-6">
					<input type="text" name="userId" class="form-input form-control col-sm-8" id="id-input">
				</div>
				<button type="button" class="btn btn--check-duplicate col-sm-2">ID 중복 확인</button>
			</div>
			<div class="form-group form-item">
				<label class="form-label control-label col-sm-2" for="pw-input">패스워드</label>
				<div class="col-sm-6">
					<input type="password" name="password" class="form-control form-input" id="pw-input">
				</div>
			</div>
			<div class="form-group form-item">
				<label class="control-label form-label col-sm-2" for="pw-check-input">패스워드 재입력</label>
				<div class="col-sm-6">
					<input type="password" class="form-control form-input" id="pw-check-input">
				</div>
			</div>
			<div class="form-group form-item">
				<label class="form-label control-label col-sm-2" for="username">이름</label>
				<div class="col-sm-6">
					<input type="text" name="userName" class="form-control form-input" id="username">
				</div>
			</div>
			<div class="form-group form-item">
				<label class="form-label control-label col-sm-2" for="ssn">주민번호</label>
				<div class="col-sm-6">
					<input type="text" name="ssn" class="form-input form-control" id="ssn" placeholder="- 제외 13자리 입력">
				</div>
			</div>
			<div class="form-group form-item">
				<label class="form-label control-label col-sm-2" for="address">주소</label>
				<div class="col-sm-6">
					<input type="text" name="addr" class="form-input form-control" id="address">
				</div>
			</div>
			<div class="form-group form-item">
				<label class="form-label control-label col-sm-2">휴대전화번호</label>
				<div class="col-sm-6 phone-input-group">
					<select name="phone1" class="phone-number form-control col-sm-3" id="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					<input type="number" name="phone2" class="phone-number form-control col-sm-9" id="phone2">
				</div>
				<input type="hidden" name="phone">
			</div>
			<div class="form-group form-item">
				<label class="form-label col-sm-2 control-label" for="role-select">역할</label>
				<div class="col-sm-2">
					<select class="form-control col-sm-2" name="role" id="role-select">
						<option value="user" selected>유저</option>
						<option value="seller">판매자</option>
					</select>
				</div>
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
