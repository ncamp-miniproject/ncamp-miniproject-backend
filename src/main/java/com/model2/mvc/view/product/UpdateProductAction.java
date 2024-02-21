package com.model2.mvc.view.product;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.domain.Product;

public class UpdateProductAction extends ProductAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Product parameter = Product.builder()
                .prodNo(Integer.parseInt(request.getParameter("prodNo")))
                .prodName(request.getParameter("prodName"))
                .prodDetail(request.getParameter("prodDetail"))
                .manuDate(request.getParameter("manuDate"))
                .price(Integer.parseInt(request.getParameter("price")))
                .fileName(request.getParameter("fileName"))
                .regDate(new Date(System.currentTimeMillis()))
                .build();
                
        super.productService.updateProduct(parameter);
        return "redirect:/listProduct.do";
    }
}
