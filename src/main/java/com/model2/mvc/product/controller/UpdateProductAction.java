package com.model2.mvc.product.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.service.ProductService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("updateProductAction")
public class UpdateProductAction extends Action {
    private ProductService productService;

    public UpdateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UpdateProductRequestDTO requestDTO = new UpdateProductRequestDTO().builder()
                .prodNo(Integer.parseInt(request.getParameter("prodNo")))
                .prodName(request.getParameter("prodName"))
                .prodDetail(request.getParameter("prodDetail"))
                .manuDate(request.getParameter("manuDate"))
                .price(Integer.parseInt(request.getParameter("price")))
                .fileName(request.getParameter("fileName"))
                .stock(Integer.parseInt(request.getParameter("stock")))
                .build();

        this.productService.updateProduct(requestDTO);
        return "redirect:/listProduct.do";
    }
}
