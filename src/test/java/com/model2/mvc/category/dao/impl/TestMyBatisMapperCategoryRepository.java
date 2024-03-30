package com.model2.mvc.category.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.category.dao.CategoryRepository;
import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(locations = { "classpath*:spring-config/context-*.xml" })
public class TestMyBatisMapperCategoryRepository extends TestCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SqlSession sqlSession;

    @Before
    public void init() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductImageMapper.clear", "ProductMapper.clear", "CategoryMapper.clear");
    }

    @After
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession, "ProductImageMapper.clear", "ProductMapper.clear", "CategoryMapper.clear");
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