package com.model2.mvc.product.service;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.product.dto.request.AddProductRequestDto;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.dto.request.UpdateProductRequestDto;
import com.model2.mvc.product.dto.response.AddProductResponseDto;
import com.model2.mvc.product.dto.response.GetProductResponseDto;
import com.model2.mvc.product.dto.response.ListProductResponseDto;
import com.model2.mvc.product.dto.response.UpdateProductResponseDto;
import com.model2.mvc.user.domain.User;

import java.util.List;

public interface ProductService {

    public AddProductResponseDto addProduct(AddProductRequestDto toInsert, String contextRealPath);

    public GetProductResponseDto getProduct(int prodNo, User loginUser);

    public ListProductResponseDto getProductList(ListProductRequestDto requestDTO);

    public UpdateProductResponseDto updateProduct(UpdateProductRequestDto requestDTO);

    public Category addCategory(String categoryName);

    public List<Category> getCategoryList();
}
