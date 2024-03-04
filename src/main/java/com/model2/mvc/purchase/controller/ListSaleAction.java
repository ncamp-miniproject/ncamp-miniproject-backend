package com.model2.mvc.purchase.controller;

import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListSaleAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User loginUser = (User)request.getSession().getAttribute("user");
        String menu = request.getParameter("menu");
        int currentPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
        if ((menu == null || menu.equals("search")) || !loginUser.getRole().equals("admin")) {
            return "redirect:/listPurchase.do?menu=search&page=" + currentPage;
        }
        ListPurchaseResponseDTO responseDTO = super.purchaseService.getSaleList(currentPage, loginUser);
        request.setAttribute("data", responseDTO);
        return "forward:/purchase/listPurchase.jsp";
    }
}
