package com.model2.mvc.product.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("myBatisMapperProductDAO")
@Primary
public class MyBatisMapperProductDAO implements ProductRepository {
    private final SqlSession sqlSession;

    @Autowired
    public MyBatisMapperProductDAO(@Qualifier("sqlSessionTemplate") SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Optional<Product> findById(int prodNo) {
        return Optional.ofNullable(this.sqlSession.selectOne("ProductMapper.findById", prodNo));
    }

    @Override
    public Map<Integer, Product> findProductsByIds(List<Integer> ids) {
        List<Product> productsFound = this.sqlSession.selectList("ProductMapper.findProductsByIds", ids);
        if (productsFound == null) {
            return new HashMap<>();
        }
        Map<Integer, Product> idProductMap = new HashMap<>();
        productsFound.forEach(p -> idProductMap.put(p.getProdNo(), p));
        return idProductMap;
    }

    @Override
    public ListData<Product> findProductsByProdName(Map<String, Object> search) {
        ListData<Product> productsFound = this.sqlSession.selectOne("ProductMapper.findProducts", search);
        return productsFound == null ? new ListData<>(0, new ArrayList<>()) : productsFound;
    }

    @Override
    public ListData<Product> findProductsByPriceRange(Map<String, Object> search) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insertProduct(Product toInsert) {
        try {
            this.sqlSession.insert("ProductMapper.insert", toInsert);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product to) {
        try {
            this.sqlSession.insert("ProductMapper.update", to);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
