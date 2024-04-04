package com.model2.mvc.product.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.config.context.ContextConfig;
import com.model2.mvc.config.context.MyBatisConfig;
import com.model2.mvc.config.context.PropertyConfig;
import com.model2.mvc.product.domain.OrderBy;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.domain.ProductImage;
import com.model2.mvc.product.repository.ProductImageRepository;
import com.model2.mvc.product.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest
@SpringJUnitConfig(classes = {
        ContextConfig.class, MyBatisConfig.class, PropertyConfig.class
})
public class TestMyBatisMapperProductImageRepository {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @BeforeEach
    public void before() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductImageMapper.clear",
                                                                      "ProductMapper.clear",
                                                                      "CategoryMapper.clear");
    }

    @AfterEach
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession);
    }

    @Test
    public void insert() {
        insertSample();
    }

    private Integer insertSample() {
        Product generated = generateOne();
        this.productRepository.insertProduct(generated);
        Integer prodNo = generated.getProdNo();
        List<ProductImage> productImages = Arrays.asList(generateProductImage(prodNo,
                                                                              "sample-file01.jpg",
                                                                              "desc-01",
                                                                              true),
                                                         generateProductImage(prodNo,
                                                                              "sample-file02.jpg",
                                                                              "desc-02",
                                                                              false),
                                                         generateProductImage(prodNo,
                                                                              "sample-file03.jpg",
                                                                              "desc-03",
                                                                              false),
                                                         generateProductImage(prodNo,
                                                                              "sample-file04.jpg",
                                                                              "desc-04",
                                                                              false));
        this.productImageRepository.insert(productImages);
        return prodNo;
    }

    private Product generateOne() {
        Product prod = new Product();
        prod.setProdName("Product1");
        prod.setProdDetail("Detail of prod");
        prod.setManuDate(LocalDate.of(2023, 1, 2));
        prod.setPrice(10000);
        prod.setRegDate(new Date(System.currentTimeMillis()));
        prod.setStock(13);
        return prod;
    }

    private ProductImage generateProductImage(Integer prodNo, String fileName, String description, Boolean thumbnail) {
        ProductImage productImage = new ProductImage();
        productImage.setProdNo(prodNo);
        productImage.setFileName(fileName);
        productImage.setDescription(description);
        productImage.setThumbnail(thumbnail);
        return productImage;
    }

    @Test
    public void findProductById() {
        Integer prodNo = insertSample();

        Optional<Product> product = this.productRepository.findById(prodNo);
        assertThat(product.isPresent()).isTrue();
        product.ifPresent(p -> {
            List<ProductImage> productImages = p.getProductImages();

            assertThat(productImages.size()).isEqualTo(4);
        });
    }

    @Test
    public void findList() {
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            insertSampleProduct(num >= 10 ? "product-" + num : "product-0" + num, num);
        }
        List<Product> products = this.productRepository.findListByProdName("product",
                                                                           false,
                                                                           1,
                                                                           1000,
                                                                           null,
                                                                           OrderBy.PROD_NAME,
                                                                           true);
        for (int i = 0; i < products.size(); i++) {
            int num = i + 1;
            assertThat(products.get(i).getProductImages().size()).isEqualTo(num);
        }
    }

    private final AtomicLong longValue = new AtomicLong(1L);

    private Integer insertSampleProduct(String prodName, int numOfImages) {
        Product sample = sampleProduct(prodName);
        this.productRepository.insertProduct(sample);
        Integer prodNo = sample.getProdNo();
        List<ProductImage> productImages = new LinkedList<>();
        for (int i = 0; i < numOfImages; i++) {
            productImages.add(sampleProductImage(prodNo));
        }
        this.productImageRepository.insert(productImages);
        return prodNo;
    }

    private Product sampleProduct(String prodName) {
        Product prod = new Product();
        prod.setProdName(prodName);
        prod.setProdDetail("Detail of prod");
        prod.setManuDate(LocalDate.of(2023, 1, 2));
        prod.setPrice(10000);
        prod.setRegDate(new Date(System.currentTimeMillis()));
        prod.setStock(13);
        return prod;
    }

    private ProductImage sampleProductImage(Integer prodNo) {
        long sampleLong = longValue.getAndIncrement();
        ProductImage productImage = new ProductImage();
        productImage.setProdNo(prodNo);
        productImage.setFileName("sample-file-" + sampleLong + ".jpg");
        productImage.setDescription("sample-description-" + sampleLong);
        productImage.setThumbnail(true);
        return productImage;
    }
}