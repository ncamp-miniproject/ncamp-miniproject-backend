package com.model2.mvc.product.service;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.AddProductResponseDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.dto.response.UpdateProductResponseDTO;
import com.model2.mvc.product.repository.ProductRepository;
import com.model2.mvc.product.service.helper.ListQueryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
    public AddProductResponseDTO addProduct(AddProductRequestDTO toInsert, String contextRealPath) {
        Product product = new Product();
        product.setFileName(storeFile(toInsert.getImageFile(), contextRealPath));
        product.setManuDate(StringUtil.parseDate(toInsert.getManuDate(), "-"));
        product.setPrice(toInsert.getPrice());
        product.setProdDetail(toInsert.getProdDetail());
        product.setProdName(toInsert.getProdName());
        product.setRegDate(new Date(System.currentTimeMillis()));
        product.setStock(toInsert.getStock());
        if (toInsert.getCategoryNo() != null) {
            product.setCategory(this.categoryService.getCategory(toInsert.getCategoryNo()));
        }
        this.productRepository.insertProduct(product);
        return AddProductResponseDTO.from(product);
    }

    private String storeFile(MultipartFile file, String contextRealPath) {
        System.out.println(file.getSize());
        if (file.getSize() == 0) {
            return null;
        }
        int extensionIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(extensionIndex);
        String filename = RandomSerialGenerator.generate() + extension;
        String uploadPath = contextRealPath + File.separator + filename;
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(new File(uploadPath).toPath()))) {
            byte[] fileData = file.getBytes();
            bos.write(fileData);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(); // TODO
        }
        return filename;
    }

    @Override
    public GetProductResponseDTO getProduct(int prodNo) {
        Optional<Product> result = productRepository.findById(prodNo);
        return GetProductResponseDTO.from(result.orElseThrow(() -> new IllegalArgumentException(
                "No record for the given prodNo: " + prodNo)));
    }

    @Override
    public ListProductResponseDTO getProductList(ListProductRequestDTO requestDTO) {
        requestDTO.setPageSize(requestDTO.getPageSize() == null ? defaultPageSize : requestDTO.getPageSize());
        ListData<Product> resultMap = ListQueryHelper.findProductList(this.productRepository, requestDTO);

        int page = resultMap.getPage();
        int pageSize = resultMap.getPageSize();

        Page pageInfo = Page.of(page, resultMap.getCount(), defaultPageSize, defaultPageDisplay);

        List<Category> categories = this.categoryService.getCategoryList();

        return ListProductResponseDTO.from(resultMap,
                                           categories,
                                           pageInfo,
                                           requestDTO,
                                           new Search(resultMap.getSearchCondition().getConditionCode(),
                                                      StringUtil.null2nullStr(requestDTO.getSearchKeyword()),
                                                      (page - 1) * pageSize + 1,
                                                      page * pageSize));
    }

    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO requestDTO) {
        Product previous = this.productRepository.findById(requestDTO.getProdNo())
                .orElseThrow(() -> new IllegalArgumentException("No such record for given prodNo:" +
                                                                requestDTO.getProdNo()));
        Product to = new Product();
        to.setProdNo(requestDTO.getProdNo());
        to.setFileName(requestDTO.getFileName());
        to.setManuDate(StringUtil.parseDate(requestDTO.getManuDate(), "-"));
        to.setPrice(requestDTO.getPrice());
        to.setProdDetail(requestDTO.getProdDetail());
        to.setProdName(requestDTO.getProdName());
        to.setRegDate(new Date(System.currentTimeMillis()));
        to.setStock(requestDTO.getStock());
        to.setCategory(new Category(requestDTO.getCategoryNo()));
        this.productRepository.updateProduct(to);
        return UpdateProductResponseDTO.from(previous);
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
