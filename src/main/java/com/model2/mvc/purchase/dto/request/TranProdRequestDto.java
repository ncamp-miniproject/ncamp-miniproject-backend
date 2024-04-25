package com.model2.mvc.purchase.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class TranProdRequestDto {
    private Integer prodNo;
    private int quantity;
}
