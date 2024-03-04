package com.model2.mvc.product.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MyBatisMapperProductDAO implements ProductDAO {
    private SqlSession sqlSession;

    @Autowired
    public MyBatisMapperProductDAO(@Qualifier("sqlSessionTemplate") SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Optional<Product> findById(int prodNo) {
        return Optional.empty();
    }

    @Override
    public Map<Integer, Product> findProductsByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public ListData<Product> findProductsByProdName(Search search) {
        return null;
    }

    @Override
    public boolean insertProduct(Product toInsert) {
        return false;
    }

    @Override
    public boolean updateProduct(Product to) {
        return false;
    }
}
