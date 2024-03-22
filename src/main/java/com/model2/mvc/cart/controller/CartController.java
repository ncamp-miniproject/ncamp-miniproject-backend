package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.request.AddItemRequestDto;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDto;
import com.model2.mvc.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/items/new")
    public ModelAndView addItem(@CookieValue(value = "cart", required = false) String cartValue,
                                @RequestParam("prodNo") String prodNo,
                                @RequestParam("quantity") String quantity) {
        if (cartValue == null) {
            cartValue = "";
        }
        String result = this.cartService.addItem(new AddItemRequestDto(prodNo, quantity, cartValue));
        return new ModelAndView(String.format("redirect:/products/%s?menu=search", prodNo),
                                "cartCookieValue",
                                result);
    }

    @PostMapping("/items/clear")
    public String clearCart(HttpServletResponse response) {
        response.addCookie(new Cookie("cart", ""));
        return "redirect:/cart/items";
    }

    @GetMapping("/items")
    public String listCart(@CookieValue(value = "cart", required = false) String cartValue, Model model) {
        ListCartItemResponseDto responseDto = this.cartService.getCartItemList(cartValue);
        model.addAttribute("data", responseDto);
        return "cart/listCartItem";
    }
}
