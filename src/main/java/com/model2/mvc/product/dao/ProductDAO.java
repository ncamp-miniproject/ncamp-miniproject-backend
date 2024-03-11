package com.model2.mvc.product.dao;

import com.model2.mvc.common.ListData;
import com.model2.mvc.product.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductDAO {

    public Optional<Product> findById(int prodNo);

    public Map<Integer, Product> findProductsByIds(List<Integer> ids);

    public ListData<Product> findProductsByProdName(Map<String, Object> search);

    public boolean insertProduct(Product toInsert);

    public boolean updateProduct(Product to);
}
