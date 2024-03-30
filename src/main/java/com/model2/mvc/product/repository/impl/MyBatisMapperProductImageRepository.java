package com.model2.mvc.product.repository.impl;

import com.model2.mvc.product.domain.ProductImage;
import com.model2.mvc.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisMapperProductImageRepository implements ProductImageRepository {
    private final SqlSession sqlSession;

    @Override
    public boolean insert(List<ProductImage> productImage) {
        try {
            productImage.forEach(i -> this.sqlSession.insert("ProductImageMapper.insert", i));
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
