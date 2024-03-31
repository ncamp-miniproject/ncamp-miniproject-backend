package com.model2.mvc.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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
