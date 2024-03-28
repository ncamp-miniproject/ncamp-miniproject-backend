package com.model2.mvc.product.service;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.util.IntegerUtil;
import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.CreateProductRequestDto;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.dto.request.UpdateProductRequestDto;
import com.model2.mvc.product.dto.response.AddProductResponseDto;
import com.model2.mvc.product.dto.response.GetProductResponseDto;
import com.model2.mvc.product.dto.response.ListProductResponseDto;
import com.model2.mvc.product.dto.response.ProductDto;
import com.model2.mvc.product.dto.response.UpdateProductResponseDto;
import com.model2.mvc.product.repository.ProductRepository;
import com.model2.mvc.product.service.helper.ListQueryHelper;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public AddProductResponseDto addProduct(CreateProductRequestDto toInsert, String contextRealPath) {
        Product product = new Product();
        product.setFileName(storeFile(toInsert.getBase64ImageData(), contextRealPath, toInsert.getImageName()));
        product.setManuDate(toInsert.getManuDate());
        product.setPrice(toInsert.getPrice());
        product.setProdDetail(toInsert.getProdDetail());
        product.setProdName(toInsert.getProdName());
        product.setRegDate(new Date(System.currentTimeMillis()));
        product.setStock(toInsert.getStock());
        if (toInsert.getCategoryNo() != null) {
            product.setCategory(this.categoryService.getCategory(toInsert.getCategoryNo()));
        }
        this.productRepository.insertProduct(product);
        return AddProductResponseDto.from(product);
    }

    private String storeFile(String base64ImageData, String contextRealPath, String imageName) {
        if (base64ImageData == null || imageName == null) {
            return null;
        }
        int extensionIndex = imageName.lastIndexOf(".");
        String extension = imageName.substring(extensionIndex);
        String filename = RandomSerialGenerator.generate() + extension;
        String uploadPath = contextRealPath + File.separator + filename;

        byte[] encodedData = Base64.getDecoder().decode(base64ImageData);
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(new File(uploadPath).toPath()))) {
            bos.write(encodedData);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(); // TODO
        }
        return filename;
    }

    @Override
    public GetProductResponseDto getProduct(int prodNo, User loginUser) {
        Optional<Product> result = productRepository.findById(prodNo);
        GetProductResponseDto responseDTO
                = GetProductResponseDto.from(result.orElseThrow(() -> new IllegalArgumentException(
                "No record for the given prodNo: " + prodNo)));
        responseDTO.setPurchasable(loginUser != null && loginUser.getRole() == Role.USER && responseDTO.getStock() > 0);
        return responseDTO;
    }

    @Override
    public ListProductResponseDto getProductList(ListProductRequestDto requestDto) {
        requestDto.setPageSize(requestDto.getPageSize() == null ? defaultPageSize : requestDto.getPageSize());
        List<Product> result = ListQueryHelper.findProductList(this.productRepository, requestDto);
        int count = ListQueryHelper.count(this.productRepository, requestDto);

        int page = IntegerUtil.getOneIfNull(requestDto.getPage());
        int pageSize = IntegerUtil.getOneIfNull(requestDto.getPage());

        Page pageInfo = Page.of(page, count, defaultPageSize, defaultPageDisplay);

        List<Category> categories = this.categoryService.getCategoryList();

        SearchCondition searchCondition = requestDto.getSearchCondition() == null
                                          ? SearchCondition.NO_CONDITION
                                          : requestDto.getSearchCondition();


        return ListProductResponseDto.builder()
                .count(count)
                .products(result)
                .categories(categories)
                .pageInfo(pageInfo)
                .menuMode(requestDto.getMenu())
                .searchInfo(new Search(searchCondition.getConditionCode(),
                                       StringUtil.null2nullStr(requestDto.getSearchKeyword()),
                                       (page - 1) * pageSize + 1,
                                       page * pageSize))
                .currentCategoryNo(requestDto.getCategoryNo())
                .build();
    }

    @Override
    public List<ProductDto> getProductList(List<Integer> prodNos) {
        Map<Integer, Product> prodNoProductMap = this.productRepository.findProductsByIds(prodNos);
        return prodNoProductMap.keySet()
                .stream()
                .map(prodNoProductMap::get)
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateProductResponseDto updateProduct(int prodNo, UpdateProductRequestDto requestDTO) {
        Product previous = this.productRepository.findById(prodNo)
                .orElseThrow(() -> new IllegalArgumentException("No such record for given prodNo:" + prodNo));
        Product to = new Product();
        to.setProdNo(prodNo);
        to.setFileName(requestDTO.getFileName());
        to.setManuDate(StringUtil.parseDate(requestDTO.getManuDate(), "-"));
        to.setPrice(requestDTO.getPrice());
        to.setProdDetail(requestDTO.getProdDetail());
        to.setProdName(requestDTO.getProdName());
        to.setRegDate(new Date(System.currentTimeMillis()));
        to.setStock(requestDTO.getStock());
        to.setCategory(new Category(requestDTO.getCategoryNo()));
        this.productRepository.updateProduct(to);
        return UpdateProductResponseDto.from(previous);
    }

    @Override
    public Category addCategory(String categoryName) {
        return this.categoryService.insertCategory(categoryName);
    }

    @Override
    public List<Category> getCategoryList() {
        return this.categoryService.getCategoryList();
    }
}
