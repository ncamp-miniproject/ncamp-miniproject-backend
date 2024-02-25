package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListPurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");

        Search searchInfo = new Search();
        String page = request.getParameter("page");

        int pageNum = page == null ? 1 : Integer.parseInt(page);
        searchInfo.setPage(pageNum);
        searchInfo.setPageUnit(CommonConstants.PAGE_SIZE);
        searchInfo.setSearchCondition(StringUtil.null2nullStr(request.getParameter("searchCondition")));
        searchInfo.setSearchKeyword(StringUtil.null2nullStr(request.getParameter("searchKeyword")));

        ListPurchaseResponseDTO result = super.purchaseService.getPurchaseList(searchInfo, loginUser);

        System.out.println(result);

        request.setAttribute("data", result);
        return "forward:/purchase/listPurchase.jsp";
    }
}
