package com.model2.mvc.product.service;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.product.domain.Product;

public interface ProductService {
    public static ProductService getInstance() {
        return ProductServiceImpl.getInstance();
    }
    
    public Product addProduct(Product toInsert);
    
    public Product getProduct(int prodNo);
    
    public Map<String, Object> getProductList(Search seachVO);
    
    public Product updateProduct(Product to);
}
