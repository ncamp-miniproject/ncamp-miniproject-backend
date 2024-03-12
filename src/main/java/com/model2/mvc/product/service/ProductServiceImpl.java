package com.model2.mvc.product.service;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.util.OptionalHashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
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
    public AddProductResponseDTO addProduct(AddProductRequestDTO toInsert) {
        Product product = new Product();
        product.setFileName(toInsert.getFileName());
        product.setManuDate(StringUtil.parseDate(toInsert.getManuDate(), "-"));
        product.setPrice(toInsert.getPrice());
        product.setProdDetail(toInsert.getProdDetail());
        product.setProdName(toInsert.getProdName());
        product.setRegDate(new Date(System.currentTimeMillis()));
        product.setStock(toInsert.getStock());
        this.productRepository.insertProduct(product);
        return AddProductResponseDTO.from(product);
    }

    @Override
    public GetProductResponseDTO getProduct(int prodNo) {
        Optional<Product> result = productRepository.findById(prodNo);

        result.ifPresent(p -> {
            System.out.println("-- ProductServiceImpl.getProduct() --");
            System.out.println(p + "\n");
        });
        return GetProductResponseDTO.from(result.orElseThrow(() -> new IllegalArgumentException(
                "No record for the given prodNo: " + prodNo)));
    }

    private static final OptionalHashMap<SearchCondition, BiFunction<ProductRepository, ListProductRequestDTO, ListData<Product>>>
            listTaskMapper = new OptionalHashMap<>();

    static {
        listTaskMapper.put(SearchCondition.NO_CONDITION,
                           (repository, dto) -> repository.findAllInCategory(getOneIfNull(dto.getPage()),
                                                                             getOneIfNull(dto.getPageSize()),
                                                                             dto.getCategoryNo()));
        listTaskMapper.put(SearchCondition.BY_NAME,
                           (repository, dto) -> repository.findListByProdName(StringUtil.null2nullStr(dto.getSearchKeyword()),
                                                                              false,
                                                                              getOneIfNull(dto.getPage()),
                                                                              getOneIfNull(dto.getPageSize()),
                                                                              dto.getCategoryNo()));
        listTaskMapper.put(SearchCondition.BY_INTEGER_RANGE, (repository, dto) -> {
            List<Integer> boundPair = Arrays.stream(dto.getSearchKeyword().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            Integer lowerBound = null;
            Integer upperBound = null;
            if (boundPair.size() > 2) {
                throw new IllegalArgumentException();
            } else if (boundPair.size() == 2) {
                lowerBound = boundPair.get(0);
                upperBound = boundPair.get(1);
            } else if (boundPair.size() == 1) {
                if (dto.getSearchKeyword().startsWith(",")) {
                    upperBound = boundPair.get(0);
                } else {
                    lowerBound = boundPair.get(0);
                }
            }
            return repository.findListByPriceRange(lowerBound,
                                                   upperBound,
                                                   getOneIfNull(dto.getPage()),
                                                   getOneIfNull(dto.getPageSize()),
                                                   dto.getCategoryNo());
        });
    }

    private static Integer getOneIfNull(Integer num) {
        return num == null ? 1 : num;
    }

    @Override
    public ListProductResponseDTO getProductList(ListProductRequestDTO requestDTO) {
        ListData<Product> resultMap = listTaskMapper.getOptional(requestDTO.getSearchCondition())
                .orElse(listTaskMapper.get(SearchCondition.NO_CONDITION))
                .apply(this.productRepository, requestDTO);

        Integer page = getOneIfNull(requestDTO.getPage());
        Integer pageSize = getOneIfNull(requestDTO.getPageSize());

        Page pageInfo = Page.of(page, resultMap.getCount(), defaultPageSize, defaultPageDisplay);

        List<Category> categories = this.categoryService.getCategoryList();

        return ListProductResponseDTO.from(resultMap,
                                           categories,
                                           pageInfo,
                                           requestDTO,
                                           new Search(StringUtil.null2nullStr(requestDTO.getSearchCondition()
                                                                                      .getConditionCode()),
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
        this.productRepository.updateProduct(to);
        return UpdateProductResponseDTO.from(previous);
    }

    @Override
    public Category addCategory(String categoryName) {
        return this.categoryService.insertCategory(categoryName);
    }
}
