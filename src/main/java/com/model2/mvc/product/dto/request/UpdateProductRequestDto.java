package com.model2.mvc.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateProductRequestDto {
    private String prodName;
    private String prodDetail;
    private String manuDate;
    private int price;
    private String fileName;
    private int stock;
    private Integer categoryNo;
}
