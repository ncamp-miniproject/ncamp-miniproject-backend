package com.model2.mvc.cart.dto.response;

import javax.servlet.http.Cookie;

public class AddItemResponseDTO {
    private Cookie cookie;

    public AddItemResponseDTO(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie getCookie() {
        return this.cookie;
    }
}
