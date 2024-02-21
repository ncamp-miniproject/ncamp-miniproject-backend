package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.purchase.domain.Purchase;

public class GetPuchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        
        Purchase purchaseData = super.purchaseService.getPurchase(tranNo);
        request.setAttribute("purchaseData", purchaseData);
        return "forward:/purchase/getPurchase.jsp";
    }
}
