package com.model2.mvc.cart.dto.request;

import javax.servlet.http.Cookie;

public class AddItemRequestDTO {
    private String prodNo;
    private String quantity;
    private String cartValue;

    public AddItemRequestDTO(String prodNo, String quantity, String cartValue) {
        this.prodNo = prodNo;
        this.quantity = quantity;
        this.cartValue = cartValue;
    }

    public String getProdNo() {
        return prodNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCartValue() {
        return this.cartValue;
    }

    @Override
    public String toString() {
        return "AddItemRequestDTO{" +
               "prodNo='" +
               prodNo +
               '\'' +
               ", quantity='" +
               quantity +
               '\'' +
               ", cartValue='" +
               cartValue +
               '\'' +
               '}';
    }
}
