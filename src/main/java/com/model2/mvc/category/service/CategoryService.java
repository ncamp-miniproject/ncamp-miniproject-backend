package com.model2.mvc.category.service;

import com.model2.mvc.category.domain.Category;

import java.util.List;

public interface CategoryService {

    public Category insertCategory(String categoryName);

    public List<Category> getCategoryList();

    public Category getCategory(int categoryNo);

    public boolean updateCategory(int categoryNo, String newCategoryName);
}
