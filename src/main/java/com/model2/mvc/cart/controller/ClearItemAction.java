package com.model2.mvc.cart.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearItemAction extends CartAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie emptyCookie = new Cookie("cart", "");
        response.addCookie(emptyCookie);
        return "redirect:/listCart.do";
    }
}
