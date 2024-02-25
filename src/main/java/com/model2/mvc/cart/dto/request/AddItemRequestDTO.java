package com.model2.mvc.cart.dto.request;

import javax.servlet.http.Cookie;

public class AddItemRequestDTO {
    private String prodNo;
    private String quantity;
    private Cookie cookie;

    public AddItemRequestDTO(String prodNo, String quantity, Cookie cookie) {
        this.prodNo = prodNo;
        this.quantity = quantity;
        this.cookie = cookie;
    }

    public String getProdNo() {
        return prodNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public Cookie getCookie() {
        return this.cookie;
    }

    @Override
    public String toString() {
        return "AddItemRequestDTO{" + "prodNo=" + prodNo + ", quantity=" + quantity + '}';
    }
}
