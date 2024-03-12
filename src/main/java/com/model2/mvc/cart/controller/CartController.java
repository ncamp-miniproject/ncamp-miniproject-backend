package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;
import com.model2.mvc.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping("/addItem.do")
    public ModelAndView addItem(@CookieValue(value = "cart", required = false) String cartValue,
                                @RequestParam("prodNo") String prodNo,
                                @RequestParam("quantity") String quantity) {
        if (cartValue == null) {
            cartValue = "";
        }
        String result = this.cartService.addItem(new AddItemRequestDTO(prodNo, quantity, cartValue));
        return new ModelAndView(String.format("redirect:/getProduct.do?prodNo=%s&menu=search", prodNo),
                                "cartCookieValue",
                                result);
    }

    @RequestMapping("/clearCart.do")
    public String clearCart(HttpServletResponse response) {
        response.addCookie(new Cookie("cart", ""));
        return "redirect:/listCart.do";
    }

    @RequestMapping("/listCart.do")
    public String listCart(@CookieValue(value = "cart", required = false) String cartValue, Model model) {
        ListCartItemResponseDTO responseDTO = this.cartService.getCartItemList(cartValue);
        model.addAttribute("data", responseDTO);
        return "cart/listCartItem";
    }
}
