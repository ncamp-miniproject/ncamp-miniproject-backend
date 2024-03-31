package com.model2.mvc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductImageDto {
    private String fileExtension;
    private String base64Data;
    private String description;
    private Boolean thumbnail;
}
