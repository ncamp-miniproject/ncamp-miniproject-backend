<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>회원정보수정</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/user/updateUser.js"></script>

</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/header.jsp"/>
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="inner-header">
            <h2 class="page-title">회원정보수정</h2>
        </div>
        <form:form name="update-form" modelAttribute="user">
            <div class="form-item">
				<label class="form-label">아이디</label>
				<form:input path="userId" type="text" cssClass="form-input" readonly="true"/>
			</div>
			<div class="form-item">
				<label class="form-label" for="username">이름</label>
                <form:input path="userName" type="text" cssClass="form-input" id="username"/>
			</div>
			<div class="form-item">
				<label class="form-label" for="ssn">주민번호</label>
                <form:input path="ssn" cssClass="form-input" id="ssn" placeholder="- 제외 13자리 입력"/>
			</div>
			<div class="form-item">
				<label class="form-label" for="address">주소</label>
                <form:input path="addr" type="text" cssClass="form-input" id="address"/>
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
                <form:input path="phone" type="hidden"/>
			</div>
			<div class="form-item">
				<label class="form-label" for="email">이메일</label>
                <form:input path="email" cssClass="form-input" type="text" id="email" readonly="true"/>
			</div>
			<div class="form-item">
				<label class="form-label" for="role-select">역할</label>
				<select name="role" id="role-select">
					<option value="user" ${user.role.role == 'user' ? "selected" : ""}>유저</option>
					<option value="seller" ${user.role.role == 'seller' ? "selected" : ""}>판매자</option>
				</select>
			</div>

			<div class="btn-box">
				<button type="button" class="btn btn--update">수정</button>
				<button type="button" class="btn btn--cancel">취소</button>
			</div>
        </form:form>
    </main>
</body>
</html>
