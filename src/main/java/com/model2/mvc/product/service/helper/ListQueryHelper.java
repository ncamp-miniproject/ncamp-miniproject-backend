package com.model2.mvc.product.service.helper;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.repository.ProductRepository;

import java.util.List;

public interface ListQueryHelper {

    public List<Product> findProductList(ProductRepository repository, ListProductRequestDto dto);

    public int count(ProductRepository repository, ListProductRequestDto dto);
}
