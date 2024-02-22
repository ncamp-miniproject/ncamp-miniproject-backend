package com.model2.mvc.purchase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.user.domain.User;

public class UpdatePurchaseViewAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");
        
        Purchase toUpdate = super.purchaseService.getPurchase(tranNo);
        
        request.setAttribute("purchaseData", toUpdate);
        request.setAttribute("loginUser", loginUser);
        return "forward:/purchase/updatePurchaseView.jsp"; // TODO: forward update view
    }
}
