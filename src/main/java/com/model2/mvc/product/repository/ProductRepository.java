package com.model2.mvc.product.repository;

import com.model2.mvc.product.domain.OrderBy;
import com.model2.mvc.product.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {

    public Optional<Product> findById(int prodNo);

    public boolean insertProduct(Product toInsert);

    public boolean updateProduct(Product to);

    @Deprecated
    public int countAll(Integer categoryNo);

    public int countAll(Integer categoryNo, String seller);

    @Deprecated
    public int countByProdName(String prodName, boolean match, Integer categoryNo);

    public int countByProdName(String prodName, boolean match, Integer categoryNo, String seller);

    @Deprecated
    public int countByPriceRange(Integer lowerBound, Integer upperBound, Integer categoryNo);

    public int countByPriceRange(Integer lowerBound, Integer upperBound, Integer categoryNo, String seller);

    public Map<Integer, Product> findProductsByIds(List<Integer> ids);

    @Deprecated
    public List<Product> findListByProdName(String prodName, boolean match, int page, int pageSize, Integer categoryNo);

    @Deprecated
    public List<Product> findListByProdName(String prodName,
                                            boolean match,
                                            int page,
                                            int pageSize,
                                            Integer categoryNo,
                                            OrderBy orderBy,
                                            Boolean ascend);

    public List<Product> findListByProdName(String prodName,
                                            boolean match,
                                            int page,
                                            int pageSize,
                                            Integer categoryNo,
                                            OrderBy orderBy,
                                            Boolean ascend,
                                            String seller);

    @Deprecated
    public List<Product> findListByPriceRange(Integer lowerBound,
                                              Integer upperBound,
                                              int page,
                                              int pageSize,
                                              Integer categoryNo);

    @Deprecated
    public List<Product> findListByPriceRange(Integer lowerBound,
                                              Integer upperBound,
                                              int page,
                                              int pageSize,
                                              Integer categoryNo,
                                              OrderBy orderBy,
                                              Boolean ascend);

    public List<Product> findListByPriceRange(Integer lowerBound,
                                              Integer upperBound,
                                              int page,
                                              int pageSize,
                                              Integer categoryNo,
                                              OrderBy orderBy,
                                              Boolean ascend,
                                              String seller);

    @Deprecated
    public List<Product> findAllInCategory(int page, int pageSize, Integer categoryNo);

    @Deprecated
    public List<Product> findAllInCategory(int page, int pageSize, Integer categoryNo, OrderBy orderBy, Boolean ascend);

    @Deprecated
    public List<Product> findAllInCategory(int page, int pageSize, Integer categoryNo, String seller);

    public List<Product> findAllInCategory(int page,
                                           int pageSize,
                                           Integer categoryNo,
                                           OrderBy orderBy,
                                           Boolean ascend,
                                           String seller);
}
