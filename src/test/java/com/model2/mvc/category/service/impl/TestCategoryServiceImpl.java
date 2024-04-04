package com.model2.mvc.category.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
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
public class TestCategoryServiceImpl {

    @Autowired
    private CategoryService categoryService;

    private SqlSession sqlSession;

    @BeforeEach
    public void init() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductImageMapper.clear", "ProductMapper.clear", "CategoryMapper.clear");
    }

    @AfterEach
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession, "ProductImageMapper.clear", "ProductMapper.clear", "CategoryMapper.clear");
    }

    @Test
    public void insert() {
        insertSample();
    }

    private List<Category> insertSample() {
        Category category1 = this.categoryService.insertCategory("category1");
        Category category2 = this.categoryService.insertCategory("category2");
        return Arrays.asList(category1, category2);
    }

    @Test
    public void select() {
        List<Category> sample = insertSample();
        List<Category> found = this.categoryService.getCategoryList();
        assertThat(found.size()).isEqualTo(sample.size());
        assertThat(found).contains(sample.toArray(new Category[0]));
    }
}