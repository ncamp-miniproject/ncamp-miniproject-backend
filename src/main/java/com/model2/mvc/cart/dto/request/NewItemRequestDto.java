package com.model2.mvc.cart.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class NewItemRequestDto {
    private Integer prodNo;
    private Integer quantity;
}
