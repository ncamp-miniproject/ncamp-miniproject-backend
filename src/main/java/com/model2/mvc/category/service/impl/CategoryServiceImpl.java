package com.model2.mvc.category.service.impl;

import com.model2.mvc.category.dao.CategoryDAO;
import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.dto.request.UpdateCategoryRequestDTO;
import com.model2.mvc.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void insertCategory(String categoryName) {
        this.categoryDAO.insertCategory(new Category(categoryName));
    }

    @Override
    public List<Category> getCategoryList() {
        return this.categoryDAO.findAll();
    }

    @Override
    public void updateCategory(UpdateCategoryRequestDTO requestDTO) {
        this.categoryDAO.updateCategory(new Category(requestDTO.getCategoryNo(), requestDTO.getCategoryName()));
    }
}
