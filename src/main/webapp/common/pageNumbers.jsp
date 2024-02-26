<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
    <tr>

        <td align="center">
            <c:if test="${ data.pageInfo.previousPageSetAvailable }">
                <a
                    href="${ url }?page=${ data.pageInfo.previousPageSetEntry }${ additionalQueryString }">이전</a>
            </c:if>
            <c:forEach var="pageToJump" items="${ data.pageInfo.pagesToDisplay }">
                <c:choose>
                    <c:when test="${ pageToJump != data.pageInfo.currentPage }">
                        <a
                            href="${ url }?page=${ pageToJump }${ additionalQueryString }">${ pageToJump }</a>
                    </c:when>
                    <c:otherwise>
                        <strong>${ pageToJump }</strong>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${ data.pageInfo.nextPageSetAvailable }">
                <a
                    href="${ url }?page=${ data.pageInfo.nextPageSetEntry }${ additionalQueryString }">다음</a>
            </c:if>
        </td>
    </tr>
</table>