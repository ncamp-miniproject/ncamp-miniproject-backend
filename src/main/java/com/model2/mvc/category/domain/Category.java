package com.model2.mvc.category.domain;

import java.util.Objects;

public class Category {
    private Integer categoryNo;
    private String categoryName;

    public Category() {
    }

    public Category(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryNo, String categoryName) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
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

    @Override
    public String toString() {
        return "Category{" + "categoryNo=" + categoryNo + ", categoryName='" + categoryName + '\'' + '}';
    }
}
