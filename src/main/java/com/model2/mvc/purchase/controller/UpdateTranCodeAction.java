package com.model2.mvc.purchase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranCode;

public class UpdateTranCodeAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        String tranCode = request.getParameter("tranCode");
        
        Purchase updateCondition = new Purchase();
        updateCondition.setTranCode(TranCode.getTranCode(tranCode));
        updateCondition.setTranNo(tranNo);
        
        super.purchaseService.updateTranCode(updateCondition);
        
        return "redirect:/listPurchase.do";
    }
}
