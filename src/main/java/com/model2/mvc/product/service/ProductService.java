package com.model2.mvc.product.service;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.AddProductResponseDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.dto.response.UpdateProductResponseDTO;

public interface ProductService {
    public static ProductService getInstance() {
        return ProductServiceImpl.getInstance();
    }
    
    public AddProductResponseDTO addProduct(AddProductRequestDTO toInsert);
    
    public GetProductResponseDTO getProduct(int prodNo);
    
    public ListProductResponseDTO getProductList(ListProductRequestDTO requestDTO);
    
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO requestDTO);
}
