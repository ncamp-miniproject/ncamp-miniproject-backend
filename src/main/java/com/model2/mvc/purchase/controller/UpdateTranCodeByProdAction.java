package com.model2.mvc.purchase.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.purchase.service.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTranCodeByProdAction extends Action {
    private PurchaseService purchaseService;

    public UpdateTranCodeByProdAction(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
//        String tranCode = request.getParameter("tranCode");
//
//        Purchase updateCondition = new Purchase();
//        updateCondition.setTranCode(TranStatusCode.getTranCode(tranCode));
//        updateCondition.setTranNo(-1);
//
//        super.purchaseService.updateTranCode(updateCondition);
//
//        return "redirect:/listProduct.do?menu=manage";
        throw new UnsupportedOperationException();
    }
}
