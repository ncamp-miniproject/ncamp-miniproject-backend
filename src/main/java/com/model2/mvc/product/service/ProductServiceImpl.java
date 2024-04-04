package com.model2.mvc.product.service;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.util.BeanUtil;
import com.model2.mvc.common.util.IntegerUtil;
import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.domain.ProductImage;
import com.model2.mvc.product.dto.request.CreateProductRequestDto;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.dto.request.UpdateProductRequestDto;
import com.model2.mvc.product.dto.response.AddProductResponseDto;
import com.model2.mvc.product.dto.response.GetProductResponseDto;
import com.model2.mvc.product.dto.response.ListProductResponseDto;
import com.model2.mvc.product.dto.response.ProductDto;
import com.model2.mvc.product.dto.response.UpdateProductResponseDto;
import com.model2.mvc.product.repository.ProductImageRepository;
import com.model2.mvc.product.repository.ProductRepository;
import com.model2.mvc.product.service.helper.ListQueryHelper;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
@Transactional(value = "transactionManager")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryService categoryService;
    private final ListQueryHelper listQueryHelper;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Override
    public AddProductResponseDto addProduct(CreateProductRequestDto toInsert, String contextRealPath) {
        Product product = null;
        try {
            product = BeanUtil.doMapping(Product.class, toInsert);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        product.setRegDate(new Date(System.currentTimeMillis()));
        if (toInsert.getCategoryNo() != null) {
            product.setCategory(this.categoryService.getCategory(toInsert.getCategoryNo()));
        }
        this.productRepository.insertProduct(product);

        Integer prodNo = product.getProdNo();

        List<ProductImage> productImages = toInsert.getProductImageDto().stream().map(pi -> {
            String fileName = storeFile(pi.getBase64Data(), contextRealPath, pi.getFileExtension());
            return ProductImage.builder()
                    .prodNo(prodNo)
                    .fileName(fileName)
                    .description(pi.getDescription())
                    .thumbnail(pi.getThumbnail())
                    .build();
        }).collect(Collectors.toList());
        if (!this.productImageRepository.insert(productImages)) {
            throw new RuntimeException(); // TODO: Specific exception
        }
        return AddProductResponseDto.from(product);
    }

    private String storeFile(String base64ImageData, String contextRealPath, String fileExtension) {
        if (base64ImageData == null || fileExtension == null) {
            return null;
        }
        String filename = RandomSerialGenerator.generate(30) + fileExtension;
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
        List<Product> result = this.listQueryHelper.findProductList(this.productRepository, requestDto);
        int count = this.listQueryHelper.count(this.productRepository, requestDto);

        int page = IntegerUtil.getOneIfNull(requestDto.getPage());
        int pageSize = requestDto.getPageSize() == null ? defaultPageSize : requestDto.getPageSize();

        Page pageInfo = Page.of(page, count, pageSize, defaultPageDisplay);

        return ListProductResponseDto.builder().count(count).products(result).pageInfo(pageInfo).build();
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
        // TODO: remove
        return this.categoryService.insertCategory(categoryName);
    }

    @Override
    public List<Category> getCategoryList() {
        // TODO: remove
        return this.categoryService.getCategoryList();
    }
}
