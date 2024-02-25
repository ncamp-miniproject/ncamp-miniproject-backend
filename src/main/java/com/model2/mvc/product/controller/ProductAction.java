package com.model2.mvc.product.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.product.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ProductAction extends Action {
    protected ProductService productService;

    protected ProductAction() {
        this.productService = ProductService.getInstance();
    }

    @Override
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
