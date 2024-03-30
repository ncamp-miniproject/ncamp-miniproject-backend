package com.model2.mvc.product.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.model2.mvc.category.dao.CategoryRepository;
import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.product.domain.OrderBy;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-config/context-*.xml" })
public class TestMyBatisMapperProductRepository {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private SqlSession sqlSession;

    @Before
    public void before() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductMapper.clear", "CategoryMapper.clear");
    }

    @After
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession);
    }

    @Test
    public void insert() {
        this.productRepository.insertProduct(generateOne());
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

    @Test
    public void findById() {
        Product prod = generateOne();
        this.productRepository.insertProduct(prod);

        Optional<Product> prodFound = this.productRepository.findById(prod.getProdNo());

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
        this.productRepository.insertProduct(product1);
        this.productRepository.insertProduct(product2);
        this.productRepository.insertProduct(product3);

        List<Integer> ids = new ArrayList<>();
        ids.add(product1.getProdNo());
        ids.add(product2.getProdNo());
        ids.add(product3.getProdNo());

        Map<Integer, Product> productsByIds = this.productRepository.findProductsByIds(ids);

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
        assertThat(this.productRepository.insertProduct(product)).isFalse();
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
        this.productRepository.insertProduct(product);
        return product;
    }

    @Test
    public void findWithCategory() {
        Product sampleProduct = insertSample();

        Optional<Product> foundProduct = this.productRepository.findById(sampleProduct.getProdNo());

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
        this.productRepository.insertProduct(product);

        Category toInsert2 = new Category("category2");
        Product product2 = new Product("sample-product2", 13500, 200);
        product2.setCategory(toInsert2);
        this.categoryRepository.insertCategory(toInsert2);
        this.productRepository.insertProduct(product2);

        Product product3 = new Product("sample-product3", 13600, 100);
        product3.setCategory(toInsert);
        this.productRepository.insertProduct(product3);

        return Arrays.asList(product, product2, product3);
    }

    @Test
    public void findListWithCategory_findAll() {
        insertSampleList();

        List<Product> found = this.productRepository.findAllInCategory(1, 3, null);
        assertThat(found.size()).isEqualTo(3);
        assertThat((int)found.stream()
                .filter(p -> p.getCategory().getCategoryName().equals("category1"))
                .count()).isEqualTo(2);
    }

    @Test
    public void findListWithCategory_findAllWithSpecificCategory() {
        List<Product> products = insertSampleList();
        Integer categoryNo = products.get(0).getCategory().getCategoryNo();

        List<Product> found = this.productRepository.findAllInCategory(1, 3, categoryNo);
        assertThat(found.size()).isEqualTo(2);
        assertThat((int)found.stream()
                .filter(p -> p.getCategory().getCategoryName().equals("category1"))
                .count()).isEqualTo(2);
    }

    @Test
    public void countAll_resultOverZero_noCategorySpecified() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .build();
            this.productRepository.insertProduct(product);
        }
        int result = this.productRepository.countAll(null);
        assertThat(result).isEqualTo(20);
    }

    @Test
    public void countAll_resultZero_noCategorySpecified() {
        int result = this.productRepository.countAll(null);
        assertThat(result).isZero();
    }

    @Test
    public void countAll_resultZero_productInserted_categorySpecified() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .build();
            this.productRepository.insertProduct(product);
        }
        int result = this.productRepository.countAll(10000);
        assertThat(result).isZero();
    }

    @Test
    public void countAll_resultOverZero_categorySpecified() {
        Category sampleCategory = new Category("sample-category");
        this.sqlSession.insert("CategoryMapper.insert", sampleCategory);
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(num % 2 == 0 ? sampleCategory : null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        int result = this.productRepository.countAll(sampleCategory.getCategoryNo());
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void countByProdName_resultZero_categoryNotSpecified() {
        assertThat(this.productRepository.countByProdName("prod-name", false, null)).isZero();
    }

    @Test
    public void countByProdName_resultIsOne_categoryNotSpecified() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .build();
            this.productRepository.insertProduct(product);
        }
        int result = this.productRepository.countByProdName("product-11", false, null);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void countByProdName_resultIsLargerThan1_categoryNotSpecified() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + (num % 2))
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .build();
            this.productRepository.insertProduct(product);
        }
        int result = this.productRepository.countByProdName("product-1", false, null);
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void countByProdName_resultIs1_categoryIsSpecified() {
        Category sampleCategory = new Category("sample-category");
        this.sqlSession.insert("CategoryMapper.insert", sampleCategory);
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(("product-" + num).equals("product-1") ? sampleCategory : null)
                    .build();
            this.productRepository.insertProduct(product);
        }
        int result = this.productRepository.countByProdName("product-1", false, sampleCategory.getCategoryNo());
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void countByProdName_resultIsLargerThan1_categoryIsSpecified() {
        Category sampleCategory = new Category("sample-category");
        this.sqlSession.insert("CategoryMapper.insert", sampleCategory);
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + ((num - 1) % 4 + 1))
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(num % 2 == 1 ? sampleCategory : null)
                    .build();
            this.productRepository.insertProduct(product);
        }
        int result = this.productRepository.countByProdName("product-1", false, sampleCategory.getCategoryNo());
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void findListByProdName_orderBy_prodName_ascend() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Random random = new Random();
            Product product = Product.builder()
                    .prodName("product-" + (Math.abs(random.nextInt()) % 100 + 1))
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<String> prodLabels = this.productRepository.findListByProdName("product",
                                                                             false,
                                                                             1,
                                                                             1000,
                                                                             null,
                                                                             OrderBy.PROD_NAME,
                                                                             true)
                .stream()
                .map(Product::getProdName)
                .collect(Collectors.toList());
        for (int i = 0; i < prodLabels.size() - 1; i++) {
            assertThat(prodLabels.get(i)).isLessThanOrEqualTo(prodLabels.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_prodName_descend() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Random random = new Random();
            Product product = Product.builder()
                    .prodName("product-" + (Math.abs(random.nextInt()) % 100 + 1))
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<String> prodNames = this.productRepository.findListByProdName("product",
                                                                             false,
                                                                             1,
                                                                             1000,
                                                                             null,
                                                                             OrderBy.PROD_NAME,
                                                                             false)
                .stream()
                .map(Product::getProdName)
                .collect(Collectors.toList());
        for (int i = 0; i < prodNames.size() - 1; i++) {
            assertThat(prodNames.get(i)).isGreaterThanOrEqualTo(prodNames.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_price_ascend() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Random random = new Random();
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * (Math.abs(random.nextInt()) % 100 + 1))
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prices = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PRICE,
                                                                         true)
                .stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());
        for (int i = 0; i < prices.size() - 1; i++) {
            assertThat(prices.get(i)).isLessThanOrEqualTo(prices.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_price_descend() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Random random = new Random();
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * (Math.abs(random.nextInt()) % 100 + 1))
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prices = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PRICE,
                                                                         false)
                .stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());
        for (int i = 0; i < prices.size() - 1; i++) {
            assertThat(prices.get(i)).isGreaterThanOrEqualTo(prices.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_prodNo_ascend() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prodNumbers = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PROD_NO,
                                                                         true)
                .stream()
                .map(Product::getProdNo)
                .collect(Collectors.toList());
        for (int i = 0; i < prodNumbers.size() - 1; i++) {
            assertThat(prodNumbers.get(i)).isLessThanOrEqualTo(prodNumbers.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_prodNo_descend() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prodNumbers = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PROD_NO,
                                                                         false)
                .stream()
                .map(Product::getProdNo)
                .collect(Collectors.toList());
        for (int i = 0; i < prodNumbers.size() - 1; i++) {
            assertThat(prodNumbers.get(i)).isGreaterThanOrEqualTo(prodNumbers.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_prodName_default() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<String> prodNames = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PROD_NAME,
                                                                         null)
                .stream()
                .map(Product::getProdName)
                .collect(Collectors.toList());;
        for (int i = 0; i < prodNames.size() - 1; i++) {
            assertThat(prodNames.get(i)).isGreaterThanOrEqualTo(prodNames.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_price_default() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prices = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PRICE,
                                                                         null)
                .stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());;
        for (int i = 0; i < prices.size() - 1; i++) {
            assertThat(prices.get(i)).isGreaterThanOrEqualTo(prices.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_prodNo_default() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prodNumbers = this.productRepository.findListByProdName("product",
                                                                         false,
                                                                         1,
                                                                         1000,
                                                                         null,
                                                                         OrderBy.PROD_NO,
                                                                         null)
                .stream()
                .map(Product::getProdNo)
                .collect(Collectors.toList());

        for (int i = 0; i < prodNumbers.size() - 1; i++) {
            assertThat(prodNumbers.get(i)).isGreaterThanOrEqualTo(prodNumbers.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_default() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prodNumbers = this.productRepository.findListByProdName("product",
                                                                              false,
                                                                              1,
                                                                              1000,
                                                                              null,
                                                                              null,
                                                                              null)
                .stream()
                .map(Product::getProdNo)
                .collect(Collectors.toList());

        for (int i = 0; i < prodNumbers.size() - 1; i++) {
            assertThat(prodNumbers.get(i)).isGreaterThanOrEqualTo(prodNumbers.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_default_but_ascendFlagIsSet() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prodNumbers = this.productRepository.findListByProdName("product",
                                                                              false,
                                                                              1,
                                                                              1000,
                                                                              null,
                                                                              null,
                                                                              true)
                .stream()
                .map(Product::getProdNo)
                .collect(Collectors.toList());

        for (int i = 0; i < prodNumbers.size() - 1; i++) {
            assertThat(prodNumbers.get(i)).isGreaterThanOrEqualTo(prodNumbers.get(i + 1));
        }
    }

    @Test
    public void findListByProdName_orderBy_default_but_ascendFlagIsSet_ascendIsFalse() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            Product product = Product.builder()
                    .prodName("product-" + num)
                    .prodDetail("sample-" + num)
                    .price(100 * num)
                    .stock(10 * num)
                    .regDate(new Date(System.currentTimeMillis()))
                    .manuDate(LocalDate.now())
                    .category(null)
                    .build();
            this.productRepository.insertProduct(product);
        }

        List<Integer> prodNumbers = this.productRepository.findListByProdName("product",
                                                                              false,
                                                                              1,
                                                                              1000,
                                                                              null,
                                                                              null,
                                                                              false)
                .stream()
                .map(Product::getProdNo)
                .collect(Collectors.toList());

        for (int i = 0; i < prodNumbers.size() - 1; i++) {
            assertThat(prodNumbers.get(i)).isGreaterThanOrEqualTo(prodNumbers.get(i + 1));
        }
    }
}