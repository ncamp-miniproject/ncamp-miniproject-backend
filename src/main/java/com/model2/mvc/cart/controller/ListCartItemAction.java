package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class ListCartItemAction extends CartAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cartValue = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("cart"))
                .findAny()
                .orElse(new Cookie("cart", ""))
                .getValue();

        ListCartItemResponseDTO responseDTO = super.cartService.getCartItemList(cartValue);

        System.out.println(responseDTO);

        request.setAttribute("data", responseDTO);
        return "forward:/cart/listCartItem.jsp";
    }
}
