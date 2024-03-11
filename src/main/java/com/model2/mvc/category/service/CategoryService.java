package com.model2.mvc.category.service;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.dto.request.UpdateCategoryRequestDTO;

import java.util.List;

public interface CategoryService {

    public void insertCategory(String categoryName);

    public List<Category> getCategoryList();

    public void updateCategory(UpdateCategoryRequestDTO requestDTO);
}
