package com.model2.mvc.purchase.controller;

import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPurchaseViewAction extends PurchaseAction {
    private ProductService productService;

    public AddPurchaseViewAction() {
        this.productService = ProductService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GetProductResponseDTO productToPurchase = this.productService.getProduct(Integer.parseInt(request.getParameter(
                "prod_no")));

        request.setAttribute("productToPurchase", productToPurchase);
        request.setAttribute("loginUser", request.getSession().getAttribute("user"));
        return "forward:/purchase/addPurchaseView.jsp";
    }
}
