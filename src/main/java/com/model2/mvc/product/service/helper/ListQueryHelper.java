package com.model2.mvc.product.service.helper;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.util.OptionalHashMap;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ListQueryHelper {

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

    public static ListData<Product> findProductList(ProductRepository repository, ListProductRequestDTO dto) {
        Optional<BiFunction<ProductRepository, ListProductRequestDTO, ListData<Product>>> funcOptional
                = listTaskMapper.getOptional(dto.getSearchCondition());
        BiFunction<ProductRepository, ListProductRequestDTO, ListData<Product>> func = funcOptional.orElse(
                listTaskMapper.get(SearchCondition.NO_CONDITION));
        ListData<Product> listData = func.apply(repository, dto);
        setPageAndPageSize(listData, getOneIfNull(dto.getPage()), getOneIfNull(dto.getPageSize()));
        listData.setSearchCondition(dto.getSearchCondition() == null
                                    ? SearchCondition.NO_CONDITION
                                    : dto.getSearchCondition());
        return listData;
    }

    private static void setPageAndPageSize(ListData<Product> listData, int page, int pageSize) {
        listData.setPage(page);
        listData.setPageSize(pageSize);
    }
}
