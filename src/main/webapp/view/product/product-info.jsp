<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title>${productData.prodName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script type="text/javascript">
        const prodImageData = [
            <c:forEach var="prodImage" items="${productData.productImages}">
            {
                fileName: "${prodImage.fileName}",
                description: "${prodImage.description}"
            },
            </c:forEach>
        ];
    </script>

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
            width: 1440px;
            display: flex;
            flex-direction: column;
            gap: 48px
        }

        h4 {
            font-size: 14px;
        }

        img {
            width: 160px;
        }

        .prod-no {
            font-size: 10px;
            color: #adb5bd;
        }

        .product-view img {
            width: 100%;
        }

        .panel {
            border-radius: 1px;
            margin-bottom: 0px;
        }

        .panel-heading {
            font-size: 10px;
            background-color: #fff;
        }

        .panel-default > .panel-heading {
            background-color: #fff;
            padding: 4px 6px;
        }

        .panel-default > .panel-body {
            padding: 8px 10px;
        }

        .product-info {
            display: flex;
            flex-direction: column;
            gap: 4px;
        }

        .prod-main {
            margin-bottom: 16px;
        }

        form[name=cart] {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }

        .button-container {
            padding-top: 18px;
        }

        #quantityInput {
            width: 64px;
        }

        .slide-btn {
            margin-top: 8%;
        }

        #image-slide {
            display: flex;
            flex-direction: row;
            width: 100%;
            padding: 36px 12px;
            justify-content: space-between;
        }

        #image-container img {
            width: 480px;
        }

        #image-container {
            flex-grow: 1;
            display: flex;
            flex-direction: row;
            gap: 18px;
            justify-content: center;
            width: 100%;
        }
    </style>
</head>

<body data-context-path="${pageContext.request.contextPath}">
    <c:import url="${pageContext.request.contextPath}/view/layout/nav.jsp"/>

    <main>
        <div class="top">
            <div class="page-header">
                <h2>상품상세조회</h2>
            </div>
            <h4 class="prod-no">상품번호 ${productData.prodNo}</h4>
            <div class="prod-main row">
                <div class="product-view col-xs-6">
                    <c:forEach var="image" items="${productData.productImages}">
                        <c:if test="${image.thumbnail}">
                            <img src="${pageContext.request.contextPath}/images/uploadFiles/${image.fileName}"
                                 alt="No image">
                        </c:if>
                    </c:forEach>
                </div>
                <div class="product-info col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">상품명</div>
                        <div class="panel-body">${productData.prodName}</div>
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
                                <input type="number"
                                       class="form-control"
                                       name="quantity"
                                       id="quantityInput"
                                       value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" id="loadOnCart">장바구니</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="product-detail">
            <div class="page-header">
                <h3>상품상세</h3>
            </div>
            <p>${productData.prodDetail}</p>
        </div>
        <div class="product-image">
            <div class="page-header">
                <h3>상품 이미지</h3>
            </div>
            <div id="image-slide">
                <a class="slide-btn left" role="button" id="slide-left">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>

                <div id="image-container"></div>

                <a class="slide-btn right" role="button" id="slide-right">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </main>
</body>
</html>