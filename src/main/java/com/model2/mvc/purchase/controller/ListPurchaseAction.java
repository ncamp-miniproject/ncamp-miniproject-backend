package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.user.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("listPurchaseAction")
public class ListPurchaseAction extends Action {
    private final PurchaseService purchaseService;

    public ListPurchaseAction(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

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

        ListPurchaseResponseDTO result = this.purchaseService.getPurchaseList(searchInfo, loginUser);

        System.out.println(result);

        request.setAttribute("data", result);
        return "forward:/purchase/listPurchase.jsp";
    }
}
