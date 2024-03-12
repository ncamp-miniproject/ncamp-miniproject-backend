package com.model2.mvc.product.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.in;

import com.model2.mvc.category.dao.CategoryRepository;
import com.model2.mvc.category.domain.Category;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    private CategoryRepository categoryRepository;

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
        Product product1 = new Product("Product1", 3000, 24);
        Product product2 = new Product("Product2", 2000, 12);
        Product product3 = new Product("Product3", 50000, 120);
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

    @Test
    public void insertWithCategory_DataIntegrityViolationException() {
        Product product = new Product("sample-product", 13000, 25000);
        product.setCategory(new Category(10000, "category"));
        assertThat(this.productDAO.insertProduct(product)).isFalse();
    }

    @Test
    public void insertWithCategory() {
        insertSample();
    }

    private Product insertSample() {
        Category toInsert = new Category("category1");
        Product product = new Product("sample-product", 13000, 25000);
        product.setCategory(toInsert);
        this.categoryRepository.insertCategory(toInsert);
        this.productDAO.insertProduct(product);
        return product;
    }
    
    @Test
    public void findWithCategory() {
        Product sampleProduct = insertSample();

        Optional<Product> foundProduct = this.productDAO.findById(sampleProduct.getProdNo());

        assertThat(foundProduct.get()).isNotNull();

        foundProduct.ifPresent(product -> {
            assertThat(product).isEqualTo(sampleProduct);
            assertThat(product.getCategory()).isNotNull();
            assertThat(product.getCategory().getCategoryName()).isEqualTo("category1");
        });
    }

    private List<Product> insertSampleList() {
        Category toInsert = new Category("category1");
        Product product = new Product("sample-product", 13000, 25000);
        product.setCategory(toInsert);
        this.categoryRepository.insertCategory(toInsert);
        this.productDAO.insertProduct(product);

        Category toInsert2 = new Category("category2");
        Product product2 = new Product("sample-product2", 13500, 200);
        product.setCategory(toInsert2);
        this.categoryRepository.insertCategory(toInsert2);
        this.productDAO.insertProduct(product2);

        Product product3 = new Product("sample-product3", 13600, 100);
        product3.setCategory(toInsert);
        this.productDAO.insertProduct(product3);

        return Arrays.asList(product, product2, product3);
    }

    @Test
    public void findListWithCategory_findAll() {
        List<Product> sampleProducts = insertSampleList();


    }
}