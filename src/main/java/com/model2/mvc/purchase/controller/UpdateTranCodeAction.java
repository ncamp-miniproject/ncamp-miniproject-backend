package com.model2.mvc.purchase.controller;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTranCodeAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        String tranCode = request.getParameter("tranCode");

        Purchase updateCondition = new Purchase();
        updateCondition.setTranCode(TranStatusCode.getTranCode(tranCode));
        updateCondition.setTranNo(tranNo);

        super.purchaseService.updateTranCode(updateCondition);

        return "redirect:/listPurchase.do";
    }
}
