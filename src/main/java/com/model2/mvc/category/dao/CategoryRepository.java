package com.model2.mvc.category.dao;

import com.model2.mvc.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    public void insertCategory(Category categoryName);

    public List<Category> findAll();

    public Optional<Category> findById(int categoryNo);

    public void updateCategory(Category to);
}
