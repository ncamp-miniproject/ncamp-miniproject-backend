package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.domain.Product;

public class UpdateProductViewAction extends ProductAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        
        Product result = super.productService.getProduct(prodNo);
        request.setAttribute("data", result);
        return "forward:/product/updateProduct.jsp";
    }
}
