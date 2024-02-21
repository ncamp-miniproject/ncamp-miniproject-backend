package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.domain.Purchase;

public class UpdateTranCodeByProdAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        String tranCode = request.getParameter("tranCode");
        
        Purchase updateCondition = new Purchase();
        updateCondition.setTranCode(tranCode);
        updateCondition.setTranNo(-1);
        updateCondition.setPurchaseProd(Product.builder().prodNo(prodNo).build());
        
        super.purchaseService.updateTranCode(updateCondition);
        
        return "redirect:/listProduct.do?menu=manage";
    }
}
