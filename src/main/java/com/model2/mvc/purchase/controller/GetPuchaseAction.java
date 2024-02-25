package com.model2.mvc.purchase.controller;

import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPuchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));

        GetPurchaseResponseDTO purchaseData = super.purchaseService.getPurchase(tranNo);
        request.setAttribute("purchaseData", purchaseData);
        return "forward:/purchase/getPurchase.jsp";
    }
}
