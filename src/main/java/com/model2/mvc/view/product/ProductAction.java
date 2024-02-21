package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;

public abstract class ProductAction extends Action {
    protected ProductService productService;

    protected ProductAction() {
        this.productService = ProductService.getInstance();
    }

    @Override
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception;
}
