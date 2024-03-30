package com.model2.mvc.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductImage {
    private Integer imageNo;
    private Integer prodNo;
    private String fileName;
    private String description;
    private Boolean thumbnail;
}
