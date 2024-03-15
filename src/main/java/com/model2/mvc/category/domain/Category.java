package com.model2.mvc.category.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    private Integer categoryNo;
    private String categoryName;

    public Category(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category)o;
        return Objects.equals(categoryNo, category.categoryNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryNo);
    }
}
