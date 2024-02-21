package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.domain.Purchase;

public class UpdateTranCodeAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        String tranCode = request.getParameter("tranCode");
        
        Purchase updateCondition = new Purchase();
        updateCondition.setTranCode(tranCode);
        updateCondition.setTranNo(tranNo);
        updateCondition.setPurchaseProd(Product.builder().prodNo(-1).build());
        
        super.purchaseService.updateTranCode(updateCondition);
        
        return "redirect:/listPurchase.do";
    }
}
