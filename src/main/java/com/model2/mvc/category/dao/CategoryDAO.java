package com.model2.mvc.category.dao;

import com.model2.mvc.category.domain.Category;

import java.util.List;

public interface CategoryDAO {

    public void insertCategory(Category categoryName);

    public List<Category> findAll();

    public void updateCategory(Category to);
}
