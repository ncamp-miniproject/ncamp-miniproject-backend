package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.service.CartService;
import com.model2.mvc.framework.Action;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("clearItemAction")
public class ClearItemAction extends Action {
    private final CartService cartService;

    public ClearItemAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie emptyCookie = new Cookie("cart", "");
        response.addCookie(emptyCookie);
        return "redirect:/listCart.do";
    }
}
