package com.model2.mvc.purchase.controller;

import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdatePurchaseViewAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));

        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");

        GetPurchaseResponseDTO toUpdate = super.purchaseService.getPurchase(tranNo);

        request.setAttribute("purchaseData", toUpdate);
        request.setAttribute("loginUser", loginUser);
        return "forward:/purchase/updatePurchaseView.jsp";
    }
}
