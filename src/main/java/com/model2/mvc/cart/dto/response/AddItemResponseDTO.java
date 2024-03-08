package com.model2.mvc.cart.dto.response;

import javax.servlet.http.Cookie;

public class AddItemResponseDTO {
    private String cookie;

    public AddItemResponseDTO(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie() {
        return this.cookie;
    }
}
