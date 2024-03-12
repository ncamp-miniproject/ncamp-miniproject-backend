<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>회원 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

    <div style="width: 98%; margin-left: 10px;">

        <form name="detailForm" action="/listUser.do" method="post">

            <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37"></td>
                    <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="93%" class="ct_ttl01">회원 목록조회</td>
                            </tr>
                        </table>
                    </td>
                    <td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
                </tr>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <c:if test="${ !empty searchVO.searchCondition }">
                        <td align="right"><select name="searchCondition" class="ct_input_g" style="width: 80px">
                                <option value="0" ${ searchVO.searchCondition == "0" ? "selected" : "" }>회원ID</option>
                                <option value="1" ${ searchVO.searchCondition != "0" ? "selected" : "" }>회원명</option>
                        </select> <input type="text" name="searchKeyword" value="${ searchVO.searchKeyword }" class="ct_input_g"
                            style="width: 200px; height: 19px"></td>
                    </c:if>
                    <c:if test="${ empty searchVO.searchCondition }">
                        <td align="right"><select name="searchCondition" class="ct_input_g" style="width: 80px">
                                <option value="0">회원ID</option>
                                <option value="1">회원명</option>
                        </select> <input type="text" name="searchKeyword" class="ct_input_g" style="width: 200px; height: 19px"></td>
                    </c:if>

                    <td align="right" width="70">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23">
                                </td>
                                <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;"><a
                                    href="javascript:fncGetUserList();">검색</a></td>
                                <td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td colspan="11">전체 ${ total } 건수, 현재 ${ currentPage } 페이지</td>
                </tr>
                <tr>
                    <td class="ct_list_b" width="100">No</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">회원ID</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b" width="150">회원명</td>
                    <td class="ct_line02"></td>
                    <td class="ct_list_b">이메일</td>
                </tr>
                <tr>
                    <td colspan="11" bgcolor="808285" height="1"></td>
                </tr>

                <c:set var="no" value="${ list.size() }" />
                <c:forEach var="data" items="${ list }">
                    <tr class="ct_list_pop">
                        <td align="center">${ no }</td>
                        <c:set target="no" value="${ no - 1 }" />
                        <td></td>
                        <td align="left"><a href="/getUser.do?userId=${ data.userId }">${ data.userId }</a></td>
                        <td></td>
                        <td align="left">${ data.userName }</td>
                        <td></td>
                        <td align="left">${ data.email }</td>
                    </tr>
                    <tr>
                        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
                    </tr>
                </c:forEach>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td align="center">
                        <c:forEach var="i" begin="1" end="${ totalPage }" step="1">
                            <a href="/listUser.do?page=${ i }">${ i }</a> 
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <!--  페이지 Navigator 끝 -->
        </form>
    </div>

</body>
</html>