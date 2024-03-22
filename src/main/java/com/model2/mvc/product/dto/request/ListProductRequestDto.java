package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.SearchCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ListProductRequestDto {
    private Integer page;
    private Integer pageSize;
    private String searchKeyword;
    private SearchCondition searchCondition;
    private String menu;
    private Integer categoryNo;
}
