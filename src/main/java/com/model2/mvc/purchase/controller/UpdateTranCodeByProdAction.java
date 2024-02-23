package com.model2.mvc.purchase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;

public class UpdateTranCodeByProdAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        String tranCode = request.getParameter("tranCode");
        
        Purchase updateCondition = new Purchase();
        updateCondition.setTranCode(TranStatusCode.getTranCode(tranCode));
        updateCondition.setTranNo(-1);
        
        super.purchaseService.updateTranCode(updateCondition);
        
        return "redirect:/listProduct.do?menu=manage";
    }
}
