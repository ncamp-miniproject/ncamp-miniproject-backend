package com.model2.mvc.product.dao;

import com.model2.mvc.common.ListData;
import com.model2.mvc.product.dao.SimpleProductDAO;
import com.model2.mvc.product.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductDAO extends SimpleProductDAO {

    public Map<Integer, Product> findProductsByIds(List<Integer> ids);

    public ListData<Product> findProductsByProdName(Map<String, Object> search);
}
