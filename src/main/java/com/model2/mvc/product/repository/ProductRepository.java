package com.model2.mvc.product.repository;

import com.model2.mvc.common.ListData;
import com.model2.mvc.product.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {

    public Optional<Product> findById(int prodNo);

    public boolean insertProduct(Product toInsert);

    public boolean updateProduct(Product to);

    public Map<Integer, Product> findProductsByIds(List<Integer> ids);

    public ListData<Product> findListByProdName(String prodName,
                                                boolean match,
                                                int page,
                                                int pageSize,
                                                Integer categoryNo);

    public ListData<Product> findListByPriceRange(Integer lowerBound,
                                                  Integer upperBound,
                                                  int page,
                                                  int pageSize,
                                                  Integer categoryNo);

    public ListData<Product> findAllInCategory(int page, int pageSize, Integer categoryNo);
}
