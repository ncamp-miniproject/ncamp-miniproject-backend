package com.model2.mvc.category.service.impl;

import com.model2.mvc.category.dao.CategoryRepository;
import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category insertCategory(String categoryName) {
        Category category = new Category(categoryName);
        this.categoryRepository.insertCategory(category);
        return category;
    }

    @Override
    public List<Category> getCategoryList() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategory(int categoryNo) {
        return this.categoryRepository.findById(categoryNo).orElse(null);
    }

    @Override
    public boolean updateCategory(int categoryNo, String newCategoryName) {
        if (isCategoryAbsent(categoryNo)) {
            return false;
        }
        this.categoryRepository.updateCategory(new Category(categoryNo, newCategoryName));
        return true;
    }

    private boolean isCategoryAbsent(int categoryNo) {
        return !this.categoryRepository.findById(categoryNo).isPresent();
    }
}
