package com.model2.mvc.product.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductAction extends Action {
    private final ProductService productService;

    public AddProductAction(ProductService productService) {
        this.productService = productService;
    }

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
        this.productService.addProduct(dto);
        return "redirect:/listProduct.do";
    }
}
