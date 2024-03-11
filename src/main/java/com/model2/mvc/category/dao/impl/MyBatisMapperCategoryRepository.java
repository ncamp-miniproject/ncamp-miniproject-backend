package com.model2.mvc.category.dao.impl;

import com.model2.mvc.category.dao.CategoryRepository;
import com.model2.mvc.category.domain.Category;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class MyBatisMapperCategoryRepository implements CategoryRepository {
    private final SqlSession sqlSession;

    public MyBatisMapperCategoryRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertCategory(Category categoryName) {
        try {
            this.sqlSession.insert("CategoryMapper.insert", categoryName);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Category> findAll() {
        return this.sqlSession.selectList("CategoryMapper.findAll");
    }

    @Override
    public void updateCategory(Category to) {
        this.sqlSession.update("CategoryMapper.update", to);
    }
}
