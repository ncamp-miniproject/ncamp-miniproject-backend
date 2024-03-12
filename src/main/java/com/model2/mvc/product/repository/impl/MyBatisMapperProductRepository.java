package com.model2.mvc.product.repository.impl;

import com.model2.mvc.product.dao.impl.MyBatisMapperProductDAO;
import com.model2.mvc.product.repository.ExtendedProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("myBatisMapperProductRepository")
@Primary
public class MyBatisMapperProductRepository extends MyBatisMapperProductDAO implements ExtendedProductRepository {

    public MyBatisMapperProductRepository(SqlSession sqlSession) {
        super(sqlSession);
    }


}
