package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDTO;
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
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        User loginUser = (User)request.getSession().getAttribute("user");
        String menu = request.getParameter("menu");
        if ((menu != null && menu.equals("manage")) || loginUser.getRole().equals("admin")) {
            return "redirect:/listSale.do?menu=manage&page=" + page;
        }
        ListPurchaseRequestDTO requestDTO = new ListPurchaseRequestDTO(page, CommonConstants.PAGE_SIZE,
                                                                       loginUser.getUserId());
        requestDTO.setSearchCondition(StringUtil.null2nullStr(request.getParameter("searchCondition")));
        requestDTO.setSearchKeyword(StringUtil.null2nullStr(request.getParameter("searchKeyword")));

        ListPurchaseResponseDTO result = this.purchaseService.getPurchaseList(requestDTO);

        System.out.println(result);

        request.setAttribute("data", result.builder().loginUser(loginUser).build());
        return "forward:/purchase/listPurchase.jsp";
    }
}
