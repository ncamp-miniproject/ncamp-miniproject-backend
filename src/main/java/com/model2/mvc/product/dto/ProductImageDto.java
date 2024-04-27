package com.model2.mvc.product.dto;

import com.model2.mvc.common.dto.Base64ImageDto;
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
public class ProductImageDto extends Base64ImageDto {
    private String description;
    private Boolean thumbnail;
}
