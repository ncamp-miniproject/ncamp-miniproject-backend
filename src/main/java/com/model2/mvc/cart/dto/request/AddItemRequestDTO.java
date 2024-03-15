package com.model2.mvc.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AddItemRequestDTO {
    private String prodNo;
    private String quantity;
    private String cartValue;
}
