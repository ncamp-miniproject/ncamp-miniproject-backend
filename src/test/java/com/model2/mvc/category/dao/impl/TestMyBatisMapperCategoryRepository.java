package com.model2.mvc.category.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.category.dao.CategoryRepository;
import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.config.context.ContextConfig;
import com.model2.mvc.config.context.MyBatisConfig;
import com.model2.mvc.config.context.PropertyConfig;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@SpringJUnitConfig(classes = {
        ContextConfig.class, MyBatisConfig.class, PropertyConfig.class
})
public class TestMyBatisMapperCategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SqlSession sqlSession;

    @BeforeEach
    public void init() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductImageMapper.clear",
                                                                      "ProductMapper.clear",
                                                                      "CategoryMapper.clear");
    }

    @AfterEach
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession,
                                                     "ProductImageMapper.clear",
                                                     "ProductMapper.clear",
                                                     "CategoryMapper.clear");
    }

    @Test
    public void insert() {
        insertSample();
    }

    private List<Category> insertSample() {
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        this.categoryRepository.insertCategory(category1);
        this.categoryRepository.insertCategory(category2);
        return Arrays.asList(category1, category2);
    }

    @Test
    public void select() {
        List<Category> sample = insertSample();
        List<Category> found = this.categoryRepository.findAll();
        assertThat(found.size()).isEqualTo(sample.size());
        assertThat(found).contains(sample.toArray(new Category[0]));
    }
}