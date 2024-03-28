package com.model2.mvc.product.dto.response;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
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
    private List<Category> categories;
    private Page pageInfo;
    private String menuMode;
    private Search searchInfo;
    private Integer currentCategoryNo;
}
