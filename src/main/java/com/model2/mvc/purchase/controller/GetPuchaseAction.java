package com.model2.mvc.purchase.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.service.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPuchaseAction extends Action {
    private final PurchaseService purchaseService;

    public GetPuchaseAction(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));

        GetPurchaseResponseDTO purchaseData = this.purchaseService.getPurchase(tranNo);
        request.setAttribute("purchaseData", purchaseData);
        System.out.println("purchaseData: " + purchaseData);
        return "forward:/purchase/getPurchase.jsp";
    }
}
