package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.AddItemResponseDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AddItemAction extends CartAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = Arrays.stream(request.getCookies())
                              .filter(c -> c.getName()
                                            .equals("cart"))
                              .findAny()
                              .orElse(new Cookie("cart", ""));
        AddItemResponseDTO responseDTO = super.cartService.addItem(new AddItemRequestDTO(request.getParameter("prodNo"),
                                                                                         request.getParameter("quantity"),
                                                                                         cookie));
        response.addCookie(responseDTO.getCookie());
        return String.format("redirect:/getProduct.do?prodNo=%s&menu=search", request.getParameter("prodNo"));
    }
}
