package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.AddItemResponseDTO;
import com.model2.mvc.cart.service.CartService;
import com.model2.mvc.framework.Action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AddItemAction extends Action {
    private final CartService cartService;

    public AddItemAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("cart"))
                .findAny()
                .orElse(new Cookie("cart", ""));
        AddItemResponseDTO responseDTO = this.cartService.addItem(new AddItemRequestDTO(request.getParameter("prodNo"),
                                                                                         request.getParameter("quantity"),
                                                                                         cookie));
        response.addCookie(responseDTO.getCookie());
        return String.format("redirect:/getProduct.do?prodNo=%s&menu=search", request.getParameter("prodNo"));
    }
}
