package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.service.CartService;
import com.model2.mvc.framework.Action;

public abstract class CartAction extends Action {
    protected CartService cartService;

    public CartAction() {
        this.cartService = CartService.getInstance();
    }
}
