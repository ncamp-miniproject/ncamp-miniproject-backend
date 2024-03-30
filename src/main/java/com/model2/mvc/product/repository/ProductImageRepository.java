package com.model2.mvc.product.repository;

import com.model2.mvc.product.domain.ProductImage;

import java.util.List;

public interface ProductImageRepository {

    public boolean insert(List<ProductImage> productImage);
}
