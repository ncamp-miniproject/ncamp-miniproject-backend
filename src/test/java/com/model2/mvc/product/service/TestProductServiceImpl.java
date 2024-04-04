package com.model2.mvc.product.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.config.context.ContextConfig;
import com.model2.mvc.config.context.MyBatisConfig;
import com.model2.mvc.config.context.PropertyConfig;
import com.model2.mvc.config.context.TransactionConfig;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.CreateProductRequestDto;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.dto.response.AddProductResponseDto;
import com.model2.mvc.product.dto.response.GetProductResponseDto;
import com.model2.mvc.product.dto.response.ListProductResponseDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@SpringJUnitConfig(classes = {
        ContextConfig.class, MyBatisConfig.class, PropertyConfig.class, TransactionConfig.class
})
public class TestProductServiceImpl {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private SqlSession sqlSession;

    @BeforeEach
    public void before() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductImageMapper.clear", "ProductMapper.clear", "CategoryMapper.clear");
    }

    @AfterEach
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession, "ProductMapper.clear", "CategoryMapper.clear");
    }

    @Test
    public void testAddProduct() {
        List<Product> sampleProducts = insertSampleProducts();
        System.out.println("\n=================================");
        System.out.println("Sample products:");
        System.out.println(sampleProducts);
        System.out.println("===================================\n");
    }

    private List<Product> insertSampleProducts() {
        Category category1 = this.categoryService.insertCategory("category1");
        Category category2 = this.categoryService.insertCategory("category2");
        CreateProductRequestDto req1 = new CreateProductRequestDto();
        req1.setManuDate(LocalDate.of(2017, Month.APRIL, 25));
        req1.setPrice(1000);
        req1.setProdName("prod1");
        req1.setStock(20);
        req1.setCategoryNo(category1.getCategoryNo());
        CreateProductRequestDto req2 = new CreateProductRequestDto();
        req2.setPrice(200);
        req2.setProdName("prod2");
        req2.setStock(30);
        req2.setCategoryNo(category2.getCategoryNo());

        AddProductResponseDto resp1 = this.productService.addProduct(req1, "");
        AddProductResponseDto resp2 = this.productService.addProduct(req2, "");
        Product prod1 = new Product();
        prod1.setProdNo(resp1.getProdNo());
        prod1.setProdDetail(resp1.getProdDetail());
        prod1.setManuDate(resp1.getManuDate());
        prod1.setPrice(resp1.getPrice());
        prod1.setProdName(resp1.getProdName());
        prod1.setRegDate(resp1.getRegDate());
        prod1.setStock(resp1.getStock());
        prod1.setCategory(resp1.getCategory());
        Product prod2 = new Product();
        prod2.setProdNo(resp2.getProdNo());
        prod2.setProdDetail(resp2.getProdDetail());
        prod2.setManuDate(resp2.getManuDate());
        prod2.setPrice(resp2.getPrice());
        prod2.setProdName(resp2.getProdName());
        prod2.setRegDate(resp2.getRegDate());
        prod2.setStock(resp2.getStock());
        prod2.setCategory(resp2.getCategory());
        return Arrays.asList(prod1, prod2);
    }

    @Test
    public void testGetProduct() {
        List<Product> sampleProducts = insertSampleProducts();

        for (Product prod : sampleProducts) {
            GetProductResponseDto result = this.productService.getProduct(prod.getProdNo(), null);
            System.out.println("\n===============================");
            System.out.println("Result of ProductsService.getProduct");
            System.out.println(result);
            System.out.println("=====================================\n");
            assertThat(result.getProdNo()).isEqualTo(prod.getProdNo());
            assertThat(result.getManuDate()).isEqualTo(prod.getManuDate());
            assertThat(result.getPrice()).isEqualTo(prod.getPrice());
            assertThat(result.getProdDetail()).isEqualTo(prod.getProdDetail());
            assertThat(result.getProdName()).isEqualTo(prod.getProdName());
            assertThat(result.getStock()).isEqualTo(prod.getStock());
            assertThat(result.getCategoryName()).isEqualTo(prod.getCategory().getCategoryName());
        }
    }

    @Test
    public void testGetProductList_FindAll_NoCategory() {
        List<Product> sampleProducts = insertSampleProducts();

        ListProductRequestDto req = new ListProductRequestDto();
        ListProductResponseDto result = this.productService.getProductList(req);

        System.out.println("\n===============================");
        System.out.println("List result:");
        System.out.println(result);
        System.out.println("===============================\n");

        assertThat(result.getProducts().size()).isEqualTo(2);
        assertThat(result.getProducts()).contains(sampleProducts.toArray(new Product[0]));
        assertThat(result.getCount()).isEqualTo(2);
        assertThat(result.getPageInfo().getCurrentPage()).isEqualTo(1);
        assertThat(result.getPageInfo().getPageSize()).isEqualTo(3);
    }

    @Test
    public void testGetProductList_FindAll_SpecifiedCategory() {
        List<Product> sampleProducts = insertSampleProducts();
        Product sampleProduct = sampleProducts.get(0);

        ListProductRequestDto req = new ListProductRequestDto();
        req.setCategoryNo(sampleProduct.getCategory().getCategoryNo());
        ListProductResponseDto result = this.productService.getProductList(req);

        System.out.println("\n===============================");
        System.out.println("List result:");
        System.out.println(result);
        System.out.println("===============================\n");

        assertThat(result.getProducts().size()).isEqualTo(1);
        assertThat(result.getProducts()).contains(sampleProduct);
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getPageInfo().getCurrentPage()).isEqualTo(1);
        assertThat(result.getPageInfo().getPageSize()).isEqualTo(3);
    }

    public void testUpdateProduct() {
    }

    public void testAddCategory() {
    }
}