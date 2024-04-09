package com.model2.mvc.product.dto.response;

import com.model2.mvc.common.Pagination;
import com.model2.mvc.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ListProductResponseDto {
    private int count;
    private List<Product> products;
    private Pagination pagination;
}
