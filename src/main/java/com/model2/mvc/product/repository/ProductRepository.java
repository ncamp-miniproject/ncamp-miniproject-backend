package com.model2.mvc.product.repository;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;

public interface ProductRepository extends ProductDAO {

    public ListData<Product> findProductsByPriceRange(Search search);
}
