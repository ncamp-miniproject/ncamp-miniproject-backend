<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta charset="UTF-8">
    <title>회원정보수정</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/updateUser.js"></script>

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
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
		<div class="row">
			<div class="page-header col-md-12">
				<h2 class="page-title">회원정보수정</h2>
			</div>
		</div>

		<div class="row">
			<form:form name="update-form" modelAttribute="user" cssClass="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">아이디</label>
					<div class="col-sm-10">
						<form:input path="userId" type="text" cssClass="form-control" readonly="true"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="username">이름</label>
					<div class="col-sm-10">
						<form:input path="userName" type="text" cssClass="form-control" id="username"/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">주민등록번호</label>
					<div class="col-sm-10" for ssn>
						<form:input path="ssn" type="text" cssClass="form-control" id="ssn" placeholder="- 제외 13자리 입력"/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label" for="address">주소</label>
					<div class="col-sm-10">
						<form:input path="addr" type="text" cssClass="form-control" id="address"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">휴대전화번호</label>
					<div class="col-sm-10">
						<div class="input-group">
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" id="phone-dropdown">
									010 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#" class="front-phone-number" data-number="010">010</a></li>
									<li><a href="#" class="front-phone-number" data-number="011">011</a></li>
									<li><a href="#" class="front-phone-number" data-number="016">016</a></li>
									<li><a href="#" class="front-phone-number" data-number="018">018</a></li>
									<li><a href="#" class="front-phone-number" data-number="019">019</a></li>
								</ul>
							</div>
							<input type="hidden" name="phone1" value="010">
							<input type="text" name="phone2" class="form-control">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">이메일</label>
					<div class="col-sm-10">
						<form:input path="email" type="text" cssClass="form-control" id="email" readonly="true"/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">역할</label>
					<div class="col-sm-10">
						<div class="dropdown">
							<button class="btn btn-default dropdown-toggle"
									type="button"
									id="role-dropdown"
									data-toggle="dropdown"
									aria-haspopup="true"
									aria-expanded="true">
								${user.role.role == 'seller' ? '판매자' : '유저'} <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="role-dropdown">
								<li><a href="#" class="role-select" data-role="user">유저</a></li>
								<li><a href="#" class="role-select" data-role="seller">판매자</a></li>
							</ul>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<div class="row">
			<div class="col-md-12">
				<button type="button" class="btn btn-primary btn--update">수정</button>
				<button type="button" class="btn btn-default btn--cancel">취소</button>
			</div>
		</div>
    </main>
</body>
</html>
