package com.model2.mvc.product.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductViewAction extends Action {
    private final ProductService productService;

    public UpdateProductViewAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));

        GetProductResponseDTO result = this.productService.getProduct(prodNo);
        request.setAttribute("data", result);
        return "forward:/product/updateProduct.jsp";
    }
}
