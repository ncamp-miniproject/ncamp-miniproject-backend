package com.model2.mvc.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddProductRequestDTO {
    private String fileName;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int stock;
    private Integer categoryNo;
}
