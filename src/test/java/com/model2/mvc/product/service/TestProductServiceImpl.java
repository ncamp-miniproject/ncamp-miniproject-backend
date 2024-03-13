package com.model2.mvc.product.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.response.AddProductResponseDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-config/context-*.xml")
public class TestProductServiceImpl extends TestCase {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private SqlSession sqlSession;

    @Before
    public void before() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductMapper.clear", "CategoryMapper.clear");
    }

    @After
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
        AddProductRequestDTO req1 = new AddProductRequestDTO();
        req1.setFileName("sample-file");
        req1.setManuDate("2017-02-11");
        req1.setPrice(1000);
        req1.setProdName("prod1");
        req1.setStock(20);
        req1.setCategoryNo(category1.getCategoryNo());
        AddProductRequestDTO req2 = new AddProductRequestDTO();
        req2.setPrice(200);
        req2.setProdName("prod2");
        req2.setStock(30);
        req2.setCategoryNo(category2.getCategoryNo());

        AddProductResponseDTO resp1 = this.productService.addProduct(req1);
        AddProductResponseDTO resp2 = this.productService.addProduct(req2);
        Product prod1 = new Product();
        prod1.setProdNo(resp1.getProdNo());
        prod1.setProdDetail(resp1.getProdDetail());
        prod1.setFileName(resp1.getFileName());
        prod1.setManuDate(resp1.getManuDate());
        prod1.setPrice(resp1.getPrice());
        prod1.setProdName(resp1.getProdName());
        prod1.setRegDate(resp1.getRegDate());
        prod1.setStock(resp1.getStock());
        prod1.setCategory(resp1.getCategory());
        Product prod2 = new Product();
        prod2.setProdNo(resp2.getProdNo());
        prod2.setProdDetail(resp2.getProdDetail());
        prod2.setFileName(resp2.getFileName());
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
            GetProductResponseDTO result = this.productService.getProduct(prod.getProdNo());
            System.out.println("\n===============================");
            System.out.println("Result of ProductsService.getProduct");
            System.out.println(result);
            System.out.println("=====================================\n");
            assertThat(result.getProdNo()).isEqualTo(prod.getProdNo());
            assertThat(result.getFileName()).isEqualTo(prod.getFileName());
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

        ListProductRequestDTO req = new ListProductRequestDTO();
        ListProductResponseDTO result = this.productService.getProductList(req);

        System.out.println("\n===============================");
        System.out.println("List result:");
        System.out.println(result);
        System.out.println("===============================\n");

        assertThat(result.getProducts().size()).isEqualTo(2);
        assertThat(result.getProducts()).contains(sampleProducts.toArray(new Product[0]));
        assertThat(result.getCount()).isEqualTo(2);
        assertThat(result.getCategories()).contains(sampleProducts.stream()
                                                            .map(Product::getCategory)
                                                            .collect(Collectors.toList())
                                                            .toArray(new Category[0]));
        assertThat(result.getPageInfo().getCurrentPage()).isEqualTo(1);
        assertThat(result.getPageInfo().getPageSize()).isEqualTo(3);
    }

    @Test
    public void testGetProductList_FindAll_SpecifiedCategory() {
        List<Product> sampleProducts = insertSampleProducts();
        Product sampleProduct = sampleProducts.get(0);

        ListProductRequestDTO req = new ListProductRequestDTO();
        req.setCategoryNo(sampleProduct.getCategory().getCategoryNo());
        ListProductResponseDTO result = this.productService.getProductList(req);

        System.out.println("\n===============================");
        System.out.println("List result:");
        System.out.println(result);
        System.out.println("===============================\n");

        assertThat(result.getProducts().size()).isEqualTo(1);
        assertThat(result.getProducts()).contains(sampleProduct);
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getCategories()).contains(sampleProduct.getCategory());
        assertThat(result.getPageInfo().getCurrentPage()).isEqualTo(1);
        assertThat(result.getPageInfo().getPageSize()).isEqualTo(3);
    }

    public void testUpdateProduct() {
    }

    public void testAddCategory() {
    }
}