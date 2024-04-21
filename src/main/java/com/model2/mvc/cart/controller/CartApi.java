package com.model2.mvc.cart.controller;

import com.model2.mvc.cart.dto.request.NewItemRequestDto;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDto;
import com.model2.mvc.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartApi {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void> addItem(@CookieValue(value = "cart", defaultValue = "") String cartValue,
                                        @RequestBody NewItemRequestDto request) {
        String result = this.cartService.addItem(request, cartValue);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE, ResponseCookie.from("cart", result).build().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(HttpServletResponse response) {
        response.addCookie(new Cookie("cart", ""));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<ListCartItemResponseDto> listCart(
            @CookieValue(value = "cart", defaultValue = "") String cartValue) {
        return new ResponseEntity<>(this.cartService.getCartItemList(cartValue), HttpStatus.OK);
    }
}
