<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>��ǰ �����ȸ</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/javascript/product/productList.js"></script><link rel="stylesheet"
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
        .prod-item {
            display: flex;
            gap: 24px;
        }

        .prod-item img {
            width: 240px;
        }

        .page-container {
            display: flex;
            justify-content: center;
        }

        main {
            padding: 12px 120px;
        }

        .page-navigator, .category-item, .order-button {
            cursor: pointer;
        }
    </style>
</head>

<body data-context-path="${pageContext.request.contextPath}" data-menu="${menuMode}">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main class="row">
        <div class="category-box col-md-3">
            <div class="category-display">
            </div>
            <c:if test="${!empty user && user.role.role == 'admin' && menuMode == 'manage'}">
                <a href="${pageContext.request.contextPath}/categories/new-form">
                    ī�װ� ����
                </a>
            </c:if>
        </div>
        <div class="content col-md-9">
            <div class="inner-header">
                <h2 class="page-title">��ǰ ��� ��ȸ</h2>
                <form class="search-form"
                      name="search">
                    <select name="searchCondition" class="search-condition">
                        <option value="1">��ǰ��</option>
                        <option value="2">��ǰ����</option>
                    </select>
                    <button type="button" id="search-button">�˻�</button>
                </form>
                <p>��ü <span id="count-display">0</span>�Ǽ�, ���� <span id="current-page-display">0</span> ������</p>
            </div>
            <div class="btn-group btn-group-xs order-button-set" role="group">
                <div class="btn-group order-button-group" data-order-by="prodName">
                    <button type="button"
                            class="btn btn-default dropdown-toggle order-dropdown-button"
                            data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false">
                        ��ǰ�̸� ����
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="order-button" data-ascend="true">��ǰ�̸� ��������</a></li>
                        <li><a class="order-button" data-ascend="false">��ǰ�̸� ��������</a></li>
                    </ul>
                </div>
                <div class="btn-group order-button-group" data-order-by="price">
                    <button type="button"
                            class="btn btn-default dropdown-toggle order-dropdown-button"
                            data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false">
                        ���� ����
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="order-button" data-ascend="true">���� ���� ��</a></li>
                        <li><a class="order-button" data-ascend="false">���� ���� ��</a></li>
                    </ul>
                </div>
            </div>

            <div class="container page-container"
                 id="page-container">
                <nav aria-label="Page navigation">
                    <ul class="pagination" id="pagination">
                    </ul>
                </nav>
            </div>

            <div class="container item-list">
            </div>
        </div>
    </main>
</body>
</html>
