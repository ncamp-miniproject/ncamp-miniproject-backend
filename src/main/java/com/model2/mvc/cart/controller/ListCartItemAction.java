package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;
import com.model2.mvc.cart.service.CartService;
import com.model2.mvc.framework.Action;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component("listCartItemAction")
public class ListCartItemAction extends Action {
    private final CartService cartService;

    public ListCartItemAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cartValue = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("cart"))
                .findAny()
                .orElse(new Cookie("cart", ""))
                .getValue();

        ListCartItemResponseDTO responseDTO = this.cartService.getCartItemList(cartValue);

        System.out.println(responseDTO);

        request.setAttribute("data", responseDTO);
        return "forward:/cart/listCartItem.jsp";
    }
}
