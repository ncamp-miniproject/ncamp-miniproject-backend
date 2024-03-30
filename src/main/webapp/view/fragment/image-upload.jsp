<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>이미지 업로드</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>

    <script src="${pageContext.request.contextPath}/javascript/common/imageUpload.js"></script>

    <style>
        #upload-image {
            height: 240px;
        }
    </style>
</head>
<body>
    <img id="upload-image" src="" alt="No">

    <textarea id="image-desc"></textarea>

    <button type="button" id="btn-upload-image">이미지 찾기</button>

    <button type="button" id="btn-select">선택</button>
</body>
</html>
