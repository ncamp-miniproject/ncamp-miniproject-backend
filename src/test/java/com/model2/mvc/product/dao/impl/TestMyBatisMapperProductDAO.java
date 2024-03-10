package com.model2.mvc.product.dao.impl;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/common.xml", "classpath*:spring-config/context-*.xml" })
public class TestMyBatisMapperProductDAO {

    @Autowired
    @Qualifier("myBatisMapperProductDAO")
    private ProductDAO productDAO;

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    @Before
    public void before() {
        this.sqlSession.delete("ProductMapper.clear");
    }

    @After
    public void after() {
        this.sqlSession.delete("ProductMapper.clear");
    }

    @Test
    public void insert() {
        this.productDAO.insertProduct(generateOne());
    }

    private Product generateOne() {
        Product prod = new Product();
        prod.setProdName("Product1");
        prod.setProdDetail("Detail of prod");
        prod.setManuDate(LocalDate.of(2023, 1, 2));
        prod.setPrice(10000);
        prod.setFileName("asdf.png");
        prod.setRegDate(new Date(System.currentTimeMillis()));
        prod.setStock(13);
        return prod;
    }

    @Test
    public void findById() {
        Product prod = generateOne();
        this.productDAO.insertProduct(prod);

        Optional<Product> prodFound = this.productDAO.findById(prod.getProdNo());

        assertThatNoException().isThrownBy(() -> prodFound.orElseThrow(RuntimeException::new));

        Product product = prodFound.get();
        Product expected = prod;

        assertThat(product).isEqualTo(expected);

        assertThat(product.getPrice()).isEqualTo(expected.getPrice());
    }

    @Test
    public void findProductsByIds() {
        Product product1 = new Product();
        product1.setProdName("Product1");
        Product product2 = new Product();
        product2.setProdName("Product2");
        Product product3 = new Product();
        product3.setProdName("Product3");
        this.productDAO.insertProduct(product1);
        this.productDAO.insertProduct(product2);
        this.productDAO.insertProduct(product3);

        List<Integer> ids = new ArrayList<>();
        ids.add(product1.getProdNo());
        ids.add(product2.getProdNo());
        ids.add(product3.getProdNo());

        Map<Integer, Product> productsByIds = this.productDAO.findProductsByIds(ids);

        assertThat(productsByIds.size()).isEqualTo(3);

        List<Integer> foundIds = productsByIds.keySet()
                .stream()
                .map(productsByIds::get)
                .map(Product::getProdNo)
                .collect(Collectors.toList());
        assertThat(foundIds).contains(product1.getProdNo(), product2.getProdNo(), product3.getProdNo());

        List<String> foundNames = productsByIds.keySet()
                .stream()
                .map(productsByIds::get)
                .map(Product::getProdName)
                .collect(Collectors.toList());
        assertThat(foundNames).contains(product1.getProdName(), product2.getProdName(), product3.getProdName());
    }
}