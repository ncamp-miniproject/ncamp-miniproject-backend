package com.model2.mvc.category.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryRequestDTO {
    private int categoryNo;
    private String categoryName;
}
