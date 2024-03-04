package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListPurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Search searchInfo = new Search();
        String page = request.getParameter("page");

        int pageNum = page == null ? 1 : Integer.parseInt(page);
        searchInfo.setStartRowNum(pageNum);
        searchInfo.setEndRowNum(CommonConstants.PAGE_SIZE);
        searchInfo.setSearchCondition(StringUtil.null2nullStr(request.getParameter("searchCondition")));
        searchInfo.setSearchKeyword(StringUtil.null2nullStr(request.getParameter("searchKeyword")));

        User loginUser = (User)request.getSession().getAttribute("user");
        String menu = request.getParameter("menu");
        if ((menu != null && menu.equals("manage")) || loginUser.getRole().equals("admin")) {
            return "redirect:/listSale.do?menu=manage&page=" + pageNum;
        }

        ListPurchaseResponseDTO result = super.purchaseService.getPurchaseList(searchInfo, loginUser);

        System.out.println(result);

        request.setAttribute("data", result);
        return "forward:/purchase/listPurchase.jsp";
    }
}
