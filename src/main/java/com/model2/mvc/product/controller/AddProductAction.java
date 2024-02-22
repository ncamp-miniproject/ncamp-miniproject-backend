package com.model2.mvc.product.controller;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.product.domain.Product;

public class AddProductAction extends ProductAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Product productVO = Product.builder()
                .fileName(request.getParameter("fileName"))
                .manuDate(request.getParameter("manuDate"))
                .price(Integer.parseInt(request.getParameter("price")))
                .prodDetail(request.getParameter("prodDetail"))
                .prodName(request.getParameter("prodName"))
                .regDate(new Date(System.currentTimeMillis()))
                .build();
        System.out.println(productVO);
        super.productService.addProduct(productVO);
        return "redirect:/listProduct.do";
    }
}
