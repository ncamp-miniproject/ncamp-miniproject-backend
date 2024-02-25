package com.model2.mvc.purchase.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.purchase.service.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PurchaseAction extends Action {
    protected PurchaseService purchaseService;

    protected PurchaseAction() {
        this.purchaseService = PurchaseService.getInstance();
    }

    @Override
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
