<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${pageContext.request.contextPath}/javascript/common/pageNavigation.js"></script>

<div class="container"
     id="page-container"
     data-additional-query-string="${additionalQueryString}"
     data-url="${url}"
     data-current-page="${data.pageInfo.currentPage}">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li id="nav-previous"
                data-previous-page-set-available="${data.pageInfo.previousPageSetAvailable ? 1 : 0}"
                data-previous-page-set-entry="${data.pageInfo.previousPageSetEntry}">
                <a aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <c:forEach var="pageToJump" items="${data.pageInfo.pagesToDisplay}">
                <li class="page-number" data-page="${pageToJump}"><a>${pageToJump}</a></li>
            </c:forEach>
            <li id="nav-next"
                data-next-page-set-available="${data.pageInfo.nextPageSetAvailable ? 1 : 0}"
                data-next-page-set-entry="${data.pageInfo.nextPageSetEntry}">
                <a aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>