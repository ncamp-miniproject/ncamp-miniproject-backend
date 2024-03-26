<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>

<c:if test="${empty currentUser}">
	<c:set var="currentUser" value="${user}"/>
</c:if>

<html>
<head>
	<title>ȸ��������ȸ</title>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
			integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
			crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/javascript/user/userInfo.js"></script>

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
			margin: 5%;
		}
	</style>
</head>

<body data-context-path="${pageContext.request.contextPath}">
	<c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

	<main>
		<div class="page-header">
			<h1>ȸ��������ȸ</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">���̵�</div>
			<div class="panel-body">${currentUser.userId}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">�̸�</div>
			<div class="panel-body">${currentUser.userName}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">�ּ�</div>
			<div class="panel-body">${currentUser.addr}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">�޴���ȭ��ȣ</div>
			<div class="panel-body">${currentUser.phone}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">�̸���</div>
			<div class="panel-body">${currentUser.email}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">��������</div>
			<div class="panel-body">${currentUser.regDate}</div>
		</div>

		<div class="button-container">
			<c:if test="${!empty user && currentUser.userId == user.userId}">
				<button class="btn btn--update" data-user-id="${currentUser.userId}">����</button>
			</c:if>
			<button class="btn btn--back">�ڷ�</button>
		</div>
	</main>
</body>
</html>