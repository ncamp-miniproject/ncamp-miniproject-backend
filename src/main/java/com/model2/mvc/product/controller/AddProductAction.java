package com.model2.mvc.product.controller;

import com.model2.mvc.product.dto.request.AddProductRequestDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductAction extends ProductAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AddProductRequestDTO dto = new AddProductRequestDTO().builder()
                .fileName(request.getParameter("fileName"))
                .manuDate(request.getParameter("manuDate"))
                .price(Integer.parseInt(request.getParameter("price")))
                .prodDetail(request.getParameter("prodDetail"))
                .prodName(request.getParameter("prodName"))
                .stock(Integer.parseInt(request.getParameter("stock")))
                .build();
        System.out.println(dto);
        super.productService.addProduct(dto);
        return "redirect:/listProduct.do";
    }
}
