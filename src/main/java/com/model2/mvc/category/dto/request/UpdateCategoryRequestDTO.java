package com.model2.mvc.category.dto.request;

public class UpdateCategoryRequestDTO {
    private int categoryNo;
    private String categoryName;

    public UpdateCategoryRequestDTO() {
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
