package com.model2.mvc.product.repository;

import com.model2.mvc.common.ListData;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;

import java.util.Map;

public interface ExtendedProductRepository extends ProductDAO {

    public ListData<Product> findProductsByPriceRange(Map<String, Object> search);
}
