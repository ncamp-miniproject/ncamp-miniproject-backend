package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;

public abstract class PurchaseAction extends Action {
    protected PurchaseService purchaseService;
    
    protected PurchaseAction() {
        this.purchaseService = PurchaseService.getInstance();
    }
    
    @Override
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
